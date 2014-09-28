// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.os.Handler;

// Referenced classes of package jp.co.yahoo.android.ads:
//            h

static final class c extends Thread
{

    final long a;
    final Handler b;
    final Runnable c;

    public void run()
    {
        if (a == 0L)
        {
            b.post(c);
            return;
        }
        try
        {
            b.postDelayed(c, a);
            return;
        }
        catch (Exception exception)
        {
            h.a(6, "Unhandled exception requesting a fresh ad.", exception);
        }
        return;
    }

    (long l, Handler handler, Runnable runnable)
    {
        a = l;
        b = handler;
        c = runnable;
        super();
    }
}
