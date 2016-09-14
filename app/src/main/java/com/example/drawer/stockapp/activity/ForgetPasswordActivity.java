package com.example.drawer.stockapp.activity;

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

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.MyDialog;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ForgetPasswordActivity extends BascActivity implements View.OnClickListener{
    private EditText mUserName,mPassword,mVerify;
    private TextView mGetVerify;
    private String verify;
    private MyDialog dialog;
    private TextView mSureTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        tintManager.setStatusBarTintResource(android.R.color.transparent);
        initWight();

    }

    /**
     * 初始化控件
     */
    public void initWight(){
        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);

        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.forget_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);
        ImageView mEyeImg = (ImageView) findViewById(R.id.eye_img);     //密码是否可见
        mSureTxt = (TextView) findViewById(R.id.alter_sure_txt);  //修改密码

        mGetVerify = (TextView) findViewById(R.id.get_verify);
        mUserName = (EditText) findViewById(R.id.user_name_txt);
        mPassword = (EditText) findViewById(R.id.password_txt);
        mVerify = (EditText) findViewById(R.id.verify_txt);

        mEyeImg.setOnClickListener(this);
        mGetVerify.setOnClickListener(this);
        mBackImg.setOnClickListener(this);
        mSureTxt.setOnClickListener(this);
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
            case R.id.eye_img:
                if (mPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD){
                    mPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);   //显示小圆点
                }else {
                    mPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);    //显示密码
                }
                break;
            case R.id.alter_sure_txt:
                String phone = mUserName.getText().toString();
                String password = mPassword.getText().toString();
                verify = mVerify.getText().toString();
                if (phone.length() == 11&& !TextUtils.isEmpty(verify)){
                    dialog = ManagerUtil.getDiaLog(this);
                    AlterAsyn getRegister = new AlterAsyn();
                    getRegister.execute(phone,password,verify);
                }else {
                    TSnackbar.make(mSureTxt,"输入有误！",TSnackbar.LENGTH_SHORT).show();
//                    Toast.makeText(this,"输入有误",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.get_verify:
                mGetVerify.setEnabled(false);
                String phoneV = mUserName.getText().toString();
                if (phoneV.length() == 11){
                    GetVerify getVerify = new GetVerify();
                    getVerify.execute(phoneV);
                }else {
//                    Toast.makeText(this,"输入有误",Toast.LENGTH_SHORT).show();
                    TSnackbar.make(mGetVerify,"输入有误！",TSnackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 获取短信验证码
     */
    private class GetVerify extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("PhoneNum", strings[0]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,HttpManager.ReSetPasswordCode_URL);
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
                Toast.makeText(ForgetPasswordActivity.this,msg,Toast.LENGTH_SHORT).show();
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
     * 修改密码
     */
    private class AlterAsyn extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("PhoneNum", strings[0]);
            map.put("NewPassWord", strings[1]);
            map.put("Verification", strings[2]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,HttpManager.ReSetPassword_URL);
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
                TSnackbar.make(mSureTxt,"修改成功！",TSnackbar.LENGTH_SHORT).show();
//                Toast.makeText(ForgetPasswordActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                finish();
            }else if (stutas.equals("1")){
//                Toast.makeText(ForgetPasswordActivity.this,msg,Toast.LENGTH_SHORT).show();
                TSnackbar.make(mSureTxt,msg,TSnackbar.LENGTH_SHORT).show();
            }
        }
    }
}
