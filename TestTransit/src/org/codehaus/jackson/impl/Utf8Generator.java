// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.impl;

import java.io.IOException;
import java.io.OutputStream;
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

public class Utf8Generator extends JsonGeneratorBase
{

    private static final byte BYTE_0 = 48;
    private static final byte BYTE_BACKSLASH = 92;
    private static final byte BYTE_COLON = 58;
    private static final byte BYTE_COMMA = 44;
    private static final byte BYTE_LBRACKET = 91;
    private static final byte BYTE_LCURLY = 123;
    private static final byte BYTE_QUOTE = 34;
    private static final byte BYTE_RBRACKET = 93;
    private static final byte BYTE_RCURLY = 125;
    private static final byte BYTE_SPACE = 32;
    private static final byte BYTE_u = 117;
    private static final byte FALSE_BYTES[] = {
        102, 97, 108, 115, 101
    };
    static final byte HEX_CHARS[] = CharTypes.copyHexBytes();
    private static final int MAX_BYTES_TO_BUFFER = 512;
    private static final byte NULL_BYTES[] = {
        110, 117, 108, 108
    };
    protected static final int SURR1_FIRST = 55296;
    protected static final int SURR1_LAST = 56319;
    protected static final int SURR2_FIRST = 56320;
    protected static final int SURR2_LAST = 57343;
    private static final byte TRUE_BYTES[] = {
        116, 114, 117, 101
    };
    protected static final int sOutputEscapes[] = CharTypes.get7BitOutputEscapes();
    protected boolean _bufferRecyclable;
    protected char _charBuffer[];
    protected final int _charBufferLength;
    protected CharacterEscapes _characterEscapes;
    protected byte _entityBuffer[];
    protected final IOContext _ioContext;
    protected int _maximumNonEscapedChar;
    protected byte _outputBuffer[];
    protected final int _outputEnd;
    protected int _outputEscapes[];
    protected final int _outputMaxContiguous;
    protected final OutputStream _outputStream;
    protected int _outputTail;

    public Utf8Generator(IOContext iocontext, int i, ObjectCodec objectcodec, OutputStream outputstream)
    {
        super(i, objectcodec);
        _outputEscapes = sOutputEscapes;
        _outputTail = 0;
        _ioContext = iocontext;
        _outputStream = outputstream;
        _bufferRecyclable = true;
        _outputBuffer = iocontext.allocWriteEncodingBuffer();
        _outputEnd = _outputBuffer.length;
        _outputMaxContiguous = _outputEnd >> 3;
        _charBuffer = iocontext.allocConcatBuffer();
        _charBufferLength = _charBuffer.length;
        if (isEnabled(org.codehaus.jackson.JsonGenerator.Feature.ESCAPE_NON_ASCII))
        {
            setHighestNonEscapedChar(127);
        }
    }

    public Utf8Generator(IOContext iocontext, int i, ObjectCodec objectcodec, OutputStream outputstream, byte abyte0[], int j, boolean flag)
    {
        super(i, objectcodec);
        _outputEscapes = sOutputEscapes;
        _outputTail = 0;
        _ioContext = iocontext;
        _outputStream = outputstream;
        _bufferRecyclable = flag;
        _outputTail = j;
        _outputBuffer = abyte0;
        _outputEnd = _outputBuffer.length;
        _outputMaxContiguous = _outputEnd >> 3;
        _charBuffer = iocontext.allocConcatBuffer();
        _charBufferLength = _charBuffer.length;
        if (isEnabled(org.codehaus.jackson.JsonGenerator.Feature.ESCAPE_NON_ASCII))
        {
            setHighestNonEscapedChar(127);
        }
    }

    private int _handleLongCustomEscape(byte abyte0[], int i, int j, byte abyte1[], int k)
        throws IOException, JsonGenerationException
    {
        int l = abyte1.length;
        if (i + l > j)
        {
            _outputTail = i;
            _flushBuffer();
            int i1 = _outputTail;
            if (l > abyte0.length)
            {
                _outputStream.write(abyte1, 0, l);
                return i1;
            }
            System.arraycopy(abyte1, 0, abyte0, i1, l);
            i = i1 + l;
        }
        if (i + k * 6 > j)
        {
            _flushBuffer();
            return _outputTail;
        } else
        {
            return i;
        }
    }

