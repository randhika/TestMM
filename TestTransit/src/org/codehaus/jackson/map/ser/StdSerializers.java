// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializable;
import org.codehaus.jackson.map.JsonSerializableWithType;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.JsonSerializableSchema;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.TokenBuffer;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            ScalarSerializerBase, SerializerBase

public class StdSerializers
{
    public static final class BooleanSerializer extends NonTypedScalarSerializer
    {

        final boolean _forPrimitive;

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            boolean flag;
            if (!_forPrimitive)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            return createSchemaNode("boolean", flag);
        }

        public void serialize(Boolean boolean1, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeBoolean(boolean1.booleanValue());
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((Boolean)obj, jsongenerator, serializerprovider);
        }

        public BooleanSerializer(boolean flag)
        {
            super(java/lang/Boolean);
            _forPrimitive = flag;
        }
    }

    public static final class CalendarSerializer extends ScalarSerializerBase
    {

        public static final CalendarSerializer instance = new CalendarSerializer();

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            String s;
            if (serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS))
            {
                s = "number";
            } else
            {
                s = "string";
            }
            return createSchemaNode(s, true);
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((Calendar)obj, jsongenerator, serializerprovider);
        }

        public void serialize(Calendar calendar, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializerprovider.defaultSerializeDateValue(calendar.getTimeInMillis(), jsongenerator);
        }


        public CalendarSerializer()
        {
            super(java/util/Calendar);
        }
    }

    public static final class DoubleSerializer extends NonTypedScalarSerializer
    {

        static final DoubleSerializer instance = new DoubleSerializer();

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            return createSchemaNode("number", true);
        }

        public void serialize(Double double1, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeNumber(double1.doubleValue());
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((Double)obj, jsongenerator, serializerprovider);
        }


        public DoubleSerializer()
        {
            super(java/lang/Double);
        }
    }

    public static final class FloatSerializer extends ScalarSerializerBase
    {

        static final FloatSerializer instance = new FloatSerializer();

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            return createSchemaNode("number", true);
        }

        public void serialize(Float float1, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeNumber(float1.floatValue());
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((Float)obj, jsongenerator, serializerprovider);
        }


        public FloatSerializer()
        {
            super(java/lang/Float);
        }
    }

    public static final class IntLikeSerializer extends ScalarSerializerBase
    {

        static final IntLikeSerializer instance = new IntLikeSerializer();

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            return createSchemaNode("integer", true);
        }

        public void serialize(Number number, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeNumber(number.intValue());
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((Number)obj, jsongenerator, serializerprovider);
        }


        public IntLikeSerializer()
        {
            super(java/lang/Number);
        }
    }

    public static final class IntegerSerializer extends NonTypedScalarSerializer
    {

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            return createSchemaNode("integer", true);
        }

        public void serialize(Integer integer, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeNumber(integer.intValue());
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((Integer)obj, jsongenerator, serializerprovider);
        }

        public IntegerSerializer()
        {
            super(java/lang/Integer);
        }
    }

    public static final class LongSerializer extends ScalarSerializerBase
    {

        static final LongSerializer instance = new LongSerializer();

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            return createSchemaNode("number", true);
        }

        public void serialize(Long long1, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeNumber(long1.longValue());
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((Long)obj, jsongenerator, serializerprovider);
        }


        public LongSerializer()
        {
            super(java/lang/Long);
        }
    }

    protected static abstract class NonTypedScalarSerializer extends ScalarSerializerBase
    {

        public final void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonGenerationException
        {
            serialize(obj, jsongenerator, serializerprovider);
        }

        protected NonTypedScalarSerializer(Class class1)
        {
            super(class1);
        }
    }

    public static final class NumberSerializer extends ScalarSerializerBase
    {

        public static final NumberSerializer instance = new NumberSerializer();

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            return createSchemaNode("number", true);
        }

        public void serialize(Number number, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            if (number instanceof BigDecimal)
            {
                jsongenerator.writeNumber((BigDecimal)number);
                return;
            }
            if (number instanceof BigInteger)
            {
                jsongenerator.writeNumber((BigInteger)number);
                return;
            }
            if (number instanceof Double)
            {
                jsongenerator.writeNumber(((Double)number).doubleValue());
                return;
            }
            if (number instanceof Float)
            {
                jsongenerator.writeNumber(((Float)number).floatValue());
                return;
            } else
            {
                jsongenerator.writeNumber(number.toString());
                return;
            }
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((Number)obj, jsongenerator, serializerprovider);
        }


        public NumberSerializer()
        {
            super(java/lang/Number);
        }
    }

    public static final class SerializableSerializer extends SerializerBase
    {

        protected static final SerializableSerializer instance = new SerializableSerializer();

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
            throws JsonMappingException
        {
            ObjectNode objectnode = createObjectNode();
            String s = "any";
            String s1 = null;
            String s2 = null;
            if (type != null)
            {
                Class class1 = TypeFactory.type(type).getRawClass();
                boolean flag = class1.isAnnotationPresent(org/codehaus/jackson/schema/JsonSerializableSchema);
                s1 = null;
                s2 = null;
                if (flag)
                {
                    JsonSerializableSchema jsonserializableschema = (JsonSerializableSchema)class1.getAnnotation(org/codehaus/jackson/schema/JsonSerializableSchema);
                    s = jsonserializableschema.schemaType();
                    boolean flag1 = "##irrelevant".equals(jsonserializableschema.schemaObjectPropertiesDefinition());
                    s2 = null;
                    if (!flag1)
                    {
                        s2 = jsonserializableschema.schemaObjectPropertiesDefinition();
                    }
                    boolean flag2 = "##irrelevant".equals(jsonserializableschema.schemaItemDefinition());
                    s1 = null;
                    if (!flag2)
                    {
                        s1 = jsonserializableschema.schemaItemDefinition();
                    }
                }
            }
            objectnode.put("type", s);
            if (s2 != null)
            {
                try
                {
                    objectnode.put("properties", (JsonNode)(new ObjectMapper()).readValue(s2, org/codehaus/jackson/JsonNode));
                }
                catch (IOException ioexception1)
                {
                    throw new IllegalStateException(ioexception1);
                }
            }
            if (s1 != null)
            {
                try
                {
                    objectnode.put("items", (JsonNode)(new ObjectMapper()).readValue(s1, org/codehaus/jackson/JsonNode));
                }
                catch (IOException ioexception)
                {
                    throw new IllegalStateException(ioexception);
                }
            }
            return objectnode;
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((JsonSerializable)obj, jsongenerator, serializerprovider);
        }

        public void serialize(JsonSerializable jsonserializable, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsonserializable.serialize(jsongenerator, serializerprovider);
        }

        public volatile void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonProcessingException
        {
            serializeWithType((JsonSerializable)obj, jsongenerator, serializerprovider, typeserializer);
        }

        public final void serializeWithType(JsonSerializable jsonserializable, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonGenerationException
        {
            if (jsonserializable instanceof JsonSerializableWithType)
            {
                ((JsonSerializableWithType)jsonserializable).serializeWithType(jsongenerator, serializerprovider, typeserializer);
                return;
            } else
            {
                serialize(jsonserializable, jsongenerator, serializerprovider);
                return;
            }
        }


        private SerializableSerializer()
        {
            super(org/codehaus/jackson/map/JsonSerializable);
        }
    }

    public static final class SerializableWithTypeSerializer extends SerializerBase
    {

        protected static final SerializableWithTypeSerializer instance = new SerializableWithTypeSerializer();

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
            throws JsonMappingException
        {
            ObjectNode objectnode = createObjectNode();
            String s = "any";
            String s1 = null;
            String s2 = null;
            if (type != null)
            {
                Class class1 = TypeFactory.rawClass(type);
                boolean flag = class1.isAnnotationPresent(org/codehaus/jackson/schema/JsonSerializableSchema);
                s1 = null;
                s2 = null;
                if (flag)
                {
                    JsonSerializableSchema jsonserializableschema = (JsonSerializableSchema)class1.getAnnotation(org/codehaus/jackson/schema/JsonSerializableSchema);
                    s = jsonserializableschema.schemaType();
                    boolean flag1 = "##irrelevant".equals(jsonserializableschema.schemaObjectPropertiesDefinition());
                    s2 = null;
                    if (!flag1)
                    {
                        s2 = jsonserializableschema.schemaObjectPropertiesDefinition();
                    }
                    boolean flag2 = "##irrelevant".equals(jsonserializableschema.schemaItemDefinition());
                    s1 = null;
                    if (!flag2)
                    {
                        s1 = jsonserializableschema.schemaItemDefinition();
                    }
                }
            }
            objectnode.put("type", s);
            if (s2 != null)
            {
                try
                {
                    objectnode.put("properties", (JsonNode)(new ObjectMapper()).readValue(s2, org/codehaus/jackson/JsonNode));
                }
                catch (IOException ioexception1)
                {
                    throw new IllegalStateException(ioexception1);
                }
            }
            if (s1 != null)
            {
                try
                {
                    objectnode.put("items", (JsonNode)(new ObjectMapper()).readValue(s1, org/codehaus/jackson/JsonNode));
                }
                catch (IOException ioexception)
                {
                    throw new IllegalStateException(ioexception);
                }
            }
            return objectnode;
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((JsonSerializableWithType)obj, jsongenerator, serializerprovider);
        }

        public void serialize(JsonSerializableWithType jsonserializablewithtype, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsonserializablewithtype.serialize(jsongenerator, serializerprovider);
        }

        public volatile void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonProcessingException
        {
            serializeWithType((JsonSerializableWithType)obj, jsongenerator, serializerprovider, typeserializer);
        }

        public final void serializeWithType(JsonSerializableWithType jsonserializablewithtype, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonGenerationException
        {
            jsonserializablewithtype.serializeWithType(jsongenerator, serializerprovider, typeserializer);
        }


        private SerializableWithTypeSerializer()
        {
            super(org/codehaus/jackson/map/JsonSerializableWithType);
        }
    }

    public static final class SqlDateSerializer extends ScalarSerializerBase
    {

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            return createSchemaNode("string", true);
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((Date)obj, jsongenerator, serializerprovider);
        }

        public void serialize(Date date, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeString(date.toString());
        }

        public SqlDateSerializer()
        {
            super(java/sql/Date);
        }
    }

    public static final class SqlTimeSerializer extends ScalarSerializerBase
    {

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            return createSchemaNode("string", true);
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((Time)obj, jsongenerator, serializerprovider);
        }

        public void serialize(Time time, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeString(time.toString());
        }

        public SqlTimeSerializer()
        {
            super(java/sql/Time);
        }
    }

    public static final class StringSerializer extends NonTypedScalarSerializer
    {

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            return createSchemaNode("string", true);
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((String)obj, jsongenerator, serializerprovider);
        }

        public void serialize(String s, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeString(s);
        }

        public StringSerializer()
        {
            super(java/lang/String);
        }
    }

    public static final class TokenBufferSerializer extends SerializerBase
    {

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            return createSchemaNode("any", true);
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((TokenBuffer)obj, jsongenerator, serializerprovider);
        }

        public void serialize(TokenBuffer tokenbuffer, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            tokenbuffer.serialize(jsongenerator);
        }

        public volatile void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonProcessingException
        {
            serializeWithType((TokenBuffer)obj, jsongenerator, serializerprovider, typeserializer);
        }

        public final void serializeWithType(TokenBuffer tokenbuffer, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonGenerationException
        {
            typeserializer.writeTypePrefixForScalar(tokenbuffer, jsongenerator);
            serialize(tokenbuffer, jsongenerator, serializerprovider);
            typeserializer.writeTypeSuffixForScalar(tokenbuffer, jsongenerator);
        }

        public TokenBufferSerializer()
        {
            super(org/codehaus/jackson/util/TokenBuffer);
        }
    }

    public static final class UtilDateSerializer extends ScalarSerializerBase
    {

        public static final UtilDateSerializer instance = new UtilDateSerializer();

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            String s;
            if (serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS))
            {
                s = "number";
            } else
            {
                s = "string";
            }
            return createSchemaNode(s, true);
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((java.util.Date)obj, jsongenerator, serializerprovider);
        }

        public void serialize(java.util.Date date, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializerprovider.defaultSerializeDateValue(date, jsongenerator);
        }


        public UtilDateSerializer()
        {
            super(java/util/Date);
        }
    }


    protected StdSerializers()
    {
    }
}
