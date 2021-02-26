package com.example.myapplication.handler;

import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.window.dialog.ToastDialog;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;
import ohos.eventhandler.InnerEvent;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MyEventHandler extends EventHandler {

    public static final int CODE_DOWNLOAD_FILE1 = 1;
    public static final int CODE_DOWNLOAD_FILE2 = 2;
    public static final int CODE_DOWNLOAD_FILE3 = 3;

    private static final HiLogLabel label = new HiLogLabel(3, 0xD001100, "MyEventHandler");

    private AbilitySlice slice;

    public MyEventHandler(EventRunner runner, AbilitySlice slice) {
        super(runner);
        this.slice = slice;
    }

    @Override
    protected void processEvent(InnerEvent event) {
        super.processEvent(event);

        if (event == null) {
            return;
        }

        int eventId = event.eventId;
        Object object = event.object;

        switch (eventId) {
            case CODE_DOWNLOAD_FILE1:
                HiLog.info(label, "CODE_DOWNLOAD_FILE1");

                break;
            case CODE_DOWNLOAD_FILE2:
                HiLog.info(label, "CODE_DOWNLOAD_FILE2");
                break;
            case CODE_DOWNLOAD_FILE3:
                HiLog.info(label, "CODE_DOWNLOAD_FILE3");

                //从子线程回到主线程（来时的线程）
                if (object instanceof EventRunner) {
                    EventRunner runner2 = (EventRunner) object;
                    EventHandler myHandler2 = new EventHandler(runner2) {
                        @Override
                        public void processEvent(InnerEvent event) {
                            new ToastDialog(slice)
                                    .setText("third button click.")
                                    .show();
                        }
                    };
                    int eventId2 = 1;
                    long param2 = 0L;
                    Object object2 = null;
                    InnerEvent event2 = InnerEvent.get(eventId2, param2, object2);
                    myHandler2.sendEvent(event2);
                }


                break;
            default:

                break;

        }


    }
}
