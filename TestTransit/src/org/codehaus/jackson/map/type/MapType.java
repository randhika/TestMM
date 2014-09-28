// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.type;

import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.type:
//            MapLikeType

public final class MapType extends MapLikeType
{

    private MapType(Class class1, JavaType javatype, JavaType javatype1)
    {
        super(class1, javatype, javatype1);
    }

    public static MapType construct(Class class1, JavaType javatype, JavaType javatype1)
    {
        return new MapType(class1, javatype, javatype1);
    }

    protected JavaType _narrow(Class class1)
    {
        return new MapType(class1, _keyType, _valueType);
    }

    public JavaType narrowContentsBy(Class class1)
    {
        if (class1 == _valueType.getRawClass())
        {
            return this;
        } else
        {
            return (new MapType(_class, _keyType, _valueType.narrowBy(class1))).copyHandlers(this);
        }
    }

    public JavaType narrowKey(Class class1)
    {
        if (class1 == _keyType.getRawClass())
        {
            return this;
        } else
        {
            return (new MapType(_class, _keyType.narrowBy(class1), _valueType)).copyHandlers(this);
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("[map type; class ").append(_class.getName()).append(", ").append(_keyType).append(" -> ").append(_valueType).append("]").toString();
    }

    public JavaType widenContentsBy(Class class1)
    {
        if (class1 == _valueType.getRawClass())
        {
            return this;
        } else
        {
            return (new MapType(_class, _keyType, _valueType.widenBy(class1))).copyHandlers(this);
        }
    }

    public JavaType widenKey(Class class1)
    {
        if (class1 == _keyType.getRawClass())
        {
            return this;
        } else
        {
            return (new MapType(_class, _keyType.widenBy(class1), _valueType)).copyHandlers(this);
        }
    }

    public volatile MapLikeType withContentTypeHandler(Object obj)
    {
        return withContentTypeHandler(obj);
    }

    public MapType withContentTypeHandler(Object obj)
    {
        return new MapType(_class, _keyType, _valueType.withTypeHandler(obj));
    }

    public volatile JavaType withContentTypeHandler(Object obj)
    {
        return withContentTypeHandler(obj);
    }

    public volatile MapLikeType withTypeHandler(Object obj)
    {
        return withTypeHandler(obj);
    }

    public MapType withTypeHandler(Object obj)
    {
        MapType maptype = new MapType(_class, _keyType, _valueType);
        maptype._typeHandler = obj;
        return maptype;
    }

    public volatile JavaType withTypeHandler(Object obj)
    {
        return withTypeHandler(obj);
    }
}
