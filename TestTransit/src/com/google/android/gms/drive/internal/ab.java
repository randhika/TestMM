// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.StatusCreator;
import com.google.android.gms.drive.realtime.internal.m;

// Referenced classes of package com.google.android.gms.drive.internal:
//            OnContentsResponse, OnDownloadProgressResponse, OnDriveIdResponse, OnListEntriesResponse, 
//            OnListParentsResponse, OnLoadRealtimeResponse, OnMetadataResponse, OnResourceIdSetResponse, 
//            OnStorageStatsResponse, OnSyncMoreResponse

public interface ab
    extends IInterface
{
    public static abstract class a extends Binder
        implements ab
    {

        public static ab Q(IBinder ibinder)
        {
            if (ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
            if (iinterface != null && (iinterface instanceof ab))
            {
                return (ab)iinterface;
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
            int k;
            OnResourceIdSetResponse onresourceidsetresponse;
            switch (i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                int i3 = parcel.readInt();
                OnDownloadProgressResponse ondownloadprogressresponse = null;
                if (i3 != 0)
                {
                    ondownloadprogressresponse = (OnDownloadProgressResponse)OnDownloadProgressResponse.CREATOR.createFromParcel(parcel);
                }
                a(ondownloadprogressresponse);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                int l2 = parcel.readInt();
                OnListEntriesResponse onlistentriesresponse = null;
                if (l2 != 0)
                {
                    onlistentriesresponse = (OnListEntriesResponse)OnListEntriesResponse.CREATOR.createFromParcel(parcel);
                }
                a(onlistentriesresponse);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                int k2 = parcel.readInt();
                OnDriveIdResponse ondriveidresponse = null;
                if (k2 != 0)
                {
                    ondriveidresponse = (OnDriveIdResponse)OnDriveIdResponse.CREATOR.createFromParcel(parcel);
                }
                a(ondriveidresponse);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                int j2 = parcel.readInt();
                OnMetadataResponse onmetadataresponse = null;
                if (j2 != 0)
                {
                    onmetadataresponse = (OnMetadataResponse)OnMetadataResponse.CREATOR.createFromParcel(parcel);
                }
                a(onmetadataresponse);
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                int i2 = parcel.readInt();
                OnContentsResponse oncontentsresponse = null;
                if (i2 != 0)
                {
                    oncontentsresponse = (OnContentsResponse)OnContentsResponse.CREATOR.createFromParcel(parcel);
                }
                a(oncontentsresponse);
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                int l1 = parcel.readInt();
                Status status = null;
                if (l1 != 0)
                {
                    status = Status.CREATOR.createFromParcel(parcel);
                }
                o(status);
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                onSuccess();
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                int k1 = parcel.readInt();
                OnListParentsResponse onlistparentsresponse = null;
                if (k1 != 0)
                {
                    onlistparentsresponse = (OnListParentsResponse)OnListParentsResponse.CREATOR.createFromParcel(parcel);
                }
                a(onlistparentsresponse);
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                int j1 = parcel.readInt();
                OnSyncMoreResponse onsyncmoreresponse = null;
                if (j1 != 0)
                {
                    onsyncmoreresponse = (OnSyncMoreResponse)OnSyncMoreResponse.CREATOR.createFromParcel(parcel);
                }
                a(onsyncmoreresponse);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                int i1 = parcel.readInt();
                OnStorageStatsResponse onstoragestatsresponse = null;
                if (i1 != 0)
                {
                    onstoragestatsresponse = (OnStorageStatsResponse)OnStorageStatsResponse.CREATOR.createFromParcel(parcel);
                }
                a(onstoragestatsresponse);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                int l = parcel.readInt();
                OnLoadRealtimeResponse onloadrealtimeresponse = null;
                if (l != 0)
                {
                    onloadrealtimeresponse = (OnLoadRealtimeResponse)OnLoadRealtimeResponse.CREATOR.createFromParcel(parcel);
                }
                a(onloadrealtimeresponse, com.google.android.gms.drive.realtime.internal.m.a.ac(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                k = parcel.readInt();
                onresourceidsetresponse = null;
                break;
            }
            if (k != 0)
            {
                onresourceidsetresponse = (OnResourceIdSetResponse)OnResourceIdSetResponse.CREATOR.createFromParcel(parcel);
            }
            a(onresourceidsetresponse);
            parcel1.writeNoException();
            return true;
        }

        public a()
        {
            attachInterface(this, "com.google.android.gms.drive.internal.IDriveServiceCallbacks");
        }
    }

    private static class a.a
        implements ab
    {

        private IBinder kq;

        public void a(OnContentsResponse oncontentsresponse)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
            if (oncontentsresponse == null)
            {
                break MISSING_BLOCK_LABEL_56;
            }
            parcel.writeInt(1);
            oncontentsresponse.writeToParcel(parcel, 0);
_L1:
            kq.transact(5, parcel, parcel1, 0);
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

        public void a(OnDownloadProgressResponse ondownloadprogressresponse)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
            if (ondownloadprogressresponse == null)
            {
                break MISSING_BLOCK_LABEL_56;
            }
            parcel.writeInt(1);
            ondownloadprogressresponse.writeToParcel(parcel, 0);
_L1:
            kq.transact(1, parcel, parcel1, 0);
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

        public void a(OnDriveIdResponse ondriveidresponse)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
            if (ondriveidresponse == null)
            {
                break MISSING_BLOCK_LABEL_56;
            }
            parcel.writeInt(1);
            ondriveidresponse.writeToParcel(parcel, 0);
_L1:
            kq.transact(3, parcel, parcel1, 0);
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

        public void a(OnListEntriesResponse onlistentriesresponse)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
            if (onlistentriesresponse == null)
            {
                break MISSING_BLOCK_LABEL_56;
            }
            parcel.writeInt(1);
            onlistentriesresponse.writeToParcel(parcel, 0);
_L1:
            kq.transact(2, parcel, parcel1, 0);
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

        public void a(OnListParentsResponse onlistparentsresponse)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
            if (onlistparentsresponse == null)
            {
                break MISSING_BLOCK_LABEL_57;
            }
            parcel.writeInt(1);
            onlistparentsresponse.writeToParcel(parcel, 0);
_L1:
            kq.transact(8, parcel, parcel1, 0);
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

        public void a(OnLoadRealtimeResponse onloadrealtimeresponse, m m1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
            if (onloadrealtimeresponse == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            onloadrealtimeresponse.writeToParcel(parcel, 0);
_L3:
            if (m1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            IBinder ibinder = m1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(11, parcel, parcel1, 0);
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
            ibinder = null;
              goto _L4
        }

        public void a(OnMetadataResponse onmetadataresponse)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
            if (onmetadataresponse == null)
            {
                break MISSING_BLOCK_LABEL_56;
            }
            parcel.writeInt(1);
            onmetadataresponse.writeToParcel(parcel, 0);
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

        public void a(OnResourceIdSetResponse onresourceidsetresponse)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
            if (onresourceidsetresponse == null)
            {
                break MISSING_BLOCK_LABEL_57;
            }
            parcel.writeInt(1);
            onresourceidsetresponse.writeToParcel(parcel, 0);
_L1:
            kq.transact(12, parcel, parcel1, 0);
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

        public void a(OnStorageStatsResponse onstoragestatsresponse)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
            if (onstoragestatsresponse == null)
            {
                break MISSING_BLOCK_LABEL_57;
            }
            parcel.writeInt(1);
            onstoragestatsresponse.writeToParcel(parcel, 0);
_L1:
            kq.transact(10, parcel, parcel1, 0);
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

        public void a(OnSyncMoreResponse onsyncmoreresponse)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
            if (onsyncmoreresponse == null)
            {
                break MISSING_BLOCK_LABEL_57;
            }
            parcel.writeInt(1);
            onsyncmoreresponse.writeToParcel(parcel, 0);
_L1:
            kq.transact(9, parcel, parcel1, 0);
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

        public IBinder asBinder()
        {
            return kq;
        }

        public void o(Status status)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
            if (status == null)
            {
                break MISSING_BLOCK_LABEL_57;
            }
            parcel.writeInt(1);
            status.writeToParcel(parcel, 0);
_L1:
            kq.transact(6, parcel, parcel1, 0);
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

        public void onSuccess()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
            kq.transact(7, parcel, parcel1, 0);
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


    public abstract void a(OnContentsResponse oncontentsresponse)
        throws RemoteException;

    public abstract void a(OnDownloadProgressResponse ondownloadprogressresponse)
        throws RemoteException;

    public abstract void a(OnDriveIdResponse ondriveidresponse)
        throws RemoteException;

    public abstract void a(OnListEntriesResponse onlistentriesresponse)
        throws RemoteException;

    public abstract void a(OnListParentsResponse onlistparentsresponse)
        throws RemoteException;

    public abstract void a(OnLoadRealtimeResponse onloadrealtimeresponse, m m)
        throws RemoteException;

    public abstract void a(OnMetadataResponse onmetadataresponse)
        throws RemoteException;

    public abstract void a(OnResourceIdSetResponse onresourceidsetresponse)
        throws RemoteException;

    public abstract void a(OnStorageStatsResponse onstoragestatsresponse)
        throws RemoteException;

    public abstract void a(OnSyncMoreResponse onsyncmoreresponse)
        throws RemoteException;

    public abstract void o(Status status)
        throws RemoteException;

    public abstract void onSuccess()
        throws RemoteException;
}
