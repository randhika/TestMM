// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.GoogleMapOptionsCreator;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptionsCreator;
import com.google.android.gms.maps.model.internal.a;

// Referenced classes of package com.google.android.gms.maps.internal:
//            IMapViewDelegate, IStreetViewPanoramaViewDelegate, IMapFragmentDelegate, IStreetViewPanoramaFragmentDelegate, 
//            ICameraUpdateFactoryDelegate

public interface c
    extends IInterface
{
    public static abstract class a extends Binder
        implements c
    {

        public static c ax(IBinder ibinder)
        {
            if (ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
            if (iinterface != null && (iinterface instanceof c))
            {
                return (c)iinterface;
            } else
            {
                return new a(ibinder);
            }
        }

        public boolean onTransact(int k, Parcel parcel, Parcel parcel1, int l)
            throws RemoteException
        {
            IStreetViewPanoramaFragmentDelegate istreetviewpanoramafragmentdelegate;
            IBinder ibinder;
            switch (k)
            {
            default:
                return super.onTransact(k, parcel, parcel1, l);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.maps.internal.ICreator");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                h(com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                IMapFragmentDelegate imapfragmentdelegate = i(com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder()));
                parcel1.writeNoException();
                IBinder ibinder5 = null;
                if (imapfragmentdelegate != null)
                {
                    ibinder5 = imapfragmentdelegate.asBinder();
                }
                parcel1.writeStrongBinder(ibinder5);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                d d1 = com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder());
                GoogleMapOptions googlemapoptions;
                IMapViewDelegate imapviewdelegate;
                IBinder ibinder4;
                if (parcel.readInt() != 0)
                {
                    googlemapoptions = GoogleMapOptions.CREATOR.createFromParcel(parcel);
                } else
                {
                    googlemapoptions = null;
                }
                imapviewdelegate = a(d1, googlemapoptions);
                parcel1.writeNoException();
                ibinder4 = null;
                if (imapviewdelegate != null)
                {
                    ibinder4 = imapviewdelegate.asBinder();
                }
                parcel1.writeStrongBinder(ibinder4);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                ICameraUpdateFactoryDelegate icameraupdatefactorydelegate = jH();
                parcel1.writeNoException();
                IBinder ibinder3 = null;
                if (icameraupdatefactorydelegate != null)
                {
                    ibinder3 = icameraupdatefactorydelegate.asBinder();
                }
                parcel1.writeStrongBinder(ibinder3);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                com.google.android.gms.maps.model.internal.a a1 = jI();
                parcel1.writeNoException();
                IBinder ibinder2 = null;
                if (a1 != null)
                {
                    ibinder2 = a1.asBinder();
                }
                parcel1.writeStrongBinder(ibinder2);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                a(com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                d d = com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder());
                StreetViewPanoramaOptions streetviewpanoramaoptions;
                IStreetViewPanoramaViewDelegate istreetviewpanoramaviewdelegate;
                IBinder ibinder1;
                if (parcel.readInt() != 0)
                {
                    streetviewpanoramaoptions = StreetViewPanoramaOptions.CREATOR.createFromParcel(parcel);
                } else
                {
                    streetviewpanoramaoptions = null;
                }
                istreetviewpanoramaviewdelegate = a(d, streetviewpanoramaoptions);
                parcel1.writeNoException();
                ibinder1 = null;
                if (istreetviewpanoramaviewdelegate != null)
                {
                    ibinder1 = istreetviewpanoramaviewdelegate.asBinder();
                }
                parcel1.writeStrongBinder(ibinder1);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                istreetviewpanoramafragmentdelegate = j(com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder()));
                parcel1.writeNoException();
                ibinder = null;
                break;
            }
            if (istreetviewpanoramafragmentdelegate != null)
            {
                ibinder = istreetviewpanoramafragmentdelegate.asBinder();
            }
            parcel1.writeStrongBinder(ibinder);
            return true;
        }
    }

    private static class a.a
        implements c
    {

        private IBinder kq;

        public IMapViewDelegate a(d d1, GoogleMapOptions googlemapoptions)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
            if (d1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = d1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (googlemapoptions == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            googlemapoptions.writeToParcel(parcel, 0);
_L6:
            IMapViewDelegate imapviewdelegate;
            kq.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            imapviewdelegate = IMapViewDelegate.a.aC(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return imapviewdelegate;
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

        public IStreetViewPanoramaViewDelegate a(d d1, StreetViewPanoramaOptions streetviewpanoramaoptions)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
            if (d1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = d1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (streetviewpanoramaoptions == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            streetviewpanoramaoptions.writeToParcel(parcel, 0);
_L6:
            IStreetViewPanoramaViewDelegate istreetviewpanoramaviewdelegate;
            kq.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            istreetviewpanoramaviewdelegate = IStreetViewPanoramaViewDelegate.a.aV(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return istreetviewpanoramaviewdelegate;
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

        public void a(d d1, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
            if (d1 == null)
            {
                break MISSING_BLOCK_LABEL_69;
            }
            IBinder ibinder = d1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(k);
            kq.transact(6, parcel, parcel1, 0);
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

        public void h(d d1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
            if (d1 == null)
            {
                break MISSING_BLOCK_LABEL_59;
            }
            IBinder ibinder = d1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
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

        public IMapFragmentDelegate i(d d1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
            if (d1 == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = d1.asBinder();
_L1:
            IMapFragmentDelegate imapfragmentdelegate;
            parcel.writeStrongBinder(ibinder);
            kq.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            imapfragmentdelegate = IMapFragmentDelegate.a.aB(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return imapfragmentdelegate;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IStreetViewPanoramaFragmentDelegate j(d d1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
            if (d1 == null)
            {
                break MISSING_BLOCK_LABEL_71;
            }
            IBinder ibinder = d1.asBinder();
_L1:
            IStreetViewPanoramaFragmentDelegate istreetviewpanoramafragmentdelegate;
            parcel.writeStrongBinder(ibinder);
            kq.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            istreetviewpanoramafragmentdelegate = IStreetViewPanoramaFragmentDelegate.a.aU(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return istreetviewpanoramafragmentdelegate;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ICameraUpdateFactoryDelegate jH()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            ICameraUpdateFactoryDelegate icameraupdatefactorydelegate;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
            kq.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            icameraupdatefactorydelegate = ICameraUpdateFactoryDelegate.a.av(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return icameraupdatefactorydelegate;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public com.google.android.gms.maps.model.internal.a jI()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            com.google.android.gms.maps.model.internal.a a1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
            kq.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            a1 = com.google.android.gms.maps.model.internal.a.a.aX(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return a1;
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


    public abstract IMapViewDelegate a(d d, GoogleMapOptions googlemapoptions)
        throws RemoteException;

    public abstract IStreetViewPanoramaViewDelegate a(d d, StreetViewPanoramaOptions streetviewpanoramaoptions)
        throws RemoteException;

    public abstract void a(d d, int k)
        throws RemoteException;

    public abstract void h(d d)
        throws RemoteException;

    public abstract IMapFragmentDelegate i(d d)
        throws RemoteException;

    public abstract IStreetViewPanoramaFragmentDelegate j(d d)
        throws RemoteException;

    public abstract ICameraUpdateFactoryDelegate jH()
        throws RemoteException;

    public abstract com.google.android.gms.maps.model.internal.a jI()
        throws RemoteException;
}
