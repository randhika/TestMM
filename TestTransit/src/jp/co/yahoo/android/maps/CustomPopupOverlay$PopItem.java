// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

// Referenced classes of package jp.co.yahoo.android.maps:
//            Overlay, CustomPopupOverlay

static class off_paint
{

    public int event;
    public float height;
    public Drawable off_image;
    public Paint off_paint;
    public Drawable on_image;
    public Paint on_paint;
    public boolean select;
    public String str;
    public float width;
    public int x;
    public int y;

    public void draw(Canvas canvas, int i, int j)
    {
        if (off_image == null) goto _L2; else goto _L1
_L1:
        if (!select || on_image == null) goto _L4; else goto _L3
_L3:
        Overlay.drawAt(canvas, on_image, i + x, j + y, false);
_L6:
        return;
_L4:
        Overlay.drawAt(canvas, off_image, i + x, j + y, false);
        return;
_L2:
        if (str != null)
        {
            if (select && on_paint != null)
            {
                android.graphics.PopItem popitem1 = on_paint.getFontMetrics();
                canvas.drawText(str, i + x, (float)(j + y) - popitem1.y, on_paint);
                return;
            } else
            {
                android.graphics.PopItem popitem = off_paint.getFontMetrics();
                canvas.drawText(str, i + x, (float)(j + y) - popitem.y, off_paint);
                return;
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    _cls9()
    {
        select = false;
        str = null;
        on_image = null;
        off_image = null;
        event = 0;
        x = 0;
        y = 0;
        width = 0.0F;
        height = 0.0F;
        on_paint = null;
        off_paint = null;
    }
}
