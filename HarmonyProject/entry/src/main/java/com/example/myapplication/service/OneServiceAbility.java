package com.example.myapplication.service;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.LocalRemoteObject;
import ohos.aafwk.content.Intent;
import ohos.agp.window.dialog.ToastDialog;
import ohos.event.notification.NotificationRequest;
import ohos.rpc.IRemoteObject;

import java.util.Random;

public class OneServiceAbility extends Ability {

    private final Random mGenerator = new Random();

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        //仅调用一次
        // 创建通知，其中1005为notificationId
        NotificationRequest request = new NotificationRequest(1005);
        NotificationRequest.NotificationNormalContent content = new NotificationRequest.NotificationNormalContent();
        content.setTitle("title").setText("text");
        NotificationRequest.NotificationContent notificationContent = new NotificationRequest.NotificationContent(content);
        request.setContent(notificationContent);

        // 绑定通知，1005为创建通知时传入的notificationId
        keepBackgroundRunning(1005, request);
    }

    @Override
    protected void onCommand(Intent intent, boolean restart, int startId) {
        super.onCommand(intent, restart, startId);
        //可计数被调用几次
        new ToastDialog(getContext())
                .setText("/UI11/    " + getGenerator())
                .show();
    }

    private class MyRemoteObject extends LocalRemoteObject {
        public MyRemoteObject() {
            super();
        }
    }

    @Override
    protected IRemoteObject onConnect(Intent intent) {

        return new MyRemoteObject();
        //有缓存IremoteObject对象
    }

    @Override
    protected void onDisconnect(Intent intent) {
        super.onDisconnect(intent);
        //断联
    }

    @Override
    protected void onStop() {
        super.onStop();
        //停止
        cancelBackgroundRunning();
    }

    public String getGenerator() {
        return String.valueOf(mGenerator.nextInt(100));
    }
}
