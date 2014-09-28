// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yolp.common;

import android.content.Context;

// Referenced classes of package jp.co.yahoo.android.yolp.common:
//            YolpApiBase

public class LocalSearch extends YolpApiBase
{

    public LocalSearch(Context context)
    {
        super(context);
        setUri("http://search.olp.yahooapis.jp/OpenLocalPlatform/V1/localSearch");
    }

    protected void analyze()
    {
    }

    public void setCid(String s)
    {
        setParam("cid", s);
    }

    public void setDist(int i)
    {
        setParam("dist", Integer.toString(i));
    }

    public void setDistinct(boolean flag)
    {
        setParam("distinct", Boolean.toString(flag));
    }

    public void setGroup(String s)
    {
        setParam("group", s);
    }

    public void setLat(float f)
    {
        setParam("lat", Float.toString(f));
    }

    public void setLat(String s)
    {
        setParam("lat", s);
    }

    public void setLon(float f)
    {
        setParam("lon", Float.toString(f));
    }

    public void setLon(String s)
    {
        setParam("lon", s);
    }

    public void setQuery(String s)
    {
        setParam("query", s);
    }

    public void setSort(String s)
    {
        setParam("sort", s);
    }

    public void setUid(String s)
    {
        setParam("uid", s);
    }
}
