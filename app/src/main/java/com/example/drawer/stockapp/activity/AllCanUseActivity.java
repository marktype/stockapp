package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.CustomDialog;
import com.example.drawer.stockapp.utils.DataCleanManager;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import cn.jpush.android.api.JPushInterface;

public class AllCanUseActivity extends BascActivity implements View.OnClickListener{
    private RelativeLayout mClean;
    private TextView mStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_can_use);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.write_color));
        initWight();
    }

   public void initWight(){

       RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.all_can_title);    //title布局
       //设置距离顶部状态栏高度
       RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
               DensityUtils.dp2px(this,50));
       params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
       mTitleRelat.setLayoutParams(params);
       mTitleRelat.setBackgroundColor(getResources().getColor(R.color.write_color));

       ImageView mBackImg = (ImageView) findViewById(R.id.back_img);
       RelativeLayout mNewInfo = (RelativeLayout) findViewById(R.id.news_info_lin);
       mClean = (RelativeLayout) findViewById(R.id.clean_lin);
       RelativeLayout mAbove = (RelativeLayout) findViewById(R.id.above_lin);
       mStatus = (TextView) findViewById(R.id.status_start);
       RelativeLayout mAdvice = (RelativeLayout) findViewById(R.id.advice_lin);

       if (ShapePreferenceManager.getMySharedPreferences(this).getInt(ShapePreferenceManager.ISOPEN,0) == 0){
           JPushInterface.resumePush(getApplicationContext());
           mStatus.setText("开启");
       }else {
           JPushInterface.stopPush(getApplicationContext());
           mStatus.setText("关闭");
       }



       mNewInfo.setBackgroundColor(getResources().getColor(R.color.write_color));
       mClean.setBackgroundColor(getResources().getColor(R.color.write_color));
       mAbove.setBackgroundColor(getResources().getColor(R.color.write_color));
       mAdvice.setBackgroundColor(getResources().getColor(R.color.write_color));

       mClean.setOnClickListener(this);
       mNewInfo.setOnClickListener(this);
       mBackImg.setOnClickListener(this);
       mAbove.setOnClickListener(this);
       mAdvice.setOnClickListener(this);
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
            case R.id.news_info_lin:
//                Intent intent = new Intent(this,NewsInfoActivity.class);
//                startActivity(intent);
                getSexPopWin(view);
                break;
            case R.id.clean_lin:
                popWinDow();
                break;
            case R.id.above_lin:
                Intent intent = new Intent(this,AgreementWebActivity.class);
                intent.putExtra(AgreementWebActivity.URLTYPE,1);
                startActivity(intent);
                break;
            case R.id.man_txt:
                SharedPreferences.Editor editor = ShapePreferenceManager.getMySharedPreferences(this).edit();
                editor.putInt(ShapePreferenceManager.ISOPEN,0);
                editor.commit();
                mStatus.setText("开启");
                JPushInterface.resumePush(getApplicationContext());
                mClassifyPop.dismiss();
                break;
            case R.id.woman_txt:
                editor = ShapePreferenceManager.getMySharedPreferences(this).edit();
                editor.putInt(ShapePreferenceManager.ISOPEN,1);
                editor.commit();
                mStatus.setText("关闭");
                JPushInterface.stopPush(getApplicationContext());
                mClassifyPop.dismiss();
                break;
            case R.id.cancel_txt:
                mClassifyPop.dismiss();
                break;
            case R.id.advice_lin:
                intent = new Intent(this,AdviceActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 退出弹框
     */
    public void popWinDow(){
        final CustomDialog dialog = new CustomDialog(this);
        dialog.setMessageText("确认要清除缓存吗？");
        dialog.show();
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataCleanManager.cleanCacheData(AllCanUseActivity.this);
                Toast.makeText(getApplicationContext(),"清除缓存成功！",Toast.LENGTH_SHORT).show();
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

    private PopupWindow mClassifyPop;
    /**
     * 性别选择弹框
     */
    public void getSexPopWin(View view){
        View contentView = LayoutInflater.from(this).inflate(R.layout.news_info_hint_layout, null);
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

            TextView mMan = (TextView) contentView.findViewById(R.id.man_txt);
            TextView mWoman = (TextView) contentView.findViewById(R.id.woman_txt);
            TextView cancel = (TextView) contentView.findViewById(R.id.cancel_txt);
            mWoman.setOnClickListener(this);
            cancel.setOnClickListener(this);
            mMan.setOnClickListener(this);
        }
        //产生背景变暗效果
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.8f;
        getWindow().setAttributes(lp);
        mClassifyPop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
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
