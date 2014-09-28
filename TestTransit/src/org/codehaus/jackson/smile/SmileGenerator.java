// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.smile;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.PrettyPrinter;
import org.codehaus.jackson.SerializableString;
import org.codehaus.jackson.impl.JsonGeneratorBase;
import org.codehaus.jackson.impl.JsonWriteContext;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.io.SerializedString;

// Referenced classes of package org.codehaus.jackson.smile:
//            SmileBufferRecycler, SmileUtil

public class SmileGenerator extends JsonGeneratorBase
{
    public static final class Feature extends Enum
    {

        private static final Feature $VALUES[];
        public static final Feature CHECK_SHARED_NAMES;
        public static final Feature CHECK_SHARED_STRING_VALUES;
        public static final Feature ENCODE_BINARY_AS_7BIT;
        public static final Feature WRITE_END_MARKER;
        public static final Feature WRITE_HEADER;
        protected final boolean _defaultState;
        protected final int _mask = 1 << ordinal();

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
            return (Feature)Enum.valueOf(org/codehaus/jackson/smile/SmileGenerator$Feature, s);
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
            WRITE_HEADER = new Feature("WRITE_HEADER", 0, true);
            WRITE_END_MARKER = new Feature("WRITE_END_MARKER", 1, false);
            ENCODE_BINARY_AS_7BIT = new Feature("ENCODE_BINARY_AS_7BIT", 2, true);
            CHECK_SHARED_NAMES = new Feature("CHECK_SHARED_NAMES", 3, true);
            CHECK_SHARED_STRING_VALUES = new Feature("CHECK_SHARED_STRING_VALUES", 4, false);
            Feature afeature[] = new Feature[5];
            afeature[0] = WRITE_HEADER;
            afeature[1] = WRITE_END_MARKER;
            afeature[2] = ENCODE_BINARY_AS_7BIT;
            afeature[3] = CHECK_SHARED_NAMES;
            afeature[4] = CHECK_SHARED_STRING_VALUES;
            $VALUES = afeature;
        }

