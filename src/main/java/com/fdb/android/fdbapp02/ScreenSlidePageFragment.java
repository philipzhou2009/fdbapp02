/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fdb.android.fdbapp02;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy title indicating
 * the page number, along with some dummy text.
 */
public class ScreenSlidePageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    public static ArrayList<String> perfumedata;
    public static ArrayList<FdbAddition> mNotes;

    GlowingText glowText;
    float startGlowRadius = 25f,
            minGlowRadius = 8f,
            maxGlowRadius = 20f;
    Integer gGlowSpeed = 3,
            gGlowColor = 0xffac762a;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static ScreenSlidePageFragment create(int pageNumber, ArrayList<String> strList) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);

        //System.out.println("ScreenSlidePageFragment: Contents of perfume data: " + perfumedata);
        perfumedata = strList;

        return fragment;
    }

    public static ScreenSlidePageFragment create(int pageNumber, ArrayList<String> strList, ArrayList<FdbAddition> notesData) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);

        //System.out.println("ScreenSlidePageFragment: Contents of perfume data: " + perfumedata);
        perfumedata = strList;
        mNotes = notesData;

        return fragment;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        //Context context = getActivity();
        //Toast.makeText(context, "" + mPageNumber, Toast.LENGTH_SHORT).show();
    }

    // stackoverflow.com/questions/11590538/dpi-value-of-default-large-medium-and-small-text-views-android

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView;
        if (mPageNumber == 0) {
            rootView = (ViewGroup) inflater.inflate(R.layout.notes, container, false);
        } else {
            rootView = (ViewGroup) inflater.inflate(R.layout.perfumer, container, false);

        }

        String themecolor = perfumedata.get(2);
        LinearLayout perfumeView = (LinearLayout) rootView.findViewById(R.id.perfume);
        perfumeView.setBackgroundColor(Color.parseColor(themecolor));

        String perfumeTitle = perfumedata.get(0);
        TextView titleView = (TextView) rootView.findViewById(R.id.perfumeTitle);
        titleView.setText(perfumeTitle);

        String perfumerName = perfumedata.get(6);
        TextView perfumerNameView = (TextView) rootView.findViewById(R.id.perfumerName);
        perfumerNameView.setText("BY " + perfumerName);

        Activity activity = getActivity();
        final View parentView = activity.findViewById(R.id.pager);

        if (mPageNumber == 0) {
            /*
            for (FdbAddition noteObj : mNotes) {
                Log.e("fcw, noteObj.mName=", noteObj.mName);
            }
            */

            for (int i = 0; i < 3; i++) {
                String sNotes = perfumedata.get(i + 3);
                //Log.e("fcw", sNotes);

                String sLayoutName = "notesLayout" + Integer.toString(i);
                //Log.e("fcw", sLayoutName);
                int id = getResources().getIdentifier(sLayoutName, "id", activity.getPackageName());
                LinearLayout notesLayout = (LinearLayout) rootView.findViewById(id);

                String[] notes = sNotes.split(",");
                if (notes.length > 0 && !sNotes.equals("")) {
                    for (String noteName : notes) {
                        String noteNameNew = noteName.trim();

                        //Log.e("fcw,current note:", noteNameNew);
                        TextView textView = null;
                        boolean bFlag = false;
                        for (FdbAddition noteObj : mNotes) {

                            if (noteObj.mName.equalsIgnoreCase(noteNameNew)) {
                                textView = noteObj.createTextViewWithEvent(activity, parentView);
                                bFlag = true;
                                break;
                            }

                        }
                        if (bFlag == false) {
                            textView = new TextView(activity);
                            textView.setText(noteNameNew);
                            textView.setTextSize(18);
                            textView.setTextColor(Color.WHITE);
                        }
                        notesLayout.addView(textView);

                        TextView textView1 = new TextView(activity);
                        textView1.setText(", ");
                        textView1.setTextSize(18);
                        textView1.setTextColor(Color.WHITE);

                        notesLayout.addView(textView1);

                    }
                }
            }

        } else {
            String perfumerPortrait = perfumedata.get(8);
            Integer portraitImageId = FdbHelper.getId(perfumerPortrait, R.drawable.class);

            ImageView perfumerPortraitView = (ImageView) rootView.findViewById(R.id.perfumerImage);
            perfumerPortraitView.setImageResource(portraitImageId);

            String profile = perfumedata.get(9);
            TextView profileView = (TextView) rootView.findViewById(R.id.perfumerProfile);
            profileView.setText(profile);
        }

        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}
