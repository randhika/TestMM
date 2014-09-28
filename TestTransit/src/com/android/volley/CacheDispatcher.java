// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

// Referenced classes of package com.android.volley:
//            VolleyLog, Cache, Request, NetworkResponse, 
//            ResponseDelivery, Response

public class CacheDispatcher extends Thread
{

    private static final boolean DEBUG;
    private final Cache mCache;
    private final BlockingQueue mCacheQueue;
    private final ResponseDelivery mDelivery;
    private final BlockingQueue mNetworkQueue;
    private volatile boolean mQuit;

    public CacheDispatcher(BlockingQueue blockingqueue, BlockingQueue blockingqueue1, Cache cache, ResponseDelivery responsedelivery)
    {
        mQuit = false;
        mCacheQueue = blockingqueue;
        mNetworkQueue = blockingqueue1;
        mCache = cache;
        mDelivery = responsedelivery;
    }

    public void quit()
    {
        mQuit = true;
        interrupt();
    }

    public void run()
    {
        if (DEBUG)
        {
            VolleyLog.v("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        mCache.initialize();
_L1:
        final Request request;
label0:
        {
            do
            {
                try
                {
                    do
                    {
                        request = (Request)mCacheQueue.take();
                        request.addMarker("cache-queue-take");
                        if (!request.isCanceled())
                        {
                            break label0;
                        }
                        request.finish("cache-discard-canceled");
                    } while (true);
                }
                catch (InterruptedException interruptedexception) { }
            } while (!mQuit);
            return;
        }
        Cache.Entry entry = mCache.get(request.getCacheKey());
        if (entry != null)
        {
            break MISSING_BLOCK_LABEL_110;
        }
        request.addMarker("cache-miss");
        mNetworkQueue.put(request);
          goto _L1
label1:
        {
            if (!entry.isExpired())
            {
                break label1;
            }
            request.addMarker("cache-hit-expired");
            request.setCacheEntry(entry);
            mNetworkQueue.put(request);
        }
          goto _L1
        Response response;
label2:
        {
            request.addMarker("cache-hit");
            response = request.parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
            request.addMarker("cache-hit-parsed");
            if (entry.refreshNeeded())
            {
                break label2;
            }
            mDelivery.postResponse(request, response);
        }
          goto _L1
        request.addMarker("cache-hit-refresh-needed");
        request.setCacheEntry(entry);
        response.intermediate = true;
        mDelivery.postResponse(request, response, new Runnable() {

            final CacheDispatcher this$0;
            private final Request val$request;

            public void run()
            {
                try
                {
                    mNetworkQueue.put(request);
                    return;
                }
                catch (InterruptedException interruptedexception1)
                {
                    return;
                }
            }

            
            {
                this$0 = CacheDispatcher.this;
                request = request1;
                super();
            }
        });
          goto _L1
    }

    static 
    {
        DEBUG = VolleyLog.DEBUG;
    }

}
