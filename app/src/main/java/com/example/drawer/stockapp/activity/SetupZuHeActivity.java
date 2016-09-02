package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.example.drawer.stockapp.listener.StockCallBack;
import com.example.drawer.stockapp.model.HeadIndex;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SetupZuHeActivity extends BascActivity implements View.OnClickListener{
    private String mToken;
    public static final String TYPE = "type";     //调仓还是创建
    private int type;
    private SetUpZuHeAdapter adapter;
    private MyListView mList;
    private EditText mName,mJIanJie;
    private ArrayList<HeadIndex> list;
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
        list = new ArrayList<>();
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

        adapter.setOnStockPersentListener(new StockCallBack() {
            @Override
            public void OnBackStockPersent(int position, int persent) {
               HeadIndex headIndex = list.get(position);
                headIndex.setIndexPersent(persent+"");
               list.remove(position);
                list.add(headIndex);
                Log.d("tag","百分数------"+list.get(position).getIndexName());
            }
        });


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




    public static final int RESLUT_SURE = 1;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.add_stock_txt:
                Intent intent = new Intent(this,SerchSetUpActivity.class);
                intent.putExtra(SerchActivity.URL_SEARCH, HttpManager.CodeList_URL);
                startActivityForResult(intent,RESLUT_SURE);
                break;
            case R.id.setup_sure_txt:
                Log.d("tag","type------"+type);
                if (type == 0){
                    String name = mName.getText().toString();
                    String jianjie = mJIanJie.getText().toString();
                    if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(jianjie)){
                        for (int i = 0;i<list.size();i++) {
                            if (list.get(i).getIndexPersent() != null&& !TextUtils.isEmpty(list.get(i).getIndexPersent())){
                                setUpZuHe(name,jianjie,getVolume());
                            }else {
                                Toast.makeText(this,"股票百分比不能为空",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else {
                        Toast.makeText(this,"创建组合名字或简介不能为空",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    TiaoCangAsyn();
                }

                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESLUT_SURE && resultCode == RESULT_OK) {
            HeadIndex headIndex = data.getParcelableExtra(SerchSetUpActivity.HEADINDEX);
            ArrayList<String> listCode = new ArrayList<>();
            for (int i = 0;i<list.size();i++){
                listCode.add(list.get(i).getIndexNum());
            }
            if (!listCode.contains(headIndex.getIndexNum())){
                list.add(headIndex);
            }
            adapter.setData(list);
            mList.setAdapter(adapter);
        }
    }

    /**
     * 计算股票数
     */
    private ArrayList<HashMap<String,String>> getVolume(){
        ArrayList<HashMap<String,String>> mapList = new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        Log.d("tag","data-----"+time);
        for (int i = 0;i<list.size();i++){
            int volume = 0;
            HashMap<String,String> hasp = new HashMap<>();
            HeadIndex headIndex = list.get(i);
            if (headIndex.getIndexPersent() != null&& !TextUtils.isEmpty(headIndex.getIndexPersent())){
                volume = (int) (((10000*Integer.parseInt(headIndex.getIndexPersent()))/(headIndex.getPrice()*100))*100);
            }
            Log.d("tag","volume-------"+volume);
            hasp.put("Code",headIndex.getIndexNum());
            hasp.put("Price",headIndex.getPrice()+"");
            hasp.put("Name",headIndex.getIndexName());
            hasp.put("Volume",volume+"");
            hasp.put("TradeTime",time);
            hasp.put("TradeType","Open");
            mapList.add(hasp);
        }
        return mapList;
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
    private void setUpZuHe(final String name, final String desc, final ArrayList<HashMap<String,String>> listCode){
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("Name", name);
                map.put("Amount", "1000000");
                map.put("TargetReturn", "0");
                map.put("Desc", desc);
//                ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
//                for (int i = 0;i<listCode.size();i++){
//                    HashMap<String,String> hasp = new HashMap<String, String>();
//                    hasp.put("Code","1");
//                    hasp.put("Price","2");
//                    hasp.put("Name","3");
//                    hasp.put("Volume","4");
//                    hasp.put("TradeTime","5");
//                    hasp.put("TradeType","Open");
//                    list.add(hasp);
//                }
                map.put("CodeList",listCode);


                String message = HttpManager.newInstance().getHttpDataByThreeLayerArrayObject(mToken, map, HttpManager.CreatePorfolio_URL);
                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                Log.d("tag","o--------"+o);
                String message = (String) o;
                if (!TextUtils.isEmpty(message)){
                    try {
                        JSONObject object = new JSONObject(message);
                        JSONObject head = object.getJSONObject("Head");
                        if (head.getInt("Status") == 0){
                            Toast.makeText(SetupZuHeActivity.this,"创建成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(SetupZuHeActivity.this,"创建失败",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.execute();
    }
}
