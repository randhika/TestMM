// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Set;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.util.EnumValues;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            ContainerSerializerBase, SerializerBase, EnumSerializer

public class EnumMapSerializer extends ContainerSerializerBase
    implements ResolvableSerializer
{

    protected final EnumValues _keyEnums;
    protected final BeanProperty _property;
    protected final boolean _staticTyping;
    protected JsonSerializer _valueSerializer;
    protected final JavaType _valueType;
    protected final TypeSerializer _valueTypeSerializer;

    public EnumMapSerializer(JavaType javatype, boolean flag, EnumValues enumvalues, TypeSerializer typeserializer, BeanProperty beanproperty)
    {
        this(javatype, flag, enumvalues, typeserializer, beanproperty, null);
    }

    public EnumMapSerializer(JavaType javatype, boolean flag, EnumValues enumvalues, TypeSerializer typeserializer, BeanProperty beanproperty, JsonSerializer jsonserializer)
    {
        boolean flag1;
label0:
        {
            super(java/util/EnumMap, false);
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
        _valueType = javatype;
        _keyEnums = enumvalues;
        _valueTypeSerializer = typeserializer;
        _property = beanproperty;
        _valueSerializer = jsonserializer;
    }

    public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
    {
        return new EnumMapSerializer(_valueType, _staticTyping, _keyEnums, typeserializer, _property);
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        throws JsonMappingException
    {
        ObjectNode objectnode = createSchemaNode("object", true);
        if (type instanceof ParameterizedType)
        {
            Type atype[] = ((ParameterizedType)type).getActualTypeArguments();
            if (atype.length == 2)
            {
                JavaType javatype = serializerprovider.constructType(atype[0]);
                JavaType javatype1 = serializerprovider.constructType(atype[1]);
                ObjectNode objectnode1 = JsonNodeFactory.instance.objectNode();
                Enum aenum[] = (Enum[])javatype.getRawClass().getEnumConstants();
                int i = aenum.length;
                int j = 0;
                while (j < i) 
                {
                    Enum enum = aenum[j];
                    JsonSerializer jsonserializer = serializerprovider.findValueSerializer(javatype1.getRawClass(), _property);
                    JsonNode jsonnode;
                    if (jsonserializer instanceof SchemaAware)
                    {
                        jsonnode = ((SchemaAware)jsonserializer).getSchema(serializerprovider, null);
                    } else
                    {
                        jsonnode = JsonSchema.getDefaultSchemaNode();
                    }
                    objectnode1.put(serializerprovider.getConfig().getAnnotationIntrospector().findEnumValue(enum), jsonnode);
                    j++;
                }
                objectnode.put("properties", objectnode1);
            }
        }
        return objectnode;
    }

    public void resolve(SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        if (_staticTyping && _valueSerializer == null)
        {
            _valueSerializer = serializerprovider.findValueSerializer(_valueType, _property);
        }
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((EnumMap)obj, jsongenerator, serializerprovider);
    }

    public void serialize(EnumMap enummap, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeStartObject();
        if (!enummap.isEmpty())
        {
            serializeContents(enummap, jsongenerator, serializerprovider);
        }
        jsongenerator.writeEndObject();
    }

    protected void serializeContents(EnumMap enummap, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if (_valueSerializer != null)
        {
            serializeContentsUsing(enummap, jsongenerator, serializerprovider, _valueSerializer);
        } else
        {
            JsonSerializer jsonserializer = null;
            Class class1 = null;
            EnumValues enumvalues = _keyEnums;
            Iterator iterator = enummap.entrySet().iterator();
            while (iterator.hasNext()) 
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                Enum enum = (Enum)entry.getKey();
                if (enumvalues == null)
                {
                    enumvalues = ((EnumSerializer)(SerializerBase)serializerprovider.findValueSerializer(enum.getDeclaringClass(), _property)).getEnumValues();
                }
                jsongenerator.writeFieldName(enumvalues.serializedValueFor(enum));
                Object obj = entry.getValue();
                if (obj == null)
                {
                    serializerprovider.defaultSerializeNull(jsongenerator);
                } else
                {
                    Class class2 = obj.getClass();
                    JsonSerializer jsonserializer1;
                    if (class2 == class1)
                    {
                        jsonserializer1 = jsonserializer;
                    } else
                    {
                        jsonserializer1 = serializerprovider.findValueSerializer(class2, _property);
                        jsonserializer = jsonserializer1;
                        class1 = class2;
                    }
                    try
                    {
                        jsonserializer1.serialize(obj, jsongenerator, serializerprovider);
                    }
                    catch (Exception exception)
                    {
                        wrapAndThrow(serializerprovider, exception, enummap, ((Enum)entry.getKey()).name());
                    }
                }
            }
        }
    }

    protected void serializeContentsUsing(EnumMap enummap, JsonGenerator jsongenerator, SerializerProvider serializerprovider, JsonSerializer jsonserializer)
        throws IOException, JsonGenerationException
    {
        EnumValues enumvalues = _keyEnums;
        for (Iterator iterator = enummap.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            Enum enum = (Enum)entry.getKey();
            if (enumvalues == null)
            {
                enumvalues = ((EnumSerializer)(SerializerBase)serializerprovider.findValueSerializer(enum.getDeclaringClass(), _property)).getEnumValues();
            }
            jsongenerator.writeFieldName(enumvalues.serializedValueFor(enum));
            Object obj = entry.getValue();
            if (obj == null)
            {
                serializerprovider.defaultSerializeNull(jsongenerator);
            } else
            {
                try
                {
                    jsonserializer.serialize(obj, jsongenerator, serializerprovider);
                }
                catch (Exception exception)
                {
                    wrapAndThrow(serializerprovider, exception, enummap, ((Enum)entry.getKey()).name());
                }
            }
        }

    }

    public volatile void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        serializeWithType((EnumMap)obj, jsongenerator, serializerprovider, typeserializer);
    }

    public void serializeWithType(EnumMap enummap, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        typeserializer.writeTypePrefixForObject(enummap, jsongenerator);
        if (!enummap.isEmpty())
        {
            serializeContents(enummap, jsongenerator, serializerprovider);
        }
        typeserializer.writeTypeSuffixForObject(enummap, jsongenerator);
    }
}
