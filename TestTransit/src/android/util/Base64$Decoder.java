// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.util;


// Referenced classes of package android.util:
//            Base64

static class value extends value
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
    //                   0 283
    //                   1 312
    //                   2 347
    //                   3 415
    //                   4 526
    //                   5 553;
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
    //                   0 622
    //                   1 629
    //                   2 637
    //                   3 656
    //                   4 696;
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


    public (int i, byte abyte0[])
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
