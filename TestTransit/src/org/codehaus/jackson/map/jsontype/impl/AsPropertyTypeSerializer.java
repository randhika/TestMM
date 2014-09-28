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
//            AsArrayTypeSerializer

public class AsPropertyTypeSerializer extends AsArrayTypeSerializer
{

    protected final String _typePropertyName;

    public AsPropertyTypeSerializer(TypeIdResolver typeidresolver, BeanProperty beanproperty, String s)
    {
        super(typeidresolver, beanproperty);
        _typePropertyName = s;
    }

    public String getPropertyName()
    {
        return _typePropertyName;
    }

    public org.codehaus.jackson.annotate.JsonTypeInfo.As getTypeInclusion()
    {
        return org.codehaus.jackson.annotate.JsonTypeInfo.As.PROPERTY;
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeStartObject();
        jsongenerator.writeStringField(_typePropertyName, _idResolver.idFromValue(obj));
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsongenerator, Class class1)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeStartObject();
        jsongenerator.writeStringField(_typePropertyName, _idResolver.idFromValueAndType(obj, class1));
    }

    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsongenerator)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeEndObject();
    }
}
