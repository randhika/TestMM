// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, mc, lz, ly, 
//            mh, d, me

public static final class s extends ma
{

    private static volatile anb fM[];
    public String fN;
    public anb fO[];
    public anb fP[];
    public anb fQ[];
    public String fR;
    public String fS;
    public long fT;
    public boolean fU;
    public anb fV[];
    public int fW[];
    public boolean fX;
    public int type;

    public static s[] r()
    {
        if (fM == null)
        {
            synchronized (mc.ana)
            {
                if (fM == null)
                {
                    fM = new fM[0];
                }
            }
        }
        return fM;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void a(lz lz1)
        throws IOException
    {
        lz1.p(1, type);
        if (!fN.equals(""))
        {
            lz1.b(2, fN);
        }
        if (fO != null && fO.length > 0)
        {
            for (int k1 = 0; k1 < fO.length; k1++)
            {
                fM fm3 = fO[k1];
                if (fm3 != null)
                {
                    lz1.a(3, fm3);
                }
            }

        }
        if (fP != null && fP.length > 0)
        {
            for (int j1 = 0; j1 < fP.length; j1++)
            {
                fM fm2 = fP[j1];
                if (fm2 != null)
                {
                    lz1.a(4, fm2);
                }
            }

        }
        if (fQ != null && fQ.length > 0)
        {
            for (int i1 = 0; i1 < fQ.length; i1++)
            {
                fM fm1 = fQ[i1];
                if (fm1 != null)
                {
                    lz1.a(5, fm1);
                }
            }

        }
        if (!fR.equals(""))
        {
            lz1.b(6, fR);
        }
        if (!fS.equals(""))
        {
            lz1.b(7, fS);
        }
        if (fT != 0L)
        {
            lz1.b(8, fT);
        }
        if (fX)
        {
            lz1.a(9, fX);
        }
        if (fW != null && fW.length > 0)
        {
            for (int k = 0; k < fW.length; k++)
            {
                lz1.p(10, fW[k]);
            }

        }
        if (fV != null)
        {
            int i = fV.length;
            int j = 0;
            if (i > 0)
            {
                for (; j < fV.length; j++)
                {
                    fM fm = fV[j];
                    if (fm != null)
                    {
                        lz1.a(11, fm);
                    }
                }

            }
        }
        if (fU)
        {
            lz1.a(12, fU);
        }
        super.a(lz1);
    }

    public me b(ly ly1)
        throws IOException
    {
        return l(ly1);
    }

    protected int c()
    {
        int i = super.c() + lz.r(1, type);
        if (!fN.equals(""))
        {
            i += lz.h(2, fN);
        }
        if (fO != null && fO.length > 0)
        {
            int i3 = i;
            for (int j3 = 0; j3 < fO.length; j3++)
            {
                l l4 = fO[j3];
                if (l4 != null)
                {
                    i3 += lz.b(3, l4);
                }
            }

            i = i3;
        }
        if (fP != null && fP.length > 0)
        {
            int j2 = i;
            for (int k2 = 0; k2 < fP.length; k2++)
            {
                l l3 = fP[k2];
                if (l3 != null)
                {
                    j2 += lz.b(4, l3);
                }
            }

            i = j2;
        }
        if (fQ != null && fQ.length > 0)
        {
            int k1 = i;
            for (int i2 = 0; i2 < fQ.length; i2++)
            {
                l l2 = fQ[i2];
                if (l2 != null)
                {
                    k1 += lz.b(5, l2);
                }
            }

            i = k1;
        }
        if (!fR.equals(""))
        {
            i += lz.h(6, fR);
        }
        if (!fS.equals(""))
        {
            i += lz.h(7, fS);
        }
        if (fT != 0L)
        {
            i += lz.d(8, fT);
        }
        if (fX)
        {
            i += lz.b(9, fX);
        }
        if (fW != null && fW.length > 0)
        {
            int i1 = 0;
            int j1 = 0;
            for (; i1 < fW.length; i1++)
            {
                j1 += lz.eE(fW[i1]);
            }

            i = i + j1 + 1 * fW.length;
        }
        if (fV != null)
        {
            int j = fV.length;
            int k = 0;
            if (j > 0)
            {
                for (; k < fV.length; k++)
                {
                    l l1 = fV[k];
                    if (l1 != null)
                    {
                        i += lz.b(11, l1);
                    }
                }

            }
        }
        if (fU)
        {
            i += lz.b(12, fU);
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
        flag = obj instanceof fU;
        flag1 = false;
        if (!flag) goto _L4; else goto _L3
_L3:
        fU fu;
        int i;
        int j;
        fu = (fU)obj;
        i = type;
        j = fu.type;
        flag1 = false;
        if (i != j) goto _L4; else goto _L5
_L5:
        if (fN != null) goto _L7; else goto _L6
_L6:
        String s3;
        s3 = fu.fN;
        flag1 = false;
        if (s3 != null) goto _L4; else goto _L8
_L8:
        boolean flag2;
        flag2 = mc.equals(fO, fu.fO);
        flag1 = false;
        if (!flag2) goto _L4; else goto _L9
_L9:
        boolean flag3;
        flag3 = mc.equals(fP, fu.fP);
        flag1 = false;
        if (!flag3) goto _L4; else goto _L10
_L10:
        boolean flag4;
        flag4 = mc.equals(fQ, fu.fQ);
        flag1 = false;
        if (!flag4) goto _L4; else goto _L11
_L11:
        if (fR != null) goto _L13; else goto _L12
_L12:
        String s2;
        s2 = fu.fR;
        flag1 = false;
        if (s2 != null) goto _L4; else goto _L14
_L14:
        if (fS != null) goto _L16; else goto _L15
_L15:
        String s1;
        s1 = fu.fS;
        flag1 = false;
        if (s1 != null) goto _L4; else goto _L17
_L17:
        int k;
        k = fT != fu.fT;
        flag1 = false;
        if (k != 0) goto _L4; else goto _L18
_L18:
        boolean flag5;
        boolean flag6;
        flag5 = fU;
        flag6 = fu.fU;
        flag1 = false;
        if (flag5 != flag6) goto _L4; else goto _L19
_L19:
        boolean flag7;
        flag7 = mc.equals(fV, fu.fV);
        flag1 = false;
        if (!flag7) goto _L4; else goto _L20
_L20:
        boolean flag8;
        flag8 = mc.equals(fW, fu.fW);
        flag1 = false;
        if (!flag8) goto _L4; else goto _L21
_L21:
        boolean flag9;
        boolean flag10;
        flag9 = fX;
        flag10 = fu.fX;
        flag1 = false;
        if (flag9 != flag10) goto _L4; else goto _L22
_L22:
        boolean flag11;
        if (amX != null && !amX.isEmpty())
        {
            break MISSING_BLOCK_LABEL_378;
        }
        if (fu.amX == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag11 = fu.amX.isEmpty();
        flag1 = false;
        if (!flag11) goto _L4; else goto _L23
_L23:
        return true;
_L7:
        if (!fN.equals(fu.fN))
        {
            return false;
        }
          goto _L8
_L13:
        if (!fR.equals(fu.fR))
        {
            return false;
        }
          goto _L14
_L16:
        if (!fS.equals(fu.fS))
        {
            return false;
        }
          goto _L17
        return amX.equals(fu.amX);
          goto _L8
    }

    public int hashCode()
    {
        char c1 = '\u04CF';
        int i = 31 * (527 + type);
        int j;
        int k;
        int i1;
        int j1;
        int k1;
        int l1;
        char c2;
        int i2;
        int j2;
        List list;
        int k2;
        if (fN == null)
        {
            j = 0;
        } else
        {
            j = fN.hashCode();
        }
        k = 31 * (31 * (31 * (31 * (j + i) + mc.hashCode(fO)) + mc.hashCode(fP)) + mc.hashCode(fQ));
        if (fR == null)
        {
            i1 = 0;
        } else
        {
            i1 = fR.hashCode();
        }
        j1 = 31 * (i1 + k);
        if (fS == null)
        {
            k1 = 0;
        } else
        {
            k1 = fS.hashCode();
        }
        l1 = 31 * (31 * (k1 + j1) + (int)(fT ^ fT >>> 32));
        if (fU)
        {
            c2 = c1;
        } else
        {
            c2 = '\u04D5';
        }
        i2 = 31 * (31 * (31 * (c2 + l1) + mc.hashCode(fV)) + mc.hashCode(fW));
        if (!fX)
        {
            c1 = '\u04D5';
        }
        j2 = 31 * (i2 + c1);
        list = amX;
        k2 = 0;
        if (list != null)
        {
            boolean flag = amX.isEmpty();
            k2 = 0;
            if (!flag)
            {
                k2 = amX.hashCode();
            }
        }
        return j2 + k2;
    }

    public amX l(ly ly1)
        throws IOException
    {
_L16:
        int i = ly1.nB();
        i;
        JVM INSTR lookupswitch 14: default 128
    //                   0: 137
    //                   8: 139
    //                   18: 204
    //                   26: 215
    //                   34: 343
    //                   42: 471
    //                   50: 599
    //                   58: 610
    //                   64: 621
    //                   72: 632
    //                   80: 643
    //                   82: 897
    //                   90: 1212
    //                   96: 1338;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15
_L1:
        if (a(ly1, i)) goto _L16; else goto _L2
_L2:
        return this;
_L3:
        int k5 = ly1.nE();
        switch (k5)
        {
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
            type = k5;
            break;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        fN = ly1.readString();
        continue; /* Loop/switch isn't completed */
_L5:
        int i5 = mh.b(ly1, 26);
        int j5;
        amX aamx3[];
        if (fO == null)
        {
            j5 = 0;
        } else
        {
            j5 = fO.length;
        }
        aamx3 = new fO[i5 + j5];
        if (j5 != 0)
        {
            System.arraycopy(fO, 0, aamx3, 0, j5);
        }
        for (; j5 < -1 + aamx3.length; j5++)
        {
            aamx3[j5] = new <init>();
            ly1.a(aamx3[j5]);
            ly1.nB();
        }

        aamx3[j5] = new <init>();
        ly1.a(aamx3[j5]);
        fO = aamx3;
        continue; /* Loop/switch isn't completed */
_L6:
        int k4 = mh.b(ly1, 34);
        int l4;
        amX aamx2[];
        if (fP == null)
        {
            l4 = 0;
        } else
        {
            l4 = fP.length;
        }
        aamx2 = new fP[k4 + l4];
        if (l4 != 0)
        {
            System.arraycopy(fP, 0, aamx2, 0, l4);
        }
        for (; l4 < -1 + aamx2.length; l4++)
        {
            aamx2[l4] = new <init>();
            ly1.a(aamx2[l4]);
            ly1.nB();
        }

        aamx2[l4] = new <init>();
        ly1.a(aamx2[l4]);
        fP = aamx2;
        continue; /* Loop/switch isn't completed */
_L7:
        int i4 = mh.b(ly1, 42);
        int j4;
        amX aamx1[];
        if (fQ == null)
        {
            j4 = 0;
        } else
        {
            j4 = fQ.length;
        }
        aamx1 = new fQ[i4 + j4];
        if (j4 != 0)
        {
            System.arraycopy(fQ, 0, aamx1, 0, j4);
        }
        for (; j4 < -1 + aamx1.length; j4++)
        {
            aamx1[j4] = new <init>();
            ly1.a(aamx1[j4]);
            ly1.nB();
        }

        aamx1[j4] = new <init>();
        ly1.a(aamx1[j4]);
        fQ = aamx1;
        continue; /* Loop/switch isn't completed */
_L8:
        fR = ly1.readString();
        continue; /* Loop/switch isn't completed */
_L9:
        fS = ly1.readString();
        continue; /* Loop/switch isn't completed */
_L10:
        fT = ly1.nD();
        continue; /* Loop/switch isn't completed */
_L11:
        fX = ly1.nF();
        continue; /* Loop/switch isn't completed */
_L12:
        int k2;
        int ai1[];
        int l2;
        int i3;
        k2 = mh.b(ly1, 80);
        ai1 = new int[k2];
        l2 = 0;
        i3 = 0;
_L21:
        if (l2 >= k2) goto _L18; else goto _L17
_L17:
        int k3;
        if (l2 != 0)
        {
            ly1.nB();
        }
        k3 = ly1.nE();
        k3;
        JVM INSTR tableswitch 1 17: default 772
    //                   1 786
    //                   2 786
    //                   3 786
    //                   4 786
    //                   5 786
    //                   6 786
    //                   7 786
    //                   8 786
    //                   9 786
    //                   10 786
    //                   11 786
    //                   12 786
    //                   13 786
    //                   14 786
    //                   15 786
    //                   16 786
    //                   17 786;
           goto _L19 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20
_L19:
        int l3 = i3;
_L22:
        l2++;
        i3 = l3;
          goto _L21
_L20:
        l3 = i3 + 1;
        ai1[i3] = k3;
          goto _L22
_L18:
        if (i3 != 0)
        {
            int j3;
            if (fW == null)
            {
                j3 = 0;
            } else
            {
                j3 = fW.length;
            }
            if (j3 == 0 && i3 == ai1.length)
            {
                fW = ai1;
            } else
            {
                int ai2[] = new int[j3 + i3];
                if (j3 != 0)
                {
                    System.arraycopy(fW, 0, ai2, 0, j3);
                }
                System.arraycopy(ai1, 0, ai2, j3, i3);
                fW = ai2;
            }
        }
        continue; /* Loop/switch isn't completed */
          goto _L21
_L13:
        int i1;
        int j1;
        int k1;
        i1 = ly1.ex(ly1.nI());
        j1 = ly1.getPosition();
        k1 = 0;
_L28:
        if (ly1.nN() > 0)
        {
            switch (ly1.nE())
            {
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            case 8: // '\b'
            case 9: // '\t'
            case 10: // '\n'
            case 11: // '\013'
            case 12: // '\f'
            case 13: // '\r'
            case 14: // '\016'
            case 15: // '\017'
            case 16: // '\020'
            case 17: // '\021'
                k1++;
                break;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (k1 == 0) goto _L24; else goto _L23
_L23:
        int ai[];
        ly1.ez(j1);
        int l1;
        int i2;
        int j2;
        if (fW == null)
        {
            l1 = 0;
        } else
        {
            l1 = fW.length;
        }
        ai = new int[k1 + l1];
        if (l1 != 0)
        {
            System.arraycopy(fW, 0, ai, 0, l1);
        }
_L26:
        if (ly1.nN() > 0)
        {
            i2 = ly1.nE();
            switch (i2)
            {
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            case 8: // '\b'
            case 9: // '\t'
            case 10: // '\n'
            case 11: // '\013'
            case 12: // '\f'
            case 13: // '\r'
            case 14: // '\016'
            case 15: // '\017'
            case 16: // '\020'
            case 17: // '\021'
                j2 = l1 + 1;
                ai[l1] = i2;
                l1 = j2;
                break;
            }
            continue; /* Loop/switch isn't completed */
        }
        fW = ai;
_L24:
        ly1.ey(i1);
        continue; /* Loop/switch isn't completed */
        if (true) goto _L26; else goto _L25
_L25:
        if (true) goto _L28; else goto _L27
_L27:
_L14:
        int j = mh.b(ly1, 90);
        int k;
        amX aamx[];
        if (fV == null)
        {
            k = 0;
        } else
        {
            k = fV.length;
        }
        aamx = new fV[j + k];
        if (k != 0)
        {
            System.arraycopy(fV, 0, aamx, 0, k);
        }
        for (; k < -1 + aamx.length; k++)
        {
            aamx[k] = new <init>();
            ly1.a(aamx[k]);
            ly1.nB();
        }

        aamx[k] = new <init>();
        ly1.a(aamx[k]);
        fV = aamx;
        continue; /* Loop/switch isn't completed */
_L15:
        fU = ly1.nF();
        if (true) goto _L16; else goto _L29
_L29:
    }

    public F s()
    {
        type = 1;
        fN = "";
        fO = r();
        fP = r();
        fQ = r();
        fR = "";
        fS = "";
        fT = 0L;
        fU = false;
        fV = r();
        fW = mh.and;
        fX = false;
        amX = null;
        anb = -1;
        return this;
    }

    public ()
    {
        s();
    }
}
