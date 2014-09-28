// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.io;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package org.codehaus.jackson.io:
//            BaseReader, IOContext

public final class UTF32Reader extends BaseReader
{

    final boolean mBigEndian;
    int mByteCount;
    int mCharCount;
    char mSurrogate;

    public UTF32Reader(IOContext iocontext, InputStream inputstream, byte abyte0[], int i, int j, boolean flag)
    {
        super(iocontext, inputstream, abyte0, i, j);
        mSurrogate = '\0';
        mCharCount = 0;
        mByteCount = 0;
        mBigEndian = flag;
    }

    private boolean loadMore(int i)
        throws IOException
    {
        mByteCount = mByteCount + (_length - i);
        int k;
        if (i > 0)
        {
            if (_ptr > 0)
            {
                for (int l = 0; l < i; l++)
                {
                    _buffer[l] = _buffer[l + _ptr];
                }

                _ptr = 0;
            }
        } else
        {
            _ptr = 0;
            int j = _in.read(_buffer);
            if (j < 1)
            {
                _length = 0;
                if (j < 0)
                {
                    freeBuffers();
                    return false;
                }
                reportStrangeStream();
            }
            _length = j;
        }
        for (_length = i; _length < 4; _length = k + _length)
        {
            k = _in.read(_buffer, _length, _buffer.length - _length);
            if (k < 1)
            {
                if (k < 0)
                {
                    freeBuffers();
                    reportUnexpectedEOF(_length, 4);
                }
                reportStrangeStream();
            }
        }

        return true;
    }

    private void reportInvalid(int i, int j, String s)
        throws IOException
    {
        int k = -1 + (mByteCount + _ptr);
        int l = j + mCharCount;
        throw new CharConversionException((new StringBuilder()).append("Invalid UTF-32 character 0x").append(Integer.toHexString(i)).append(s).append(" at char #").append(l).append(", byte #").append(k).append(")").toString());
    }

    private void reportUnexpectedEOF(int i, int j)
        throws IOException
    {
        int k = i + mByteCount;
        int l = mCharCount;
        throw new CharConversionException((new StringBuilder()).append("Unexpected EOF in the middle of a 4-byte UTF-32 char: got ").append(i).append(", needed ").append(j).append(", at char #").append(l).append(", byte #").append(k).append(")").toString());
    }

    public volatile void close()
        throws IOException
    {
        super.close();
    }

    public volatile int read()
        throws IOException
    {
        return super.read();
    }

    public int read(char ac[], int i, int j)
        throws IOException
    {
        if (_buffer != null) goto _L2; else goto _L1
_L1:
        return -1;
_L2:
        int k;
        if (j < 1)
        {
            return j;
        }
        if (i < 0 || i + j > ac.length)
        {
            reportBounds(ac, i, j);
        }
        k = j + i;
        if (mSurrogate == 0) goto _L4; else goto _L3
_L3:
        int i1;
        i1 = i + 1;
        ac[i] = mSurrogate;
        mSurrogate = '\0';
_L10:
        int j1;
        int i2;
        if (i1 >= k)
        {
            break MISSING_BLOCK_LABEL_407;
        }
        int l1 = _ptr;
        int l;
        int k1;
        int j2;
        if (mBigEndian)
        {
            i2 = _buffer[l1] << 24 | (0xff & _buffer[l1 + 1]) << 16 | (0xff & _buffer[l1 + 2]) << 8 | 0xff & _buffer[l1 + 3];
        } else
        {
            i2 = 0xff & _buffer[l1] | (0xff & _buffer[l1 + 1]) << 8 | (0xff & _buffer[l1 + 2]) << 16 | _buffer[l1 + 3] << 24;
        }
        _ptr = 4 + _ptr;
        if (i2 <= 65535) goto _L6; else goto _L5
_L5:
        if (i2 > 0x10ffff)
        {
            reportInvalid(i2, i1 - i, (new StringBuilder()).append("(above ").append(Integer.toHexString(0x10ffff)).append(") ").toString());
        }
        j2 = i2 - 0x10000;
        j1 = i1 + 1;
        ac[i1] = (char)(55296 + (j2 >> 10));
        i2 = 0xdc00 | j2 & 0x3ff;
        if (j1 < k) goto _L8; else goto _L7
_L7:
        mSurrogate = (char)i2;
_L12:
        k1 = j1 - i;
        mCharCount = k1 + mCharCount;
        return k1;
_L4:
        l = _length - _ptr;
        if (l < 4 && !loadMore(l)) goto _L1; else goto _L9
_L9:
        i1 = i;
          goto _L10
_L6:
        j1 = i1;
_L8:
        i1 = j1 + 1;
        ac[j1] = (char)i2;
        if (_ptr < _length) goto _L10; else goto _L11
_L11:
        j1 = i1;
          goto _L12
        j1 = i1;
          goto _L12
    }
}
