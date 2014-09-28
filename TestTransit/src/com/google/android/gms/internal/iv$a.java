// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, lz, mc, ly, 
//            mh, iv, me

public static final class iQ extends ma
{
    public static final class a extends ma
    {

        private static volatile a Uz[];
        public String UA;
        public String UB;
        public int viewId;

        public static a[] iR()
        {
            if (Uz == null)
            {
                synchronized (mc.ana)
                {
                    if (Uz == null)
                    {
                        Uz = new a[0];
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
            flag = obj instanceof a;
            flag1 = false;
            if (!flag) goto _L4; else goto _L3
_L3:
            a a1 = (a)obj;
            if (UA != null) goto _L6; else goto _L5
_L5:
            String s1;
            s1 = a1.UA;
            flag1 = false;
            if (s1 != null) goto _L4; else goto _L7
_L7:
            if (UB != null) goto _L9; else goto _L8
_L8:
            String s;
            s = a1.UB;
            flag1 = false;
            if (s != null) goto _L4; else goto _L10
_L10:
            int i;
            int j;
            i = viewId;
            j = a1.viewId;
            flag1 = false;
            if (i != j) goto _L4; else goto _L11
_L11:
            boolean flag2;
            if (amX != null && !amX.isEmpty())
            {
                break MISSING_BLOCK_LABEL_172;
            }
            if (a1.amX == null)
            {
                break; /* Loop/switch isn't completed */
            }
            flag2 = a1.amX.isEmpty();
            flag1 = false;
            if (!flag2) goto _L4; else goto _L12
_L12:
            return true;
_L6:
            if (!UA.equals(a1.UA))
            {
                return false;
            }
              goto _L7
_L9:
            if (!UB.equals(a1.UB))
            {
                return false;
            }
              goto _L10
            return amX.equals(a1.amX);
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

        public a iS()
        {
            UA = "";
            UB = "";
            viewId = 0;
            amX = null;
            anb = -1;
            return this;
        }

        public a o(ly ly1)
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

        public a()
        {
            iS();
        }
    }


    public a Uy[];

    public void a(lz lz1)
        throws IOException
    {
        if (Uy != null && Uy.length > 0)
        {
            for (int i = 0; i < Uy.length; i++)
            {
                a a1 = Uy[i];
                if (a1 != null)
                {
                    lz1.a(1, a1);
                }
            }

        }
        super.a(lz1);
    }

    public me b(ly ly1)
        throws IOException
    {
        return n(ly1);
    }

    protected int c()
    {
        int i = super.c();
        if (Uy != null && Uy.length > 0)
        {
            for (int j = 0; j < Uy.length; j++)
            {
                a a1 = Uy[j];
                if (a1 != null)
                {
                    i += lz.b(1, a1);
                }
            }

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
        flag = obj instanceof Uy;
        flag1 = false;
        if (!flag) goto _L4; else goto _L3
_L3:
        Uy uy;
        boolean flag2;
        uy = (Uy)obj;
        flag2 = mc.equals(Uy, uy.Uy);
        flag1 = false;
        if (!flag2) goto _L4; else goto _L5
_L5:
        boolean flag3;
        if (amX != null && !amX.isEmpty())
        {
            break MISSING_BLOCK_LABEL_95;
        }
        if (uy.amX == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag3 = uy.amX.isEmpty();
        flag1 = false;
        if (!flag3) goto _L4; else goto _L6
_L6:
        return true;
        return amX.equals(uy.amX);
    }

    public int hashCode()
    {
        int i = 31 * (527 + mc.hashCode(Uy));
        int j;
        if (amX == null || amX.isEmpty())
        {
            j = 0;
        } else
        {
            j = amX.hashCode();
        }
        return j + i;
    }

    public amX iQ()
    {
        Uy = a.iR();
        amX = null;
        anb = -1;
        return this;
    }

    public anb n(ly ly1)
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
                int j = mh.b(ly1, 10);
                int k;
                a aa[];
                if (Uy == null)
                {
                    k = 0;
                } else
                {
                    k = Uy.length;
                }
                aa = new a[j + k];
                if (k != 0)
                {
                    System.arraycopy(Uy, 0, aa, 0, k);
                }
                for (; k < -1 + aa.length; k++)
                {
                    aa[k] = new a();
                    ly1.a(aa[k]);
                    ly1.nB();
                }

                aa[k] = new a();
                ly1.a(aa[k]);
                Uy = aa;
                break;
            }
        } while (true);
    }

    public a.iS()
    {
        iQ();
    }
}
