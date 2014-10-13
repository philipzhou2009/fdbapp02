package com.fdb.android.fdbapp02;

/**
 * Created by philip on 10/13/14.
 */

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class FdbHelper {
    private static final String ns = null;

    public static int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
    }

    public static class Personality {
        public String mName;
        public float mXcoord;
        public float mYcoord;
        public float mDegree;

        private Personality(String name, float xcoord, float ycoord, float degree) {
            this.mName = name;
            this.mXcoord = xcoord;
            this.mYcoord = ycoord;
            this.mDegree = degree;
        }
    }

    public List<Personality> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List<Personality> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Personality> entries = new ArrayList<Personality>();

        parser.require(XmlPullParser.START_TAG, ns, "personalities");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("personality")) {
                entries.add(readEntry(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private Personality readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "personality");
        String title = null;
        float xcoord = 0;
        float ycoord = 0;
        float degree = 0f;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("name")) {
                title = readByNoteName(parser, name);
            } else if (name.equals("x")) {
                xcoord = Float.parseFloat(readByNoteName(parser, name));
            } else if (name.equals("y")) {
                ycoord = Float.parseFloat(readByNoteName(parser, name));
            } else if (name.equals("degree")) {
                degree = Float.parseFloat(readByNoteName(parser, name));
            }
            else {
                skip(parser);
            }
        }
        return new Personality(title, xcoord, ycoord, degree);
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    private String readByNoteName(XmlPullParser parser, String node) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, node);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, node);
        return title;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    public List<FdbWheeler> parseWheeler(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readWheeler(parser);
        } finally {
            in.close();
        }
    }

    private List<FdbWheeler> readWheeler(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<FdbWheeler> entries = new ArrayList<FdbWheeler>();

        parser.require(XmlPullParser.START_TAG, ns, "personalities");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("personality")) {
                entries.add(readPersonality(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private FdbWheeler readPersonality(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "personality");
        String title = null;
        float xcoord = 0;
        float ycoord = 0;
        float degree = 0f;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("name")) {
                title = readByNoteName(parser, name);
            } else if (name.equals("x")) {
                xcoord = Float.parseFloat(readByNoteName(parser, name));
            } else if (name.equals("y")) {
                ycoord = Float.parseFloat(readByNoteName(parser, name));
            } else if (name.equals("degree")) {
                degree = Float.parseFloat(readByNoteName(parser, name));
            }
            else {
                skip(parser);
            }
        }
        return new FdbWheeler(title, xcoord, ycoord, degree);
    }
}

