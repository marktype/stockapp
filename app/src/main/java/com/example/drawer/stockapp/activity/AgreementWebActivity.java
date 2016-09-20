package com.example.drawer.stockapp.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.MyDialog;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class AgreementWebActivity extends BascActivity {
    private int type;
    public static final String URLTYPE = "urltype";
    public static final String URL = "url";
    private String url ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.write_color));
        setContentView(R.layout.activity_agreement_web);
        type = getIntent().getIntExtra(URLTYPE,0);
        url = getIntent().getStringExtra(URL);

        dialog = ManagerUtil.getDiaLog(this);

        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.above_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);
        mTitleRelat.setBackgroundColor(getResources().getColor(R.color.write_color));

        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);//auto load images
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);//zoom
        webView.getSettings().setUseWideViewPort(false); //auto adjust screen
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebChromeClient(new WebChromeClientInfo());

        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView mTxt = (TextView) findViewById(R.id.back_txt);

        if (type == 1){      //关于爱猫爪
            mTxt.setText("关于爱猫爪");
            webView.loadUrl("http://www.imaozhua.com/about.html");
        }else if (type == 2){    //用户协议
            mTxt.setText("用户协议");
            webView.loadUrl("http://www.imaozhua.com/service.html");
        }else if (type == 3){
            mTxt.setText("新闻");
            webView.loadUrl(url);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }

    private MyDialog dialog;
    /**
     * web加载进度
     */
    private class WebChromeClientInfo extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress >= 100) {
                dialog.dismiss();
            }
        }
    }
}
