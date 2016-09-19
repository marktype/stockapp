package com.example.drawer.stockapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 欢大哥 on 2016/7/13.(此类用于保存用户的基本信息)
 */
public class ShapePreferenceManager {

    private static ShapePreferenceManager instance;
    private static SharedPreferences mSharePrefence,mShareImage;
    public static final String COLLECT_USER_INFO = "com.stockapp.userinfo";    //保存用户信息
    public static final String TOKEN = "token";    //保存用户token信息
    public static final String USER_NMAE = "username";    //保存用户名信息
    public static final String USER_ID = "uid";    //保存用户id信息
//    public static final String IMAGE_CELUE = "image";    //保存量化策略图片

    private ShapePreferenceManager(){

    }
    public static ShapePreferenceManager newInstance(){
        if (instance == null){
            instance = new ShapePreferenceManager();
        }
        return instance;
    }

    public static SharedPreferences getMySharedPreferences(Context context){
        if (mSharePrefence == null){
            mSharePrefence= context.getSharedPreferences(COLLECT_USER_INFO,
                    Activity.MODE_PRIVATE);
        }
        return mSharePrefence;
    }

    /**
     * 保存图片
     */
    public static final String IMAGE_CELUE = "images";
    public static final String IMAGE_INFO = "imagesinfo";

    public static SharedPreferences getImageSharePreference(Context context){
        if (mShareImage == null){
            mShareImage= context.getSharedPreferences(IMAGE_INFO,
                    Activity.MODE_PRIVATE);
        }
        return mShareImage;
    }
}
