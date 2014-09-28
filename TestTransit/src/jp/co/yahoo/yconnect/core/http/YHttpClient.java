// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.http;

import java.io.IOException;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.Set;
import javax.net.ssl.SSLException;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

// Referenced classes of package jp.co.yahoo.yconnect.core.http:
//            HttpHeaders, CustomAbstractVerifier, CustomSSLSocketFactory, CustomYHttpDelete, 
//            HttpParameters

public class YHttpClient
{

    private static final String TAG = jp/co/yahoo/yconnect/core/http/YHttpClient.getSimpleName();
    private static boolean checkSSL = true;
    private HttpClient httpClient;
    private String responseBody;
    private int responseCode;
    private HttpHeaders responseHeaders;
    private String responseMessage;

    public YHttpClient()
    {
        httpClient = null;
        responseCode = 0;
        responseMessage = "";
        responseBody = "";
        responseHeaders = new HttpHeaders();
    }

    private static void checkSSLCertification(SchemeRegistry schemeregistry)
    {
        SSLSocketFactory sslsocketfactory = (SSLSocketFactory)schemeregistry.getScheme("https").getSocketFactory();
        if (!(sslsocketfactory.getHostnameVerifier() instanceof CustomAbstractVerifier))
        {
            YConnectLogger.info(TAG, "This httpClient is different from CustomAbstractVerifier.");
            sslsocketfactory.setHostnameVerifier(new CustomAbstractVerifier());
        }
    }

    public static void disableSSLCheck()
    {
        checkSSL = false;
    }

    public static void enableSSLCheck()
    {
        checkSSL = true;
    }

    private static void ignoreSSLCertification(HttpClient httpclient)
    {
        try
        {
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(null, null);
            CustomSSLSocketFactory customsslsocketfactory = new CustomSSLSocketFactory(keystore);
            customsslsocketfactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            Scheme scheme = new Scheme("https", customsslsocketfactory, 443);
            httpclient.getConnectionManager().getSchemeRegistry().register(scheme);
            return;
        }
        catch (Exception exception)
        {
            YConnectLogger.error(TAG, exception.getMessage());
            exception.printStackTrace();
            return;
        }
    }

    public String getResponseBody()
    {
        return responseBody;
    }

    public HttpHeaders getResponseHeaders()
    {
        return responseHeaders;
    }

    public int getStatusCode()
    {
        return responseCode;
    }

    public String getStatusMessage()
    {
        return responseMessage;
    }

    public void requestDelete(String s, String s1, HttpHeaders httpheaders, String s2, String s3)
    {
        CustomYHttpDelete customyhttpdelete;
        YConnectLogger.debug(TAG, (new StringBuilder()).append("URL: ").append(s).toString());
        customyhttpdelete = new CustomYHttpDelete(s);
        if (httpheaders != null)
        {
            String s4;
            String s5;
            for (Iterator iterator = httpheaders.keySet().iterator(); iterator.hasNext(); YConnectLogger.debug(TAG, (new StringBuilder()).append(s4).append(": ").append(s5).toString()))
            {
                s4 = (String)iterator.next();
                s5 = (String)httpheaders.get(s4);
                customyhttpdelete.setHeader(s4, s5);
            }

        }
        SchemeRegistry schemeregistry;
        StringEntity stringentity = new StringEntity(s1, s3);
        stringentity.setChunked(false);
        stringentity.setContentType(s2);
        customyhttpdelete.setEntity(stringentity);
        schemeregistry = new SchemeRegistry();
        schemeregistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        schemeregistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setSoTimeout(basichttpparams, 30000);
        HttpConnectionParams.setConnectionTimeout(basichttpparams, 30000);
        httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basichttpparams, schemeregistry), basichttpparams);
        if (!s.startsWith("https")) goto _L2; else goto _L1
_L1:
        if (checkSSL) goto _L4; else goto _L3
_L3:
        YConnectLogger.debug(TAG, "HTTPS ignore SSL Certification");
        ignoreSSLCertification(httpClient);
_L2:
        HttpResponse httpresponse;
        Header aheader[];
        int i;
        httpresponse = httpClient.execute(customyhttpdelete);
        responseCode = httpresponse.getStatusLine().getStatusCode();
        responseMessage = httpresponse.getStatusLine().getReasonPhrase();
        YConnectLogger.debug(TAG, (new StringBuilder()).append("responseCode: ").append(responseCode).toString());
        YConnectLogger.debug(TAG, (new StringBuilder()).append("responseMessage: ").append(responseMessage).toString());
        aheader = httpresponse.getAllHeaders();
        i = aheader.length;
        int j = 0;
