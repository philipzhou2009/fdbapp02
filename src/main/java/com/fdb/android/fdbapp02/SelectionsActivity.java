package com.fdb.android.fdbapp02;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class SelectionsActivity extends Activity {

    public List<FdbWheeler> mFdbWheelers;
    public Button mShowCW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selections);

        Button showcolorwheel = (Button)  findViewById(R.id.showcolorwheel);
        showcolorwheel.setVisibility(View.INVISIBLE);
        mShowCW = showcolorwheel;

        try {
            //List<PerfumeXmlParser.Entry> entries = loadPerfumeXml();
            mFdbWheelers = loadPersonalityXml();
        } catch (IOException e) {
            //return getResources().getString(R.string.connection_error);
            Log.e("FDB", "io error");
        } catch (XmlPullParserException e) {
            //return getResources().getString(R.string.xml_error);
            Log.e("FDB", "XmlPullParserException error");

        }

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, mFdbWheelers));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.selections, menu);
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

    private List<FdbWheeler> loadPersonalityXml() throws XmlPullParserException, IOException {
        InputStream stream = null;
        FdbHelper xmlParser = new FdbHelper();
        List<FdbWheeler> list = null;

        AssetManager assetMgr = this.getAssets();

        try {
            stream = assetMgr.open("personalities.xml");
            list = xmlParser.parseWheeler(stream);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        return list;
    }

    public void test() {
        Log.e("fdb", "test outputtttttttt");
    }

    public void showColorWheelButton() {
        int iCount = 0;
        for(FdbWheeler wheeler: mFdbWheelers)
        {
            if(wheeler.mFlag == true)
            {
                iCount++;
            }
        }

        if(iCount == 3)
        {
            mShowCW.setVisibility(View.VISIBLE);
        }
        else
        {
            mShowCW.setVisibility(View.INVISIBLE);

        }
    }
}
