// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.util.Map;

// Referenced classes of package com.google.android.gms.internal:
//            bc, et, eu, ex, 
//            cf, cj

public final class bh
    implements bc
{

    public bh()
    {
    }

    private static int a(DisplayMetrics displaymetrics, Map map, String s, int i)
    {
        String s1 = (String)map.get(s);
        if (s1 != null)
        {
            int j;
            try
            {
                j = et.a(displaymetrics, Integer.parseInt(s1));
            }
            catch (NumberFormatException numberformatexception)
            {
                eu.D((new StringBuilder()).append("Could not parse ").append(s).append(" in a video GMSG: ").append(s1).toString());
                return i;
            }
            i = j;
        }
        return i;
    }

    public void b(ex ex1, Map map)
    {
        String s = (String)map.get("action");
        if (s == null)
        {
            eu.D("Action missing from video GMSG.");
            return;
        }
        cf cf1 = ex1.ca();
        if (cf1 == null)
        {
            eu.D("Could not get ad overlay for a video GMSG.");
            return;
        }
        boolean flag = "new".equalsIgnoreCase(s);
        boolean flag1 = "position".equalsIgnoreCase(s);
        if (flag || flag1)
        {
            DisplayMetrics displaymetrics = ex1.getContext().getResources().getDisplayMetrics();
            int i = a(displaymetrics, map, "x", 0);
            int j = a(displaymetrics, map, "y", 0);
            int k = a(displaymetrics, map, "w", -1);
            int l = a(displaymetrics, map, "h", -1);
            if (flag && cf1.aQ() == null)
            {
                cf1.c(i, j, k, l);
                return;
            } else
            {
                cf1.b(i, j, k, l);
                return;
            }
        }
        cj cj1 = cf1.aQ();
        if (cj1 == null)
        {
            cj.a(ex1, "no_video_view", null);
            return;
        }
        if ("click".equalsIgnoreCase(s))
        {
            DisplayMetrics displaymetrics1 = ex1.getContext().getResources().getDisplayMetrics();
            int i1 = a(displaymetrics1, map, "x", 0);
            int j1 = a(displaymetrics1, map, "y", 0);
            long l1 = SystemClock.uptimeMillis();
            MotionEvent motionevent = MotionEvent.obtain(l1, l1, 0, i1, j1, 0);
            cj1.b(motionevent);
            motionevent.recycle();
            return;
        }
        if ("controls".equalsIgnoreCase(s))
        {
            String s2 = (String)map.get("enabled");
            if (s2 == null)
            {
                eu.D("Enabled parameter missing from controls video GMSG.");
                return;
            } else
            {
                cj1.l(Boolean.parseBoolean(s2));
                return;
            }
        }
        if ("currentTime".equalsIgnoreCase(s))
        {
            String s1 = (String)map.get("time");
            if (s1 == null)
            {
                eu.D("Time parameter missing from currentTime video GMSG.");
                return;
            }
            try
            {
                cj1.seekTo((int)(1000F * Float.parseFloat(s1)));
                return;
            }
            catch (NumberFormatException numberformatexception)
            {
                eu.D((new StringBuilder()).append("Could not parse time parameter from currentTime video GMSG: ").append(s1).toString());
            }
            return;
        }
        if ("hide".equalsIgnoreCase(s))
        {
            cj1.setVisibility(4);
            return;
        }
        if ("load".equalsIgnoreCase(s))
        {
            cj1.ba();
            return;
        }
        if ("pause".equalsIgnoreCase(s))
        {
            cj1.pause();
            return;
        }
        if ("play".equalsIgnoreCase(s))
        {
            cj1.play();
            return;
        }
        if ("show".equalsIgnoreCase(s))
        {
            cj1.setVisibility(0);
            return;
        }
        if ("src".equalsIgnoreCase(s))
        {
            cj1.o((String)map.get("src"));
            return;
        } else
        {
            eu.D((new StringBuilder()).append("Unknown video action: ").append(s).toString());
            return;
        }
    }
}
