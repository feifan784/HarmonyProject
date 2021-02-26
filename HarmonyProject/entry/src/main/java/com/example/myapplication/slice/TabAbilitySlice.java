package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.TabList;
import ohos.agp.components.Text;
import ohos.agp.window.dialog.ToastDialog;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class TabAbilitySlice extends AbilitySlice {

    private static final HiLogLabel label = new HiLogLabel(3, 0xD001100, "TabAbilitySlice");

    private TabList tab_list;

    private Text tvClick;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_layout_tab);

        initView();

    }

    private void initView() {
        tvClick = (Text) findComponentById(ResourceTable.Id_tvClick);

        tab_list = (TabList) findComponentById(ResourceTable.Id_tab_list);
        TabList.Tab tab = tab_list.new Tab(getContext());
        tab.setText("News");
        tab_list.addTab(tab);
        TabList.Tab tab2 = tab_list.new Tab(getContext());
        tab2.setText("Image");
        tab_list.addTab(tab2, true);
        TabList.Tab tab4 = tab_list.new Tab(getContext());
        tab4.setText("Picture");
        tab_list.addTab(tab4);
        TabList.Tab tab5 = tab_list.new Tab(getContext());
        tab5.setText("Video");
        tab_list.addTab(tab5);

        tvClick.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                TabList.Tab tab = tab_list.new Tab(getContext());
                tab.setText("Add");
                tab.setMinWidth(64);
                tab.setPadding(12, 0, 12, 0);
                tab_list.addTab(tab, 1); // 1表示位置
            }
        });

        tab_list.addTabSelectedListener(new TabList.TabSelectedListener() {
            @Override
            public void onSelected(TabList.Tab tab) {
                HiLog.info(label, "1111111111111111111---" + tab.getText());
                new ToastDialog(getContext())
                        .setText(tab.getText())
                        .show();
            }

            @Override
            public void onUnselected(TabList.Tab tab) {

            }

            @Override
            public void onReselected(TabList.Tab tab) {

            }
        });


    }
}
