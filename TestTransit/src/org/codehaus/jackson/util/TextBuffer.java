// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.codehaus.jackson.io.NumberInput;

// Referenced classes of package org.codehaus.jackson.util:
//            BufferRecycler

public final class TextBuffer
{

    static final int MAX_SEGMENT_LEN = 0x40000;
    static final int MIN_SEGMENT_LEN = 1000;
    static final char NO_CHARS[] = new char[0];
    private final BufferRecycler _allocator;
    private char _currentSegment[];
    private int _currentSize;
    private boolean _hasSegments;
    private char _inputBuffer[];
    private int _inputLen;
    private int _inputStart;
    private char _resultArray[];
    private String _resultString;
    private int _segmentSize;
    private ArrayList _segments;

    public TextBuffer(BufferRecycler bufferrecycler)
    {
        _hasSegments = false;
        _allocator = bufferrecycler;
    }

    private final char[] _charArray(int i)
    {
        return new char[i];
    }

    private char[] buildResultArray()
    {
        if (_resultString != null)
        {
            return _resultString.toCharArray();
        }
        if (_inputStart >= 0)
        {
            if (_inputLen < 1)
            {
                return NO_CHARS;
            } else
            {
                char ac2[] = _charArray(_inputLen);
                System.arraycopy(_inputBuffer, _inputStart, ac2, 0, _inputLen);
                return ac2;
            }
        }
        int i = size();
        if (i < 1)
        {
            return NO_CHARS;
        }
        char ac[] = _charArray(i);
        ArrayList arraylist = _segments;
        int j = 0;
        if (arraylist != null)
        {
            int k = 0;
            for (int l = _segments.size(); k < l; k++)
            {
                char ac1[] = (char[])(char[])_segments.get(k);
                int i1 = ac1.length;
                System.arraycopy(ac1, 0, ac, j, i1);
                j += i1;
            }

        }
        System.arraycopy(_currentSegment, 0, ac, j, _currentSize);
        return ac;
    }

    private final void clearSegments()
    {
        _hasSegments = false;
        _segments.clear();
        _segmentSize = 0;
        _currentSize = 0;
    }

    private void expand(int i)
    {
        if (_segments == null)
        {
            _segments = new ArrayList();
        }
        char ac[] = _currentSegment;
        _hasSegments = true;
        _segments.add(ac);
        _segmentSize = _segmentSize + ac.length;
        int j = ac.length;
        int k = j >> 1;
        if (k < i)
        {
            k = i;
        }
        char ac1[] = _charArray(Math.min(0x40000, j + k));
        _currentSize = 0;
        _currentSegment = ac1;
    }

    private final char[] findBuffer(int i)
    {
        if (_allocator != null)
        {
            return _allocator.allocCharBuffer(BufferRecycler.CharBufferType.TEXT_BUFFER, i);
        } else
        {
            return new char[Math.max(i, 1000)];
        }
    }

    private void unshare(int i)
    {
        int j = _inputLen;
        _inputLen = 0;
        char ac[] = _inputBuffer;
        _inputBuffer = null;
        int k = _inputStart;
        _inputStart = -1;
        int l = j + i;
        if (_currentSegment == null || l > _currentSegment.length)
        {
            _currentSegment = findBuffer(l);
        }
        if (j > 0)
        {
            System.arraycopy(ac, k, _currentSegment, 0, j);
        }
        _segmentSize = 0;
        _currentSize = j;
    }

    public void append(char c)
    {
        if (_inputStart >= 0)
        {
            unshare(16);
        }
        _resultString = null;
        _resultArray = null;
        char ac[] = _currentSegment;
        if (_currentSize >= ac.length)
        {
            expand(1);
            ac = _currentSegment;
        }
        int i = _currentSize;
        _currentSize = i + 1;
        ac[i] = c;
    }

    public void append(String s, int i, int j)
    {
        if (_inputStart >= 0)
        {
            unshare(j);
        }
        _resultString = null;
        _resultArray = null;
        char ac[] = _currentSegment;
        int k = ac.length - _currentSize;
        if (k >= j)
        {
            s.getChars(i, i + j, ac, _currentSize);
            _currentSize = j + _currentSize;
            return;
        }
        if (k > 0)
        {
            s.getChars(i, i + k, ac, _currentSize);
            j -= k;
            i += k;
        }
        expand(j);
        s.getChars(i, i + j, _currentSegment, 0);
        _currentSize = j;
    }

    public void append(char ac[], int i, int j)
    {
        if (_inputStart >= 0)
        {
            unshare(j);
        }
        _resultString = null;
        _resultArray = null;
        char ac1[] = _currentSegment;
        int k = ac1.length - _currentSize;
        if (k >= j)
        {
            System.arraycopy(ac, i, ac1, _currentSize, j);
            _currentSize = j + _currentSize;
            return;
        }
        if (k > 0)
        {
            System.arraycopy(ac, i, ac1, _currentSize, k);
            i += k;
            j -= k;
        }
        expand(j);
        System.arraycopy(ac, i, _currentSegment, 0, j);
        _currentSize = j;
    }

    public char[] contentsAsArray()
    {
        char ac[] = _resultArray;
        if (ac == null)
        {
            ac = buildResultArray();
            _resultArray = ac;
        }
        return ac;
    }

    public BigDecimal contentsAsDecimal()
        throws NumberFormatException
    {
        if (_resultArray != null)
        {
            return new BigDecimal(_resultArray);
        }
        if (_inputStart >= 0)
        {
            return new BigDecimal(_inputBuffer, _inputStart, _inputLen);
        }
        if (_segmentSize == 0)
        {
            return new BigDecimal(_currentSegment, 0, _currentSize);
        } else
        {
            return new BigDecimal(contentsAsArray());
        }
    }

