package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.animation.Animator;
import ohos.agp.animation.AnimatorProperty;
import ohos.agp.animation.AnimatorValue;
import ohos.agp.components.Button;

public class AnimatorAbilitySlice extends AbilitySlice {

    private Button buttonValue;
    private Button buttonProperty;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_layout_animator);

        buttonValue = (Button) findComponentById(ResourceTable.Id_btAnimatorValue);
        buttonProperty = (Button) findComponentById(ResourceTable.Id_btAnimatorProperty);

        initAnimationValue();

        initAnimationProperty();


    }

    private void initAnimationProperty() {

        AnimatorProperty animatorProperty = buttonProperty.createAnimatorProperty();
        animatorProperty.moveFromX(50).moveToX(1000).rotate(90).scaleX(2).scaleY(2).alpha(0).setDelay(500).setDuration(3000).setLoopedCount(2);

        //页面显示时候启动
//        buttonProperty.setBindStateChangedListener(new Component.BindStateChangedListener() {
//            @Override
//            public void onComponentBoundToWindow(Component component) {
//                animatorProperty.start();
//            }
//
//            @Override
//            public void onComponentUnboundFromWindow(Component component) {
//                animatorProperty.stop();
//            }
//        });

        buttonProperty.setClickedListener(component -> animatorProperty.start());
    }

    private void initAnimationValue() {
        AnimatorValue animatorValue = new AnimatorValue();
        animatorValue.setDuration(2000);
        animatorValue.setDelay(1000);
        animatorValue.setLoopedCount(2);
        animatorValue.setCurveType(Animator.CurveType.BOUNCE);

        animatorValue.setValueUpdateListener(new AnimatorValue.ValueUpdateListener() {
            @Override
            public void onUpdate(AnimatorValue animatorValue, float v) {

                buttonValue.setContentPosition((int) (800 * v), buttonValue.getContentPositionY());

            }
        });

        buttonValue.setClickedListener(component -> animatorValue.start());
    }

    @Override
    protected void onBackground() {
        super.onBackground();


    }
}
