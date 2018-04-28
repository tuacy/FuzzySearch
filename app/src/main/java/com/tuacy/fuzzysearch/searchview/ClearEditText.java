package com.tuacy.fuzzysearch.searchview;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.tuacy.fuzzysearch.R;

public class ClearEditText extends AppCompatEditText {

	private Drawable mClearDrawable;
	private Drawable mSearchDrawable;

	public ClearEditText(Context context) {
		super(context);
		init();
	}

	public ClearEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		mClearDrawable = getResources().getDrawable(R.drawable.clear);
		mSearchDrawable = getResources().getDrawable(R.drawable.search);
		setCompoundDrawablesWithIntrinsicBounds(mSearchDrawable, null, null, null);
	}


	@Override
	protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
		super.onTextChanged(text, start, lengthBefore, lengthAfter);
		setClearIconVisible(hasFocus() && text.length() > 0);
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
		super.onFocusChanged(focused, direction, previouslyFocusedRect);
		setClearIconVisible(focused && length() > 0);
	}

	private void setClearIconVisible(boolean visible) {
		setCompoundDrawablesWithIntrinsicBounds(mSearchDrawable, null, visible ? mClearDrawable : null, null);
	}

	@Override
	public boolean performClick() {
		return super.performClick();
	}

	/**
	 * 对删除图标区域设置点击事件，即"点击 = 清空搜索框内容"
	 * 原理：当手指抬起的位置在删除图标的区域，即视为点击了删除图标 = 清空搜索框内容
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			performClick();
		}
		switch (event.getAction()) {
			// 原理：当手指抬起的位置在删除图标的区域，即视为点击了删除图标 = 清空搜索框内容
			case MotionEvent.ACTION_UP:
				if (mClearDrawable != null && event.getX() <= (getWidth() - getPaddingRight()) &&
					event.getX() >= (getWidth() - getPaddingRight() - mClearDrawable.getBounds().width())) {
					setText("");
				}
				break;
		}
		return super.onTouchEvent(event);
	}

}
