package com.fdb.android.fdbapp02;

/**
 * Created by philip on 10/21/14.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import java.util.ArrayList;


public class FdbWheelTriangle extends View {
    Paint paint = new Paint();

    ArrayList<float[]> mCoordsList;

    public FdbWheelTriangle(Context context, ArrayList<float[]> coordsList) {
        super(context);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(4);

        mCoordsList = coordsList;
    }

    @Override
    public void onDraw(Canvas canvas) {

        //canvas.drawLine(0, 0, 20, 20, paint);
        //canvas.drawLine(20, 0, 0, 20, paint);
        float[] coords0 = mCoordsList.get(0);
        float[] coords1 = mCoordsList.get(1);
        float[] coords2 = mCoordsList.get(2);

        /*
        canvas.drawLine(coords0[0], coords0[1], coords1[0], coords1[1], paint);
        canvas.drawLine(coords1[0], coords1[1], coords2[0], coords2[1], paint);
        canvas.drawLine(coords2[0], coords2[1], coords0[0], coords0[1], paint);
        */
        Path path = new Path();
        path.moveTo(coords0[0], coords0[1]);
        path.lineTo(coords1[0], coords1[1]);
        //path.moveTo(coords1[0], coords1[1]);
        path.lineTo(coords2[0], coords2[1]);
        //path.moveTo(coords2[0], coords2[1]);
        path.lineTo(coords0[0], coords0[1]);
        path.close();

        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawPath(path, paint);
    }

}
