package com.example.drawer.stockapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.drawer.stockapp.MainActivity;
import com.example.drawer.stockapp.R;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

public class HomeLauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_launcher);
        initWidget();

//        handler.postDelayed(runnable, 1000); //每隔1s执行

    }

    private void initWidget() {
        ImageView homeLauncherImageView = (ImageView) findViewById(R.id.home_launcher_imageview);
        Picasso.with(this).load(R.mipmap.wallhaven).into(homeLauncherImageView);
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
