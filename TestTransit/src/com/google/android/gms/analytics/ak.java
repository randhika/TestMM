// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class ak
{

    private static final char xE[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'A', 'B', 'C', 'D', 'E', 'F'
    };

    public static Map U(String s)
    {
        HashMap hashmap = new HashMap();
        String as[] = s.split("&");
        int i = as.length;
        int j = 0;
        while (j < i) 
        {
            String as1[] = as[j].split("=");
            if (as1.length > 1)
            {
                hashmap.put(as1[0], as1[1]);
            } else
            if (as1.length == 1 && as1[0].length() != 0)
            {
                hashmap.put(as1[0], null);
            }
            j++;
        }
        return hashmap;
    }

    public static String V(String s)
    {
        if (TextUtils.isEmpty(s))
        {
            return null;
        }
        if (s.contains("?"))
        {
            String as1[] = s.split("[\\?]");
            if (as1.length > 1)
            {
                s = as1[1];
            }
        }
        StringBuilder stringbuilder;
        if (s.contains("%3D"))
        {
            Map map;
            String as[];
            int i;
            String s1;
            try
            {
                s1 = URLDecoder.decode(s, "UTF-8");
            }
            catch (UnsupportedEncodingException unsupportedencodingexception)
            {
                return null;
            }
            s = s1;
        } else
        if (!s.contains("="))
        {
            return null;
        }
        map = U(s);
        as = (new String[] {
            "dclid", "utm_source", "gclid", "utm_campaign", "utm_medium", "utm_term", "utm_content", "utm_id", "gmob_t"
        });
        stringbuilder = new StringBuilder();
        for (i = 0; i < as.length; i++)
        {
            if (!TextUtils.isEmpty((CharSequence)map.get(as[i])))
            {
                if (stringbuilder.length() > 0)
                {
                    stringbuilder.append("&");
                }
                stringbuilder.append(as[i]).append("=").append((String)map.get(as[i]));
            }
        }

        return stringbuilder.toString();
    }

    public static MessageDigest W(String s)
    {
        int i = 0;
_L3:
        if (i >= 2) goto _L2; else goto _L1
_L1:
        MessageDigest messagedigest = MessageDigest.getInstance(s);
        if (messagedigest != null)
        {
            return messagedigest;
        }
        continue; /* Loop/switch isn't completed */
        NoSuchAlgorithmException nosuchalgorithmexception;
        nosuchalgorithmexception;
        i++;
          goto _L3
_L2:
        return null;
    }

    public static double a(String s, double d1)
    {
        if (s == null)
        {
            return d1;
        }
        double d2;
        try
        {
            d2 = Double.parseDouble(s);
        }
        catch (NumberFormatException numberformatexception)
        {
            return d1;
        }
        return d2;
    }

    static String a(Locale locale)
    {
        while (locale == null || TextUtils.isEmpty(locale.getLanguage())) 
        {
            return null;
        }
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(locale.getLanguage().toLowerCase());
        if (!TextUtils.isEmpty(locale.getCountry()))
        {
            stringbuilder.append("-").append(locale.getCountry().toLowerCase());
        }
        return stringbuilder.toString();
    }

    public static void a(Map map, String s, String s1)
    {
        if (!map.containsKey(s))
        {
            map.put(s, s1);
        }
    }

    public static boolean d(String s, boolean flag)
    {
        if (s != null)
        {
            if (s.equalsIgnoreCase("true") || s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("1"))
            {
                flag = true;
            } else
            if (s.equalsIgnoreCase("false") || s.equalsIgnoreCase("no") || s.equalsIgnoreCase("0"))
            {
                return false;
            }
        }
        return flag;
    }

    static String v(boolean flag)
    {
        if (flag)
        {
            return "1";
        } else
        {
            return "0";
        }
    }

}
