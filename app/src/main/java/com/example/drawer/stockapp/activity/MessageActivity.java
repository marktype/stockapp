package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.MessageInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageActivity extends BascActivity implements AdapterView.OnItemClickListener{
    private MessageInfo messageInfo;
    private ListView mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        tintManager.setStatusBarTintResource(R.color.write_color);
        initWight();
        getMessageData();
    }

    public void initWight(){

        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.all_message_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);

        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mList = (ListView) findViewById(R.id.message_listview);
        mList.setOnItemClickListener(this);

    }


    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String message = (String) adapterView.getAdapter().getItem(i);
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,WebViewActivity.class);
        startActivity(intent);
    }

    /**
     * 获取消息一级页面
     */
    public void getMessageData(){
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String,String> map = new HashMap<>();
                map.put("Id", "0");
                String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,HttpManager.Information_other_URL);
                return message;
            }


            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                String message = (String) o;
                if (!TextUtils.isEmpty(message)){
                    Gson gson = new Gson();
                    messageInfo = gson.fromJson(message, MessageInfo.class);
                    if (messageInfo.getHead().getStatus()==0){
                        ArrayList<String> list = new ArrayList<String>();
                        List<MessageInfo.ResultBean.InfoBean> ben = messageInfo.getResult().getInfo();
                        for (int i = 0;i<ben.size();i++){
                            list.add(ben.get(i).getTitle());
                        }
                        if (list.size()>0){
                            ArrayAdapter adapter = new ArrayAdapter(MessageActivity.this,R.layout.txt_item_layout,list);
                            mList.setAdapter(adapter);
                        }
                    }

                }
            }
        }.execute();
    }
}
