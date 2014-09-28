// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            IYLoginServiceCallback

public static abstract class attachInterface extends Binder
    implements IYLoginServiceCallback
{
    private static class Proxy
        implements IYLoginServiceCallback
    {

        private IBinder mRemote;

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "jp.co.yahoo.android.common.login.IYLoginServiceCallback";
        }

        public void receiveMessage(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginServiceCallback");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(1, parcel, parcel1, 0);
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

        Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    private static final String DESCRIPTOR = "jp.co.yahoo.android.common.login.IYLoginServiceCallback";
    static final int TRANSACTION_receiveMessage = 1;

    public static IYLoginServiceCallback asInterface(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        android.os.IInterface iinterface = ibinder.queryLocalInterface("jp.co.yahoo.android.common.login.IYLoginServiceCallback");
        if (iinterface != null && (iinterface instanceof IYLoginServiceCallback))
        {
            return (IYLoginServiceCallback)iinterface;
        } else
        {
            return new Proxy(ibinder);
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
            parcel1.writeString("jp.co.yahoo.android.common.login.IYLoginServiceCallback");
            return true;

        case 1: // '\001'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginServiceCallback");
            receiveMessage(parcel.readString(), parcel.readString(), parcel.readString());
            parcel1.writeNoException();
            return true;
        }
    }

    public Proxy.mRemote()
    {
        attachInterface(this, "jp.co.yahoo.android.common.login.IYLoginServiceCallback");
    }
}
