package com.example.drawer.stockapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.TiaoCangHistoryAdapter;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.HistoryInfoList;
import com.example.drawer.stockapp.model.HistoryTiaoCangInfo;
import com.example.drawer.stockapp.model.TiaoCangInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HistoryRecordActivity extends BascActivity {
    private ListView mList;
    private String id;
    private TiaoCangHistoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.write_color));
        setContentView(R.layout.activity_history_record);
        id = getIntent().getStringExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID);
        initWight();
        HistoryAsyn historyAsyn = new HistoryAsyn();
        historyAsyn.execute(id);
    }

    private void initWight(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.history_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mList = (ListView) findViewById(R.id.history_list);
        adapter = new TiaoCangHistoryAdapter(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }


    /**
     * 获取历史记录
     */
    private class HistoryAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,Object> hashMap = new HashMap<>();
             HashMap<String,String> map = new HashMap<>();
             map.put("PageIndex", "0");
             map.put("PageCount", "0");
             map.put("PageSize", "0");
             hashMap.put("PageInfo",map);
             hashMap.put("Id",strings[0]);
            String message =  HttpManager.newInstance().getHttpDataByThreeLayer("",hashMap,HttpManager.Signals_URL);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)&&!s.equals(HttpManager.FAILED)){
                Gson gson = new Gson();
                HistoryInfoList historyInfoList = gson.fromJson(s,HistoryInfoList.class);
                parseData(historyInfoList);
            }
        }
    }

    private void parseData(HistoryInfoList historyInfoList){
        if (historyInfoList.getHead().getStatus() == 0){
            ArrayList<HistoryTiaoCangInfo> historyList = new ArrayList<>();
            List<HistoryInfoList.ResultBean.DataBean> Data = historyInfoList.getResult().getData();
            for (int i = 0;i<Data.size();i++){
                HistoryTiaoCangInfo historyTiaoCangInfo = new HistoryTiaoCangInfo();
                HistoryInfoList.ResultBean.DataBean bean = Data.get(i);
                historyTiaoCangInfo.setTime(bean.getTime().replace("T"," "));
                ArrayList<TiaoCangInfo> tiaoCnagList = new ArrayList<>();
                List<HistoryInfoList.ResultBean.DataBean.CodesBean> Codes = bean.getCodes();
                for (int j = 0;j<Codes.size();j++){
                    TiaoCangInfo info = new TiaoCangInfo();
                    if (Codes.get(j).getTradeType() == 0){
                        info.setBuyCome(true);
                    }else {
                        info.setBuyCome(false);
                    }
                    info.setStockName(Codes.get(j).getName());
                    info.setStockNum(Codes.get(j).getCode());
                    info.setTradeNumStart(0);
                    info.setTradeNumEnd(Codes.get(j).getVolume());
                    info.setTradePrice(Codes.get(j).getPrice()+"");
                    tiaoCnagList.add(info);
                }
                historyTiaoCangInfo.setList(tiaoCnagList);
                historyList.add(historyTiaoCangInfo);
            }
            adapter.setData(historyList);
            mList.setAdapter(adapter);
        }

    }
}
