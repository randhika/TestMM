// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, md, me, lz, 
//            mc, ly, mh, c

public static final class q extends ma
{

    public anb fJ[];
    public anb fK;
    public String fL;

    public static q b(byte abyte0[])
        throws md
    {
        return (q)me.a(new <init>(), abyte0);
    }

    public void a(lz lz1)
        throws IOException
    {
        if (fJ != null && fJ.length > 0)
        {
            for (int i = 0; i < fJ.length; i++)
            {
                <init> <init>1 = fJ[i];
                if (<init>1 != null)
                {
                    lz1.a(1, <init>1);
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
        int i = super.c();
        if (fJ != null && fJ.length > 0)
        {
            for (int l = 0; l < fJ.length; l++)
            {
                k k1 = fJ[l];
                if (k1 != null)
                {
                    i += lz.b(1, k1);
                }
            }

        }
        if (fK != null)
        {
            i += lz.b(2, fK);
        }
        if (!fL.equals(""))
        {
            i += lz.h(3, fL);
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
        flag = obj instanceof fL;
        flag1 = false;
        if (!flag) goto _L4; else goto _L3
_L3:
        fL fl;
        boolean flag2;
        fl = (fL)obj;
        flag2 = mc.equals(fJ, fl.fJ);
        flag1 = false;
        if (!flag2) goto _L4; else goto _L5
_L5:
        if (fK != null) goto _L7; else goto _L6
_L6:
        fL fl1;
        fl1 = fl.fK;
        flag1 = false;
        if (fl1 != null) goto _L4; else goto _L8
_L8:
        if (fL != null) goto _L10; else goto _L9
_L9:
        String s;
        s = fl.fL;
        flag1 = false;
        if (s != null) goto _L4; else goto _L11
_L11:
        boolean flag3;
        if (amX != null && !amX.isEmpty())
        {
            break MISSING_BLOCK_LABEL_171;
        }
        if (fl.amX == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag3 = fl.amX.isEmpty();
        flag1 = false;
        if (!flag3) goto _L4; else goto _L12
_L12:
        return true;
_L7:
        if (!fK.equals(fl.fK))
        {
            return false;
        }
          goto _L8
_L10:
        if (!fL.equals(fl.fL))
        {
            return false;
        }
          goto _L11
        return amX.equals(fl.amX);
          goto _L8
    }

    public int hashCode()
    {
        int i = 31 * (527 + mc.hashCode(fJ));
        int l;
        int i1;
        int j1;
        int k1;
        List list;
        int l1;
        if (fK == null)
        {
            l = 0;
        } else
        {
            l = fK.hashCode();
        }
        i1 = 31 * (l + i);
        if (fL == null)
        {
            j1 = 0;
        } else
        {
            j1 = fL.hashCode();
        }
        k1 = 31 * (j1 + i1);
        list = amX;
        l1 = 0;
        if (list != null)
        {
            boolean flag = amX.isEmpty();
            l1 = 0;
            if (!flag)
            {
                l1 = amX.hashCode();
            }
        }
        return k1 + l1;
    }

    public amX k(ly ly1)
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
                int l = mh.b(ly1, 10);
                int i1;
                amX aamx[];
                if (fJ == null)
                {
                    i1 = 0;
                } else
                {
                    i1 = fJ.length;
                }
                aamx = new fJ[l + i1];
                if (i1 != 0)
                {
                    System.arraycopy(fJ, 0, aamx, 0, i1);
                }
                for (; i1 < -1 + aamx.length; i1++)
                {
                    aamx[i1] = new <init>();
                    ly1.a(aamx[i1]);
                    ly1.nB();
                }

                aamx[i1] = new <init>();
                ly1.a(aamx[i1]);
                fJ = aamx;
                break;

            case 18: // '\022'
                if (fK == null)
                {
                    fK = new <init>();
                }
                ly1.a(fK);
                break;

            case 26: // '\032'
                fL = ly1.readString();
                break;
            }
        } while (true);
    }

    public eadString q()
    {
        fJ = o();
        fK = null;
        fL = "";
        amX = null;
        anb = -1;
        return this;
    }

    public ()
    {
        q();
    }
}
