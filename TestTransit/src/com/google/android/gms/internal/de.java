// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;

public interface de
    extends IInterface
{
    public static abstract class a extends Binder
        implements de
    {

        public static de s(IBinder ibinder)
        {
            if (ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
            if (iinterface != null && (iinterface instanceof de))
            {
                return (de)iinterface;
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
                parcel1.writeString("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
                IBinder ibinder = b(com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeStrongBinder(ibinder);
                return true;
            }
        }
    }

    private static class a.a
        implements de
    {

        private IBinder kq;

        public IBinder asBinder()
        {
            return kq;
        }

        public IBinder b(d d1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
            if (d1 == null)
            {
                break MISSING_BLOCK_LABEL_67;
            }
            IBinder ibinder = d1.asBinder();
_L1:
            IBinder ibinder1;
            parcel.writeStrongBinder(ibinder);
            kq.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            ibinder1 = parcel1.readStrongBinder();
            parcel1.recycle();
            parcel.recycle();
            return ibinder1;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        a.a(IBinder ibinder)
        {
            kq = ibinder;
        }
    }


    public abstract IBinder b(d d)
        throws RemoteException;
}
