package com.example.drawer.stockapp.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.DynamicInfoAdapter;
import com.example.drawer.stockapp.adapter.ImageAdapter;
import com.example.drawer.stockapp.customview.MyDialog;
import com.example.drawer.stockapp.customview.MyGridView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.CommnetInfo;
import com.example.drawer.stockapp.model.DynamicsInfo;
import com.example.drawer.stockapp.model.TrendsInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyDynamicActivity extends BascActivity implements View.OnClickListener{
    public static final String DYNAMICINFO = "dynamicinfo";//获取动态数据
    public static final String TYPE = "type";//获取类型数据
    private DynamicsInfo.ResultBean.ShareBean shareBean;
    private DynamicInfoAdapter adapter;
    private CommnetInfo commnetInfo;
    private ListView mList;
    private int type;     //跳转类型
    private EditText mCommentEdit;
    private String mToken;
    private MyDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dynamic);
        mToken = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,null);
        tintManager.setStatusBarTintResource(R.color.write_color);
        shareBean = getIntent().getParcelableExtra(DYNAMICINFO);
        type = getIntent().getIntExtra(TYPE,0);
        initWight();
        initSoftWindow(type);
        DynamicTask task = new DynamicTask();
        task.execute();
        dialog = ManagerUtil.getDiaLog(this);
    }

    public void initWight(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.dynamic_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);
        View headRelat = LayoutInflater.from(this).inflate(R.layout.dongtai_item_layout,null);

        mCommentEdit = (EditText) findViewById(R.id.dongtai_comment_edit);


        ImageView head = (ImageView) headRelat.findViewById(R.id.dongtai_image);
        TextView name = (TextView) headRelat.findViewById(R.id.dongtai_name);
        TextView content = (TextView) headRelat.findViewById(R.id.dongtai_content);
        MyGridView contentImage = (MyGridView) headRelat.findViewById(R.id.dongtai_cntent_image);
        TextView time = (TextView) headRelat.findViewById(R.id.dongtai_time);
        TextView mZhuanFa = (TextView) headRelat.findViewById(R.id.dongtai_zhuanfa);
        TextView mComment = (TextView) headRelat.findViewById(R.id.dongtai_pinglun);
        TextView mLikes = (TextView) headRelat.findViewById(R.id.dongtai_zan);
//        ImageView mCollect = (ImageView) headRelat.findViewById(R.id.collect_info_img);

        if (shareBean != null){
            if (!TextUtils.isEmpty(shareBean.getImgUrl())){
                Picasso.with(this).load(shareBean.getImgUrl()).into(head);
            }
            content.setMaxLines(100);
            content.setTextSize(14);

            ImageAdapter adapter = new ImageAdapter(this);
            adapter.setData(shareBean.getImgs());
            contentImage.setAdapter(adapter);
            name.setText(shareBean.getNickName());
            content.setText(shareBean.getContent());
            time.setText(shareBean.getUpdateTime());
            mZhuanFa.setText(shareBean.getForward()+"");
            mComment.setText(shareBean.getComments()+"");
            mLikes.setText(shareBean.getLikes()+"");
        }

        mList = (ListView) findViewById(R.id.dynamic_list);
        adapter = new DynamicInfoAdapter(this);

        mList.addHeaderView(headRelat);


        mCommentEdit.setOnKeyListener(onKeyListener);
        mBackimg.setOnClickListener(this);
        mZhuanFa.setOnClickListener(this);
        mComment.setOnClickListener(this);
        mLikes.setOnClickListener(this);
//        mCollect.setOnClickListener(this);
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
                LikeOrCollectAsyn likeOrCollectAsyn = new LikeOrCollectAsyn();
                likeOrCollectAsyn.execute(shareBean.getId(),"Like",mToken);
                break;
//            case R.id.collect_info_img:   //收藏
//                likeOrCollectAsyn = new LikeOrCollectAsyn();
//                if (shareBean.isHasFavorites()){
//                    likeOrCollectAsyn.execute(shareBean.getId(),"CancelFavorites",mToken);
//                }else {
//                    likeOrCollectAsyn.execute(shareBean.getId(),"Favorites",mToken);
//                }
//                break;
        }
    }

    /**
     * 获取动态评论
     */
    private class DynamicTask extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {

            HashMap<String,Object> hashMap = new HashMap<>();
            HashMap<String,String> map = new HashMap<>();
            map.put("PageIndex", "0");
            map.put("PageCount", "0");
            map.put("PageSize", "0");
            hashMap.put("PageInfo",map);
            hashMap.put("Id",shareBean.getId());
            hashMap.put("Type","Comment");
            String message = HttpManager.newInstance().getHttpDataByThreeLayer(mToken,hashMap,HttpManager.COMMENT_LIST_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
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
            info.setFriendName(dataBean.getUsername());
            info.setFriendContent(dataBean.getContent());
            info.setFriendImage(dataBean.getImgUrl());
            list.add(info);
        }
        adapter.setData(list);
        mList.setAdapter(adapter);
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
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN|WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                break;
        }
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
                if (type == 2){
                    likeOrForwordAsyn.execute(shareBean.getId(),"Forward",key,mToken,HttpManager.Comment_URL);
                }else {
                    likeOrForwordAsyn.execute(shareBean.getId(),"Comment",key,mToken,HttpManager.Comment_URL);
                }

                return true;
            }
            return false;
        }
    };


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if(null != this.getCurrentFocus()){
//            /**
//             * 点击空白位置 隐藏软键盘
//             */
//            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
//        }
//        return super .onTouchEvent(event);
//    }

    // 获取点击事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 判定是否需要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    // 隐藏软键盘
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
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
                            Toast.makeText(MyDynamicActivity.this,"发布失败",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MyDynamicActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                            DynamicTask task = new DynamicTask();
                            task.execute();
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
            if (!TextUtils.isEmpty(s)){
                try {
                    JSONObject object = new JSONObject(s);
                    if (object.has("Head")){
                        JSONObject head = object.getJSONObject("Head");
                        if (head.getString("Status").equals("1")){
//                            Toast.makeText(context,"发布失败",Toast.LENGTH_SHORT).show();
                        }else {
//                            Toast.makeText(context,"发布成功",Toast.LENGTH_SHORT).show();
                            DynamicTask task = new DynamicTask();
                            task.execute();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
