// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

// Referenced classes of package com.google.android.gms.internal:
//            ds, du, dt, dv

public interface dw
    extends IInterface
{
    public static abstract class a extends Binder
        implements dw
    {

        public static dw y(IBinder ibinder)
        {
            if (ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
            if (iinterface != null && (iinterface instanceof dw))
            {
                return (dw)iinterface;
            } else
            {
                return new a(ibinder);
            }
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            switch (i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.ads.internal.request.IAdRequestService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
                break;
            }
            ds ds1;
            du du1;
            if (parcel.readInt() != 0)
            {
                ds1 = ds.CREATOR.h(parcel);
            } else
            {
                ds1 = null;
            }
            du1 = b(ds1);
            parcel1.writeNoException();
            if (du1 != null)
            {
                parcel1.writeInt(1);
                du1.writeToParcel(parcel1, 1);
            } else
            {
                parcel1.writeInt(0);
            }
            return true;
        }

        public a()
        {
            attachInterface(this, "com.google.android.gms.ads.internal.request.IAdRequestService");
        }
    }

    private static class a.a
        implements dw
    {

        private IBinder kq;

        public IBinder asBinder()
        {
            return kq;
        }

        public du b(ds ds1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.ads.internal.request.IAdRequestService");
            if (ds1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            ds1.writeToParcel(parcel, 0);
_L3:
            du du2;
            kq.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0)
            {
                break MISSING_BLOCK_LABEL_99;
            }
            du2 = du.CREATOR.i(parcel1);
            du du1 = du2;
_L4:
            parcel1.recycle();
            parcel.recycle();
            return du1;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            du1 = null;
              goto _L4
        }

        a.a(IBinder ibinder)
        {
            kq = ibinder;
        }
    }


    public abstract du b(ds ds)
        throws RemoteException;
}
