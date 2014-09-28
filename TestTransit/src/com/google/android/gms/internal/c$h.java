// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, mb, lz, mc, 
//            ly, mh, c, me

public static final class n extends ma
{

    public static final mb fx = mb.a(11, com/google/android/gms/internal/c$h, 810);
    private static final  fy[] = new [0];
    public int fA[];
    public int fB[];
    public int fC;
    public int fD[];
    public int fE;
    public int fF;
    public int fz[];

    public void a(lz lz1)
        throws IOException
    {
        if (fz != null && fz.length > 0)
        {
            for (int j1 = 0; j1 < fz.length; j1++)
            {
                lz1.p(1, fz[j1]);
            }

        }
        if (fA != null && fA.length > 0)
        {
            for (int i1 = 0; i1 < fA.length; i1++)
            {
                lz1.p(2, fA[i1]);
            }

        }
        if (fB != null && fB.length > 0)
        {
            for (int l = 0; l < fB.length; l++)
            {
                lz1.p(3, fB[l]);
            }

        }
        if (fC != 0)
        {
            lz1.p(4, fC);
        }
        if (fD != null)
        {
            int j = fD.length;
            int k = 0;
            if (j > 0)
            {
                for (; k < fD.length; k++)
                {
                    lz1.p(5, fD[k]);
                }

            }
        }
        if (fE != 0)
        {
            lz1.p(6, fE);
        }
        if (fF != 0)
        {
            lz1.p(7, fF);
        }
        super.a(lz1);
    }

    public me b(ly ly1)
        throws IOException
    {
        return i(ly1);
    }

