// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, ly, lz, me, 
//            mc, mh, md, mb

public interface com.google.android.gms.internal.c
{
    public static final class a extends ma
    {

        public int eE;
        public int eF;
        public int level;

        public a a(ly ly1)
            throws IOException
        {
_L6:
            int k = ly1.nB();
            k;
            JVM INSTR lookupswitch 4: default 48
        //                       0: 57
        //                       8: 59
        //                       16: 103
        //                       24: 114;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            if (a(ly1, k)) goto _L6; else goto _L2
_L2:
            return this;
_L3:
            int l = ly1.nE();
            switch (l)
            {
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                level = l;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            eE = ly1.nE();
            continue; /* Loop/switch isn't completed */
_L5:
            eF = ly1.nE();
            if (true) goto _L6; else goto _L7
_L7:
        }

        public void a(lz lz1)
            throws IOException
        {
            if (level != 1)
            {
                lz1.p(1, level);
            }
            if (eE != 0)
            {
                lz1.p(2, eE);
            }
            if (eF != 0)
            {
                lz1.p(3, eF);
            }
            super.a(lz1);
        }

        public a b()
        {
            level = 1;
            eE = 0;
            eF = 0;
            amX = null;
            anb = -1;
            return this;
        }

        public me b(ly ly1)
            throws IOException
        {
            return a(ly1);
        }

        protected int c()
        {
            int k = super.c();
            if (level != 1)
            {
                k += lz.r(1, level);
            }
            if (eE != 0)
            {
                k += lz.r(2, eE);
            }
            if (eF != 0)
            {
                k += lz.r(3, eF);
            }
            return k;
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
            flag = obj instanceof a;
            flag1 = false;
            if (!flag) goto _L4; else goto _L3
_L3:
            a a1;
            int k;
            int l;
            a1 = (a)obj;
            k = level;
            l = a1.level;
            flag1 = false;
            if (k != l) goto _L4; else goto _L5
_L5:
            int i1;
            int j1;
            i1 = eE;
            j1 = a1.eE;
            flag1 = false;
            if (i1 != j1) goto _L4; else goto _L6
_L6:
            int k1;
            int l1;
            k1 = eF;
            l1 = a1.eF;
            flag1 = false;
            if (k1 != l1) goto _L4; else goto _L7
_L7:
            boolean flag2;
            if (amX != null && !amX.isEmpty())
            {
                break MISSING_BLOCK_LABEL_140;
            }
            if (a1.amX == null)
            {
                break; /* Loop/switch isn't completed */
            }
            flag2 = a1.amX.isEmpty();
            flag1 = false;
            if (!flag2) goto _L4; else goto _L8
_L8:
            return true;
            return amX.equals(a1.amX);
        }

        public int hashCode()
        {
            int k = 31 * (31 * (31 * (527 + level) + eE) + eF);
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

        public a()
        {
            b();
        }
    }

    public static final class b extends ma
    {

        private static volatile b eG[];
        public int eH[];
        public int eI;
        public boolean eJ;
        public boolean eK;
        public int name;

        public static b[] d()
        {
            if (eG == null)
            {
                synchronized (mc.ana)
                {
                    if (eG == null)
                    {
                        eG = new b[0];
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
                for (int k = 0; k < eH.length; k++)
                {
                    lz1.p(3, eH[k]);
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
            int k = 0;
            int l = super.c();
            if (eK)
            {
                l += lz.b(1, eK);
            }
            int i1 = l + lz.r(2, eI);
            int j1;
            if (eH != null && eH.length > 0)
            {
                for (int k1 = 0; k1 < eH.length; k1++)
                {
                    k += lz.eE(eH[k1]);
                }

                j1 = i1 + k + 1 * eH.length;
            } else
            {
                j1 = i1;
            }
            if (name != 0)
            {
                j1 += lz.r(4, name);
            }
            if (eJ)
            {
                j1 += lz.b(6, eJ);
            }
            return j1;
        }

        public b c(ly ly1)
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
                    eK = ly1.nF();
                    break;

                case 16: // '\020'
                    eI = ly1.nE();
                    break;

                case 24: // '\030'
                    int l1 = mh.b(ly1, 24);
                    int i2;
                    int ai1[];
                    if (eH == null)
                    {
                        i2 = 0;
                    } else
                    {
                        i2 = eH.length;
                    }
                    ai1 = new int[l1 + i2];
                    if (i2 != 0)
                    {
                        System.arraycopy(eH, 0, ai1, 0, i2);
                    }
                    for (; i2 < -1 + ai1.length; i2++)
                    {
                        ai1[i2] = ly1.nE();
                        ly1.nB();
                    }

                    ai1[i2] = ly1.nE();
                    eH = ai1;
                    break;

                case 26: // '\032'
                    int l = ly1.ex(ly1.nI());
                    int i1 = ly1.getPosition();
                    int j1;
                    for (j1 = 0; ly1.nN() > 0; j1++)
                    {
                        ly1.nE();
                    }

                    ly1.ez(i1);
                    int k1;
                    int ai[];
                    if (eH == null)
                    {
                        k1 = 0;
                    } else
                    {
                        k1 = eH.length;
                    }
                    ai = new int[j1 + k1];
                    if (k1 != 0)
                    {
                        System.arraycopy(eH, 0, ai, 0, k1);
                    }
                    for (; k1 < ai.length; k1++)
                    {
                        ai[k1] = ly1.nE();
                    }

                    eH = ai;
                    ly1.ey(l);
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

        public b e()
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
            flag = obj instanceof b;
            flag1 = false;
            if (!flag) goto _L4; else goto _L3
_L3:
            b b1;
            boolean flag2;
            b1 = (b)obj;
            flag2 = mc.equals(eH, b1.eH);
            flag1 = false;
            if (!flag2) goto _L4; else goto _L5
_L5:
            int k;
            int l;
            k = eI;
            l = b1.eI;
            flag1 = false;
            if (k != l) goto _L4; else goto _L6
_L6:
            int i1;
            int j1;
            i1 = name;
            j1 = b1.name;
            flag1 = false;
            if (i1 != j1) goto _L4; else goto _L7
_L7:
            boolean flag3;
            boolean flag4;
            flag3 = eJ;
            flag4 = b1.eJ;
            flag1 = false;
            if (flag3 != flag4) goto _L4; else goto _L8
_L8:
            boolean flag5;
            boolean flag6;
            flag5 = eK;
            flag6 = b1.eK;
            flag1 = false;
            if (flag5 != flag6) goto _L4; else goto _L9
_L9:
            boolean flag7;
            if (amX != null && !amX.isEmpty())
            {
                break MISSING_BLOCK_LABEL_183;
            }
            if (b1.amX == null)
            {
                break; /* Loop/switch isn't completed */
            }
            flag7 = b1.amX.isEmpty();
            flag1 = false;
            if (!flag7) goto _L4; else goto _L10
_L10:
            return true;
            return amX.equals(b1.amX);
        }

        public int hashCode()
        {
            char c1 = '\u04CF';
            int k = 31 * (31 * (31 * (527 + mc.hashCode(eH)) + eI) + name);
            char c2;
            int l;
            int i1;
            int j1;
            if (eJ)
            {
                c2 = c1;
            } else
            {
                c2 = '\u04D5';
            }
            l = 31 * (c2 + k);
            if (!eK)
            {
                c1 = '\u04D5';
            }
            i1 = 31 * (l + c1);
            if (amX == null || amX.isEmpty())
            {
                j1 = 0;
            } else
            {
                j1 = amX.hashCode();
            }
            return j1 + i1;
        }

        public b()
        {
            e();
        }
    }

    public static final class c extends ma
    {

        private static volatile c eL[];
        public String eM;
        public long eN;
        public long eO;
        public boolean eP;
        public long eQ;

        public static c[] f()
        {
            if (eL == null)
            {
                synchronized (mc.ana)
                {
                    if (eL == null)
                    {
                        eL = new c[0];
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
            int k = super.c();
            if (!eM.equals(""))
            {
                k += lz.h(1, eM);
            }
            if (eN != 0L)
            {
                k += lz.d(2, eN);
            }
            if (eO != 0x7fffffffL)
            {
                k += lz.d(3, eO);
            }
            if (eP)
            {
                k += lz.b(4, eP);
            }
            if (eQ != 0L)
            {
                k += lz.d(5, eQ);
            }
            return k;
        }

        public c d(ly ly1)
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
            flag = obj instanceof c;
            flag1 = false;
            if (!flag) goto _L4; else goto _L3
_L3:
            c c1;
            String s;
            c1 = (c)obj;
            if (eM != null)
            {
                break MISSING_BLOCK_LABEL_174;
            }
            s = c1.eM;
            flag1 = false;
            if (s != null) goto _L4; else goto _L5
_L5:
            int k;
            k = eN != c1.eN;
            flag1 = false;
            if (k != 0) goto _L4; else goto _L6
_L6:
            int l;
            l = eO != c1.eO;
            flag1 = false;
            if (l != 0) goto _L4; else goto _L7
_L7:
            boolean flag2;
            boolean flag3;
            flag2 = eP;
            flag3 = c1.eP;
            flag1 = false;
            if (flag2 != flag3) goto _L4; else goto _L8
_L8:
            int i1;
            i1 = eQ != c1.eQ;
            flag1 = false;
            if (i1 != 0) goto _L4; else goto _L9
_L9:
            boolean flag4;
            if (amX != null && !amX.isEmpty())
            {
                break MISSING_BLOCK_LABEL_191;
            }
            if (c1.amX == null)
            {
                break; /* Loop/switch isn't completed */
            }
            flag4 = c1.amX.isEmpty();
            flag1 = false;
            if (!flag4) goto _L4; else goto _L10
_L10:
            return true;
            if (!eM.equals(c1.eM))
            {
                return false;
            }
              goto _L5
            return amX.equals(c1.amX);
        }

        public c g()
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
            int k;
            int l;
            char c1;
            int i1;
            List list;
            int j1;
            if (eM == null)
            {
                k = 0;
            } else
            {
                k = eM.hashCode();
            }
            l = 31 * (31 * (31 * (k + 527) + (int)(eN ^ eN >>> 32)) + (int)(eO ^ eO >>> 32));
            if (eP)
            {
                c1 = '\u04CF';
            } else
            {
                c1 = '\u04D5';
            }
            i1 = 31 * (31 * (c1 + l) + (int)(eQ ^ eQ >>> 32));
            list = amX;
            j1 = 0;
            if (list != null)
            {
                boolean flag = amX.isEmpty();
                j1 = 0;
                if (!flag)
                {
                    j1 = amX.hashCode();
                }
            }
            return i1 + j1;
        }

        public c()
        {
            g();
        }
    }

    public static final class d extends ma
    {

        public d.a eR[];
        public d.a eS[];
        public c eT[];

        public void a(lz lz1)
            throws IOException
        {
            if (eR != null && eR.length > 0)
            {
                for (int j1 = 0; j1 < eR.length; j1++)
                {
                    d.a a2 = eR[j1];
                    if (a2 != null)
                    {
                        lz1.a(1, a2);
                    }
                }

            }
            if (eS != null && eS.length > 0)
            {
                for (int i1 = 0; i1 < eS.length; i1++)
                {
                    d.a a1 = eS[i1];
                    if (a1 != null)
                    {
                        lz1.a(2, a1);
                    }
                }

            }
            if (eT != null)
            {
                int k = eT.length;
                int l = 0;
                if (k > 0)
                {
                    for (; l < eT.length; l++)
                    {
                        c c1 = eT[l];
                        if (c1 != null)
                        {
                            lz1.a(3, c1);
                        }
                    }

                }
            }
            super.a(lz1);
        }

        public me b(ly ly1)
            throws IOException
        {
            return e(ly1);
        }

        protected int c()
        {
            int k = super.c();
            if (eR != null && eR.length > 0)
            {
                int l1 = k;
                for (int i2 = 0; i2 < eR.length; i2++)
                {
                    d.a a2 = eR[i2];
                    if (a2 != null)
                    {
                        l1 += lz.b(1, a2);
                    }
                }

                k = l1;
            }
            if (eS != null && eS.length > 0)
            {
                int j1 = k;
                for (int k1 = 0; k1 < eS.length; k1++)
                {
                    d.a a1 = eS[k1];
                    if (a1 != null)
                    {
                        j1 += lz.b(2, a1);
                    }
                }

                k = j1;
            }
            if (eT != null)
            {
                int l = eT.length;
                int i1 = 0;
                if (l > 0)
                {
                    for (; i1 < eT.length; i1++)
                    {
                        c c1 = eT[i1];
                        if (c1 != null)
                        {
                            k += lz.b(3, c1);
                        }
                    }

                }
            }
            return k;
        }

        public d e(ly ly1)
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

                case 10: // '\n'
                    int l1 = mh.b(ly1, 10);
                    int i2;
                    d.a aa1[];
                    if (eR == null)
                    {
                        i2 = 0;
                    } else
                    {
                        i2 = eR.length;
                    }
                    aa1 = new d.a[l1 + i2];
                    if (i2 != 0)
                    {
                        System.arraycopy(eR, 0, aa1, 0, i2);
                    }
                    for (; i2 < -1 + aa1.length; i2++)
                    {
                        aa1[i2] = new d.a();
                        ly1.a(aa1[i2]);
                        ly1.nB();
                    }

                    aa1[i2] = new d.a();
                    ly1.a(aa1[i2]);
                    eR = aa1;
                    break;

                case 18: // '\022'
                    int j1 = mh.b(ly1, 18);
                    int k1;
                    d.a aa[];
                    if (eS == null)
                    {
                        k1 = 0;
                    } else
                    {
                        k1 = eS.length;
                    }
                    aa = new d.a[j1 + k1];
                    if (k1 != 0)
                    {
                        System.arraycopy(eS, 0, aa, 0, k1);
                    }
                    for (; k1 < -1 + aa.length; k1++)
                    {
                        aa[k1] = new d.a();
                        ly1.a(aa[k1]);
                        ly1.nB();
                    }

                    aa[k1] = new d.a();
                    ly1.a(aa[k1]);
                    eS = aa;
                    break;

                case 26: // '\032'
                    int l = mh.b(ly1, 26);
                    int i1;
                    c ac[];
                    if (eT == null)
                    {
                        i1 = 0;
                    } else
                    {
                        i1 = eT.length;
                    }
                    ac = new c[l + i1];
                    if (i1 != 0)
                    {
                        System.arraycopy(eT, 0, ac, 0, i1);
                    }
                    for (; i1 < -1 + ac.length; i1++)
                    {
                        ac[i1] = new c();
                        ly1.a(ac[i1]);
                        ly1.nB();
                    }

                    ac[i1] = new c();
                    ly1.a(ac[i1]);
                    eT = ac;
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
            flag = obj instanceof d;
            flag1 = false;
            if (!flag) goto _L4; else goto _L3
_L3:
            d d1;
            boolean flag2;
            d1 = (d)obj;
            flag2 = mc.equals(eR, d1.eR);
            flag1 = false;
            if (!flag2) goto _L4; else goto _L5
_L5:
            boolean flag3;
            flag3 = mc.equals(eS, d1.eS);
            flag1 = false;
            if (!flag3) goto _L4; else goto _L6
_L6:
            boolean flag4;
            flag4 = mc.equals(eT, d1.eT);
            flag1 = false;
            if (!flag4) goto _L4; else goto _L7
_L7:
            boolean flag5;
            if (amX != null && !amX.isEmpty())
            {
                break MISSING_BLOCK_LABEL_137;
            }
            if (d1.amX == null)
            {
                break; /* Loop/switch isn't completed */
            }
            flag5 = d1.amX.isEmpty();
            flag1 = false;
            if (!flag5) goto _L4; else goto _L8
_L8:
            return true;
            return amX.equals(d1.amX);
        }

        public d h()
        {
            eR = com.google.android.gms.internal.d.a.r();
            eS = com.google.android.gms.internal.d.a.r();
            eT = c.f();
            amX = null;
            anb = -1;
            return this;
        }

        public int hashCode()
        {
            int k = 31 * (31 * (31 * (527 + mc.hashCode(eR)) + mc.hashCode(eS)) + mc.hashCode(eT));
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

        public d()
        {
            h();
        }
    }

    public static final class e extends ma
    {

        private static volatile e eU[];
        public int key;
        public int value;

        public static e[] i()
        {
            if (eU == null)
            {
                synchronized (mc.ana)
                {
                    if (eU == null)
                    {
                        eU = new e[0];
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
            flag = obj instanceof e;
            flag1 = false;
            if (!flag) goto _L4; else goto _L3
_L3:
            e e1;
            int k;
            int l;
            e1 = (e)obj;
            k = key;
            l = e1.key;
            flag1 = false;
            if (k != l) goto _L4; else goto _L5
_L5:
            int i1;
            int j1;
            i1 = value;
            j1 = e1.value;
            flag1 = false;
            if (i1 != j1) goto _L4; else goto _L6
_L6:
            boolean flag2;
            if (amX != null && !amX.isEmpty())
            {
                break MISSING_BLOCK_LABEL_118;
            }
            if (e1.amX == null)
            {
                break; /* Loop/switch isn't completed */
            }
            flag2 = e1.amX.isEmpty();
            flag1 = false;
            if (!flag2) goto _L4; else goto _L7
_L7:
            return true;
            return amX.equals(e1.amX);
        }

        public e f(ly ly1)
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

        public e j()
        {
            key = 0;
            value = 0;
            amX = null;
            anb = -1;
            return this;
        }

        public e()
        {
            j();
        }
    }

    public static final class f extends ma
    {

        public String eV[];
        public String eW[];
        public d.a eX[];
        public e eY[];
        public b eZ[];
        public b fa[];
        public b fb[];
        public g fc[];
        public String fd;
        public String fe;
        public String ff;
        public String fg;
        public a fh;
        public float fi;
        public boolean fj;
        public String fk[];
        public int fl;

        public static f a(byte abyte0[])
            throws md
        {
            return (f)me.a(new f(), abyte0);
        }

        public void a(lz lz1)
            throws IOException
        {
            if (eW != null && eW.length > 0)
            {
                for (int i3 = 0; i3 < eW.length; i3++)
                {
                    String s2 = eW[i3];
                    if (s2 != null)
                    {
                        lz1.b(1, s2);
                    }
                }

            }
            if (eX != null && eX.length > 0)
            {
                for (int l2 = 0; l2 < eX.length; l2++)
                {
                    d.a a1 = eX[l2];
                    if (a1 != null)
                    {
                        lz1.a(2, a1);
                    }
                }

            }
            if (eY != null && eY.length > 0)
            {
                for (int k2 = 0; k2 < eY.length; k2++)
                {
                    e e1 = eY[k2];
                    if (e1 != null)
                    {
                        lz1.a(3, e1);
                    }
                }

            }
            if (eZ != null && eZ.length > 0)
            {
                for (int j2 = 0; j2 < eZ.length; j2++)
                {
                    b b3 = eZ[j2];
                    if (b3 != null)
                    {
                        lz1.a(4, b3);
                    }
                }

            }
            if (fa != null && fa.length > 0)
            {
                for (int i2 = 0; i2 < fa.length; i2++)
                {
                    b b2 = fa[i2];
                    if (b2 != null)
                    {
                        lz1.a(5, b2);
                    }
                }

            }
            if (fb != null && fb.length > 0)
            {
                for (int l1 = 0; l1 < fb.length; l1++)
                {
                    b b1 = fb[l1];
                    if (b1 != null)
                    {
                        lz1.a(6, b1);
                    }
                }

            }
            if (fc != null && fc.length > 0)
            {
                for (int k1 = 0; k1 < fc.length; k1++)
                {
                    g g1 = fc[k1];
                    if (g1 != null)
                    {
                        lz1.a(7, g1);
                    }
                }

            }
            if (!fd.equals(""))
            {
                lz1.b(9, fd);
            }
            if (!fe.equals(""))
            {
                lz1.b(10, fe);
            }
            if (!ff.equals("0"))
            {
                lz1.b(12, ff);
            }
            if (!fg.equals(""))
            {
                lz1.b(13, fg);
            }
            if (fh != null)
            {
                lz1.a(14, fh);
            }
            if (Float.floatToIntBits(fi) != Float.floatToIntBits(0.0F))
            {
                lz1.b(15, fi);
            }
            if (fk != null && fk.length > 0)
            {
                for (int j1 = 0; j1 < fk.length; j1++)
                {
                    String s1 = fk[j1];
                    if (s1 != null)
                    {
                        lz1.b(16, s1);
                    }
                }

            }
            if (fl != 0)
            {
                lz1.p(17, fl);
            }
            if (fj)
            {
                lz1.a(18, fj);
            }
            if (eV != null)
            {
                int l = eV.length;
                int i1 = 0;
                if (l > 0)
                {
                    for (; i1 < eV.length; i1++)
                    {
                        String s = eV[i1];
                        if (s != null)
                        {
                            lz1.b(19, s);
                        }
                    }

                }
            }
            super.a(lz1);
        }

        public me b(ly ly1)
            throws IOException
        {
            return g(ly1);
        }

        protected int c()
        {
            int l = 0;
            int i1 = super.c();
            int j1;
            if (eW != null && eW.length > 0)
            {
                int l5 = 0;
                int i6 = 0;
                int j6 = 0;
                for (; l5 < eW.length; l5++)
                {
                    String s2 = eW[l5];
                    if (s2 != null)
                    {
                        j6++;
                        i6 += lz.cz(s2);
                    }
                }

                j1 = i1 + i6 + j6 * 1;
            } else
            {
                j1 = i1;
            }
            if (eX != null && eX.length > 0)
            {
                int j5 = j1;
                for (int k5 = 0; k5 < eX.length; k5++)
                {
                    d.a a1 = eX[k5];
                    if (a1 != null)
                    {
                        j5 += lz.b(2, a1);
                    }
                }

                j1 = j5;
            }
            if (eY != null && eY.length > 0)
            {
                int l4 = j1;
                for (int i5 = 0; i5 < eY.length; i5++)
                {
                    e e1 = eY[i5];
                    if (e1 != null)
                    {
                        l4 += lz.b(3, e1);
                    }
                }

                j1 = l4;
            }
            if (eZ != null && eZ.length > 0)
            {
                int j4 = j1;
                for (int k4 = 0; k4 < eZ.length; k4++)
                {
                    b b3 = eZ[k4];
                    if (b3 != null)
                    {
                        j4 += lz.b(4, b3);
                    }
                }

                j1 = j4;
            }
            if (fa != null && fa.length > 0)
            {
                int l3 = j1;
                for (int i4 = 0; i4 < fa.length; i4++)
                {
                    b b2 = fa[i4];
                    if (b2 != null)
                    {
                        l3 += lz.b(5, b2);
                    }
                }

                j1 = l3;
            }
            if (fb != null && fb.length > 0)
            {
                int j3 = j1;
                for (int k3 = 0; k3 < fb.length; k3++)
                {
                    b b1 = fb[k3];
                    if (b1 != null)
                    {
                        j3 += lz.b(6, b1);
                    }
                }

                j1 = j3;
            }
            if (fc != null && fc.length > 0)
            {
                int l2 = j1;
                for (int i3 = 0; i3 < fc.length; i3++)
                {
                    g g1 = fc[i3];
                    if (g1 != null)
                    {
                        l2 += lz.b(7, g1);
                    }
                }

                j1 = l2;
            }
            if (!fd.equals(""))
            {
                j1 += lz.h(9, fd);
            }
            if (!fe.equals(""))
            {
                j1 += lz.h(10, fe);
            }
            if (!ff.equals("0"))
            {
                j1 += lz.h(12, ff);
            }
            if (!fg.equals(""))
            {
                j1 += lz.h(13, fg);
            }
            if (fh != null)
            {
                j1 += lz.b(14, fh);
            }
            if (Float.floatToIntBits(fi) != Float.floatToIntBits(0.0F))
            {
                j1 += lz.c(15, fi);
            }
            if (fk != null && fk.length > 0)
            {
                int i2 = 0;
                int j2 = 0;
                int k2 = 0;
                for (; i2 < fk.length; i2++)
                {
                    String s1 = fk[i2];
                    if (s1 != null)
                    {
                        k2++;
                        j2 += lz.cz(s1);
                    }
                }

                j1 = j1 + j2 + k2 * 2;
            }
            if (fl != 0)
            {
                j1 += lz.r(17, fl);
            }
            if (fj)
            {
                j1 += lz.b(18, fj);
            }
            if (eV != null && eV.length > 0)
            {
                int k1 = 0;
                int l1 = 0;
                for (; l < eV.length; l++)
                {
                    String s = eV[l];
                    if (s != null)
                    {
                        l1++;
                        k1 += lz.cz(s);
                    }
                }

                j1 = j1 + k1 + l1 * 2;
            }
            return j1;
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
            flag = obj instanceof f;
            flag1 = false;
            if (!flag) goto _L4; else goto _L3
_L3:
            f f1;
            boolean flag2;
            f1 = (f)obj;
            flag2 = mc.equals(eV, f1.eV);
            flag1 = false;
            if (!flag2) goto _L4; else goto _L5
_L5:
            boolean flag3;
            flag3 = mc.equals(eW, f1.eW);
            flag1 = false;
            if (!flag3) goto _L4; else goto _L6
_L6:
            boolean flag4;
            flag4 = mc.equals(eX, f1.eX);
            flag1 = false;
            if (!flag4) goto _L4; else goto _L7
_L7:
            boolean flag5;
            flag5 = mc.equals(eY, f1.eY);
            flag1 = false;
            if (!flag5) goto _L4; else goto _L8
_L8:
            boolean flag6;
            flag6 = mc.equals(eZ, f1.eZ);
            flag1 = false;
            if (!flag6) goto _L4; else goto _L9
_L9:
            boolean flag7;
            flag7 = mc.equals(fa, f1.fa);
            flag1 = false;
            if (!flag7) goto _L4; else goto _L10
_L10:
            boolean flag8;
            flag8 = mc.equals(fb, f1.fb);
            flag1 = false;
            if (!flag8) goto _L4; else goto _L11
_L11:
            boolean flag9;
            flag9 = mc.equals(fc, f1.fc);
            flag1 = false;
            if (!flag9) goto _L4; else goto _L12
_L12:
            if (fd != null) goto _L14; else goto _L13
_L13:
            String s3;
            s3 = f1.fd;
            flag1 = false;
            if (s3 != null) goto _L4; else goto _L15
_L15:
            if (fe != null) goto _L17; else goto _L16
_L16:
            String s2;
            s2 = f1.fe;
            flag1 = false;
            if (s2 != null) goto _L4; else goto _L18
_L18:
            if (ff != null) goto _L20; else goto _L19
_L19:
            String s1;
            s1 = f1.ff;
            flag1 = false;
            if (s1 != null) goto _L4; else goto _L21
_L21:
            if (fg != null) goto _L23; else goto _L22
_L22:
            String s;
            s = f1.fg;
            flag1 = false;
            if (s != null) goto _L4; else goto _L24
_L24:
            if (fh != null) goto _L26; else goto _L25
_L25:
            a a1;
            a1 = f1.fh;
            flag1 = false;
            if (a1 != null) goto _L4; else goto _L27
_L27:
            int l;
            int i1;
            l = Float.floatToIntBits(fi);
            i1 = Float.floatToIntBits(f1.fi);
            flag1 = false;
            if (l != i1) goto _L4; else goto _L28
_L28:
            boolean flag10;
            boolean flag11;
            flag10 = fj;
            flag11 = f1.fj;
            flag1 = false;
            if (flag10 != flag11) goto _L4; else goto _L29
_L29:
            boolean flag12;
            flag12 = mc.equals(fk, f1.fk);
            flag1 = false;
            if (!flag12) goto _L4; else goto _L30
_L30:
            int j1;
            int k1;
            j1 = fl;
            k1 = f1.fl;
            flag1 = false;
            if (j1 != k1) goto _L4; else goto _L31
_L31:
            boolean flag13;
            if (amX != null && !amX.isEmpty())
            {
                break MISSING_BLOCK_LABEL_525;
            }
            if (f1.amX == null)
            {
                break; /* Loop/switch isn't completed */
            }
            flag13 = f1.amX.isEmpty();
            flag1 = false;
            if (!flag13) goto _L4; else goto _L32
_L32:
            return true;
_L14:
            if (!fd.equals(f1.fd))
            {
                return false;
            }
              goto _L15
_L17:
            if (!fe.equals(f1.fe))
            {
                return false;
            }
              goto _L18
_L20:
            if (!ff.equals(f1.ff))
            {
                return false;
            }
              goto _L21
_L23:
            if (!fg.equals(f1.fg))
            {
                return false;
            }
              goto _L24
_L26:
            if (!fh.equals(f1.fh))
            {
                return false;
            }
              goto _L27
            return amX.equals(f1.amX);
              goto _L15
        }

        public f g(ly ly1)
            throws IOException
        {
            do
            {
                int l = ly1.nB();
                switch (l)
                {
                default:
                    if (a(ly1, l))
                    {
                        continue;
                    }
                    // fall through

                case 0: // '\0'
                    return this;

                case 10: // '\n'
                    int i5 = mh.b(ly1, 10);
                    int j5;
                    String as2[];
                    if (eW == null)
                    {
                        j5 = 0;
                    } else
                    {
                        j5 = eW.length;
                    }
                    as2 = new String[i5 + j5];
                    if (j5 != 0)
                    {
                        System.arraycopy(eW, 0, as2, 0, j5);
                    }
                    for (; j5 < -1 + as2.length; j5++)
                    {
                        as2[j5] = ly1.readString();
                        ly1.nB();
                    }

                    as2[j5] = ly1.readString();
                    eW = as2;
                    break;

                case 18: // '\022'
                    int k4 = mh.b(ly1, 18);
                    int l4;
                    d.a aa[];
                    if (eX == null)
                    {
                        l4 = 0;
                    } else
                    {
                        l4 = eX.length;
                    }
                    aa = new d.a[k4 + l4];
                    if (l4 != 0)
                    {
                        System.arraycopy(eX, 0, aa, 0, l4);
                    }
                    for (; l4 < -1 + aa.length; l4++)
                    {
                        aa[l4] = new d.a();
                        ly1.a(aa[l4]);
                        ly1.nB();
                    }

                    aa[l4] = new d.a();
                    ly1.a(aa[l4]);
                    eX = aa;
                    break;

                case 26: // '\032'
                    int i4 = mh.b(ly1, 26);
                    int j4;
                    e ae[];
                    if (eY == null)
                    {
                        j4 = 0;
                    } else
                    {
                        j4 = eY.length;
                    }
                    ae = new e[i4 + j4];
                    if (j4 != 0)
                    {
                        System.arraycopy(eY, 0, ae, 0, j4);
                    }
                    for (; j4 < -1 + ae.length; j4++)
                    {
                        ae[j4] = new e();
                        ly1.a(ae[j4]);
                        ly1.nB();
                    }

                    ae[j4] = new e();
                    ly1.a(ae[j4]);
                    eY = ae;
                    break;

                case 34: // '"'
                    int k3 = mh.b(ly1, 34);
                    int l3;
                    b ab2[];
                    if (eZ == null)
                    {
                        l3 = 0;
                    } else
                    {
                        l3 = eZ.length;
                    }
                    ab2 = new b[k3 + l3];
                    if (l3 != 0)
                    {
                        System.arraycopy(eZ, 0, ab2, 0, l3);
                    }
                    for (; l3 < -1 + ab2.length; l3++)
                    {
                        ab2[l3] = new b();
                        ly1.a(ab2[l3]);
                        ly1.nB();
                    }

                    ab2[l3] = new b();
                    ly1.a(ab2[l3]);
                    eZ = ab2;
                    break;

                case 42: // '*'
                    int i3 = mh.b(ly1, 42);
                    int j3;
                    b ab1[];
                    if (fa == null)
                    {
                        j3 = 0;
                    } else
                    {
                        j3 = fa.length;
                    }
                    ab1 = new b[i3 + j3];
                    if (j3 != 0)
                    {
                        System.arraycopy(fa, 0, ab1, 0, j3);
                    }
                    for (; j3 < -1 + ab1.length; j3++)
                    {
                        ab1[j3] = new b();
                        ly1.a(ab1[j3]);
                        ly1.nB();
                    }

                    ab1[j3] = new b();
                    ly1.a(ab1[j3]);
                    fa = ab1;
                    break;

                case 50: // '2'
                    int k2 = mh.b(ly1, 50);
                    int l2;
                    b ab[];
                    if (fb == null)
                    {
                        l2 = 0;
                    } else
                    {
                        l2 = fb.length;
                    }
                    ab = new b[k2 + l2];
                    if (l2 != 0)
                    {
                        System.arraycopy(fb, 0, ab, 0, l2);
                    }
                    for (; l2 < -1 + ab.length; l2++)
                    {
                        ab[l2] = new b();
                        ly1.a(ab[l2]);
                        ly1.nB();
                    }

                    ab[l2] = new b();
                    ly1.a(ab[l2]);
                    fb = ab;
                    break;

                case 58: // ':'
                    int i2 = mh.b(ly1, 58);
                    int j2;
                    g ag[];
                    if (fc == null)
                    {
                        j2 = 0;
                    } else
                    {
                        j2 = fc.length;
                    }
                    ag = new g[i2 + j2];
                    if (j2 != 0)
                    {
                        System.arraycopy(fc, 0, ag, 0, j2);
                    }
                    for (; j2 < -1 + ag.length; j2++)
                    {
                        ag[j2] = new g();
                        ly1.a(ag[j2]);
                        ly1.nB();
                    }

                    ag[j2] = new g();
                    ly1.a(ag[j2]);
                    fc = ag;
                    break;

                case 74: // 'J'
                    fd = ly1.readString();
                    break;

                case 82: // 'R'
                    fe = ly1.readString();
                    break;

                case 98: // 'b'
                    ff = ly1.readString();
                    break;

                case 106: // 'j'
                    fg = ly1.readString();
                    break;

                case 114: // 'r'
                    if (fh == null)
                    {
                        fh = new a();
                    }
                    ly1.a(fh);
                    break;

                case 125: // '}'
                    fi = ly1.readFloat();
                    break;

                case 130: 
                    int k1 = mh.b(ly1, 130);
                    int l1;
                    String as1[];
                    if (fk == null)
                    {
                        l1 = 0;
                    } else
                    {
                        l1 = fk.length;
                    }
                    as1 = new String[k1 + l1];
                    if (l1 != 0)
                    {
                        System.arraycopy(fk, 0, as1, 0, l1);
                    }
                    for (; l1 < -1 + as1.length; l1++)
                    {
                        as1[l1] = ly1.readString();
                        ly1.nB();
                    }

                    as1[l1] = ly1.readString();
                    fk = as1;
                    break;

                case 136: 
                    fl = ly1.nE();
                    break;

                case 144: 
                    fj = ly1.nF();
                    break;

                case 154: 
                    int i1 = mh.b(ly1, 154);
                    int j1;
                    String as[];
                    if (eV == null)
                    {
                        j1 = 0;
                    } else
                    {
                        j1 = eV.length;
                    }
                    as = new String[i1 + j1];
                    if (j1 != 0)
                    {
                        System.arraycopy(eV, 0, as, 0, j1);
                    }
                    for (; j1 < -1 + as.length; j1++)
                    {
                        as[j1] = ly1.readString();
                        ly1.nB();
                    }

                    as[j1] = ly1.readString();
                    eV = as;
                    break;
                }
            } while (true);
        }

        public int hashCode()
        {
            int l = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (527 + mc.hashCode(eV)) + mc.hashCode(eW)) + mc.hashCode(eX)) + mc.hashCode(eY)) + mc.hashCode(eZ)) + mc.hashCode(fa)) + mc.hashCode(fb)) + mc.hashCode(fc));
            int i1;
            int j1;
            int k1;
            int l1;
            int i2;
            int j2;
            int k2;
            int l2;
            int i3;
            int j3;
            char c1;
            int k3;
            List list;
            int l3;
            if (fd == null)
            {
                i1 = 0;
            } else
            {
                i1 = fd.hashCode();
            }
            j1 = 31 * (i1 + l);
            if (fe == null)
            {
                k1 = 0;
            } else
            {
                k1 = fe.hashCode();
            }
            l1 = 31 * (k1 + j1);
            if (ff == null)
            {
                i2 = 0;
            } else
            {
                i2 = ff.hashCode();
            }
            j2 = 31 * (i2 + l1);
            if (fg == null)
            {
                k2 = 0;
            } else
            {
                k2 = fg.hashCode();
            }
            l2 = 31 * (k2 + j2);
            if (fh == null)
            {
                i3 = 0;
            } else
            {
                i3 = fh.hashCode();
            }
            j3 = 31 * (31 * (i3 + l2) + Float.floatToIntBits(fi));
            if (fj)
            {
                c1 = '\u04CF';
            } else
            {
                c1 = '\u04D5';
            }
            k3 = 31 * (31 * (31 * (c1 + j3) + mc.hashCode(fk)) + fl);
            list = amX;
            l3 = 0;
            if (list != null)
            {
                boolean flag = amX.isEmpty();
                l3 = 0;
                if (!flag)
                {
                    l3 = amX.hashCode();
                }
            }
            return k3 + l3;
        }

        public f k()
        {
            eV = mh.ani;
            eW = mh.ani;
            eX = com.google.android.gms.internal.d.a.r();
            eY = e.i();
            eZ = b.d();
            fa = b.d();
            fb = b.d();
            fc = g.l();
            fd = "";
            fe = "";
            ff = "0";
            fg = "";
            fh = null;
            fi = 0.0F;
            fj = false;
            fk = mh.ani;
            fl = 0;
            amX = null;
            anb = -1;
            return this;
        }

        public f()
        {
            k();
        }
    }

    public static final class g extends ma
    {

        private static volatile g fm[];
        public int fn[];
        public int fo[];
        public int fp[];
        public int fq[];
        public int fr[];
        public int fs[];
        public int ft[];
        public int fu[];
        public int fv[];
        public int fw[];

        public static g[] l()
        {
            if (fm == null)
            {
                synchronized (mc.ana)
                {
                    if (fm == null)
                    {
                        fm = new g[0];
                    }
                }
            }
            return fm;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void a(lz lz1)
            throws IOException
        {
            if (fn != null && fn.length > 0)
            {
                for (int j3 = 0; j3 < fn.length; j3++)
                {
                    lz1.p(1, fn[j3]);
                }

            }
            if (fo != null && fo.length > 0)
            {
                for (int i3 = 0; i3 < fo.length; i3++)
                {
                    lz1.p(2, fo[i3]);
                }

            }
            if (fp != null && fp.length > 0)
            {
                for (int l2 = 0; l2 < fp.length; l2++)
                {
                    lz1.p(3, fp[l2]);
                }

            }
            if (fq != null && fq.length > 0)
            {
                for (int k2 = 0; k2 < fq.length; k2++)
                {
                    lz1.p(4, fq[k2]);
                }

            }
            if (fr != null && fr.length > 0)
            {
                for (int j2 = 0; j2 < fr.length; j2++)
                {
                    lz1.p(5, fr[j2]);
                }

            }
            if (fs != null && fs.length > 0)
            {
                for (int i2 = 0; i2 < fs.length; i2++)
                {
                    lz1.p(6, fs[i2]);
                }

            }
            if (ft != null && ft.length > 0)
            {
                for (int l1 = 0; l1 < ft.length; l1++)
                {
                    lz1.p(7, ft[l1]);
                }

            }
            if (fu != null && fu.length > 0)
            {
                for (int k1 = 0; k1 < fu.length; k1++)
                {
                    lz1.p(8, fu[k1]);
                }

            }
            if (fv != null && fv.length > 0)
            {
                for (int j1 = 0; j1 < fv.length; j1++)
                {
                    lz1.p(9, fv[j1]);
                }

            }
            if (fw != null)
            {
                int k = fw.length;
                int i1 = 0;
                if (k > 0)
                {
                    for (; i1 < fw.length; i1++)
                    {
                        lz1.p(10, fw[i1]);
                    }

                }
            }
            super.a(lz1);
        }

        public me b(ly ly1)
            throws IOException
        {
            return h(ly1);
        }

        protected int c()
        {
            int k = 0;
            int i1 = super.c();
            int j1;
            if (fn != null && fn.length > 0)
            {
                int l5 = 0;
                int i6 = 0;
                for (; l5 < fn.length; l5++)
                {
                    i6 += lz.eE(fn[l5]);
                }

                j1 = i1 + i6 + 1 * fn.length;
            } else
            {
                j1 = i1;
            }
            if (fo != null && fo.length > 0)
            {
                int j5 = 0;
                int k5 = 0;
                for (; j5 < fo.length; j5++)
                {
                    k5 += lz.eE(fo[j5]);
                }

                j1 = j1 + k5 + 1 * fo.length;
            }
            if (fp != null && fp.length > 0)
            {
                int l4 = 0;
                int i5 = 0;
                for (; l4 < fp.length; l4++)
                {
                    i5 += lz.eE(fp[l4]);
                }

                j1 = j1 + i5 + 1 * fp.length;
            }
            if (fq != null && fq.length > 0)
            {
                int j4 = 0;
                int k4 = 0;
                for (; j4 < fq.length; j4++)
                {
                    k4 += lz.eE(fq[j4]);
                }

                j1 = j1 + k4 + 1 * fq.length;
            }
            if (fr != null && fr.length > 0)
            {
                int l3 = 0;
                int i4 = 0;
                for (; l3 < fr.length; l3++)
                {
                    i4 += lz.eE(fr[l3]);
                }

                j1 = j1 + i4 + 1 * fr.length;
            }
            if (fs != null && fs.length > 0)
            {
                int j3 = 0;
                int k3 = 0;
                for (; j3 < fs.length; j3++)
                {
                    k3 += lz.eE(fs[j3]);
                }

                j1 = j1 + k3 + 1 * fs.length;
            }
            if (ft != null && ft.length > 0)
            {
                int l2 = 0;
                int i3 = 0;
                for (; l2 < ft.length; l2++)
                {
                    i3 += lz.eE(ft[l2]);
                }

                j1 = j1 + i3 + 1 * ft.length;
            }
            if (fu != null && fu.length > 0)
            {
                int j2 = 0;
                int k2 = 0;
                for (; j2 < fu.length; j2++)
                {
                    k2 += lz.eE(fu[j2]);
                }

                j1 = j1 + k2 + 1 * fu.length;
            }
            if (fv != null && fv.length > 0)
            {
                int l1 = 0;
                int i2 = 0;
                for (; l1 < fv.length; l1++)
                {
                    i2 += lz.eE(fv[l1]);
                }

                j1 = j1 + i2 + 1 * fv.length;
            }
            if (fw != null && fw.length > 0)
            {
                int k1 = 0;
                for (; k < fw.length; k++)
                {
                    k1 += lz.eE(fw[k]);
                }

                j1 = j1 + k1 + 1 * fw.length;
            }
            return j1;
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
            flag = obj instanceof g;
            flag1 = false;
            if (!flag) goto _L4; else goto _L3
_L3:
            g g1;
            boolean flag2;
            g1 = (g)obj;
            flag2 = mc.equals(fn, g1.fn);
            flag1 = false;
            if (!flag2) goto _L4; else goto _L5
_L5:
            boolean flag3;
            flag3 = mc.equals(fo, g1.fo);
            flag1 = false;
            if (!flag3) goto _L4; else goto _L6
_L6:
            boolean flag4;
            flag4 = mc.equals(fp, g1.fp);
            flag1 = false;
            if (!flag4) goto _L4; else goto _L7
_L7:
            boolean flag5;
            flag5 = mc.equals(fq, g1.fq);
            flag1 = false;
            if (!flag5) goto _L4; else goto _L8
_L8:
            boolean flag6;
            flag6 = mc.equals(fr, g1.fr);
            flag1 = false;
            if (!flag6) goto _L4; else goto _L9
_L9:
            boolean flag7;
            flag7 = mc.equals(fs, g1.fs);
            flag1 = false;
            if (!flag7) goto _L4; else goto _L10
_L10:
            boolean flag8;
            flag8 = mc.equals(ft, g1.ft);
            flag1 = false;
            if (!flag8) goto _L4; else goto _L11
_L11:
            boolean flag9;
            flag9 = mc.equals(fu, g1.fu);
            flag1 = false;
            if (!flag9) goto _L4; else goto _L12
_L12:
            boolean flag10;
            flag10 = mc.equals(fv, g1.fv);
            flag1 = false;
            if (!flag10) goto _L4; else goto _L13
_L13:
            boolean flag11;
            flag11 = mc.equals(fw, g1.fw);
            flag1 = false;
            if (!flag11) goto _L4; else goto _L14
_L14:
            boolean flag12;
            if (amX != null && !amX.isEmpty())
            {
                break MISSING_BLOCK_LABEL_284;
            }
            if (g1.amX == null)
            {
                break; /* Loop/switch isn't completed */
            }
            flag12 = g1.amX.isEmpty();
            flag1 = false;
            if (!flag12) goto _L4; else goto _L15
_L15:
            return true;
            return amX.equals(g1.amX);
        }

        public g h(ly ly1)
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
                    int k15 = mh.b(ly1, 8);
                    int l15;
                    int ai19[];
                    if (fn == null)
                    {
                        l15 = 0;
                    } else
                    {
                        l15 = fn.length;
                    }
                    ai19 = new int[k15 + l15];
                    if (l15 != 0)
                    {
                        System.arraycopy(fn, 0, ai19, 0, l15);
                    }
                    for (; l15 < -1 + ai19.length; l15++)
                    {
                        ai19[l15] = ly1.nE();
                        ly1.nB();
                    }

                    ai19[l15] = ly1.nE();
                    fn = ai19;
                    break;

                case 10: // '\n'
                    int k14 = ly1.ex(ly1.nI());
                    int l14 = ly1.getPosition();
                    int i15;
                    for (i15 = 0; ly1.nN() > 0; i15++)
                    {
                        ly1.nE();
                    }

                    ly1.ez(l14);
                    int j15;
                    int ai18[];
                    if (fn == null)
                    {
                        j15 = 0;
                    } else
                    {
                        j15 = fn.length;
                    }
                    ai18 = new int[i15 + j15];
                    if (j15 != 0)
                    {
                        System.arraycopy(fn, 0, ai18, 0, j15);
                    }
                    for (; j15 < ai18.length; j15++)
                    {
                        ai18[j15] = ly1.nE();
                    }

                    fn = ai18;
                    ly1.ey(k14);
                    break;

                case 16: // '\020'
                    int i14 = mh.b(ly1, 16);
                    int j14;
                    int ai17[];
                    if (fo == null)
                    {
                        j14 = 0;
                    } else
                    {
                        j14 = fo.length;
                    }
                    ai17 = new int[i14 + j14];
                    if (j14 != 0)
                    {
                        System.arraycopy(fo, 0, ai17, 0, j14);
                    }
                    for (; j14 < -1 + ai17.length; j14++)
                    {
                        ai17[j14] = ly1.nE();
                        ly1.nB();
                    }

                    ai17[j14] = ly1.nE();
                    fo = ai17;
                    break;

                case 18: // '\022'
                    int i13 = ly1.ex(ly1.nI());
                    int j13 = ly1.getPosition();
                    int k13;
                    for (k13 = 0; ly1.nN() > 0; k13++)
                    {
                        ly1.nE();
                    }

                    ly1.ez(j13);
                    int l13;
                    int ai16[];
                    if (fo == null)
                    {
                        l13 = 0;
                    } else
                    {
                        l13 = fo.length;
                    }
                    ai16 = new int[k13 + l13];
                    if (l13 != 0)
                    {
                        System.arraycopy(fo, 0, ai16, 0, l13);
                    }
                    for (; l13 < ai16.length; l13++)
                    {
                        ai16[l13] = ly1.nE();
                    }

                    fo = ai16;
                    ly1.ey(i13);
                    break;

                case 24: // '\030'
                    int k12 = mh.b(ly1, 24);
                    int l12;
                    int ai15[];
                    if (fp == null)
                    {
                        l12 = 0;
                    } else
                    {
                        l12 = fp.length;
                    }
                    ai15 = new int[k12 + l12];
                    if (l12 != 0)
                    {
                        System.arraycopy(fp, 0, ai15, 0, l12);
                    }
                    for (; l12 < -1 + ai15.length; l12++)
                    {
                        ai15[l12] = ly1.nE();
                        ly1.nB();
                    }

                    ai15[l12] = ly1.nE();
                    fp = ai15;
                    break;

                case 26: // '\032'
                    int k11 = ly1.ex(ly1.nI());
                    int l11 = ly1.getPosition();
                    int i12;
                    for (i12 = 0; ly1.nN() > 0; i12++)
                    {
                        ly1.nE();
                    }

                    ly1.ez(l11);
                    int j12;
                    int ai14[];
                    if (fp == null)
                    {
                        j12 = 0;
                    } else
                    {
                        j12 = fp.length;
                    }
                    ai14 = new int[i12 + j12];
                    if (j12 != 0)
                    {
                        System.arraycopy(fp, 0, ai14, 0, j12);
                    }
                    for (; j12 < ai14.length; j12++)
                    {
                        ai14[j12] = ly1.nE();
                    }

                    fp = ai14;
                    ly1.ey(k11);
                    break;

                case 32: // ' '
                    int i11 = mh.b(ly1, 32);
                    int j11;
                    int ai13[];
                    if (fq == null)
                    {
                        j11 = 0;
                    } else
                    {
                        j11 = fq.length;
                    }
                    ai13 = new int[i11 + j11];
                    if (j11 != 0)
                    {
                        System.arraycopy(fq, 0, ai13, 0, j11);
                    }
                    for (; j11 < -1 + ai13.length; j11++)
                    {
                        ai13[j11] = ly1.nE();
                        ly1.nB();
                    }

                    ai13[j11] = ly1.nE();
                    fq = ai13;
                    break;

                case 34: // '"'
                    int i10 = ly1.ex(ly1.nI());
                    int j10 = ly1.getPosition();
                    int k10;
                    for (k10 = 0; ly1.nN() > 0; k10++)
                    {
                        ly1.nE();
                    }

                    ly1.ez(j10);
                    int l10;
                    int ai12[];
                    if (fq == null)
                    {
                        l10 = 0;
                    } else
                    {
                        l10 = fq.length;
                    }
                    ai12 = new int[k10 + l10];
                    if (l10 != 0)
                    {
                        System.arraycopy(fq, 0, ai12, 0, l10);
                    }
                    for (; l10 < ai12.length; l10++)
                    {
                        ai12[l10] = ly1.nE();
                    }

                    fq = ai12;
                    ly1.ey(i10);
                    break;

                case 40: // '('
                    int k9 = mh.b(ly1, 40);
                    int l9;
                    int ai11[];
                    if (fr == null)
                    {
                        l9 = 0;
                    } else
                    {
                        l9 = fr.length;
                    }
                    ai11 = new int[k9 + l9];
                    if (l9 != 0)
                    {
                        System.arraycopy(fr, 0, ai11, 0, l9);
                    }
                    for (; l9 < -1 + ai11.length; l9++)
                    {
                        ai11[l9] = ly1.nE();
                        ly1.nB();
                    }

                    ai11[l9] = ly1.nE();
                    fr = ai11;
                    break;

                case 42: // '*'
                    int k8 = ly1.ex(ly1.nI());
                    int l8 = ly1.getPosition();
                    int i9;
                    for (i9 = 0; ly1.nN() > 0; i9++)
                    {
                        ly1.nE();
                    }

                    ly1.ez(l8);
                    int j9;
                    int ai10[];
                    if (fr == null)
                    {
                        j9 = 0;
                    } else
                    {
                        j9 = fr.length;
                    }
                    ai10 = new int[i9 + j9];
                    if (j9 != 0)
                    {
                        System.arraycopy(fr, 0, ai10, 0, j9);
                    }
                    for (; j9 < ai10.length; j9++)
                    {
                        ai10[j9] = ly1.nE();
                    }

                    fr = ai10;
                    ly1.ey(k8);
                    break;

                case 48: // '0'
                    int i8 = mh.b(ly1, 48);
                    int j8;
                    int ai9[];
                    if (fs == null)
                    {
                        j8 = 0;
                    } else
                    {
                        j8 = fs.length;
                    }
                    ai9 = new int[i8 + j8];
                    if (j8 != 0)
                    {
                        System.arraycopy(fs, 0, ai9, 0, j8);
                    }
                    for (; j8 < -1 + ai9.length; j8++)
                    {
                        ai9[j8] = ly1.nE();
                        ly1.nB();
                    }

                    ai9[j8] = ly1.nE();
                    fs = ai9;
                    break;

                case 50: // '2'
                    int i7 = ly1.ex(ly1.nI());
                    int j7 = ly1.getPosition();
                    int k7;
                    for (k7 = 0; ly1.nN() > 0; k7++)
                    {
                        ly1.nE();
                    }

                    ly1.ez(j7);
                    int l7;
                    int ai8[];
                    if (fs == null)
                    {
                        l7 = 0;
                    } else
                    {
                        l7 = fs.length;
                    }
                    ai8 = new int[k7 + l7];
                    if (l7 != 0)
                    {
                        System.arraycopy(fs, 0, ai8, 0, l7);
                    }
                    for (; l7 < ai8.length; l7++)
                    {
                        ai8[l7] = ly1.nE();
                    }

                    fs = ai8;
                    ly1.ey(i7);
                    break;

                case 56: // '8'
                    int k6 = mh.b(ly1, 56);
                    int l6;
                    int ai7[];
                    if (ft == null)
                    {
                        l6 = 0;
                    } else
                    {
                        l6 = ft.length;
                    }
                    ai7 = new int[k6 + l6];
                    if (l6 != 0)
                    {
                        System.arraycopy(ft, 0, ai7, 0, l6);
                    }
                    for (; l6 < -1 + ai7.length; l6++)
                    {
                        ai7[l6] = ly1.nE();
                        ly1.nB();
                    }

                    ai7[l6] = ly1.nE();
                    ft = ai7;
                    break;

                case 58: // ':'
                    int k5 = ly1.ex(ly1.nI());
                    int l5 = ly1.getPosition();
                    int i6;
                    for (i6 = 0; ly1.nN() > 0; i6++)
                    {
                        ly1.nE();
                    }

                    ly1.ez(l5);
                    int j6;
                    int ai6[];
                    if (ft == null)
                    {
                        j6 = 0;
                    } else
                    {
                        j6 = ft.length;
                    }
                    ai6 = new int[i6 + j6];
                    if (j6 != 0)
                    {
                        System.arraycopy(ft, 0, ai6, 0, j6);
                    }
                    for (; j6 < ai6.length; j6++)
                    {
                        ai6[j6] = ly1.nE();
                    }

                    ft = ai6;
                    ly1.ey(k5);
                    break;

                case 64: // '@'
                    int i5 = mh.b(ly1, 64);
                    int j5;
                    int ai5[];
                    if (fu == null)
                    {
                        j5 = 0;
                    } else
                    {
                        j5 = fu.length;
                    }
                    ai5 = new int[i5 + j5];
                    if (j5 != 0)
                    {
                        System.arraycopy(fu, 0, ai5, 0, j5);
                    }
                    for (; j5 < -1 + ai5.length; j5++)
                    {
                        ai5[j5] = ly1.nE();
                        ly1.nB();
                    }

                    ai5[j5] = ly1.nE();
                    fu = ai5;
                    break;

                case 66: // 'B'
                    int i4 = ly1.ex(ly1.nI());
                    int j4 = ly1.getPosition();
                    int k4;
                    for (k4 = 0; ly1.nN() > 0; k4++)
                    {
                        ly1.nE();
                    }

                    ly1.ez(j4);
                    int l4;
                    int ai4[];
                    if (fu == null)
                    {
                        l4 = 0;
                    } else
                    {
                        l4 = fu.length;
                    }
                    ai4 = new int[k4 + l4];
                    if (l4 != 0)
                    {
                        System.arraycopy(fu, 0, ai4, 0, l4);
                    }
                    for (; l4 < ai4.length; l4++)
                    {
                        ai4[l4] = ly1.nE();
                    }

                    fu = ai4;
                    ly1.ey(i4);
                    break;

                case 72: // 'H'
                    int k3 = mh.b(ly1, 72);
                    int l3;
                    int ai3[];
                    if (fv == null)
                    {
                        l3 = 0;
                    } else
                    {
                        l3 = fv.length;
                    }
                    ai3 = new int[k3 + l3];
                    if (l3 != 0)
                    {
                        System.arraycopy(fv, 0, ai3, 0, l3);
                    }
                    for (; l3 < -1 + ai3.length; l3++)
                    {
                        ai3[l3] = ly1.nE();
                        ly1.nB();
                    }

                    ai3[l3] = ly1.nE();
                    fv = ai3;
                    break;

                case 74: // 'J'
                    int k2 = ly1.ex(ly1.nI());
                    int l2 = ly1.getPosition();
                    int i3;
                    for (i3 = 0; ly1.nN() > 0; i3++)
                    {
                        ly1.nE();
                    }

                    ly1.ez(l2);
                    int j3;
                    int ai2[];
                    if (fv == null)
                    {
                        j3 = 0;
                    } else
                    {
                        j3 = fv.length;
                    }
                    ai2 = new int[i3 + j3];
                    if (j3 != 0)
                    {
                        System.arraycopy(fv, 0, ai2, 0, j3);
                    }
                    for (; j3 < ai2.length; j3++)
                    {
                        ai2[j3] = ly1.nE();
                    }

                    fv = ai2;
                    ly1.ey(k2);
                    break;

                case 80: // 'P'
                    int i2 = mh.b(ly1, 80);
                    int j2;
                    int ai1[];
                    if (fw == null)
                    {
                        j2 = 0;
                    } else
                    {
                        j2 = fw.length;
                    }
                    ai1 = new int[i2 + j2];
                    if (j2 != 0)
                    {
                        System.arraycopy(fw, 0, ai1, 0, j2);
                    }
                    for (; j2 < -1 + ai1.length; j2++)
                    {
                        ai1[j2] = ly1.nE();
                        ly1.nB();
                    }

                    ai1[j2] = ly1.nE();
                    fw = ai1;
                    break;

                case 82: // 'R'
                    int i1 = ly1.ex(ly1.nI());
                    int j1 = ly1.getPosition();
                    int k1;
                    for (k1 = 0; ly1.nN() > 0; k1++)
                    {
                        ly1.nE();
                    }

                    ly1.ez(j1);
                    int l1;
                    int ai[];
                    if (fw == null)
                    {
                        l1 = 0;
                    } else
                    {
                        l1 = fw.length;
                    }
                    ai = new int[k1 + l1];
                    if (l1 != 0)
                    {
                        System.arraycopy(fw, 0, ai, 0, l1);
                    }
                    for (; l1 < ai.length; l1++)
                    {
                        ai[l1] = ly1.nE();
                    }

                    fw = ai;
                    ly1.ey(i1);
                    break;
                }
            } while (true);
        }

