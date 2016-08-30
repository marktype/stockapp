package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.SetUpZuHeAdapter;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.HeadIndex;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;

public class SetupZuHeActivity extends BascActivity implements View.OnClickListener{
    private String mToken;
    public static final String TYPE = "type";     //调仓还是创建
    private int type;
    private SetUpZuHeAdapter adapter;
    private MyListView mList;
    private EditText mName,mJIanJie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_zu_he);
        tintManager.setStatusBarTintResource(R.color.write_color);
        mToken = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,null);
        type = getIntent().getIntExtra(TYPE,0);
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
        mName = (EditText) findViewById(R.id.edit_name);    //组合名字
        mJIanJie = (EditText) findViewById(R.id.edit_jianjie_text);   //简介

        mList = (MyListView) findViewById(R.id.stock_mylist);
        adapter = new SetUpZuHeAdapter(this);


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

    public static final int RESLUT_SURE = 1;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.add_stock_txt:
                Intent intent = new Intent(this,SerchActivity.class);
                intent.putExtra(SerchActivity.URL_SEARCH, HttpManager.CodeList_URL);
                startActivityForResult(intent,RESLUT_SURE);
                break;
            case R.id.setup_sure_txt:
                if (type == 0){
                    if (!TextUtils.isEmpty(mName.getText().toString())&&!TextUtils.isEmpty(mJIanJie.getText().toString())){
                        setUpZuHe();
                    }else {
                        Toast.makeText(this,"创建组合名字或简介不能为空",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    adapter.setData(setData());
                    mList.setAdapter(adapter);
                    TiaoCangAsyn();
                }
                finish();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESLUT_SURE && resultCode == RESULT_OK) {

        }
    }

    /**
     * 调仓
     */
    private void TiaoCangAsyn(){
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("PorfolioId", "0");
                ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
                for (int i = 0;i<2;i++){
                    HashMap<String,String> hasp = new HashMap<String, String>();
                    hasp.put("Code","1");
                    hasp.put("Price","2");
                    hasp.put("Name","3");
                    hasp.put("Volume","4");
                    hasp.put("TradeTime","5");
                    hasp.put("TradeType","Open");
                    list.add(hasp);
                }
                map.put("Codes",list);
                String message = HttpManager.newInstance().getHttpDataByThreeLayerArrayObject(mToken, map, HttpManager.ChangePosition_URL);
                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

            }
        }.execute();
    }

    /**
     * 创建组合
     */
    private void setUpZuHe(){
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("Name", "0");
                map.put("Amount", "0");
                map.put("TargetReturn", "0");
                map.put("Desc", "0");
                ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
                for (int i = 0;i<2;i++){
                    HashMap<String,String> hasp = new HashMap<String, String>();
                    hasp.put("Code","1");
                    hasp.put("Price","2");
                    hasp.put("Name","3");
                    hasp.put("Volume","4");
                    hasp.put("TradeTime","5");
                    hasp.put("TradeType","Open");
                    list.add(hasp);
                }
                map.put("CodeList",list);
                String message = HttpManager.newInstance().getHttpDataByThreeLayerArrayObject(mToken, map, HttpManager.CreatePorfolio_URL);
                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

            }
        }.execute();
    }
}
