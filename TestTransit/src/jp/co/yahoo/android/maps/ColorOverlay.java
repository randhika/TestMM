// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.Canvas;
import android.graphics.Paint;

// Referenced classes of package jp.co.yahoo.android.maps:
//            Overlay, MapView

public class ColorOverlay extends Overlay
{

    private int color;

    public ColorOverlay(int i)
    {
        color = i;
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        super.draw(canvas, mapview, flag);
        int i = mapview.getWidth();
        int j = mapview.getHeight();
        Paint paint = new Paint();
        paint.setStyle(android.graphics.Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawRect(0.0F, 0.0F, i, j, paint);
    }

    public void setColor(int i)
    {
        color = i;
    }
}
