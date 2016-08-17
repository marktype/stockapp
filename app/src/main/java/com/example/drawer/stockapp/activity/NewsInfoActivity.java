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

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class NewsInfoActivity extends BascActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        tintManager.setStatusBarTintResource(R.color.write_color);
        initWight();
    }


    public void initWight(){

        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.news_info_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                100);
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);
        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);
        RelativeLayout mTime = (RelativeLayout) findViewById(R.id.time_select_relat);


        mTime.setOnClickListener(this);
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
            case R.id.time_select_relat:
                popWindTime(view);
                break;
        }
    }

    private PopupWindow mClassifyPop;
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
}
