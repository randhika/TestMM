// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class HierarchicType
{

    protected final Type _actualType;
    protected final ParameterizedType _genericType;
    protected final Class _rawClass;
    protected HierarchicType _subType;
    protected HierarchicType _superType;

    public HierarchicType(Type type)
    {
        _actualType = type;
        if (type instanceof Class)
        {
            _rawClass = (Class)type;
            _genericType = null;
            return;
        }
        if (type instanceof ParameterizedType)
        {
            _genericType = (ParameterizedType)type;
            _rawClass = (Class)_genericType.getRawType();
            return;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Type ").append(type.getClass().getName()).append(" can not be used to construct HierarchicType").toString());
        }
    }

    public ParameterizedType asGeneric()
    {
        return _genericType;
    }

    public Class getRawClass()
    {
        return _rawClass;
    }

    public HierarchicType getSubType()
    {
        return _subType;
    }

    public HierarchicType getSuperType()
    {
        return _superType;
    }

    public boolean isGeneric()
    {
        return _genericType != null;
    }

    public void setSubType(HierarchicType hierarchictype)
    {
        _subType = hierarchictype;
    }

    public void setSuperType(HierarchicType hierarchictype)
    {
        _superType = hierarchictype;
    }

    public String toString()
    {
        if (_genericType != null)
        {
            return _genericType.toString();
        } else
        {
            return _rawClass.getName();
        }
    }
}
