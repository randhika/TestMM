// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.jsontype.impl;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;

// Referenced classes of package org.codehaus.jackson.map.jsontype.impl:
//            TypeSerializerBase

public class AsWrapperTypeSerializer extends TypeSerializerBase
{

    public AsWrapperTypeSerializer(TypeIdResolver typeidresolver, BeanProperty beanproperty)
    {
        super(typeidresolver, beanproperty);
    }

    public org.codehaus.jackson.annotate.JsonTypeInfo.As getTypeInclusion()
    {
        return org.codehaus.jackson.annotate.JsonTypeInfo.As.WRAPPER_OBJECT;
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeStartObject();
        jsongenerator.writeArrayFieldStart(_idResolver.idFromValue(obj));
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsongenerator, Class class1)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeStartObject();
        jsongenerator.writeArrayFieldStart(_idResolver.idFromValueAndType(obj, class1));
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeStartObject();
        jsongenerator.writeObjectFieldStart(_idResolver.idFromValue(obj));
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsongenerator, Class class1)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeStartObject();
        jsongenerator.writeObjectFieldStart(_idResolver.idFromValueAndType(obj, class1));
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeStartObject();
        jsongenerator.writeFieldName(_idResolver.idFromValue(obj));
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsongenerator, Class class1)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeStartObject();
        jsongenerator.writeFieldName(_idResolver.idFromValueAndType(obj, class1));
    }

    public void writeTypeSuffixForArray(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeEndArray();
        jsongenerator.writeEndObject();
    }

    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeEndObject();
        jsongenerator.writeEndObject();
    }

    public void writeTypeSuffixForScalar(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeEndObject();
    }
}
