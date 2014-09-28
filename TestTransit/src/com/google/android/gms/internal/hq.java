// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class hq
{

    private final LinkedHashMap GM;
    private int GN;
    private int GO;
    private int GP;
    private int GQ;
    private int GR;
    private int GS;
    private int size;

    public hq(int i)
    {
        if (i <= 0)
        {
            throw new IllegalArgumentException("maxSize <= 0");
        } else
        {
            GN = i;
            GM = new LinkedHashMap(0, 0.75F, true);
            return;
        }
    }

    private int c(Object obj, Object obj1)
    {
        int i = sizeOf(obj, obj1);
        if (i < 0)
        {
            throw new IllegalStateException((new StringBuilder()).append("Negative size: ").append(obj).append("=").append(obj1).toString());
        } else
        {
            return i;
        }
    }

    protected Object create(Object obj)
    {
        return null;
    }

    protected void entryRemoved(boolean flag, Object obj, Object obj1, Object obj2)
    {
    }

    public final void evictAll()
    {
        trimToSize(-1);
    }

    public final Object get(Object obj)
    {
        if (obj == null)
        {
            throw new NullPointerException("key == null");
        }
        this;
        JVM INSTR monitorenter ;
        Object obj1 = GM.get(obj);
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_43;
        }
        GR = 1 + GR;
        this;
        JVM INSTR monitorexit ;
        return obj1;
        GS = 1 + GS;
        this;
        JVM INSTR monitorexit ;
        Object obj2;
        obj2 = create(obj);
        if (obj2 == null)
        {
            return null;
        }
        break MISSING_BLOCK_LABEL_74;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        this;
        JVM INSTR monitorenter ;
        Object obj3;
        GP = 1 + GP;
        obj3 = GM.put(obj, obj2);
        if (obj3 == null)
        {
            break MISSING_BLOCK_LABEL_134;
        }
        GM.put(obj, obj3);
_L1:
        this;
        JVM INSTR monitorexit ;
        Exception exception1;
        if (obj3 != null)
        {
            entryRemoved(false, obj, obj2, obj3);
            return obj3;
        } else
        {
            trimToSize(GN);
            return obj2;
        }
        size = size + c(obj, obj2);
          goto _L1
        exception1;
        this;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public final Object put(Object obj, Object obj1)
    {
        if (obj == null || obj1 == null)
        {
            throw new NullPointerException("key == null || value == null");
        }
        this;
        JVM INSTR monitorenter ;
        Object obj2;
        GO = 1 + GO;
        size = size + c(obj, obj1);
        obj2 = GM.put(obj, obj1);
        if (obj2 == null)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        size = size - c(obj, obj2);
        this;
        JVM INSTR monitorexit ;
        if (obj2 != null)
        {
            entryRemoved(false, obj, obj2, obj1);
        }
        trimToSize(GN);
        return obj2;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final int size()
    {
        this;
        JVM INSTR monitorenter ;
        int i = size;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    protected int sizeOf(Object obj, Object obj1)
    {
        return 1;
    }

    public final String toString()
    {
        this;
        JVM INSTR monitorenter ;
        int i = GR + GS;
        int j;
        j = 0;
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_28;
        }
        j = (100 * GR) / i;
        String s;
        Object aobj[] = new Object[4];
        aobj[0] = Integer.valueOf(GN);
        aobj[1] = Integer.valueOf(GR);
        aobj[2] = Integer.valueOf(GS);
        aobj[3] = Integer.valueOf(j);
        s = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", aobj);
        this;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    public void trimToSize(int i)
    {
_L2:
        this;
        JVM INSTR monitorenter ;
        if (size < 0 || GM.isEmpty() && size != 0)
        {
            throw new IllegalStateException((new StringBuilder()).append(getClass().getName()).append(".sizeOf() is reporting inconsistent results!").toString());
        }
        break MISSING_BLOCK_LABEL_64;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if (size > i && !GM.isEmpty())
        {
            break MISSING_BLOCK_LABEL_85;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        Object obj1;
        java.util.Map.Entry entry = (java.util.Map.Entry)GM.entrySet().iterator().next();
        obj = entry.getKey();
        obj1 = entry.getValue();
        GM.remove(obj);
        size = size - c(obj, obj1);
        GQ = 1 + GQ;
        this;
        JVM INSTR monitorexit ;
        entryRemoved(true, obj, obj1, null);
        if (true) goto _L2; else goto _L1
_L1:
    }
}
