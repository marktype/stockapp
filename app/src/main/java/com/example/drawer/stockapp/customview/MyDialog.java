package com.example.drawer.stockapp.customview;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 自定义对话框，所有对话框都可以引用此类，布局自定义（此类只用于设置对话框大小，显示格式，位置）
 * R.style.myDialog    格式可用
 */
public class MyDialog extends Dialog {

    private static int default_width = 200; //默认宽度
    private static int default_height = 160;//默认高度
    private View mView;

//    public MyDialog(Context context) {
//        super(context);
//    }

    public MyDialog(Context context, int layout, int style) {
        this(context, default_width, default_height, layout, style);
        mView = LayoutInflater.from(context).inflate(layout,null);
    }

    public MyDialog(Context context, int width, int height, int layout, int style) {
        super(context, style);
//set content
        mView = LayoutInflater.from(context).inflate(layout,null);
        setContentView(mView);

//set window params
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
//set width,height by density and gravity

        float density = getDensity(context);
//        params.width = (int) (width * density);
//        params.height = (int) (height * density);
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    private float getDensity(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.density;
    }

    public View getView(){
        return mView;
    }
}
