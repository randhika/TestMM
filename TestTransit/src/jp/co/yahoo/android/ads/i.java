// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.ads:
//            h, e, AdResponse, AdViewListener, 
//            AdLocationService, g, f, a, 
//            n, p, l, k, 
//            m, q, o, CreateAdLayoutDisplay

public class i extends RelativeLayout
{
    private class a
        implements Runnable
    {

        final i a;

        public void run()
        {
            jp.co.yahoo.android.ads.h.a(3, "Create AdView start.");
            a.e = new HashMap();
            String s;
            try
            {
                s = jp.co.yahoo.android.ads.f.a(a.getContext(), a.a, a.b, a.c, a.g, a.h, a.i, a.j, a.k);
            }
            catch (g g1)
            {
                try
                {
                    AdResponse adresponse1 = new AdResponse();
                    adresponse1.setCode("601");
                    adresponse1.setMessage("Connection Error");
                    a.e.put(a.b, adresponse1);
                    a.endAdViewListener();
                    return;
                }
                catch (JSONException jsonexception)
                {
                    jp.co.yahoo.android.ads.h.a(5, "Unhandled exception requesting a fresh ad.", jsonexception);
                }
                AdResponse adresponse = new AdResponse();
                adresponse.setCode("500");
                adresponse.setMessage("Adview System Error");
                a.e.put(a.b, adresponse);
                a.endAdViewListener();
                return;
            }
            if (jp.co.yahoo.android.ads.h.d(s))
            {
                AdResponse adresponse2 = new AdResponse();
                adresponse2.setCode("502");
                adresponse2.setMessage("Internal Server Error");
                a.e.put(a.b, adresponse2);
                a.endAdViewListener();
                return;
            }
            JSONObject jsonobject;
            jsonobject = new JSONObject(s);
            if ("error".equals(jsonobject.getString("response")))
            {
                AdResponse adresponse3 = new AdResponse();
                adresponse3.setCode("500");
                adresponse3.setMessage(jsonobject.getString("message"));
                a.e.put(a.b, adresponse3);
                a.endAdViewListener();
                return;
            }
            JSONArray jsonarray = jsonobject.getJSONArray("ads");
            int i1 = 0;
_L5:
            jp.co.yahoo.android.ads.a a1;
            JSONObject jsonobject1;
            boolean flag;
            if (i1 >= jsonarray.length())
            {
                break MISSING_BLOCK_LABEL_763;
            }
            a1 = new jp.co.yahoo.android.ads.a();
            jsonobject1 = jsonarray.getJSONObject(i1);
            a1.a(jsonobject1.getString("apos"));
            a1.c(jsonobject1.getString("status"));
            a1.b(jsonobject1.getString("position"));
            flag = "isad".equals(a1.c());
            Object obj = null;
            if (!flag) goto _L2; else goto _L1
_L1:
            if (!"ZZZ_IMSDK".equals(a1.b())) goto _L4; else goto _L3
_L3:
            obj = new n(a.getContext(), a1, jsonobject1);
_L2:
            AdResponse adresponse4;
            if ("noad".equals(a1.c()))
            {
                obj = new p(a.getContext(), a1, jsonobject1);
            }
            if ("error".equals(a1.c()))
            {
                obj = new l();
            }
            ((k) (obj)).a();
            adresponse4 = ((k) (obj)).b();
            if (adresponse4 != null)
            {
                break MISSING_BLOCK_LABEL_573;
            }
            adresponse4 = new AdResponse();
            adresponse4.setCode("701");
            adresponse4.setMessage("Ad Url Loading Error");
            a.e.put(a1.a(), adresponse4);
            i1++;
              goto _L5
_L4:
label0:
            {
                if (!"Z".equals(a1.b()))
                {
                    break label0;
                }
                obj = new p(a.getContext(), a1, jsonobject1);
            }
              goto _L2
label1:
            {
                if (!"SITS".equals(a1.b()))
                {
                    break label1;
                }
                obj = new m(a.getContext(), a1, jsonobject1);
            }
              goto _L2
label2:
            {
                if (!jsonobject1.has("xml"))
                {
                    break label2;
                }
                obj = new q(a.getContext(), a1, jsonobject1);
            }
              goto _L2
label3:
            {
                if (!a1.b().matches("^MEM[2-5]?$"))
                {
                    break label3;
                }
                obj = new o(a.getContext(), a1, jsonobject1);
            }
              goto _L2
            obj = new CreateAdLayoutDisplay(a.getContext(), a1, jsonobject1);
              goto _L2
            a.endAdViewListener();
            return;
              goto _L5
        }

        private a()
        {
            a = i.this;
            super();
        }

    }


    protected static Intent l = null;
    protected static String m = null;
    private static long n;
    private static boolean o = true;
    private static Handler p;
    protected String a;
    protected String b;
    protected String c;
    protected boolean d;
    protected Map e;
    protected AdViewListener f;
    protected boolean g;
    protected String h;
    protected String i;
    protected String j;
    protected String k;

    protected i(Context context)
    {
        super(context);
        g = false;
        i = null;
        j = null;
        k = null;
    }

    protected i(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        g = false;
        i = null;
        j = null;
        k = null;
    }

    protected i(Context context, AttributeSet attributeset, int i1)
    {
        super(context, attributeset, i1);
        g = false;
        i = null;
        j = null;
        k = null;
    }

