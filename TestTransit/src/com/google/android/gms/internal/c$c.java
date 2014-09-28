// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, mc, lz, ly, 
//            c, me

public static final class g extends ma
{

    private static volatile amX eL[];
    public String eM;
    public long eN;
    public long eO;
    public boolean eP;
    public long eQ;

    public static g[] f()
    {
        if (eL == null)
        {
            synchronized (mc.ana)
            {
                if (eL == null)
                {
                    eL = new eL[0];
                }
            }
        }
        return eL;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void a(lz lz1)
        throws IOException
    {
        if (!eM.equals(""))
        {
            lz1.b(1, eM);
        }
        if (eN != 0L)
        {
            lz1.b(2, eN);
        }
        if (eO != 0x7fffffffL)
        {
            lz1.b(3, eO);
        }
        if (eP)
        {
            lz1.a(4, eP);
        }
        if (eQ != 0L)
        {
            lz1.b(5, eQ);
        }
        super.a(lz1);
    }

    public me b(ly ly1)
        throws IOException
    {
        return d(ly1);
    }

    protected int c()
    {
        int i = super.c();
        if (!eM.equals(""))
        {
            i += lz.h(1, eM);
        }
        if (eN != 0L)
        {
            i += lz.d(2, eN);
        }
        if (eO != 0x7fffffffL)
        {
            i += lz.d(3, eO);
        }
        if (eP)
        {
            i += lz.b(4, eP);
        }
        if (eQ != 0L)
        {
            i += lz.d(5, eQ);
        }
        return i;
    }

    public eQ d(ly ly1)
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
                eM = ly1.readString();
                break;

            case 16: // '\020'
                eN = ly1.nD();
                break;

            case 24: // '\030'
                eO = ly1.nD();
                break;

            case 32: // ' '
                eP = ly1.nF();
                break;

            case 40: // '('
                eQ = ly1.nD();
                break;
            }
        } while (true);
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
        flag = obj instanceof D;
        flag1 = false;
        if (!flag) goto _L4; else goto _L3
_L3:
        D d1;
        String s;
        d1 = (D)obj;
        if (eM != null)
        {
            break MISSING_BLOCK_LABEL_174;
        }
        s = d1.eM;
        flag1 = false;
        if (s != null) goto _L4; else goto _L5
_L5:
        int i;
        i = eN != d1.eN;
        flag1 = false;
        if (i != 0) goto _L4; else goto _L6
_L6:
        int j;
        j = eO != d1.eO;
        flag1 = false;
        if (j != 0) goto _L4; else goto _L7
_L7:
        boolean flag2;
        boolean flag3;
        flag2 = eP;
        flag3 = d1.eP;
        flag1 = false;
        if (flag2 != flag3) goto _L4; else goto _L8
_L8:
        int k;
        k = eQ != d1.eQ;
        flag1 = false;
        if (k != 0) goto _L4; else goto _L9
_L9:
        boolean flag4;
        if (amX != null && !amX.isEmpty())
        {
            break MISSING_BLOCK_LABEL_191;
        }
        if (d1.amX == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag4 = d1.amX.isEmpty();
        flag1 = false;
        if (!flag4) goto _L4; else goto _L10
_L10:
        return true;
        if (!eM.equals(d1.eM))
        {
            return false;
        }
          goto _L5
        return amX.equals(d1.amX);
    }

    public amX g()
    {
        eM = "";
        eN = 0L;
        eO = 0x7fffffffL;
        eP = false;
        eQ = 0L;
        amX = null;
        anb = -1;
        return this;
    }

    public int hashCode()
    {
        int i;
        int j;
        char c1;
        int k;
        List list;
        int l;
        if (eM == null)
        {
            i = 0;
        } else
        {
            i = eM.hashCode();
        }
        j = 31 * (31 * (31 * (i + 527) + (int)(eN ^ eN >>> 32)) + (int)(eO ^ eO >>> 32));
        if (eP)
        {
            c1 = '\u04CF';
        } else
        {
            c1 = '\u04D5';
        }
        k = 31 * (31 * (c1 + j) + (int)(eQ ^ eQ >>> 32));
        list = amX;
        l = 0;
        if (list != null)
        {
            boolean flag = amX.isEmpty();
            l = 0;
            if (!flag)
            {
                l = amX.hashCode();
            }
        }
        return k + l;
    }

    public ()
    {
        g();
    }
}
