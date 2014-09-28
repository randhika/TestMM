// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.webkit.WebView;
import android.webkit.WebViewClient;

// Referenced classes of package jp.co.yahoo.android.ads:
//            CreateAdLayoutDisplay, h

private class <init> extends WebViewClient
{

    final CreateAdLayoutDisplay a;

    public void onPageFinished(WebView webview, String s)
    {
        super.onPageFinished(webview, s);
        CreateAdLayoutDisplay.l(a).loadUrl("javascript:android.getAdWebViewMessage(getMessage())");
        h.a(3, (new StringBuilder()).append("ExpandWebViewClient: onPageFinished=").append(s).toString());
    }

    private (CreateAdLayoutDisplay createadlayoutdisplay)
    {
        a = createadlayoutdisplay;
        super();
    }

    a(CreateAdLayoutDisplay createadlayoutdisplay, a a1)
    {
        this(createadlayoutdisplay);
    }
}
