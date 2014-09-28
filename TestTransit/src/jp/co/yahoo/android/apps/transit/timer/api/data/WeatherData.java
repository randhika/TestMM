// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api.data;

import java.io.Serializable;

public class WeatherData
    implements Serializable
{

    private static final long serialVersionUID = 0x4d9ee6fabd94b64aL;
    private String areaname;
    private String icon;
    private String maxTemp;
    private String minTemp;
    private String precip;
    private String url;

    public WeatherData()
    {
    }

    public String getAreaname()
    {
        return areaname;
    }

    public String getIcon()
    {
        return icon;
    }

    public String getMaxTemp()
    {
        return maxTemp;
    }

    public String getMinTemp()
    {
        return minTemp;
    }

    public String getPrecip()
    {
        return precip;
    }

    public String getUrl()
    {
        return url;
    }

    public void setAreaname(String s)
    {
        areaname = s;
    }

    public void setIcon(String s)
    {
        icon = s;
    }

    public void setMaxTemp(String s)
    {
        maxTemp = s;
    }

    public void setMinTemp(String s)
    {
        minTemp = s;
    }

    public void setPrecip(String s)
    {
        precip = s;
    }

    public void setUrl(String s)
    {
        url = s;
    }
}
