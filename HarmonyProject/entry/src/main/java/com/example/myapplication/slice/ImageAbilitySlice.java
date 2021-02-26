package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Text;

public class ImageAbilitySlice extends AbilitySlice {

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_layout_image);

        initView();

    }

    private void initView() {

        Text textImage = (Text) findComponentById(ResourceTable.Id_text_image);
        textImage.setClickedListener(component -> present(new LoadNetImageAbilitySlice(), new Intent()));

    }
}
