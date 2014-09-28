// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.ar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class MapBgView extends View
{

    public MapBgView(Context context)
    {
        super(context);
    }

    protected void onDraw(Canvas canvas)
    {
        int i = getWidth();
        int j = getHeight();
        Paint paint = new Paint();
        paint.setColor(Color.argb(96, 0, 0, 0));
        Path path = new Path();
        path.moveTo(0.0F, 0.0F);
        path.rLineTo(i, 0.0F);
        path.rLineTo(0.0F, j);
        path.rLineTo(-i, 0.0F);
        path.rLineTo(0.0F, -j);
        path.rLineTo(10F, 10F);
        path.rLineTo(0.0F, j - 20);
        path.rLineTo(i - 20, 0.0F);
        path.rLineTo(0.0F, -(j - 20));
        path.rLineTo(-(i - 20), 0.0F);
        path.rLineTo(-10F, -10F);
        canvas.drawPath(path, paint);
    }
}
