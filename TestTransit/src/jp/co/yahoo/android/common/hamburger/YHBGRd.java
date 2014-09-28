// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.content.Context;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YIOUtils

public class YHBGRd
{

    static String sBCookie;

    public YHBGRd()
    {
    }

    public static transient String getUrl(Context context, String as[])
    {
        return getUrl(context.getPackageName(), as);
    }

    public static transient String getUrl(String s, String as[])
    {
        String s1 = (new StringBuilder("hamburger/smartphone/app/android/")).append(s).append("/").toString();
        int i = as.length;
        int j = 0;
        do
        {
            if (j >= i)
            {
                return String.format("http://rdsig.yahoo.co.jp/%s/RV=1/RU=aHR0cDovL2kueWltZy5qcC9pL3NwYWNlLmdpZg--", new Object[] {
                    s1.substring(0, -1 + s1.length())
                });
            }
            String s2 = as[j];
            s1 = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(s1))).append(s2).toString()))).append("/").toString();
            j++;
        } while (true);
    }

    public static transient void sendAsync(Context context, String as[])
    {
        sendAsync(context.getPackageName(), as);
    }

    public static transient void sendAsync(String s, String as[])
    {
        YIOUtils.touchUrlAsync(new String[] {
            getUrl(s, as)
        });
    }

    public static transient void sendSync(Context context, String as[])
    {
        sendSync(context.getPackageName(), as);
    }

    public static transient void sendSync(String s, String as[])
    {
        YIOUtils.touchUrl(getUrl(s, as));
    }

    public static void setBCookie(String s)
    {
        sBCookie = s;
    }
}
