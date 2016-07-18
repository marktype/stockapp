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
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.MessageInfo;
import com.google.gson.Gson;

import java.util.HashMap;

public class MessageActivity extends BascActivity implements AdapterView.OnItemClickListener{
    private MessageInfo messageInfo;
    private ListView mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initWight();
        getMessageData();
    }

    public void initWight(){
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
                        ArrayAdapter adapter = new ArrayAdapter(MessageActivity.this,R.layout.txt_item_layout,messageInfo.getResult().getInfo());
                        mList.setAdapter(adapter);
                    }

                }
            }
        }.execute();
    }
}
