package com.example.drawer.stockapp.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

public class NewsInfoActivity extends BascActivity implements View.OnClickListener,TimePicker.OnTimeChangedListener{
    private TextView time;
    private BasicPushNotificationBuilder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.write_color));
        initWight();
    }


    public void initWight(){
        builder = new BasicPushNotificationBuilder(NewsInfoActivity.this);

        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.news_info_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);
        mTitleRelat.setBackgroundColor(getResources().getColor(R.color.write_color));

        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);
//        RelativeLayout mTime = (RelativeLayout) findViewById(R.id.time_select_relat);

//        RelativeLayout layoutOne = (RelativeLayout) findViewById(R.id.get_info);
//        RelativeLayout layoutTwo = (RelativeLayout) findViewById(R.id.sound_relat);
//        RelativeLayout layoutThree = (RelativeLayout) findViewById(R.id.shock_relat);
//        RelativeLayout layoutFour = (RelativeLayout) findViewById(R.id.aovid_relat);

//        mTime.setBackgroundColor(getResources().getColor(R.color.write_color));
//        layoutOne.setBackgroundColor(getResources().getColor(R.color.write_color));
//        layoutTwo.setBackgroundColor(getResources().getColor(R.color.write_color));
//        layoutThree.setBackgroundColor(getResources().getColor(R.color.write_color));
//        layoutFour.setBackgroundColor(getResources().getColor(R.color.write_color));


//        time = (TextView) findViewById(R.id.time_info);   //时间设置
//
//        WiperSwitch wiperSwitchSounds = (WiperSwitch) findViewById(R.id.wiperSwitch_one);    //声音
//        WiperSwitch wiperSwitchZhenDong = (WiperSwitch) findViewById(R.id.wiperSwitch_two);   //震动
//        WiperSwitch wiperSwitchEvening = (WiperSwitch) findViewById(R.id.wiperSwitch_three);   //免打扰模式
//        WiperSwitch wiperSwitchOpenClose = (WiperSwitch) findViewById(R.id.wiperSwitch_four);   //推送的开关
//
//        wiperSwitchOpenClose.setChecked(true);
//        wiperSwitchSounds.setChecked(true);
//        wiperSwitchZhenDong.setChecked(true);
//
//        wiperSwitchOpenClose.setOnChangedListener(new WiperSwitch.OnChangedListener() {
//            @Override
//            public void OnChanged(WiperSwitch wiperSwitch, boolean checkState) {
//                if (checkState){
//                    JPushInterface.init(NewsInfoActivity.this);
//                }else {
//                    JPushInterface.stopPush(NewsInfoActivity.this);
//                }
//            }
//        });
//
//        wiperSwitchSounds.setOnChangedListener(new WiperSwitch.OnChangedListener() {
//            @Override
//            public void OnChanged(WiperSwitch wiperSwitch, boolean checkState) {
//                if (checkState){
//
//                    builder.notificationFlags = Notification.FLAG_AUTO_CANCEL; // 设置为自动消失
//                    builder.notificationDefaults = Notification.DEFAULT_SOUND; // 设置为铃声
//                }else {
//                    builder = new BasicPushNotificationBuilder(NewsInfoActivity.this);
//                    builder.notificationFlags = Notification.FLAG_AUTO_CANCEL; // 设置为自动消失
//                }
//                JPushInterface.setDefaultPushNotificationBuilder(builder);
//                JPushInterface.setPushNotificationBuilder(1, builder);
//
//            }
//        });
//
//        wiperSwitchZhenDong.setOnChangedListener(new WiperSwitch.OnChangedListener() {
//            @Override
//            public void OnChanged(WiperSwitch wiperSwitch, boolean checkState) {
//                if (checkState){
//                    builder.notificationFlags = Notification.FLAG_AUTO_CANCEL; // 设置为自动消失
//                    builder.notificationDefaults = Notification.DEFAULT_VIBRATE; // 设置震动
//                }else {
//                    builder = new BasicPushNotificationBuilder(NewsInfoActivity.this);
//                    builder.notificationFlags = Notification.FLAG_AUTO_CANCEL; // 设置为自动消失
//                }
//                JPushInterface.setDefaultPushNotificationBuilder(builder);
//                JPushInterface.setPushNotificationBuilder(1, builder);
//            }
//        });
//
//        wiperSwitchEvening.setOnChangedListener(new WiperSwitch.OnChangedListener() {
//            @Override
//            public void OnChanged(WiperSwitch wiperSwitch, boolean checkState) {
//                if (checkState){
//                    setTime(endHour,endMinte,23-startHour,59-startMinte);
//                }else {
//                    setTime(0,0,0,0);
//                }
//            }
//        });
//
//
//        mTime.setOnClickListener(this);
        mBackImg.setOnClickListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
