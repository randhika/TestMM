// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import java.lang.ref.WeakReference;

public class AlignImageSpan extends ImageSpan
{

    private WeakReference mDrawableRef;

    public AlignImageSpan(Context context, int i)
    {
        super(context, i);
    }

    private Drawable getCachedDrawable()
    {
        WeakReference weakreference = mDrawableRef;
        Drawable drawable = null;
        if (weakreference != null)
        {
            drawable = (Drawable)weakreference.get();
        }
        if (drawable == null)
        {
            drawable = getDrawable();
            mDrawableRef = new WeakReference(drawable);
        }
        return drawable;
    }

    public void draw(Canvas canvas, CharSequence charsequence, int i, int j, float f, int k, int l, 
            int i1, Paint paint)
    {
        Drawable drawable = getCachedDrawable();
        canvas.save();
        int j1 = drawable.getBounds().bottom - drawable.getBounds().top;
        int k1 = paint.getFontMetricsInt().descent - paint.getFontMetricsInt().ascent;
        int l1;
        if (i1 - k < k1)
        {
            l1 = i1 - j1;
        } else
        {
            l1 = (i1 - k1) + (k1 - j1) / 2;
        }
        canvas.translate(f, l1);
        drawable.draw(canvas);
        canvas.restore();
    }
}
