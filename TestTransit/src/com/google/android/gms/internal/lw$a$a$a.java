// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, mh, lz, mc, 
//            ly, lw, me

public static final class nA extends ma
{

    public long amA;
    public int amB;
    public int amC;
    public boolean amD;
    public amI amE[];
    public amI amF[];
    public String amG[];
    public long amH[];
    public float amI[];
    public long amJ;
    public byte amw[];
    public String amx;
    public double amy;
    public float amz;

    public void a(lz lz1)
        throws IOException
    {
        if (!Arrays.equals(amw, mh.ank))
        {
            lz1.a(1, amw);
        }
        if (!amx.equals(""))
        {
            lz1.b(2, amx);
        }
        if (Double.doubleToLongBits(amy) != Double.doubleToLongBits(0.0D))
        {
            lz1.a(3, amy);
        }
        if (Float.floatToIntBits(amz) != Float.floatToIntBits(0.0F))
        {
            lz1.b(4, amz);
        }
        if (amA != 0L)
        {
            lz1.b(5, amA);
        }
        if (amB != 0)
        {
            lz1.p(6, amB);
        }
        if (amC != 0)
        {
            lz1.q(7, amC);
        }
        if (amD)
        {
            lz1.a(8, amD);
        }
        if (amE != null && amE.length > 0)
        {
            for (int j1 = 0; j1 < amE.length; j1++)
            {
                nA na1 = amE[j1];
                if (na1 != null)
                {
                    lz1.a(9, na1);
                }
            }

        }
        if (amF != null && amF.length > 0)
        {
            for (int i1 = 0; i1 < amF.length; i1++)
            {
                nA na = amF[i1];
                if (na != null)
                {
                    lz1.a(10, na);
                }
            }

        }
        if (amG != null && amG.length > 0)
        {
            for (int l = 0; l < amG.length; l++)
            {
                String s = amG[l];
                if (s != null)
                {
                    lz1.b(11, s);
                }
            }

        }
        if (amH != null && amH.length > 0)
        {
            for (int k = 0; k < amH.length; k++)
            {
                lz1.b(12, amH[k]);
            }

        }
        if (amJ != 0L)
        {
            lz1.b(13, amJ);
        }
        if (amI != null)
        {
            int i = amI.length;
            int j = 0;
            if (i > 0)
            {
                for (; j < amI.length; j++)
                {
                    lz1.b(14, amI[j]);
                }

            }
        }
        super.a(lz1);
    }

    public me b(ly ly1)
        throws IOException
    {
        return t(ly1);
    }

