// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

// Referenced classes of package com.google.android.gms.internal:
//            eh, ei, ai

public class eg
{
    private static final class a
    {

        private long rN;
        private long rO;

        public long bE()
        {
            return rO;
        }

        public void bF()
        {
            rO = SystemClock.elapsedRealtime();
        }

        public void bG()
        {
            rN = SystemClock.elapsedRealtime();
        }

        public Bundle toBundle()
        {
            Bundle bundle = new Bundle();
            bundle.putLong("topen", rN);
            bundle.putLong("tclose", rO);
            return bundle;
        }

        public a()
        {
            rN = -1L;
            rO = -1L;
        }
    }


    private final Object ls;
    private boolean qO;
    private final eh rD;
    private final LinkedList rE;
    private final String rF;
    private final String rG;
    private long rH;
    private long rI;
    private long rJ;
    private long rK;
    private long rL;
    private long rM;

    public eg(eh eh1, String s, String s1)
    {
        ls = new Object();
        rH = -1L;
        rI = -1L;
        qO = false;
        rJ = -1L;
        rK = 0L;
        rL = -1L;
        rM = -1L;
        rD = eh1;
        rF = s;
        rG = s1;
        rE = new LinkedList();
    }

    public eg(String s, String s1)
    {
        this(eh.bH(), s, s1);
    }

    public void bB()
    {
        synchronized (ls)
        {
            if (rM != -1L && rI == -1L)
            {
                rI = SystemClock.elapsedRealtime();
                rD.a(this);
            }
            rD;
            eh.bK().bB();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void bC()
    {
        synchronized (ls)
        {
            if (rM != -1L)
            {
                a a1 = new a();
                a1.bG();
                rE.add(a1);
                rK = 1L + rK;
                rD;
                eh.bK().bC();
                rD.a(this);
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void bD()
    {
        synchronized (ls)
        {
            if (rM != -1L && !rE.isEmpty())
            {
                a a1 = (a)rE.getLast();
                if (a1.bE() == -1L)
                {
                    a1.bF();
                    rD.a(this);
                }
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void f(ai ai)
    {
        synchronized (ls)
        {
            rL = SystemClock.elapsedRealtime();
            rD;
            eh.bK().b(ai, rL);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void j(long l)
    {
        synchronized (ls)
        {
            rM = l;
            if (rM != -1L)
            {
                rD.a(this);
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void k(long l)
    {
        synchronized (ls)
        {
            if (rM != -1L)
            {
                rH = l;
                rD.a(this);
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void n(boolean flag)
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        if (rM == -1L)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        rJ = SystemClock.elapsedRealtime();
        if (flag)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        rI = rJ;
        rD.a(this);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void o(boolean flag)
    {
        synchronized (ls)
        {
            if (rM != -1L)
            {
                qO = flag;
                rD.a(this);
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Bundle toBundle()
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        Bundle bundle;
        ArrayList arraylist;
        bundle = new Bundle();
        bundle.putString("seqnum", rF);
        bundle.putString("slotid", rG);
        bundle.putBoolean("ismediation", qO);
        bundle.putLong("treq", rL);
        bundle.putLong("tresponse", rM);
        bundle.putLong("timp", rI);
        bundle.putLong("tload", rJ);
        bundle.putLong("pcc", rK);
        bundle.putLong("tfetch", rH);
        arraylist = new ArrayList();
        for (Iterator iterator = rE.iterator(); iterator.hasNext(); arraylist.add(((a)iterator.next()).toBundle())) { }
        break MISSING_BLOCK_LABEL_160;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        bundle.putParcelableArrayList("tclick", arraylist);
        obj;
        JVM INSTR monitorexit ;
        return bundle;
    }
}
