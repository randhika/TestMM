// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.impl;

import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.format.InputAccessor;
import org.codehaus.jackson.format.MatchStrength;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.io.MergedStream;
import org.codehaus.jackson.io.UTF32Reader;
import org.codehaus.jackson.sym.BytesToNameCanonicalizer;
import org.codehaus.jackson.sym.CharsToNameCanonicalizer;

// Referenced classes of package org.codehaus.jackson.impl:
//            Utf8StreamParser, ReaderBasedParser

public final class ByteSourceBootstrapper
{

    static final byte UTF8_BOM_1 = -17;
    static final byte UTF8_BOM_2 = -69;
    static final byte UTF8_BOM_3 = -65;
    protected boolean _bigEndian;
    private final boolean _bufferRecyclable;
    protected int _bytesPerChar;
    final IOContext _context;
    final InputStream _in;
    final byte _inputBuffer[];
    private int _inputEnd;
    protected int _inputProcessed;
    private int _inputPtr;

    public ByteSourceBootstrapper(IOContext iocontext, InputStream inputstream)
    {
        _bigEndian = true;
        _bytesPerChar = 0;
        _context = iocontext;
        _in = inputstream;
        _inputBuffer = iocontext.allocReadIOBuffer();
        _inputPtr = 0;
        _inputEnd = 0;
        _inputProcessed = 0;
        _bufferRecyclable = true;
    }

    public ByteSourceBootstrapper(IOContext iocontext, byte abyte0[], int i, int j)
    {
        _bigEndian = true;
        _bytesPerChar = 0;
        _context = iocontext;
        _in = null;
        _inputBuffer = abyte0;
        _inputPtr = i;
        _inputEnd = i + j;
        _inputProcessed = -i;
        _bufferRecyclable = false;
    }

    private boolean checkUTF16(int i)
    {
        if ((0xff00 & i) != 0) goto _L2; else goto _L1
_L1:
        _bigEndian = true;
_L6:
        boolean flag;
        _bytesPerChar = 2;
        flag = true;
_L4:
        return flag;
_L2:
        int j;
        j = i & 0xff;
        flag = false;
        if (j != 0) goto _L4; else goto _L3
_L3:
        _bigEndian = false;
        if (true) goto _L6; else goto _L5
_L5:
    }

    private boolean checkUTF32(int i)
        throws IOException
    {
        if (i >> 8 != 0) goto _L2; else goto _L1
_L1:
        _bigEndian = true;
_L6:
        boolean flag;
        _bytesPerChar = 4;
        flag = true;
_L4:
        return flag;
_L2:
        int j;
        if ((0xffffff & i) == 0)
        {
            _bigEndian = false;
            continue; /* Loop/switch isn't completed */
        }
        if ((0xff00ffff & i) == 0)
        {
            reportWeirdUCS4("3412");
            continue; /* Loop/switch isn't completed */
        }
        j = 0xffff00ff & i;
        flag = false;
        if (j != 0) goto _L4; else goto _L3
_L3:
        reportWeirdUCS4("2143");
        if (true) goto _L6; else goto _L5
_L5:
    }

    private boolean handleBOM(int i)
        throws IOException
    {
        i;
        JVM INSTR lookupswitch 4: default 44
    //                   -16842752: 127
    //                   -131072: 99
    //                   65279: 77
    //                   65534: 121;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        int j;
        j = i >>> 16;
        if (j == 65279)
        {
            _inputPtr = 2 + _inputPtr;
            _bytesPerChar = 2;
            _bigEndian = true;
            return true;
        }
        break; /* Loop/switch isn't completed */
_L4:
        _bigEndian = true;
        _inputPtr = 4 + _inputPtr;
        _bytesPerChar = 4;
        return true;
_L3:
        _inputPtr = 4 + _inputPtr;
        _bytesPerChar = 4;
        _bigEndian = false;
        return true;
_L5:
        reportWeirdUCS4("2143");
_L2:
        reportWeirdUCS4("3412");
        if (true) goto _L1; else goto _L6
_L6:
        if (j == 65534)
        {
            _inputPtr = 2 + _inputPtr;
            _bytesPerChar = 2;
            _bigEndian = false;
            return true;
        }
        if (i >>> 8 == 0xefbbbf)
        {
            _inputPtr = 3 + _inputPtr;
            _bytesPerChar = 1;
            _bigEndian = true;
            return true;
        } else
        {
            return false;
        }
    }

