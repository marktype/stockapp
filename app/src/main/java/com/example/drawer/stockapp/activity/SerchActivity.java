package com.example.drawer.stockapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.SearchAdapter;
import com.example.drawer.stockapp.customview.FlowLayout;
import com.example.drawer.stockapp.customview.MyDialog;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.HeadIndex;
import com.example.drawer.stockapp.model.HotWordInfo;
import com.example.drawer.stockapp.model.NewsSearchInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class SerchActivity extends BascActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    public static final String URL_SEARCH = "search";     //不同地方传入的url不同
    private String url = "http://op.juhe.cn/onebox/news/query?key=906932bbeaaa6fabe024330b6ce53e0e&q=";
    private  EditText mEditTxt;
    private ListView mList;
    private SearchAdapter searchAdapter;
    private FlowLayout mFloelayout;
    private RelativeLayout mHotSearch;
    private MyDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serch);
//        url = getIntent().getStringExtra(URL_SEARCH);    //获取URL
        tintManager.setStatusBarTintResource(R.color.write_color);
        initWight();

        HotSearchAsyn hotSearchAsyn = new HotSearchAsyn();
        hotSearchAsyn.execute();
        dialog = ManagerUtil.getDiaLog(this);
    }

    public void initWight(){

        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.search_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);
        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);
        TextView mSearchTxt = (TextView) findViewById(R.id.search_txt);
        mEditTxt = (EditText) findViewById(R.id.search_edit);
        mList = (ListView) findViewById(R.id.search_listview);

        mFloelayout = (FlowLayout) findViewById(R.id.search_flow);
        mHotSearch = (RelativeLayout) findViewById(R.id.hot_layout);   //热门搜索

        searchAdapter = new SearchAdapter(this);

        mEditTxt.setOnKeyListener(onKeyListener);
        mBackimg.setOnClickListener(this);
        mSearchTxt.setOnClickListener(this);
        mList.setOnItemClickListener(this);
        setEditChangeListener();
    }

    private void setEditChangeListener(){
        mEditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String key = mEditTxt.getText().toString();

                if (!TextUtils.isEmpty(key)){
                    SearchAsyn asyn = new SearchAsyn();
                    asyn.execute(key);
                    mHotSearch.setVisibility(View.GONE);
                    mList.setVisibility(View.VISIBLE);
                }else {
                    mHotSearch.setVisibility(View.VISIBLE);
                    mList.setVisibility(View.GONE);
                }

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.search_txt:
                String key = mEditTxt.getText().toString();
                SearchAsyn asyn = new SearchAsyn();
                asyn.execute(key,url);
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }

    /**
     * 软键盘监听
     */
    private View.OnKeyListener onKeyListener = new View.OnKeyListener() {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                /*隐藏软键盘*/
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if(inputMethodManager.isActive()){
                    inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                }

                //点击进行逻辑处理
                String key = mEditTxt.getText().toString();
                SearchAsyn asyn = new SearchAsyn();
                asyn.execute(key,url);
                return true;
            }
            return false;
        }
    };


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super .onTouchEvent(event);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            HeadIndex index = (HeadIndex) adapterView.getAdapter().getItem(i);
        Intent intent = new Intent(this,AgreementWebActivity.class);
        intent.putExtra(AgreementWebActivity.URLTYPE,3);
        intent.putExtra(AgreementWebActivity.URL,index.getIndexImage());
        startActivity(intent);
    }


    /**
     * 搜索关键词
     */
    private class SearchAsyn extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String message = HttpManager.newInstance().getHttpData(url+strings[0]);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("tag","sss-----搜索---"+s);
            if (!TextUtils.isEmpty(s)&&!s.equals(HttpManager.FAILED)){
                Gson gson = new Gson();
                NewsSearchInfo info = gson.fromJson(s,NewsSearchInfo.class);
                if (info.getError_code() == 0){
                    List<NewsSearchInfo.ResultBean>  mark = info.getResult();
                    ArrayList<HeadIndex> list = new ArrayList<>();
                    for (int i = 0;i<mark.size();i++){
                        HeadIndex index = new HeadIndex();
                        index.setIndexName(mark.get(i).getTitle());
                        index.setIndexNum(mark.get(i).getPdate());
                        index.setIndexImage(mark.get(i).getUrl());
                        list.add(index);
                    }
                    searchAdapter.setData(list);
                    mList.setAdapter(searchAdapter);
                }else {
                    Toast.makeText(getApplicationContext(),"亲，没有找到新闻哦",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * 热门搜索
     */
    private class HotSearchAsyn extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            String message = HttpManager.newInstance().getHttpData("http://v.juhe.cn/toutiao/index?type=caijing&key=bf20d3748fc4d2996d1952c1d0c4a8f8");
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("tag","s--------热门搜索-----"+s);
            dialog.dismiss();
            Gson gson = new Gson();
            HotWordInfo hotWordInfo = gson.fromJson(s,HotWordInfo.class);
            setHotWordData(hotWordInfo);
        }
    }

    private void setHotWordData(HotWordInfo hotWordInfo){
        if (hotWordInfo.getError_code() == 0){
            List<HotWordInfo.ResultBean.DataBean> dataBeen = hotWordInfo.getResult().getData();
            for (int i = 0;i<dataBeen.size()&&i<10;i++){
                View view = getTextView(dataBeen.get(i).getTitle(),dataBeen.get(i).getUrl());
                mFloelayout.addView(view);
            }
        }
    }

    private View getTextView(String title, final String url){
        TextView txt = new TextView(this);
        LinearLayout.LayoutParams aaaa = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
        aaaa.setMargins(10,10,10,10);
        txt.setPadding(5,5,5,5);
        txt.setLayoutParams(aaaa);
        txt.setTextSize(12);
        txt.setText(title);
        txt.setBackground(getResources().getDrawable(R.drawable.layout_selector));
        txt.setGravity(Gravity.CENTER);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SerchActivity.this,AgreementWebActivity.class);
                intent.putExtra(AgreementWebActivity.URL,url);
                intent.putExtra(AgreementWebActivity.URLTYPE,3);
                startActivity(intent);
            }
        });

        return txt;
    }
}
