// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.plus.internal;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.hg;
import com.google.android.gms.internal.hr;
import com.google.android.gms.internal.hs;
import com.google.android.gms.internal.ie;
import com.google.android.gms.internal.if;
import java.util.List;

// Referenced classes of package com.google.android.gms.plus.internal:
//            b

public interface d
    extends IInterface
{
    public static abstract class a extends Binder
        implements d
    {

        public static d bm(IBinder ibinder)
        {
            if (ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.plus.internal.IPlusService");
            if (iinterface != null && (iinterface instanceof d))
            {
                return (d)iinterface;
            } else
            {
                return new a(ibinder);
            }
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            String s;
            switch (i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.plus.internal.IPlusService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                a(com.google.android.gms.plus.internal.b.a.bk(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                a(com.google.android.gms.plus.internal.b.a.bk(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                b(com.google.android.gms.plus.internal.b.a.bk(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                ie ie2;
                if (parcel.readInt() != 0)
                {
                    ie2 = ie.CREATOR.L(parcel);
                } else
                {
                    ie2 = null;
                }
                a(ie2);
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                String s4 = getAccountName();
                parcel1.writeNoException();
                parcel1.writeString(s4);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                clearDefaultAccount();
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                a(com.google.android.gms.plus.internal.b.a.bk(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                b b3 = com.google.android.gms.plus.internal.b.a.bk(parcel.readStrongBinder());
                Uri uri1;
                Bundle bundle;
                if (parcel.readInt() != 0)
                {
                    uri1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                } else
                {
                    uri1 = null;
                }
                if (parcel.readInt() != 0)
                {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                } else
                {
                    bundle = null;
                }
                a(b3, uri1, bundle);
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                b b2 = com.google.android.gms.plus.internal.b.a.bk(parcel.readStrongBinder());
                int j1 = parcel.readInt();
                String s3 = parcel.readString();
                Uri uri;
                if (parcel.readInt() != 0)
                {
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                } else
                {
                    uri = null;
                }
                a(b2, j1, s3, uri, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                hg hg1 = a(com.google.android.gms.plus.internal.b.a.bk(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                IBinder ibinder = null;
                if (hg1 != null)
                {
                    ibinder = hg1.asBinder();
                }
                parcel1.writeStrongBinder(ibinder);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                removeMoment(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                c(com.google.android.gms.plus.internal.b.a.bk(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                b(com.google.android.gms.plus.internal.b.a.bk(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                a(com.google.android.gms.plus.internal.b.a.bk(parcel.readStrongBinder()), parcel.createStringArrayList());
                parcel1.writeNoException();
                return true;

            case 40: // '('
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                d(com.google.android.gms.plus.internal.b.a.bk(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 41: // ')'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                String s2 = jU();
                parcel1.writeNoException();
                parcel1.writeString(s2);
                return true;

            case 42: // '*'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                boolean flag = jV();
                parcel1.writeNoException();
                int i1;
                if (flag)
                {
                    i1 = 1;
                } else
                {
                    i1 = 0;
                }
                parcel1.writeInt(i1);
                return true;

            case 43: // '+'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                String s1 = jW();
                parcel1.writeNoException();
                parcel1.writeString(s1);
                return true;

            case 44: // ','
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                e(com.google.android.gms.plus.internal.b.a.bk(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 45: // '-'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                b b1 = com.google.android.gms.plus.internal.b.a.bk(parcel.readStrongBinder());
                int l = parcel.readInt();
                ie ie1 = null;
                if (l != 0)
                {
                    ie1 = ie.CREATOR.L(parcel);
                }
                a(b1, ie1);
                parcel1.writeNoException();
                return true;

            case 46: // '.'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                s = parcel.readString();
                break;
            }
            hr hr1;
            int k;
            hr hr2;
            if (parcel.readInt() != 0)
            {
                hr1 = hr.CREATOR.D(parcel);
            } else
            {
                hr1 = null;
            }
            k = parcel.readInt();
            hr2 = null;
            if (k != 0)
            {
                hr2 = hr.CREATOR.D(parcel);
            }
            a(s, hr1, hr2);
            parcel1.writeNoException();
            return true;
        }
    }

    private static class a.a
        implements d
    {

        private IBinder kq;

        public hg a(b b1, int i, int j, int k, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            if (b1 == null)
            {
                break MISSING_BLOCK_LABEL_107;
            }
            IBinder ibinder = b1.asBinder();
_L1:
            hg hg;
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeString(s);
            kq.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            hg = com.google.android.gms.internal.hg.a.J(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return hg;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ie ie1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            if (ie1 == null)
            {
                break MISSING_BLOCK_LABEL_56;
            }
            parcel.writeInt(1);
            ie1.writeToParcel(parcel, 0);
_L1:
            kq.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(b b1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            if (b1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = b1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(8, parcel, parcel1, 0);
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

        public void a(b b1, int i, String s, Uri uri, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            if (b1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = b1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeString(s);
            if (uri == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L6:
            parcel.writeString(s1);
            parcel.writeString(s2);
            kq.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
              goto _L5
        }

        public void a(b b1, Uri uri, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            if (b1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = b1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (uri == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L6:
            if (bundle == null)
            {
                break MISSING_BLOCK_LABEL_133;
            }
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L7:
            kq.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            parcel.writeInt(0);
              goto _L7
        }

        public void a(b b1, ie ie1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            if (b1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = b1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (ie1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            ie1.writeToParcel(parcel, 0);
_L6:
            kq.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
              goto _L5
        }

        public void a(b b1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            if (b1 == null)
            {
                break MISSING_BLOCK_LABEL_68;
            }
            IBinder ibinder = b1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            kq.transact(1, parcel, parcel1, 0);
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

        public void a(b b1, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            if (b1 == null)
            {
                break MISSING_BLOCK_LABEL_80;
            }
            IBinder ibinder = b1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            kq.transact(2, parcel, parcel1, 0);
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

        public void a(b b1, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            if (b1 == null)
            {
                break MISSING_BLOCK_LABEL_69;
            }
            IBinder ibinder = b1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeStringList(list);
            kq.transact(34, parcel, parcel1, 0);
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

        public void a(String s, hr hr1, hr hr2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            parcel.writeString(s);
            if (hr1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            hr1.writeToParcel(parcel, 0);
_L3:
            if (hr2 == null)
            {
                break MISSING_BLOCK_LABEL_114;
            }
            parcel.writeInt(1);
            hr2.writeToParcel(parcel, 0);
_L4:
            kq.transact(46, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            parcel.writeInt(0);
              goto _L4
        }

        public IBinder asBinder()
        {
            return kq;
        }

        public void b(b b1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            if (b1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = b1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(19, parcel, parcel1, 0);
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

        public void b(b b1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            if (b1 == null)
            {
                break MISSING_BLOCK_LABEL_68;
            }
            IBinder ibinder = b1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            kq.transact(3, parcel, parcel1, 0);
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

        public void c(b b1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            if (b1 == null)
            {
                break MISSING_BLOCK_LABEL_69;
            }
            IBinder ibinder = b1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            kq.transact(18, parcel, parcel1, 0);
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

        public void clearDefaultAccount()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            kq.transact(6, parcel, parcel1, 0);
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

        public void d(b b1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            if (b1 == null)
            {
                break MISSING_BLOCK_LABEL_69;
            }
            IBinder ibinder = b1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            kq.transact(40, parcel, parcel1, 0);
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

        public void e(b b1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            if (b1 == null)
            {
                break MISSING_BLOCK_LABEL_69;
            }
            IBinder ibinder = b1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            kq.transact(44, parcel, parcel1, 0);
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

        public String getAccountName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            kq.transact(5, parcel, parcel1, 0);
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

        public String jU()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            kq.transact(41, parcel, parcel1, 0);
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

        public boolean jV()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            kq.transact(42, parcel, parcel1, 0);
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

        public String jW()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            kq.transact(43, parcel, parcel1, 0);
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

        public void removeMoment(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            parcel.writeString(s);
            kq.transact(17, parcel, parcel1, 0);
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

        a.a(IBinder ibinder)
        {
            kq = ibinder;
        }
    }


    public abstract hg a(b b1, int i, int j, int k, String s)
        throws RemoteException;

    public abstract void a(ie ie)
        throws RemoteException;

    public abstract void a(b b1)
        throws RemoteException;

    public abstract void a(b b1, int i, String s, Uri uri, String s1, String s2)
        throws RemoteException;

    public abstract void a(b b1, Uri uri, Bundle bundle)
        throws RemoteException;

    public abstract void a(b b1, ie ie)
        throws RemoteException;

    public abstract void a(b b1, String s)
        throws RemoteException;

    public abstract void a(b b1, String s, String s1)
        throws RemoteException;

    public abstract void a(b b1, List list)
        throws RemoteException;

    public abstract void a(String s, hr hr, hr hr1)
        throws RemoteException;

    public abstract void b(b b1)
        throws RemoteException;

    public abstract void b(b b1, String s)
        throws RemoteException;

    public abstract void c(b b1, String s)
        throws RemoteException;

    public abstract void clearDefaultAccount()
        throws RemoteException;

    public abstract void d(b b1, String s)
        throws RemoteException;

    public abstract void e(b b1, String s)
        throws RemoteException;

    public abstract String getAccountName()
        throws RemoteException;

    public abstract String jU()
        throws RemoteException;

    public abstract boolean jV()
        throws RemoteException;

    public abstract String jW()
        throws RemoteException;

    public abstract void removeMoment(String s)
        throws RemoteException;
}