    protected int c()
    {
        int i = 0;
        int j = super.c();
        if (!Arrays.equals(amw, mh.ank))
        {
            j += lz.b(1, amw);
        }
        if (!amx.equals(""))
        {
            j += lz.h(2, amx);
        }
        if (Double.doubleToLongBits(amy) != Double.doubleToLongBits(0.0D))
        {
            j += lz.b(3, amy);
        }
        if (Float.floatToIntBits(amz) != Float.floatToIntBits(0.0F))
        {
            j += lz.c(4, amz);
        }
        if (amA != 0L)
        {
            j += lz.d(5, amA);
        }
        if (amB != 0)
        {
            j += lz.r(6, amB);
        }
        if (amC != 0)
        {
            j += lz.s(7, amC);
        }
        if (amD)
        {
            j += lz.b(8, amD);
        }
        if (amE != null && amE.length > 0)
        {
            int i2 = j;
            for (int j2 = 0; j2 < amE.length; j2++)
            {
                t t2 = amE[j2];
                if (t2 != null)
                {
                    i2 += lz.b(9, t2);
                }
            }

            j = i2;
        }
        if (amF != null && amF.length > 0)
        {
            int k1 = j;
            for (int l1 = 0; l1 < amF.length; l1++)
            {
                t t1 = amF[l1];
                if (t1 != null)
                {
                    k1 += lz.b(10, t1);
                }
            }

            j = k1;
        }
        if (amG != null && amG.length > 0)
        {
            int l = 0;
            int i1 = 0;
            int j1 = 0;
            for (; l < amG.length; l++)
            {
                String s = amG[l];
                if (s != null)
                {
                    j1++;
                    i1 += lz.cz(s);
                }
            }

            j = j + i1 + j1 * 1;
        }
        if (amH != null && amH.length > 0)
        {
            int k = 0;
            for (; i < amH.length; i++)
            {
                k += lz.D(amH[i]);
            }

            j = j + k + 1 * amH.length;
        }
        if (amJ != 0L)
        {
            j += lz.d(13, amJ);
        }
        if (amI != null && amI.length > 0)
        {
            j = j + 4 * amI.length + 1 * amI.length;
        }
        return j;
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
        flag = obj instanceof amI;
        flag1 = false;
        if (!flag) goto _L4; else goto _L3
_L3:
        amI ami;
        boolean flag2;
        ami = (amI)obj;
        flag2 = Arrays.equals(amw, ami.amw);
        flag1 = false;
        if (!flag2) goto _L4; else goto _L5
_L5:
        String s;
        if (amx != null)
        {
            break MISSING_BLOCK_LABEL_378;
        }
        s = ami.amx;
        flag1 = false;
        if (s != null) goto _L4; else goto _L6
_L6:
        int i;
        i = Double.doubleToLongBits(amy) != Double.doubleToLongBits(ami.amy);
        flag1 = false;
        if (i != 0) goto _L4; else goto _L7
_L7:
        int j;
        int k;
        j = Float.floatToIntBits(amz);
        k = Float.floatToIntBits(ami.amz);
        flag1 = false;
        if (j != k) goto _L4; else goto _L8
_L8:
        int l;
        l = amA != ami.amA;
        flag1 = false;
        if (l != 0) goto _L4; else goto _L9
_L9:
        int i1;
        int j1;
        i1 = amB;
        j1 = ami.amB;
        flag1 = false;
        if (i1 != j1) goto _L4; else goto _L10
_L10:
        int k1;
        int l1;
        k1 = amC;
        l1 = ami.amC;
        flag1 = false;
        if (k1 != l1) goto _L4; else goto _L11
_L11:
        boolean flag3;
        boolean flag4;
        flag3 = amD;
        flag4 = ami.amD;
        flag1 = false;
        if (flag3 != flag4) goto _L4; else goto _L12
_L12:
        boolean flag5;
        flag5 = mc.equals(amE, ami.amE);
        flag1 = false;
        if (!flag5) goto _L4; else goto _L13
_L13:
        boolean flag6;
        flag6 = mc.equals(amF, ami.amF);
        flag1 = false;
        if (!flag6) goto _L4; else goto _L14
_L14:
        boolean flag7;
        flag7 = mc.equals(amG, ami.amG);
        flag1 = false;
        if (!flag7) goto _L4; else goto _L15
_L15:
        boolean flag8;
        flag8 = mc.equals(amH, ami.amH);
        flag1 = false;
        if (!flag8) goto _L4; else goto _L16
_L16:
        boolean flag9;
        flag9 = mc.equals(amI, ami.amI);
        flag1 = false;
        if (!flag9) goto _L4; else goto _L17
_L17:
        int i2;
        i2 = amJ != ami.amJ;
        flag1 = false;
        if (i2 != 0) goto _L4; else goto _L18
_L18:
        boolean flag10;
        if (amX != null && !amX.isEmpty())
        {
            break MISSING_BLOCK_LABEL_395;
        }
        if (ami.amX == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag10 = ami.amX.isEmpty();
        flag1 = false;
        if (!flag10) goto _L4; else goto _L19
_L19:
        return true;
        if (!amx.equals(ami.amx))
        {
            return false;
        }
          goto _L6
        return amX.equals(ami.amX);
    }

    public int hashCode()
    {
        int i = 31 * (527 + Arrays.hashCode(amw));
        int j;
        int k;
        long l;
        int i1;
        char c1;
        int j1;
        List list;
        int k1;
        if (amx == null)
        {
            j = 0;
        } else
        {
            j = amx.hashCode();
        }
        k = j + i;
        l = Double.doubleToLongBits(amy);
        i1 = 31 * (31 * (31 * (31 * (31 * (k * 31 + (int)(l ^ l >>> 32)) + Float.floatToIntBits(amz)) + (int)(amA ^ amA >>> 32)) + amB) + amC);
        if (amD)
        {
            c1 = '\u04CF';
        } else
        {
            c1 = '\u04D5';
        }
        j1 = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (c1 + i1) + mc.hashCode(amE)) + mc.hashCode(amF)) + mc.hashCode(amG)) + mc.hashCode(amH)) + mc.hashCode(amI)) + (int)(amJ ^ amJ >>> 32));
        list = amX;
        k1 = 0;
        if (list != null)
        {
            boolean flag = amX.isEmpty();
            k1 = 0;
            if (!flag)
            {
                k1 = amX.hashCode();
            }
        }
        return j1 + k1;
    }

    public amX nA()
    {
        amw = mh.ank;
        amx = "";
        amy = 0.0D;
        amz = 0.0F;
        amA = 0L;
        amB = 0;
        amC = 0;
        amD = false;
        amE = amE();
        amF = ();
        amG = mh.ani;
        amH = mh.ane;
        amI = mh.anf;
        amJ = 0L;
        amX = null;
        anb = -1;
        return this;
    }

    public anb t(ly ly1)
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
                amw = ly1.readBytes();
                break;

            case 18: // '\022'
                amx = ly1.readString();
                break;

            case 25: // '\031'
                amy = ly1.readDouble();
                break;

            case 37: // '%'
                amz = ly1.readFloat();
                break;

            case 40: // '('
                amA = ly1.nD();
                break;

            case 48: // '0'
                amB = ly1.nE();
                break;

            case 56: // '8'
                amC = ly1.nG();
                break;

            case 64: // '@'
                amD = ly1.nF();
                break;

            case 74: // 'J'
                int j4 = mh.b(ly1, 74);
                int k4;
                anb aanb1[];
                if (amE == null)
                {
                    k4 = 0;
                } else
                {
                    k4 = amE.length;
                }
                aanb1 = new amE[j4 + k4];
                if (k4 != 0)
                {
                    System.arraycopy(amE, 0, aanb1, 0, k4);
                }
                for (; k4 < -1 + aanb1.length; k4++)
                {
                    aanb1[k4] = new t>();
                    ly1.a(aanb1[k4]);
                    ly1.nB();
                }

                aanb1[k4] = new t>();
                ly1.a(aanb1[k4]);
                amE = aanb1;
                break;

            case 82: // 'R'
                int l3 = mh.b(ly1, 82);
                int i4;
                anb aanb[];
                if (amF == null)
                {
                    i4 = 0;
                } else
                {
                    i4 = amF.length;
                }
                aanb = new amF[l3 + i4];
                if (i4 != 0)
                {
                    System.arraycopy(amF, 0, aanb, 0, i4);
                }
                for (; i4 < -1 + aanb.length; i4++)
                {
                    aanb[i4] = new nit>();
                    ly1.a(aanb[i4]);
                    ly1.nB();
                }

                aanb[i4] = new nit>();
                ly1.a(aanb[i4]);
                amF = aanb;
                break;

            case 90: // 'Z'
                int j3 = mh.b(ly1, 90);
                int k3;
                String as[];
                if (amG == null)
                {
                    k3 = 0;
                } else
                {
                    k3 = amG.length;
                }
                as = new String[j3 + k3];
                if (k3 != 0)
                {
                    System.arraycopy(amG, 0, as, 0, k3);
                }
                for (; k3 < -1 + as.length; k3++)
                {
                    as[k3] = ly1.readString();
                    ly1.nB();
                }

                as[k3] = ly1.readString();
                amG = as;
                break;

            case 96: // '`'
                int l2 = mh.b(ly1, 96);
                int i3;
                long al1[];
                if (amH == null)
                {
                    i3 = 0;
                } else
                {
                    i3 = amH.length;
                }
                al1 = new long[l2 + i3];
                if (i3 != 0)
                {
                    System.arraycopy(amH, 0, al1, 0, i3);
                }
                for (; i3 < -1 + al1.length; i3++)
                {
                    al1[i3] = ly1.nD();
                    ly1.nB();
                }

                al1[i3] = ly1.nD();
                amH = al1;
                break;

            case 98: // 'b'
                int l1 = ly1.ex(ly1.nI());
                int i2 = ly1.getPosition();
                int j2;
                for (j2 = 0; ly1.nN() > 0; j2++)
                {
                    ly1.nD();
                }

                ly1.ez(i2);
                int k2;
                long al[];
                if (amH == null)
                {
                    k2 = 0;
                } else
                {
                    k2 = amH.length;
                }
                al = new long[j2 + k2];
                if (k2 != 0)
                {
                    System.arraycopy(amH, 0, al, 0, k2);
                }
                for (; k2 < al.length; k2++)
                {
                    al[k2] = ly1.nD();
                }

                amH = al;
                ly1.ey(l1);
                break;

            case 104: // 'h'
                amJ = ly1.nD();
                break;

            case 117: // 'u'
                int j1 = mh.b(ly1, 117);
                int k1;
                float af1[];
                if (amI == null)
                {
                    k1 = 0;
                } else
                {
                    k1 = amI.length;
                }
                af1 = new float[j1 + k1];
                if (k1 != 0)
                {
                    System.arraycopy(amI, 0, af1, 0, k1);
                }
                for (; k1 < -1 + af1.length; k1++)
                {
                    af1[k1] = ly1.readFloat();
                    ly1.nB();
                }

                af1[k1] = ly1.readFloat();
                amI = af1;
                break;

            case 114: // 'r'
                int j = ly1.nI();
                int k = ly1.ex(j);
                int l = j / 4;
                int i1;
                float af[];
                if (amI == null)
                {
                    i1 = 0;
                } else
                {
                    i1 = amI.length;
                }
                af = new float[l + i1];
                if (i1 != 0)
                {
                    System.arraycopy(amI, 0, af, 0, i1);
                }
                for (; i1 < af.length; i1++)
                {
                    af[i1] = ly1.readFloat();
                }

                amI = af;
                ly1.ey(k);
                break;
            }
        } while (true);
    }

    public ()
    {
        nA();
    }
}