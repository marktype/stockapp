package com.example.drawer.stockapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.CollectionAdapter;
import com.example.drawer.stockapp.model.NewsInfo;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;

public class CollectionActivity extends BascActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        tintManager.setStatusBarTintResource(R.color.write_color);
        initWight();
    }


    public void initWight(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.collect_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                100);
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);

        mBackimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ListView mListview = (ListView) findViewById(R.id.collect_list);
        CollectionAdapter adapter = new CollectionAdapter(this);
        adapter.setData(setData());
        mListview.setAdapter(adapter);

    }
    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }

    public ArrayList<NewsInfo> setData(){
        ArrayList<NewsInfo> list = new ArrayList<>();
        for (int i = 0;i<3;i++){
            NewsInfo info = new NewsInfo();
            if (i == 0){
                info.setType(1);
                info.setImage("http://m2.quanjing.com/2m/ivary_photorf001/stp005_00051.jpg");
                info.setTitle("战略转移");
                info.setContent("文本内容测试显示");
                info.setPeopleNum("20");
                info.setTime("2016.08.11");
            }else if (i== 1){
                info.setType(2);
                info.setThreeTitle("3张图片的title");
                info.setThreeContent("啊哈哈哈哈哈哈哈哈哈");
                info.setThreeTime("2016.08.11");
                info.setThreePeopleNum(30);
            }else if (i == 2){
                info.setType(3);
                info.setDynImage("http://m2.quanjing.com/2m/ivary_photorf001/stp005_00051.jpg");
                info.setDynName("牛人1号");
                info.setDynTime("2016.08.11");
                info.setDynContent("yeyeyeyeyeyyeyeyeyeyeyye");
                ArrayList<String> listimg = new ArrayList<>();
                listimg.add("http://m2.quanjing.com/2m/ivary_photorf001/stp005_00051.jpg");
                listimg.add("http://m2.quanjing.com/2m/ivary_photorf001/stp005_00051.jpg");
                listimg.add("http://m2.quanjing.com/2m/ivary_photorf001/stp005_00051.jpg");
                info.setDynContentImage(listimg);
            }
            list.add(info);
        }
        return list;
    }
}
