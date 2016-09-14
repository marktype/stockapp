package com.example.drawer.stockapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
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
    private EditText mUserName,mPassWord;
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
        mUserName = (EditText) findViewById(R.id.user_name_txt);
        mPassWord = (EditText) findViewById(R.id.password_txt);
        mLogin = (TextView) findViewById(R.id.alter_sure_txt);
        mLogin.setOnClickListener(this);
        mBackimg.setOnClickListener(this);
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
                if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
                    dialog = ManagerUtil.getDiaLog(this);
                    LoginAsyn asyn = new LoginAsyn();
                    asyn.execute(password,name,mToken);
                }else {
//                    Toast.makeText(this,"输入有误，请重新输入",Toast.LENGTH_SHORT).show();
                    TSnackbar.make(mLogin,"输入有误，请重新输入！",TSnackbar.LENGTH_SHORT).show();
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
                    TSnackbar.make(mLogin,"修改成功！",TSnackbar.LENGTH_SHORT).show();
//                    Toast.makeText(AlterPasswordActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
//                    Toast.makeText(AlterPasswordActivity.this,userInfo.getHead().getMsg(),Toast.LENGTH_SHORT).show();
                    TSnackbar.make(mLogin,userInfo.getHead().getMsg(),TSnackbar.LENGTH_SHORT).show();
                }
            }
        }
    }
}
