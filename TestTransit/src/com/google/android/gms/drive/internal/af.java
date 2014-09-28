// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import com.google.android.gms.internal.ly;
import com.google.android.gms.internal.lz;
import com.google.android.gms.internal.ma;
import com.google.android.gms.internal.md;
import com.google.android.gms.internal.me;
import java.io.IOException;
import java.util.List;

public final class af extends ma
{

    public String Jt;
    public long Ju;
    public long Jv;
    public int versionCode;

    public af()
    {
        gs();
    }

    public static af g(byte abyte0[])
        throws md
    {
        return (af)me.a(new af(), abyte0);
    }

    public void a(lz lz1)
        throws IOException
    {
        lz1.p(1, versionCode);
        lz1.b(2, Jt);
        lz1.c(3, Ju);
        lz1.c(4, Jv);
        super.a(lz1);
    }

    public me b(ly ly1)
        throws IOException
    {
        return m(ly1);
    }

    protected int c()
    {
        return super.c() + lz.r(1, versionCode) + lz.h(2, Jt) + lz.e(3, Ju) + lz.e(4, Jv);
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
        flag = obj instanceof af;
        flag1 = false;
        if (!flag) goto _L4; else goto _L3
_L3:
        af af1;
        int i;
        int j;
        af1 = (af)obj;
        i = versionCode;
        j = af1.versionCode;
        flag1 = false;
        if (i != j) goto _L4; else goto _L5
_L5:
        String s;
        if (Jt != null)
        {
            break MISSING_BLOCK_LABEL_155;
        }
        s = af1.Jt;
        flag1 = false;
        if (s != null) goto _L4; else goto _L6
_L6:
        int k;
        k = Ju != af1.Ju;
        flag1 = false;
        if (k != 0) goto _L4; else goto _L7
_L7:
        int l;
        l = Jv != af1.Jv;
        flag1 = false;
        if (l != 0) goto _L4; else goto _L8
_L8:
        boolean flag2;
        if (amX != null && !amX.isEmpty())
        {
            break MISSING_BLOCK_LABEL_172;
        }
        if (af1.amX == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag2 = af1.amX.isEmpty();
        flag1 = false;
        if (!flag2) goto _L4; else goto _L9
_L9:
        return true;
        if (!Jt.equals(af1.Jt))
        {
            return false;
        }
          goto _L6
        return amX.equals(af1.amX);
    }

    public af gs()
    {
        versionCode = 1;
        Jt = "";
        Ju = -1L;
        Jv = -1L;
        amX = null;
        anb = -1;
        return this;
    }

    public int hashCode()
    {
        int i = 31 * (527 + versionCode);
        int j;
        int k;
        List list;
        int l;
        if (Jt == null)
        {
            j = 0;
        } else
        {
            j = Jt.hashCode();
        }
        k = 31 * (31 * (31 * (j + i) + (int)(Ju ^ Ju >>> 32)) + (int)(Jv ^ Jv >>> 32));
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

    public af m(ly ly1)
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
                versionCode = ly1.nE();
                break;

            case 18: // '\022'
                Jt = ly1.readString();
                break;

            case 24: // '\030'
                Ju = ly1.nH();
                break;

            case 32: // ' '
                Jv = ly1.nH();
                break;
            }
        } while (true);
    }
}
