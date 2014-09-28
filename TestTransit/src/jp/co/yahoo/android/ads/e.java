// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

// Referenced classes of package jp.co.yahoo.android.ads:
//            h

public class e
{

    private static String a = "";
    private static String b;
    private static boolean c;
    private static String d;
    private static String e;
    private static String f;
    private static String g;
    private static String h;
    private static boolean i = true;
    private static String j;

    static String a()
    {
        if (!i && b != null)
        {
            return b;
        }
        if (h == null)
        {
            h = "";
        }
        if (j == null)
        {
            j = "";
        }
        b = (new StringBuilder()).append(a).append(" ").append(j).append(h).append("YJAd-ANDROID/").append("3.3.4").toString();
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("Phone's user-agent is: ").append(b).toString());
        i = false;
        return b;
    }

    public static void a(Context context)
    {
        if (!a.equals(""))
        {
            return;
        } else
        {
            WebView webview = new WebView(context);
            a = webview.getSettings().getUserAgentString();
            webview.destroy();
            return;
        }
    }

    public static void a(String s, String s1)
    {
        f = s;
        g = s1;
    }

    public static void a(boolean flag)
    {
        c = flag;
    }

    public static boolean a(String s)
    {
        c();
        if (s == null)
        {
            return true;
        }
        if (s.length() > 50)
        {
            jp.co.yahoo.android.ads.h.a(5, "Extra user-agent string is too long");
            return false;
        }
        if (!s.matches("[0-9a-zA-Z!,\\.\\+\\- _;:\\[\\]/\\(\\)]+"))
        {
            jp.co.yahoo.android.ads.h.a(5, "Extra user-agent string contains wrong characters");
            return false;
        } else
        {
            h = (new StringBuilder()).append(s).append(" ").toString();
            return true;
        }
    }

    public static String b()
    {
        return h;
    }

    public static String b(Context context)
    {
        return context.getPackageName();
    }

    public static void b(String s)
    {
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("User ID set to ").append(s).toString());
        e = s;
    }

    public static String c(Context context)
    {
        String s = i(context);
        if (s == null)
        {
            return null;
        } else
        {
            return d(s);
        }
    }

    public static void c()
    {
        h = null;
        i = true;
    }

    public static void c(String s)
    {
        d = s;
    }

    public static com.google.android.gms.ads.identifier.AdvertisingIdClient.Info d(Context context)
    {
        com.google.android.gms.ads.identifier.AdvertisingIdClient.Info info1 = AdvertisingIdClient.getAdvertisingIdInfo(context);
        com.google.android.gms.ads.identifier.AdvertisingIdClient.Info info = info1;
_L1:
        GooglePlayServicesNotAvailableException googleplayservicesnotavailableexception;
        String s;
        IOException ioexception;
        GooglePlayServicesRepairableException googleplayservicesrepairableexception;
        IllegalStateException illegalstateexception;
        if (info != null)
        {
            s = info.getId();
        } else
        {
            s = "null";
        }
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("Advertising ID is ").append(s).toString());
        return info;
        illegalstateexception;
        info = null;
          goto _L1
        googleplayservicesrepairableexception;
        info = null;
          goto _L1
        ioexception;
        info = null;
          goto _L1
        googleplayservicesnotavailableexception;
        info = null;
          goto _L1
    }

    private static String d(String s)
    {
        String s1 = jp.co.yahoo.android.ads.h.a((new StringBuilder()).append(s).append("ysmaudid").toString(), "SHA-1", "%040x");
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("Hashed ID is ").append(s1).toString());
        return s1;
    }

    public static void d()
    {
        j = "Mobile ";
        i = true;
    }

    public static String e(Context context)
    {
        return d(f(context));
    }

    public static void e()
    {
        j = "";
        i = true;
    }

    private static boolean e(String s)
    {
        return s == null;
    }

    public static String f(Context context)
    {
        String s = android.provider.Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (e(s))
        {
            jp.co.yahoo.android.ads.h.a(3, "ANDROID_ID is 9774d56d682e549c (dummy)");
            return "9774d56d682e549c";
        } else
        {
            jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("ANDROID_ID is ").append(s).toString());
            return s;
        }
    }

    public static void f()
    {
        j = "";
        i = true;
    }

    public static String g()
    {
        Locale locale = Locale.getDefault();
        if (jp.co.yahoo.android.ads.h.d(locale.getLanguage()))
        {
            return "jp";
        } else
        {
            return locale.getLanguage().toLowerCase();
        }
    }

    public static boolean g(Context context)
    {
        if (android.os.Build.VERSION.SDK_INT >= 8)
        {
            android.content.pm.ApplicationInfo applicationinfo;
            try
            {
                applicationinfo = context.getPackageManager().getApplicationInfo("com.adobe.flashplayer", 0);
            }
            catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                return false;
            }
            if (applicationinfo != null)
            {
                return true;
            }
        }
        return false;
    }

    public static String h()
    {
        String s = Locale.getDefault().getCountry();
        if (jp.co.yahoo.android.ads.h.d(s))
        {
            return "ja";
        } else
        {
            return s.toLowerCase();
        }
    }

    public static HashMap h(Context context)
    {
        HashMap hashmap = new HashMap();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getMetrics(displaymetrics);
        hashmap.put("density", Float.valueOf(displaymetrics.density));
        hashmap.put("densityDpi", Float.valueOf(displaymetrics.densityDpi));
        hashmap.put("scaledDensity", Float.valueOf(displaymetrics.scaledDensity));
        hashmap.put("widthPixels", Float.valueOf(displaymetrics.widthPixels));
        hashmap.put("heightPixels", Float.valueOf(displaymetrics.heightPixels));
        hashmap.put("xdpi", Float.valueOf(displaymetrics.xdpi));
        hashmap.put("ydpi", Float.valueOf(displaymetrics.ydpi));
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("DisplayInfo : density = ").append(displaymetrics.density).toString());
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("DisplayInfo : densityDpi = ").append(displaymetrics.densityDpi).toString());
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("DisplayInfo : scaledDensity = ").append(displaymetrics.scaledDensity).toString());
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("DisplayInfo : widthPixels = ").append(displaymetrics.widthPixels).toString());
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("DisplayInfo : heightPixels = ").append(displaymetrics.heightPixels).toString());
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("DisplayInfo : xDpi = ").append(displaymetrics.xdpi).toString());
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("DisplayInfo : yDpi = ").append(displaymetrics.ydpi).toString());
        return hashmap;
    }

    public static String i()
    {
        if (jp.co.yahoo.android.ads.h.d(e))
        {
            return e;
        } else
        {
            return e;
        }
    }

    private static String i(Context context)
    {
        com.google.android.gms.ads.identifier.AdvertisingIdClient.Info info = d(context);
        if (info != null)
        {
            jp.co.yahoo.android.ads.h.a(3, "BCookie Seed is : Advertising ID");
            return info.getId();
        } else
        {
            jp.co.yahoo.android.ads.h.a(3, "BCookie Seed is : Android ID");
            return f(context);
        }
    }

    public static boolean j()
    {
        return c;
    }

    public static String k()
    {
        return d;
    }

    public static String[] l()
    {
        String as[] = new String[2];
        as[0] = f;
        as[1] = g;
        return as;
    }

    public static void m()
    {
        f = "";
        g = "";
    }

    static 
    {
        jp.co.yahoo.android.ads.h.a(3, "YJAd SDK version is 3.3.4");
    }
}
