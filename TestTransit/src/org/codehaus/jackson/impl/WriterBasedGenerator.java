// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.impl;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.PrettyPrinter;
import org.codehaus.jackson.SerializableString;
import org.codehaus.jackson.io.CharacterEscapes;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.io.NumberOutput;
import org.codehaus.jackson.io.SerializedString;
import org.codehaus.jackson.util.CharTypes;

// Referenced classes of package org.codehaus.jackson.impl:
//            JsonGeneratorBase, JsonWriteContext

public final class WriterBasedGenerator extends JsonGeneratorBase
{

    protected static final char HEX_CHARS[] = CharTypes.copyHexChars();
    protected static final int SHORT_WRITE = 32;
    protected static final int sOutputEscapes[] = CharTypes.get7BitOutputEscapes();
    protected CharacterEscapes _characterEscapes;
    protected SerializableString _currentEscape;
    protected char _entityBuffer[];
    protected final IOContext _ioContext;
    protected int _maximumNonEscapedChar;
    protected char _outputBuffer[];
    protected int _outputEnd;
    protected int _outputEscapes[];
    protected int _outputHead;
    protected int _outputTail;
    protected final Writer _writer;

    public WriterBasedGenerator(IOContext iocontext, int i, ObjectCodec objectcodec, Writer writer)
    {
        super(i, objectcodec);
        _outputEscapes = sOutputEscapes;
        _outputHead = 0;
        _outputTail = 0;
        _ioContext = iocontext;
        _writer = writer;
        _outputBuffer = iocontext.allocConcatBuffer();
        _outputEnd = _outputBuffer.length;
        if (isEnabled(org.codehaus.jackson.JsonGenerator.Feature.ESCAPE_NON_ASCII))
        {
            setHighestNonEscapedChar(127);
        }
    }

    private char[] _allocateEntityBuffer()
    {
        char ac[] = new char[14];
        ac[0] = '\\';
        ac[2] = '\\';
        ac[3] = 'u';
        ac[4] = '0';
        ac[5] = '0';
        ac[8] = '\\';
        ac[9] = 'u';
        _entityBuffer = ac;
        return ac;
    }

    private final void _appendCharacterEscape(char c, int i)
        throws IOException, JsonGenerationException
    {
        if (i >= 0)
        {
            if (2 + _outputTail > _outputEnd)
            {
                _flushBuffer();
            }
            char ac1[] = _outputBuffer;
            int k2 = _outputTail;
            _outputTail = k2 + 1;
            ac1[k2] = '\\';
            char ac2[] = _outputBuffer;
            int l2 = _outputTail;
            _outputTail = l2 + 1;
            ac2[l2] = (char)i;
            return;
        }
        if (i != -2)
        {
            if (2 + _outputTail > _outputEnd)
            {
                _flushBuffer();
            }
            int k = _outputTail;
            char ac[] = _outputBuffer;
            int l = k + 1;
            ac[k] = '\\';
            int i1 = l + 1;
            ac[l] = 'u';
            int k1;
            int l1;
            if (c > '\377')
            {
                int i2 = 0xff & c >> 8;
                int j2 = i1 + 1;
                ac[i1] = HEX_CHARS[i2 >> 4];
                k1 = j2 + 1;
                ac[j2] = HEX_CHARS[i2 & 0xf];
                c &= '\377';
            } else
            {
                int j1 = i1 + 1;
                ac[i1] = '0';
                k1 = j1 + 1;
                ac[j1] = '0';
            }
            l1 = k1 + 1;
            ac[k1] = HEX_CHARS[c >> 4];
            ac[l1] = HEX_CHARS[c & 0xf];
            _outputTail = l1;
            return;
        }
        String s;
        int j;
        if (_currentEscape == null)
        {
            s = _characterEscapes.getEscapeSequence(c).getValue();
        } else
        {
            s = _currentEscape.getValue();
            _currentEscape = null;
        }
        j = s.length();
        if (j + _outputTail > _outputEnd)
        {
            _flushBuffer();
            if (j > _outputEnd)
            {
                _writer.write(s);
                return;
            }
        }
        s.getChars(0, j, _outputBuffer, _outputTail);
        _outputTail = j + _outputTail;
    }

