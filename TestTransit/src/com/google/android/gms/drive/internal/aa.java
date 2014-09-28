// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.content.IntentSender;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

// Referenced classes of package com.google.android.gms.drive.internal:
//            CreateFileIntentSenderRequest, OpenFileIntentSenderRequest, AddEventListenerRequest, ac, 
//            ab, AuthorizeAccessRequest, CheckResourceIdsExistRequest, CloseContentsAndUpdateMetadataRequest, 
//            CloseContentsRequest, CreateContentsRequest, CreateFileRequest, CreateFolderRequest, 
//            DeleteCustomPropertyRequest, DeleteResourceRequest, DisconnectRequest, GetDriveIdFromUniqueIdentifierRequest, 
//            GetMetadataRequest, ListParentsRequest, LoadRealtimeRequest, OpenContentsRequest, 
//            QueryRequest, RemoveEventListenerRequest, SetResourceParentsRequest, TrashResourceRequest, 
//            UpdateMetadataRequest

public interface aa
    extends IInterface
{
    public static abstract class a extends Binder
        implements aa
    {

        public static aa P(IBinder ibinder)
        {
            if (ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.drive.internal.IDriveService");
            if (iinterface != null && (iinterface instanceof aa))
            {
                return (aa)iinterface;
            } else
            {
                return new a(ibinder);
            }
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            int k;
            CheckResourceIdsExistRequest checkresourceidsexistrequest;
            switch (i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.drive.internal.IDriveService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int j6 = parcel.readInt();
                GetMetadataRequest getmetadatarequest = null;
                if (j6 != 0)
                {
                    getmetadatarequest = (GetMetadataRequest)GetMetadataRequest.CREATOR.createFromParcel(parcel);
                }
                a(getmetadatarequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int i6 = parcel.readInt();
                QueryRequest queryrequest1 = null;
                if (i6 != 0)
                {
                    queryrequest1 = (QueryRequest)QueryRequest.CREATOR.createFromParcel(parcel);
                }
                a(queryrequest1, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int l5 = parcel.readInt();
                UpdateMetadataRequest updatemetadatarequest = null;
                if (l5 != 0)
                {
                    updatemetadatarequest = (UpdateMetadataRequest)UpdateMetadataRequest.CREATOR.createFromParcel(parcel);
                }
                a(updatemetadatarequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int k5 = parcel.readInt();
                CreateContentsRequest createcontentsrequest = null;
                if (k5 != 0)
                {
                    createcontentsrequest = (CreateContentsRequest)CreateContentsRequest.CREATOR.createFromParcel(parcel);
                }
                a(createcontentsrequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int j5 = parcel.readInt();
                CreateFileRequest createfilerequest = null;
                if (j5 != 0)
                {
                    createfilerequest = (CreateFileRequest)CreateFileRequest.CREATOR.createFromParcel(parcel);
                }
                a(createfilerequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int i5 = parcel.readInt();
                CreateFolderRequest createfolderrequest = null;
                if (i5 != 0)
                {
                    createfolderrequest = (CreateFolderRequest)CreateFolderRequest.CREATOR.createFromParcel(parcel);
                }
                a(createfolderrequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int l4 = parcel.readInt();
                OpenContentsRequest opencontentsrequest = null;
                if (l4 != 0)
                {
                    opencontentsrequest = (OpenContentsRequest)OpenContentsRequest.CREATOR.createFromParcel(parcel);
                }
                a(opencontentsrequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int k4 = parcel.readInt();
                CloseContentsRequest closecontentsrequest = null;
                if (k4 != 0)
                {
                    closecontentsrequest = (CloseContentsRequest)CloseContentsRequest.CREATOR.createFromParcel(parcel);
                }
                a(closecontentsrequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                a(ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int j4 = parcel.readInt();
                OpenFileIntentSenderRequest openfileintentsenderrequest = null;
                if (j4 != 0)
                {
                    openfileintentsenderrequest = (OpenFileIntentSenderRequest)OpenFileIntentSenderRequest.CREATOR.createFromParcel(parcel);
                }
                IntentSender intentsender1 = a(openfileintentsenderrequest);
                parcel1.writeNoException();
                if (intentsender1 != null)
                {
                    parcel1.writeInt(1);
                    intentsender1.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int i4 = parcel.readInt();
                CreateFileIntentSenderRequest createfileintentsenderrequest = null;
                if (i4 != 0)
                {
                    createfileintentsenderrequest = (CreateFileIntentSenderRequest)CreateFileIntentSenderRequest.CREATOR.createFromParcel(parcel);
                }
                IntentSender intentsender = a(createfileintentsenderrequest);
                parcel1.writeNoException();
                if (intentsender != null)
                {
                    parcel1.writeInt(1);
                    intentsender.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int l3 = parcel.readInt();
                AuthorizeAccessRequest authorizeaccessrequest = null;
                if (l3 != 0)
                {
                    authorizeaccessrequest = (AuthorizeAccessRequest)AuthorizeAccessRequest.CREATOR.createFromParcel(parcel);
                }
                a(authorizeaccessrequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int k3 = parcel.readInt();
                ListParentsRequest listparentsrequest = null;
                if (k3 != 0)
                {
                    listparentsrequest = (ListParentsRequest)ListParentsRequest.CREATOR.createFromParcel(parcel);
                }
                a(listparentsrequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int j3 = parcel.readInt();
                AddEventListenerRequest addeventlistenerrequest = null;
                if (j3 != 0)
                {
                    addeventlistenerrequest = (AddEventListenerRequest)AddEventListenerRequest.CREATOR.createFromParcel(parcel);
                }
                a(addeventlistenerrequest, ac.a.R(parcel.readStrongBinder()), parcel.readString(), ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int i3 = parcel.readInt();
                RemoveEventListenerRequest removeeventlistenerrequest = null;
                if (i3 != 0)
                {
                    removeeventlistenerrequest = (RemoveEventListenerRequest)RemoveEventListenerRequest.CREATOR.createFromParcel(parcel);
                }
                a(removeeventlistenerrequest, ac.a.R(parcel.readStrongBinder()), parcel.readString(), ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int l2 = parcel.readInt();
                DisconnectRequest disconnectrequest = null;
                if (l2 != 0)
                {
                    disconnectrequest = (DisconnectRequest)DisconnectRequest.CREATOR.createFromParcel(parcel);
                }
                a(disconnectrequest);
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int k2 = parcel.readInt();
                TrashResourceRequest trashresourcerequest = null;
                if (k2 != 0)
                {
                    trashresourcerequest = (TrashResourceRequest)TrashResourceRequest.CREATOR.createFromParcel(parcel);
                }
                a(trashresourcerequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int j2 = parcel.readInt();
                CloseContentsAndUpdateMetadataRequest closecontentsandupdatemetadatarequest = null;
                if (j2 != 0)
                {
                    closecontentsandupdatemetadatarequest = (CloseContentsAndUpdateMetadataRequest)CloseContentsAndUpdateMetadataRequest.CREATOR.createFromParcel(parcel);
                }
                a(closecontentsandupdatemetadatarequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int i2 = parcel.readInt();
                QueryRequest queryrequest = null;
                if (i2 != 0)
                {
                    queryrequest = (QueryRequest)QueryRequest.CREATOR.createFromParcel(parcel);
                }
                b(queryrequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                b(ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                c(ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                d(ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                e(ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int l1 = parcel.readInt();
                DeleteResourceRequest deleteresourcerequest = null;
                if (l1 != 0)
                {
                    deleteresourcerequest = (DeleteResourceRequest)DeleteResourceRequest.CREATOR.createFromParcel(parcel);
                }
                a(deleteresourcerequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int k1 = parcel.readInt();
                DeleteCustomPropertyRequest deletecustompropertyrequest = null;
                if (k1 != 0)
                {
                    deletecustompropertyrequest = (DeleteCustomPropertyRequest)DeleteCustomPropertyRequest.CREATOR.createFromParcel(parcel);
                }
                a(deletecustompropertyrequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int j1 = parcel.readInt();
                LoadRealtimeRequest loadrealtimerequest = null;
                if (j1 != 0)
                {
                    loadrealtimerequest = (LoadRealtimeRequest)LoadRealtimeRequest.CREATOR.createFromParcel(parcel);
                }
                a(loadrealtimerequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int i1 = parcel.readInt();
                SetResourceParentsRequest setresourceparentsrequest = null;
                if (i1 != 0)
                {
                    setresourceparentsrequest = (SetResourceParentsRequest)SetResourceParentsRequest.CREATOR.createFromParcel(parcel);
                }
                a(setresourceparentsrequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                int l = parcel.readInt();
                GetDriveIdFromUniqueIdentifierRequest getdriveidfromuniqueidentifierrequest = null;
                if (l != 0)
                {
                    getdriveidfromuniqueidentifierrequest = (GetDriveIdFromUniqueIdentifierRequest)GetDriveIdFromUniqueIdentifierRequest.CREATOR.createFromParcel(parcel);
                }
                a(getdriveidfromuniqueidentifierrequest, ab.a.Q(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                k = parcel.readInt();
                checkresourceidsexistrequest = null;
                break;
            }
            if (k != 0)
            {
                checkresourceidsexistrequest = (CheckResourceIdsExistRequest)CheckResourceIdsExistRequest.CREATOR.createFromParcel(parcel);
            }
            a(checkresourceidsexistrequest, ab.a.Q(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;
        }
    }

    private static class a.a
        implements aa
    {

        private IBinder kq;

        public IntentSender a(CreateFileIntentSenderRequest createfileintentsenderrequest)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (createfileintentsenderrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            createfileintentsenderrequest.writeToParcel(parcel, 0);
_L3:
            IntentSender intentsender;
            kq.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            intentsender = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intentsender;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            intentsender = null;
              goto _L4
        }

        public IntentSender a(OpenFileIntentSenderRequest openfileintentsenderrequest)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (openfileintentsenderrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            openfileintentsenderrequest.writeToParcel(parcel, 0);
_L3:
            IntentSender intentsender;
            kq.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            intentsender = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intentsender;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            intentsender = null;
              goto _L4
        }

        public void a(AddEventListenerRequest addeventlistenerrequest, ac ac1, String s, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (addeventlistenerrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            addeventlistenerrequest.writeToParcel(parcel, 0);
_L3:
            if (ac1 == null)
            {
                break MISSING_BLOCK_LABEL_140;
            }
            IBinder ibinder = ac1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            IBinder ibinder1;
            ibinder1 = null;
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_76;
            }
            ibinder1 = ab1.asBinder();
            parcel.writeStrongBinder(ibinder1);
            kq.transact(14, parcel, parcel1, 0);
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

        public void a(AuthorizeAccessRequest authorizeaccessrequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (authorizeaccessrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            authorizeaccessrequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(12, parcel, parcel1, 0);
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

        public void a(CheckResourceIdsExistRequest checkresourceidsexistrequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (checkresourceidsexistrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            checkresourceidsexistrequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(30, parcel, parcel1, 0);
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

        public void a(CloseContentsAndUpdateMetadataRequest closecontentsandupdatemetadatarequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (closecontentsandupdatemetadatarequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            closecontentsandupdatemetadatarequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(18, parcel, parcel1, 0);
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

        public void a(CloseContentsRequest closecontentsrequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (closecontentsrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            closecontentsrequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(8, parcel, parcel1, 0);
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

        public void a(CreateContentsRequest createcontentsrequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (createcontentsrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            createcontentsrequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_100;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(4, parcel, parcel1, 0);
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

        public void a(CreateFileRequest createfilerequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (createfilerequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            createfilerequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_100;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(5, parcel, parcel1, 0);
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

        public void a(CreateFolderRequest createfolderrequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (createfolderrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            createfolderrequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(6, parcel, parcel1, 0);
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

        public void a(DeleteCustomPropertyRequest deletecustompropertyrequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (deletecustompropertyrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            deletecustompropertyrequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(26, parcel, parcel1, 0);
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

        public void a(DeleteResourceRequest deleteresourcerequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (deleteresourcerequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            deleteresourcerequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(24, parcel, parcel1, 0);
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

        public void a(DisconnectRequest disconnectrequest)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (disconnectrequest == null)
            {
                break MISSING_BLOCK_LABEL_57;
            }
            parcel.writeInt(1);
            disconnectrequest.writeToParcel(parcel, 0);
_L1:
            kq.transact(16, parcel, parcel1, 0);
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

        public void a(GetDriveIdFromUniqueIdentifierRequest getdriveidfromuniqueidentifierrequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (getdriveidfromuniqueidentifierrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            getdriveidfromuniqueidentifierrequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(29, parcel, parcel1, 0);
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

        public void a(GetMetadataRequest getmetadatarequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (getmetadatarequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            getmetadatarequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_100;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(1, parcel, parcel1, 0);
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

        public void a(ListParentsRequest listparentsrequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (listparentsrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            listparentsrequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(13, parcel, parcel1, 0);
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

        public void a(LoadRealtimeRequest loadrealtimerequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (loadrealtimerequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            loadrealtimerequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(27, parcel, parcel1, 0);
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

        public void a(OpenContentsRequest opencontentsrequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (opencontentsrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            opencontentsrequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(7, parcel, parcel1, 0);
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

        public void a(QueryRequest queryrequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (queryrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            queryrequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_100;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(2, parcel, parcel1, 0);
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

        public void a(RemoveEventListenerRequest removeeventlistenerrequest, ac ac1, String s, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (removeeventlistenerrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            removeeventlistenerrequest.writeToParcel(parcel, 0);
_L3:
            if (ac1 == null)
            {
                break MISSING_BLOCK_LABEL_140;
            }
            IBinder ibinder = ac1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            IBinder ibinder1;
            ibinder1 = null;
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_76;
            }
            ibinder1 = ab1.asBinder();
            parcel.writeStrongBinder(ibinder1);
            kq.transact(15, parcel, parcel1, 0);
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

        public void a(SetResourceParentsRequest setresourceparentsrequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (setresourceparentsrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            setresourceparentsrequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(28, parcel, parcel1, 0);
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

        public void a(TrashResourceRequest trashresourcerequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (trashresourcerequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            trashresourcerequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(17, parcel, parcel1, 0);
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

        public void a(UpdateMetadataRequest updatemetadatarequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (updatemetadatarequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            updatemetadatarequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_100;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(3, parcel, parcel1, 0);
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

        public void a(ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = ab1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(9, parcel, parcel1, 0);
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

        public void b(QueryRequest queryrequest, ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (queryrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            queryrequest.writeToParcel(parcel, 0);
_L3:
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            IBinder ibinder = ab1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(19, parcel, parcel1, 0);
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

        public void b(ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = ab1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(20, parcel, parcel1, 0);
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

        public void c(ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = ab1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(21, parcel, parcel1, 0);
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

        public void d(ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = ab1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(22, parcel, parcel1, 0);
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

        public void e(ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = ab1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(23, parcel, parcel1, 0);
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

        a.a(IBinder ibinder)
        {
            kq = ibinder;
        }
    }


    public abstract IntentSender a(CreateFileIntentSenderRequest createfileintentsenderrequest)
        throws RemoteException;

    public abstract IntentSender a(OpenFileIntentSenderRequest openfileintentsenderrequest)
        throws RemoteException;

    public abstract void a(AddEventListenerRequest addeventlistenerrequest, ac ac, String s, ab ab)
        throws RemoteException;

    public abstract void a(AuthorizeAccessRequest authorizeaccessrequest, ab ab)
        throws RemoteException;

    public abstract void a(CheckResourceIdsExistRequest checkresourceidsexistrequest, ab ab)
        throws RemoteException;

    public abstract void a(CloseContentsAndUpdateMetadataRequest closecontentsandupdatemetadatarequest, ab ab)
        throws RemoteException;

    public abstract void a(CloseContentsRequest closecontentsrequest, ab ab)
        throws RemoteException;

    public abstract void a(CreateContentsRequest createcontentsrequest, ab ab)
        throws RemoteException;

    public abstract void a(CreateFileRequest createfilerequest, ab ab)
        throws RemoteException;

    public abstract void a(CreateFolderRequest createfolderrequest, ab ab)
        throws RemoteException;

    public abstract void a(DeleteCustomPropertyRequest deletecustompropertyrequest, ab ab)
        throws RemoteException;

    public abstract void a(DeleteResourceRequest deleteresourcerequest, ab ab)
        throws RemoteException;

    public abstract void a(DisconnectRequest disconnectrequest)
        throws RemoteException;

    public abstract void a(GetDriveIdFromUniqueIdentifierRequest getdriveidfromuniqueidentifierrequest, ab ab)
        throws RemoteException;

    public abstract void a(GetMetadataRequest getmetadatarequest, ab ab)
        throws RemoteException;

    public abstract void a(ListParentsRequest listparentsrequest, ab ab)
        throws RemoteException;

    public abstract void a(LoadRealtimeRequest loadrealtimerequest, ab ab)
        throws RemoteException;

    public abstract void a(OpenContentsRequest opencontentsrequest, ab ab)
        throws RemoteException;

    public abstract void a(QueryRequest queryrequest, ab ab)
        throws RemoteException;

    public abstract void a(RemoveEventListenerRequest removeeventlistenerrequest, ac ac, String s, ab ab)
        throws RemoteException;

    public abstract void a(SetResourceParentsRequest setresourceparentsrequest, ab ab)
        throws RemoteException;

    public abstract void a(TrashResourceRequest trashresourcerequest, ab ab)
        throws RemoteException;

    public abstract void a(UpdateMetadataRequest updatemetadatarequest, ab ab)
        throws RemoteException;

    public abstract void a(ab ab)
        throws RemoteException;

    public abstract void b(QueryRequest queryrequest, ab ab)
        throws RemoteException;

    public abstract void b(ab ab)
        throws RemoteException;

    public abstract void c(ab ab)
        throws RemoteException;

    public abstract void d(ab ab)
        throws RemoteException;

    public abstract void e(ab ab)
        throws RemoteException;
}
