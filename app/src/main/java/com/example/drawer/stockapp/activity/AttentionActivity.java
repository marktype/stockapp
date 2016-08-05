package com.example.drawer.stockapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.AttentionAdapter;
import com.example.drawer.stockapp.model.TrendsInfo;

import java.util.ArrayList;

public class AttentionActivity extends BascActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
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

        ListView mList = (ListView) findViewById(R.id.attention_list);

        AttentionAdapter adapter = new AttentionAdapter(this);
        adapter.setData(setData());
        mList.setAdapter(adapter);

    }

    public ArrayList<TrendsInfo> setData(){
        ArrayList<TrendsInfo> list = new ArrayList<>();
        for (int i = 0;i<5;i++){
            TrendsInfo info = new TrendsInfo();
            info.setImage("http://img.lanrentuku.com/img/allimg/1605/5-1605291106390-L.jpg");
            info.setName("dhfjshfu");
            list.add(info);
        }
        return list;
    }
}
