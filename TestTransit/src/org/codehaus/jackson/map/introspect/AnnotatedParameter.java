// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.introspect:
//            AnnotatedMember, AnnotationMap

public final class AnnotatedParameter extends AnnotatedMember
{

    protected final AnnotationMap _annotations;
    protected final AnnotatedMember _owner;
    protected final Type _type;

    public AnnotatedParameter(AnnotatedMember annotatedmember, Type type, AnnotationMap annotationmap)
    {
        _owner = annotatedmember;
        _type = type;
        _annotations = annotationmap;
    }

    public void addOrOverride(Annotation annotation)
    {
        _annotations.add(annotation);
    }

    public AnnotatedElement getAnnotated()
    {
        return null;
    }

    public Annotation getAnnotation(Class class1)
    {
        return _annotations.get(class1);
    }

    public Class getDeclaringClass()
    {
        return _owner.getDeclaringClass();
    }

    public Type getGenericType()
    {
        return _type;
    }

    public Member getMember()
    {
        return _owner.getMember();
    }

    public int getModifiers()
    {
        return _owner.getModifiers();
    }

    public String getName()
    {
        return "";
    }

    public Type getParameterType()
    {
        return _type;
    }

    public Class getRawType()
    {
        if (_type instanceof Class)
        {
            return (Class)_type;
        } else
        {
            return TypeFactory.defaultInstance().constructType(_type).getRawClass();
        }
    }
}
