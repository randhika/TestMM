// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// Referenced classes of package com.google.android.gms.internal:
//            ey, eo, ex, eu, 
//            al, ev

public class fa extends ey
{

    public fa(ex ex1, boolean flag)
    {
        super(ex1, flag);
    }

    protected WebResourceResponse c(Context context, String s, String s1)
        throws IOException
    {
        HttpURLConnection httpurlconnection = (HttpURLConnection)(new URL(s1)).openConnection();
        WebResourceResponse webresourceresponse;
        eo.a(context, s, true, httpurlconnection);
        httpurlconnection.connect();
        webresourceresponse = new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(eo.a(new InputStreamReader(httpurlconnection.getInputStream())).getBytes("UTF-8")));
        httpurlconnection.disconnect();
        return webresourceresponse;
        Exception exception;
        exception;
        httpurlconnection.disconnect();
        throw exception;
    }

    public WebResourceResponse shouldInterceptRequest(WebView webview, String s)
    {
        WebResourceResponse webresourceresponse;
        try
        {
            if (!"mraid.js".equalsIgnoreCase((new File(s)).getName()))
            {
                return super.shouldInterceptRequest(webview, s);
            }
            if (!(webview instanceof ex))
            {
                eu.D("Tried to intercept request from a WebView that wasn't an AdWebView.");
                return super.shouldInterceptRequest(webview, s);
            }
            ex ex1 = (ex)webview;
            ex1.cb().aS();
            if (ex1.V().mf)
            {
                eu.C("shouldInterceptRequest(http://media.admob.com/mraid/v1/mraid_app_interstitial.js)");
                return c(ex1.getContext(), lN.cd().sw, "http://media.admob.com/mraid/v1/mraid_app_interstitial.js");
            }
            if (ex1.ce())
            {
                eu.C("shouldInterceptRequest(http://media.admob.com/mraid/v1/mraid_app_expanded_banner.js)");
                return c(ex1.getContext(), lN.cd().sw, "http://media.admob.com/mraid/v1/mraid_app_expanded_banner.js");
            }
            eu.C("shouldInterceptRequest(http://media.admob.com/mraid/v1/mraid_app_banner.js)");
            webresourceresponse = c(ex1.getContext(), lN.cd().sw, "http://media.admob.com/mraid/v1/mraid_app_banner.js");
        }
        catch (IOException ioexception)
        {
            eu.D((new StringBuilder()).append("Could not fetching MRAID JS. ").append(ioexception.getMessage()).toString());
            return super.shouldInterceptRequest(webview, s);
        }
        return webresourceresponse;
    }
}
