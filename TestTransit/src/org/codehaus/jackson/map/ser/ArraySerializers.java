// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.node.ObjectNode;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            ContainerSerializerBase, SerializerBase

public final class ArraySerializers
{
    public static abstract class AsArraySerializer extends ContainerSerializerBase
    {

        protected final BeanProperty _property;
        protected final TypeSerializer _valueTypeSerializer;

        public final void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeStartArray();
            serializeContents(obj, jsongenerator, serializerprovider);
            jsongenerator.writeEndArray();
        }

        protected abstract void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException;

        public final void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonGenerationException
        {
            typeserializer.writeTypePrefixForArray(obj, jsongenerator);
            serializeContents(obj, jsongenerator, serializerprovider);
            typeserializer.writeTypeSuffixForArray(obj, jsongenerator);
        }

        protected AsArraySerializer(Class class1, TypeSerializer typeserializer, BeanProperty beanproperty)
        {
            super(class1);
            _valueTypeSerializer = typeserializer;
            _property = beanproperty;
        }
    }

    public static final class BooleanArraySerializer extends AsArraySerializer
    {

        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return this;
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            objectnode.put("items", createSchemaNode("boolean"));
            return objectnode;
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((boolean[])obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(boolean aflag[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            int i = 0;
            for (int j = aflag.length; i < j; i++)
            {
                jsongenerator.writeBoolean(aflag[i]);
            }

        }

        public BooleanArraySerializer()
        {
            super([Z, null, null);
        }
    }

    public static final class ByteArraySerializer extends SerializerBase
    {

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            objectnode.put("items", createSchemaNode("string"));
            return objectnode;
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((byte[])obj, jsongenerator, serializerprovider);
        }

        public void serialize(byte abyte0[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeBinary(abyte0);
        }

        public volatile void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonProcessingException
        {
            serializeWithType((byte[])obj, jsongenerator, serializerprovider, typeserializer);
        }

        public void serializeWithType(byte abyte0[], JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonGenerationException
        {
            typeserializer.writeTypePrefixForScalar(abyte0, jsongenerator);
            jsongenerator.writeBinary(abyte0);
            typeserializer.writeTypeSuffixForScalar(abyte0, jsongenerator);
        }

        public ByteArraySerializer()
        {
            super([B);
        }
    }

    public static final class CharArraySerializer extends SerializerBase
    {

        private final void _writeArrayContents(JsonGenerator jsongenerator, char ac[])
            throws IOException, JsonGenerationException
        {
            int i = 0;
            for (int j = ac.length; i < j; i++)
            {
                jsongenerator.writeString(ac, i, 1);
            }

        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            ObjectNode objectnode1 = createSchemaNode("string");
            objectnode1.put("type", "string");
            objectnode.put("items", objectnode1);
            return objectnode;
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((char[])obj, jsongenerator, serializerprovider);
        }

        public void serialize(char ac[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            if (serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS))
            {
                jsongenerator.writeStartArray();
                _writeArrayContents(jsongenerator, ac);
                jsongenerator.writeEndArray();
                return;
            } else
            {
                jsongenerator.writeString(ac, 0, ac.length);
                return;
            }
        }

        public volatile void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonProcessingException
        {
            serializeWithType((char[])obj, jsongenerator, serializerprovider, typeserializer);
        }

        public void serializeWithType(char ac[], JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonGenerationException
        {
            if (serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS))
            {
                typeserializer.writeTypePrefixForArray(ac, jsongenerator);
                _writeArrayContents(jsongenerator, ac);
                typeserializer.writeTypeSuffixForArray(ac, jsongenerator);
                return;
            } else
            {
                typeserializer.writeTypePrefixForScalar(ac, jsongenerator);
                jsongenerator.writeString(ac, 0, ac.length);
                typeserializer.writeTypeSuffixForScalar(ac, jsongenerator);
                return;
            }
        }

        public CharArraySerializer()
        {
            super([C);
        }
    }

    public static final class DoubleArraySerializer extends AsArraySerializer
    {

        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return this;
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            objectnode.put("items", createSchemaNode("number"));
            return objectnode;
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((double[])obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(double ad[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            int i = 0;
            for (int j = ad.length; i < j; i++)
            {
                jsongenerator.writeNumber(ad[i]);
            }

        }

        public DoubleArraySerializer()
        {
            super([D, null, null);
        }
    }

    public static final class FloatArraySerializer extends AsArraySerializer
    {

        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return new FloatArraySerializer(typeserializer);
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            objectnode.put("items", createSchemaNode("number"));
            return objectnode;
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((float[])obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(float af[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            int i = 0;
            for (int j = af.length; i < j; i++)
            {
                jsongenerator.writeNumber(af[i]);
            }

        }

        public FloatArraySerializer()
        {
            this(null);
        }

        public FloatArraySerializer(TypeSerializer typeserializer)
        {
            super([F, typeserializer, null);
        }
    }

    public static final class IntArraySerializer extends AsArraySerializer
    {

        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return this;
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            objectnode.put("items", createSchemaNode("integer"));
            return objectnode;
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((int[])obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(int ai[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            int i = 0;
            for (int j = ai.length; i < j; i++)
            {
                jsongenerator.writeNumber(ai[i]);
            }

        }

        public IntArraySerializer()
        {
            super([I, null, null);
        }
    }

    public static final class LongArraySerializer extends AsArraySerializer
    {

        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return new LongArraySerializer(typeserializer);
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            objectnode.put("items", createSchemaNode("number", true));
            return objectnode;
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((long[])obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(long al[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            int i = 0;
            for (int j = al.length; i < j; i++)
            {
                jsongenerator.writeNumber(al[i]);
            }

        }

        public LongArraySerializer()
        {
            this(null);
        }

        public LongArraySerializer(TypeSerializer typeserializer)
        {
            super([J, typeserializer, null);
        }
    }

    public static final class ShortArraySerializer extends AsArraySerializer
    {

        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return new ShortArraySerializer(typeserializer);
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            objectnode.put("items", createSchemaNode("integer"));
            return objectnode;
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((short[])obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(short aword0[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            int i = 0;
            for (int j = aword0.length; i < j; i++)
            {
                jsongenerator.writeNumber(aword0[i]);
            }

        }

        public ShortArraySerializer()
        {
            this(null);
        }

        public ShortArraySerializer(TypeSerializer typeserializer)
        {
            super([S, typeserializer, null);
        }
    }

    public static final class StringArraySerializer extends AsArraySerializer
        implements ResolvableSerializer
    {

        protected JsonSerializer _elementSerializer;

        private void serializeContentsSlow(String as[], JsonGenerator jsongenerator, SerializerProvider serializerprovider, JsonSerializer jsonserializer)
            throws IOException, JsonGenerationException
        {
            int i = 0;
            int j = as.length;
            while (i < j) 
            {
                if (as[i] == null)
                {
                    serializerprovider.defaultSerializeNull(jsongenerator);
                } else
                {
                    jsonserializer.serialize(as[i], jsongenerator, serializerprovider);
                }
                i++;
            }
        }

        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return this;
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            objectnode.put("items", createSchemaNode("string"));
            return objectnode;
        }

        public void resolve(SerializerProvider serializerprovider)
            throws JsonMappingException
        {
            JsonSerializer jsonserializer = serializerprovider.findValueSerializer(java/lang/String, _property);
            if (jsonserializer != null && jsonserializer.getClass().getAnnotation(org/codehaus/jackson/map/annotate/JacksonStdImpl) == null)
            {
                _elementSerializer = jsonserializer;
            }
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((String[])obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(String as[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            int i = as.length;
            if (i != 0)
            {
                if (_elementSerializer != null)
                {
                    serializeContentsSlow(as, jsongenerator, serializerprovider, _elementSerializer);
                    return;
                }
                int j = 0;
                while (j < i) 
                {
                    if (as[j] == null)
                    {
                        jsongenerator.writeNull();
                    } else
                    {
                        jsongenerator.writeString(as[j]);
                    }
                    j++;
                }
            }
        }

        public StringArraySerializer(BeanProperty beanproperty)
        {
            super([Ljava/lang/String;, null, beanproperty);
        }
    }


    private ArraySerializers()
    {
    }
}
