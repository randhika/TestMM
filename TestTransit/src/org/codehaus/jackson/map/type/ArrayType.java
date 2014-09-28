// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.type;

import java.lang.reflect.Array;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.type:
//            TypeBase, TypeFactory

public final class ArrayType extends TypeBase
{

    final JavaType _componentType;
    final Object _emptyArray;

    private ArrayType(JavaType javatype, Object obj)
    {
        super(obj.getClass(), javatype.hashCode());
        _componentType = javatype;
        _emptyArray = obj;
    }

    public static ArrayType construct(JavaType javatype)
    {
        return new ArrayType(javatype, Array.newInstance(javatype.getRawClass(), 0));
    }

    protected JavaType _narrow(Class class1)
    {
        if (!class1.isArray())
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Incompatible narrowing operation: trying to narrow ").append(toString()).append(" to class ").append(class1.getName()).toString());
        } else
        {
            Class class2 = class1.getComponentType();
            return construct(TypeFactory.defaultInstance().constructType(class2));
        }
    }

    protected String buildCanonicalName()
    {
        return _class.getName();
    }

    public JavaType containedType(int i)
    {
        if (i == 0)
        {
            return _componentType;
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
        boolean flag;
        if (obj == this)
        {
            flag = true;
        } else
        {
            flag = false;
            if (obj != null)
            {
                Class class1 = obj.getClass();
                Class class2 = getClass();
                flag = false;
                if (class1 == class2)
                {
                    ArrayType arraytype = (ArrayType)obj;
                    return _componentType.equals(arraytype._componentType);
                }
            }
        }
        return flag;
    }

    public JavaType getContentType()
    {
        return _componentType;
    }

    public StringBuilder getErasedSignature(StringBuilder stringbuilder)
    {
        stringbuilder.append('[');
        return _componentType.getErasedSignature(stringbuilder);
    }

    public StringBuilder getGenericSignature(StringBuilder stringbuilder)
    {
        stringbuilder.append('[');
        return _componentType.getGenericSignature(stringbuilder);
    }

    public boolean hasGenericTypes()
    {
        return _componentType.hasGenericTypes();
    }

    public boolean isAbstract()
    {
        return false;
    }

    public boolean isArrayType()
    {
        return true;
    }

    public boolean isConcrete()
    {
        return true;
    }

    public boolean isContainerType()
    {
        return true;
    }

    public JavaType narrowContentsBy(Class class1)
    {
        if (class1 == _componentType.getRawClass())
        {
            return this;
        } else
        {
            return construct(_componentType.narrowBy(class1)).copyHandlers(this);
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("[array type, component type: ").append(_componentType).append("]").toString();
    }

    public JavaType widenContentsBy(Class class1)
    {
        if (class1 == _componentType.getRawClass())
        {
            return this;
        } else
        {
            return construct(_componentType.widenBy(class1)).copyHandlers(this);
        }
    }

    public ArrayType withContentTypeHandler(Object obj)
    {
        return new ArrayType(_componentType.withTypeHandler(obj), _emptyArray);
    }

    public volatile JavaType withContentTypeHandler(Object obj)
    {
        return withContentTypeHandler(obj);
    }

    public ArrayType withTypeHandler(Object obj)
    {
        ArrayType arraytype = new ArrayType(_componentType, _emptyArray);
        arraytype._typeHandler = obj;
        return arraytype;
    }

    public volatile JavaType withTypeHandler(Object obj)
    {
        return withTypeHandler(obj);
    }
}
