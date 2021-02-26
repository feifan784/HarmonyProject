package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import com.example.myapplication.bean.SettingItem;
import com.example.myapplication.provider.ListContainerProvider;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ListContainer;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;

import java.util.ArrayList;
import java.util.List;


public class ListContainerAbilitySlice extends AbilitySlice {
    private ListContainer lcNext;
    private ListContainerProvider provider;


    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_layout_first);

        initView();

    }

    private void initView() {
        lcNext = (ListContainer) findComponentById(ResourceTable.Id_lc_next);
        provider = new ListContainerProvider(getData(), this);
        lcNext.setItemProvider(provider);

        lcNext.setItemClickedListener((listContainer, component, position, l) -> new ToastDialog(getContext())
                .setText("Choose position:  " + position)
                .setAlignment(LayoutAlignment.CENTER)
                .show());
    }

    private List<SettingItem> getData() {
        ArrayList<SettingItem> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(new SettingItem(
                    ResourceTable.Media_icon,
                    "SettingName" + i,
                    i % 3 == 0
            ));
        }
        return data;
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
