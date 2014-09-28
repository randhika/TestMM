// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.routing;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

// Referenced classes of package jp.co.yahoo.android.maps.routing:
//            LocationControl

class this._cls0
    implements LocationListener
{

    final LocationControl this$0;

    public void onLocationChanged(Location location)
    {
        this;
        JVM INSTR monitorenter ;
        if (location != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        LocationControl.access$8(LocationControl.this, LocationControl.access$7(LocationControl.this));
        LocationControl.access$9(LocationControl.this, location);
        LocationControl.access$10(LocationControl.this).onYLocationChanged(LocationControl.this);
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
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

    cationControlListener()
    {
        this$0 = LocationControl.this;
        super();
    }
}
