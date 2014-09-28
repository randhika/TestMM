// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.xc;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.SerializerBase;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;

public class XmlAdapterJsonSerializer extends SerializerBase
    implements SchemaAware
{

    private final BeanProperty _property;
    private final XmlAdapter xmlAdapter;

    public XmlAdapterJsonSerializer(XmlAdapter xmladapter, BeanProperty beanproperty)
    {
        super(java/lang/Object);
        xmlAdapter = xmladapter;
        _property = beanproperty;
    }

    private Class findValueClass()
    {
        Type type;
        for (type = xmlAdapter.getClass().getGenericSuperclass(); (type instanceof ParameterizedType) && javax/xml/bind/annotation/adapters/XmlAdapter != ((ParameterizedType)type).getRawType(); type = ((Class)((ParameterizedType)type).getRawType()).getGenericSuperclass()) { }
        return (Class)((ParameterizedType)type).getActualTypeArguments()[0];
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer = serializerprovider.findValueSerializer(findValueClass(), _property);
        if (jsonserializer instanceof SchemaAware)
        {
            return ((SchemaAware)jsonserializer).getSchema(serializerprovider, null);
        } else
        {
            return JsonSchema.getDefaultSchemaNode();
        }
    }

    public void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException
    {
        Object obj1;
        try
        {
            obj1 = xmlAdapter.marshal(obj);
        }
        catch (Exception exception)
        {
            throw new JsonMappingException((new StringBuilder()).append("Unable to marshal: ").append(exception.getMessage()).toString(), exception);
        }
        if (obj1 == null)
        {
            serializerprovider.getNullValueSerializer().serialize(null, jsongenerator, serializerprovider);
            return;
        } else
        {
            serializerprovider.findTypedValueSerializer(obj1.getClass(), true, _property).serialize(obj1, jsongenerator, serializerprovider);
            return;
        }
    }
}
