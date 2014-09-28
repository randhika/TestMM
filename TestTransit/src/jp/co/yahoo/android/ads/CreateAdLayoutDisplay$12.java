// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.webkit.WebView;

// Referenced classes of package jp.co.yahoo.android.ads:
//            CreateAdLayoutDisplay, AdContainer

class a
    implements Runnable
{

    final WebView a;
    final CreateAdLayoutDisplay b;

    public void run()
    {
        CreateAdLayoutDisplay.e(b).addView(a);
    }

    (CreateAdLayoutDisplay createadlayoutdisplay, WebView webview)
    {
        b = createadlayoutdisplay;
        a = webview;
        super();
    }
}
