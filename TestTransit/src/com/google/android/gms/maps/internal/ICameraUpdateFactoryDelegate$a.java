// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CameraPositionCreator;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBoundsCreator;
import com.google.android.gms.maps.model.LatLngCreator;

// Referenced classes of package com.google.android.gms.maps.internal:
//            ICameraUpdateFactoryDelegate

public static abstract class a.kq extends Binder
    implements ICameraUpdateFactoryDelegate
{
    private static class a
        implements ICameraUpdateFactoryDelegate
    {

        private IBinder kq;

        public IBinder asBinder()
        {
            return kq;
        }

        public d newCameraPosition(CameraPosition cameraposition)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            if (cameraposition == null)
            {
                break MISSING_BLOCK_LABEL_68;
            }
            parcel.writeInt(1);
            cameraposition.writeToParcel(parcel, 0);
_L1:
            d d1;
            kq.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            d1 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
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

        public d newLatLng(LatLng latlng)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            if (latlng == null)
            {
                break MISSING_BLOCK_LABEL_68;
            }
            parcel.writeInt(1);
            latlng.writeToParcel(parcel, 0);
_L1:
            d d1;
            kq.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            d1 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
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

        public d newLatLngBounds(LatLngBounds latlngbounds, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            if (latlngbounds == null)
            {
                break MISSING_BLOCK_LABEL_78;
            }
            parcel.writeInt(1);
            latlngbounds.writeToParcel(parcel, 0);
_L1:
            d d1;
            parcel.writeInt(i);
            kq.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            d1 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
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

        public d newLatLngBoundsWithSize(LatLngBounds latlngbounds, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            if (latlngbounds == null)
            {
                break MISSING_BLOCK_LABEL_98;
            }
            parcel.writeInt(1);
            latlngbounds.writeToParcel(parcel, 0);
_L1:
            d d1;
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            kq.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            d1 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
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

        public d newLatLngZoom(LatLng latlng, float f)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            if (latlng == null)
            {
                break MISSING_BLOCK_LABEL_78;
            }
            parcel.writeInt(1);
            latlng.writeToParcel(parcel, 0);
_L1:
            d d1;
            parcel.writeFloat(f);
            kq.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            d1 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
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

        public d scrollBy(float f, float f1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            d d1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            parcel.writeFloat(f);
            parcel.writeFloat(f1);
            kq.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            d1 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return d1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public d zoomBy(float f)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            d d1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            parcel.writeFloat(f);
            kq.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            d1 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return d1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public d zoomByWithFocus(float f, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            d d1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            parcel.writeFloat(f);
            parcel.writeInt(i);
            parcel.writeInt(j);
            kq.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            d1 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return d1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public d zoomIn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            d d1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            kq.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            d1 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return d1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public d zoomOut()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            d d1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            kq.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            d1 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return d1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public d zoomTo(float f)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            d d1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            parcel.writeFloat(f);
            kq.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            d1 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return d1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        a(IBinder ibinder)
        {
            kq = ibinder;
        }
    }


    public static ICameraUpdateFactoryDelegate av(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        android.os.IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
        if (iinterface != null && (iinterface instanceof ICameraUpdateFactoryDelegate))
        {
            return (ICameraUpdateFactoryDelegate)iinterface;
        } else
        {
            return new a(ibinder);
        }
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException
    {
        switch (i)
        {
        default:
            return super.onTransact(i, parcel, parcel1, j);

        case 1598968902: 
            parcel1.writeString("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            return true;

        case 1: // '\001'
            parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            d d11 = zoomIn();
            parcel1.writeNoException();
            IBinder ibinder10 = null;
            if (d11 != null)
            {
                ibinder10 = d11.asBinder();
            }
            parcel1.writeStrongBinder(ibinder10);
            return true;

        case 2: // '\002'
            parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            d d10 = zoomOut();
            parcel1.writeNoException();
            IBinder ibinder9 = null;
            if (d10 != null)
            {
                ibinder9 = d10.asBinder();
            }
            parcel1.writeStrongBinder(ibinder9);
            return true;

        case 3: // '\003'
            parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            d d9 = scrollBy(parcel.readFloat(), parcel.readFloat());
            parcel1.writeNoException();
            IBinder ibinder8 = null;
            if (d9 != null)
            {
                ibinder8 = d9.asBinder();
            }
            parcel1.writeStrongBinder(ibinder8);
            return true;

        case 4: // '\004'
            parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            d d8 = zoomTo(parcel.readFloat());
            parcel1.writeNoException();
            IBinder ibinder7 = null;
            if (d8 != null)
            {
                ibinder7 = d8.asBinder();
            }
            parcel1.writeStrongBinder(ibinder7);
            return true;

        case 5: // '\005'
            parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            d d7 = zoomBy(parcel.readFloat());
            parcel1.writeNoException();
            IBinder ibinder6 = null;
            if (d7 != null)
            {
                ibinder6 = d7.asBinder();
            }
            parcel1.writeStrongBinder(ibinder6);
            return true;

        case 6: // '\006'
            parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            d d6 = zoomByWithFocus(parcel.readFloat(), parcel.readInt(), parcel.readInt());
            parcel1.writeNoException();
            IBinder ibinder5 = null;
            if (d6 != null)
            {
                ibinder5 = d6.asBinder();
            }
            parcel1.writeStrongBinder(ibinder5);
            return true;

        case 7: // '\007'
            parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            CameraPosition cameraposition;
            d d5;
            IBinder ibinder4;
            if (parcel.readInt() != 0)
            {
                cameraposition = CameraPosition.CREATOR.createFromParcel(parcel);
            } else
            {
                cameraposition = null;
            }
            d5 = newCameraPosition(cameraposition);
            parcel1.writeNoException();
            ibinder4 = null;
            if (d5 != null)
            {
                ibinder4 = d5.asBinder();
            }
            parcel1.writeStrongBinder(ibinder4);
            return true;

        case 8: // '\b'
            parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            LatLng latlng1;
            d d4;
            IBinder ibinder3;
            if (parcel.readInt() != 0)
            {
                latlng1 = LatLng.CREATOR.createFromParcel(parcel);
            } else
            {
                latlng1 = null;
            }
            d4 = newLatLng(latlng1);
            parcel1.writeNoException();
            ibinder3 = null;
            if (d4 != null)
            {
                ibinder3 = d4.asBinder();
            }
            parcel1.writeStrongBinder(ibinder3);
            return true;

        case 9: // '\t'
            parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            LatLng latlng;
            d d3;
            IBinder ibinder2;
            if (parcel.readInt() != 0)
            {
                latlng = LatLng.CREATOR.createFromParcel(parcel);
            } else
            {
                latlng = null;
            }
            d3 = newLatLngZoom(latlng, parcel.readFloat());
            parcel1.writeNoException();
            ibinder2 = null;
            if (d3 != null)
            {
                ibinder2 = d3.asBinder();
            }
            parcel1.writeStrongBinder(ibinder2);
            return true;

        case 10: // '\n'
            parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            LatLngBounds latlngbounds1;
            d d2;
            IBinder ibinder1;
            if (parcel.readInt() != 0)
            {
                latlngbounds1 = LatLngBounds.CREATOR.createFromParcel(parcel);
            } else
            {
                latlngbounds1 = null;
            }
            d2 = newLatLngBounds(latlngbounds1, parcel.readInt());
            parcel1.writeNoException();
            ibinder1 = null;
            if (d2 != null)
            {
                ibinder1 = d2.asBinder();
            }
            parcel1.writeStrongBinder(ibinder1);
            return true;

        case 11: // '\013'
            parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            break;
        }
        LatLngBounds latlngbounds;
        d d1;
        IBinder ibinder;
        if (parcel.readInt() != 0)
        {
            latlngbounds = LatLngBounds.CREATOR.createFromParcel(parcel);
        } else
        {
            latlngbounds = null;
        }
        d1 = newLatLngBoundsWithSize(latlngbounds, parcel.readInt(), parcel.readInt(), parcel.readInt());
        parcel1.writeNoException();
        ibinder = null;
        if (d1 != null)
        {
            ibinder = d1.asBinder();
        }
        parcel1.writeStrongBinder(ibinder);
        return true;
    }
}
