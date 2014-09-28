// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;

// Referenced classes of package jp.co.yahoo.android.common:
//            YLog

public class YHttpUtils
{

    public YHttpUtils()
    {
    }

    public static void closeEntityContent(HttpResponse httpresponse)
    {
        HttpEntity httpentity = httpresponse.getEntity();
        if (httpentity == null)
        {
            return;
        }
        InputStream inputstream = httpentity.getContent();
        if (inputstream != null)
        {
            try
            {
                inputstream.close();
                return;
            }
            catch (IllegalStateException illegalstateexception)
            {
                illegalstateexception.printStackTrace();
                return;
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        }
        return;
    }

    public static void log(int i, String s, HttpResponse httpresponse)
    {
        HttpEntity httpentity;
        YLog.println(i, s, httpresponse.getStatusLine().toString());
        org.apache.http.Header aheader[] = httpresponse.getAllHeaders();
        for (int j = 0; j < aheader.length; j++)
        {
            YLog.println(i, s, aheader[j].toString());
        }

        httpentity = httpresponse.getEntity();
        if (httpentity == null)
        {
            break MISSING_BLOCK_LABEL_72;
        }
        YLog.println(i, s, EntityUtils.toString(httpentity));
        return;
        ParseException parseexception;
        parseexception;
        YLog.e(s, parseexception.getMessage());
        return;
        IOException ioexception;
        ioexception;
        YLog.e(s, ioexception.getMessage());
        return;
    }

    public static void log(int i, String s, HttpRequestBase httprequestbase)
    {
        HttpEntity httpentity;
        YLog.println(i, s, httprequestbase.getRequestLine().toString());
        org.apache.http.Header aheader[] = httprequestbase.getAllHeaders();
        for (int j = 0; j < aheader.length; j++)
        {
            YLog.println(i, s, aheader[j].toString());
        }

        if (!(httprequestbase instanceof HttpEntityEnclosingRequestBase))
        {
            break MISSING_BLOCK_LABEL_76;
        }
        httpentity = ((HttpEntityEnclosingRequestBase)httprequestbase).getEntity();
        if (httpentity == null)
        {
            break MISSING_BLOCK_LABEL_76;
        }
        YLog.println(i, s, EntityUtils.toString(httpentity));
        return;
        ParseException parseexception;
        parseexception;
        YLog.e(s, parseexception.getMessage());
        return;
        IOException ioexception;
        ioexception;
        YLog.e(s, ioexception.getMessage());
        return;
    }

    public static void loge(String s, HttpResponse httpresponse)
    {
        log(6, s, httpresponse);
    }

    public static void loge(String s, HttpRequestBase httprequestbase)
    {
        log(6, s, httprequestbase);
    }
}
