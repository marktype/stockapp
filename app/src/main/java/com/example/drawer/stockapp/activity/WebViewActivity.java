package com.example.drawer.stockapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.htttputil.HttpManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WebViewActivity extends BascActivity {
    public static final String URLID = "urlid";
    private String urlId;    //新闻id
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        urlId = getIntent().getStringExtra(URLID);
        initWight();
        NewsInfoAsyn newsInfoAsyn = new NewsInfoAsyn();
        newsInfoAsyn.execute(urlId);


//        Log.d("tag","url-------"+url);
//        webView.loadUrl(url);

//        TextView mText = (TextView) findViewById(R.id.test);
////        Spanned str = Html.fromHtml("<p><h1>Getting started<h1>\\r\\nASP.NET Web API is a framework that makes it easy to build HTTP services that reach a broad range of clients,\\r\\n                    including browsers and mobile devices.ASP.NET Web API is an ideal platform for building RESTful applications on the.NET Framework.</p>");
//
//        mText.setText(Html.fromHtml("<p><h1>Getting started<h1>\r\nASP.NET Web API is a framework that makes it easy to build HTTP services that reach a broad range of clients,\r\n                    including browsers and mobile devices.ASP.NET Web API is an ideal platform for building RESTful applications on the.NET Framework.</p>"));
    }

    /**
     * 初始化控件
     */
    public void initWight(){
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);//auto load images
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);//zoom
        webView.getSettings().setUseWideViewPort(true); //auto adjust screen
        webView.getSettings().setLoadWithOverviewMode(true);

        ImageView mBackImg = (ImageView) findViewById(R.id.web_image_back);
        MyListView myListView = (MyListView) findViewById(R.id.comment_listview);
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.txt_item_layout,setCommentData());
        myListView.setAdapter(adapter);

        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public List<String> setCommentData(){
        List<String> list = new ArrayList<>();
        for(int i = 0;i<5;i++){
            list.add("评论"+i);
        }
        return list;
    }


    /**
     * 获取新闻详情
     */
    private class NewsInfoAsyn extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("Id", strings[0]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,HttpManager.NewsDetail_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }
}
