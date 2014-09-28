// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

// Referenced classes of package com.android.volley.toolbox:
//            HttpStack

public class HurlStack
    implements HttpStack
{
    public static interface UrlRewriter
    {

        public abstract String rewriteUrl(String s);
    }


    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private final SSLSocketFactory mSslSocketFactory;
    private final UrlRewriter mUrlRewriter;

    public HurlStack()
    {
        this(null);
    }

    public HurlStack(UrlRewriter urlrewriter)
    {
        this(urlrewriter, null);
    }

    public HurlStack(UrlRewriter urlrewriter, SSLSocketFactory sslsocketfactory)
    {
        mUrlRewriter = urlrewriter;
        mSslSocketFactory = sslsocketfactory;
    }

    private static void addBodyIfExists(HttpURLConnection httpurlconnection, Request request)
        throws IOException, AuthFailureError
    {
        byte abyte0[] = request.getBody();
        if (abyte0 != null)
        {
            httpurlconnection.setDoOutput(true);
            httpurlconnection.addRequestProperty("Content-Type", request.getBodyContentType());
            DataOutputStream dataoutputstream = new DataOutputStream(httpurlconnection.getOutputStream());
            dataoutputstream.write(abyte0);
            dataoutputstream.close();
        }
    }

    private static HttpEntity entityFromConnection(HttpURLConnection httpurlconnection)
    {
        BasicHttpEntity basichttpentity = new BasicHttpEntity();
        java.io.InputStream inputstream1 = httpurlconnection.getInputStream();
        java.io.InputStream inputstream = inputstream1;
_L2:
        basichttpentity.setContent(inputstream);
        basichttpentity.setContentLength(httpurlconnection.getContentLength());
        basichttpentity.setContentEncoding(httpurlconnection.getContentEncoding());
        basichttpentity.setContentType(httpurlconnection.getContentType());
        return basichttpentity;
        IOException ioexception;
        ioexception;
        inputstream = httpurlconnection.getErrorStream();
        if (true) goto _L2; else goto _L1
_L1:
    }

    private HttpURLConnection openConnection(URL url, Request request)
        throws IOException
    {
        HttpURLConnection httpurlconnection = createConnection(url);
        int i = request.getTimeoutMs();
        httpurlconnection.setConnectTimeout(i);
        httpurlconnection.setReadTimeout(i);
        httpurlconnection.setUseCaches(false);
        httpurlconnection.setDoInput(true);
        if ("https".equals(url.getProtocol()) && mSslSocketFactory != null)
        {
            ((HttpsURLConnection)httpurlconnection).setSSLSocketFactory(mSslSocketFactory);
        }
        return httpurlconnection;
    }

    static void setConnectionParametersForRequest(HttpURLConnection httpurlconnection, Request request)
        throws IOException, AuthFailureError
    {
        switch (request.getMethod())
        {
        default:
            throw new IllegalStateException("Unknown method type.");

        case -1: 
            byte abyte0[] = request.getPostBody();
            if (abyte0 != null)
            {
                httpurlconnection.setDoOutput(true);
                httpurlconnection.setRequestMethod("POST");
                httpurlconnection.addRequestProperty("Content-Type", request.getPostBodyContentType());
                DataOutputStream dataoutputstream = new DataOutputStream(httpurlconnection.getOutputStream());
                dataoutputstream.write(abyte0);
                dataoutputstream.close();
            }
            return;

        case 0: // '\0'
            httpurlconnection.setRequestMethod("GET");
            return;

        case 3: // '\003'
            httpurlconnection.setRequestMethod("DELETE");
            return;

        case 1: // '\001'
            httpurlconnection.setRequestMethod("POST");
            addBodyIfExists(httpurlconnection, request);
            return;

        case 2: // '\002'
            httpurlconnection.setRequestMethod("PUT");
            addBodyIfExists(httpurlconnection, request);
            return;

        case 4: // '\004'
            httpurlconnection.setRequestMethod("HEAD");
            return;

        case 5: // '\005'
            httpurlconnection.setRequestMethod("OPTIONS");
            return;

        case 6: // '\006'
            httpurlconnection.setRequestMethod("TRACE");
            return;

        case 7: // '\007'
            addBodyIfExists(httpurlconnection, request);
            httpurlconnection.setRequestMethod("PATCH");
            return;
        }
    }

    protected HttpURLConnection createConnection(URL url)
        throws IOException
    {
        return (HttpURLConnection)url.openConnection();
    }

    public HttpResponse performRequest(Request request, Map map)
        throws IOException, AuthFailureError
    {
        String s = request.getUrl();
        HashMap hashmap = new HashMap();
        hashmap.putAll(request.getHeaders());
        hashmap.putAll(map);
        if (mUrlRewriter != null)
        {
            String s2 = mUrlRewriter.rewriteUrl(s);
            if (s2 == null)
            {
                throw new IOException((new StringBuilder("URL blocked by rewriter: ")).append(s).toString());
            }
            s = s2;
        }
        HttpURLConnection httpurlconnection = openConnection(new URL(s), request);
        Iterator iterator = hashmap.keySet().iterator();
        ProtocolVersion protocolversion;
        do
        {
            if (!iterator.hasNext())
            {
                setConnectionParametersForRequest(httpurlconnection, request);
                protocolversion = new ProtocolVersion("HTTP", 1, 1);
                if (httpurlconnection.getResponseCode() == -1)
                {
                    throw new IOException("Could not retrieve response code from HttpUrlConnection.");
                }
                break;
            }
            String s1 = (String)iterator.next();
            httpurlconnection.addRequestProperty(s1, (String)hashmap.get(s1));
        } while (true);
        BasicHttpResponse basichttpresponse = new BasicHttpResponse(new BasicStatusLine(protocolversion, httpurlconnection.getResponseCode(), httpurlconnection.getResponseMessage()));
        basichttpresponse.setEntity(entityFromConnection(httpurlconnection));
        Iterator iterator1 = httpurlconnection.getHeaderFields().entrySet().iterator();
        do
        {
            java.util.Map.Entry entry;
            do
            {
                if (!iterator1.hasNext())
                {
                    return basichttpresponse;
                }
                entry = (java.util.Map.Entry)iterator1.next();
            } while (entry.getKey() == null);
            basichttpresponse.addHeader(new BasicHeader((String)entry.getKey(), (String)((List)entry.getValue()).get(0)));
        } while (true);
    }
}
