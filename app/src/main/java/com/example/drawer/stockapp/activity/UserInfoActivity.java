package com.example.drawer.stockapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.drawer.stockapp.R;

public class UserInfoActivity extends BascActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        initWeight();
    }

    public void initWeight() {
        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);


        mBackimg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
        }
    }
}
