package com.example.drawer.stockapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.DynamicInfoAdapter;
import com.example.drawer.stockapp.customview.MyDialog;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.CommnetInfo;
import com.example.drawer.stockapp.model.NewsDetial;
import com.example.drawer.stockapp.model.TrendsInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class WebViewActivity extends BascActivity implements View.OnClickListener{
    public static final String URLID = "urlid";
    private String urlId;    //新闻id
    private WebView webView;
    private TextView mTxt,mZhuanFa,mComment,mLikes,mTitle;
    private TextView mLogin;
    private int type;     //跳转类型
    private EditText mCommentEdit;
    private DynamicInfoAdapter adapter;
    private String mToken,urlGet;
    private MyListView mList;
    private CommnetInfo commnetInfo;
    private MyDialog dialog;
    private SharedPreferences sp;
    private ImageView mZanImg;
    private Boolean flag;    //是否点赞
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        tintManager.setStatusBarTintResource(R.color.write_color);
        sp = ShapePreferenceManager.getMySharedPreferences(this);
        mToken = sp.getString(ShapePreferenceManager.TOKEN,null);
        urlId = getIntent().getStringExtra(URLID);
        initWight();
        NewsInfoAsyn newsInfoAsyn = new NewsInfoAsyn();
        newsInfoAsyn.execute(urlId);

        DynamicTask dynamicTask = new DynamicTask();
        dynamicTask.execute(urlId);

        initSoftWindow(type);
        // 切换页面
        dialog = ManagerUtil.getDiaLog(this);
    }


    /**
     * 初始化控件
     */
    public void initWight(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.dynamic_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);//auto load images
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);//zoom
        webView.getSettings().setUseWideViewPort(false); //auto adjust screen
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebChromeClient(new WebChromeClientInfo());
        //设置Web视图
        webView.setWebViewClient(new webViewClient ());
        webView.getSettings().setAllowFileAccess(true);//资源加载超时操作


        RelativeLayout mLayout = (RelativeLayout) findViewById(R.id.pinglun_relat);    //评论选项

        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);
