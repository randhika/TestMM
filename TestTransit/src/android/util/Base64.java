// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.util;

import java.io.UnsupportedEncodingException;

public class Base64
{
    static abstract class Coder
    {

        public int op;
        public byte output[];

        public abstract int maxOutputSize(int i);

        public abstract boolean process(byte abyte0[], int i, int j, boolean flag);

        Coder()
        {
        }
    }

    static class Decoder extends Coder
    {

        private static final int DECODE[] = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 
            54, 55, 56, 57, 58, 59, 60, 61, -1, -1, 
            -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 
            5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 
            25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 
            29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 
            39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 
            49, 50, 51, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1
        };
        private static final int DECODE_WEBSAFE[] = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 
            54, 55, 56, 57, 58, 59, 60, 61, -1, -1, 
            -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 
            5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 
            25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 
            29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 
            39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 
            49, 50, 51, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1
        };
        private static final int EQUALS = -2;
        private static final int SKIP = -1;
        private final int alphabet[];
        private int state;
        private int value;

        public int maxOutputSize(int i)
        {
            return 10 + (i * 3) / 4;
        }

        public boolean process(byte abyte0[], int i, int j, boolean flag)
        {
            int k;
            int l;
            int i1;
            int j1;
            int k1;
            byte abyte1[];
            int ai[];
            if (state == 6)
            {
                return false;
            }
            k = i;
            l = j + i;
            i1 = state;
            j1 = value;
            k1 = 0;
            abyte1 = output;
            ai = alphabet;
_L12:
            if (k >= l)
            {
                break MISSING_BLOCK_LABEL_704;
            }
            if (i1 != 0) goto _L2; else goto _L1
_L1:
            do
            {
                if (k + 4 > l)
                {
                    break;
                }
                j1 = ai[0xff & abyte0[k]] << 18 | ai[0xff & abyte0[k + 1]] << 12 | ai[0xff & abyte0[k + 2]] << 6 | ai[0xff & abyte0[k + 3]];
                if (j1 < 0)
                {
                    break;
                }
                abyte1[k1 + 2] = (byte)j1;
                abyte1[k1 + 1] = (byte)(j1 >> 8);
                abyte1[k1] = (byte)(j1 >> 16);
                k1 += 3;
                k += 4;
            } while (true);
            if (k < l) goto _L2; else goto _L3
_L3:
            int l1 = k1;
_L22:
            if (!flag)
            {
                state = i1;
                value = j1;
                op = l1;
                return true;
            }
              goto _L4
_L2:
            int l2;
            int i3;
            l2 = k + 1;
            i3 = ai[0xff & abyte0[k]];
            i1;
            JVM INSTR tableswitch 0 5: default 276
        //                       0 283
        //                       1 312
        //                       2 347
        //                       3 415
        //                       4 526
        //                       5 553;
               goto _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L5:
            k = l2;
              goto _L12
_L6:
            if (i3 >= 0)
            {
                j1 = i3;
                i1++;
            } else
            if (i3 != -1)
            {
                state = 6;
                return false;
            }
              goto _L5
_L7:
            if (i3 >= 0)
            {
                j1 = i3 | j1 << 6;
                i1++;
            } else
            if (i3 != -1)
            {
                state = 6;
                return false;
            }
              goto _L5
_L8:
            if (i3 >= 0)
            {
                j1 = i3 | j1 << 6;
                i1++;
            } else
            if (i3 == -2)
            {
                int j3 = k1 + 1;
                abyte1[k1] = (byte)(j1 >> 4);
                i1 = 4;
                k1 = j3;
            } else
            if (i3 != -1)
            {
                state = 6;
                return false;
            }
              goto _L5
_L9:
            if (i3 >= 0)
            {
                j1 = i3 | j1 << 6;
                abyte1[k1 + 2] = (byte)j1;
                abyte1[k1 + 1] = (byte)(j1 >> 8);
                abyte1[k1] = (byte)(j1 >> 16);
                k1 += 3;
                i1 = 0;
            } else
            if (i3 == -2)
            {
                abyte1[k1 + 1] = (byte)(j1 >> 2);
                abyte1[k1] = (byte)(j1 >> 10);
                k1 += 2;
                i1 = 5;
            } else
            if (i3 != -1)
            {
                state = 6;
                return false;
            }
              goto _L5
_L10:
            if (i3 != -2)
            {
                continue; /* Loop/switch isn't completed */
            }
            i1++;
              goto _L5
            if (i3 == -1) goto _L5; else goto _L13
_L13:
            state = 6;
            return false;
_L11:
            if (i3 == -1) goto _L5; else goto _L14
_L14:
            state = 6;
            return false;
_L4:
            i1;
            JVM INSTR tableswitch 0 4: default 604
        //                       0 622
        //                       1 629
        //                       2 637
        //                       3 656
        //                       4 696;
               goto _L15 _L16 _L17 _L18 _L19 _L20
_L15:
            int k2 = l1;
_L21:
            state = i1;
            op = k2;
            return true;
_L16:
            k2 = l1;
            continue; /* Loop/switch isn't completed */
_L17:
            state = 6;
            return false;
_L18:
            k2 = l1 + 1;
            abyte1[l1] = (byte)(j1 >> 4);
            continue; /* Loop/switch isn't completed */
_L19:
            int i2 = l1 + 1;
            abyte1[l1] = (byte)(j1 >> 10);
            int j2 = i2 + 1;
            abyte1[i2] = (byte)(j1 >> 2);
            k2 = j2;
            if (true) goto _L21; else goto _L20
_L20:
            state = 6;
            return false;
            l1 = k1;
              goto _L22
        }


        public Decoder(int i, byte abyte0[])
        {
            output = abyte0;
            int ai[];
            if ((i & 8) == 0)
            {
                ai = DECODE;
            } else
            {
                ai = DECODE_WEBSAFE;
            }
            alphabet = ai;
            state = 0;
            value = 0;
        }
    }

    static class Encoder extends Coder
    {

        static final boolean $assertionsDisabled = false;
        private static final byte ENCODE[] = {
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
            75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
            85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
            101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
            111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
            121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
            56, 57, 43, 47
        };
        private static final byte ENCODE_WEBSAFE[] = {
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
            75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
            85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
            101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
            111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
            121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
            56, 57, 45, 95
        };
        public static final int LINE_GROUPS = 19;
        private final byte alphabet[];
        private int count;
        public final boolean do_cr;
        public final boolean do_newline;
        public final boolean do_padding;
        private final byte tail[] = new byte[2];
        int tailLen;

        public int maxOutputSize(int i)
        {
            return 10 + (i * 8) / 5;
        }

        public boolean process(byte abyte0[], int i, int j, boolean flag)
        {
            byte abyte1[];
            byte abyte2[];
            int k;
            int l;
            int i1;
            int j1;
            abyte1 = alphabet;
            abyte2 = output;
            k = count;
            l = i;
            i1 = j + i;
            j1 = -1;
            tailLen;
            JVM INSTR tableswitch 0 2: default 60
        //                       0 60
        //                       1 387
        //                       2 460;
               goto _L1 _L1 _L2 _L3
_L1:
            int i2;
            int k2;
            i2 = 0;
            if (j1 == -1)
            {
                break MISSING_BLOCK_LABEL_1278;
            }
            int j9 = 0 + 1;
            abyte2[0] = abyte1[0x3f & j1 >> 18];
            int k9 = j9 + 1;
            abyte2[j9] = abyte1[0x3f & j1 >> 12];
            int l9 = k9 + 1;
            abyte2[k9] = abyte1[0x3f & j1 >> 6];
            i2 = l9 + 1;
            abyte2[l9] = abyte1[j1 & 0x3f];
            if (--k != 0)
            {
                break MISSING_BLOCK_LABEL_1278;
            }
            if (do_cr)
            {
                int i10 = i2 + 1;
                abyte2[i2] = 13;
                i2 = i10;
            }
            k2 = i2 + 1;
            abyte2[i2] = 10;
            k = 19;
_L13:
            int j2;
            for (j2 = l; j2 + 3 <= i1; j2 = l)
            {
                int l8 = (0xff & abyte0[j2]) << 16 | (0xff & abyte0[j2 + 1]) << 8 | 0xff & abyte0[j2 + 2];
                abyte2[k2] = abyte1[0x3f & l8 >> 18];
                abyte2[k2 + 1] = abyte1[0x3f & l8 >> 12];
                abyte2[k2 + 2] = abyte1[0x3f & l8 >> 6];
                abyte2[k2 + 3] = abyte1[l8 & 0x3f];
                l = j2 + 3;
                i2 = k2 + 4;
                if (--k != 0)
                {
                    break MISSING_BLOCK_LABEL_1278;
                }
                if (do_cr)
                {
                    int i9 = i2 + 1;
                    abyte2[i2] = 13;
                    i2 = i9;
                }
                k2 = i2 + 1;
                abyte2[i2] = 10;
                k = 19;
            }

              goto _L4
_L2:
            if (l + 2 <= i1)
            {
                int j10 = (0xff & tail[0]) << 16;
                int k10 = l + 1;
                int l10 = j10 | (0xff & abyte0[l]) << 8;
                l = k10 + 1;
                j1 = l10 | 0xff & abyte0[k10];
                tailLen = 0;
            }
              goto _L1
_L3:
            if (l + 1 <= i1)
            {
                int k1 = (0xff & tail[0]) << 16 | (0xff & tail[1]) << 8;
                int l1 = l + 1;
                j1 = k1 | 0xff & abyte0[l];
                tailLen = 0;
                l = l1;
            }
              goto _L1
_L4:
            if (!flag) goto _L6; else goto _L5
_L5:
            if (j2 - tailLen != i1 - 1) goto _L8; else goto _L7
_L7:
            int l2;
            int l3;
            int l5;
            byte byte2;
            int i7;
            int j7;
            int k7;
            int l7;
            int i8;
            if (tailLen > 0)
            {
                byte abyte8[] = tail;
                int k8 = 0 + 1;
                byte2 = abyte8[0];
                i7 = k8;
                l3 = j2;
            } else
            {
                l3 = j2 + 1;
                byte2 = abyte0[j2];
                i7 = 0;
            }
            j7 = (byte2 & 0xff) << 4;
            tailLen = tailLen - i7;
            k7 = k2 + 1;
            abyte2[k2] = abyte1[0x3f & j7 >> 6];
            l7 = k7 + 1;
            abyte2[k7] = abyte1[j7 & 0x3f];
            if (do_padding)
            {
                int j8 = l7 + 1;
                abyte2[l7] = 61;
                l7 = j8 + 1;
                abyte2[j8] = 61;
            }
            l2 = l7;
            if (!do_newline) goto _L10; else goto _L9
_L9:
            if (do_cr)
            {
                i8 = l2 + 1;
                abyte2[l2] = 13;
                l2 = i8;
            }
            l5 = l2 + 1;
            abyte2[l2] = 10;
_L12:
            l2 = l5;
_L10:
            if (!$assertionsDisabled && tailLen != 0)
            {
                throw new AssertionError();
            }
              goto _L11
_L8:
            if (j2 - tailLen == i1 - 2)
            {
                byte byte0;
                int j4;
                int k4;
                byte byte1;
                int i5;
                int j5;
                int k5;
                if (tailLen > 1)
                {
                    byte abyte7[] = tail;
                    int l6 = 0 + 1;
                    byte0 = abyte7[0];
                    j4 = l6;
                    l3 = j2;
                } else
                {
                    l3 = j2 + 1;
                    byte0 = abyte0[j2];
                    j4 = 0;
                }
                k4 = (byte0 & 0xff) << 10;
                if (tailLen > 0)
                {
                    byte abyte6[] = tail;
                    int k6 = j4 + 1;
                    byte1 = abyte6[j4];
                    j4 = k6;
                } else
                {
                    int l4 = l3 + 1;
                    byte1 = abyte0[l3];
                    l3 = l4;
                }
                i5 = k4 | (byte1 & 0xff) << 2;
                tailLen = tailLen - j4;
                j5 = k2 + 1;
                abyte2[k2] = abyte1[0x3f & i5 >> 12];
                k5 = j5 + 1;
                abyte2[j5] = abyte1[0x3f & i5 >> 6];
                l2 = k5 + 1;
                abyte2[k5] = abyte1[i5 & 0x3f];
                if (do_padding)
                {
                    int j6 = l2 + 1;
                    abyte2[l2] = 61;
                    l2 = j6;
                }
                if (do_newline)
                {
                    if (do_cr)
                    {
                        int i6 = l2 + 1;
                        abyte2[l2] = 13;
                        l2 = i6;
                    }
                    l5 = l2 + 1;
                    abyte2[l2] = 10;
                    continue; /* Loop/switch isn't completed */
                }
            } else
            {
                if (do_newline && k2 > 0 && k != 19)
                {
                    byte abyte3[];
                    int i3;
                    byte abyte4[];
                    int j3;
                    byte abyte5[];
                    int k3;
                    int i4;
                    if (do_cr)
                    {
                        i4 = k2 + 1;
                        abyte2[k2] = 13;
                    } else
                    {
                        i4 = k2;
                    }
                    k2 = i4 + 1;
                    abyte2[i4] = 10;
                }
                l3 = j2;
                l2 = k2;
            }
            if (true) goto _L10; else goto _L11
_L11:
            if (!$assertionsDisabled && l3 != i1)
            {
                throw new AssertionError();
            }
            break MISSING_BLOCK_LABEL_1178;
            if (true) goto _L12; else goto _L6
_L6:
            if (j2 == i1 - 1)
            {
                abyte5 = tail;
                k3 = tailLen;
                tailLen = k3 + 1;
                abyte5[k3] = abyte0[j2];
                j2;
                l2 = k2;
            } else
            {
                if (j2 == i1 - 2)
                {
                    abyte3 = tail;
                    i3 = tailLen;
                    tailLen = i3 + 1;
                    abyte3[i3] = abyte0[j2];
                    abyte4 = tail;
                    j3 = tailLen;
                    tailLen = j3 + 1;
                    abyte4[j3] = abyte0[j2 + 1];
                }
                j2;
                l2 = k2;
            }
            op = l2;
            count = k;
            return true;
            j2 = l;
            k2 = i2;
              goto _L13
        }

        static 
        {
            boolean flag;
            if (!android/util/Base64.desiredAssertionStatus())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            $assertionsDisabled = flag;
        }

        public Encoder(int i, byte abyte0[])
        {
            boolean flag = true;
            super();
            output = abyte0;
            boolean flag1;
            boolean flag2;
            byte abyte1[];
            int j;
            if ((i & 1) == 0)
            {
                flag1 = flag;
            } else
            {
                flag1 = false;
            }
            do_padding = flag1;
            if ((i & 2) == 0)
            {
                flag2 = flag;
            } else
            {
                flag2 = false;
            }
            do_newline = flag2;
            if ((i & 4) == 0)
            {
                flag = false;
            }
            do_cr = flag;
            if ((i & 8) == 0)
            {
                abyte1 = ENCODE;
            } else
            {
                abyte1 = ENCODE_WEBSAFE;
            }
            alphabet = abyte1;
            tailLen = 0;
            if (do_newline)
            {
                j = 19;
            } else
            {
                j = -1;
            }
            count = j;
        }
    }


    static final boolean $assertionsDisabled = false;
    public static final int CRLF = 4;
    public static final int DEFAULT = 0;
    public static final int NO_CLOSE = 16;
    public static final int NO_PADDING = 1;
    public static final int NO_WRAP = 2;
    public static final int URL_SAFE = 8;

    private Base64()
    {
    }

    public static byte[] decode(String s, int i)
    {
        return decode(s.getBytes(), i);
    }

    public static byte[] decode(byte abyte0[], int i)
    {
        return decode(abyte0, 0, abyte0.length, i);
    }

    public static byte[] decode(byte abyte0[], int i, int j, int k)
    {
        Decoder decoder = new Decoder(k, new byte[(j * 3) / 4]);
        if (!decoder.process(abyte0, i, j, true))
        {
            throw new IllegalArgumentException("bad base-64");
        }
        if (decoder.op == decoder.output.length)
        {
            return decoder.output;
        } else
        {
            byte abyte1[] = new byte[decoder.op];
            System.arraycopy(decoder.output, 0, abyte1, 0, decoder.op);
            return abyte1;
        }
    }

    public static byte[] encode(byte abyte0[], int i)
    {
        return encode(abyte0, 0, abyte0.length, i);
    }

    public static byte[] encode(byte abyte0[], int i, int j, int k)
    {
        Encoder encoder;
        int l;
        encoder = new Encoder(k, null);
        l = 4 * (j / 3);
        if (!encoder.do_padding) goto _L2; else goto _L1
_L1:
        if (j % 3 > 0)
        {
            l += 4;
        }
_L4:
        if (encoder.do_newline && j > 0)
        {
            int i1 = 1 + (j - 1) / 57;
            byte byte0;
            if (encoder.do_cr)
            {
                byte0 = 2;
            } else
            {
                byte0 = 1;
            }
            l += byte0 * i1;
        }
        encoder.output = new byte[l];
        encoder.process(abyte0, i, j, true);
        if (!$assertionsDisabled && encoder.op != l)
        {
            throw new AssertionError();
        } else
        {
            return encoder.output;
        }
_L2:
        switch (j % 3)
        {
        case 1: // '\001'
            l += 2;
            break;

        case 2: // '\002'
            l += 3;
            break;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static String encodeToString(byte abyte0[], int i)
    {
        String s;
        try
        {
            s = new String(encode(abyte0, i), "US-ASCII");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new AssertionError(unsupportedencodingexception);
        }
        return s;
    }

    public static String encodeToString(byte abyte0[], int i, int j, int k)
    {
        String s;
        try
        {
            s = new String(encode(abyte0, i, j, k), "US-ASCII");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new AssertionError(unsupportedencodingexception);
        }
        return s;
    }

    static 
    {
        boolean flag;
        if (!android/util/Base64.desiredAssertionStatus())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        $assertionsDisabled = flag;
    }
}
