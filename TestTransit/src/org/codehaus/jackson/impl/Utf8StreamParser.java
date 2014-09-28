// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.impl;

import java.io.IOException;
import java.io.InputStream;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.sym.BytesToNameCanonicalizer;
import org.codehaus.jackson.sym.Name;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.CharTypes;
import org.codehaus.jackson.util.TextBuffer;

// Referenced classes of package org.codehaus.jackson.impl:
//            StreamBasedParserBase, JsonReadContext

public final class Utf8StreamParser extends StreamBasedParserBase
{

    static final byte BYTE_LF = 10;
    private static final int sInputCodesLatin1[] = CharTypes.getInputCodeLatin1();
    private static final int sInputCodesUtf8[] = CharTypes.getInputCodeUtf8();
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int _quadBuffer[];
    protected final BytesToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;

    public Utf8StreamParser(IOContext iocontext, int i, InputStream inputstream, ObjectCodec objectcodec, BytesToNameCanonicalizer bytestonamecanonicalizer, byte abyte0[], int j, 
            int k, boolean flag)
    {
        super(iocontext, i, inputstream, abyte0, j, k, flag);
        _quadBuffer = new int[16];
        _tokenIncomplete = false;
        _objectCodec = objectcodec;
        _symbols = bytestonamecanonicalizer;
        if (!org.codehaus.jackson.JsonParser.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(i))
        {
            _throwInternal();
        }
    }

    private final int _decodeBase64Escape(Base64Variant base64variant, int i, int j)
        throws IOException, JsonParseException
    {
        if (i != 92)
        {
            throw reportInvalidChar(base64variant, i, j);
        }
        char c = _decodeEscaped();
        int k;
        if (c <= ' ' && j == 0)
        {
            k = -1;
        } else
        {
            k = base64variant.decodeBase64Char(c);
            if (k < 0)
            {
                throw reportInvalidChar(base64variant, c, j);
            }
        }
        return k;
    }

    private final int _decodeUtf8_2(int i)
        throws IOException, JsonParseException
    {
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        byte byte0 = abyte0[j];
        if ((byte0 & 0xc0) != 128)
        {
            _reportInvalidOther(byte0 & 0xff, _inputPtr);
        }
        return (i & 0x1f) << 6 | byte0 & 0x3f;
    }

    private final int _decodeUtf8_3(int i)
        throws IOException, JsonParseException
    {
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        int j = i & 0xf;
        byte abyte0[] = _inputBuffer;
        int k = _inputPtr;
        _inputPtr = k + 1;
        byte byte0 = abyte0[k];
        if ((byte0 & 0xc0) != 128)
        {
            _reportInvalidOther(byte0 & 0xff, _inputPtr);
        }
        int l = j << 6 | byte0 & 0x3f;
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        byte abyte1[] = _inputBuffer;
        int i1 = _inputPtr;
        _inputPtr = i1 + 1;
        byte byte1 = abyte1[i1];
        if ((byte1 & 0xc0) != 128)
        {
            _reportInvalidOther(byte1 & 0xff, _inputPtr);
        }
        return l << 6 | byte1 & 0x3f;
    }

    private final int _decodeUtf8_3fast(int i)
        throws IOException, JsonParseException
    {
        int j = i & 0xf;
        byte abyte0[] = _inputBuffer;
        int k = _inputPtr;
        _inputPtr = k + 1;
        byte byte0 = abyte0[k];
        if ((byte0 & 0xc0) != 128)
        {
            _reportInvalidOther(byte0 & 0xff, _inputPtr);
        }
        int l = j << 6 | byte0 & 0x3f;
        byte abyte1[] = _inputBuffer;
        int i1 = _inputPtr;
        _inputPtr = i1 + 1;
        byte byte1 = abyte1[i1];
        if ((byte1 & 0xc0) != 128)
        {
            _reportInvalidOther(byte1 & 0xff, _inputPtr);
        }
        return l << 6 | byte1 & 0x3f;
    }

    private final int _decodeUtf8_4(int i)
        throws IOException, JsonParseException
    {
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        byte byte0 = abyte0[j];
        if ((byte0 & 0xc0) != 128)
        {
            _reportInvalidOther(byte0 & 0xff, _inputPtr);
        }
        int k = (i & 7) << 6 | byte0 & 0x3f;
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        byte abyte1[] = _inputBuffer;
        int l = _inputPtr;
        _inputPtr = l + 1;
        byte byte1 = abyte1[l];
        if ((byte1 & 0xc0) != 128)
        {
            _reportInvalidOther(byte1 & 0xff, _inputPtr);
        }
        int i1 = k << 6 | byte1 & 0x3f;
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        byte abyte2[] = _inputBuffer;
        int j1 = _inputPtr;
        _inputPtr = j1 + 1;
        byte byte2 = abyte2[j1];
        if ((byte2 & 0xc0) != 128)
        {
            _reportInvalidOther(byte2 & 0xff, _inputPtr);
        }
        return (i1 << 6 | byte2 & 0x3f) - 0x10000;
    }

    private final void _finishString2(char ac[], int i)
        throws IOException, JsonParseException
    {
label0:
        {
label1:
            {
label2:
                {
                    int ai[] = sInputCodesUtf8;
                    byte abyte0[] = _inputBuffer;
label3:
                    do
                    {
                        {
                            int i1;
                            int k1;
label4:
                            {
                                int j = _inputPtr;
                                if (j >= _inputEnd)
                                {
                                    loadMoreGuaranteed();
                                    j = _inputPtr;
                                }
                                if (i >= ac.length)
                                {
                                    ac = _textBuffer.finishCurrentSegment();
                                    i = 0;
                                }
                                int k = Math.min(_inputEnd, j + (ac.length - i));
                                int l = j;
                                i1 = i;
                                do
                                {
                                    if (l >= k)
                                    {
                                        break;
                                    }
                                    int j1 = l + 1;
                                    k1 = 0xff & abyte0[l];
                                    if (ai[k1] != 0)
                                    {
                                        _inputPtr = j1;
                                        if (k1 == 34)
                                        {
                                            _textBuffer.setCurrentLength(i1);
                                            return;
                                        }
                                        break label4;
                                    }
                                    int l1 = i1 + 1;
                                    ac[i1] = (char)k1;
                                    l = j1;
                                    i1 = l1;
                                } while (true);
                                _inputPtr = l;
                                i = i1;
                                continue;
                            }
                            int i2;
                            int j2;
                            int k2;
                            switch (ai[k1])
                            {
                            default:
                                if (k1 < 32)
                                {
                                    _throwUnquotedSpace(k1, "string value");
                                    j2 = i1;
                                } else
                                {
                                    _reportInvalidChar(k1);
                                    j2 = i1;
                                }
                                break;

                            case 1: // '\001'
                                break label3;

                            case 2: // '\002'
                                break label2;

                            case 3: // '\003'
                                break label1;

                            case 4: // '\004'
                                break label0;
                            }
                        }
                        if (j2 >= ac.length)
                        {
                            ac = _textBuffer.finishCurrentSegment();
                            j2 = 0;
                        }
                        k2 = j2 + 1;
                        ac[j2] = (char)k1;
                        i = k2;
                    } while (true);
                    k1 = _decodeEscaped();
                    j2 = i1;
                    break MISSING_BLOCK_LABEL_219;
                }
                k1 = _decodeUtf8_2(k1);
                j2 = i1;
                break MISSING_BLOCK_LABEL_219;
            }
            if (_inputEnd - _inputPtr >= 2)
            {
                k1 = _decodeUtf8_3fast(k1);
                j2 = i1;
            } else
            {
                k1 = _decodeUtf8_3(k1);
                j2 = i1;
            }
            break MISSING_BLOCK_LABEL_219;
        }
        i2 = _decodeUtf8_4(k1);
        j2 = i1 + 1;
        ac[i1] = (char)(0xd800 | i2 >> 10);
        if (j2 >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            j2 = 0;
        }
        k1 = 0xdc00 | i2 & 0x3ff;
        break MISSING_BLOCK_LABEL_219;
    }

