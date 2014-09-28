// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.util;


// Referenced classes of package android.util:
//            Base64

static class count extends count
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
    //                   0 60
    //                   1 387
    //                   2 460;
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

    public (int i, byte abyte0[])
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