    private final int _outputMultiByteChar(int i, int j)
        throws IOException
    {
        byte abyte0[] = _outputBuffer;
        if (i >= 55296 && i <= 57343)
        {
            int j1 = j + 1;
            abyte0[j] = 92;
            int k1 = j1 + 1;
            abyte0[j1] = 117;
            int l1 = k1 + 1;
            abyte0[k1] = HEX_CHARS[0xf & i >> 12];
            int i2 = l1 + 1;
            abyte0[l1] = HEX_CHARS[0xf & i >> 8];
            int j2 = i2 + 1;
            abyte0[i2] = HEX_CHARS[0xf & i >> 4];
            int k2 = j2 + 1;
            abyte0[j2] = HEX_CHARS[i & 0xf];
            return k2;
        } else
        {
            int k = j + 1;
            abyte0[j] = (byte)(0xe0 | i >> 12);
            int l = k + 1;
            abyte0[k] = (byte)(0x80 | 0x3f & i >> 6);
            int i1 = l + 1;
            abyte0[l] = (byte)(0x80 | i & 0x3f);
            return i1;
        }
    }

    private final int _outputRawMultiByteChar(int i, char ac[], int j, int k)
        throws IOException
    {
        if (i >= 55296 && i <= 57343)
        {
            if (j >= k)
            {
                _reportError("Split surrogate on writeRaw() input (last character)");
            }
            _outputSurrogates(i, ac[j]);
            return j + 1;
        } else
        {
            byte abyte0[] = _outputBuffer;
            int l = _outputTail;
            _outputTail = l + 1;
            abyte0[l] = (byte)(0xe0 | i >> 12);
            int i1 = _outputTail;
            _outputTail = i1 + 1;
            abyte0[i1] = (byte)(0x80 | 0x3f & i >> 6);
            int j1 = _outputTail;
            _outputTail = j1 + 1;
            abyte0[j1] = (byte)(0x80 | i & 0x3f);
            return j;
        }
    }

    private final void _writeBytes(byte abyte0[])
        throws IOException
    {
        int i = abyte0.length;
        if (i + _outputTail > _outputEnd)
        {
            _flushBuffer();
            if (i > 512)
            {
                _outputStream.write(abyte0, 0, i);
                return;
            }
        }
        System.arraycopy(abyte0, 0, _outputBuffer, _outputTail, i);
        _outputTail = i + _outputTail;
    }

    private final void _writeBytes(byte abyte0[], int i, int j)
        throws IOException
    {
        if (j + _outputTail > _outputEnd)
        {
            _flushBuffer();
            if (j > 512)
            {
                _outputStream.write(abyte0, i, j);
                return;
            }
        }
        System.arraycopy(abyte0, i, _outputBuffer, _outputTail, j);
        _outputTail = j + _outputTail;
    }

    private int _writeCustomEscape(byte abyte0[], int i, SerializableString serializablestring, int j)
        throws IOException, JsonGenerationException
    {
        byte abyte1[] = serializablestring.asUnquotedUTF8();
        int k = abyte1.length;
        if (k > 6)
        {
            return _handleLongCustomEscape(abyte0, i, _outputEnd, abyte1, j);
        } else
        {
            System.arraycopy(abyte1, 0, abyte0, i, k);
            return i + k;
        }
    }

