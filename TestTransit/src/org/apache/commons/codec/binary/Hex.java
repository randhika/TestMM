// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.apache.commons.codec.binary;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;

public class Hex
    implements BinaryEncoder, BinaryDecoder
{

    private static final char DIGITS[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'a', 'b', 'c', 'd', 'e', 'f'
    };

    public Hex()
    {
    }

    public static byte[] decodeHex(char ac[])
        throws DecoderException
    {
        int i = ac.length;
        if ((i & 1) != 0)
        {
            throw new DecoderException("Odd number of characters.");
        }
        byte abyte0[] = new byte[i >> 1];
        int j = 0;
        for (int k = 0; k < i;)
        {
            int l = toDigit(ac[k], k) << 4;
            int i1 = k + 1;
            int j1 = l | toDigit(ac[i1], i1);
            k = i1 + 1;
            abyte0[j] = (byte)(j1 & 0xff);
            j++;
        }

        return abyte0;
    }

    public static char[] encodeHex(byte abyte0[])
    {
        int i = abyte0.length;
        char ac[] = new char[i << 1];
        int j = 0;
        int k = 0;
        for (; j < i; j++)
        {
            int l = k + 1;
            ac[k] = DIGITS[(0xf0 & abyte0[j]) >>> 4];
            k = l + 1;
            ac[l] = DIGITS[0xf & abyte0[j]];
        }

        return ac;
    }

    protected static int toDigit(char c, int i)
        throws DecoderException
    {
        int j = Character.digit(c, 16);
        if (j == -1)
        {
            throw new DecoderException("Illegal hexadecimal charcter " + c + " at index " + i);
        } else
        {
            return j;
        }
    }

    public Object decode(Object obj)
        throws DecoderException
    {
        if (!(obj instanceof String)) goto _L2; else goto _L1
_L1:
        char ac1[] = ((String)obj).toCharArray();
_L3:
        return decodeHex(ac1);
_L2:
        char ac[] = (char[])obj;
        ac1 = ac;
          goto _L3
        ClassCastException classcastexception;
        classcastexception;
        throw new DecoderException(classcastexception.getMessage());
    }

    public byte[] decode(byte abyte0[])
        throws DecoderException
    {
        return decodeHex((new String(abyte0)).toCharArray());
    }

    public Object encode(Object obj)
        throws EncoderException
    {
        if (!(obj instanceof String)) goto _L2; else goto _L1
_L1:
        byte abyte1[] = ((String)obj).getBytes();
_L3:
        return encodeHex(abyte1);
_L2:
        byte abyte0[] = (byte[])obj;
        abyte1 = abyte0;
          goto _L3
        ClassCastException classcastexception;
        classcastexception;
        throw new EncoderException(classcastexception.getMessage());
    }

    public byte[] encode(byte abyte0[])
    {
        return (new String(encodeHex(abyte0))).getBytes();
    }

}
