package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;

import java.util.ArrayList;

public class MessageActivity extends BascActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initWight();
    }

    public void initWight(){
        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);

        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ListView mList = (ListView) findViewById(R.id.message_listview);
        mList.setOnItemClickListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.txt_item_layout,setData());
        mList.setAdapter(adapter);
    }

    public  ArrayList<String> setData(){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0;i<5;i++){
            list.add("测试消息"+i);
        }
        return list;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String message = (String) adapterView.getAdapter().getItem(i);
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,WebViewActivity.class);
        startActivity(intent);
    }
}