    private final void _writeCustomStringSegment2(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        if (_outputTail + 6 * (j - i) > _outputEnd)
        {
            _flushBuffer();
        }
        int k = _outputTail;
        byte abyte0[] = _outputBuffer;
        int ai[] = _outputEscapes;
        int l;
        CharacterEscapes characterescapes;
        int i1;
        if (_maximumNonEscapedChar <= 0)
        {
            l = 65535;
        } else
        {
            l = _maximumNonEscapedChar;
        }
        characterescapes = _characterEscapes;
        i1 = k;
        do
        {
            for (int j1 = i; j1 < j;)
            {
                int k1 = j1 + 1;
                char c = ac[j1];
                if (c <= '\177')
                {
                    if (ai[c] == 0)
                    {
                        int i3 = i1 + 1;
                        abyte0[i1] = (byte)c;
                        i1 = i3;
                        j1 = k1;
                    } else
                    {
                        int k2 = ai[c];
                        if (k2 > 0)
                        {
                            int l2 = i1 + 1;
                            abyte0[i1] = 92;
                            i1 = l2 + 1;
                            abyte0[l2] = (byte)k2;
                            j1 = k1;
                        } else
                        if (k2 == -2)
                        {
                            SerializableString serializablestring1 = characterescapes.getEscapeSequence(c);
                            if (serializablestring1 == null)
                            {
                                throw new JsonGenerationException((new StringBuilder()).append("Invalid custom escape definitions; custom escape not found for character code 0x").append(Integer.toHexString(c)).append(", although was supposed to have one").toString());
                            }
                            i1 = _writeCustomEscape(abyte0, i1, serializablestring1, j - k1);
                            j1 = k1;
                        } else
                        {
                            i1 = _writeGenericEscape(c, i1);
                            j1 = k1;
                        }
                    }
                } else
                if (c > l)
                {
                    i1 = _writeGenericEscape(c, i1);
                    j1 = k1;
                } else
                {
                    SerializableString serializablestring = characterescapes.getEscapeSequence(c);
                    if (serializablestring != null)
                    {
                        i1 = _writeCustomEscape(abyte0, i1, serializablestring, j - k1);
                        j1 = k1;
                    } else
                    {
                        int l1;
                        if (c <= '\u07FF')
                        {
                            int i2 = i1 + 1;
                            abyte0[i1] = (byte)(0xc0 | c >> 6);
                            int j2 = i2 + 1;
                            abyte0[i2] = (byte)(0x80 | c & 0x3f);
                            l1 = j2;
                        } else
                        {
                            l1 = _outputMultiByteChar(c, i1);
                        }
                        i1 = l1;
                        j1 = k1;
                    }
                }
            }

            _outputTail = i1;
            return;
        } while (true);
    }

    private int _writeGenericEscape(int i, int j)
        throws IOException
    {
        byte abyte0[] = _outputBuffer;
        int k = j + 1;
        abyte0[j] = 92;
        int l = k + 1;
        abyte0[k] = 117;
        int j1;
        int k1;
        int l1;
        if (i > 255)
        {
            int i2 = 0xff & i >> 8;
            int j2 = l + 1;
            abyte0[l] = HEX_CHARS[i2 >> 4];
            j1 = j2 + 1;
            abyte0[j2] = HEX_CHARS[i2 & 0xf];
            i &= 0xff;
        } else
        {
            int i1 = l + 1;
            abyte0[l] = 48;
            j1 = i1 + 1;
            abyte0[i1] = 48;
        }
        k1 = j1 + 1;
        abyte0[j1] = HEX_CHARS[i >> 4];
        l1 = k1 + 1;
        abyte0[k1] = HEX_CHARS[i & 0xf];
        return l1;
    }

    private final void _writeLongString(String s)
        throws IOException, JsonGenerationException
    {
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 34;
        _writeStringSegments(s);
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte1[j] = 34;
    }

    private final void _writeLongString(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte0[k] = 34;
        _writeStringSegments(_charBuffer, 0, j);
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte1[] = _outputBuffer;
        int l = _outputTail;
        _outputTail = l + 1;
        abyte1[l] = 34;
    }

