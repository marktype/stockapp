package com.example.drawer.stockapp.customview.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

/**
 * 自定义seekbar
 */
public class BubbleSeekBar extends SeekBar {
	private Drawable mThumbDrawable;
	private BubbleIndicator mBubbleIndicator;
	
	public BubbleSeekBar(Context context) {
		this(context, null);
	}

	public BubbleSeekBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public BubbleSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mBubbleIndicator = new BubbleIndicator(context, attrs, defStyleAttr, "100");
		setOnSeekBarChangeListener(mOnSeekBarChangeListener);
	}
	
    @Override
    public void setThumb(Drawable thumb) {
        super.setThumb(thumb);
        mThumbDrawable = thumb;
    }

	private OnSeekBarChangeListener mOnSeekBarChangeListener = new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
//			mBubbleIndicator.hideIndicator();

		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			mBubbleIndicator.showIndicator(seekBar, mThumbDrawable.getBounds());
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
									  boolean fromUser) {
			if(fromUser)
				mBubbleIndicator.moveIndicator(mThumbDrawable.getBounds(), progress);
		}


	};
}
