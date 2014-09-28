// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.lang.reflect.Array;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

public class YTcookieChecker
{

    private static int KEY = 0;
    private static final String TAG = jp/co/yahoo/yconnect/sso/YTcookieChecker.getSimpleName();
    private static int VALUE = 1;
    private static String domain = "yahoo.co.jp";

    public YTcookieChecker()
    {
    }

    private static boolean chkNullChar(String s)
    {
        return s != null && !s.equals("");
    }

    private static boolean chkTcookie(String as[][])
    {
        int i = as.length;
        int j = 0;
        do
        {
label0:
            {
                boolean flag = false;
                if (j < i)
                {
                    String as1[] = as[j];
                    YConnectLogger.debug(TAG, (new StringBuilder()).append("key=").append(as1[KEY].trim()).append(",value=").append(as1[VALUE].trim()).toString());
                    if (!as1[KEY].trim().equals("T") || !chkNullChar(as1[VALUE].trim()))
                    {
                        break label0;
                    }
                    flag = true;
                }
                return flag;
            }
            j++;
        } while (true);
    }

    public static boolean chkYTcookie()
    {
        String s = getCookie(domain);
        YConnectLogger.debug(TAG, (new StringBuilder()).append("cookies=").append(s).toString());
        if (chkNullChar(s))
        {
            String as[][] = getCookieArr(s);
            if (chkTcookie(as) && chkYcookie(as))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean chkYTcookie(Context context)
    {
        CookieSyncManager.createInstance(context);
        CookieSyncManager.getInstance().sync();
        return chkYTcookie();
    }

    private static boolean chkYcookie(String as[][])
    {
        int i = as.length;
        int j = 0;
        do
        {
label0:
            {
                boolean flag = false;
                if (j < i)
                {
                    String as1[] = as[j];
                    YConnectLogger.debug(TAG, (new StringBuilder()).append("key=").append(as1[KEY].trim()).append(",value=").append(as1[VALUE].trim()).toString());
                    if (!as1[KEY].trim().equals("Y") || !chkNullChar(as1[VALUE].trim()))
                    {
                        break label0;
                    }
                    flag = true;
                }
                return flag;
            }
            j++;
        } while (true);
    }

    private static String getCookie(String s)
    {
        return CookieManager.getInstance().getCookie(s);
    }

    private static String[][] getCookieArr(String s)
    {
        String as[] = s.split(";");
        String as1[][] = (String[][])Array.newInstance(java/lang/String, new int[] {
            as.length, 2
        });
        int i = 0;
        int j = as.length;
        for (int k = 0; k < j; k++)
        {
            String s1 = as[k];
            as1[i][KEY] = s1.split("=")[0];
            as1[i][VALUE] = s1.split("=")[1];
            i++;
        }

        return as1;
    }

}
