# PermissionRequest
Android动态申请权限工具，这是个demo。可以把demo中permission模块中的三各类拷走直接用，demo中有使用方法，使用方法贼简单
com.permission.rjq.permission.PermissionRequest permissionRequest = new com.permission.rjq.permission.PermissionRequest(this);

        permissionRequest.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, new PermissionListener() {

            @Override

            public void onGranted() {

                //获取权限后的操作

                Toast.makeText(MainActivity.this, "您获得了权限", Toast.LENGTH_SHORT).show();

            }



            @Override

            public void onDenied(List<String> deniedPermission) {

                //禁止权限后的操作

                Toast.makeText(MainActivity.this, "您禁止了权限", Toast.LENGTH_SHORT).show();

            }



            @Override

            public void onShouldShowRationale(List<String> deniedPermission) {

                //禁止权限并且选择了不再提示的操作

                Toast.makeText(MainActivity.this, "您禁止并且选择了不再提示权限", Toast.LENGTH_SHORT).show();

            }

        });
欢迎star，
