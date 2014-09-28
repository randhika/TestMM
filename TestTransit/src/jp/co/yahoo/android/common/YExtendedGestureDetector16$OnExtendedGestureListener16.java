// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.view.MotionEvent;

// Referenced classes of package jp.co.yahoo.android.common:
//            YExtendedGestureDetector16

public static interface 
{

    public abstract boolean onDown(MotionEvent motionevent);

    public abstract boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1);

    public abstract void onHoldAfterMove(MotionEvent motionevent);

    public abstract void onLongPress(MotionEvent motionevent);

    public abstract boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1);

    public abstract void onShowPress(MotionEvent motionevent);

    public abstract boolean onSingleTapUp(MotionEvent motionevent);
}
