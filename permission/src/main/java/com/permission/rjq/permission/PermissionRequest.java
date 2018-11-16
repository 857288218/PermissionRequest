package com.permission.rjq.permission;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import java.lang.ref.WeakReference;
import java.util.List;

public class PermissionRequest {
    private static final String TAG = "PermissionsUtil";
    private PermissionFragment fragment;
    private static WeakReference<Activity> activity;

    public PermissionRequest(@NonNull Activity activity) {
        fragment = getPermissionsFragment(activity);
        PermissionRequest.activity = new WeakReference<>(activity);
    }

    private PermissionFragment getPermissionsFragment(Activity activity) {
        PermissionFragment fragment = (PermissionFragment) activity.getFragmentManager().findFragmentByTag(TAG);
        boolean isNewInstance = fragment == null;
        if (isNewInstance) {
            fragment = new PermissionFragment();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(fragment, TAG)
                    .commit();
        }

        return fragment;
    }

    /**
     * 外部调用申请权限
     * @param permissions 申请的权限
     * @param listener 监听权限接口
     */
    public void requestPermissions(String[] permissions, PermissionListener listener) {
        fragment.setListener(listener);
        fragment.requestPermissions(permissions);
    }

    //返回拒绝权限列表
    public static String deniedPermissionToMsg(List<String> deniedPermission){
        StringBuffer sb = new StringBuffer();
        String open = activity.get().getString(R.string.mx_run_permission_open);
        String openText = String.format(open, activity.get().getString(R.string.app_name),activity.get().getString(R.string.app_name));
        sb.append(openText);
        if (deniedPermission.contains(Manifest.permission.CAMERA)){
            sb.append(activity.get().getResources().getString(R.string.mx_run_permission_camera));
        }
        if (deniedPermission.contains(Manifest.permission.READ_CONTACTS)){
            sb.append(activity.get().getResources().getString(R.string.mx_run_permission_read_contacts));
        }
        if (deniedPermission.contains(Manifest.permission.WRITE_CONTACTS)){
            sb.append(activity.get().getResources().getString(R.string.mx_run_permission_write_contacts));
        }
        if (deniedPermission.contains(Manifest.permission.RECORD_AUDIO)){
            sb.append(activity.get().getResources().getString(R.string.mx_run_permission_record_audio));
        }
        if (deniedPermission.contains(Manifest.permission.ACCESS_FINE_LOCATION)||deniedPermission.contains(Manifest.permission.ACCESS_COARSE_LOCATION)){
            sb.append(activity.get().getResources().getString(R.string.mx_run_permission_access_location));
        }
        if (deniedPermission.contains(Manifest.permission.CALL_PHONE)){
            sb.append(activity.get().getResources().getString(R.string.mx_run_permission_call_phone));
        }
        if (deniedPermission.contains(Manifest.permission.BODY_SENSORS)){
            sb.append(activity.get().getResources().getString(R.string.mx_run_permission_body_sensors));
        }
        if (deniedPermission.contains(Manifest.permission.READ_EXTERNAL_STORAGE)||deniedPermission.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            sb.append(activity.get().getResources().getString(R.string.mx_run_permission_external_storage));
        }
        if (deniedPermission.contains(Manifest.permission.SEND_SMS)){
            sb.append(activity.get().getResources().getString(R.string.mx_run_permission_send_sms));
        }
        if (deniedPermission.contains(Manifest.permission.READ_SMS)){
            sb.append(activity.get().getResources().getString(R.string.mx_run_permission_read_sms));
        }
        if (deniedPermission.contains(Manifest.permission.READ_PHONE_STATE)){
            sb.append(activity.get().getResources().getString(R.string.mx_run_permission_read_phone_status));
        }
        sb.append(activity.get().getResources().getString(R.string.mx_run_permission));
        sb.append(activity.get().getString(R.string.mx_run_permission_normal_function));
        return sb.toString();
    }

    public static void showDialog(final Context context,String msg){

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(context.getResources().getString(R.string.mx_run_permission_request))
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(context.getResources().getString(R.string.mx_run_permission_setting), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent =  new Intent(Settings.ACTION_SETTINGS);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("取消", null).create();
        dialog.show();

//        dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.news_text_selected));
//        dialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(context.getResources().getColor(R.color.news_detail_reading_color));
//        TextView tvMsg = (TextView) dialog.findViewById(android.R.id.message);
//        tvMsg.setTextColor(context.getResources().getColor(R.color.news_detail_reading_color));
    }
}
