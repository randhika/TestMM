// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, mc, lz, ly, 
//            mh, c, me

public static final class e extends ma
{

    private static volatile amX eG[];
    public int eH[];
    public int eI;
    public boolean eJ;
    public boolean eK;
    public int name;

    public static e[] d()
    {
        if (eG == null)
        {
            synchronized (mc.ana)
            {
                if (eG == null)
                {
                    eG = new eG[0];
                }
            }
        }
        return eG;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void a(lz lz1)
        throws IOException
    {
        if (eK)
        {
            lz1.a(1, eK);
        }
        lz1.p(2, eI);
        if (eH != null && eH.length > 0)
        {
            for (int i = 0; i < eH.length; i++)
            {
                lz1.p(3, eH[i]);
            }

        }
        if (name != 0)
        {
            lz1.p(4, name);
        }
        if (eJ)
        {
            lz1.a(6, eJ);
        }
        super.a(lz1);
    }

    public me b(ly ly1)
        throws IOException
    {
        return c(ly1);
    }

    protected int c()
    {
        int i = 0;
        int j = super.c();
        if (eK)
        {
            j += lz.b(1, eK);
        }
        int k = j + lz.r(2, eI);
        int l;
        if (eH != null && eH.length > 0)
        {
            for (int i1 = 0; i1 < eH.length; i1++)
            {
                i += lz.eE(eH[i1]);
            }

            l = k + i + 1 * eH.length;
        } else
        {
            l = k;
        }
        if (name != 0)
        {
            l += lz.r(4, name);
        }
        if (eJ)
        {
            l += lz.b(6, eJ);
        }
        return l;
    }

    public eJ c(ly ly1)
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

            case 8: // '\b'
                eK = ly1.nF();
                break;

            case 16: // '\020'
                eI = ly1.nE();
                break;

            case 24: // '\030'
                int j1 = mh.b(ly1, 24);
                int k1;
                int ai1[];
                if (eH == null)
                {
                    k1 = 0;
                } else
                {
                    k1 = eH.length;
                }
                ai1 = new int[j1 + k1];
                if (k1 != 0)
                {
                    System.arraycopy(eH, 0, ai1, 0, k1);
                }
                for (; k1 < -1 + ai1.length; k1++)
                {
                    ai1[k1] = ly1.nE();
                    ly1.nB();
                }

                ai1[k1] = ly1.nE();
                eH = ai1;
                break;

            case 26: // '\032'
                int j = ly1.ex(ly1.nI());
                int k = ly1.getPosition();
                int l;
                for (l = 0; ly1.nN() > 0; l++)
                {
                    ly1.nE();
                }

                ly1.ez(k);
                int i1;
                int ai[];
                if (eH == null)
                {
                    i1 = 0;
                } else
                {
                    i1 = eH.length;
                }
                ai = new int[l + i1];
                if (i1 != 0)
                {
                    System.arraycopy(eH, 0, ai, 0, i1);
                }
                for (; i1 < ai.length; i1++)
                {
                    ai[i1] = ly1.nE();
                }

                eH = ai;
                ly1.ey(j);
                break;

            case 32: // ' '
                name = ly1.nE();
                break;

            case 48: // '0'
                eJ = ly1.nF();
                break;
            }
        } while (true);
    }

    public F e()
    {
        eH = mh.and;
        eI = 0;
        name = 0;
        eJ = false;
        eK = false;
        amX = null;
        anb = -1;
        return this;
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
        flag = obj instanceof anb;
        flag1 = false;
        if (!flag) goto _L4; else goto _L3
_L3:
        anb anb;
        boolean flag2;
        anb = (anb)obj;
        flag2 = mc.equals(eH, anb.eH);
        flag1 = false;
        if (!flag2) goto _L4; else goto _L5
_L5:
        int i;
        int j;
        i = eI;
        j = anb.eI;
        flag1 = false;
        if (i != j) goto _L4; else goto _L6
_L6:
        int k;
        int l;
        k = name;
        l = anb.name;
        flag1 = false;
        if (k != l) goto _L4; else goto _L7
_L7:
        boolean flag3;
        boolean flag4;
        flag3 = eJ;
        flag4 = anb.eJ;
        flag1 = false;
        if (flag3 != flag4) goto _L4; else goto _L8
_L8:
        boolean flag5;
        boolean flag6;
        flag5 = eK;
        flag6 = anb.eK;
        flag1 = false;
        if (flag5 != flag6) goto _L4; else goto _L9
_L9:
        boolean flag7;
        if (amX != null && !amX.isEmpty())
        {
            break MISSING_BLOCK_LABEL_183;
        }
        if (anb.amX == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag7 = anb.amX.isEmpty();
        flag1 = false;
        if (!flag7) goto _L4; else goto _L10
_L10:
        return true;
        return amX.equals(anb.amX);
    }

    public int hashCode()
    {
        char c1 = '\u04CF';
        int i = 31 * (31 * (31 * (527 + mc.hashCode(eH)) + eI) + name);
        char c2;
        int j;
        int k;
        int l;
        if (eJ)
        {
            c2 = c1;
        } else
        {
            c2 = '\u04D5';
        }
        j = 31 * (c2 + i);
        if (!eK)
        {
            c1 = '\u04D5';
        }
        k = 31 * (j + c1);
        if (amX == null || amX.isEmpty())
        {
            l = 0;
        } else
        {
            l = amX.hashCode();
        }
        return l + k;
    }

    public ()
    {
        e();
    }
}
