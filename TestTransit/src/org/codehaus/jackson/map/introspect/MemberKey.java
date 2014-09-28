// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.introspect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class MemberKey
{

    static final Class NO_CLASSES[] = new Class[0];
    final Class _argTypes[];
    final String _name;

    public MemberKey(String s, Class aclass[])
    {
        _name = s;
        if (aclass == null)
        {
            aclass = NO_CLASSES;
        }
        _argTypes = aclass;
    }

    public MemberKey(Constructor constructor)
    {
        this("", constructor.getParameterTypes());
    }

    public MemberKey(Method method)
    {
        this(method.getName(), method.getParameterTypes());
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
            MemberKey memberkey = (MemberKey)obj;
            if (!_name.equals(memberkey._name))
            {
                return false;
            }
            Class aclass[] = memberkey._argTypes;
            int i = _argTypes.length;
            if (aclass.length != i)
            {
                return false;
            }
            int j = 0;
            while (j < i) 
            {
                Class class1 = aclass[j];
                Class class2 = _argTypes[j];
                if (class1 == class2 || class1.isAssignableFrom(class2) || class2.isAssignableFrom(class1))
                {
                    j++;
                } else
                {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode()
    {
        return _name.hashCode() + _argTypes.length;
    }

    public String toString()
    {
        return (new StringBuilder()).append(_name).append("(").append(_argTypes.length).append("-args)").toString();
    }

}
