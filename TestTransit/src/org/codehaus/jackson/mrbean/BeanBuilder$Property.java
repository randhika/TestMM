// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.mrbean;

import java.lang.reflect.Method;
import org.codehaus.jackson.map.type.TypeFactory;

// Referenced classes of package org.codehaus.jackson.mrbean:
//            BeanUtil, BeanBuilder

private static class _fieldName
{

    protected final String _fieldName;
    protected Method _getter;
    protected final String _name;
    protected Method _setter;

    private iption getterType(TypeFactory typefactory)
    {
        Class class1 = _getter.getDeclaringClass();
        return new iption(typefactory.constructType(_getter.getGenericReturnType(), class1));
    }

    private iption setterType(TypeFactory typefactory)
    {
        Class class1 = _setter.getDeclaringClass();
        return new iption(typefactory.constructType(_setter.getGenericParameterTypes()[0], class1));
    }

    public String getFieldName()
    {
        return _fieldName;
    }

    public Method getGetter()
    {
        return _getter;
    }

    public String getName()
    {
        return _name;
    }

    public Method getSetter()
    {
        return _setter;
    }

    public boolean hasConcreteGetter()
    {
        return _getter != null && BeanUtil.isConcrete(_getter);
    }

    public boolean hasConcreteSetter()
    {
        return _setter != null && BeanUtil.isConcrete(_setter);
    }

    public iption selectType(TypeFactory typefactory)
    {
        iption iption2;
        if (_getter == null)
        {
            iption2 = setterType(typefactory);
        } else
        {
            if (_setter == null)
            {
                return getterType(typefactory);
            }
            iption iption = setterType(typefactory);
            iption iption1 = getterType(typefactory);
            iption2 = org.codehaus.jackson.mrbean.iption.moreSpecificType(iption, iption1);
            if (iption2 == null)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid property '").append(getName()).append("': incompatible types for getter/setter (").append(iption1).append(" vs ").append(iption).append(")").toString());
            }
        }
        return iption2;
    }

    public void setGetter(Method method)
    {
        _getter = method;
    }

    public void setSetter(Method method)
    {
        _setter = method;
    }

    public iption(String s)
    {
        _name = s;
        _fieldName = (new StringBuilder()).append("_").append(s).toString();
    }
}
