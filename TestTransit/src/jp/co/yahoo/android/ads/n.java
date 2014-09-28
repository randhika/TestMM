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
//            k, a, h, AdResponse, 
//            AdContainer, AdViewForInstance, AdSdkBrowserActivity

public class n
    implements k
{

    private final Context a;
    private a b;
    private JSONObject c;
    private WebView d;
    private AtomicBoolean e;

    public n(Context context, a a1, JSONObject jsonobject)
    {
        a = context;
        b = a1;
        c = jsonobject;
        c();
    }

    static Context a(n n1)
    {
        return n1.a;
    }

    static WebView a(n n1, WebView webview)
    {
        n1.d = webview;
        return webview;
    }

    private boolean a(int i)
    {
        return i != 0;
    }

    static boolean a(n n1, int i)
    {
        return n1.a(i);
    }

    static WebView b(n n1)
    {
        return n1.d;
    }

    static a c(n n1)
    {
        return n1.b;
    }

    private void c()
    {
        d = null;
        e = new AtomicBoolean(false);
    }

    static AtomicBoolean d(n n1)
    {
        return n1.e;
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
        }
        AdResponse adresponse = new AdResponse();
        AdContainer adcontainer = new AdContainer(a);
        ((Activity)a).runOnUiThread(new Runnable(adcontainer) {

            final AdContainer a;
            final n b;

            public void run()
            {
                n.a(b, new WebView(n.a(b)));
                n.b(b).setWebViewClient(new WebViewClient(this) {

                    final _cls1 a;

                    public boolean shouldOverrideUrlLoading(WebView webview, String s)
                    {
                        if (s.matches("^ydninfos://.*$") || s.matches("^ydninfo://.*$"))
                        {
                            String s1 = s.replace("ydninfos://", "https://").replace("ydninfo://", "http://");
                            try
                            {
                                Intent intent = new Intent(n.a(a.b), jp/co/yahoo/android/ads/AdSdkBrowserActivity);
                                intent.putExtra("YJADSDK_URL", s1);
                                intent.putExtra("YJADSDK_ADRATIO", a.a.getAdRatio());
                                intent.setAction("android.intent.action.MAIN");
                                n.a(a.b).startActivity(intent);
                            }
                            catch (ActivityNotFoundException activitynotfoundexception)
                            {
                                Intent intent1 = new Intent("android.intent.action.VIEW", Uri.parse(s1));
                                intent1.setAction("android.intent.action.VIEW");
                                n.a(a.b).startActivity(intent1);
                                return true;
                            }
                            return true;
                        } else
                        {
                            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(s));
                            intent2.setAction("android.intent.action.VIEW");
                            n.a(a.b).startActivity(intent2);
                            return true;
                        }
                    }

            
            {
                a = _pcls1;
                super();
            }
                });
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
                    int l;
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
                        l = (int)((float)n.c(b).f() * a.getDisplayInfo("density"));
                    } else
                    {
                        l = -1;
                    }
                    layoutparams = new android.widget.RelativeLayout.LayoutParams(l, j);
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
                b = n.this;
                a = adcontainer;
                super();
            }
        });
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
        adresponse.setAdlayout(adcontainer);
        adresponse.setCode("200");
        adresponse.setMessage("AdView Success");
        return adresponse;
    }
}
