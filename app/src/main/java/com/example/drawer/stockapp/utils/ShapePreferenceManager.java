package com.example.drawer.stockapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 欢大哥 on 2016/7/13.(此类用于保存用户的基本信息)
 */
public class ShapePreferenceManager {

    private static ShapePreferenceManager instance;
    private SharedPreferences mSharePrefence;
    public static final String COLLECT_USER_INFO = "com.stockapp.userinfo";    //保存用户信息

    private ShapePreferenceManager(){

    }
    public static ShapePreferenceManager newInstance(){
        if (instance == null){
            instance = new ShapePreferenceManager();
        }
        return instance;
    }

    public SharedPreferences getMySharedPreferences(Context context){
        if (mSharePrefence == null){
            mSharePrefence= context.getSharedPreferences(COLLECT_USER_INFO,
                    Activity.MODE_PRIVATE);
        }
        return mSharePrefence;
    }

}
