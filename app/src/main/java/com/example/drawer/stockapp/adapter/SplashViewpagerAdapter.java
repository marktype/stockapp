package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by on 2016/03/29.
 */
public class SplashViewpagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<Integer> splashImageResourceIdList;

    public SplashViewpagerAdapter(Context mContext, List<Integer> splashImageResourceIdList) {
        this.mContext = mContext;
        this.splashImageResourceIdList = splashImageResourceIdList;
    }

    @Override
    public int getCount() {
        return splashImageResourceIdList == null ? 0 : splashImageResourceIdList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new ViewPager.LayoutParams());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(splashImageResourceIdList.get(position));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
