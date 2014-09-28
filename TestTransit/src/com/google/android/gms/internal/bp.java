// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.dynamic.e;

// Referenced classes of package com.google.android.gms.internal:
//            bm, ev, al, bl, 
//            bu, eu, bt, bo, 
//            et, bq, ai

public final class bp
    implements bq.a
{

    private final bt kB;
    private final ai lh;
    private final Object ls = new Object();
    private final Context mContext;
    private final String nC;
    private final long nD;
    private final bl nE;
    private final al nF;
    private final ev nG;
    private bu nH;
    private int nI;

    public bp(Context context, String s, bt bt1, bm bm1, bl bl1, ai ai, al al1, 
            ev ev1)
    {
        nI = -2;
        mContext = context;
        long l;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(s))
        {
            nC = b(bl1);
        } else
        {
            nC = s;
        }
        kB = bt1;
        if (bm1.ns != -1L)
        {
            l = bm1.ns;
        } else
        {
            l = 10000L;
        }
        nD = l;
        nE = bl1;
        lh = ai;
        nF = al1;
        nG = ev1;
    }

    static bu a(bp bp1, bu bu1)
    {
        bp1.nH = bu1;
        return bu1;
    }

    static Object a(bp bp1)
    {
        return bp1.ls;
    }

    private void a(long l, long l1, long l2, long l3)
    {
        do
        {
            if (nI != -2)
            {
                return;
            }
            b(l, l1, l2, l3);
        } while (true);
    }

    private void a(bo bo1)
    {
        if (nG.sy >= 0x3e8fa0)
        {
            break MISSING_BLOCK_LABEL_97;
        }
        if (nF.mf)
        {
            nH.a(e.h(mContext), lh, nE.nq, bo1);
            return;
        }
        try
        {
            nH.a(e.h(mContext), nF, lh, nE.nq, bo1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not request ad from mediation adapter.", remoteexception);
        }
        g(5);
        return;
        if (nF.mf)
        {
            nH.a(e.h(mContext), lh, nE.nq, nE.nk, bo1);
            return;
        }
        nH.a(e.h(mContext), nF, lh, nE.nq, nE.nk, bo1);
        return;
    }

    static void a(bp bp1, bo bo1)
    {
        bp1.a(bo1);
    }

    private bu aP()
    {
        eu.B((new StringBuilder()).append("Instantiating mediation adapter: ").append(nC).toString());
        bu bu1;
        try
        {
            bu1 = kB.m(nC);
        }
        catch (RemoteException remoteexception)
        {
            eu.a((new StringBuilder()).append("Could not instantiate mediation adapter: ").append(nC).toString(), remoteexception);
            return null;
        }
        return bu1;
    }

    static int b(bp bp1)
    {
        return bp1.nI;
    }

    private String b(bl bl1)
    {
        if (!TextUtils.isEmpty(bl1.no) && com/google/android/gms/ads/mediation/customevent/CustomEvent.isAssignableFrom(Class.forName(bl1.no, false, com/google/android/gms/internal/bp.getClassLoader())))
        {
            return "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter";
        }
        break MISSING_BLOCK_LABEL_40;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        eu.D("Could not create custom event adapter.");
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }

    private void b(long l, long l1, long l2, long l3)
    {
        long l4 = SystemClock.elapsedRealtime();
        long l5 = l1 - (l4 - l);
        long l6 = l3 - (l4 - l2);
        if (l5 <= 0L || l6 <= 0L)
        {
            eu.B("Timed out waiting for adapter.");
            nI = 3;
            return;
        }
        try
        {
            ls.wait(Math.min(l5, l6));
            return;
        }
        catch (InterruptedException interruptedexception)
        {
            nI = -1;
        }
    }

    static bu c(bp bp1)
    {
        return bp1.aP();
    }

    static bu d(bp bp1)
    {
        return bp1.nH;
    }

    public bq b(long l, long l1)
    {
        bq bq1;
        synchronized (ls)
        {
            long l2 = SystemClock.elapsedRealtime();
            bo bo1 = new bo();
            et.sv.post(new Runnable(bo1) {

                final bo nJ;
                final bp nK;

                public void run()
                {
label0:
                    {
                        synchronized (bp.a(nK))
                        {
                            if (bp.b(nK) == -2)
                            {
                                break label0;
                            }
                        }
                        return;
                    }
                    bp.a(nK, bp.c(nK));
                    if (bp.d(nK) != null)
                    {
                        break MISSING_BLOCK_LABEL_66;
                    }
                    nK.g(4);
                    obj1;
                    JVM INSTR monitorexit ;
                    return;
                    exception1;
                    obj1;
                    JVM INSTR monitorexit ;
                    throw exception1;
                    nJ.a(nK);
                    bp.a(nK, nJ);
                    obj1;
                    JVM INSTR monitorexit ;
                }

            
            {
                nK = bp.this;
                nJ = bo1;
                super();
            }
            });
            a(l2, nD, l, l1);
            bq1 = new bq(nE, nH, nC, bo1, nI);
        }
        return bq1;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void cancel()
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        if (nH != null)
        {
            nH.destroy();
        }
_L1:
        nI = -1;
        ls.notify();
        return;
        RemoteException remoteexception;
        remoteexception;
        eu.c("Could not destroy mediation adapter.", remoteexception);
          goto _L1
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void g(int i)
    {
        synchronized (ls)
        {
            nI = i;
            ls.notify();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
