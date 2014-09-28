// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.android.volley:
//            VolleyLog

static class mFinished
{
    private static class Marker
    {

        public final String name;
        public final long thread;
        public final long time;

        public Marker(String s, long l, long l1)
        {
            name = s;
            thread = l;
            time = l1;
        }
    }


    public static final boolean ENABLED;
    private static final long MIN_DURATION_FOR_LOGGING_MS;
    private boolean mFinished;
    private final List mMarkers = new ArrayList();

    private long getTotalDuration()
    {
        if (mMarkers.size() == 0)
        {
            return 0L;
        } else
        {
            long l = ((Marker)mMarkers.get(0)).time;
            return ((Marker)mMarkers.get(-1 + mMarkers.size())).time - l;
        }
    }

    public void add(String s, long l)
    {
        this;
        JVM INSTR monitorenter ;
        if (mFinished)
        {
            throw new IllegalStateException("Marker added to finished log");
        }
        break MISSING_BLOCK_LABEL_26;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        mMarkers.add(new Marker(s, l, SystemClock.elapsedRealtime()));
        this;
        JVM INSTR monitorexit ;
    }

    protected void finalize()
        throws Throwable
    {
        if (!mFinished)
        {
            finish("Request on the loose");
            VolleyLog.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
        }
    }

    public void finish(String s)
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        mFinished = true;
        l = getTotalDuration();
        if (l > 0L) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        long l1;
        Iterator iterator;
        l1 = ((Marker)mMarkers.get(0)).time;
        Object aobj[] = new Object[2];
        aobj[0] = Long.valueOf(l);
        aobj[1] = s;
        VolleyLog.d("(%-4d ms) %s", aobj);
        iterator = mMarkers.iterator();
_L4:
        long l2;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        Marker marker = (Marker)iterator.next();
        l2 = marker.time;
        Object aobj1[] = new Object[3];
        aobj1[0] = Long.valueOf(l2 - l1);
        aobj1[1] = Long.valueOf(marker.thread);
        aobj1[2] = marker.name;
        VolleyLog.d("(+%-4d) [%2d] %s", aobj1);
        l1 = l2;
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L1; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    static 
    {
        ENABLED = VolleyLog.DEBUG;
    }

    Marker.time()
    {
        mFinished = false;
    }
}
