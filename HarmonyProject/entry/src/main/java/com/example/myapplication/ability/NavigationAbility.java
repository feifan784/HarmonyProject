package com.example.myapplication.ability;

import com.example.myapplication.slice.NavigationAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class NavigationAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(NavigationAbilitySlice.class.getName());



    }
}
