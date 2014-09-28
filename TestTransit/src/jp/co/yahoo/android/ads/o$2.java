// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

// Referenced classes of package jp.co.yahoo.android.ads:
//            o, AdViewForInstance

class  extends WebViewClient
{

    final o a;

    public boolean shouldOverrideUrlLoading(WebView webview, String s)
    {
        Intent intent;
        String s1;
        intent = AdViewForInstance.getInnerBrowserIntent();
        if (intent == null)
        {
            o.a(a, s);
            return true;
        }
        s1 = AdViewForInstance.getUrlExtraName();
        if (s1 != null)
        {
            break MISSING_BLOCK_LABEL_65;
        }
        intent.setData(Uri.parse(s.toString()));
_L1:
        o.a(a).startActivity(intent);
        ActivityNotFoundException activitynotfoundexception;
        return true;
        try
        {
            intent.putExtra(s1, s);
        }
        // Misplaced declaration of an exception variable
        catch (ActivityNotFoundException activitynotfoundexception)
        {
            o.a(a, s);
            return true;
        }
          goto _L1
    }

    oundException(o o1)
    {
        a = o1;
        super();
    }
}
