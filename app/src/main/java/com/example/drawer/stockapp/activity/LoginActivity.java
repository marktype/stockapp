package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.MyDialog;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.UserInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.HashMap;

public class LoginActivity extends BascActivity implements View.OnClickListener{
    private EditText mUserName,mPassWord;
    private TextView mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tintManager.setStatusBarTintResource(android.R.color.transparent);
        initWight();
    }

    public void initWight(){
        TextView mRegister = (TextView) findViewById(R.id.register_txt);
        mLogin = (TextView) findViewById(R.id.login_txt);
        TextView mForgetPassword = (TextView) findViewById(R.id.forget_password_txt);
        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);
        mUserName = (EditText) findViewById(R.id.user_name_txt);
        mPassWord = (EditText) findViewById(R.id.password_txt);
        ImageView mEyeImg = (ImageView) findViewById(R.id.eye_img);

        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.login_relat);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);



        mForgetPassword.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mBackimg.setOnClickListener(this);
        mEyeImg.setOnClickListener(this);
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
            case R.id.register_txt:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_txt:
                String name = mUserName.getText().toString();
                String password = mPassWord.getText().toString();
                if (name.length() != 11){
                    Toast.makeText(getApplicationContext(),"电话号码写错了",Toast.LENGTH_SHORT).show();
                }else if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
                    dialog = ManagerUtil.getDiaLog(this);
                    LoginAsyn loginAsyn = new LoginAsyn();
                    loginAsyn.execute(name,password);
                }else {
                    Toast.makeText(getApplicationContext(),"还没写密码哦",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.forget_password_txt:
                intent = new Intent(this,ForgetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.back_img:
                finish();
                break;
            case R.id.eye_img:
                if (mPassWord.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD){
                    mPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    mPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
//        in.setAction("com.ymhd.select");
//        sendBroadcast(in);
//        finish();
//        Intent intent = new Intent(this, MainActivity.class);
//        MainActivity.isFirst = true;
//        startActivity(intent);
        finish();
    }

    private MyDialog dialog;
    /**
     * 异步登录接口
     */
        private class LoginAsyn extends AsyncTask<String,Void,String>{

            @Override
            protected String doInBackground(String... strings) {

                HashMap<String,String> map = new HashMap<>();
                map.put("UserName", strings[0]);
                map.put("Password", strings[1]);
                String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,HttpManager.Login_URL);
                return message;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();
                String message = s;
                if (!TextUtils.isEmpty(message)&&message.length()>10){
                    Gson gson = new Gson();
                    UserInfo userInfo = gson.fromJson(message,UserInfo.class);
                    if (userInfo.getHead().getStatus()==0){
                        SharedPreferences sharedPreferences = ShapePreferenceManager.getMySharedPreferences(LoginActivity.this);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ShapePreferenceManager.TOKEN,userInfo.getResult().getToken());
                        editor.putString(ShapePreferenceManager.USER_ID,userInfo.getResult().getId());
                        editor.putString(ShapePreferenceManager.USER_NMAE,userInfo.getResult().getPhoneNum());
                        editor.commit();

                        Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),userInfo.getHead().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

}
