// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, mc, lz, ly, 
//            c, me

public static final class j extends ma
{

    private static volatile anb eU[];
    public int key;
    public int value;

    public static j[] i()
    {
        if (eU == null)
        {
            synchronized (mc.ana)
            {
                if (eU == null)
                {
                    eU = new eU[0];
                }
            }
        }
        return eU;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void a(lz lz1)
        throws IOException
    {
        lz1.p(1, key);
        lz1.p(2, value);
        super.a(lz1);
    }

    public me b(ly ly1)
        throws IOException
    {
        return f(ly1);
    }

    protected int c()
    {
        return super.c() + lz.r(1, key) + lz.r(2, value);
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        boolean flag1 = true;
_L4:
        return flag1;
_L2:
        boolean flag;
        flag = obj instanceof value;
        flag1 = false;
        if (!flag) goto _L4; else goto _L3
_L3:
        value value1;
        int k;
        int l;
        value1 = (value)obj;
        k = key;
        l = value1.key;
        flag1 = false;
        if (k != l) goto _L4; else goto _L5
_L5:
        int i1;
        int j1;
        i1 = value;
        j1 = value1.value;
        flag1 = false;
        if (i1 != j1) goto _L4; else goto _L6
_L6:
        boolean flag2;
        if (amX != null && !amX.isEmpty())
        {
            break MISSING_BLOCK_LABEL_118;
        }
        if (value1.amX == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag2 = value1.amX.isEmpty();
        flag1 = false;
        if (!flag2) goto _L4; else goto _L7
_L7:
        return true;
        return amX.equals(value1.amX);
    }

    public amX f(ly ly1)
        throws IOException
    {
        do
        {
            int k = ly1.nB();
            switch (k)
            {
            default:
                if (a(ly1, k))
                {
                    continue;
                }
                // fall through

            case 0: // '\0'
                return this;

            case 8: // '\b'
                key = ly1.nE();
                break;

            case 16: // '\020'
                value = ly1.nE();
                break;
            }
        } while (true);
    }

    public int hashCode()
    {
        int k = 31 * (31 * (527 + key) + value);
        int l;
        if (amX == null || amX.isEmpty())
        {
            l = 0;
        } else
        {
            l = amX.hashCode();
        }
        return l + k;
    }

    public amX j()
    {
        key = 0;
        value = 0;
        amX = null;
        anb = -1;
        return this;
    }

    public ()
    {
        j();
    }
}
