package com.loyagram.android.campaignsdk.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.loyagram.android.campaignsdk.R;

/**
 * Custom Spinner text for Language selection.
 */

public class SpinnerTextVIew extends AppCompatTextView {
    public SpinnerTextVIew(Context context) {
        super(context);
    }

    public SpinnerTextVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributeSet(context, attrs);

    }

    private void setAttributeSet(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.TextViewSpinner);
        String customFont = a.getString(R.styleable.TextViewSpinner_customFont);
        setCustomFont(ctx, customFont);
        a.recycle();
    }
    public boolean setCustomFont(Context ctx, String asset) {
        Typeface tf;
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(), asset);
        } catch (Exception e) {

            return false;
        }

        setTypeface(tf);
        return true;
    }
}
