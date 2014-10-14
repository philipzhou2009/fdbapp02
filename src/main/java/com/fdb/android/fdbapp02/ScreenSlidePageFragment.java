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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


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

    GlowingText glowText;
    float 	startGlowRadius = 25f,
            minGlowRadius   = 8f,
            maxGlowRadius   = 20f;
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

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        //Context context = getActivity();
        //Toast.makeText(context, "" + mPageNumber, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        ViewGroup rootView;
        if(mPageNumber == 0)
        {
            rootView = (ViewGroup) inflater.inflate(R.layout.notes, container, false);
        }
        else
        {
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

        if(mPageNumber==0)
        {
            String topNotes = perfumedata.get(3);
            TextView topNotesView = (TextView) rootView.findViewById(R.id.topnotes);
            topNotesView.setText(topNotes);

            String middleNotes = perfumedata.get(4);
            TextView middleNotesView = (TextView) rootView.findViewById(R.id.middlenotes);
            middleNotesView.setText(middleNotes);

            String baseNotes = perfumedata.get(5);
            TextView baseNotesView = (TextView) rootView.findViewById(R.id.basenotes);
            baseNotesView.setText(baseNotes);

            // Start Glowing :D

            Activity activity = getActivity();

            glowText = new GlowingText(
                    activity,           // Pass activity Object
                    activity.getBaseContext(),   // Context
                    topNotesView,           // TextView
                    minGlowRadius,      // Minimum Glow Radius
                    maxGlowRadius,      // Maximum Glow Radius
                    startGlowRadius,    // Start Glow Radius - Increases to MaxGlowRadius then decreases to MinGlowRadius.
                    Color.WHITE,         // Glow Color (int)
                    gGlowSpeed);                 // Glowing Transition Speed (Range of 1 to 10)

        }
        else
        {
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
