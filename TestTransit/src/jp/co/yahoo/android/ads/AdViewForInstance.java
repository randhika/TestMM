// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.Context;
import android.content.Intent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.RelativeLayout;

// Referenced classes of package jp.co.yahoo.android.ads:
//            i, e, h, AdViewListener

public class AdViewForInstance extends i
{

    private static CookieSyncManager n;
    private static boolean o = false;

    public AdViewForInstance(Context context, String s, String s1)
    {
        this(context, s, s1, null, false);
    }

    public AdViewForInstance(Context context, String s, String s1, String s2, boolean flag)
    {
        super(context);
        a = s;
        b = s1;
        e.a(flag);
        e.c(s2);
    }

    public AdViewForInstance(Context context, String s, String s1, boolean flag)
    {
        this(context, s, s1, null, flag);
    }

    public static void callWebViewResumeTimers(WebView webview, boolean flag)
    {
        jp/co/yahoo/android/ads/AdViewForInstance;
        JVM INSTR monitorenter ;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_11;
        }
        webview.resumeTimers();
        jp/co/yahoo/android/ads/AdViewForInstance;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean checkSearchWordLength(String s)
    {
        return s.length() <= 1000;
    }

    public static String getHashedAdvertisingId(Context context)
    {
        return e.e(context);
    }

    public static Intent getInnerBrowserIntent()
    {
        jp/co/yahoo/android/ads/AdViewForInstance;
        JVM INSTR monitorenter ;
        Intent intent = l;
        jp/co/yahoo/android/ads/AdViewForInstance;
        JVM INSTR monitorexit ;
        return intent;
        Exception exception;
        exception;
        throw exception;
    }

    public static int getMemHtmlHeight(RelativeLayout relativelayout)
    {
        return ((WebView)relativelayout.getChildAt(0)).getContentHeight();
    }

    public static boolean getNeedWebViewResumeTimers()
    {
        jp/co/yahoo/android/ads/AdViewForInstance;
        JVM INSTR monitorenter ;
        boolean flag = o;
        jp/co/yahoo/android/ads/AdViewForInstance;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public static String getUrlExtraName()
    {
        jp/co/yahoo/android/ads/AdViewForInstance;
        JVM INSTR monitorenter ;
        String s = m;
        jp/co/yahoo/android/ads/AdViewForInstance;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    private String normalizeSearchWord(String s)
    {
        return s.replaceAll("\uFF5E", "&#xFF5E;").replaceAll("\u301C", "&#x301C;");
    }

    public static void setInnerBrowserIntent(Intent intent)
    {
        jp/co/yahoo/android/ads/AdViewForInstance;
        JVM INSTR monitorenter ;
        l = intent;
        jp/co/yahoo/android/ads/AdViewForInstance;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static void setNeedWebViewResumeTimers(boolean flag)
    {
        jp/co/yahoo/android/ads/AdViewForInstance;
        JVM INSTR monitorenter ;
        o = flag;
        jp/co/yahoo/android/ads/AdViewForInstance;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static void setUrlExtraName(String s)
    {
        jp/co/yahoo/android/ads/AdViewForInstance;
        JVM INSTR monitorenter ;
        m = s;
        jp/co/yahoo/android/ads/AdViewForInstance;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static void startSyncBcookie(Context context)
    {
        String s = e.i();
        if (h.d(s))
        {
            h.a(3, "Bat sync Bcookie! Not set Bcookie");
            return;
        } else
        {
            n = CookieSyncManager.createInstance(context);
            n.startSync();
            CookieManager cookiemanager = CookieManager.getInstance();
            cookiemanager.setAcceptCookie(true);
            cookiemanager.removeExpiredCookie();
            String s1 = String.format("B=%s; expires=Tue, 02-Jun-2037 20:00:00 GMT; path=/; domain=.yahoo.co.jp", new Object[] {
                s
            });
            cookiemanager.setCookie("yahoo.co.jp", s1);
            n.sync();
            h.a(3, (new StringBuilder()).append("Sync Bcookie: ").append(s1).toString());
            h.a(3, "Sync Bcookie start");
            return;
        }
    }

    public static void stopSyncBcookie()
    {
        if (n == null)
        {
            h.a(3, "Not sync Bcookie");
            return;
        } else
        {
            h.a(3, "Sync Bcookie stop");
            n.stopSync();
            return;
        }
    }

    public AdViewForInstance clearDeviceType()
    {
        e.f();
        return this;
    }

    public void clearExtraUserAgentString()
    {
        e.c();
    }

    public AdViewForInstance extraUserAgentString(String s)
    {
        e.a(s);
        return this;
    }

    public String getBcookie()
    {
        return e.i();
    }

    public void getExtraUserAgentString()
    {
        e.b();
    }

    public AdViewForInstance pageless(boolean flag)
    {
        d = flag;
        return this;
    }

    public AdViewForInstance setAccessToken(String s)
    {
        i = s;
        return this;
    }

    public void setAdViewListener(AdViewListener adviewlistener)
    {
        f = adviewlistener;
    }

    public void setApos(String s)
    {
        b = s;
    }

    public AdViewForInstance setCrawlUrl(String s)
    {
        h = s;
        return this;
    }

    public AdViewForInstance setLocationMode(boolean flag)
    {
        g = flag;
        return this;
    }

    public AdViewForInstance setSearchWord(String s)
    {
        if (checkSearchWordLength(s))
        {
            j = normalizeSearchWord(s);
        }
        return this;
    }

    public AdViewForInstance setSearchWord(String s, String s1)
    {
        if (checkSearchWordLength(s))
        {
            j = normalizeSearchWord(s);
            k = s1;
        }
        return this;
    }

    public AdViewForInstance spaceid(String s)
    {
        c = s;
        return this;
    }

    public AdViewForInstance userAgentForSmartPhone()
    {
        e.d();
        return this;
    }

    public AdViewForInstance userAgentForTablet()
    {
        e.e();
        return this;
    }

}
