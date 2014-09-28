// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.graphics.Bitmap;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YHBGRecommendApps

public static class 
{

    public Bitmap mBitmap;
    public String mExcludePackages;
    public String mImageUrl;
    public String mMinOs;
    public String mName;
    public String mPackage;
    public String mUrl;

    public int getMinOsAsInt()
    {
        String s;
        int i;
        int j;
        try
        {
            s = mMinOs;
        }
        catch (NumberFormatException numberformatexception)
        {
            return 0;
        }
        i = 0;
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        j = Integer.parseInt(mMinOs);
        i = j;
        return i;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append((new StringBuilder("Name = ")).append(mName).append("\n").toString());
        stringbuilder.append((new StringBuilder("Url = ")).append(mUrl).append("\n").toString());
        stringbuilder.append((new StringBuilder("ImageUrl = ")).append(mImageUrl).append("\n").toString());
        stringbuilder.append((new StringBuilder("Package = ")).append(mPackage).append("\n").toString());
        stringbuilder.append((new StringBuilder("ExcludePackages = ")).append(mExcludePackages).append("\n").toString());
        stringbuilder.append((new StringBuilder("MinOs = ")).append(mMinOs).append("\n").toString());
        stringbuilder.append((new StringBuilder("Bitmap = ")).append(mBitmap).append("\n").toString());
        return stringbuilder.toString();
    }

    public ()
    {
    }
}
