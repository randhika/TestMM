// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.type;

import java.util.Map;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.type:
//            TypeBase

public class MapLikeType extends TypeBase
{

    protected final JavaType _keyType;
    protected final JavaType _valueType;

    protected MapLikeType(Class class1, JavaType javatype, JavaType javatype1)
    {
        super(class1, javatype.hashCode() ^ javatype1.hashCode());
        _keyType = javatype;
        _valueType = javatype1;
    }

    public static MapLikeType construct(Class class1, JavaType javatype, JavaType javatype1)
    {
        return new MapLikeType(class1, javatype, javatype1);
    }

    protected JavaType _narrow(Class class1)
    {
        return new MapLikeType(class1, _keyType, _valueType);
    }

    protected String buildCanonicalName()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(_class.getName());
        if (_keyType != null)
        {
            stringbuilder.append('<');
            stringbuilder.append(_keyType.toCanonical());
            stringbuilder.append(',');
            stringbuilder.append(_valueType.toCanonical());
            stringbuilder.append('>');
        }
        return stringbuilder.toString();
    }

    public JavaType containedType(int i)
    {
        if (i == 0)
        {
            return _keyType;
        }
        if (i == 1)
        {
            return _valueType;
        } else
        {
            return null;
        }
    }

    public int containedTypeCount()
    {
        return 2;
    }

    public String containedTypeName(int i)
    {
        if (i == 0)
        {
            return "K";
        }
        if (i == 1)
        {
            return "V";
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
            MapLikeType mapliketype = (MapLikeType)obj;
            if (_class != mapliketype._class || !_keyType.equals(mapliketype._keyType) || !_valueType.equals(mapliketype._valueType))
            {
                return false;
            }
        }
        return true;
    }

    public JavaType getContentType()
    {
        return _valueType;
    }

    public StringBuilder getErasedSignature(StringBuilder stringbuilder)
    {
        return _classSignature(_class, stringbuilder, true);
    }

    public StringBuilder getGenericSignature(StringBuilder stringbuilder)
    {
        _classSignature(_class, stringbuilder, false);
        stringbuilder.append('<');
        _keyType.getGenericSignature(stringbuilder);
        _valueType.getGenericSignature(stringbuilder);
        stringbuilder.append(">;");
        return stringbuilder;
    }

    public JavaType getKeyType()
    {
        return _keyType;
    }

    public boolean isContainerType()
    {
        return true;
    }

    public boolean isMapLikeType()
    {
        return true;
    }

    public boolean isTrueMapType()
    {
        return java/util/Map.isAssignableFrom(_class);
    }

    public JavaType narrowContentsBy(Class class1)
    {
        if (class1 == _valueType.getRawClass())
        {
            return this;
        } else
        {
            return (new MapLikeType(_class, _keyType, _valueType.narrowBy(class1))).copyHandlers(this);
        }
    }

    public JavaType narrowKey(Class class1)
    {
        if (class1 == _keyType.getRawClass())
        {
            return this;
        } else
        {
            return (new MapLikeType(_class, _keyType.narrowBy(class1), _valueType)).copyHandlers(this);
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("[map-like type; class ").append(_class.getName()).append(", ").append(_keyType).append(" -> ").append(_valueType).append("]").toString();
    }

    public JavaType widenContentsBy(Class class1)
    {
        if (class1 == _valueType.getRawClass())
        {
            return this;
        } else
        {
            return (new MapLikeType(_class, _keyType, _valueType.widenBy(class1))).copyHandlers(this);
        }
    }

    public JavaType widenKey(Class class1)
    {
        if (class1 == _keyType.getRawClass())
        {
            return this;
        } else
        {
            return (new MapLikeType(_class, _keyType.widenBy(class1), _valueType)).copyHandlers(this);
        }
    }

    public MapLikeType withContentTypeHandler(Object obj)
    {
        return new MapLikeType(_class, _keyType, _valueType.withTypeHandler(obj));
    }

    public volatile JavaType withContentTypeHandler(Object obj)
    {
        return withContentTypeHandler(obj);
    }

    public MapLikeType withTypeHandler(Object obj)
    {
        MapLikeType mapliketype = new MapLikeType(_class, _keyType, _valueType);
        mapliketype._typeHandler = obj;
        return mapliketype;
    }

    public volatile JavaType withTypeHandler(Object obj)
    {
        return withTypeHandler(obj);
    }
}
