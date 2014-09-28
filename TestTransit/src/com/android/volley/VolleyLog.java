// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class VolleyLog
{
    static class MarkerLog
    {

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

        MarkerLog()
        {
            mFinished = false;
        }
    }

    private static class MarkerLog.Marker
    {

        public final String name;
        public final long thread;
        public final long time;

        public MarkerLog.Marker(String s, long l, long l1)
        {
            name = s;
            thread = l;
            time = l1;
        }
    }


    public static boolean DEBUG;
    public static String TAG;

    public VolleyLog()
    {
    }

    private static transient String buildMessage(String s, Object aobj[])
    {
        StackTraceElement astacktraceelement[];
        String s2;
        int i;
        String s1;
        Locale locale;
        Object aobj1[];
        if (aobj == null)
        {
            s1 = s;
        } else
        {
            s1 = String.format(Locale.US, s, aobj);
        }
        astacktraceelement = (new Throwable()).fillInStackTrace().getStackTrace();
        s2 = "<unknown>";
        i = 2;
_L5:
        if (i < astacktraceelement.length) goto _L2; else goto _L1
_L1:
        locale = Locale.US;
        aobj1 = new Object[3];
        aobj1[0] = Long.valueOf(Thread.currentThread().getId());
        aobj1[1] = s2;
        aobj1[2] = s1;
        return String.format(locale, "[%d] %s: %s", aobj1);
_L2:
        if (astacktraceelement[i].getClass().equals(com/android/volley/VolleyLog))
        {
            break; /* Loop/switch isn't completed */
        }
        String s3 = astacktraceelement[i].getClassName();
        String s4 = s3.substring(1 + s3.lastIndexOf('.'));
        s2 = (new StringBuilder(String.valueOf(s4.substring(1 + s4.lastIndexOf('$'))))).append(".").append(astacktraceelement[i].getMethodName()).toString();
        if (true) goto _L1; else goto _L3
_L3:
        i++;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public static transient void d(String s, Object aobj[])
    {
        Log.d(TAG, buildMessage(s, aobj));
    }

    public static transient void e(String s, Object aobj[])
    {
        Log.e(TAG, buildMessage(s, aobj));
    }

    public static transient void e(Throwable throwable, String s, Object aobj[])
    {
        Log.e(TAG, buildMessage(s, aobj), throwable);
    }

    public static void setTag(String s)
    {
        d("Changing log tag to %s", new Object[] {
            s
        });
        TAG = s;
        DEBUG = Log.isLoggable(TAG, 2);
    }

    public static transient void v(String s, Object aobj[])
    {
        if (DEBUG)
        {
            Log.v(TAG, buildMessage(s, aobj));
        }
    }

    public static transient void wtf(String s, Object aobj[])
    {
        Log.wtf(TAG, buildMessage(s, aobj));
    }

    public static transient void wtf(Throwable throwable, String s, Object aobj[])
    {
        Log.wtf(TAG, buildMessage(s, aobj), throwable);
    }

    static 
    {
        TAG = "Volley";
        DEBUG = Log.isLoggable(TAG, 2);
    }
}
