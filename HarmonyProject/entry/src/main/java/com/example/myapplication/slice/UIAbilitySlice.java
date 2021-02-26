package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Text;

public class UIAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_ui);

        initView();
    }

    private void initView() {
        Text tvText = (Text) findComponentById(ResourceTable.Id_tvText);
        Text tvButton = (Text) findComponentById(ResourceTable.Id_tvButton);
        Text tvTextField = (Text) findComponentById(ResourceTable.Id_tvTextField);
        Text tvImage = (Text) findComponentById(ResourceTable.Id_tvImage);
        Text tvTab = (Text) findComponentById(ResourceTable.Id_tvTab);
        Text tvPicker = (Text) findComponentById(ResourceTable.Id_tvPicker);
        Text tvDatePicker = (Text) findComponentById(ResourceTable.Id_tvDatePicker);
        Text tvTimePicker = (Text) findComponentById(ResourceTable.Id_tvTimePicker);
        Text tvSwitch = (Text) findComponentById(ResourceTable.Id_tvSwitch);
        Text tvRadioButton = (Text) findComponentById(ResourceTable.Id_tvRadioButton);
        Text tvRadioContainer = (Text) findComponentById(ResourceTable.Id_tvRadioContainer);
        Text tvCheckBox = (Text) findComponentById(ResourceTable.Id_tvCheckBox);
        Text tvProgress = (Text) findComponentById(ResourceTable.Id_tvProgress);
        Text tvListContainer = (Text) findComponentById(ResourceTable.Id_tvListContainer);
        Text tvScrollView = (Text) findComponentById(ResourceTable.Id_tvScrollView);


        tvText.setClickedListener(component -> present(new TextAbilitySlice(), new Intent()));
        tvButton.setClickedListener(component -> present(new ButtonAbilitySlice(), new Intent()));
        tvTextField.setClickedListener(component -> present(new TextFieldAbilitySlice(), new Intent()));
        tvImage.setClickedListener(component -> present(new ImageAbilitySlice(), new Intent()));
        tvTab.setClickedListener(component -> present(new TabAbilitySlice(), new Intent()));
        tvPicker.setClickedListener(component -> present(new PickerAbilitySlice(), new Intent()));
        tvDatePicker.setClickedListener(component -> present(new DatePickerAbilitySlice(), new Intent()));
        tvTimePicker.setClickedListener(component -> present(new TimePickerAbilitySlice(), new Intent()));
        tvSwitch.setClickedListener(component -> present(new SwitchAbilitySlice(), new Intent()));
        tvRadioButton.setClickedListener(component -> present(new RadioButtonAbilitySlice(), new Intent()));
        tvRadioContainer.setClickedListener(component -> present(new RadioContainerAbilitySlice(), new Intent()));
        tvCheckBox.setClickedListener(component -> present(new CheckBoxAbilitySlice(), new Intent()));
        tvProgress.setClickedListener(component -> present(new ProgressBarAbilitySlice(), new Intent()));
        tvProgress.setClickedListener(component -> present(new ProgressBarAbilitySlice(), new Intent()));
        tvListContainer.setClickedListener(component -> present(new ListContainerAbilitySlice(), new Intent()));
        tvScrollView.setClickedListener(component -> present(new ScrollViewAbilitySlice(), new Intent()));


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
