// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

// Referenced classes of package org.codehaus.jackson.io:
//            IOContext

public final class UTF8Writer extends Writer
{

    static final int SURR1_FIRST = 55296;
    static final int SURR1_LAST = 56319;
    static final int SURR2_FIRST = 56320;
    static final int SURR2_LAST = 57343;
    protected final IOContext _context;
    OutputStream _out;
    byte _outBuffer[];
    final int _outBufferEnd;
    int _outPtr;
    int _surrogate;

    public UTF8Writer(IOContext iocontext, OutputStream outputstream)
    {
        _surrogate = 0;
        _context = iocontext;
        _out = outputstream;
        _outBuffer = iocontext.allocWriteEncodingBuffer();
        _outBufferEnd = -4 + _outBuffer.length;
        _outPtr = 0;
    }

    private int convertSurrogate(int i)
        throws IOException
    {
        int j = _surrogate;
        _surrogate = 0;
        if (i < 56320 || i > 57343)
        {
            throw new IOException((new StringBuilder()).append("Broken surrogate pair: first char 0x").append(Integer.toHexString(j)).append(", second 0x").append(Integer.toHexString(i)).append("; illegal combination").toString());
        } else
        {
            return 0x10000 + (j - 55296 << 10) + (i - 56320);
        }
    }

    private void throwIllegal(int i)
        throws IOException
    {
        if (i > 0x10ffff)
        {
            throw new IOException((new StringBuilder()).append("Illegal character point (0x").append(Integer.toHexString(i)).append(") to output; max is 0x10FFFF as per RFC 4627").toString());
        }
        if (i >= 55296)
        {
            if (i <= 56319)
            {
                throw new IOException((new StringBuilder()).append("Unmatched first part of surrogate pair (0x").append(Integer.toHexString(i)).append(")").toString());
            } else
            {
                throw new IOException((new StringBuilder()).append("Unmatched second part of surrogate pair (0x").append(Integer.toHexString(i)).append(")").toString());
            }
        } else
        {
            throw new IOException((new StringBuilder()).append("Illegal character point (0x").append(Integer.toHexString(i)).append(") to output").toString());
        }
    }

    public Writer append(char c)
        throws IOException
    {
        write(c);
        return this;
    }

    public volatile Appendable append(char c)
        throws IOException
    {
        return append(c);
    }

    public void close()
        throws IOException
    {
        if (_out != null)
        {
            if (_outPtr > 0)
            {
                _out.write(_outBuffer, 0, _outPtr);
                _outPtr = 0;
            }
            OutputStream outputstream = _out;
            _out = null;
            byte abyte0[] = _outBuffer;
            if (abyte0 != null)
            {
                _outBuffer = null;
                _context.releaseWriteEncodingBuffer(abyte0);
            }
            outputstream.close();
            int i = _surrogate;
            _surrogate = 0;
            if (i > 0)
            {
                throwIllegal(i);
            }
        }
    }

    public void flush()
        throws IOException
    {
        if (_out != null)
        {
            if (_outPtr > 0)
            {
                _out.write(_outBuffer, 0, _outPtr);
                _outPtr = 0;
            }
            _out.flush();
        }
    }

    public void write(int i)
        throws IOException
    {
        if (_surrogate > 0)
        {
            i = convertSurrogate(i);
        } else
        if (i >= 55296 && i <= 57343)
        {
            if (i > 56319)
            {
                throwIllegal(i);
            }
            _surrogate = i;
            return;
        }
        if (_outPtr >= _outBufferEnd)
        {
            _out.write(_outBuffer, 0, _outPtr);
            _outPtr = 0;
        }
        if (i < 128)
        {
            byte abyte9[] = _outBuffer;
            int k2 = _outPtr;
            _outPtr = k2 + 1;
            abyte9[k2] = (byte)i;
            return;
        }
        int j = _outPtr;
        int j1;
        if (i < 2048)
        {
            byte abyte7[] = _outBuffer;
            int j2 = j + 1;
            abyte7[j] = (byte)(0xc0 | i >> 6);
            byte abyte8[] = _outBuffer;
            j1 = j2 + 1;
            abyte8[j2] = (byte)(0x80 | i & 0x3f);
        } else
        if (i <= 65535)
        {
            byte abyte4[] = _outBuffer;
            int k1 = j + 1;
            abyte4[j] = (byte)(0xe0 | i >> 12);
            byte abyte5[] = _outBuffer;
            int l1 = k1 + 1;
            abyte5[k1] = (byte)(0x80 | 0x3f & i >> 6);
            byte abyte6[] = _outBuffer;
            int i2 = l1 + 1;
            abyte6[l1] = (byte)(0x80 | i & 0x3f);
            j1 = i2;
        } else
        {
            if (i > 0x10ffff)
            {
                throwIllegal(i);
            }
            byte abyte0[] = _outBuffer;
            int k = j + 1;
            abyte0[j] = (byte)(0xf0 | i >> 18);
            byte abyte1[] = _outBuffer;
            int l = k + 1;
            abyte1[k] = (byte)(0x80 | 0x3f & i >> 12);
            byte abyte2[] = _outBuffer;
            int i1 = l + 1;
            abyte2[l] = (byte)(0x80 | 0x3f & i >> 6);
            byte abyte3[] = _outBuffer;
            j1 = i1 + 1;
            abyte3[i1] = (byte)(0x80 | i & 0x3f);
        }
        _outPtr = j1;
    }

