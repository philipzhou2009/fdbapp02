package com.fdb.android.fdbapp02;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by philip on 9/15/14.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private SelectionsActivity mActivity;

    private String[] mTitles;
    private List<FdbWheeler> mFdbWheelers;

    //private Integer mCwThumbId;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public ImageAdapter(SelectionsActivity c, List<FdbWheeler> dataList) {
        mContext = c;
        mActivity = c;

        mFdbWheelers = dataList;

        // create drawable grid array
        Integer listSize = mFdbWheelers.size();
        //mThumbIds = new Integer[listSize];
        mTitles = new String[listSize];

        Integer i = 0, flag;

        for (FdbWheeler entry : mFdbWheelers) {
            flag = i++;
            //String thumbnail = entry.thumbnail;
            //Integer resId = fdbHelper.getId(thumbnail, R.drawable.class);
            //mThumbIds[flag] = resId;

            String title = entry.mName;
            mTitles[flag] = title;
        }
    }

    public int getCount() {
        return mTitles.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView0(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {  // if it's not recycled, initialize some attributes

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_selection, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            //ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);

            String title;
            int thumbId;
            if (position < mTitles.length) {
                title = mTitles[position];
                //thumbId = mThumbIds[position];
            } else {
                title = "Color Wheel";
                Integer resId = FdbHelper.getId("colour_wheel_draft_rev_white_640", R.drawable.class);
                //thumbId = resId;

            }

            textView.setText(title);
            //textView.setTextSize(16f);
            textView.setTextColor(Color.rgb(0x89, 0x72, 0x3a));
            //imageView.setImageResource(thumbId);

        } else {
            grid = (LinearLayout) convertView;
        }

        return grid;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {  // if it's not recycled, initialize some attributes

            FdbWheeler wheeler = mFdbWheelers.get(position);
            grid = wheeler.createGridSelection(mActivity);

        } else {
            grid = (LinearLayout) convertView;
        }

        return grid;
    }
}