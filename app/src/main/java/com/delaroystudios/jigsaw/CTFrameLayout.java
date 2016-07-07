package com.delaroystudios.jigsaw;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class CTFrameLayout extends FrameLayout{

	public CTFrameLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CTFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CTFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		onInterceptTouch.OnIntercept(ev);
		return super.onInterceptTouchEvent(ev);
	}
	
	public interface OnInterceptTouch {
		
		public abstract void OnIntercept(MotionEvent ev);
	}
	
	
	OnInterceptTouch onInterceptTouch;
	
	public void setOnInterceptTouchEvent(OnInterceptTouch onInterceptTouch){
		this.onInterceptTouch = onInterceptTouch;
	}
	
}
