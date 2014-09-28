// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.util;

import java.util.Arrays;

public final class CharTypes
{

    private static final byte HEX_BYTES[];
    private static final char HEX_CHARS[];
    static final int sHexValues[];
    static final int sInputCodes[];
    static final int sInputCodesComment[];
    static final int sInputCodesJsNames[];
    static final int sInputCodesUtf8[];
    static final int sInputCodesUtf8JsNames[];
    static final int sOutputEscapes128[];

    public CharTypes()
    {
    }

    public static void appendQuoted(StringBuilder stringbuilder, String s)
    {
        int ai[] = sOutputEscapes128;
        int i = ai.length;
        int j = 0;
        int k = s.length();
        while (j < k) 
        {
            char c = s.charAt(j);
            if (c >= i || ai[c] == 0)
            {
                stringbuilder.append(c);
            } else
            {
                stringbuilder.append('\\');
                int l = ai[c];
                if (l < 0)
                {
                    stringbuilder.append('u');
                    stringbuilder.append('0');
                    stringbuilder.append('0');
                    int i1 = -(l + 1);
                    stringbuilder.append(HEX_CHARS[i1 >> 4]);
                    stringbuilder.append(HEX_CHARS[i1 & 0xf]);
                } else
                {
                    stringbuilder.append((char)l);
                }
            }
            j++;
        }
    }

    public static int charToHex(int i)
    {
        if (i > 127)
        {
            return -1;
        } else
        {
            return sHexValues[i];
        }
    }

    public static byte[] copyHexBytes()
    {
        return (byte[])(byte[])HEX_BYTES.clone();
    }

    public static char[] copyHexChars()
    {
        return (char[])(char[])HEX_CHARS.clone();
    }

    public static final int[] get7BitOutputEscapes()
    {
        return sOutputEscapes128;
    }

    public static final int[] getInputCodeComment()
    {
        return sInputCodesComment;
    }

    public static final int[] getInputCodeLatin1()
    {
        return sInputCodes;
    }

    public static final int[] getInputCodeLatin1JsNames()
    {
        return sInputCodesJsNames;
    }

    public static final int[] getInputCodeUtf8()
    {
        return sInputCodesUtf8;
    }

    public static final int[] getInputCodeUtf8JsNames()
    {
        return sInputCodesUtf8JsNames;
    }

    static 
    {
        HEX_CHARS = "0123456789ABCDEF".toCharArray();
        int i = HEX_CHARS.length;
        HEX_BYTES = new byte[i];
        for (int j = 0; j < i; j++)
        {
            HEX_BYTES[j] = (byte)HEX_CHARS[j];
        }

        int ai[] = new int[256];
        for (int k = 0; k < 32; k++)
        {
            ai[k] = -1;
        }

        ai[34] = 1;
        ai[92] = 1;
        sInputCodes = ai;
        int ai1[] = new int[sInputCodes.length];
        System.arraycopy(sInputCodes, 0, ai1, 0, sInputCodes.length);
        int l = 128;
        while (l < 256) 
        {
            byte byte0;
            if ((l & 0xe0) == 192)
            {
                byte0 = 2;
            } else
            if ((l & 0xf0) == 224)
            {
                byte0 = 3;
            } else
            if ((l & 0xf8) == 240)
            {
                byte0 = 4;
            } else
            {
                byte0 = -1;
            }
            ai1[l] = byte0;
            l++;
        }
        sInputCodesUtf8 = ai1;
        int ai2[] = new int[256];
        Arrays.fill(ai2, -1);
        for (int i1 = 33; i1 < 256; i1++)
        {
            if (Character.isJavaIdentifierPart((char)i1))
            {
                ai2[i1] = 0;
            }
        }

        ai2[64] = 0;
        ai2[35] = 0;
        ai2[42] = 0;
        ai2[45] = 0;
        ai2[43] = 0;
        sInputCodesJsNames = ai2;
        int ai3[] = new int[256];
        System.arraycopy(sInputCodesJsNames, 0, ai3, 0, sInputCodesJsNames.length);
        Arrays.fill(ai3, 128, 128, 0);
        sInputCodesUtf8JsNames = ai3;
        sInputCodesComment = new int[256];
        System.arraycopy(sInputCodesUtf8, 128, sInputCodesComment, 128, 128);
        Arrays.fill(sInputCodesComment, 0, 32, -1);
        sInputCodesComment[9] = 0;
        sInputCodesComment[10] = 10;
        sInputCodesComment[13] = 13;
        sInputCodesComment[42] = 42;
        int ai4[] = new int[128];
        for (int j1 = 0; j1 < 32; j1++)
        {
            ai4[j1] = -1;
        }

        ai4[34] = 34;
        ai4[92] = 92;
        ai4[8] = 98;
        ai4[9] = 116;
        ai4[12] = 102;
        ai4[10] = 110;
        ai4[13] = 114;
        sOutputEscapes128 = ai4;
        sHexValues = new int[128];
        Arrays.fill(sHexValues, -1);
        for (int k1 = 0; k1 < 10; k1++)
        {
            sHexValues[k1 + 48] = k1;
        }

        for (int l1 = 0; l1 < 6; l1++)
        {
            sHexValues[l1 + 97] = l1 + 10;
            sHexValues[l1 + 65] = l1 + 10;
        }

    }
}
