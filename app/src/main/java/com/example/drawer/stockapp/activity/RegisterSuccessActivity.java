package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.drawer.stockapp.MainActivity;
import com.example.drawer.stockapp.R;

public class RegisterSuccessActivity extends BascActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);
        initWight();
    }

    /**
     * 初始化控件
     */
    public void initWight(){
        TextView mBackImg = (TextView) findViewById(R.id.success_txt);

        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterSuccessActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
