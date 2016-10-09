package com.example.drawer.stockapp.activity;

import android.content.Intent;
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

public class AlterPasswordActivity extends BascActivity implements View.OnClickListener{
    private EditText mUserName,mPassWord,mOtherPassWord;
    private String mToken;
    private TextView mLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_password);
        tintManager.setStatusBarTintResource(android.R.color.transparent);
        mToken = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,null);
        initWidget();
    }


    private void initWidget(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.login_relat);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);
        ImageView mEyeImg = (ImageView) findViewById(R.id.eye_img);     //密码是否可见
        ImageView mEyeImgTwo = (ImageView) findViewById(R.id.eye_img_two);     //密码是否可见

        mUserName = (EditText) findViewById(R.id.user_name_txt);
        mPassWord = (EditText) findViewById(R.id.password_txt);
        mOtherPassWord = (EditText) findViewById(R.id.password_txt_two);
        mLogin = (TextView) findViewById(R.id.alter_sure_txt);
        TextView forgetPassword = (TextView) findViewById(R.id.forget_password_txt);

        mLogin.setOnClickListener(this);
        mBackimg.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);
        mEyeImg.setOnClickListener(this);
        mEyeImgTwo.setOnClickListener(this);
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
            case R.id.alter_sure_txt:
                String name = mUserName.getText().toString();
                String password = mPassWord.getText().toString();
                String other = mOtherPassWord.getText().toString();
                if (!other.equals(password)){
                    Toast.makeText(getApplicationContext(),"您输入的新密码不一致",Toast.LENGTH_SHORT).show();
                }else {
                    if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
                        if (password.length()>=6){
                            dialog = ManagerUtil.getDiaLog(this);
                            LoginAsyn asyn = new LoginAsyn();
                            asyn.execute(password,name,mToken);
                        }else {
                            Toast.makeText(getApplicationContext(),"请输入6-18位的密码",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"请填写密码，请重新输入",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.forget_password_txt:
                Intent intent = new Intent(this,ForgetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.eye_img:
                if (mPassWord.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD){
                    mPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);   //显示小圆点
                }else {
                    mPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);    //显示密码
                }
                break;
            case R.id.eye_img_two:
                if (mOtherPassWord.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD){
                    mOtherPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);   //显示小圆点
                }else {
                    mOtherPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);    //显示密码
                }
                break;
        }
    }

    private MyDialog dialog;
    /**
     * 修改密码
     */
    private class LoginAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {

            HashMap<String,String> map = new HashMap<>();
            map.put("NewPassword", strings[0]);
            map.put("Password", strings[1]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer(strings[2],map,HttpManager.UpdateCustomerPassword_URL);
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
//                    TSnackbar.make(mLogin,"修改成功！",TSnackbar.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),userInfo.getHead().getMsg(),Toast.LENGTH_SHORT).show();
//                    TSnackbar.make(mLogin,userInfo.getHead().getMsg(),TSnackbar.LENGTH_SHORT).show();
                }
            }
        }
    }
}
