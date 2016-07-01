package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drawer.stockapp.R;

public class RegisterActivity extends BascActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initWight();
    }

    /**
     * 初始化控件
     */
    public void initWight(){
        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);
        TextView mRegist = (TextView) findViewById(R.id.login_txt);

        mBackImg.setOnClickListener(this);
        mRegist.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.login_txt:
                Intent intent = new Intent(this,RegisterSuccessActivity.class);
                startActivity(intent);
                break;
        }
    }
}
