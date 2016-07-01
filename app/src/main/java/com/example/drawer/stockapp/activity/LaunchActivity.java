package com.example.drawer.stockapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.drawer.stockapp.MainActivity;
import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.SplashViewpagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class LaunchActivity extends Activity {
    private ViewPager vp_plash;
    private LinearLayout ll_point_container;
    private SplashViewpagerAdapter splashViewpagerAdapter;
    private List<Integer> splashImageResourceIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initWight();
    }

    private void initWight() {
        vp_plash = (ViewPager) findViewById(R.id.vp_plash);
        ll_point_container = (LinearLayout) findViewById(R.id.ll_point_container);
        splashImageResourceIdList = new ArrayList<>();
        final List<ImageView> imageViews = new ArrayList<>();
        splashImageResourceIdList.add(R.mipmap.wallhaven);
        splashImageResourceIdList.add(R.mipmap.wallhaven);
        splashImageResourceIdList.add(R.mipmap.wallhaven);
        splashImageResourceIdList.add(R.mipmap.wallhaven);
        for (int i = 0; i < splashImageResourceIdList.size(); i++) {
            ImageView addView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(5, 0, 5, 0);
            addView.setLayoutParams(layoutParams);
            if (i == 0) {
                addView.setImageResource(R.mipmap.pager_dot_selected);
            } else {
                addView.setImageResource(R.mipmap.pager_dot_normal);
            }
            ll_point_container.addView(addView);
            imageViews.add(addView);
        }
        splashViewpagerAdapter = new SplashViewpagerAdapter(this, splashImageResourceIdList);

        vp_plash.setAdapter(splashViewpagerAdapter);
        vp_plash.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (ImageView imageView : imageViews) {
                    imageView.setImageResource(R.mipmap.pager_dot_normal);
                }
                imageViews.get(position).setImageResource(R.mipmap.pager_dot_selected);
                if (position + 1 == splashImageResourceIdList.size()) {
                    Intent intent = new Intent(LaunchActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
