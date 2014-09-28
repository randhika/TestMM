// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;


class j
{

    public static byte[] bE(String s)
    {
        int i = s.length();
        if (i % 2 != 0)
        {
            throw new IllegalArgumentException("purported base16 string has odd number of characters");
        }
        byte abyte0[] = new byte[i / 2];
        for (int k = 0; k < i; k += 2)
        {
            int l = Character.digit(s.charAt(k), 16);
            int i1 = Character.digit(s.charAt(k + 1), 16);
            if (l == -1 || i1 == -1)
            {
                throw new IllegalArgumentException("purported base16 string has illegal char");
            }
            abyte0[k / 2] = (byte)(i1 + (l << 4));
        }

        return abyte0;
    }

    public static String d(byte abyte0[])
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = abyte0.length;
        for (int k = 0; k < i; k++)
        {
            byte byte0 = abyte0[k];
            if ((byte0 & 0xf0) == 0)
            {
                stringbuilder.append("0");
            }
            stringbuilder.append(Integer.toHexString(byte0 & 0xff));
        }

        return stringbuilder.toString().toUpperCase();
    }
}
