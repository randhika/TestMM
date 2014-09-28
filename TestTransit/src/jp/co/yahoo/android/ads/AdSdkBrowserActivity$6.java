// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.view.View;
import android.webkit.WebView;

// Referenced classes of package jp.co.yahoo.android.ads:
//            AdSdkBrowserActivity

class a
    implements android.view.Activity._cls6
{

    final AdSdkBrowserActivity a;

    public void onClick(View view)
    {
        if (AdSdkBrowserActivity.access$500(a).canGoForward())
        {
            AdSdkBrowserActivity.access$500(a).goForward();
        }
    }

    (AdSdkBrowserActivity adsdkbrowseractivity)
    {
        a = adsdkbrowseractivity;
        super();
    }
}
