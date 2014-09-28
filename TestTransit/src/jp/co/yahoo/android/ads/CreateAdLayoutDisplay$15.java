// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.graphics.Color;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package jp.co.yahoo.android.ads:
//            CreateAdLayoutDisplay, AdViewForInstance, r, AdContainer, 
//            a

class a
    implements Runnable
{

    final CreateAdLayoutDisplay a;

    public void run()
    {
        CreateAdLayoutDisplay.b(a, new WebView(CreateAdLayoutDisplay.c(a)));
        AdViewForInstance.callWebViewResumeTimers(CreateAdLayoutDisplay.h(a), AdViewForInstance.getNeedWebViewResumeTimers());
        CreateAdLayoutDisplay.h(a).setBackgroundColor(Color.parseColor("#00000000"));
        WebSettings websettings = CreateAdLayoutDisplay.h(a).getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setCacheMode(1);
        (new r()).a(websettings);
        CreateAdLayoutDisplay.h(a).setFocusable(false);
        CreateAdLayoutDisplay.h(a).setScrollBarStyle(0);
        android.widget.ms ms = new android.widget.ms(-1, (int)(50D * CreateAdLayoutDisplay.e(a).getAdRatio()));
        CreateAdLayoutDisplay.h(a).setLayoutParams(ms);
        CreateAdLayoutDisplay.h(a).setInitialScale((int)(100D * CreateAdLayoutDisplay.e(a).getAdRatio()));
        CreateAdLayoutDisplay.h(a).loadUrl(CreateAdLayoutDisplay.b(a).o());
        CreateAdLayoutDisplay.h(a).setId(10);
        CreateAdLayoutDisplay.i(a).set(true);
    }

    (CreateAdLayoutDisplay createadlayoutdisplay)
    {
        a = createadlayoutdisplay;
        super();
    }
}
