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
import com.example.drawer.stockapp.customview.MyDialog;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.listener.StockCallBack;
import com.example.drawer.stockapp.model.HeadIndex;
import com.example.drawer.stockapp.model.TiaoCangClass;
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
    public static final String CHICANG_STOCK = "chicang";    //持仓所有股票,及id等信息
    private int type;
    private double mAllMoney = 1000000;     //总资产
    private SetUpZuHeAdapter adapter;
    private MyListView mList;
    private TextView mSrueBuild;
    private EditText mName,mJIanJie;
    private ArrayList<HeadIndex> list;
    private TiaoCangClass tiaoCangClass;   //调仓信息
    private int[] persentNum;      //调仓百分数
    private double[] stockNum;     //股票数
    private MyDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_zu_he);
        tintManager.setStatusBarTintResource(R.color.write_color);
        mToken = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,null);
        initWight();

        type = getIntent().getIntExtra(TYPE,0);
        if (type == 1){
            mSrueBuild.setText("确认调仓");
            tiaoCangClass = getIntent().getParcelableExtra(CHICANG_STOCK);
            list.addAll(getTiaoCangParsent(tiaoCangClass.getList()));
            adapter.setData(list);
            mList.setAdapter(adapter);
            mName.setText(tiaoCangClass.getName());
            mJIanJie.setText(tiaoCangClass.getDesc());
        }


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
        mSrueBuild = (TextView) findViewById(R.id.setup_sure_txt);
        mName = (EditText) findViewById(R.id.edit_name);    //组合名字
        mJIanJie = (EditText) findViewById(R.id.edit_jianjie_text);   //简介

        mList = (MyListView) findViewById(R.id.stock_mylist);
        adapter = new SetUpZuHeAdapter(this);

        adapter.setOnStockPersentListener(new StockCallBack() {
            @Override
            public void OnBackStockPersent(int position, int persent) {
               HeadIndex headIndex = list.get(position);
                headIndex.setIndexPersent(persent+"");
                Log.d("tag","百分数-------"+persent);
                list.set(position,headIndex);
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


    /**
     * 获取调仓百分数
     */
    public ArrayList<HeadIndex> getTiaoCangParsent(ArrayList<HeadIndex> listHead){
        persentNum = new int[listHead.size()];
        stockNum = new double[listHead.size()];

        ArrayList<HeadIndex> headIndices = new ArrayList<>();
        double price = 0;     //所有股票总价
        for (int i = 0;i<listHead.size();i++){
           HeadIndex headIndex = listHead.get(i);
            price += headIndex.getPrice()*headIndex.getStockNum();
        }
        mAllMoney = tiaoCangClass.getTotalMoney()+price;    //总价
        Log.d("tag","mAllMoney-------"+mAllMoney);
        //现有股票百分比
        for (int i= 0;i<listHead.size();i++){
            HeadIndex headIndex = listHead.get(i);
            stockNum[i] = headIndex.getStockNum();
            try {
                int parsent = (int)(headIndex.getPrice()*headIndex.getStockNum()/mAllMoney*100);
                persentNum[i] = parsent;
                headIndex.setIndexPersent(parsent+"");
            }catch (Exception e){
                e.printStackTrace();
            }
            headIndices.add(headIndex);
        }
        return headIndices;
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
                if (type == 0){
                    String name = mName.getText().toString();
                    String jianjie = mJIanJie.getText().toString();
                    if (!TextUtils.isEmpty(name)){
                        Boolean flag = true;
                        for (int i = 0;i<list.size();i++) {
                            if (list.get(i).getIndexPersent() != null&& !TextUtils.isEmpty(list.get(i).getIndexPersent())){
                            }else {
                                flag = false;
                                Toast.makeText(getApplicationContext(),"0仓位，你让我怎么买！",Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (flag){
                            if (list.size()>0){
                                dialog = ManagerUtil.getDiaLog(this);
                                setUpZuHe(name,jianjie,getVolume());
                            }else {
                                Toast.makeText(getApplicationContext(),"你还没有选择股票呢",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"你还没有输入名字呢！",Toast.LENGTH_SHORT).show();
                    }
                }else if (type == 1){
                    ArrayList<HashMap<String,String>> mapList = getTiaoVolume();
                    if (mapList != null&&mapList.size()>0){
                        dialog = ManagerUtil.getDiaLog(this);
                        TiaoCangAsyn(tiaoCangClass.getStockID(),mapList);
                    }else {
                        Toast.makeText(getApplicationContext(),"你什么都没动，我怎么调,不调就返回",Toast.LENGTH_SHORT).show();
                    }
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
     * 计算创建时股票数
     */
    private ArrayList<HashMap<String,String>> getVolume(){
        ArrayList<HashMap<String,String>> mapList = new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        for (int i = 0;i<list.size();i++){
            int volume = 0;
            HashMap<String,String> hasp = new HashMap<>();
            HeadIndex headIndex = list.get(i);
            if (headIndex.getIndexPersent() != null&& !TextUtils.isEmpty(headIndex.getIndexPersent())){
                volume = (int) (((mAllMoney*Integer.parseInt(headIndex.getIndexPersent())/100)/(headIndex.getPrice()*100))*100);
            }
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
     * 计算调仓时股票数
     */
    private ArrayList<HashMap<String,String>> getTiaoVolume(){
        ArrayList<HashMap<String,String>> mapList = new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);

        for (int i = 0;i<list.size();i++){
            int volume = 0;
            HashMap<String,String> hasp = new HashMap<>();
            HeadIndex headIndex = list.get(i);
            if (headIndex.getType() == 1){     //老仓位
                int persent = Integer.parseInt(headIndex.getIndexPersent());
                Log.d("tag","persent-----"+persent+"-----num----"+persentNum[i]);
                if (persent == 0){
                    hasp.put("Code",headIndex.getIndexNum());
                    hasp.put("Price",headIndex.getPrice()+"");
                    hasp.put("Name",headIndex.getIndexName());
                    hasp.put("Volume",stockNum[i]+"");
                    hasp.put("TradeTime",time);
                    hasp.put("TradeType","Close");
                    mapList.add(hasp);
                }else if (persent != persentNum[i]){

                    if (persent>persentNum[i]){
                            volume = (int) (((mAllMoney*(persent-persentNum[i])/100)/(headIndex.getPrice()*100))*100);
                        hasp.put("TradeType","Open");
                    }else {
                        volume = (int) (((mAllMoney*(persentNum[i]-persent)/100)/(headIndex.getPrice()*100))*100);
                        hasp.put("TradeType","Close");
                    }
                    Log.d("tag","volume-------"+volume);
                    hasp.put("Code",headIndex.getIndexNum());
                    hasp.put("Price",headIndex.getPrice()+"");
                    hasp.put("Name",headIndex.getIndexName());
                    hasp.put("Volume",volume+"");
                    hasp.put("TradeTime",time);
                    mapList.add(hasp);
                }


            }else if (headIndex.getType() == 2){      //新仓位
                if (headIndex.getIndexPersent() != null&& !TextUtils.isEmpty(headIndex.getIndexPersent())){
                    volume = (int) (((mAllMoney*Integer.parseInt(headIndex.getIndexPersent())/100)/(headIndex.getPrice()*100))*100);
                }
                hasp.put("Code",headIndex.getIndexNum());
                hasp.put("Price",headIndex.getPrice()+"");
                hasp.put("Name",headIndex.getIndexName());
                hasp.put("Volume",volume+"");
                hasp.put("TradeTime",time);
                hasp.put("TradeType","Open");
                mapList.add(hasp);
            }
        }
        return mapList;
    }
    /**
     * 调仓
     */
    private void TiaoCangAsyn(final String id, final ArrayList<HashMap<String,String>> listCode){
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("PorfolioId", id);
                map.put("Codes",listCode);
                String message = HttpManager.newInstance().getHttpDataByThreeLayerArrayObject(mToken, map, HttpManager.ChangePosition_URL);
                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                Log.d("tag","o---调仓返回信息-----"+o);
                dialog.dismiss();
                String message = (String) o;
                if (!TextUtils.isEmpty(message)){
                    try {
                        JSONObject object = new JSONObject(message);
                        JSONObject head = object.getJSONObject("Head");
                        if (head.getInt("Status") == 0){
                            Toast.makeText(getApplicationContext(),"调仓成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"调仓失败",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

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
                map.put("CodeList",listCode);

                String message = HttpManager.newInstance().getHttpDataByThreeLayerArrayObject(mToken, map, HttpManager.CreatePorfolio_URL);
                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                Log.d("tag","o---创建返回-----"+o);
                dialog.dismiss();
                String message = (String) o;
                if (!TextUtils.isEmpty(message)){
                    try {
                        JSONObject object = new JSONObject(message);
                        JSONObject head = object.getJSONObject("Head");
                        if (head.getInt("Status") == 0){
                            Toast.makeText(getApplicationContext(),"创建成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"创建失败",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.execute();
    }
}
