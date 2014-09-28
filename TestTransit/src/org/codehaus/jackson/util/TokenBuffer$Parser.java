// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.impl.JsonParserMinimalBase;
import org.codehaus.jackson.impl.JsonReadContext;

// Referenced classes of package org.codehaus.jackson.util:
//            ByteArrayBuilder, TokenBuffer

protected static final class ateRootContext extends JsonParserMinimalBase
{

    protected transient ByteArrayBuilder _byteBuilder;
    protected boolean _closed;
    protected ObjectCodec _codec;
    protected JsonLocation _location;
    protected JsonReadContext _parsingContext;
    protected  _segment;
    protected int _segmentPtr;

    protected final void _checkIsNumber()
        throws JsonParseException
    {
        if (_currToken == null || !_currToken.isNumeric())
        {
            throw _constructError((new StringBuilder()).append("Current token (").append(_currToken).append(") not numeric, can not use numeric value accessors").toString());
        } else
        {
            return;
        }
    }

    protected final Object _currentObject()
    {
        return _segment.get(_segmentPtr);
    }

    protected void _decodeBase64(String s, ByteArrayBuilder bytearraybuilder, Base64Variant base64variant)
        throws IOException, JsonParseException
    {
        int i;
        int j;
        i = 0;
        j = s.length();
_L4:
        if (i >= j) goto _L2; else goto _L1
_L1:
        int k;
        char c;
        k = i + 1;
        c = s.charAt(i);
        if (k < j) goto _L3; else goto _L2
_L2:
        return;
_L3:
label0:
        {
            if (c <= ' ')
            {
                break label0;
            }
            int l = base64variant.decodeBase64Char(c);
            if (l < 0)
            {
                _reportInvalidBase64(base64variant, c, 0, null);
            }
            if (k >= j)
            {
                _reportBase64EOF();
            }
            int i1 = k + 1;
            char c1 = s.charAt(k);
            int j1 = base64variant.decodeBase64Char(c1);
            if (j1 < 0)
            {
                _reportInvalidBase64(base64variant, c1, 1, null);
            }
            int k1 = j1 | l << 6;
            if (i1 >= j)
            {
                if (!base64variant.usesPadding())
                {
                    bytearraybuilder.append(k1 >> 4);
                    return;
                }
                _reportBase64EOF();
            }
            int l1 = i1 + 1;
            char c2 = s.charAt(i1);
            int i2 = base64variant.decodeBase64Char(c2);
            if (i2 < 0)
            {
                if (i2 != -2)
                {
                    _reportInvalidBase64(base64variant, c2, 2, null);
                }
                if (l1 >= j)
                {
                    _reportBase64EOF();
                }
                i = l1 + 1;
                char c4 = s.charAt(l1);
                if (!base64variant.usesPaddingChar(c4))
                {
                    _reportInvalidBase64(base64variant, c4, 3, (new StringBuilder()).append("expected padding character '").append(base64variant.getPaddingChar()).append("'").toString());
                }
                bytearraybuilder.append(k1 >> 4);
            } else
            {
                int j2 = i2 | k1 << 6;
                if (l1 >= j)
                {
                    if (!base64variant.usesPadding())
                    {
                        bytearraybuilder.appendTwoBytes(j2 >> 2);
                        return;
                    }
                    _reportBase64EOF();
                }
                i = l1 + 1;
                char c3 = s.charAt(l1);
                int k2 = base64variant.decodeBase64Char(c3);
                if (k2 < 0)
                {
                    if (k2 != -2)
                    {
                        _reportInvalidBase64(base64variant, c3, 3, null);
                    }
                    bytearraybuilder.appendTwoBytes(j2 >> 2);
                } else
                {
                    bytearraybuilder.appendThreeBytes(k2 | j2 << 6);
                }
            }
        }
          goto _L4
        i = k;
          goto _L1
    }

    protected void _handleEOF()
        throws JsonParseException
    {
        _throwInternal();
    }

    protected void _reportBase64EOF()
        throws JsonParseException
    {
        throw _constructError("Unexpected end-of-String in base64 content");
    }

