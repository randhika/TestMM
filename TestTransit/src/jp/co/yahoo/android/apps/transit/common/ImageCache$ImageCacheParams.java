// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;


// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            ImageCache

public static class setMemCacheSizePercent
{

    public int compressQuality;
    public int memCacheSize;
    public boolean memoryCacheEnabled;

    public void setMemCacheSizePercent(float f)
    {
        if (f < 0.05F || f > 0.8F)
        {
            throw new IllegalArgumentException("setMemCacheSizePercent - percent must be between 0.05 and 0.8 (inclusive)");
        } else
        {
            memCacheSize = Math.round((f * (float)Runtime.getRuntime().maxMemory()) / 1024F);
            return;
        }
    }

    public I()
    {
        memCacheSize = 5120;
        compressQuality = 70;
        memoryCacheEnabled = true;
        setMemCacheSizePercent(0.1F);
    }
}
