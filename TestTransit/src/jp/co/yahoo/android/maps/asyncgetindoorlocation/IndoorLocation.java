// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.asyncgetindoorlocation;


public class IndoorLocation
{

    String floorid;
    String indoorid;
    String latitude;
    String longitude;

    public IndoorLocation()
    {
        latitude = "";
        longitude = "";
        floorid = "";
        indoorid = "";
    }

    public IndoorLocation(String s, String s1, String s2, String s3)
    {
        latitude = "";
        longitude = "";
        floorid = "";
        indoorid = "";
        latitude = s;
        longitude = s1;
        floorid = s2;
        indoorid = s3;
    }

    public void copy(IndoorLocation indoorlocation)
    {
        latitude = indoorlocation.latitude;
        longitude = indoorlocation.longitude;
        floorid = indoorlocation.floorid;
        indoorid = indoorlocation.indoorid;
    }

    public String getFloorid()
    {
        return floorid;
    }

    public String getIndoorid()
    {
        return indoorid;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public String getLongitude()
    {
        return longitude;
    }
}
