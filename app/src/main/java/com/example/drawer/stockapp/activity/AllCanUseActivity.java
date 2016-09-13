package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.CustomDialog;
import com.example.drawer.stockapp.utils.DataCleanManager;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class AllCanUseActivity extends BascActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_can_use);
        tintManager.setStatusBarTintResource(R.color.write_color);
        initWight();
    }

   public void initWight(){

       RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.all_can_title);    //title布局
       //设置距离顶部状态栏高度
       RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
               DensityUtils.dp2px(this,50));
       params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
       mTitleRelat.setLayoutParams(params);
       ImageView mBackImg = (ImageView) findViewById(R.id.back_img);
       RelativeLayout mNewInfo = (RelativeLayout) findViewById(R.id.news_info_lin);
       RelativeLayout mClean = (RelativeLayout) findViewById(R.id.clean_lin);
       RelativeLayout mAbove = (RelativeLayout) findViewById(R.id.above_lin);

       mClean.setOnClickListener(this);
       mNewInfo.setOnClickListener(this);
       mBackImg.setOnClickListener(this);
       mAbove.setOnClickListener(this);
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
            case R.id.news_info_lin:
                Intent intent = new Intent(this,NewsInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.clean_lin:
                popWinDow();
                break;
            case R.id.above_lin:
                intent = new Intent(this,AgreementWebActivity.class);
                intent.putExtra(AgreementWebActivity.URLTYPE,1);
                startActivity(intent);
                break;
        }
    }

    /**
     * 退出弹框
     */
    public void popWinDow(){
        final CustomDialog dialog = new CustomDialog(this);
        dialog.setMessageText("确认要清除缓存吗？");
        dialog.show();
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataCleanManager.cleanCacheData(AllCanUseActivity.this);
                Toast.makeText(AllCanUseActivity.this, "清除缓存成功！", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }
}
