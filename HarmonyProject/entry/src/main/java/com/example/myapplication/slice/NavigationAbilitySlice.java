package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class NavigationAbilitySlice extends AbilitySlice {
    private static final HiLogLabel label = new HiLogLabel(3, 0xD001100, "NavigationAbilitySlice");

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_navigation_activity);
        Text tvOne = (Text) findComponentById(ResourceTable.Id_tvOnePage);
        tvOne.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {

//                present(new NavigationAbilityTwoSlice(), new Intent());

                presentForResult(new NavigationAbilityTwoSlice(), new Intent(), 0);


            }
        });
    }

    @Override
    protected void onResult(int requestCode, Intent resultIntent) {
        super.onResult(requestCode, resultIntent);
        if (requestCode == 0) {
            // Process resultIntent here.
            HiLog.info(label, "同一个Page内导航后回来了-------" + requestCode);
        }
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
