// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.weather.data;

import java.io.InputStream;
import java.util.concurrent.LinkedBlockingQueue;
import jp.co.yahoo.android.maps.HttpClient;
import jp.co.yahoo.android.maps.viewlayer.ScaleUtils;

// Referenced classes of package jp.co.yahoo.android.maps.weather.data:
//            WeatherRequest

public class WeatherHttpLoader extends Thread
{
    public static interface WeatherHttpLoaderListener
    {

        public abstract boolean endAllWeatherHttpLoader(WeatherHttpLoader weatherhttploader);

        public abstract boolean endWeatherHttpLoader(InputStream inputstream, WeatherRequest weatherrequest);

        public abstract boolean errorWeatherHttpLoader(WeatherHttpLoader weatherhttploader);
    }


    private Object lock;
    private HttpClient mHttpClient;
    private String mMapUrl;
    private boolean mThreadRun;
    private WeatherHttpLoaderListener mWeatherHttpLoaderListener;
    private LinkedBlockingQueue requestQueue;

    public WeatherHttpLoader(WeatherHttpLoaderListener weatherhttploaderlistener)
    {
        mMapUrl = null;
        mHttpClient = null;
        mWeatherHttpLoaderListener = null;
        requestQueue = null;
        mThreadRun = false;
        lock = new Object();
        mWeatherHttpLoaderListener = weatherhttploaderlistener;
        requestQueue = new LinkedBlockingQueue();
    }

    private boolean getHttpTile(WeatherRequest weatherrequest)
    {
        int i;
        String s;
        InputStream inputstream;
        try
        {
            i = ScaleUtils.scidturn(weatherrequest.getTileZ());
        }
        catch (Exception exception)
        {
            return false;
        }
        mMapUrl = "http://weather.map.c.yimg.jp/weather";
        s = (new StringBuilder(String.valueOf(mMapUrl))).append("?x=").append(weatherrequest.getTileX()).append("&y=").append(weatherrequest.getTileY()).append("&z=").append(i).append("&date=").append(weatherrequest.getDate()).toString();
        mHttpClient = new HttpClient();
        inputstream = mHttpClient.httpGet(s);
        if (inputstream == null)
        {
            mHttpClient.clear();
            mHttpClient = null;
            return false;
        }
        if (!createTileImg(inputstream, weatherrequest))
        {
            mHttpClient.clear();
            mHttpClient = null;
            return false;
        }
        try
        {
            inputstream.close();
        }
        catch (Exception exception1)
        {
            exception1.printStackTrace();
        }
        mHttpClient.clear();
        mHttpClient = null;
        return true;
    }

    public void addTileRequest(WeatherRequest weatherrequest)
    {
        if (requestQueue.contains(weatherrequest))
        {
            return;
        }
        try
        {
            requestQueue.put(weatherrequest);
        }
        catch (InterruptedException interruptedexception)
        {
            interruptedexception.printStackTrace();
        }
        if (!mThreadRun)
        {
            mThreadRun = true;
            start();
            return;
        }
        synchronized (lock)
        {
            lock.notify();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean createTileImg(InputStream inputstream, WeatherRequest weatherrequest)
    {
        mWeatherHttpLoaderListener.endWeatherHttpLoader(inputstream, weatherrequest);
        return true;
    }

    public void run()
    {
_L5:
        if (mThreadRun) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        int i = requestQueue.size();
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_34;
        }
        lock.wait();
_L3:
        obj;
        JVM INSTR monitorexit ;
        if (mThreadRun)
        {
            Exception exception;
            WeatherRequest weatherrequest;
            InterruptedException interruptedexception1;
            try
            {
                weatherrequest = (WeatherRequest)requestQueue.take();
            }
            catch (InterruptedException interruptedexception)
            {
                interruptedexception.printStackTrace();
                weatherrequest = null;
            }
            if (!getHttpTile(weatherrequest))
            {
                mWeatherHttpLoaderListener.errorWeatherHttpLoader(this);
            }
            if (requestQueue.size() == 0)
            {
                mWeatherHttpLoaderListener.endAllWeatherHttpLoader(this);
            }
            continue; /* Loop/switch isn't completed */
        }
          goto _L1
        interruptedexception1;
        interruptedexception1.printStackTrace();
          goto _L3
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public void setMapUrl(String s)
    {
        mMapUrl = s;
    }

    public void stopThread()
    {
        mThreadRun = false;
        synchronized (lock)
        {
            lock.notify();
        }
        try
        {
            join();
            return;
        }
        catch (Exception exception1)
        {
            return;
        }
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
