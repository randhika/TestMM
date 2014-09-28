// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.navdrawer;

import android.view.MotionEvent;

// Referenced classes of package com.navdrawer:
//            SimpleSideDrawer

private abstract class <init>
{

    private boolean mDraggable;
    private float mLastMotionX;
    private boolean mOpening;
    final SimpleSideDrawer this$0;

    public abstract boolean onTouchEvent(MotionEvent motionevent);







    private ()
    {
        this$0 = SimpleSideDrawer.this;
        super();
        mLastMotionX = 0.0F;
        mOpening = false;
        mDraggable = false;
    }

    mDraggable(mDraggable mdraggable)
    {
        this();
    }
}
