package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.HeJiAdapter;
import com.example.drawer.stockapp.model.HeadIndex;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;

public class HeJiDetailActivity extends BascActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_he_ji_detail);
        tintManager.setStatusBarTintResource(R.color.write_color);
        initWight();
    }

    public void initWight(){
        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);
        ListView mList = (ListView) findViewById(R.id.heji_listview);
        mList.setOnItemClickListener(this);
        HeJiAdapter adapter = new HeJiAdapter(this);
        adapter.setData(setData());
        mList.setAdapter(adapter);

        mBackimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }

    /**
     * 设置合集数据
     * @return
     */
    public ArrayList<HeadIndex> setData(){
        ArrayList<HeadIndex> headIndices = new ArrayList<>();
        for (int i= 0;i<5;i++){
            HeadIndex headIndex = new HeadIndex();
            headIndex.setIndexImage("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1266090329,3650149218&fm=80");
            headIndex.setIndexName("课程"+i);
            headIndices.add(headIndex);
        }
        return headIndices;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


        Intent intent = new Intent(this,WebViewActivity.class);
        startActivity(intent);
    }
}
