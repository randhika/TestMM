// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.routing;

import jp.co.yahoo.android.maps.GeoPoint;

public interface YRouteNodeInfo
{

    public abstract String getAttributeKey();

    public abstract int getBackHierarchy();

    public abstract int getCrossKind();

    public abstract GeoPoint getGeoPoint();

    public abstract String getGuideInfo();

    public abstract int getHierarchy();

    public abstract double getLinkDistance();

    public abstract int getNextHierarchy();

    public abstract int getNodeType();

    public abstract int getPassType();
}
