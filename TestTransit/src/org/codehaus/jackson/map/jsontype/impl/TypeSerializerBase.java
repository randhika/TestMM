// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.jsontype.impl;

import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;

public abstract class TypeSerializerBase extends TypeSerializer
{

    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;

    protected TypeSerializerBase(TypeIdResolver typeidresolver, BeanProperty beanproperty)
    {
        _idResolver = typeidresolver;
        _property = beanproperty;
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
}
