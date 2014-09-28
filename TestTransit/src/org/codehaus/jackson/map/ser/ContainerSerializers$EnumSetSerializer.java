// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Iterator;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            ContainerSerializers, ContainerSerializerBase

public static class <init> extends <init>
{

    public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
    {
        return this;
    }

    public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serializeContents((EnumSet)obj, jsongenerator, serializerprovider);
    }

    public void serializeContents(EnumSet enumset, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        JsonSerializer jsonserializer = _elementSerializer;
        Enum enum;
        for (Iterator iterator = enumset.iterator(); iterator.hasNext(); jsonserializer.serialize(enum, jsongenerator, serializerprovider))
        {
            enum = (Enum)iterator.next();
            if (jsonserializer == null)
            {
                jsonserializer = serializerprovider.findValueSerializer(enum.getDeclaringClass(), _property);
            }
        }

    }

    public I(JavaType javatype, BeanProperty beanproperty)
    {
        super(java/util/EnumSet, javatype, true, null, beanproperty);
    }
}
