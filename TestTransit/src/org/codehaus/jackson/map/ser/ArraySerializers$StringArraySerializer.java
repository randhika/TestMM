// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.node.ObjectNode;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            ArraySerializers, ContainerSerializerBase

public static final class t> extends t>
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

    public I(BeanProperty beanproperty)
    {
        super([Ljava/lang/String;, null, beanproperty);
    }
}
