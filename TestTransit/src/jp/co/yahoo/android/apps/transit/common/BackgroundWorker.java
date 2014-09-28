// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class BackgroundWorker
{
    public static interface WorkerListener
    {

        public abstract boolean doInBackground();

        public abstract void onPostExecute();
    }


    private static final Handler sHandler;
    private static final HandlerThread sThread;
    private static final Handler sUIHandler = new Handler(Looper.getMainLooper());

    public BackgroundWorker()
    {
    }

    public static void post(Runnable runnable)
    {
        sUIHandler.post(runnable);
    }

    public static void post(WorkerListener workerlistener)
    {
        sHandler.post(new Runnable(workerlistener) {

            final WorkerListener val$listener;

            public void run()
            {
                if (listener.doInBackground())
                {
                    BackgroundWorker.sUIHandler.post(new Runnable() {

                        final _cls1 this$0;

                        public void run()
                        {
                            listener.onPostExecute();
                        }

            
            {
                this$0 = _cls1.this;
                super();
            }
                    });
                }
            }

            
            {
                listener = workerlistener;
                super();
            }
        });
    }

    public static void postDelayed(Runnable runnable, long l)
    {
        sUIHandler.postDelayed(runnable, l);
    }

    public static void removeCallbacks(Runnable runnable)
    {
        sUIHandler.removeCallbacks(runnable);
    }

    static 
    {
        sThread = new HandlerThread("BackgroundWorkerThread");
        sThread.start();
        sHandler = new Handler(sThread.getLooper());
    }

}
