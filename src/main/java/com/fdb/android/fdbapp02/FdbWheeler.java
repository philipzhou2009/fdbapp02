package com.fdb.android.fdbapp02;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by philip on 12/10/14.
 */
public class FdbWheeler {
    public String mName;
    public float mXcoord;
    public float mYcoord;
    public float mDegree;
    TextView mTV;
    boolean mFlag;
    final float mLX = 800f;
    final float mLY = 150f;

    final float mPX = 300f;
    final float mPY = 200f;

    float mRealX = 0;
    float mRealY = 0;

    View mGridSelection;

    public FdbWheeler(String name, float xcoord, float ycoord, float degree) {
        mName = name;
        mXcoord = xcoord;
        mYcoord = ycoord;
        mDegree = degree;
        mFlag = false;
    }

    public TextView createTextView0(final Activity activity) {
        TextView tv = new TextView(activity);
        tv.setText(mName);
        tv.setTextColor(Color.WHITE);
        tv.setRotation(mDegree);

        mRealX = mXcoord + mLX;
        mRealY = mYcoord + mLY;
        tv.setX(mRealX);
        tv.setY(mRealY);

        final FdbWheeler wheeler = this;
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                if (wheeler.mFlag == false) {
                    tv.setTextSize(16);
                    tv.setTypeface(Typeface.DEFAULT_BOLD);
                    tv.setShadowLayer((float) 0.06, 5, 5, Color.BLACK);
                    wheeler.mFlag = true;
                    //activity.drawFlower();
                } else {
                    tv.setTextSize(14);
                    tv.setTypeface(Typeface.DEFAULT);
                    tv.setShadowLayer((float) 0, 3, 3, Color.BLACK);
                    wheeler.mFlag = false;
                }
            }
        });

        mTV = tv;

        return tv;
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
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                if (wheeler.mFlag == false) {
                    tv.setTextSize(26);
                    tv.setTypeface(Typeface.DEFAULT_BOLD);
                    tv.setShadowLayer((float) 0.06, 3, 5, Color.GRAY);
                    wheeler.mFlag = true;
                } else {
                    tv.setTextSize(22);
                    tv.setTypeface(Typeface.DEFAULT);
                    tv.setShadowLayer((float) 0, 3, 3, Color.BLACK);
                    wheeler.mFlag = false;
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

        mRealX = mXcoord + mLX;
        mRealY = mYcoord + mLY;
        tv.setX(mRealX);
        tv.setY(mRealY);

        mTV = tv;
        mFlag = true;

        return tv;
    }
}
