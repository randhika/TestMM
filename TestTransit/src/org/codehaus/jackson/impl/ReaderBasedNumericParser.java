// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.impl;

import java.io.IOException;
import java.io.Reader;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.util.TextBuffer;

// Referenced classes of package org.codehaus.jackson.impl:
//            ReaderBasedParserBase

public abstract class ReaderBasedNumericParser extends ReaderBasedParserBase
{

    public ReaderBasedNumericParser(IOContext iocontext, int i, Reader reader)
    {
        super(iocontext, i, reader);
    }

    private final char _verifyNoLeadingZeroes()
        throws IOException, JsonParseException
    {
        if (_inputPtr < _inputEnd || loadMore()) goto _L2; else goto _L1
_L1:
        char c = '0';
_L4:
        return c;
_L2:
        c = _inputBuffer[_inputPtr];
        if (c < '0' || c > '9')
        {
            return '0';
        }
        if (!isEnabled(org.codehaus.jackson.JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS))
        {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        _inputPtr = 1 + _inputPtr;
        if (c != '0')
        {
            continue; /* Loop/switch isn't completed */
        }
        do
        {
            if (_inputPtr >= _inputEnd && !loadMore())
            {
                continue; /* Loop/switch isn't completed */
            }
            c = _inputBuffer[_inputPtr];
            if (c < '0' || c > '9')
            {
                return '0';
            }
            _inputPtr = 1 + _inputPtr;
        } while (c == '0');
        break; /* Loop/switch isn't completed */
        if (true) goto _L4; else goto _L3
_L3:
        return c;
    }

    private final JsonToken parseNumberText2(boolean flag)
        throws IOException, JsonParseException
    {
        char ac[];
        int i;
        char c;
        int k;
        boolean flag1;
        int l;
        int i1;
        int j1;
        ac = _textBuffer.emptyAndGetCurrentSegment();
        i = 0;
        if (flag)
        {
            int j4 = 0 + 1;
            ac[0] = '-';
            i = j4;
        }
        int j = 0;
        int i2;
        char ac2[];
        int l2;
        char ac3[];
        int i3;
        if (_inputPtr < _inputEnd)
        {
            char ac6[] = _inputBuffer;
            int i4 = _inputPtr;
            _inputPtr = i4 + 1;
            c = ac6[i4];
        } else
        {
            c = getNextChar("No digit following minus sign");
        }
        if (c == '0')
        {
            c = _verifyNoLeadingZeroes();
        }
_L14:
        if (c < '0' || c > '9')
        {
            break MISSING_BLOCK_LABEL_744;
        }
        j++;
        if (i >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            i = 0;
        }
        k = i + 1;
        ac[i] = c;
        if (_inputPtr < _inputEnd || loadMore()) goto _L2; else goto _L1
_L1:
        c = '\0';
        flag1 = true;
_L19:
        if (j == 0)
        {
            reportInvalidNumber((new StringBuilder()).append("Missing integer part (next char ").append(_getCharDesc(c)).append(")").toString());
        }
        l = 0;
        if (c != '.') goto _L4; else goto _L3
_L3:
        i1 = k + 1;
        ac[k] = c;
_L17:
        if (_inputPtr < _inputEnd || loadMore()) goto _L6; else goto _L5
_L5:
        flag1 = true;
_L16:
        if (l == 0)
        {
            reportUnexpectedNumberChar(c, "Decimal point not followed by a digit");
        }
_L18:
        j1 = 0;
        if (c == 'e') goto _L8; else goto _L7
_L7:
        j1 = 0;
        if (c != 'E') goto _L9; else goto _L8
_L8:
        if (i1 >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            i1 = 0;
        }
        int k1 = i1 + 1;
        ac[i1] = c;
        char c1;
        if (_inputPtr < _inputEnd)
        {
            ac3 = _inputBuffer;
            i3 = _inputPtr;
            _inputPtr = i3 + 1;
            c1 = ac3[i3];
        } else
        {
            c1 = getNextChar("expected a digit for number exponent");
        }
        if (c1 == '-' || c1 == '+')
        {
            int l1;
            int j2;
            char ac1[];
            int k2;
            char ac4[];
            int j3;
            int k3;
            char ac5[];
            int l3;
            if (k1 >= ac.length)
            {
                ac = _textBuffer.finishCurrentSegment();
                l1 = 0;
            } else
            {
                l1 = k1;
            }
            i2 = l1 + 1;
            ac[l1] = c1;
            if (_inputPtr < _inputEnd)
            {
                ac2 = _inputBuffer;
                l2 = _inputPtr;
                _inputPtr = l2 + 1;
                c1 = ac2[l2];
            } else
            {
                c1 = getNextChar("expected a digit for number exponent");
            }
            i1 = i2;
        } else
        {
            i1 = k1;
            j1 = 0;
        }
        if (c1 > '9' || c1 < '0') goto _L11; else goto _L10
_L10:
        j1++;
        if (i1 >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            i1 = 0;
        }
        j2 = i1 + 1;
        ac[i1] = c1;
        if (_inputPtr < _inputEnd || loadMore()) goto _L13; else goto _L12
_L12:
        flag1 = true;
        i1 = j2;
_L11:
        if (j1 == 0)
        {
            reportUnexpectedNumberChar(c1, "Exponent indicator not followed by a digit");
        }
_L9:
        if (!flag1)
        {
            _inputPtr = -1 + _inputPtr;
        }
        _textBuffer.setCurrentLength(i1);
        return reset(flag, j, l, j1);
_L2:
        ac5 = _inputBuffer;
        l3 = _inputPtr;
        _inputPtr = l3 + 1;
        c = ac5[l3];
        i = k;
          goto _L14
_L6:
        ac4 = _inputBuffer;
        j3 = _inputPtr;
        _inputPtr = j3 + 1;
        c = ac4[j3];
        if (c < '0' || c > '9') goto _L16; else goto _L15
_L15:
        l++;
        if (i1 >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            i1 = 0;
        }
        k3 = i1 + 1;
        ac[i1] = c;
        i1 = k3;
          goto _L17
_L13:
        ac1 = _inputBuffer;
        k2 = _inputPtr;
        _inputPtr = k2 + 1;
        c1 = ac1[k2];
        i1 = j2;
        break MISSING_BLOCK_LABEL_415;
_L4:
        i1 = k;
        l = 0;
          goto _L18
        k = i;
        flag1 = false;
          goto _L19
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
        char ac[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        i = ac[j];
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

    protected final JsonToken parseNumberText(int i)
        throws IOException, JsonParseException
    {
        boolean flag;
        int j;
        int k;
        int l;
        if (i == 45)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        j = _inputPtr;
        k = j - 1;
        l = _inputEnd;
        if (!flag) goto _L2; else goto _L1
_L1:
        if (j < _inputEnd) goto _L4; else goto _L3
_L3:
        if (flag)
        {
            k++;
        }
        _inputPtr = k;
        return parseNumberText2(flag);
_L4:
        char ac5[] = _inputBuffer;
        int j3 = j + 1;
        i = ac5[j];
        if (i > 57 || i < 48)
        {
            _inputPtr = j3;
            return _handleInvalidNumberStart(i, true);
        }
        j = j3;
_L2:
        int i1;
        if (i == 48)
        {
            continue; /* Loop/switch isn't completed */
        }
        i1 = 1;
_L9:
        int j1;
        char c;
        if (j >= _inputEnd)
        {
            continue; /* Loop/switch isn't completed */
        }
        char ac[] = _inputBuffer;
        j1 = j + 1;
        c = ac[j];
        if (c >= '0' && c <= '9') goto _L6; else goto _L5
_L5:
        int k1 = 0;
        if (c != '.') goto _L8; else goto _L7
_L6:
        i1++;
        j = j1;
          goto _L9
_L7:
        int i3;
        if (j1 >= l)
        {
            j1;
            continue; /* Loop/switch isn't completed */
        }
        char ac4[] = _inputBuffer;
        i3 = j1 + 1;
        c = ac4[j1];
        if (c >= '0' && c <= '9')
        {
            break MISSING_BLOCK_LABEL_267;
        }
        if (k1 == 0)
        {
            reportUnexpectedNumberChar(c, "Decimal point not followed by a digit");
        }
        j1 = i3;
_L8:
        int l1;
        l1 = 0;
        if (c != 'e')
        {
            l1 = 0;
            if (c != 'E')
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (j1 >= l)
        {
            j1;
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_277;
        k1++;
        j1 = i3;
        if (true) goto _L7; else goto _L10
_L10:
        int k2;
        char c1;
        char ac1[] = _inputBuffer;
        k2 = j1 + 1;
        c1 = ac1[j1];
        if (c1 != '-' && c1 != '+')
        {
            break MISSING_BLOCK_LABEL_451;
        }
        if (k2 >= l)
        {
            continue; /* Loop/switch isn't completed */
        }
        char ac2[] = _inputBuffer;
        j1 = k2 + 1;
        c1 = ac2[k2];
_L12:
label0:
        {
            if (c1 > '9' || c1 < '0')
            {
                break MISSING_BLOCK_LABEL_392;
            }
            l1++;
            if (j1 < l)
            {
                break label0;
            }
            j1;
        }
        if (true) goto _L3; else goto _L11
        char ac3[] = _inputBuffer;
        int l2 = j1 + 1;
        c1 = ac3[j1];
        j1 = l2;
          goto _L12
        if (l1 == 0)
        {
            reportUnexpectedNumberChar(c1, "Exponent indicator not followed by a digit");
        }
_L11:
        int i2 = -1 + j1;
        _inputPtr = i2;
        int j2 = i2 - k;
        _textBuffer.resetWithShared(_inputBuffer, k, j2);
        return reset(flag, i1, k1, l1);
        j1 = k2;
        l1 = 0;
          goto _L12
    }
}
