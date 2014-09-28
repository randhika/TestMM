// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

// Referenced classes of package jp.co.yahoo.android.ads:
//            AdSdkBrowserActivity

class a extends WebChromeClient
{

    final AdSdkBrowserActivity a;

    public void onProgressChanged(WebView webview, int i)
    {
        a.setProgress(i * 100);
        if (i == 100)
        {
            a.setProgressBarVisibility(false);
        }
    }

    (AdSdkBrowserActivity adsdkbrowseractivity)
    {
        a = adsdkbrowseractivity;
        super();
    }
}
