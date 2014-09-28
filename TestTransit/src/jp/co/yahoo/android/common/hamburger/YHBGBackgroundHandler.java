// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class YHBGBackgroundHandler
{

    static ExecutorService mThreadPool = Executors.newSingleThreadExecutor();
    static HandlerThread sLooperThread;

    private YHBGBackgroundHandler()
    {
    }

    public static void execute(Runnable runnable)
    {
        mThreadPool.execute(runnable);
    }

    public static void execute(Runnable runnable, long l)
    {
        (new Handler(getLooper())).postDelayed(runnable, l);
    }

    public static Looper getLooper()
    {
        return sLooperThread.getLooper();
    }

    static 
    {
        sLooperThread = new HandlerThread("BackgroundHandler", 1);
        sLooperThread.start();
    }
}
