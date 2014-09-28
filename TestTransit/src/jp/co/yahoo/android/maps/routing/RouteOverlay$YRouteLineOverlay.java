// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.routing;

import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.PolylineOverlay;

// Referenced classes of package jp.co.yahoo.android.maps.routing:
//            RouteOverlay

private class visible extends PolylineOverlay
{

    final RouteOverlay this$0;
    public boolean visible;
    public int z;

    public (GeoPoint ageopoint[])
    {
        this$0 = RouteOverlay.this;
        super(ageopoint);
        z = 0;
        visible = true;
    }
}
