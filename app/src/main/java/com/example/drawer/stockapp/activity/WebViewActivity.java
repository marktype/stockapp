package com.example.drawer.stockapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.MyListView;

import java.util.ArrayList;
import java.util.List;

public class WebViewActivity extends BascActivity {
    private String url = "https://www.baidu.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initWight();

        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);//auto load images
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);//zoom
        webView.getSettings().setUseWideViewPort(true); //auto adjust screen
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl(url);


    }

    /**
     * 初始化控件
     */
    public void initWight(){
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
}
