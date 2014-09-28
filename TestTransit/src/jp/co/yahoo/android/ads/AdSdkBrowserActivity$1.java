// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

// Referenced classes of package jp.co.yahoo.android.ads:
//            AdSdkBrowserActivity

class a extends WebViewClient
{

    final AdSdkBrowserActivity a;

    public void onPageFinished(WebView webview, String s)
    {
        super.onPageFinished(webview, s);
        AdSdkBrowserActivity.access$200(a);
        AdSdkBrowserActivity.access$300(a);
        a.setTitle(webview.getTitle());
        AdSdkBrowserActivity.access$400(a);
    }

    public void onPageStarted(WebView webview, String s, Bitmap bitmap)
    {
        super.onPageStarted(webview, s, bitmap);
        if (AdSdkBrowserActivity.access$000(a, s))
        {
            AdSdkBrowserActivity.access$100(a);
            a.setProgressBarVisibility(true);
            return;
        } else
        {
            webview.stopLoading();
            return;
        }
    }

    public boolean shouldOverrideUrlLoading(WebView webview, String s)
    {
        if (AdSdkBrowserActivity.access$000(a, s))
        {
            return false;
        } else
        {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(s));
            intent.setAction("android.intent.action.VIEW");
            a.startActivity(intent);
            return true;
        }
    }

    (AdSdkBrowserActivity adsdkbrowseractivity)
    {
        a = adsdkbrowseractivity;
        super();
    }
}
