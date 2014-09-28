// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.Base64Variants;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.io.NumberInput;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ResolvableDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.TokenBuffer;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdScalarDeserializer

public abstract class StdDeserializer extends JsonDeserializer
{
    public static final class AtomicBooleanDeserializer extends StdScalarDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public AtomicBoolean deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return new AtomicBoolean(_parseBooleanPrimitive(jsonparser, deserializationcontext));
        }

        public AtomicBooleanDeserializer()
        {
            super(java/util/concurrent/atomic/AtomicBoolean);
        }
    }

    public static class AtomicReferenceDeserializer extends StdScalarDeserializer
        implements ResolvableDeserializer
    {

        protected final BeanProperty _property;
        protected final JavaType _referencedType;
        protected JsonDeserializer _valueDeserializer;

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public AtomicReference deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return new AtomicReference(_valueDeserializer.deserialize(jsonparser, deserializationcontext));
        }

        public void resolve(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider)
            throws JsonMappingException
        {
            _valueDeserializer = deserializerprovider.findValueDeserializer(deserializationconfig, _referencedType, _property);
        }

        public AtomicReferenceDeserializer(JavaType javatype, BeanProperty beanproperty)
        {
            super(java/util/concurrent/atomic/AtomicReference);
            _referencedType = javatype;
            _property = beanproperty;
        }
    }

    public static class BigDecimalDeserializer extends StdScalarDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public BigDecimal deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            JsonToken jsontoken = jsonparser.getCurrentToken();
            if (jsontoken == JsonToken.VALUE_NUMBER_INT || jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
            {
                return jsonparser.getDecimalValue();
            }
            if (jsontoken == JsonToken.VALUE_STRING)
            {
                String s = jsonparser.getText().trim();
                if (s.length() == 0)
                {
                    return null;
                }
                BigDecimal bigdecimal;
                try
                {
                    bigdecimal = new BigDecimal(s);
                }
                catch (IllegalArgumentException illegalargumentexception)
                {
                    throw deserializationcontext.weirdStringException(_valueClass, "not a valid representation");
                }
                return bigdecimal;
            } else
            {
                throw deserializationcontext.mappingException(_valueClass);
            }
        }

        public BigDecimalDeserializer()
        {
            super(java/math/BigDecimal);
        }
    }

    public static class BigIntegerDeserializer extends StdScalarDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public BigInteger deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            JsonToken jsontoken = jsonparser.getCurrentToken();
            if (jsontoken != JsonToken.VALUE_NUMBER_INT) goto _L2; else goto _L1
_L1:
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
                        $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[org.codehaus.jackson.JsonParser.NumberType.LONG.ordinal()] = 2;
                    }
                    catch (NoSuchFieldError nosuchfielderror1) { }
                    $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 1;
                    }
                    catch (NoSuchFieldError nosuchfielderror2) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 2;
                    }
                    catch (NoSuchFieldError nosuchfielderror3) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 3;
                    }
                    catch (NoSuchFieldError nosuchfielderror4)
                    {
                        return;
                    }
                }
            }

            _cls1..SwitchMap.org.codehaus.jackson.JsonParser.NumberType[jsonparser.getNumberType().ordinal()];
            JVM INSTR tableswitch 1 2: default 44
        //                       1 63
        //                       2 63;
               goto _L3 _L4 _L4
_L3:
            String s;
            s = jsonparser.getText().trim();
            if (s.length() == 0)
            {
                return null;
            }
            break; /* Loop/switch isn't completed */
_L4:
            return BigInteger.valueOf(jsonparser.getLongValue());
