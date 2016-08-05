package com.example.drawer.stockapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.AttentionAdapter;
import com.example.drawer.stockapp.model.TrendsInfo;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;

public class AttentionActivity extends BascActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
        tintManager.setStatusBarTintResource(R.color.write_color);
        initWight();
    }

    public void initWight(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.attention_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                100);
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

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

    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }
}
