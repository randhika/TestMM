// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.Map;

// Referenced classes of package com.google.android.gms.internal:
//            eu, ex, eb, bc, 
//            el

public final class dz
{

    private ex lN;
    private final Object ls = new Object();
    private int pJ;
    private String qB;
    private eb qC;
    public final bc qD = new bc() {

        final dz qF;

        public void b(ex ex, Map map)
        {
            synchronized (dz.a(qF))
            {
                eb eb1 = new eb(map);
                eu.D((new StringBuilder()).append("Invalid ").append(eb1.getType()).append(" request error: ").append(eb1.by()).toString());
                dz.a(qF, 1);
                dz.a(qF).notify();
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

            
            {
                qF = dz.this;
                super();
            }
    };
    public final bc qE = new bc() {

        final dz qF;

        public void b(ex ex1, Map map)
        {
            Object obj = dz.a(qF);
            obj;
            JVM INSTR monitorenter ;
            eb eb1;
            String s1;
            eb1 = new eb(map);
            s1 = eb1.getUrl();
            if (s1 != null)
            {
                break MISSING_BLOCK_LABEL_40;
            }
            eu.D("URL missing in loadAdUrl GMSG.");
            obj;
            JVM INSTR monitorexit ;
            return;
            if (s1.contains("%40mediation_adapters%40"))
            {
                String s2 = s1.replaceAll("%40mediation_adapters%40", el.a(ex1.getContext(), (String)map.get("check_adapters"), dz.b(qF)));
                eu.C((new StringBuilder()).append("Ad request URL modified to ").append(s2).toString());
            }
            dz.a(qF, eb1);
            dz.a(qF).notify();
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

            
            {
                qF = dz.this;
                super();
            }
    };

    public dz(String s)
    {
        pJ = -2;
        qB = s;
    }

    static int a(dz dz1, int i)
    {
        dz1.pJ = i;
        return i;
    }

    static eb a(dz dz1, eb eb)
    {
        dz1.qC = eb;
        return eb;
    }

    static Object a(dz dz1)
    {
        return dz1.ls;
    }

    static String b(dz dz1)
    {
        return dz1.qB;
    }

    public void b(ex ex)
    {
        synchronized (ls)
        {
            lN = ex;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public eb bx()
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
_L1:
        int i;
        if (qC != null)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        i = pJ;
        if (i != -2)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        ls.wait();
          goto _L1
        InterruptedException interruptedexception;
        interruptedexception;
        eu.D("Ad request service was interrupted.");
        obj;
        JVM INSTR monitorexit ;
        return null;
        eb eb = qC;
        obj;
        JVM INSTR monitorexit ;
        return eb;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getErrorCode()
    {
        int i;
        synchronized (ls)
        {
            i = pJ;
        }
        return i;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
