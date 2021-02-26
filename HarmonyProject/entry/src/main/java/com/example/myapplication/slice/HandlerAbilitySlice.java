package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import com.example.myapplication.handler.MyEventHandler;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Text;
import ohos.eventhandler.EventRunner;
import ohos.eventhandler.InnerEvent;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import static com.example.myapplication.handler.MyEventHandler.*;

/**
 * 线程间通讯
 */
public class HandlerAbilitySlice extends AbilitySlice {

    private EventRunner runner;
    private MyEventHandler handler;
    private static final HiLogLabel label = new HiLogLabel(3, 0xD001100, "HandlerAbilitySlice");
    private int count = 0;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_layout_handler);

        initHandler();

        initView();
    }

    private void initHandler() {

        runner = EventRunner.create("downloadRunner");
        if (runner == null) {
            return;
        }

        handler = new MyEventHandler(runner, this);

    }

    private void initView() {
        Text tvCount = (Text) findComponentById(ResourceTable.Id_tvCount);
        Button one = (Button) findComponentById(ResourceTable.Id_btOne);
        Button two = (Button) findComponentById(ResourceTable.Id_btTwo);
        Button Three = (Button) findComponentById(ResourceTable.Id_btThree);


        one.setClickedListener(component -> {
            count++;
            tvCount.setText(String.valueOf(count));
            HiLog.info(label, "111111111111");

            int eventId = CODE_DOWNLOAD_FILE1;
            long param = 0L;
            Object object = (Object) EventRunner.current();
            InnerEvent event = InnerEvent.get(eventId, param, object);
            handler.sendEvent(event);
        });

        two.setClickedListener(component -> {
            int eventId = CODE_DOWNLOAD_FILE2;
            long param = 0L;
            Object object = (Object) EventRunner.current();
            InnerEvent event = InnerEvent.get(eventId, param, object);
            handler.sendEvent(event);
        });

        Three.setClickedListener(component -> {
            int eventId = CODE_DOWNLOAD_FILE3;
            long param = 0L;
            Object object = (Object) EventRunner.current();
            InnerEvent event = InnerEvent.get(eventId, param, object);
            handler.sendEvent(event);
        });
    }

    @Override
    protected void onBackground() {
        super.onBackground();
        runner = null;
    }
}
