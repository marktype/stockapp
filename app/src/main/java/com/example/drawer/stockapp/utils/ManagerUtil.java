package com.example.drawer.stockapp.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.CustomDialog;
import com.example.drawer.stockapp.customview.MyDialog;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 欢大哥 on 2016/7/25.
 */
public class ManagerUtil {
    private Context context;
    public ManagerUtil(){

    }
    public ManagerUtil(Context context){
        this.context = context;
    }

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

    //写数据到SD中的文件
    public static void writeFileSdcardFile(String fileName,String write_str){
        try{
            FileOutputStream fout = new FileOutputStream(fileName,true);  //追加文件
            byte [] bytes = write_str.getBytes();
            fout.write(bytes);
            fout.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    //读SD中的文件
    public static String readFileSdcardFile(String fileName){
        String res="";
        try{
            FileInputStream fin = new FileInputStream(fileName);

            int length = fin.available();

            byte [] buffer = new byte[length];
            fin.read(buffer);

            res = new String(buffer, "UTF-8");

            fin.close();
        }

        catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 保存本地图片
     * @param context
     * @param fileName
     */
    public static void saveImg(Context context,String fileName) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon) ;
        File file = new File(fileName) ;
        if(!file.exists()){
            try {
                file.createNewFile() ;
                FileOutputStream fos = new FileOutputStream(file) ;
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, fos) ;
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

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

    /**
     *   / 将字符串转为时间戳
     */
    public static long getTime(String user_time) {
//        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        long l = 0;
        try {
            d = sdf.parse(user_time);
            l = d.getTime();
//            String str = String.valueOf(l);
//            re_time = str.substring(0, 10);
        } catch (ParseException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return l;
    }




    private String localVersion;
    private final String TAG = this.getClass().getName();
    private final int UPDATA_NONEED = 0;
    private final int UPDATA_CLIENT = 1;
    private final int GET_UNDATAINFO_ERROR = 2;
    private final int SDCARD_NOMOUNTED = 3;
    private final int DOWN_ERROR = 4;
    private UpdataInfo info;
    /**
     * 检查更新
     */
    public void checkUserCode(Context context){
        try {
            localVersion = getVersionName(context);
            CheckVersionTask cv = new CheckVersionTask(context);
            new Thread(cv).start();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String getVersionName(Context context) throws Exception {
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),
                0);
        return packInfo.versionName;
    }
    public class CheckVersionTask implements Runnable {
        private Context context;
        public CheckVersionTask(Context context){
            this.context = context;
        }
        InputStream is;
        public void run() {
            try {
                String path = context.getResources().getString(R.string.url_server);
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                int responseCode = conn.getResponseCode();
                if (responseCode == 200) {
                    // 从服务器获得一个输入流
                    is = conn.getInputStream();
                }
                info = UpdataInfoParser.getUpdataInfo(is);
                if (info.getVersion().equals(localVersion)) {
                        Log.i(TAG, "版本号相同");
                    Message msg = new Message();
                    msg.what = UPDATA_NONEED;
                    handler.sendMessage(msg);
                    // LoginMain();
                } else {
                    Log.i(TAG, "版本号不相同 ");
                    Message msg = new Message();
                    msg.what = UPDATA_CLIENT;
                    handler.sendMessage(msg);
                }
            } catch (Exception e) {
                Message msg = new Message();
                msg.what = GET_UNDATAINFO_ERROR;
                handler.sendMessage(msg);
                e.printStackTrace();
            }
        }
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATA_NONEED:
//                    Toast.makeText(getApplicationContext(), "不需要更新",
//                            Toast.LENGTH_SHORT).show();
                    break;
                case UPDATA_CLIENT:
                    //对话框通知用户升级程序
                    showUpdataDialog();
                    break;
                case GET_UNDATAINFO_ERROR:
                    //服务器超时
                    Toast.makeText(context, "获取服务器更新信息失败", Toast.LENGTH_SHORT).show();
                    break;
                case DOWN_ERROR:
                    //下载apk失败
                    Toast.makeText(context, "下载新版本失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    /*
     *
     * 弹出对话框通知用户更新程序
     *
     * 弹出对话框的步骤：
     *  1.创建alertDialog的builder.
     *  2.要给builder设置属性, 对话框的内容,样式,按钮
     *  3.通过builder 创建一个对话框
     *  4.对话框show()出来
     */
    protected void showUpdataDialog() {

        final CustomDialog dialog = new CustomDialog(context);
        dialog.setTitle("版本升级");
        dialog.setMessageText(info.getDescription());
        dialog.show();
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downLoadApk(info.getUrl());
                dialog.dismiss();
            }
        });
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    /*
     * 从服务器中下载APK
     */
    public void downLoadApk(final String url) {
        final ProgressDialog pd;    //进度条对话框
        pd = new  ProgressDialog(context);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
//        pd.setCancelable(true);//设置进度条是否可以按退回键取消
        // 设置点击进度对话框外的区域对话框不消失
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        new Thread(){
            @Override
            public void run() {
                try {
                    File file = DownLoadManager.getFileFromServer(url, pd);
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    Message msg = new Message();
                    msg.what = DOWN_ERROR;
                    handler.sendMessage(msg);
                    e.printStackTrace();
                }
            }}.start();
    }

    //安装apk
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
