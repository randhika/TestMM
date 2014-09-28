// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            AsyncTask

private static class <init>
    implements Executor
{

    Runnable mActive;
    final ArrayDeque mTasks;

    public void execute(final Runnable r)
    {
        this;
        JVM INSTR monitorenter ;
        mTasks.offer(new Runnable() {

            final AsyncTask.SerialExecutor this$0;
            final Runnable val$r;

            public void run()
            {
                r.run();
                scheduleNext();
                return;
                Exception exception1;
                exception1;
                scheduleNext();
                throw exception1;
            }

            
            {
                this$0 = AsyncTask.SerialExecutor.this;
                r = runnable;
                super();
            }
        });
        if (mActive == null)
        {
            scheduleNext();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void scheduleNext()
    {
        this;
        JVM INSTR monitorenter ;
        Runnable runnable;
        runnable = (Runnable)mTasks.poll();
        mActive = runnable;
        if (runnable == null)
        {
            break MISSING_BLOCK_LABEL_34;
        }
        AsyncTask.THREAD_POOL_EXECUTOR.execute(mActive);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private _cls1.val.r()
    {
        mTasks = new ArrayDeque();
    }

    mTasks(mTasks mtasks)
    {
        this();
    }
}