    private final int _prependOrWriteCharacterEscape(char ac[], int i, int j, char c, int k)
        throws IOException, JsonGenerationException
    {
        if (k >= 0)
        {
            if (i > 1 && i < j)
            {
                i -= 2;
                ac[i] = '\\';
                ac[i + 1] = (char)k;
            } else
            {
                char ac2[] = _entityBuffer;
                if (ac2 == null)
                {
                    ac2 = _allocateEntityBuffer();
                }
                ac2[1] = (char)k;
                _writer.write(ac2, 0, 2);
            }
            return i;
        }
        if (k != -2)
        {
            if (i > 5 && i < j)
            {
                int k1 = i - 6;
                int l1 = k1 + 1;
                ac[k1] = '\\';
                int i2 = l1 + 1;
                ac[l1] = 'u';
                int k2;
                int l2;
                if (c > '\377')
                {
                    int i3 = 0xff & c >> 8;
                    int j3 = i2 + 1;
                    ac[i2] = HEX_CHARS[i3 >> 4];
                    k2 = j3 + 1;
                    ac[j3] = HEX_CHARS[i3 & 0xf];
                    c &= '\377';
                } else
                {
                    int j2 = i2 + 1;
                    ac[i2] = '0';
                    k2 = j2 + 1;
                    ac[j2] = '0';
                }
                l2 = k2 + 1;
                ac[k2] = HEX_CHARS[c >> 4];
                ac[l2] = HEX_CHARS[c & 0xf];
                i = l2 - 5;
            } else
            {
                char ac1[] = _entityBuffer;
                if (ac1 == null)
                {
                    ac1 = _allocateEntityBuffer();
                }
                _outputHead = _outputTail;
                if (c > '\377')
                {
                    int i1 = 0xff & c >> 8;
                    int j1 = c & 0xff;
                    ac1[10] = HEX_CHARS[i1 >> 4];
                    ac1[11] = HEX_CHARS[i1 & 0xf];
                    ac1[12] = HEX_CHARS[j1 >> 4];
                    ac1[13] = HEX_CHARS[j1 & 0xf];
                    _writer.write(ac1, 8, 6);
                } else
                {
                    ac1[6] = HEX_CHARS[c >> 4];
                    ac1[7] = HEX_CHARS[c & 0xf];
                    _writer.write(ac1, 2, 6);
                }
            }
            return i;
        }
        String s;
        int l;
        if (_currentEscape == null)
        {
            s = _characterEscapes.getEscapeSequence(c).getValue();
        } else
        {
            s = _currentEscape.getValue();
            _currentEscape = null;
        }
        l = s.length();
        if (i >= l && i < j)
        {
            i -= l;
            s.getChars(0, l, ac, i);
        } else
        {
            _writer.write(s);
        }
        return i;
    }

    private final void _prependOrWriteCharacterEscape(char c, int i)
        throws IOException, JsonGenerationException
    {
        if (i >= 0)
        {
            if (_outputTail >= 2)
            {
                int i3 = -2 + _outputTail;
                _outputHead = i3;
                char ac3[] = _outputBuffer;
                int j3 = i3 + 1;
                ac3[i3] = '\\';
                _outputBuffer[j3] = (char)i;
                return;
            }
            char ac2[] = _entityBuffer;
            if (ac2 == null)
            {
                ac2 = _allocateEntityBuffer();
            }
            _outputHead = _outputTail;
            ac2[1] = (char)i;
            _writer.write(ac2, 0, 2);
            return;
        }
        if (i != -2)
        {
            if (_outputTail >= 6)
            {
                char ac1[] = _outputBuffer;
                int j1 = -6 + _outputTail;
                _outputHead = j1;
                ac1[j1] = '\\';
                int k1 = j1 + 1;
                ac1[k1] = 'u';
                int i2;
                int j2;
                if (c > '\377')
                {
                    int k2 = 0xff & c >> 8;
                    int l2 = k1 + 1;
                    ac1[l2] = HEX_CHARS[k2 >> 4];
                    i2 = l2 + 1;
                    ac1[i2] = HEX_CHARS[k2 & 0xf];
                    c &= '\377';
                } else
                {
                    int l1 = k1 + 1;
                    ac1[l1] = '0';
                    i2 = l1 + 1;
                    ac1[i2] = '0';
                }
                j2 = i2 + 1;
                ac1[j2] = HEX_CHARS[c >> 4];
                ac1[j2 + 1] = HEX_CHARS[c & 0xf];
                return;
            }
            char ac[] = _entityBuffer;
            if (ac == null)
            {
                ac = _allocateEntityBuffer();
            }
            _outputHead = _outputTail;
            if (c > '\377')
            {
                int l = 0xff & c >> 8;
                int i1 = c & 0xff;
                ac[10] = HEX_CHARS[l >> 4];
                ac[11] = HEX_CHARS[l & 0xf];
                ac[12] = HEX_CHARS[i1 >> 4];
                ac[13] = HEX_CHARS[i1 & 0xf];
                _writer.write(ac, 8, 6);
                return;
            } else
            {
                ac[6] = HEX_CHARS[c >> 4];
                ac[7] = HEX_CHARS[c & 0xf];
                _writer.write(ac, 2, 6);
                return;
            }
        }
        String s;
        int j;
        if (_currentEscape == null)
        {
            s = _characterEscapes.getEscapeSequence(c).getValue();
        } else
        {
            s = _currentEscape.getValue();
            _currentEscape = null;
        }
        j = s.length();
        if (_outputTail >= j)
        {
            int k = _outputTail - j;
            _outputHead = k;
            s.getChars(0, j, _outputBuffer, k);
            return;
        } else
        {
            _outputHead = _outputTail;
            _writer.write(s);
            return;
        }
    }

    private void _writeLongString(String s)
        throws IOException, JsonGenerationException
    {
        _flushBuffer();
        int i = s.length();
        int j = 0;
        do
        {
            int k = _outputEnd;
            int l;
            if (j + k > i)
            {
                l = i - j;
            } else
            {
                l = k;
            }
            s.getChars(j, j + l, _outputBuffer, 0);
            if (_characterEscapes != null)
            {
                _writeSegmentCustom(l);
            } else
            if (_maximumNonEscapedChar != 0)
            {
                _writeSegmentASCII(l, _maximumNonEscapedChar);
            } else
            {
                _writeSegment(l);
            }
            j += l;
        } while (j < i);
    }

