// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
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

public static class it> extends it>
{

    public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
    {
        return new <init>(_elementType, _staticTyping, typeserializer, _property);
    }

    public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serializeContents((Collection)obj, jsongenerator, serializerprovider);
    }

    public void serializeContents(Collection collection, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if (_elementSerializer == null) goto _L2; else goto _L1
_L1:
        serializeContentsUsing(collection, jsongenerator, serializerprovider, _elementSerializer);
_L4:
        return;
_L2:
        Iterator iterator = collection.iterator();
        if (!iterator.hasNext()) goto _L4; else goto _L3
_L3:
        PropertySerializerMap propertyserializermap;
        TypeSerializer typeserializer;
        int i;
        propertyserializermap = _dynamicSerializers;
        typeserializer = _valueTypeSerializer;
        i = 0;
_L8:
        Object obj;
        Class class1;
        JsonSerializer jsonserializer;
        try
        {
            obj = iterator.next();
        }
        catch (Exception exception)
        {
            wrapAndThrow(serializerprovider, exception, collection, i);
            return;
        }
        if (obj != null) goto _L6; else goto _L5
_L5:
        serializerprovider.defaultSerializeNull(jsongenerator);
_L11:
        i++;
        if (iterator.hasNext()) goto _L8; else goto _L7
_L7:
        return;
_L6:
        class1 = obj.getClass();
        jsonserializer = propertyserializermap.serializerFor(class1);
        if (jsonserializer != null) goto _L10; else goto _L9
_L9:
        if (!_elementType.hasGenericTypes())
        {
            break MISSING_BLOCK_LABEL_170;
        }
        jsonserializer = _findAndAddDynamic(propertyserializermap, _elementType.forcedNarrowBy(class1), serializerprovider);
_L12:
        propertyserializermap = _dynamicSerializers;
_L10:
        if (typeserializer != null)
        {
            break MISSING_BLOCK_LABEL_184;
        }
        jsonserializer.serialize(obj, jsongenerator, serializerprovider);
          goto _L11
        jsonserializer = _findAndAddDynamic(propertyserializermap, class1, serializerprovider);
          goto _L12
        jsonserializer.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
          goto _L11
    }

    public void serializeContentsUsing(Collection collection, JsonGenerator jsongenerator, SerializerProvider serializerprovider, JsonSerializer jsonserializer)
        throws IOException, JsonGenerationException
    {
        Iterator iterator = collection.iterator();
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        TypeSerializer typeserializer;
        int i;
        typeserializer = _valueTypeSerializer;
        i = 0;
_L5:
        Object obj = iterator.next();
        if (obj != null) goto _L4; else goto _L3
_L3:
        serializerprovider.defaultSerializeNull(jsongenerator);
_L6:
        i++;
_L7:
        if (iterator.hasNext()) goto _L5; else goto _L2
_L2:
        return;
_L4:
        if (typeserializer != null)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        jsonserializer.serialize(obj, jsongenerator, serializerprovider);
          goto _L6
        Exception exception;
        exception;
        wrapAndThrow(serializerprovider, exception, collection, i);
          goto _L7
        jsonserializer.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
          goto _L6
    }

    public (JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty)
    {
        this(javatype, flag, typeserializer, beanproperty, null);
    }

    public <init>(JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty, JsonSerializer jsonserializer)
    {
        super(java/util/Collection, javatype, flag, typeserializer, beanproperty, jsonserializer);
    }
}
