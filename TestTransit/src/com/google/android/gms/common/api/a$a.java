// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.api;

import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.internal.hg;
import com.google.android.gms.internal.hm;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.gms.common.api:
//            PendingResult, Result, Status, a, 
//            ResultCallback

public static abstract class Dq
    implements PendingResult, ingResult
{

    private final Object Dp;
    private  Dq;
    private final ArrayList Dr;
    private ResultCallback Ds;
    private volatile Result Dt;
    private volatile boolean Du;
    private boolean Dv;
    private boolean Dw;
    private hg Dx;
    private final CountDownLatch kK;

    static void a(ingResult ingresult)
    {
        ingresult.eF();
    }

    private void c(Result result)
    {
        Dt = result;
        Dx = null;
        kK.countDown();
        Status status = Dt.getStatus();
        if (Ds != null)
        {
            Dq.eH();
            if (!Dv)
            {
                Dq.a(Ds, eC());
            }
        }
        for (Iterator iterator = Dr.iterator(); iterator.hasNext(); ((ingResult.a)iterator.next()).n(status)) { }
        Dr.clear();
    }

    private Result eC()
    {
        Object obj = Dp;
        obj;
        JVM INSTR monitorenter ;
        Exception exception;
        boolean flag;
        Result result;
        if (!Du)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        hm.a(flag, "Result has already been consumed.");
        hm.a(isReady(), "Result is not ready.");
        result = Dt;
        eD();
        obj;
        JVM INSTR monitorexit ;
        return result;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void eE()
    {
        synchronized (Dp)
        {
            if (!isReady())
            {
                b(c(Status.Eo));
                Dw = true;
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void eF()
    {
        synchronized (Dp)
        {
            if (!isReady())
            {
                b(c(Status.Eq));
                Dw = true;
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void a(ingResult.a a1)
    {
        boolean flag;
        Object obj;
        Exception exception;
        if (!Du)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        hm.a(flag, "Result has already been consumed.");
        obj = Dp;
        obj;
        JVM INSTR monitorenter ;
        if (!isReady())
        {
            break MISSING_BLOCK_LABEL_47;
        }
        a1.n(Dt.getStatus());
_L2:
        return;
        Dr.add(a1);
        if (true) goto _L2; else goto _L1
_L1:
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void a(Dr dr)
    {
        Dq = dr;
    }

    protected final void a(hg hg1)
    {
        synchronized (Dp)
        {
            Dx = hg1;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void a(Object obj)
    {
        b((Result)obj);
    }

    public final Result await()
    {
        boolean flag = true;
        boolean flag1;
        if (Looper.myLooper() != Looper.getMainLooper())
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        hm.a(flag1, "await must not be called on the UI thread");
        if (Du)
        {
            flag = false;
        }
        hm.a(flag, "Result has already been consumed");
        try
        {
            kK.await();
        }
        catch (InterruptedException interruptedexception)
        {
            eE();
        }
        hm.a(isReady(), "Result is not ready.");
        return eC();
    }

    public final Result await(long l, TimeUnit timeunit)
    {
        boolean flag = true;
        boolean flag1;
        if (l <= 0L || Looper.myLooper() != Looper.getMainLooper())
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        hm.a(flag1, "await must not be called on the UI thread when time is greater than zero.");
        if (Du)
        {
            flag = false;
        }
        hm.a(flag, "Result has already been consumed.");
        try
        {
            if (!kK.await(l, timeunit))
            {
                eF();
            }
        }
        catch (InterruptedException interruptedexception)
        {
            eE();
        }
        hm.a(isReady(), "Result is not ready.");
        return eC();
    }

    public final void b(Result result)
    {
        boolean flag;
label0:
        {
            flag = true;
            synchronized (Dp)
            {
                if (!Dw && !Dv)
                {
                    break label0;
                }
                com.google.android.gms.common.api.a.a(result);
            }
            return;
        }
        boolean flag1;
        if (!isReady())
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        hm.a(flag1, "Results have already been set");
        if (Du)
        {
            flag = false;
        }
        hm.a(flag, "Result has already been consumed");
        c(result);
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected abstract Result c(Status status);

    public void cancel()
    {
label0:
        {
            synchronized (Dp)
            {
                if (!Dv && !Du)
                {
                    break label0;
                }
            }
            return;
        }
        hg hg1 = Dx;
        if (hg1 == null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        try
        {
            Dx.cancel();
        }
        catch (RemoteException remoteexception) { }
        com.google.android.gms.common.api.a.a(Dt);
        Ds = null;
        Dv = true;
        c(c(Status.Er));
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void eD()
    {
        Du = true;
        Dt = null;
        Ds = null;
    }

    public boolean isCanceled()
    {
        boolean flag;
        synchronized (Dp)
        {
            flag = Dv;
        }
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final boolean isReady()
    {
        return kK.getCount() == 0L;
    }

    public final void setResultCallback(ResultCallback resultcallback)
    {
label0:
        {
            boolean flag;
            if (!Du)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            hm.a(flag, "Result has already been consumed.");
            synchronized (Dp)
            {
                if (!isCanceled())
                {
                    break label0;
                }
            }
            return;
        }
        if (!isReady())
        {
            break MISSING_BLOCK_LABEL_61;
        }
        Dq.a(resultcallback, eC());
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        Ds = resultcallback;
          goto _L1
    }

    public final void setResultCallback(ResultCallback resultcallback, long l, TimeUnit timeunit)
    {
label0:
        {
            boolean flag;
            if (!Du)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            hm.a(flag, "Result has already been consumed.");
            synchronized (Dp)
            {
                if (!isCanceled())
                {
                    break label0;
                }
            }
            return;
        }
        if (!isReady())
        {
            break MISSING_BLOCK_LABEL_68;
        }
        Dq.a(resultcallback, eC());
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        Ds = resultcallback;
        Dq.a(this, timeunit.toMillis(l));
          goto _L1
    }

    ltCallback()
    {
        Dp = new Object();
        kK = new CountDownLatch(1);
        Dr = new ArrayList();
    }

    public Dr(Looper looper)
    {
        Dp = new Object();
        kK = new CountDownLatch(1);
        Dr = new ArrayList();
        Dq = new <init>(looper);
    }

    public <init>(<init> <init>1)
    {
        Dp = new Object();
        kK = new CountDownLatch(1);
        Dr = new ArrayList();
        Dq = <init>1;
    }
}
