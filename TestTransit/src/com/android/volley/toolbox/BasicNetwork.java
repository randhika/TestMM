// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.AuthFailureError;
import com.android.volley.Network;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.cookie.DateUtils;

// Referenced classes of package com.android.volley.toolbox:
//            ByteArrayPool, PoolingByteArrayOutputStream, HttpStack

public class BasicNetwork
    implements Network
{

    protected static final boolean DEBUG;
    private static int DEFAULT_POOL_SIZE = 4096;
    private static int SLOW_REQUEST_THRESHOLD_MS = 3000;
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;

    public BasicNetwork(HttpStack httpstack)
    {
        this(httpstack, new ByteArrayPool(DEFAULT_POOL_SIZE));
    }

    public BasicNetwork(HttpStack httpstack, ByteArrayPool bytearraypool)
    {
        mHttpStack = httpstack;
        mPool = bytearraypool;
    }

    private void addCacheHeaders(Map map, com.android.volley.Cache.Entry entry)
    {
        if (entry != null)
        {
            if (entry.etag != null)
            {
                map.put("If-None-Match", entry.etag);
            }
            if (entry.serverDate > 0L)
            {
                map.put("If-Modified-Since", DateUtils.formatDate(new Date(entry.serverDate)));
                return;
            }
        }
    }

    private static void attemptRetryOnException(String s, Request request, VolleyError volleyerror)
        throws VolleyError
    {
        RetryPolicy retrypolicy = request.getRetryPolicy();
        int i = request.getTimeoutMs();
        Object aobj1[];
        try
        {
            retrypolicy.retry(volleyerror);
        }
        catch (VolleyError volleyerror1)
        {
            Object aobj[] = new Object[2];
            aobj[0] = s;
            aobj[1] = Integer.valueOf(i);
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", aobj));
            throw volleyerror1;
        }
        aobj1 = new Object[2];
        aobj1[0] = s;
        aobj1[1] = Integer.valueOf(i);
        request.addMarker(String.format("%s-retry [timeout=%s]", aobj1));
    }

    private static Map convertHeaders(Header aheader[])
    {
        HashMap hashmap = new HashMap();
        int i = 0;
        do
        {
            if (i >= aheader.length)
            {
                return hashmap;
            }
            hashmap.put(aheader[i].getName(), aheader[i].getValue());
            i++;
        } while (true);
    }

    private byte[] entityToBytes(HttpEntity httpentity)
        throws IOException, ServerError
    {
        PoolingByteArrayOutputStream poolingbytearrayoutputstream;
        byte abyte0[];
        poolingbytearrayoutputstream = new PoolingByteArrayOutputStream(mPool, (int)httpentity.getContentLength());
        abyte0 = (byte[])null;
        InputStream inputstream = httpentity.getContent();
        if (inputstream != null)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        throw new ServerError();
        Exception exception;
        exception;
        int i;
        byte abyte1[];
        IOException ioexception1;
        try
        {
            httpentity.consumeContent();
        }
        catch (IOException ioexception)
        {
            VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
        }
        mPool.returnBuf(abyte0);
        poolingbytearrayoutputstream.close();
        throw exception;
        abyte0 = mPool.getBuf(1024);
_L1:
        i = inputstream.read(abyte0);
        if (i != -1)
        {
            break MISSING_BLOCK_LABEL_120;
        }
        abyte1 = poolingbytearrayoutputstream.toByteArray();
        try
        {
            httpentity.consumeContent();
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception1)
        {
            VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
        }
        mPool.returnBuf(abyte0);
        poolingbytearrayoutputstream.close();
        return abyte1;
        poolingbytearrayoutputstream.write(abyte0, 0, i);
          goto _L1
    }

    private void logSlowRequests(long l, Request request, byte abyte0[], StatusLine statusline)
    {
        if (DEBUG || l > (long)SLOW_REQUEST_THRESHOLD_MS)
        {
            Object aobj[] = new Object[5];
            aobj[0] = request;
            aobj[1] = Long.valueOf(l);
            Object obj;
            if (abyte0 != null)
            {
                obj = Integer.valueOf(abyte0.length);
            } else
            {
                obj = "null";
            }
            aobj[2] = obj;
            aobj[3] = Integer.valueOf(statusline.getStatusCode());
            aobj[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", aobj);
        }
    }

    protected void logError(String s, String s1, long l)
    {
        long l1 = SystemClock.elapsedRealtime();
        Object aobj[] = new Object[3];
        aobj[0] = s;
        aobj[1] = Long.valueOf(l1 - l);
        aobj[2] = s1;
        VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", aobj);
    }

    public NetworkResponse performRequest(Request request)
        throws VolleyError
    {
        long l = SystemClock.elapsedRealtime();
_L3:
        HttpResponse httpresponse;
        byte abyte0[];
        Object obj;
        httpresponse = null;
        abyte0 = (byte[])null;
        obj = new HashMap();
        StatusLine statusline;
        int j;
        HashMap hashmap = new HashMap();
        addCacheHeaders(hashmap, request.getCacheEntry());
        httpresponse = mHttpStack.performRequest(request, hashmap);
        statusline = httpresponse.getStatusLine();
        j = statusline.getStatusCode();
        obj = convertHeaders(httpresponse.getAllHeaders());
        if (j != 304)
        {
            break MISSING_BLOCK_LABEL_114;
        }
        return new NetworkResponse(304, request.getCacheEntry().data, ((Map) (obj)), true);
        if (httpresponse.getEntity() == null)
        {
            break MISSING_BLOCK_LABEL_194;
        }
        abyte0 = entityToBytes(httpresponse.getEntity());
_L1:
        logSlowRequests(SystemClock.elapsedRealtime() - l, request, abyte0, statusline);
        if (j < 200 || j > 299)
        {
            NetworkResponse networkresponse1;
            try
            {
                throw new IOException();
            }
            catch (SocketTimeoutException sockettimeoutexception)
            {
                attemptRetryOnException("socket", request, new TimeoutError());
            }
            catch (ConnectTimeoutException connecttimeoutexception)
            {
                attemptRetryOnException("connection", request, new TimeoutError());
            }
            catch (MalformedURLException malformedurlexception)
            {
                throw new RuntimeException((new StringBuilder("Bad URL ")).append(request.getUrl()).toString(), malformedurlexception);
            }
            catch (IOException ioexception)
            {
                if (httpresponse != null)
                {
                    int i = httpresponse.getStatusLine().getStatusCode();
                    Object aobj[] = new Object[2];
                    aobj[0] = Integer.valueOf(i);
                    aobj[1] = request.getUrl();
                    VolleyLog.e("Unexpected response code %d for %s", aobj);
                    if (abyte0 != null)
                    {
                        NetworkResponse networkresponse = new NetworkResponse(i, abyte0, ((Map) (obj)), false);
                        if (i == 401 || i == 403)
                        {
                            attemptRetryOnException("auth", request, new AuthFailureError(networkresponse));
                        } else
                        {
                            throw new ServerError(networkresponse);
                        }
                    } else
                    {
                        throw new NetworkError(null);
                    }
                } else
                {
                    throw new NoConnectionError(ioexception);
                }
            }
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_202;
        abyte0 = new byte[0];
          goto _L1
        networkresponse1 = new NetworkResponse(j, abyte0, ((Map) (obj)), false);
        return networkresponse1;
        if (true) goto _L3; else goto _L2
_L2:
    }

    static 
    {
        DEBUG = VolleyLog.DEBUG;
    }
}
