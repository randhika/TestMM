// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AbsoluteLayout;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package jp.co.yahoo.android.ads:
//            CreateAdLayoutDisplay, AdViewForInstance, AdContainer, a, 
//            h

class a
    implements Runnable
{

    final CreateAdLayoutDisplay a;

    public void run()
    {
        CreateAdLayoutDisplay.d(a, new WebView(CreateAdLayoutDisplay.c(a)));
        AdViewForInstance.callWebViewResumeTimers(CreateAdLayoutDisplay.l(a), AdViewForInstance.getNeedWebViewResumeTimers());
        WebSettings websettings = CreateAdLayoutDisplay.l(a).getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setCacheMode(1);
        CreateAdLayoutDisplay.l(a).setFocusable(false);
        CreateAdLayoutDisplay.l(a).setBackgroundColor(Color.parseColor("#00000000"));
        CreateAdLayoutDisplay.l(a).setVerticalScrollbarOverlay(true);
        CreateAdLayoutDisplay.l(a).setVerticalScrollBarEnabled(false);
        CreateAdLayoutDisplay.l(a).setHorizontalScrollbarOverlay(true);
        CreateAdLayoutDisplay.l(a).setHorizontalScrollBarEnabled(false);
        android.widget.ams ams = new android.widget.ams(-1, (int)CreateAdLayoutDisplay.o(a));
        ams.addRule(3, 25);
        CreateAdLayoutDisplay.l(a).setLayoutParams(ams);
        CreateAdLayoutDisplay.l(a).setInitialScale((int)(100D * CreateAdLayoutDisplay.e(a).getAdRatio()));
        String s = "<html><head><meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=0\" /><script>function getMessage() {return document.getElementById('message').innerHTML;}</script></head><body style='margin:0;padding:0;'><img src='#AD_IMAGE_URL#' onError='document.getElementById(\"message\").innerHTML=\"error\"' width=\"100%\" height=\"100%\"/><div id='message' style='display:none;'>ok</div></body></html>".replace("#AD_IMAGE_URL#", (new StringBuilder()).append(CreateAdLayoutDisplay.b(a).g()).append("?v").append((new Random()).nextInt(0xdbba0)).toString());
        vascriptInterface vascriptinterface = new vascriptInterface(a);
        CreateAdLayoutDisplay.l(a).addJavascriptInterface(vascriptinterface, "android");
        CreateAdLayoutDisplay.l(a).setWebViewClient(new <init>(a, null));
        CreateAdLayoutDisplay.l(a).loadDataWithBaseURL(null, s, "text/html", "UTF-8", null);
        CreateAdLayoutDisplay.l(a).setId(21);
        CreateAdLayoutDisplay.a(a, null);
        if (h.d(CreateAdLayoutDisplay.b(a).i()[0])) goto _L2; else goto _L1
_L1:
        CreateAdLayoutDisplay.a(a, new AbsoluteLayout(CreateAdLayoutDisplay.c(a)));
        android.widget.ams ams1 = new android.widget.ams(-1, (int)CreateAdLayoutDisplay.o(a));
        ams1.addRule(3, 25);
        CreateAdLayoutDisplay.m(a).setLayoutParams(ams1);
        CreateAdLayoutDisplay.m(a).setId(26);
        android.widget.FrameLayout framelayout = CreateAdLayoutDisplay.b(a, CreateAdLayoutDisplay.b(a).i()[0]);
        android.widget.FrameLayout framelayout1 = CreateAdLayoutDisplay.b(a, CreateAdLayoutDisplay.b(a).i()[1]);
        android.widget.FrameLayout framelayout2 = CreateAdLayoutDisplay.b(a, CreateAdLayoutDisplay.b(a).i()[2]);
        android.widget.FrameLayout framelayout3 = CreateAdLayoutDisplay.b(a, CreateAdLayoutDisplay.b(a).i()[3]);
        android.widget.FrameLayout framelayout4 = CreateAdLayoutDisplay.b(a, CreateAdLayoutDisplay.b(a).i()[4]);
        if (framelayout != null)
        {
            CreateAdLayoutDisplay.m(a).addView(framelayout);
        }
        if (framelayout1 != null)
        {
            CreateAdLayoutDisplay.m(a).addView(framelayout1);
        }
        if (framelayout2 != null)
        {
            CreateAdLayoutDisplay.m(a).addView(framelayout2);
        }
        if (framelayout3 != null)
        {
            CreateAdLayoutDisplay.m(a).addView(framelayout3);
        }
        if (framelayout4 != null)
        {
            CreateAdLayoutDisplay.m(a).addView(framelayout4);
        }
        CreateAdLayoutDisplay.a(a, framelayout, CreateAdLayoutDisplay.b(a).h()[0]);
        CreateAdLayoutDisplay.a(a, framelayout1, CreateAdLayoutDisplay.b(a).h()[1]);
        CreateAdLayoutDisplay.a(a, framelayout2, CreateAdLayoutDisplay.b(a).h()[2]);
        CreateAdLayoutDisplay.a(a, framelayout3, CreateAdLayoutDisplay.b(a).h()[3]);
        CreateAdLayoutDisplay.a(a, framelayout4, CreateAdLayoutDisplay.b(a).h()[4]);
_L4:
        CreateAdLayoutDisplay.q(a).set(true);
        return;
_L2:
        if (!h.d(CreateAdLayoutDisplay.b(a).h()[0]))
        {
            CreateAdLayoutDisplay.l(a).setOnTouchListener(new android.view.View.OnTouchListener() {

                final CreateAdLayoutDisplay._cls4 a;

                public boolean onTouch(View view, MotionEvent motionevent)
                {
                    motionevent.getAction();
                    JVM INSTR tableswitch 0 0: default 24
                //                               0 26;
                       goto _L1 _L2
_L1:
                    return true;
_L2:
                    (new Thread(new CreateAdLayoutDisplay.a(a.a, CreateAdLayoutDisplay.b(a.a).h()[0]))).start();
                    if (true) goto _L1; else goto _L3
_L3:
                }

            
            {
                a = CreateAdLayoutDisplay._cls4.this;
                super();
            }
            });
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    _cls1.a(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        a = createadlayoutdisplay;
        super();
    }
}
