// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.plus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;

public interface c
    extends IInterface
{
    public static abstract class a extends Binder
        implements c
    {

        public static c bl(IBinder ibinder)
        {
            if (ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
            if (iinterface != null && (iinterface instanceof c))
            {
                return (c)iinterface;
            } else
            {
                return new a(ibinder);
            }
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            d d1;
            IBinder ibinder;
            switch (i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
                d d2 = a(com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                IBinder ibinder1;
                if (d2 != null)
                {
                    ibinder1 = d2.asBinder();
                } else
                {
                    ibinder1 = null;
                }
                parcel1.writeStrongBinder(ibinder1);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
                d1 = a(com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                ibinder = null;
                break;
            }
            if (d1 != null)
            {
                ibinder = d1.asBinder();
            }
            parcel1.writeStrongBinder(ibinder);
            return true;
        }
    }

    private static class a.a
        implements c
    {

        private IBinder kq;

        public d a(d d1, int i, int j, String s, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
            if (d1 == null)
            {
                break MISSING_BLOCK_LABEL_106;
            }
            IBinder ibinder = d1.asBinder();
_L1:
            d d2;
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            parcel.writeInt(k);
            kq.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            d2 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return d2;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public d a(d d1, int i, int j, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
            if (d1 == null)
            {
                break MISSING_BLOCK_LABEL_106;
            }
            IBinder ibinder = d1.asBinder();
_L1:
            d d2;
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            parcel.writeString(s1);
            kq.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            d2 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return d2;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IBinder asBinder()
        {
            return kq;
        }

        a.a(IBinder ibinder)
        {
            kq = ibinder;
        }
    }


    public abstract d a(d d, int i, int j, String s, int k)
        throws RemoteException;

    public abstract d a(d d, int i, int j, String s, String s1)
        throws RemoteException;
}
