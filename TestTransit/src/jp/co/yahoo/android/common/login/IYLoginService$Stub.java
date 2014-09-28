// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            IYLoginService, IYLoginServiceCallback

public static abstract class attachInterface extends Binder
    implements IYLoginService
{
    private static class Proxy
        implements IYLoginService
    {

        private IBinder mRemote;

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancelCredential()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            mRemote.transact(11, parcel, parcel1, 0);
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

        public void cancelLogin()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            mRemote.transact(9, parcel, parcel1, 0);
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

        public String getCredential()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getGuid()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "jp.co.yahoo.android.common.login.IYLoginService";
        }

        public String getStoredYID()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getYID()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean hasCredential()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean hasToken()
            throws RemoteException
        {
            boolean flag;
            Parcel parcel;
            Parcel parcel1;
            flag = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if (i == 0)
            {
                flag = false;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isAutoLogin()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void login(String s, String s1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            parcel.writeString(s);
            parcel.writeString(s1);
            int i;
            i = 0;
            if (flag)
            {
                i = 1;
            }
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
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

        public void logout()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            mRemote.transact(8, parcel, parcel1, 0);
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

        public void registerCallback(IYLoginServiceCallback iyloginservicecallback)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            if (iyloginservicecallback == null)
            {
                break MISSING_BLOCK_LABEL_59;
            }
            IBinder ibinder = iyloginservicecallback.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(5, parcel, parcel1, 0);
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

        public void requestCredential()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            mRemote.transact(10, parcel, parcel1, 0);
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

        public boolean requestingCredential()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setAppid(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            parcel.writeString(s);
            mRemote.transact(12, parcel, parcel1, 0);
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

        public void setAutoLogin(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            int i;
            i = 0;
            if (flag)
            {
                i = 1;
            }
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
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

        public void unregisterCallback(IYLoginServiceCallback iyloginservicecallback)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("jp.co.yahoo.android.common.login.IYLoginService");
            if (iyloginservicecallback == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = iyloginservicecallback.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(6, parcel, parcel1, 0);
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

        Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    private static final String DESCRIPTOR = "jp.co.yahoo.android.common.login.IYLoginService";
    static final int TRANSACTION_cancelCredential = 11;
    static final int TRANSACTION_cancelLogin = 9;
    static final int TRANSACTION_getCredential = 17;
    static final int TRANSACTION_getGuid = 16;
    static final int TRANSACTION_getStoredYID = 15;
    static final int TRANSACTION_getYID = 14;
    static final int TRANSACTION_hasCredential = 2;
    static final int TRANSACTION_hasToken = 1;
    static final int TRANSACTION_isAutoLogin = 4;
    static final int TRANSACTION_login = 7;
    static final int TRANSACTION_logout = 8;
    static final int TRANSACTION_registerCallback = 5;
    static final int TRANSACTION_requestCredential = 10;
    static final int TRANSACTION_requestingCredential = 3;
    static final int TRANSACTION_setAppid = 12;
    static final int TRANSACTION_setAutoLogin = 13;
    static final int TRANSACTION_unregisterCallback = 6;

    public static IYLoginService asInterface(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        android.os.IInterface iinterface = ibinder.queryLocalInterface("jp.co.yahoo.android.common.login.IYLoginService");
        if (iinterface != null && (iinterface instanceof IYLoginService))
        {
            return (IYLoginService)iinterface;
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
            parcel1.writeString("jp.co.yahoo.android.common.login.IYLoginService");
            return true;

        case 1: // '\001'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            boolean flag5 = hasToken();
            parcel1.writeNoException();
            int j1 = 0;
            if (flag5)
            {
                j1 = 1;
            }
            parcel1.writeInt(j1);
            return true;

        case 2: // '\002'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            boolean flag4 = hasCredential();
            parcel1.writeNoException();
            int i1 = 0;
            if (flag4)
            {
                i1 = 1;
            }
            parcel1.writeInt(i1);
            return true;

        case 3: // '\003'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            boolean flag3 = requestingCredential();
            parcel1.writeNoException();
            int l = 0;
            if (flag3)
            {
                l = 1;
            }
            parcel1.writeInt(l);
            return true;

        case 4: // '\004'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            boolean flag2 = isAutoLogin();
            parcel1.writeNoException();
            int k = 0;
            if (flag2)
            {
                k = 1;
            }
            parcel1.writeInt(k);
            return true;

        case 5: // '\005'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            registerCallback(ck.Stub.asInterface(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;

        case 6: // '\006'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            unregisterCallback(ck.Stub.asInterface(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;

        case 7: // '\007'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            String s4 = parcel.readString();
            String s5 = parcel.readString();
            boolean flag1;
            if (parcel.readInt() != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            login(s4, s5, flag1);
            parcel1.writeNoException();
            return true;

        case 8: // '\b'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            logout();
            parcel1.writeNoException();
            return true;

        case 9: // '\t'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            cancelLogin();
            parcel1.writeNoException();
            return true;

        case 10: // '\n'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            requestCredential();
            parcel1.writeNoException();
            return true;

        case 11: // '\013'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            cancelCredential();
            parcel1.writeNoException();
            return true;

        case 12: // '\f'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            setAppid(parcel.readString());
            parcel1.writeNoException();
            return true;

        case 13: // '\r'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            boolean flag;
            if (parcel.readInt() != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            setAutoLogin(flag);
            parcel1.writeNoException();
            return true;

        case 14: // '\016'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            String s3 = getYID();
            parcel1.writeNoException();
            parcel1.writeString(s3);
            return true;

        case 15: // '\017'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            String s2 = getStoredYID();
            parcel1.writeNoException();
            parcel1.writeString(s2);
            return true;

        case 16: // '\020'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            String s1 = getGuid();
            parcel1.writeNoException();
            parcel1.writeString(s1);
            return true;

        case 17: // '\021'
            parcel.enforceInterface("jp.co.yahoo.android.common.login.IYLoginService");
            String s = getCredential();
            parcel1.writeNoException();
            parcel1.writeString(s);
            return true;
        }
    }

    public Proxy.mRemote()
    {
        attachInterface(this, "jp.co.yahoo.android.common.login.IYLoginService");
    }
}
