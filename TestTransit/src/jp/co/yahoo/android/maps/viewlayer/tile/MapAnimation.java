// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer.tile;

import jp.co.yahoo.android.maps.GeoPoint;

public class MapAnimation
{

    private GeoPoint mToGeoPoint;
    private int mToZLevel;

    public MapAnimation(GeoPoint geopoint, int i)
    {
        mToGeoPoint = null;
        mToZLevel = 0;
        mToGeoPoint = geopoint;
        mToZLevel = i;
    }

    public GeoPoint getToGeoPoint()
    {
        return mToGeoPoint;
    }

    public int getToZLevel()
    {
        return mToZLevel;
    }
}