    public static MatchStrength hasJSONFormat(InputAccessor inputaccessor)
        throws IOException
    {
        MatchStrength matchstrength;
        if (!inputaccessor.hasMoreBytes())
        {
            matchstrength = MatchStrength.INCONCLUSIVE;
        } else
        {
            byte byte0 = inputaccessor.nextByte();
            if (byte0 == -17)
            {
                if (!inputaccessor.hasMoreBytes())
                {
                    return MatchStrength.INCONCLUSIVE;
                }
                if (inputaccessor.nextByte() != -69)
                {
                    return MatchStrength.NO_MATCH;
                }
                if (!inputaccessor.hasMoreBytes())
                {
                    return MatchStrength.INCONCLUSIVE;
                }
                if (inputaccessor.nextByte() != -65)
                {
                    return MatchStrength.NO_MATCH;
                }
                if (!inputaccessor.hasMoreBytes())
                {
                    return MatchStrength.INCONCLUSIVE;
                }
                byte0 = inputaccessor.nextByte();
            }
            int i = skipSpace(inputaccessor, byte0);
            if (i < 0)
            {
                return MatchStrength.INCONCLUSIVE;
            }
            if (i == 123)
            {
                int l = skipSpace(inputaccessor);
                if (l < 0)
                {
                    return MatchStrength.INCONCLUSIVE;
                }
                if (l == 34 || l == 125)
                {
                    return MatchStrength.SOLID_MATCH;
                } else
                {
                    return MatchStrength.NO_MATCH;
                }
            }
            if (i == 91)
            {
                int k = skipSpace(inputaccessor);
                if (k < 0)
                {
                    return MatchStrength.INCONCLUSIVE;
                }
                if (k == 93 || k == 91)
                {
                    return MatchStrength.SOLID_MATCH;
                } else
                {
                    return MatchStrength.SOLID_MATCH;
                }
            }
            matchstrength = MatchStrength.WEAK_MATCH;
            if (i != 34 && (i > 57 || i < 48))
            {
                if (i == 45)
                {
                    int j = skipSpace(inputaccessor);
                    if (j < 0)
                    {
                        return MatchStrength.INCONCLUSIVE;
                    }
                    if (j > 57 || j < 48)
                    {
                        return MatchStrength.NO_MATCH;
                    }
                } else
                {
                    if (i == 110)
                    {
                        return tryMatch(inputaccessor, "ull", matchstrength);
                    }
                    if (i == 116)
                    {
                        return tryMatch(inputaccessor, "rue", matchstrength);
                    }
                    if (i == 102)
                    {
                        return tryMatch(inputaccessor, "alse", matchstrength);
                    } else
                    {
                        return MatchStrength.NO_MATCH;
                    }
                }
            }
        }
        return matchstrength;
    }

    private void reportWeirdUCS4(String s)
        throws IOException
    {
        throw new CharConversionException((new StringBuilder()).append("Unsupported UCS-4 endianness (").append(s).append(") detected").toString());
    }

    private static final int skipSpace(InputAccessor inputaccessor)
        throws IOException
    {
        if (!inputaccessor.hasMoreBytes())
        {
            return -1;
        } else
        {
            return skipSpace(inputaccessor, inputaccessor.nextByte());
        }
    }

    private static final int skipSpace(InputAccessor inputaccessor, byte byte0)
        throws IOException
    {
        do
        {
            int i = byte0 & 0xff;
            if (i != 32 && i != 13 && i != 10 && i != 9)
            {
                return i;
            }
            if (!inputaccessor.hasMoreBytes())
            {
                return -1;
            }
            byte0 = inputaccessor.nextByte();
            int _tmp = byte0 & 0xff;
        } while (true);
    }

    private static final MatchStrength tryMatch(InputAccessor inputaccessor, String s, MatchStrength matchstrength)
        throws IOException
    {
        int i = 0;
        int j = s.length();
        do
        {
label0:
            {
                if (i < j)
                {
                    if (inputaccessor.hasMoreBytes())
                    {
                        break label0;
                    }
                    matchstrength = MatchStrength.INCONCLUSIVE;
                }
                return matchstrength;
            }
            if (inputaccessor.nextByte() != s.charAt(i))
            {
                return MatchStrength.NO_MATCH;
            }
            i++;
        } while (true);
    }

    public JsonParser constructParser(int i, ObjectCodec objectcodec, BytesToNameCanonicalizer bytestonamecanonicalizer, CharsToNameCanonicalizer charstonamecanonicalizer)
        throws IOException, JsonParseException
    {
        JsonEncoding jsonencoding = detectEncoding();
        boolean flag = org.codehaus.jackson.JsonParser.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(i);
        boolean flag1 = org.codehaus.jackson.JsonParser.Feature.INTERN_FIELD_NAMES.enabledIn(i);
        if (jsonencoding == JsonEncoding.UTF8 && flag)
        {
            BytesToNameCanonicalizer bytestonamecanonicalizer1 = bytestonamecanonicalizer.makeChild(flag, flag1);
            return new Utf8StreamParser(_context, i, _in, objectcodec, bytestonamecanonicalizer1, _inputBuffer, _inputPtr, _inputEnd, _bufferRecyclable);
        } else
        {
            return new ReaderBasedParser(_context, i, constructReader(), objectcodec, charstonamecanonicalizer.makeChild(flag, flag1));
        }
    }

