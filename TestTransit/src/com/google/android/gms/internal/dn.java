// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.internal:
//            em, du, ds, al, 
//            bk, bq, bm, et, 
//            eu, k, g, dp, 
//            ef, ex, do, eo, 
//            bt, ey

public class dn extends em
    implements dp.a, ey.a
{
    private static final class a extends Exception
    {

        private final int pJ;

        public int getErrorCode()
        {
            return pJ;
        }

        public a(String s, int i)
        {
            super(s);
            pJ = i;
        }
    }


    private final bt kB;
    private final ex lN;
    private final Object ls = new Object();
    private final Context mContext;
    private bm nf;
    private final k pA;
    private em pB;
    private du pC;
    private boolean pD;
    private bk pE;
    private bq pF;
    private final dm.a px;
    private final Object py = new Object();
    private final ds.a pz;

    public dn(Context context, ds.a a1, k k1, ex ex1, bt bt, dm.a a2)
    {
        pD = false;
        kB = bt;
        px = a2;
        lN = ex1;
        mContext = context;
        pz = a1;
        pA = k1;
    }

    private al a(ds ds1)
        throws a
    {
        if (pC.qj == null)
        {
            throw new a("The ad response must specify one of the supported ad sizes.", 0);
        }
        String as[] = pC.qj.split("x");
        if (as.length != 2)
        {
            throw new a((new StringBuilder()).append("Could not parse the ad size from the ad response: ").append(pC.qj).toString(), 0);
        }
        int i;
        int j;
        al aal[];
        int l;
        try
        {
            i = Integer.parseInt(as[0]);
            j = Integer.parseInt(as[1]);
        }
        catch (NumberFormatException numberformatexception)
        {
            throw new a((new StringBuilder()).append("Could not parse the ad size from the ad response: ").append(pC.qj).toString(), 0);
        }
        aal = ds1.kT.mg;
        l = aal.length;
        al al1;
        int j1;
        int k1;
        for (int i1 = 0; i1 < l; i1++)
        {
            al1 = aal[i1];
            float f1 = mContext.getResources().getDisplayMetrics().density;
            if (al1.width == -1)
            {
                j1 = (int)((float)al1.widthPixels / f1);
            } else
            {
                j1 = al1.width;
            }
            if (al1.height == -2)
            {
                k1 = (int)((float)al1.heightPixels / f1);
            } else
            {
                k1 = al1.height;
            }
            if (i == j1 && j == k1)
            {
                return new al(al1, ds1.kT.mg);
            }
        }

        throw new a((new StringBuilder()).append("The ad size from the ad response was not one of the requested sizes: ").append(pC.qj).toString(), 0);
    }

    static Object a(dn dn1)
    {
        return dn1.ls;
    }

    private void a(ds ds1, long l)
        throws a
    {
        synchronized (py)
        {
            pE = new bk(mContext, ds1, kB, nf);
        }
        pF = pE.a(l, 60000L);
        switch (pF.nL)
        {
        default:
            throw new a((new StringBuilder()).append("Unexpected mediation result: ").append(pF.nL).toString(), 0);

        case 1: // '\001'
            throw new a("No fill from any mediation ad networks.", 3);

        case 0: // '\0'
            return;
        }
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    static dm.a b(dn dn1)
    {
        return dn1.px;
    }

    private void bn()
        throws a
    {
        if (pC.errorCode != -3)
        {
            if (TextUtils.isEmpty(pC.qe))
            {
                throw new a("No fill from ad server.", 3);
            }
            if (pC.qg)
            {
                try
                {
                    nf = new bm(pC.qe);
                    return;
                }
                catch (JSONException jsonexception)
                {
                    throw new a((new StringBuilder()).append("Could not parse mediation config: ").append(pC.qe).toString(), 0);
                }
            }
        }
    }

    static du c(dn dn1)
    {
        return dn1.pC;
    }

    private boolean c(long l)
        throws a
    {
        long l1 = 60000L - (SystemClock.elapsedRealtime() - l);
        if (l1 <= 0L)
        {
            return false;
        }
        try
        {
            ls.wait(l1);
        }
        catch (InterruptedException interruptedexception)
        {
            throw new a("Ad request cancelled.", -1);
        }
        return true;
    }

    static ex d(dn dn1)
    {
        return dn1.lN;
    }

    private void e(long l)
        throws a
    {
        et.sv.post(new Runnable() {

            final dn pG;

            public void run()
            {
label0:
                {
                    synchronized (dn.a(pG))
                    {
                        if (dn.c(pG).errorCode == -2)
                        {
                            break label0;
                        }
                    }
                    return;
                }
                dn.d(pG).cb().a(pG);
                if (dn.c(pG).errorCode != -3)
                {
                    break MISSING_BLOCK_LABEL_119;
                }
                eu.C((new StringBuilder()).append("Loading URL in WebView: ").append(dn.c(pG).oA).toString());
                dn.d(pG).loadUrl(dn.c(pG).oA);
_L1:
                obj;
                JVM INSTR monitorexit ;
                return;
                exception;
                obj;
                JVM INSTR monitorexit ;
                throw exception;
                eu.C("Loading HTML in WebView.");
                dn.d(pG).loadDataWithBaseURL(eo.v(dn.c(pG).oA), dn.c(pG).qe, "text/html", "UTF-8", null);
                  goto _L1
            }

            
            {
                pG = dn.this;
                super();
            }
        });
        h(l);
    }

    private void g(long l)
        throws a
    {
        do
        {
            if (!c(l))
            {
                throw new a("Timed out waiting for ad response.", 2);
            }
        } while (pC == null);
        synchronized (py)
        {
            pB = null;
        }
        if (pC.errorCode != -2 && pC.errorCode != -3)
        {
            throw new a((new StringBuilder()).append("There was a problem getting an ad response. ErrorCode: ").append(pC.errorCode).toString(), pC.errorCode);
        } else
        {
            return;
        }
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void h(long l)
        throws a
    {
        do
        {
            if (!c(l))
            {
                throw new a("Timed out waiting for WebView to finish loading.", 2);
            }
        } while (!pD);
    }

    public void a(du du1)
    {
        synchronized (ls)
        {
            eu.z("Received ad response.");
            pC = du1;
            ls.notify();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void a(ex ex1)
    {
        synchronized (ls)
        {
            eu.z("WebView finished loading.");
            pD = true;
            ls.notify();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void bh()
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        ds ds1;
        eu.z("AdLoaderBackgroundTask started.");
        g g1 = pA.z();
        String s = g1.b(mContext);
        String s1 = g1.a(mContext);
        ds1 = new ds(pz, s, s1);
        al al1;
        int i;
        long l;
        al1 = null;
        i = -2;
        l = -1L;
        long l3;
        em em1;
        l3 = SystemClock.elapsedRealtime();
        em1 = dp.a(mContext, ds1, this);
        Object obj1 = py;
        obj1;
        JVM INSTR monitorenter ;
        pB = em1;
        if (pB == null)
        {
            throw new a("Could not start the ad request service.", 0);
        }
          goto _L1
        Exception exception2;
        exception2;
        obj1;
        JVM INSTR monitorexit ;
        throw exception2;
        a a1;
        a1;
        i = a1.getErrorCode();
        if (i != 3 && i != -1) goto _L3; else goto _L2
_L2:
        eu.B(a1.getMessage());
_L16:
        if (pC != null) goto _L5; else goto _L4
_L4:
        pC = new du(i);
_L17:
        et.sv.post(new Runnable() {

            final dn pG;

            public void run()
            {
                pG.onStop();
            }

            
            {
                pG = dn.this;
                super();
            }
        });
        long l1;
        al al2;
        l1 = l;
        al2 = al1;
_L23:
        boolean flag = TextUtils.isEmpty(pC.qo);
        if (flag) goto _L7; else goto _L6
_L6:
        JSONObject jsonobject;
        String s4 = pC.qo;
        jsonobject = new JSONObject(s4);
_L18:
        ai ai;
        ex ex1;
        java.util.List list;
        java.util.List list1;
        java.util.List list2;
        int j;
        long l2;
        String s2;
        boolean flag1;
        ai = ds1.pX;
        ex1 = lN;
        list = pC.nt;
        list1 = pC.nu;
        list2 = pC.qi;
        j = pC.orientation;
        l2 = pC.nx;
        s2 = ds1.qa;
        flag1 = pC.qg;
        if (pF == null) goto _L9; else goto _L8
_L8:
        bl bl = pF.nM;
_L19:
        if (pF == null) goto _L11; else goto _L10
_L10:
        bu bu = pF.nN;
_L20:
        if (pF == null) goto _L13; else goto _L12
_L12:
        String s3 = pF.nO;
_L21:
        bm bm1 = nf;
        if (pF == null) goto _L15; else goto _L14
_L14:
        bo bo = pF.nP;
_L22:
        ef ef1 = new ef(ai, ex1, list, i, list1, list2, j, l2, s2, flag1, bl, bu, s3, bm1, bo, pC.qh, al2, pC.qf, l1, pC.qk, pC.ql, jsonobject);
        et.sv.post(new Runnable(ef1) {

            final dn pG;
            final ef pH;

            public void run()
            {
                synchronized (dn.a(pG))
                {
                    dn.b(pG).a(pH);
                }
                return;
                exception3;
                obj2;
                JVM INSTR monitorexit ;
                throw exception3;
            }

            
            {
                pG = dn.this;
                pH = ef1;
                super();
            }
        });
        obj;
        JVM INSTR monitorexit ;
        return;
_L1:
        obj1;
        JVM INSTR monitorexit ;
        al aal[];
        g(l3);
        l = SystemClock.elapsedRealtime();
        bn();
        aal = ds1.kT.mg;
        al1 = null;
        if (aal == null)
        {
            break MISSING_BLOCK_LABEL_523;
        }
        al1 = a(ds1);
        if (pC.qg)
        {
            a(ds1, l3);
            break MISSING_BLOCK_LABEL_651;
        }
        if (pC.qm)
        {
            f(l3);
            break MISSING_BLOCK_LABEL_651;
        }
        break MISSING_BLOCK_LABEL_568;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        e(l3);
        break MISSING_BLOCK_LABEL_651;
_L3:
        eu.D(a1.getMessage());
          goto _L16
_L5:
        pC = new du(i, pC.nx);
          goto _L17
        Exception exception1;
        exception1;
        eu.b("Error parsing the JSON for Active View.", exception1);
_L7:
        jsonobject = null;
          goto _L18
_L9:
        bl = null;
          goto _L19
_L11:
        bu = null;
          goto _L20
_L13:
        s3 = null;
          goto _L21
_L15:
        bo = null;
          goto _L22
        l1 = l;
        al2 = al1;
          goto _L23
    }

    protected void f(long l)
        throws a
    {
        al al1 = lN.V();
        int i;
        int j;
        do do1;
        if (al1.mf)
        {
            i = mContext.getResources().getDisplayMetrics().widthPixels;
            j = mContext.getResources().getDisplayMetrics().heightPixels;
        } else
        {
            i = al1.widthPixels;
            j = al1.heightPixels;
        }
        do1 = new do(this, lN, i, j);
        et.sv.post(new Runnable(do1) {

            final dn pG;
            final do pI;

            public void run()
            {
label0:
                {
                    synchronized (dn.a(pG))
                    {
                        if (dn.c(pG).errorCode == -2)
                        {
                            break label0;
                        }
                    }
                    return;
                }
                dn.d(pG).cb().a(pG);
                pI.b(dn.c(pG));
                obj;
                JVM INSTR monitorexit ;
                return;
                exception;
                obj;
                JVM INSTR monitorexit ;
                throw exception;
            }

            
            {
                pG = dn.this;
                pI = do1;
                super();
            }
        });
        h(l);
        if (do1.bq())
        {
            eu.z("Ad-Network indicated no fill with passback URL.");
            throw new a("AdNetwork sent passback url", 3);
        }
        if (!do1.br())
        {
            throw new a("AdNetwork timed out", 2);
        } else
        {
            return;
        }
    }

    public void onStop()
    {
        synchronized (py)
        {
            if (pB != null)
            {
                pB.cancel();
            }
            lN.stopLoading();
            eo.a(lN);
            if (pE != null)
            {
                pE.cancel();
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
