// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.io;

import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package org.codehaus.jackson.io:
//            IOContext

public final class MergedStream extends InputStream
{

    byte _buffer[];
    protected final IOContext _context;
    final int _end;
    final InputStream _in;
    int _ptr;

    public MergedStream(IOContext iocontext, InputStream inputstream, byte abyte0[], int i, int j)
    {
        _context = iocontext;
        _in = inputstream;
        _buffer = abyte0;
        _ptr = i;
        _end = j;
    }

    private void freeMergedBuffer()
    {
        byte abyte0[] = _buffer;
        if (abyte0 != null)
        {
            _buffer = null;
            if (_context != null)
            {
                _context.releaseReadIOBuffer(abyte0);
            }
        }
    }

    public int available()
        throws IOException
    {
        if (_buffer != null)
        {
            return _end - _ptr;
        } else
        {
            return _in.available();
        }
    }

    public void close()
        throws IOException
    {
        freeMergedBuffer();
        _in.close();
    }

    public void mark(int i)
    {
        if (_buffer == null)
        {
            _in.mark(i);
        }
    }

    public boolean markSupported()
    {
        return _buffer == null && _in.markSupported();
    }

    public int read()
        throws IOException
    {
        if (_buffer != null)
        {
            byte abyte0[] = _buffer;
            int i = _ptr;
            _ptr = i + 1;
            int j = 0xff & abyte0[i];
            if (_ptr >= _end)
            {
                freeMergedBuffer();
            }
            return j;
        } else
        {
            return _in.read();
        }
    }

    public int read(byte abyte0[])
        throws IOException
    {
        return read(abyte0, 0, abyte0.length);
    }

    public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        if (_buffer != null)
        {
            int k = _end - _ptr;
            if (j > k)
            {
                j = k;
            }
            System.arraycopy(_buffer, _ptr, abyte0, i, j);
            _ptr = j + _ptr;
            if (_ptr >= _end)
            {
                freeMergedBuffer();
            }
            return j;
        } else
        {
            return _in.read(abyte0, i, j);
        }
    }

    public void reset()
        throws IOException
    {
        if (_buffer == null)
        {
            _in.reset();
        }
    }

    public long skip(long l)
        throws IOException
    {
        long l1 = 0L;
        if (_buffer != null)
        {
            int i = _end - _ptr;
            if ((long)i > l)
            {
                _ptr = _ptr + (int)l;
                return l;
            }
            freeMergedBuffer();
            l1 += i;
            l -= i;
        }
        if (l > 0L)
        {
            l1 += _in.skip(l);
        }
        return l1;
    }
}
