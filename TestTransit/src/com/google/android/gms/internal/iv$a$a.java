// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, mc, lz, ly, 
//            iv, me

public static final class iS extends ma
{

    private static volatile viewId Uz[];
    public String UA;
    public String UB;
    public int viewId;

    public static iS[] iR()
    {
        if (Uz == null)
        {
            synchronized (mc.ana)
            {
                if (Uz == null)
                {
                    Uz = new Uz[0];
                }
            }
        }
        return Uz;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void a(lz lz1)
        throws IOException
    {
        if (!UA.equals(""))
        {
            lz1.b(1, UA);
        }
        if (!UB.equals(""))
        {
            lz1.b(2, UB);
        }
        if (viewId != 0)
        {
            lz1.p(3, viewId);
        }
        super.a(lz1);
    }

    public me b(ly ly1)
        throws IOException
    {
        return o(ly1);
    }

    protected int c()
    {
        int i = super.c();
        if (!UA.equals(""))
        {
            i += lz.h(1, UA);
        }
        if (!UB.equals(""))
        {
            i += lz.h(2, UB);
        }
        if (viewId != 0)
        {
            i += lz.r(3, viewId);
        }
        return i;
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
        flag = obj instanceof viewId;
        flag1 = false;
        if (!flag) goto _L4; else goto _L3
_L3:
        viewId viewid = (viewId)obj;
        if (UA != null) goto _L6; else goto _L5
_L5:
        String s1;
        s1 = viewid.UA;
        flag1 = false;
        if (s1 != null) goto _L4; else goto _L7
_L7:
        if (UB != null) goto _L9; else goto _L8
_L8:
        String s;
        s = viewid.UB;
        flag1 = false;
        if (s != null) goto _L4; else goto _L10
_L10:
        int i;
        int j;
        i = viewId;
        j = viewid.viewId;
        flag1 = false;
        if (i != j) goto _L4; else goto _L11
_L11:
        boolean flag2;
        if (amX != null && !amX.isEmpty())
        {
            break MISSING_BLOCK_LABEL_172;
        }
        if (viewid.amX == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag2 = viewid.amX.isEmpty();
        flag1 = false;
        if (!flag2) goto _L4; else goto _L12
_L12:
        return true;
_L6:
        if (!UA.equals(viewid.UA))
        {
            return false;
        }
          goto _L7
_L9:
        if (!UB.equals(viewid.UB))
        {
            return false;
        }
          goto _L10
        return amX.equals(viewid.amX);
          goto _L7
    }

    public int hashCode()
    {
        int i;
        int j;
        int k;
        int l;
        List list;
        int i1;
        if (UA == null)
        {
            i = 0;
        } else
        {
            i = UA.hashCode();
        }
        j = 31 * (i + 527);
        if (UB == null)
        {
            k = 0;
        } else
        {
            k = UB.hashCode();
        }
        l = 31 * (31 * (k + j) + viewId);
        list = amX;
        i1 = 0;
        if (list != null)
        {
            boolean flag = amX.isEmpty();
            i1 = 0;
            if (!flag)
            {
                i1 = amX.hashCode();
            }
        }
        return l + i1;
    }

    public amX iS()
    {
        UA = "";
        UB = "";
        viewId = 0;
        amX = null;
        anb = -1;
        return this;
    }

    public anb o(ly ly1)
        throws IOException
    {
        do
        {
            int i = ly1.nB();
            switch (i)
            {
            default:
                if (a(ly1, i))
                {
                    continue;
                }
                // fall through

            case 0: // '\0'
                return this;

            case 10: // '\n'
                UA = ly1.readString();
                break;

            case 18: // '\022'
                UB = ly1.readString();
                break;

            case 24: // '\030'
                viewId = ly1.nE();
                break;
            }
        } while (true);
    }

    public ()
    {
        iS();
    }
}
