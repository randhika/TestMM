// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.io;

import java.lang.ref.SoftReference;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.CharTypes;
import org.codehaus.jackson.util.TextBuffer;

public final class JsonStringEncoder
{

    private static final byte HEX_BYTES[] = CharTypes.copyHexBytes();
    private static final char HEX_CHARS[] = CharTypes.copyHexChars();
    private static final int INT_0 = 48;
    private static final int INT_BACKSLASH = 92;
    private static final int INT_U = 117;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    protected static final ThreadLocal _threadEncoder = new ThreadLocal();
    protected ByteArrayBuilder _byteBuilder;
    protected final char _quoteBuffer[] = new char[6];
    protected TextBuffer _textBuffer;

    public JsonStringEncoder()
    {
        _quoteBuffer[0] = '\\';
        _quoteBuffer[2] = '0';
        _quoteBuffer[3] = '0';
    }

    private int _appendByteEscape(int i, int j, ByteArrayBuilder bytearraybuilder, int k)
    {
        bytearraybuilder.setCurrentSegmentLength(k);
        bytearraybuilder.append(92);
        if (j < 0)
        {
            bytearraybuilder.append(117);
            if (i > 255)
            {
                int l = i >> 8;
                bytearraybuilder.append(HEX_BYTES[l >> 4]);
                bytearraybuilder.append(HEX_BYTES[l & 0xf]);
                i &= 0xff;
            } else
            {
                bytearraybuilder.append(48);
                bytearraybuilder.append(48);
            }
            bytearraybuilder.append(HEX_BYTES[i >> 4]);
            bytearraybuilder.append(HEX_BYTES[i & 0xf]);
        } else
        {
            bytearraybuilder.append((byte)j);
        }
        return bytearraybuilder.getCurrentSegmentLength();
    }

