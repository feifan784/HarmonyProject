package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.IAbilityConnection;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.*;
import ohos.agp.window.dialog.ToastDialog;
import ohos.bundle.ElementName;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.rpc.IRemoteObject;

public class MainAbilitySlice extends AbilitySlice {
    private int count = 0;
    private static final HiLogLabel label = new HiLogLabel(3, 0xD001100, "MainAbilitySlice");


    // 创建连接回调实例
    private IAbilityConnection connection = new IAbilityConnection() {
        // 连接到Service的回调
        @Override
        public void onAbilityConnectDone(ElementName elementName, IRemoteObject iRemoteObject, int resultCode) {
            // Client侧需要定义与Service侧相同的IRemoteObject实现类。开发者获取服务端传过来IRemoteObject对象，并从中解析出服务端传过来的信息。
            HiLog.info(label, "链接上了");
        }

        // 断开与Service连接的回调
        @Override
        public void onAbilityDisconnectDone(ElementName elementName, int resultCode) {
            HiLog.info(label, "断开了");
            new ToastDialog(getContext())
                    .setText("/UI11/   断开了 ")
                    .show();
        }
    };

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        Text text = (Text) findComponentById(ResourceTable.Id_text_helloworld);
        Text textHandler = (Text) findComponentById(ResourceTable.Id_text_handler);
        Text textAnimator = (Text) findComponentById(ResourceTable.Id_text_animator);
//        Text tvJump = (Text) findComponentById(ResourceTable.Id_tvJump);
        Text tvUI = (Text) findComponentById(ResourceTable.Id_tvUI);
        Text tvNavigation = (Text) findComponentById(ResourceTable.Id_tvNavigation);
        Text tvThreadManage = (Text) findComponentById(ResourceTable.Id_tvThreadManage);
        Text tvStartService = (Text) findComponentById(ResourceTable.Id_tvStartService);
        Text tvConnectService = (Text) findComponentById(ResourceTable.Id_tvConnectService);
        Text tvMainMove = (Text) findComponentById(ResourceTable.Id_tvMainMove);

        tvMainMove.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                Intent intent1 = new Intent();

                // 通过Intent中的OperationBuilder类构造operation对象，指定设备标识（空串表示当前设备）、应用包名、Ability名称
                Operation operation = new Intent.OperationBuilder()
                        .withDeviceId("")
                        .withBundleName("com.example.myapplication")
                        .withAbilityName("com.example.myapplication.ability.MoveAbility")
                        .build();

                // 把operation设置到intent中
                intent1.setOperation(operation);
                startAbility(intent1);
            }
        });

        tvConnectService.setClickedListener(component -> {
            Intent intentConnect = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId("")
                    .withBundleName("com.example.myapplication")
                    .withAbilityName("com.example.myapplication.service.OneServiceAbility")
                    .build();
            intentConnect.setOperation(operation);
            connectAbility(intentConnect, connection);
        });

        tvStartService.setClickedListener(component -> {
            Intent intentStart = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId("")
                    .withBundleName("com.example.myapplication")
                    .withAbilityName("com.example.myapplication.service.OneServiceAbility")
                    .build();
            intentStart.setOperation(operation);
            startAbility(intentStart);
        });

        tvThreadManage.setClickedListener(component -> present(new ThreadManageAbilitySlice(), new Intent()));

        tvNavigation.setClickedListener(component -> {
            Intent intent1 = new Intent();

            // 通过Intent中的OperationBuilder类构造operation对象，指定设备标识（空串表示当前设备）、应用包名、Ability名称
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId("")
                    .withBundleName("com.example.myapplication")
                    .withAbilityName("com.example.myapplication.ability.NavigationAbility")
                    .build();

            // 把operation设置到intent中
            intent1.setOperation(operation);
            startAbility(intent1);
        });
        tvUI.setClickedListener(component -> {
            Intent intent1 = new Intent();

            // 通过Intent中的OperationBuilder类构造operation对象，指定设备标识（空串表示当前设备）、应用包名、Ability名称
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId("")
                    .withBundleName("com.example.myapplication")
                    .withAbilityName("com.example.myapplication.ability.UIAbility")
                    .build();

            // 把operation设置到intent中
            intent1.setOperation(operation);
            startAbility(intent1);
        });

//        tvJump.setClickedListener(component -> {
//            Intent intent1 = new Intent();
//
//            // 通过Intent中的OperationBuilder类构造operation对象，指定设备标识（空串表示当前设备）、应用包名、Ability名称
//            Operation operation = new Intent.OperationBuilder()
//                    .withDeviceId("")
//                    .withBundleName("com.example.myapplication")
//                    .withAbilityName("com.example.myapplication.ability.UIAbility")
//                    .build();
//
//            // 把operation设置到intent中
//            intent1.setOperation(operation);
//            startAbility(intent1);
//        });

        Button button = (Button) findComponentById(ResourceTable.Id_button_count);

        button.setClickedListener(component -> {
            count++;
            text.setText(String.valueOf(count));
        });

        textHandler.setClickedListener(component -> present(new HandlerAbilitySlice(), new Intent()));

        textAnimator.setClickedListener(component -> present(new AnimatorAbilitySlice(), new Intent()));

    }


    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    protected void onBackground() {
        super.onBackground();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        terminateAbility();
    }
}
