// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import org.codehaus.jackson.map.ser.ContainerSerializerBase;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.ser.impl:
//            PropertySerializerMap

public class ObjectArraySerializer extends org.codehaus.jackson.map.ser.ArraySerializers.AsArraySerializer
    implements ResolvableSerializer
{

    protected PropertySerializerMap _dynamicSerializers;
    protected JsonSerializer _elementSerializer;
    protected final JavaType _elementType;
    protected final boolean _staticTyping;

    public ObjectArraySerializer(JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty)
    {
        this(javatype, flag, typeserializer, beanproperty, null);
    }

    public ObjectArraySerializer(JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty, JsonSerializer jsonserializer)
    {
        super([Ljava/lang/Object;, typeserializer, beanproperty);
        _elementType = javatype;
        _staticTyping = flag;
        _dynamicSerializers = PropertySerializerMap.emptyMap();
        _elementSerializer = jsonserializer;
    }

    protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap propertyserializermap, Class class1, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        PropertySerializerMap.SerializerAndMapResult serializerandmapresult = propertyserializermap.findAndAddSerializer(class1, serializerprovider, _property);
        if (propertyserializermap != serializerandmapresult.map)
        {
            _dynamicSerializers = serializerandmapresult.map;
        }
        return serializerandmapresult.serializer;
    }

    protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap propertyserializermap, JavaType javatype, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        PropertySerializerMap.SerializerAndMapResult serializerandmapresult = propertyserializermap.findAndAddSerializer(javatype, serializerprovider, _property);
        if (propertyserializermap != serializerandmapresult.map)
        {
            _dynamicSerializers = serializerandmapresult.map;
        }
        return serializerandmapresult.serializer;
    }

    public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
    {
        return new ObjectArraySerializer(_elementType, _staticTyping, typeserializer, _property, _elementSerializer);
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        throws JsonMappingException
    {
        ObjectNode objectnode;
        Class class1;
label0:
        {
            objectnode = createSchemaNode("array", true);
            if (type != null)
            {
                JavaType javatype = serializerprovider.constructType(type);
                if (javatype.isArrayType())
                {
                    class1 = ((ArrayType)javatype).getContentType().getRawClass();
                    if (class1 != java/lang/Object)
                    {
                        break label0;
                    }
                    objectnode.put("items", JsonSchema.getDefaultSchemaNode());
                }
            }
            return objectnode;
        }
        JsonSerializer jsonserializer = serializerprovider.findValueSerializer(class1, _property);
        JsonNode jsonnode;
        if (jsonserializer instanceof SchemaAware)
        {
            jsonnode = ((SchemaAware)jsonserializer).getSchema(serializerprovider, null);
        } else
        {
            jsonnode = JsonSchema.getDefaultSchemaNode();
        }
        objectnode.put("items", jsonnode);
        return objectnode;
    }

    public void resolve(SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        if (_staticTyping && _elementSerializer == null)
        {
            _elementSerializer = serializerprovider.findValueSerializer(_elementType, _property);
        }
    }

    public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serializeContents((Object[])obj, jsongenerator, serializerprovider);
    }

    public void serializeContents(Object aobj[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        int i = aobj.length;
        if (i != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j;
        if (_elementSerializer != null)
        {
            serializeContentsUsing(aobj, jsongenerator, serializerprovider, _elementSerializer);
            return;
        }
        if (_valueTypeSerializer != null)
        {
            serializeTypedContents(aobj, jsongenerator, serializerprovider);
            return;
        }
        j = 0;
        Object obj = null;
        PropertySerializerMap propertyserializermap;
        Class class1;
        JsonSerializer jsonserializer;
        JsonSerializer jsonserializer1;
        try
        {
            propertyserializermap = _dynamicSerializers;
        }
        catch (IOException ioexception)
        {
            throw ioexception;
        }
        catch (Exception exception)
        {
            Object obj1;
            for (obj1 = exception; (obj1 instanceof InvocationTargetException) && ((Throwable) (obj1)).getCause() != null; obj1 = ((Throwable) (obj1)).getCause()) { }
            if (obj1 instanceof Error)
            {
                throw (Error)obj1;
            } else
            {
                throw JsonMappingException.wrapWithPath(((Throwable) (obj1)), obj, j);
            }
        }
_L9:
        if (j >= i)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = aobj[j];
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_82;
        }
        serializerprovider.defaultSerializeNull(jsongenerator);
        break MISSING_BLOCK_LABEL_222;
        class1 = obj.getClass();
        jsonserializer = propertyserializermap.serializerFor(class1);
        if (jsonserializer != null) goto _L4; else goto _L3
_L3:
        if (!_elementType.hasGenericTypes()) goto _L6; else goto _L5
_L5:
        jsonserializer = _findAndAddDynamic(propertyserializermap, _elementType.forcedNarrowBy(class1), serializerprovider);
_L4:
        jsonserializer.serialize(obj, jsongenerator, serializerprovider);
        break MISSING_BLOCK_LABEL_222;
_L6:
        jsonserializer1 = _findAndAddDynamic(propertyserializermap, class1, serializerprovider);
        jsonserializer = jsonserializer1;
        if (true) goto _L4; else goto _L7
_L7:
        j++;
        if (true) goto _L9; else goto _L8
_L8:
        if (true) goto _L1; else goto _L10
_L10:
    }

    public void serializeContentsUsing(Object aobj[], JsonGenerator jsongenerator, SerializerProvider serializerprovider, JsonSerializer jsonserializer)
        throws IOException, JsonGenerationException
    {
        int i;
        TypeSerializer typeserializer;
        int j;
        Object obj;
        i = aobj.length;
        typeserializer = _valueTypeSerializer;
        j = 0;
        obj = null;
_L2:
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_134;
        }
        try
        {
            obj = aobj[j];
        }
        catch (IOException ioexception)
        {
            throw ioexception;
        }
        catch (Exception exception)
        {
            Object obj1;
            for (obj1 = exception; (obj1 instanceof InvocationTargetException) && ((Throwable) (obj1)).getCause() != null; obj1 = ((Throwable) (obj1)).getCause()) { }
            if (obj1 instanceof Error)
            {
                throw (Error)obj1;
            } else
            {
                throw JsonMappingException.wrapWithPath(((Throwable) (obj1)), obj, j);
            }
        }
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        serializerprovider.defaultSerializeNull(jsongenerator);
        break MISSING_BLOCK_LABEL_135;
        if (typeserializer != null)
        {
            break MISSING_BLOCK_LABEL_64;
        }
        jsonserializer.serialize(obj, jsongenerator, serializerprovider);
        break MISSING_BLOCK_LABEL_135;
        jsonserializer.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
        break MISSING_BLOCK_LABEL_135;
        return;
        j++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void serializeTypedContents(Object aobj[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        int j;
        int i = aobj.length;
        TypeSerializer typeserializer = _valueTypeSerializer;
        j = 0;
        Object obj = null;
        PropertySerializerMap propertyserializermap;
        Class class1;
        JsonSerializer jsonserializer;
        try
        {
            propertyserializermap = _dynamicSerializers;
        }
        catch (IOException ioexception)
        {
            throw ioexception;
        }
        catch (Exception exception)
        {
            Object obj1;
            for (obj1 = exception; (obj1 instanceof InvocationTargetException) && ((Throwable) (obj1)).getCause() != null; obj1 = ((Throwable) (obj1)).getCause()) { }
            if (obj1 instanceof Error)
            {
                throw (Error)obj1;
            } else
            {
                throw JsonMappingException.wrapWithPath(((Throwable) (obj1)), obj, j);
            }
        }
_L2:
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_155;
        }
        obj = aobj[j];
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        serializerprovider.defaultSerializeNull(jsongenerator);
        break MISSING_BLOCK_LABEL_156;
        class1 = obj.getClass();
        jsonserializer = propertyserializermap.serializerFor(class1);
        if (jsonserializer != null)
        {
            break MISSING_BLOCK_LABEL_80;
        }
        jsonserializer = _findAndAddDynamic(propertyserializermap, class1, serializerprovider);
        jsonserializer.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
        break MISSING_BLOCK_LABEL_156;
        return;
        j++;
        if (true) goto _L2; else goto _L1
_L1:
    }
}
