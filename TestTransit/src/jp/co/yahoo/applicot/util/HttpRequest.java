// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import jp.co.yahoo.applicot.Applicot;
import jp.co.yahoo.applicot.ApplicotConfiguration;
import jp.co.yahoo.applicot.log.ApplicotLog;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

// Referenced classes of package jp.co.yahoo.applicot.util:
//            FakeSocketFactory

public final class HttpRequest
{
    private static class SocketTimeOutRetryHandler
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

        private SocketTimeOutRetryHandler(HttpParams httpparams, int i)
        {
            httpParams = httpparams;
            maxNrRetries = i;
        }

    }


    private int connectionTimeOut;
    private Map headers;
    private String login;
    private int maxNrRetries;
    private String password;
    private int socketTimeOut;

    public HttpRequest()
    {
        connectionTimeOut = 3000;
        socketTimeOut = 3000;
        maxNrRetries = 3;
    }

    private UsernamePasswordCredentials getCredentials()
    {
        if (login != null || password != null)
        {
            return new UsernamePasswordCredentials(login, password);
        } else
        {
            return null;
        }
    }

    private HttpClient getHttpClient()
    {
        BasicHttpParams basichttpparams = new BasicHttpParams();
        basichttpparams.setParameter("http.protocol.cookie-policy", "rfc2109");
        HttpConnectionParams.setConnectionTimeout(basichttpparams, connectionTimeOut);
        HttpConnectionParams.setSoTimeout(basichttpparams, socketTimeOut);
        HttpConnectionParams.setSocketBufferSize(basichttpparams, 8192);
        SchemeRegistry schemeregistry = new SchemeRegistry();
        schemeregistry.register(new Scheme("http", new PlainSocketFactory(), 80));
        DefaultHttpClient defaulthttpclient;
        if (Applicot.getConfig().disableSSLCertValidation())
        {
            schemeregistry.register(new Scheme("https", new FakeSocketFactory(), 443));
        } else
        {
            schemeregistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        }
        defaulthttpclient = new DefaultHttpClient(new ThreadSafeClientConnManager(basichttpparams, schemeregistry), basichttpparams);
        defaulthttpclient.setHttpRequestRetryHandler(new SocketTimeOutRetryHandler(basichttpparams, maxNrRetries));
        return defaulthttpclient;
    }

    private HttpEntityEnclosingRequestBase getHttpRequest(URL url, jp.co.yahoo.applicot.sender.HttpSender.Method method, String s, jp.co.yahoo.applicot.sender.HttpSender.Type type)
        throws UnsupportedEncodingException, UnsupportedOperationException
    {
        static class _cls1
        {

            static final int $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Method[];

            static 
            {
                $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Method = new int[jp.co.yahoo.applicot.sender.HttpSender.Method.values().length];
                try
                {
                    $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Method[jp.co.yahoo.applicot.sender.HttpSender.Method.POST.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Method[jp.co.yahoo.applicot.sender.HttpSender.Method.PUT.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.jp.co.yahoo.applicot.sender.HttpSender.Method[method.ordinal()];
        JVM INSTR tableswitch 1 2: default 32
    //                   1 62
    //                   2 198;
           goto _L1 _L2 _L3
_L1:
        throw new UnsupportedOperationException((new StringBuilder()).append("Unknown method: ").append(method.name()).toString());
_L2:
        Object obj = new HttpPost(url.toString());
_L5:
        UsernamePasswordCredentials usernamepasswordcredentials = getCredentials();
        if (usernamepasswordcredentials != null)
        {
            ((HttpEntityEnclosingRequestBase) (obj)).addHeader(BasicScheme.authenticate(usernamepasswordcredentials, "UTF-8", false));
        }
        ((HttpEntityEnclosingRequestBase) (obj)).setHeader("User-Agent", "Android");
        ((HttpEntityEnclosingRequestBase) (obj)).setHeader("Accept", "text/html,application/xml,application/json,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
        ((HttpEntityEnclosingRequestBase) (obj)).setHeader("Content-Type", type.getContentType());
        if (headers != null)
        {
            String s1;
            for (Iterator iterator = headers.keySet().iterator(); iterator.hasNext(); ((HttpEntityEnclosingRequestBase) (obj)).setHeader(s1, (String)headers.get(s1)))
            {
                s1 = (String)iterator.next();
            }

        }
        break; /* Loop/switch isn't completed */
_L3:
        obj = new HttpPut(url.toString());
        if (true) goto _L5; else goto _L4
_L4:
        ((HttpEntityEnclosingRequestBase) (obj)).setEntity(new StringEntity(s, "UTF-8"));
        return ((HttpEntityEnclosingRequestBase) (obj));
    }

    public static String getParamsAsFormString(Map map)
        throws UnsupportedEncodingException
    {
        StringBuilder stringbuilder = new StringBuilder();
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) 
        {
            Object obj = iterator.next();
            if (stringbuilder.length() != 0)
            {
                stringbuilder.append('&');
            }
            Object obj1 = map.get(obj);
            Object obj2;
            if (obj1 == null)
            {
                obj2 = "";
            } else
            {
                obj2 = obj1;
            }
            stringbuilder.append(URLEncoder.encode(obj.toString(), "UTF-8"));
            stringbuilder.append('=');
            stringbuilder.append(URLEncoder.encode(obj2.toString(), "UTF-8"));
        }
        return stringbuilder.toString();
    }

    public void send(URL url, jp.co.yahoo.applicot.sender.HttpSender.Method method, String s, jp.co.yahoo.applicot.sender.HttpSender.Type type)
        throws IOException
    {
        HttpClient httpclient;
        HttpEntityEnclosingRequestBase httpentityenclosingrequestbase;
        HttpResponse httpresponse;
        httpclient = getHttpClient();
        httpentityenclosingrequestbase = getHttpRequest(url, method, s, type);
        Applicot.log.d(Applicot.LOG_TAG, (new StringBuilder()).append("Sending request to ").append(url).toString());
        httpresponse = null;
        httpresponse = httpclient.execute(httpentityenclosingrequestbase, new BasicHttpContext());
        if (httpresponse == null)
        {
            break MISSING_BLOCK_LABEL_208;
        }
        if (httpresponse.getStatusLine() != null)
        {
            String s1 = Integer.toString(httpresponse.getStatusLine().getStatusCode());
            if (!s1.equals("409") && !s1.equals("403") && (s1.startsWith("4") || s1.startsWith("5")))
            {
                throw new IOException((new StringBuilder()).append("Host returned error code ").append(s1).toString());
            }
        }
        break MISSING_BLOCK_LABEL_197;
        Exception exception;
        exception;
        if (httpresponse != null)
        {
            httpresponse.getEntity().consumeContent();
        }
        throw exception;
        EntityUtils.toString(httpresponse.getEntity());
        if (httpresponse != null)
        {
            httpresponse.getEntity().consumeContent();
        }
        return;
    }

    public void setConnectionTimeOut(int i)
    {
        connectionTimeOut = i;
    }

    public void setHeaders(Map map)
    {
        headers = map;
    }

    public void setLogin(String s)
    {
        login = s;
    }

    public void setMaxNrRetries(int i)
    {
        maxNrRetries = i;
    }

    public void setPassword(String s)
    {
        password = s;
    }

    public void setSocketTimeOut(int i)
    {
        socketTimeOut = i;
    }
}
