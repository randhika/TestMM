// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.apache.commons.codec.binary;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;

public class Base64
    implements BinaryEncoder, BinaryDecoder
{

    static final int BASELENGTH = 255;
    static final byte CHUNK_SEPARATOR[] = "\r\n".getBytes();
    static final int CHUNK_SIZE = 76;
    static final int EIGHTBIT = 8;
    static final int FOURBYTE = 4;
    static final int LOOKUPLENGTH = 64;
    static final byte PAD = 61;
    static final int SIGN = -128;
    static final int SIXTEENBIT = 16;
    static final int TWENTYFOURBITGROUP = 24;
    private static byte base64Alphabet[];
    private static byte lookUpBase64Alphabet[];

    public Base64()
    {
    }

    public static byte[] decodeBase64(byte abyte0[])
    {
        byte abyte1[] = discardNonBase64(abyte0);
        byte abyte2[];
        if (abyte1.length == 0)
        {
            abyte2 = new byte[0];
        } else
        {
            int i = abyte1.length / 4;
            int j = 0;
            int k;
            for (k = abyte1.length; abyte1[k - 1] == 61;)
            {
                if (--k == 0)
                {
                    return new byte[0];
                }
            }

            abyte2 = new byte[k - i];
            int l = 0;
            while (l < i) 
            {
                int i1 = l * 4;
                byte byte0 = abyte1[i1 + 2];
                byte byte1 = abyte1[i1 + 3];
                byte byte2 = base64Alphabet[abyte1[i1]];
                byte byte3 = base64Alphabet[abyte1[i1 + 1]];
                if (byte0 != 61 && byte1 != 61)
                {
                    byte byte5 = base64Alphabet[byte0];
                    byte byte6 = base64Alphabet[byte1];
                    abyte2[j] = (byte)(byte2 << 2 | byte3 >> 4);
                    abyte2[j + 1] = (byte)((byte3 & 0xf) << 4 | 0xf & byte5 >> 2);
                    abyte2[j + 2] = (byte)(byte6 | byte5 << 6);
                } else
                if (byte0 == 61)
                {
                    abyte2[j] = (byte)(byte2 << 2 | byte3 >> 4);
                } else
                if (byte1 == 61)
                {
                    byte byte4 = base64Alphabet[byte0];
                    abyte2[j] = (byte)(byte2 << 2 | byte3 >> 4);
                    abyte2[j + 1] = (byte)((byte3 & 0xf) << 4 | 0xf & byte4 >> 2);
                }
                j += 3;
                l++;
            }
        }
        return abyte2;
    }

    static byte[] discardNonBase64(byte abyte0[])
    {
        byte abyte1[] = new byte[abyte0.length];
        int i = 0;
        for (int j = 0; j < abyte0.length; j++)
        {
            if (isBase64(abyte0[j]))
            {
                int k = i + 1;
                abyte1[i] = abyte0[j];
                i = k;
            }
        }

        byte abyte2[] = new byte[i];
        System.arraycopy(abyte1, 0, abyte2, 0, i);
        return abyte2;
    }

    static byte[] discardWhitespace(byte abyte0[])
    {
        byte abyte1[] = new byte[abyte0.length];
        int i = 0;
        int j = 0;
        do
        {
            if (j < abyte0.length)
            {
                switch (abyte0[j])
                {
                default:
                    int k = i + 1;
                    abyte1[i] = abyte0[j];
                    i = k;
                    // fall through

                case 9: // '\t'
                case 10: // '\n'
                case 13: // '\r'
                case 32: // ' '
                    j++;
                    break;
                }
            } else
            {
                byte abyte2[] = new byte[i];
                System.arraycopy(abyte1, 0, abyte2, 0, i);
                return abyte2;
            }
        } while (true);
    }

    public static byte[] encodeBase64(byte abyte0[])
    {
        return encodeBase64(abyte0, false);
    }

    public static byte[] encodeBase64(byte abyte0[], boolean flag)
    {
        int j;
        int l;
        int i1;
        byte abyte1[];
        int j1;
        int l1;
        int j2;
        int i = 8 * abyte0.length;
        j = i % 24;
        int k = i / 24;
        int i2;
        if (j != 0)
        {
            l = 4 * (k + 1);
        } else
        {
            l = k * 4;
        }
        i1 = 0;
        if (flag)
        {
            int k1;
            int k2;
            byte byte12;
            byte byte13;
            if (CHUNK_SEPARATOR.length == 0)
            {
                i1 = 0;
            } else
            {
                i1 = (int)Math.ceil((float)l / 76F);
            }
            l += i1 * CHUNK_SEPARATOR.length;
        }
        abyte1 = new byte[l];
        j1 = 0;
        k1 = 76;
        l1 = 0;
        i2 = 0;
        while (i2 < k) 
        {
            k2 = i2 * 3;
            byte byte9 = abyte0[k2];
            byte byte10 = abyte0[k2 + 1];
            byte byte11 = abyte0[k2 + 2];
            byte12 = (byte)(byte10 & 0xf);
            byte13 = (byte)(byte9 & 3);
            byte byte14;
            byte byte15;
            byte byte16;
            if ((byte9 & 0xffffff80) == 0)
            {
                byte14 = (byte)(byte9 >> 2);
            } else
            {
                byte14 = (byte)(0xc0 ^ byte9 >> 2);
            }
            if ((byte10 & 0xffffff80) == 0)
            {
                byte15 = (byte)(byte10 >> 4);
            } else
            {
                byte15 = (byte)(0xf0 ^ byte10 >> 4);
            }
            if ((byte11 & 0xffffff80) == 0)
            {
                byte16 = (byte)(byte11 >> 6);
            } else
            {
                byte16 = (byte)(0xfc ^ byte11 >> 6);
            }
            abyte1[j1] = lookUpBase64Alphabet[byte14];
            abyte1[j1 + 1] = lookUpBase64Alphabet[byte15 | byte13 << 4];
            abyte1[j1 + 2] = lookUpBase64Alphabet[byte16 | byte12 << 2];
            abyte1[j1 + 3] = lookUpBase64Alphabet[byte11 & 0x3f];
            j1 += 4;
            if (flag && j1 == k1)
            {
                System.arraycopy(CHUNK_SEPARATOR, 0, abyte1, j1, CHUNK_SEPARATOR.length);
                l1++;
                k1 = 76 * (l1 + 1) + l1 * CHUNK_SEPARATOR.length;
                j1 += CHUNK_SEPARATOR.length;
            }
            i2++;
        }
        j2 = i2 * 3;
        if (j != 8) goto _L2; else goto _L1
_L1:
        byte byte6 = abyte0[j2];
        byte byte7 = (byte)(byte6 & 3);
        byte byte8;
        if ((byte6 & 0xffffff80) == 0)
        {
            byte8 = (byte)(byte6 >> 2);
        } else
        {
            byte8 = (byte)(0xc0 ^ byte6 >> 2);
        }
        abyte1[j1] = lookUpBase64Alphabet[byte8];
        abyte1[j1 + 1] = lookUpBase64Alphabet[byte7 << 4];
        abyte1[j1 + 2] = 61;
        abyte1[j1 + 3] = 61;
_L4:
        if (flag && l1 < i1)
        {
            System.arraycopy(CHUNK_SEPARATOR, 0, abyte1, l - CHUNK_SEPARATOR.length, CHUNK_SEPARATOR.length);
        }
        return abyte1;
_L2:
        if (j == 16)
        {
            byte byte0 = abyte0[j2];
            byte byte1 = abyte0[j2 + 1];
            byte byte2 = (byte)(byte1 & 0xf);
            byte byte3 = (byte)(byte0 & 3);
            byte byte4;
            byte byte5;
            if ((byte0 & 0xffffff80) == 0)
            {
                byte4 = (byte)(byte0 >> 2);
            } else
            {
                byte4 = (byte)(0xc0 ^ byte0 >> 2);
            }
            if ((byte1 & 0xffffff80) == 0)
            {
                byte5 = (byte)(byte1 >> 4);
            } else
            {
                byte5 = (byte)(0xf0 ^ byte1 >> 4);
            }
            abyte1[j1] = lookUpBase64Alphabet[byte4];
            abyte1[j1 + 1] = lookUpBase64Alphabet[byte5 | byte3 << 4];
            abyte1[j1 + 2] = lookUpBase64Alphabet[byte2 << 2];
            abyte1[j1 + 3] = 61;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static byte[] encodeBase64Chunked(byte abyte0[])
    {
        return encodeBase64(abyte0, true);
    }

    public static boolean isArrayByteBase64(byte abyte0[])
    {
        byte abyte1[] = discardWhitespace(abyte0);
        int i = abyte1.length;
        if (i != 0)
        {
            int j = 0;
            while (j < i) 
            {
                if (!isBase64(abyte1[j]))
                {
                    return false;
                }
                j++;
            }
        }
        return true;
    }

    private static boolean isBase64(byte byte0)
    {
        while (byte0 == 61 || base64Alphabet[byte0] != -1) 
        {
            return true;
        }
        return false;
    }

    public Object decode(Object obj)
        throws DecoderException
    {
        if (!(obj instanceof byte[]))
        {
            throw new DecoderException("Parameter supplied to Base64 decode is not a byte[]");
        } else
        {
            return decode((byte[])obj);
        }
    }

    public byte[] decode(byte abyte0[])
    {
        return decodeBase64(abyte0);
    }

    public Object encode(Object obj)
        throws EncoderException
    {
        if (!(obj instanceof byte[]))
        {
            throw new EncoderException("Parameter supplied to Base64 encode is not a byte[]");
        } else
        {
            return encode((byte[])obj);
        }
    }

    public byte[] encode(byte abyte0[])
    {
        return encodeBase64(abyte0, false);
    }

    static 
    {
        base64Alphabet = new byte[255];
        lookUpBase64Alphabet = new byte[64];
        for (int i = 0; i < 255; i++)
        {
            base64Alphabet[i] = -1;
        }

        for (int j = 90; j >= 65; j--)
        {
            base64Alphabet[j] = (byte)(j - 65);
        }

        for (int k = 122; k >= 97; k--)
        {
            base64Alphabet[k] = (byte)(26 + (k - 97));
        }

        for (int l = 57; l >= 48; l--)
        {
            base64Alphabet[l] = (byte)(52 + (l - 48));
        }

        base64Alphabet[43] = 62;
        base64Alphabet[47] = 63;
        for (int i1 = 0; i1 <= 25; i1++)
        {
            lookUpBase64Alphabet[i1] = (byte)(i1 + 65);
        }

        int j1 = 26;
        for (int k1 = 0; j1 <= 51; k1++)
        {
            lookUpBase64Alphabet[j1] = (byte)(k1 + 97);
            j1++;
        }

        int l1 = 52;
        for (int i2 = 0; l1 <= 61; i2++)
        {
            lookUpBase64Alphabet[l1] = (byte)(i2 + 48);
            l1++;
        }

        lookUpBase64Alphabet[62] = 43;
        lookUpBase64Alphabet[63] = 47;
    }
}
