package com.example.drawer.stockapp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.MyDialog;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by 欢大哥 on 2016/7/25.
 */
public class ManagerUtil {
    //通知栏高度写在dimen文件中(获取状态栏高度)
    public static int getStatusBarHeight(Context context){
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }


    /**
     * 设置状态栏图标为深色和魅族特定的文字风格，Flyme4.0以上
     * 可以用来判断是否为Flyme用户
     * @param window 需要设置的窗口
     * @param dark 是否把状态栏字体及图标颜色设置为深色
     * @return  boolean 成功执行返回true
     *
     */
    public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     * @param window 需要设置的窗口
     * @param dark 是否把状态栏字体及图标颜色设置为深色
     * @return  boolean 成功执行返回true
     *
     */
    public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field  field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if(dark){
                    extraFlagField.invoke(window,darkModeFlag,darkModeFlag);//状态栏透明且黑色字体
                }else{
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result=true;
            }catch (Exception e){

            }
        }
        return result;
    }

    private static SystemBarTintManager tintManager;
    private static ManagerUtil managerUtil;

    /**
     * 单例（SystemBarTintManager）
     * @param activity
     * @return
     */
    public static SystemBarTintManager newInstance(Activity activity){
        if (tintManager == null){
            tintManager = new SystemBarTintManager(activity);
        }
        return tintManager;
    }

    /**
     * 实例化（单例）
     * @return
     */
    public static ManagerUtil newInsManager(){
        if (managerUtil == null){
            managerUtil = new ManagerUtil();
        }
        return managerUtil;
    }
    /**
     * 设置状态栏颜色，字体（白底黑字）
     * @param activity
     */
    public static void setStataBarColor(Activity activity,SystemBarTintManager tintManager){
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintColor(activity.getResources().getColor(R.color.write_color));
        FlymeSetStatusBarLightMode(activity.getWindow(),true);
        MIUISetStatusBarLightMode(activity.getWindow(),true);
    }
    /**
     * 设置状态栏颜色，字体（透明底黑字）
     * @param activity
     */
    public static void setStataBarColorBlack(Activity activity,SystemBarTintManager tintManager){
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintColor(activity.getResources().getColor(android.R.color.transparent));
        FlymeSetStatusBarLightMode(activity.getWindow(),true);
        MIUISetStatusBarLightMode(activity.getWindow(),true);
    }
    /**
     * 设置状态栏颜色，字体（黑底白字）透明
     * @param activity
     */
    public static void setStataBarColorWhite(Activity activity,SystemBarTintManager tintManager){
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(android.R.color.transparent);
        FlymeSetStatusBarLightMode(activity.getWindow(),false);
        MIUISetStatusBarLightMode(activity.getWindow(),false);
    }

    /**
     * 获取屏幕的宽高
     * @param context
     * @return
     */
    public static int getPetilWight(Context context){
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return width;
    }

    /**
     * 获取加载进度
     * @param context
     * @return
     */
    public static MyDialog getDiaLog(Context context){
        MyDialog dialog = new MyDialog(context, 120, 80,R.layout.progress_layout,R.style.MyDialogStyleDia);
        dialog.setCancelable(true);
        dialog.show();
        return dialog;
    }


}
