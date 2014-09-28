// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package jp.co.yahoo.android.ads:
//            h, AdLocationService, c

private class <init>
    implements LocationListener
{

    final AdLocationService a;
    private LocationManager b;

    private boolean a(double d)
    {
        int i = Double.compare(d, 10D);
        boolean flag = false;
        if (i < 0)
        {
            flag = true;
        }
        h.a(3, (new StringBuilder()).append("[ALListener] isValidAdLocation: ").append(flag).append(" (accuracy=").append(d).append(")").toString());
        return flag;
    }

    public boolean a()
    {
label0:
        {
            if (b == null)
            {
                try
                {
                    AdLocationService.a(a).a(System.currentTimeMillis());
                    b = (LocationManager)a.getApplicationContext().getSystemService("location");
                    List list = b.getProviders(true);
                    h.a(3, (new StringBuilder()).append("[ALRequester] providers=").append(list).toString());
                    String s;
                    for (Iterator iterator = list.iterator(); iterator.hasNext(); b.requestLocationUpdates(s, 10000L, 10F, AdLocationService.a()))
                    {
                        s = (String)iterator.next();
                    }

                    break label0;
                }
                catch (Exception exception)
                {
                    h.a(6, (new StringBuilder()).append("[ALRequester] error.").append(exception.getClass().getName()).append(":").append(exception.getMessage()).toString(), exception);
                    b = null;
                }
            }
            return false;
        }
        AdLocationService.b(a);
        return true;
    }

    public boolean a(Location location)
    {
        return AdLocationService.c(a) == null || Double.compare(AdLocationService.c(a).getAccuracy(), location.getAccuracy()) > 0;
    }

    public void b()
    {
        if (b != null)
        {
            b.removeUpdates(this);
            b = null;
        }
    }

    public void onLocationChanged(Location location)
    {
        h.a(2, (new StringBuilder()).append("[ALListener] onLocationChanged: location=").append(location).toString());
        if (a(location))
        {
            AdLocationService.a(a, location);
            h.a(3, "[ALListener] location updated.");
        }
        if (a(location.getAccuracy()))
        {
            a.stopSelf();
        }
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

    private (AdLocationService adlocationservice)
    {
        a = adlocationservice;
        super();
    }

    a(AdLocationService adlocationservice, a a1)
    {
        this(adlocationservice);
    }
}