    protected void _reportInvalidBase64(Base64Variant base64variant, char c, int i, String s)
        throws JsonParseException
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
        throw _constructError(s1);
    }

    public void close()
        throws IOException
    {
        if (!_closed)
        {
            _closed = true;
        }
    }

    public BigInteger getBigIntegerValue()
        throws IOException, JsonParseException
    {
        Number number = getNumberValue();
        if (number instanceof BigInteger)
        {
            return (BigInteger)number;
        }
        switch (chMap.org.codehaus.jackson.JsonParser.NumberType[getNumberType().ordinal()])
        {
        default:
            return BigInteger.valueOf(number.longValue());

        case 3: // '\003'
            return ((BigDecimal)number).toBigInteger();
        }
    }

    public byte[] getBinaryValue(Base64Variant base64variant)
        throws IOException, JsonParseException
    {
        if (_currToken == JsonToken.VALUE_EMBEDDED_OBJECT)
        {
            Object obj = _currentObject();
            if (obj instanceof byte[])
            {
                return (byte[])(byte[])obj;
            }
        }
        if (_currToken != JsonToken.VALUE_STRING)
        {
            throw _constructError((new StringBuilder()).append("Current token (").append(_currToken).append(") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), can not access as binary").toString());
        }
        String s = getText();
        if (s == null)
        {
            return null;
        }
        ByteArrayBuilder bytearraybuilder = _byteBuilder;
        if (bytearraybuilder == null)
        {
            bytearraybuilder = new ByteArrayBuilder(100);
            _byteBuilder = bytearraybuilder;
        }
        _decodeBase64(s, bytearraybuilder, base64variant);
        return bytearraybuilder.toByteArray();
    }

    public ObjectCodec getCodec()
    {
        return _codec;
    }

    public JsonLocation getCurrentLocation()
    {
        if (_location == null)
        {
            return JsonLocation.NA;
        } else
        {
            return _location;
        }
    }

    public String getCurrentName()
    {
        return _parsingContext.getCurrentName();
    }

    public BigDecimal getDecimalValue()
        throws IOException, JsonParseException
    {
        Number number = getNumberValue();
        if (number instanceof BigDecimal)
        {
            return (BigDecimal)number;
        }
        switch (chMap.org.codehaus.jackson.JsonParser.NumberType[getNumberType().ordinal()])
        {
        case 3: // '\003'
        case 4: // '\004'
        default:
            return BigDecimal.valueOf(number.doubleValue());

        case 1: // '\001'
        case 5: // '\005'
            return BigDecimal.valueOf(number.longValue());

        case 2: // '\002'
            return new BigDecimal((BigInteger)number);
        }
    }

    public double getDoubleValue()
        throws IOException, JsonParseException
    {
        return getNumberValue().doubleValue();
    }

    public Object getEmbeddedObject()
    {
        if (_currToken == JsonToken.VALUE_EMBEDDED_OBJECT)
        {
            return _currentObject();
        } else
        {
            return null;
        }
    }

    public float getFloatValue()
        throws IOException, JsonParseException
    {
        return getNumberValue().floatValue();
    }

    public int getIntValue()
        throws IOException, JsonParseException
    {
        if (_currToken == JsonToken.VALUE_NUMBER_INT)
        {
            return ((Number)_currentObject()).intValue();
        } else
        {
            return getNumberValue().intValue();
        }
    }

    public long getLongValue()
        throws IOException, JsonParseException
    {
        return getNumberValue().longValue();
    }

    public org.codehaus.jackson.pe getNumberType()
        throws IOException, JsonParseException
    {
        Number number = getNumberValue();
        if (number instanceof Integer)
        {
            return org.codehaus.jackson.pe.INT;
        }
        if (number instanceof Long)
        {
            return org.codehaus.jackson.pe.LONG;
        }
        if (number instanceof Double)
        {
            return org.codehaus.jackson.pe.DOUBLE;
        }
        if (number instanceof BigDecimal)
        {
            return org.codehaus.jackson.pe.BIG_DECIMAL;
        }
        if (number instanceof Float)
        {
            return org.codehaus.jackson.pe.FLOAT;
        }
        if (number instanceof BigInteger)
        {
            return org.codehaus.jackson.pe.BIG_INTEGER;
        } else
        {
            return null;
        }
    }

    public final Number getNumberValue()
        throws IOException, JsonParseException
    {
        _checkIsNumber();
        return (Number)_currentObject();
    }

    public JsonStreamContext getParsingContext()
    {
        return _parsingContext;
    }

    public String getText()
    {
        if (_currToken != JsonToken.VALUE_STRING && _currToken != JsonToken.FIELD_NAME) goto _L2; else goto _L1
_L1:
        Object obj = _currentObject();
        if (!(obj instanceof String)) goto _L4; else goto _L3
_L3:
        String s = (String)obj;
_L6:
        return s;
_L4:
        s = null;
        if (obj != null)
        {
            return obj.toString();
        }
        continue; /* Loop/switch isn't completed */
_L2:
        JsonToken jsontoken = _currToken;
        s = null;
        if (jsontoken != null)
        {
            Object obj1;
            switch (chMap.org.codehaus.jackson.JsonToken[_currToken.ordinal()])
            {
            default:
                return _currToken.asString();

            case 7: // '\007'
            case 8: // '\b'
                obj1 = _currentObject();
                break;
            }
            s = null;
            if (obj1 != null)
            {
                return obj1.toString();
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public char[] getTextCharacters()
    {
        String s = getText();
        if (s == null)
        {
            return null;
        } else
        {
            return s.toCharArray();
        }
    }

    public int getTextLength()
    {
        String s = getText();
        if (s == null)
        {
            return 0;
        } else
        {
            return s.length();
        }
    }

    public int getTextOffset()
    {
        return 0;
    }

    public JsonLocation getTokenLocation()
    {
        return getCurrentLocation();
    }

    public boolean hasTextCharacters()
    {
        return false;
    }

    public boolean isClosed()
    {
        return _closed;
    }

    public JsonToken nextToken()
        throws IOException, JsonParseException
    {
        if (!_closed && _segment != null) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        int i = 1 + _segmentPtr;
        _segmentPtr = i;
        if (i < 16)
        {
            break; /* Loop/switch isn't completed */
        }
        _segmentPtr = 0;
        _segment = _segment.next();
        if (_segment == null) goto _L1; else goto _L3
_L3:
        _currToken = _segment.type(_segmentPtr);
        if (_currToken != JsonToken.FIELD_NAME) goto _L5; else goto _L4
_L4:
        Object obj = _currentObject();
        String s;
        if (obj instanceof String)
        {
            s = (String)obj;
        } else
        {
            s = obj.toString();
        }
        _parsingContext.setCurrentName(s);
_L7:
        return _currToken;
_L5:
        if (_currToken == JsonToken.START_OBJECT)
        {
            _parsingContext = _parsingContext.createChildObjectContext(-1, -1);
        } else
        if (_currToken == JsonToken.START_ARRAY)
        {
            _parsingContext = _parsingContext.createChildArrayContext(-1, -1);
        } else
        if (_currToken == JsonToken.END_OBJECT || _currToken == JsonToken.END_ARRAY)
        {
            _parsingContext = _parsingContext.getParent();
            if (_parsingContext == null)
            {
                _parsingContext = JsonReadContext.createRootContext(-1, -1);
            }
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    public JsonToken peekNextToken()
        throws IOException, JsonParseException
    {
        if (!_closed)
        {
              = _segment;
            int i = 1 + _segmentPtr;
            if (i >= 16)
            {
                i = 0;
                if ( == null)
                {
                     = null;
                } else
                {
                     = .next();
                    i = 0;
                }
            }
            if ( != null)
            {
                return .type(i);
            }
        }
        return null;
    }

    public void setCodec(ObjectCodec objectcodec)
    {
        _codec = objectcodec;
    }

    public void setLocation(JsonLocation jsonlocation)
    {
        _location = jsonlocation;
    }

    public ( , ObjectCodec objectcodec)
    {
        super(0);
        _location = null;
        _segment = ;
        _segmentPtr = -1;
        _codec = objectcodec;
        _parsingContext = JsonReadContext.createRootContext(-1, -1);
    }
}
