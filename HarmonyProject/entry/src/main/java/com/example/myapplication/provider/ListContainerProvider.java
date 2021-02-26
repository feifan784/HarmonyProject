package com.example.myapplication.provider;

import com.example.myapplication.ResourceTable;
import com.example.myapplication.bean.SettingItem;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.components.element.StateElement;

import java.util.List;

public class ListContainerProvider extends BaseItemProvider {

    private List<SettingItem> settingList;
    private AbilitySlice slice;

    public ListContainerProvider(List<SettingItem> settingList, AbilitySlice slice) {
        this.settingList = settingList;
        this.slice = slice;
    }

    @Override
    public int getCount() {
        return settingList == null ? 0 : settingList.size();
    }

    @Override
    public Object getItem(int position) {
        if (settingList != null && position > 0 && position < settingList.size()) {
            return settingList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Component getComponent(int position, Component component, ComponentContainer componentContainer) {
        Component cpt;
        SettingHolder holder;
        SettingItem setting = settingList.get(position);

        if (component == null) {
            cpt = LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_layout_provider_next, null, false);
            holder = new SettingHolder(cpt);
            cpt.setTag(holder);
        } else {
            cpt = component;
            holder = (SettingHolder) cpt.getTag();
        }
        holder.settingIma.setPixelMap(setting.getImageId());
        holder.settingText.setText(setting.getSettingName());
        holder.settingSwitch.setChecked(setting.isChecked());
        return cpt;
    }

    public class SettingHolder {
        Image settingIma;
        Text settingText;
        Switch settingSwitch;

        SettingHolder(Component component) {
            settingIma = (Image) component.findComponentById(ResourceTable.Id_ima_setting);
            settingText = (Text) component.findComponentById(ResourceTable.Id_text_setting);
            settingSwitch = (Switch) component.findComponentById(ResourceTable.Id_switch_setting);

            settingSwitch.setTrackElement(trackElementInit(
                    new ShapeElement(slice, ResourceTable.Graphic_track_on_element),
                    new ShapeElement(slice, ResourceTable.Graphic_track_off_element)));

            settingSwitch.setThumbElement(thumbElementInit(
                    new ShapeElement(slice, ResourceTable.Graphic_thumb_on_element),
                    new ShapeElement(slice, ResourceTable.Graphic_thumb_off_element)));
        }

        private StateElement trackElementInit(ShapeElement on, ShapeElement off) {
            StateElement trackElement = new StateElement();
            trackElement.addState(new int[]{ComponentState.COMPONENT_STATE_CHECKED}, on);
            trackElement.addState(new int[]{ComponentState.COMPONENT_STATE_EMPTY}, off);
            return trackElement;
        }


        private StateElement thumbElementInit(ShapeElement on, ShapeElement off) {
            StateElement thumbElement = new StateElement();
            thumbElement.addState(new int[]{ComponentState.COMPONENT_STATE_CHECKED}, on);
            thumbElement.addState(new int[]{ComponentState.COMPONENT_STATE_EMPTY}, off);
            return thumbElement;
        }
    }
}
