// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngCreator;
import com.google.android.gms.maps.model.VisibleRegion;
import com.google.android.gms.maps.model.VisibleRegionCreator;

// Referenced classes of package com.google.android.gms.maps.internal:
//            IProjectionDelegate

private static class kq
    implements IProjectionDelegate
{

    private IBinder kq;

    public IBinder asBinder()
    {
        return kq;
    }

    public LatLng fromScreenLocation(d d1)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
        if (d1 == null)
        {
            break MISSING_BLOCK_LABEL_88;
        }
        IBinder ibinder = d1.asBinder();
_L1:
        int i;
        parcel.writeStrongBinder(ibinder);
        kq.transact(1, parcel, parcel1, 0);
        parcel1.readException();
        i = parcel1.readInt();
        LatLng latlng;
        latlng = null;
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        LatLng latlng1 = LatLng.CREATOR.createFromParcel(parcel1);
        latlng = latlng1;
        parcel1.recycle();
        parcel.recycle();
        return latlng;
        ibinder = null;
          goto _L1
        Exception exception;
        exception;
        parcel1.recycle();
        parcel.recycle();
        throw exception;
    }

    public VisibleRegion getVisibleRegion()
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
        kq.transact(3, parcel, parcel1, 0);
        parcel1.readException();
        if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
        VisibleRegion visibleregion1 = VisibleRegion.CREATOR.createFromParcel(parcel1);
        VisibleRegion visibleregion = visibleregion1;
_L4:
        parcel1.recycle();
        parcel.recycle();
        return visibleregion;
_L2:
        visibleregion = null;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        parcel1.recycle();
        parcel.recycle();
        throw exception;
    }

    public d toScreenLocation(LatLng latlng)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
        if (latlng == null)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        parcel.writeInt(1);
        latlng.writeToParcel(parcel, 0);
_L1:
        d d1;
        kq.transact(2, parcel, parcel1, 0);
        parcel1.readException();
        d1 = com.google.android.gms.dynamic.e.a.a.kq(parcel1.readStrongBinder());
        parcel1.recycle();
        parcel.recycle();
        return d1;
        parcel.writeInt(0);
          goto _L1
        Exception exception;
        exception;
        parcel1.recycle();
        parcel.recycle();
        throw exception;
    }

    (IBinder ibinder)
    {
        kq = ibinder;
    }
}
