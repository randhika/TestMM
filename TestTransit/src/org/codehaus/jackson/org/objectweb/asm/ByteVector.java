// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.org.objectweb.asm;


public class ByteVector
{

    byte a[];
    int b;

    public ByteVector()
    {
        a = new byte[64];
    }

    public ByteVector(int i)
    {
        a = new byte[i];
    }

    private void a(int i)
    {
        int j = 2 * a.length;
        int k = i + b;
        byte abyte0[];
        if (j <= k)
        {
            j = k;
        }
        abyte0 = new byte[j];
        System.arraycopy(a, 0, abyte0, 0, b);
        a = abyte0;
    }

    ByteVector a(int i, int j)
    {
        int k = b;
        if (k + 2 > a.length)
        {
            a(2);
        }
        byte abyte0[] = a;
        int l = k + 1;
        abyte0[k] = (byte)i;
        int i1 = l + 1;
        abyte0[l] = (byte)j;
        b = i1;
        return this;
    }

    ByteVector b(int i, int j)
    {
        int k = b;
        if (k + 3 > a.length)
        {
            a(3);
        }
        byte abyte0[] = a;
        int l = k + 1;
        abyte0[k] = (byte)i;
        int i1 = l + 1;
        abyte0[l] = (byte)(j >>> 8);
        int j1 = i1 + 1;
        abyte0[i1] = (byte)j;
        b = j1;
        return this;
    }

    public ByteVector putByte(int i)
    {
        int j = b;
        if (j + 1 > a.length)
        {
            a(1);
        }
        byte abyte0[] = a;
        int k = j + 1;
        abyte0[j] = (byte)i;
        b = k;
        return this;
    }

    public ByteVector putByteArray(byte abyte0[], int i, int j)
    {
        if (j + b > a.length)
        {
            a(j);
        }
        if (abyte0 != null)
        {
            System.arraycopy(abyte0, i, a, b, j);
        }
        b = j + b;
        return this;
    }

    public ByteVector putInt(int i)
    {
        int j = b;
        if (j + 4 > a.length)
        {
            a(4);
        }
        byte abyte0[] = a;
        int k = j + 1;
        abyte0[j] = (byte)(i >>> 24);
        int l = k + 1;
        abyte0[k] = (byte)(i >>> 16);
        int i1 = l + 1;
        abyte0[l] = (byte)(i >>> 8);
        int j1 = i1 + 1;
        abyte0[i1] = (byte)i;
        b = j1;
        return this;
    }

    public ByteVector putLong(long l)
    {
        int i = b;
        if (i + 8 > a.length)
        {
            a(8);
        }
        byte abyte0[] = a;
        int j = (int)(l >>> 32);
        int k = i + 1;
        abyte0[i] = (byte)(j >>> 24);
        int i1 = k + 1;
        abyte0[k] = (byte)(j >>> 16);
        int j1 = i1 + 1;
        abyte0[i1] = (byte)(j >>> 8);
        int k1 = j1 + 1;
        abyte0[j1] = (byte)j;
        int l1 = (int)l;
        int i2 = k1 + 1;
        abyte0[k1] = (byte)(l1 >>> 24);
        int j2 = i2 + 1;
        abyte0[i2] = (byte)(l1 >>> 16);
        int k2 = j2 + 1;
        abyte0[j2] = (byte)(l1 >>> 8);
        int l2 = k2 + 1;
        abyte0[k2] = (byte)l1;
        b = l2;
        return this;
    }

    public ByteVector putShort(int i)
    {
        int j = b;
        if (j + 2 > a.length)
        {
            a(2);
        }
        byte abyte0[] = a;
        int k = j + 1;
        abyte0[j] = (byte)(i >>> 8);
        int l = k + 1;
        abyte0[k] = (byte)i;
        b = l;
        return this;
    }

    public ByteVector putUTF8(String s)
    {
        int k1 = i1;
        int l1 = i1;
        while (k1 < i) 
        {
            char c2 = s.charAt(k1);
            if (c2 >= '\001' && c2 <= '\177')
            {
                l1++;
            } else
            if (c2 > '\u07FF')
            {
                l1 += 3;
            } else
            {
                l1 += 2;
            }
            k1++;
        }
        abyte0[b] = (byte)(l1 >>> 8);
        abyte0[1 + b] = (byte)l1;
        byte abyte1[];
        if (l1 + (2 + b) > abyte0.length)
        {
            b = l;
            a(l1 + 2);
            abyte1 = a;
        } else
        {
            abyte1 = abyte0;
        }
        while (i1 < i) 
        {
            char c1 = s.charAt(i1);
            int j2;
            if (c1 >= '\001' && c1 <= '\177')
            {
                j2 = l + 1;
                abyte1[l] = (byte)c1;
            } else
            if (c1 > '\u07FF')
            {
                int k2 = l + 1;
                abyte1[l] = (byte)(0xe0 | 0xf & c1 >> 12);
                int l2 = k2 + 1;
                abyte1[k2] = (byte)(0x80 | 0x3f & c1 >> 6);
                j2 = l2 + 1;
                abyte1[l2] = (byte)(0x80 | c1 & 0x3f);
            } else
            {
                int i2 = l + 1;
                abyte1[l] = (byte)(0xc0 | 0x1f & c1 >> 6);
                j2 = i2 + 1;
                abyte1[i2] = (byte)(0x80 | c1 & 0x3f);
            }
            i1++;
            l = j2;
        }
        j1 = l;
_L2:
        b = j1;
        return this;
        int i = s.length();
        int j = b;
        if (i + (j + 2) > a.length)
        {
            a(i + 2);
        }
        byte abyte0[] = a;
        int k = j + 1;
        abyte0[j] = (byte)(i >>> 8);
        int l = k + 1;
        abyte0[k] = (byte)i;
        int i1;
        int j1;
        for (i1 = 0; i1 < i;)
        {
label0:
            {
                char c = s.charAt(i1);
                if (c < '\001' || c > '\177')
                {
                    break label0;
                }
                int i3 = l + 1;
                abyte0[l] = (byte)c;
                i1++;
                l = i3;
            }
        }

        j1 = l;
        if (true) goto _L2; else goto _L1
_L1:
    }
}