    public Reader constructReader()
        throws IOException
    {
        JsonEncoding jsonencoding = _context.getEncoding();
        static class _cls1
        {

            static final int $SwitchMap$org$codehaus$jackson$JsonEncoding[];

            static 
            {
                $SwitchMap$org$codehaus$jackson$JsonEncoding = new int[JsonEncoding.values().length];
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonEncoding[JsonEncoding.UTF32_BE.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonEncoding[JsonEncoding.UTF32_LE.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonEncoding[JsonEncoding.UTF16_BE.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonEncoding[JsonEncoding.UTF16_LE.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonEncoding[JsonEncoding.UTF8.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror4)
                {
                    return;
                }
            }
        }

        InputStream inputstream;
        switch (_cls1..SwitchMap.org.codehaus.jackson.JsonEncoding[jsonencoding.ordinal()])
        {
        default:
            throw new RuntimeException("Internal error");

        case 1: // '\001'
        case 2: // '\002'
            return new UTF32Reader(_context, _in, _inputBuffer, _inputPtr, _inputEnd, _context.getEncoding().isBigEndian());

        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
            inputstream = _in;
            break;
        }
        Object obj;
        if (inputstream == null)
        {
            obj = new ByteArrayInputStream(_inputBuffer, _inputPtr, _inputEnd);
        } else
        if (_inputPtr < _inputEnd)
        {
            obj = new MergedStream(_context, inputstream, _inputBuffer, _inputPtr, _inputEnd);
        } else
        {
            obj = inputstream;
        }
        return new InputStreamReader(((InputStream) (obj)), jsonencoding.getJavaName());
    }

    public JsonEncoding detectEncoding()
        throws IOException, JsonParseException
    {
        if (!ensureLoaded(4)) goto _L2; else goto _L1
_L1:
        int i = _inputBuffer[_inputPtr] << 24 | (0xff & _inputBuffer[1 + _inputPtr]) << 16 | (0xff & _inputBuffer[2 + _inputPtr]) << 8 | 0xff & _inputBuffer[3 + _inputPtr];
        if (!handleBOM(i)) goto _L4; else goto _L3
_L3:
        boolean flag1 = true;
_L10:
        if (flag1) goto _L6; else goto _L5
_L5:
        JsonEncoding jsonencoding = JsonEncoding.UTF8;
_L8:
        _context.setEncoding(jsonencoding);
        return jsonencoding;
_L4:
        if (checkUTF32(i))
        {
            flag1 = true;
        } else
        {
            boolean flag3 = checkUTF16(i >>> 16);
            flag1 = false;
            if (flag3)
            {
                flag1 = true;
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        boolean flag = ensureLoaded(2);
        flag1 = false;
        if (flag)
        {
            boolean flag2 = checkUTF16((0xff & _inputBuffer[_inputPtr]) << 8 | 0xff & _inputBuffer[1 + _inputPtr]);
            flag1 = false;
            if (flag2)
            {
                flag1 = true;
            }
        }
        continue; /* Loop/switch isn't completed */
_L6:
        switch (_bytesPerChar)
        {
        case 3: // '\003'
        default:
            throw new RuntimeException("Internal error");

        case 1: // '\001'
            jsonencoding = JsonEncoding.UTF8;
            break;

        case 2: // '\002'
            if (_bigEndian)
            {
                jsonencoding = JsonEncoding.UTF16_BE;
            } else
            {
                jsonencoding = JsonEncoding.UTF16_LE;
            }
            break;

        case 4: // '\004'
            if (_bigEndian)
            {
                jsonencoding = JsonEncoding.UTF32_BE;
            } else
            {
                jsonencoding = JsonEncoding.UTF32_LE;
            }
            break;
        }
        if (true) goto _L8; else goto _L7
_L7:
        if (true) goto _L10; else goto _L9
_L9:
    }

    protected boolean ensureLoaded(int i)
        throws IOException
    {
        boolean flag = true;
        int j = _inputEnd - _inputPtr;
        do
        {
            int k;
label0:
            {
                if (j < i)
                {
                    if (_in == null)
                    {
                        k = -1;
                    } else
                    {
                        k = _in.read(_inputBuffer, _inputEnd, _inputBuffer.length - _inputEnd);
                    }
                    if (k >= flag)
                    {
                        break label0;
                    }
                    flag = false;
                }
                return flag;
            }
            _inputEnd = k + _inputEnd;
            j += k;
        } while (true);
    }
}