    public void write(String s)
        throws IOException
    {
        write(s, 0, s.length());
    }

    public void write(String s, int i, int j)
        throws IOException
    {
        int k;
        byte abyte0[];
        int l;
        int i1;
        int j1;
        if (j < 2)
        {
            if (j == 1)
            {
                write(s.charAt(i));
            }
            return;
        }
        if (_surrogate > 0)
        {
            int i6 = i + 1;
            char c = s.charAt(i);
            j--;
            write(convertSurrogate(c));
            i = i6;
        }
        k = _outPtr;
        abyte0 = _outBuffer;
        l = _outBufferEnd;
        i1 = j + i;
        j1 = i;
_L3:
        int k1;
        int l1;
        if (j1 >= i1)
        {
            break MISSING_BLOCK_LABEL_586;
        }
        if (k >= l)
        {
            _out.write(abyte0, 0, k);
            k = 0;
        }
        k1 = j1 + 1;
        l1 = s.charAt(j1);
        if (l1 >= 128) goto _L2; else goto _L1
_L1:
        int i2;
        int j5;
        i2 = k + 1;
        abyte0[k] = (byte)l1;
        int l4 = i1 - k1;
        int i5 = l - i2;
        if (l4 > i5)
        {
            l4 = i5;
        }
        j5 = l4 + k1;
        j1 = k1;
_L8:
label0:
        {
            if (j1 < j5)
            {
                break label0;
            }
            k = i2;
        }
          goto _L3
        int k5;
        k5 = j1 + 1;
        l1 = s.charAt(j1);
        if (l1 < 128) goto _L5; else goto _L4
_L4:
        j1 = k5;
_L12:
        if (l1 >= 2048) goto _L7; else goto _L6
_L6:
        int l2;
        int j4 = i2 + 1;
        abyte0[i2] = (byte)(0xc0 | l1 >> 6);
        int k4 = j4 + 1;
        abyte0[j4] = (byte)(0x80 | l1 & 0x3f);
        k = k4;
        l2 = j1;
_L11:
        j1 = l2;
          goto _L3
_L5:
        int l5 = i2 + 1;
        abyte0[i2] = (byte)l1;
        i2 = l5;
        j1 = k5;
          goto _L8
_L7:
label1:
        {
            if (l1 >= 55296 && l1 <= 57343)
            {
                break label1;
            }
            int j2 = i2 + 1;
            abyte0[i2] = (byte)(0xe0 | l1 >> 12);
            int k2 = j2 + 1;
            abyte0[j2] = (byte)(0x80 | 0x3f & l1 >> 6);
            k = k2 + 1;
            abyte0[k2] = (byte)(0x80 | l1 & 0x3f);
        }
          goto _L3
        if (l1 > 56319)
        {
            _outPtr = i2;
            throwIllegal(l1);
        }
        _surrogate = l1;
        if (j1 < i1) goto _L10; else goto _L9
_L9:
        k = i2;
        j1;
_L13:
        _outPtr = k;
        return;
_L10:
        l2 = j1 + 1;
        int i3 = convertSurrogate(s.charAt(j1));
        if (i3 > 0x10ffff)
        {
            _outPtr = i2;
            throwIllegal(i3);
        }
        int j3 = i2 + 1;
        abyte0[i2] = (byte)(0xf0 | i3 >> 18);
        int k3 = j3 + 1;
        abyte0[j3] = (byte)(0x80 | 0x3f & i3 >> 12);
        int l3 = k3 + 1;
        abyte0[k3] = (byte)(0x80 | 0x3f & i3 >> 6);
        int i4 = l3 + 1;
        abyte0[l3] = (byte)(0x80 | i3 & 0x3f);
        k = i4;
          goto _L11
_L2:
        i2 = k;
        j1 = k1;
          goto _L12
        j1;
          goto _L13
    }

