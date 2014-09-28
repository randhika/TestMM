// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.type;

import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.type:
//            CollectionLikeType

public final class CollectionType extends CollectionLikeType
{

    private CollectionType(Class class1, JavaType javatype)
    {
        super(class1, javatype);
    }

    public static CollectionType construct(Class class1, JavaType javatype)
    {
        return new CollectionType(class1, javatype);
    }

    protected JavaType _narrow(Class class1)
    {
        return new CollectionType(class1, _elementType);
    }

    public JavaType narrowContentsBy(Class class1)
    {
        if (class1 == _elementType.getRawClass())
        {
            return this;
        } else
        {
            return (new CollectionType(_class, _elementType.narrowBy(class1))).copyHandlers(this);
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("[collection type; class ").append(_class.getName()).append(", contains ").append(_elementType).append("]").toString();
    }

    public JavaType widenContentsBy(Class class1)
    {
        if (class1 == _elementType.getRawClass())
        {
            return this;
        } else
        {
            return (new CollectionType(_class, _elementType.widenBy(class1))).copyHandlers(this);
        }
    }

    public volatile CollectionLikeType withContentTypeHandler(Object obj)
    {
        return withContentTypeHandler(obj);
    }

    public CollectionType withContentTypeHandler(Object obj)
    {
        return new CollectionType(_class, _elementType.withTypeHandler(obj));
    }

    public volatile JavaType withContentTypeHandler(Object obj)
    {
        return withContentTypeHandler(obj);
    }

    public volatile CollectionLikeType withTypeHandler(Object obj)
    {
        return withTypeHandler(obj);
    }

    public CollectionType withTypeHandler(Object obj)
    {
        CollectionType collectiontype = new CollectionType(_class, _elementType);
        collectiontype._typeHandler = obj;
        return collectiontype;
    }

    public volatile JavaType withTypeHandler(Object obj)
    {
        return withTypeHandler(obj);
    }
}
