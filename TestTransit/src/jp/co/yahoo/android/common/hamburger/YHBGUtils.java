// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

public class YHBGUtils
{

    private static final String TAG = "YHamburger";
    private static boolean sDebugChecked = false;
    private static boolean sDebugLog = false;
    private static boolean sDebuggable = false;
    public static String sDefaultUserAgent = null;
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public YHBGUtils()
    {
    }

    public static boolean checkDatesInRange(Date date, long l)
    {
        return checkDatesInRange(new Date(), date, l);
    }

    public static boolean checkDatesInRange(Date date, Date date1, long l)
    {
        int i = Math.abs(date.getTime() - date1.getTime()) != l;
        boolean flag = false;
        if (i <= 0)
        {
            flag = true;
        }
        return flag;
    }

    public static boolean checkPermission(Context context, String s)
    {
        boolean flag;
        if (context.checkCallingOrSelfPermission(s) == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        debug((new StringBuilder("DEBUG: \u30D1\u30FC\u30DF\u30C3\u30B7\u30E7\u30F3\u30C1\u30A7\u30C3\u30AF(")).append(s).append(")").append(") ").append(flag).toString());
        return flag;
    }

    public static boolean contains(int ai[], int i)
    {
        if (ai == null) goto _L2; else goto _L1
_L1:
        int j = 0;
_L5:
        if (j < ai.length) goto _L3; else goto _L2
_L2:
        return false;
_L3:
        if (ai[j] == i)
        {
            return true;
        }
        j++;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public static boolean contains(String as[], String s)
    {
        return contains(as, s, true);
    }

    public static boolean contains(String as[], String s, boolean flag)
    {
        if (s == null) goto _L2; else goto _L1
_L1:
        String s1;
        s1 = s;
        if (flag)
        {
            s1 = s.toLowerCase();
        }
        if (as == null) goto _L2; else goto _L3
_L3:
        int i = 0;
_L6:
        if (i < as.length) goto _L4; else goto _L2
_L2:
        return false;
_L4:
        if (as[i] != null)
        {
            String s2;
            if (flag)
            {
                s2 = as[i].toLowerCase();
            } else
            {
                s2 = as[i];
            }
            if (s1.contains(s2))
            {
                return true;
            }
        }
        i++;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static void debug(String s)
    {
        if (sDebugLog)
        {
            Log.d("YHamburger", (new StringBuilder("DEBUG: ")).append(s).toString());
        }
    }

    public static void debug(String s, String s1)
    {
        if (sDebugLog)
        {
            Log.d(s, (new StringBuilder("DEBUG: ")).append(s1).toString());
        }
    }

    public static transient void debug(String s, String s1, Object aobj[])
    {
        if (sDebugLog)
        {
            Formatter formatter = new Formatter(new StringBuilder(128));
            formatter.format(s1, aobj);
            Log.d(s, (new StringBuilder("DEBUG: ")).append(s1).toString());
            formatter.close();
        }
    }

    public static void disableDebugLog()
    {
        jp/co/yahoo/android/common/hamburger/YHBGUtils;
        JVM INSTR monitorenter ;
        sDebugLog = false;
        jp/co/yahoo/android/common/hamburger/YHBGUtils;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static void enableDebugLog()
    {
        jp/co/yahoo/android/common/hamburger/YHBGUtils;
        JVM INSTR monitorenter ;
        sDebugLog = true;
        jp/co/yahoo/android/common/hamburger/YHBGUtils;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static void enableDebugLogForce()
    {
        jp/co/yahoo/android/common/hamburger/YHBGUtils;
        JVM INSTR monitorenter ;
        sDebugLog = true;
        sDebuggable = true;
        sDebugChecked = true;
        jp/co/yahoo/android/common/hamburger/YHBGUtils;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static void error(String s)
    {
        Log.e("YHamburger", (new StringBuilder("ERROR: ")).append(s).toString());
    }

    public static void error(String s, String s1)
    {
        Log.e(s, (new StringBuilder("ERROR: ")).append(s1).toString());
    }

    public static transient void error(String s, String s1, Object aobj[])
    {
        Formatter formatter = new Formatter(new StringBuilder(128));
        formatter.format(s1, aobj);
        Log.e(s, (new StringBuilder("ERROR: ")).append(s1).toString());
        formatter.close();
    }

    public static String format(String s, Map map)
    {
        StringBuilder stringbuilder;
        Iterator iterator;
        stringbuilder = new StringBuilder(s);
        iterator = map.entrySet().iterator();
_L2:
        if (!iterator.hasNext())
        {
            return stringbuilder.toString();
        }
        java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
        String s1 = (new StringBuilder("$(")).append((String)entry.getKey()).append(")").toString();
        String s2 = (String)entry.getValue();
        do
        {
            int i = stringbuilder.indexOf(s1);
            if (i == -1)
            {
                continue;
            }
            stringbuilder.replace(i, i + s1.length(), s2);
        } while (true);
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static int generateViewId()
    {
        int i;
        int j;
        do
        {
            i = sNextGeneratedId.get();
            j = i + 1;
            if (j > 0xffffff)
            {
                j = 1;
            }
        } while (!sNextGeneratedId.compareAndSet(i, j));
        return i;
    }

    public static ApplicationInfo getApplicationInfo(Context context, String s)
    {
label0:
        {
            ApplicationInfo applicationinfo = null;
            if (s == null)
            {
                break label0;
            }
            PackageManager packagemanager = context.getPackageManager();
            try
            {
                applicationinfo = packagemanager.getApplicationInfo(s, 8192);
                if (android.os.Build.VERSION.SDK_INT < 17 || (0x800000 & applicationinfo.flags) != 0)
                {
                    break label0;
                }
                debug((new StringBuilder("Android4.2\u4EE5\u964D: \u3053\u306E\u30E6\u30FC\u30B6\u30FC\u306B\u306F ")).append(s).append(" \u306F\u30A4\u30F3\u30B9\u30C8\u30FC\u30EB\u3055\u308C\u3066\u3044\u307E\u305B\u3093").toString());
            }
            catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                debug((new StringBuilder(String.valueOf(s))).append(" \u306F\u30A4\u30F3\u30B9\u30C8\u30FC\u30EB\u3055\u308C\u3066\u3044\u307E\u305B\u3093").toString());
                return applicationinfo;
            }
            applicationinfo = null;
        }
        return applicationinfo;
    }

    public static String getBrowserUserAgent(Context context)
    {
        if (sDefaultUserAgent != null)
        {
            return sDefaultUserAgent;
        }
        if (android.os.Build.VERSION.SDK_INT >= 17)
        {
            sDefaultUserAgent = WebSettings.getDefaultUserAgent(context);
        }
        Constructor constructor;
        constructor = android/webkit/WebSettings.getDeclaredConstructor(new Class[] {
            android/content/Context, android/webkit/WebView
        });
        constructor.setAccessible(true);
        sDefaultUserAgent = ((WebSettings)constructor.newInstance(new Object[] {
            context, null
        })).getUserAgentString();
        Exception exception1;
        try
        {
            constructor.setAccessible(false);
        }
        catch (Exception exception)
        {
            sDefaultUserAgent = (new WebView(context)).getSettings().getUserAgentString();
        }
        return sDefaultUserAgent;
        exception1;
        constructor.setAccessible(false);
        throw exception1;
    }

    public static String getCarrierName(Context context, boolean flag)
    {
        String s = "other";
        TelephonyManager telephonymanager = (TelephonyManager)context.getSystemService("phone");
        if (telephonymanager != null)
        {
            s = telephonymanager.getSimOperatorName();
            String s1 = telephonymanager.getSimCountryIso();
            debug((new StringBuilder("DEBUG: Sim\u306E\u60C5\u5831 getSimOperatorName: ")).append(s).append(", getSimCountryIso: ").append(s1).toString());
            if (flag && (s1 == null || !s1.equalsIgnoreCase("jp")))
            {
                s = "other";
            }
        }
        return s;
    }

    public static void getPackageForMasterStroage(Context context)
    {
        List list = context.getPackageManager().getInstalledApplications(8192);
        if (android.os.Build.VERSION.SDK_INT >= 17)
        {
            ApplicationInfo applicationinfo = (ApplicationInfo)list.get(0);
            if ((0x800000 & applicationinfo.flags) == 0)
            {
                debug((new StringBuilder("Android4.2\u4EE5\u964D: \u3053\u306E\u30E6\u30FC\u30B6\u30FC\u306B\u306F ")).append(applicationinfo.packageName).append(" \u306F\u30A4\u30F3\u30B9\u30C8\u30FC\u30EB\u3055\u308C\u3066\u3044\u307E\u305B\u3093").toString());
            }
        }
    }

    public static String getPackageName(Context context)
    {
        ApplicationInfo applicationinfo = context.getApplicationInfo();
        String s = null;
        if (applicationinfo != null)
        {
            s = applicationinfo.packageName;
        }
        return s;
    }

    public static boolean hasEqualsString(String as[], String s)
    {
        if (as == null || s == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L5:
        if (i < as.length) goto _L3; else goto _L2
_L2:
        return false;
_L3:
        if (as[i] != null && as[i].equals(s))
        {
            return true;
        }
        i++;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public static boolean isAU(Context context)
    {
        String s = getCarrierName(context, true);
        boolean flag = false;
        if (s != null)
        {
            boolean flag1 = s.toLowerCase().contains("kddi");
            flag = false;
            if (flag1)
            {
                flag = true;
            }
        }
        return flag;
    }

    public static boolean isDebuggable(Context context)
    {
        jp/co/yahoo/android/common/hamburger/YHBGUtils;
        JVM INSTR monitorenter ;
        if (sDebugChecked) goto _L2; else goto _L1
_L1:
        sDebugChecked = true;
        boolean flag1;
        flag1 = false;
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_66;
        }
        PackageManager packagemanager = context.getPackageManager();
        ApplicationInfo applicationinfo = packagemanager.getApplicationInfo(context.getPackageName(), 0);
        flag1 = false;
        if (applicationinfo == null)
        {
            break MISSING_BLOCK_LABEL_66;
        }
        int i = applicationinfo.flags;
        int j = i & 2;
        flag1 = false;
        if (j != 0)
        {
            flag1 = true;
        }
_L4:
        sDebuggable = flag1;
        sDebugLog = flag1;
_L2:
        boolean flag = sDebuggable;
        jp/co/yahoo/android/common/hamburger/YHBGUtils;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        flag1 = false;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static boolean isDoCoMo(Context context)
    {
        String s = getCarrierName(context, true);
        boolean flag = false;
        if (s != null)
        {
            boolean flag1 = s.toLowerCase().contains("docomo");
            flag = false;
            if (flag1)
            {
                flag = true;
            }
        }
        return flag;
    }

    public static boolean isOvertime(Date date, long l)
    {
        return isOvertime(new Date(), date, l);
    }

    public static boolean isOvertime(Date date, Date date1, long l)
    {
        boolean flag = true;
        if (date != null && date1 != null && date.getTime() - date1.getTime() <= l)
        {
            flag = false;
        }
        return flag;
    }

    public static boolean isSoftbank(Context context)
    {
        boolean flag;
label0:
        {
            String s = getCarrierName(context, true);
            flag = false;
            if (s == null)
            {
                break label0;
            }
            String s1 = s.toLowerCase();
            if (!s1.contains("softbank"))
            {
                boolean flag1 = s1.contains("vodafone");
                flag = false;
                if (!flag1)
                {
                    break label0;
                }
            }
            flag = true;
        }
        return flag;
    }

    public static void logBuildInfo()
    {
        logBuildInfo("YHamburger", false);
    }

    public static void logBuildInfo(String s, boolean flag)
    {
        if (!sDebugLog)
        {
            return;
        }
        debug(s, "DEBUG: \u7AEF\u672B\u60C5\u5831");
        debug(s, (new StringBuilder("  BRAND:")).append(Build.BRAND).toString());
        debug(s, (new StringBuilder("  CPU_ABI:")).append(Build.CPU_ABI).toString());
        if (flag)
        {
            debug(s, (new StringBuilder("  DEVICE:")).append(Build.DEVICE).toString());
        }
        if (flag)
        {
            debug(s, (new StringBuilder("  DISPLAY:")).append(Build.DISPLAY).toString());
        }
        if (flag)
        {
            debug(s, (new StringBuilder("  FINGERPRINT:")).append(Build.FINGERPRINT).toString());
        }
        if (flag)
        {
            debug(s, (new StringBuilder("  HOST:")).append(Build.HOST).toString());
        }
        if (flag)
        {
            debug(s, (new StringBuilder("  ID:")).append(Build.ID).toString());
        }
        debug(s, (new StringBuilder("  MANUFACTURER:")).append(Build.MANUFACTURER).toString());
        debug(s, (new StringBuilder("  MODEL:")).append(Build.MODEL).toString());
        debug(s, (new StringBuilder("  PRODUCT:")).append(Build.PRODUCT).toString());
        if (flag)
        {
            debug(s, (new StringBuilder("  TAGS:")).append(Build.TAGS).toString());
        }
        if (flag)
        {
            debug(s, (new StringBuilder("  TIME:")).append(Build.TIME).toString());
        }
        if (flag)
        {
            debug(s, (new StringBuilder("  TYPE:")).append(Build.TYPE).toString());
        }
        if (flag)
        {
            debug(s, (new StringBuilder("  USER:")).append(Build.USER).toString());
        }
        if (flag)
        {
            debug(s, (new StringBuilder("  VERSION.CODENAME:")).append(android.os.Build.VERSION.CODENAME).toString());
        }
        if (flag)
        {
            debug(s, (new StringBuilder("  VERSION.INCREMENTAL:")).append(android.os.Build.VERSION.INCREMENTAL).toString());
        }
        debug(s, (new StringBuilder("  VERSION.RELEASE:")).append(android.os.Build.VERSION.RELEASE).toString());
        debug(s, (new StringBuilder("  VERSION.SDK_INT:")).append(android.os.Build.VERSION.SDK_INT).toString());
    }

    public static void logIntentExtra(Intent intent)
    {
        if (sDebugLog)
        {
            debug("DEBUG: Intent\n");
            if (intent != null)
            {
                String s = intent.getAction();
                debug((new StringBuilder("  Action: ")).append(s).toString());
                Bundle bundle = intent.getExtras();
                if (bundle != null)
                {
                    Set set = bundle.keySet();
                    if (set != null)
                    {
                        Iterator iterator = set.iterator();
                        while (iterator.hasNext()) 
                        {
                            String s1 = (String)iterator.next();
                            debug((new StringBuilder("  ")).append(s1).append("=").append(bundle.get(s1)).toString());
                        }
                    }
                } else
                {
                    debug("  Extras are null");
                    return;
                }
            } else
            {
                debug("Intent is null");
                return;
            }
        }
    }

    public static String[] parseCsvAndTrim(String s)
    {
        return parseCsvAndTrim(s, false);
    }

    public static String[] parseCsvAndTrim(String s, boolean flag)
    {
        String as[] = null;
        if (s == null) goto _L2; else goto _L1
_L1:
        if (!s.contains(",")) goto _L4; else goto _L3
_L3:
        int i;
        int j;
        as = s.split(",");
        i = as.length;
        j = 0;
_L6:
        if (j < i) goto _L5; else goto _L2
_L2:
        return as;
_L5:
        as[j] = as[j].trim();
        if (flag && as[j].length() > 0)
        {
            return null;
        }
        j++;
        if (true) goto _L6; else goto _L4
_L4:
        String as1[] = new String[1];
        as1[0] = s.trim();
        return as1;
    }

    public static Date parseDate(String s)
    {
        Date date = null;
        if (s != null)
        {
            Date date1;
            try
            {
                date1 = DateUtils.parseDate(s);
            }
            catch (DateParseException dateparseexception)
            {
                dateparseexception.printStackTrace();
                return null;
            }
            date = date1;
        }
        return date;
    }

    public static int[] parseIntArray(String s)
    {
        int ai[] = null;
        if (s == null) goto _L2; else goto _L1
_L1:
        if (!s.contains(",")) goto _L4; else goto _L3
_L3:
        String as[];
        int i;
        as = s.split(",");
        i = as.length;
        ai = new int[i];
        int j = 0;
          goto _L5
_L6:
        int ai1[];
        try
        {
            ai[j] = Integer.parseInt(as[j].trim());
        }
        catch (NumberFormatException numberformatexception)
        {
            return null;
        }
        j++;
        continue; /* Loop/switch isn't completed */
_L4:
        ai1 = new int[1];
        ai1[0] = Integer.parseInt(s.trim());
        return ai1;
_L5:
        if (j < i) goto _L6; else goto _L2
_L2:
        return ai;
    }

    public static void sleep(long l)
    {
        try
        {
            Thread.sleep(l);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public static boolean startActivity(Context context, Intent intent)
    {
        context.startActivity(intent);
        return true;
        ActivityNotFoundException activitynotfoundexception;
        activitynotfoundexception;
        error((new StringBuilder("startActivity ")).append(intent).append(" ").append(activitynotfoundexception).toString());
_L2:
        return false;
        Exception exception;
        exception;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static transient String substringIfStartsWith(String s, String as[])
    {
        String s1 = s;
        int i = as.length;
        int j = 0;
        do
        {
            if (j >= i)
            {
                return s1;
            }
            String s2 = as[j];
            if (s1.startsWith(s2))
            {
                s1 = s1.substring(s2.length());
            }
            j++;
        } while (true);
    }

}