    public void write(char ac[])
        throws IOException
    {
        write(ac, 0, ac.length);
    }

    public void write(char ac[], int i, int j)
        throws IOException
    {
        int k;
        byte abyte0[];
        int l;
        int i1;
        int j1;
        if (j < 2)
        {
            if (j == 1)
            {
                write(ac[i]);
            }
            return;
        }
        if (_surrogate > 0)
        {
            int i6 = i + 1;
            char c = ac[i];
            j--;
            write(convertSurrogate(c));
            i = i6;
        }
        k = _outPtr;
        abyte0 = _outBuffer;
        l = _outBufferEnd;
        i1 = j + i;
        j1 = i;
_L3:
        int k1;
        int l1;
        if (j1 >= i1)
        {
            break MISSING_BLOCK_LABEL_576;
        }
        if (k >= l)
        {
            _out.write(abyte0, 0, k);
            k = 0;
        }
        k1 = j1 + 1;
        l1 = ac[j1];
        if (l1 >= 128) goto _L2; else goto _L1
_L1:
        int i2;
        int j5;
        i2 = k + 1;
        abyte0[k] = (byte)l1;
        int l4 = i1 - k1;
        int i5 = l - i2;
        if (l4 > i5)
        {
            l4 = i5;
        }
        j5 = l4 + k1;
        j1 = k1;
_L8:
label0:
        {
            if (j1 < j5)
            {
                break label0;
            }
            k = i2;
        }
          goto _L3
        int k5;
        k5 = j1 + 1;
        l1 = ac[j1];
        if (l1 < 128) goto _L5; else goto _L4
_L4:
        j1 = k5;
_L12:
        if (l1 >= 2048) goto _L7; else goto _L6
_L6:
        int l2;
        int j4 = i2 + 1;
        abyte0[i2] = (byte)(0xc0 | l1 >> 6);
        int k4 = j4 + 1;
        abyte0[j4] = (byte)(0x80 | l1 & 0x3f);
        k = k4;
        l2 = j1;
_L11:
        j1 = l2;
          goto _L3
_L5:
        int l5 = i2 + 1;
        abyte0[i2] = (byte)l1;
        i2 = l5;
        j1 = k5;
          goto _L8
_L7:
label1:
        {
            if (l1 >= 55296 && l1 <= 57343)
            {
                break label1;
            }
            int j2 = i2 + 1;
            abyte0[i2] = (byte)(0xe0 | l1 >> 12);
            int k2 = j2 + 1;
            abyte0[j2] = (byte)(0x80 | 0x3f & l1 >> 6);
            k = k2 + 1;
            abyte0[k2] = (byte)(0x80 | l1 & 0x3f);
        }
          goto _L3
        if (l1 > 56319)
        {
            _outPtr = i2;
            throwIllegal(l1);
        }
        _surrogate = l1;
        if (j1 < i1) goto _L10; else goto _L9
_L9:
        k = i2;
        j1;
_L13:
        _outPtr = k;
        return;
_L10:
        l2 = j1 + 1;
        int i3 = convertSurrogate(ac[j1]);
        if (i3 > 0x10ffff)
        {
            _outPtr = i2;
            throwIllegal(i3);
        }
        int j3 = i2 + 1;
        abyte0[i2] = (byte)(0xf0 | i3 >> 18);
        int k3 = j3 + 1;
        abyte0[j3] = (byte)(0x80 | 0x3f & i3 >> 12);
        int l3 = k3 + 1;
        abyte0[k3] = (byte)(0x80 | 0x3f & i3 >> 6);
        int i4 = l3 + 1;
        abyte0[l3] = (byte)(0x80 | i3 & 0x3f);
        k = i4;
          goto _L11
_L2:
        i2 = k;
        j1 = k1;
          goto _L12
        j1;
          goto _L13
    }
}
