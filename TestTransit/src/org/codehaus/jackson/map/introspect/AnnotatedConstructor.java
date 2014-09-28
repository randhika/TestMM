// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.introspect;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.introspect:
//            AnnotatedWithParams, AnnotatedParameter, AnnotationMap

public final class AnnotatedConstructor extends AnnotatedWithParams
{

    protected final Constructor _constructor;

    public AnnotatedConstructor(Constructor constructor, AnnotationMap annotationmap, AnnotationMap aannotationmap[])
    {
        super(annotationmap, aannotationmap);
        if (constructor == null)
        {
            throw new IllegalArgumentException("Null constructor not allowed");
        } else
        {
            _constructor = constructor;
            return;
        }
    }

    public volatile AnnotatedElement getAnnotated()
    {
        return getAnnotated();
    }

    public Constructor getAnnotated()
    {
        return _constructor;
    }

    public Class getDeclaringClass()
    {
        return _constructor.getDeclaringClass();
    }

    public Type getGenericType()
    {
        return getRawType();
    }

    public Member getMember()
    {
        return _constructor;
    }

    public int getModifiers()
    {
        return _constructor.getModifiers();
    }

    public String getName()
    {
        return _constructor.getName();
    }

    public AnnotatedParameter getParameter(int i)
    {
        return new AnnotatedParameter(this, getParameterType(i), _paramAnnotations[i]);
    }

    public Class getParameterClass(int i)
    {
        Class aclass[] = _constructor.getParameterTypes();
        if (i >= aclass.length)
        {
            return null;
        } else
        {
            return aclass[i];
        }
    }

    public int getParameterCount()
    {
        return _constructor.getParameterTypes().length;
    }

    public Type getParameterType(int i)
    {
        Type atype[] = _constructor.getGenericParameterTypes();
        if (i >= atype.length)
        {
            return null;
        } else
        {
            return atype[i];
        }
    }

    public Class getRawType()
    {
        return _constructor.getDeclaringClass();
    }

    public JavaType getType(TypeBindings typebindings)
    {
        return getType(typebindings, _constructor.getTypeParameters());
    }

    public String toString()
    {
        return (new StringBuilder()).append("[constructor for ").append(getName()).append(", annotations: ").append(_annotations).append("]").toString();
    }
}
