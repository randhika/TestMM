// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
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
import org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            ContainerSerializerBase, ContainerSerializers

public static abstract class _dynamicSerializers extends ContainerSerializerBase
    implements ResolvableSerializer
{

    protected PropertySerializerMap _dynamicSerializers;
    protected JsonSerializer _elementSerializer;
    protected final JavaType _elementType;
    protected final BeanProperty _property;
    protected final boolean _staticTyping;
    protected final TypeSerializer _valueTypeSerializer;

    protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap propertyserializermap, Class class1, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        org.codehaus.jackson.map.ser.impl.esult esult = propertyserializermap.findAndAddSerializer(class1, serializerprovider, _property);
        if (propertyserializermap != esult.map)
        {
            _dynamicSerializers = esult.map;
        }
        return esult.serializer;
    }

    protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap propertyserializermap, JavaType javatype, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        org.codehaus.jackson.map.ser.impl.esult esult = propertyserializermap.findAndAddSerializer(javatype, serializerprovider, _property);
        if (propertyserializermap != esult.map)
        {
            _dynamicSerializers = esult.map;
        }
        return esult.serializer;
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        throws JsonMappingException
    {
        ObjectNode objectnode = createSchemaNode("array", true);
        JavaType javatype = null;
        if (type != null)
        {
            javatype = serializerprovider.constructType(type).getContentType();
            if (javatype == null && (type instanceof ParameterizedType))
            {
                Type atype[] = ((ParameterizedType)type).getActualTypeArguments();
                if (atype.length == 1)
                {
                    javatype = serializerprovider.constructType(atype[0]);
                }
            }
        }
        if (javatype == null && _elementType != null)
        {
            javatype = _elementType;
        }
        if (javatype != null)
        {
            Class class1 = javatype.getRawClass();
            JsonNode jsonnode = null;
            if (class1 != java/lang/Object)
            {
                JsonSerializer jsonserializer = serializerprovider.findValueSerializer(javatype, _property);
                boolean flag = jsonserializer instanceof SchemaAware;
                jsonnode = null;
                if (flag)
                {
                    jsonnode = ((SchemaAware)jsonserializer).getSchema(serializerprovider, null);
                }
            }
            if (jsonnode == null)
            {
                jsonnode = JsonSchema.getDefaultSchemaNode();
            }
            objectnode.put("items", jsonnode);
        }
        return objectnode;
    }

    public void resolve(SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        if (_staticTyping && _elementType != null && _elementSerializer == null)
        {
            _elementSerializer = serializerprovider.findValueSerializer(_elementType, _property);
        }
    }

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

    protected dMapResult(Class class1, JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty)
    {
        this(class1, javatype, flag, typeserializer, beanproperty, null);
    }

    protected <init>(Class class1, JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty, JsonSerializer jsonserializer)
    {
        boolean flag1;
label0:
        {
            super(class1, false);
            _elementType = javatype;
            if (!flag)
            {
                flag1 = false;
                if (javatype == null)
                {
                    break label0;
                }
                boolean flag2 = javatype.isFinal();
                flag1 = false;
                if (!flag2)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        _staticTyping = flag1;
        _valueTypeSerializer = typeserializer;
        _property = beanproperty;
        _elementSerializer = jsonserializer;
        _dynamicSerializers = PropertySerializerMap.emptyMap();
    }
}