_L6:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        Header header = aheader[j];
        responseHeaders.put(header.getName(), header.getValue());
        j++;
        if (true) goto _L6; else goto _L5
_L4:
        YConnectLogger.debug(TAG, "checkSSLCertification");
        checkSSLCertification(schemeregistry);
          goto _L2
        SSLException sslexception;
        sslexception;
        YConnectLogger.error(TAG, sslexception.getMessage());
        sslexception.printStackTrace();
        httpClient.getConnectionManager().shutdown();
        return;
_L5:
        YConnectLogger.debug(TAG, (new StringBuilder()).append("responseHeaders: ").append(responseHeaders).toString());
        responseBody = EntityUtils.toString(httpresponse.getEntity());
        YConnectLogger.debug(TAG, (new StringBuilder()).append("responseBody: ").append(responseBody).toString());
        if (responseCode >= 400)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("Request URL: ").append(s).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Request Headers: ").append(httpheaders.toHeaderString()).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Request Query: ").append(s1).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Response Code: ").append(responseCode).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Response Message: ").append(responseMessage).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Response Headers: ").append(responseHeaders).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Response Body: ").append(responseBody).toString());
        }
        httpClient.getConnectionManager().shutdown();
        return;
        IOException ioexception;
        ioexception;
        YConnectLogger.error(TAG, ioexception.getMessage());
        ioexception.printStackTrace();
        httpClient.getConnectionManager().shutdown();
        return;
        Exception exception;
        exception;
        httpClient.getConnectionManager().shutdown();
        throw exception;
    }

    public void requestDelete(String s, HttpParameters httpparameters, HttpHeaders httpheaders)
    {
        String s1 = null;
        if (httpparameters != null)
        {
            s1 = httpparameters.toQueryString();
            s = (new StringBuilder()).append(s).append("?").append(s1).toString();
        }
        requestDelete(s, s1, httpheaders, "application/x-www-form-urlencoded", "ISO-8859-1");
    }

    public void requestGet(String s, HttpParameters httpparameters, HttpHeaders httpheaders)
    {
        HttpGet httpget;
        if (httpparameters != null)
        {
            String s3 = httpparameters.toQueryString();
            s = (new StringBuilder()).append(s).append("?").append(s3).toString();
        }
        YConnectLogger.debug(TAG, (new StringBuilder()).append("URL: ").append(s).toString());
        httpget = new HttpGet(s);
        if (httpheaders != null)
        {
            String s1;
            String s2;
            for (Iterator iterator = httpheaders.keySet().iterator(); iterator.hasNext(); YConnectLogger.debug(TAG, (new StringBuilder()).append(s1).append(": ").append(s2).toString()))
            {
                s1 = (String)iterator.next();
                s2 = (String)httpheaders.get(s1);
                httpget.setHeader(s1, s2);
            }

        }
        SchemeRegistry schemeregistry;
        schemeregistry = new SchemeRegistry();
        schemeregistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        schemeregistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setSoTimeout(basichttpparams, 30000);
        HttpConnectionParams.setConnectionTimeout(basichttpparams, 30000);
        httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basichttpparams, schemeregistry), basichttpparams);
        if (!s.startsWith("https")) goto _L2; else goto _L1
_L1:
        if (checkSSL) goto _L4; else goto _L3
_L3:
        YConnectLogger.debug(TAG, "HTTPS ignore SSL Certification");
        ignoreSSLCertification(httpClient);
_L2:
        HttpResponse httpresponse;
        Header aheader[];
        int i;
        httpresponse = httpClient.execute(httpget);
        responseCode = httpresponse.getStatusLine().getStatusCode();
        responseMessage = httpresponse.getStatusLine().getReasonPhrase();
        YConnectLogger.debug(TAG, (new StringBuilder()).append("responseCode: ").append(responseCode).toString());
        YConnectLogger.debug(TAG, (new StringBuilder()).append("responseMessage: ").append(responseMessage).toString());
        aheader = httpresponse.getAllHeaders();
        i = aheader.length;
        int j = 0;
_L6:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        Header header = aheader[j];
        responseHeaders.put(header.getName(), header.getValue());
        j++;
        if (true) goto _L6; else goto _L5
_L4:
        YConnectLogger.debug(TAG, "checkSSLCertification");
        checkSSLCertification(schemeregistry);
          goto _L2
        SSLException sslexception;
        sslexception;
        YConnectLogger.error(TAG, sslexception.getMessage());
        sslexception.printStackTrace();
        httpClient.getConnectionManager().shutdown();
        return;
