// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.android.volley:
//            ExecutorDelivery, NetworkDispatcher, Request, VolleyLog, 
//            CacheDispatcher, Cache, ResponseDelivery, Network

public class RequestQueue
{
    public static interface RequestFilter
    {

        public abstract boolean apply(Request request);
    }


    private static final int DEFAULT_NETWORK_THREAD_POOL_SIZE = 4;
    private final Cache mCache;
    private CacheDispatcher mCacheDispatcher;
    private final PriorityBlockingQueue mCacheQueue;
    private final Set mCurrentRequests;
    private final ResponseDelivery mDelivery;
    private NetworkDispatcher mDispatchers[];
    private final Network mNetwork;
    private final PriorityBlockingQueue mNetworkQueue;
    private AtomicInteger mSequenceGenerator;
    private final Map mWaitingRequests;

    public RequestQueue(Cache cache, Network network)
    {
        this(cache, network, 4);
    }

    public RequestQueue(Cache cache, Network network, int i)
    {
        this(cache, network, i, ((ResponseDelivery) (new ExecutorDelivery(new Handler(Looper.getMainLooper())))));
    }

    public RequestQueue(Cache cache, Network network, int i, ResponseDelivery responsedelivery)
    {
        mSequenceGenerator = new AtomicInteger();
        mWaitingRequests = new HashMap();
        mCurrentRequests = new HashSet();
        mCacheQueue = new PriorityBlockingQueue();
        mNetworkQueue = new PriorityBlockingQueue();
        mCache = cache;
        mNetwork = network;
        mDispatchers = new NetworkDispatcher[i];
        mDelivery = responsedelivery;
    }

    public Request add(Request request)
    {
        request.setRequestQueue(this);
        synchronized (mCurrentRequests)
        {
            mCurrentRequests.add(request);
        }
        request.setSequence(getSequenceNumber());
        request.addMarker("add-to-queue");
        if (!request.shouldCache())
        {
            mNetworkQueue.add(request);
            return request;
        }
        break MISSING_BLOCK_LABEL_62;
        exception;
        set;
        JVM INSTR monitorexit ;
        throw exception;
        Map map = mWaitingRequests;
        map;
        JVM INSTR monitorenter ;
        String s;
        Object obj;
        s = request.getCacheKey();
        if (!mWaitingRequests.containsKey(s))
        {
            break MISSING_BLOCK_LABEL_177;
        }
        obj = (Queue)mWaitingRequests.get(s);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_121;
        }
        obj = new LinkedList();
        ((Queue) (obj)).add(request);
        mWaitingRequests.put(s, obj);
        if (VolleyLog.DEBUG)
        {
            VolleyLog.v("Request for cacheKey=%s is in flight, putting on hold.", new Object[] {
                s
            });
        }
_L1:
        map;
        JVM INSTR monitorexit ;
        return request;
        Exception exception1;
        exception1;
        map;
        JVM INSTR monitorexit ;
        throw exception1;
        mWaitingRequests.put(s, null);
        mCacheQueue.add(request);
          goto _L1
    }

    public void cancelAll(RequestFilter requestfilter)
    {
        Set set = mCurrentRequests;
        set;
        JVM INSTR monitorenter ;
        Iterator iterator = mCurrentRequests.iterator();
_L2:
        if (iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_31;
        }
        return;
        Request request = (Request)iterator.next();
        if (requestfilter.apply(request))
        {
            request.cancel();
        }
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        set;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void cancelAll(final Object tag)
    {
        if (tag == null)
        {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        } else
        {
            cancelAll(new RequestFilter() {

                final RequestQueue this$0;
                private final Object val$tag;

                public boolean apply(Request request)
                {
                    return request.getTag() == tag;
                }

            
            {
                this$0 = RequestQueue.this;
                tag = obj;
                super();
            }
            });
            return;
        }
    }

    void finish(Request request)
    {
        synchronized (mCurrentRequests)
        {
            mCurrentRequests.remove(request);
        }
        if (!request.shouldCache()) goto _L2; else goto _L1
_L1:
        Map map = mWaitingRequests;
        map;
        JVM INSTR monitorenter ;
        String s;
        Queue queue;
        s = request.getCacheKey();
        queue = (Queue)mWaitingRequests.remove(s);
        if (queue == null)
        {
            break MISSING_BLOCK_LABEL_112;
        }
        if (VolleyLog.DEBUG)
        {
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(queue.size());
            aobj[1] = s;
            VolleyLog.v("Releasing %d waiting requests for cacheKey=%s.", aobj);
        }
        mCacheQueue.addAll(queue);
        map;
        JVM INSTR monitorexit ;
_L2:
        return;
        exception;
        set;
        JVM INSTR monitorexit ;
        throw exception;
        Exception exception1;
        exception1;
        map;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public Cache getCache()
    {
        return mCache;
    }

    public int getSequenceNumber()
    {
        return mSequenceGenerator.incrementAndGet();
    }

    public void start()
    {
        stop();
        mCacheDispatcher = new CacheDispatcher(mCacheQueue, mNetworkQueue, mCache, mDelivery);
        mCacheDispatcher.start();
        int i = 0;
        do
        {
            if (i >= mDispatchers.length)
            {
                return;
            }
            NetworkDispatcher networkdispatcher = new NetworkDispatcher(mNetworkQueue, mNetwork, mCache, mDelivery);
            mDispatchers[i] = networkdispatcher;
            networkdispatcher.start();
            i++;
        } while (true);
    }

    public void stop()
    {
        if (mCacheDispatcher != null)
        {
            mCacheDispatcher.quit();
        }
        int i = 0;
        do
        {
            if (i >= mDispatchers.length)
            {
                return;
            }
            if (mDispatchers[i] != null)
            {
                mDispatchers[i].quit();
            }
            i++;
        } while (true);
    }
}
