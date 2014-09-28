// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

// Referenced classes of package jp.co.yahoo.android.maps:
//            MyLocationOverlay

class this._cls0
    implements LocationListener
{

    final MyLocationOverlay this$0;

    public void onLocationChanged(Location location)
    {
        MyLocationOverlay.access$0(MyLocationOverlay.this, location);
    }

    public void onProviderDisabled(String s)
    {
    }

    public void onProviderEnabled(String s)
    {
    }

    public void onStatusChanged(String s, int i, Bundle bundle)
    {
    }

    ()
    {
        this$0 = MyLocationOverlay.this;
        super();
    }
}
