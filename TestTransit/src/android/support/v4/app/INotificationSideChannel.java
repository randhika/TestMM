// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface INotificationSideChannel
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INotificationSideChannel
    {

        private static final String DESCRIPTOR = "android.support.v4.app.INotificationSideChannel";
        static final int TRANSACTION_cancel = 2;
        static final int TRANSACTION_cancelAll = 3;
        static final int TRANSACTION_notify = 1;

        public static INotificationSideChannel asInterface(IBinder ibinder)
        {
            if (ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("android.support.v4.app.INotificationSideChannel");
            if (iinterface != null && (iinterface instanceof INotificationSideChannel))
            {
                return (INotificationSideChannel)iinterface;
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
                parcel1.writeString("android.support.v4.app.INotificationSideChannel");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.support.v4.app.INotificationSideChannel");
                String s = parcel.readString();
                int k = parcel.readInt();
                String s1 = parcel.readString();
                Notification notification;
                if (parcel.readInt() != 0)
                {
                    notification = (Notification)Notification.CREATOR.createFromParcel(parcel);
                } else
                {
                    notification = null;
                }
                notify(s, k, s1, notification);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.support.v4.app.INotificationSideChannel");
                cancel(parcel.readString(), parcel.readInt(), parcel.readString());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.support.v4.app.INotificationSideChannel");
                cancelAll(parcel.readString());
                return true;
            }
        }

        public Stub()
        {
            attachInterface(this, "android.support.v4.app.INotificationSideChannel");
        }
    }

    private static class Stub.Proxy
        implements INotificationSideChannel
    {

        private IBinder mRemote;

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancel(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void cancelAll(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
            parcel.writeString(s);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.support.v4.app.INotificationSideChannel";
        }

        public void notify(String s, int i, String s1, Notification notification)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            if (notification == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            parcel.writeInt(1);
            notification.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void cancel(String s, int i, String s1)
        throws RemoteException;

    public abstract void cancelAll(String s)
        throws RemoteException;

    public abstract void notify(String s, int i, String s1, Notification notification)
        throws RemoteException;
}
