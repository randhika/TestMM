// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package jp.co.yahoo.android.common:
//            Base64

public static class decodabet extends FilterInputStream
{

    private boolean breakLines;
    private byte buffer[];
    private int bufferLength;
    private byte decodabet[];
    private boolean encode;
    private int lineLength;
    private int numSigBytes;
    private int options;
    private int position;

    public int read()
        throws IOException
    {
        return -1;
        byte abyte1[] = new byte[4];
        int j = 0;
        do
        {
label0:
            {
                int k;
                if (j < 4)
                {
                    do
                    {
                        k = in.read();
                    } while (k >= 0 && decodabet[k & 0x7f] <= -5);
                    if (k >= 0)
                    {
                        break label0;
                    }
                }
                if (j == 4)
                {
                    numSigBytes = Base64.access$200(abyte1, 0, buffer, 0, options);
                    position = 0;
                } else
                if (j == 0)
                {
                    return -1;
                } else
                {
                    throw new IOException("Improperly padded Base64 input.");
                }
                continue;
            }
            abyte1[j] = (byte)k;
            j++;
        } while (true);
        if (position < 0)
        {
            if (!encode)
            {
                break MISSING_BLOCK_LABEL_117;
            }
            byte abyte2[] = new byte[3];
            int l = 0;
            int i1 = 0;
            do
            {
                if (i1 >= 3)
                {
                    break;
                }
                int j1 = in.read();
                if (j1 < 0)
                {
                    break;
                }
                abyte2[i1] = (byte)j1;
                l++;
                i1++;
            } while (true);
            if (l <= 0)
            {
                break MISSING_BLOCK_LABEL_115;
            }
            Base64.access$100(abyte2, 0, l, buffer, 0, options);
            position = 0;
            numSigBytes = 4;
        }
        do
        {
            if (position >= 0)
            {
                if (position >= numSigBytes)
                {
                    return -1;
                }
                if (encode && breakLines && lineLength >= 76)
                {
                    lineLength = 0;
                    return 10;
                }
                lineLength = 1 + lineLength;
                byte abyte0[] = buffer;
                int i = position;
                position = i + 1;
                byte byte0 = abyte0[i];
                if (position >= bufferLength)
                {
                    position = -1;
                }
                return byte0 & 0xff;
            }
            throw new IOException("Error in Base64 code reading stream.");
        } while (true);
    }

    public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        int k;
label0:
        {
            k = 0;
            do
            {
                if (k >= j)
                {
                    break label0;
                }
                int l = read();
                if (l < 0)
                {
                    break;
                }
                abyte0[i + k] = (byte)l;
                k++;
            } while (true);
            if (k == 0)
            {
                k = -1;
            }
        }
        return k;
    }

    public (InputStream inputstream)
    {
        this(inputstream, 0);
    }

    public <init>(InputStream inputstream, int i)
    {
        boolean flag = true;
        super(inputstream);
        options = i;
        boolean flag1;
        int j;
        if ((i & 8) > 0)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        breakLines = flag1;
        if ((i & 1) <= 0)
        {
            flag = false;
        }
        encode = flag;
        if (encode)
        {
            j = 4;
        } else
        {
            j = 3;
        }
        bufferLength = j;
        buffer = new byte[bufferLength];
        position = -1;
        lineLength = 0;
        decodabet = Base64.access$000(i);
    }
}
