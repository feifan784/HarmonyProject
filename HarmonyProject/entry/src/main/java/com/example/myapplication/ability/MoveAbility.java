package com.example.myapplication.ability;

import com.example.myapplication.slice.MoveAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.IAbilityContinuation;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.IntentParams;

public class MoveAbility extends Ability implements IAbilityContinuation {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MoveAbilitySlice.class.getName());

        //请求迁移
        try {
            continueAbility();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        //请求完成后数据回迁
        try {
            continueAbilityReversibly();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onStartContinuation() {
        //Page请求迁移后，系统首先回调此方法，开发者可以在此回调中决策当前是否可以执行迁移，比如，弹框让用户确认是否开始迁移
        return false;
    }

    @Override
    public boolean onSaveData(IntentParams intentParams) {
        //如果onStartContinuation()返回true，则系统回调此方法，开发者在此回调中保存必须传递到另外设备上以便恢复Page状态的数据。
        return false;
    }

    @Override
    public boolean onRestoreData(IntentParams intentParams) {
        //源侧设备上Page完成保存数据后，系统在目标侧设备上回调此方法，开发者在此回调中接受用于恢复Page状态的数据。
        // 注意，在目标侧设备上的Page会重新启动其生命周期，无论其启动模式如何配置。且系统回调此方法的时机在onStart()之前。
        return false;
    }

    @Override
    public void onCompleteContinuation(int i) {
        //目标侧设备上恢复数据一旦完成，系统就会在源侧设备上回调Page的此方法，以便通知应用迁移流程已结束。
        // 开发者可以在此检查迁移结果是否成功，并在此处理迁移结束的动作，例如，应用可以在迁移完成后终止自身生命周期。

        //源测发起回迁
        try {
            reverseContinueAbility();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRemoteTerminated() {
        //如果开发者使用continueAbilityReversibly()而不是continueAbility()，则此后可以在源侧设备上使用reverseContinueAbility()进行回迁。
        // 这种场景下，相当于同一个Page（的两个实例）同时在两个设备上运行，迁移完成后，如果目标侧设备上Page因任何原因终止，则源侧Page通过此回调接收终止通知。
    }
}
