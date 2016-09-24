package com.example.drawer.stockapp;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by 欢大哥 on 2016/9/7.
 */
public class MyAppliction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ShareSDK.initSDK(this);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
