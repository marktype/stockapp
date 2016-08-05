package com.example.drawer.stockapp.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drawer.stockapp.R;

public class MyWalletActivity extends BascActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
    }

    public void initWight(){
        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView mYuE = (TextView) findViewById(R.id.yu_e_num_txt);
        TextView mMaoLiang = (TextView) findViewById(R.id.maoliang_num_txt);

        mYuE.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/DIN Medium.ttf"));   //设置字体风格
        mMaoLiang.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/DIN Medium.ttf"));   //设置字体风格
    }
}
