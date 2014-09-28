// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.internal.hm;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.RuntimeRemoteException;

// Referenced classes of package com.google.android.gms.maps:
//            CameraUpdate

public final class CameraUpdateFactory
{

    private static ICameraUpdateFactoryDelegate YY;

    private CameraUpdateFactory()
    {
    }

    static void a(ICameraUpdateFactoryDelegate icameraupdatefactorydelegate)
    {
        if (YY != null)
        {
            return;
        } else
        {
            YY = (ICameraUpdateFactoryDelegate)hm.f(icameraupdatefactorydelegate);
            return;
        }
    }

    private static ICameraUpdateFactoryDelegate jo()
    {
        return (ICameraUpdateFactoryDelegate)hm.b(YY, "CameraUpdateFactory is not initialized");
    }

    public static CameraUpdate newCameraPosition(CameraPosition cameraposition)
    {
        CameraUpdate cameraupdate;
        try
        {
            cameraupdate = new CameraUpdate(jo().newCameraPosition(cameraposition));
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return cameraupdate;
    }

    public static CameraUpdate newLatLng(LatLng latlng)
    {
        CameraUpdate cameraupdate;
        try
        {
            cameraupdate = new CameraUpdate(jo().newLatLng(latlng));
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return cameraupdate;
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latlngbounds, int i)
    {
        CameraUpdate cameraupdate;
        try
        {
            cameraupdate = new CameraUpdate(jo().newLatLngBounds(latlngbounds, i));
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return cameraupdate;
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latlngbounds, int i, int j, int k)
    {
        CameraUpdate cameraupdate;
        try
        {
            cameraupdate = new CameraUpdate(jo().newLatLngBoundsWithSize(latlngbounds, i, j, k));
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return cameraupdate;
    }

    public static CameraUpdate newLatLngZoom(LatLng latlng, float f)
    {
        CameraUpdate cameraupdate;
        try
        {
            cameraupdate = new CameraUpdate(jo().newLatLngZoom(latlng, f));
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return cameraupdate;
    }

    public static CameraUpdate scrollBy(float f, float f1)
    {
        CameraUpdate cameraupdate;
        try
        {
            cameraupdate = new CameraUpdate(jo().scrollBy(f, f1));
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return cameraupdate;
    }

    public static CameraUpdate zoomBy(float f)
    {
        CameraUpdate cameraupdate;
        try
        {
            cameraupdate = new CameraUpdate(jo().zoomBy(f));
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return cameraupdate;
    }

    public static CameraUpdate zoomBy(float f, Point point)
    {
        CameraUpdate cameraupdate;
        try
        {
            cameraupdate = new CameraUpdate(jo().zoomByWithFocus(f, point.x, point.y));
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return cameraupdate;
    }

    public static CameraUpdate zoomIn()
    {
        CameraUpdate cameraupdate;
        try
        {
            cameraupdate = new CameraUpdate(jo().zoomIn());
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return cameraupdate;
    }

    public static CameraUpdate zoomOut()
    {
        CameraUpdate cameraupdate;
        try
        {
            cameraupdate = new CameraUpdate(jo().zoomOut());
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return cameraupdate;
    }

    public static CameraUpdate zoomTo(float f)
    {
        CameraUpdate cameraupdate;
        try
        {
            cameraupdate = new CameraUpdate(jo().zoomTo(f));
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return cameraupdate;
    }
}
