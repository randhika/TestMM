// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

// Referenced classes of package org.codehaus.jackson.map.introspect:
//            AnnotatedMember, AnnotationMap

public final class AnnotatedField extends AnnotatedMember
{

    protected final AnnotationMap _annotations;
    protected final Field _field;

    public AnnotatedField(Field field, AnnotationMap annotationmap)
    {
        _field = field;
        _annotations = annotationmap;
    }

    public void addOrOverride(Annotation annotation)
    {
        _annotations.add(annotation);
    }

    public volatile AnnotatedElement getAnnotated()
    {
        return getAnnotated();
    }

    public Field getAnnotated()
    {
        return _field;
    }

    public Annotation getAnnotation(Class class1)
    {
        return _annotations.get(class1);
    }

    public int getAnnotationCount()
    {
        return _annotations.size();
    }

    public Class getDeclaringClass()
    {
        return _field.getDeclaringClass();
    }

    public String getFullName()
    {
        return (new StringBuilder()).append(getDeclaringClass().getName()).append("#").append(getName()).toString();
    }

    public Type getGenericType()
    {
        return _field.getGenericType();
    }

    public Member getMember()
    {
        return _field;
    }

    public int getModifiers()
    {
        return _field.getModifiers();
    }

    public String getName()
    {
        return _field.getName();
    }

    public Class getRawType()
    {
        return _field.getType();
    }

    public String toString()
    {
        return (new StringBuilder()).append("[field ").append(getName()).append(", annotations: ").append(_annotations).append("]").toString();
    }
}
