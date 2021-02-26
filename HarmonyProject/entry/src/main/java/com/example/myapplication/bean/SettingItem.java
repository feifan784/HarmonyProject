package com.example.myapplication.bean;

public class SettingItem {
    private int imageId;
    private String settingName;
    private boolean isChecked;

    public SettingItem(int imageId, String settingName, boolean isChecked) {
        this.imageId = imageId;
        this.settingName = settingName;
        this.isChecked = isChecked;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
