package com.fdb.android.fdbapp02;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ColorWheel extends Activity {

    //public List<fdbHelper.Personality> mPersonalities;
    public List<FdbWheeler> mFdbWheelers;
    RelativeLayout mCWLayout;
    public ArrayList<String> mSelections;
    public List<PerfumeXmlParser.Entry> mPerfumes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_wheel);

        try {
            mFdbWheelers = FdbHelper.loadPersonalityXml(this);
            mPerfumes = FdbHelper.loadPerfumeXml(this);

        } catch (IOException e) {
            Log.e("FCW", "io error");
        } catch (XmlPullParserException e) {
            Log.e("FCW", "XmlPullParserException error");
        }

        Intent intent = getIntent();
        mSelections = intent.getStringArrayListExtra("selections");

        RelativeLayout colorWheelLayout = (RelativeLayout) findViewById(R.id.colorWheelLayout);
        mCWLayout = colorWheelLayout;

        for(String select: mSelections)
        {
            Log.e("fdb:ColorWheel:select", select);
        }

        for (FdbWheeler wheeler : mFdbWheelers) {
            for(String select: mSelections)
            {
                if(wheeler.mName.equals(select) )
                {
                    //Log.e("fdb:ColorWheel", wheeler.mName);
                    TextView tv = wheeler.createTextView(this);
                    colorWheelLayout.addView(tv);
                }
            }
        }

        this.drawFlower();

        // for test, draw flower for each perfume
        for (PerfumeXmlParser.Entry perfume: mPerfumes)
        {
            View view = perfume.drawFlower(this);
            colorWheelLayout.addView(view);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.color_wheel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<FdbHelper.Personality> loadPersonalityXml0() throws XmlPullParserException, IOException {
        InputStream stream = null;
        FdbHelper xmlParser = new FdbHelper();
        List<FdbHelper.Personality> list = null;

        AssetManager assetMgr = this.getAssets();

        try {
            stream = assetMgr.open("personalities.xml");
            list = xmlParser.parse(stream);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        return list;
    }

    public void drawFlower() {
        int iCount = 0;
        List<FdbWheeler> aEntries = new ArrayList<FdbWheeler>();

        for (FdbWheeler wheeler : mFdbWheelers) {
            if(wheeler.mFlag == true)
            {
                aEntries.add(wheeler);
                iCount++;
            }
        }

        if(iCount == 3)
        {// time to flower
            FdbWheeler wheeler1 = aEntries.get(0);
            FdbWheeler wheeler2 = aEntries.get(1);
            FdbWheeler wheeler3 = aEntries.get(2);

            float fCentralX = (wheeler1.mRealX + wheeler2.mRealX + wheeler3.mRealX)/3;
            float fCentralY = (wheeler1.mRealY + wheeler2.mRealY + wheeler3.mRealY)/3;

            ImageView flower = new ImageView(this);
            flower.setImageResource(R.drawable.icon);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, 50);
            flower.setLayoutParams(layoutParams);

            flower.setX(fCentralX);
            flower.setY(fCentralY);

            mCWLayout.addView(flower);



        }

    }
}