    private final void _writeNull()
        throws IOException
    {
        if (4 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        int i = _outputTail;
        char ac[] = _outputBuffer;
        ac[i] = 'n';
        int j = i + 1;
        ac[j] = 'u';
        int k = j + 1;
        ac[k] = 'l';
        int l = k + 1;
        ac[l] = 'l';
        _outputTail = l + 1;
    }

    private final void _writeQuotedInt(int i)
        throws IOException
    {
        if (13 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        char ac[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        ac[j] = '"';
        _outputTail = NumberOutput.outputInt(i, _outputBuffer, _outputTail);
        char ac1[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        ac1[k] = '"';
    }

    private final void _writeQuotedLong(long l)
        throws IOException
    {
        if (23 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        char ac[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        ac[i] = '"';
        _outputTail = NumberOutput.outputLong(l, _outputBuffer, _outputTail);
        char ac1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        ac1[j] = '"';
    }

    private final void _writeQuotedRaw(Object obj)
        throws IOException
    {
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        char ac[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        ac[i] = '"';
        writeRaw(obj.toString());
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        char ac1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        ac1[j] = '"';
    }

    private final void _writeSegment(int i)
        throws IOException, JsonGenerationException
    {
        int ai[];
        int j;
        int k;
        int l;
        ai = _outputEscapes;
        j = ai.length;
        k = 0;
        l = 0;
_L7:
        if (k >= i) goto _L2; else goto _L1
_L1:
        char c;
        c = _outputBuffer[k];
        continue; /* Loop/switch isn't completed */
_L5:
        int i1 = k - l;
        if (i1 <= 0) goto _L4; else goto _L3
_L3:
        _writer.write(_outputBuffer, l, i1);
        if (k < i) goto _L4; else goto _L2
_L2:
        return;
        if ((c >= j || ai[c] == 0) && ++k < i) goto _L1; else goto _L5
_L4:
        k++;
        l = _prependOrWriteCharacterEscape(_outputBuffer, k, i, c, ai[c]);
        if (true) goto _L7; else goto _L6
_L6:
    }

    private final void _writeSegmentASCII(int i, int j)
        throws IOException, JsonGenerationException
    {
        int ai[];
        int k;
        int l;
        int i1;
        int j1;
        ai = _outputEscapes;
        k = Math.min(ai.length, 1 + _maximumNonEscapedChar);
        l = 0;
        i1 = 0;
        j1 = 0;
_L11:
        if (l >= i) goto _L2; else goto _L1
_L1:
        char c = _outputBuffer[l];
        if (c >= k) goto _L4; else goto _L3
_L3:
        i1 = ai[c];
        if (i1 == 0) goto _L6; else goto _L5
_L5:
        int k1 = l - j1;
        if (k1 <= 0) goto _L8; else goto _L7
_L7:
        _writer.write(_outputBuffer, j1, k1);
        if (l < i) goto _L8; else goto _L2
_L2:
        return;
_L4:
        if (c <= j) goto _L6; else goto _L9
_L9:
        i1 = -1;
          goto _L5
_L6:
        if (++l < i) goto _L1; else goto _L5
_L8:
        l++;
        j1 = _prependOrWriteCharacterEscape(_outputBuffer, l, i, c, i1);
        if (true) goto _L11; else goto _L10
_L10:
    }

    private final void _writeSegmentCustom(int i)
        throws IOException, JsonGenerationException
    {
        int j;
        CharacterEscapes characterescapes;
        int l;
        int i1;
        int j1;
        char c;
        int ai[] = _outputEscapes;
        int k;
        int k1;
        if (_maximumNonEscapedChar < 1)
        {
            j = 65535;
        } else
        {
            j = _maximumNonEscapedChar;
        }
        k = Math.min(ai.length, 1 + _maximumNonEscapedChar);
        characterescapes = _characterEscapes;
        l = 0;
        i1 = 0;
        j1 = 0;
_L11:
        if (l >= i) goto _L2; else goto _L1
_L1:
        c = _outputBuffer[l];
        if (c >= k) goto _L4; else goto _L3
_L3:
        i1 = ai[c];
        if (i1 == 0) goto _L6; else goto _L5
_L5:
        k1 = l - j1;
        if (k1 <= 0) goto _L8; else goto _L7
_L7:
        _writer.write(_outputBuffer, j1, k1);
        if (l < i) goto _L8; else goto _L2
_L2:
        return;
_L4:
label0:
        {
            if (c <= j)
            {
                break label0;
            }
            i1 = -1;
        }
          goto _L5
        SerializableString serializablestring;
        serializablestring = characterescapes.getEscapeSequence(c);
        _currentEscape = serializablestring;
        if (serializablestring == null) goto _L6; else goto _L9
_L9:
        i1 = -2;
          goto _L5
_L6:
        if (++l < i) goto _L1; else goto _L5
_L8:
        l++;
        j1 = _prependOrWriteCharacterEscape(_outputBuffer, l, i, c, i1);
        if (true) goto _L11; else goto _L10
_L10:
    }

    private void _writeString(String s)
        throws IOException, JsonGenerationException
    {
        int i = s.length();
        if (i > _outputEnd)
        {
            _writeLongString(s);
            return;
        }
        if (i + _outputTail > _outputEnd)
        {
            _flushBuffer();
        }
        s.getChars(0, i, _outputBuffer, _outputTail);
        if (_characterEscapes != null)
        {
            _writeStringCustom(i);
            return;
        }
        if (_maximumNonEscapedChar != 0)
        {
            _writeStringASCII(i, _maximumNonEscapedChar);
            return;
        } else
        {
            _writeString2(i);
            return;
        }
    }

    private final void _writeString(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        if (_characterEscapes == null) goto _L2; else goto _L1
_L1:
        _writeStringCustom(ac, i, j);
_L9:
        return;
_L2:
        int k;
        int ai[];
        int l;
        if (_maximumNonEscapedChar != 0)
        {
            _writeStringASCII(ac, i, j, _maximumNonEscapedChar);
            return;
        }
        k = j + i;
        ai = _outputEscapes;
        l = ai.length;
_L7:
        int i1;
        if (i >= k)
        {
            continue; /* Loop/switch isn't completed */
        }
        i1 = i;
_L4:
        int j1;
        char c = ac[i];
        if (c >= l || ai[c] == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        j1 = i;
_L5:
        int k1 = j1 - i1;
        if (k1 < 32)
        {
            if (k1 + _outputTail > _outputEnd)
            {
                _flushBuffer();
            }
            if (k1 > 0)
            {
                System.arraycopy(ac, i1, _outputBuffer, _outputTail, k1);
                _outputTail = k1 + _outputTail;
            }
        } else
        {
            _flushBuffer();
            _writer.write(ac, i1, k1);
        }
        if (j1 >= k)
        {
            j1;
            return;
        }
        break MISSING_BLOCK_LABEL_191;
        if (++i < k) goto _L4; else goto _L3
_L3:
        j1 = i;
          goto _L5
        i = j1 + 1;
        char c1 = ac[j1];
        _appendCharacterEscape(c1, ai[c1]);
        if (true) goto _L7; else goto _L6
_L6:
        if (true) goto _L9; else goto _L8
_L8:
    }

    private void _writeString2(int i)
        throws IOException, JsonGenerationException
    {
        int j = i + _outputTail;
        int ai[] = _outputEscapes;
        int k = ai.length;
label0:
        do
        {
            int l;
            if (_outputTail < j)
            {
                do
                {
                    char c = _outputBuffer[_outputTail];
                    if (c < k && ai[c] != 0)
                    {
                        int i1 = _outputTail - _outputHead;
                        if (i1 > 0)
                        {
                            _writer.write(_outputBuffer, _outputHead, i1);
                        }
                        char ac[] = _outputBuffer;
                        int j1 = _outputTail;
                        _outputTail = j1 + 1;
                        char c1 = ac[j1];
                        _prependOrWriteCharacterEscape(c1, ai[c1]);
                        continue label0;
                    }
                    l = 1 + _outputTail;
                    _outputTail = l;
                } while (l < j);
            }
            return;
        } while (true);
    }

    private void _writeStringASCII(int i, int j)
        throws IOException, JsonGenerationException
    {
        int k;
        int ai[];
        int l;
        k = i + _outputTail;
        ai = _outputEscapes;
        l = Math.min(ai.length, 1 + _maximumNonEscapedChar);
_L8:
        if (_outputTail >= k) goto _L2; else goto _L1
_L1:
        char c = _outputBuffer[_outputTail];
        if (c >= l) goto _L4; else goto _L3
_L3:
        int j1 = ai[c];
        if (j1 == 0) goto _L6; else goto _L5
_L5:
        int k1 = _outputTail - _outputHead;
        if (k1 > 0)
        {
            _writer.write(_outputBuffer, _outputHead, k1);
        }
        _outputTail = 1 + _outputTail;
        _prependOrWriteCharacterEscape(c, j1);
        continue; /* Loop/switch isn't completed */
_L4:
        if (c <= j)
        {
            break; /* Loop/switch isn't completed */
        }
        j1 = -1;
        if (true) goto _L5; else goto _L6
_L6:
        int i1;
        i1 = 1 + _outputTail;
        _outputTail = i1;
        if (i1 < k) goto _L1; else goto _L2
_L2:
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private final void _writeStringASCII(char ac[], int i, int j, int k)
        throws IOException, JsonGenerationException
    {
        int l;
        int ai[];
        int i1;
        int j1;
        l = j + i;
        ai = _outputEscapes;
        i1 = Math.min(ai.length, k + 1);
        j1 = 0;
_L11:
        if (i >= l) goto _L2; else goto _L1
_L1:
        int k1 = i;
_L9:
        char c = ac[i];
        if (c >= i1) goto _L4; else goto _L3
_L3:
        j1 = ai[c];
        if (j1 == 0) goto _L6; else goto _L5
_L5:
        int l1 = i - k1;
        if (l1 < 32)
        {
            if (l1 + _outputTail > _outputEnd)
            {
                _flushBuffer();
            }
            if (l1 > 0)
            {
                System.arraycopy(ac, k1, _outputBuffer, _outputTail, l1);
                _outputTail = l1 + _outputTail;
            }
        } else
        {
            _flushBuffer();
            _writer.write(ac, k1, l1);
        }
        if (i < l) goto _L7; else goto _L2
_L2:
        return;
_L4:
        if (c <= k) goto _L6; else goto _L8
_L8:
        j1 = -1;
          goto _L5
_L6:
        if (++i < l) goto _L9; else goto _L5
_L7:
        i++;
        _appendCharacterEscape(c, j1);
        if (true) goto _L11; else goto _L10
_L10:
    }

    private void _writeStringCustom(int i)
        throws IOException, JsonGenerationException
    {
        int j;
        int k;
        CharacterEscapes characterescapes;
        char c;
        int j1;
        j = i + _outputTail;
        int ai[] = _outputEscapes;
        int l;
        int k1;
        if (_maximumNonEscapedChar < 1)
        {
            k = 65535;
        } else
        {
            k = _maximumNonEscapedChar;
        }
        l = Math.min(ai.length, k + 1);
        characterescapes = _characterEscapes;
_L8:
        if (_outputTail >= j) goto _L2; else goto _L1
_L1:
        c = _outputBuffer[_outputTail];
        if (c >= l) goto _L4; else goto _L3
_L3:
        j1 = ai[c];
        if (j1 == 0) goto _L6; else goto _L5
_L5:
        k1 = _outputTail - _outputHead;
        if (k1 > 0)
        {
            _writer.write(_outputBuffer, _outputHead, k1);
        }
        _outputTail = 1 + _outputTail;
        _prependOrWriteCharacterEscape(c, j1);
        continue; /* Loop/switch isn't completed */
_L4:
        if (c > k)
        {
            j1 = -1;
            continue; /* Loop/switch isn't completed */
        }
        SerializableString serializablestring = characterescapes.getEscapeSequence(c);
        _currentEscape = serializablestring;
        if (serializablestring == null)
        {
            break; /* Loop/switch isn't completed */
        }
        j1 = -2;
        if (true) goto _L5; else goto _L6
_L6:
        int i1;
        i1 = 1 + _outputTail;
        _outputTail = i1;
        if (i1 < j) goto _L1; else goto _L2
_L2:
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private final void _writeStringCustom(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        int k;
        int l;
        CharacterEscapes characterescapes;
        int j1;
        int k1;
        char c;
        k = j + i;
        int ai[] = _outputEscapes;
        int i1;
        if (_maximumNonEscapedChar < 1)
        {
            l = 65535;
        } else
        {
            l = _maximumNonEscapedChar;
        }
        i1 = Math.min(ai.length, l + 1);
        characterescapes = _characterEscapes;
        j1 = 0;
_L11:
        if (i >= k) goto _L2; else goto _L1
_L1:
        k1 = i;
_L9:
        c = ac[i];
        if (c >= i1) goto _L4; else goto _L3
_L3:
        j1 = ai[c];
        if (j1 == 0) goto _L6; else goto _L5
_L5:
        int l1 = i - k1;
        SerializableString serializablestring;
        if (l1 < 32)
        {
            if (l1 + _outputTail > _outputEnd)
            {
                _flushBuffer();
            }
            if (l1 > 0)
            {
                System.arraycopy(ac, k1, _outputBuffer, _outputTail, l1);
                _outputTail = l1 + _outputTail;
            }
        } else
        {
            _flushBuffer();
            _writer.write(ac, k1, l1);
        }
        if (i < k) goto _L7; else goto _L2
_L2:
        return;
_L4:
label0:
        {
            if (c <= l)
            {
                break label0;
            }
            j1 = -1;
        }
          goto _L5
        serializablestring = characterescapes.getEscapeSequence(c);
        _currentEscape = serializablestring;
        if (serializablestring == null) goto _L6; else goto _L8
_L8:
        j1 = -2;
          goto _L5
_L6:
        if (++i < k) goto _L9; else goto _L5
_L7:
        i++;
        _appendCharacterEscape(c, j1);
        if (true) goto _L11; else goto _L10
_L10:
    }

    private void writeRawLong(String s)
        throws IOException, JsonGenerationException
    {
        int i = _outputEnd - _outputTail;
        s.getChars(0, i, _outputBuffer, _outputTail);
        _outputTail = i + _outputTail;
        _flushBuffer();
        int j = i;
        int k;
        int l;
        for (k = s.length() - i; k > _outputEnd; k -= l)
        {
            l = _outputEnd;
            s.getChars(j, j + l, _outputBuffer, 0);
            _outputHead = 0;
            _outputTail = l;
            _flushBuffer();
            j += l;
        }

        s.getChars(j, j + k, _outputBuffer, 0);
        _outputHead = 0;
        _outputTail = k;
    }

    protected final void _flushBuffer()
        throws IOException
    {
        int i = _outputTail - _outputHead;
        if (i > 0)
        {
            int j = _outputHead;
            _outputHead = 0;
            _outputTail = 0;
            _writer.write(_outputBuffer, j, i);
        }
    }

    protected void _releaseBuffers()
    {
        char ac[] = _outputBuffer;
        if (ac != null)
        {
            _outputBuffer = null;
            _ioContext.releaseConcatBuffer(ac);
        }
    }

    protected final void _verifyPrettyValueWrite(String s, int i)
        throws IOException, JsonGenerationException
    {
        i;
        JVM INSTR tableswitch 0 3: default 32
    //                   0 70
    //                   1 37
    //                   2 48
    //                   3 59;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        _cantHappen();
_L7:
        return;
_L3:
        _cfgPrettyPrinter.writeArrayValueSeparator(this);
        return;
_L4:
        _cfgPrettyPrinter.writeObjectFieldValueSeparator(this);
        return;
_L5:
        _cfgPrettyPrinter.writeRootValueSeparator(this);
        return;
_L2:
        if (_writeContext.inArray())
        {
            _cfgPrettyPrinter.beforeArrayValues(this);
            return;
        }
        if (_writeContext.inObject())
        {
            _cfgPrettyPrinter.beforeObjectEntries(this);
            return;
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    protected final void _verifyValueWrite(String s)
        throws IOException, JsonGenerationException
    {
        int i;
        i = _writeContext.writeValue();
        if (i == 5)
        {
            _reportError((new StringBuilder()).append("Can not ").append(s).append(", expecting field name").toString());
        }
        if (_cfgPrettyPrinter != null) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 1 3: default 76
    //                   1 77
    //                   2 116
    //                   3 122;
           goto _L3 _L4 _L5 _L6
_L3:
        return;
_L4:
        char c = ',';
_L7:
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        _outputBuffer[_outputTail] = c;
        _outputTail = 1 + _outputTail;
        return;
_L5:
        c = ':';
        continue; /* Loop/switch isn't completed */
_L6:
        c = ' ';
        if (true) goto _L7; else goto _L2
_L2:
        _verifyPrettyValueWrite(s, i);
        return;
    }

    protected void _writeBinary(Base64Variant base64variant, byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        int k = j - 3;
        int l = -6 + _outputEnd;
        int i1 = base64variant.getMaxLineLength() >> 2;
        int j1;
        int j3;
        for (j1 = i; j1 <= k; j1 = j3)
        {
            if (_outputTail > l)
            {
                _flushBuffer();
            }
            int j2 = j1 + 1;
            int k2 = abyte0[j1] << 8;
            int l2 = j2 + 1;
            int i3 = (k2 | 0xff & abyte0[j2]) << 8;
            j3 = l2 + 1;
            _outputTail = base64variant.encodeBase64Chunk(i3 | 0xff & abyte0[l2], _outputBuffer, _outputTail);
            if (--i1 <= 0)
            {
                char ac[] = _outputBuffer;
                int k3 = _outputTail;
                _outputTail = k3 + 1;
                ac[k3] = '\\';
                char ac1[] = _outputBuffer;
                int l3 = _outputTail;
                _outputTail = l3 + 1;
                ac1[l3] = 'n';
                i1 = base64variant.getMaxLineLength() >> 2;
            }
        }

        int k1 = j - j1;
        if (k1 > 0)
        {
            if (_outputTail > l)
            {
                _flushBuffer();
            }
            int l1 = j1 + 1;
            int i2 = abyte0[j1] << 16;
            if (k1 == 2)
            {
                int _tmp = l1 + 1;
                i2 |= (0xff & abyte0[l1]) << 8;
            }
            _outputTail = base64variant.encodeBase64Partial(i2, k1, _outputBuffer, _outputTail);
            return;
        } else
        {
            int _tmp1 = j1;
            return;
        }
    }

    protected void _writeFieldName(String s, boolean flag)
        throws IOException, JsonGenerationException
    {
        if (_cfgPrettyPrinter != null)
        {
            _writePPFieldName(s, flag);
            return;
        }
        if (1 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        if (flag)
        {
            char ac2[] = _outputBuffer;
            int k = _outputTail;
            _outputTail = k + 1;
            ac2[k] = ',';
        }
        if (!isEnabled(org.codehaus.jackson.JsonGenerator.Feature.QUOTE_FIELD_NAMES))
        {
            _writeString(s);
            return;
        }
        char ac[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        ac[i] = '"';
        _writeString(s);
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        char ac1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        ac1[j] = '"';
    }

    public void _writeFieldName(SerializableString serializablestring, boolean flag)
        throws IOException, JsonGenerationException
    {
        if (_cfgPrettyPrinter != null)
        {
            _writePPFieldName(serializablestring, flag);
            return;
        }
        if (1 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        if (flag)
        {
            char ac4[] = _outputBuffer;
            int i1 = _outputTail;
            _outputTail = i1 + 1;
            ac4[i1] = ',';
        }
        char ac[] = serializablestring.asQuotedChars();
        if (!isEnabled(org.codehaus.jackson.JsonGenerator.Feature.QUOTE_FIELD_NAMES))
        {
            writeRaw(ac, 0, ac.length);
            return;
        }
        char ac1[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        ac1[i] = '"';
        int j = ac.length;
        if (1 + (j + _outputTail) >= _outputEnd)
        {
            writeRaw(ac, 0, j);
            if (_outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            char ac3[] = _outputBuffer;
            int l = _outputTail;
            _outputTail = l + 1;
            ac3[l] = '"';
            return;
        } else
        {
            System.arraycopy(ac, 0, _outputBuffer, _outputTail, j);
            _outputTail = j + _outputTail;
            char ac2[] = _outputBuffer;
            int k = _outputTail;
            _outputTail = k + 1;
            ac2[k] = '"';
            return;
        }
    }

    protected final void _writePPFieldName(String s, boolean flag)
        throws IOException, JsonGenerationException
    {
        if (flag)
        {
            _cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else
        {
            _cfgPrettyPrinter.beforeObjectEntries(this);
        }
        if (isEnabled(org.codehaus.jackson.JsonGenerator.Feature.QUOTE_FIELD_NAMES))
        {
            if (_outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            char ac[] = _outputBuffer;
            int i = _outputTail;
            _outputTail = i + 1;
            ac[i] = '"';
            _writeString(s);
            if (_outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            char ac1[] = _outputBuffer;
            int j = _outputTail;
            _outputTail = j + 1;
            ac1[j] = '"';
            return;
        } else
        {
            _writeString(s);
            return;
        }
    }

    protected final void _writePPFieldName(SerializableString serializablestring, boolean flag)
        throws IOException, JsonGenerationException
    {
        char ac[];
        if (flag)
        {
            _cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else
        {
            _cfgPrettyPrinter.beforeObjectEntries(this);
        }
        ac = serializablestring.asQuotedChars();
        if (isEnabled(org.codehaus.jackson.JsonGenerator.Feature.QUOTE_FIELD_NAMES))
        {
            if (_outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            char ac1[] = _outputBuffer;
            int i = _outputTail;
            _outputTail = i + 1;
            ac1[i] = '"';
            writeRaw(ac, 0, ac.length);
            if (_outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            char ac2[] = _outputBuffer;
            int j = _outputTail;
            _outputTail = j + 1;
            ac2[j] = '"';
            return;
        } else
        {
            writeRaw(ac, 0, ac.length);
            return;
        }
    }

    public void close()
        throws IOException
    {
        super.close();
        if (_outputBuffer != null && isEnabled(org.codehaus.jackson.JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT))
        {
            do
            {
                JsonWriteContext jsonwritecontext = getOutputContext();
                if (jsonwritecontext.inArray())
                {
                    writeEndArray();
                    continue;
                }
                if (!jsonwritecontext.inObject())
                {
                    break;
                }
                writeEndObject();
            } while (true);
        }
        _flushBuffer();
        if (_ioContext.isResourceManaged() || isEnabled(org.codehaus.jackson.JsonGenerator.Feature.AUTO_CLOSE_TARGET))
        {
            _writer.close();
        } else
        {
            _writer.flush();
        }
        _releaseBuffers();
    }

    public final void flush()
        throws IOException
    {
        _flushBuffer();
        if (_writer != null && isEnabled(org.codehaus.jackson.JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM))
        {
            _writer.flush();
        }
    }

    public CharacterEscapes getCharacterEscapes()
    {
        return _characterEscapes;
    }

    public int getHighestEscapedChar()
    {
        return _maximumNonEscapedChar;
    }

    public Object getOutputTarget()
    {
        return _writer;
    }

    public JsonGenerator setCharacterEscapes(CharacterEscapes characterescapes)
    {
        _characterEscapes = characterescapes;
        if (characterescapes == null)
        {
            _outputEscapes = sOutputEscapes;
            return this;
        } else
        {
            _outputEscapes = characterescapes.getEscapeCodesForAscii();
            return this;
        }
    }

    public JsonGenerator setHighestNonEscapedChar(int i)
    {
        if (i < 0)
        {
            i = 0;
        }
        _maximumNonEscapedChar = i;
        return this;
    }

    public void writeBinary(Base64Variant base64variant, byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write binary value");
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        char ac[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        ac[k] = '"';
        _writeBinary(base64variant, abyte0, i, i + j);
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        char ac1[] = _outputBuffer;
        int l = _outputTail;
        _outputTail = l + 1;
        ac1[l] = '"';
    }

    public void writeBoolean(boolean flag)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write boolean value");
        if (5 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        int i = _outputTail;
        char ac[] = _outputBuffer;
        int i1;
        if (flag)
        {
            ac[i] = 't';
            int j1 = i + 1;
            ac[j1] = 'r';
            int k1 = j1 + 1;
            ac[k1] = 'u';
            i1 = k1 + 1;
            ac[i1] = 'e';
        } else
        {
            ac[i] = 'f';
            int j = i + 1;
            ac[j] = 'a';
            int k = j + 1;
            ac[k] = 'l';
            int l = k + 1;
            ac[l] = 's';
            i1 = l + 1;
            ac[i1] = 'e';
        }
        _outputTail = i1 + 1;
    }

    public final void writeEndArray()
        throws IOException, JsonGenerationException
    {
        if (!_writeContext.inArray())
        {
            _reportError((new StringBuilder()).append("Current context not an ARRAY but ").append(_writeContext.getTypeDesc()).toString());
        }
        if (_cfgPrettyPrinter != null)
        {
            _cfgPrettyPrinter.writeEndArray(this, _writeContext.getEntryCount());
        } else
        {
            if (_outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            char ac[] = _outputBuffer;
            int i = _outputTail;
            _outputTail = i + 1;
            ac[i] = ']';
        }
        _writeContext = _writeContext.getParent();
    }

    public final void writeEndObject()
        throws IOException, JsonGenerationException
    {
        if (!_writeContext.inObject())
        {
            _reportError((new StringBuilder()).append("Current context not an object but ").append(_writeContext.getTypeDesc()).toString());
        }
        _writeContext = _writeContext.getParent();
        if (_cfgPrettyPrinter != null)
        {
            _cfgPrettyPrinter.writeEndObject(this, _writeContext.getEntryCount());
            return;
        }
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        char ac[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        ac[i] = '}';
    }

    public final void writeFieldName(String s)
        throws IOException, JsonGenerationException
    {
        boolean flag = true;
        int i = _writeContext.writeFieldName(s);
        if (i == 4)
        {
            _reportError("Can not write a field name, expecting a value");
        }
        if (i != flag)
        {
            flag = false;
        }
        _writeFieldName(s, flag);
    }

    public final void writeFieldName(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        boolean flag = true;
        int i = _writeContext.writeFieldName(serializablestring.getValue());
        if (i == 4)
        {
            _reportError("Can not write a field name, expecting a value");
        }
        if (i != flag)
        {
            flag = false;
        }
        _writeFieldName(serializablestring, flag);
    }

    public final void writeFieldName(SerializedString serializedstring)
        throws IOException, JsonGenerationException
    {
        boolean flag = true;
        int i = _writeContext.writeFieldName(serializedstring.getValue());
        if (i == 4)
        {
            _reportError("Can not write a field name, expecting a value");
        }
        if (i != flag)
        {
            flag = false;
        }
        _writeFieldName(serializedstring, flag);
    }

    public void writeNull()
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write null value");
        _writeNull();
    }

    public void writeNumber(double d)
        throws IOException, JsonGenerationException
    {
        if (_cfgNumbersAsStrings || (Double.isNaN(d) || Double.isInfinite(d)) && isEnabled(org.codehaus.jackson.JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))
        {
            writeString(String.valueOf(d));
            return;
        } else
        {
            _verifyValueWrite("write number");
            writeRaw(String.valueOf(d));
            return;
        }
    }

    public void writeNumber(float f)
        throws IOException, JsonGenerationException
    {
        if (_cfgNumbersAsStrings || (Float.isNaN(f) || Float.isInfinite(f)) && isEnabled(org.codehaus.jackson.JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))
        {
            writeString(String.valueOf(f));
            return;
        } else
        {
            _verifyValueWrite("write number");
            writeRaw(String.valueOf(f));
            return;
        }
    }

    public void writeNumber(int i)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write number");
        if (11 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        if (_cfgNumbersAsStrings)
        {
            _writeQuotedInt(i);
            return;
        } else
        {
            _outputTail = NumberOutput.outputInt(i, _outputBuffer, _outputTail);
            return;
        }
    }

    public void writeNumber(long l)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write number");
        if (_cfgNumbersAsStrings)
        {
            _writeQuotedLong(l);
            return;
        }
        if (21 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        _outputTail = NumberOutput.outputLong(l, _outputBuffer, _outputTail);
    }

    public void writeNumber(String s)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write number");
        if (_cfgNumbersAsStrings)
        {
            _writeQuotedRaw(s);
            return;
        } else
        {
            writeRaw(s);
            return;
        }
    }

    public void writeNumber(BigDecimal bigdecimal)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write number");
        if (bigdecimal == null)
        {
            _writeNull();
            return;
        }
        if (_cfgNumbersAsStrings)
        {
            _writeQuotedRaw(bigdecimal);
            return;
        } else
        {
            writeRaw(bigdecimal.toString());
            return;
        }
    }

    public void writeNumber(BigInteger biginteger)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write number");
        if (biginteger == null)
        {
            _writeNull();
            return;
        }
        if (_cfgNumbersAsStrings)
        {
            _writeQuotedRaw(biginteger);
            return;
        } else
        {
            writeRaw(biginteger.toString());
            return;
        }
    }

    public void writeRaw(char c)
        throws IOException, JsonGenerationException
    {
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        char ac[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        ac[i] = c;
    }

    public void writeRaw(String s)
        throws IOException, JsonGenerationException
    {
        int i = s.length();
        int j = _outputEnd - _outputTail;
        if (j == 0)
        {
            _flushBuffer();
            j = _outputEnd - _outputTail;
        }
        if (j >= i)
        {
            s.getChars(0, i, _outputBuffer, _outputTail);
            _outputTail = i + _outputTail;
            return;
        } else
        {
            writeRawLong(s);
            return;
        }
    }

    public void writeRaw(String s, int i, int j)
        throws IOException, JsonGenerationException
    {
        int k = _outputEnd - _outputTail;
        if (k < j)
        {
            _flushBuffer();
            k = _outputEnd - _outputTail;
        }
        if (k >= j)
        {
            s.getChars(i, i + j, _outputBuffer, _outputTail);
            _outputTail = j + _outputTail;
            return;
        } else
        {
            writeRawLong(s.substring(i, i + j));
            return;
        }
    }

    public void writeRaw(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        if (j < 32)
        {
            if (j > _outputEnd - _outputTail)
            {
                _flushBuffer();
            }
            System.arraycopy(ac, i, _outputBuffer, _outputTail, j);
            _outputTail = j + _outputTail;
            return;
        } else
        {
            _flushBuffer();
            _writer.write(ac, i, j);
            return;
        }
    }

    public void writeRawUTF8String(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public final void writeStartArray()
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("start an array");
        _writeContext = _writeContext.createChildArrayContext();
        if (_cfgPrettyPrinter != null)
        {
            _cfgPrettyPrinter.writeStartArray(this);
            return;
        }
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        char ac[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        ac[i] = '[';
    }

    public final void writeStartObject()
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("start an object");
        _writeContext = _writeContext.createChildObjectContext();
        if (_cfgPrettyPrinter != null)
        {
            _cfgPrettyPrinter.writeStartObject(this);
            return;
        }
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        char ac[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        ac[i] = '{';
    }

    public void writeString(String s)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write text value");
        if (s == null)
        {
            _writeNull();
            return;
        }
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        char ac[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        ac[i] = '"';
        _writeString(s);
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        char ac1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        ac1[j] = '"';
    }

    public final void writeString(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write text value");
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        char ac[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        ac[i] = '"';
        char ac1[] = serializablestring.asQuotedChars();
        int j = ac1.length;
        char ac2[];
        int k;
        if (j < 32)
        {
            if (j > _outputEnd - _outputTail)
            {
                _flushBuffer();
            }
            System.arraycopy(ac1, 0, _outputBuffer, _outputTail, j);
            _outputTail = j + _outputTail;
        } else
        {
            _flushBuffer();
            _writer.write(ac1, 0, j);
        }
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        ac2 = _outputBuffer;
        k = _outputTail;
        _outputTail = k + 1;
        ac2[k] = '"';
    }

    public void writeString(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write text value");
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        char ac1[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        ac1[k] = '"';
        _writeString(ac, i, j);
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        char ac2[] = _outputBuffer;
        int l = _outputTail;
        _outputTail = l + 1;
        ac2[l] = '"';
    }

    public final void writeStringField(String s, String s1)
        throws IOException, JsonGenerationException
    {
        writeFieldName(s);
        writeString(s1);
    }

    public void writeUTF8String(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

}
