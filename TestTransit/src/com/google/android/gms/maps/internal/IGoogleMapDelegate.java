// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CameraPositionCreator;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.CircleOptionsCreator;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.GroundOverlayOptionsCreator;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.MarkerOptionsCreator;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolygonOptionsCreator;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.PolylineOptionsCreator;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileOverlayOptionsCreator;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.maps.model.internal.b;
import com.google.android.gms.maps.model.internal.c;
import com.google.android.gms.maps.model.internal.f;
import com.google.android.gms.maps.model.internal.g;
import com.google.android.gms.maps.model.internal.h;

// Referenced classes of package com.google.android.gms.maps.internal:
//            b, IProjectionDelegate, IUiSettingsDelegate, d, 
//            ILocationSourceDelegate, e, f, g, 
//            i, j, k, l, 
//            m, n, o, s

public interface IGoogleMapDelegate
    extends IInterface
{
    public static abstract class a extends Binder
        implements IGoogleMapDelegate
    {

        public static IGoogleMapDelegate ay(IBinder ibinder)
        {
            if (ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (iinterface != null && (iinterface instanceof IGoogleMapDelegate))
            {
                return (IGoogleMapDelegate)iinterface;
            } else
            {
                return new a(ibinder);
            }
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            int k;
            boolean flag;
            switch (i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                CameraPosition cameraposition = getCameraPosition();
                parcel1.writeNoException();
                if (cameraposition != null)
                {
                    parcel1.writeInt(1);
                    cameraposition.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                float f3 = getMaxZoomLevel();
                parcel1.writeNoException();
                parcel1.writeFloat(f3);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                float f2 = getMinZoomLevel();
                parcel1.writeNoException();
                parcel1.writeFloat(f2);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                moveCamera(com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                animateCamera(com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                animateCameraWithCallback(com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder()), b.a.aw(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                animateCameraWithDurationAndCallback(com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder()), parcel.readInt(), b.a.aw(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                stopAnimation();
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                PolylineOptions polylineoptions;
                IPolylineDelegate ipolylinedelegate;
                IBinder ibinder9;
                if (parcel.readInt() != 0)
                {
                    polylineoptions = PolylineOptions.CREATOR.createFromParcel(parcel);
                } else
                {
                    polylineoptions = null;
                }
                ipolylinedelegate = addPolyline(polylineoptions);
                parcel1.writeNoException();
                ibinder9 = null;
                if (ipolylinedelegate != null)
                {
                    ibinder9 = ipolylinedelegate.asBinder();
                }
                parcel1.writeStrongBinder(ibinder9);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                PolygonOptions polygonoptions;
                g g1;
                IBinder ibinder8;
                if (parcel.readInt() != 0)
                {
                    polygonoptions = PolygonOptions.CREATOR.createFromParcel(parcel);
                } else
                {
                    polygonoptions = null;
                }
                g1 = addPolygon(polygonoptions);
                parcel1.writeNoException();
                ibinder8 = null;
                if (g1 != null)
                {
                    ibinder8 = g1.asBinder();
                }
                parcel1.writeStrongBinder(ibinder8);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                MarkerOptions markeroptions;
                f f1;
                IBinder ibinder7;
                if (parcel.readInt() != 0)
                {
                    markeroptions = MarkerOptions.CREATOR.createFromParcel(parcel);
                } else
                {
                    markeroptions = null;
                }
                f1 = addMarker(markeroptions);
                parcel1.writeNoException();
                ibinder7 = null;
                if (f1 != null)
                {
                    ibinder7 = f1.asBinder();
                }
                parcel1.writeStrongBinder(ibinder7);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                GroundOverlayOptions groundoverlayoptions;
                c c1;
                IBinder ibinder6;
                if (parcel.readInt() != 0)
                {
                    groundoverlayoptions = GroundOverlayOptions.CREATOR.createFromParcel(parcel);
                } else
                {
                    groundoverlayoptions = null;
                }
                c1 = addGroundOverlay(groundoverlayoptions);
                parcel1.writeNoException();
                ibinder6 = null;
                if (c1 != null)
                {
                    ibinder6 = c1.asBinder();
                }
                parcel1.writeStrongBinder(ibinder6);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                TileOverlayOptions tileoverlayoptions;
                h h1;
                IBinder ibinder5;
                if (parcel.readInt() != 0)
                {
                    tileoverlayoptions = TileOverlayOptions.CREATOR.createFromParcel(parcel);
                } else
                {
                    tileoverlayoptions = null;
                }
                h1 = addTileOverlay(tileoverlayoptions);
                parcel1.writeNoException();
                ibinder5 = null;
                if (h1 != null)
                {
                    ibinder5 = h1.asBinder();
                }
                parcel1.writeStrongBinder(ibinder5);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                clear();
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                int l2 = getMapType();
                parcel1.writeNoException();
                parcel1.writeInt(l2);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setMapType(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                boolean flag9 = isTrafficEnabled();
                parcel1.writeNoException();
                int k2 = 0;
                if (flag9)
                {
                    k2 = 1;
                }
                parcel1.writeInt(k2);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                int j2 = parcel.readInt();
                boolean flag8 = false;
                if (j2 != 0)
                {
                    flag8 = true;
                }
                setTrafficEnabled(flag8);
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                boolean flag7 = isIndoorEnabled();
                parcel1.writeNoException();
                int i2 = 0;
                if (flag7)
                {
                    i2 = 1;
                }
                parcel1.writeInt(i2);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                boolean flag5;
                boolean flag6;
                int l1;
                if (parcel.readInt() != 0)
                {
                    flag5 = true;
                } else
                {
                    flag5 = false;
                }
                flag6 = setIndoorEnabled(flag5);
                parcel1.writeNoException();
                l1 = 0;
                if (flag6)
                {
                    l1 = 1;
                }
                parcel1.writeInt(l1);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                boolean flag4 = isMyLocationEnabled();
                parcel1.writeNoException();
                int k1 = 0;
                if (flag4)
                {
                    k1 = 1;
                }
                parcel1.writeInt(k1);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                int j1 = parcel.readInt();
                boolean flag3 = false;
                if (j1 != 0)
                {
                    flag3 = true;
                }
                setMyLocationEnabled(flag3);
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                Location location = getMyLocation();
                parcel1.writeNoException();
                if (location != null)
                {
                    parcel1.writeInt(1);
                    location.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 24: // '\030'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setLocationSource(ILocationSourceDelegate.a.aA(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                IUiSettingsDelegate iuisettingsdelegate = getUiSettings();
                parcel1.writeNoException();
                IBinder ibinder4 = null;
                if (iuisettingsdelegate != null)
                {
                    ibinder4 = iuisettingsdelegate.asBinder();
                }
                parcel1.writeStrongBinder(ibinder4);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                IProjectionDelegate iprojectiondelegate = getProjection();
                parcel1.writeNoException();
                IBinder ibinder3 = null;
                if (iprojectiondelegate != null)
                {
                    ibinder3 = iprojectiondelegate.asBinder();
                }
                parcel1.writeStrongBinder(ibinder3);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnCameraChangeListener(e.a.aD(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMapClickListener(com.google.android.gms.maps.internal.i.a.aH(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMapLongClickListener(com.google.android.gms.maps.internal.k.a.aJ(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMarkerClickListener(com.google.android.gms.maps.internal.l.a.aK(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMarkerDragListener(m.a.aL(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnInfoWindowClickListener(g.a.aF(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setInfoWindowAdapter(d.a.az(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                d d2 = getTestingHelper();
                parcel1.writeNoException();
                IBinder ibinder2 = null;
                if (d2 != null)
                {
                    ibinder2 = d2.asBinder();
                }
                parcel1.writeStrongBinder(ibinder2);
                return true;

            case 35: // '#'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                CircleOptions circleoptions;
                b b1;
                IBinder ibinder1;
                if (parcel.readInt() != 0)
                {
                    circleoptions = CircleOptions.CREATOR.createFromParcel(parcel);
                } else
                {
                    circleoptions = null;
                }
                b1 = addCircle(circleoptions);
                parcel1.writeNoException();
                ibinder1 = null;
                if (b1 != null)
                {
                    ibinder1 = b1.asBinder();
                }
                parcel1.writeStrongBinder(ibinder1);
                return true;

            case 36: // '$'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMyLocationChangeListener(o.a.aN(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 37: // '%'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMyLocationButtonClickListener(n.a.aM(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 38: // '&'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                snapshot(s.a.aS(parcel.readStrongBinder()), com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 39: // '\''
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setPadding(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 40: // '('
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                boolean flag2 = isBuildingsEnabled();
                parcel1.writeNoException();
                int i1 = 0;
                if (flag2)
                {
                    i1 = 1;
                }
                parcel1.writeInt(i1);
                return true;

            case 41: // ')'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                int l = parcel.readInt();
                boolean flag1 = false;
                if (l != 0)
                {
                    flag1 = true;
                }
                setBuildingsEnabled(flag1);
                parcel1.writeNoException();
                return true;

            case 42: // '*'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMapLoadedCallback(com.google.android.gms.maps.internal.j.a.aI(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 44: // ','
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                com.google.android.gms.maps.model.internal.d d1 = getFocusedBuilding();
                parcel1.writeNoException();
                IBinder ibinder = null;
                if (d1 != null)
                {
                    ibinder = d1.asBinder();
                }
                parcel1.writeStrongBinder(ibinder);
                return true;

            case 45: // '-'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnIndoorStateChangeListener(f.a.aE(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 51: // '3'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                k = parcel.readInt();
                flag = false;
                break;
            }
            if (k != 0)
            {
                flag = true;
            }
            setWatermarkEnabled(flag);
            parcel1.writeNoException();
            return true;
        }
    }

    private static class a.a
        implements IGoogleMapDelegate
    {

        private IBinder kq;

        public b addCircle(CircleOptions circleoptions)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (circleoptions == null)
            {
                break MISSING_BLOCK_LABEL_68;
            }
            parcel.writeInt(1);
            circleoptions.writeToParcel(parcel, 0);
_L1:
            b b1;
            kq.transact(35, parcel, parcel1, 0);
            parcel1.readException();
            b1 = com.google.android.gms.maps.model.internal.b.a.aY(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return b1;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public c addGroundOverlay(GroundOverlayOptions groundoverlayoptions)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (groundoverlayoptions == null)
            {
                break MISSING_BLOCK_LABEL_68;
            }
            parcel.writeInt(1);
            groundoverlayoptions.writeToParcel(parcel, 0);
_L1:
            c c;
            kq.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            c = com.google.android.gms.maps.model.internal.c.a.aZ(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return c;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public f addMarker(MarkerOptions markeroptions)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (markeroptions == null)
            {
                break MISSING_BLOCK_LABEL_68;
            }
            parcel.writeInt(1);
            markeroptions.writeToParcel(parcel, 0);
_L1:
            f f1;
            kq.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            f1 = com.google.android.gms.maps.model.internal.f.a.bc(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return f1;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public g addPolygon(PolygonOptions polygonoptions)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (polygonoptions == null)
            {
                break MISSING_BLOCK_LABEL_68;
            }
            parcel.writeInt(1);
            polygonoptions.writeToParcel(parcel, 0);
_L1:
            g g1;
            kq.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            g1 = com.google.android.gms.maps.model.internal.g.a.bd(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return g1;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IPolylineDelegate addPolyline(PolylineOptions polylineoptions)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (polylineoptions == null)
            {
                break MISSING_BLOCK_LABEL_68;
            }
            parcel.writeInt(1);
            polylineoptions.writeToParcel(parcel, 0);
_L1:
            IPolylineDelegate ipolylinedelegate;
            kq.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            ipolylinedelegate = com.google.android.gms.maps.model.internal.IPolylineDelegate.a.be(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return ipolylinedelegate;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public h addTileOverlay(TileOverlayOptions tileoverlayoptions)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (tileoverlayoptions == null)
            {
                break MISSING_BLOCK_LABEL_68;
            }
            parcel.writeInt(1);
            tileoverlayoptions.writeToParcel(parcel, 0);
_L1:
            h h;
            kq.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            h = com.google.android.gms.maps.model.internal.h.a.bf(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return h;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void animateCamera(d d1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (d1 == null)
            {
                break MISSING_BLOCK_LABEL_59;
            }
            IBinder ibinder = d1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(5, parcel, parcel1, 0);
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

        public void animateCameraWithCallback(d d1, com.google.android.gms.maps.internal.b b1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (d1 == null)
            {
                break MISSING_BLOCK_LABEL_85;
            }
            IBinder ibinder = d1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            IBinder ibinder1;
            ibinder1 = null;
            if (b1 == null)
            {
                break MISSING_BLOCK_LABEL_48;
            }
            ibinder1 = b1.asBinder();
            parcel.writeStrongBinder(ibinder1);
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

        public void animateCameraWithDurationAndCallback(d d1, int i1, com.google.android.gms.maps.internal.b b1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (d1 == null)
            {
                break MISSING_BLOCK_LABEL_97;
            }
            IBinder ibinder = d1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            IBinder ibinder1;
            ibinder1 = null;
            if (b1 == null)
            {
                break MISSING_BLOCK_LABEL_57;
            }
            ibinder1 = b1.asBinder();
            parcel.writeStrongBinder(ibinder1);
            kq.transact(7, parcel, parcel1, 0);
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

        public void clear()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            kq.transact(14, parcel, parcel1, 0);
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

        public CameraPosition getCameraPosition()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            kq.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            CameraPosition cameraposition1 = CameraPosition.CREATOR.createFromParcel(parcel1);
            CameraPosition cameraposition = cameraposition1;
_L4:
            parcel1.recycle();
            parcel.recycle();
            return cameraposition;
_L2:
            cameraposition = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public com.google.android.gms.maps.model.internal.d getFocusedBuilding()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            com.google.android.gms.maps.model.internal.d d1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            kq.transact(44, parcel, parcel1, 0);
            parcel1.readException();
            d1 = com.google.android.gms.maps.model.internal.d.a.ba(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return d1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getMapType()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            kq.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public float getMaxZoomLevel()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            float f1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            kq.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            f1 = parcel1.readFloat();
            parcel1.recycle();
            parcel.recycle();
            return f1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public float getMinZoomLevel()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            float f1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            kq.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            f1 = parcel1.readFloat();
            parcel1.recycle();
            parcel.recycle();
            return f1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Location getMyLocation()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            kq.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Location location = (Location)Location.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return location;
_L2:
            location = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IProjectionDelegate getProjection()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IProjectionDelegate iprojectiondelegate;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            kq.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            iprojectiondelegate = IProjectionDelegate.a.aR(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return iprojectiondelegate;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public d getTestingHelper()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            d d1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            kq.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            d1 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return d1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IUiSettingsDelegate getUiSettings()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IUiSettingsDelegate iuisettingsdelegate;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            kq.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            iuisettingsdelegate = IUiSettingsDelegate.a.aW(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return iuisettingsdelegate;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isBuildingsEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            kq.transact(40, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            boolean flag = false;
            if (i1 != 0)
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

        public boolean isIndoorEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            kq.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            boolean flag = false;
            if (i1 != 0)
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

        public boolean isMyLocationEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            kq.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            boolean flag = false;
            if (i1 != 0)
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

        public boolean isTrafficEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            kq.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            boolean flag = false;
            if (i1 != 0)
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

        public void moveCamera(d d1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (d1 == null)
            {
                break MISSING_BLOCK_LABEL_59;
            }
            IBinder ibinder = d1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(4, parcel, parcel1, 0);
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

        public void setBuildingsEnabled(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(41, parcel, parcel1, 0);
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

        public boolean setIndoorEnabled(boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            int i1;
            int j1;
            if (flag)
            {
                i1 = ((flag1) ? 1 : 0);
            } else
            {
                i1 = 0;
            }
            parcel.writeInt(i1);
            kq.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            j1 = parcel1.readInt();
            if (j1 == 0)
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

        public void setInfoWindowAdapter(com.google.android.gms.maps.internal.d d1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (d1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = d1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(33, parcel, parcel1, 0);
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

        public void setLocationSource(ILocationSourceDelegate ilocationsourcedelegate)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (ilocationsourcedelegate == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = ilocationsourcedelegate.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(24, parcel, parcel1, 0);
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

        public void setMapType(int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            parcel.writeInt(i1);
            kq.transact(16, parcel, parcel1, 0);
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

        public void setMyLocationEnabled(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(22, parcel, parcel1, 0);
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

        public void setOnCameraChangeListener(e e1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (e1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = e1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(27, parcel, parcel1, 0);
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

        public void setOnIndoorStateChangeListener(com.google.android.gms.maps.internal.f f1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (f1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = f1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(45, parcel, parcel1, 0);
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

        public void setOnInfoWindowClickListener(com.google.android.gms.maps.internal.g g1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (g1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = g1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(32, parcel, parcel1, 0);
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

        public void setOnMapClickListener(i i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (i1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = i1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(28, parcel, parcel1, 0);
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

        public void setOnMapLoadedCallback(j j1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (j1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = j1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(42, parcel, parcel1, 0);
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

        public void setOnMapLongClickListener(k k1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (k1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = k1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(29, parcel, parcel1, 0);
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

        public void setOnMarkerClickListener(l l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (l1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = l1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(30, parcel, parcel1, 0);
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

        public void setOnMarkerDragListener(m m1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (m1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = m1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(31, parcel, parcel1, 0);
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

        public void setOnMyLocationButtonClickListener(n n1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (n1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = n1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(37, parcel, parcel1, 0);
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

        public void setOnMyLocationChangeListener(o o1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (o1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = o1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(36, parcel, parcel1, 0);
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

        public void setPadding(int i1, int j1, int k1, int l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeInt(k1);
            parcel.writeInt(l1);
            kq.transact(39, parcel, parcel1, 0);
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

        public void setTrafficEnabled(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(18, parcel, parcel1, 0);
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

        public void setWatermarkEnabled(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(51, parcel, parcel1, 0);
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

        public void snapshot(s s1, d d1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (s1 == null)
            {
                break MISSING_BLOCK_LABEL_85;
            }
            IBinder ibinder = s1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            IBinder ibinder1;
            ibinder1 = null;
            if (d1 == null)
            {
                break MISSING_BLOCK_LABEL_48;
            }
            ibinder1 = d1.asBinder();
            parcel.writeStrongBinder(ibinder1);
            kq.transact(38, parcel, parcel1, 0);
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

        public void stopAnimation()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            kq.transact(8, parcel, parcel1, 0);
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


    public abstract b addCircle(CircleOptions circleoptions)
        throws RemoteException;

    public abstract c addGroundOverlay(GroundOverlayOptions groundoverlayoptions)
        throws RemoteException;

    public abstract f addMarker(MarkerOptions markeroptions)
        throws RemoteException;

    public abstract g addPolygon(PolygonOptions polygonoptions)
        throws RemoteException;

    public abstract IPolylineDelegate addPolyline(PolylineOptions polylineoptions)
        throws RemoteException;

    public abstract h addTileOverlay(TileOverlayOptions tileoverlayoptions)
        throws RemoteException;

    public abstract void animateCamera(d d)
        throws RemoteException;

    public abstract void animateCameraWithCallback(d d, com.google.android.gms.maps.internal.b b)
        throws RemoteException;

    public abstract void animateCameraWithDurationAndCallback(d d, int i, com.google.android.gms.maps.internal.b b)
        throws RemoteException;

    public abstract void clear()
        throws RemoteException;

    public abstract CameraPosition getCameraPosition()
        throws RemoteException;

    public abstract com.google.android.gms.maps.model.internal.d getFocusedBuilding()
        throws RemoteException;

    public abstract int getMapType()
        throws RemoteException;

    public abstract float getMaxZoomLevel()
        throws RemoteException;

    public abstract float getMinZoomLevel()
        throws RemoteException;

    public abstract Location getMyLocation()
        throws RemoteException;

    public abstract IProjectionDelegate getProjection()
        throws RemoteException;

    public abstract d getTestingHelper()
        throws RemoteException;

    public abstract IUiSettingsDelegate getUiSettings()
        throws RemoteException;

    public abstract boolean isBuildingsEnabled()
        throws RemoteException;

    public abstract boolean isIndoorEnabled()
        throws RemoteException;

    public abstract boolean isMyLocationEnabled()
        throws RemoteException;

    public abstract boolean isTrafficEnabled()
        throws RemoteException;

    public abstract void moveCamera(d d)
        throws RemoteException;

    public abstract void setBuildingsEnabled(boolean flag)
        throws RemoteException;

    public abstract boolean setIndoorEnabled(boolean flag)
        throws RemoteException;

    public abstract void setInfoWindowAdapter(com.google.android.gms.maps.internal.d d)
        throws RemoteException;

    public abstract void setLocationSource(ILocationSourceDelegate ilocationsourcedelegate)
        throws RemoteException;

    public abstract void setMapType(int i)
        throws RemoteException;

    public abstract void setMyLocationEnabled(boolean flag)
        throws RemoteException;

    public abstract void setOnCameraChangeListener(e e)
        throws RemoteException;

    public abstract void setOnIndoorStateChangeListener(com.google.android.gms.maps.internal.f f)
        throws RemoteException;

    public abstract void setOnInfoWindowClickListener(com.google.android.gms.maps.internal.g g)
        throws RemoteException;

    public abstract void setOnMapClickListener(i i)
        throws RemoteException;

    public abstract void setOnMapLoadedCallback(j j)
        throws RemoteException;

    public abstract void setOnMapLongClickListener(k k)
        throws RemoteException;

    public abstract void setOnMarkerClickListener(l l)
        throws RemoteException;

    public abstract void setOnMarkerDragListener(m m)
        throws RemoteException;

    public abstract void setOnMyLocationButtonClickListener(n n)
        throws RemoteException;

    public abstract void setOnMyLocationChangeListener(o o)
        throws RemoteException;

    public abstract void setPadding(int i, int j, int k, int l)
        throws RemoteException;

    public abstract void setTrafficEnabled(boolean flag)
        throws RemoteException;

    public abstract void setWatermarkEnabled(boolean flag)
        throws RemoteException;

    public abstract void snapshot(s s, d d)
        throws RemoteException;

    public abstract void stopAnimation()
        throws RemoteException;
}
