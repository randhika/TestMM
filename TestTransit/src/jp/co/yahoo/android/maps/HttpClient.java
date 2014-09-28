// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class HttpClient
{

    public static final int GET = 2;
    public static final int POST = 1;
    private static final HttpParams httprun_params;
    private DefaultHttpClient m_client;
    private HttpURLConnection m_hc;

    public HttpClient()
    {
        m_client = null;
        m_hc = null;
    }

    public static InputStream httprun(String s)
    {
        HttpGet httpget = new HttpGet(s);
        InputStream inputstream;
        try
        {
            inputstream = (new DefaultHttpClient(httprun_params)).execute(httpget).getEntity().getContent();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            return null;
        }
        return inputstream;
    }

    public void clear()
    {
        if (m_hc != null)
        {
            m_hc.disconnect();
            m_hc = null;
        }
        if (m_client != null)
        {
            m_client.clearRequestInterceptors();
            m_client.clearResponseInterceptors();
        }
    }

    public InputStream httpGet(String s)
    {
        InputStream inputstream;
        try
        {
            m_hc = (HttpURLConnection)(new URL(s)).openConnection();
            m_hc.setRequestMethod("GET");
            inputstream = m_hc.getInputStream();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        return inputstream;
    }

    static 
    {
        httprun_params = new BasicHttpParams();
        httprun_params.setIntParameter("http.connection.timeout", 10000);
        httprun_params.setIntParameter("http.socket.timeout", 5000);
    }
}
