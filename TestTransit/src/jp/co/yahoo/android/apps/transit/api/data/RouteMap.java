// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.io.Serializable;

public class RouteMap
    implements Serializable
{

    private static final long serialVersionUID = 0xa14777569b6f4756L;
    private String category;
    private String defaultPosX;
    private String defaultPosY;
    private String height;
    private String id;
    private String name;
    private String station;
    private String width;

    public RouteMap()
    {
    }

    public String getCategory()
    {
        return category;
    }

    public String getDefaultPosX()
    {
        return defaultPosX;
    }

    public String getDefaultPosY()
    {
        return defaultPosY;
    }

    public String getHeight()
    {
        return height;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getStation()
    {
        return station;
    }

    public String getWidth()
    {
        return width;
    }

    public void setCategory(String s)
    {
        category = s;
    }

    public void setDefaultPosX(String s)
    {
        defaultPosX = s;
    }

    public void setDefaultPosY(String s)
    {
        defaultPosY = s;
    }

    public void setHeight(String s)
    {
        height = s;
    }

    public void setId(String s)
    {
        id = s;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setStation(String s)
    {
        station = s;
    }

    public void setWidth(String s)
    {
        width = s;
    }
}
