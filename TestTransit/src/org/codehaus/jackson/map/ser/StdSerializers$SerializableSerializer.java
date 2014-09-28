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

// Referenced classes of package org.codehaus.jackson.map.ser:
//            SerializerBase, StdSerializers

public static final class  extends SerializerBase
{

    protected static final serialize instance = new <init>();

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


    private ()
    {
        super(org/codehaus/jackson/map/JsonSerializable);
    }
}
