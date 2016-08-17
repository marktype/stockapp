package com.example.drawer.stockapp.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.AllCanUseActivity;
import com.example.drawer.stockapp.activity.AlterNameActivity;
import com.example.drawer.stockapp.activity.AttentionActivity;
import com.example.drawer.stockapp.activity.CollectionActivity;
import com.example.drawer.stockapp.activity.LoginActivity;
import com.example.drawer.stockapp.activity.MyWalletActivity;
import com.example.drawer.stockapp.customview.MyReboundScrollView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.UserInfo;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 *
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment implements View.OnClickListener{
        private View mView;
    private PopupWindow mClassifyPop;
    private RelativeLayout mTitleRelat;
    protected SystemBarTintManager tintManager;
    private String token,userId;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = ShapePreferenceManager.getMySharedPreferences(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my, container, false);

        initWight();

        return mView;
    }


    public void initWight(){
        tintManager = ManagerUtil.newInstance(getActivity());
        ManagerUtil.setStataBarColor(getActivity(),tintManager);


        mTitleRelat = (RelativeLayout) mView.findViewById(R.id.my_info_relat);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                100);
        params.setMargins(0, ManagerUtil.getStatusBarHeight(getActivity()),0,0);
        mTitleRelat.setLayoutParams(params);

        CircleImageView circleImageView = (CircleImageView) mView.findViewById(R.id.user_head);      //头像
        Picasso.with(getActivity()).load(R.mipmap.ic_launcher).into(circleImageView);

        TextView mFenSi = (TextView) mView.findViewById(R.id.fensi_num_txt);
        mFenSi.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/DIN Medium.ttf"));   //设置字体风格
        TextView mFoucs = (TextView) mView.findViewById(R.id.foucs_txt);
        mFoucs.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/DIN Medium.ttf"));
        TextView mCollect = (TextView) mView.findViewById(R.id.collect_num_txt);
        mCollect.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/DIN Medium.ttf"));

        LinearLayout mAttention = (LinearLayout) mView.findViewById(R.id.attention_lin);    //关注
        RelativeLayout mMyWallet = (RelativeLayout) mView.findViewById(R.id.my_wallet_lin);    //我的钱包
        RelativeLayout mName = (RelativeLayout) mView.findViewById(R.id.user_name_lin);    //修改昵称
        RelativeLayout mSex = (RelativeLayout) mView.findViewById(R.id.sex_lin);      //性别
        LinearLayout mCollectLin = (LinearLayout) mView.findViewById(R.id.collect_lin);
        RelativeLayout mAllCan = (RelativeLayout) mView.findViewById(R.id.all_can_lin);    //通用

        //不设置渐变
        mTitleRelat.getBackground().setAlpha(1);
        tintManager.setTintAlpha(0);
        MyReboundScrollView mScrollview = (MyReboundScrollView) mView.findViewById(R.id.my_scrollview);

        mScrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                    if (view.getScrollY()>100){
                        mTitleRelat.getBackground().setAlpha(255);
                        tintManager.setTintAlpha(1);
                    }else {
                        //不设置渐变
                        mTitleRelat.getBackground().setAlpha(1);
                        tintManager.setTintAlpha(0);
                    }
                }
                return false;
            }
        });


        mSex.setOnClickListener(this);
        mName.setOnClickListener(this);
        mMyWallet.setOnClickListener(this);
        mAttention.setOnClickListener(this);
        circleImageView.setOnClickListener(this);
        mCollectLin.setOnClickListener(this);
        mAllCan.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(getActivity());
        ManagerUtil.setStataBarColor(getActivity(),tintManager);
        token = sharedPreferences.getString(ShapePreferenceManager.TOKEN,"");
        userId = sharedPreferences.getString(ShapePreferenceManager.USER_ID,"");
        UserInfoAsyn userInfoAsyn = new UserInfoAsyn();
        userInfoAsyn.execute(userId,token);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.attention_lin:
                Intent intent = new Intent(getContext(), AttentionActivity.class);
                startActivity(intent);
                break;
            case R.id.my_wallet_lin:
                intent = new Intent(getContext(), MyWalletActivity.class);
                startActivity(intent);
                break;
            case R.id.user_head:
                intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.user_name_lin:
                intent = new Intent(getContext(), AlterNameActivity.class);
                startActivity(intent);
                break;
            case R.id.sex_lin:
                getSexPopWin(view);
                break;
            case R.id.man_txt:
                mClassifyPop.dismiss();
                break;
            case R.id.woman_txt:
                mClassifyPop.dismiss();
                break;
            case R.id.cancel_txt:
                mClassifyPop.dismiss();
                break;
            case R.id.collect_lin:
                intent = new Intent(getContext(), CollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.all_can_lin:
                intent = new Intent(getContext(), AllCanUseActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 性别选择弹框
     */
    public void getSexPopWin(View view){
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.sex_selcet_layout, null);
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
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.8f;
        getActivity().getWindow().setAttributes(lp);
        mClassifyPop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
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

    /**
     * 获取用户信息
     */
    private class UserInfoAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("Id", strings[0]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer(strings[1],map,HttpManager.USERINFO_URL);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String message = s;
            if (!TextUtils.isEmpty(message)&&message.length()>10){
                Gson gson = new Gson();
                UserInfo userInfo = gson.fromJson(message,UserInfo.class);   //获取用户信息

            }
        }
    }
}
