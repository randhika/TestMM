// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;

// Referenced classes of package com.android.volley.toolbox:
//            HttpStack

public class HttpClientStack
    implements HttpStack
{
    public static final class HttpPatch extends HttpEntityEnclosingRequestBase
    {

        public static final String METHOD_NAME = "PATCH";

        public String getMethod()
        {
            return "PATCH";
        }

        public HttpPatch()
        {
        }

        public HttpPatch(String s)
        {
            setURI(URI.create(s));
        }

        public HttpPatch(URI uri)
        {
            setURI(uri);
        }
    }


    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    protected final HttpClient mClient;

    public HttpClientStack(HttpClient httpclient)
    {
        mClient = httpclient;
    }

    private static void addHeaders(HttpUriRequest httpurirequest, Map map)
    {
        Iterator iterator = map.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                return;
            }
            String s = (String)iterator.next();
            httpurirequest.setHeader(s, (String)map.get(s));
        } while (true);
    }

    static HttpUriRequest createHttpRequest(Request request, Map map)
        throws AuthFailureError
    {
        switch (request.getMethod())
        {
        default:
            throw new IllegalStateException("Unknown request method.");

        case -1: 
            byte abyte0[] = request.getPostBody();
            if (abyte0 != null)
            {
                HttpPost httppost1 = new HttpPost(request.getUrl());
                httppost1.addHeader("Content-Type", request.getPostBodyContentType());
                httppost1.setEntity(new ByteArrayEntity(abyte0));
                return httppost1;
            } else
            {
                return new HttpGet(request.getUrl());
            }

        case 0: // '\0'
            return new HttpGet(request.getUrl());

        case 3: // '\003'
            return new HttpDelete(request.getUrl());

        case 1: // '\001'
            HttpPost httppost = new HttpPost(request.getUrl());
            httppost.addHeader("Content-Type", request.getBodyContentType());
            setEntityIfNonEmptyBody(httppost, request);
            return httppost;

        case 2: // '\002'
            HttpPut httpput = new HttpPut(request.getUrl());
            httpput.addHeader("Content-Type", request.getBodyContentType());
            setEntityIfNonEmptyBody(httpput, request);
            return httpput;

        case 4: // '\004'
            return new HttpHead(request.getUrl());

        case 5: // '\005'
            return new HttpOptions(request.getUrl());

        case 6: // '\006'
            return new HttpTrace(request.getUrl());

        case 7: // '\007'
            HttpPatch httppatch = new HttpPatch(request.getUrl());
            httppatch.addHeader("Content-Type", request.getBodyContentType());
            setEntityIfNonEmptyBody(httppatch, request);
            return httppatch;
        }
    }

    private static List getPostParameterPairs(Map map)
    {
        ArrayList arraylist = new ArrayList(map.size());
        Iterator iterator = map.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                return arraylist;
            }
            String s = (String)iterator.next();
            arraylist.add(new BasicNameValuePair(s, (String)map.get(s)));
        } while (true);
    }

    private static void setEntityIfNonEmptyBody(HttpEntityEnclosingRequestBase httpentityenclosingrequestbase, Request request)
        throws AuthFailureError
    {
        byte abyte0[] = request.getBody();
        if (abyte0 != null)
        {
            httpentityenclosingrequestbase.setEntity(new ByteArrayEntity(abyte0));
        }
    }

    protected void onPrepareRequest(HttpUriRequest httpurirequest)
        throws IOException
    {
    }

    public HttpResponse performRequest(Request request, Map map)
        throws IOException, AuthFailureError
    {
        HttpUriRequest httpurirequest = createHttpRequest(request, map);
        addHeaders(httpurirequest, map);
        addHeaders(httpurirequest, request.getHeaders());
        onPrepareRequest(httpurirequest);
        org.apache.http.params.HttpParams httpparams = httpurirequest.getParams();
        int i = request.getTimeoutMs();
        HttpConnectionParams.setConnectionTimeout(httpparams, 5000);
        HttpConnectionParams.setSoTimeout(httpparams, i);
        return mClient.execute(httpurirequest);
    }
}
