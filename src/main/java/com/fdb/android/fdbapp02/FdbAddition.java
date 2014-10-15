package com.fdb.android.fdbapp02;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by philip on 10/15/14.
 */
public class FdbAddition extends FdbWheeler {

    public String mImageName;
    public int mImageResId;

    public FdbAddition(String name, float xcoord, float ycoord, String imagename) {
        super(name, xcoord, ycoord, 0);

        mImageName = imagename;
        mImageResId = FdbHelper.getId(mImageName, R.drawable.class);
    }

    public TextView createTextView(final Activity activity) {
        TextView tv = new TextView(activity);
        tv.setText(mName);
        tv.setTextColor(Color.WHITE);
        tv.setRotation(mDegree);
        tv.setShadowLayer((float) 0.1, 4, 4, Color.BLACK);

        mRealX = mXcoord + mLX;
        mRealY = mYcoord + mLY;
        tv.setX(mRealX);
        tv.setY(mRealY);

        mTV = tv;
        mFlag = true;

        final View parentView = activity.findViewById(R.id.colorWheelLayout);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("fcw", "PopupWindow");

                LayoutInflater layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.popup_ingredient, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, 500, 500);

                ImageView imageView = (ImageView)popupView.findViewById(R.id.popup_image);
                imageView.setImageResource(mImageResId);

                TextView textView = (TextView)popupView.findViewById(R.id.popup_name);
                textView.setText(mName);

                popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);

            }
        });

        return tv;
    }

}