    public double contentsAsDouble()
        throws NumberFormatException
    {
        return NumberInput.parseDouble(contentsAsString());
    }

    public String contentsAsString()
    {
        if (_resultString == null)
        {
            if (_resultArray != null)
            {
                _resultString = new String(_resultArray);
            } else
            if (_inputStart >= 0)
            {
                if (_inputLen < 1)
                {
                    _resultString = "";
                    return "";
                }
                _resultString = new String(_inputBuffer, _inputStart, _inputLen);
            } else
            {
                int i = _segmentSize;
                int j = _currentSize;
                if (i == 0)
                {
                    String s;
                    if (j == 0)
                    {
                        s = "";
                    } else
                    {
                        s = new String(_currentSegment, 0, j);
                    }
                    _resultString = s;
                } else
                {
                    StringBuilder stringbuilder = new StringBuilder(i + j);
                    if (_segments != null)
                    {
                        int k = 0;
                        for (int l = _segments.size(); k < l; k++)
                        {
                            char ac[] = (char[])_segments.get(k);
                            stringbuilder.append(ac, 0, ac.length);
                        }

                    }
                    stringbuilder.append(_currentSegment, 0, _currentSize);
                    _resultString = stringbuilder.toString();
                }
            }
        }
        return _resultString;
    }

    public final char[] emptyAndGetCurrentSegment()
    {
        _inputStart = -1;
        _currentSize = 0;
        _inputLen = 0;
        _inputBuffer = null;
        _resultString = null;
        _resultArray = null;
        if (_hasSegments)
        {
            clearSegments();
        }
        char ac[] = _currentSegment;
        if (ac == null)
        {
            ac = findBuffer(0);
            _currentSegment = ac;
        }
        return ac;
    }

    public void ensureNotShared()
    {
        if (_inputStart >= 0)
        {
            unshare(16);
        }
    }

    public char[] expandCurrentSegment()
    {
        char ac[] = _currentSegment;
        int i = ac.length;
        int j;
        if (i == 0x40000)
        {
            j = 0x40001;
        } else
        {
            j = Math.min(0x40000, i + (i >> 1));
        }
        _currentSegment = _charArray(j);
        System.arraycopy(ac, 0, _currentSegment, 0, i);
        return _currentSegment;
    }

    public char[] finishCurrentSegment()
    {
        if (_segments == null)
        {
            _segments = new ArrayList();
        }
        _hasSegments = true;
        _segments.add(_currentSegment);
        int i = _currentSegment.length;
        _segmentSize = i + _segmentSize;
        char ac[] = _charArray(Math.min(i + (i >> 1), 0x40000));
        _currentSize = 0;
        _currentSegment = ac;
        return ac;
    }

    public char[] getCurrentSegment()
    {
        if (_inputStart < 0) goto _L2; else goto _L1
_L1:
        unshare(1);
_L4:
        return _currentSegment;
_L2:
        char ac[] = _currentSegment;
        if (ac == null)
        {
            _currentSegment = findBuffer(0);
        } else
        if (_currentSize >= ac.length)
        {
            expand(1);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public int getCurrentSegmentSize()
    {
        return _currentSize;
    }

    public char[] getTextBuffer()
    {
        if (_inputStart >= 0)
        {
            return _inputBuffer;
        }
        if (!_hasSegments)
        {
            return _currentSegment;
        } else
        {
            return contentsAsArray();
        }
    }

    public int getTextOffset()
    {
        if (_inputStart >= 0)
        {
            return _inputStart;
        } else
        {
            return 0;
        }
    }

    public void releaseBuffers()
    {
        if (_allocator == null)
        {
            resetWithEmpty();
        } else
        if (_currentSegment != null)
        {
            resetWithEmpty();
            char ac[] = _currentSegment;
            _currentSegment = null;
            _allocator.releaseCharBuffer(BufferRecycler.CharBufferType.TEXT_BUFFER, ac);
            return;
        }
    }

    public void resetWithCopy(char ac[], int i, int j)
    {
        _inputBuffer = null;
        _inputStart = -1;
        _inputLen = 0;
        _resultString = null;
        _resultArray = null;
        if (!_hasSegments) goto _L2; else goto _L1
_L1:
        clearSegments();
_L4:
        _segmentSize = 0;
        _currentSize = 0;
        append(ac, i, j);
        return;
_L2:
        if (_currentSegment == null)
        {
            _currentSegment = findBuffer(j);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void resetWithEmpty()
    {
        _inputStart = -1;
        _currentSize = 0;
        _inputLen = 0;
        _inputBuffer = null;
        _resultString = null;
        _resultArray = null;
        if (_hasSegments)
        {
            clearSegments();
        }
    }

    public void resetWithShared(char ac[], int i, int j)
    {
        _resultString = null;
        _resultArray = null;
        _inputBuffer = ac;
        _inputStart = i;
        _inputLen = j;
        if (_hasSegments)
        {
            clearSegments();
        }
    }

    public void resetWithString(String s)
    {
        _inputBuffer = null;
        _inputStart = -1;
        _inputLen = 0;
        _resultString = s;
        _resultArray = null;
        if (_hasSegments)
        {
            clearSegments();
        }
        _currentSize = 0;
    }

    public void setCurrentLength(int i)
    {
        _currentSize = i;
    }

    public int size()
    {
        if (_inputStart >= 0)
        {
            return _inputLen;
        } else
        {
            return _segmentSize + _currentSize;
        }
    }

    public String toString()
    {
        return contentsAsString();
    }

}
