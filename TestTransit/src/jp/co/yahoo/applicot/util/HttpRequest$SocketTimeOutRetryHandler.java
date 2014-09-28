// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.util;

import java.io.IOException;
import java.net.SocketTimeoutException;
import jp.co.yahoo.applicot.Applicot;
import jp.co.yahoo.applicot.log.ApplicotLog;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

// Referenced classes of package jp.co.yahoo.applicot.util:
//            HttpRequest

private static class <init>
    implements HttpRequestRetryHandler
{

    private final HttpParams httpParams;
    private final int maxNrRetries;

    public boolean retryRequest(IOException ioexception, int i, HttpContext httpcontext)
    {
        if (ioexception instanceof SocketTimeoutException)
        {
            if (i <= maxNrRetries)
            {
                if (httpParams != null)
                {
                    int j = 2 * HttpConnectionParams.getSoTimeout(httpParams);
                    HttpConnectionParams.setSoTimeout(httpParams, j);
                    Applicot.log.d(Applicot.LOG_TAG, (new StringBuilder()).append("SocketTimeOut - increasing time out to ").append(j).append(" millis and trying again").toString());
                } else
                {
                    Applicot.log.d(Applicot.LOG_TAG, "SocketTimeOut - no HttpParams, cannot increase time out. Trying again with current settings");
                }
                return true;
            }
            Applicot.log.d(Applicot.LOG_TAG, (new StringBuilder()).append("SocketTimeOut but exceeded max number of retries : ").append(maxNrRetries).toString());
        }
        return false;
    }

    private (HttpParams httpparams, int i)
    {
        httpParams = httpparams;
        maxNrRetries = i;
    }

    maxNrRetries(HttpParams httpparams, int i, maxNrRetries maxnrretries)
    {
        this(httpparams, i);
    }
}