    private final void _writeNull()
        throws IOException
    {
        if (4 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        System.arraycopy(NULL_BYTES, 0, _outputBuffer, _outputTail, 4);
        _outputTail = 4 + _outputTail;
    }

    private final void _writeQuotedInt(int i)
        throws IOException
    {
        if (13 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte0[j] = 34;
        _outputTail = NumberOutput.outputInt(i, _outputBuffer, _outputTail);
        byte abyte1[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte1[k] = 34;
    }

    private final void _writeQuotedLong(long l)
        throws IOException
    {
        if (23 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 34;
        _outputTail = NumberOutput.outputLong(l, _outputBuffer, _outputTail);
        byte abyte1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte1[j] = 34;
    }

    private final void _writeQuotedRaw(Object obj)
        throws IOException
    {
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 34;
        writeRaw(obj.toString());
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte1[j] = 34;
    }

    private final void _writeSegmentedRaw(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        int k = _outputEnd;
        byte abyte0[] = _outputBuffer;
label0:
        do
        {
            if (i < j)
            {
                do
                {
                    char c = ac[i];
                    if (c >= '\200')
                    {
                        if (3 + _outputTail >= _outputEnd)
                        {
                            _flushBuffer();
                        }
                        int i1 = i + 1;
                        char c1 = ac[i];
                        int l;
                        if (c1 < '\u0800')
                        {
                            int j1 = _outputTail;
                            _outputTail = j1 + 1;
                            abyte0[j1] = (byte)(0xc0 | c1 >> 6);
                            int k1 = _outputTail;
                            _outputTail = k1 + 1;
                            abyte0[k1] = (byte)(0x80 | c1 & 0x3f);
                        } else
                        {
                            _outputRawMultiByteChar(c1, ac, i1, j);
                        }
                        i = i1;
                        continue label0;
                    }
                    if (_outputTail >= k)
                    {
                        _flushBuffer();
                    }
                    l = _outputTail;
                    _outputTail = l + 1;
                    abyte0[l] = (byte)c;
                } while (++i < j);
            }
            return;
        } while (true);
    }

    private final void _writeStringSegment(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        int k = j + i;
        int l = _outputTail;
        byte abyte0[] = _outputBuffer;
        int ai[] = _outputEscapes;
        int i1 = l;
label0:
        do
        {
            char c;
label1:
            {
                if (i < k)
                {
                    c = ac[i];
                    if (c <= '\177' && ai[c] == 0)
                    {
                        break label1;
                    }
                }
                _outputTail = i1;
                if (i < k)
                {
                    if (_characterEscapes == null)
                    {
                        break label0;
                    }
                    _writeCustomStringSegment2(ac, i, k);
                }
                return;
            }
            int j1 = i1 + 1;
            abyte0[i1] = (byte)c;
            i++;
            i1 = j1;
        } while (true);
        if (_maximumNonEscapedChar == 0)
        {
            _writeStringSegment2(ac, i, k);
            return;
        } else
        {
            _writeStringSegmentASCII2(ac, i, k);
            return;
        }
    }

    private final void _writeStringSegment2(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        if (_outputTail + 6 * (j - i) > _outputEnd)
        {
            _flushBuffer();
        }
        int k = _outputTail;
        byte abyte0[] = _outputBuffer;
        int ai[] = _outputEscapes;
        int l = k;
        do
        {
            for (int i1 = i; i1 < j;)
            {
                int j1 = i1 + 1;
                char c = ac[i1];
                if (c <= '\177')
                {
                    if (ai[c] == 0)
                    {
                        int l2 = l + 1;
                        abyte0[l] = (byte)c;
                        l = l2;
                        i1 = j1;
                    } else
                    {
                        int j2 = ai[c];
                        if (j2 > 0)
                        {
                            int k2 = l + 1;
                            abyte0[l] = 92;
                            l = k2 + 1;
                            abyte0[k2] = (byte)j2;
                            i1 = j1;
                        } else
                        {
                            l = _writeGenericEscape(c, l);
                            i1 = j1;
                        }
                    }
                } else
                {
                    int k1;
                    if (c <= '\u07FF')
                    {
                        int l1 = l + 1;
                        abyte0[l] = (byte)(0xc0 | c >> 6);
                        int i2 = l1 + 1;
                        abyte0[l1] = (byte)(0x80 | c & 0x3f);
                        k1 = i2;
                    } else
                    {
                        k1 = _outputMultiByteChar(c, l);
                    }
                    l = k1;
                    i1 = j1;
                }
            }

            _outputTail = l;
            return;
        } while (true);
    }

    private final void _writeStringSegmentASCII2(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        if (_outputTail + 6 * (j - i) > _outputEnd)
        {
            _flushBuffer();
        }
        int k = _outputTail;
        byte abyte0[] = _outputBuffer;
        int ai[] = _outputEscapes;
        int l = _maximumNonEscapedChar;
        int i1 = k;
        do
        {
            for (int j1 = i; j1 < j;)
            {
                int k1 = j1 + 1;
                char c = ac[j1];
                if (c <= '\177')
                {
                    if (ai[c] == 0)
                    {
                        int i3 = i1 + 1;
                        abyte0[i1] = (byte)c;
                        i1 = i3;
                        j1 = k1;
                    } else
                    {
                        int k2 = ai[c];
                        if (k2 > 0)
                        {
                            int l2 = i1 + 1;
                            abyte0[i1] = 92;
                            i1 = l2 + 1;
                            abyte0[l2] = (byte)k2;
                            j1 = k1;
                        } else
                        {
                            i1 = _writeGenericEscape(c, i1);
                            j1 = k1;
                        }
                    }
                } else
                if (c > l)
                {
                    i1 = _writeGenericEscape(c, i1);
                    j1 = k1;
                } else
                {
                    int l1;
                    if (c <= '\u07FF')
                    {
                        int i2 = i1 + 1;
                        abyte0[i1] = (byte)(0xc0 | c >> 6);
                        int j2 = i2 + 1;
                        abyte0[i2] = (byte)(0x80 | c & 0x3f);
                        l1 = j2;
                    } else
                    {
                        l1 = _outputMultiByteChar(c, i1);
                    }
                    i1 = l1;
                    j1 = k1;
                }
            }

            _outputTail = i1;
            return;
        } while (true);
    }

    private final void _writeStringSegments(String s)
        throws IOException, JsonGenerationException
    {
        int i = s.length();
        int j = 0;
        char ac[] = _charBuffer;
        int k;
        for (; i > 0; i -= k)
        {
            k = Math.min(_outputMaxContiguous, i);
            s.getChars(j, j + k, ac, 0);
            if (k + _outputTail > _outputEnd)
            {
                _flushBuffer();
            }
            _writeStringSegment(ac, 0, k);
            j += k;
        }

    }

    private final void _writeStringSegments(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        do
        {
            int k = Math.min(_outputMaxContiguous, j);
            if (k + _outputTail > _outputEnd)
            {
                _flushBuffer();
            }
            _writeStringSegment(ac, i, k);
            i += k;
            j -= k;
        } while (j > 0);
    }

    private final void _writeUTF8Segment(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        int ai[] = _outputEscapes;
        int k = i + j;
        int l;
        int i1;
        for (l = i; l < k; l = i1)
        {
            i1 = l + 1;
            byte byte0 = abyte0[l];
            if (byte0 >= 0 && ai[byte0] != 0)
            {
                _writeUTF8Segment2(abyte0, i, j);
                return;
            }
        }

        if (j + _outputTail > _outputEnd)
        {
            _flushBuffer();
        }
        System.arraycopy(abyte0, i, _outputBuffer, _outputTail, j);
        _outputTail = j + _outputTail;
        int _tmp = l;
    }

    private final void _writeUTF8Segment2(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        int k = _outputTail;
        if (k + j * 6 > _outputEnd)
        {
            _flushBuffer();
            k = _outputTail;
        }
        byte abyte1[] = _outputBuffer;
        int ai[] = _outputEscapes;
        int l = j + i;
        int i1 = k;
        do
        {
            for (int j1 = i; j1 < l;)
            {
                int k1 = j1 + 1;
                byte byte0 = abyte0[j1];
                if (byte0 < 0 || ai[byte0] == 0)
                {
                    int l1 = i1 + 1;
                    abyte1[i1] = byte0;
                    i1 = l1;
                    j1 = k1;
                } else
                {
                    int i2 = ai[byte0];
                    int j2;
                    if (i2 > 0)
                    {
                        int k2 = i1 + 1;
                        abyte1[i1] = 92;
                        int l2 = k2 + 1;
                        abyte1[k2] = (byte)i2;
                        j2 = l2;
                    } else
                    {
                        j2 = _writeGenericEscape(byte0, i1);
                    }
                    i1 = j2;
                    j1 = k1;
                }
            }

            _outputTail = i1;
            return;
        } while (true);
    }

    private final void _writeUTF8Segments(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        do
        {
            int k = Math.min(_outputMaxContiguous, j);
            _writeUTF8Segment(abyte0, i, k);
            i += k;
            j -= k;
        } while (j > 0);
    }

    protected final int _decodeSurrogate(int i, int j)
        throws IOException
    {
        if (j < 56320 || j > 57343)
        {
            _reportError((new StringBuilder()).append("Incomplete surrogate pair: first char 0x").append(Integer.toHexString(i)).append(", second 0x").append(Integer.toHexString(j)).toString());
        }
        return 0x10000 + (i - 55296 << 10) + (j - 56320);
    }

    protected final void _flushBuffer()
        throws IOException
    {
        int i = _outputTail;
        if (i > 0)
        {
            _outputTail = 0;
            _outputStream.write(_outputBuffer, 0, i);
        }
    }

    protected final void _outputSurrogates(int i, int j)
        throws IOException
    {
        int k = _decodeSurrogate(i, j);
        if (4 + _outputTail > _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        int l = _outputTail;
        _outputTail = l + 1;
        abyte0[l] = (byte)(0xf0 | k >> 18);
        int i1 = _outputTail;
        _outputTail = i1 + 1;
        abyte0[i1] = (byte)(0x80 | 0x3f & k >> 12);
        int j1 = _outputTail;
        _outputTail = j1 + 1;
        abyte0[j1] = (byte)(0x80 | 0x3f & k >> 6);
        int k1 = _outputTail;
        _outputTail = k1 + 1;
        abyte0[k1] = (byte)(0x80 | k & 0x3f);
    }

    protected void _releaseBuffers()
    {
        byte abyte0[] = _outputBuffer;
        if (abyte0 != null && _bufferRecyclable)
        {
            _outputBuffer = null;
            _ioContext.releaseWriteEncodingBuffer(abyte0);
        }
        char ac[] = _charBuffer;
        if (ac != null)
        {
            _charBuffer = null;
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
        byte byte0 = 44;
_L7:
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        _outputBuffer[_outputTail] = byte0;
        _outputTail = 1 + _outputTail;
        return;
_L5:
        byte0 = 58;
        continue; /* Loop/switch isn't completed */
_L6:
        byte0 = 32;
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
                byte abyte1[] = _outputBuffer;
                int k3 = _outputTail;
                _outputTail = k3 + 1;
                abyte1[k3] = 92;
                byte abyte2[] = _outputBuffer;
                int l3 = _outputTail;
                _outputTail = l3 + 1;
                abyte2[l3] = 110;
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

    protected final void _writeFieldName(String s)
        throws IOException, JsonGenerationException
    {
        if (!isEnabled(org.codehaus.jackson.JsonGenerator.Feature.QUOTE_FIELD_NAMES))
        {
            _writeStringSegments(s);
            return;
        }
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 34;
        int j = s.length();
        if (j <= _charBufferLength)
        {
            s.getChars(0, j, _charBuffer, 0);
            byte abyte1[];
            int k;
            if (j <= _outputMaxContiguous)
            {
                if (j + _outputTail > _outputEnd)
                {
                    _flushBuffer();
                }
                _writeStringSegment(_charBuffer, 0, j);
            } else
            {
                _writeStringSegments(_charBuffer, 0, j);
            }
        } else
        {
            _writeStringSegments(s);
        }
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        abyte1 = _outputBuffer;
        k = _outputTail;
        _outputTail = k + 1;
        abyte1[k] = 34;
    }

    protected final void _writeFieldName(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        byte abyte0[] = serializablestring.asQuotedUTF8();
        if (!isEnabled(org.codehaus.jackson.JsonGenerator.Feature.QUOTE_FIELD_NAMES))
        {
            _writeBytes(abyte0);
            return;
        }
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte1[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte1[i] = 34;
        int j = abyte0.length;
        if (1 + (j + _outputTail) < _outputEnd)
        {
            System.arraycopy(abyte0, 0, _outputBuffer, _outputTail, j);
            _outputTail = j + _outputTail;
            byte abyte3[] = _outputBuffer;
            int l = _outputTail;
            _outputTail = l + 1;
            abyte3[l] = 34;
            return;
        }
        _writeBytes(abyte0);
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte2[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte2[k] = 34;
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
            byte abyte0[] = _outputBuffer;
            int i = _outputTail;
            _outputTail = i + 1;
            abyte0[i] = 34;
            int j = s.length();
            if (j <= _charBufferLength)
            {
                s.getChars(0, j, _charBuffer, 0);
                byte abyte1[];
                int k;
                if (j <= _outputMaxContiguous)
                {
                    if (j + _outputTail > _outputEnd)
                    {
                        _flushBuffer();
                    }
                    _writeStringSegment(_charBuffer, 0, j);
                } else
                {
                    _writeStringSegments(_charBuffer, 0, j);
                }
            } else
            {
                _writeStringSegments(s);
            }
            if (_outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            abyte1 = _outputBuffer;
            k = _outputTail;
            _outputTail = k + 1;
            abyte1[k] = 34;
            return;
        } else
        {
            _writeStringSegments(s);
            return;
        }
    }

    protected final void _writePPFieldName(SerializableString serializablestring, boolean flag)
        throws IOException, JsonGenerationException
    {
        boolean flag1;
        if (flag)
        {
            _cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else
        {
            _cfgPrettyPrinter.beforeObjectEntries(this);
        }
        flag1 = isEnabled(org.codehaus.jackson.JsonGenerator.Feature.QUOTE_FIELD_NAMES);
        if (flag1)
        {
            if (_outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            byte abyte1[] = _outputBuffer;
            int j = _outputTail;
            _outputTail = j + 1;
            abyte1[j] = 34;
        }
        _writeBytes(serializablestring.asQuotedUTF8());
        if (flag1)
        {
            if (_outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            byte abyte0[] = _outputBuffer;
            int i = _outputTail;
            _outputTail = i + 1;
            abyte0[i] = 34;
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
            _outputStream.close();
        } else
        {
            _outputStream.flush();
        }
        _releaseBuffers();
    }

    public final void flush()
        throws IOException
    {
        _flushBuffer();
        if (_outputStream != null && isEnabled(org.codehaus.jackson.JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM))
        {
            _outputStream.flush();
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
        return _outputStream;
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
        byte abyte1[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte1[k] = 34;
        _writeBinary(base64variant, abyte0, i, i + j);
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte2[] = _outputBuffer;
        int l = _outputTail;
        _outputTail = l + 1;
        abyte2[l] = 34;
    }

    public void writeBoolean(boolean flag)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write boolean value");
        if (5 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[];
        int i;
        if (flag)
        {
            abyte0 = TRUE_BYTES;
        } else
        {
            abyte0 = FALSE_BYTES;
        }
        i = abyte0.length;
        System.arraycopy(abyte0, 0, _outputBuffer, _outputTail, i);
        _outputTail = i + _outputTail;
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
            byte abyte0[] = _outputBuffer;
            int i = _outputTail;
            _outputTail = i + 1;
            abyte0[i] = 93;
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
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 125;
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
        if (_cfgPrettyPrinter != null)
        {
            if (i != flag)
            {
                flag = false;
            }
            _writePPFieldName(s, flag);
            return;
        }
        if (i == flag)
        {
            if (_outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            byte abyte0[] = _outputBuffer;
            int j = _outputTail;
            _outputTail = j + 1;
            abyte0[j] = 44;
        }
        _writeFieldName(s);
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
        if (_cfgPrettyPrinter != null)
        {
            if (i != flag)
            {
                flag = false;
            }
            _writePPFieldName(serializablestring, flag);
            return;
        }
        if (i == flag)
        {
            if (_outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            byte abyte0[] = _outputBuffer;
            int j = _outputTail;
            _outputTail = j + 1;
            abyte0[j] = 44;
        }
        _writeFieldName(serializablestring);
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
        if (_cfgPrettyPrinter != null)
        {
            if (i != flag)
            {
                flag = false;
            }
            _writePPFieldName(serializedstring, flag);
            return;
        }
        if (i == flag)
        {
            if (_outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            byte abyte0[] = _outputBuffer;
            int j = _outputTail;
            _outputTail = j + 1;
            abyte0[j] = 44;
        }
        _writeFieldName(serializedstring);
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
        if (3 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        if (c <= '\177')
        {
            int k = _outputTail;
            _outputTail = k + 1;
            abyte0[k] = (byte)c;
            return;
        }
        if (c < '\u0800')
        {
            int i = _outputTail;
            _outputTail = i + 1;
            abyte0[i] = (byte)(0xc0 | c >> 6);
            int j = _outputTail;
            _outputTail = j + 1;
            abyte0[j] = (byte)(0x80 | c & 0x3f);
            return;
        } else
        {
            _outputRawMultiByteChar(c, null, 0, 0);
            return;
        }
    }

    public void writeRaw(String s)
        throws IOException, JsonGenerationException
    {
        int i = 0;
        int j = s.length();
        while (j > 0) 
        {
            char ac[] = _charBuffer;
            int k = ac.length;
            int l;
            if (j < k)
            {
                l = j;
            } else
            {
                l = k;
            }
            s.getChars(i, i + l, ac, 0);
            writeRaw(ac, 0, l);
            i += l;
            j -= l;
        }
    }

    public void writeRaw(String s, int i, int j)
        throws IOException, JsonGenerationException
    {
        while (j > 0) 
        {
            char ac[] = _charBuffer;
            int k = ac.length;
            int l;
            if (j < k)
            {
                l = j;
            } else
            {
                l = k;
            }
            s.getChars(i, i + l, ac, 0);
            writeRaw(ac, 0, l);
            i += l;
            j -= l;
        }
    }

    public final void writeRaw(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        int k = j + (j + j);
        if (k + _outputTail <= _outputEnd) goto _L2; else goto _L1
_L1:
        if (_outputEnd >= k) goto _L4; else goto _L3
_L3:
        _writeSegmentedRaw(ac, i, j);
_L10:
        return;
_L4:
        _flushBuffer();
_L2:
        int l = j + i;
_L8:
        if (i >= l)
        {
            continue; /* Loop/switch isn't completed */
        }
_L6:
label0:
        {
            char c = ac[i];
            if (c <= '\177')
            {
                break label0;
            }
            int j1 = i + 1;
            char c1 = ac[i];
            byte abyte0[];
            int i1;
            if (c1 < '\u0800')
            {
                byte abyte1[] = _outputBuffer;
                int k1 = _outputTail;
                _outputTail = k1 + 1;
                abyte1[k1] = (byte)(0xc0 | c1 >> 6);
                byte abyte2[] = _outputBuffer;
                int l1 = _outputTail;
                _outputTail = l1 + 1;
                abyte2[l1] = (byte)(0x80 | c1 & 0x3f);
            } else
            {
                _outputRawMultiByteChar(c1, ac, j1, l);
            }
            i = j1;
        }
        continue; /* Loop/switch isn't completed */
        abyte0 = _outputBuffer;
        i1 = _outputTail;
        _outputTail = i1 + 1;
        abyte0[i1] = (byte)c;
        if (++i < l) goto _L6; else goto _L5
_L5:
        return;
        if (true) goto _L8; else goto _L7
_L7:
        if (true) goto _L10; else goto _L9
_L9:
    }

    public void writeRawUTF8String(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write text value");
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte1[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte1[k] = 34;
        _writeBytes(abyte0, i, j);
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte2[] = _outputBuffer;
        int l = _outputTail;
        _outputTail = l + 1;
        abyte2[l] = 34;
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
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 91;
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
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 123;
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
        int i = s.length();
        if (i > _charBufferLength)
        {
            _writeLongString(s);
            return;
        }
        s.getChars(0, i, _charBuffer, 0);
        if (i > _outputMaxContiguous)
        {
            _writeLongString(_charBuffer, 0, i);
            return;
        }
        if (2 + (i + _outputTail) > _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte0[j] = 34;
        _writeStringSegment(_charBuffer, 0, i);
        byte abyte1[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte1[k] = 34;
    }

    public final void writeString(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write text value");
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 34;
        _writeBytes(serializablestring.asQuotedUTF8());
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte1[j] = 34;
    }

    public void writeString(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write text value");
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte0[k] = 34;
        byte abyte1[];
        int l;
        if (j <= _outputMaxContiguous)
        {
            if (j + _outputTail > _outputEnd)
            {
                _flushBuffer();
            }
            _writeStringSegment(ac, i, j);
        } else
        {
            _writeStringSegments(ac, i, j);
        }
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        abyte1 = _outputBuffer;
        l = _outputTail;
        _outputTail = l + 1;
        abyte1[l] = 34;
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
        _verifyValueWrite("write text value");
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte1[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte1[k] = 34;
        byte abyte2[];
        int l;
        if (j <= _outputMaxContiguous)
        {
            _writeUTF8Segment(abyte0, i, j);
        } else
        {
            _writeUTF8Segments(abyte0, i, j);
        }
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        abyte2 = _outputBuffer;
        l = _outputTail;
        _outputTail = l + 1;
        abyte2[l] = 34;
    }

}
