// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.text.InputFilter;
import android.text.Spanned;

// Referenced classes of package jp.co.yahoo.android.common:
//            YInputFilter

public static class mHanKanaCount
    implements InputFilter
{

    private double mHanKanaCount;
    private boolean mIsImeMode;
    private int mMax;

    private double getCharCount(char c)
    {
        if (' ' <= c && c <= '~')
        {
            return 0.0D + 0.5D;
        }
        if (c >= '\uFF61' && c <= '\uFF9F')
        {
            return 0.0D + mHanKanaCount;
        } else
        {
            return 0.0D + 1.0D;
        }
    }

    public CharSequence filter(CharSequence charsequence, int i, int j, Spanned spanned, int k, int l)
    {
        boolean flag;
        int i1;
        int j1;
        double d;
        if (l - k > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mIsImeMode = flag;
        i1 = spanned.length();
        j1 = j - i;
        if (l - k > 0)
        {
            i1 -= j1;
        }
        d = 0.0D;
        for (int k1 = 0; k1 < i1; k1++)
        {
            d += getCharCount(spanned.charAt(k1));
        }

        for (int l1 = 0; l1 < j1; l1++)
        {
            d += getCharCount(charsequence.charAt(l1));
        }

        if ((double)mMax - d < 0.0D)
        {
            if (j1 == 0)
            {
                return "";
            }
            if ((int)((double)mMax - (d - (double)(l - k))) < 0)
            {
                return "";
            } else
            {
                return charsequence.subSequence(i, i + (int)((double)mMax - (d - (double)(l - k))));
            }
        } else
        {
            return null;
        }
    }

    public boolean isImeMode()
    {
        return mIsImeMode;
    }

    public (int i)
    {
        mHanKanaCount = 1.0D;
        mIsImeMode = false;
        mMax = i;
    }

    public mMax(int i, double d)
    {
        mHanKanaCount = 1.0D;
        mIsImeMode = false;
        mMax = i;
        mHanKanaCount = d;
    }
}
