// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package jp.co.yahoo.android.ads:
//            n, AdSdkBrowserActivity, AdContainer, a, 
//            AdViewForInstance

class init> extends WebViewClient
{

    final ctivity a;

    public boolean shouldOverrideUrlLoading(WebView webview, String s)
    {
        if (s.matches("^ydninfos://.*$") || s.matches("^ydninfo://.*$"))
        {
            String s1 = s.replace("ydninfos://", "https://").replace("ydninfo://", "http://");
            try
            {
                Intent intent = new Intent(n.a(a.a), jp/co/yahoo/android/ads/AdSdkBrowserActivity);
                intent.putExtra("YJADSDK_URL", s1);
                intent.putExtra("YJADSDK_ADRATIO", a.a.getAdRatio());
                intent.setAction("android.intent.action.MAIN");
                n.a(a.a).startActivity(intent);
            }
            catch (ActivityNotFoundException activitynotfoundexception)
            {
                Intent intent1 = new Intent("android.intent.action.VIEW", Uri.parse(s1));
                intent1.setAction("android.intent.action.VIEW");
                n.a(a.a).startActivity(intent1);
                return true;
            }
            return true;
        } else
        {
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(s));
            intent2.setAction("android.intent.action.VIEW");
            n.a(a.a).startActivity(intent2);
            return true;
        }
    }

    ainer(ainer ainer)
    {
        a = ainer;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/ads/n$1

/* anonymous class */
    class n._cls1
        implements Runnable
    {

        final AdContainer a;
        final n b;

        public void run()
        {
            n.a(b, new WebView(n.a(b)));
            n.b(b).setWebViewClient(new n._cls1._cls1(this));
            n.b(b).getSettings().setJavaScriptEnabled(true);
            n.b(b).getSettings().setCacheMode(2);
            android.widget.RelativeLayout.LayoutParams layoutparams;
            if (!n.a(b, n.c(b).e()) && !n.a(b, n.c(b).f()))
            {
                layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, (int)(double)(50F * a.getDisplayInfo("density")));
            } else
            {
                int i;
                int j;
                int k;
                if (n.a(b, n.c(b).e()))
                {
                    i = n.c(b).e();
                } else
                {
                    i = 50;
                }
                j = (int)((float)i * a.getDisplayInfo("density"));
                if (n.a(b, n.c(b).f()))
                {
                    k = (int)((float)n.c(b).f() * a.getDisplayInfo("density"));
                } else
                {
                    k = -1;
                }
                layoutparams = new android.widget.RelativeLayout.LayoutParams(k, j);
            }
            n.b(b).setFocusable(false);
            n.b(b).setLayoutParams(layoutparams);
            n.b(b).setInitialScale((int)(100F * a.getDisplayInfo("density")));
            n.b(b).setVerticalScrollbarOverlay(true);
            n.b(b).loadDataWithBaseURL(null, n.c(b).n(), "text/html", "utf-8", null);
            AdViewForInstance.callWebViewResumeTimers(n.b(b), AdViewForInstance.getNeedWebViewResumeTimers());
            a.addView(n.b(b));
            n.d(b).set(true);
        }

            
            {
                b = n1;
                a = adcontainer;
                super();
            }
    }

}
