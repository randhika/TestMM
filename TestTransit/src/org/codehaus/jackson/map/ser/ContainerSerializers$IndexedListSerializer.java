// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            ContainerSerializers, ContainerSerializerBase

public static class t> extends t>
{

    public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
    {
        return new <init>(_elementType, _staticTyping, typeserializer, _property, _elementSerializer);
    }

    public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serializeContents((List)obj, jsongenerator, serializerprovider);
    }

    public void serializeContents(List list, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if (_elementSerializer == null) goto _L2; else goto _L1
_L1:
        serializeContentsUsing(list, jsongenerator, serializerprovider, _elementSerializer);
_L9:
        return;
_L2:
        if (_valueTypeSerializer != null)
        {
            serializeTypedContents(list, jsongenerator, serializerprovider);
            return;
        }
        int i = list.size();
        if (i == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        int j = 0;
        PropertySerializerMap propertyserializermap;
        Object obj;
        Class class1;
        JsonSerializer jsonserializer;
        try
        {
            propertyserializermap = _dynamicSerializers;
        }
        catch (Exception exception)
        {
            wrapAndThrow(serializerprovider, exception, list, j);
            return;
        }
        JsonSerializer jsonserializer1;
        for (; j >= i; j++)
        {
            continue; /* Loop/switch isn't completed */
        }

        obj = list.get(j);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_86;
        }
        serializerprovider.defaultSerializeNull(jsongenerator);
        break MISSING_BLOCK_LABEL_184;
        class1 = obj.getClass();
        jsonserializer = propertyserializermap.serializerFor(class1);
        if (jsonserializer != null) goto _L4; else goto _L3
_L3:
        if (!_elementType.hasGenericTypes()) goto _L6; else goto _L5
_L5:
        jsonserializer = _findAndAddDynamic(propertyserializermap, _elementType.forcedNarrowBy(class1), serializerprovider);
_L7:
        propertyserializermap = _dynamicSerializers;
_L4:
        jsonserializer.serialize(obj, jsongenerator, serializerprovider);
        break MISSING_BLOCK_LABEL_184;
_L6:
        jsonserializer1 = _findAndAddDynamic(propertyserializermap, class1, serializerprovider);
        jsonserializer = jsonserializer1;
          goto _L7
        if (true) goto _L9; else goto _L8
_L8:
    }

    public void serializeContentsUsing(List list, JsonGenerator jsongenerator, SerializerProvider serializerprovider, JsonSerializer jsonserializer)
        throws IOException, JsonGenerationException
    {
        int i = list.size();
        if (i != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        TypeSerializer typeserializer;
        int j;
        typeserializer = _valueTypeSerializer;
        j = 0;
_L4:
        Object obj;
        if (j >= i)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = list.get(j);
        if (obj == null)
        {
            try
            {
                serializerprovider.defaultSerializeNull(jsongenerator);
            }
            catch (Exception exception)
            {
                wrapAndThrow(serializerprovider, exception, list, j);
            }
            break MISSING_BLOCK_LABEL_96;
        }
        if (typeserializer != null)
        {
            break MISSING_BLOCK_LABEL_85;
        }
        jsonserializer.serialize(obj, jsongenerator, serializerprovider);
        break MISSING_BLOCK_LABEL_96;
        jsonserializer.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
        j++;
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L1; else goto _L5
_L5:
    }

    public void serializeTypedContents(List list, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        int i = list.size();
        if (i != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j = 0;
        TypeSerializer typeserializer;
        PropertySerializerMap propertyserializermap;
        Object obj;
        Class class1;
        JsonSerializer jsonserializer;
        try
        {
            typeserializer = _valueTypeSerializer;
            propertyserializermap = _dynamicSerializers;
        }
        catch (Exception exception)
        {
            wrapAndThrow(serializerprovider, exception, list, j);
            return;
        }
        JsonSerializer jsonserializer1;
        for (; j >= i; j++)
        {
            continue; /* Loop/switch isn't completed */
        }

        obj = list.get(j);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_59;
        }
        serializerprovider.defaultSerializeNull(jsongenerator);
        break MISSING_BLOCK_LABEL_159;
        class1 = obj.getClass();
        jsonserializer = propertyserializermap.serializerFor(class1);
        if (jsonserializer != null) goto _L4; else goto _L3
_L3:
        if (!_elementType.hasGenericTypes()) goto _L6; else goto _L5
_L5:
        jsonserializer = _findAndAddDynamic(propertyserializermap, _elementType.forcedNarrowBy(class1), serializerprovider);
_L7:
        propertyserializermap = _dynamicSerializers;
_L4:
        jsonserializer.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
        break MISSING_BLOCK_LABEL_159;
_L6:
        jsonserializer1 = _findAndAddDynamic(propertyserializermap, class1, serializerprovider);
        jsonserializer = jsonserializer1;
          goto _L7
        if (true) goto _L1; else goto _L8
_L8:
    }

    public (JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty, JsonSerializer jsonserializer)
    {
        super(java/util/List, javatype, flag, typeserializer, beanproperty, jsonserializer);
    }
}