_L5:
        YConnectLogger.debug(TAG, (new StringBuilder()).append("responseHeaders: ").append(responseHeaders).toString());
        responseBody = EntityUtils.toString(httpresponse.getEntity());
        YConnectLogger.debug(TAG, (new StringBuilder()).append("responseBody: ").append(responseBody).toString());
        if (responseCode >= 400)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("Request URL: ").append(s).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Request Headers: ").append(httpheaders.toHeaderString()).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Request Query: ").append(httpparameters.toQueryString()).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Response Code: ").append(responseCode).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Response Message: ").append(responseMessage).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Response Headers: ").append(responseHeaders).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Response Body: ").append(responseBody).toString());
        }
        httpClient.getConnectionManager().shutdown();
        return;
        IOException ioexception;
        ioexception;
        YConnectLogger.error(TAG, ioexception.getMessage());
        ioexception.printStackTrace();
        httpClient.getConnectionManager().shutdown();
        return;
        Exception exception;
        exception;
        httpClient.getConnectionManager().shutdown();
        throw exception;
    }

    public void requestPost(String s, String s1, HttpHeaders httpheaders, String s2, String s3)
    {
        HttpPost httppost;
        YConnectLogger.debug(TAG, (new StringBuilder()).append("URL: ").append(s).toString());
        httppost = new HttpPost(s);
        if (httpheaders != null)
        {
            String s4;
            String s5;
            for (Iterator iterator = httpheaders.keySet().iterator(); iterator.hasNext(); YConnectLogger.debug(TAG, (new StringBuilder()).append(s4).append(": ").append(s5).toString()))
            {
                s4 = (String)iterator.next();
                s5 = (String)httpheaders.get(s4);
                httppost.setHeader(s4, s5);
            }

        }
        SchemeRegistry schemeregistry;
        StringEntity stringentity = new StringEntity(s1, s3);
        stringentity.setChunked(false);
        stringentity.setContentType(s2);
        httppost.setEntity(stringentity);
        schemeregistry = new SchemeRegistry();
        schemeregistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        schemeregistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setSoTimeout(basichttpparams, 30000);
        HttpConnectionParams.setConnectionTimeout(basichttpparams, 30000);
        httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basichttpparams, schemeregistry), basichttpparams);
        if (!s.startsWith("https")) goto _L2; else goto _L1
_L1:
        if (checkSSL) goto _L4; else goto _L3
_L3:
        YConnectLogger.debug(TAG, "HTTPS ignore SSL Certification");
        ignoreSSLCertification(httpClient);
_L2:
        HttpResponse httpresponse;
        Header aheader[];
        int i;
        YConnectLogger.debug(TAG, (new StringBuilder()).append("POST Body: ").append(s1).toString());
        httpresponse = httpClient.execute(httppost);
        responseCode = httpresponse.getStatusLine().getStatusCode();
        responseMessage = httpresponse.getStatusLine().getReasonPhrase();
        YConnectLogger.debug(TAG, (new StringBuilder()).append("responseCode: ").append(responseCode).toString());
        YConnectLogger.debug(TAG, (new StringBuilder()).append("responseMessage: ").append(responseMessage).toString());
        aheader = httpresponse.getAllHeaders();
        i = aheader.length;
        int j = 0;
_L6:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        Header header = aheader[j];
        responseHeaders.put(header.getName(), header.getValue());
        j++;
        if (true) goto _L6; else goto _L5
_L4:
        YConnectLogger.debug(TAG, "checkSSLCertification");
        checkSSLCertification(schemeregistry);
          goto _L2
        SSLException sslexception;
        sslexception;
        YConnectLogger.error(TAG, sslexception.getMessage());
        sslexception.printStackTrace();
        httpClient.getConnectionManager().shutdown();
        return;
