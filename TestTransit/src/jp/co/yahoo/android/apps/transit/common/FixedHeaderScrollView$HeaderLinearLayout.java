// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            FixedHeaderScrollView

public static class OnHeaderDrawListener extends LinearLayout
{
    public static interface OnHeaderDrawListener
    {

        public abstract void onHeaderDraw();
    }


    private OnHeaderDrawListener listener;

    protected void dispatchDraw(Canvas canvas)
    {
        if (listener != null)
        {
            listener.onHeaderDraw();
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        return true;
    }

    public void setOnHeaderDrawListener(OnHeaderDrawListener onheaderdrawlistener)
    {
        listener = onheaderdrawlistener;
    }

    public OnHeaderDrawListener(Context context)
    {
        super(context);
    }

    public OnHeaderDrawListener(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }
}
