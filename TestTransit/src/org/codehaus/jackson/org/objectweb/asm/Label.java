// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm;


// Referenced classes of package org.codehaus.jackson.org.objectweb.asm:
//            Frame, ByteVector, Edge, MethodWriter

public class Label
{

    int a;
    int b;
    int c;
    private int d;
    private int e[];
    int f;
    int g;
    Frame h;
    Label i;
    public Object info;
    Edge j;
    Label k;

    public Label()
    {
    }

    private void a(int l, int i1)
    {
        if (e == null)
        {
            e = new int[6];
        }
        if (d >= e.length)
        {
            int ai2[] = new int[6 + e.length];
            System.arraycopy(e, 0, ai2, 0, e.length);
            e = ai2;
        }
        int ai[] = e;
        int j1 = d;
        d = j1 + 1;
        ai[j1] = l;
        int ai1[] = e;
        int k1 = d;
        d = k1 + 1;
        ai1[k1] = i1;
    }

    Label a()
    {
        if (h == null)
        {
            return this;
        } else
        {
            return h.b;
        }
    }

    void a(long l, int i1)
    {
        if ((0x400 & a) == 0)
        {
            a = 0x400 | a;
            e = new int[1 + (i1 - 1) / 32];
        }
        int ai[] = e;
        int j1 = (int)(l >>> 32);
        ai[j1] = ai[j1] | (int)l;
    }

    void a(MethodWriter methodwriter, ByteVector bytevector, int l, boolean flag)
    {
        if ((2 & a) == 0)
        {
            if (flag)
            {
                a(-1 - l, bytevector.b);
                bytevector.putInt(-1);
                return;
            } else
            {
                a(l, bytevector.b);
                bytevector.putShort(-1);
                return;
            }
        }
        if (flag)
        {
            bytevector.putInt(c - l);
            return;
        } else
        {
            bytevector.putShort(c - l);
            return;
        }
    }

    boolean a(long l)
    {
        int i1 = 0x400 & a;
        boolean flag = false;
        if (i1 != 0)
        {
            int j1 = e[(int)(l >>> 32)] & (int)l;
            flag = false;
            if (j1 != 0)
            {
                flag = true;
            }
        }
        return flag;
    }

    boolean a(Label label)
    {
        if ((0x400 & a) != 0 && (0x400 & label.a) != 0)
        {
            int l = 0;
            while (l < e.length) 
            {
                if ((e[l] & label.e[l]) != 0)
                {
                    return true;
                }
                l++;
            }
        }
        return false;
    }

    boolean a(MethodWriter methodwriter, int l, byte abyte0[])
    {
        int i1 = 0;
        a = 2 | a;
        c = l;
        boolean flag = false;
        while (i1 < d) 
        {
            int ai[] = e;
            int j1 = i1 + 1;
            int k1 = ai[i1];
            int ai1[] = e;
            i1 = j1 + 1;
            int l1 = ai1[j1];
            if (k1 >= 0)
            {
                int i3 = l - k1;
                if (i3 < -32768 || i3 > 32767)
                {
                    int j3 = 0xff & abyte0[l1 - 1];
                    int k3;
                    if (j3 <= 168)
                    {
                        abyte0[l1 - 1] = (byte)(j3 + 49);
                    } else
                    {
                        abyte0[l1 - 1] = (byte)(j3 + 20);
                    }
                    flag = true;
                }
                k3 = l1 + 1;
                abyte0[l1] = (byte)(i3 >>> 8);
                abyte0[k3] = (byte)i3;
            } else
            {
                int i2 = 1 + (k1 + l);
                int j2 = l1 + 1;
                abyte0[l1] = (byte)(i2 >>> 24);
                int k2 = j2 + 1;
                abyte0[j2] = (byte)(i2 >>> 16);
                int l2 = k2 + 1;
                abyte0[k2] = (byte)(i2 >>> 8);
                abyte0[l2] = (byte)i2;
            }
        }
        return flag;
    }

    void b(Label label, long l, int i1)
    {
_L6:
        Label label1;
        if (this == null)
        {
            break; /* Loop/switch isn't completed */
        }
        label1 = k;
        k = null;
        if (label == null) goto _L2; else goto _L1
_L1:
        if ((0x800 & a) != 0)
        {
            this = label1;
            continue; /* Loop/switch isn't completed */
        }
        a = 0x800 | a;
        if ((0x100 & a) != 0 && !a(label))
        {
            Edge edge2 = new Edge();
            edge2.a = f;
            edge2.b = label.j.b;
            edge2.c = j;
            j = edge2;
        }
_L4:
        Label label2;
        Edge edge = j;
        label2 = label1;
        for (Edge edge1 = edge; edge1 != null; edge1 = edge1.c)
        {
            if (((0x80 & a) == 0 || edge1 != j.c) && edge1.b.k == null)
            {
                edge1.b.k = label2;
                label2 = edge1.b;
            }
        }

        break; /* Loop/switch isn't completed */
_L2:
        if (a(l))
        {
            this = label1;
            continue; /* Loop/switch isn't completed */
        }
        a(l, i1);
        if (true) goto _L4; else goto _L3
_L3:
        this = label2;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public int getOffset()
    {
        if ((2 & a) == 0)
        {
            throw new IllegalStateException("Label offset position has not been resolved yet");
        } else
        {
            return c;
        }
    }

    public String toString()
    {
        return "L" + System.identityHashCode(this);
    }
}
