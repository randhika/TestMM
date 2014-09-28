// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.mrbean;

import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.mrbean:
//            BeanBuilder

private static class _asmType
{

    private final Type _asmType;
    private JavaType _jacksonType;

    public static _asmType moreSpecificType(_asmType _pasmtype, _asmType _pasmtype1)
    {
        Class class1 = _pasmtype.getRawClass();
        Class class2 = _pasmtype1.getRawClass();
        if (class1.isAssignableFrom(class2))
        {
            return _pasmtype1;
        }
        if (class2.isAssignableFrom(class1))
        {
            return _pasmtype;
        } else
        {
            return null;
        }
    }

    public String erasedSignature()
    {
        return _jacksonType.getErasedSignature();
    }

    public String genericSignature()
    {
        return _jacksonType.getGenericSignature();
    }

    public int getLoadOpcode()
    {
        return _asmType.getOpcode(21);
    }

    public Class getRawClass()
    {
        return _jacksonType.getRawClass();
    }

    public int getReturnOpcode()
    {
        return _asmType.getOpcode(172);
    }

    public boolean hasGenerics()
    {
        return _jacksonType.hasGenericTypes();
    }

    public String toString()
    {
        return _jacksonType.toString();
    }

    public (JavaType javatype)
    {
        _jacksonType = javatype;
        _asmType = Type.getType(javatype.getRawClass());
    }
}