    private int _appendSingleEscape(int i, char ac[])
    {
        if (i < 0)
        {
            int j = -(i + 1);
            ac[1] = 'u';
            ac[4] = HEX_CHARS[j >> 4];
            ac[5] = HEX_CHARS[j & 0xf];
            return 6;
        } else
        {
            ac[1] = (char)i;
            return 2;
        }
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

    public static JsonStringEncoder getInstance()
    {
        SoftReference softreference = (SoftReference)_threadEncoder.get();
        JsonStringEncoder jsonstringencoder;
        if (softreference == null)
        {
            jsonstringencoder = null;
        } else
        {
            jsonstringencoder = (JsonStringEncoder)softreference.get();
        }
        if (jsonstringencoder == null)
        {
            jsonstringencoder = new JsonStringEncoder();
            _threadEncoder.set(new SoftReference(jsonstringencoder));
        }
        return jsonstringencoder;
    }

    public byte[] encodeAsUTF8(String s)
    {
        ByteArrayBuilder bytearraybuilder;
        int i;
        int j;
        byte abyte0[];
        int k;
        int l;
        bytearraybuilder = _byteBuilder;
        if (bytearraybuilder == null)
        {
            bytearraybuilder = new ByteArrayBuilder(null);
            _byteBuilder = bytearraybuilder;
        }
        i = s.length();
        j = 0;
        abyte0 = bytearraybuilder.resetAndGetFirstSegment();
        k = abyte0.length;
        l = 0;
_L6:
        int j1;
        int k1;
        if (l >= i)
        {
            break MISSING_BLOCK_LABEL_547;
        }
        int i1 = l + 1;
        j1 = s.charAt(l);
        k1 = i1;
_L5:
        if (j1 > '\177') goto _L2; else goto _L1
_L1:
        int j4;
        if (j >= k)
        {
            abyte0 = bytearraybuilder.finishCurrentSegment();
            k = abyte0.length;
            j = 0;
        }
        j4 = j + 1;
        abyte0[j] = (byte)j1;
        if (k1 < i) goto _L4; else goto _L3
_L3:
        j = j4;
        k1;
_L7:
        return _byteBuilder.completeAndCoalesce(j);
_L4:
        int k4 = k1 + 1;
        j1 = s.charAt(k1);
        j = j4;
        k1 = k4;
          goto _L5
_L2:
        int l1;
        int k2;
        int l2;
        int i3;
        if (j >= k)
        {
            abyte0 = bytearraybuilder.finishCurrentSegment();
            k = abyte0.length;
            l1 = 0;
        } else
        {
            l1 = j;
        }
        if (j1 < '\u0800')
        {
            k2 = l1 + 1;
            abyte0[l1] = (byte)(0xc0 | j1 >> 6);
            l2 = k1;
        } else
        if (j1 < '\uD800' || j1 > '\uDFFF')
        {
            int i2 = l1 + 1;
            abyte0[l1] = (byte)(0xe0 | j1 >> 12);
            if (i2 >= k)
            {
                abyte0 = bytearraybuilder.finishCurrentSegment();
                k = abyte0.length;
                i2 = 0;
            }
            int j2 = i2 + 1;
            abyte0[i2] = (byte)(0x80 | 0x3f & j1 >> 6);
            k2 = j2;
            l2 = k1;
        } else
        {
            if (j1 > '\uDBFF')
            {
                _throwIllegalSurrogate(j1);
            }
            if (k1 >= i)
            {
                _throwIllegalSurrogate(j1);
            }
            l2 = k1 + 1;
            j1 = _convertSurrogate(j1, s.charAt(k1));
            if (j1 > 0x10ffff)
            {
                _throwIllegalSurrogate(j1);
            }
            int j3 = l1 + 1;
            abyte0[l1] = (byte)(0xf0 | j1 >> 18);
            if (j3 >= k)
            {
                abyte0 = bytearraybuilder.finishCurrentSegment();
                k = abyte0.length;
                j3 = 0;
            }
            int k3 = j3 + 1;
            abyte0[j3] = (byte)(0x80 | 0x3f & j1 >> 12);
            int l3;
            int i4;
            if (k3 >= k)
            {
                abyte0 = bytearraybuilder.finishCurrentSegment();
                k = abyte0.length;
                l3 = 0;
            } else
            {
                l3 = k3;
            }
            i4 = l3 + 1;
            abyte0[l3] = (byte)(0x80 | 0x3f & j1 >> 6);
            k2 = i4;
        }
        if (k2 >= k)
        {
            abyte0 = bytearraybuilder.finishCurrentSegment();
            k = abyte0.length;
            k2 = 0;
        }
        i3 = k2 + 1;
        abyte0[k2] = (byte)(0x80 | j1 & 0x3f);
        j = i3;
        l = l2;
          goto _L6
        l;
          goto _L7
    }

    public char[] quoteAsString(String s)
    {
        TextBuffer textbuffer;
        char ac[];
        int ai[];
        int i;
        int j;
        int k;
        int l;
        textbuffer = _textBuffer;
        if (textbuffer == null)
        {
            textbuffer = new TextBuffer(null);
            _textBuffer = textbuffer;
        }
        ac = textbuffer.emptyAndGetCurrentSegment();
        ai = CharTypes.get7BitOutputEscapes();
        i = ai.length;
        j = 0;
        k = s.length();
        l = 0;
_L3:
        if (j >= k) goto _L2; else goto _L1
_L1:
label0:
        {
            char c = s.charAt(j);
            if (c >= i || ai[c] == 0)
            {
                break label0;
            }
            int j1 = j + 1;
            int k1 = _appendSingleEscape(ai[s.charAt(j)], _quoteBuffer);
            int i1;
            if (l + k1 > ac.length)
            {
                int l1 = ac.length - l;
                if (l1 > 0)
                {
                    System.arraycopy(_quoteBuffer, 0, ac, l, l1);
                }
                ac = textbuffer.finishCurrentSegment();
                int i2 = k1 - l1;
                System.arraycopy(_quoteBuffer, l1, ac, l, i2);
                l += i2;
            } else
            {
                System.arraycopy(_quoteBuffer, 0, ac, l, k1);
                l += k1;
            }
            j = j1;
        }
        if (true) goto _L3; else goto _L2
        if (l >= ac.length)
        {
            ac = textbuffer.finishCurrentSegment();
            l = 0;
        }
        i1 = l + 1;
        ac[l] = c;
        if (++j < k)
        {
            break MISSING_BLOCK_LABEL_232;
        }
        l = i1;
_L2:
        textbuffer.setCurrentLength(l);
        return textbuffer.contentsAsArray();
        l = i1;
          goto _L1
    }

    public byte[] quoteAsUTF8(String s)
    {
        ByteArrayBuilder bytearraybuilder;
        int i;
        int j;
        int k;
        byte abyte0[];
        bytearraybuilder = _byteBuilder;
        if (bytearraybuilder == null)
        {
            bytearraybuilder = new ByteArrayBuilder(null);
            _byteBuilder = bytearraybuilder;
        }
        i = 0;
        j = s.length();
        k = 0;
        abyte0 = bytearraybuilder.resetAndGetFirstSegment();
_L8:
        if (i >= j) goto _L2; else goto _L1
_L1:
        int ai[] = CharTypes.get7BitOutputEscapes();
_L6:
        int l;
        char c1;
        int j4;
        char c = s.charAt(i);
        if (c > '\177' || ai[c] != 0)
        {
            if (k >= abyte0.length)
            {
                abyte0 = bytearraybuilder.finishCurrentSegment();
                k = 0;
            }
            l = i + 1;
            c1 = s.charAt(i);
            if (c1 > '\177')
            {
                break; /* Loop/switch isn't completed */
            }
            k = _appendByteEscape(c1, ai[c1], bytearraybuilder, k);
            abyte0 = bytearraybuilder.getCurrentSegment();
            i = l;
            continue; /* Loop/switch isn't completed */
        }
        if (k >= abyte0.length)
        {
            abyte0 = bytearraybuilder.finishCurrentSegment();
            k = 0;
        }
        j4 = k + 1;
        abyte0[k] = (byte)c;
        if (++i < j) goto _L4; else goto _L3
_L3:
        k = j4;
_L2:
        return _byteBuilder.completeAndCoalesce(k);
_L4:
        k = j4;
        if (true) goto _L6; else goto _L5
_L5:
        int l1;
        int i2;
        if (c1 > '\u07FF')
        {
            break; /* Loop/switch isn't completed */
        }
        int i4 = k + 1;
        abyte0[k] = (byte)(0xc0 | c1 >> 6);
        l1 = 0x80 | c1 & 0x3f;
        i2 = i4;
        i = l;
_L9:
        if (i2 >= abyte0.length)
        {
            abyte0 = bytearraybuilder.finishCurrentSegment();
            i2 = 0;
        }
        int j2 = i2 + 1;
        abyte0[i2] = (byte)l1;
        k = j2;
        if (true) goto _L8; else goto _L7
_L7:
        if (c1 < '\uD800' || c1 > '\uDFFF')
        {
            int i1 = k + 1;
            abyte0[k] = (byte)(0xe0 | c1 >> 12);
            int j1;
            int k1;
            int k2;
            int l2;
            int i3;
            int j3;
            int k3;
            int l3;
            if (i1 >= abyte0.length)
            {
                abyte0 = bytearraybuilder.finishCurrentSegment();
                j1 = 0;
            } else
            {
                j1 = i1;
            }
            k1 = j1 + 1;
            abyte0[j1] = (byte)(0x80 | 0x3f & c1 >> 6);
            l1 = 0x80 | c1 & 0x3f;
            i2 = k1;
            i = l;
        } else
        {
            if (c1 > '\uDBFF')
            {
                _throwIllegalSurrogate(c1);
            }
            if (l >= j)
            {
                _throwIllegalSurrogate(c1);
            }
            i = l + 1;
            k2 = _convertSurrogate(c1, s.charAt(l));
            if (k2 > 0x10ffff)
            {
                _throwIllegalSurrogate(k2);
            }
            l2 = k + 1;
            abyte0[k] = (byte)(0xf0 | k2 >> 18);
            if (l2 >= abyte0.length)
            {
                abyte0 = bytearraybuilder.finishCurrentSegment();
                i3 = 0;
            } else
            {
                i3 = l2;
            }
            j3 = i3 + 1;
            abyte0[i3] = (byte)(0x80 | 0x3f & k2 >> 12);
            if (j3 >= abyte0.length)
            {
                abyte0 = bytearraybuilder.finishCurrentSegment();
                k3 = 0;
            } else
            {
                k3 = j3;
            }
            l3 = k3 + 1;
            abyte0[k3] = (byte)(0x80 | 0x3f & k2 >> 6);
            l1 = 0x80 | k2 & 0x3f;
            i2 = l3;
        }
          goto _L9
        if (true) goto _L8; else goto _L10
_L10:
    }

}
