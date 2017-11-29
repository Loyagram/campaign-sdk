package com.loyagram.android.campaignsdk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static android.view.View.TEXT_DIRECTION_LTR;


/**
 * Custom Spinner Adapter for language selection
 */

public class SpinnerAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] spinnerItems;
    private String colorPrimary;
    private Typeface typeface;
    private int[] currentIndex;

    /**
     * @param context            context
     * @param textViewResourceId custom text view resource id
     * @param objects            languages to be filled in spinner
     * @param colorPrimary       dropdown item text color
     * @param typeface           drop down item custom font
     */
    public SpinnerAdapter(Context context, int textViewResourceId, String[] objects, String colorPrimary, Typeface typeface, int[] currentIndex) {
        super(context, textViewResourceId, objects);
        this.context = context;
        spinnerItems = objects;
        this.colorPrimary = colorPrimary;
        this.typeface = typeface;
        this.currentIndex = currentIndex;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                @NonNull ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }

    /**
     * Super class method to define spinner item
     *
     * @param position    item index
     * @param convertView reusable view
     * @param parent      parent view
     * @return return spinner item view
     */
    @Override
    public
    @NonNull
    View getView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView label = new TextView(context);
        label.setMaxLines(1);
        label.setEllipsize(TextUtils.TruncateAt.END);
        label.setGravity(Gravity.START);
        int padding = 5;
        final float scale = context.getResources().getDisplayMetrics().density;
        int paddingTop = (int) (padding * scale + 0.5f);
        label.setPadding(10, paddingTop, 0, 0);
        int heightDp = 30;
        int height = (int) (heightDp * scale + 0.5f);
        ListView.LayoutParams params = new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        label.setText(spinnerItems[position]);
        label.setTextColor(Color.parseColor("#FFFFFF"));
        label.setBackgroundColor(Color.TRANSPARENT);
        label.setLayoutParams(params);
        if(Build.VERSION.SDK_INT > 16 ){
            label.setTextDirection(TEXT_DIRECTION_LTR);
        }
        if (typeface != null) {
            label.setTypeface(typeface);
        }
        return label;
    }

    /**
     * Returns custom drop down view for spinner
     *
     * @param position    item indices
     * @param convertView reusable view
     * @param parent      parent view
     * @return return drop down item
     */
    private View getCustomView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setGravity(Gravity.CENTER_VERTICAL);
        int padding = 5;
        final float scale = context.getResources().getDisplayMetrics().density;
        int paddingTop = (int) (padding * scale + 0.5f);
        label.setPadding(20, paddingTop, 0, 0);
        label.setGravity(Gravity.START);

        int heightDp = 30;
        int height = (int) (heightDp * scale + 0.5f);
        ListView.LayoutParams params = new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        label.setText(spinnerItems[position]);
        if(position == currentIndex[0]) {
            label.setTextColor(Color.parseColor(colorPrimary));
            label.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            label.setTextColor(Color.parseColor("#FFFFFF"));
            label.setBackgroundColor(Color.parseColor(colorPrimary));
        }
       label.setLayoutParams(params);
        if(Build.VERSION.SDK_INT > 16 ){
            label.setTextDirection(TEXT_DIRECTION_LTR);
        }
        if (typeface != null) {
            label.setTypeface(typeface);
        }
        return label;
    }

}
