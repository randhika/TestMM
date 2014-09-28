// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.ads:
//            k, AdContainer, a, AdViewForInstance, 
//            h, AdResponse, f

public class o
    implements k
{

    private final Context a;
    private a b;
    private JSONObject c;
    private WebView d;
    private AtomicBoolean e;

    public o(Context context, a a1, JSONObject jsonobject)
    {
        a = context;
        b = a1;
        c = jsonobject;
        c();
    }

    static Context a(o o1)
    {
        return o1.a;
    }

    static WebView a(o o1, WebView webview)
    {
        o1.d = webview;
        return webview;
    }

    private void a(WebView webview, AdContainer adcontainer)
    {
        d.setWebViewClient(new WebViewClient() {

            final o a;

            public boolean shouldOverrideUrlLoading(WebView webview1, String s)
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

            
            {
                a = o.this;
                super();
            }
        });
        d.getSettings().setJavaScriptEnabled(true);
        d.getSettings().setCacheMode(2);
        d.setFocusable(false);
        d.setInitialScale((int)(100F * adcontainer.getDisplayInfo("density")));
        d.setVerticalScrollbarOverlay(true);
        d.loadDataWithBaseURL(null, b.n(), "text/html", "utf-8", null);
        AdViewForInstance.callWebViewResumeTimers(d, AdViewForInstance.getNeedWebViewResumeTimers());
    }

    private void a(String s)
    {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(s));
        intent.setAction("android.intent.action.VIEW");
        a.startActivity(intent);
    }

    static void a(o o1, WebView webview, AdContainer adcontainer)
    {
        o1.a(webview, adcontainer);
    }

    static void a(o o1, String s)
    {
        o1.a(s);
    }

    static WebView b(o o1)
    {
        return o1.d;
    }

    static AtomicBoolean c(o o1)
    {
        return o1.e;
    }

    private void c()
    {
        d = null;
        e = new AtomicBoolean(false);
    }

    private void d()
    {
        while (!e.get()) 
        {
            try
            {
                Thread.sleep(50L);
            }
            catch (InterruptedException interruptedexception)
            {
                h.a(3, (new StringBuilder()).append("InterruptedException: ").append(interruptedexception.getMessage()).toString());
            }
        }
    }

    public void a()
    {
        try
        {
            if (c.has("adhtml"))
            {
                b.l(c.getString("adhtml"));
            }
            if (c.has("image"))
            {
                JSONObject jsonobject = c.getJSONObject("image");
                if (jsonobject.has("banner"))
                {
                    JSONObject jsonobject1 = jsonobject.getJSONObject("banner");
                    if (jsonobject1.has("height"))
                    {
                        b.a(jsonobject1.getInt("height"));
                    }
                    if (jsonobject1.has("width"))
                    {
                        b.b(jsonobject1.getInt("width"));
                    }
                }
            }
            if (c.has("csc"))
            {
                b.j(c.getString("csc"));
            }
            return;
        }
        catch (JSONException jsonexception)
        {
            h.a(6, (new StringBuilder()).append("JSONException Failed to parse im ad response:  ").append(c).toString(), jsonexception);
        }
    }

    public AdResponse b()
    {
        if ("".equals(b.n()))
        {
            return null;
        } else
        {
            AdContainer adcontainer = new AdContainer(a);
            ((Activity)a).runOnUiThread(new Runnable(adcontainer) {

                final AdContainer a;
                final o b;

                public void run()
                {
                    o.a(b, new WebView(o.a(b)));
                    o.a(b, o.b(b), a);
                    a.addView(o.b(b));
                    android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, -2);
                    o.b(b).setLayoutParams(layoutparams);
                    o.c(b).set(true);
                }

            
            {
                b = o.this;
                a = adcontainer;
                super();
            }
            });
            d();
            AdResponse adresponse = new AdResponse();
            adresponse.setAdlayout(adcontainer);
            adresponse.setCode("200");
            adresponse.setMessage("AdView Success");
            f.a(a, b.l());
            return adresponse;
        }
    }
}
