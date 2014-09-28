// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.net.TrafficStats;
import android.os.Process;
import java.util.concurrent.BlockingQueue;

// Referenced classes of package com.android.volley:
//            Request, ResponseDelivery, VolleyError, Network, 
//            NetworkResponse, VolleyLog, Response, Cache

public class NetworkDispatcher extends Thread
{

    private final Cache mCache;
    private final ResponseDelivery mDelivery;
    private final Network mNetwork;
    private final BlockingQueue mQueue;
    private volatile boolean mQuit;

    public NetworkDispatcher(BlockingQueue blockingqueue, Network network, Cache cache, ResponseDelivery responsedelivery)
    {
        mQuit = false;
        mQueue = blockingqueue;
        mNetwork = network;
        mCache = cache;
        mDelivery = responsedelivery;
    }

    private void parseAndDeliverNetworkError(Request request, VolleyError volleyerror)
    {
        VolleyError volleyerror1 = request.parseNetworkError(volleyerror);
        mDelivery.postError(request, volleyerror1);
    }

    public void quit()
    {
        mQuit = true;
        interrupt();
    }

    public void run()
    {
        Process.setThreadPriority(10);
_L2:
        Request request;
        VolleyError volleyerror;
        try
        {
            request = (Request)mQueue.take();
        }
        catch (InterruptedException interruptedexception)
        {
            if (mQuit)
            {
                return;
            }
            continue; /* Loop/switch isn't completed */
        }
        request.addMarker("network-queue-take");
        if (request.isCanceled())
        {
            request.finish("network-discard-cancelled");
            continue; /* Loop/switch isn't completed */
        }
        if (android.os.Build.VERSION.SDK_INT >= 14)
        {
            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
        }
        NetworkResponse networkresponse = mNetwork.performRequest(request);
        request.addMarker("network-http-complete");
        Exception exception;
        Object aobj[];
        if (networkresponse.notModified && request.hasHadResponseDelivered())
        {
            request.finish("not-modified");
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            Response response = request.parseNetworkResponse(networkresponse);
            request.addMarker("network-parse-complete");
            if (request.shouldCache() && response.cacheEntry != null)
            {
                mCache.put(request.getCacheKey(), response.cacheEntry);
                request.addMarker("network-cache-written");
            }
            request.markDelivered();
            mDelivery.postResponse(request, response);
        }
        // Misplaced declaration of an exception variable
        catch (VolleyError volleyerror)
        {
            parseAndDeliverNetworkError(request, volleyerror);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            aobj = new Object[1];
            aobj[0] = exception.toString();
            VolleyLog.e(exception, "Unhandled exception %s", aobj);
            mDelivery.postError(request, new VolleyError(exception));
        }
        if (true) goto _L2; else goto _L1
_L1:
    }
}