//            case R.id.time_select_relat:
//                popWindTime(view);
//                break;
            case R.id.time_cancal:
                mClassifyPop.dismiss();
                break;
            case R.id.time_sure:
                time.setText("从"+startHour+":"+startMinte+"至"+endHour+":"+endMinte);
                mClassifyPop.dismiss();
                break;
        }
    }

    /**
     * 推送开始和结束时间（静音）
     * @param startHour
     * @param endHour
     */
    private void setTime(int startHour,int startMinte,int endHour,int endMinte){
        JPushInterface.setSilenceTime(getApplicationContext(), startHour, startMinte, endHour, endMinte);

    }

    /**
     * 允许推送时间
     * @param startHour
     * @param endHour
     */
   private void setSlience(int startHour,int endHour){
       Set<Integer> days = new HashSet<Integer>();
       days.add(0);
       days.add(1);
       days.add(2);
       days.add(3);
       days.add(4);
       days.add(5);
       days.add(6);
       JPushInterface.setPushTime(getApplicationContext(), days, startHour, endHour);
   }

    private PopupWindow mClassifyPop;
//    private TimePicker start,end;
//    private TextView mSure,mCancal;
    /**
     * 时间选择器弹框
     */
    public void popWindTime(View view){

            View contentView = LayoutInflater.from(this).inflate(R.layout.date_picker_dialog, null);
            /**
             * 如果pop是null就执行这个方法
             */
            if (mClassifyPop == null) {
                mClassifyPop = new PopupWindow(contentView,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                //        实例化一个ColorDrawable颜色为半透明
                ColorDrawable dw = new ColorDrawable(0xb0000000);
                //设置SelectPicPopupWindow弹出窗体的背景
                mClassifyPop.setBackgroundDrawable(dw);
                mClassifyPop.setOutsideTouchable(true);
                mClassifyPop.setAnimationStyle(R.style.mypopwindow_anim_style);
                TimePicker start = (TimePicker) contentView.findViewById(R.id.datePickerStart);
                TimePicker end = (TimePicker) contentView.findViewById(R.id.datePickerEnd);
                start.setIs24HourView(true);
                end.setIs24HourView(true);
                TextView mSure = (TextView) contentView.findViewById(R.id.time_sure);
                TextView mCancal = (TextView) contentView.findViewById(R.id.time_cancal);

                mCancal.setOnClickListener(NewsInfoActivity.this);
                mSure.setOnClickListener(NewsInfoActivity.this);

                start.setOnTimeChangedListener(NewsInfoActivity.this);
                end.setOnTimeChangedListener(NewsInfoActivity.this);
            }

            //产生背景变暗效果
            WindowManager.LayoutParams lp = this.getWindow().getAttributes();
            lp.alpha = 0.8f;
            getWindow().setAttributes(lp);
            mClassifyPop.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override
                public void onDismiss() {

                    //产生背景变暗效果
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
            });
            mClassifyPop.setFocusable(true);
            /**
             * 显示就消失
             */
            if (mClassifyPop.isShowing()) {
                mClassifyPop.dismiss();
            } else {
                mClassifyPop.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
            }


    }

    private int startHour,startMinte,endHour,endMinte;
    @Override
    public void onTimeChanged(TimePicker timePicker, int i, int i1) {
        switch (timePicker.getId()){
            case R.id.datePickerStart:
                startHour = timePicker.getCurrentHour();    //获取小时
                startMinte = timePicker.getCurrentMinute();   //获取分钟
                break;
            case R.id.datePickerEnd:
                endHour = timePicker.getCurrentHour();    //获取小时
                endMinte = timePicker.getCurrentMinute();   //获取分钟
                break;
        }
    }
}
