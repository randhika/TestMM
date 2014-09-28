// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer;


public class MapYmlLayer
{

    private int mCode;
    private String mCopyright;
    private int mScale;

    public MapYmlLayer(int i, String s, int j)
    {
        mCode = 0;
        mCopyright = null;
        mCode = i;
        mCopyright = s;
        mScale = j;
    }

    public int getCode()
    {
        return mCode;
    }

    public String getCopyright()
    {
        return mCopyright;
    }

    public int getScale()
    {
        return mScale;
    }
}
