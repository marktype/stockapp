package com.example.drawer.stockapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.QuanAdapter;
import com.example.drawer.stockapp.model.QuanInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;

public class DiYongQuanActivity extends BascActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_di_yong_quan);
        tintManager.setStatusBarTintResource(R.color.write_color);
        initWight();
    }

    public void initWight(){

        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.diyongquan_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        ListView mList = (ListView) findViewById(R.id.my_quan_list);

        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);

        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        QuanAdapter adapter = new QuanAdapter(this);
        adapter.setData(getData());
       mList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }

    public ArrayList<QuanInfo> getData(){
        ArrayList<QuanInfo> list = new ArrayList<>();
        for (int i =0;i<3;i++){
            QuanInfo info = new QuanInfo();
            info.setCanUsed("满35元可用");
            info.setMoney("5");
            info.setWhoUsed("仅限猫抓008用户可用");
            info.setTimeUsed("2016.2.12-2016.05.13");
            info.setTimeOver(false);
            list.add(info);
        }
        return list;
    }

}
