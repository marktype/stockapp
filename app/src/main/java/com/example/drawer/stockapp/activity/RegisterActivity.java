package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
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
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cn.jpush.android.api.JPushInterface;

public class RegisterActivity extends BascActivity implements View.OnClickListener{
    private EditText mUserName,mPassword,mVerify;
    private TextView mGetVerify;
    private String verify;
    private MyDialog dialog;
    private CheckBox mCheck;
    private TextView mRegist;
    private String phone,password;
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
        mRegist = (TextView) findViewById(R.id.login_txt);
        ImageView mEyeImg = (ImageView) findViewById(R.id.eye_img);     //密码是否可见

        mGetVerify = (TextView) findViewById(R.id.get_verify);
        mUserName = (EditText) findViewById(R.id.user_name_txt);
        mPassword = (EditText) findViewById(R.id.password_txt);
        mVerify = (EditText) findViewById(R.id.verify_txt);
        mCheck = (CheckBox) findViewById(R.id.isCheck);    //是否同意
        TextView mAbove = (TextView) findViewById(R.id.above_txt);   //用户协议

        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.register_relat);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);


        mEyeImg.setOnClickListener(this);
        mGetVerify.setOnClickListener(this);
        mBackImg.setOnClickListener(this);
        mRegist.setOnClickListener(this);
        mAbove.setOnClickListener(this);
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
                phone = mUserName.getText().toString();
                password = mPassword.getText().toString();
                verify = mVerify.getText().toString();
                if (phone.length() != 11){
                    Toast.makeText(getApplicationContext(),"手机号码写错了",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(verify)){
                    Toast.makeText(getApplicationContext(),"验证码写错了",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(password)||password.length()<6){
                    Toast.makeText(getApplicationContext(),"请输入6-18位的密码",Toast.LENGTH_SHORT).show();
                }else {
                    if (mCheck.isChecked()){
                        dialog = ManagerUtil.getDiaLog(this);
                        RegisterAsyn getRegister = new RegisterAsyn();
                        getRegister.execute(phone,password,verify);
                    }else {
                        Toast.makeText(getApplicationContext(),"同意用户协议才能注册哦",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.eye_img:
                if (mPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD){
                    mPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);   //显示小圆点
                }else {
                    mPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);    //显示密码
                }
                break;
            case R.id.get_verify:
                mGetVerify.setEnabled(false);
                String phoneV = mUserName.getText().toString();
                if (phoneV.length() == 11){
                        GetVerify getVerify = new GetVerify();
                        getVerify.execute(phoneV);
                }else {
                    mGetVerify.setEnabled(true);
                    Toast.makeText(getApplicationContext(),"手机号码写错了",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.above_txt:
                Intent intent = new Intent(this,AgreementWebActivity.class);
                intent.putExtra(AgreementWebActivity.URLTYPE,2);
                startActivity(intent);
                break;
        }
    }

    /**
     * 获取短信验证码
     */
    private class GetVerify extends AsyncTask<String,Integer,String>{

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
            String stutas = null;
            String msg = null;
            try {
                JSONObject object = new JSONObject(message);
                JSONObject head = object.getJSONObject("Head");
                stutas = head.getString("Status");
                msg = head.getString("Msg");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (stutas.equals("1")){
                mGetVerify.setEnabled(true);
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }else {
                TimeCount time = new TimeCount(60000, 1000);
                time.start();// 开始计时
            }

        }
    }


    /**
     * 倒计时
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            mGetVerify.setText("发送验证码");
            mGetVerify.setEnabled(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            mGetVerify.setEnabled(false);
            mGetVerify.setText(millisUntilFinished / 1000+"秒后重新获取");
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
            map.put("Password", strings[1]);
            map.put("Verification", strings[2]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,HttpManager.Register_URL);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            String message = s;
            String stutas = null;
            String msg = null;
//            JSONObject reault = null;
            try {
                JSONObject object = new JSONObject(message);
                JSONObject head = object.getJSONObject("Head");
//                reault = object.getJSONObject("Result");
                stutas = head.getString("Status");
                msg = head.getString("Msg");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (stutas.equals("0")){

                MobclickAgent.onEvent(getApplicationContext(),"Reg");

                Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
//                SharedPreferences sharedPreferences = ShapePreferenceManager.getMySharedPreferences(RegisterActivity.this);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString(ShapePreferenceManager.TOKEN,token);
//                editor.commit();
                dialog = ManagerUtil.getDiaLog(RegisterActivity.this);
                LoginAsyn loginAsyn = new LoginAsyn();
                loginAsyn.execute(phone,password);

                Intent in = new Intent();
                in.setAction(LoginActivity.isFlishType);
                //发送广播,销毁此界面
                sendBroadcast(in);
            }else if (stutas.equals("1")){
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        }
    }

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
                    MobclickAgent.onEvent(getApplicationContext(),"Login");
                    SharedPreferences sharedPreferences = ShapePreferenceManager.getMySharedPreferences(RegisterActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(ShapePreferenceManager.TOKEN,userInfo.getResult().getToken());
                    editor.putString(ShapePreferenceManager.USER_ID,userInfo.getResult().getId());
                    editor.putString(ShapePreferenceManager.USER_NMAE,userInfo.getResult().getNickName());
                    editor.putString(ShapePreferenceManager.PHONE,userInfo.getResult().getPhoneNum());
                    editor.commit();
                    //推送标签(别名)
//                        Set<String> set = new HashSet<>();
//                        set.add(userInfo.getResult().getPhoneNum());
                    JPushInterface.setAlias(RegisterActivity.this, userInfo.getResult().getPhoneNum(), null);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),userInfo.getHead().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