//        mTxt = (TextView) findViewById(R.id.test_txt);   //文本展示

        mCommentEdit = (EditText) findViewById(R.id.dongtai_comment_edit);
        mZhuanFa = (TextView) findViewById(R.id.dongtai_zhuanfa);
        mComment = (TextView) findViewById(R.id.dongtai_pinglun);
        mLikes = (TextView) findViewById(R.id.dongtai_zan);
        mTitle = (TextView) findViewById(R.id.back_txt);    //题目
        ImageView mShare = (ImageView) findViewById(R.id.share_img);   //分享
        mLogin = (TextView) findViewById(R.id.login_btn);    //登录
        mZanImg = (ImageView) findViewById(R.id.dianzan_img);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.zan_relate);

        mList = (MyListView) findViewById(R.id.dynamic_list);
        adapter = new DynamicInfoAdapter(this);

        mBackImg.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mZhuanFa.setOnClickListener(this);
        mComment.setOnClickListener(this);
        layout.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mCommentEdit.setOnKeyListener(onKeyListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mToken = sp.getString(ShapePreferenceManager.TOKEN,null);

        if (!TextUtils.isEmpty(mToken)){
            mLogin.setVisibility(View.GONE);
        }else {
            mLogin.setVisibility(View.VISIBLE);
        }

        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.dongtai_zhuanfa:
                Toast.makeText(getApplicationContext(),"该功能还在完善",Toast.LENGTH_SHORT).show();
                if (!TextUtils.isEmpty(mToken)){
                    type  = 2;
                    initSoftWindow(type);
                }else {
                    Intent intent = new Intent(this,LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.dongtai_pinglun:
                if (!TextUtils.isEmpty(mToken)){
                    type = 1;
                    initSoftWindow(type);
                }else {
                    Intent intent = new Intent(this,LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.zan_relate:
                if (!TextUtils.isEmpty(mToken)){
                    dialog = ManagerUtil.getDiaLog(this);
                    if (flag){
                        LikeOrCollectAsyn likeOrCollectAsyn = new LikeOrCollectAsyn();
                        likeOrCollectAsyn.execute(urlId,"3",mToken);
//                        mLikes.setText((Integer.parseInt(mLikes.getText().toString())-1)+"");
//                        mZanImg.setImageResource(R.mipmap.zan);
                        flag = false;
                    }else {
                        LikeOrCollectAsyn likeOrCollectAsyn = new LikeOrCollectAsyn();
                        likeOrCollectAsyn.execute(urlId,"2",mToken);
                        flag = true;
//                        mZanImg.setImageResource(R.mipmap.y_dianzan);
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"你还未登陆，请先登录",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.share_img:

                if (!TextUtils.isEmpty(urlGet)){
                    showShare(mTitle.getText().toString(),urlGet);
                }else {
                    showShare(mTitle.getText().toString(),"");
                }
                break;
            case R.id.login_btn:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK();
    }
    /**
     * 分享
     */
    private void showShare(String shareTxt,String image) {

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(shareTxt);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("");

        // text是分享文本，所有平台都需要这个字段
        oks.setText(shareTxt);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        oks.setImageUrl(image);
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("");

// 启动分享GUI
        oks.show(this);
    }
    /**
     * r软件盘弹出状况
     * @param type
     */
    public void initSoftWindow(int type){
        switch (type){
            case 0:
                mCommentEdit.setHint("写下你的评论");
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                break;
            case 1:
                mCommentEdit.setHint("写下你的评论");
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN|WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                break;
            case 2:
                mCommentEdit.setHint("写下你的转发内容");
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN|WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                break;
        }
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
            if (!TextUtils.isEmpty(s)){
                Gson gson = new Gson();
                NewsDetial newsDetial = gson.fromJson(s,NewsDetial.class);
                if (newsDetial.getHead().getStatus() == 0){
                    NewsDetial.ResultBean bean = newsDetial.getResult();
                    if (bean.getContent() != null&&!TextUtils.isEmpty(bean.getContent())){
                        //Html.fromHtml(bean.getContent()).toString()    此种转换有点奇葩，通常是不加Html.fromHtml转换的
                        webView.loadDataWithBaseURL("about:blank", Html.fromHtml(bean.getContent()).toString(), "text/html", "utf-8",null);
                    }else if (bean.getTargetUrl() != null&&!TextUtils.isEmpty(bean.getTargetUrl())){
                        webView.loadUrl(bean.getTargetUrl());
                    }
                    urlGet  = bean.getTargetUrl();
                    mTitle.setText(bean.getTitle());
                    mZhuanFa.setText(bean.getForward()+"");
                    mComment.setText(bean.getComments()+"");
                    mLikes.setText(bean.getLikes()+"");

                    if (bean.isHasLike()){
                        flag= true;
                        mZanImg.setImageResource(R.mipmap.y_dianzan);
                    }else {
                        flag = false;
                        mZanImg.setImageResource(R.mipmap.zan);
                    }
                }
            }
        }
    }

    /**
     * 获取新闻评论
     */
    private class DynamicTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            HashMap<String,Object> hashMap = new HashMap<>();
            HashMap<String,String> map = new HashMap<>();
            map.put("PageIndex", "0");
            map.put("PageCount", "0");
            map.put("PageSize", "0");
            hashMap.put("PageInfo",map);
            hashMap.put("Id",strings[0]);
            hashMap.put("Type","Comment");
            String message = HttpManager.newInstance().getHttpDataByThreeLayer("",hashMap,HttpManager.CommentsList_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String message = s;
            if (!TextUtils.isEmpty(message)){
                Gson gson = new Gson();
                commnetInfo = gson.fromJson(message,CommnetInfo.class);
                parseData(commnetInfo);
            }
        }
    }

    public void parseData(CommnetInfo commnetInfo){
        List<CommnetInfo.ResultBean.DataBean> data = commnetInfo.getResult().getData();
        ArrayList<TrendsInfo> list = new ArrayList<>();
        for (int i = 0;i<data.size();i++){
            CommnetInfo.ResultBean.DataBean dataBean = data.get(i);
            TrendsInfo info = new TrendsInfo();
            info.setFriendName(dataBean.getNickName());
            info.setFriendContent(dataBean.getContent());
            info.setFriendImage(dataBean.getImgUrl());
            list.add(info);
        }
        adapter.setData(list);
        mList.setAdapter(adapter);
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
                String key = mCommentEdit.getText().toString();
                LikeOrForwordAsyn likeOrForwordAsyn = new LikeOrForwordAsyn();
                if (!TextUtils.isEmpty(mToken)){
                    if (type == 2){
                        Toast.makeText(getApplicationContext(),"该功能还在完善",Toast.LENGTH_SHORT).show();
//                        likeOrForwordAsyn.execute(urlId,"Forward",key,mToken,HttpManager.News_Comment_URL);    //完善之后放开
                    }else {
                        likeOrForwordAsyn.execute(urlId,"Comment",key,mToken,HttpManager.News_Comment_URL);
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"你还未登陆，请先登录",Toast.LENGTH_SHORT).show();
                }

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

    /**
     * 搜索评论、转发
     */
    private class LikeOrForwordAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> map = new HashMap<>();
            map.put("Id", strings[0]);
            map.put("Type", strings[1]);
            map.put("Content", strings[2]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer(strings[3], map, strings[4]);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)){
                try {
                    JSONObject object = new JSONObject(s);
                    if (object.has("Head")){
                        JSONObject head = object.getJSONObject("Head");
                        if (head.getString("Status").equals("1")){
                            Toast.makeText(getApplicationContext(),"发布失败",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"发布成功",Toast.LENGTH_SHORT).show();
                            DynamicTask dynamicTask = new DynamicTask();
                            dynamicTask.execute(urlId);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * 搜索收藏、点赞
     */
    private class LikeOrCollectAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> map = new HashMap<>();
            map.put("Id", strings[0]);
            map.put("Type", strings[1]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer(strings[2], map, HttpManager.Favorites_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            if (!TextUtils.isEmpty(s)){
                try {
                    JSONObject object = new JSONObject(s);
                    if (object.has("Head")){
                        JSONObject head = object.getJSONObject("Head");
                        if (head.getString("Status").equals("1")){
                            Toast.makeText(getApplicationContext(),head.getString("Msg"),Toast.LENGTH_SHORT).show();
                        }else {
//                            Toast.makeText(context,"发布成功",Toast.LENGTH_SHORT).show();
                            NewsInfoAsyn newsInfoAsyn = new NewsInfoAsyn();
                            newsInfoAsyn.execute(urlId);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

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

    private long timeout = 10000;


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 1:
                    //这里对已经显示出页面且加载超时的情况不做处理
                    dialog.dismiss();
                    break ;

            }
        }
    };

    private Timer timer;
    /**
     *  /Web视图
     */
    private class webViewClient extends WebViewClient {
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            timer = new Timer();
            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                        /*
                         * 超时后,首先判断页面加载进度,超时并且进度小于100,就执行超时后的动作
                         */
                    if (WebViewActivity.this.webView.getProgress() < 100) {
                        Log.d("testTimeout", "timeout...........");
                        Message msg = new Message();
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                        timer.cancel();
                        timer.purge();
                    }
                }
            };
            timer.schedule(tt, timeout, 1);
        }

        /**
         * onPageFinished指页面加载完成,完成后取消计时器
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            timer.cancel();
            timer.purge();
        }

    }
}
