package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.*;

public class MainAbilitySlice extends AbilitySlice {
    private int count = 0;

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
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
