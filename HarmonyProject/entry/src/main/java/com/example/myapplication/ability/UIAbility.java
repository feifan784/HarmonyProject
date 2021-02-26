package com.example.myapplication.ability;

import com.example.myapplication.slice.UIAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.window.dialog.ToastDialog;
import ohos.bundle.IBundleManager;

public class UIAbility extends Ability {
    private final static int MY_PERMISSIONS_REQUEST_CAMERA = 100;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(UIAbilitySlice.class.getName());


        if (verifySelfPermission("ohos.permission.CAMERA") != IBundleManager.PERMISSION_GRANTED) {
            // 应用未被授予权限
            if (canRequestPermission("ohos.permission.CAMERA")) {
                // 是否可以申请弹框授权(首次申请或者用户未选择禁止且不再提示)
                requestPermissionsFromUser(
                        new String[]{"ohos.permission.CAMERA"}, MY_PERMISSIONS_REQUEST_CAMERA);
            } else {
                // 显示应用需要权限的理由，提示用户进入设置授权
                new ToastDialog(getContext())
                        .setText("为了测试动态授予权限")
                        .show();
            }
        } else {
            // 权限已被授予
            new ToastDialog(getContext())
                    .setText("已授予过权限")
                    .show();
        }
    }

    @Override
    public void onRequestPermissionsFromUserResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // 匹配requestPermissions的requestCode
                if (grantResults.length > 0
                        && grantResults[0] == IBundleManager.PERMISSION_GRANTED) {

                    // 权限被授予
                    // 注意：因时间差导致接口权限检查时有无权限，所以对那些因无权限而抛异常的接口进行异常捕获处理
                    new ToastDialog(getContext())
                            .setText("权限被授予")
                            .show();
                } else {
                    // 权限被拒绝
                    new ToastDialog(getContext())
                            .setText("权限被拒绝")
                            .show();
                }
                return;
            }
        }
    }
}
