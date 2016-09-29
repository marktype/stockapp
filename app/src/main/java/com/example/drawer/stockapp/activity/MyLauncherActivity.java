package com.example.drawer.stockapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MyLauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mySharedPreferences = getSharedPreferences("launch", Activity.MODE_PRIVATE);

//        Review.MD5Review(this,"com.example.drawer.stockapp","f879ca72840f62316f29b25d0be0e596");   //检测md5签名是否正确

        /**
         * sp不为空,则取数据,
         */
        int first = mySharedPreferences.getInt("first", 0);//
        if (first == 0) {
            Intent intent = new Intent(MyLauncherActivity.this, SplashActivity.class);//这个activity是没改吗?没我把他的名字重新命名,以及纳米改名怎么搞
            startActivity(intent);
            finish();
        } else {
//            Intent intent = new Intent(MyLauncherActivity.this, MainTab.class);
            Intent   intent = new Intent(MyLauncherActivity.this, HomeLauncherActivity.class);

            startActivity(intent);
            finish();
        }
    }
}