    protected int c()
    {
        int j = 0;
        int k = super.c();
        int l;
        if (fz != null && fz.length > 0)
        {
            int j2 = 0;
            int k2 = 0;
            for (; j2 < fz.length; j2++)
            {
                k2 += lz.eE(fz[j2]);
            }

            l = k + k2 + 1 * fz.length;
        } else
        {
            l = k;
        }
        if (fA != null && fA.length > 0)
        {
            int l1 = 0;
            int i2 = 0;
            for (; l1 < fA.length; l1++)
            {
                i2 += lz.eE(fA[l1]);
            }

            l = l + i2 + 1 * fA.length;
        }
        if (fB != null && fB.length > 0)
        {
            int j1 = 0;
            int k1 = 0;
            for (; j1 < fB.length; j1++)
            {
                k1 += lz.eE(fB[j1]);
            }

            l = l + k1 + 1 * fB.length;
        }
        if (fC != 0)
        {
            l += lz.r(4, fC);
        }
        if (fD != null && fD.length > 0)
        {
            int i1 = 0;
            for (; j < fD.length; j++)
            {
                i1 += lz.eE(fD[j]);
            }

            l = l + i1 + 1 * fD.length;
        }
        if (fE != 0)
        {
            l += lz.r(6, fE);
        }
        if (fF != 0)
        {
            l += lz.r(7, fF);
        }
        return l;
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
        flag = obj instanceof fF;
        flag1 = false;
        if (!flag) goto _L4; else goto _L3
_L3:
        fF ff;
        boolean flag2;
        ff = (fF)obj;
        flag2 = mc.equals(fz, ff.fz);
        flag1 = false;
        if (!flag2) goto _L4; else goto _L5
_L5:
        boolean flag3;
        flag3 = mc.equals(fA, ff.fA);
        flag1 = false;
        if (!flag3) goto _L4; else goto _L6
_L6:
        boolean flag4;
        flag4 = mc.equals(fB, ff.fB);
        flag1 = false;
        if (!flag4) goto _L4; else goto _L7
_L7:
        int j;
        int k;
        j = fC;
        k = ff.fC;
        flag1 = false;
        if (j != k) goto _L4; else goto _L8
_L8:
        boolean flag5;
        flag5 = mc.equals(fD, ff.fD);
        flag1 = false;
        if (!flag5) goto _L4; else goto _L9
_L9:
        int l;
        int i1;
        l = fE;
        i1 = ff.fE;
        flag1 = false;
        if (l != i1) goto _L4; else goto _L10
_L10:
        int j1;
        int k1;
        j1 = fF;
        k1 = ff.fF;
        flag1 = false;
        if (j1 != k1) goto _L4; else goto _L11
_L11:
        boolean flag6;
        if (amX != null && !amX.isEmpty())
        {
            break MISSING_BLOCK_LABEL_224;
        }
        if (ff.amX == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag6 = ff.amX.isEmpty();
        flag1 = false;
        if (!flag6) goto _L4; else goto _L12
_L12:
        return true;
        return amX.equals(ff.amX);
    }

    public int hashCode()
    {
        int j = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (527 + mc.hashCode(fz)) + mc.hashCode(fA)) + mc.hashCode(fB)) + fC) + mc.hashCode(fD)) + fE) + fF);
        int k;
        if (amX == null || amX.isEmpty())
        {
            k = 0;
        } else
        {
            k = amX.hashCode();
        }
        return k + j;
    }

    public amX i(ly ly1)
        throws IOException
    {
        do
        {
            int j = ly1.nB();
            switch (j)
            {
            default:
                if (a(ly1, j))
                {
                    continue;
                }
                // fall through

            case 0: // '\0'
                return this;

            case 8: // '\b'
                int i6 = mh.b(ly1, 8);
                int j6;
                int ai7[];
                if (fz == null)
                {
                    j6 = 0;
                } else
                {
                    j6 = fz.length;
                }
                ai7 = new int[i6 + j6];
                if (j6 != 0)
                {
                    System.arraycopy(fz, 0, ai7, 0, j6);
                }
                for (; j6 < -1 + ai7.length; j6++)
                {
                    ai7[j6] = ly1.nE();
                    ly1.nB();
                }

                ai7[j6] = ly1.nE();
                fz = ai7;
                break;

            case 10: // '\n'
                int i5 = ly1.ex(ly1.nI());
                int j5 = ly1.getPosition();
                int k5;
                for (k5 = 0; ly1.nN() > 0; k5++)
                {
                    ly1.nE();
                }

                ly1.ez(j5);
                int l5;
                int ai6[];
                if (fz == null)
                {
                    l5 = 0;
                } else
                {
                    l5 = fz.length;
                }
                ai6 = new int[k5 + l5];
                if (l5 != 0)
                {
                    System.arraycopy(fz, 0, ai6, 0, l5);
                }
                for (; l5 < ai6.length; l5++)
                {
                    ai6[l5] = ly1.nE();
                }

                fz = ai6;
                ly1.ey(i5);
                break;

            case 16: // '\020'
                int k4 = mh.b(ly1, 16);
                int l4;
                int ai5[];
                if (fA == null)
                {
                    l4 = 0;
                } else
                {
                    l4 = fA.length;
                }
                ai5 = new int[k4 + l4];
                if (l4 != 0)
                {
                    System.arraycopy(fA, 0, ai5, 0, l4);
                }
                for (; l4 < -1 + ai5.length; l4++)
                {
                    ai5[l4] = ly1.nE();
                    ly1.nB();
                }

                ai5[l4] = ly1.nE();
                fA = ai5;
                break;

            case 18: // '\022'
                int k3 = ly1.ex(ly1.nI());
                int l3 = ly1.getPosition();
                int i4;
                for (i4 = 0; ly1.nN() > 0; i4++)
                {
                    ly1.nE();
                }

                ly1.ez(l3);
                int j4;
                int ai4[];
                if (fA == null)
                {
                    j4 = 0;
                } else
                {
                    j4 = fA.length;
                }
                ai4 = new int[i4 + j4];
                if (j4 != 0)
                {
                    System.arraycopy(fA, 0, ai4, 0, j4);
                }
                for (; j4 < ai4.length; j4++)
                {
                    ai4[j4] = ly1.nE();
                }

                fA = ai4;
                ly1.ey(k3);
                break;

            case 24: // '\030'
                int i3 = mh.b(ly1, 24);
                int j3;
                int ai3[];
                if (fB == null)
                {
                    j3 = 0;
                } else
                {
                    j3 = fB.length;
                }
                ai3 = new int[i3 + j3];
                if (j3 != 0)
                {
                    System.arraycopy(fB, 0, ai3, 0, j3);
                }
                for (; j3 < -1 + ai3.length; j3++)
                {
                    ai3[j3] = ly1.nE();
                    ly1.nB();
                }

                ai3[j3] = ly1.nE();
                fB = ai3;
                break;

            case 26: // '\032'
                int i2 = ly1.ex(ly1.nI());
                int j2 = ly1.getPosition();
                int k2;
                for (k2 = 0; ly1.nN() > 0; k2++)
                {
                    ly1.nE();
                }

                ly1.ez(j2);
                int l2;
                int ai2[];
                if (fB == null)
                {
                    l2 = 0;
                } else
                {
                    l2 = fB.length;
                }
                ai2 = new int[k2 + l2];
                if (l2 != 0)
                {
                    System.arraycopy(fB, 0, ai2, 0, l2);
                }
                for (; l2 < ai2.length; l2++)
                {
                    ai2[l2] = ly1.nE();
                }

                fB = ai2;
                ly1.ey(i2);
                break;

            case 32: // ' '
                fC = ly1.nE();
                break;

            case 40: // '('
                int k1 = mh.b(ly1, 40);
                int l1;
                int ai1[];
                if (fD == null)
                {
                    l1 = 0;
                } else
                {
                    l1 = fD.length;
                }
                ai1 = new int[k1 + l1];
                if (l1 != 0)
                {
                    System.arraycopy(fD, 0, ai1, 0, l1);
                }
                for (; l1 < -1 + ai1.length; l1++)
                {
                    ai1[l1] = ly1.nE();
                    ly1.nB();
                }

                ai1[l1] = ly1.nE();
                fD = ai1;
                break;

            case 42: // '*'
                int k = ly1.ex(ly1.nI());
                int l = ly1.getPosition();
                int i1;
                for (i1 = 0; ly1.nN() > 0; i1++)
                {
                    ly1.nE();
                }

                ly1.ez(l);
                int j1;
                int ai[];
                if (fD == null)
                {
                    j1 = 0;
                } else
                {
                    j1 = fD.length;
                }
                ai = new int[i1 + j1];
                if (j1 != 0)
                {
                    System.arraycopy(fD, 0, ai, 0, j1);
                }
                for (; j1 < ai.length; j1++)
                {
                    ai[j1] = ly1.nE();
                }

                fD = ai;
                ly1.ey(k);
                break;

            case 48: // '0'
                fE = ly1.nE();
                break;

            case 56: // '8'
                fF = ly1.nE();
                break;
            }
        } while (true);
    }

    public E n()
    {
        fz = mh.and;
        fA = mh.and;
        fB = mh.and;
        fC = 0;
        fD = mh.and;
        fE = 0;
        fF = 0;
        amX = null;
        anb = -1;
        return this;
    }


    public ()
    {
        n();
    }
}
