package com.example.drawer.stockapp.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import cn.jpush.android.api.JPushInterface;

/**
 * 基类，几乎所有的activity都继承他
 */
public class BascActivity extends AppCompatActivity {
    protected SystemBarTintManager tintManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //设置状态栏的颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintResource(android.R.color.transparent);
//        ManagerUtil.FlymeSetStatusBarLightMode(this.getWindow(),true);
//        ManagerUtil.MIUISetStatusBarLightMode(this.getWindow(),true);
    }

    protected  void setStatusColor(int color){
        tintManager.setStatusBarTintColor(color);
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
