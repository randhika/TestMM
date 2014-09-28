// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;


public class GeoPoint
{

    private int m_latitudeE6;
    private int m_longitudeE6;

    public GeoPoint()
    {
        this(0, 0);
    }

    public GeoPoint(int i, int j)
    {
        m_latitudeE6 = i;
        m_longitudeE6 = j;
    }

    public GeoPoint(GeoPoint geopoint)
    {
        this(geopoint.m_latitudeE6, geopoint.m_longitudeE6);
    }

    public GeoPoint(int ai[])
    {
        m_latitudeE6 = ai[0];
        m_longitudeE6 = ai[1];
    }

    public boolean equals(Object obj)
    {
        return false;
    }

    public double getLatitude()
    {
        return (double)m_latitudeE6 / 1000000D;
    }

    public int getLatitudeE6()
    {
        return m_latitudeE6;
    }

    public double getLongitude()
    {
        return (double)m_longitudeE6 / 1000000D;
    }

    public int getLongitudeE6()
    {
        return m_longitudeE6;
    }

    public int hashCode()
    {
        return 0;
    }

    public void setLatitudeE6(int i)
    {
        m_latitudeE6 = i;
    }

    public void setLongitudeE6(int i)
    {
        m_longitudeE6 = i;
    }

    public String toString()
    {
        return (new StringBuilder("GeoPoint(")).append(m_latitudeE6).append(",").append(m_longitudeE6).append(")").toString();
    }
}
