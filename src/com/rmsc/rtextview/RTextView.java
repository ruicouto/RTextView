package com.rmsc.rtextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Custom textview to support custom fonts.
 * Developed to support the usage of fonts as icons.
 * @author ruicouto
 *
 */
public class RTextView extends TextView {
	
	private TypedArray mStyledAttributes;
	private String mFontName;
	private String mPlaceholder;
	private Rect mTempBounds;
	private Typeface mTypeface;
	private int activeColor;
	private int baseColor;
	
	
	/**
	 * Default constructor.
	 * @param context
	 */
	public RTextView(Context context) {
		super(context);
		init(context,null);
	}
	
	/**
	 * Constructor with attribute set.
	 * @param context
	 * @param attrs
	 */
	public RTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context,attrs);
	}
	
	/**
	 * Constructor with attribute set and def style.
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public RTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context,attrs);
	}
	
	/**
	 * Common method to initialize data.
	 * @param context
	 * @param attrs
	 */
	private void init(Context context, AttributeSet attrs) {
		mTempBounds = new Rect();
		mTypeface = Typeface.DEFAULT;
		if(attrs!=null) {
			mStyledAttributes = context.getTheme().obtainStyledAttributes(attrs,R.styleable.RTextView,0, 0);
			mFontName = mStyledAttributes.getString(R.styleable.RTextView_typeface);
			mPlaceholder = mStyledAttributes.getString(R.styleable.RTextView_placeholder);
			String c = mStyledAttributes.getString(R.styleable.RTextView_activecolor);
			if(c!=null) {
				activeColor = Color.parseColor(c);
			} else {
				activeColor = -1;
			}
			baseColor = getCurrentTextColor();
			
			try {
				mTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/"+mFontName+".ttf");	
			} catch(Exception e) {
				Log.e("RTextView", "Unable to load font '" + mFontName + ".ttf'.\nDo the file 'assets/fonts/"+mFontName+".ttf' exists?");
			}
			
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			if(activeColor!=-1) {
				setTextColor(activeColor);
				invalidate();
			}
		}
		if(event.getAction() == MotionEvent.ACTION_UP) {
			if(activeColor!=-1) {
				setTextColor(baseColor);
				invalidate();
			}
			return performClick();
		}
		return super.onTouchEvent(event);
	}
	
	@Override
	public boolean performClick() {
		return super.performClick();
	}
	
	/**
	 * Override of the onDraw method.
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		if(mFontName!=null && !mFontName.equals("")) {
			if(isInEditMode()) {
				if(mPlaceholder!=null && !mPlaceholder.equals("")) {
					setText(mPlaceholder);
				} else {
					Log.i("RTextView", "No placeholder was given. Using default char 'x'.");
					setText("x");
				}
			}
		}
		setTypeface(mTypeface);
		super.onDraw(canvas);
	}

	/**
	 * Adjust size for editing mode.
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if(!isInEditMode() || mPlaceholder==null) {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		} else {
			getPaint().getTextBounds(mPlaceholder,0,mPlaceholder.length(),mTempBounds);
			int height = mTempBounds.height();
			int width = mTempBounds.width();
			setMeasuredDimension((int)width, (int)height);			
		}		
	}
	

}
