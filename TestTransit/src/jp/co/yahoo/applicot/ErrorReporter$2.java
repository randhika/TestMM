// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;

import android.text.format.Time;
import android.util.Log;

// Referenced classes of package jp.co.yahoo.applicot:
//            ErrorReporter, Applicot

class this._cls0 extends Thread
{

    final ErrorReporter this$0;

    public void run()
    {
        Time time = new Time();
        Time time1 = new Time();
        time.setToNow();
        long l = time.toMillis(false);
        long l1 = 0L;
        while (l1 < 3000L) 
        {
            try
            {
                Thread.sleep(3000L);
            }
            catch (InterruptedException interruptedexception)
            {
                Log.d(Applicot.LOG_TAG, "Interrupted while waiting for Toast to end.", interruptedexception);
            }
            time1.setToNow();
            l1 = time1.toMillis(false) - l;
        }
        boolean _tmp = ErrorReporter.access$102(true);
    }

    ()
    {
        this$0 = ErrorReporter.this;
        super();
    }
}
