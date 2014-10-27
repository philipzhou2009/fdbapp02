package com.fdb.android.fdbapp02;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by philip on 10/15/14.
 */
public class FdbAddition extends FdbWheeler implements Parcelable {

    public String mImageName;
    public int mImageResId;

    // for implementation of Parcelable
    public FdbAddition() {
    }

    public int describeContents() {
        return 0;
    }

    public FdbAddition(Parcel in) {
        this.mImageName = in.readString();
        this.mImageResId = in.readInt();
        this.mName = in.readString();
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.mImageName);
        out.writeInt(this.mImageResId);
        out.writeString(this.mName);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public FdbAddition createFromParcel(Parcel in) {
            return new FdbAddition(in);
        }

        public FdbAddition[] newArray(int size) {
            return new FdbAddition[size];
        }
    };


    // end of implementation of Parcelable


    public FdbAddition(String name, float xcoord, float ycoord, String imagename) {
        super(name, xcoord, ycoord, 0);

        mImageName = imagename;
        mImageResId = FdbHelper.getId(mImageName, R.drawable.class);
    }

    public TextView createTextView(final Activity activity) {

        TextView tv = new TextView(activity);
        tv.setText(mName);
        tv.setTextSize(18);
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

                LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.popup_ingredient, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, 1200, 1200);

                ImageView imageView = (ImageView) popupView.findViewById(R.id.popup_image);
                imageView.setImageResource(mImageResId);

                TextView textView = (TextView) popupView.findViewById(R.id.popup_name);
                textView.setText(mName);

                popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);

                ColorWheel aCW = (ColorWheel) activity;
                aCW.mPW = popupWindow;

                Log.e("fcw", "PopupWindow setAlpha");
                FrameLayout layout_MainMenu = (FrameLayout) activity.findViewById(R.id.fcwdimlayer);
                layout_MainMenu.setAlpha(0.8f);

            }
        });

        return tv;
    }

    public TextView createTextViewWithEvent(final Activity activity, final View parentView) {

        //Log.e("fcw", "createTextViewWithEvent");
        TextView tv;

        /*
        if(mTV == null){
            tv = new TextView(activity);
            tv.setText(mName);
            tv.setTextSize(18);
            tv.setTextColor(Color.WHITE);
            tv.setRotation(mDegree);
            tv.setShadowLayer((float) 0.1, 4, 4, Color.BLACK);
            tv.setTypeface(null, Typeface.BOLD);
        }
        else {
            tv = mTV;
        }
        */

        tv = new TextView(activity);
        tv.setText(mName);
        tv.setTextSize(18);
        tv.setTextColor(Color.WHITE);
        tv.setRotation(mDegree);
        tv.setShadowLayer((float) 0.1, 4, 3, Color.BLACK);
        tv.setTypeface(null, Typeface.BOLD);

        mTV = tv;

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.popup_ingredient, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, 1200, 1200);

                ImageView imageView = (ImageView) popupView.findViewById(R.id.popup_image);
                imageView.setImageResource(mImageResId);

                TextView textView = (TextView) popupView.findViewById(R.id.popup_name);
                textView.setText(mName);

                popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);

                //Log.e("fcw", "PopupWindow setAlpha");
                FrameLayout layout_MainMenu = (FrameLayout) activity.findViewById(R.id.fcwdimlayer);
                layout_MainMenu.setAlpha(0.8f);

                View popup_ingredient = popupView.findViewById(R.id.popup_ingredient);
                popup_ingredient.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View arg0, MotionEvent arg1) {
                        popupWindow.dismiss();
                        FrameLayout layout_MainMenu = (FrameLayout) activity.findViewById(R.id.fcwdimlayer);
                        layout_MainMenu.setAlpha(0.0f);
                        return true;
                    }
                });

            }
        });

        return tv;
    }

    public void adjustTextView() {
        TextView textView = mTV;

        mRealX = mXcoord + mLX;
        mRealY = mYcoord + mLY;
        textView.setX(mRealX);
        textView.setY(mRealY);

        // margin and padding
        textView.setPadding(0, 0, 5, 0);
    }
}
