// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

// Referenced classes of package com.google.android.gms.analytics:
//            n, GoogleAnalytics, ak, ab, 
//            aa, q, x, y

class ah
    implements n
{

    private final Context mContext;
    private GoogleAnalytics ul;
    private final String xa;
    private final HttpClient xb;
    private URL xc;

    ah(HttpClient httpclient, Context context)
    {
        this(httpclient, GoogleAnalytics.getInstance(context), context);
    }

    ah(HttpClient httpclient, GoogleAnalytics googleanalytics, Context context)
    {
        mContext = context.getApplicationContext();
        xa = a("GoogleAnalytics", "3.0", android.os.Build.VERSION.RELEASE, ak.a(Locale.getDefault()), Build.MODEL, Build.ID);
        xb = httpclient;
        ul = googleanalytics;
    }

    private void a(ab ab1, URL url, boolean flag)
    {
        if (!TextUtils.isEmpty(ab1.dq()) && dx()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (url != null)
        {
            break MISSING_BLOCK_LABEL_273;
        }
        if (xc == null) goto _L4; else goto _L3
_L3:
        URL url1 = xc;
_L7:
        HttpHost httphost = new HttpHost(url1.getHost(), url1.getPort(), url1.getProtocol());
        HttpEntityEnclosingRequest httpentityenclosingrequest;
        HttpResponse httpresponse;
        int i;
        HttpEntity httpentity;
        MalformedURLException malformedurlexception;
        try
        {
            httpentityenclosingrequest = e(ab1.dq(), url1.getPath());
        }
        catch (ClientProtocolException clientprotocolexception)
        {
            aa.D("ClientProtocolException sending monitoring hit.");
            return;
        }
        catch (IOException ioexception)
        {
            aa.D((new StringBuilder()).append("Exception sending monitoring hit: ").append(ioexception.getClass().getSimpleName()).toString());
            aa.D(ioexception.getMessage());
            return;
        }
        if (httpentityenclosingrequest == null) goto _L1; else goto _L5
_L5:
        httpentityenclosingrequest.addHeader("Host", httphost.toHostString());
        if (aa.dp())
        {
            a(httpentityenclosingrequest);
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_116;
        }
        q.w(mContext);
        httpresponse = xb.execute(httphost, httpentityenclosingrequest);
        i = httpresponse.getStatusLine().getStatusCode();
        httpentity = httpresponse.getEntity();
        if (httpentity == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        httpentity.consumeContent();
        if (i == 200) goto _L1; else goto _L6
_L6:
        aa.D((new StringBuilder()).append("Bad response: ").append(httpresponse.getStatusLine().getStatusCode()).toString());
        return;
_L4:
        try
        {
            url1 = new URL("https://ssl.google-analytics.com/collect");
        }
        // Misplaced declaration of an exception variable
        catch (MalformedURLException malformedurlexception)
        {
            return;
        }
          goto _L7
        url1 = url;
          goto _L7
    }

    private void a(HttpEntityEnclosingRequest httpentityenclosingrequest)
    {
        StringBuffer stringbuffer;
        stringbuffer = new StringBuffer();
        org.apache.http.Header aheader[] = httpentityenclosingrequest.getAllHeaders();
        int i = aheader.length;
        for (int j = 0; j < i; j++)
        {
            stringbuffer.append(aheader[j].toString()).append("\n");
        }

        stringbuffer.append(httpentityenclosingrequest.getRequestLine().toString()).append("\n");
        if (httpentityenclosingrequest.getEntity() == null)
        {
            break MISSING_BLOCK_LABEL_150;
        }
        InputStream inputstream = httpentityenclosingrequest.getEntity().getContent();
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_150;
        }
        int k = inputstream.available();
        if (k > 0)
        {
            try
            {
                byte abyte0[] = new byte[k];
                inputstream.read(abyte0);
                stringbuffer.append("POST:\n");
                stringbuffer.append(new String(abyte0)).append("\n");
            }
            catch (IOException ioexception)
            {
                aa.C("Error Writing hit to log...");
            }
        }
        aa.C(stringbuffer.toString());
        return;
    }

    private HttpEntityEnclosingRequest e(String s, String s1)
    {
        String s2;
        if (TextUtils.isEmpty(s))
        {
            aa.D("Empty hit, discarding.");
            return null;
        }
        s2 = (new StringBuilder()).append(s1).append("?").append(s).toString();
        if (s2.length() >= 2036) goto _L2; else goto _L1
_L1:
        BasicHttpEntityEnclosingRequest basichttpentityenclosingrequest = new BasicHttpEntityEnclosingRequest("GET", s2);
_L4:
        basichttpentityenclosingrequest.addHeader("User-Agent", xa);
        return basichttpentityenclosingrequest;
_L2:
        basichttpentityenclosingrequest = new BasicHttpEntityEnclosingRequest("POST", s1);
        try
        {
            basichttpentityenclosingrequest.setEntity(new StringEntity(s));
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            aa.D("Encoding error, discarding hit");
            return null;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void M(String s)
    {
        try
        {
            xc = new URL(s);
            return;
        }
        catch (MalformedURLException malformedurlexception)
        {
            xc = null;
        }
    }

    public int a(List list, ab ab1, boolean flag)
    {
        int i;
        int j;
        int k;
        URL url;
        boolean flag1;
        int l;
        i = 0;
        j = Math.min(list.size(), 40);
        ab1.c("_hr", list.size());
        k = 0;
        url = null;
        flag1 = true;
        l = 0;
_L2:
        x x1;
        URL url1;
        int i1;
        URL url2;
        if (l >= j)
        {
            break MISSING_BLOCK_LABEL_544;
        }
        x1 = (x)list.get(l);
        url1 = a(x1);
        if (url1 != null)
        {
            break; /* Loop/switch isn't completed */
        }
        int l1;
        URL url3;
        if (aa.dp())
        {
            aa.D((new StringBuilder()).append("No destination: discarding hit: ").append(x1.dk()).toString());
        } else
        {
            aa.D("No destination: discarding hit.");
        }
        l1 = i + 1;
        k++;
        url3 = url;
        i1 = l1;
        url2 = url3;
_L3:
        l++;
        i = i1;
        url = url2;
        if (true) goto _L2; else goto _L1
_L1:
        HttpHost httphost;
        String s1;
        HttpEntityEnclosingRequest httpentityenclosingrequest;
label0:
        {
            httphost = new HttpHost(url1.getHost(), url1.getPort(), url1.getProtocol());
            String s = url1.getPath();
            int k1;
            if (TextUtils.isEmpty(x1.dk()))
            {
                s1 = "";
            } else
            {
                s1 = y.a(x1, System.currentTimeMillis());
            }
            httpentityenclosingrequest = e(s1, s);
            if (httpentityenclosingrequest != null)
            {
                break label0;
            }
            k1 = i + 1;
            k++;
            i1 = k1;
            url2 = url1;
        }
          goto _L3
        httpentityenclosingrequest.addHeader("Host", httphost.toHostString());
        if (aa.dp())
        {
            a(httpentityenclosingrequest);
        }
        if (s1.length() > 8192)
        {
            aa.D("Hit too long (> 8192 bytes)--not sent");
            k++;
        } else
        {
label1:
            {
                if (!ul.isDryRunEnabled())
                {
                    break label1;
                }
                aa.B("Dry run enabled. Hit not actually sent.");
            }
        }
_L4:
        ab1.c("_td", s1.getBytes().length);
        i1 = i + 1;
        url2 = url1;
          goto _L3
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_352;
        }
        q.w(mContext);
        flag1 = false;
        HttpResponse httpresponse;
        int j1;
        HttpEntity httpentity;
        httpresponse = xb.execute(httphost, httpentityenclosingrequest);
        j1 = httpresponse.getStatusLine().getStatusCode();
        httpentity = httpresponse.getEntity();
        if (httpentity == null)
        {
            break MISSING_BLOCK_LABEL_402;
        }
        httpentity.consumeContent();
        if (j1 != 200)
        {
            try
            {
                aa.D((new StringBuilder()).append("Bad response: ").append(httpresponse.getStatusLine().getStatusCode()).toString());
            }
            catch (ClientProtocolException clientprotocolexception)
            {
                aa.D("ClientProtocolException sending hit; discarding hit...");
                ab1.c("_hd", k);
            }
            catch (IOException ioexception)
            {
                aa.D((new StringBuilder()).append("Exception sending hit: ").append(ioexception.getClass().getSimpleName()).toString());
                aa.D(ioexception.getMessage());
                ab1.c("_de", 1);
                ab1.c("_hd", k);
                ab1.c("_hs", i);
                a(ab1, url1, flag1);
                return i;
            }
        }
          goto _L4
        ab1.c("_hd", k);
        ab1.c("_hs", i);
        if (flag)
        {
            a(ab1, url, flag1);
        }
        return i;
          goto _L3
    }

    String a(String s, String s1, String s2, String s3, String s4, String s5)
    {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[] {
            s, s1, s2, s3, s4, s5
        });
    }

    URL a(x x1)
    {
        String s;
        if (xc != null)
        {
            return xc;
        }
        s = x1.dn();
        String s1;
        URL url;
        if ("http:".equals(s))
        {
            s1 = "http://www.google-analytics.com/collect";
        } else
        {
            s1 = "https://ssl.google-analytics.com/collect";
        }
        try
        {
            url = new URL(s1);
        }
        catch (MalformedURLException malformedurlexception)
        {
            aa.A("Error trying to parse the hardcoded host url. This really shouldn't happen.");
            return null;
        }
        return url;
    }

    public boolean cC()
    {
        NetworkInfo networkinfo = ((ConnectivityManager)mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (networkinfo == null || !networkinfo.isConnected())
        {
            aa.C("...no network connectivity");
            return false;
        } else
        {
            return true;
        }
    }

    boolean dx()
    {
        return 100D * Math.random() <= 1.0D;
    }
}
