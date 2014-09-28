// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;

import android.util.Log;

// Referenced classes of package jp.co.yahoo.android.yjvoice:
//            MonitorListener, DCWrap

class Monitor
    implements Runnable
{

    private static final String TAG = "YJVOICE:Monitor:";
    private MonitorListener mListener;
    private int mStateCount;
    private Thread mThread;

    public Monitor(MonitorListener monitorlistener)
    {
        mListener = null;
        mThread = null;
        mStateCount = 0;
        mListener = monitorlistener;
    }

    public final void clearState()
    {
        DCWrap dcwrap = mListener.getDataClientWrapper();
        do
        {
            if (mStateCount >= dcwrap.getStateCount() || dcwrap.getState() == -1)
            {
                return;
            }
            mStateCount = 1 + mStateCount;
        } while (true);
    }

    public final void destroy()
    {
        stop();
    }

    public final boolean isMonitoring()
    {
        return mThread != null;
    }

    public final void run()
    {
        DCWrap dcwrap;
        int i;
        dcwrap = mListener.getDataClientWrapper();
        mStateCount = 0;
        i = 0;
_L7:
        if (mThread == null) goto _L2; else goto _L1
_L1:
        int j = dcwrap.getState();
        j;
        JVM INSTR lookupswitch 5: default 80
    //                   -1: 112
    //                   3: 105
    //                   13: 105
    //                   14: 105
    //                   17: 105;
           goto _L3 _L4 _L5 _L5 _L5 _L5
_L3:
        mListener.stateChanged(j);
        mStateCount = 1 + mStateCount;
        i = 0;
        continue; /* Loop/switch isn't completed */
_L5:
        stop();
          goto _L3
_L4:
        try
        {
            Thread.sleep(100L);
        }
        catch (InterruptedException interruptedexception)
        {
            Log.e("YJVOICE:Monitor:", interruptedexception.toString());
        }
        if (++i >= 100)
        {
            Log.e("YJVOICE:Monitor:", "stoped in Monitor:");
            stop();
            mListener.stateChanged(-2);
        }
        continue; /* Loop/switch isn't completed */
_L2:
        return;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final void start()
    {
        if (mThread == null)
        {
            mThread = new Thread(this);
            mThread.start();
            return;
        } else
        {
            Log.e("YJVOICE:Monitor:", "cannot call Start during running!");
            return;
        }
    }

    public final void stop()
    {
        mThread = null;
    }
}
