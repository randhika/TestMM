// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.navdrawer;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package com.navdrawer:
//            SimpleSideDrawer

private class this._cls0 extends View
{

    private static final float CLICK_RANGE = 3F;
    private android.view..mClickListener mClickListener;
    private float mDownX;
    private float mDownY;
    final SimpleSideDrawer this$0;

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        motionevent.setLocation(motionevent.getX() - (float)SimpleSideDrawer.access$1(SimpleSideDrawer.this).getScrollX(), 0.0F);
        SimpleSideDrawer.this.onTouchEvent(motionevent);
        int i = 0xff & motionevent.getAction();
        float f = motionevent.getX();
        float f1 = motionevent.getY();
        if (i == 0)
        {
            mDownX = f;
            mDownY = f1;
        } else
        if (i == 1 && mClickListener != null && Math.abs(mDownX - f) < 3F && Math.abs(mDownY - f1) < 3F)
        {
            mClickListener.mClickListener(this);
            return true;
        }
        return true;
    }

    public void setOnClickListener(android.view. )
    {
        mClickListener = ;
        super.setOnClickListener();
    }

    public (Context context)
    {
        this$0 = SimpleSideDrawer.this;
        super(context);
    }
}
