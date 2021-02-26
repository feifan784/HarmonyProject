package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import com.example.myapplication.wheel.HmOSImageLoader;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Image;
import ohos.media.image.PixelMap;

public class LoadNetImageAbilitySlice extends AbilitySlice implements HmOSImageLoader.ImageLoadSuccessInterface {

    private String url = "https://alifei02.cfp.cn/creative/vcg/veer/800water/veer-322478261.jpg";

    private LoadNetImageAbilitySlice INSTANCE;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_layout_load_net_image);

        INSTANCE = this;
        initView();
    }

    private void initView() {

        Button btLoad = (Button) findComponentById(ResourceTable.Id_btLoad);
        Image ivLoad = (Image) findComponentById(ResourceTable.Id_ivLoad);

        btLoad.setClickedListener(component -> {

            HmOSImageLoader.with(this).load(url).into(ivLoad);

        });

    }

    @Override
    public void onLoadSuccess(Image image, PixelMap pixelMap) {
        //展示到组件上
        image.setPixelMap(pixelMap);
        //释放位图
        pixelMap.release();
    }

    @Override
    protected void onBackground() {
        super.onBackground();
    }
}
