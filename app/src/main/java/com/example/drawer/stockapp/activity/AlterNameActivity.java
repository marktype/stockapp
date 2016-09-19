package com.example.drawer.stockapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
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
    private TextView mSavaName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_name);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.write_color));
        initWight();
    }


    public void initWight(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.alter_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);
        mTitleRelat.setBackgroundColor(getResources().getColor(R.color.write_color));

        mName = (EditText) findViewById(R.id.alter_name);
        mName.setBackgroundColor(getResources().getColor(R.color.write_color));

        mName.setFilters(new InputFilter[]{filter});

        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);

        mBackimg.setOnClickListener(this);

        mSavaName = (TextView) findViewById(R.id.save_name);

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
                    Toast.makeText(getApplicationContext(),"昵称不能为空",Toast.LENGTH_SHORT).show();
//                    TSnackbar.make(mSavaName,"昵称不能为空！",TSnackbar.LENGTH_SHORT).show();
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

    /**
     * 限制中文8个字，英文16个字
     */
    private final int maxLen = 16;
    private InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            int dindex = 0;
            int count = 0;

            while (count <= maxLen && dindex < dest.length()) {
                char c = dest.charAt(dindex++);
                if (c < 128) {
                    count = count + 1;
                } else {
                    count = count + 2;
                }
            }

            if (count > maxLen) {
                return dest.subSequence(0, dindex - 1);
            }

            int sindex = 0;
            while (count <= maxLen && sindex < source.length()) {
                char c = source.charAt(sindex++);
                if (c < 128) {
                    count = count + 1;
                } else {
                    count = count + 2;
                }
            }

            if (count > maxLen) {
                sindex--;
            }

            return source.subSequence(0, sindex);
        }


    };
}
