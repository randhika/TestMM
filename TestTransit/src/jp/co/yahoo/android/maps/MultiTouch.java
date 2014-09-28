// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.view.MotionEvent;

public class MultiTouch
{

    private MotionEvent eve;
    private boolean ver;

    public MultiTouch()
    {
        eve = null;
        ver = false;
    }

    public MultiTouch(MotionEvent motionevent)
    {
        eve = null;
        ver = false;
        if (android.os.Build.VERSION.SDK_INT > 6)
        {
            ver = true;
            eve = motionevent;
        }
    }

    public boolean checkVersion()
    {
        return ver;
    }

    public int getPointerCount()
    {
        if (ver)
        {
            return eve.getPointerCount();
        } else
        {
            return 0;
        }
    }

    public boolean init(MotionEvent motionevent)
    {
        if (android.os.Build.VERSION.SDK_INT > 6)
        {
            ver = true;
            eve = motionevent;
        } else
        {
            ver = false;
        }
        return ver;
    }

    public int wgetX(int i)
    {
        if (ver)
        {
            return (int)eve.getX(i);
        }
        if (i == 0)
        {
            return (int)eve.getX();
        } else
        {
            return -1;
        }
    }

    public int wgetY(int i)
    {
        if (ver)
        {
            return (int)eve.getY(i);
        }
        if (i == 0)
        {
            return (int)eve.getY();
        } else
        {
            return -1;
        }
    }
}
