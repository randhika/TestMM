// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.type;

import org.codehaus.jackson.type.JavaType;

public abstract class TypeBase extends JavaType
{

    volatile String _canonicalName;

    protected TypeBase(Class class1, int i)
    {
        super(class1, i);
    }

    protected static StringBuilder _classSignature(Class class1, StringBuilder stringbuilder, boolean flag)
    {
        if (!class1.isPrimitive()) goto _L2; else goto _L1
_L1:
        if (class1 != Boolean.TYPE) goto _L4; else goto _L3
_L3:
        stringbuilder.append('Z');
_L6:
        return stringbuilder;
_L4:
        if (class1 == Byte.TYPE)
        {
            stringbuilder.append('B');
            return stringbuilder;
        }
        if (class1 == Short.TYPE)
        {
            stringbuilder.append('S');
            return stringbuilder;
        }
        if (class1 == Character.TYPE)
        {
            stringbuilder.append('C');
            return stringbuilder;
        }
        if (class1 == Integer.TYPE)
        {
            stringbuilder.append('I');
            return stringbuilder;
        }
        if (class1 == Long.TYPE)
        {
            stringbuilder.append('J');
            return stringbuilder;
        }
        if (class1 == Float.TYPE)
        {
            stringbuilder.append('F');
            return stringbuilder;
        }
        if (class1 == Double.TYPE)
        {
            stringbuilder.append('D');
            return stringbuilder;
        }
        if (class1 == Void.TYPE)
        {
            stringbuilder.append('V');
            return stringbuilder;
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("Unrecognized primitive type: ").append(class1.getName()).toString());
        }
_L2:
        stringbuilder.append('L');
        String s = class1.getName();
        int i = 0;
        for (int j = s.length(); i < j; i++)
        {
            char c = s.charAt(i);
            if (c == '.')
            {
                c = '/';
            }
            stringbuilder.append(c);
        }

        if (flag)
        {
            stringbuilder.append(';');
            return stringbuilder;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected abstract String buildCanonicalName();

    protected final JavaType copyHandlers(JavaType javatype)
    {
        _valueHandler = javatype.getValueHandler();
        _typeHandler = javatype.getTypeHandler();
        return this;
    }

    public abstract StringBuilder getErasedSignature(StringBuilder stringbuilder);

    public abstract StringBuilder getGenericSignature(StringBuilder stringbuilder);

    public String toCanonical()
    {
        String s = _canonicalName;
        if (s == null)
        {
            s = buildCanonicalName();
        }
        return s;
    }
}
