// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.type;

import java.util.Collection;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.type:
//            TypeBase

public class CollectionLikeType extends TypeBase
{

    protected final JavaType _elementType;

    protected CollectionLikeType(Class class1, JavaType javatype)
    {
        super(class1, javatype.hashCode());
        _elementType = javatype;
    }

    public static CollectionLikeType construct(Class class1, JavaType javatype)
    {
        return new CollectionLikeType(class1, javatype);
    }

    protected JavaType _narrow(Class class1)
    {
        return new CollectionLikeType(class1, _elementType);
    }

    protected String buildCanonicalName()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(_class.getName());
        if (_elementType != null)
        {
            stringbuilder.append('<');
            stringbuilder.append(_elementType.toCanonical());
            stringbuilder.append('>');
        }
        return stringbuilder.toString();
    }

    public JavaType containedType(int i)
    {
        if (i == 0)
        {
            return _elementType;
        } else
        {
            return null;
        }
    }

    public int containedTypeCount()
    {
        return 1;
    }

    public String containedTypeName(int i)
    {
        if (i == 0)
        {
            return "E";
        } else
        {
            return null;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj == null)
            {
                return false;
            }
            if (obj.getClass() != getClass())
            {
                return false;
            }
            CollectionLikeType collectionliketype = (CollectionLikeType)obj;
            if (_class != collectionliketype._class || !_elementType.equals(collectionliketype._elementType))
            {
                return false;
            }
        }
        return true;
    }

    public JavaType getContentType()
    {
        return _elementType;
    }

    public StringBuilder getErasedSignature(StringBuilder stringbuilder)
    {
        return _classSignature(_class, stringbuilder, true);
    }

    public StringBuilder getGenericSignature(StringBuilder stringbuilder)
    {
        _classSignature(_class, stringbuilder, false);
        stringbuilder.append('<');
        _elementType.getGenericSignature(stringbuilder);
        stringbuilder.append(">;");
        return stringbuilder;
    }

    public boolean isCollectionLikeType()
    {
        return true;
    }

    public boolean isContainerType()
    {
        return true;
    }

    public boolean isTrueCollectionType()
    {
        return java/util/Collection.isAssignableFrom(_class);
    }

    public JavaType narrowContentsBy(Class class1)
    {
        if (class1 == _elementType.getRawClass())
        {
            return this;
        } else
        {
            return (new CollectionLikeType(_class, _elementType.narrowBy(class1))).copyHandlers(this);
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("[collection-like type; class ").append(_class.getName()).append(", contains ").append(_elementType).append("]").toString();
    }

    public JavaType widenContentsBy(Class class1)
    {
        if (class1 == _elementType.getRawClass())
        {
            return this;
        } else
        {
            return (new CollectionLikeType(_class, _elementType.widenBy(class1))).copyHandlers(this);
        }
    }

    public CollectionLikeType withContentTypeHandler(Object obj)
    {
        return new CollectionLikeType(_class, _elementType.withTypeHandler(obj));
    }

    public volatile JavaType withContentTypeHandler(Object obj)
    {
        return withContentTypeHandler(obj);
    }

    public CollectionLikeType withTypeHandler(Object obj)
    {
        CollectionLikeType collectionliketype = new CollectionLikeType(_class, _elementType);
        collectionliketype._typeHandler = obj;
        return collectionliketype;
    }

    public volatile JavaType withTypeHandler(Object obj)
    {
        return withTypeHandler(obj);
    }
}
