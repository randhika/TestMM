// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.introspect;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.introspect:
//            AnnotatedWithParams, AnnotatedParameter, AnnotationMap

public final class AnnotatedMethod extends AnnotatedWithParams
{

    protected final Method _method;
    protected Class _paramTypes[];

    public AnnotatedMethod(Method method, AnnotationMap annotationmap, AnnotationMap aannotationmap[])
    {
        super(annotationmap, aannotationmap);
        _method = method;
    }

    public volatile AnnotatedElement getAnnotated()
    {
        return getAnnotated();
    }

    public Method getAnnotated()
    {
        return _method;
    }

    public Class getDeclaringClass()
    {
        return _method.getDeclaringClass();
    }

    public String getFullName()
    {
        return (new StringBuilder()).append(getDeclaringClass().getName()).append("#").append(getName()).append("(").append(getParameterCount()).append(" params)").toString();
    }

    public Type getGenericType()
    {
        return _method.getGenericReturnType();
    }

    public Member getMember()
    {
        return _method;
    }

    public int getModifiers()
    {
        return _method.getModifiers();
    }

    public String getName()
    {
        return _method.getName();
    }

    public AnnotatedParameter getParameter(int i)
    {
        return new AnnotatedParameter(this, getParameterType(i), _paramAnnotations[i]);
    }

    public Class getParameterClass(int i)
    {
        Class aclass[] = _method.getParameterTypes();
        if (i >= aclass.length)
        {
            return null;
        } else
        {
            return aclass[i];
        }
    }

    public Class[] getParameterClasses()
    {
        if (_paramTypes == null)
        {
            _paramTypes = _method.getParameterTypes();
        }
        return _paramTypes;
    }

    public int getParameterCount()
    {
        return getParameterTypes().length;
    }

    public Type getParameterType(int i)
    {
        Type atype[] = _method.getGenericParameterTypes();
        if (i >= atype.length)
        {
            return null;
        } else
        {
            return atype[i];
        }
    }

    public Type[] getParameterTypes()
    {
        return _method.getGenericParameterTypes();
    }

    public Class getRawType()
    {
        return _method.getReturnType();
    }

    public JavaType getType(TypeBindings typebindings)
    {
        return getType(typebindings, _method.getTypeParameters());
    }

    public String toString()
    {
        return (new StringBuilder()).append("[method ").append(getName()).append(", annotations: ").append(_annotations).append("]").toString();
    }

    public AnnotatedMethod withMethod(Method method)
    {
        return new AnnotatedMethod(method, _annotations, _paramAnnotations);
    }
}