_L2:
            if (jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
            {
                return jsonparser.getDecimalValue().toBigInteger();
            }
            if (jsontoken != JsonToken.VALUE_STRING)
            {
                throw deserializationcontext.mappingException(_valueClass);
            }
            if (true) goto _L3; else goto _L5
_L5:
            BigInteger biginteger;
            try
            {
                biginteger = new BigInteger(s);
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                throw deserializationcontext.weirdStringException(_valueClass, "not a valid representation");
            }
            return biginteger;
        }

        public BigIntegerDeserializer()
        {
            super(java/math/BigInteger);
        }
    }

    public static final class BooleanDeserializer extends PrimitiveOrWrapperDeserializer
    {

        public Boolean deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _parseBoolean(jsonparser, deserializationcontext);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public Boolean deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            return _parseBoolean(jsonparser, deserializationcontext);
        }

        public volatile Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            return deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
        }

        public BooleanDeserializer(Class class1, Boolean boolean1)
        {
            super(class1, boolean1);
        }
    }

    public static final class ByteDeserializer extends PrimitiveOrWrapperDeserializer
    {

        public Byte deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            int i = _parseIntPrimitive(jsonparser, deserializationcontext);
            if (i < -128 || i > 127)
            {
                throw deserializationcontext.weirdStringException(_valueClass, "overflow, value can not be represented as 8-bit value");
            } else
            {
                return Byte.valueOf((byte)i);
            }
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public ByteDeserializer(Class class1, Byte byte1)
        {
            super(class1, byte1);
        }
    }

    public static class CalendarDeserializer extends StdScalarDeserializer
    {

        Class _calendarClass;

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public Calendar deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            Date date = _parseDate(jsonparser, deserializationcontext);
            if (date == null)
            {
                return null;
            }
            if (_calendarClass == null)
            {
                return deserializationcontext.constructCalendar(date);
            }
            Calendar calendar;
            try
            {
                calendar = (Calendar)_calendarClass.newInstance();
                calendar.setTimeInMillis(date.getTime());
            }
            catch (Exception exception)
            {
                throw deserializationcontext.instantiationException(_calendarClass, exception);
            }
            return calendar;
        }

        public CalendarDeserializer()
        {
            this(null);
        }

        public CalendarDeserializer(Class class1)
        {
            super(java/util/Calendar);
            _calendarClass = class1;
        }
    }

    public static final class CharacterDeserializer extends PrimitiveOrWrapperDeserializer
    {

        public Character deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            JsonToken jsontoken = jsonparser.getCurrentToken();
            if (jsontoken == JsonToken.VALUE_NUMBER_INT)
            {
                int i = jsonparser.getIntValue();
                if (i >= 0 && i <= 65535)
                {
                    return Character.valueOf((char)i);
                }
            } else
            if (jsontoken == JsonToken.VALUE_STRING)
            {
                String s = jsonparser.getText();
                if (s.length() == 1)
                {
                    return Character.valueOf(s.charAt(0));
                }
            }
            throw deserializationcontext.mappingException(_valueClass);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public CharacterDeserializer(Class class1, Character character)
        {
            super(class1, character);
        }
    }

    public static final class ClassDeserializer extends StdScalarDeserializer
    {

        public Class deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (jsonparser.getCurrentToken() == JsonToken.VALUE_STRING)
            {
                String s = jsonparser.getText();
                if (s.indexOf('.') < 0)
                {
                    if ("int".equals(s))
                    {
                        return Integer.TYPE;
                    }
                    if ("long".equals(s))
                    {
                        return Long.TYPE;
                    }
                    if ("float".equals(s))
                    {
                        return Float.TYPE;
                    }
                    if ("double".equals(s))
                    {
                        return Double.TYPE;
                    }
                    if ("boolean".equals(s))
                    {
                        return Boolean.TYPE;
                    }
                    if ("byte".equals(s))
                    {
                        return Byte.TYPE;
                    }
                    if ("char".equals(s))
                    {
                        return Character.TYPE;
                    }
                    if ("short".equals(s))
                    {
                        return Short.TYPE;
                    }
                    if ("void".equals(s))
                    {
                        return Void.TYPE;
                    }
                }
                Class class1;
                try
                {
                    class1 = Class.forName(jsonparser.getText());
                }
                catch (ClassNotFoundException classnotfoundexception)
                {
                    throw deserializationcontext.instantiationException(_valueClass, classnotfoundexception);
                }
                return class1;
            } else
            {
                throw deserializationcontext.mappingException(_valueClass);
            }
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public ClassDeserializer()
        {
            super(java/lang/Class);
        }
    }

    public static final class DoubleDeserializer extends PrimitiveOrWrapperDeserializer
    {

        public Double deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _parseDouble(jsonparser, deserializationcontext);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public Double deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            return _parseDouble(jsonparser, deserializationcontext);
        }

        public volatile Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            return deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
        }

        public DoubleDeserializer(Class class1, Double double1)
        {
            super(class1, double1);
        }
    }

    public static final class FloatDeserializer extends PrimitiveOrWrapperDeserializer
    {

        public Float deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _parseFloat(jsonparser, deserializationcontext);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public FloatDeserializer(Class class1, Float float1)
        {
            super(class1, float1);
        }
    }

    public static final class IntegerDeserializer extends PrimitiveOrWrapperDeserializer
    {

        public Integer deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _parseInteger(jsonparser, deserializationcontext);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public Integer deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            return _parseInteger(jsonparser, deserializationcontext);
        }

        public volatile Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            return deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
        }

        public IntegerDeserializer(Class class1, Integer integer)
        {
            super(class1, integer);
        }
    }

    public static final class LongDeserializer extends PrimitiveOrWrapperDeserializer
    {

        public Long deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _parseLong(jsonparser, deserializationcontext);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public LongDeserializer(Class class1, Long long1)
        {
            super(class1, long1);
        }
    }

    public static final class NumberDeserializer extends StdScalarDeserializer
    {

        public Number deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            String s;
label0:
            {
                JsonToken jsontoken = jsonparser.getCurrentToken();
                if (jsontoken == JsonToken.VALUE_NUMBER_INT)
                {
                    if (deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS))
                    {
                        return jsonparser.getBigIntegerValue();
                    } else
                    {
                        return jsonparser.getNumberValue();
                    }
                }
                if (jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
                {
                    if (deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS))
                    {
                        return jsonparser.getDecimalValue();
                    } else
                    {
                        return Double.valueOf(jsonparser.getDoubleValue());
                    }
                }
                if (jsontoken != JsonToken.VALUE_STRING)
                {
                    break MISSING_BLOCK_LABEL_197;
                }
                s = jsonparser.getText().trim();
                BigDecimal bigdecimal;
                try
                {
                    if (s.indexOf('.') < 0)
                    {
                        break MISSING_BLOCK_LABEL_135;
                    }
                    if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS))
                    {
                        break label0;
                    }
                    bigdecimal = new BigDecimal(s);
                }
                catch (IllegalArgumentException illegalargumentexception)
                {
                    throw deserializationcontext.weirdStringException(_valueClass, "not a valid number");
                }
                return bigdecimal;
            }
            return new Double(s);
            long l;
            if (deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS))
            {
                return new BigInteger(s);
            }
            l = Long.parseLong(s);
            if (l > 0x7fffffffL || l < 0xffffffff80000000L)
            {
                break MISSING_BLOCK_LABEL_187;
            }
            return Integer.valueOf((int)l);
            Long long1 = Long.valueOf(l);
            return long1;
            throw deserializationcontext.mappingException(_valueClass);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            switch (_cls1..SwitchMap.org.codehaus.jackson.JsonToken[jsonparser.getCurrentToken().ordinal()])
            {
            default:
                return typedeserializer.deserializeTypedFromScalar(jsonparser, deserializationcontext);

            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                return deserialize(jsonparser, deserializationcontext);
            }
        }

        public NumberDeserializer()
        {
            super(java/lang/Number);
        }
    }

    protected static abstract class PrimitiveOrWrapperDeserializer extends StdScalarDeserializer
    {

        final Object _nullValue;

        public final Object getNullValue()
        {
            return _nullValue;
        }

        protected PrimitiveOrWrapperDeserializer(Class class1, Object obj)
        {
            super(class1);
            _nullValue = obj;
        }
    }

    public static final class ShortDeserializer extends PrimitiveOrWrapperDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public Short deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _parseShort(jsonparser, deserializationcontext);
        }

        public ShortDeserializer(Class class1, Short short1)
        {
            super(class1, short1);
        }
    }

    public static class SqlDateDeserializer extends StdScalarDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public java.sql.Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            Date date = _parseDate(jsonparser, deserializationcontext);
            if (date == null)
            {
                return null;
            } else
            {
                return new java.sql.Date(date.getTime());
            }
        }

        public SqlDateDeserializer()
        {
            super(java/sql/Date);
        }
    }

    public static class StackTraceElementDeserializer extends StdScalarDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public StackTraceElement deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (jsonparser.getCurrentToken() == JsonToken.START_OBJECT)
            {
                String s = "";
                String s1 = "";
                String s2 = "";
                int i = -1;
                do
                {
                    JsonToken jsontoken = jsonparser.nextValue();
                    if (jsontoken == JsonToken.END_OBJECT)
                    {
                        break;
                    }
                    String s3 = jsonparser.getCurrentName();
                    if ("className".equals(s3))
                    {
                        s = jsonparser.getText();
                    } else
                    if ("fileName".equals(s3))
                    {
                        s2 = jsonparser.getText();
                    } else
                    if ("lineNumber".equals(s3))
                    {
                        if (jsontoken.isNumeric())
                        {
                            i = jsonparser.getIntValue();
                        } else
                        {
                            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Non-numeric token (").append(jsontoken).append(") for property 'lineNumber'").toString());
                        }
                    } else
                    if ("methodName".equals(s3))
                    {
                        s1 = jsonparser.getText();
                    } else
                    if (!"nativeMethod".equals(s3))
                    {
                        handleUnknownProperty(jsonparser, deserializationcontext, _valueClass, s3);
                    }
                } while (true);
                return new StackTraceElement(s, s1, s2, i);
            } else
            {
                throw deserializationcontext.mappingException(_valueClass);
            }
        }

        public StackTraceElementDeserializer()
        {
            super(java/lang/StackTraceElement);
        }
    }

    public static final class StringDeserializer extends StdScalarDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public String deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            JsonToken jsontoken = jsonparser.getCurrentToken();
            if (jsontoken == JsonToken.VALUE_STRING)
            {
                return jsonparser.getText();
            }
            if (jsontoken == JsonToken.VALUE_EMBEDDED_OBJECT)
            {
                Object obj = jsonparser.getEmbeddedObject();
                if (obj == null)
                {
                    return null;
                }
                if (obj instanceof byte[])
                {
                    return Base64Variants.getDefaultVariant().encode((byte[])(byte[])obj, false);
                } else
                {
                    return obj.toString();
                }
            }
            if (jsontoken.isScalarValue())
            {
                return jsonparser.getText();
            } else
            {
                throw deserializationcontext.mappingException(_valueClass);
            }
        }

        public volatile Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            return deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
        }

        public String deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public StringDeserializer()
        {
            super(java/lang/String);
        }
    }

    public static class TokenBufferDeserializer extends StdScalarDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public TokenBuffer deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            TokenBuffer tokenbuffer = new TokenBuffer(jsonparser.getCodec());
            tokenbuffer.copyCurrentStructure(jsonparser);
            return tokenbuffer;
        }

        public TokenBufferDeserializer()
        {
            super(org/codehaus/jackson/util/TokenBuffer);
        }
    }


    protected final Class _valueClass;

    protected StdDeserializer(Class class1)
    {
        _valueClass = class1;
    }

    protected StdDeserializer(JavaType javatype)
    {
        Class class1;
        if (javatype == null)
        {
            class1 = null;
        } else
        {
            class1 = javatype.getRawClass();
        }
        _valueClass = class1;
    }

    protected static final double parseDouble(String s)
        throws NumberFormatException
    {
        if ("2.2250738585072012e-308".equals(s))
        {
            return 2.2250738585072014E-308D;
        } else
        {
            return Double.parseDouble(s);
        }
    }

    protected final Boolean _parseBoolean(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken == JsonToken.VALUE_TRUE)
        {
            return Boolean.TRUE;
        }
        if (jsontoken == JsonToken.VALUE_FALSE)
        {
            return Boolean.FALSE;
        }
        if (jsontoken == JsonToken.VALUE_NULL)
        {
            return null;
        }
        if (jsontoken == JsonToken.VALUE_NUMBER_INT)
        {
            if (jsonparser.getIntValue() == 0)
            {
                return Boolean.FALSE;
            } else
            {
                return Boolean.TRUE;
            }
        }
        if (jsontoken == JsonToken.VALUE_STRING)
        {
            String s = jsonparser.getText().trim();
            if ("true".equals(s))
            {
                return Boolean.TRUE;
            }
            if ("false".equals(s) || s.length() == 0)
            {
                return Boolean.FALSE;
            } else
            {
                throw deserializationcontext.weirdStringException(_valueClass, "only \"true\" or \"false\" recognized");
            }
        } else
        {
            throw deserializationcontext.mappingException(_valueClass);
        }
    }

    protected final boolean _parseBooleanPrimitive(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken != JsonToken.VALUE_TRUE) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (jsontoken == JsonToken.VALUE_FALSE)
        {
            return false;
        }
        if (jsontoken == JsonToken.VALUE_NULL)
        {
            return false;
        }
        if (jsontoken != JsonToken.VALUE_NUMBER_INT)
        {
            break; /* Loop/switch isn't completed */
        }
        if (jsonparser.getIntValue() == 0)
        {
            return false;
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (jsontoken == JsonToken.VALUE_STRING)
        {
            String s = jsonparser.getText().trim();
            if (!"true".equals(s))
            {
                if ("false".equals(s) || s.length() == 0)
                {
                    return Boolean.FALSE.booleanValue();
                } else
                {
                    throw deserializationcontext.weirdStringException(_valueClass, "only \"true\" or \"false\" recognized");
                }
            }
        } else
        {
            throw deserializationcontext.mappingException(_valueClass);
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    protected Date _parseDate(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        String s;
        if (jsontoken == JsonToken.VALUE_NUMBER_INT)
        {
            return new Date(jsonparser.getLongValue());
        }
        if (jsontoken != JsonToken.VALUE_STRING)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        s = jsonparser.getText().trim();
        if (s.length() == 0)
        {
            return null;
        }
        return deserializationcontext.parseDate(s);
        throw deserializationcontext.mappingException(_valueClass);
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        throw deserializationcontext.weirdStringException(_valueClass, (new StringBuilder()).append("not a valid representation (error: ").append(illegalargumentexception.getMessage()).append(")").toString());
    }

    protected final Double _parseDouble(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken != JsonToken.VALUE_NUMBER_INT && jsontoken != JsonToken.VALUE_NUMBER_FLOAT) goto _L2; else goto _L1
_L1:
        Double double1 = Double.valueOf(jsonparser.getDoubleValue());
_L13:
        return double1;
_L2:
        if (jsontoken != JsonToken.VALUE_STRING) goto _L4; else goto _L3
_L3:
        String s;
        s = jsonparser.getText().trim();
        int i = s.length();
        double1 = null;
        if (i == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        s.charAt(0);
        JVM INSTR lookupswitch 3: default 104
    //                   45: 161
    //                   73: 117
    //                   78: 144;
           goto _L5 _L6 _L7 _L8
_L5:
        Double double2;
        try
        {
            double2 = Double.valueOf(parseDouble(s));
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw deserializationcontext.weirdStringException(_valueClass, "not a valid Double value");
        }
        return double2;
_L7:
        if (!"Infinity".equals(s) && !"INF".equals(s)) goto _L5; else goto _L9
_L9:
        return Double.valueOf((1.0D / 0.0D));
_L8:
        if (!"NaN".equals(s)) goto _L5; else goto _L10
_L10:
        return Double.valueOf((0.0D / 0.0D));
_L6:
        if (!"-Infinity".equals(s) && !"-INF".equals(s)) goto _L5; else goto _L11
_L11:
        return Double.valueOf((-1.0D / 0.0D));
_L4:
        JsonToken jsontoken1 = JsonToken.VALUE_NULL;
        double1 = null;
        if (jsontoken != jsontoken1)
        {
            throw deserializationcontext.mappingException(_valueClass);
        }
        if (true) goto _L13; else goto _L12
_L12:
    }

    protected final double _parseDoublePrimitive(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        double d;
        JsonToken jsontoken;
        d = 0.0D;
        jsontoken = jsonparser.getCurrentToken();
        if (jsontoken != JsonToken.VALUE_NUMBER_INT && jsontoken != JsonToken.VALUE_NUMBER_FLOAT) goto _L2; else goto _L1
_L1:
        d = jsonparser.getDoubleValue();
_L4:
        return d;
_L2:
        String s;
        if (jsontoken != JsonToken.VALUE_STRING)
        {
            continue; /* Loop/switch isn't completed */
        }
        s = jsonparser.getText().trim();
        if (s.length() == 0) goto _L4; else goto _L3
_L3:
        s.charAt(0);
        JVM INSTR lookupswitch 3: default 96
    //                   45: 144
    //                   73: 106
    //                   78: 130;
           goto _L5 _L6 _L7 _L8
_L5:
        double d1;
        try
        {
            d1 = parseDouble(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw deserializationcontext.weirdStringException(_valueClass, "not a valid double value");
        }
        return d1;
_L7:
        if (!"Infinity".equals(s) && !"INF".equals(s)) goto _L5; else goto _L9
_L9:
        return (1.0D / 0.0D);
_L8:
        if (!"NaN".equals(s)) goto _L5; else goto _L10
_L10:
        return (0.0D / 0.0D);
_L6:
        if (!"-Infinity".equals(s) && !"-INF".equals(s)) goto _L5; else goto _L11
_L11:
        return (-1.0D / 0.0D);
        if (jsontoken == JsonToken.VALUE_NULL) goto _L4; else goto _L12
_L12:
        throw deserializationcontext.mappingException(_valueClass);
    }

    protected final Float _parseFloat(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken != JsonToken.VALUE_NUMBER_INT && jsontoken != JsonToken.VALUE_NUMBER_FLOAT) goto _L2; else goto _L1
_L1:
        Float float1 = Float.valueOf(jsonparser.getFloatValue());
_L13:
        return float1;
_L2:
        if (jsontoken != JsonToken.VALUE_STRING) goto _L4; else goto _L3
_L3:
        String s;
        s = jsonparser.getText().trim();
        int i = s.length();
        float1 = null;
        if (i == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        s.charAt(0);
        JVM INSTR lookupswitch 3: default 104
    //                   45: 159
    //                   73: 117
    //                   78: 143;
           goto _L5 _L6 _L7 _L8
_L5:
        Float float2;
        try
        {
            float2 = Float.valueOf(Float.parseFloat(s));
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw deserializationcontext.weirdStringException(_valueClass, "not a valid Float value");
        }
        return float2;
_L7:
        if (!"Infinity".equals(s) && !"INF".equals(s)) goto _L5; else goto _L9
_L9:
        return Float.valueOf((1.0F / 0.0F));
_L8:
        if (!"NaN".equals(s)) goto _L5; else goto _L10
_L10:
        return Float.valueOf((0.0F / 0.0F));
_L6:
        if (!"-Infinity".equals(s) && !"-INF".equals(s)) goto _L5; else goto _L11
_L11:
        return Float.valueOf((-1.0F / 0.0F));
_L4:
        JsonToken jsontoken1 = JsonToken.VALUE_NULL;
        float1 = null;
        if (jsontoken != jsontoken1)
        {
            throw deserializationcontext.mappingException(_valueClass);
        }
        if (true) goto _L13; else goto _L12
_L12:
    }

    protected final float _parseFloatPrimitive(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken != JsonToken.VALUE_NUMBER_INT && jsontoken != JsonToken.VALUE_NUMBER_FLOAT) goto _L2; else goto _L1
_L1:
        float f = jsonparser.getFloatValue();
_L13:
        return f;
_L2:
        if (jsontoken != JsonToken.VALUE_STRING) goto _L4; else goto _L3
_L3:
        String s;
        s = jsonparser.getText().trim();
        int i = s.length();
        f = 0.0F;
        if (i == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        s.charAt(0);
        JVM INSTR lookupswitch 3: default 100
    //                   45: 146
    //                   73: 110
    //                   78: 133;
           goto _L5 _L6 _L7 _L8
_L5:
        float f1;
        try
        {
            f1 = Float.parseFloat(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw deserializationcontext.weirdStringException(_valueClass, "not a valid float value");
        }
        return f1;
_L7:
        if (!"Infinity".equals(s) && !"INF".equals(s)) goto _L5; else goto _L9
_L9:
        return (1.0F / 0.0F);
_L8:
        if (!"NaN".equals(s)) goto _L5; else goto _L10
_L10:
        return (0.0F / 0.0F);
_L6:
        if (!"-Infinity".equals(s) && !"-INF".equals(s)) goto _L5; else goto _L11
_L11:
        return (-1.0F / 0.0F);
_L4:
        JsonToken jsontoken1 = JsonToken.VALUE_NULL;
        f = 0.0F;
        if (jsontoken != jsontoken1)
        {
            throw deserializationcontext.mappingException(_valueClass);
        }
        if (true) goto _L13; else goto _L12
_L12:
    }

    protected final int _parseIntPrimitive(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken != JsonToken.VALUE_NUMBER_INT && jsontoken != JsonToken.VALUE_NUMBER_FLOAT) goto _L2; else goto _L1
_L1:
        int i = jsonparser.getIntValue();
_L4:
        return i;
_L2:
        String s;
        int j;
        long l;
        if (jsontoken != JsonToken.VALUE_STRING)
        {
            break MISSING_BLOCK_LABEL_172;
        }
        s = jsonparser.getText().trim();
        try
        {
            j = s.length();
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw deserializationcontext.weirdStringException(_valueClass, "not a valid int value");
        }
        if (j <= 9)
        {
            break MISSING_BLOCK_LABEL_154;
        }
        l = Long.parseLong(s);
        if (l >= 0xffffffff80000000L && l <= 0x7fffffffL)
        {
            break MISSING_BLOCK_LABEL_150;
        }
        throw deserializationcontext.weirdStringException(_valueClass, (new StringBuilder()).append("Overflow: numeric value (").append(s).append(") out of range of int (").append(0x80000000).append(" - ").append(0x7fffffff).append(")").toString());
        return (int)l;
        i = 0;
        if (j == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        int k = NumberInput.parseInt(s);
        return k;
        JsonToken jsontoken1 = JsonToken.VALUE_NULL;
        i = 0;
        if (jsontoken != jsontoken1)
        {
            throw deserializationcontext.mappingException(_valueClass);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected final Integer _parseInteger(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken != JsonToken.VALUE_NUMBER_INT && jsontoken != JsonToken.VALUE_NUMBER_FLOAT) goto _L2; else goto _L1
_L1:
        Integer integer = Integer.valueOf(jsonparser.getIntValue());
_L4:
        return integer;
_L2:
        String s;
        int i;
        long l;
        if (jsontoken != JsonToken.VALUE_STRING)
        {
            break MISSING_BLOCK_LABEL_185;
        }
        s = jsonparser.getText().trim();
        try
        {
            i = s.length();
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw deserializationcontext.weirdStringException(_valueClass, "not a valid Integer value");
        }
        if (i <= 9)
        {
            break MISSING_BLOCK_LABEL_164;
        }
        l = Long.parseLong(s);
        if (l >= 0xffffffff80000000L && l <= 0x7fffffffL)
        {
            break MISSING_BLOCK_LABEL_153;
        }
        throw deserializationcontext.weirdStringException(_valueClass, (new StringBuilder()).append("Overflow: numeric value (").append(s).append(") out of range of Integer (").append(0x80000000).append(" - ").append(0x7fffffff).append(")").toString());
        int j = (int)l;
        return Integer.valueOf(j);
        integer = null;
        if (i == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        Integer integer1 = Integer.valueOf(NumberInput.parseInt(s));
        return integer1;
        JsonToken jsontoken1 = JsonToken.VALUE_NULL;
        integer = null;
        if (jsontoken != jsontoken1)
        {
            throw deserializationcontext.mappingException(_valueClass);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected final Long _parseLong(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken != JsonToken.VALUE_NUMBER_INT && jsontoken != JsonToken.VALUE_NUMBER_FLOAT) goto _L2; else goto _L1
_L1:
        Long long1 = Long.valueOf(jsonparser.getLongValue());
_L4:
        return long1;
_L2:
        if (jsontoken != JsonToken.VALUE_STRING)
        {
            break; /* Loop/switch isn't completed */
        }
        String s = jsonparser.getText().trim();
        int i = s.length();
        long1 = null;
        if (i != 0)
        {
            Long long2;
            try
            {
                long2 = Long.valueOf(NumberInput.parseLong(s));
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                throw deserializationcontext.weirdStringException(_valueClass, "not a valid Long value");
            }
            return long2;
        }
        if (true) goto _L4; else goto _L3
_L3:
        JsonToken jsontoken1 = JsonToken.VALUE_NULL;
        long1 = null;
        if (jsontoken != jsontoken1)
        {
            throw deserializationcontext.mappingException(_valueClass);
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    protected final long _parseLongPrimitive(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        long l;
        JsonToken jsontoken;
        l = 0L;
        jsontoken = jsonparser.getCurrentToken();
        if (jsontoken != JsonToken.VALUE_NUMBER_INT && jsontoken != JsonToken.VALUE_NUMBER_FLOAT) goto _L2; else goto _L1
_L1:
        l = jsonparser.getLongValue();
_L4:
        return l;
_L2:
        String s;
        if (jsontoken != JsonToken.VALUE_STRING)
        {
            continue; /* Loop/switch isn't completed */
        }
        s = jsonparser.getText().trim();
        if (s.length() == 0) goto _L4; else goto _L3
_L3:
        long l1;
        try
        {
            l1 = NumberInput.parseLong(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw deserializationcontext.weirdStringException(_valueClass, "not a valid long value");
        }
        return l1;
        if (jsontoken == JsonToken.VALUE_NULL) goto _L4; else goto _L5
_L5:
        throw deserializationcontext.mappingException(_valueClass);
    }

    protected final Short _parseShort(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken == JsonToken.VALUE_NULL)
        {
            return null;
        }
        if (jsontoken == JsonToken.VALUE_NUMBER_INT || jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
        {
            return Short.valueOf(jsonparser.getShortValue());
        }
        int i = _parseIntPrimitive(jsonparser, deserializationcontext);
        if (i < -32768 || i > 32767)
        {
            throw deserializationcontext.weirdStringException(_valueClass, "overflow, value can not be represented as 16-bit value");
        } else
        {
            return Short.valueOf((short)i);
        }
    }

    protected final short _parseShortPrimitive(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        int i = _parseIntPrimitive(jsonparser, deserializationcontext);
        if (i < -32768 || i > 32767)
        {
            throw deserializationcontext.weirdStringException(_valueClass, "overflow, value can not be represented as 16-bit value");
        } else
        {
            return (short)i;
        }
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromAny(jsonparser, deserializationcontext);
    }

    protected JsonDeserializer findDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        return deserializerprovider.findValueDeserializer(deserializationconfig, javatype, beanproperty);
    }

    public Class getValueClass()
    {
        return _valueClass;
    }

    public JavaType getValueType()
    {
        return null;
    }

    protected void handleUnknownProperty(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj, String s)
        throws IOException, JsonProcessingException
    {
        if (obj == null)
        {
            obj = getValueClass();
        }
        if (deserializationcontext.handleUnknownProperty(jsonparser, this, obj, s))
        {
            return;
        } else
        {
            reportUnknownProperty(deserializationcontext, obj, s);
            jsonparser.skipChildren();
            return;
        }
    }

    protected boolean isDefaultSerializer(JsonDeserializer jsondeserializer)
    {
        return jsondeserializer != null && jsondeserializer.getClass().getAnnotation(org/codehaus/jackson/map/annotate/JacksonStdImpl) != null;
    }

    protected void reportUnknownProperty(DeserializationContext deserializationcontext, Object obj, String s)
        throws IOException, JsonProcessingException
    {
        if (deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES))
        {
            throw deserializationcontext.unknownFieldException(obj, s);
        } else
        {
            return;
        }
    }
}
