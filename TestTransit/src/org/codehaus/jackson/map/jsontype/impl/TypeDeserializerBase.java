// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.jsontype.impl;

import java.io.IOException;
import java.util.HashMap;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.type.JavaType;

public abstract class TypeDeserializerBase extends TypeDeserializer
{

    protected final JavaType _baseType;
    protected final HashMap _deserializers = new HashMap();
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;

    protected TypeDeserializerBase(JavaType javatype, TypeIdResolver typeidresolver, BeanProperty beanproperty)
    {
        _baseType = javatype;
        _idResolver = typeidresolver;
        _property = beanproperty;
    }

    protected final JsonDeserializer _findDeserializer(DeserializationContext deserializationcontext, String s)
        throws IOException, JsonProcessingException
    {
        HashMap hashmap = _deserializers;
        hashmap;
        JVM INSTR monitorenter ;
        JsonDeserializer jsondeserializer = (JsonDeserializer)_deserializers.get(s);
        if (jsondeserializer != null)
        {
            break MISSING_BLOCK_LABEL_125;
        }
        JavaType javatype = _idResolver.typeFromId(s);
        if (javatype != null)
        {
            break MISSING_BLOCK_LABEL_59;
        }
        throw deserializationcontext.unknownTypeException(_baseType, s);
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        if (_baseType != null && _baseType.getClass() == javatype.getClass())
        {
            javatype = _baseType.narrowBy(javatype.getRawClass());
        }
        jsondeserializer = deserializationcontext.getDeserializerProvider().findValueDeserializer(deserializationcontext.getConfig(), javatype, _property);
        _deserializers.put(s, jsondeserializer);
        hashmap;
        JVM INSTR monitorexit ;
        return jsondeserializer;
    }

    public String baseTypeName()
    {
        return _baseType.getRawClass().getName();
    }

    public String getPropertyName()
    {
        return null;
    }

    public TypeIdResolver getTypeIdResolver()
    {
        return _idResolver;
    }

    public abstract org.codehaus.jackson.annotate.JsonTypeInfo.As getTypeInclusion();

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('[').append(getClass().getName());
        stringbuilder.append("; base-type:").append(_baseType);
        stringbuilder.append("; id-resolver: ").append(_idResolver);
        stringbuilder.append(']');
        return stringbuilder.toString();
    }
}