        public int hashCode()
        {
            int k = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (527 + mc.hashCode(fn)) + mc.hashCode(fo)) + mc.hashCode(fp)) + mc.hashCode(fq)) + mc.hashCode(fr)) + mc.hashCode(fs)) + mc.hashCode(ft)) + mc.hashCode(fu)) + mc.hashCode(fv)) + mc.hashCode(fw));
            int i1;
            if (amX == null || amX.isEmpty())
            {
                i1 = 0;
            } else
            {
                i1 = amX.hashCode();
            }
            return i1 + k;
        }

        public g m()
        {
            fn = mh.and;
            fo = mh.and;
            fp = mh.and;
            fq = mh.and;
            fr = mh.and;
            fs = mh.and;
            ft = mh.and;
            fu = mh.and;
            fv = mh.and;
            fw = mh.and;
            amX = null;
            anb = -1;
            return this;
        }

        public g()
        {
            m();
        }
    }

    public static final class h extends ma
    {

        public static final mb fx = mb.a(11, com/google/android/gms/internal/c$h, 810);
        private static final h fy[] = new h[0];
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
                for (int k1 = 0; k1 < fz.length; k1++)
                {
                    lz1.p(1, fz[k1]);
                }

            }
            if (fA != null && fA.length > 0)
            {
                for (int j1 = 0; j1 < fA.length; j1++)
                {
                    lz1.p(2, fA[j1]);
                }

            }
            if (fB != null && fB.length > 0)
            {
                for (int i1 = 0; i1 < fB.length; i1++)
                {
                    lz1.p(3, fB[i1]);
                }

            }
            if (fC != 0)
            {
                lz1.p(4, fC);
            }
            if (fD != null)
            {
                int k = fD.length;
                int l = 0;
                if (k > 0)
                {
                    for (; l < fD.length; l++)
                    {
                        lz1.p(5, fD[l]);
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
            int k = 0;
            int l = super.c();
            int i1;
            if (fz != null && fz.length > 0)
            {
                int k2 = 0;
                int l2 = 0;
                for (; k2 < fz.length; k2++)
                {
                    l2 += lz.eE(fz[k2]);
                }

                i1 = l + l2 + 1 * fz.length;
            } else
            {
                i1 = l;
            }
            if (fA != null && fA.length > 0)
            {
                int i2 = 0;
                int j2 = 0;
                for (; i2 < fA.length; i2++)
                {
                    j2 += lz.eE(fA[i2]);
                }

                i1 = i1 + j2 + 1 * fA.length;
            }
            if (fB != null && fB.length > 0)
            {
                int k1 = 0;
                int l1 = 0;
                for (; k1 < fB.length; k1++)
                {
                    l1 += lz.eE(fB[k1]);
                }

                i1 = i1 + l1 + 1 * fB.length;
            }
            if (fC != 0)
            {
                i1 += lz.r(4, fC);
            }
            if (fD != null && fD.length > 0)
            {
                int j1 = 0;
                for (; k < fD.length; k++)
                {
                    j1 += lz.eE(fD[k]);
                }

                i1 = i1 + j1 + 1 * fD.length;
            }
            if (fE != 0)
            {
                i1 += lz.r(6, fE);
            }
            if (fF != 0)
            {
                i1 += lz.r(7, fF);
            }
            return i1;
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
            flag = obj instanceof h;
            flag1 = false;
            if (!flag) goto _L4; else goto _L3
_L3:
            h h1;
            boolean flag2;
            h1 = (h)obj;
            flag2 = mc.equals(fz, h1.fz);
            flag1 = false;
            if (!flag2) goto _L4; else goto _L5
_L5:
            boolean flag3;
            flag3 = mc.equals(fA, h1.fA);
            flag1 = false;
            if (!flag3) goto _L4; else goto _L6
_L6:
            boolean flag4;
            flag4 = mc.equals(fB, h1.fB);
            flag1 = false;
            if (!flag4) goto _L4; else goto _L7
_L7:
            int k;
            int l;
            k = fC;
            l = h1.fC;
            flag1 = false;
            if (k != l) goto _L4; else goto _L8
_L8:
            boolean flag5;
            flag5 = mc.equals(fD, h1.fD);
            flag1 = false;
            if (!flag5) goto _L4; else goto _L9
_L9:
            int i1;
            int j1;
            i1 = fE;
            j1 = h1.fE;
            flag1 = false;
            if (i1 != j1) goto _L4; else goto _L10
_L10:
            int k1;
            int l1;
            k1 = fF;
            l1 = h1.fF;
            flag1 = false;
            if (k1 != l1) goto _L4; else goto _L11
_L11:
            boolean flag6;
            if (amX != null && !amX.isEmpty())
            {
                break MISSING_BLOCK_LABEL_224;
            }
            if (h1.amX == null)
            {
                break; /* Loop/switch isn't completed */
            }
            flag6 = h1.amX.isEmpty();
            flag1 = false;
            if (!flag6) goto _L4; else goto _L12
_L12:
            return true;
            return amX.equals(h1.amX);
        }

        public int hashCode()
        {
            int k = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (527 + mc.hashCode(fz)) + mc.hashCode(fA)) + mc.hashCode(fB)) + fC) + mc.hashCode(fD)) + fE) + fF);
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

        public h i(ly ly1)
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
                    int j6 = mh.b(ly1, 8);
                    int k6;
                    int ai7[];
                    if (fz == null)
                    {
                        k6 = 0;
                    } else
                    {
                        k6 = fz.length;
                    }
                    ai7 = new int[j6 + k6];
                    if (k6 != 0)
                    {
                        System.arraycopy(fz, 0, ai7, 0, k6);
                    }
                    for (; k6 < -1 + ai7.length; k6++)
                    {
                        ai7[k6] = ly1.nE();
                        ly1.nB();
                    }

                    ai7[k6] = ly1.nE();
                    fz = ai7;
                    break;

                case 10: // '\n'
                    int j5 = ly1.ex(ly1.nI());
                    int k5 = ly1.getPosition();
                    int l5;
                    for (l5 = 0; ly1.nN() > 0; l5++)
                    {
                        ly1.nE();
                    }

                    ly1.ez(k5);
                    int i6;
                    int ai6[];
                    if (fz == null)
                    {
                        i6 = 0;
                    } else
                    {
                        i6 = fz.length;
                    }
                    ai6 = new int[l5 + i6];
                    if (i6 != 0)
                    {
                        System.arraycopy(fz, 0, ai6, 0, i6);
                    }
                    for (; i6 < ai6.length; i6++)
                    {
                        ai6[i6] = ly1.nE();
                    }

                    fz = ai6;
                    ly1.ey(j5);
                    break;

                case 16: // '\020'
                    int l4 = mh.b(ly1, 16);
                    int i5;
                    int ai5[];
                    if (fA == null)
                    {
                        i5 = 0;
                    } else
                    {
                        i5 = fA.length;
                    }
                    ai5 = new int[l4 + i5];
                    if (i5 != 0)
                    {
                        System.arraycopy(fA, 0, ai5, 0, i5);
                    }
                    for (; i5 < -1 + ai5.length; i5++)
                    {
                        ai5[i5] = ly1.nE();
                        ly1.nB();
                    }

                    ai5[i5] = ly1.nE();
                    fA = ai5;
                    break;

                case 18: // '\022'
                    int l3 = ly1.ex(ly1.nI());
                    int i4 = ly1.getPosition();
                    int j4;
                    for (j4 = 0; ly1.nN() > 0; j4++)
                    {
                        ly1.nE();
                    }

                    ly1.ez(i4);
                    int k4;
                    int ai4[];
                    if (fA == null)
                    {
                        k4 = 0;
                    } else
                    {
                        k4 = fA.length;
                    }
                    ai4 = new int[j4 + k4];
                    if (k4 != 0)
                    {
                        System.arraycopy(fA, 0, ai4, 0, k4);
                    }
                    for (; k4 < ai4.length; k4++)
                    {
                        ai4[k4] = ly1.nE();
                    }

                    fA = ai4;
                    ly1.ey(l3);
                    break;

                case 24: // '\030'
                    int j3 = mh.b(ly1, 24);
                    int k3;
                    int ai3[];
                    if (fB == null)
                    {
                        k3 = 0;
                    } else
                    {
                        k3 = fB.length;
                    }
                    ai3 = new int[j3 + k3];
                    if (k3 != 0)
                    {
                        System.arraycopy(fB, 0, ai3, 0, k3);
                    }
                    for (; k3 < -1 + ai3.length; k3++)
                    {
                        ai3[k3] = ly1.nE();
                        ly1.nB();
                    }

                    ai3[k3] = ly1.nE();
                    fB = ai3;
                    break;

                case 26: // '\032'
                    int j2 = ly1.ex(ly1.nI());
                    int k2 = ly1.getPosition();
                    int l2;
                    for (l2 = 0; ly1.nN() > 0; l2++)
                    {
                        ly1.nE();
                    }

                    ly1.ez(k2);
                    int i3;
                    int ai2[];
                    if (fB == null)
                    {
                        i3 = 0;
                    } else
                    {
                        i3 = fB.length;
                    }
                    ai2 = new int[l2 + i3];
                    if (i3 != 0)
                    {
                        System.arraycopy(fB, 0, ai2, 0, i3);
                    }
                    for (; i3 < ai2.length; i3++)
                    {
                        ai2[i3] = ly1.nE();
                    }

                    fB = ai2;
                    ly1.ey(j2);
                    break;

                case 32: // ' '
                    fC = ly1.nE();
                    break;

                case 40: // '('
                    int l1 = mh.b(ly1, 40);
                    int i2;
                    int ai1[];
                    if (fD == null)
                    {
                        i2 = 0;
                    } else
                    {
                        i2 = fD.length;
                    }
                    ai1 = new int[l1 + i2];
                    if (i2 != 0)
                    {
                        System.arraycopy(fD, 0, ai1, 0, i2);
                    }
                    for (; i2 < -1 + ai1.length; i2++)
                    {
                        ai1[i2] = ly1.nE();
                        ly1.nB();
                    }

                    ai1[i2] = ly1.nE();
                    fD = ai1;
                    break;

                case 42: // '*'
                    int l = ly1.ex(ly1.nI());
                    int i1 = ly1.getPosition();
                    int j1;
                    for (j1 = 0; ly1.nN() > 0; j1++)
                    {
                        ly1.nE();
                    }

                    ly1.ez(i1);
                    int k1;
                    int ai[];
                    if (fD == null)
                    {
                        k1 = 0;
                    } else
                    {
                        k1 = fD.length;
                    }
                    ai = new int[j1 + k1];
                    if (k1 != 0)
                    {
                        System.arraycopy(fD, 0, ai, 0, k1);
                    }
                    for (; k1 < ai.length; k1++)
                    {
                        ai[k1] = ly1.nE();
                    }

                    fD = ai;
                    ly1.ey(l);
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

        public h n()
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


        public h()
        {
            n();
        }
    }

    public static final class i extends ma
    {

        private static volatile i fG[];
        public d.a fH;
        public d fI;
        public String name;

        public static i[] o()
        {
            if (fG == null)
            {
                synchronized (mc.ana)
                {
                    if (fG == null)
                    {
                        fG = new i[0];
                    }
                }
            }
            return fG;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void a(lz lz1)
            throws IOException
        {
            if (!name.equals(""))
            {
                lz1.b(1, name);
            }
            if (fH != null)
            {
                lz1.a(2, fH);
            }
            if (fI != null)
            {
                lz1.a(3, fI);
            }
            super.a(lz1);
        }

        public me b(ly ly1)
            throws IOException
        {
            return j(ly1);
        }

        protected int c()
        {
            int k = super.c();
            if (!name.equals(""))
            {
                k += lz.h(1, name);
            }
            if (fH != null)
            {
                k += lz.b(2, fH);
            }
            if (fI != null)
            {
                k += lz.b(3, fI);
            }
            return k;
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
            flag = obj instanceof i;
            flag1 = false;
            if (!flag) goto _L4; else goto _L3
_L3:
            i k = (i)obj;
            if (name != null) goto _L6; else goto _L5
_L5:
            String s;
            s = k.name;
            flag1 = false;
            if (s != null) goto _L4; else goto _L7
_L7:
            if (fH != null) goto _L9; else goto _L8
_L8:
            d.a a1;
            a1 = k.fH;
            flag1 = false;
            if (a1 != null) goto _L4; else goto _L10
_L10:
            if (fI != null) goto _L12; else goto _L11
_L11:
            d d1;
            d1 = k.fI;
            flag1 = false;
            if (d1 != null) goto _L4; else goto _L13
_L13:
            boolean flag2;
            if (amX != null && !amX.isEmpty())
            {
                break MISSING_BLOCK_LABEL_188;
            }
            if (k.amX == null)
            {
                break; /* Loop/switch isn't completed */
            }
            flag2 = k.amX.isEmpty();
            flag1 = false;
            if (!flag2) goto _L4; else goto _L14
_L14:
            return true;
_L6:
            if (!name.equals(k.name))
            {
                return false;
            }
              goto _L7
_L9:
            if (!fH.equals(k.fH))
            {
                return false;
            }
              goto _L10
_L12:
            if (!fI.equals(k.fI))
            {
                return false;
            }
              goto _L13
            return amX.equals(k.amX);
              goto _L7
        }

        public int hashCode()
        {
            int k;
            int l;
            int i1;
            int j1;
            int k1;
            int l1;
            List list;
            int i2;
            if (name == null)
            {
                k = 0;
            } else
            {
                k = name.hashCode();
            }
            l = 31 * (k + 527);
            if (fH == null)
            {
                i1 = 0;
            } else
            {
                i1 = fH.hashCode();
            }
            j1 = 31 * (i1 + l);
            if (fI == null)
            {
                k1 = 0;
            } else
            {
                k1 = fI.hashCode();
            }
            l1 = 31 * (k1 + j1);
            list = amX;
            i2 = 0;
            if (list != null)
            {
                boolean flag = amX.isEmpty();
                i2 = 0;
                if (!flag)
                {
                    i2 = amX.hashCode();
                }
            }
            return l1 + i2;
        }

        public i j(ly ly1)
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

                case 10: // '\n'
                    name = ly1.readString();
                    break;

                case 18: // '\022'
                    if (fH == null)
                    {
                        fH = new d.a();
                    }
                    ly1.a(fH);
                    break;

                case 26: // '\032'
                    if (fI == null)
                    {
                        fI = new d();
                    }
                    ly1.a(fI);
                    break;
                }
            } while (true);
        }

        public i p()
        {
            name = "";
            fH = null;
            fI = null;
            amX = null;
            anb = -1;
            return this;
        }

        public i()
        {
            p();
        }
    }

    public static final class j extends ma
    {

        public i fJ[];
        public f fK;
        public String fL;

        public static j b(byte abyte0[])
            throws md
        {
            return (j)me.a(new j(), abyte0);
        }

        public void a(lz lz1)
            throws IOException
        {
            if (fJ != null && fJ.length > 0)
            {
                for (int l = 0; l < fJ.length; l++)
                {
                    i i1 = fJ[l];
                    if (i1 != null)
                    {
                        lz1.a(1, i1);
                    }
                }

            }
            if (fK != null)
            {
                lz1.a(2, fK);
            }
            if (!fL.equals(""))
            {
                lz1.b(3, fL);
            }
            super.a(lz1);
        }

        public me b(ly ly1)
            throws IOException
        {
            return k(ly1);
        }

        protected int c()
        {
            int l = super.c();
            if (fJ != null && fJ.length > 0)
            {
                for (int i1 = 0; i1 < fJ.length; i1++)
                {
                    i j1 = fJ[i1];
                    if (j1 != null)
                    {
                        l += lz.b(1, j1);
                    }
                }

            }
            if (fK != null)
            {
                l += lz.b(2, fK);
            }
            if (!fL.equals(""))
            {
                l += lz.h(3, fL);
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
            flag = obj instanceof j;
            flag1 = false;
            if (!flag) goto _L4; else goto _L3
_L3:
            j j1;
            boolean flag2;
            j1 = (j)obj;
            flag2 = mc.equals(fJ, j1.fJ);
            flag1 = false;
            if (!flag2) goto _L4; else goto _L5
_L5:
            if (fK != null) goto _L7; else goto _L6
_L6:
            f f1;
            f1 = j1.fK;
            flag1 = false;
            if (f1 != null) goto _L4; else goto _L8
_L8:
            if (fL != null) goto _L10; else goto _L9
_L9:
            String s;
            s = j1.fL;
            flag1 = false;
            if (s != null) goto _L4; else goto _L11
_L11:
            boolean flag3;
            if (amX != null && !amX.isEmpty())
            {
                break MISSING_BLOCK_LABEL_171;
            }
            if (j1.amX == null)
            {
                break; /* Loop/switch isn't completed */
            }
            flag3 = j1.amX.isEmpty();
            flag1 = false;
            if (!flag3) goto _L4; else goto _L12
_L12:
            return true;
_L7:
            if (!fK.equals(j1.fK))
            {
                return false;
            }
              goto _L8
_L10:
            if (!fL.equals(j1.fL))
            {
                return false;
            }
              goto _L11
            return amX.equals(j1.amX);
              goto _L8
        }

        public int hashCode()
        {
            int l = 31 * (527 + mc.hashCode(fJ));
            int i1;
            int j1;
            int k1;
            int l1;
            List list;
            int i2;
            if (fK == null)
            {
                i1 = 0;
            } else
            {
                i1 = fK.hashCode();
            }
            j1 = 31 * (i1 + l);
            if (fL == null)
            {
                k1 = 0;
            } else
            {
                k1 = fL.hashCode();
            }
            l1 = 31 * (k1 + j1);
            list = amX;
            i2 = 0;
            if (list != null)
            {
                boolean flag = amX.isEmpty();
                i2 = 0;
                if (!flag)
                {
                    i2 = amX.hashCode();
                }
            }
            return l1 + i2;
        }

        public j k(ly ly1)
            throws IOException
        {
            do
            {
                int l = ly1.nB();
                switch (l)
                {
                default:
                    if (a(ly1, l))
                    {
                        continue;
                    }
                    // fall through

                case 0: // '\0'
                    return this;

                case 10: // '\n'
                    int i1 = mh.b(ly1, 10);
                    int j1;
                    i ai[];
                    if (fJ == null)
                    {
                        j1 = 0;
                    } else
                    {
                        j1 = fJ.length;
                    }
                    ai = new i[i1 + j1];
                    if (j1 != 0)
                    {
                        System.arraycopy(fJ, 0, ai, 0, j1);
                    }
                    for (; j1 < -1 + ai.length; j1++)
                    {
                        ai[j1] = new i();
                        ly1.a(ai[j1]);
                        ly1.nB();
                    }

                    ai[j1] = new i();
                    ly1.a(ai[j1]);
                    fJ = ai;
                    break;

                case 18: // '\022'
                    if (fK == null)
                    {
                        fK = new f();
                    }
                    ly1.a(fK);
                    break;

                case 26: // '\032'
                    fL = ly1.readString();
                    break;
                }
            } while (true);
        }

        public j q()
        {
            fJ = i.o();
            fK = null;
            fL = "";
            amX = null;
            anb = -1;
            return this;
        }

        public j()
        {
            q();
        }
    }

}
