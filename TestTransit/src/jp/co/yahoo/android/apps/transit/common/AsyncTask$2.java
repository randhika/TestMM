// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.os.Process;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            AsyncTask

class rkerRunnable extends rkerRunnable
{

    final AsyncTask this$0;

    public Object call()
        throws Exception
    {
        AsyncTask.access$300(AsyncTask.this).set(true);
        Process.setThreadPriority(10);
        return AsyncTask.access$400(AsyncTask.this, doInBackground(mParams));
    }

    rkerRunnable()
    {
        this$0 = AsyncTask.this;
        super(null);
    }
}
