// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

// Referenced classes of package com.google.android.gms.games.internal:
//            IGamesSignInService, IGamesSignInCallbacks

public static abstract class attachInterface extends Binder
    implements IGamesSignInService
{
    private static class Proxy
        implements IGamesSignInService
    {

        private IBinder kq;

        public void a(IGamesSignInCallbacks igamessignincallbacks, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
            if (igamessignincallbacks == null)
            {
                break MISSING_BLOCK_LABEL_82;
            }
            IBinder ibinder = igamessignincallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            kq.transact(5006, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(IGamesSignInCallbacks igamessignincallbacks, String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
            if (igamessignincallbacks == null)
            {
                break MISSING_BLOCK_LABEL_89;
            }
            IBinder ibinder = igamessignincallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            kq.transact(5005, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(IGamesSignInCallbacks igamessignincallbacks, String s, String s1, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
            if (igamessignincallbacks == null)
            {
                break MISSING_BLOCK_LABEL_89;
            }
            IBinder ibinder = igamessignincallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeStringArray(as);
            kq.transact(5004, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(IGamesSignInCallbacks igamessignincallbacks, String s, String s1, String as[], String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
            if (igamessignincallbacks == null)
            {
                break MISSING_BLOCK_LABEL_96;
            }
            IBinder ibinder = igamessignincallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeStringArray(as);
            parcel.writeString(s2);
            kq.transact(5003, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
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

        public void b(IGamesSignInCallbacks igamessignincallbacks, String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
            if (igamessignincallbacks == null)
            {
                break MISSING_BLOCK_LABEL_89;
            }
            IBinder ibinder = igamessignincallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            kq.transact(5007, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(IGamesSignInCallbacks igamessignincallbacks, String s, String s1, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
            if (igamessignincallbacks == null)
            {
                break MISSING_BLOCK_LABEL_89;
            }
            IBinder ibinder = igamessignincallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeStringArray(as);
            kq.transact(5008, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String bc(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
            parcel.writeString(s);
            kq.transact(5001, parcel, parcel1, 0);
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

        public String bd(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
            parcel.writeString(s);
            kq.transact(5002, parcel, parcel1, 0);
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

        public String f(String s, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
            parcel.writeString(s);
            int i;
            i = 0;
            if (flag)
            {
                i = 1;
            }
            String s1;
            parcel.writeInt(i);
            kq.transact(5009, parcel, parcel1, 0);
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

        public void o(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
            parcel.writeString(s);
            parcel.writeString(s1);
            kq.transact(9001, parcel, parcel1, 0);
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
    }


    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException
    {
        switch (i)
        {
        default:
            return super.onTransact(i, parcel, parcel1, j);

        case 1598968902: 
            parcel1.writeString("com.google.android.gms.games.internal.IGamesSignInService");
            return true;

        case 5001: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            String s3 = bc(parcel.readString());
            parcel1.writeNoException();
            parcel1.writeString(s3);
            return true;

        case 5002: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            String s2 = bd(parcel.readString());
            parcel1.writeNoException();
            parcel1.writeString(s2);
            return true;

        case 5009: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            String s = parcel.readString();
            boolean flag;
            String s1;
            if (parcel.readInt() != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            s1 = f(s, flag);
            parcel1.writeNoException();
            parcel1.writeString(s1);
            return true;

        case 5003: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            a(b.ak(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createStringArray(), parcel.readString());
            parcel1.writeNoException();
            return true;

        case 5004: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            a(b.ak(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createStringArray());
            parcel1.writeNoException();
            return true;

        case 5005: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            a(b.ak(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readString());
            parcel1.writeNoException();
            return true;

        case 5006: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            a(b.ak(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
            parcel1.writeNoException();
            return true;

        case 5007: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            b(b.ak(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readString());
            parcel1.writeNoException();
            return true;

        case 5008: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            b(b.ak(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createStringArray());
            parcel1.writeNoException();
            return true;

        case 9001: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            o(parcel.readString(), parcel.readString());
            parcel1.writeNoException();
            return true;
        }
    }

    public Proxy()
    {
        attachInterface(this, "com.google.android.gms.games.internal.IGamesSignInService");
    }
}
