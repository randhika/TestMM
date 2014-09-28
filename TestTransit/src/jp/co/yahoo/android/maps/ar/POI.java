// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.ar;

import android.graphics.drawable.Drawable;

public class POI
{

    private int hotX;
    private int hotY;
    private Drawable icon;
    private double lat;
    private double lon;
    private double x;
    private double y;

    public POI(double d, double d1, Drawable drawable, int i, int j)
    {
        lat = d;
        lon = d1;
        icon = drawable;
        hotX = i;
        hotY = j;
    }

    public int getHotX()
    {
        return hotX;
    }

    public int getHotY()
    {
        return hotY;
    }

    public Drawable getIcon()
    {
        return icon;
    }

    public double getLat()
    {
        return lat;
    }

    public double getLogX()
    {
        return x;
    }

    public double getLogY()
    {
        return y;
    }

    public double getLon()
    {
        return lon;
    }

    public void setLogPoint(double d, double d1)
    {
        x = d;
        y = d1;
    }
}
