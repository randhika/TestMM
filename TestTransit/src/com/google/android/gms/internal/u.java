// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ViewSwitcher;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.gms.internal:
//            ba, bd, bf, bn, 
//            ci, cl, ej, t, 
//            y, ab, eo, eu, 
//            ap, al, et, eg, 
//            ef, ev, bm, br, 
//            ex, bu, eh, bl, 
//            hm, cz, cs, ai, 
//            ep, bo, ek, ey, 
//            x, ac, ct, dg, 
//            cq, cu, dc, dm, 
//            as, v, ch, cf, 
//            em, bt, g, en, 
//            j, k

public class u extends aq.a
    implements ba, bd, bf, bn, ci, cl, dm.a, ej, t
{
    private static final class a extends ViewSwitcher
    {

        private final ep kI;

        static ep a(a a1)
        {
            return a1.kI;
        }

        public boolean onInterceptTouchEvent(MotionEvent motionevent)
        {
            kI.c(motionevent);
            return false;
        }

        public a(Context context)
        {
            super(context);
            kI = new ep(context);
        }
    }

    private static final class b
        implements g, Runnable
    {

        private c kC;
        private final List kJ = new Vector();
        private final CountDownLatch kK = new CountDownLatch(1);
        private final AtomicReference kL = new AtomicReference();

        private void ao()
        {
            try
            {
                kK.await();
                return;
            }
            catch (InterruptedException interruptedexception)
            {
                eu.c("Interrupted during GADSignals creation.", interruptedexception);
            }
        }

        private void ap()
        {
            if (!kJ.isEmpty())
            {
                Iterator iterator = kJ.iterator();
                while (iterator.hasNext()) 
                {
                    Object aobj[] = (Object[])iterator.next();
                    if (aobj.length == 1)
                    {
                        ((g)kL.get()).a((MotionEvent)aobj[0]);
                    } else
                    if (aobj.length == 3)
                    {
                        ((g)kL.get()).a(((Integer)aobj[0]).intValue(), ((Integer)aobj[1]).intValue(), ((Integer)aobj[2]).intValue());
                    }
                }
            }
        }

        public String a(Context context)
        {
            ao();
            if (kL.get() != null)
            {
                ap();
                return ((g)kL.get()).a(context);
            } else
            {
                return "";
            }
        }

        public String a(Context context, String s)
        {
            ao();
            if (kL.get() != null)
            {
                ap();
                return ((g)kL.get()).a(context, s);
            } else
            {
                return "";
            }
        }

        public void a(int i, int k, int l)
        {
            g g1 = (g)kL.get();
            if (g1 != null)
            {
                ap();
                g1.a(i, k, l);
                return;
            } else
            {
                List list = kJ;
                Object aobj[] = new Object[3];
                aobj[0] = Integer.valueOf(i);
                aobj[1] = Integer.valueOf(k);
                aobj[2] = Integer.valueOf(l);
                list.add(((Object) (aobj)));
                return;
            }
        }

        public void a(MotionEvent motionevent)
        {
            g g1 = (g)kL.get();
            if (g1 != null)
            {
                ap();
                g1.a(motionevent);
                return;
            } else
            {
                kJ.add(((Object) (new Object[] {
                    motionevent
                })));
                return;
            }
        }

        public String b(Context context)
        {
            ao();
            if (kL.get() != null)
            {
                ap();
                return ((g)kL.get()).b(context);
            } else
            {
                return "";
            }
        }

        public void run()
        {
            kL.set(j.a(kC.kQ.sw, kC.kO));
            kK.countDown();
            kC = null;
            return;
            Exception exception;
            exception;
            kK.countDown();
            kC = null;
            throw exception;
        }

        public b(c c1)
        {
            kC = c1;
            if (et.bW())
            {
                en.execute(this);
                return;
            } else
            {
                run();
                return;
            }
        }
    }

    private static final class c
    {

        public final a kM;
        public final String kN;
        public final Context kO;
        public final k kP = new k(new b(this));
        public final ev kQ;
        public ap kR;
        public em kS;
        public al kT;
        public ef kU;
        public eg kV;
        public as kW;
        public dg kX;
        public dc kY;
        public cz kZ;
        public ek la;
        public boolean lb;
        private HashSet lc;

        public void a(HashSet hashset)
        {
            lc = hashset;
        }

        public HashSet aq()
        {
            return lc;
        }

        public c(Context context, al al1, String s, ev ev1)
        {
            la = null;
            lb = false;
            lc = null;
            if (al1.mf)
            {
                kM = null;
            } else
            {
                kM = new a(context);
                kM.setMinimumWidth(al1.widthPixels);
                kM.setMinimumHeight(al1.heightPixels);
                kM.setVisibility(4);
            }
            kT = al1;
            kN = s;
            kO = context;
            kQ = ev1;
        }
    }


    private final bt kB;
    private final c kC;
    private final y kD = new y(this);
    private final ab kE = new ab();
    private boolean kF;
    private final ComponentCallbacks kG = new ComponentCallbacks() {

        final u kH;

        public void onConfigurationChanged(Configuration configuration)
        {
            if (u.a(kH) != null && u.a(kH).kU != null && u.a(kH).kU.oy != null)
            {
                u.a(kH).kU.oy.bX();
            }
        }

        public void onLowMemory()
        {
        }

            
            {
                kH = u.this;
                super();
            }
    };

    public u(Context context, al al1, String s, bt bt, ev ev1)
    {
        kC = new c(context, al1, s, ev1);
        kB = bt;
        eo.n(context);
        W();
    }

    private void W()
    {
        if (android.os.Build.VERSION.SDK_INT >= 14 && kC != null && kC.kO != null)
        {
            kC.kO.registerComponentCallbacks(kG);
        }
    }

    private void X()
    {
        if (android.os.Build.VERSION.SDK_INT >= 14 && kC != null && kC.kO != null)
        {
            kC.kO.unregisterComponentCallbacks(kG);
        }
    }

    static c a(u u1)
    {
        return u1.kC;
    }

    private void a(int i)
    {
        eu.D((new StringBuilder()).append("Failed to load ad: ").append(i).toString());
        if (kC.kR == null)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        kC.kR.onAdFailedToLoad(i);
        return;
        RemoteException remoteexception;
        remoteexception;
        eu.c("Could not call AdListener.onAdFailedToLoad().", remoteexception);
        return;
    }

    private void ah()
    {
        eu.B("Ad closing.");
        if (kC.kR == null)
        {
            break MISSING_BLOCK_LABEL_27;
        }
        kC.kR.onAdClosed();
        return;
        RemoteException remoteexception;
        remoteexception;
        eu.c("Could not call AdListener.onAdClosed().", remoteexception);
        return;
    }

    private void ai()
    {
        eu.B("Ad leaving application.");
        if (kC.kR == null)
        {
            break MISSING_BLOCK_LABEL_27;
        }
        kC.kR.onAdLeftApplication();
        return;
        RemoteException remoteexception;
        remoteexception;
        eu.c("Could not call AdListener.onAdLeftApplication().", remoteexception);
        return;
    }

    private void aj()
    {
        eu.B("Ad opening.");
        if (kC.kR == null)
        {
            break MISSING_BLOCK_LABEL_27;
        }
        kC.kR.onAdOpened();
        return;
        RemoteException remoteexception;
        remoteexception;
        eu.c("Could not call AdListener.onAdOpened().", remoteexception);
        return;
    }

    private void ak()
    {
        eu.B("Ad finished loading.");
        if (kC.kR == null)
        {
            break MISSING_BLOCK_LABEL_27;
        }
        kC.kR.onAdLoaded();
        return;
        RemoteException remoteexception;
        remoteexception;
        eu.c("Could not call AdListener.onAdLoaded().", remoteexception);
        return;
    }

    private boolean al()
    {
        boolean flag = true;
        if (!eo.a(kC.kO.getPackageManager(), kC.kO.getPackageName(), "android.permission.INTERNET"))
        {
            if (!kC.kT.mf)
            {
                et.a(kC.kM, kC.kT, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            }
            flag = false;
        }
        if (!eo.m(kC.kO))
        {
            if (!kC.kT.mf)
            {
                et.a(kC.kM, kC.kT, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            }
            flag = false;
        }
        if (!flag && !kC.kT.mf)
        {
            kC.kM.setVisibility(0);
        }
        return flag;
    }

    private void am()
    {
        if (kC.kU == null)
        {
            eu.D("Ad state was null when trying to ping click URLs.");
        } else
        {
            eu.z("Pinging click URLs.");
            kC.kV.bC();
            if (kC.kU.nt != null)
            {
                eo.a(kC.kO, kC.kQ.sw, kC.kU.nt);
            }
            if (kC.kU.rz != null && kC.kU.rz.nt != null)
            {
                br.a(kC.kO, kC.kQ.sw, kC.kU, kC.kN, false, kC.kU.rz.nt);
                return;
            }
        }
    }

    private void an()
    {
        if (kC.kU != null)
        {
            kC.kU.oy.destroy();
            kC.kU = null;
        }
    }

    private void b(View view)
    {
        android.view.ViewGroup.LayoutParams layoutparams = new android.view.ViewGroup.LayoutParams(-2, -2);
        kC.kM.addView(view, layoutparams);
    }

    private boolean b(ef ef1)
    {
        if (ef1.qg)
        {
            View view1;
            View view2;
            try
            {
                view1 = (View)e.e(ef1.nN.getView());
            }
            catch (RemoteException remoteexception1)
            {
                eu.c("Could not get View from mediation adapter.", remoteexception1);
                return false;
            }
            view2 = kC.kM.getNextView();
            if (view2 != null)
            {
                kC.kM.removeView(view2);
            }
            try
            {
                b(view1);
            }
            catch (Throwable throwable)
            {
                eu.c("Could not add mediation view to view hierarchy.", throwable);
                return false;
            }
        } else
        if (ef1.rA != null)
        {
            ef1.oy.a(ef1.rA);
            kC.kM.removeAllViews();
            kC.kM.setMinimumWidth(ef1.rA.widthPixels);
            kC.kM.setMinimumHeight(ef1.rA.heightPixels);
            b(((View) (ef1.oy)));
        }
        if (kC.kM.getChildCount() > 1)
        {
            kC.kM.showNext();
        }
        if (kC.kU != null)
        {
            View view = kC.kM.getNextView();
            if (view instanceof ex)
            {
                ((ex)view).a(kC.kO, kC.kT);
            } else
            if (view != null)
            {
                kC.kM.removeView(view);
            }
            if (kC.kU.nN != null)
            {
                try
                {
                    kC.kU.nN.destroy();
                }
                catch (RemoteException remoteexception)
                {
                    eu.D("Could not destroy previous mediation adapter.");
                }
            }
        }
        kC.kM.setVisibility(0);
        return true;
    }

    private ds.a c(ai ai1)
    {
        ApplicationInfo applicationinfo = kC.kO.getApplicationInfo();
        android.content.pm.PackageInfo packageinfo1 = kC.kO.getPackageManager().getPackageInfo(applicationinfo.packageName, 0);
        android.content.pm.PackageInfo packageinfo = packageinfo1;
_L1:
        Bundle bundle;
        if (!kC.kT.mf && kC.kM.getParent() != null)
        {
            int ai2[] = new int[2];
            kC.kM.getLocationOnScreen(ai2);
            int i = ai2[0];
            int j = ai2[1];
            DisplayMetrics displaymetrics = kC.kO.getResources().getDisplayMetrics();
            int k = kC.kM.getWidth();
            int l = kC.kM.getHeight();
            android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
            String s;
            Bundle bundle1;
            int i1;
            if (kC.kM.isShown() && i + k > 0 && j + l > 0 && i <= displaymetrics.widthPixels && j <= displaymetrics.heightPixels)
            {
                i1 = 1;
            } else
            {
                i1 = 0;
            }
            bundle = new Bundle(5);
            bundle.putInt("x", i);
            bundle.putInt("y", j);
            bundle.putInt("width", k);
            bundle.putInt("height", l);
            bundle.putInt("visible", i1);
        } else
        {
            bundle = null;
        }
        s = eh.bI();
        kC.kV = new eg(s, kC.kN);
        kC.kV.f(ai1);
        bundle1 = eh.a(kC.kO, this, s);
        return new ds.a(bundle, ai1, kC.kT, kC.kN, applicationinfo, packageinfo, s, eh.rQ, kC.kQ, bundle1);
        namenotfoundexception;
        packageinfo = null;
          goto _L1
    }

    private void c(boolean flag)
    {
        if (kC.kU == null)
        {
            eu.D("Ad state was null when trying to ping impression URLs.");
        } else
        {
            eu.z("Pinging Impression URLs.");
            kC.kV.bB();
            if (kC.kU.nu != null)
            {
                eo.a(kC.kO, kC.kQ.sw, kC.kU.nu);
            }
            if (kC.kU.rz != null && kC.kU.rz.nu != null)
            {
                br.a(kC.kO, kC.kQ.sw, kC.kU, kC.kN, flag, kC.kU.rz.nu);
            }
            if (kC.kU.nM != null && kC.kU.nM.np != null)
            {
                br.a(kC.kO, kC.kQ.sw, kC.kU, kC.kN, flag, kC.kU.nM.np);
                return;
            }
        }
    }

    public d U()
    {
        hm.ay("getAdFrame must be called on the main UI thread.");
        return e.h(kC.kM);
    }

    public al V()
    {
        hm.ay("getAdSize must be called on the main UI thread.");
        return kC.kT;
    }

    public void Y()
    {
        ai();
    }

    public void Z()
    {
        kE.d(kC.kU);
        if (kC.kT.mf)
        {
            an();
        }
        kF = false;
        ah();
        kC.kV.bD();
    }

    public void a(al al1)
    {
        hm.ay("setAdSize must be called on the main UI thread.");
        kC.kT = al1;
        if (kC.kU != null)
        {
            kC.kU.oy.a(al1);
        }
        if (kC.kM.getChildCount() > 1)
        {
            kC.kM.removeView(kC.kM.getNextView());
        }
        kC.kM.setMinimumWidth(al1.widthPixels);
        kC.kM.setMinimumHeight(al1.heightPixels);
        kC.kM.requestLayout();
    }

    public void a(ap ap1)
    {
        hm.ay("setAdListener must be called on the main UI thread.");
        kC.kR = ap1;
    }

    public void a(as as1)
    {
        hm.ay("setAppEventListener must be called on the main UI thread.");
        kC.kW = as1;
    }

    public void a(dc dc1)
    {
        hm.ay("setInAppPurchaseListener must be called on the main UI thread.");
        kC.kY = dc1;
    }

    public void a(dg dg1, String s)
    {
        hm.ay("setPlayStorePurchaseParams must be called on the main UI thread.");
        kC.kZ = new cz(s);
        kC.kX = dg1;
        if (!eh.bM() && dg1 != null)
        {
            (new cs(kC.kO, kC.kX, kC.kZ)).start();
        }
    }

    public void a(ef ef1)
    {
        boolean flag;
        kC.kS = null;
        if (ef1.errorCode != -2 && ef1.errorCode != 3)
        {
            eh.b(kC.aq());
        }
        if (ef1.errorCode == -1)
        {
            return;
        }
        if (ef1.pX.extras != null)
        {
            flag = ef1.pX.extras.getBoolean("_noRefresh", false);
        } else
        {
            flag = false;
        }
        if (!kC.kT.mf) goto _L2; else goto _L1
_L1:
        eo.a(ef1.oy);
_L4:
        if (ef1.errorCode == 3 && ef1.rz != null && ef1.rz.nv != null)
        {
            eu.z("Pinging no fill URLs.");
            br.a(kC.kO, kC.kQ.sw, ef1, kC.kN, false, ef1.rz.nv);
        }
        if (ef1.errorCode != -2)
        {
            a(ef1.errorCode);
            return;
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (!flag)
        {
            if (ef1.nx > 0L)
            {
                kD.a(ef1.pX, ef1.nx);
            } else
            if (ef1.rz != null && ef1.rz.nx > 0L)
            {
                kD.a(ef1.pX, ef1.rz.nx);
            } else
            if (!ef1.qg && ef1.errorCode == 2)
            {
                kD.d(ef1.pX);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (!kC.kT.mf)
        {
            if (!b(ef1))
            {
                a(0);
                return;
            }
            if (kC.kM != null)
            {
                a.a(kC.kM).x(ef1.ql);
            }
        }
        if (kC.kU != null && kC.kU.nP != null)
        {
            kC.kU.nP.a(null);
        }
        if (ef1.nP != null)
        {
            ef1.nP.a(this);
        }
        kE.d(kC.kU);
        kC.kU = ef1;
        if (ef1.rA != null)
        {
            kC.kT = ef1.rA;
        }
        kC.kV.j(ef1.rB);
        kC.kV.k(ef1.rC);
        kC.kV.n(kC.kT.mf);
        kC.kV.o(ef1.qg);
        if (!kC.kT.mf)
        {
            c(false);
        }
        if (kC.la == null)
        {
            kC.la = new ek(kC.kN);
        }
        int i;
        int j;
        if (ef1.rz != null)
        {
            i = ef1.rz.ny;
            j = ef1.rz.nz;
        } else
        {
            i = 0;
            j = 0;
        }
        kC.la.a(i, j);
        if (!kC.kT.mf && ef1.oy != null && (ef1.oy.cb().cj() || ef1.ry != null))
        {
            ac ac1 = kE.a(kC.kT, kC.kU);
            if (ef1.oy.cb().cj() && ac1 != null)
            {
                ac1.a(new x(ef1.oy));
            }
        }
        kC.kU.oy.bX();
        ak();
        return;
    }

    public void a(String s, ArrayList arraylist)
    {
        ct ct1 = new ct(s, arraylist, kC.kO, kC.kQ.sw);
        if (kC.kY != null) goto _L2; else goto _L1
_L1:
        eu.D("InAppPurchaseListener is not set. Try to launch default purchase flow.");
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(kC.kO) == 0) goto _L4; else goto _L3
_L3:
        eu.D("Google Play Service unavailable, cannot launch default purchase flow.");
_L6:
        return;
_L4:
        if (kC.kX == null)
        {
            eu.D("PlayStorePurchaseListener is not set.");
            return;
        }
        if (kC.kZ == null)
        {
            eu.D("PlayStorePurchaseVerifier is not initialized.");
            return;
        }
        boolean flag = kC.kX.isValidPurchase(s);
        if (!flag) goto _L6; else goto _L5
_L5:
        cu.a(kC.kO, kC.kQ.sz, new cq(ct1, kC.kX, kC.kZ, kC.kO));
        return;
        RemoteException remoteexception1;
        remoteexception1;
        eu.D("Could not start In-App purchase.");
        if (true) goto _L5; else goto _L2
_L2:
        try
        {
            kC.kY.a(ct1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.D("Could not start In-App purchase.");
        }
        return;
    }

    public void a(HashSet hashset)
    {
        kC.a(hashset);
    }

    public boolean a(ai ai1)
    {
        hm.ay("loadAd must be called on the main UI thread.");
        if (kC.kS == null) goto _L2; else goto _L1
_L1:
        eu.D("An ad request is already in progress. Aborting.");
_L4:
        return false;
_L2:
        if (kC.kT.mf && kC.kU != null)
        {
            eu.D("An interstitial is already loading. Aborting.");
            return false;
        }
        if (!al()) goto _L4; else goto _L3
_L3:
        ds.a a1;
        eu.B("Starting ad request.");
        if (!ai1.lV)
        {
            eu.B((new StringBuilder()).append("Use AdRequest.Builder.addTestDevice(\"").append(et.r(kC.kO)).append("\") to get test ads on this device.").toString());
        }
        kD.cancel();
        kC.lb = false;
        a1 = c(ai1);
        if (!kC.kT.mf) goto _L6; else goto _L5
_L5:
        ex ex2;
        ex ex3 = ex.a(kC.kO, kC.kT, false, false, kC.kP, kC.kQ);
        ex3.cb().a(this, null, this, this, true, this, this);
        ex2 = ex3;
_L8:
        kC.kS = dm.a(kC.kO, a1, kC.kP, ex2, kB, this);
        return true;
_L6:
        View view;
        ex ex1;
        view = kC.kM.getNextView();
        if (!(view instanceof ex))
        {
            break; /* Loop/switch isn't completed */
        }
        ex1 = (ex)view;
        ex1.a(kC.kO, kC.kT);
_L9:
        ex1.cb().a(this, this, this, this, false, this);
        ex2 = ex1;
        if (true) goto _L8; else goto _L7
_L7:
        if (view != null)
        {
            kC.kM.removeView(view);
        }
        ex1 = ex.a(kC.kO, kC.kT, false, false, kC.kP, kC.kQ);
        if (kC.kT.mg == null)
        {
            b(ex1);
        }
          goto _L9
        if (true) goto _L8; else goto _L10
_L10:
    }

    public void aa()
    {
        if (kC.kT.mf)
        {
            c(false);
        }
        kF = true;
        aj();
    }

    public void ab()
    {
        onAdClicked();
    }

    public void ac()
    {
        Z();
    }

    public void ad()
    {
        Y();
    }

    public void ae()
    {
        aa();
    }

    public void af()
    {
        if (kC.kU != null)
        {
            eu.D((new StringBuilder()).append("Mediation adapter ").append(kC.kU.nO).append(" refreshed, but mediation adapters should never refresh.").toString());
        }
        c(true);
        ak();
    }

    public void ag()
    {
        hm.ay("recordManualImpression must be called on the main UI thread.");
        if (kC.kU == null)
        {
            eu.D("Ad state was null when trying to ping manual tracking URLs.");
        } else
        {
            eu.z("Pinging manual tracking URLs.");
            if (kC.kU.qi != null)
            {
                eo.a(kC.kO, kC.kQ.sw, kC.kU.qi);
                return;
            }
        }
    }

    public void b(ai ai1)
    {
        android.view.ViewParent viewparent = kC.kM.getParent();
        if ((viewparent instanceof View) && ((View)viewparent).isShown() && eo.bQ() && !kF)
        {
            a(ai1);
            return;
        } else
        {
            eu.B("Ad is not visible. Not refreshing ad.");
            kD.d(ai1);
            return;
        }
    }

    public void b(boolean flag)
    {
        kC.lb = flag;
    }

    public void destroy()
    {
        hm.ay("destroy must be called on the main UI thread.");
        X();
        kC.kR = null;
        kC.kW = null;
        kD.cancel();
        kE.stop();
        stopLoading();
        if (kC.kM != null)
        {
            kC.kM.removeAllViews();
        }
        if (kC.kU != null && kC.kU.oy != null)
        {
            kC.kU.oy.destroy();
        }
        if (kC.kU == null || kC.kU.nN == null)
        {
            break MISSING_BLOCK_LABEL_138;
        }
        kC.kU.nN.destroy();
        return;
        RemoteException remoteexception;
        remoteexception;
        eu.D("Could not destroy mediation adapter.");
        return;
    }

    public boolean isReady()
    {
        hm.ay("isLoaded must be called on the main UI thread.");
        return kC.kS == null && kC.kU != null;
    }

    public void onAdClicked()
    {
        am();
    }

    public void onAppEvent(String s, String s1)
    {
        if (kC.kW == null)
        {
            break MISSING_BLOCK_LABEL_24;
        }
        kC.kW.onAppEvent(s, s1);
        return;
        RemoteException remoteexception;
        remoteexception;
        eu.c("Could not call the AppEventListener.", remoteexception);
        return;
    }

    public void pause()
    {
        hm.ay("pause must be called on the main UI thread.");
        if (kC.kU != null)
        {
            eo.a(kC.kU.oy);
        }
        if (kC.kU != null && kC.kU.nN != null)
        {
            try
            {
                kC.kU.nN.pause();
            }
            catch (RemoteException remoteexception)
            {
                eu.D("Could not pause mediation adapter.");
            }
        }
        kE.pause();
        kD.pause();
    }

    public void resume()
    {
        hm.ay("resume must be called on the main UI thread.");
        if (kC.kU != null)
        {
            eo.b(kC.kU.oy);
        }
        if (kC.kU != null && kC.kU.nN != null)
        {
            try
            {
                kC.kU.nN.resume();
            }
            catch (RemoteException remoteexception)
            {
                eu.D("Could not resume mediation adapter.");
            }
        }
        kD.resume();
        kE.resume();
    }

    public void showInterstitial()
    {
        hm.ay("showInterstitial must be called on the main UI thread.");
        if (!kC.kT.mf)
        {
            eu.D("Cannot call showInterstitial on a banner ad.");
            return;
        }
        if (kC.kU == null)
        {
            eu.D("The interstitial has not loaded.");
            return;
        }
        if (kC.kU.oy.ce())
        {
            eu.D("The interstitial is already showing.");
            return;
        }
        kC.kU.oy.q(true);
        if (kC.kU.oy.cb().cj() || kC.kU.ry != null)
        {
            ac ac1 = kE.a(kC.kT, kC.kU);
            if (kC.kU.oy.cb().cj() && ac1 != null)
            {
                ac1.a(new x(kC.kU.oy));
            }
        }
        if (kC.kU.qg)
        {
            try
            {
                kC.kU.nN.showInterstitial();
                return;
            }
            catch (RemoteException remoteexception)
            {
                eu.c("Could not show interstitial.", remoteexception);
            }
            an();
            return;
        }
        v v1 = new v(kC.lb, false);
        if (kC.kO instanceof Activity)
        {
            Window window = ((Activity)kC.kO).getWindow();
            Rect rect = new Rect();
            Rect rect1 = new Rect();
            window.getDecorView().getGlobalVisibleRect(rect);
            window.getDecorView().getWindowVisibleDisplayFrame(rect1);
            if (rect.bottom != 0 && rect1.bottom != 0)
            {
                boolean flag = kC.lb;
                ch ch1;
                boolean flag1;
                if (rect.top == rect1.top)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                v1 = new v(flag, flag1);
            }
        }
        ch1 = new ch(this, this, this, kC.kU.oy, kC.kU.orientation, kC.kQ, kC.kU.ql, v1);
        cf.a(kC.kO, ch1);
    }

    public void stopLoading()
    {
        hm.ay("stopLoading must be called on the main UI thread.");
        if (kC.kU != null)
        {
            kC.kU.oy.stopLoading();
            kC.kU = null;
        }
        if (kC.kS != null)
        {
            kC.kS.cancel();
        }
    }
}
