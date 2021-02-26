package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.DatePicker;
import ohos.agp.components.Text;

public class DatePickerAbilitySlice extends AbilitySlice {

    private DatePicker dp;
    private Text tvDate;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_layout_data_picker);

        initView();

    }

    private void initView() {
        dp = (DatePicker) findComponentById(ResourceTable.Id_dp);
        tvDate = (Text) findComponentById(ResourceTable.Id_tvDate);

        dp.setValueChangedListener((datePicker, year, month, day) -> tvDate.setText("Current Select Date:  " + year + " / " + month + " / " + day));

    }
}
