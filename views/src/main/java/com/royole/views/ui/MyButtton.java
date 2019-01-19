package com.royole.views.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

import com.royole.views.R;


public class MyButtton extends Button {
    private static final String TAG = "MyButtton";
    private boolean mShowText = false;
    private int mTextPos = 0;

    public MyButtton(Context context) {
        this(context, null);
        Log.d(TAG, "MyButtton: 1");
    }

    public MyButtton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MyButtton,
                0, 0);

        try {
            mShowText = a.getBoolean(R.styleable.MyButtton_myButtonshow, false);
            mTextPos = a.getInteger(R.styleable.MyButtton_labelPosition, 0);
        } finally {
            a.recycle();
        }
        Log.d(TAG, "MyButtton: 2 mShowText " + mShowText+ " mTextPos "+ mTextPos);
    }

    public MyButtton(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
        Log.d(TAG, "MyButtton:3 ");
    }

    public MyButtton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d(TAG, "MyButtton: 4");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
