// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, md, me, lz, 
//            ly

public interface le
{
    public static final class a extends ma
    {

        public long aiG;
        public c.j aiH;
        public c.f fK;

        public static a l(byte abyte0[])
            throws md
        {
            return (a)me.a(new a(), abyte0);
        }

        public void a(lz lz1)
            throws IOException
        {
            lz1.b(1, aiG);
            if (fK != null)
            {
                lz1.a(2, fK);
            }
            if (aiH != null)
            {
                lz1.a(3, aiH);
            }
            super.a(lz1);
        }

        public me b(ly ly1)
            throws IOException
        {
            return p(ly1);
        }

        protected int c()
        {
            int i = super.c() + lz.d(1, aiG);
            if (fK != null)
            {
                i += lz.b(2, fK);
            }
            if (aiH != null)
            {
                i += lz.b(3, aiH);
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
            a a1;
            int i;
            a1 = (a)obj;
            i = aiG != a1.aiG;
            flag1 = false;
            if (i != 0) goto _L4; else goto _L5
_L5:
            if (fK != null) goto _L7; else goto _L6
_L6:
            c.f f;
            f = a1.fK;
            flag1 = false;
            if (f != null) goto _L4; else goto _L8
_L8:
            if (aiH != null) goto _L10; else goto _L9
_L9:
            c.j j;
            j = a1.aiH;
            flag1 = false;
            if (j != null) goto _L4; else goto _L11
_L11:
            boolean flag2;
            if (amX != null && !amX.isEmpty())
            {
                break MISSING_BLOCK_LABEL_169;
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
_L7:
            if (!fK.equals(a1.fK))
            {
                return false;
            }
              goto _L8
_L10:
            if (!aiH.equals(a1.aiH))
            {
                return false;
            }
              goto _L11
            return amX.equals(a1.amX);
              goto _L8
        }

        public int hashCode()
        {
            int i = 31 * (527 + (int)(aiG ^ aiG >>> 32));
            int j;
            int k;
            int i1;
            int j1;
            List list;
            int k1;
            if (fK == null)
            {
                j = 0;
            } else
            {
                j = fK.hashCode();
            }
            k = 31 * (j + i);
            if (aiH == null)
            {
                i1 = 0;
            } else
            {
                i1 = aiH.hashCode();
            }
            j1 = 31 * (i1 + k);
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

        public a nf()
        {
            aiG = 0L;
            fK = null;
            aiH = null;
            amX = null;
            anb = -1;
            return this;
        }

        public a p(ly ly1)
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
                    aiG = ly1.nD();
                    break;

                case 18: // '\022'
                    if (fK == null)
                    {
                        fK = new c.f();
                    }
                    ly1.a(fK);
                    break;

                case 26: // '\032'
                    if (aiH == null)
                    {
                        aiH = new c.j();
                    }
                    ly1.a(aiH);
                    break;
                }
            } while (true);
        }

        public a()
        {
            nf();
        }
    }

}
