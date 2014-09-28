// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.impl;

import java.io.IOException;
import java.io.Reader;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.sym.CharsToNameCanonicalizer;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.CharTypes;
import org.codehaus.jackson.util.TextBuffer;

// Referenced classes of package org.codehaus.jackson.impl:
//            ReaderBasedNumericParser, JsonReadContext

public final class ReaderBasedParser extends ReaderBasedNumericParser
{

    protected ObjectCodec _objectCodec;
    protected final CharsToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;

    public ReaderBasedParser(IOContext iocontext, int i, Reader reader, ObjectCodec objectcodec, CharsToNameCanonicalizer charstonamecanonicalizer)
    {
        super(iocontext, i, reader);
        _tokenIncomplete = false;
        _objectCodec = objectcodec;
        _symbols = charstonamecanonicalizer;
    }

    private final int _decodeBase64Escape(Base64Variant base64variant, char c, int i)
        throws IOException, JsonParseException
    {
        if (c != '\\')
        {
            throw reportInvalidChar(base64variant, c, i);
        }
        char c1 = _decodeEscaped();
        int j;
        if (c1 <= ' ' && i == 0)
        {
            j = -1;
        } else
        {
            j = base64variant.decodeBase64Char(c1);
            if (j < 0)
            {
                throw reportInvalidChar(base64variant, c1, i);
            }
        }
        return j;
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

    private String _parseFieldName2(int i, int j, int k)
        throws IOException, JsonParseException
    {
        char ac[];
        int l;
        _textBuffer.resetWithShared(_inputBuffer, i, _inputPtr - i);
        ac = _textBuffer.getCurrentSegment();
        l = _textBuffer.getCurrentSegmentSize();
_L5:
        char c;
        char c1;
        if (_inputPtr >= _inputEnd && !loadMore())
        {
            _reportInvalidEOF((new StringBuilder()).append(": was expecting closing '").append((char)k).append("' for name").toString());
        }
        char ac1[] = _inputBuffer;
        int i1 = _inputPtr;
        _inputPtr = i1 + 1;
        c = ac1[i1];
        c1 = c;
        if (c1 > '\\') goto _L2; else goto _L1
_L1:
        if (c1 != '\\') goto _L4; else goto _L3
_L3:
        c = _decodeEscaped();
_L2:
        j = c1 + j * 31;
        int j1 = l + 1;
        ac[l] = c;
        TextBuffer textbuffer;
        char ac2[];
        int k1;
        int l1;
        if (j1 >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            l = 0;
        } else
        {
            l = j1;
        }
        if (true) goto _L5; else goto _L4
_L4:
        if (c1 <= k)
        {
            if (c1 == k)
            {
                _textBuffer.setCurrentLength(l);
                textbuffer = _textBuffer;
                ac2 = textbuffer.getTextBuffer();
                k1 = textbuffer.getTextOffset();
                l1 = textbuffer.size();
                return _symbols.findSymbol(ac2, k1, l1, j);
            }
            if (c1 < ' ')
            {
                _throwUnquotedSpace(c1, "name");
            }
        }
          goto _L2
    }

    private String _parseUnusualFieldName2(int i, int j, int ai[])
        throws IOException, JsonParseException
    {
        char ac[];
        int k;
        int l;
        _textBuffer.resetWithShared(_inputBuffer, i, _inputPtr - i);
        ac = _textBuffer.getCurrentSegment();
        k = _textBuffer.getCurrentSegmentSize();
        l = ai.length;
_L5:
        if (_inputPtr < _inputEnd || loadMore()) goto _L2; else goto _L1
_L1:
        char c;
        _textBuffer.setCurrentLength(k);
        TextBuffer textbuffer = _textBuffer;
        char ac1[] = textbuffer.getTextBuffer();
        int i1 = textbuffer.getTextOffset();
        int j1 = textbuffer.size();
        return _symbols.findSymbol(ac1, i1, j1, j);
_L2:
        int k1;
        if ((c = _inputBuffer[_inputPtr]) > l ? Character.isJavaIdentifierPart(c) : ai[c] == 0) goto _L3; else goto _L1
_L3:
        _inputPtr = 1 + _inputPtr;
        j = c + j * 31;
        k1 = k + 1;
        ac[k] = c;
        if (k1 >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            k = 0;
        } else
        {
            k = k1;
        }
        if (true) goto _L5; else goto _L4
_L4:
    }

    private final void _skipCComment()
        throws IOException, JsonParseException
    {
        do
        {
            char c;
label0:
            {
label1:
                {
                    if (_inputPtr < _inputEnd || loadMore())
                    {
                        char ac[] = _inputBuffer;
                        int i = _inputPtr;
                        _inputPtr = i + 1;
                        c = ac[i];
                        if (c > '*')
                        {
                            continue;
                        }
                        if (c != '*')
                        {
                            break label0;
                        }
                        if (_inputPtr < _inputEnd || loadMore())
                        {
                            break label1;
                        }
                    }
                    _reportInvalidEOF(" in a comment");
                    return;
                }
                if (_inputBuffer[_inputPtr] == '/')
                {
                    _inputPtr = 1 + _inputPtr;
                    return;
                }
                continue;
            }
            if (c < ' ')
            {
                if (c == '\n')
                {
                    _skipLF();
                } else
                if (c == '\r')
                {
                    _skipCR();
                } else
                if (c != '\t')
                {
                    _throwInvalidSpace(c);
                }
            }
        } while (true);
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
        char ac[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        char c = ac[i];
        if (c == '/')
        {
            _skipCppComment();
            return;
        }
        if (c == '*')
        {
            _skipCComment();
            return;
        } else
        {
            _reportUnexpectedChar(c, "was expecting either '*' or '/' for a comment");
            return;
        }
    }

    private final void _skipCppComment()
        throws IOException, JsonParseException
    {
        do
        {
            char c;
label0:
            {
                if (_inputPtr < _inputEnd || loadMore())
                {
                    char ac[] = _inputBuffer;
                    int i = _inputPtr;
                    _inputPtr = i + 1;
                    c = ac[i];
                    if (c >= ' ')
                    {
                        continue;
                    }
                    if (c != '\n')
                    {
                        break label0;
                    }
                    _skipLF();
                }
                return;
            }
            if (c == '\r')
            {
                _skipCR();
                return;
            }
            if (c != '\t')
            {
                _throwInvalidSpace(c);
            }
        } while (true);
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
            char ac[] = _inputBuffer;
            int i = _inputPtr;
            _inputPtr = i + 1;
            char c = ac[i];
            if (c > ' ')
            {
                if (c != '/')
                {
                    return c;
                }
                _skipComment();
            } else
            if (c != ' ')
            {
                if (c == '\n')
                {
                    _skipLF();
                } else
                if (c == '\r')
                {
                    _skipCR();
                } else
                if (c != '\t')
                {
                    _throwInvalidSpace(c);
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
            char ac[] = _inputBuffer;
            int i = _inputPtr;
            _inputPtr = i + 1;
            char c = ac[i];
            if (c > ' ')
            {
                if (c != '/')
                {
                    return c;
                }
                _skipComment();
            } else
            if (c != ' ')
            {
                if (c == '\n')
                {
                    _skipLF();
                } else
                if (c == '\r')
                {
                    _skipCR();
                } else
                if (c != '\t')
                {
                    _throwInvalidSpace(c);
                }
            }
        } while (true);
        _handleEOF();
        return -1;
    }

    protected byte[] _decodeBase64(Base64Variant base64variant)
        throws IOException, JsonParseException
    {
        ByteArrayBuilder bytearraybuilder = _getByteArrayBuilder();
        do
        {
            char c;
            do
            {
                if (_inputPtr >= _inputEnd)
                {
                    loadMoreGuaranteed();
                }
                char ac[] = _inputBuffer;
                int i = _inputPtr;
                _inputPtr = i + 1;
                c = ac[i];
            } while (c <= ' ');
            int j = base64variant.decodeBase64Char(c);
            if (j < 0)
            {
                if (c == '"')
                {
                    return bytearraybuilder.toByteArray();
                }
                j = _decodeBase64Escape(base64variant, c, 0);
                if (j < 0)
                {
                    continue;
                }
            }
            int k = j;
            if (_inputPtr >= _inputEnd)
            {
                loadMoreGuaranteed();
            }
            char ac1[] = _inputBuffer;
            int l = _inputPtr;
            _inputPtr = l + 1;
            char c1 = ac1[l];
            int i1 = base64variant.decodeBase64Char(c1);
            if (i1 < 0)
            {
                i1 = _decodeBase64Escape(base64variant, c1, 1);
            }
            int j1 = i1 | k << 6;
            if (_inputPtr >= _inputEnd)
            {
                loadMoreGuaranteed();
            }
            char ac2[] = _inputBuffer;
            int k1 = _inputPtr;
            _inputPtr = k1 + 1;
            char c2 = ac2[k1];
            int l1 = base64variant.decodeBase64Char(c2);
            if (l1 < 0)
            {
                if (l1 != -2)
                {
                    if (c2 == '"' && !base64variant.usesPadding())
                    {
                        bytearraybuilder.append(j1 >> 4);
                        return bytearraybuilder.toByteArray();
                    }
                    l1 = _decodeBase64Escape(base64variant, c2, 2);
                }
                if (l1 == -2)
                {
                    if (_inputPtr >= _inputEnd)
                    {
                        loadMoreGuaranteed();
                    }
                    char ac4[] = _inputBuffer;
                    int l2 = _inputPtr;
                    _inputPtr = l2 + 1;
                    char c4 = ac4[l2];
                    if (!base64variant.usesPaddingChar(c4))
                    {
                        throw reportInvalidChar(base64variant, c4, 3, (new StringBuilder()).append("expected padding character '").append(base64variant.getPaddingChar()).append("'").toString());
                    }
                    bytearraybuilder.append(j1 >> 4);
                    continue;
                }
            }
            int i2 = l1 | j1 << 6;
            if (_inputPtr >= _inputEnd)
            {
                loadMoreGuaranteed();
            }
            char ac3[] = _inputBuffer;
            int j2 = _inputPtr;
            _inputPtr = j2 + 1;
            char c3 = ac3[j2];
            int k2 = base64variant.decodeBase64Char(c3);
            if (k2 < 0)
            {
                if (k2 != -2)
                {
                    if (c3 == '"' && !base64variant.usesPadding())
                    {
                        bytearraybuilder.appendTwoBytes(i2 >> 2);
                        return bytearraybuilder.toByteArray();
                    }
                    k2 = _decodeBase64Escape(base64variant, c3, 3);
                }
                if (k2 == -2)
                {
                    bytearraybuilder.appendTwoBytes(i2 >> 2);
                    continue;
                }
            }
            bytearraybuilder.appendThreeBytes(k2 | i2 << 6);
        } while (true);
    }

    protected final char _decodeEscaped()
        throws IOException, JsonParseException
    {
        if (_inputPtr >= _inputEnd && !loadMore())
        {
            _reportInvalidEOF(" in character escape sequence");
        }
        char ac[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        char c = ac[i];
        int j;
        switch (c)
        {
        default:
            c = _handleUnrecognizedCharacterEscape(c);
            // fall through

        case 34: // '"'
        case 47: // '/'
        case 92: // '\\'
            return c;

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
            char ac1[] = _inputBuffer;
            int l = _inputPtr;
            _inputPtr = l + 1;
            char c1 = ac1[l];
            int i1 = CharTypes.charToHex(c1);
            if (i1 < 0)
            {
                _reportUnexpectedChar(c1, "expected a hex-digit for character escape sequence");
            }
            j = i1 | j << 4;
        }

        return (char)j;
    }

    protected void _finishString()
        throws IOException, JsonParseException
    {
        int i = _inputPtr;
        int j = _inputEnd;
        if (i < j)
        {
            int ai[] = CharTypes.getInputCodeLatin1();
            int k = ai.length;
            do
            {
                char c = _inputBuffer[i];
                if (c >= k || ai[c] == 0)
                {
                    continue;
                }
                if (c == '"')
                {
                    _textBuffer.resetWithShared(_inputBuffer, _inputPtr, i - _inputPtr);
                    _inputPtr = i + 1;
                    return;
                }
                break;
            } while (++i < j);
        }
        _textBuffer.resetWithCopy(_inputBuffer, _inputPtr, i - _inputPtr);
        _inputPtr = i;
        _finishString2();
    }

    protected void _finishString2()
        throws IOException, JsonParseException
    {
        char ac[];
        int i;
        ac = _textBuffer.getCurrentSegment();
        i = _textBuffer.getCurrentSegmentSize();
_L2:
        char c;
        char c1;
        if (_inputPtr >= _inputEnd && !loadMore())
        {
            _reportInvalidEOF(": was expecting closing quote for a string value");
        }
        char ac1[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        c = ac1[j];
        c1 = c;
        if (c1 <= '\\')
        {
            if (c1 != '\\')
            {
                break; /* Loop/switch isn't completed */
            }
            c = _decodeEscaped();
        }
_L4:
        if (i >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            i = 0;
        }
        int k = i + 1;
        ac[i] = c;
        i = k;
        if (true) goto _L2; else goto _L1
_L1:
        if (c1 > '"') goto _L4; else goto _L3
_L3:
        if (c1 == '"')
        {
            _textBuffer.setCurrentLength(i);
            return;
        }
        if (c1 < ' ')
        {
            _throwUnquotedSpace(c1, "string value");
        }
          goto _L4
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

    protected final JsonToken _handleApostropheValue()
        throws IOException, JsonParseException
    {
        char ac[];
        int i;
        ac = _textBuffer.emptyAndGetCurrentSegment();
        i = _textBuffer.getCurrentSegmentSize();
_L2:
        char c;
        char c1;
        if (_inputPtr >= _inputEnd && !loadMore())
        {
            _reportInvalidEOF(": was expecting closing quote for a string value");
        }
        char ac1[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        c = ac1[j];
        c1 = c;
        if (c1 <= '\\')
        {
            if (c1 != '\\')
            {
                break; /* Loop/switch isn't completed */
            }
            c = _decodeEscaped();
        }
_L4:
        if (i >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            i = 0;
        }
        int k = i + 1;
        ac[i] = c;
        i = k;
        if (true) goto _L2; else goto _L1
_L1:
        if (c1 > '\'') goto _L4; else goto _L3
_L3:
        if (c1 == '\'')
        {
            _textBuffer.setCurrentLength(i);
            return JsonToken.VALUE_STRING;
        }
        if (c1 < ' ')
        {
            _throwUnquotedSpace(c1, "string value");
        }
          goto _L4
    }

    protected final JsonToken _handleUnexpectedValue(int i)
        throws IOException, JsonParseException
    {
        i;
        JVM INSTR lookupswitch 3: default 36
    //                   39: 46
    //                   43: 135
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
        char ac1[] = _inputBuffer;
        int k = _inputPtr;
        _inputPtr = k + 1;
        _reportUnexpectedChar(ac1[k], "expected 'NaN' or a valid value");
        if (true) goto _L1; else goto _L3
_L3:
        if (_inputPtr >= _inputEnd && !loadMore())
        {
            _reportInvalidEOFInValue();
        }
        char ac[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        return _handleInvalidNumberStart(ac[j], false);
    }

    protected final String _handleUnusualFieldName(int i)
        throws IOException, JsonParseException
    {
        if (i == 39 && isEnabled(org.codehaus.jackson.JsonParser.Feature.ALLOW_SINGLE_QUOTES))
        {
            return _parseApostropheFieldName();
        }
        if (!isEnabled(org.codehaus.jackson.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES))
        {
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        }
        int ai[] = CharTypes.getInputCodeLatin1JsNames();
        int j = ai.length;
        boolean flag;
        int k;
        int l;
        int i1;
        if (i < j)
        {
            if (ai[i] == 0 && (i < 48 || i > 57))
            {
                flag = true;
            } else
            {
                flag = false;
            }
        } else
        {
            flag = Character.isJavaIdentifierPart((char)i);
        }
        if (!flag)
        {
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        k = _inputPtr;
        l = _inputEnd;
        i1 = 0;
        if (k < l)
        {
            do
            {
                char c = _inputBuffer[k];
                if (c < j)
                {
                    if (ai[c] != 0)
                    {
                        int l1 = -1 + _inputPtr;
                        _inputPtr = k;
                        return _symbols.findSymbol(_inputBuffer, l1, k - l1, i1);
                    }
                } else
                if (!Character.isJavaIdentifierPart(c))
                {
                    int k1 = -1 + _inputPtr;
                    _inputPtr = k;
                    return _symbols.findSymbol(_inputBuffer, k1, k - k1, i1);
                }
                i1 = c + i1 * 31;
            } while (++k < l);
        }
        int j1 = -1 + _inputPtr;
        _inputPtr = k;
        return _parseUnusualFieldName2(j1, i1, ai);
    }

    protected void _matchToken(JsonToken jsontoken)
        throws IOException, JsonParseException
    {
        String s = jsontoken.asString();
        int i = 1;
        for (int j = s.length(); i < j; i++)
        {
            if (_inputPtr >= _inputEnd && !loadMore())
            {
                _reportInvalidEOF(" in a value");
            }
            if (_inputBuffer[_inputPtr] != s.charAt(i))
            {
                _reportInvalidToken(s.substring(0, i), "'null', 'true' or 'false'");
            }
            _inputPtr = 1 + _inputPtr;
        }

    }

    protected final String _parseApostropheFieldName()
        throws IOException, JsonParseException
    {
        int i;
        int j;
        int k;
        i = _inputPtr;
        j = _inputEnd;
        k = 0;
        if (i >= j) goto _L2; else goto _L1
_L1:
        int ai[];
        int i1;
        ai = CharTypes.getInputCodeLatin1();
        i1 = ai.length;
_L4:
        char c;
        c = _inputBuffer[i];
        if (c == '\'')
        {
            int j1 = _inputPtr;
            _inputPtr = i + 1;
            return _symbols.findSymbol(_inputBuffer, j1, i - j1, k);
        }
        if (c >= i1 || ai[c] == 0) goto _L3; else goto _L2
_L2:
        int l = _inputPtr;
        _inputPtr = i;
        return _parseFieldName2(l, k, 39);
_L3:
        k = c + k * 31;
        if (++i < j) goto _L4; else goto _L2
    }

    protected final String _parseFieldName(int i)
        throws IOException, JsonParseException
    {
        if (i != 34)
        {
            return _handleUnusualFieldName(i);
        }
        int j = _inputPtr;
        int k = _inputEnd;
        int l = 0;
        if (j < k)
        {
            int ai[] = CharTypes.getInputCodeLatin1();
            int j1 = ai.length;
            do
            {
                char c = _inputBuffer[j];
                if (c < j1 && ai[c] != 0)
                {
                    if (c == '"')
                    {
                        int k1 = _inputPtr;
                        _inputPtr = j + 1;
                        return _symbols.findSymbol(_inputBuffer, k1, j - k1, l);
                    }
                    break;
                }
                l = c + l * 31;
            } while (++j < k);
        }
        int i1 = _inputPtr;
        _inputPtr = j;
        return _parseFieldName2(i1, l, 34);
    }

    protected final void _skipCR()
        throws IOException
    {
        if ((_inputPtr < _inputEnd || loadMore()) && _inputBuffer[_inputPtr] == '\n')
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
        _tokenIncomplete = false;
        int i = _inputPtr;
        int j = _inputEnd;
        char ac[] = _inputBuffer;
        do
        {
            if (i >= j)
            {
                _inputPtr = i;
                if (!loadMore())
                {
                    _reportInvalidEOF(": was expecting closing quote for a string value");
                }
                i = _inputPtr;
                j = _inputEnd;
            }
            int k = i + 1;
            char c = ac[i];
            if (c <= '\\')
            {
                if (c == '\\')
                {
                    _inputPtr = k;
                    _decodeEscaped();
                    i = _inputPtr;
                    j = _inputEnd;
                    continue;
                }
                if (c <= '"')
                {
                    if (c == '"')
                    {
                        _inputPtr = k;
                        return;
                    }
                    if (c < ' ')
                    {
                        _inputPtr = k;
                        _throwUnquotedSpace(c, "string value");
                    }
                }
            }
            i = k;
        } while (true);
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

    public final String getText()
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
        int i;
        boolean flag;
        if (_currToken == JsonToken.FIELD_NAME)
        {
            return _nextAfterName();
        }
        if (_tokenIncomplete)
        {
            _skipString();
        }
        i = _skipWSOrEnd();
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
        flag = _parsingContext.inObject();
        if (flag)
        {
            String s = _parseFieldName(i);
            _parsingContext.setCurrentName(s);
            _currToken = JsonToken.FIELD_NAME;
            int j = _skipWS();
            if (j != 58)
            {
                _reportUnexpectedChar(j, "was expecting a colon to separate field name and value");
            }
            i = _skipWS();
        }
        i;
        JVM INSTR lookupswitch 19: default 468
    //                   34: 488
    //                   45: 610
    //                   48: 610
    //                   49: 610
    //                   50: 610
    //                   51: 610
    //                   52: 610
    //                   53: 610
    //                   54: 610
    //                   55: 610
    //                   56: 610
    //                   57: 610
    //                   91: 500
    //                   93: 560
    //                   102: 582
    //                   110: 596
    //                   116: 568
    //                   123: 530
    //                   125: 560;
           goto _L1 _L2 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L5
_L3:
        break MISSING_BLOCK_LABEL_610;
_L1:
        JsonToken jsontoken = _handleUnexpectedValue(i);
_L10:
        if (flag)
        {
            _nextToken = jsontoken;
            return _currToken;
        } else
        {
            _currToken = jsontoken;
            return jsontoken;
        }
_L2:
        _tokenIncomplete = true;
        jsontoken = JsonToken.VALUE_STRING;
          goto _L10
_L4:
        if (!flag)
        {
            _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
        }
        jsontoken = JsonToken.START_ARRAY;
          goto _L10
_L9:
        if (!flag)
        {
            _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
        }
        jsontoken = JsonToken.START_OBJECT;
          goto _L10
_L5:
        _reportUnexpectedChar(i, "expected a value");
_L8:
        _matchToken(JsonToken.VALUE_TRUE);
        jsontoken = JsonToken.VALUE_TRUE;
          goto _L10
_L6:
        _matchToken(JsonToken.VALUE_FALSE);
        jsontoken = JsonToken.VALUE_FALSE;
          goto _L10
_L7:
        _matchToken(JsonToken.VALUE_NULL);
        jsontoken = JsonToken.VALUE_NULL;
          goto _L10
        jsontoken = parseNumberText(i);
          goto _L10
    }

    protected IllegalArgumentException reportInvalidChar(Base64Variant base64variant, char c, int i)
        throws IllegalArgumentException
    {
        return reportInvalidChar(base64variant, c, i, null);
    }

    protected IllegalArgumentException reportInvalidChar(Base64Variant base64variant, char c, int i, String s)
        throws IllegalArgumentException
    {
        String s1;
        if (c <= ' ')
        {
            s1 = (new StringBuilder()).append("Illegal white space character (code 0x").append(Integer.toHexString(c)).append(") as character #").append(i + 1).append(" of 4-char base64 unit: can only used between units").toString();
        } else
        if (base64variant.usesPaddingChar(c))
        {
            s1 = (new StringBuilder()).append("Unexpected padding character ('").append(base64variant.getPaddingChar()).append("') as character #").append(i + 1).append(" of 4-char base64 unit: padding only legal as 3rd or 4th character").toString();
        } else
        if (!Character.isDefined(c) || Character.isISOControl(c))
        {
            s1 = (new StringBuilder()).append("Illegal character (code 0x").append(Integer.toHexString(c)).append(") in base64 content").toString();
        } else
        {
            s1 = (new StringBuilder()).append("Illegal character '").append(c).append("' (code 0x").append(Integer.toHexString(c)).append(") in base64 content").toString();
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
}
