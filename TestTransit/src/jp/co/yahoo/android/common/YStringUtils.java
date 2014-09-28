// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.text.TextPaint;
import android.text.TextUtils;
import android.widget.TextView;

// Referenced classes of package jp.co.yahoo.android.common:
//            YLog

public class YStringUtils
{

    private static final String TAG = "YStringUtils";

    public YStringUtils()
    {
    }

    public static CharSequence ellipsize(int i, int j, TextPaint textpaint, String s)
    {
        android.graphics.Paint.FontMetricsInt fontmetricsint = textpaint.getFontMetricsInt();
        int k = fontmetricsint.descent - fontmetricsint.ascent;
        StringBuilder stringbuilder = new StringBuilder();
        int l = 0;
        int i1 = s.length();
        int j1 = k;
        do
        {
label0:
            {
                if (l < s.length())
                {
                    if (stringbuilder.length() > 0)
                    {
                        stringbuilder.append("\n");
                    }
                    j1 += k;
                    if (j1 <= j)
                    {
                        break label0;
                    }
                    stringbuilder.append(TextUtils.ellipsize(s.subSequence(l, i1), textpaint, i, android.text.TextUtils.TruncateAt.END));
                }
                return stringbuilder;
            }
            int k1 = textpaint.breakText(s, l, i1, true, i, null);
            int l1 = s.indexOf('\n', l);
            boolean flag;
            if (l1 != -1 && l1 < l + k1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                k1 = l1 - l;
            }
            stringbuilder.append(s, l, l + k1);
            if (flag)
            {
                k1++;
            }
            l += k1;
        } while (true);
    }

    public static CharSequence ellipsize(TextView textview, String s)
    {
        int i = textview.getWidth();
        int j = textview.getHeight();
        int k = textview.getPaddingLeft();
        int l = textview.getPaddingRight();
        int i1 = textview.getPaddingTop();
        int j1 = textview.getPaddingBottom();
        return ellipsize(i - k - l, j - i1 - j1, textview.getPaint(), s);
    }

    public static boolean equals(String s, String s1)
    {
        return TextUtils.equals(s, s1);
    }

    public static boolean isEmpty(String s)
    {
        return TextUtils.isEmpty(s);
    }

    public static void log(String s, String s1)
    {
        int i = 1 + s1.length() / 100;
        for (int j = 0; j < i; j++)
        {
            int k = 100 + j * 100;
            if (k >= s1.length())
            {
                k = s1.length();
            }
            YLog.e(s, s1.substring(j * 100, k));
        }

    }

    public static String trim(String s)
    {
        return trim(s, "\u3000 ");
    }

    public static String trim(String s, String s1)
    {
        if (s == null)
        {
            s = null;
        } else
        {
            if (s1 == null)
            {
                return s.trim();
            }
            int i = s.length();
            int j = 0;
            char ac[];
            for (ac = s.toCharArray(); j < i && s1.contains(String.valueOf(ac[j])); j++) { }
            for (; j < i && s1.contains(String.valueOf(ac[i - 1])); i--) { }
            if (j > 0 || i < s.length())
            {
                return s.substring(j, i);
            }
        }
        return s;
    }
}
