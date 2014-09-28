// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.smile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.impl.JsonReadContext;
import org.codehaus.jackson.impl.StreamBasedParserBase;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.sym.BytesToNameCanonicalizer;
import org.codehaus.jackson.sym.Name;
import org.codehaus.jackson.util.TextBuffer;

// Referenced classes of package org.codehaus.jackson.smile:
//            SmileConstants, SmileBufferRecycler, SmileUtil

public class SmileParser extends StreamBasedParserBase
{
    public static final class Feature extends Enum
    {

        private static final Feature $VALUES[];
        public static final Feature REQUIRE_HEADER;
        final boolean _defaultState;
        final int _mask = 1 << ordinal();

        public static int collectDefaults()
        {
            int i = 0;
            Feature afeature[] = values();
            int j = afeature.length;
            for (int k = 0; k < j; k++)
            {
                Feature feature = afeature[k];
                if (feature.enabledByDefault())
                {
                    i |= feature.getMask();
                }
            }

            return i;
        }

        public static Feature valueOf(String s)
        {
            return (Feature)Enum.valueOf(org/codehaus/jackson/smile/SmileParser$Feature, s);
        }

        public static Feature[] values()
        {
            return (Feature[])$VALUES.clone();
        }

        public boolean enabledByDefault()
        {
            return _defaultState;
        }

        public int getMask()
        {
            return _mask;
        }

        static 
        {
            REQUIRE_HEADER = new Feature("REQUIRE_HEADER", 0, true);
            Feature afeature[] = new Feature[1];
            afeature[0] = REQUIRE_HEADER;
            $VALUES = afeature;
        }

        private Feature(String s, int i, boolean flag)
        {
            super(s, i);
            _defaultState = flag;
        }
    }


    private static final int NO_INTS[] = new int[0];
    private static final String NO_STRINGS[] = new String[0];
    protected static final ThreadLocal _smileRecyclerRef = new ThreadLocal();
    protected boolean _got32BitFloat;
    protected boolean _mayContainRawBinary;
    protected ObjectCodec _objectCodec;
    protected int _quad1;
    protected int _quad2;
    protected int _quadBuffer[];
    protected int _seenNameCount;
    protected String _seenNames[];
    protected int _seenStringValueCount;
    protected String _seenStringValues[];
    protected final SmileBufferRecycler _smileBufferRecycler = _smileBufferRecycler();
    protected final BytesToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;
    protected int _typeByte;

    public SmileParser(IOContext iocontext, int i, int j, ObjectCodec objectcodec, BytesToNameCanonicalizer bytestonamecanonicalizer, InputStream inputstream, byte abyte0[], 
            int k, int l, boolean flag)
    {
        super(iocontext, i, inputstream, abyte0, k, l, flag);
        _tokenIncomplete = false;
        _quadBuffer = NO_INTS;
        _seenNames = NO_STRINGS;
        _seenNameCount = 0;
        _seenStringValues = null;
        _seenStringValueCount = -1;
        _objectCodec = objectcodec;
        _symbols = bytestonamecanonicalizer;
        _tokenInputRow = -1;
        _tokenInputCol = -1;
    }

    private final String _addDecodedToSymbols(int i, String s)
    {
        if (i < 5)
        {
            return _symbols.addName(s, _quad1, 0).getName();
        }
        if (i < 9)
        {
            return _symbols.addName(s, _quad1, _quad2).getName();
        } else
        {
            int j = i + 3 >> 2;
            return _symbols.addName(s, _quadBuffer, j).getName();
        }
    }

    private final void _addSeenStringValue()
        throws IOException, JsonParseException
    {
        _finishToken();
        if (_seenStringValueCount < _seenStringValues.length)
        {
            String as[] = _seenStringValues;
            int i = _seenStringValueCount;
            _seenStringValueCount = i + 1;
            as[i] = _textBuffer.contentsAsString();
            return;
        } else
        {
            _expandSeenStringValues();
            return;
        }
    }

    private final void _decodeLongAscii()
        throws IOException, JsonParseException
    {
        int i;
        char ac[];
        i = 0;
        ac = _textBuffer.emptyAndGetCurrentSegment();
_L2:
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        int j = _inputPtr;
        int k = _inputEnd - j;
        if (i >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            i = 0;
        }
        int l = Math.min(k, ac.length - i);
        do
        {
            int i1;
            int j1;
label0:
            {
                byte abyte0[] = _inputBuffer;
                i1 = j + 1;
                byte byte0 = abyte0[j];
                if (byte0 == -4)
                {
                    _inputPtr = i1;
                    _textBuffer.setCurrentLength(i);
                    return;
                }
                j1 = i + 1;
                ac[i] = (char)byte0;
                if (--l > 0)
                {
                    break label0;
                }
                _inputPtr = i1;
                i = j1;
            }
            if (true)
            {
                continue;
            }
            j = i1;
            i = j1;
        } while (true);
        if (true) goto _L2; else goto _L1
_L1:
    }

