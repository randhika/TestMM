// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.graphics.Color;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package jp.co.yahoo.android.ads:
//            CreateAdLayoutDisplay, AdViewForInstance, a, AdContainer

class a
    implements Runnable
{

    final CreateAdLayoutDisplay a;

    public void run()
    {
        CreateAdLayoutDisplay.a(a, new WebView(CreateAdLayoutDisplay.c(a)));
        AdViewForInstance.callWebViewResumeTimers(CreateAdLayoutDisplay.f(a), AdViewForInstance.getNeedWebViewResumeTimers());
        WebSettings websettings = CreateAdLayoutDisplay.f(a).getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setCacheMode(1);
        CreateAdLayoutDisplay.f(a).setFocusable(false);
        CreateAdLayoutDisplay.f(a).setScrollBarStyle(0);
        CreateAdLayoutDisplay.f(a).setBackgroundColor(Color.parseColor("#00000000"));
        android.widget.ms ms = new android.widget.ms(-1, (int)((double)CreateAdLayoutDisplay.b(a).e() * CreateAdLayoutDisplay.e(a).getAdRatio()));
        CreateAdLayoutDisplay.f(a).setLayoutParams(ms);
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(CreateAdLayoutDisplay.b(a).d());
        stringbuilder.append((new StringBuilder()).append("?v").append((new Random()).nextInt(0xdbba0)).toString());
        String s = "<html><head><meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=0\" /><script>function getMessage() {return document.getElementById('message').innerHTML;}</script></head><body style='margin:0;padding:0;'><img src='#AD_IMAGE_URL#' onError='document.getElementById(\"message\").innerHTML=\"error\"' width=\"100%\" height=\"100%\"/><div id='message' style='display:none;'>ok</div></body></html>".replace("#AD_IMAGE_URL#", stringbuilder.toString());
        CreateAdLayoutDisplay.f(a).loadDataWithBaseURL("about:blank", s, "text/html", "UTF-8", null);
        CreateAdLayoutDisplay.f(a).setId(10);
        CreateAdLayoutDisplay.g(a).set(true);
    }

    (CreateAdLayoutDisplay createadlayoutdisplay)
    {
        a = createadlayoutdisplay;
        super();
    }
}
