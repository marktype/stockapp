package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.MessageTiaoCangAdapter;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.HistoryTiaoCangInfo;
import com.example.drawer.stockapp.model.LastTiaoCangInfo;
import com.example.drawer.stockapp.model.MessageInfo;
import com.example.drawer.stockapp.model.NiuRenInfo;
import com.example.drawer.stockapp.model.NiuRenListInfo;
import com.example.drawer.stockapp.model.TiaoCangInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageActivity extends BascActivity{
    private MessageInfo messageInfo;
    private ListView mList,tiaocangList;
    private String mToken;
    private MessageTiaoCangAdapter adapter;
    private ArrayList<HistoryTiaoCangInfo> historyList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.write_color));
        mToken = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,null);
        setContentView(R.layout.activity_message);
        initWight();
        getMyListData();
    }

    public void initWight(){

        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.all_message_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        RelativeLayout layoutOne = (RelativeLayout) findViewById(R.id.system_message);
        RelativeLayout layoutTwo = (RelativeLayout) findViewById(R.id.now_info);

        layoutOne.setBackgroundColor(getResources().getColor(R.color.write_color));
        layoutTwo.setBackgroundColor(getResources().getColor(R.color.write_color));

        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);

        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mList = (ListView) findViewById(R.id.system_list);
        tiaocangList = (ListView) findViewById(R.id.message_list);

        adapter = new MessageTiaoCangAdapter(this);

//        mList.setOnItemClickListener(this);

        tiaocangList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HistoryTiaoCangInfo tiaoCangInfo = (HistoryTiaoCangInfo) adapterView.getAdapter().getItem(i);
                Intent intent = new Intent(MessageActivity.this,HistoryRecordActivity.class);
                intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID,tiaoCangInfo.getId());
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        String message = (String) adapterView.getAdapter().getItem(i);
//        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this,WebViewActivity.class);
//        startActivity(intent);
//    }

    /**
     * 我的组合列表
     */
    public void getMyListData() {
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String, String> map = new HashMap<>();
                map.put("PageIndex", "0");
                map.put("PageCount", "0");
                map.put("PageSize", "0");
                String message = HttpManager.newInstance().getHttpDataByTwoLayer(mToken, map, HttpManager.MyPorfolio_URL);
                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                String message = (String) o;
                if (!TextUtils.isEmpty(message)) {
                    Gson gson = new Gson();
                    NiuRenListInfo niuRenListInfo = gson.fromJson(message, NiuRenListInfo.class);
                    if (niuRenListInfo.getHead().getStatus() == 0) {
                       setMyZuHeData(niuRenListInfo);
                    }else {

                    }
                }

            }
        }.execute();
    }

    private ArrayList<NiuRenInfo> myInfoList;
    /**
     * 初始化我的组合数据
     *
     * @return
     */
    public ArrayList<NiuRenInfo> setMyZuHeData(NiuRenListInfo niuRenListInfo) {
        long nowTime = System.currentTimeMillis();
        myInfoList = new ArrayList<>();   //我的组合信息
        DecimalFormat df =new DecimalFormat("#0.00");   //保留两位小数
        List<NiuRenListInfo.ResultBean.StrategiesBean> starPorfolioBeen = niuRenListInfo.getResult().getStrategies();
        for (int i = 0; i < starPorfolioBeen.size(); i++) {
            NiuRenListInfo.ResultBean.StrategiesBean ben = starPorfolioBeen.get(i);
            NiuRenInfo info = new NiuRenInfo();
            info.setNiurenName(ben.getTitle());
            info.setId(ben.getId());
            info.setShouyiRate(Double.parseDouble(df.format(ben.getTotleReturns())));
            info.setStockType(ben.getDesc());
            info.setTradeTime(ben.getFavorites() + "");
            if (ben.getPorfolioChooseType() == 1){
                info.setZuheType(ben.getPorfolioChooseType());
                if (nowTime>ManagerUtil.getTime(ben.getRecuitmentStartTime())&&nowTime<ManagerUtil.getTime(ben.getRunStartDay())){
                    info.setType(0);    //招募中
                }else if (nowTime>ManagerUtil.getTime(ben.getRecuitmentStartTime())&&nowTime>ManagerUtil.getTime(ben.getRunStartDay())&&nowTime<ManagerUtil.getTime(ben.getRunEndDay())){
                    info.setType(1);   //运行中
                }else if (nowTime>ManagerUtil.getTime(ben.getRecuitmentStartTime())&&nowTime>ManagerUtil.getTime(ben.getRunStartDay())&&nowTime>ManagerUtil.getTime(ben.getRunEndDay())){
                    info.setType(2);   //已结束
                }
                myInfoList.add(info);
                LastTradeAsyn lastTradeAsyn = new LastTradeAsyn();
                lastTradeAsyn.execute(ben.getId(),ben.getTitle());
            }
        }
        return myInfoList;
    }


    /**
     * 最后一次调仓
     */
    private class LastTradeAsyn extends AsyncTask<String,Void,HashMap<String,String>>{

        @Override
        protected HashMap<String,String> doInBackground(String... strings) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", strings[0]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer("", map, HttpManager.LastTrades_URL);
            map.put("title",strings[1]);
            map.put("info",message);
            return map;
        }

        @Override
        protected void onPostExecute(HashMap<String,String> map) {
            super.onPostExecute(map);
            String s = map.get("info");
            if (!TextUtils.isEmpty(s)){
                Gson gson = new Gson();
                LastTiaoCangInfo lastTiaoCangInfo = gson.fromJson(s,LastTiaoCangInfo.class);
                if (lastTiaoCangInfo.getHead().getStatus() == 0){
                    List<LastTiaoCangInfo.ResultBean> Result = lastTiaoCangInfo.getResult();
                    HistoryTiaoCangInfo historyTiaoCangInfo = new HistoryTiaoCangInfo();
                    ArrayList<TiaoCangInfo> tiaoCnagList = new ArrayList<>();
                    for (int i = 0;i<Result.size();i++){
                        TiaoCangInfo info = new TiaoCangInfo();
                        if (Result.get(i).getTradeType() == 0){
                            info.setBuyCome(true);
                        }else {
                            info.setBuyCome(false);
                        }
                        info.setStockName(Result.get(i).getName());
                        info.setStockNum(Result.get(i).getCode());
                        info.setTradeNumStart(0);
                        info.setTradeNumEnd(Result.get(i).getVolume());
                        info.setTradePrice(Result.get(i).getPrice()+"");
                        historyTiaoCangInfo.setTime(Result.get(i).getTradeTime());
                        tiaoCnagList.add(info);
                    }
                    if (Result.size()>0){
                        historyTiaoCangInfo.setId(map.get("id"));
                        historyTiaoCangInfo.setTitle(map.get("title"));
                        historyTiaoCangInfo.setList(tiaoCnagList);
                        historyList.add(historyTiaoCangInfo);
                    }
                    adapter.setData(historyList);
                    tiaocangList.setAdapter(adapter);
                    }
                }
            }

        }


}