    private final void _decodeLongUnicode()
        throws IOException, JsonParseException
    {
        int i;
        char ac[];
        int ai[];
        byte abyte0[];
        i = 0;
        ac = _textBuffer.emptyAndGetCurrentSegment();
        ai = SmileConstants.sUtf8UnitLengths;
        abyte0 = _inputBuffer;
_L5:
        do
        {
label0:
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
                int k = _inputEnd;
                int l = j + (ac.length - i);
                int i1;
                int j1;
                int l1;
                int j2;
                int k2;
                int l2;
                if (l < k)
                {
                    k = l;
                    i1 = j;
                    j1 = i;
                } else
                {
                    i1 = j;
                    j1 = i;
                }
                do
                {
                    if (i1 >= k)
                    {
                        break;
                    }
                    int k1 = i1 + 1;
                    l1 = 0xff & abyte0[i1];
                    if (ai[l1] != 0)
                    {
                        _inputPtr = k1;
                        if (l1 == 252)
                        {
                            _textBuffer.setCurrentLength(j1);
                            return;
                        }
                        break label0;
                    }
                    int i2 = j1 + 1;
                    ac[j1] = (char)l1;
                    i1 = k1;
                    j1 = i2;
                } while (true);
                _inputPtr = i1;
                i = j1;
            }
        } while (true);
        ai[l1];
        JVM INSTR tableswitch 1 4: default 220
    //                   1 267
    //                   2 282
    //                   3 220
    //                   4 325;
           goto _L1 _L2 _L3 _L1 _L4
_L4:
        break MISSING_BLOCK_LABEL_325;
_L1:
        _reportInvalidChar(l1);
        k2 = j1;
_L6:
        if (k2 >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            k2 = 0;
        }
        l2 = k2 + 1;
        ac[k2] = (char)l1;
        i = l2;
          goto _L5
_L2:
        l1 = _decodeUtf8_2(l1);
        k2 = j1;
          goto _L6
_L3:
        if (_inputEnd - _inputPtr >= 2)
        {
            l1 = _decodeUtf8_3fast(l1);
            k2 = j1;
        } else
        {
            l1 = _decodeUtf8_3(l1);
            k2 = j1;
        }
          goto _L6
        j2 = _decodeUtf8_4(l1);
        k2 = j1 + 1;
        ac[j1] = (char)(0xd800 | j2 >> 10);
        if (k2 >= ac.length)
        {
            ac = _textBuffer.finishCurrentSegment();
            k2 = 0;
        }
        l1 = 0xdc00 | j2 & 0x3ff;
          goto _L6
    }

    private final Name _decodeLongUnicodeName(int ai[], int i, int j)
        throws IOException, JsonParseException
    {
        int k;
        int l;
        char ac[];
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        k = i & 3;
        int i1;
        int k2;
        int l2;
        int i3;
        int j3;
        if (k < 4)
        {
            l = ai[j - 1];
            ai[j - 1] = l << (4 - k << 3);
        } else
        {
            l = 0;
        }
        ac = _textBuffer.emptyAndGetCurrentSegment();
        i1 = 0;
        j1 = 0;
        if (i1 >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        k1 = 0xff & ai[i1 >> 2] >> (3 - (i1 & 3) << 3);
        i1++;
        if (k1 <= 127)
        {
            break MISSING_BLOCK_LABEL_496;
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
        if (i1 + j2 > i)
        {
            _reportInvalidEOF(" in long field name");
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
            break MISSING_BLOCK_LABEL_496;
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
        break MISSING_BLOCK_LABEL_47;
_L1:
        String s = new String(ac, 0, j1);
        if (k < 4)
        {
            ai[j - 1] = l;
        }
        return _symbols.addName(s, ai, j);
        l1 = j1;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private final String _decodeShortAsciiName(int i)
        throws IOException, JsonParseException
    {
        char ac[];
        byte abyte0[];
        int l;
        int i1;
        int j1;
        ac = _textBuffer.emptyAndGetCurrentSegment();
        abyte0 = _inputBuffer;
        int j = _inputPtr;
        int k = -3 + (j + i);
        l = j;
        i1 = 0;
        while (l < k) 
        {
            int i2 = i1 + 1;
            int j2 = l + 1;
            ac[i1] = (char)abyte0[l];
            int k2 = i2 + 1;
            int l2 = j2 + 1;
            ac[i2] = (char)abyte0[j2];
            int i3 = k2 + 1;
            int j3 = l2 + 1;
            ac[k2] = (char)abyte0[l2];
            i1 = i3 + 1;
            l = j3 + 1;
            ac[i3] = (char)abyte0[j3];
        }
        j1 = i & 3;
        if (j1 <= 0) goto _L2; else goto _L1
_L1:
        int k1;
        int l1;
        l1 = i1 + 1;
        k1 = l + 1;
        ac[i1] = (char)abyte0[l];
        if (j1 <= 1) goto _L4; else goto _L3
_L3:
        i1 = l1 + 1;
        l = k1 + 1;
        ac[l1] = (char)abyte0[k1];
        if (j1 <= 2) goto _L2; else goto _L5
_L5:
        i1 + 1;
        k1 = l + 1;
        ac[i1] = (char)abyte0[l];
_L4:
        _inputPtr = k1;
        _textBuffer.setCurrentLength(i);
        return _textBuffer.contentsAsString();
_L2:
        k1 = l;
        i1;
        if (true) goto _L4; else goto _L6
_L6:
    }

    private final String _decodeShortUnicodeName(int i)
        throws IOException, JsonParseException
    {
        char ac[];
        int ai[];
        byte abyte0[];
        int k;
        int l;
        int i1;
        ac = _textBuffer.emptyAndGetCurrentSegment();
        int j = _inputPtr;
        _inputPtr = i + _inputPtr;
        ai = SmileConstants.sUtf8UnitLengths;
        abyte0 = _inputBuffer;
        k = j + i;
        l = j;
        i1 = 0;
_L8:
        int j1;
        int k1;
        int l1;
        if (l >= k)
        {
            break MISSING_BLOCK_LABEL_375;
        }
        j1 = l + 1;
        k1 = 0xff & abyte0[l];
        l1 = ai[k1];
        if (l1 == 0) goto _L2; else goto _L1
_L1:
        l1;
        JVM INSTR tableswitch 1 3: default 108
    //                   1 164
    //                   2 204
    //                   3 262;
           goto _L3 _L4 _L5 _L6
_L6:
        break MISSING_BLOCK_LABEL_262;
_L4:
        break; /* Loop/switch isn't completed */
_L3:
        _reportError((new StringBuilder()).append("Invalid byte ").append(Integer.toHexString(k1)).append(" in short Unicode text block").toString());
_L2:
        int i2 = i1;
_L9:
        i1 = i2 + 1;
        ac[i2] = (char)k1;
        l = j1;
        if (true) goto _L8; else goto _L7
_L7:
        int l4 = (k1 & 0x1f) << 6;
        int i5 = j1 + 1;
        k1 = l4 | 0x3f & abyte0[j1];
        j1 = i5;
        i2 = i1;
          goto _L9
_L5:
        int i4 = (k1 & 0xf) << 12;
        int j4 = j1 + 1;
        int k4 = i4 | (0x3f & abyte0[j1]) << 6;
        j1 = j4 + 1;
        k1 = k4 | 0x3f & abyte0[j4];
        i2 = i1;
          goto _L9
        int j2 = (k1 & 7) << 18;
        int k2 = j1 + 1;
        int l2 = j2 | (0x3f & abyte0[j1]) << 12;
        int i3 = k2 + 1;
        int j3 = l2 | (0x3f & abyte0[k2]) << 6;
        int k3 = i3 + 1;
        int l3 = (j3 | 0x3f & abyte0[i3]) - 0x10000;
        i2 = i1 + 1;
        ac[i1] = (char)(0xd800 | l3 >> 10);
        k1 = 0xdc00 | l3 & 0x3ff;
        j1 = k3;
          goto _L9
        _textBuffer.setCurrentLength(i1);
        return _textBuffer.contentsAsString();
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

    private final String[] _expandSeenNames(String as[])
    {
        char c = '\u0400';
        int i = as.length;
        if (i == 0)
        {
            String as2[] = (String[])_smileBufferRecycler.allocSeenNamesBuffer();
            if (as2 == null)
            {
                as2 = new String[64];
            }
            return as2;
        }
        if (i == c)
        {
            _seenNameCount = 0;
            return as;
        }
        if (i == 64)
        {
            c = '\u0100';
        }
        String as1[] = new String[c];
        System.arraycopy(as, 0, as1, 0, as.length);
        return as1;
    }

    private final void _expandSeenStringValues()
    {
        char c = '\u0400';
        String as[] = _seenStringValues;
        int i = as.length;
        String as1[];
        String as2[];
        int j;
        if (i == 0)
        {
            as1 = (String[])_smileBufferRecycler.allocSeenStringValuesBuffer();
            if (as1 == null)
            {
                as1 = new String[64];
            }
        } else
        if (i == c)
        {
            as1 = as;
            _seenStringValueCount = 0;
        } else
        {
            if (i == 64)
            {
                c = '\u0100';
            }
            as1 = new String[c];
            System.arraycopy(as, 0, as1, 0, as.length);
        }
        _seenStringValues = as1;
        as2 = _seenStringValues;
        j = _seenStringValueCount;
        _seenStringValueCount = j + 1;
        as2[j] = _textBuffer.contentsAsString();
    }

    private final Name _findDecodedFromSymbols(int i)
        throws IOException, JsonParseException
    {
        if (_inputEnd - _inputPtr < i)
        {
            _loadToHaveAtLeast(i);
        }
        if (i < 5)
        {
            int j4 = _inputPtr;
            byte abyte1[] = _inputBuffer;
            int k4 = 0xff & abyte1[j4];
            int l4 = i - 1;
            if (l4 > 0)
            {
                int i5 = k4 << 8;
                int j5 = j4 + 1;
                k4 = i5 + (0xff & abyte1[j5]);
                int k5 = l4 - 1;
                if (k5 > 0)
                {
                    int l5 = k4 << 8;
                    int i6 = j5 + 1;
                    k4 = l5 + (0xff & abyte1[i6]);
                    if (k5 - 1 > 0)
                    {
                        k4 = (k4 << 8) + (0xff & abyte1[i6 + 1]);
                    }
                }
            }
            _quad1 = k4;
            return _symbols.findName(k4);
        }
        if (i < 9)
        {
            int j = _inputPtr;
            byte abyte0[] = _inputBuffer;
            int k = (0xff & abyte0[j]) << 8;
            int l = j + 1;
            int i1 = k + (0xff & abyte0[l]) << 8;
            int j1 = l + 1;
            int k1 = i1 + (0xff & abyte0[j1]) << 8;
            int l1 = j1 + 1;
            int i2 = k1 + (0xff & abyte0[l1]);
            int j2 = l1 + 1;
            int k2 = 0xff & abyte0[j2];
            int l2 = i - 5;
            if (l2 > 0)
            {
                int i3 = k2 << 8;
                int j3 = j2 + 1;
                k2 = i3 + (0xff & abyte0[j3]);
                int k3 = l2 - 1;
                if (k3 > 0)
                {
                    int l3 = k2 << 8;
                    int i4 = j3 + 1;
                    k2 = l3 + (0xff & abyte0[i4]);
                    if (k3 - 1 > 0)
                    {
                        k2 = (k2 << 8) + (0xff & abyte0[i4 + 1]);
                    }
                }
            }
            _quad1 = i2;
            _quad2 = k2;
            return _symbols.findName(i2, k2);
        } else
        {
            return _findDecodedMedium(i);
        }
    }

    private final Name _findDecodedMedium(int i)
        throws IOException, JsonParseException
    {
        int j = i + 3 >> 2;
        if (j > _quadBuffer.length)
        {
            _quadBuffer = _growArrayTo(_quadBuffer, j);
        }
        int k = 0;
        int l = _inputPtr;
        byte abyte0[] = _inputBuffer;
        do
        {
            int i1 = l + 1;
            int j1 = (0xff & abyte0[l]) << 8;
            int k1 = i1 + 1;
            int l1 = (j1 | 0xff & abyte0[i1]) << 8;
            int i2 = k1 + 1;
            int j2 = (l1 | 0xff & abyte0[k1]) << 8;
            l = i2 + 1;
            int k2 = j2 | 0xff & abyte0[i2];
            int ai[] = _quadBuffer;
            int l2 = k + 1;
            ai[k] = k2;
            if ((i -= 4) <= 3)
            {
                int i3;
                if (i > 0)
                {
                    int j3 = 0xff & abyte0[l];
                    int k3 = i - 1;
                    if (k3 > 0)
                    {
                        int l3 = j3 << 8;
                        int i4 = l + 1;
                        j3 = l3 + (0xff & abyte0[i4]);
                        if (k3 - 1 > 0)
                        {
                            j3 = (j3 << 8) + (0xff & abyte0[i4 + 1]);
                        }
                    }
                    int ai1[] = _quadBuffer;
                    i3 = l2 + 1;
                    ai1[l2] = j3;
                } else
                {
                    i3 = l2;
                }
                return _symbols.findName(_quadBuffer, i3);
            }
            k = l2;
        } while (true);
    }

    private final void _finishBigDecimal()
        throws IOException, JsonParseException
    {
        int i = SmileUtil.zigzagDecode(_readUnsignedVInt());
        _numberBigDecimal = new BigDecimal(new BigInteger(_read7BitBinaryWithLength()), i);
        _numTypesValid = 16;
    }

    private final void _finishBigInteger()
        throws IOException, JsonParseException
    {
        _numberBigInt = new BigInteger(_read7BitBinaryWithLength());
        _numTypesValid = 4;
    }

    private final void _finishDouble()
        throws IOException, JsonParseException
    {
        long l = ((long)_fourBytesToInt() << 28) + (long)_fourBytesToInt();
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        long l1 = l << 7;
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        long l2 = l1 + (long)abyte0[i];
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        long l3 = l2 << 7;
        byte abyte1[] = _inputBuffer;
        int j = _inputPtr;
        _inputPtr = j + 1;
        _numberDouble = Double.longBitsToDouble(l3 + (long)abyte1[j]);
        _numTypesValid = 8;
    }

    private final void _finishFloat()
        throws IOException, JsonParseException
    {
        int i = _fourBytesToInt();
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        int j = i << 7;
        byte abyte0[] = _inputBuffer;
        int k = _inputPtr;
        _inputPtr = k + 1;
        _numberDouble = Float.intBitsToFloat(j + abyte0[k]);
        _numTypesValid = 8;
    }

    private final void _finishInt()
        throws IOException, JsonParseException
    {
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        int j = abyte0[i];
        int l;
        if (j < 0)
        {
            l = j & 0x3f;
        } else
        {
            if (_inputPtr >= _inputEnd)
            {
                loadMoreGuaranteed();
            }
            byte abyte1[] = _inputBuffer;
            int k = _inputPtr;
            _inputPtr = k + 1;
            byte byte0 = abyte1[k];
            if (byte0 >= 0)
            {
                j = byte0 + (j << 7);
                if (_inputPtr >= _inputEnd)
                {
                    loadMoreGuaranteed();
                }
                byte abyte2[] = _inputBuffer;
                int i1 = _inputPtr;
                _inputPtr = i1 + 1;
                byte0 = abyte2[i1];
                if (byte0 >= 0)
                {
                    j = byte0 + (j << 7);
                    if (_inputPtr >= _inputEnd)
                    {
                        loadMoreGuaranteed();
                    }
                    byte abyte3[] = _inputBuffer;
                    int j1 = _inputPtr;
                    _inputPtr = j1 + 1;
                    byte0 = abyte3[j1];
                    if (byte0 >= 0)
                    {
                        j = byte0 + (j << 7);
                        if (_inputPtr >= _inputEnd)
                        {
                            loadMoreGuaranteed();
                        }
                        byte abyte4[] = _inputBuffer;
                        int k1 = _inputPtr;
                        _inputPtr = k1 + 1;
                        byte0 = abyte4[k1];
                        if (byte0 >= 0)
                        {
                            _reportError("Corrupt input; 32-bit VInt extends beyond 5 data bytes");
                        }
                    }
                }
            }
            l = (j << 6) + (byte0 & 0x3f);
        }
        _numberInt = SmileUtil.zigzagDecode(l);
        _numTypesValid = 1;
    }

    private final void _finishLong()
        throws IOException, JsonParseException
    {
        long l = _fourBytesToInt();
        do
        {
            if (_inputPtr >= _inputEnd)
            {
                loadMoreGuaranteed();
            }
            byte abyte0[] = _inputBuffer;
            int i = _inputPtr;
            _inputPtr = i + 1;
            int j = abyte0[i];
            if (j < 0)
            {
                _numberLong = SmileUtil.zigzagDecode((l << 6) + (long)(j & 0x3f));
                _numTypesValid = 2;
                return;
            }
            l = (l << 7) + (long)j;
        } while (true);
    }

    private final void _finishRawBinary()
        throws IOException, JsonParseException
    {
        int i = _readUnsignedVInt();
        _binaryValue = new byte[i];
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        int j = 0;
        do
        {
            int k = Math.min(i, _inputEnd - _inputPtr);
            System.arraycopy(_inputBuffer, _inputPtr, _binaryValue, j, k);
            _inputPtr = k + _inputPtr;
            j += k;
            i -= k;
            if (i <= 0)
            {
                return;
            }
            loadMoreGuaranteed();
        } while (true);
    }

    private final int _fourBytesToInt()
        throws IOException, JsonParseException
    {
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        byte byte0 = abyte0[i];
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        int j = byte0 << 7;
        byte abyte1[] = _inputBuffer;
        int k = _inputPtr;
        _inputPtr = k + 1;
        int l = j + abyte1[k];
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        int i1 = l << 7;
        byte abyte2[] = _inputBuffer;
        int j1 = _inputPtr;
        _inputPtr = j1 + 1;
        int k1 = i1 + abyte2[j1];
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        int l1 = k1 << 7;
        byte abyte3[] = _inputBuffer;
        int i2 = _inputPtr;
        _inputPtr = i2 + 1;
        return l1 + abyte3[i2];
    }

    private static int[] _growArrayTo(int ai[], int i)
    {
        int ai1[] = new int[i + 4];
        if (ai != null)
        {
            System.arraycopy(ai, 0, ai1, 0, ai.length);
        }
        return ai1;
    }

    private final void _handleLongFieldName()
        throws IOException, JsonParseException
    {
        byte abyte0[];
        int i;
        int j;
        abyte0 = _inputBuffer;
        i = 0;
        j = 0;
_L2:
        int k = _inputPtr;
        _inputPtr = k + 1;
        byte byte0 = abyte0[k];
        byte byte3;
        int l1;
        int i2;
        Name name;
        String s;
        if (-4 == byte0)
        {
            l1 = 0;
        } else
        {
            j = byte0 & 0xff;
            int l = _inputPtr;
            _inputPtr = l + 1;
            byte byte1 = abyte0[l];
            if (-4 == byte1)
            {
                l1 = 1;
            } else
            {
                j = j << 8 | byte1 & 0xff;
                int i1 = _inputPtr;
                _inputPtr = i1 + 1;
                byte byte2 = abyte0[i1];
                if (-4 == byte2)
                {
                    l1 = 2;
                } else
                {
label0:
                    {
                        j = j << 8 | byte2 & 0xff;
                        int j1 = _inputPtr;
                        _inputPtr = j1 + 1;
                        byte3 = abyte0[j1];
                        if (-4 != byte3)
                        {
                            break label0;
                        }
                        l1 = 3;
                    }
                }
            }
        }
        i2 = i << 2;
        if (l1 > 0)
        {
            if (i >= _quadBuffer.length)
            {
                _quadBuffer = _growArrayTo(_quadBuffer, 256 + _quadBuffer.length);
            }
            int ai1[] = _quadBuffer;
            int k2 = i + 1;
            ai1[i] = j;
            i2 += l1;
            i = k2;
        }
        name = _symbols.findName(_quadBuffer, i);
        int ai[];
        int k1;
        if (name != null)
        {
            s = name.getName();
        } else
        {
            s = _decodeLongUnicodeName(_quadBuffer, i2, i).getName();
        }
        if (_seenNames != null)
        {
            if (_seenNameCount >= _seenNames.length)
            {
                _seenNames = _expandSeenNames(_seenNames);
            }
            String as[] = _seenNames;
            int j2 = _seenNameCount;
            _seenNameCount = j2 + 1;
            as[j2] = s;
        }
        _parsingContext.setCurrentName(s);
        return;
        j = j << 8 | byte3 & 0xff;
        if (i >= _quadBuffer.length)
        {
            _quadBuffer = _growArrayTo(_quadBuffer, 256 + _quadBuffer.length);
        }
        ai = _quadBuffer;
        k1 = i + 1;
        ai[i] = j;
        i = k1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private final JsonToken _handleSharedString(int i)
        throws IOException, JsonParseException
    {
        if (i >= _seenStringValueCount)
        {
            _reportInvalidSharedStringValue(i);
        }
        _textBuffer.resetWithString(_seenStringValues[i]);
        JsonToken jsontoken = JsonToken.VALUE_STRING;
        _currToken = jsontoken;
        return jsontoken;
    }

    private final byte[] _read7BitBinaryWithLength()
        throws IOException, JsonParseException
    {
        int i = _readUnsignedVInt();
        byte abyte0[] = new byte[i];
        int j = i - 7;
        int k;
        int l8;
        for (k = 0; k <= j; k = l8)
        {
            if (_inputEnd - _inputPtr < 8)
            {
                _loadToHaveAtLeast(8);
            }
            byte abyte4[] = _inputBuffer;
            int i3 = _inputPtr;
            _inputPtr = i3 + 1;
            int j3 = abyte4[i3] << 25;
            byte abyte5[] = _inputBuffer;
            int k3 = _inputPtr;
            _inputPtr = k3 + 1;
            int l3 = j3 + (abyte5[k3] << 18);
            byte abyte6[] = _inputBuffer;
            int i4 = _inputPtr;
            _inputPtr = i4 + 1;
            int j4 = l3 + (abyte6[i4] << 11);
            byte abyte7[] = _inputBuffer;
            int k4 = _inputPtr;
            _inputPtr = k4 + 1;
            int l4 = j4 + (abyte7[k4] << 4);
            byte abyte8[] = _inputBuffer;
            int i5 = _inputPtr;
            _inputPtr = i5 + 1;
            byte byte0 = abyte8[i5];
            int j5 = l4 + (byte0 >> 3);
            int k5 = (byte0 & 7) << 21;
            byte abyte9[] = _inputBuffer;
            int l5 = _inputPtr;
            _inputPtr = l5 + 1;
            int i6 = k5 + (abyte9[l5] << 14);
            byte abyte10[] = _inputBuffer;
            int j6 = _inputPtr;
            _inputPtr = j6 + 1;
            int k6 = i6 + (abyte10[j6] << 7);
            byte abyte11[] = _inputBuffer;
            int l6 = _inputPtr;
            _inputPtr = l6 + 1;
            int i7 = k6 + abyte11[l6];
            int j7 = k + 1;
            abyte0[k] = (byte)(j5 >> 24);
            int k7 = j7 + 1;
            abyte0[j7] = (byte)(j5 >> 16);
            int l7 = k7 + 1;
            abyte0[k7] = (byte)(j5 >> 8);
            int i8 = l7 + 1;
            abyte0[l7] = (byte)j5;
            int j8 = i8 + 1;
            abyte0[i8] = (byte)(i7 >> 16);
            int k8 = j8 + 1;
            abyte0[j8] = (byte)(i7 >> 8);
            l8 = k8 + 1;
            abyte0[k8] = (byte)i7;
        }

        int l = abyte0.length - k;
        if (l > 0)
        {
            if (_inputEnd - _inputPtr < l + 1)
            {
                _loadToHaveAtLeast(l + 1);
            }
            byte abyte1[] = _inputBuffer;
            int i1 = _inputPtr;
            _inputPtr = i1 + 1;
            int j1 = abyte1[i1];
            for (int k1 = 1; k1 < l;)
            {
                int j2 = j1 << 7;
                byte abyte3[] = _inputBuffer;
                int k2 = _inputPtr;
                _inputPtr = k2 + 1;
                j1 = j2 + abyte3[k2];
                int l2 = k + 1;
                abyte0[k] = (byte)(j1 >> 7 - k1);
                k1++;
                k = l2;
            }

            int l1 = j1 << l;
            byte abyte2[] = _inputBuffer;
            int i2 = _inputPtr;
            _inputPtr = i2 + 1;
            abyte0[k] = (byte)(l1 + abyte2[i2]);
        }
        int _tmp = k;
        return abyte0;
    }

    private final int _readUnsignedVInt()
        throws IOException, JsonParseException
    {
        int i = 0;
        do
        {
            if (_inputPtr >= _inputEnd)
            {
                loadMoreGuaranteed();
            }
            byte abyte0[] = _inputBuffer;
            int j = _inputPtr;
            _inputPtr = j + 1;
            byte byte0 = abyte0[j];
            if (byte0 < 0)
            {
                return (i << 6) + (byte0 & 0x3f);
            }
            i = byte0 + (i << 7);
        } while (true);
    }

    protected static final SmileBufferRecycler _smileBufferRecycler()
    {
        SoftReference softreference = (SoftReference)_smileRecyclerRef.get();
        SmileBufferRecycler smilebufferrecycler;
        if (softreference == null)
        {
            smilebufferrecycler = null;
        } else
        {
            smilebufferrecycler = (SmileBufferRecycler)softreference.get();
        }
        if (smilebufferrecycler == null)
        {
            smilebufferrecycler = new SmileBufferRecycler();
            _smileRecyclerRef.set(new SoftReference(smilebufferrecycler));
        }
        return smilebufferrecycler;
    }

    protected byte[] _decodeBase64(Base64Variant base64variant)
        throws IOException, JsonParseException
    {
        _throwInternal();
        return null;
    }

    protected final void _decodeShortAsciiValue(int i)
        throws IOException, JsonParseException
    {
        if (_inputEnd - _inputPtr < i)
        {
            _loadToHaveAtLeast(i);
        }
        char ac[] = _textBuffer.emptyAndGetCurrentSegment();
        byte abyte0[] = _inputBuffer;
        int j = _inputPtr;
        int k = j + i;
        int i1;
        for (int l = 0; j < k; l = i1)
        {
            i1 = l + 1;
            ac[l] = (char)abyte0[j];
            j++;
        }

        _inputPtr = j;
        _textBuffer.setCurrentLength(i);
    }

    protected final void _decodeShortUnicodeValue(int i)
        throws IOException, JsonParseException
    {
        char ac[];
        int ai[];
        byte abyte0[];
        int k;
        int l;
        int i1;
        if (_inputEnd - _inputPtr < i)
        {
            _loadToHaveAtLeast(i);
        }
        ac = _textBuffer.emptyAndGetCurrentSegment();
        int j = _inputPtr;
        _inputPtr = i + _inputPtr;
        ai = SmileConstants.sUtf8UnitLengths;
        abyte0 = _inputBuffer;
        k = j + i;
        l = j;
        i1 = 0;
_L8:
        int j1;
        int k1;
        int l1;
        if (l >= k)
        {
            break MISSING_BLOCK_LABEL_395;
        }
        j1 = l + 1;
        k1 = 0xff & abyte0[l];
        l1 = ai[k1];
        if (l1 == 0) goto _L2; else goto _L1
_L1:
        l1;
        JVM INSTR tableswitch 1 3: default 128
    //                   1 184
    //                   2 224
    //                   3 282;
           goto _L3 _L4 _L5 _L6
_L6:
        break MISSING_BLOCK_LABEL_282;
_L4:
        break; /* Loop/switch isn't completed */
_L3:
        _reportError((new StringBuilder()).append("Invalid byte ").append(Integer.toHexString(k1)).append(" in short Unicode text block").toString());
_L2:
        int i2 = i1;
_L9:
        i1 = i2 + 1;
        ac[i2] = (char)k1;
        l = j1;
        if (true) goto _L8; else goto _L7
_L7:
        int l4 = (k1 & 0x1f) << 6;
        int i5 = j1 + 1;
        k1 = l4 | 0x3f & abyte0[j1];
        j1 = i5;
        i2 = i1;
          goto _L9
_L5:
        int i4 = (k1 & 0xf) << 12;
        int j4 = j1 + 1;
        int k4 = i4 | (0x3f & abyte0[j1]) << 6;
        j1 = j4 + 1;
        k1 = k4 | 0x3f & abyte0[j4];
        i2 = i1;
          goto _L9
        int j2 = (k1 & 7) << 18;
        int k2 = j1 + 1;
        int l2 = j2 | (0x3f & abyte0[j1]) << 12;
        int i3 = k2 + 1;
        int j3 = l2 | (0x3f & abyte0[k2]) << 6;
        int k3 = i3 + 1;
        int l3 = (j3 | 0x3f & abyte0[i3]) - 0x10000;
        i2 = i1 + 1;
        ac[i1] = (char)(0xd800 | l3 >> 10);
        k1 = 0xdc00 | l3 & 0x3ff;
        j1 = k3;
          goto _L9
        _textBuffer.setCurrentLength(i1);
        return;
    }

    protected final void _finishNumberToken(int i)
        throws IOException, JsonParseException
    {
        int j;
        int k;
        j = i & 0x1f;
        k = j >> 2;
        if (k == 1)
        {
            int l = j & 3;
            if (l == 0)
            {
                _finishInt();
                return;
            }
            if (l == 1)
            {
                _finishLong();
                return;
            }
            if (l == 2)
            {
                _finishBigInteger();
                return;
            } else
            {
                _throwInternal();
                return;
            }
        }
        if (k != 2) goto _L2; else goto _L1
_L1:
        j & 3;
        JVM INSTR tableswitch 0 2: default 92
    //                   0 97
    //                   1 102
    //                   2 107;
           goto _L2 _L3 _L4 _L5
_L2:
        _throwInternal();
        return;
_L3:
        _finishFloat();
        return;
_L4:
        _finishDouble();
        return;
_L5:
        _finishBigDecimal();
        return;
    }

    protected void _finishString()
        throws IOException, JsonParseException
    {
        _throwInternal();
    }

    protected void _finishToken()
        throws IOException, JsonParseException
    {
        int i;
        int j;
        _tokenIncomplete = false;
        i = _typeByte;
        j = 7 & i >> 5;
        if (j == 1)
        {
            _finishNumberToken(i);
            return;
        }
        if (j <= 3)
        {
            _decodeShortAsciiValue(1 + (i & 0x3f));
            return;
        }
        if (j <= 5)
        {
            _decodeShortUnicodeValue(2 + (i & 0x3f));
            return;
        }
        if (j != 7) goto _L2; else goto _L1
_L1:
        (i & 0x1f) >> 2;
        JVM INSTR tableswitch 0 7: default 120
    //                   0 125
    //                   1 130
    //                   2 135
    //                   3 120
    //                   4 120
    //                   5 120
    //                   6 120
    //                   7 144;
           goto _L2 _L3 _L4 _L5 _L2 _L2 _L2 _L2 _L6
_L2:
        _throwInternal();
        return;
_L3:
        _decodeLongAscii();
        return;
_L4:
        _decodeLongUnicode();
        return;
_L5:
        _binaryValue = _read7BitBinaryWithLength();
        return;
_L6:
        _finishRawBinary();
        return;
    }

    protected final JsonToken _handleFieldName()
        throws IOException, JsonParseException
    {
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        int j = abyte0[i];
        _typeByte = j;
        switch (3 & j >> 6)
        {
        case 0: // '\0'
            switch (j)
            {
            case 32: // ' '
                _parsingContext.setCurrentName("");
                return JsonToken.FIELD_NAME;

            case 48: // '0'
            case 49: // '1'
            case 50: // '2'
            case 51: // '3'
                if (_inputPtr >= _inputEnd)
                {
                    loadMoreGuaranteed();
                }
                int i2 = (j & 3) << 8;
                byte abyte1[] = _inputBuffer;
                int j2 = _inputPtr;
                _inputPtr = j2 + 1;
                int k2 = i2 + (0xff & abyte1[j2]);
                if (k2 >= _seenNameCount)
                {
                    _reportInvalidSharedName(k2);
                }
                _parsingContext.setCurrentName(_seenNames[k2]);
                return JsonToken.FIELD_NAME;

            case 52: // '4'
                _handleLongFieldName();
                return JsonToken.FIELD_NAME;
            }
            // fall through

        default:
            if (true)
            {
                continue;
            }
            // fall through

        case 1: // '\001'
            int l1 = j & 0x3f;
            if (l1 >= _seenNameCount)
            {
                _reportInvalidSharedName(l1);
            }
            _parsingContext.setCurrentName(_seenNames[l1]);
            return JsonToken.FIELD_NAME;

        case 2: // '\002'
            int j1 = 1 + (j & 0x3f);
            Name name1 = _findDecodedFromSymbols(j1);
            String s1;
            if (name1 != null)
            {
                s1 = name1.getName();
                _inputPtr = j1 + _inputPtr;
            } else
            {
                s1 = _addDecodedToSymbols(j1, _decodeShortAsciiName(j1));
            }
            if (_seenNames != null)
            {
                if (_seenNameCount >= _seenNames.length)
                {
                    _seenNames = _expandSeenNames(_seenNames);
                }
                String as1[] = _seenNames;
                int k1 = _seenNameCount;
                _seenNameCount = k1 + 1;
                as1[k1] = s1;
            }
            _parsingContext.setCurrentName(s1);
            return JsonToken.FIELD_NAME;

        case 3: // '\003'
            int k = j & 0x3f;
            if (k > 55)
            {
                if (k == 59)
                {
                    if (!_parsingContext.inObject())
                    {
                        _reportMismatchedEndMarker(125, ']');
                    }
                    _parsingContext = _parsingContext.getParent();
                    return JsonToken.END_OBJECT;
                }
            } else
            {
                int l = k + 2;
                Name name = _findDecodedFromSymbols(l);
                String s;
                if (name != null)
                {
                    s = name.getName();
                    _inputPtr = l + _inputPtr;
                } else
                {
                    s = _addDecodedToSymbols(l, _decodeShortUnicodeName(l));
                }
                if (_seenNames != null)
                {
                    if (_seenNameCount >= _seenNames.length)
                    {
                        _seenNames = _expandSeenNames(_seenNames);
                    }
                    String as[] = _seenNames;
                    int i1 = _seenNameCount;
                    _seenNameCount = i1 + 1;
                    as[i1] = s;
                }
                _parsingContext.setCurrentName(s);
                return JsonToken.FIELD_NAME;
            }
            break;
        }
        while (true) 
        {
            _reportError((new StringBuilder()).append("Invalid type marker byte 0x").append(Integer.toHexString(j)).append(" for expected field name (or END_OBJECT marker)").toString());
            return null;
        }
    }

    protected void _parseNumericValue(int i)
        throws IOException, JsonParseException
    {
        if (_tokenIncomplete)
        {
            int j = _typeByte;
            if ((7 & j >> 5) != 1)
            {
                _reportError((new StringBuilder()).append("Current token (").append(_currToken).append(") not numeric, can not use numeric value accessors").toString());
            }
            _tokenIncomplete = false;
            _finishNumberToken(j);
        }
    }

    protected void _releaseBuffers()
        throws IOException
    {
        super._releaseBuffers();
        String as[] = _seenNames;
        if (as != null && as.length > 0)
        {
            _seenNames = null;
            Arrays.fill(as, 0, _seenNameCount, null);
            _smileBufferRecycler.releaseSeenNamesBuffer(as);
        }
        String as1[] = _seenStringValues;
        if (as1 != null && as1.length > 0)
        {
            _seenStringValues = null;
            Arrays.fill(as1, 0, _seenStringValueCount, null);
            _smileBufferRecycler.releaseSeenStringValuesBuffer(as1);
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

    protected void _reportInvalidSharedName(int i)
        throws IOException
    {
        if (_seenNames == null)
        {
            _reportError("Encountered shared name reference, even though document header explicitly declared no shared name references are included");
        }
        _reportError((new StringBuilder()).append("Invalid shared name reference ").append(i).append("; only got ").append(_seenNameCount).append(" names in buffer (invalid content)").toString());
    }

    protected void _reportInvalidSharedStringValue(int i)
        throws IOException
    {
        if (_seenStringValues == null)
        {
            _reportError("Encountered shared text value reference, even though document header did not declared shared text value references may be included");
        }
        _reportError((new StringBuilder()).append("Invalid shared text value reference ").append(i).append("; only got ").append(_seenStringValueCount).append(" names in buffer (invalid content)").toString());
    }

    protected void _skip7BitBinary()
        throws IOException, JsonParseException
    {
        int i = _readUnsignedVInt();
        int j = i / 7;
        int k = j * 8;
        int l = i - j * 7;
        if (l > 0)
        {
            k += l + 1;
        }
        _skipBytes(k);
    }

    protected void _skipBytes(int i)
        throws IOException, JsonParseException
    {
        do
        {
            int j = Math.min(i, _inputEnd - _inputPtr);
            _inputPtr = j + _inputPtr;
            i -= j;
            if (i <= 0)
            {
                return;
            }
            loadMoreGuaranteed();
        } while (true);
    }

    protected void _skipIncomplete()
        throws IOException, JsonParseException
    {
        int i;
        _tokenIncomplete = false;
        i = _typeByte;
        7 & i >> 5;
        JVM INSTR tableswitch 1 7: default 60
    //                   1 65
    //                   2 250
    //                   3 250
    //                   4 261
    //                   5 261
    //                   6 60
    //                   7 272;
           goto _L1 _L2 _L3 _L3 _L4 _L4 _L1 _L5
_L19:
        _throwInternal();
        return;
_L2:
        int l = i & 0x1f;
        l >> 2;
        JVM INSTR tableswitch 1 2: default 96
    //                   1 99
    //                   2 195;
           goto _L6 _L7 _L8
_L6:
        continue; /* Loop/switch isn't completed */
_L7:
        l & 3;
        JVM INSTR tableswitch 0 2: default 128
    //                   0 131
    //                   1 175
    //                   2 190;
           goto _L9 _L10 _L11 _L12
_L10:
        break MISSING_BLOCK_LABEL_131;
_L9:
        continue; /* Loop/switch isn't completed */
_L17:
        int i1;
        byte abyte1[];
        i1 = _inputEnd;
        abyte1 = _inputBuffer;
_L16:
        if (_inputPtr >= i1) goto _L14; else goto _L13
_L13:
        int j1;
        j1 = _inputPtr;
        _inputPtr = j1 + 1;
        if (abyte1[j1] >= 0) goto _L16; else goto _L15
_L15:
        return;
_L11:
        _skipBytes(4);
          goto _L17
_L14:
        loadMoreGuaranteed();
          goto _L17
_L12:
        _skip7BitBinary();
        return;
_L8:
        switch (l & 3)
        {
        case 0: // '\0'
            _skipBytes(5);
            return;

        case 1: // '\001'
            _skipBytes(10);
            return;

        case 2: // '\002'
            _readUnsignedVInt();
            _skip7BitBinary();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        _skipBytes(1 + (i & 0x3f));
        return;
_L4:
        _skipBytes(2 + (i & 0x3f));
        return;
_L5:
        switch ((i & 0x1f) >> 2)
        {
        case 0: // '\0'
        case 1: // '\001'
            do
            {
                int j = _inputEnd;
                byte abyte0[] = _inputBuffer;
                while (_inputPtr < j) 
                {
                    int k = _inputPtr;
                    _inputPtr = k + 1;
                    if (abyte0[k] == -4)
                    {
                        return;
                    }
                }
                loadMoreGuaranteed();
            } while (true);

        case 2: // '\002'
            _skip7BitBinary();
            return;

        case 7: // '\007'
            _skipBytes(_readUnsignedVInt());
            return;
        }
_L1:
        if (true) goto _L19; else goto _L18
_L18:
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
        if (_tokenIncomplete)
        {
            _finishToken();
        }
        if (_currToken != JsonToken.VALUE_EMBEDDED_OBJECT)
        {
            _reportError((new StringBuilder()).append("Current token (").append(_currToken).append(") not VALUE_EMBEDDED_OBJECT, can not access as binary").toString());
        }
        return _binaryValue;
    }

    public ObjectCodec getCodec()
    {
        return _objectCodec;
    }

    public String getCurrentName()
        throws IOException, JsonParseException
    {
        return _parsingContext.getCurrentName();
    }

    public org.codehaus.jackson.JsonParser.NumberType getNumberType()
        throws IOException, JsonParseException
    {
        if (_got32BitFloat)
        {
            return org.codehaus.jackson.JsonParser.NumberType.FLOAT;
        } else
        {
            return super.getNumberType();
        }
    }

    public String getText()
        throws IOException, JsonParseException
    {
        if (_tokenIncomplete)
        {
            _tokenIncomplete = false;
            int i = _typeByte;
            int j = 7 & i >> 5;
            if (j == 2 || j == 3)
            {
                _decodeShortAsciiValue(1 + (i & 0x3f));
                return _textBuffer.contentsAsString();
            }
            if (j == 4 || j == 5)
            {
                _decodeShortUnicodeValue(2 + (i & 0x3f));
                return _textBuffer.contentsAsString();
            }
            _finishToken();
        }
        if (_currToken == JsonToken.VALUE_STRING)
        {
            return _textBuffer.contentsAsString();
        }
        JsonToken jsontoken = _currToken;
        if (jsontoken == null)
        {
            return null;
        }
        if (jsontoken == JsonToken.FIELD_NAME)
        {
            return _parsingContext.getCurrentName();
        }
        if (jsontoken.isNumeric())
        {
            return getNumberValue().toString();
        } else
        {
            return _currToken.asString();
        }
    }

    public char[] getTextCharacters()
        throws IOException, JsonParseException
    {
        if (_currToken != null)
        {
            if (_tokenIncomplete)
            {
                _finishToken();
            }
            static class _cls1
            {

                static final int $SwitchMap$org$codehaus$jackson$JsonToken[];

                static 
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 1;
                    }
                    catch (NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 2;
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

            switch (_cls1..SwitchMap.org.codehaus.jackson.JsonToken[_currToken.ordinal()])
            {
            default:
                return _currToken.asCharArray();

            case 1: // '\001'
                return _textBuffer.getTextBuffer();

            case 2: // '\002'
                if (!_nameCopied)
                {
                    String s = _parsingContext.getCurrentName();
                    int i = s.length();
                    if (_nameCopyBuffer == null)
                    {
                        _nameCopyBuffer = _ioContext.allocNameCopyBuffer(i);
                    } else
                    if (_nameCopyBuffer.length < i)
                    {
                        _nameCopyBuffer = new char[i];
                    }
                    s.getChars(0, i, _nameCopyBuffer, 0);
                    _nameCopied = true;
                }
                return _nameCopyBuffer;

            case 3: // '\003'
            case 4: // '\004'
                return getNumberValue().toString().toCharArray();
            }
        } else
        {
            return null;
        }
    }

    public int getTextLength()
        throws IOException, JsonParseException
    {
        if (_currToken != null)
        {
            if (_tokenIncomplete)
            {
                _finishToken();
            }
            switch (_cls1..SwitchMap.org.codehaus.jackson.JsonToken[_currToken.ordinal()])
            {
            default:
                return _currToken.asCharArray().length;

            case 1: // '\001'
                return _textBuffer.size();

            case 2: // '\002'
                return _parsingContext.getCurrentName().length();

            case 3: // '\003'
            case 4: // '\004'
                return getNumberValue().toString().length();
            }
        } else
        {
            return 0;
        }
    }

    public int getTextOffset()
        throws IOException, JsonParseException
    {
        return 0;
    }

    protected boolean handleSignature(boolean flag, boolean flag1)
        throws IOException, JsonParseException
    {
        if (flag)
        {
            _inputPtr = 1 + _inputPtr;
        }
        if (_inputPtr >= _inputEnd)
        {
            loadMoreGuaranteed();
        }
        if (_inputBuffer[_inputPtr] != 41)
        {
            if (flag1)
            {
                _reportError((new StringBuilder()).append("Malformed content: signature not valid, starts with 0x3a but followed by 0x").append(Integer.toHexString(_inputBuffer[_inputPtr])).append(", not 0x29").toString());
            }
        } else
        {
            int i = 1 + _inputPtr;
            _inputPtr = i;
            if (i >= _inputEnd)
            {
                loadMoreGuaranteed();
            }
            if (_inputBuffer[_inputPtr] != 10)
            {
                if (flag1)
                {
                    _reportError((new StringBuilder()).append("Malformed content: signature not valid, starts with 0x3a, 0x29, but followed by 0x").append(Integer.toHexString(_inputBuffer[_inputPtr])).append(", not 0xA").toString());
                    return false;
                }
            } else
            {
                int j = 1 + _inputPtr;
                _inputPtr = j;
                if (j >= _inputEnd)
                {
                    loadMoreGuaranteed();
                }
                byte abyte0[] = _inputBuffer;
                int k = _inputPtr;
                _inputPtr = k + 1;
                byte byte0 = abyte0[k];
                int l = 0xf & byte0 >> 4;
                if (l != 0)
                {
                    _reportError((new StringBuilder()).append("Header version number bits (0x").append(Integer.toHexString(l)).append(") indicate unrecognized version; only 0x0 handled by parser").toString());
                }
                if ((byte0 & 1) == 0)
                {
                    _seenNames = null;
                    _seenNameCount = -1;
                }
                if ((byte0 & 2) != 0)
                {
                    _seenStringValues = NO_STRINGS;
                    _seenStringValueCount = 0;
                }
                int i1 = byte0 & 4;
                boolean flag2 = false;
                if (i1 != 0)
                {
                    flag2 = true;
                }
                _mayContainRawBinary = flag2;
                return true;
            }
        }
        return false;
    }

    public boolean mayContainRawBinary()
    {
        return _mayContainRawBinary;
    }

    public JsonToken nextToken()
        throws IOException, JsonParseException
    {
        boolean flag = true;
        if (_tokenIncomplete)
        {
            _skipIncomplete();
        }
        _tokenInputTotal = (_currInputProcessed + (long)_inputPtr) - 1L;
        _binaryValue = null;
        if (_parsingContext.inObject() && _currToken != JsonToken.FIELD_NAME)
        {
            JsonToken jsontoken13 = _handleFieldName();
            _currToken = jsontoken13;
            return jsontoken13;
        }
        if (_inputPtr >= _inputEnd && !loadMore())
        {
            _handleEOF();
            close();
            _currToken = null;
            return null;
        }
        byte abyte0[] = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = i + 1;
        int j = abyte0[i];
        _typeByte = j;
        switch (7 & j >> 5)
        {
        case 0: // '\0'
            if (j == 0)
            {
                _reportError("Invalid token byte 0x00");
            }
            return _handleSharedString(j - 1);

        case 1: // '\001'
            int i1 = j & 0x1f;
            if (i1 < 4)
            {
                JsonToken jsontoken9;
                switch (i1)
                {
                default:
                    JsonToken jsontoken12 = JsonToken.VALUE_TRUE;
                    _currToken = jsontoken12;
                    return jsontoken12;

                case 0: // '\0'
                    _textBuffer.resetWithEmpty();
                    JsonToken jsontoken11 = JsonToken.VALUE_STRING;
                    _currToken = jsontoken11;
                    return jsontoken11;

                case 1: // '\001'
                    JsonToken jsontoken10 = JsonToken.VALUE_NULL;
                    _currToken = jsontoken10;
                    return jsontoken10;

                case 2: // '\002'
                    jsontoken9 = JsonToken.VALUE_FALSE;
                    break;
                }
                _currToken = jsontoken9;
                return jsontoken9;
            }
            if (i1 < 8)
            {
                if ((i1 & 3) <= 2)
                {
                    _tokenIncomplete = flag;
                    _numTypesValid = 0;
                    JsonToken jsontoken8 = JsonToken.VALUE_NUMBER_INT;
                    _currToken = jsontoken8;
                    return jsontoken8;
                }
                continue;
            }
            if (i1 < 12)
            {
                int j1 = i1 & 3;
                if (j1 <= 2)
                {
                    _tokenIncomplete = flag;
                    _numTypesValid = 0;
                    JsonToken jsontoken7;
                    if (j1 != 0)
                    {
                        flag = false;
                    }
                    _got32BitFloat = flag;
                    jsontoken7 = JsonToken.VALUE_NUMBER_FLOAT;
                    _currToken = jsontoken7;
                    return jsontoken7;
                }
            } else
            {
                if (i1 == 26 && handleSignature(false, false))
                {
                    if (_currToken == null)
                    {
                        return nextToken();
                    } else
                    {
                        _currToken = null;
                        return null;
                    }
                }
                _reportError("Unrecognized token byte 0x3A (malformed segment header?");
            }
            continue;

        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
            _currToken = JsonToken.VALUE_STRING;
            if (_seenStringValueCount >= 0)
            {
                _addSeenStringValue();
            } else
            {
                _tokenIncomplete = flag;
            }
            return _currToken;

        case 6: // '\006'
            _numberInt = SmileUtil.zigzagDecode(j & 0x1f);
            _numTypesValid = ((flag) ? 1 : 0);
            JsonToken jsontoken6 = JsonToken.VALUE_NUMBER_INT;
            _currToken = jsontoken6;
            return jsontoken6;

        case 7: // '\007'
            switch (j & 0x1f)
            {
            case 0: // '\0'
            case 4: // '\004'
                _tokenIncomplete = flag;
                JsonToken jsontoken5 = JsonToken.VALUE_STRING;
                _currToken = jsontoken5;
                return jsontoken5;

            case 8: // '\b'
                _tokenIncomplete = flag;
                JsonToken jsontoken4 = JsonToken.VALUE_EMBEDDED_OBJECT;
                _currToken = jsontoken4;
                return jsontoken4;

            case 12: // '\f'
            case 13: // '\r'
            case 14: // '\016'
            case 15: // '\017'
                if (_inputPtr >= _inputEnd)
                {
                    loadMoreGuaranteed();
                }
                int k = (j & 3) << 8;
                byte abyte1[] = _inputBuffer;
                int l = _inputPtr;
                _inputPtr = l + 1;
                return _handleSharedString(k + (0xff & abyte1[l]));

            case 24: // '\030'
                _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
                JsonToken jsontoken3 = JsonToken.START_ARRAY;
                _currToken = jsontoken3;
                return jsontoken3;

            case 25: // '\031'
                if (!_parsingContext.inArray())
                {
                    _reportMismatchedEndMarker(93, '}');
                }
                _parsingContext = _parsingContext.getParent();
                JsonToken jsontoken2 = JsonToken.END_ARRAY;
                _currToken = jsontoken2;
                return jsontoken2;

            case 26: // '\032'
                _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
                JsonToken jsontoken1 = JsonToken.START_OBJECT;
                _currToken = jsontoken1;
                return jsontoken1;

            case 27: // '\033'
                _reportError("Invalid type marker byte 0xFB in value mode (would be END_OBJECT in key mode)");
                // fall through

            case 29: // '\035'
                _tokenIncomplete = flag;
                JsonToken jsontoken = JsonToken.VALUE_EMBEDDED_OBJECT;
                _currToken = jsontoken;
                return jsontoken;

            case 31: // '\037'
                _currToken = null;
                return null;
            }
            break;
        }
        while (true) 
        {
            _reportError((new StringBuilder()).append("Invalid type marker byte 0x").append(Integer.toHexString(j & 0xff)).append(" for expected value token").toString());
            return null;
        }
    }

    public void setCodec(ObjectCodec objectcodec)
    {
        _objectCodec = objectcodec;
    }

}
