package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends BascActivity implements View.OnClickListener{
    private EditText mUserName,mPassword,mVerify;
    private TextView mGetVerify;
    private String verify;
    private MyDialog dialog;
    private CheckBox mCheck;
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
                String phone = mUserName.getText().toString();
                String password = mPassword.getText().toString();
                verify = mVerify.getText().toString();
                if (phone.length() == 11&& !TextUtils.isEmpty(verify)){
                    if (mCheck.isChecked()){
                    dialog = ManagerUtil.getDiaLog(this);
                    RegisterAsyn getRegister = new RegisterAsyn();
                    getRegister.execute(phone,password,verify);
                    }else {
                        Toast.makeText(this,"同意用户协议才能注册哦",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this,"输入有误",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(this,"输入有误",Toast.LENGTH_SHORT).show();
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
                Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_SHORT).show();
            }else {
                DaojiShiAsyn daojiShiAsyn = new DaojiShiAsyn();
                daojiShiAsyn.execute(60);
            }

        }
    }

    /**
     * 倒计时
     */
    private class DaojiShiAsyn extends AsyncTask<Integer,Integer,String>{
        @Override
        protected String doInBackground(Integer... integers) {
            int i= integers[0];
            while(i>0){
                if (!TextUtils.isEmpty(verify)){
                    i= 1;
                }
                i--;
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            verify = mVerify.getText().toString();
            mGetVerify.setText(values[0]+"");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mGetVerify.setText("发送验证码");
            mGetVerify.setEnabled(true);
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
            try {
                JSONObject object = new JSONObject(message);
                JSONObject head = object.getJSONObject("Head");
                stutas = head.getString("Status");
                msg = head.getString("Msg");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (stutas.equals("0")){
                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                finish();
            }else if (stutas.equals("1")){
                Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
