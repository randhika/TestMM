// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.ads;

import android.content.Context;

public final class AdSize
{

    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER = new AdSize(320, 50, "mb");
    public static final int FULL_WIDTH = -1;
    public static final AdSize IAB_BANNER = new AdSize(468, 60, "as");
    public static final AdSize IAB_LEADERBOARD = new AdSize(728, 90, "as");
    public static final AdSize IAB_MRECT = new AdSize(300, 250, "as");
    public static final AdSize IAB_WIDE_SKYSCRAPER = new AdSize(160, 600, "as");
    public static final int LANDSCAPE_AD_HEIGHT = 32;
    public static final int LARGE_AD_HEIGHT = 90;
    public static final int PORTRAIT_AD_HEIGHT = 50;
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "mb");
    private final com.google.android.gms.ads.AdSize c;

    public AdSize(int i, int j)
    {
        this(new com.google.android.gms.ads.AdSize(i, j));
    }

    private AdSize(int i, int j, String s)
    {
        this(new com.google.android.gms.ads.AdSize(i, j));
    }

    public AdSize(com.google.android.gms.ads.AdSize adsize)
    {
        c = adsize;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof AdSize))
        {
            return false;
        } else
        {
            AdSize adsize = (AdSize)obj;
            return c.equals(adsize.c);
        }
    }

    public transient AdSize findBestSize(AdSize aadsize[])
    {
        AdSize adsize = null;
        if (aadsize != null) goto _L2; else goto _L1
_L1:
        return adsize;
_L2:
        float f;
        int i;
        int j;
        int k;
        int l;
        f = 0.0F;
        i = getWidth();
        j = getHeight();
        k = aadsize.length;
        l = 0;
_L4:
        if (l >= k) goto _L1; else goto _L3
_L3:
        float f1;
        AdSize adsize2;
        AdSize adsize1 = aadsize[l];
        int i1 = adsize1.getWidth();
        int j1 = adsize1.getHeight();
        if (!isSizeAppropriate(i1, j1))
        {
            break MISSING_BLOCK_LABEL_118;
        }
        f1 = (float)(i1 * j1) / (float)(i * j);
        if (f1 > 1.0F)
        {
            f1 = 1.0F / f1;
        }
        if (f1 <= f)
        {
            break MISSING_BLOCK_LABEL_118;
        }
        adsize2 = adsize1;
_L5:
        l++;
        adsize = adsize2;
        f = f1;
          goto _L4
          goto _L1
        f1 = f;
        adsize2 = adsize;
          goto _L5
    }

    public int getHeight()
    {
        return c.getHeight();
    }

    public int getHeightInPixels(Context context)
    {
        return c.getHeightInPixels(context);
    }

    public int getWidth()
    {
        return c.getWidth();
    }

    public int getWidthInPixels(Context context)
    {
        return c.getWidthInPixels(context);
    }

    public int hashCode()
    {
        return c.hashCode();
    }

    public boolean isAutoHeight()
    {
        return c.isAutoHeight();
    }

    public boolean isCustomAdSize()
    {
        return false;
    }

    public boolean isFullWidth()
    {
        return c.isFullWidth();
    }

    public boolean isSizeAppropriate(int i, int j)
    {
        int k = getWidth();
        int l = getHeight();
        return (float)i <= 1.25F * (float)k && (float)i >= 0.8F * (float)k && (float)j <= 1.25F * (float)l && (float)j >= 0.8F * (float)l;
    }

    public String toString()
    {
        return c.toString();
    }

}
