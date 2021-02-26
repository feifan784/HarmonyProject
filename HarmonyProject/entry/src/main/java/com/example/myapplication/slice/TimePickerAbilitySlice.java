package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Text;
import ohos.agp.components.TimePicker;

public class TimePickerAbilitySlice extends AbilitySlice {

    private TimePicker tp;
    private Text tvTime;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_layout_time_picker);

        initView();

    }

    private void initView() {

        tp = (TimePicker) findComponentById(ResourceTable.Id_tp);
        tvTime = (Text) findComponentById(ResourceTable.Id_tvTime);

        tp.setTimeChangedListener((timePicker, hour, minute, second) -> tvTime.setText("Current Select Time:" + hour + " / " + minute + " / " + second));

    }
}
