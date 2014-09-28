// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

// Referenced classes of package com.google.android.gms.internal:
//            ad, ac, al, ef

public final class ab
    implements ad
{

    private final Object ls = new Object();
    private WeakHashMap lt;
    private ArrayList lu;

    public ab()
    {
        lt = new WeakHashMap();
        lu = new ArrayList();
    }

    public ac a(al al, ef ef)
    {
label0:
        {
            ac ac2;
            synchronized (ls)
            {
                if (!c(ef))
                {
                    break label0;
                }
                ac2 = (ac)lt.get(ef);
            }
            return ac2;
        }
        ac ac1;
        ac1 = new ac(al, ef);
        ac1.a(this);
        lt.put(ef, ac1);
        lu.add(ac1);
        obj;
        JVM INSTR monitorexit ;
        return ac1;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void a(ac ac1)
    {
        synchronized (ls)
        {
            if (!ac1.az())
            {
                lu.remove(ac1);
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean c(ef ef)
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        ac ac1 = (ac)lt.get(ef);
        if (ac1 == null) goto _L2; else goto _L1
_L1:
        if (!ac1.az()) goto _L2; else goto _L3
_L3:
        boolean flag = true;
_L5:
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
_L2:
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public void d(ef ef)
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        ac ac1 = (ac)lt.get(ef);
        if (ac1 == null)
        {
            break MISSING_BLOCK_LABEL_30;
        }
        ac1.ax();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void pause()
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        for (Iterator iterator = lu.iterator(); iterator.hasNext(); ((ac)iterator.next()).pause()) { }
        break MISSING_BLOCK_LABEL_44;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        obj;
        JVM INSTR monitorexit ;
    }

    public void resume()
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        for (Iterator iterator = lu.iterator(); iterator.hasNext(); ((ac)iterator.next()).resume()) { }
        break MISSING_BLOCK_LABEL_44;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        obj;
        JVM INSTR monitorexit ;
    }

    public void stop()
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        for (Iterator iterator = lu.iterator(); iterator.hasNext(); ((ac)iterator.next()).stop()) { }
        break MISSING_BLOCK_LABEL_44;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        obj;
        JVM INSTR monitorexit ;
    }
}
