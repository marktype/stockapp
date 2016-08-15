package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.HashMap;

public class RegisterActivity extends BascActivity implements View.OnClickListener{
    private EditText mUserName,mPassword,mVerify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tintManager.setStatusBarTintResource(android.R.color.transparent);
        initWight();
    }

    /**
     * 初始化控件
     */
    public void initWight(){
        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);
        TextView mRegist = (TextView) findViewById(R.id.login_txt);
        TextView mGetVerify = (TextView) findViewById(R.id.get_verify);
        ImageView mEyeImg = (ImageView) findViewById(R.id.eye_img);     //密码是否可见

        mUserName = (EditText) findViewById(R.id.user_name_txt);
        mPassword = (EditText) findViewById(R.id.password_txt);
        mVerify = (EditText) findViewById(R.id.verify_txt);


        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.register_relat);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                100);
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);


        mEyeImg.setOnClickListener(this);
        mGetVerify.setOnClickListener(this);
        mBackImg.setOnClickListener(this);
        mRegist.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColorBlack(this,tintManager);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.login_txt:
                Intent intent = new Intent(this,RegisterSuccessActivity.class);
                startActivity(intent);
                break;
            case R.id.eye_img:
                if (mPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD){
                    mPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);   //显示小圆点
                }else {
                    mPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);    //显示密码
                }
                break;
            case R.id.get_verify:
                String phone = mUserName.getText().toString();
                if (phone.length() == 11){
                    GetVerify getVerify = new GetVerify();
                    getVerify.execute(phone);
                }
                break;
        }
    }

    /**
     * 获取短信验证码
     */
    private class GetVerify extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("PhoneNum", strings[0]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,HttpManager.RegisterCode_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String message = s;
        }
    }

    /**
     * 注册
     */
    private class RegisterAsyn extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("PhoneNum", strings[0]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,HttpManager.Register_URL);
            return message;
        }
    }
}
