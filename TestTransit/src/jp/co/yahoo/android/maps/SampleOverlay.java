// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

// Referenced classes of package jp.co.yahoo.android.maps:
//            Overlay, MapView

public class SampleOverlay extends Overlay
{

    static final int BALL_R = 30;
    int cx;
    int cy;
    int mBaseScreenHeight;
    int mBaseScreenWidth;
    boolean mDrawFlg;
    boolean xflag;
    boolean yflag;

    public SampleOverlay()
    {
        cx = 30;
        cy = 30;
        xflag = true;
        yflag = true;
        mBaseScreenWidth = 0;
        mBaseScreenHeight = 0;
        mDrawFlg = false;
        mDrawFlg = true;
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        super.draw(canvas, mapview, flag);
        if (mDrawFlg)
        {
            mBaseScreenWidth = mapview.getWidth();
            mBaseScreenHeight = mapview.getHeight();
            Paint paint = new Paint();
            Paint paint1 = new Paint();
            paint1.setStyle(android.graphics.Paint.Style.FILL);
            paint1.setColor(-1);
            paint.setStyle(android.graphics.Paint.Style.FILL);
            paint.setColor(0xff0000ff);
            canvas.drawRect(0.0F, 0.0F, mBaseScreenWidth, mBaseScreenHeight, paint1);
            canvas.drawCircle(cx, cy, 30F, paint);
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent, MapView mapview)
    {
        int i = (int)motionevent.getX();
        int j = (int)motionevent.getY();
        cx = i;
        cy = j;
        return false;
    }
}
