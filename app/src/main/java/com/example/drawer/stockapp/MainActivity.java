package com.example.drawer.stockapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentTabHost;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.activity.BascActivity;
import com.example.drawer.stockapp.activity.LoginActivity;
import com.example.drawer.stockapp.activity.UserInfoActivity;
import com.example.drawer.stockapp.customview.CustomDialog;
import com.example.drawer.stockapp.fragment.AutoWisdomFragment;
import com.example.drawer.stockapp.fragment.FirstNewsFragment;
import com.example.drawer.stockapp.fragment.MyFragment;
import com.example.drawer.stockapp.fragment.XueTangFragment;
import com.example.drawer.stockapp.listener.OnFragmentInteractionListener;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.tencent.tmassistantsdk.common.TMAssistantDownloadSDKTaskState;
import com.tencent.tmassistantsdk.selfUpdateSDK.ITMSelfUpdateSDKListener;
import com.tencent.tmassistantsdk.selfUpdateSDK.TMSelfUpdateSDK;
import com.tencent.tmassistantsdk.selfUpdateSDK.TMSelfUpdateSDKUpdateInfo;

public class MainActivity extends BascActivity implements OnFragmentInteractionListener,View.OnClickListener {
    private FragmentTabHost tabHost;
    public static Boolean isFirst = false;   //判断是否跳转回首页的标记
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tintManager.setStatusBarTintResource(android.R.color.transparent);
        setContentView(R.layout.activity_main);
        initTab();
//        initWight();
        String  fileName = Environment.getExternalStorageDirectory() +"/icon.png"  ;
        ManagerUtil.saveImg(this,fileName);

        checkVersion();

    }

    public void initWight(){

        TextView mExit = (TextView) findViewById(R.id.exit_now);
        RelativeLayout mLayout = (RelativeLayout) findViewById(R.id.drawer_head_info_relat);


        mLayout.setOnClickListener(this);
        mExit.setOnClickListener(this);
    }

    //自定义tab
    public View setTabMenu(String name, int image) {
        View v = LayoutInflater.from(this).inflate(R.layout.tab_own_item_layout, null);
        TextView menuText = (TextView) v.findViewById(R.id.tab_menu_txt);
        ImageView menuImg = (ImageView) v.findViewById(R.id.tab_image);
        menuText.setText(name);
        menuImg.setImageResource(image);
        return v;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isFirst) {
            tabHost.setCurrentTab(0);
            isFirst = false;
        }
    }

    public void initTab(){
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        //使用fragment代替activity转换实现
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);


        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(setTabMenu("资讯", R.drawable.tab_item1_selector)), FirstNewsFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(setTabMenu("智能投顾", R.drawable.tab_item2_selector)), AutoWisdomFragment.class, null);
//        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(setTabMenu("自选股", R.drawable.tab_item3_selector)), AutoStockFragment.class, null);   //暂时隐藏
//        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator(setTabMenu("学堂", R.drawable.tab_item4_selector)), SchoolFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator(setTabMenu("学堂", R.drawable.tab_item4_selector)), XueTangFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab5").setIndicator(setTabMenu("我的", R.drawable.tab_item5_selector)), MyFragment.class, null);

        tabHost.getTabWidget().setDividerDrawable(null);     //去除tab之间的分割线
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                switch (s){
                    case "tab1":
                        tintManager.setStatusBarTintResource(android.R.color.transparent);
                        break;
                    case "tab2":
                        tintManager.setStatusBarTintResource(android.R.color.transparent);
                        break;
                    case "tab4":
                        tintManager.setStatusBarTintResource(R.color.write_color);
                        break;
                    case "tab5":
                        tintManager.setStatusBarTintResource(android.R.color.transparent);
                        break;
                }
            }
        });


    }



    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 800) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();

            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onFragmentInteraction() {
    }



    @Override
    protected void onStop() {
        super.onStop();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.exit_now:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.drawer_head_info_relat:
                intent = new Intent(this, UserInfoActivity.class);
                startActivity(intent);
                break;
        }
    }

    //1002390
    public void checkVersion(){
    TMSelfUpdateSDK.getInstance().initTMSelfUpdateSDK(getApplicationContext(), 1105631907, "1002390",
            new ITMSelfUpdateSDKListener() {
        @Override
        public void OnDownloadYYBStateChanged(String arg0, int arg1, int arg2, String arg3) {
        }
        @Override
        public void OnDownloadYYBProgressChanged(String arg0, long arg1, long arg2) {
        }
        @Override
        public void OnDownloadAppStateChanged(int arg0, int arg1, String arg2) {
        }
        @Override
        public void OnDownloadAppProgressChanged(long arg0, long arg1) {
        }
        @Override
        public void OnCheckNeedUpdateInfo(TMSelfUpdateSDKUpdateInfo arg0) {
            if (arg0 != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("NewApkSize=")
                        .append(arg0.getNewApkSize())
                        .append("NewFeature=")//更新日志
                        .append(arg0.getNewFeature())
                        .append("PatchSize=")
                        .append(arg0.getPatchSize())
                        .append("Status=").append(arg0.getStatus())
                        .append("UpdateDownloadUrl=")//apk下载地址
                        .append(arg0.getUpdateDownloadUrl())
                        .append("UpdateMethod=")
                        .append(arg0.getUpdateMethod());
                Log.d("tag","SelfUpdate " + sb.toString());
                Log.d("tag","arg0---TMSelfUpdateUpdateInfo----"+arg0);
                    Log.d("tag","更新信息-----"+arg0.getNewFeature());
                    checkYYbIsHave(arg0.getUpdateDownloadUrl());
            } else {
                Log.d("tag","SelfUpdate already latest!!!");
            }
        }
    });
//    TMSelfUpdateSDK.getInstance().checkNeedUpdate();
    }

