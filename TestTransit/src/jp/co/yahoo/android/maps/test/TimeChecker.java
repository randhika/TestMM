// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.test;


public class TimeChecker extends Thread
{

    private static TimeChecker _instance = null;
    private int count;
    private int log_interval;
    private long max_time;
    private long min_time;
    private long start_time;
    private long total_time;

    private TimeChecker()
    {
        super("TimeChecker");
        log_interval = 5000;
        start_time = 0L;
        total_time = 0L;
        max_time = 0L;
        min_time = 0L;
        count = 0;
    }

    public static TimeChecker getInstance()
    {
        jp/co/yahoo/android/maps/test/TimeChecker;
        JVM INSTR monitorenter ;
        TimeChecker timechecker;
        if (_instance == null)
        {
            _instance = new TimeChecker();
            _instance.start();
        }
        timechecker = _instance;
        jp/co/yahoo/android/maps/test/TimeChecker;
        JVM INSTR monitorexit ;
        return timechecker;
        Exception exception;
        exception;
        throw exception;
    }

    public void endPoint()
    {
        if (start_time != 0L)
        {
            long l = System.currentTimeMillis() - start_time;
            if (max_time < l)
            {
                max_time = l;
            }
            if (min_time > l)
            {
                min_time = l;
            }
            total_time = l + total_time;
            count = 1 + count;
        }
    }

    public void run()
    {
        do
        {
            do
            {
                try
                {
                    Thread.sleep(log_interval);
                }
                catch (InterruptedException interruptedexception)
                {
                    interruptedexception.printStackTrace();
                }
            } while (count == 0);
            long _tmp = total_time / (long)count;
            count = 0;
            total_time = 0L;
            max_time = 0L;
            min_time = 0x7fffffffffffffffL;
        } while (true);
    }

    public void setLogInterval(int i)
    {
        log_interval = i;
    }

    public void startPoint()
    {
        start_time = System.currentTimeMillis();
    }

}