_L5:
        YConnectLogger.debug(TAG, (new StringBuilder()).append("responseHeaders: ").append(responseHeaders).toString());
        responseBody = EntityUtils.toString(httpresponse.getEntity());
        YConnectLogger.debug(TAG, (new StringBuilder()).append("responseBody: ").append(responseBody).toString());
        if (responseCode >= 400)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("Request URL: ").append(s).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Request Headers: ").append(httpheaders.toHeaderString()).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Request Query: ").append(s1).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Response Code: ").append(responseCode).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Response Message: ").append(responseMessage).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Response Headers: ").append(responseHeaders).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Response Body: ").append(responseBody).toString());
        }
        httpClient.getConnectionManager().shutdown();
        return;
        IOException ioexception;
        ioexception;
        YConnectLogger.error(TAG, ioexception.getMessage());
        ioexception.printStackTrace();
        httpClient.getConnectionManager().shutdown();
        return;
        Exception exception;
        exception;
        httpClient.getConnectionManager().shutdown();
        throw exception;
    }

    public void requestPost(String s, HttpParameters httpparameters, HttpHeaders httpheaders)
    {
        requestPost(s, httpparameters.toQueryString(), httpheaders, "application/x-www-form-urlencoded", "ISO-8859-1");
    }

    public void requestPut(String s, String s1, HttpHeaders httpheaders, String s2, String s3)
    {
        HttpPut httpput;
        YConnectLogger.debug(TAG, (new StringBuilder()).append("URL: ").append(s).toString());
        httpput = new HttpPut(s);
        if (httpheaders != null)
        {
            String s4;
            String s5;
            for (Iterator iterator = httpheaders.keySet().iterator(); iterator.hasNext(); YConnectLogger.debug(TAG, (new StringBuilder()).append(s4).append(": ").append(s5).toString()))
            {
                s4 = (String)iterator.next();
                s5 = (String)httpheaders.get(s4);
                httpput.setHeader(s4, s5);
            }

        }
        SchemeRegistry schemeregistry;
        StringEntity stringentity = new StringEntity(s1, s3);
        stringentity.setChunked(false);
        stringentity.setContentType(s2);
        httpput.setEntity(stringentity);
        schemeregistry = new SchemeRegistry();
        schemeregistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        schemeregistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setSoTimeout(basichttpparams, 30000);
        HttpConnectionParams.setConnectionTimeout(basichttpparams, 30000);
        httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basichttpparams, schemeregistry), basichttpparams);
        if (!s.startsWith("https")) goto _L2; else goto _L1
_L1:
        if (checkSSL) goto _L4; else goto _L3
_L3:
        YConnectLogger.debug(TAG, "HTTPS ignore SSL Certification");
        ignoreSSLCertification(httpClient);
_L2:
        HttpResponse httpresponse;
        Header aheader[];
        int i;
        YConnectLogger.debug(TAG, (new StringBuilder()).append("POST Body: ").append(s1).toString());
        httpresponse = httpClient.execute(httpput);
        responseCode = httpresponse.getStatusLine().getStatusCode();
        responseMessage = httpresponse.getStatusLine().getReasonPhrase();
        YConnectLogger.debug(TAG, (new StringBuilder()).append("responseCode: ").append(responseCode).toString());
        YConnectLogger.debug(TAG, (new StringBuilder()).append("responseMessage: ").append(responseMessage).toString());
        aheader = httpresponse.getAllHeaders();
        i = aheader.length;
        int j = 0;
_L6:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        Header header = aheader[j];
        responseHeaders.put(header.getName(), header.getValue());
        j++;
        if (true) goto _L6; else goto _L5
_L4:
        YConnectLogger.debug(TAG, "checkSSLCertification");
        checkSSLCertification(schemeregistry);
          goto _L2
        SSLException sslexception;
        sslexception;
        YConnectLogger.error(TAG, sslexception.getMessage());
        sslexception.printStackTrace();
        httpClient.getConnectionManager().shutdown();
        return;
_L5:
        YConnectLogger.debug(TAG, (new StringBuilder()).append("responseHeaders: ").append(responseHeaders).toString());
        responseBody = EntityUtils.toString(httpresponse.getEntity());
        YConnectLogger.debug(TAG, (new StringBuilder()).append("responseBody: ").append(responseBody).toString());
        if (responseCode >= 400)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("Request URL: ").append(s).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Request Headers: ").append(httpheaders.toHeaderString()).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Request Query: ").append(s1).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Response Code: ").append(responseCode).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Response Message: ").append(responseMessage).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Response Headers: ").append(responseHeaders).toString());
            YConnectLogger.error(TAG, (new StringBuilder()).append("Response Body: ").append(responseBody).toString());
        }
        httpClient.getConnectionManager().shutdown();
        return;
        IOException ioexception;
        ioexception;
        YConnectLogger.error(TAG, ioexception.getMessage());
        ioexception.printStackTrace();
        httpClient.getConnectionManager().shutdown();
        return;
        Exception exception;
        exception;
        httpClient.getConnectionManager().shutdown();
        throw exception;
    }

    public void requestPut(String s, HttpParameters httpparameters, HttpHeaders httpheaders)
    {
        requestPut(s, httpparameters.toQueryString(), httpheaders, "application/x-www-form-urlencoded", "ISO-8859-1");
    }

}