        private Feature(String s, int i, boolean flag)
        {
            super(s, i);
            _defaultState = flag;
        }
    }

    protected static final class SharedStringNode
    {

        public final int index;
        public SharedStringNode next;
        public final String value;

        public SharedStringNode(String s, int i, SharedStringNode sharedstringnode)
        {
            value = s;
            index = i;
            next = sharedstringnode;
        }
    }


    protected static final long MAX_INT_AS_LONG = 0x7fffffffL;
    private static final int MIN_BUFFER_LENGTH = 770;
    protected static final long MIN_INT_AS_LONG = 0xffffffff80000000L;
    protected static final int SURR1_FIRST = 55296;
    protected static final int SURR1_LAST = 56319;
    protected static final int SURR2_FIRST = 56320;
    protected static final int SURR2_LAST = 57343;
    protected static final byte TOKEN_BYTE_BIG_DECIMAL = 42;
    protected static final byte TOKEN_BYTE_BIG_INTEGER = 38;
    protected static final byte TOKEN_BYTE_FLOAT_32 = 40;
    protected static final byte TOKEN_BYTE_FLOAT_64 = 41;
    protected static final byte TOKEN_BYTE_INT_32 = 36;
    protected static final byte TOKEN_BYTE_INT_64 = 37;
    protected static final byte TOKEN_BYTE_LONG_STRING_ASCII = -32;
    protected static final byte TOKEN_BYTE_LONG_STRING_UNICODE = -28;
    protected static final ThreadLocal _smileRecyclerRef = new ThreadLocal();
    protected boolean _bufferRecyclable;
    protected int _bytesWritten;
    protected char _charBuffer[];
    protected final int _charBufferLength;
    protected final IOContext _ioContext;
    protected final OutputStream _out;
    protected byte _outputBuffer[];
    protected final int _outputEnd;
    protected int _outputTail;
    protected int _seenNameCount;
    protected SharedStringNode _seenNames[];
    protected int _seenStringValueCount;
    protected SharedStringNode _seenStringValues[];
    protected final SmileBufferRecycler _smileBufferRecycler;
    protected int _smileFeatures;

    public SmileGenerator(IOContext iocontext, int i, int j, ObjectCodec objectcodec, OutputStream outputstream)
    {
        super(i, objectcodec);
        _outputTail = 0;
        _smileFeatures = j;
        _ioContext = iocontext;
        _smileBufferRecycler = _smileBufferRecycler();
        _out = outputstream;
        _bufferRecyclable = true;
        _outputBuffer = iocontext.allocWriteEncodingBuffer();
        _outputEnd = _outputBuffer.length;
        _charBuffer = iocontext.allocConcatBuffer();
        _charBufferLength = _charBuffer.length;
        if (_outputEnd < 770)
        {
            throw new IllegalStateException((new StringBuilder()).append("Internal encoding buffer length (").append(_outputEnd).append(") too short, must be at least ").append(770).toString());
        }
        if ((j & Feature.CHECK_SHARED_NAMES.getMask()) == 0)
        {
            _seenNames = null;
            _seenNameCount = -1;
        } else
        {
            _seenNames = (SharedStringNode[])_smileBufferRecycler.allocSeenNamesBuffer();
            if (_seenNames == null)
            {
                _seenNames = new SharedStringNode[64];
            }
            _seenNameCount = 0;
        }
        if ((j & Feature.CHECK_SHARED_STRING_VALUES.getMask()) == 0)
        {
            _seenStringValues = null;
            _seenStringValueCount = -1;
            return;
        }
        _seenStringValues = (SharedStringNode[])_smileBufferRecycler.allocSeenStringValuesBuffer();
        if (_seenStringValues == null)
        {
            _seenStringValues = new SharedStringNode[64];
        }
        _seenStringValueCount = 0;
    }

    public SmileGenerator(IOContext iocontext, int i, int j, ObjectCodec objectcodec, OutputStream outputstream, byte abyte0[], int k, 
            boolean flag)
    {
        super(i, objectcodec);
        _outputTail = 0;
        _smileFeatures = j;
        _ioContext = iocontext;
        _smileBufferRecycler = _smileBufferRecycler();
        _out = outputstream;
        _bufferRecyclable = flag;
        _outputTail = k;
        _outputBuffer = abyte0;
        _outputEnd = _outputBuffer.length;
        _charBuffer = iocontext.allocConcatBuffer();
        _charBufferLength = _charBuffer.length;
        if (_outputEnd < 770)
        {
            throw new IllegalStateException((new StringBuilder()).append("Internal encoding buffer length (").append(_outputEnd).append(") too short, must be at least ").append(770).toString());
        }
        if ((j & Feature.CHECK_SHARED_NAMES.getMask()) == 0)
        {
            _seenNames = null;
            _seenNameCount = -1;
        } else
        {
            _seenNames = (SharedStringNode[])_smileBufferRecycler.allocSeenNamesBuffer();
            if (_seenNames == null)
            {
                _seenNames = new SharedStringNode[64];
            }
            _seenNameCount = 0;
        }
        if ((j & Feature.CHECK_SHARED_STRING_VALUES.getMask()) == 0)
        {
            _seenStringValues = null;
            _seenStringValueCount = -1;
            return;
        }
        _seenStringValues = (SharedStringNode[])_smileBufferRecycler.allocSeenStringValuesBuffer();
        if (_seenStringValues == null)
        {
            _seenStringValues = new SharedStringNode[64];
        }
        _seenStringValueCount = 0;
    }

    private final void _addSeenName(String s)
    {
        int i;
        if (_seenNameCount == _seenNames.length)
        {
            if (_seenNameCount == 1024)
            {
                Arrays.fill(_seenNames, null);
                _seenNameCount = 0;
            } else
            {
                SharedStringNode asharedstringnode[] = _seenNames;
                _seenNames = new SharedStringNode[1024];
                int j = asharedstringnode.length;
                int k = 0;
                while (k < j) 
                {
                    for (SharedStringNode sharedstringnode = asharedstringnode[k]; sharedstringnode != null; sharedstringnode = sharedstringnode.next)
                    {
                        int l = 0x3ff & sharedstringnode.value.hashCode();
                        sharedstringnode.next = _seenNames[l];
                        _seenNames[l] = sharedstringnode;
                    }

                    k++;
                }
            }
        }
        i = s.hashCode() & -1 + _seenNames.length;
        _seenNames[i] = new SharedStringNode(s, _seenNameCount, _seenNames[i]);
        _seenNameCount = 1 + _seenNameCount;
    }

    private final void _addSeenStringValue(String s)
    {
        int i;
        if (_seenStringValueCount == _seenStringValues.length)
        {
            if (_seenStringValueCount == 1024)
            {
                Arrays.fill(_seenStringValues, null);
                _seenStringValueCount = 0;
            } else
            {
                SharedStringNode asharedstringnode[] = _seenStringValues;
                _seenStringValues = new SharedStringNode[1024];
                int j = asharedstringnode.length;
                int k = 0;
                while (k < j) 
                {
                    for (SharedStringNode sharedstringnode = asharedstringnode[k]; sharedstringnode != null; sharedstringnode = sharedstringnode.next)
                    {
                        int l = 0x3ff & sharedstringnode.value.hashCode();
                        sharedstringnode.next = _seenStringValues[l];
                        _seenStringValues[l] = sharedstringnode;
                    }

                    k++;
                }
            }
        }
        i = s.hashCode() & -1 + _seenStringValues.length;
        _seenStringValues[i] = new SharedStringNode(s, _seenStringValueCount, _seenStringValues[i]);
        _seenStringValueCount = 1 + _seenStringValueCount;
    }

    private int _convertSurrogate(int i, int j)
    {
        if (j < 56320 || j > 57343)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Broken surrogate pair: first char 0x").append(Integer.toHexString(i)).append(", second 0x").append(Integer.toHexString(j)).append("; illegal combination").toString());
        } else
        {
            return 0x10000 + (i - 55296 << 10) + (j - 56320);
        }
    }

    private final void _ensureRoomForOutput(int i)
        throws IOException
    {
        if (i + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
    }

    private final int _findSeenName(String s)
    {
        int i = s.hashCode();
        SharedStringNode sharedstringnode = _seenNames[i & -1 + _seenNames.length];
        if (sharedstringnode == null)
        {
            return -1;
        }
        SharedStringNode sharedstringnode1 = sharedstringnode;
        if (sharedstringnode1.value == s)
        {
            return sharedstringnode1.index;
        }
        do
        {
            sharedstringnode1 = sharedstringnode1.next;
            if (sharedstringnode1 != null)
            {
                if (sharedstringnode1.value == s)
                {
                    return sharedstringnode1.index;
                }
            } else
            {
                SharedStringNode sharedstringnode2 = sharedstringnode;
                do
                {
                    String s1 = sharedstringnode2.value;
                    if (s1.hashCode() == i && s1.equals(s))
                    {
                        return sharedstringnode2.index;
                    }
                    sharedstringnode2 = sharedstringnode2.next;
                } while (sharedstringnode2 != null);
                return -1;
            }
        } while (true);
    }

    private final int _findSeenStringValue(String s)
    {
        int i = s.hashCode();
        SharedStringNode sharedstringnode = _seenStringValues[i & -1 + _seenStringValues.length];
        if (sharedstringnode != null)
        {
            SharedStringNode sharedstringnode1 = sharedstringnode;
            do
            {
                if (sharedstringnode1.value == s)
                {
                    return sharedstringnode1.index;
                }
                sharedstringnode1 = sharedstringnode1.next;
            } while (sharedstringnode1 != null);
            SharedStringNode sharedstringnode2 = sharedstringnode;
            do
            {
                String s1 = sharedstringnode2.value;
                if (s1.hashCode() == i && s1.equals(s))
                {
                    return sharedstringnode2.index;
                }
                sharedstringnode2 = sharedstringnode2.next;
            } while (sharedstringnode2 != null);
        }
        return -1;
    }

    private void _mediumUTF8Encode(char ac[], int i, int j)
        throws IOException
    {
        int k;
        int l;
        k = -4 + _outputEnd;
        l = i;
_L4:
        if (l >= j) goto _L2; else goto _L1
_L1:
        int i1;
        char c;
        int l4;
        if (_outputTail >= k)
        {
            _flushBuffer();
        }
        i1 = l + 1;
        c = ac[l];
        if (c > '\177')
        {
            break MISSING_BLOCK_LABEL_600;
        }
        byte abyte9[] = _outputBuffer;
        int i4 = _outputTail;
        _outputTail = i4 + 1;
        abyte9[i4] = (byte)c;
        int j4 = j - i1;
        int k4 = k - _outputTail;
        if (j4 > k4)
        {
            j4 = k4;
        }
        l4 = j4 + i1;
        l = i1;
_L9:
        if (l >= l4) goto _L4; else goto _L3
_L3:
        int i5;
        i5 = l + 1;
        c = ac[l];
        if (c <= '\177') goto _L6; else goto _L5
_L5:
        l = i5;
_L11:
        if (c >= '\u0800') goto _L8; else goto _L7
_L7:
        int i2;
        byte abyte7[] = _outputBuffer;
        int k3 = _outputTail;
        _outputTail = k3 + 1;
        abyte7[k3] = (byte)(0xc0 | c >> 6);
        byte abyte8[] = _outputBuffer;
        int l3 = _outputTail;
        _outputTail = l3 + 1;
        abyte8[l3] = (byte)(0x80 | c & 0x3f);
        i2 = l;
_L10:
        l = i2;
          goto _L4
_L6:
        byte abyte10[] = _outputBuffer;
        int j5 = _outputTail;
        _outputTail = j5 + 1;
        abyte10[j5] = (byte)c;
        l = i5;
          goto _L9
_L8:
label0:
        {
            if (c >= '\uD800' && c <= '\uDFFF')
            {
                break label0;
            }
            byte abyte0[] = _outputBuffer;
            int j1 = _outputTail;
            _outputTail = j1 + 1;
            abyte0[j1] = (byte)(0xe0 | c >> 12);
            byte abyte1[] = _outputBuffer;
            int k1 = _outputTail;
            _outputTail = k1 + 1;
            abyte1[k1] = (byte)(0x80 | 0x3f & c >> 6);
            byte abyte2[] = _outputBuffer;
            int l1 = _outputTail;
            _outputTail = l1 + 1;
            abyte2[l1] = (byte)(0x80 | c & 0x3f);
        }
          goto _L4
        if (c > '\uDBFF')
        {
            _throwIllegalSurrogate(c);
        }
        if (l >= j)
        {
            _throwIllegalSurrogate(c);
        }
        i2 = l + 1;
        int j2 = _convertSurrogate(c, ac[l]);
        if (j2 > 0x10ffff)
        {
            _throwIllegalSurrogate(j2);
        }
        byte abyte3[] = _outputBuffer;
        int k2 = _outputTail;
        _outputTail = k2 + 1;
        abyte3[k2] = (byte)(0xf0 | j2 >> 18);
        byte abyte4[] = _outputBuffer;
        int l2 = _outputTail;
        _outputTail = l2 + 1;
        abyte4[l2] = (byte)(0x80 | 0x3f & j2 >> 12);
        byte abyte5[] = _outputBuffer;
        int i3 = _outputTail;
        _outputTail = i3 + 1;
        abyte5[i3] = (byte)(0x80 | 0x3f & j2 >> 6);
        byte abyte6[] = _outputBuffer;
        int j3 = _outputTail;
        _outputTail = j3 + 1;
        abyte6[j3] = (byte)(0x80 | j2 & 0x3f);
          goto _L10
_L2:
        return;
        l = i1;
          goto _L11
    }

    private final int _shortUTF8Encode(char ac[], int i, int j)
    {
        int k = _outputTail;
        byte abyte0[] = _outputBuffer;
        do
        {
            char c = ac[i];
            if (c > '\177')
            {
                return _shortUTF8Encode2(ac, i, j, k);
            }
            int l = k + 1;
            abyte0[k] = (byte)c;
            if (++i >= j)
            {
                int i1 = l - _outputTail;
                _outputTail = l;
                return i1;
            }
            k = l;
        } while (true);
    }

    private final int _shortUTF8Encode2(char ac[], int i, int j, int k)
    {
        byte abyte0[] = _outputBuffer;
        int l = k;
        for (int i1 = i; i1 < j;)
        {
            int k1 = i1 + 1;
            char c = ac[i1];
            if (c <= '\177')
            {
                int l3 = l + 1;
                abyte0[l] = (byte)c;
                l = l3;
                i1 = k1;
            } else
            if (c < '\u0800')
            {
                int k3 = l + 1;
                abyte0[l] = (byte)(0xc0 | c >> 6);
                l = k3 + 1;
                abyte0[k3] = (byte)(0x80 | c & 0x3f);
                i1 = k1;
            } else
            if (c < '\uD800' || c > '\uDFFF')
            {
                int l1 = l + 1;
                abyte0[l] = (byte)(0xe0 | c >> 12);
                int i2 = l1 + 1;
                abyte0[l1] = (byte)(0x80 | 0x3f & c >> 6);
                int j2 = i2 + 1;
                abyte0[i2] = (byte)(0x80 | c & 0x3f);
                l = j2;
                i1 = k1;
            } else
            {
                if (c > '\uDBFF')
                {
                    _throwIllegalSurrogate(c);
                }
                if (k1 >= j)
                {
                    _throwIllegalSurrogate(c);
                }
                i1 = k1 + 1;
                int k2 = _convertSurrogate(c, ac[k1]);
                if (k2 > 0x10ffff)
                {
                    _throwIllegalSurrogate(k2);
                }
                int l2 = l + 1;
                abyte0[l] = (byte)(0xf0 | k2 >> 18);
                int i3 = l2 + 1;
                abyte0[l2] = (byte)(0x80 | 0x3f & k2 >> 12);
                int j3 = i3 + 1;
                abyte0[i3] = (byte)(0x80 | 0x3f & k2 >> 6);
                l = j3 + 1;
                abyte0[j3] = (byte)(0x80 | k2 & 0x3f);
            }
        }

        int j1 = l - _outputTail;
        _outputTail = l;
        return j1;
    }

    private void _slowUTF8Encode(String s)
        throws IOException
    {
        int i;
        int j;
        int k;
        i = s.length();
        j = -4 + _outputEnd;
        k = 0;
_L4:
        if (k >= i) goto _L2; else goto _L1
_L1:
        int l;
        char c;
        int k4;
        if (_outputTail >= j)
        {
            _flushBuffer();
        }
        l = k + 1;
        c = s.charAt(k);
        if (c > '\177')
        {
            break MISSING_BLOCK_LABEL_608;
        }
        byte abyte9[] = _outputBuffer;
        int l3 = _outputTail;
        _outputTail = l3 + 1;
        abyte9[l3] = (byte)c;
        int i4 = i - l;
        int j4 = j - _outputTail;
        if (i4 > j4)
        {
            i4 = j4;
        }
        k4 = i4 + l;
        k = l;
_L9:
        if (k >= k4) goto _L4; else goto _L3
_L3:
        int l4;
        l4 = k + 1;
        c = s.charAt(k);
        if (c <= '\177') goto _L6; else goto _L5
_L5:
        k = l4;
_L11:
        if (c >= '\u0800') goto _L8; else goto _L7
_L7:
        int l1;
        byte abyte7[] = _outputBuffer;
        int j3 = _outputTail;
        _outputTail = j3 + 1;
        abyte7[j3] = (byte)(0xc0 | c >> 6);
        byte abyte8[] = _outputBuffer;
        int k3 = _outputTail;
        _outputTail = k3 + 1;
        abyte8[k3] = (byte)(0x80 | c & 0x3f);
        l1 = k;
_L10:
        k = l1;
          goto _L4
_L6:
        byte abyte10[] = _outputBuffer;
        int i5 = _outputTail;
        _outputTail = i5 + 1;
        abyte10[i5] = (byte)c;
        k = l4;
          goto _L9
_L8:
label0:
        {
            if (c >= '\uD800' && c <= '\uDFFF')
            {
                break label0;
            }
            byte abyte0[] = _outputBuffer;
            int i1 = _outputTail;
            _outputTail = i1 + 1;
            abyte0[i1] = (byte)(0xe0 | c >> 12);
            byte abyte1[] = _outputBuffer;
            int j1 = _outputTail;
            _outputTail = j1 + 1;
            abyte1[j1] = (byte)(0x80 | 0x3f & c >> 6);
            byte abyte2[] = _outputBuffer;
            int k1 = _outputTail;
            _outputTail = k1 + 1;
            abyte2[k1] = (byte)(0x80 | c & 0x3f);
        }
          goto _L4
        if (c > '\uDBFF')
        {
            _throwIllegalSurrogate(c);
        }
        if (k >= i)
        {
            _throwIllegalSurrogate(c);
        }
        l1 = k + 1;
        int i2 = _convertSurrogate(c, s.charAt(k));
        if (i2 > 0x10ffff)
        {
            _throwIllegalSurrogate(i2);
        }
        byte abyte3[] = _outputBuffer;
        int j2 = _outputTail;
        _outputTail = j2 + 1;
        abyte3[j2] = (byte)(0xf0 | i2 >> 18);
        byte abyte4[] = _outputBuffer;
        int k2 = _outputTail;
        _outputTail = k2 + 1;
        abyte4[k2] = (byte)(0x80 | 0x3f & i2 >> 12);
        byte abyte5[] = _outputBuffer;
        int l2 = _outputTail;
        _outputTail = l2 + 1;
        abyte5[l2] = (byte)(0x80 | 0x3f & i2 >> 6);
        byte abyte6[] = _outputBuffer;
        int i3 = _outputTail;
        _outputTail = i3 + 1;
        abyte6[i3] = (byte)(0x80 | i2 & 0x3f);
          goto _L10
_L2:
        return;
        k = l;
          goto _L11
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

    private void _throwIllegalSurrogate(int i)
    {
        if (i > 0x10ffff)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal character point (0x").append(Integer.toHexString(i)).append(") to output; max is 0x10FFFF as per RFC 4627").toString());
        }
        if (i >= 55296)
        {
            if (i <= 56319)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Unmatched first part of surrogate pair (0x").append(Integer.toHexString(i)).append(")").toString());
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Unmatched second part of surrogate pair (0x").append(Integer.toHexString(i)).append(")").toString());
            }
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal character point (0x").append(Integer.toHexString(i)).append(") to output").toString());
        }
    }

    private final void _writeByte(byte byte0)
        throws IOException
    {
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = byte0;
    }

    private final void _writeBytes(byte byte0, byte byte1)
        throws IOException
    {
        if (1 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = byte0;
        byte abyte1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte1[j] = byte1;
    }

    private final void _writeBytes(byte byte0, byte byte1, byte byte2)
        throws IOException
    {
        if (2 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = byte0;
        byte abyte1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte1[j] = byte1;
        byte abyte2[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte2[k] = byte2;
    }

    private final void _writeBytes(byte byte0, byte byte1, byte byte2, byte byte3)
        throws IOException
    {
        if (3 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = byte0;
        byte abyte1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte1[j] = byte1;
        byte abyte2[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte2[k] = byte2;
        byte abyte3[] = _outputBuffer;
        int l = _outputTail;
        _outputTail = l + 1;
        abyte3[l] = byte3;
    }

    private final void _writeBytes(byte byte0, byte byte1, byte byte2, byte byte3, byte byte4)
        throws IOException
    {
        if (4 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = byte0;
        byte abyte1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte1[j] = byte1;
        byte abyte2[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte2[k] = byte2;
        byte abyte3[] = _outputBuffer;
        int l = _outputTail;
        _outputTail = l + 1;
        abyte3[l] = byte3;
        byte abyte4[] = _outputBuffer;
        int i1 = _outputTail;
        _outputTail = i1 + 1;
        abyte4[i1] = byte4;
    }

    private final void _writeBytes(byte byte0, byte byte1, byte byte2, byte byte3, byte byte4, byte byte5)
        throws IOException
    {
        if (5 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = byte0;
        byte abyte1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte1[j] = byte1;
        byte abyte2[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte2[k] = byte2;
        byte abyte3[] = _outputBuffer;
        int l = _outputTail;
        _outputTail = l + 1;
        abyte3[l] = byte3;
        byte abyte4[] = _outputBuffer;
        int i1 = _outputTail;
        _outputTail = i1 + 1;
        abyte4[i1] = byte4;
        byte abyte5[] = _outputBuffer;
        int j1 = _outputTail;
        _outputTail = j1 + 1;
        abyte5[j1] = byte5;
    }

    private final void _writeBytes(byte abyte0[], int i, int j)
        throws IOException
    {
        if (j == 0)
        {
            return;
        }
        if (j + _outputTail >= _outputEnd)
        {
            _writeBytesLong(abyte0, i, j);
            return;
        } else
        {
            System.arraycopy(abyte0, i, _outputBuffer, _outputTail, j);
            _outputTail = j + _outputTail;
            return;
        }
    }

    private final void _writeBytesLong(byte abyte0[], int i, int j)
        throws IOException
    {
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        do
        {
            int k = Math.min(j, _outputEnd - _outputTail);
            System.arraycopy(abyte0, i, _outputBuffer, _outputTail, k);
            _outputTail = k + _outputTail;
            j -= k;
            if (j == 0)
            {
                return;
            }
            i += k;
            _flushBuffer();
        } while (true);
    }

    private final void _writeFieldName(String s)
        throws IOException, JsonGenerationException
    {
        int i = s.length();
        if (i == 0)
        {
            _writeByte((byte)32);
        } else
        {
            if (_seenNameCount >= 0)
            {
                int j1 = _findSeenName(s);
                if (j1 >= 0)
                {
                    _writeSharedNameReference(j1);
                    return;
                }
            }
            if (i > 56)
            {
                _writeNonShortFieldName(s, i);
                return;
            }
            if (196 + _outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            s.getChars(0, i, _charBuffer, 0);
            int j = _outputTail;
            _outputTail = 1 + _outputTail;
            int k = _shortUTF8Encode(_charBuffer, 0, i);
            byte byte0;
            if (k == i)
            {
                if (k <= 64)
                {
                    byte0 = (byte)(k + 127);
                } else
                {
                    byte0 = 52;
                    byte abyte1[] = _outputBuffer;
                    int i1 = _outputTail;
                    _outputTail = i1 + 1;
                    abyte1[i1] = -4;
                }
            } else
            if (k <= 56)
            {
                byte0 = (byte)(k + 190);
            } else
            {
                byte0 = 52;
                byte abyte0[] = _outputBuffer;
                int l = _outputTail;
                _outputTail = l + 1;
                abyte0[l] = -4;
            }
            _outputBuffer[j] = byte0;
            if (_seenNameCount >= 0)
            {
                _addSeenName(s);
                return;
            }
        }
    }

    private final void _writeNonSharedString(String s, int i)
        throws IOException, JsonGenerationException
    {
        if (i > _charBufferLength)
        {
            _writeByte((byte)-28);
            _slowUTF8Encode(s);
            _writeByte((byte)-4);
            return;
        }
        s.getChars(0, i, _charBuffer, 0);
        int j = 2 + (i + (i + i));
        if (j > _outputBuffer.length)
        {
            _writeByte((byte)-28);
            _mediumUTF8Encode(_charBuffer, 0, i);
            _writeByte((byte)-4);
            return;
        }
        if (j + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        int k = _outputTail;
        _writeByte((byte)-32);
        if (_shortUTF8Encode(_charBuffer, 0, i) > i)
        {
            _outputBuffer[k] = -28;
        }
        byte abyte0[] = _outputBuffer;
        int l = _outputTail;
        _outputTail = l + 1;
        abyte0[l] = -4;
    }

    private final void _writeNonShortFieldName(String s, int i)
        throws IOException, JsonGenerationException
    {
        _writeByte((byte)52);
        byte abyte0[];
        int k;
        if (i > _charBufferLength)
        {
            _slowUTF8Encode(s);
        } else
        {
            s.getChars(0, i, _charBuffer, 0);
            int j = i + (i + i);
            if (j <= _outputBuffer.length)
            {
                if (j + _outputTail >= _outputEnd)
                {
                    _flushBuffer();
                }
                _shortUTF8Encode(_charBuffer, 0, i);
            } else
            {
                _mediumUTF8Encode(_charBuffer, 0, i);
            }
        }
        if (_seenNameCount >= 0)
        {
            _addSeenName(s);
        }
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        abyte0 = _outputBuffer;
        k = _outputTail;
        _outputTail = k + 1;
        abyte0[k] = -4;
    }

    private void _writePositiveVInt(int i)
        throws IOException
    {
        _ensureRoomForOutput(5);
        byte byte0 = (byte)(128 + (i & 0x3f));
        int j = i >> 6;
        if (j <= 127)
        {
            if (j > 0)
            {
                byte abyte13[] = _outputBuffer;
                int j4 = _outputTail;
                _outputTail = j4 + 1;
                abyte13[j4] = (byte)j;
            }
            byte abyte12[] = _outputBuffer;
            int i4 = _outputTail;
            _outputTail = i4 + 1;
            abyte12[i4] = byte0;
            return;
        }
        byte byte1 = (byte)(j & 0x7f);
        int k = j >> 7;
        if (k <= 127)
        {
            byte abyte9[] = _outputBuffer;
            int j3 = _outputTail;
            _outputTail = j3 + 1;
            abyte9[j3] = (byte)k;
            byte abyte10[] = _outputBuffer;
            int k3 = _outputTail;
            _outputTail = k3 + 1;
            abyte10[k3] = byte1;
            byte abyte11[] = _outputBuffer;
            int l3 = _outputTail;
            _outputTail = l3 + 1;
            abyte11[l3] = byte0;
            return;
        }
        byte byte2 = (byte)(k & 0x7f);
        int l = k >> 7;
        if (l <= 127)
        {
            byte abyte5[] = _outputBuffer;
            int j2 = _outputTail;
            _outputTail = j2 + 1;
            abyte5[j2] = (byte)l;
            byte abyte6[] = _outputBuffer;
            int k2 = _outputTail;
            _outputTail = k2 + 1;
            abyte6[k2] = byte2;
            byte abyte7[] = _outputBuffer;
            int l2 = _outputTail;
            _outputTail = l2 + 1;
            abyte7[l2] = byte1;
            byte abyte8[] = _outputBuffer;
            int i3 = _outputTail;
            _outputTail = i3 + 1;
            abyte8[i3] = byte0;
            return;
        } else
        {
            byte byte3 = (byte)(l & 0x7f);
            byte abyte0[] = _outputBuffer;
            int i1 = _outputTail;
            _outputTail = i1 + 1;
            abyte0[i1] = (byte)(l >> 7);
            byte abyte1[] = _outputBuffer;
            int j1 = _outputTail;
            _outputTail = j1 + 1;
            abyte1[j1] = byte3;
            byte abyte2[] = _outputBuffer;
            int k1 = _outputTail;
            _outputTail = k1 + 1;
            abyte2[k1] = byte2;
            byte abyte3[] = _outputBuffer;
            int l1 = _outputTail;
            _outputTail = l1 + 1;
            abyte3[l1] = byte1;
            byte abyte4[] = _outputBuffer;
            int i2 = _outputTail;
            _outputTail = i2 + 1;
            abyte4[i2] = byte0;
            return;
        }
    }

    private final void _writeSharedNameReference(int i)
        throws IOException, JsonGenerationException
    {
        if (i >= _seenNameCount)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Internal error: trying to write shared name with index ").append(i).append("; but have only seen ").append(_seenNameCount).append(" so far!").toString());
        }
        if (i < 64)
        {
            _writeByte((byte)(i + 64));
            return;
        } else
        {
            _writeBytes((byte)(48 + (i >> 8)), (byte)i);
            return;
        }
    }

    private final void _writeSharedStringValueReference(int i)
        throws IOException, JsonGenerationException
    {
        if (i >= _seenStringValueCount)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Internal error: trying to write shared String value with index ").append(i).append("; but have only seen ").append(_seenStringValueCount).append(" so far!").toString());
        }
        if (i < 31)
        {
            _writeByte((byte)(i + 1));
            return;
        } else
        {
            _writeBytes((byte)(236 + (i >> 8)), (byte)i);
            return;
        }
    }

    private void _writeSignedVInt(int i)
        throws IOException
    {
        _writePositiveVInt(SmileUtil.zigzagEncode(i));
    }

    protected final void _flushBuffer()
        throws IOException
    {
        if (_outputTail > 0)
        {
            _bytesWritten = _bytesWritten + _outputTail;
            _out.write(_outputBuffer, 0, _outputTail);
            _outputTail = 0;
        }
    }

    protected UnsupportedOperationException _notSupported()
    {
        return new UnsupportedOperationException();
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
        SharedStringNode asharedstringnode[] = _seenNames;
        if (asharedstringnode != null && asharedstringnode.length == 64)
        {
            _seenNames = null;
            _smileBufferRecycler.releaseSeenNamesBuffer(asharedstringnode);
        }
        SharedStringNode asharedstringnode1[] = _seenStringValues;
        if (asharedstringnode1 != null && asharedstringnode1.length == 64)
        {
            _seenStringValues = null;
            _smileBufferRecycler.releaseSeenStringValuesBuffer(asharedstringnode1);
        }
    }

    protected final void _verifyValueWrite(String s)
        throws IOException, JsonGenerationException
    {
        if (_writeContext.writeValue() == 5)
        {
            _reportError((new StringBuilder()).append("Can not ").append(s).append(", expecting field name").toString());
        }
    }

    protected void _write7BitBinaryWithLength(byte abyte0[], int i, int j)
        throws IOException
    {
        _writePositiveVInt(j);
        int k;
        int j13;
        for (k = i; j >= 7; k = j13)
        {
            if (8 + _outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            int k7 = k + 1;
            byte byte1 = abyte0[k];
            byte abyte13[] = _outputBuffer;
            int l7 = _outputTail;
            _outputTail = l7 + 1;
            abyte13[l7] = (byte)(0x7f & byte1 >> 1);
            int i8 = byte1 << 8;
            int j8 = k7 + 1;
            int k8 = i8 | 0xff & abyte0[k7];
            byte abyte14[] = _outputBuffer;
            int l8 = _outputTail;
            _outputTail = l8 + 1;
            abyte14[l8] = (byte)(0x7f & k8 >> 2);
            int i9 = k8 << 8;
            int j9 = j8 + 1;
            int k9 = i9 | 0xff & abyte0[j8];
            byte abyte15[] = _outputBuffer;
            int l9 = _outputTail;
            _outputTail = l9 + 1;
            abyte15[l9] = (byte)(0x7f & k9 >> 3);
            int i10 = k9 << 8;
            int j10 = j9 + 1;
            int k10 = i10 | 0xff & abyte0[j9];
            byte abyte16[] = _outputBuffer;
            int l10 = _outputTail;
            _outputTail = l10 + 1;
            abyte16[l10] = (byte)(0x7f & k10 >> 4);
            int i11 = k10 << 8;
            int j11 = j10 + 1;
            int k11 = i11 | 0xff & abyte0[j10];
            byte abyte17[] = _outputBuffer;
            int l11 = _outputTail;
            _outputTail = l11 + 1;
            abyte17[l11] = (byte)(0x7f & k11 >> 5);
            int i12 = k11 << 8;
            int j12 = j11 + 1;
            int k12 = i12 | 0xff & abyte0[j11];
            byte abyte18[] = _outputBuffer;
            int l12 = _outputTail;
            _outputTail = l12 + 1;
            abyte18[l12] = (byte)(0x7f & k12 >> 6);
            int i13 = k12 << 8;
            j13 = j12 + 1;
            int k13 = i13 | 0xff & abyte0[j12];
            byte abyte19[] = _outputBuffer;
            int l13 = _outputTail;
            _outputTail = l13 + 1;
            abyte19[l13] = (byte)(0x7f & k13 >> 7);
            byte abyte20[] = _outputBuffer;
            int i14 = _outputTail;
            _outputTail = i14 + 1;
            abyte20[i14] = (byte)(k13 & 0x7f);
            j -= 7;
        }

        if (j > 0)
        {
            if (7 + _outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            int l = k + 1;
            byte byte0 = abyte0[k];
            byte abyte1[] = _outputBuffer;
            int i1 = _outputTail;
            _outputTail = i1 + 1;
            abyte1[i1] = (byte)(0x7f & byte0 >> 1);
            if (j > 1)
            {
                int k1 = (byte0 & 1) << 8;
                int l1 = l + 1;
                int i2 = k1 | 0xff & abyte0[l];
                byte abyte3[] = _outputBuffer;
                int j2 = _outputTail;
                _outputTail = j2 + 1;
                abyte3[j2] = (byte)(0x7f & i2 >> 2);
                if (j > 2)
                {
                    int l2 = (i2 & 3) << 8;
                    int i3 = l1 + 1;
                    int j3 = l2 | 0xff & abyte0[l1];
                    byte abyte5[] = _outputBuffer;
                    int k3 = _outputTail;
                    _outputTail = k3 + 1;
                    abyte5[k3] = (byte)(0x7f & j3 >> 3);
                    if (j > 3)
                    {
                        int i4 = (j3 & 7) << 8;
                        int j4 = i3 + 1;
                        int k4 = i4 | 0xff & abyte0[i3];
                        byte abyte7[] = _outputBuffer;
                        int l4 = _outputTail;
                        _outputTail = l4 + 1;
                        abyte7[l4] = (byte)(0x7f & k4 >> 4);
                        if (j > 4)
                        {
                            int j5 = (k4 & 0xf) << 8;
                            int k5 = j4 + 1;
                            int l5 = j5 | 0xff & abyte0[j4];
                            byte abyte9[] = _outputBuffer;
                            int i6 = _outputTail;
                            _outputTail = i6 + 1;
                            abyte9[i6] = (byte)(0x7f & l5 >> 5);
                            if (j > 5)
                            {
                                int k6 = (l5 & 0x1f) << 8;
                                int _tmp = k5 + 1;
                                int l6 = k6 | 0xff & abyte0[k5];
                                byte abyte11[] = _outputBuffer;
                                int i7 = _outputTail;
                                _outputTail = i7 + 1;
                                abyte11[i7] = (byte)(0x7f & l6 >> 6);
                                byte abyte12[] = _outputBuffer;
                                int j7 = _outputTail;
                                _outputTail = j7 + 1;
                                abyte12[j7] = (byte)(l6 & 0x3f);
                                return;
                            } else
                            {
                                byte abyte10[] = _outputBuffer;
                                int j6 = _outputTail;
                                _outputTail = j6 + 1;
                                abyte10[j6] = (byte)(l5 & 0x1f);
                                return;
                            }
                        } else
                        {
                            byte abyte8[] = _outputBuffer;
                            int i5 = _outputTail;
                            _outputTail = i5 + 1;
                            abyte8[i5] = (byte)(k4 & 0xf);
                            return;
                        }
                    } else
                    {
                        byte abyte6[] = _outputBuffer;
                        int l3 = _outputTail;
                        _outputTail = l3 + 1;
                        abyte6[l3] = (byte)(j3 & 7);
                        return;
                    }
                } else
                {
                    byte abyte4[] = _outputBuffer;
                    int k2 = _outputTail;
                    _outputTail = k2 + 1;
                    abyte4[k2] = (byte)(i2 & 3);
                    return;
                }
            } else
            {
                byte abyte2[] = _outputBuffer;
                int j1 = _outputTail;
                _outputTail = j1 + 1;
                abyte2[j1] = (byte)(byte0 & 1);
                return;
            }
        } else
        {
            int _tmp1 = k;
            return;
        }
    }

    protected final void _writeFieldName(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        int i = serializablestring.charLength();
        if (i != 0) goto _L2; else goto _L1
_L1:
        _writeByte((byte)32);
_L4:
        return;
_L2:
        byte abyte0[];
        int j;
        abyte0 = serializablestring.asUnquotedUTF8();
        j = abyte0.length;
        if (j != i)
        {
            _writeFieldNameUnicode(serializablestring, abyte0);
            return;
        }
        if (_seenNameCount >= 0)
        {
            int j1 = _findSeenName(serializablestring.getValue());
            if (j1 >= 0)
            {
                _writeSharedNameReference(j1);
                return;
            }
        }
        if (j > 64)
        {
            break; /* Loop/switch isn't completed */
        }
        if (j + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte3[] = _outputBuffer;
        int i1 = _outputTail;
        _outputTail = i1 + 1;
        abyte3[i1] = (byte)(j + 127);
        System.arraycopy(abyte0, 0, _outputBuffer, _outputTail, j);
        _outputTail = j + _outputTail;
        if (_seenNameCount >= 0)
        {
            _addSeenName(serializablestring.getValue());
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte1[] = _outputBuffer;
        int k = _outputTail;
        _outputTail = k + 1;
        abyte1[k] = 52;
        byte abyte2[];
        int l;
        if (1 + (j + _outputTail) < _outputEnd)
        {
            System.arraycopy(abyte0, 0, _outputBuffer, _outputTail, j);
            _outputTail = j + _outputTail;
        } else
        {
            _flushBuffer();
            if (j < 770)
            {
                System.arraycopy(abyte0, 0, _outputBuffer, _outputTail, j);
                _outputTail = j + _outputTail;
            } else
            {
                if (_outputTail > 0)
                {
                    _flushBuffer();
                }
                _out.write(abyte0, 0, j);
            }
        }
        abyte2 = _outputBuffer;
        l = _outputTail;
        _outputTail = l + 1;
        abyte2[l] = -4;
        if (_seenNameCount >= 0)
        {
            _addSeenName(serializablestring.getValue());
            return;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    protected final void _writeFieldNameUnicode(SerializableString serializablestring, byte abyte0[])
        throws IOException, JsonGenerationException
    {
        if (_seenNameCount < 0) goto _L2; else goto _L1
_L1:
        int i1 = _findSeenName(serializablestring.getValue());
        if (i1 < 0) goto _L2; else goto _L3
_L3:
        _writeSharedNameReference(i1);
_L5:
        return;
_L2:
        int i;
        i = abyte0.length;
        if (i > 56)
        {
            break; /* Loop/switch isn't completed */
        }
        if (i + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte3[] = _outputBuffer;
        int l = _outputTail;
        _outputTail = l + 1;
        abyte3[l] = (byte)(i + 190);
        System.arraycopy(abyte0, 0, _outputBuffer, _outputTail, i);
        _outputTail = i + _outputTail;
        if (_seenNameCount >= 0)
        {
            _addSeenName(serializablestring.getValue());
            return;
        }
        if (true) goto _L5; else goto _L4
_L4:
        if (_outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        byte abyte1[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte1[j] = 52;
        byte abyte2[];
        int k;
        if (1 + (i + _outputTail) < _outputEnd)
        {
            System.arraycopy(abyte0, 0, _outputBuffer, _outputTail, i);
            _outputTail = i + _outputTail;
        } else
        {
            _flushBuffer();
            if (i < 770)
            {
                System.arraycopy(abyte0, 0, _outputBuffer, _outputTail, i);
                _outputTail = i + _outputTail;
            } else
            {
                if (_outputTail > 0)
                {
                    _flushBuffer();
                }
                _out.write(abyte0, 0, i);
            }
        }
        abyte2 = _outputBuffer;
        k = _outputTail;
        _outputTail = k + 1;
        abyte2[k] = -4;
        if (_seenNameCount >= 0)
        {
            _addSeenName(serializablestring.getValue());
            return;
        }
        if (true) goto _L5; else goto _L6
_L6:
    }

    public void close()
        throws IOException
    {
        boolean flag = _closed;
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
        if (!flag && isEnabled(Feature.WRITE_END_MARKER))
        {
            _writeByte((byte)-1);
        }
        _flushBuffer();
        if (_ioContext.isResourceManaged() || isEnabled(org.codehaus.jackson.JsonGenerator.Feature.AUTO_CLOSE_TARGET))
        {
            _out.close();
        } else
        {
            _out.flush();
        }
        _releaseBuffers();
    }

    public SmileGenerator configure(Feature feature, boolean flag)
    {
        if (flag)
        {
            enable(feature);
            return this;
        } else
        {
            disable(feature);
            return this;
        }
    }

    public SmileGenerator disable(Feature feature)
    {
        _smileFeatures = _smileFeatures & (-1 ^ feature.getMask());
        return this;
    }

    public SmileGenerator enable(Feature feature)
    {
        _smileFeatures = _smileFeatures | feature.getMask();
        return this;
    }

    public final void flush()
        throws IOException
    {
        _flushBuffer();
        if (isEnabled(org.codehaus.jackson.JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM))
        {
            _out.flush();
        }
    }

    public Object getOutputTarget()
    {
        return _out;
    }

    public final boolean isEnabled(Feature feature)
    {
        return (_smileFeatures & feature.getMask()) != 0;
    }

    protected long outputOffset()
    {
        return (long)(_bytesWritten + _outputTail);
    }

    public JsonGenerator setPrettyPrinter(PrettyPrinter prettyprinter)
    {
        return this;
    }

    public JsonGenerator useDefaultPrettyPrinter()
    {
        return this;
    }

    public void writeBinary(Base64Variant base64variant, byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        if (abyte0 == null)
        {
            writeNull();
            return;
        }
        _verifyValueWrite("write Binary value");
        if (isEnabled(Feature.ENCODE_BINARY_AS_7BIT))
        {
            _writeByte((byte)-24);
            _write7BitBinaryWithLength(abyte0, i, j);
            return;
        } else
        {
            _writeByte((byte)-3);
            _writePositiveVInt(j);
            _writeBytes(abyte0, i, j);
            return;
        }
    }

    public void writeBoolean(boolean flag)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write boolean value");
        if (flag)
        {
            _writeByte((byte)35);
            return;
        } else
        {
            _writeByte((byte)34);
            return;
        }
    }

    public void writeBytes(byte abyte0[], int i, int j)
        throws IOException
    {
        _writeBytes(abyte0, i, j);
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
            _writeByte((byte)-7);
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
        } else
        {
            _writeByte((byte)-5);
            return;
        }
    }

    public final void writeFieldName(String s)
        throws IOException, JsonGenerationException
    {
        if (_writeContext.writeFieldName(s) == 4)
        {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(s);
    }

    public final void writeFieldName(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        if (_writeContext.writeFieldName(serializablestring.getValue()) == 4)
        {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(serializablestring);
    }

    public final void writeFieldName(SerializedString serializedstring)
        throws IOException, JsonGenerationException
    {
        if (_writeContext.writeFieldName(serializedstring.getValue()) == 4)
        {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(serializedstring);
    }

    public void writeHeader()
        throws IOException
    {
        int i = _smileFeatures & Feature.CHECK_SHARED_NAMES.getMask();
        int j = 0;
        if (i != 0)
        {
            j = false | true;
        }
        if ((_smileFeatures & Feature.CHECK_SHARED_STRING_VALUES.getMask()) != 0)
        {
            j |= 2;
        }
        if ((_smileFeatures & Feature.ENCODE_BINARY_AS_7BIT.getMask()) == 0)
        {
            j |= 4;
        }
        _writeBytes((byte)58, (byte)41, (byte)10, (byte)j);
    }

    public void writeNull()
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write null value");
        _writeByte((byte)33);
    }

    public void writeNumber(double d)
        throws IOException, JsonGenerationException
    {
        _ensureRoomForOutput(11);
        _verifyValueWrite("write number");
        long l = Double.doubleToRawLongBits(d);
        byte abyte0[] = _outputBuffer;
        int i = _outputTail;
        _outputTail = i + 1;
        abyte0[i] = 41;
        int j = (int)(l >>> 35);
        _outputBuffer[4 + _outputTail] = (byte)(j & 0x7f);
        int k = j >> 7;
        _outputBuffer[3 + _outputTail] = (byte)(k & 0x7f);
        int i1 = k >> 7;
        _outputBuffer[2 + _outputTail] = (byte)(i1 & 0x7f);
        int j1 = i1 >> 7;
        _outputBuffer[1 + _outputTail] = (byte)(j1 & 0x7f);
        int k1 = j1 >> 7;
        _outputBuffer[_outputTail] = (byte)k1;
        _outputTail = 5 + _outputTail;
        int l1 = (int)(l >> 28);
        byte abyte1[] = _outputBuffer;
        int i2 = _outputTail;
        _outputTail = i2 + 1;
        abyte1[i2] = (byte)(l1 & 0x7f);
        int j2 = (int)l;
        _outputBuffer[3 + _outputTail] = (byte)(j2 & 0x7f);
        int k2 = j2 >> 7;
        _outputBuffer[2 + _outputTail] = (byte)(k2 & 0x7f);
        int l2 = k2 >> 7;
        _outputBuffer[1 + _outputTail] = (byte)(l2 & 0x7f);
        int i3 = l2 >> 7;
        _outputBuffer[_outputTail] = (byte)(i3 & 0x7f);
        _outputTail = 4 + _outputTail;
    }

    public void writeNumber(float f)
        throws IOException, JsonGenerationException
    {
        _ensureRoomForOutput(6);
        _verifyValueWrite("write number");
        int i = Float.floatToRawIntBits(f);
        byte abyte0[] = _outputBuffer;
        int j = _outputTail;
        _outputTail = j + 1;
        abyte0[j] = 40;
        _outputBuffer[4 + _outputTail] = (byte)(i & 0x7f);
        int k = i >> 7;
        _outputBuffer[3 + _outputTail] = (byte)(k & 0x7f);
        int l = k >> 7;
        _outputBuffer[2 + _outputTail] = (byte)(l & 0x7f);
        int i1 = l >> 7;
        _outputBuffer[1 + _outputTail] = (byte)(i1 & 0x7f);
        int j1 = i1 >> 7;
        _outputBuffer[_outputTail] = (byte)(j1 & 0x7f);
        _outputTail = 5 + _outputTail;
    }

    public void writeNumber(int i)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write number");
        int j = SmileUtil.zigzagEncode(i);
        if (j <= 63 && j >= 0)
        {
            if (j <= 31)
            {
                _writeByte((byte)(j + 192));
                return;
            } else
            {
                _writeBytes((byte)36, (byte)(j + 128));
                return;
            }
        }
        byte byte0 = (byte)(128 + (j & 0x3f));
        int k = j >>> 6;
        if (k <= 127)
        {
            _writeBytes((byte)36, (byte)k, byte0);
            return;
        }
        byte byte1 = (byte)(k & 0x7f);
        int l = k >> 7;
        if (l <= 127)
        {
            _writeBytes((byte)36, (byte)l, byte1, byte0);
            return;
        }
        byte byte2 = (byte)(l & 0x7f);
        int i1 = l >> 7;
        if (i1 <= 127)
        {
            _writeBytes((byte)36, (byte)i1, byte2, byte1, byte0);
            return;
        } else
        {
            byte byte3 = (byte)(i1 & 0x7f);
            _writeBytes((byte)36, (byte)(i1 >> 7), byte3, byte2, byte1, byte0);
            return;
        }
    }

    public void writeNumber(long l)
        throws IOException, JsonGenerationException
    {
        if (l <= 0x7fffffffL && l >= 0xffffffff80000000L)
        {
            writeNumber((int)l);
            return;
        }
        _verifyValueWrite("write number");
        long l1 = SmileUtil.zigzagEncode(l);
        int i = (int)l1;
        byte byte0 = (byte)(128 + (i & 0x3f));
        byte byte1 = (byte)(0x7f & i >> 6);
        byte byte2 = (byte)(0x7f & i >> 13);
        byte byte3 = (byte)(0x7f & i >> 20);
        long l2 = l1 >>> 27;
        byte byte4 = (byte)(0x7f & (int)l2);
        int j = (int)(l2 >> 7);
        if (j == 0)
        {
            _writeBytes((byte)37, byte4, byte3, byte2, byte1, byte0);
            return;
        }
        if (j <= 127)
        {
            _writeBytes((byte)37, (byte)j);
            _writeBytes(byte4, byte3, byte2, byte1, byte0);
            return;
        }
        byte byte5 = (byte)(j & 0x7f);
        int k = j >> 7;
        if (k <= 127)
        {
            _writeBytes((byte)37, (byte)k);
            _writeBytes(byte5, byte4, byte3, byte2, byte1, byte0);
            return;
        }
        byte byte6 = (byte)(k & 0x7f);
        int i1 = k >> 7;
        if (i1 <= 127)
        {
            _writeBytes((byte)37, (byte)i1, byte6);
            _writeBytes(byte5, byte4, byte3, byte2, byte1, byte0);
            return;
        }
        byte byte7 = (byte)(i1 & 0x7f);
        int j1 = i1 >> 7;
        if (j1 <= 127)
        {
            _writeBytes((byte)37, (byte)j1, byte7, byte6);
            _writeBytes(byte5, byte4, byte3, byte2, byte1, byte0);
            return;
        } else
        {
            byte byte8 = (byte)(j1 & 0x7f);
            _writeBytes((byte)37, (byte)(j1 >> 7), byte8, byte7, byte6);
            _writeBytes(byte5, byte4, byte3, byte2, byte1, byte0);
            return;
        }
    }

    public void writeNumber(String s)
        throws IOException, JsonGenerationException, UnsupportedOperationException
    {
        throw _notSupported();
    }

    public void writeNumber(BigDecimal bigdecimal)
        throws IOException, JsonGenerationException
    {
        if (bigdecimal == null)
        {
            writeNull();
            return;
        } else
        {
            _verifyValueWrite("write number");
            _writeByte((byte)42);
            _writeSignedVInt(bigdecimal.scale());
            byte abyte0[] = bigdecimal.unscaledValue().toByteArray();
            _write7BitBinaryWithLength(abyte0, 0, abyte0.length);
            return;
        }
    }

    public void writeNumber(BigInteger biginteger)
        throws IOException, JsonGenerationException
    {
        if (biginteger == null)
        {
            writeNull();
            return;
        } else
        {
            _verifyValueWrite("write number");
            _writeByte((byte)38);
            byte abyte0[] = biginteger.toByteArray();
            _write7BitBinaryWithLength(abyte0, 0, abyte0.length);
            return;
        }
    }

    public void writeRaw(byte byte0)
        throws IOException, JsonGenerationException
    {
        _writeByte((byte)-8);
    }

    public void writeRaw(char c)
        throws IOException, JsonGenerationException
    {
        throw _notSupported();
    }

    public void writeRaw(String s)
        throws IOException, JsonGenerationException
    {
        throw _notSupported();
    }

    public void writeRaw(String s, int i, int j)
        throws IOException, JsonGenerationException
    {
        throw _notSupported();
    }

    public void writeRaw(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        throw _notSupported();
    }

    public void writeRawUTF8String(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write String value");
        if (j == 0)
        {
            _writeByte((byte)32);
            return;
        }
        if (_seenStringValueCount >= 0)
        {
            throw new UnsupportedOperationException("Can not use direct UTF-8 write methods when 'Feature.CHECK_SHARED_STRING_VALUES' enabled");
        }
        if (j <= 65)
        {
            if (j + _outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            if (j == 1)
            {
                byte abyte4[] = _outputBuffer;
                int k1 = _outputTail;
                _outputTail = k1 + 1;
                abyte4[k1] = 64;
                byte abyte5[] = _outputBuffer;
                int l1 = _outputTail;
                _outputTail = l1 + 1;
                abyte5[l1] = abyte0[i];
                return;
            } else
            {
                byte abyte3[] = _outputBuffer;
                int j1 = _outputTail;
                _outputTail = j1 + 1;
                abyte3[j1] = (byte)(j + 126);
                System.arraycopy(abyte0, i, _outputBuffer, _outputTail, j);
                _outputTail = j + _outputTail;
                return;
            }
        }
        int k = 2 + (j + (j + j));
        if (k <= _outputBuffer.length)
        {
            if (k + _outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            byte abyte1[] = _outputBuffer;
            int l = _outputTail;
            _outputTail = l + 1;
            abyte1[l] = -28;
            System.arraycopy(abyte0, i, _outputBuffer, _outputTail, j);
            _outputTail = j + _outputTail;
            byte abyte2[] = _outputBuffer;
            int i1 = _outputTail;
            _outputTail = i1 + 1;
            abyte2[i1] = -4;
            return;
        } else
        {
            _writeByte((byte)-28);
            _writeBytes(abyte0, i, j);
            _writeByte((byte)-4);
            return;
        }
    }

    public void writeRawValue(String s)
        throws IOException, JsonGenerationException
    {
        throw _notSupported();
    }

    public void writeRawValue(String s, int i, int j)
        throws IOException, JsonGenerationException
    {
        throw _notSupported();
    }

    public void writeRawValue(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        throw _notSupported();
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
        } else
        {
            _writeByte((byte)-8);
            return;
        }
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
        } else
        {
            _writeByte((byte)-6);
            return;
        }
    }

    public void writeString(String s)
        throws IOException, JsonGenerationException
    {
        if (s == null)
        {
            writeNull();
            return;
        }
        _verifyValueWrite("write String value");
        int i = s.length();
        if (i == 0)
        {
            _writeByte((byte)32);
            return;
        }
        if (i > 65)
        {
            _writeNonSharedString(s, i);
            return;
        }
        if (_seenStringValueCount >= 0)
        {
            int i1 = _findSeenStringValue(s);
            if (i1 >= 0)
            {
                _writeSharedStringValueReference(i1);
                return;
            }
        }
        if (196 + _outputTail >= _outputEnd)
        {
            _flushBuffer();
        }
        s.getChars(0, i, _charBuffer, 0);
        int j = _outputTail;
        _outputTail = 1 + _outputTail;
        int k = _shortUTF8Encode(_charBuffer, 0, i);
        if (k <= 64)
        {
            if (_seenStringValueCount >= 0)
            {
                _addSeenStringValue(s);
            }
            if (k == i)
            {
                _outputBuffer[j] = (byte)(k + 63);
                return;
            } else
            {
                _outputBuffer[j] = (byte)(k + 126);
                return;
            }
        }
        byte abyte0[] = _outputBuffer;
        byte byte0;
        byte abyte1[];
        int l;
        if (k == i)
        {
            byte0 = -32;
        } else
        {
            byte0 = -28;
        }
        abyte0[j] = byte0;
        abyte1 = _outputBuffer;
        l = _outputTail;
        _outputTail = l + 1;
        abyte1[l] = -4;
    }

    public final void writeString(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        _verifyValueWrite("write String value");
        String s = serializablestring.getValue();
        int i = s.length();
        if (i == 0)
        {
            _writeByte((byte)32);
        } else
        {
            if (i <= 65 && _seenStringValueCount >= 0)
            {
                int i1 = _findSeenStringValue(s);
                if (i1 >= 0)
                {
                    _writeSharedStringValueReference(i1);
                    return;
                }
            }
            byte abyte0[] = serializablestring.asUnquotedUTF8();
            int j = abyte0.length;
            if (j <= 64)
            {
                if (1 + (j + _outputTail) >= _outputEnd)
                {
                    _flushBuffer();
                }
                int k;
                byte abyte1[];
                int l;
                if (j == i)
                {
                    k = j + 63;
                } else
                {
                    k = j + 126;
                }
                abyte1 = _outputBuffer;
                l = _outputTail;
                _outputTail = l + 1;
                abyte1[l] = (byte)k;
                System.arraycopy(abyte0, 0, _outputBuffer, _outputTail, j);
                _outputTail = j + _outputTail;
                if (_seenStringValueCount >= 0)
                {
                    _addSeenStringValue(serializablestring.getValue());
                    return;
                }
            } else
            {
                byte byte0;
                if (j == i)
                {
                    byte0 = -32;
                } else
                {
                    byte0 = -28;
                }
                _writeByte(byte0);
                _writeBytes(abyte0, 0, abyte0.length);
                _writeByte((byte)-4);
                return;
            }
        }
    }

    public void writeString(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        if (j <= 65 && _seenStringValueCount >= 0 && j > 0)
        {
            writeString(new String(ac, i, j));
            return;
        }
        _verifyValueWrite("write String value");
        if (j == 0)
        {
            _writeByte((byte)32);
            return;
        }
        if (j <= 64)
        {
            if (196 + _outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            int j1 = _outputTail;
            _outputTail = 1 + _outputTail;
            int k1 = _shortUTF8Encode(ac, i, i + j);
            byte byte0;
            if (k1 <= 64)
            {
                if (k1 == j)
                {
                    byte0 = (byte)(k1 + 63);
                } else
                {
                    byte0 = (byte)(k1 + 126);
                }
            } else
            {
                byte0 = -28;
                byte abyte1[] = _outputBuffer;
                int l1 = _outputTail;
                _outputTail = l1 + 1;
                abyte1[l1] = -4;
            }
            _outputBuffer[j1] = byte0;
            return;
        }
        int k = 2 + (j + (j + j));
        if (k <= _outputBuffer.length)
        {
            if (k + _outputTail >= _outputEnd)
            {
                _flushBuffer();
            }
            int l = _outputTail;
            _writeByte((byte)-28);
            if (_shortUTF8Encode(ac, i, i + j) == j)
            {
                _outputBuffer[l] = -32;
            }
            byte abyte0[] = _outputBuffer;
            int i1 = _outputTail;
            _outputTail = i1 + 1;
            abyte0[i1] = -4;
            return;
        } else
        {
            _writeByte((byte)-28);
            _mediumUTF8Encode(ac, i, i + j);
            _writeByte((byte)-4);
            return;
        }
    }

    public final void writeStringField(String s, String s1)
        throws IOException, JsonGenerationException
    {
        if (_writeContext.writeFieldName(s) == 4)
        {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(s);
        writeString(s1);
    }

    public final void writeUTF8String(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        writeRawUTF8String(abyte0, i, j);
    }

}
