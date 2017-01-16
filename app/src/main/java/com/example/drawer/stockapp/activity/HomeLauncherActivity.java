package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.drawer.stockapp.MainActivity;
import com.example.drawer.stockapp.R;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

public class HomeLauncherActivity extends BascActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        tintManager.setStatusBarTintResource(android.R.color.transparent);
        setContentView(R.layout.activity_home_launcher);
        initWidget();
    }

    private void initWidget() {
        ImageView homeLauncherImageView = (ImageView) findViewById(R.id.home_launcher_imageview);
        Picasso.with(this).load(R.mipmap.start1).into(homeLauncherImageView);
        Timer timer = new Timer();
        timer.schedule(new MyTask(), 3000);
    }

    class MyTask extends TimerTask {

        @Override
        public void run() {
            Intent intent = new Intent(HomeLauncherActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }



}
