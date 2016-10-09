package com.example.drawer.stockapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AdviceActivity extends BascActivity implements View.OnClickListener{
    private EditText mName,mPhone,mContent;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.write_color));
        token = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,null);
        initWight();
    }

    private void initWight(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.advice_can_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);
        mTitleRelat.setBackgroundColor(getResources().getColor(R.color.write_color));

        mName = (EditText) findViewById(R.id.name_txt);   //姓名
        mPhone = (EditText) findViewById(R.id.phone_text);  //电话
        mContent = (EditText) findViewById(R.id.edit_txt);   //内容

        mName.setBackgroundColor(getResources().getColor(R.color.write_color));
        mPhone.setBackgroundColor(getResources().getColor(R.color.write_color));
        mContent.setBackgroundColor(getResources().getColor(R.color.write_color));

        TextView mApply = (TextView) findViewById(R.id.send_txt);   //提交
        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);


        mBackImg.setOnClickListener(this);
        mApply.setOnClickListener(this);
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
            case R.id.send_txt:
                String name = mName.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                String content = mContent.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(),"姓名忘写啦",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(phone)||phone.length() != 11){
                    Toast.makeText(getApplicationContext(),"请输入正确的电话哦",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(content)){
                    Toast.makeText(getApplicationContext(),"写点建议吧",Toast.LENGTH_SHORT).show();
                }else {
                    SendApplyAsyn applyAsyn = new SendApplyAsyn();
                    applyAsyn.execute(name,phone,content,token);
                }

                break;
        }
    }

    /**
     * 提交反馈
     */
    private class SendApplyAsyn extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> map = new HashMap<>();
            map.put("Name", strings[0]);
            map.put("Contact", strings[1]);
            map.put("Content", strings[2]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer(strings[3], map, HttpManager.AddFeedBack_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)){
                try {
                    JSONObject object = new JSONObject(s);
                    JSONObject head = object.getJSONObject("Head");
                    if (head.getInt("Status") == 0){
                        Toast.makeText(getApplicationContext(),"提交成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),"提交失败",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(getApplicationContext(),"请求失败",Toast.LENGTH_SHORT).show();
            }

        }
    }
}
