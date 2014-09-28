// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.view.MotionEvent;

// Referenced classes of package jp.co.yahoo.android.maps:
//            MapView, PinOverlay, GeoPoint

public static interface 
{

    public abstract boolean onLongPress(MapView mapview, Object obj, PinOverlay pinoverlay, GeoPoint geopoint);

    public abstract boolean onPinchIn(MapView mapview);

    public abstract boolean onPinchOut(MapView mapview);

    public abstract boolean onTouch(MapView mapview, MotionEvent motionevent);
}
