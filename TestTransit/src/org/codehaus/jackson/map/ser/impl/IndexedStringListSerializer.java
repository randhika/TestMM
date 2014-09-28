// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser.impl;

import java.io.IOException;
import java.util.List;
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

// Referenced classes of package org.codehaus.jackson.map.ser.impl:
//            StaticListSerializerBase

public final class IndexedStringListSerializer extends StaticListSerializerBase
    implements ResolvableSerializer
{

    protected JsonSerializer _serializer;

    public IndexedStringListSerializer(BeanProperty beanproperty)
    {
        super(java/util/List, beanproperty);
    }

    private final void serializeContents(List list, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        int i = 0;
        int j = list.size();
_L2:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_65;
        }
        String s = (String)list.get(i);
        if (s == null)
        {
            try
            {
                serializerprovider.defaultSerializeNull(jsongenerator);
                break MISSING_BLOCK_LABEL_66;
            }
            catch (Exception exception)
            {
                wrapAndThrow(serializerprovider, exception, list, i);
            }
            break MISSING_BLOCK_LABEL_65;
        }
        jsongenerator.writeString(s);
        break MISSING_BLOCK_LABEL_66;
        return;
        i++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private final void serializeUsingCustom(List list, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        int i = 0;
        int j;
        JsonSerializer jsonserializer;
        j = list.size();
        jsonserializer = _serializer;
        i = 0;
_L2:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        String s = (String)list.get(i);
        if (s == null)
        {
            try
            {
                serializerprovider.defaultSerializeNull(jsongenerator);
                break MISSING_BLOCK_LABEL_78;
            }
            catch (Exception exception)
            {
                wrapAndThrow(serializerprovider, exception, list, i);
            }
            break MISSING_BLOCK_LABEL_77;
        }
        jsonserializer.serialize(s, jsongenerator, serializerprovider);
        break MISSING_BLOCK_LABEL_78;
        return;
        i++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    protected JsonNode contentSchema()
    {
        return createSchemaNode("string", true);
    }

    public void resolve(SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = serializerprovider.findValueSerializer(java/lang/String, _property);
        if (!isDefaultSerializer(jsonserializer))
        {
            _serializer = jsonserializer;
        }
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((List)obj, jsongenerator, serializerprovider);
    }

    public void serialize(List list, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeStartArray();
        if (_serializer == null)
        {
            serializeContents(list, jsongenerator, serializerprovider);
        } else
        {
            serializeUsingCustom(list, jsongenerator, serializerprovider);
        }
        jsongenerator.writeEndArray();
    }

    public volatile void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        serializeWithType((List)obj, jsongenerator, serializerprovider, typeserializer);
    }

    public void serializeWithType(List list, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        typeserializer.writeTypePrefixForArray(list, jsongenerator);
        if (_serializer == null)
        {
            serializeContents(list, jsongenerator, serializerprovider);
        } else
        {
            serializeUsingCustom(list, jsongenerator, serializerprovider);
        }
        typeserializer.writeTypeSuffixForArray(list, jsongenerator);
    }
}
