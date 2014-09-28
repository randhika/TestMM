// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.view.MotionEvent;

public class MultiTouch
{

    private MotionEvent event;
    private boolean version;

    public MultiTouch()
    {
        event = null;
        version = false;
    }

    public float getX(int i)
    {
        if (version)
        {
            return event.getX(i);
        } else
        {
            return 0.0F;
        }
    }

    public float getY(int i)
    {
        if (version)
        {
            return event.getY(i);
        } else
        {
            return 0.0F;
        }
    }

    public boolean init(MotionEvent motionevent)
    {
        if (android.os.Build.VERSION.SDK_INT > 6)
        {
            version = true;
            event = motionevent;
        } else
        {
            version = false;
        }
        return version;
    }
}
