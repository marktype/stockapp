package com.example.drawer.stockapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.SearchAdapter;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.HeadIndex;
import com.example.drawer.stockapp.model.SearchInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SerchActivity extends BascActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    public static final String URL_SEARCH = "search";     //不同地方传入的url不同
    private String url;
    private  EditText mEditTxt;
    private ListView mList;
    private SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serch);
        url = getIntent().getStringExtra(URL_SEARCH);    //获取URL
        tintManager.setStatusBarTintResource(R.color.write_color);
        initWight();
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
                SearchAsyn asyn = new SearchAsyn();
                asyn.execute(key,url);
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
                Toast.makeText(this,"正在搜索。。",Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this,"选择项目"+i,Toast.LENGTH_SHORT).show();

        //选择后直接跳转返回
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }


    /**
     * 搜索关键词
     */
    private class SearchAsyn extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("Key", strings[0]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,strings[1]);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)&&!s.equals(HttpManager.FAILED)){
                Gson gson = new Gson();
                SearchInfo info = gson.fromJson(s,SearchInfo.class);
                if (info.getHead().getStatus() == 0){
                    List<SearchInfo.ResultBean.MarketDataBean> mark = info.getResult().getMarketData();
                    ArrayList<HeadIndex> list = new ArrayList<>();
                    for (int i = 0;i<mark.size();i++){
                        HeadIndex index = new HeadIndex();
                        index.setIndexName(mark.get(i).getName());
                        index.setIndexNum(mark.get(i).getCode());
                        list.add(index);
                    }
                    searchAdapter.setData(list);
                    mList.setAdapter(searchAdapter);
                }
            }
        }
    }
}
