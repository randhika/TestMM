// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface s
    extends IInterface
{
    public static abstract class a extends Binder
        implements s
    {

        public static s b(IBinder ibinder)
        {
            if (ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            if (iinterface != null && (iinterface instanceof s))
            {
                return (s)iinterface;
            } else
            {
                return new a(ibinder);
            }
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            String s1;
            int k;
            boolean flag;
            switch (i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                String s3 = getId();
                parcel1.writeNoException();
                parcel1.writeString(s3);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                boolean flag1;
                boolean flag2;
                int l;
                if (parcel.readInt() != 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                flag2 = a(flag1);
                parcel1.writeNoException();
                l = 0;
                if (flag2)
                {
                    l = 1;
                }
                parcel1.writeInt(l);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                String s2 = c(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(s2);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                s1 = parcel.readString();
                k = parcel.readInt();
                flag = false;
                break;
            }
            if (k != 0)
            {
                flag = true;
            }
            b(s1, flag);
            parcel1.writeNoException();
            return true;
        }
    }

    private static class a.a
        implements s
    {

        private IBinder kq;

        public boolean a(boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            int i;
            int j;
            if (flag)
            {
                i = ((flag1) ? 1 : 0);
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
            kq.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            j = parcel1.readInt();
            if (j == 0)
            {
                flag1 = false;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag1;
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

        public void b(String s1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            parcel.writeString(s1);
            int i;
            i = 0;
            if (flag)
            {
                i = 1;
            }
            parcel.writeInt(i);
            kq.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String c(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s2;
            parcel.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            parcel.writeString(s1);
            kq.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            s2 = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s2;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getId()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s1;
            parcel.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            kq.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            s1 = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s1;
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


    public abstract boolean a(boolean flag)
        throws RemoteException;

    public abstract void b(String s1, boolean flag)
        throws RemoteException;

    public abstract String c(String s1)
        throws RemoteException;

    public abstract String getId()
        throws RemoteException;
}
