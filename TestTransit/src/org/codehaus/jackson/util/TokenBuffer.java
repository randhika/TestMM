// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.SerializableString;
import org.codehaus.jackson.impl.JsonParserMinimalBase;
import org.codehaus.jackson.impl.JsonReadContext;
import org.codehaus.jackson.impl.JsonWriteContext;
import org.codehaus.jackson.io.SerializedString;

// Referenced classes of package org.codehaus.jackson.util:
//            ByteArrayBuilder

public class TokenBuffer extends JsonGenerator
{
    protected static final class Parser extends JsonParserMinimalBase
    {

        protected transient ByteArrayBuilder _byteBuilder;
        protected boolean _closed;
        protected ObjectCodec _codec;
        protected JsonLocation _location;
        protected JsonReadContext _parsingContext;
        protected Segment _segment;
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
            static class _cls1
            {

                static final int $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[];
                static final int $SwitchMap$org$codehaus$jackson$JsonToken[];

                static 
                {
                    $SwitchMap$org$codehaus$jackson$JsonParser$NumberType = new int[org.codehaus.jackson.JsonParser.NumberType.values().length];
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[org.codehaus.jackson.JsonParser.NumberType.INT.ordinal()] = 1;
                    }
                    catch (NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[org.codehaus.jackson.JsonParser.NumberType.BIG_INTEGER.ordinal()] = 2;
                    }
                    catch (NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[org.codehaus.jackson.JsonParser.NumberType.BIG_DECIMAL.ordinal()] = 3;
                    }
                    catch (NoSuchFieldError nosuchfielderror2) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[org.codehaus.jackson.JsonParser.NumberType.FLOAT.ordinal()] = 4;
                    }
                    catch (NoSuchFieldError nosuchfielderror3) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[org.codehaus.jackson.JsonParser.NumberType.LONG.ordinal()] = 5;
                    }
                    catch (NoSuchFieldError nosuchfielderror4) { }
                    $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_OBJECT.ordinal()] = 1;
                    }
                    catch (NoSuchFieldError nosuchfielderror5) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_OBJECT.ordinal()] = 2;
                    }
                    catch (NoSuchFieldError nosuchfielderror6) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_ARRAY.ordinal()] = 3;
                    }
                    catch (NoSuchFieldError nosuchfielderror7) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_ARRAY.ordinal()] = 4;
                    }
                    catch (NoSuchFieldError nosuchfielderror8) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 5;
                    }
                    catch (NoSuchFieldError nosuchfielderror9) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 6;
                    }
                    catch (NoSuchFieldError nosuchfielderror10) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 7;
                    }
                    catch (NoSuchFieldError nosuchfielderror11) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 8;
                    }
                    catch (NoSuchFieldError nosuchfielderror12) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 9;
                    }
                    catch (NoSuchFieldError nosuchfielderror13) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 10;
                    }
                    catch (NoSuchFieldError nosuchfielderror14) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 11;
                    }
                    catch (NoSuchFieldError nosuchfielderror15) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 12;
                    }
                    catch (NoSuchFieldError nosuchfielderror16)
                    {
                        return;
                    }
                }
            }

            switch (_cls1..SwitchMap.org.codehaus.jackson.JsonParser.NumberType[getNumberType().ordinal()])
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
            switch (_cls1..SwitchMap.org.codehaus.jackson.JsonParser.NumberType[getNumberType().ordinal()])
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

        public org.codehaus.jackson.JsonParser.NumberType getNumberType()
            throws IOException, JsonParseException
        {
            Number number = getNumberValue();
            if (number instanceof Integer)
            {
                return org.codehaus.jackson.JsonParser.NumberType.INT;
            }
            if (number instanceof Long)
            {
                return org.codehaus.jackson.JsonParser.NumberType.LONG;
            }
            if (number instanceof Double)
            {
                return org.codehaus.jackson.JsonParser.NumberType.DOUBLE;
            }
            if (number instanceof BigDecimal)
            {
                return org.codehaus.jackson.JsonParser.NumberType.BIG_DECIMAL;
            }
            if (number instanceof Float)
            {
                return org.codehaus.jackson.JsonParser.NumberType.FLOAT;
            }
            if (number instanceof BigInteger)
            {
                return org.codehaus.jackson.JsonParser.NumberType.BIG_INTEGER;
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
                switch (_cls1..SwitchMap.org.codehaus.jackson.JsonToken[_currToken.ordinal()])
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
                Segment segment = _segment;
                int i = 1 + _segmentPtr;
                if (i >= 16)
                {
                    i = 0;
                    if (segment == null)
                    {
                        segment = null;
                    } else
                    {
                        segment = segment.next();
                        i = 0;
                    }
                }
                if (segment != null)
                {
                    return segment.type(i);
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

        public Parser(Segment segment, ObjectCodec objectcodec)
        {
            super(0);
            _location = null;
            _segment = segment;
            _segmentPtr = -1;
            _codec = objectcodec;
            _parsingContext = JsonReadContext.createRootContext(-1, -1);
        }
    }

    protected static final class Segment
    {

        public static final int TOKENS_PER_SEGMENT = 16;
        private static final JsonToken TOKEN_TYPES_BY_INDEX[];
        protected Segment _next;
        protected long _tokenTypes;
        protected final Object _tokens[] = new Object[16];

        public Segment append(int i, JsonToken jsontoken)
        {
            if (i < 16)
            {
                set(i, jsontoken);
                return null;
            } else
            {
                _next = new Segment();
                _next.set(0, jsontoken);
                return _next;
            }
        }

        public Segment append(int i, JsonToken jsontoken, Object obj)
        {
            if (i < 16)
            {
                set(i, jsontoken, obj);
                return null;
            } else
            {
                _next = new Segment();
                _next.set(0, jsontoken, obj);
                return _next;
            }
        }

        public Object get(int i)
        {
            return _tokens[i];
        }

        public Segment next()
        {
            return _next;
        }

        public void set(int i, JsonToken jsontoken)
        {
            long l = jsontoken.ordinal();
            if (i > 0)
            {
                l <<= i << 2;
            }
            _tokenTypes = l | _tokenTypes;
        }

        public void set(int i, JsonToken jsontoken, Object obj)
        {
            _tokens[i] = obj;
            long l = jsontoken.ordinal();
            if (i > 0)
            {
                l <<= i << 2;
            }
            _tokenTypes = l | _tokenTypes;
        }

        public JsonToken type(int i)
        {
            long l = _tokenTypes;
            if (i > 0)
            {
                l >>= i << 2;
            }
            int j = 0xf & (int)l;
            return TOKEN_TYPES_BY_INDEX[j];
        }

        static 
        {
            TOKEN_TYPES_BY_INDEX = new JsonToken[16];
            JsonToken ajsontoken[] = JsonToken.values();
            System.arraycopy(ajsontoken, 1, TOKEN_TYPES_BY_INDEX, 1, Math.min(15, -1 + ajsontoken.length));
        }

        public Segment()
        {
        }
    }


    protected static final int DEFAULT_PARSER_FEATURES = org.codehaus.jackson.JsonParser.Feature.collectDefaults();
    protected int _appendOffset;
    protected boolean _closed;
    protected Segment _first;
    protected int _generatorFeatures;
    protected Segment _last;
    protected ObjectCodec _objectCodec;
    protected JsonWriteContext _writeContext;

    public TokenBuffer(ObjectCodec objectcodec)
    {
        _objectCodec = objectcodec;
        _generatorFeatures = DEFAULT_PARSER_FEATURES;
        _writeContext = JsonWriteContext.createRootContext();
        Segment segment = new Segment();
        _last = segment;
        _first = segment;
        _appendOffset = 0;
    }

    protected final void _append(JsonToken jsontoken)
    {
        Segment segment = _last.append(_appendOffset, jsontoken);
        if (segment == null)
        {
            _appendOffset = 1 + _appendOffset;
            return;
        } else
        {
            _last = segment;
            _appendOffset = 1;
            return;
        }
    }

    protected final void _append(JsonToken jsontoken, Object obj)
    {
        Segment segment = _last.append(_appendOffset, jsontoken, obj);
        if (segment == null)
        {
            _appendOffset = 1 + _appendOffset;
            return;
        } else
        {
            _last = segment;
            _appendOffset = 1;
            return;
        }
    }

    protected void _reportUnsupportedOperation()
    {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    public JsonParser asParser()
    {
        return asParser(_objectCodec);
    }

    public JsonParser asParser(JsonParser jsonparser)
    {
        Parser parser = new Parser(_first, jsonparser.getCodec());
        parser.setLocation(jsonparser.getTokenLocation());
        return parser;
    }

    public JsonParser asParser(ObjectCodec objectcodec)
    {
        return new Parser(_first, objectcodec);
    }

    public void close()
        throws IOException
    {
        _closed = true;
    }

    public void copyCurrentEvent(JsonParser jsonparser)
        throws IOException, JsonProcessingException
    {
        switch (_cls1..SwitchMap.org.codehaus.jackson.JsonToken[jsonparser.getCurrentToken().ordinal()])
        {
        default:
            throw new RuntimeException("Internal error: should never end up through this code path");

        case 1: // '\001'
            writeStartObject();
            return;

        case 2: // '\002'
            writeEndObject();
            return;

        case 3: // '\003'
            writeStartArray();
            return;

        case 4: // '\004'
            writeEndArray();
            return;

        case 5: // '\005'
            writeFieldName(jsonparser.getCurrentName());
            return;

        case 6: // '\006'
            if (jsonparser.hasTextCharacters())
            {
                writeString(jsonparser.getTextCharacters(), jsonparser.getTextOffset(), jsonparser.getTextLength());
                return;
            } else
            {
                writeString(jsonparser.getText());
                return;
            }

        case 7: // '\007'
            switch (_cls1..SwitchMap.org.codehaus.jackson.JsonParser.NumberType[jsonparser.getNumberType().ordinal()])
            {
            default:
                writeNumber(jsonparser.getLongValue());
                return;

            case 1: // '\001'
                writeNumber(jsonparser.getIntValue());
                return;

            case 2: // '\002'
                writeNumber(jsonparser.getBigIntegerValue());
                break;
            }
            return;

        case 8: // '\b'
            switch (_cls1..SwitchMap.org.codehaus.jackson.JsonParser.NumberType[jsonparser.getNumberType().ordinal()])
            {
            default:
                writeNumber(jsonparser.getDoubleValue());
                return;

            case 3: // '\003'
                writeNumber(jsonparser.getDecimalValue());
                return;

            case 4: // '\004'
                writeNumber(jsonparser.getFloatValue());
                break;
            }
            return;

        case 9: // '\t'
            writeBoolean(true);
            return;

        case 10: // '\n'
            writeBoolean(false);
            return;

        case 11: // '\013'
            writeNull();
            return;

        case 12: // '\f'
            writeObject(jsonparser.getEmbeddedObject());
            return;
        }
    }

    public void copyCurrentStructure(JsonParser jsonparser)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken == JsonToken.FIELD_NAME)
        {
            writeFieldName(jsonparser.getCurrentName());
            jsontoken = jsonparser.nextToken();
        }
        switch (_cls1..SwitchMap.org.codehaus.jackson.JsonToken[jsontoken.ordinal()])
        {
        case 2: // '\002'
        default:
            copyCurrentEvent(jsonparser);
            return;

        case 3: // '\003'
            writeStartArray();
            for (; jsonparser.nextToken() != JsonToken.END_ARRAY; copyCurrentStructure(jsonparser)) { }
            writeEndArray();
            return;

        case 1: // '\001'
            writeStartObject();
            break;
        }
        for (; jsonparser.nextToken() != JsonToken.END_OBJECT; copyCurrentStructure(jsonparser)) { }
        writeEndObject();
    }

    public JsonGenerator disable(org.codehaus.jackson.JsonGenerator.Feature feature)
    {
        _generatorFeatures = _generatorFeatures & (-1 ^ feature.getMask());
        return this;
    }

    public JsonGenerator enable(org.codehaus.jackson.JsonGenerator.Feature feature)
    {
        _generatorFeatures = _generatorFeatures | feature.getMask();
        return this;
    }

    public void flush()
        throws IOException
    {
    }

    public ObjectCodec getCodec()
    {
        return _objectCodec;
    }

    public volatile JsonStreamContext getOutputContext()
    {
        return getOutputContext();
    }

    public final JsonWriteContext getOutputContext()
    {
        return _writeContext;
    }

    public boolean isClosed()
    {
        return _closed;
    }

    public boolean isEnabled(org.codehaus.jackson.JsonGenerator.Feature feature)
    {
        return (_generatorFeatures & feature.getMask()) != 0;
    }

    public void serialize(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        Segment segment;
        int i;
        segment = _first;
        i = -1;
_L6:
        if (++i < 16) goto _L2; else goto _L1
_L1:
        segment = segment.next();
        i = 0;
        if (segment != null) goto _L2; else goto _L3
_L3:
        JsonToken jsontoken;
        return;
_L2:
        if ((jsontoken = segment.type(i)) == null) goto _L3; else goto _L4
_L4:
        switch (_cls1..SwitchMap.org.codehaus.jackson.JsonToken[jsontoken.ordinal()])
        {
        default:
            throw new RuntimeException("Internal error: should never end up through this code path");

        case 1: // '\001'
            jsongenerator.writeStartObject();
            break;

        case 2: // '\002'
            jsongenerator.writeEndObject();
            break;

        case 3: // '\003'
            jsongenerator.writeStartArray();
            break;

        case 4: // '\004'
            jsongenerator.writeEndArray();
            break;

        case 5: // '\005'
            Object obj2 = segment.get(i);
            if (obj2 instanceof SerializableString)
            {
                jsongenerator.writeFieldName((SerializableString)obj2);
            } else
            {
                jsongenerator.writeFieldName((String)obj2);
            }
            break;

        case 6: // '\006'
            Object obj1 = segment.get(i);
            if (obj1 instanceof SerializableString)
            {
                jsongenerator.writeString((SerializableString)obj1);
            } else
            {
                jsongenerator.writeString((String)obj1);
            }
            break;

        case 7: // '\007'
            Number number = (Number)segment.get(i);
            if (number instanceof BigInteger)
            {
                jsongenerator.writeNumber((BigInteger)number);
            } else
            if (number instanceof Long)
            {
                jsongenerator.writeNumber(number.longValue());
            } else
            {
                jsongenerator.writeNumber(number.intValue());
            }
            break;

        case 8: // '\b'
            Object obj = segment.get(i);
            if (obj instanceof BigDecimal)
            {
                jsongenerator.writeNumber((BigDecimal)obj);
            } else
            if (obj instanceof Float)
            {
                jsongenerator.writeNumber(((Float)obj).floatValue());
            } else
            if (obj instanceof Double)
            {
                jsongenerator.writeNumber(((Double)obj).doubleValue());
            } else
            if (obj == null)
            {
                jsongenerator.writeNull();
            } else
            if (obj instanceof String)
            {
                jsongenerator.writeNumber((String)obj);
            } else
            {
                throw new JsonGenerationException((new StringBuilder()).append("Unrecognized value type for VALUE_NUMBER_FLOAT: ").append(obj.getClass().getName()).append(", can not serialize").toString());
            }
            break;

        case 9: // '\t'
            jsongenerator.writeBoolean(true);
            break;

        case 10: // '\n'
            jsongenerator.writeBoolean(false);
            break;

        case 11: // '\013'
            jsongenerator.writeNull();
            break;

        case 12: // '\f'
            jsongenerator.writeObject(segment.get(i));
            break;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public JsonGenerator setCodec(ObjectCodec objectcodec)
    {
        _objectCodec = objectcodec;
        return this;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[TokenBuffer: ");
        JsonParser jsonparser = asParser();
        int i = 0;
        do
        {
            JsonToken jsontoken;
            try
            {
                jsontoken = jsonparser.nextToken();
            }
            catch (IOException ioexception)
            {
                throw new IllegalStateException(ioexception);
            }
            if (jsontoken == null)
            {
                if (i >= 100)
                {
                    stringbuilder.append(" ... (truncated ").append(i - 100).append(" entries)");
                }
                stringbuilder.append(']');
                return stringbuilder.toString();
            }
            if (i < 100)
            {
                if (i > 0)
                {
                    stringbuilder.append(", ");
                }
                stringbuilder.append(jsontoken.toString());
            }
            i++;
        } while (true);
    }

    public JsonGenerator useDefaultPrettyPrinter()
    {
        return this;
    }

    public void writeBinary(Base64Variant base64variant, byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        byte abyte1[] = new byte[j];
        System.arraycopy(abyte0, i, abyte1, 0, j);
        writeObject(abyte1);
    }

    public void writeBoolean(boolean flag)
        throws IOException, JsonGenerationException
    {
        JsonToken jsontoken;
        if (flag)
        {
            jsontoken = JsonToken.VALUE_TRUE;
        } else
        {
            jsontoken = JsonToken.VALUE_FALSE;
        }
        _append(jsontoken);
    }

    public final void writeEndArray()
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.END_ARRAY);
        JsonWriteContext jsonwritecontext = _writeContext.getParent();
        if (jsonwritecontext != null)
        {
            _writeContext = jsonwritecontext;
        }
    }

    public final void writeEndObject()
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.END_OBJECT);
        JsonWriteContext jsonwritecontext = _writeContext.getParent();
        if (jsonwritecontext != null)
        {
            _writeContext = jsonwritecontext;
        }
    }

    public final void writeFieldName(String s)
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.FIELD_NAME, s);
        _writeContext.writeFieldName(s);
    }

    public void writeFieldName(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.FIELD_NAME, serializablestring);
        _writeContext.writeFieldName(serializablestring.getValue());
    }

    public void writeFieldName(SerializedString serializedstring)
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.FIELD_NAME, serializedstring);
        _writeContext.writeFieldName(serializedstring.getValue());
    }

    public void writeNull()
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.VALUE_NULL);
    }

    public void writeNumber(double d)
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.VALUE_NUMBER_FLOAT, Double.valueOf(d));
    }

    public void writeNumber(float f)
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.VALUE_NUMBER_FLOAT, Float.valueOf(f));
    }

    public void writeNumber(int i)
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.VALUE_NUMBER_INT, Integer.valueOf(i));
    }

    public void writeNumber(long l)
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.VALUE_NUMBER_INT, Long.valueOf(l));
    }

    public void writeNumber(String s)
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.VALUE_NUMBER_FLOAT, s);
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
            _append(JsonToken.VALUE_NUMBER_FLOAT, bigdecimal);
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
            _append(JsonToken.VALUE_NUMBER_INT, biginteger);
            return;
        }
    }

    public void writeObject(Object obj)
        throws IOException, JsonProcessingException
    {
        _append(JsonToken.VALUE_EMBEDDED_OBJECT, obj);
    }

    public void writeRaw(char c)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public void writeRaw(String s)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public void writeRaw(String s, int i, int j)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public void writeRaw(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public void writeRawUTF8String(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public void writeRawValue(String s)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public void writeRawValue(String s, int i, int j)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public void writeRawValue(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

    public final void writeStartArray()
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.START_ARRAY);
        _writeContext = _writeContext.createChildArrayContext();
    }

    public final void writeStartObject()
        throws IOException, JsonGenerationException
    {
        _append(JsonToken.START_OBJECT);
        _writeContext = _writeContext.createChildObjectContext();
    }

    public void writeString(String s)
        throws IOException, JsonGenerationException
    {
        if (s == null)
        {
            writeNull();
            return;
        } else
        {
            _append(JsonToken.VALUE_STRING, s);
            return;
        }
    }

    public void writeString(SerializableString serializablestring)
        throws IOException, JsonGenerationException
    {
        if (serializablestring == null)
        {
            writeNull();
            return;
        } else
        {
            _append(JsonToken.VALUE_STRING, serializablestring);
            return;
        }
    }

    public void writeString(char ac[], int i, int j)
        throws IOException, JsonGenerationException
    {
        writeString(new String(ac, i, j));
    }

    public void writeTree(JsonNode jsonnode)
        throws IOException, JsonProcessingException
    {
        _append(JsonToken.VALUE_EMBEDDED_OBJECT, jsonnode);
    }

    public void writeUTF8String(byte abyte0[], int i, int j)
        throws IOException, JsonGenerationException
    {
        _reportUnsupportedOperation();
    }

}
