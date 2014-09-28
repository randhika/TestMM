// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yolp.common;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class HttpClient
{

    private DefaultHttpClient client;

    public HttpClient()
    {
        client = null;
    }

    public int close()
    {
        if (client == null)
        {
            return 0;
        }
        try
        {
            client = null;
        }
        catch (Exception exception)
        {
            return 1;
        }
        return 0;
    }

    public InputStream doGet(String s, String s1, String s2)
    {
        HttpGet httpget = new HttpGet(s);
        InputStream inputstream;
        try
        {
            BasicHttpParams basichttpparams = new BasicHttpParams();
            basichttpparams.setIntParameter("http.connection.timeout", 30000);
            basichttpparams.setIntParameter("http.socket.timeout", 30000);
            DefaultHttpClient defaulthttpclient = new DefaultHttpClient(basichttpparams);
            httpget.setHeader(s1, s2);
            inputstream = defaulthttpclient.execute(httpget).getEntity().getContent();
        }
        catch (IOException ioexception)
        {
            httpget.abort();
            return null;
        }
        return inputstream;
    }

    public String doGetString(String s)
    {
        String s1;
        try
        {
            HttpGet httpget = new HttpGet(s);
            BasicHttpParams basichttpparams = new BasicHttpParams();
            basichttpparams.setIntParameter("http.connection.timeout", 30000);
            basichttpparams.setIntParameter("http.socket.timeout", 30000);
            client = new DefaultHttpClient(basichttpparams);
            httpget.setHeader("Connection", "Keep-Alive");
            HttpResponse httpresponse = client.execute(httpget);
            if (httpresponse.getStatusLine().getStatusCode() != 200)
            {
                throw new Exception("");
            }
            s1 = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
        }
        catch (Exception exception)
        {
            return null;
        }
        return s1;
    }

    public String doGetString(String s, String s1)
    {
        String s2;
        try
        {
            HttpGet httpget = new HttpGet(s);
            BasicHttpParams basichttpparams = new BasicHttpParams();
            basichttpparams.setIntParameter("http.connection.timeout", 30000);
            basichttpparams.setIntParameter("http.socket.timeout", 30000);
            client = new DefaultHttpClient(basichttpparams);
            httpget.setHeader("Cookie", s1);
            HttpResponse httpresponse = client.execute(httpget);
            if (httpresponse.getStatusLine().getStatusCode() != 200)
            {
                throw new Exception("");
            }
            s2 = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
        }
        catch (Exception exception)
        {
            return null;
        }
        return s2;
    }

    public String doPostString(String s, String s1)
    {
        String s2;
        try
        {
            HttpPost httppost = new HttpPost(s);
            BasicHttpParams basichttpparams = new BasicHttpParams();
            basichttpparams.setIntParameter("http.connection.timeout", 30000);
            basichttpparams.setIntParameter("http.socket.timeout", 30000);
            client = new DefaultHttpClient(basichttpparams);
            StringEntity stringentity = new StringEntity(s1);
            stringentity.setChunked(false);
            stringentity.setContentType("application/x-www-form-urlencoded");
            httppost.setEntity(stringentity);
            HttpResponse httpresponse = client.execute(httppost);
            if (httpresponse.getStatusLine().getStatusCode() != 200)
            {
                throw new Exception("");
            }
            s2 = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
        }
        catch (Exception exception)
        {
            return null;
        }
        return s2;
    }

    public String doPostString(String s, String s1, String s2)
    {
        String s3;
        try
        {
            HttpPost httppost = new HttpPost(s);
            BasicHttpParams basichttpparams = new BasicHttpParams();
            basichttpparams.setIntParameter("http.connection.timeout", 30000);
            basichttpparams.setIntParameter("http.socket.timeout", 30000);
            client = new DefaultHttpClient(basichttpparams);
            StringEntity stringentity = new StringEntity(s1);
            stringentity.setChunked(false);
            stringentity.setContentType("application/x-www-form-urlencoded");
            httppost.setHeader("Connection", "Keep-Alive");
            httppost.setHeader("Cookie", s2);
            httppost.setEntity(stringentity);
            HttpResponse httpresponse = client.execute(httppost);
            if (httpresponse.getStatusLine().getStatusCode() != 200)
            {
                throw new Exception("");
            }
            s3 = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
        }
        catch (Exception exception)
        {
            return null;
        }
        return s3;
    }

    public String https(String s)
    {
        (new SchemeRegistry()).register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        return "";
    }

    public InputStream loadImage(String s)
    {
        HttpGet httpget = new HttpGet(s);
        InputStream inputstream;
        try
        {
            BasicHttpParams basichttpparams = new BasicHttpParams();
            basichttpparams.setIntParameter("http.connection.timeout", 30000);
            basichttpparams.setIntParameter("http.socket.timeout", 30000);
            inputstream = (new DefaultHttpClient(basichttpparams)).execute(httpget).getEntity().getContent();
        }
        catch (IOException ioexception)
        {
            httpget.abort();
            return null;
        }
        return inputstream;
    }
}
