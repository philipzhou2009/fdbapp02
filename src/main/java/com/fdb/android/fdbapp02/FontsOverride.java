package com.fdb.android.fdbapp02;

/**
 * Created by philip on 10/21/14.
 * http://stackoverflow.com/questions/2711858/is-it-possible-to-set-font-for-entire-application?rq=1
 * http://stackoverflow.com/questions/2973270/using-a-custom-typeface-in-android/16275257#16275257
 */
import java.lang.reflect.Field;
import android.content.Context;
import android.graphics.Typeface;

public final class FontsOverride {

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected static void setAllDefaultFonts(Context context) {
        try {
            final Typeface bold = Typeface.createFromAsset(context.getAssets(),
                    "fonts/Adobe Garamond Bold.ttf");
            final Typeface italic = Typeface.createFromAsset(context.getAssets(),
                    "fonts/Adobe Garamond Italic.ttf");
            final Typeface boldItalic = Typeface.createFromAsset(context.getAssets(),
                    "fonts/Adobe Garamond Italic.ttf");
            final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                    "fonts/Adobe Garamond Regular.ttf");

            Field DEFAULT = Typeface.class.getDeclaredField("DEFAULT");
            DEFAULT.setAccessible(true);
            DEFAULT.set(null, regular);

            Field DEFAULT_BOLD = Typeface.class
                    .getDeclaredField("DEFAULT_BOLD");
            DEFAULT_BOLD.setAccessible(true);
            DEFAULT_BOLD.set(null, bold);

            Field sDefaults = Typeface.class.getDeclaredField("sDefaults");
            sDefaults.setAccessible(true);
            sDefaults.set(null, new Typeface[] { regular, bold, italic,
                    boldItalic });

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}