    private final JsonToken _nextAfterName()
    {
        JsonToken jsontoken;
        _nameCopied = false;
        jsontoken = _nextToken;
        _nextToken = null;
        if (jsontoken != JsonToken.START_ARRAY) goto _L2; else goto _L1
_L1:
        _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
_L4:
        _currToken = jsontoken;
        return jsontoken;
_L2:
        if (jsontoken == JsonToken.START_OBJECT)
        {
            _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private final JsonToken _nextTokenNotInObject(int i)
        throws IOException, JsonParseException
    {
        if (i == 34)
        {
            _tokenIncomplete = true;
            JsonToken jsontoken7 = JsonToken.VALUE_STRING;
            _currToken = jsontoken7;
            return jsontoken7;
        }
        switch (i)
        {
        default:
            JsonToken jsontoken6 = _handleUnexpectedValue(i);
            _currToken = jsontoken6;
            return jsontoken6;

        case 91: // '['
            _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
            JsonToken jsontoken5 = JsonToken.START_ARRAY;
            _currToken = jsontoken5;
            return jsontoken5;

        case 123: // '{'
            _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
            JsonToken jsontoken4 = JsonToken.START_OBJECT;
            _currToken = jsontoken4;
            return jsontoken4;

        case 93: // ']'
        case 125: // '}'
            _reportUnexpectedChar(i, "expected a value");
            // fall through

        case 116: // 't'
            _matchToken(JsonToken.VALUE_TRUE);
            JsonToken jsontoken3 = JsonToken.VALUE_TRUE;
            _currToken = jsontoken3;
            return jsontoken3;

        case 102: // 'f'
            _matchToken(JsonToken.VALUE_FALSE);
            JsonToken jsontoken2 = JsonToken.VALUE_FALSE;
            _currToken = jsontoken2;
            return jsontoken2;

        case 110: // 'n'
            _matchToken(JsonToken.VALUE_NULL);
            JsonToken jsontoken1 = JsonToken.VALUE_NULL;
            _currToken = jsontoken1;
            return jsontoken1;

        case 45: // '-'
        case 48: // '0'
        case 49: // '1'
        case 50: // '2'
        case 51: // '3'
        case 52: // '4'
        case 53: // '5'
        case 54: // '6'
        case 55: // '7'
        case 56: // '8'
        case 57: // '9'
            JsonToken jsontoken = parseNumberText(i);
            _currToken = jsontoken;
            return jsontoken;
        }
    }

    private final JsonToken _parseFloatText(char ac[], int i, int j, boolean flag, int k)
        throws IOException, JsonParseException
    {
        boolean flag1;
        int l;
        flag1 = false;
        l = 0;
        if (j != 46) goto _L2; else goto _L1
_L1:
        int j3 = i + 1;
        ac[i] = (char)j;
        i = j3;
_L9:
        if (_inputPtr < _inputEnd || loadMore()) goto _L4; else goto _L3
_L3:
        flag1 = true;
_L6:
        if (l == 0)
        {
            reportUnexpectedNumberChar(j, "Decimal point not followed by a digit");
        }
_L2:
label0:
        {
            {
                int i1 = 0;
                if (j != 101)
                {
                    i1 = 0;
                    if (j != 69)
                    {
                        break label0;
                    }
                }
                if (i >= ac.length)
                {
                    ac = _textBuffer.finishCurrentSegment();
                    i = 0;
                }
                int j1 = i + 1;
                ac[i] = (char)j;
                if (_inputPtr >= _inputEnd)
                {
                    loadMoreGuaranteed();
                }
                byte abyte0[] = _inputBuffer;
                int k1 = _inputPtr;
                _inputPtr = k1 + 1;
                int l1 = 0xff & abyte0[k1];
                if (l1 == 45 || l1 == 43)
                {
                    int i2;
                    int j2;
                    byte abyte1[];
                    int k2;
                    int l2;
                    byte abyte2[];
                    int i3;
                    byte abyte3[];
                    int k3;
                    int l3;
                    if (j1 >= ac.length)
                    {
                        ac = _textBuffer.finishCurrentSegment();
                        i2 = 0;
                    } else
                    {
                        i2 = j1;
                    }
                    j2 = i2 + 1;
                    ac[i2] = (char)l1;
                    if (_inputPtr >= _inputEnd)
                    {
                        loadMoreGuaranteed();
                    }
                    abyte1 = _inputBuffer;
                    k2 = _inputPtr;
                    _inputPtr = k2 + 1;
                    l1 = 0xff & abyte1[k2];
                    i = j2;
                } else
                {
                    i = j1;
                    i1 = 0;
                }
            }
            if (l1 <= 57 && l1 >= 48)
            {
                i1++;
                if (i >= ac.length)
                {
                    ac = _textBuffer.finishCurrentSegment();
                    i = 0;
                }
                l2 = i + 1;
                ac[i] = (char)l1;
                if (_inputPtr < _inputEnd || loadMore())
                {
                    break MISSING_BLOCK_LABEL_442;
                }
                flag1 = true;
                i = l2;
            }
            if (i1 == 0)
            {
                reportUnexpectedNumberChar(l1, "Exponent indicator not followed by a digit");
            }
        }
        if (!flag1)
        {
            _inputPtr = -1 + _inputPtr;
        }
        _textBuffer.setCurrentLength(i);
        return resetFloat(flag, k, l, i1);
_L4:
        abyte3 = _inputBuffer;
        k3 = _inputPtr;
        _inputPtr = k3 + 1;
        j = 0xff & abyte3[k3];
        flag1 = false;
        if (j < 48) goto _L6; else goto _L5
_L5:
        flag1 = false;
        if (j > 57) goto _L6; else goto _L7
_L7:
        l++;
        if (i >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            i = 0;
        }
        l3 = i + 1;
        ac[i] = (char)j;
        i = l3;
        if (true) goto _L9; else goto _L8
_L8:
        abyte2 = _inputBuffer;
        i3 = _inputPtr;
        _inputPtr = i3 + 1;
        l1 = 0xff & abyte2[i3];
        i = l2;
        break MISSING_BLOCK_LABEL_242;
    }

    private final JsonToken _parserNumber2(char ac[], int i, boolean flag, int j)
        throws IOException, JsonParseException
    {
        do
        {
            if (_inputPtr >= _inputEnd && !loadMore())
            {
                _textBuffer.setCurrentLength(i);
                return resetInt(flag, j);
            }
            byte abyte0[] = _inputBuffer;
            int k = _inputPtr;
            _inputPtr = k + 1;
            int l = 0xff & abyte0[k];
            int i1;
            if (l > 57 || l < 48)
            {
                if (l == 46 || l == 101 || l == 69)
                {
                    return _parseFloatText(ac, i, l, flag, j);
                } else
                {
                    _inputPtr = -1 + _inputPtr;
                    _textBuffer.setCurrentLength(i);
                    return resetInt(flag, j);
                }
            }
            if (i >= ac.length)
            {
                ac = _textBuffer.finishCurrentSegment();
                i = 0;
            }
            i1 = i + 1;
            ac[i] = (char)l;
            j++;
            i = i1;
        } while (true);
    }

    private final void _skipCComment()
        throws IOException, JsonParseException
    {
        int ai[] = CharTypes.getInputCodeComment();
        do
        {
            if (_inputPtr >= _inputEnd && !loadMore())
            {
                break;
            }
            byte abyte0[] = _inputBuffer;
            int i = _inputPtr;
            _inputPtr = i + 1;
            int j = 0xff & abyte0[i];
            int k = ai[j];
            if (k != 0)
            {
                switch (k)
                {
                default:
                    _reportInvalidChar(j);
                    break;

                case 42: // '*'
                    if (_inputBuffer[_inputPtr] == 47)
                    {
                        _inputPtr = 1 + _inputPtr;
                        return;
                    }
                    break;

                case 10: // '\n'
                    _skipLF();
                    break;

                case 13: // '\r'
                    _skipCR();
                    break;
                }
            }
        } while (true);
        _reportInvalidEOF(" in a comment");
    }

    private final void _skipComment()
        throws IOException, JsonParseException
    {
        if (!isEnabled(org.codehaus.jackson.JsonParser.Feature.ALLOW_COMMENTS))
        {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (_inputPtr >= _inputEnd && !loadMore())
        {
            _reportInvalidEOF(" in a comment");
        }
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        int j = 0xff & abyte0[i];
        if (j == 47)
        {
            _skipCppComment();
            return;
        }
        if (j == 42)
        {
            _skipCComment();
            return;
        } else
        {
            _reportUnexpectedChar(j, "was expecting either '*' or '/' for a comment");
            return;
        }
    }

    private final void _skipCppComment()
        throws IOException, JsonParseException
    {
        int ai[] = CharTypes.getInputCodeComment();
_L4:
        if (_inputPtr >= _inputEnd && !loadMore()) goto _L2; else goto _L1
_L1:
        int j;
        int k;
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        j = 0xff & abyte0[i];
        k = ai[j];
        if (k == 0) goto _L4; else goto _L3
_L3:
        k;
        JVM INSTR lookupswitch 3: default 96
    //                   10: 105
    //                   13: 110
    //                   42: 4;
           goto _L5 _L6 _L7 _L4
_L5:
        _reportInvalidChar(j);
          goto _L4
_L6:
        _skipLF();
_L2:
        return;
_L7:
        _skipCR();
        return;
    }

    private final void _skipUtf8_2(int i)
        throws IOException, JsonParseException
    {
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        byte byte0 = abyte0[j];
        if ((byte0 & 0xc0) != 128)
        {
            _reportInvalidOther(byte0 & 0xff, _inputPtr);
        }
    }

    private final void _skipUtf8_3(int i)
        throws IOException, JsonParseException
    {
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        byte byte0 = abyte0[j];
        if ((byte0 & 0xc0) != 128)
        {
            _reportInvalidOther(byte0 & 0xff, _inputPtr);
        }
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        byte abyte1[] = _inputBuffer;
        int k = _inputPtr;
        _inputPtr = k + 1;
        byte byte1 = abyte1[k];
        if ((byte1 & 0xc0) != 128)
        {
            _reportInvalidOther(byte1 & 0xff, _inputPtr);
        }
    }

    private final void _skipUtf8_4(int i)
        throws IOException, JsonParseException
    {
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        byte byte0 = abyte0[j];
        if ((byte0 & 0xc0) != 128)
        {
            _reportInvalidOther(byte0 & 0xff, _inputPtr);
        }
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        if ((byte0 & 0xc0) != 128)
        {
            _reportInvalidOther(byte0 & 0xff, _inputPtr);
        }
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        byte abyte1[] = _inputBuffer;
        int k = _inputPtr;
        _inputPtr = k + 1;
        byte byte1 = abyte1[k];
        if ((byte1 & 0xc0) != 128)
        {
            _reportInvalidOther(byte1 & 0xff, _inputPtr);
        }
    }

    private final int _skipWS()
        throws IOException, JsonParseException
    {
        do
        {
            if (_inputPtr >= _inputEnd && !loadMore())
            {
                break;
            }
            byte abyte0[] = _inputBuffer;
            int i = _inputPtr;
            _inputPtr = i + 1;
            int j = 0xff & abyte0[i];
            if (j > 32)
            {
                if (j != 47)
                {
                    return j;
                }
                _skipComment();
            } else
            if (j != 32)
            {
                if (j == 10)
                {
                    _skipLF();
                } else
                if (j == 13)
                {
                    _skipCR();
                } else
                if (j != 9)
                {
                    _throwInvalidSpace(j);
                }
            }
        } while (true);
        throw _constructError((new StringBuilder()).append("Unexpected end-of-input within/between ").append(_parsingContext.getTypeDesc()).append(" entries").toString());
    }

    private final int _skipWSOrEnd()
        throws IOException, JsonParseException
    {
        do
        {
            if (_inputPtr >= _inputEnd && !loadMore())
            {
                break;
            }
            byte abyte0[] = _inputBuffer;
            int i = _inputPtr;
            _inputPtr = i + 1;
            int j = 0xff & abyte0[i];
            if (j > 32)
            {
                if (j != 47)
                {
                    return j;
                }
                _skipComment();
            } else
            if (j != 32)
            {
                if (j == 10)
                {
                    _skipLF();
                } else
                if (j == 13)
                {
                    _skipCR();
                } else
                if (j != 9)
                {
                    _throwInvalidSpace(j);
                }
            }
        } while (true);
        _handleEOF();
        return -1;
    }

    private final int _verifyNoLeadingZeroes()
        throws IOException, JsonParseException
    {
        if (_inputPtr < _inputEnd || loadMore()) goto _L2; else goto _L1
_L1:
        int i = 48;
_L4:
        return i;
_L2:
        i = 0xff & _inputBuffer[_inputPtr];
        if (i < 48 || i > 57)
        {
            return 48;
        }
        if (!isEnabled(org.codehaus.jackson.JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS))
        {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        _inputPtr = 1 + _inputPtr;
        if (i != 48)
        {
            continue; /* Loop/switch isn't completed */
        }
        do
        {
            if (_inputPtr >= _inputEnd && !loadMore())
            {
                continue; /* Loop/switch isn't completed */
            }
            i = 0xff & _inputBuffer[_inputPtr];
            if (i < 48 || i > 57)
            {
                return 48;
            }
            _inputPtr = 1 + _inputPtr;
        } while (i == 48);
        break; /* Loop/switch isn't completed */
        if (true) goto _L4; else goto _L3
_L3:
        return i;
    }

    private final Name addName(int ai[], int i, int j)
        throws JsonParseException
    {
        int l;
        char ac[];
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k = j + (-4 + (i << 2));
        int i1;
        int k2;
        int l2;
        int i3;
        int j3;
        if (j < 4)
        {
            l = ai[i - 1];
            ai[i - 1] = l << (4 - j << 3);
        } else
        {
            l = 0;
        }
        ac = _textBuffer.emptyAndGetCurrentSegment();
        i1 = 0;
        j1 = 0;
        if (i1 >= k)
        {
            break; /* Loop/switch isn't completed */
        }
        k1 = 0xff & ai[i1 >> 2] >> (3 - (i1 & 3) << 3);
        i1++;
        if (k1 <= 127)
        {
            break MISSING_BLOCK_LABEL_501;
        }
        if ((k1 & 0xe0) == 192)
        {
            i2 = k1 & 0x1f;
            j2 = 1;
        } else
        if ((k1 & 0xf0) == 224)
        {
            i2 = k1 & 0xf;
            j2 = 2;
        } else
        if ((k1 & 0xf8) == 240)
        {
            i2 = k1 & 7;
            j2 = 3;
        } else
        {
            _reportInvalidInitial(k1);
            i2 = 1;
            j2 = i2;
        }
        if (i1 + j2 > k)
        {
            _reportInvalidEOF(" in field name");
        }
        k2 = ai[i1 >> 2] >> (3 - (i1 & 3) << 3);
        i1++;
        if ((k2 & 0xc0) != 128)
        {
            _reportInvalidOther(k2);
        }
        k1 = i2 << 6 | k2 & 0x3f;
        if (j2 > 1)
        {
            i3 = ai[i1 >> 2] >> (3 - (i1 & 3) << 3);
            i1++;
            if ((i3 & 0xc0) != 128)
            {
                _reportInvalidOther(i3);
            }
            k1 = k1 << 6 | i3 & 0x3f;
            if (j2 > 2)
            {
                j3 = ai[i1 >> 2] >> (3 - (i1 & 3) << 3);
                i1++;
                if ((j3 & 0xc0) != 128)
                {
                    _reportInvalidOther(j3 & 0xff);
                }
                k1 = k1 << 6 | j3 & 0x3f;
            }
        }
        if (j2 <= 2)
        {
            break MISSING_BLOCK_LABEL_501;
        }
        l2 = k1 - 0x10000;
        if (j1 >= ac.length)
        {
            ac = _textBuffer.expandCurrentSegment();
        }
        l1 = j1 + 1;
        ac[j1] = (char)(55296 + (l2 >> 10));
        k1 = 0xdc00 | l2 & 0x3ff;
_L4:
        if (l1 >= ac.length)
        {
            ac = _textBuffer.expandCurrentSegment();
        }
        j1 = l1 + 1;
        ac[l1] = (char)k1;
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_50;
_L1:
        String s = new String(ac, 0, j1);
        if (j < 4)
        {
            ai[i - 1] = l;
        }
        return _symbols.addName(s, ai, i);
        l1 = j1;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private final Name findName(int i, int j)
        throws JsonParseException
    {
        Name name = _symbols.findName(i);
        if (name != null)
        {
            return name;
        } else
        {
            _quadBuffer[0] = i;
            return addName(_quadBuffer, 1, j);
        }
    }

    private final Name findName(int i, int j, int k)
        throws JsonParseException
    {
        Name name = _symbols.findName(i, j);
        if (name != null)
        {
            return name;
        } else
        {
            _quadBuffer[0] = i;
            _quadBuffer[1] = j;
            return addName(_quadBuffer, 2, k);
        }
    }

    private final Name findName(int ai[], int i, int j, int k)
        throws JsonParseException
    {
        if (i >= ai.length)
        {
            ai = growArrayBy(ai, ai.length);
            _quadBuffer = ai;
        }
        int l = i + 1;
        ai[i] = j;
        Name name = _symbols.findName(ai, l);
        if (name == null)
        {
            name = addName(ai, l, k);
        }
        return name;
    }

    public static int[] growArrayBy(int ai[], int i)
    {
        if (ai == null)
        {
            return new int[i];
        } else
        {
            int j = ai.length;
            int ai1[] = new int[j + i];
            System.arraycopy(ai, 0, ai1, 0, j);
            return ai1;
        }
    }

    private int nextByte()
        throws IOException, JsonParseException
    {
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        return 0xff & abyte0[i];
    }

    private final Name parseFieldName(int i, int j, int k)
        throws IOException, JsonParseException
    {
        return parseEscapedFieldName(_quadBuffer, 0, i, j, k);
    }

    private final Name parseFieldName(int i, int j, int k, int l)
        throws IOException, JsonParseException
    {
        _quadBuffer[0] = i;
        return parseEscapedFieldName(_quadBuffer, 1, j, k, l);
    }

    protected byte[] _decodeBase64(Base64Variant base64variant)
        throws IOException, JsonParseException
    {
        ByteArrayBuilder bytearraybuilder = _getByteArrayBuilder();
        do
        {
            int j;
            do
            {
                if (_inputPtr >= _inputEnd)
                {
                    loadMoreGuaranteed();
                }
                byte abyte0[] = _inputBuffer;
                int i = _inputPtr;
                _inputPtr = i + 1;
                j = 0xff & abyte0[i];
            } while (j <= 32);
            int k = base64variant.decodeBase64Char(j);
            if (k < 0)
            {
                if (j == 34)
                {
                    return bytearraybuilder.toByteArray();
                }
                k = _decodeBase64Escape(base64variant, j, 0);
                if (k < 0)
                {
                    continue;
                }
            }
            int l = k;
            if (_inputPtr >= _inputEnd)
            {
                loadMoreGuaranteed();
            }
            byte abyte1[] = _inputBuffer;
            int i1 = _inputPtr;
            _inputPtr = i1 + 1;
            int j1 = 0xff & abyte1[i1];
            int k1 = base64variant.decodeBase64Char(j1);
            if (k1 < 0)
            {
                k1 = _decodeBase64Escape(base64variant, j1, 1);
            }
            int l1 = k1 | l << 6;
            if (_inputPtr >= _inputEnd)
            {
                loadMoreGuaranteed();
            }
            byte abyte2[] = _inputBuffer;
            int i2 = _inputPtr;
            _inputPtr = i2 + 1;
            int j2 = 0xff & abyte2[i2];
            int k2 = base64variant.decodeBase64Char(j2);
            if (k2 < 0)
            {
                if (k2 != -2)
                {
                    if (j2 == 34 && !base64variant.usesPadding())
                    {
                        bytearraybuilder.append(l1 >> 4);
                        return bytearraybuilder.toByteArray();
                    }
                    k2 = _decodeBase64Escape(base64variant, j2, 2);
                }
                if (k2 == -2)
                {
                    if (_inputPtr >= _inputEnd)
                    {
                        loadMoreGuaranteed();
                    }
                    byte abyte4[] = _inputBuffer;
                    int l3 = _inputPtr;
                    _inputPtr = l3 + 1;
                    int i4 = 0xff & abyte4[l3];
                    if (!base64variant.usesPaddingChar(i4))
                    {
                        throw reportInvalidChar(base64variant, i4, 3, (new StringBuilder()).append("expected padding character '").append(base64variant.getPaddingChar()).append("'").toString());
                    }
                    bytearraybuilder.append(l1 >> 4);
                    continue;
                }
            }
            int l2 = k2 | l1 << 6;
            if (_inputPtr >= _inputEnd)
            {
                loadMoreGuaranteed();
            }
            byte abyte3[] = _inputBuffer;
            int i3 = _inputPtr;
            _inputPtr = i3 + 1;
            int j3 = 0xff & abyte3[i3];
            int k3 = base64variant.decodeBase64Char(j3);
            if (k3 < 0)
            {
                if (k3 != -2)
                {
                    if (j3 == 34 && !base64variant.usesPadding())
                    {
                        bytearraybuilder.appendTwoBytes(l2 >> 2);
                        return bytearraybuilder.toByteArray();
                    }
                    k3 = _decodeBase64Escape(base64variant, j3, 3);
                }
                if (k3 == -2)
                {
                    bytearraybuilder.appendTwoBytes(l2 >> 2);
                    continue;
                }
            }
            bytearraybuilder.appendThreeBytes(k3 | l2 << 6);
        } while (true);
    }

    protected int _decodeCharForError(int i)
        throws IOException, JsonParseException
    {
        int j = i;
        if (j < 0)
        {
            int k;
            int l;
            if ((j & 0xe0) == 192)
            {
                j &= 0x1f;
                k = 1;
            } else
            if ((j & 0xf0) == 224)
            {
                j &= 0xf;
                k = 2;
            } else
            if ((j & 0xf8) == 240)
            {
                j &= 7;
                k = 3;
            } else
            {
                _reportInvalidInitial(j & 0xff);
                k = 1;
            }
            l = nextByte();
            if ((l & 0xc0) != 128)
            {
                _reportInvalidOther(l & 0xff);
            }
            j = j << 6 | l & 0x3f;
            if (k > 1)
            {
                int i1 = nextByte();
                if ((i1 & 0xc0) != 128)
                {
                    _reportInvalidOther(i1 & 0xff);
                }
                j = j << 6 | i1 & 0x3f;
                if (k > 2)
                {
                    int j1 = nextByte();
                    if ((j1 & 0xc0) != 128)
                    {
                        _reportInvalidOther(j1 & 0xff);
                    }
                    j = j << 6 | j1 & 0x3f;
                }
            }
        }
        return j;
    }

    protected final char _decodeEscaped()
        throws IOException, JsonParseException
    {
        if (_inputPtr >= _inputEnd && !loadMore())
        {
            _reportInvalidEOF(" in character escape sequence");
        }
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        byte byte0 = abyte0[i];
        int j;
        switch (byte0)
        {
        default:
            return _handleUnrecognizedCharacterEscape((char)_decodeCharForError(byte0));

        case 98: // 'b'
            return '\b';

        case 116: // 't'
            return '\t';

        case 110: // 'n'
            return '\n';

        case 102: // 'f'
            return '\f';

        case 114: // 'r'
            return '\r';

        case 34: // '"'
        case 47: // '/'
        case 92: // '\\'
            return (char)byte0;

        case 117: // 'u'
            j = 0;
            break;
        }
        for (int k = 0; k < 4; k++)
        {
            if (_inputPtr >= _inputEnd && !loadMore())
            {
                _reportInvalidEOF(" in character escape sequence");
            }
            byte abyte1[] = _inputBuffer;
            int l = _inputPtr;
            _inputPtr = l + 1;
            byte byte1 = abyte1[l];
            int i1 = CharTypes.charToHex(byte1);
            if (i1 < 0)
            {
                _reportUnexpectedChar(byte1, "expected a hex-digit for character escape sequence");
            }
            j = i1 | j << 4;
        }

        return (char)j;
    }

    protected void _finishString()
        throws IOException, JsonParseException
    {
        int i = _inputPtr;
        if (i >= _inputEnd)
        {
            loadMoreGuaranteed();
            i = _inputPtr;
        }
        char ac[] = _textBuffer.emptyAndGetCurrentSegment();
        int ai[] = sInputCodesUtf8;
        int j = Math.min(_inputEnd, i + ac.length);
        byte abyte0[] = _inputBuffer;
        int k = 0;
        do
        {
            if (i >= j)
            {
                break;
            }
            int l = 0xff & abyte0[i];
            if (ai[l] != 0)
            {
                if (l == 34)
                {
                    _inputPtr = i + 1;
                    _textBuffer.setCurrentLength(k);
                    return;
                }
                break;
            }
            i++;
            int i1 = k + 1;
            ac[k] = (char)l;
            k = i1;
        } while (true);
        _inputPtr = i;
        _finishString2(ac, k);
    }

    protected final String _getText2(JsonToken jsontoken)
    {
        if (jsontoken == null)
        {
            return null;
        }
        static class _cls1
        {

            static final int $SwitchMap$org$codehaus$jackson$JsonToken[];

            static 
            {
                $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror3)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.org.codehaus.jackson.JsonToken[jsontoken.ordinal()])
        {
        default:
            return jsontoken.asString();

        case 1: // '\001'
            return _parsingContext.getCurrentName();

        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
            return _textBuffer.contentsAsString();
        }
    }

    protected JsonToken _handleApostropheValue()
        throws IOException, JsonParseException
    {
        int i;
        char ac[];
        int ai[];
        byte abyte0[];
        i = 0;
        ac = _textBuffer.emptyAndGetCurrentSegment();
        ai = sInputCodesUtf8;
        abyte0 = _inputBuffer;
_L7:
        int i1;
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        if (i >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            i = 0;
        }
        int j = _inputEnd;
        int k = _inputPtr + (ac.length - i);
        if (k < j)
        {
            j = k;
        }
        do
        {
            if (_inputPtr >= j)
            {
                continue; /* Loop/switch isn't completed */
            }
            int l = _inputPtr;
            _inputPtr = l + 1;
            i1 = 0xff & abyte0[l];
            if (i1 == 39 || ai[i1] != 0)
            {
                if (i1 == 39)
                {
                    _textBuffer.setCurrentLength(i);
                    return JsonToken.VALUE_STRING;
                }
                break;
            }
            int i2 = i + 1;
            ac[i] = (char)i1;
            i = i2;
        } while (true);
        ai[i1];
        JVM INSTR tableswitch 1 4: default 196
    //                   1 250
    //                   2 266
    //                   3 277
    //                   4 312;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_312;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        if (i1 < 32)
        {
            _throwUnquotedSpace(i1, "string value");
        }
        _reportInvalidChar(i1);
_L8:
        if (i >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            i = 0;
        }
        int l1 = i + 1;
        ac[i] = (char)i1;
        i = l1;
        if (true) goto _L7; else goto _L6
_L6:
        if (i1 != 34)
        {
            i1 = _decodeEscaped();
        }
          goto _L8
_L3:
        i1 = _decodeUtf8_2(i1);
          goto _L8
_L4:
        if (_inputEnd - _inputPtr >= 2)
        {
            i1 = _decodeUtf8_3fast(i1);
        } else
        {
            i1 = _decodeUtf8_3(i1);
        }
          goto _L8
        int j1 = _decodeUtf8_4(i1);
        int k1 = i + 1;
        ac[i] = (char)(0xd800 | j1 >> 10);
        if (k1 >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            i = 0;
        } else
        {
            i = k1;
        }
        i1 = 0xdc00 | j1 & 0x3ff;
          goto _L8
    }

    protected JsonToken _handleInvalidNumberStart(int i, boolean flag)
        throws IOException, JsonParseException
    {
        double d = (-1.0D / 0.0D);
        if (i != 73) goto _L2; else goto _L1
_L1:
        if (_inputPtr >= _inputEnd && !loadMore())
        {
            _reportInvalidEOFInValue();
        }
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        i = abyte0[j];
        if (i != 78) goto _L4; else goto _L3
_L3:
        String s1;
        if (flag)
        {
            s1 = "-INF";
        } else
        {
            s1 = "+INF";
        }
        if (_matchToken(s1, 3))
        {
            if (isEnabled(org.codehaus.jackson.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS))
            {
                if (!flag)
                {
                    d = (1.0D / 0.0D);
                }
                return resetAsNaN(s1, d);
            }
            _reportError((new StringBuilder()).append("Non-standard token '").append(s1).append("': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow").toString());
        }
_L2:
        reportUnexpectedNumberChar(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
_L4:
        if (i == 110)
        {
            String s;
            if (flag)
            {
                s = "-Infinity";
            } else
            {
                s = "+Infinity";
            }
            if (_matchToken(s, 3))
            {
                if (isEnabled(org.codehaus.jackson.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS))
                {
                    if (!flag)
                    {
                        d = (1.0D / 0.0D);
                    }
                    return resetAsNaN(s, d);
                }
                _reportError((new StringBuilder()).append("Non-standard token '").append(s).append("': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow").toString());
            }
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    protected JsonToken _handleUnexpectedValue(int i)
        throws IOException, JsonParseException
    {
        i;
        JVM INSTR lookupswitch 3: default 36
    //                   39: 46
    //                   43: 139
    //                   78: 61;
           goto _L1 _L2 _L3 _L4
_L1:
        _reportUnexpectedChar(i, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
_L2:
        if (isEnabled(org.codehaus.jackson.JsonParser.Feature.ALLOW_SINGLE_QUOTES))
        {
            return _handleApostropheValue();
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (_matchToken("NaN", 1))
        {
            if (isEnabled(org.codehaus.jackson.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS))
            {
                return resetAsNaN("NaN", (0.0D / 0.0D));
            }
            _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        }
        byte abyte1[] = _inputBuffer;
        int k = _inputPtr;
        _inputPtr = k + 1;
        _reportUnexpectedChar(0xff & abyte1[k], "expected 'NaN' or a valid value");
        if (true) goto _L1; else goto _L3
_L3:
        if (_inputPtr >= _inputEnd && !loadMore())
        {
            _reportInvalidEOFInValue();
        }
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        return _handleInvalidNumberStart(0xff & abyte0[j], false);
    }

    protected final Name _handleUnusualFieldName(int i)
        throws IOException, JsonParseException
    {
        if (i != 39 || !isEnabled(org.codehaus.jackson.JsonParser.Feature.ALLOW_SINGLE_QUOTES)) goto _L2; else goto _L1
_L1:
        Name name = _parseApostropheFieldName();
_L4:
        return name;
_L2:
        if (!isEnabled(org.codehaus.jackson.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES))
        {
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        }
        int ai[] = CharTypes.getInputCodeUtf8JsNames();
        if (ai[i] != 0)
        {
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int ai1[] = _quadBuffer;
        int j = 0;
        int k = 0;
        int l = 0;
        do
        {
            int i1;
label0:
            {
                if (k < 4)
                {
                    k++;
                    j = i | j << 8;
                    i1 = l;
                } else
                {
                    if (l >= ai1.length)
                    {
                        ai1 = growArrayBy(ai1, ai1.length);
                        _quadBuffer = ai1;
                    }
                    i1 = l + 1;
                    ai1[l] = j;
                    j = i;
                    k = 1;
                }
                if (_inputPtr >= _inputEnd && !loadMore())
                {
                    _reportInvalidEOF(" in field name");
                }
                i = 0xff & _inputBuffer[_inputPtr];
                if (ai[i] == 0)
                {
                    break label0;
                }
                if (k > 0)
                {
                    if (i1 >= ai1.length)
                    {
                        ai1 = growArrayBy(ai1, ai1.length);
                        _quadBuffer = ai1;
                    }
                    int j1 = i1 + 1;
                    ai1[i1] = j;
                    i1 = j1;
                }
                name = _symbols.findName(ai1, i1);
                if (name == null)
                {
                    return addName(ai1, i1, k);
                }
            }
            if (true)
            {
                continue;
            }
            _inputPtr = 1 + _inputPtr;
            l = i1;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void _matchToken(JsonToken jsontoken)
        throws IOException, JsonParseException
    {
        byte abyte0[] = jsontoken.asByteArray();
        int i = 1;
        for (int j = abyte0.length; i < j; i++)
        {
            if (_inputPtr >= _inputEnd)
            {
                loadMoreGuaranteed();
            }
            if (abyte0[i] != _inputBuffer[_inputPtr])
            {
                _reportInvalidToken(jsontoken.asString().substring(0, i), "'null', 'true' or 'false'");
            }
            _inputPtr = 1 + _inputPtr;
        }

    }

    protected final boolean _matchToken(String s, int i)
        throws IOException, JsonParseException
    {
        int j = s.length();
        do
        {
            if (_inputPtr >= _inputEnd && !loadMore())
            {
                _reportInvalidEOF(" in a value");
            }
            if (_inputBuffer[_inputPtr] != s.charAt(i))
            {
                _reportInvalidToken(s.substring(0, i), "'null', 'true', 'false' or NaN");
            }
            _inputPtr = 1 + _inputPtr;
        } while (++i < j);
        while (_inputPtr >= _inputEnd && !loadMore() || !Character.isJavaIdentifierPart((char)_decodeCharForError(0xff & _inputBuffer[_inputPtr]))) 
        {
            return true;
        }
        _inputPtr = 1 + _inputPtr;
        _reportInvalidToken(s.substring(0, i), "'null', 'true', 'false' or NaN");
        return true;
    }

    protected final Name _parseApostropheFieldName()
        throws IOException, JsonParseException
    {
        int j;
        if (_inputPtr >= _inputEnd && !loadMore())
        {
            _reportInvalidEOF(": was expecting closing ''' for name");
        }
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        j = 0xff & abyte0[i];
        if (j != 39) goto _L2; else goto _L1
_L1:
        Name name = BytesToNameCanonicalizer.getEmptyName();
_L8:
        return name;
_L2:
        int ai[];
        int k;
        int l;
        int ai1[];
        int i1;
        ai = _quadBuffer;
        k = 0;
        l = 0;
        ai1 = sInputCodesLatin1;
        i1 = 0;
_L6:
label0:
        {
            if (j != 39)
            {
                break label0;
            }
            int j1;
            byte abyte1[];
            int k1;
            int l1;
            int i2;
            int k2;
            int l2;
            if (l > 0)
            {
                if (i1 >= ai.length)
                {
                    ai = growArrayBy(ai, ai.length);
                    _quadBuffer = ai;
                }
                l2 = i1 + 1;
                ai[i1] = k;
            } else
            {
                l2 = i1;
            }
            name = _symbols.findName(ai, l2);
            if (name == null)
            {
                return addName(ai, l2, l);
            }
        }
        if (true) goto _L4; else goto _L3
_L4:
        continue; /* Loop/switch isn't completed */
_L3:
        if (j != 34 && ai1[j] != 0)
        {
            if (j != 92)
            {
                _throwUnquotedSpace(j, "name");
            } else
            {
                j = _decodeEscaped();
            }
            if (j > 127)
            {
                if (l >= 4)
                {
                    if (i1 >= ai.length)
                    {
                        ai = growArrayBy(ai, ai.length);
                        _quadBuffer = ai;
                    }
                    k2 = i1 + 1;
                    ai[i1] = k;
                    k = 0;
                    l = 0;
                    i1 = k2;
                }
                int j2;
                if (j < 2048)
                {
                    k = k << 8 | (0xc0 | j >> 6);
                    l++;
                    j2 = i1;
                } else
                {
                    l1 = k << 8 | (0xe0 | j >> 12);
                    i2 = l + 1;
                    if (i2 >= 4)
                    {
                        if (i1 >= ai.length)
                        {
                            ai = growArrayBy(ai, ai.length);
                            _quadBuffer = ai;
                        }
                        j2 = i1 + 1;
                        ai[i1] = l1;
                        l1 = 0;
                        i2 = 0;
                    } else
                    {
                        j2 = i1;
                    }
                    k = l1 << 8 | (0x80 | 0x3f & j >> 6);
                    l = i2 + 1;
                }
                j = 0x80 | j & 0x3f;
                i1 = j2;
            }
        }
        if (l < 4)
        {
            l++;
            k = j | k << 8;
            j1 = i1;
        } else
        {
            if (i1 >= ai.length)
            {
                ai = growArrayBy(ai, ai.length);
                _quadBuffer = ai;
            }
            j1 = i1 + 1;
            ai[i1] = k;
            k = j;
            l = 1;
        }
        if (_inputPtr >= _inputEnd && !loadMore())
        {
            _reportInvalidEOF(" in field name");
        }
        abyte1 = _inputBuffer;
        k1 = _inputPtr;
        _inputPtr = k1 + 1;
        j = 0xff & abyte1[k1];
        i1 = j1;
        if (true) goto _L6; else goto _L5
_L5:
        if (true) goto _L8; else goto _L7
_L7:
    }

    protected final Name _parseFieldName(int i)
        throws IOException, JsonParseException
    {
        if (i != 34)
        {
            return _handleUnusualFieldName(i);
        }
        if (9 + _inputPtr > _inputEnd)
        {
            return slowParseFieldName();
        }
        byte abyte0[] = _inputBuffer;
        int ai[] = sInputCodesLatin1;
        int j = _inputPtr;
        _inputPtr = j + 1;
        int k = 0xff & abyte0[j];
        if (ai[k] == 0)
        {
            int l = _inputPtr;
            _inputPtr = l + 1;
            int i1 = 0xff & abyte0[l];
            if (ai[i1] == 0)
            {
                int j1 = i1 | k << 8;
                int k1 = _inputPtr;
                _inputPtr = k1 + 1;
                int l1 = 0xff & abyte0[k1];
                if (ai[l1] == 0)
                {
                    int i2 = l1 | j1 << 8;
                    int j2 = _inputPtr;
                    _inputPtr = j2 + 1;
                    int k2 = 0xff & abyte0[j2];
                    if (ai[k2] == 0)
                    {
                        int l2 = k2 | i2 << 8;
                        int i3 = _inputPtr;
                        _inputPtr = i3 + 1;
                        int j3 = 0xff & abyte0[i3];
                        if (ai[j3] == 0)
                        {
                            _quad1 = l2;
                            return parseMediumFieldName(j3, ai);
                        }
                        if (j3 == 34)
                        {
                            return findName(l2, 4);
                        } else
                        {
                            return parseFieldName(l2, j3, 4);
                        }
                    }
                    if (k2 == 34)
                    {
                        return findName(i2, 3);
                    } else
                    {
                        return parseFieldName(i2, k2, 3);
                    }
                }
                if (l1 == 34)
                {
                    return findName(j1, 2);
                } else
                {
                    return parseFieldName(j1, l1, 2);
                }
            }
            if (i1 == 34)
            {
                return findName(k, 1);
            } else
            {
                return parseFieldName(k, i1, 1);
            }
        }
        if (k == 34)
        {
            return BytesToNameCanonicalizer.getEmptyName();
        } else
        {
            return parseFieldName(0, k, 0);
        }
    }

    protected void _reportInvalidChar(int i)
        throws JsonParseException
    {
        if (i < 32)
        {
            _throwInvalidSpace(i);
        }
        _reportInvalidInitial(i);
    }

    protected void _reportInvalidInitial(int i)
        throws JsonParseException
    {
        _reportError((new StringBuilder()).append("Invalid UTF-8 start byte 0x").append(Integer.toHexString(i)).toString());
    }

    protected void _reportInvalidOther(int i)
        throws JsonParseException
    {
        _reportError((new StringBuilder()).append("Invalid UTF-8 middle byte 0x").append(Integer.toHexString(i)).toString());
    }

    protected void _reportInvalidOther(int i, int j)
        throws JsonParseException
    {
        _inputPtr = j;
        _reportInvalidOther(i);
    }

    protected void _reportInvalidToken(String s, String s1)
        throws IOException, JsonParseException
    {
        StringBuilder stringbuilder = new StringBuilder(s);
_L5:
        if (_inputPtr < _inputEnd || loadMore()) goto _L2; else goto _L1
_L1:
        _reportError((new StringBuilder()).append("Unrecognized token '").append(stringbuilder.toString()).append("': was expecting ").append(s1).toString());
        return;
_L2:
        char c;
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        c = (char)_decodeCharForError(abyte0[i]);
        if (!Character.isJavaIdentifierPart(c)) goto _L1; else goto _L3
_L3:
        _inputPtr = 1 + _inputPtr;
        stringbuilder.append(c);
        if (true) goto _L5; else goto _L4
_L4:
    }

    protected final void _skipCR()
        throws IOException
    {
        if ((_inputPtr < _inputEnd || loadMore()) && _inputBuffer[_inputPtr] == 10)
        {
            _inputPtr = 1 + _inputPtr;
        }
        _currInputRow = 1 + _currInputRow;
        _currInputRowStart = _inputPtr;
    }

    protected final void _skipLF()
        throws IOException
    {
        _currInputRow = 1 + _currInputRow;
        _currInputRowStart = _inputPtr;
    }

    protected void _skipString()
        throws IOException, JsonParseException
    {
        int ai[];
        byte abyte0[];
        _tokenIncomplete = false;
        ai = sInputCodesUtf8;
        abyte0 = _inputBuffer;
_L2:
        do
        {
label0:
            {
                int i = _inputPtr;
                int j = _inputEnd;
                if (i < j)
                {
                    break MISSING_BLOCK_LABEL_198;
                }
                loadMoreGuaranteed();
                int i1 = _inputPtr;
                j = _inputEnd;
                int l;
                for (int k = i1; k < j; k = i)
                {
                    i = k + 1;
                    l = 0xff & abyte0[k];
                    if (ai[l] == 0)
                    {
                        break MISSING_BLOCK_LABEL_198;
                    }
                    _inputPtr = i;
                    if (l == 34)
                    {
                        return;
                    }
                    break label0;
                }

                _inputPtr = k;
            }
        } while (true);
        switch (ai[l])
        {
        default:
            if (l < 32)
            {
                _throwUnquotedSpace(l, "string value");
            } else
            {
                _reportInvalidChar(l);
            }
            break;

        case 1: // '\001'
            _decodeEscaped();
            break;

        case 2: // '\002'
            _skipUtf8_2(l);
            break;

        case 3: // '\003'
            _skipUtf8_3(l);
            break;

        case 4: // '\004'
            _skipUtf8_4(l);
            break;
        }
        continue; /* Loop/switch isn't completed */
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void close()
        throws IOException
    {
        super.close();
        _symbols.release();
    }

    public byte[] getBinaryValue(Base64Variant base64variant)
        throws IOException, JsonParseException
    {
        if (_currToken != JsonToken.VALUE_STRING && (_currToken != JsonToken.VALUE_EMBEDDED_OBJECT || _binaryValue == null))
        {
            _reportError((new StringBuilder()).append("Current token (").append(_currToken).append(") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary").toString());
        }
        if (_tokenIncomplete)
        {
            try
            {
                _binaryValue = _decodeBase64(base64variant);
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                throw _constructError((new StringBuilder()).append("Failed to decode VALUE_STRING as base64 (").append(base64variant).append("): ").append(illegalargumentexception.getMessage()).toString());
            }
            _tokenIncomplete = false;
        }
        return _binaryValue;
    }

    public ObjectCodec getCodec()
    {
        return _objectCodec;
    }

    public String getText()
        throws IOException, JsonParseException
    {
        JsonToken jsontoken = _currToken;
        if (jsontoken == JsonToken.VALUE_STRING)
        {
            if (_tokenIncomplete)
            {
                _tokenIncomplete = false;
                _finishString();
            }
            return _textBuffer.contentsAsString();
        } else
        {
            return _getText2(jsontoken);
        }
    }

    public char[] getTextCharacters()
        throws IOException, JsonParseException
    {
        if (_currToken == null)
        {
            break MISSING_BLOCK_LABEL_159;
        }
        _cls1..SwitchMap.org.codehaus.jackson.JsonToken[_currToken.ordinal()];
        JVM INSTR tableswitch 1 4: default 48
    //                   1 56
    //                   2 135
    //                   3 151
    //                   4 151;
           goto _L1 _L2 _L3 _L4 _L4
_L4:
        break; /* Loop/switch isn't completed */
_L1:
        return _currToken.asCharArray();
_L2:
        if (_nameCopied) goto _L6; else goto _L5
_L5:
        String s;
        int i;
        s = _parsingContext.getCurrentName();
        i = s.length();
        if (_nameCopyBuffer != null) goto _L8; else goto _L7
_L7:
        _nameCopyBuffer = _ioContext.allocNameCopyBuffer(i);
_L10:
        s.getChars(0, i, _nameCopyBuffer, 0);
        _nameCopied = true;
_L6:
        return _nameCopyBuffer;
_L8:
        if (_nameCopyBuffer.length < i)
        {
            _nameCopyBuffer = new char[i];
        }
        if (true) goto _L10; else goto _L9
_L9:
        break; /* Loop/switch isn't completed */
_L3:
        if (_tokenIncomplete)
        {
            _tokenIncomplete = false;
            _finishString();
        }
        return _textBuffer.getTextBuffer();
        return null;
    }

    public int getTextLength()
        throws IOException, JsonParseException
    {
label0:
        {
label1:
            {
label2:
                {
                    JsonToken jsontoken = _currToken;
                    int i = 0;
                    if (jsontoken != null)
                    {
                        switch (_cls1..SwitchMap.org.codehaus.jackson.JsonToken[_currToken.ordinal()])
                        {
                        default:
                            i = _currToken.asCharArray().length;
                            break;

                        case 1: // '\001'
                            break label2;

                        case 2: // '\002'
                            break label1;

                        case 3: // '\003'
                        case 4: // '\004'
                            break label0;
                        }
                    }
                    return i;
                }
                return _parsingContext.getCurrentName().length();
            }
            if (_tokenIncomplete)
            {
                _tokenIncomplete = false;
                _finishString();
            }
        }
        return _textBuffer.size();
    }

    public int getTextOffset()
        throws IOException, JsonParseException
    {
        if (_currToken == null) goto _L2; else goto _L1
_L1:
        _cls1..SwitchMap.org.codehaus.jackson.JsonToken[_currToken.ordinal()];
        JVM INSTR tableswitch 1 4: default 48
    //                   1 48
    //                   2 50
    //                   3 66
    //                   4 66;
           goto _L2 _L2 _L3 _L4 _L4
_L2:
        return 0;
_L3:
        if (_tokenIncomplete)
        {
            _tokenIncomplete = false;
            _finishString();
        }
_L4:
        return _textBuffer.getTextOffset();
    }

    public JsonToken nextToken()
        throws IOException, JsonParseException
    {
        int k;
        if (_currToken == JsonToken.FIELD_NAME)
        {
            return _nextAfterName();
        }
        if (_tokenIncomplete)
        {
            _skipString();
        }
        int i = _skipWSOrEnd();
        if (i < 0)
        {
            close();
            _currToken = null;
            return null;
        }
        _tokenInputTotal = (_currInputProcessed + (long)_inputPtr) - 1L;
        _tokenInputRow = _currInputRow;
        _tokenInputCol = -1 + (_inputPtr - _currInputRowStart);
        _binaryValue = null;
        if (i == 93)
        {
            if (!_parsingContext.inArray())
            {
                _reportMismatchedEndMarker(i, '}');
            }
            _parsingContext = _parsingContext.getParent();
            JsonToken jsontoken2 = JsonToken.END_ARRAY;
            _currToken = jsontoken2;
            return jsontoken2;
        }
        if (i == 125)
        {
            if (!_parsingContext.inObject())
            {
                _reportMismatchedEndMarker(i, ']');
            }
            _parsingContext = _parsingContext.getParent();
            JsonToken jsontoken1 = JsonToken.END_OBJECT;
            _currToken = jsontoken1;
            return jsontoken1;
        }
        if (_parsingContext.expectComma())
        {
            if (i != 44)
            {
                _reportUnexpectedChar(i, (new StringBuilder()).append("was expecting comma to separate ").append(_parsingContext.getTypeDesc()).append(" entries").toString());
            }
            i = _skipWS();
        }
        if (!_parsingContext.inObject())
        {
            return _nextTokenNotInObject(i);
        }
        Name name = _parseFieldName(i);
        _parsingContext.setCurrentName(name.getName());
        _currToken = JsonToken.FIELD_NAME;
        int j = _skipWS();
        if (j != 58)
        {
            _reportUnexpectedChar(j, "was expecting a colon to separate field name and value");
        }
        k = _skipWS();
        if (k == 34)
        {
            _tokenIncomplete = true;
            _nextToken = JsonToken.VALUE_STRING;
            return _currToken;
        }
        k;
        JVM INSTR lookupswitch 18: default 488
    //                   45: 576
    //                   48: 576
    //                   49: 576
    //                   50: 576
    //                   51: 576
    //                   52: 576
    //                   53: 576
    //                   54: 576
    //                   55: 576
    //                   56: 576
    //                   57: 576
    //                   91: 507
    //                   93: 523
    //                   102: 546
    //                   110: 561
    //                   116: 531
    //                   123: 515
    //                   125: 523;
           goto _L1 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L4
_L1:
        JsonToken jsontoken = _handleUnexpectedValue(k);
_L10:
        _nextToken = jsontoken;
        return _currToken;
_L3:
        jsontoken = JsonToken.START_ARRAY;
        continue; /* Loop/switch isn't completed */
_L8:
        jsontoken = JsonToken.START_OBJECT;
        continue; /* Loop/switch isn't completed */
_L4:
        _reportUnexpectedChar(k, "expected a value");
_L7:
        _matchToken(JsonToken.VALUE_TRUE);
        jsontoken = JsonToken.VALUE_TRUE;
        continue; /* Loop/switch isn't completed */
_L5:
        _matchToken(JsonToken.VALUE_FALSE);
        jsontoken = JsonToken.VALUE_FALSE;
        continue; /* Loop/switch isn't completed */
_L6:
        _matchToken(JsonToken.VALUE_NULL);
        jsontoken = JsonToken.VALUE_NULL;
        continue; /* Loop/switch isn't completed */
_L2:
        jsontoken = parseNumberText(k);
        if (true) goto _L10; else goto _L9
_L9:
    }

    protected Name parseEscapedFieldName(int ai[], int i, int j, int k, int l)
        throws IOException, JsonParseException
    {
        int ai1[] = sInputCodesLatin1;
_L2:
        int i1;
        if (ai1[k] == 0)
        {
            break; /* Loop/switch isn't completed */
        }
        if (k == 34)
        {
            if (l > 0)
            {
                if (i >= ai.length)
                {
                    ai = growArrayBy(ai, ai.length);
                    _quadBuffer = ai;
                }
                int k2 = i + 1;
                ai[i] = j;
                i = k2;
            }
            Name name = _symbols.findName(ai, i);
            if (name == null)
            {
                name = addName(ai, i, l);
            }
            return name;
        }
        byte abyte0[];
        int j1;
        int k1;
        int j2;
        if (k != 92)
        {
            _throwUnquotedSpace(k, "name");
        } else
        {
            k = _decodeEscaped();
        }
        if (k <= 127)
        {
            break; /* Loop/switch isn't completed */
        }
        if (l >= 4)
        {
            if (i >= ai.length)
            {
                ai = growArrayBy(ai, ai.length);
                _quadBuffer = ai;
            }
            k1 = i + 1;
            ai[i] = j;
            j = 0;
            l = 0;
        } else
        {
            k1 = i;
        }
        if (k < 2048)
        {
            j = j << 8 | (0xc0 | k >> 6);
            l++;
            j2 = k1;
        } else
        {
            int l1 = j << 8 | (0xe0 | k >> 12);
            int i2 = l + 1;
            if (i2 >= 4)
            {
                if (k1 >= ai.length)
                {
                    ai = growArrayBy(ai, ai.length);
                    _quadBuffer = ai;
                }
                j2 = k1 + 1;
                ai[k1] = l1;
                l1 = 0;
                i2 = 0;
            } else
            {
                j2 = k1;
            }
            j = l1 << 8 | (0x80 | 0x3f & k >> 6);
            l = i2 + 1;
        }
        k = 0x80 | k & 0x3f;
        i1 = j2;
_L3:
        if (l < 4)
        {
            l++;
            j = k | j << 8;
            i = i1;
        } else
        {
            if (i1 >= ai.length)
            {
                ai = growArrayBy(ai, ai.length);
                _quadBuffer = ai;
            }
            i = i1 + 1;
            ai[i1] = j;
            j = k;
            l = 1;
        }
        if (_inputPtr >= _inputEnd && !loadMore())
        {
            _reportInvalidEOF(" in field name");
        }
        abyte0 = _inputBuffer;
        j1 = _inputPtr;
        _inputPtr = j1 + 1;
        k = 0xff & abyte0[j1];
        if (true) goto _L2; else goto _L1
_L1:
        i1 = i;
          goto _L3
        if (true) goto _L2; else goto _L4
_L4:
    }

    protected Name parseLongFieldName(int i)
        throws IOException, JsonParseException
    {
        int ai[] = sInputCodesLatin1;
        int j = 2;
        do
        {
            if (_inputEnd - _inputPtr < 4)
            {
                return parseEscapedFieldName(_quadBuffer, j, 0, i, 0);
            }
            byte abyte0[] = _inputBuffer;
            int k = _inputPtr;
            _inputPtr = k + 1;
            int l = 0xff & abyte0[k];
            if (ai[l] != 0)
            {
                if (l == 34)
                {
                    return findName(_quadBuffer, j, i, 1);
                } else
                {
                    return parseEscapedFieldName(_quadBuffer, j, i, l, 1);
                }
            }
            int i1 = l | i << 8;
            byte abyte1[] = _inputBuffer;
            int j1 = _inputPtr;
            _inputPtr = j1 + 1;
            int k1 = 0xff & abyte1[j1];
            if (ai[k1] != 0)
            {
                if (k1 == 34)
                {
                    return findName(_quadBuffer, j, i1, 2);
                } else
                {
                    return parseEscapedFieldName(_quadBuffer, j, i1, k1, 2);
                }
            }
            int l1 = k1 | i1 << 8;
            byte abyte2[] = _inputBuffer;
            int i2 = _inputPtr;
            _inputPtr = i2 + 1;
            int j2 = 0xff & abyte2[i2];
            if (ai[j2] != 0)
            {
                if (j2 == 34)
                {
                    return findName(_quadBuffer, j, l1, 3);
                } else
                {
                    return parseEscapedFieldName(_quadBuffer, j, l1, j2, 3);
                }
            }
            int k2 = j2 | l1 << 8;
            byte abyte3[] = _inputBuffer;
            int l2 = _inputPtr;
            _inputPtr = l2 + 1;
            int i3 = 0xff & abyte3[l2];
            if (ai[i3] != 0)
            {
                if (i3 == 34)
                {
                    return findName(_quadBuffer, j, k2, 4);
                } else
                {
                    return parseEscapedFieldName(_quadBuffer, j, k2, i3, 4);
                }
            }
            if (j >= _quadBuffer.length)
            {
                _quadBuffer = growArrayBy(_quadBuffer, j);
            }
            int ai1[] = _quadBuffer;
            int j3 = j + 1;
            ai1[j] = k2;
            i = i3;
            j = j3;
        } while (true);
    }

    protected final Name parseMediumFieldName(int i, int ai[])
        throws IOException, JsonParseException
    {
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        int k = 0xff & abyte0[j];
        if (ai[k] != 0)
        {
            if (k == 34)
            {
                return findName(_quad1, i, 1);
            } else
            {
                return parseFieldName(_quad1, i, k, 1);
            }
        }
        int l = k | i << 8;
        byte abyte1[] = _inputBuffer;
        int i1 = _inputPtr;
        _inputPtr = i1 + 1;
        int j1 = 0xff & abyte1[i1];
        if (ai[j1] != 0)
        {
            if (j1 == 34)
            {
                return findName(_quad1, l, 2);
            } else
            {
                return parseFieldName(_quad1, l, j1, 2);
            }
        }
        int k1 = j1 | l << 8;
        byte abyte2[] = _inputBuffer;
        int l1 = _inputPtr;
        _inputPtr = l1 + 1;
        int i2 = 0xff & abyte2[l1];
        if (ai[i2] != 0)
        {
            if (i2 == 34)
            {
                return findName(_quad1, k1, 3);
            } else
            {
                return parseFieldName(_quad1, k1, i2, 3);
            }
        }
        int j2 = i2 | k1 << 8;
        byte abyte3[] = _inputBuffer;
        int k2 = _inputPtr;
        _inputPtr = k2 + 1;
        int l2 = 0xff & abyte3[k2];
        if (ai[l2] != 0)
        {
            if (l2 == 34)
            {
                return findName(_quad1, j2, 4);
            } else
            {
                return parseFieldName(_quad1, j2, l2, 4);
            }
        } else
        {
            _quadBuffer[0] = _quad1;
            _quadBuffer[1] = j2;
            return parseLongFieldName(l2);
        }
    }

    protected final JsonToken parseNumberText(int i)
        throws IOException, JsonParseException
    {
        char ac[] = _textBuffer.emptyAndGetCurrentSegment();
        boolean flag;
        int j;
        if (i == 45)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            j = 0 + 1;
            ac[0] = '-';
            if (_inputPtr >= _inputEnd)
            {
                loadMoreGuaranteed();
            }
            byte abyte1[] = _inputBuffer;
            int i2 = _inputPtr;
            _inputPtr = i2 + 1;
            i = 0xff & abyte1[i2];
            if (i < 48 || i > 57)
            {
                JsonToken jsontoken = _handleInvalidNumberStart(i, true);
                int _tmp = j;
                return jsontoken;
            }
        } else
        {
            j = 0;
        }
        if (i == 48)
        {
            i = _verifyNoLeadingZeroes();
        }
        int k = j + 1;
        ac[j] = (char)i;
        int l = 1;
        int i1 = _inputPtr + ac.length;
        if (i1 > _inputEnd)
        {
            i1 = _inputEnd;
        }
        do
        {
            if (_inputPtr >= i1)
            {
                return _parserNumber2(ac, k, flag, l);
            }
            byte abyte0[] = _inputBuffer;
            int j1 = _inputPtr;
            _inputPtr = j1 + 1;
            int k1 = 0xff & abyte0[j1];
            int l1;
            if (k1 < 48 || k1 > 57)
            {
                if (k1 == 46 || k1 == 101 || k1 == 69)
                {
                    return _parseFloatText(ac, k, k1, flag, l);
                } else
                {
                    _inputPtr = -1 + _inputPtr;
                    _textBuffer.setCurrentLength(k);
                    return resetInt(flag, l);
                }
            }
            l++;
            l1 = k + 1;
            ac[k] = (char)k1;
            k = l1;
        } while (true);
    }

    protected IllegalArgumentException reportInvalidChar(Base64Variant base64variant, int i, int j)
        throws IllegalArgumentException
    {
        return reportInvalidChar(base64variant, i, j, null);
    }

    protected IllegalArgumentException reportInvalidChar(Base64Variant base64variant, int i, int j, String s)
        throws IllegalArgumentException
    {
        String s1;
        if (i <= 32)
        {
            s1 = (new StringBuilder()).append("Illegal white space character (code 0x").append(Integer.toHexString(i)).append(") as character #").append(j + 1).append(" of 4-char base64 unit: can only used between units").toString();
        } else
        if (base64variant.usesPaddingChar(i))
        {
            s1 = (new StringBuilder()).append("Unexpected padding character ('").append(base64variant.getPaddingChar()).append("') as character #").append(j + 1).append(" of 4-char base64 unit: padding only legal as 3rd or 4th character").toString();
        } else
        if (!Character.isDefined(i) || Character.isISOControl(i))
        {
            s1 = (new StringBuilder()).append("Illegal character (code 0x").append(Integer.toHexString(i)).append(") in base64 content").toString();
        } else
        {
            s1 = (new StringBuilder()).append("Illegal character '").append((char)i).append("' (code 0x").append(Integer.toHexString(i)).append(") in base64 content").toString();
        }
        if (s != null)
        {
            s1 = (new StringBuilder()).append(s1).append(": ").append(s).toString();
        }
        return new IllegalArgumentException(s1);
    }

    public void setCodec(ObjectCodec objectcodec)
    {
        _objectCodec = objectcodec;
    }

    protected Name slowParseFieldName()
        throws IOException, JsonParseException
    {
        if (_inputPtr >= _inputEnd && !loadMore())
        {
            _reportInvalidEOF(": was expecting closing '\"' for name");
        }
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        int j = 0xff & abyte0[i];
        if (j == 34)
        {
            return BytesToNameCanonicalizer.getEmptyName();
        } else
        {
            return parseEscapedFieldName(_quadBuffer, 0, 0, j, 0);
        }
    }

}
