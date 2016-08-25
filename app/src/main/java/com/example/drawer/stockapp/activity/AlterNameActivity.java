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

import java.util.HashMap;

public class AlterNameActivity extends BascActivity implements View.OnClickListener{

    private EditText mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_name);
        tintManager.setStatusBarTintResource(R.color.write_color);
        initWight();
    }


    public void initWight(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.alter_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        mName = (EditText) findViewById(R.id.alter_name);


        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);

        mBackimg.setOnClickListener(this);

        TextView mSavaName = (TextView) findViewById(R.id.save_name);

        mSavaName.setOnClickListener(this);
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
            case R.id.save_name:
                String str = mName.getText().toString();
                UpdataUserIfoAsyn updataUserIfoAsyn = new UpdataUserIfoAsyn();
                if (!TextUtils.isEmpty(str)){
                    updataUserIfoAsyn.execute(str, ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,null));
                }else {
                    Toast.makeText(this,"昵称不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 跟新用户信息
     */
    private class UpdataUserIfoAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("NickName", strings[0]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer(strings[1],map,HttpManager.UpdataUser_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String message = s;
            finish();
        }
    }
}
