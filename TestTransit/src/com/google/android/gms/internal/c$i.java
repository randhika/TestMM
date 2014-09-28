// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, mc, lz, ly, 
//            c, me

public static final class p extends ma
{

    private static volatile anb fG[];
    public anb fH;
    public anb fI;
    public String name;

    public static p[] o()
    {
        if (fG == null)
        {
            synchronized (mc.ana)
            {
                if (fG == null)
                {
                    fG = new fG[0];
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
        flag = obj instanceof fI;
        flag1 = false;
        if (!flag) goto _L4; else goto _L3
_L3:
        fI fi = (fI)obj;
        if (name != null) goto _L6; else goto _L5
_L5:
        String s;
        s = fi.name;
        flag1 = false;
        if (s != null) goto _L4; else goto _L7
_L7:
        if (fH != null) goto _L9; else goto _L8
_L8:
        fI fi2;
        fi2 = fi.fH;
        flag1 = false;
        if (fi2 != null) goto _L4; else goto _L10
_L10:
        if (fI != null) goto _L12; else goto _L11
_L11:
        fI fi1;
        fi1 = fi.fI;
        flag1 = false;
        if (fi1 != null) goto _L4; else goto _L13
_L13:
        boolean flag2;
        if (amX != null && !amX.isEmpty())
        {
            break MISSING_BLOCK_LABEL_188;
        }
        if (fi.amX == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag2 = fi.amX.isEmpty();
        flag1 = false;
        if (!flag2) goto _L4; else goto _L14
_L14:
        return true;
_L6:
        if (!name.equals(fi.name))
        {
            return false;
        }
          goto _L7
_L9:
        if (!fH.equals(fi.fH))
        {
            return false;
        }
          goto _L10
_L12:
        if (!fI.equals(fi.fI))
        {
            return false;
        }
          goto _L13
        return amX.equals(fi.amX);
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

    public amX j(ly ly1)
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
                    fH = new <init>();
                }
                ly1.a(fH);
                break;

            case 26: // '\032'
                if (fI == null)
                {
                    fI = new <init>();
                }
                ly1.a(fI);
                break;
            }
        } while (true);
    }

    public fI p()
    {
        name = "";
        fH = null;
        fI = null;
        amX = null;
        anb = -1;
        return this;
    }

    public ()
    {
        p();
    }
}