//    private  TMSelfUpdateManager selfUpdateManager;
//    private Boolean isHave = false;   //是否安装有应用宝
//    public void checkVersion(){
//        // 自更新sdk初始化
//        selfUpdateManager = TMSelfUpdateManager.getInstance();
//        try {
//            Context context = getApplication();//application的context
//            String channelid = "1002390"; //应用宝渠道包的渠道号，申请方法请参见《腾讯应用宝自更新SDK产品介绍》中的产品接入步骤step1
//            ITMSelfUpdateListener selfupdateListener = new ITMSelfUpdateListener(){
//                @Override
//                public void onDownloadAppStateChanged(final int state, final int errorCode, final String errorMsg) {
//                    //TODO 更新包下载状态变化的处理逻辑
//                    Log.d("tag","aaaaaaaaaaaa");
//                }
//                @Override
//                public void onUpdateInfoReceived(TMSelfUpdateUpdateInfo arg0) {
//                    //TODO 收到更新信息的处理逻辑
//                    Log.d("tag","arg0---TMSelfUpdateUpdateInfo----"+arg0);
//                    if (arg0 != null){
//                        Log.d("tag","更新信息-----"+arg0.getNewFeature());
//                        checkYYbIsHave(arg0.getUpdateDownloadUrl());
//                    }
//                }
//
//                @Override
//                public void onDownloadAppProgressChanged(final long arg0, final long arg1){
//                    //TODO 更新包下载进度发生变化的处理逻辑
//                    Log.d("tag","bbbbbbbbbbbbbbbbbb");
//                }
//                //实现请参考下一小节实现自更新状态监听器部分的内容
//            };//自更新状态监听器
//            YYBDownloadListener yybDownloadListener = new YYBDownloadListener() {
//                @Override
//                public void onDownloadYYBStateChanged(String url, final int state, int errorCode, String errorMsg) {
//                    //TODO 应用宝下载状态变化的处理逻辑
//                }
//
//                @Override
//                public void onDownloadYYBProgressChanged(final String url, final long receiveDataLen, final long totalDataLen) {
//                    //TODO 应用宝下载进度变化的处理逻辑
//                }
//
//                @Override
//                public void onCheckDownloadYYBState(String s, int i, long l, long l1) {
//
//                }
//                //实现请参考下一小节实现应用宝下载状态监听器部分的内容
//            };//应用宝下载状态监听器
//            Bundle bundle = null;//附加参数的bundle，一般情况下传空，可以由外部传入场景信息等，具体字段可参考 TMSelfUpdateConst. BUNDLE_KEY_* 的定义
//            selfUpdateManager.init(context, channelid, selfupdateListener, yybDownloadListener, bundle);
//            selfUpdateManager.checkSelfUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * 检查是否安装有应用宝
     */
    public void checkYYbIsHave(String apkUrl){

        if (TMSelfUpdateSDK.getInstance().checkYYBInstalled() == TMAssistantDownloadSDKTaskState.ALREADY_INSTALLED){   //已经安装应用宝
            showUpdataDialog(null);
        }else {
            showUpdataDialog(apkUrl);
        }

//            int status = 0;
//        try {
//            //检查应用宝安装状态
//            status = selfUpdateManager. checkYYBInstallState ();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (status == TMAssistantDownloadTaskState.ALREADY_INSTALLED) {
//            // TODO：已安装应用宝：可直接跳转到应用宝的指定页面；
//
//
//        } else if (status == TMAssistantDownloadTaskState.UN_INSTALLED) {
//            //TODO：未安装应用宝：建议提示用户下载应用宝（需第三方开发者自己实现应用宝的
//            //下载逻辑）或者不做跳转；
//
//
//        }else if(status== TMAssistantDownloadTaskState.LOWWER_VERSION_INSTALLED) {
//            //TODO：当前安装的应用宝版本过低（即不支持跳转）：建议提示用户升级应用宝
//            //(需第三方开发者自己实现应用宝的升级逻辑）或者不做跳转。
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    private ManagerUtil managerUtil = new ManagerUtil();
    protected void showUpdataDialog(final String url) {
        final CustomDialog dialog = new CustomDialog(this);
        dialog.setTitle("版本升级");
        dialog.setMessageText("您有新的爱猫爪升级啦！");
        dialog.show();
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (url != null&& !TextUtils.isEmpty(url)){   //没有应用宝时
                    managerUtil.downLoadApk(url);
                }else {    //有应用宝时
                    TMSelfUpdateSDK.getInstance().startSaveUpdate(getApplicationContext());
                }
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

}
