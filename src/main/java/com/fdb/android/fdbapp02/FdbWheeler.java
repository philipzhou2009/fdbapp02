package com.fdb.android.fdbapp02;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by philip on 12/10/14.
 */
public class FdbWheeler implements Comparable<FdbWheeler>{

    //static float mScreenRatioW = 1.0f;
    //static float mScreenRatioH = 1.0f;

    public String mName = "";
    public float mXcoord;
    public float mYcoord;
    public float mDegree;
    TextView mTV = null;
    boolean mFlag = false;
    final float mLX = 740f;
    final float mLY = 225f;
    //final float mLX = 800f;
    //final float mLY = 150f;

    float mRealX = 0;
    float mRealY = 0;

    View mGridSelection;

    public FdbWheeler() {
    }

    public FdbWheeler(String name, float xcoord, float ycoord, float degree) {
        mName = name;
        mXcoord = xcoord;
        mYcoord = ycoord;
        mDegree = degree;
        mFlag = false;
    }

    public void grayOut() {
        if (mFlag == false) {
            mTV.setOnClickListener(null);
            mTV.setTextColor(Color.GRAY);
        } else {
            mTV.setOnClickListener(null);
        }
    }

    public View createGridSelection(final SelectionsActivity context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View grid = new View(context);
        grid = inflater.inflate(R.layout.grid_selection, null);
        TextView textView = (TextView) grid.findViewById(R.id.grid_text);
        textView.setText(mName);

        mGridSelection = grid;

        final FdbWheeler wheeler = this;
        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TextView tv = (TextView) v;
                TextView tv = (TextView) v.findViewById(R.id.grid_text);
                if (wheeler.mFlag == false) {
                    wheeler.mFlag = true;
                    tv.setTextSize(24);
                    tv.setTypeface(Typeface.DEFAULT_BOLD);
                    tv.setShadowLayer((float) 0.07, 3, 2, Color.GRAY);

                } else {
                    wheeler.mFlag = false;
                    tv.setTextSize(22);
                    tv.setTypeface(Typeface.DEFAULT);
                    tv.setShadowLayer((float) 0, 3, 3, Color.BLACK);

                }
                context.showColorWheelButton();
            }
        });

        return grid;
    }

    public TextView createTextView(final Activity activity) {
        TextView tv = new TextView(activity);
        tv.setText(mName);
        tv.setTextColor(Color.WHITE);
        tv.setRotation(mDegree);
        tv.setShadowLayer((float) 0.06, 5, 5, Color.BLACK);

        /*
        mRealX = (mXcoord + mLX) * mScreenRatioW;
        mRealY = (mYcoord + mLY) * mScreenRatioH;
        */

        mRealY = FdbHelper.fdbHelperCalcYCoord(mYcoord + mLY);
        mRealX = FdbHelper.fdbHelperCalcXCoord(mRealY, (mXcoord + mLX), (mYcoord + mLY));
        Log.e("fcw, mRealX=", Float.toString(mRealX));
        Log.e("fcw, mRealY=", Float.toString(mRealY));

        tv.setX(mRealX);
        tv.setY(mRealY);

        mTV = tv;
        mFlag = true;

        return tv;
    }

    public void hideTextView() {
        if (mFlag == true && mTV != null) {
            mTV.setVisibility(View.INVISIBLE);
        }
    }

    public int compareTo(FdbWheeler other){
        return mName.compareTo(other.mName);
    }
}