    private boolean checkAdRequest()
    {
        boolean flag = true;
        if (jp.co.yahoo.android.ads.h.d(a) || jp.co.yahoo.android.ads.h.d(b))
        {
            jp.co.yahoo.android.ads.h.a(5, "Not set appli_id or apos.");
            flag = false;
        } else
        if (d)
        {
            long l1 = System.currentTimeMillis();
            if (l1 - n < 5000L)
            {
                jp.co.yahoo.android.ads.h.a(5, (new StringBuilder()).append("Not pageless Ad refresh. (").append(l1 - n).append("ms)").toString());
                return false;
            } else
            {
                n = System.currentTimeMillis();
                jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append(" AdView request time: ").append(n).toString());
                return flag;
            }
        }
        return flag;
    }

    private void createWebViewUA(Context context)
    {
        jp.co.yahoo.android.ads.e.a(getContext());
    }

    private void endAdViewListener()
    {
        if (f == null)
        {
            return;
        } else
        {
            jp.co.yahoo.android.ads.h.a(3, "call Adview activity end listener.");
            p.post(new Runnable() {

                final i a;

                public void run()
                {
                    a.f.onAdViewActivityEnd(a.e);
                }

            
            {
                a = i.this;
                super();
            }
            });
            return;
        }
    }

    public static boolean getNeedWebViewDestroy()
    {
        jp/co/yahoo/android/ads/i;
        JVM INSTR monitorenter ;
        boolean flag = o;
        jp/co/yahoo/android/ads/i;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public static void setNeedWebViewDestroy(boolean flag)
    {
        jp/co/yahoo/android/ads/i;
        JVM INSTR monitorenter ;
        o = flag;
        jp/co/yahoo/android/ads/i;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void showLogTestMode()
    {
        if (jp.co.yahoo.android.ads.e.j())
        {
            jp.co.yahoo.android.ads.h.a(5, "################################################################\n#             Hello Yahoo!Japan Android Ads SDK.\n#  Don't release an application !!\n#  Please change testmode invalid in release.\n#  - AdViewForXML\n#    XML Layout change app:testing=\"false\" or delete.\n#  - AdViewForInstance\n#    change testing false.\n#        new AdViewForInstance(Context, appli_id, apos, testing)\n#    delete testing param.\n#        new AdViewForInstance(Context, appli_id, apos)\n################################################################\n");
        }
    }

    public void claenAdLayout()
    {
        this;
        JVM INSTR monitorenter ;
        cleanAdLayout();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void cleanAdLayout()
    {
        this;
        JVM INSTR monitorenter ;
        Map map = e;
        if (map != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Iterator iterator = e.entrySet().iterator();
_L4:
        RelativeLayout relativelayout;
        int i1;
        do
        {
            if (!iterator.hasNext())
            {
                continue; /* Loop/switch isn't completed */
            }
            relativelayout = ((AdResponse)((java.util.Map.Entry)iterator.next()).getValue()).getAdlayout();
        } while (relativelayout == null);
        i1 = 0;
_L6:
        if (i1 >= relativelayout.getChildCount()) goto _L4; else goto _L3
_L3:
        android.view.View view = relativelayout.getChildAt(i1);
        if (!(view instanceof WebView) || view == null)
        {
            break MISSING_BLOCK_LABEL_141;
        }
        jp.co.yahoo.android.ads.h.a(3, "  # WEBVIEW: Started destory");
        WebView webview = (WebView)view;
        webview.stopLoading();
        webview.setWebChromeClient(null);
        webview.setWebViewClient(null);
        webview.destroy();
        jp.co.yahoo.android.ads.h.a(3, "  # WEBVIEW: Finished destroy");
        i1++;
        if (true) goto _L6; else goto _L5
_L5:
        if (true) goto _L4; else goto _L7
_L7:
        if (true) goto _L1; else goto _L8
_L8:
        Exception exception;
        exception;
        throw exception;
    }

    public long getAppStopTime()
    {
        long l1 = getContext().getSharedPreferences("yjadviewpref", 0).getLong("appStopTime", -1L);
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("Get appStopTime : ").append(l1).toString());
        return l1;
    }

    public String getUserAgent()
    {
        return jp.co.yahoo.android.ads.e.a();
    }

    protected void onMeasure(int i1, int j1)
    {
        super.onMeasure(i1, j1);
        int k1 = getMeasuredWidth();
        int l1 = getMeasuredHeight();
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("Call onMeasure : ").append(i1).append(" , ").append(j1).toString());
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("Call onMeasure : ").append(k1).append(" , ").append(l1).toString());
        setMeasuredDimension(k1, l1);
    }

    public void requestFreshAd()
    {
        this;
        JVM INSTR monitorenter ;
        if (getNeedWebViewDestroy())
        {
            cleanAdLayout();
        }
        showLogTestMode();
        if (f != null)
        {
            f.onAdViewActivityStart();
        }
        e = new HashMap();
        if (p == null)
        {
            p = new Handler();
        }
        createWebViewUA(getContext());
        if (checkAdRequest()) goto _L2; else goto _L1
_L1:
        AdResponse adresponse = new AdResponse();
        adresponse.setCode("400");
        adresponse.setMessage("Bad request Adview");
        e.put(b, adresponse);
        endAdViewListener();
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        (new Thread(new a())).start();
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void setAppStopTime()
    {
        android.content.SharedPreferences.Editor editor = getContext().getSharedPreferences("yjadviewpref", 0).edit();
        long l1 = System.currentTimeMillis();
        editor.putLong("appStopTime", l1);
        editor.commit();
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("Set appStopTime : ").append(l1).toString());
    }

    public void stopLocationService()
    {
        try
        {
            Activity activity = (Activity)getContext();
            activity.stopService(new Intent(activity, jp/co/yahoo/android/ads/AdLocationService));
            return;
        }
        catch (Exception exception)
        {
            jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("stopLocationService: ").append(exception.getClass().getName()).append(": ").append(exception.getMessage()).toString());
        }
    }


}
