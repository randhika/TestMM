// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.view.MotionEvent;

// Referenced classes of package jp.co.yahoo.android.common:
//            YExtendedGestureDetector16

public static interface Q
{

    public abstract boolean onDoubleTap(MotionEvent motionevent);

    public abstract boolean onDoubleTapEvent(MotionEvent motionevent);

    public abstract boolean onSingleTapConfirmed(MotionEvent motionevent);
}
