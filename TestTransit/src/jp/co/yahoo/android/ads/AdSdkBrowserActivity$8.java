// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.view.View;
import android.webkit.WebView;

// Referenced classes of package jp.co.yahoo.android.ads:
//            AdSdkBrowserActivity

class a
    implements android.view.Activity._cls8
{

    final AdSdkBrowserActivity a;

    public void onClick(View view)
    {
        if (AdSdkBrowserActivity.access$1200(a))
        {
            AdSdkBrowserActivity.access$500(a).stopLoading();
            AdSdkBrowserActivity.access$300(a);
            return;
        } else
        {
            AdSdkBrowserActivity.access$500(a).reload();
            AdSdkBrowserActivity.access$100(a);
            return;
        }
    }

    (AdSdkBrowserActivity adsdkbrowseractivity)
    {
        a = adsdkbrowseractivity;
        super();
    }
}
