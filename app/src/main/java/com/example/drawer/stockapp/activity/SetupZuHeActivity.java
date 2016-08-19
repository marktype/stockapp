package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.SetUpZuHeAdapter;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.model.HeadIndex;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;

public class SetupZuHeActivity extends BascActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_zu_he);
        tintManager.setStatusBarTintResource(R.color.write_color);
        initWight();
    }

    public void initWight(){

        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.setup_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);
        TextView mAddTxt = (TextView) findViewById(R.id.add_stock_txt);
        TextView mSrueBuild = (TextView) findViewById(R.id.setup_sure_txt);
        MyListView mList = (MyListView) findViewById(R.id.stock_mylist);

        SetUpZuHeAdapter adapter = new SetUpZuHeAdapter(this);
        adapter.setData(setData());
        mList.setAdapter(adapter);


        mBackimg.setOnClickListener(this);
        mAddTxt.setOnClickListener(this);
        mSrueBuild.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }

    public ArrayList<HeadIndex> setData(){
        ArrayList<HeadIndex> list = new ArrayList<>();
        for (int i = 0;i<2;i++){
            HeadIndex headIndex = new HeadIndex();
            headIndex.setIndexName("凯乐新材"+i);
            headIndex.setIndexNum("300466");
            headIndex.setIndexPersent("20%");
            list.add(headIndex);
        }
        return list;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.add_stock_txt:
                Intent intent = new Intent(this,SerchActivity.class);
                startActivity(intent);
                break;
            case R.id.setup_sure_txt:
                finish();
                break;
        }
    }
}
