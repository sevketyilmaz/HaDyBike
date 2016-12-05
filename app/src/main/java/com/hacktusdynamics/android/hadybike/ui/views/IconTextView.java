package com.hacktusdynamics.android.hadybike.ui.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class IconTextView extends TextView {
    public IconTextView(Context context) {
        super(context);
        setIcomoonFont();
    }

    public IconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setIcomoonFont();
    }

    public IconTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setIcomoonFont();
    }

    private void setIcomoonFont(){
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/icomoon.ttf");
        setTypeface(typeface);
    }
}
