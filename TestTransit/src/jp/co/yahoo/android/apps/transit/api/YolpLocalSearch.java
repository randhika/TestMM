// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import android.os.Bundle;
import java.util.HashMap;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            YolpApiBase

public class YolpLocalSearch extends YolpApiBase
{

    public YolpLocalSearch(Context context, ApiBase.ApiListener apilistener)
    {
        super(context, apilistener);
        setUri(getContext().getString(0x7f0d0039));
        setParameter("appid", getContext().getString(0x7f0d003a));
    }

    public void setCid(String s)
    {
        param.put("cid", s);
    }

    public void setDetail(String s)
    {
        param.put("detail", s);
    }

    public void setDistance(String s)
    {
        param.put("distinct", s);
    }

    public void setDistinct(String s)
    {
        param.put("distinct", s);
    }

    public void setGroup(String s)
    {
        param.put("group", s);
    }

    public void setParamCond(Bundle bundle)
    {
        if (bundle.containsKey(context.getString(0x7f0d01a3)) && bundle.containsKey(context.getString(0x7f0d01a3)))
        {
            setLat(bundle.getString(context.getString(0x7f0d01a3)));
            setLon(bundle.getString(context.getString(0x7f0d01a4)));
        } else
        if (bundle.containsKey(context.getString(0x7f0d0241)))
        {
            setQuery(bundle.getString(context.getString(0x7f0d0241)));
        }
        if (bundle.containsKey(context.getString(0x7f0d023b)))
        {
            setStart(Integer.parseInt(bundle.getString(context.getString(0x7f0d023b))));
        } else
        {
            setStart(1);
        }
        if (bundle.containsKey(context.getString(0x7f0d01e4)))
        {
            setResultCount(Integer.parseInt(bundle.getString(context.getString(0x7f0d01e4))));
        } else
        {
            setResultCount(10);
        }
        if (bundle.containsKey(context.getString(0x7f0d018e)))
        {
            setSort(bundle.getString(context.getString(0x7f0d018e)));
        } else
        {
            setSort("dist");
        }
        if (bundle.containsKey(context.getString(0x7f0d01b6)))
        {
            setDistance(bundle.getString(context.getString(0x7f0d01b6)));
        }
        if (bundle.containsKey(context.getString(0x7f0d01c4)))
        {
            setGroup(bundle.getString(context.getString(0x7f0d01c4)));
        }
    }

    public void setSort(String s)
    {
        param.put("sort", s);
    }

    public void setVI(String s)
    {
        param.put("sVI", s);
    }
}
