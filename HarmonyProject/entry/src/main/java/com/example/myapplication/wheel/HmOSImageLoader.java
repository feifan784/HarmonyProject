package com.example.myapplication.wheel;

import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.Image;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStream;

public class HmOSImageLoader {
    private final static HiLogLabel LABEL_LOG = new HiLogLabel(HiLog.LOG_APP, 0, "HmOSImageLoader");
    Image image;
    String url;
    int defImage;
    AbilitySlice abilitySlice;

    private HmOSImageLoader(AbilitySlice abilitySlice) {
        this.abilitySlice = abilitySlice;
    }

    public static HmOSImageLoader with(AbilitySlice abilitySlice) {
        return new HmOSImageLoader(abilitySlice);
    }

    public HmOSImageLoader load(String url) {
        this.url = url;
        return this;
    }

    public HmOSImageLoader def(int defImage) {
        this.defImage = defImage;
        return this;
    }

    public void into(Image image) {
        this.image = image;
//        setImageLoadSuccessInterface(face);
        start();
    }

    private void start() {
        if (defImage != 0)
            image.setPixelMap(defImage);
        Request request = new Request.Builder().url(url).get().build();

        String dispatcherName = "parallelTaskDispatcher";
        TaskDispatcher parallelTaskDispatcher = abilitySlice.createParallelTaskDispatcher(dispatcherName, TaskPriority.DEFAULT);

        parallelTaskDispatcher.asyncDispatch(() -> {
            OkHttpClient okHttpClient = new OkHttpClient();
            try {
                //异步网络请求
                Response execute = okHttpClient.newCall(request).execute();
                //获取流
                InputStream inputStream = execute.body().byteStream();
                //利用鸿蒙api将流解码为图片源
                ImageSource imageSource = ImageSource.create(inputStream, null);
                //根据图片源创建位图
                PixelMap pixelMap = imageSource.createPixelmap(null);

                TaskDispatcher uiTaskDispatcher = abilitySlice.getUITaskDispatcher();
                uiTaskDispatcher.asyncDispatch(new Runnable() {
                    @Override
                    public void run() {
                        //展示到组件上
                        image.setPixelMap(pixelMap);
                        //释放位图
                        pixelMap.release();
                    }
                });


                if (imageLoadSuccessInterface != null) {
                    imageLoadSuccessInterface.onLoadSuccess(image, pixelMap);
                }
            } catch (IOException e) {
                HiLog.error(LABEL_LOG, " ----- " + e.getMessage());
                e.printStackTrace();
            }
        });

    }

    private ImageLoadSuccessInterface imageLoadSuccessInterface;

    public interface ImageLoadSuccessInterface {

        void onLoadSuccess(Image image, PixelMap pixelMap);
    }

    public void setImageLoadSuccessInterface(ImageLoadSuccessInterface imageLoadSuccessInterface) {
        this.imageLoadSuccessInterface = imageLoadSuccessInterface;
    }
}
