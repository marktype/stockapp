package com.example.drawer.stockapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.DynamicInfoAdapter;
import com.example.drawer.stockapp.adapter.ImageAdapter;
import com.example.drawer.stockapp.customview.MyGridView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.CommnetInfo;
import com.example.drawer.stockapp.model.DynamicsInfo;
import com.example.drawer.stockapp.model.TrendsInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dynamic);
        tintManager.setStatusBarTintResource(R.color.write_color);
        shareBean = getIntent().getParcelableExtra(DYNAMICINFO);
        type = getIntent().getIntExtra(TYPE,0);
        initWight();
        initSoftWindow(type);
        DynamicTask task = new DynamicTask();
        task.execute();
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
        if (shareBean != null){
            if (!TextUtils.isEmpty(shareBean.getImgUrl())){
                Picasso.with(this).load(shareBean.getImgUrl()).into(head);
            }
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



        mBackimg.setOnClickListener(this);
        mZhuanFa.setOnClickListener(this);
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
                initSoftWindow(2);
                break;
            case R.id.dongtai_pinglun:
                initSoftWindow(1);
                break;
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
            hashMap.put("Id","0");
            hashMap.put("Type","0");
            String message = HttpManager.newInstance().getHttpDataByThreeLayer("",hashMap,HttpManager.COMMENT_LIST_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String message = s;
            Log.d("tag","message--dy---"+message);
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
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN|WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                break;
            case 2:
                mCommentEdit.setHint("写下你的转发内容");
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN|WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                break;
        }
    }
}
