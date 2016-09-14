package com.example.drawer.stockapp.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.example.drawer.stockapp.model.ClassDetial;
import com.example.drawer.stockapp.model.CommnetInfo;
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

public class WebViewUpTitleActivity extends BascActivity implements View.OnClickListener{
    public static final String URLID = "urlid";
    private String urlId;    //新闻id
    private WebView webView;
    private TextView mTxt,mZhuanFa,mComment,mLikes;
    private int type;     //跳转类型
    private EditText mCommentEdit;
    private DynamicInfoAdapter adapter;
    private String mToken;
    private MyListView mList;
    private CommnetInfo commnetInfo;
    private MyDialog dialog;
    private TextView mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        tintManager.setStatusBarTintResource(R.color.write_color);
        mToken = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,null);
        urlId = getIntent().getStringExtra(URLID);
        initWight();
        NewsInfoAsyn newsInfoAsyn = new NewsInfoAsyn();
        newsInfoAsyn.execute(urlId);

        DynamicTask dynamicTask = new DynamicTask();
        dynamicTask.execute(urlId);

        // 切换页面
        dialog = ManagerUtil.getDiaLog(this);
        initSoftWindow(type);
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

        RelativeLayout mLayout = (RelativeLayout) findViewById(R.id.pinglun_relat);    //评论选项
        mLayout.setVisibility(View.GONE);

        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);
        mCommentEdit = (EditText) findViewById(R.id.dongtai_comment_edit);
//        mTxt = (TextView) findViewById(R.id.test_txt);   //文本展示
        mZhuanFa = (TextView) findViewById(R.id.dongtai_zhuanfa);
        mComment = (TextView) findViewById(R.id.dongtai_pinglun);
        mLikes = (TextView) findViewById(R.id.dongtai_zan);

        mList = (MyListView) findViewById(R.id.dynamic_list);
        adapter = new DynamicInfoAdapter(this);

        mBackImg.setOnClickListener(this);
        mZhuanFa.setOnClickListener(this);
        mComment.setOnClickListener(this);
        mLikes.setOnClickListener(this);
        mCommentEdit.setOnKeyListener(onKeyListener);

        mTitle = (TextView) findViewById(R.id.back_txt);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                type  = 2;
                initSoftWindow(type);
                break;
            case R.id.dongtai_pinglun:
                type = 1;
                initSoftWindow(type);
                break;
            case R.id.dongtai_zan:
//                if (!TextUtils.isEmpty(mToken)){
//                    LikeOrCollectAsyn likeOrCollectAsyn = new LikeOrCollectAsyn();
//                    likeOrCollectAsyn.execute(urlId,"Like",mToken);
//                }else {
//                    Toast.makeText(this,"你还未登陆，请先登录",Toast.LENGTH_SHORT).show();
//                }
                break;
        }
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
    private class NewsInfoAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("Id", strings[0]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,HttpManager.CourseDetail_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)){
                Gson gson = new Gson();
                ClassDetial newsDetial = gson.fromJson(s,ClassDetial.class);
                if (newsDetial.getHead().getStatus() == 0){
//                    NewsDetial.ResultBean bean = newsDetial.getResult();
                    ClassDetial.ResultBean.CourseDetailBean bean = newsDetial.getResult().getCourseDetail();
                    ClassDetial.ResultBean.CourseInfoBean infoBean = newsDetial.getResult().getCourseInfo();
                    if (bean.getContent() != null&&!TextUtils.isEmpty(bean.getContent())){
                        //Html.fromHtml(bean.getContent()).toString()    此种转换有点奇葩，通常是不加Html.fromHtml转换的
                        webView.loadDataWithBaseURL("about:blank", Html.fromHtml(bean.getContent()).toString(), "text/html", "utf-8",null);
                    }else if (infoBean.getTargetUrl() != null&&TextUtils.isEmpty(infoBean.getTargetUrl()+"")){
                        webView.loadUrl(infoBean.getTargetUrl()+"");
                    }

                    mTitle.setText(infoBean.getName());
//                    mZhuanFa.setText(bean.getForward()+"");
//                    mComment.setText(bean.getComments()+"");
//                    mLikes.setText(bean.getLikes()+"");

                }
            }
        }
    }

    /**
     * 获取课堂评论
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
            String message = HttpManager.newInstance().getHttpDataByThreeLayer("",hashMap,HttpManager.CourseCommentsInfo_URL);
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
            Log.d("tag","list--------"+dataBean.getNickName());
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
                        likeOrForwordAsyn.execute(urlId,"Forward",key,mToken,HttpManager.CourseComment_URL);
                    }else {
                        likeOrForwordAsyn.execute(urlId,"Comment",key,mToken,HttpManager.CourseComment_URL);
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
                            finish();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

//    /**
//     * 搜索收藏、点赞
//     */
//    private class LikeOrCollectAsyn extends AsyncTask<String,Void,String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            HashMap<String, String> map = new HashMap<>();
//            map.put("Id", strings[0]);
//            map.put("Type", strings[1]);
//            String message = HttpManager.newInstance().getHttpDataByTwoLayer(strings[2], map, HttpManager.Favorites_URL);
//            return message;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            if (!TextUtils.isEmpty(s)){
//                try {
//                    JSONObject object = new JSONObject(s);
//                    if (object.has("Head")){
//                        JSONObject head = object.getJSONObject("Head");
//                        if (head.getString("Status").equals("1")){
//                            Toast.makeText(WebViewUpTitleActivity.this,head.getString("Msg"),Toast.LENGTH_SHORT).show();
//                        }else {
////                            Toast.makeText(context,"发布成功",Toast.LENGTH_SHORT).show();
//                            NewsInfoAsyn newsInfoAsyn = new NewsInfoAsyn();
//                            newsInfoAsyn.execute(urlId);
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//    }

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
