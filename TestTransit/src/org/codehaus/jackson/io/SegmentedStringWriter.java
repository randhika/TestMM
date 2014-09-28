// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.io;

import java.io.IOException;
import java.io.Writer;
import org.codehaus.jackson.util.BufferRecycler;
import org.codehaus.jackson.util.TextBuffer;

public final class SegmentedStringWriter extends Writer
{

    protected final TextBuffer _buffer;

    public SegmentedStringWriter(BufferRecycler bufferrecycler)
    {
        _buffer = new TextBuffer(bufferrecycler);
    }

    public Writer append(char c)
    {
        write(c);
        return this;
    }

    public Writer append(CharSequence charsequence)
    {
        String s = charsequence.toString();
        _buffer.append(s, 0, s.length());
        return this;
    }

    public Writer append(CharSequence charsequence, int i, int j)
    {
        String s = charsequence.subSequence(i, j).toString();
        _buffer.append(s, 0, s.length());
        return this;
    }

    public volatile Appendable append(char c)
        throws IOException
    {
        return append(c);
    }

    public volatile Appendable append(CharSequence charsequence)
        throws IOException
    {
        return append(charsequence);
    }

    public volatile Appendable append(CharSequence charsequence, int i, int j)
        throws IOException
    {
        return append(charsequence, i, j);
    }

    public void close()
    {
    }

    public void flush()
    {
    }

    public String getAndClear()
    {
        String s = _buffer.contentsAsString();
        _buffer.releaseBuffers();
        return s;
    }

    public void write(int i)
    {
        _buffer.append((char)i);
    }

    public void write(String s)
    {
        _buffer.append(s, 0, s.length());
    }

    public void write(String s, int i, int j)
    {
        _buffer.append(s, 0, s.length());
    }

    public void write(char ac[])
    {
        _buffer.append(ac, 0, ac.length);
    }

    public void write(char ac[], int i, int j)
    {
        _buffer.append(ac, i, j);
    }
}
