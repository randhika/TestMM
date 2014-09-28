// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.introspect:
//            AnnotatedMember, AnnotationMap, AnnotatedParameter

public abstract class AnnotatedWithParams extends AnnotatedMember
{

    protected final AnnotationMap _annotations;
    protected final AnnotationMap _paramAnnotations[];

    protected AnnotatedWithParams(AnnotationMap annotationmap, AnnotationMap aannotationmap[])
    {
        _annotations = annotationmap;
        _paramAnnotations = aannotationmap;
    }

    public final void addIfNotPresent(Annotation annotation)
    {
        _annotations.addIfNotPresent(annotation);
    }

    public final void addOrOverride(Annotation annotation)
    {
        _annotations.add(annotation);
    }

    public final void addOrOverrideParam(int i, Annotation annotation)
    {
        AnnotationMap annotationmap = _paramAnnotations[i];
        if (annotationmap == null)
        {
            annotationmap = new AnnotationMap();
            _paramAnnotations[i] = annotationmap;
        }
        annotationmap.add(annotation);
    }

    public final Annotation getAnnotation(Class class1)
    {
        return _annotations.get(class1);
    }

    public final int getAnnotationCount()
    {
        return _annotations.size();
    }

    public abstract AnnotatedParameter getParameter(int i);

    public final AnnotationMap getParameterAnnotations(int i)
    {
        if (_paramAnnotations != null && i >= 0 && i <= _paramAnnotations.length)
        {
            return _paramAnnotations[i];
        } else
        {
            return null;
        }
    }

    public abstract Class getParameterClass(int i);

    public abstract int getParameterCount();

    public abstract Type getParameterType(int i);

    protected JavaType getType(TypeBindings typebindings, TypeVariable atypevariable[])
    {
        if (atypevariable != null && atypevariable.length > 0)
        {
            typebindings = typebindings.childInstance();
            int i = atypevariable.length;
            int j = 0;
            while (j < i) 
            {
                TypeVariable typevariable = atypevariable[j];
                typebindings._addPlaceholder(typevariable.getName());
                Type type = typevariable.getBounds()[0];
                JavaType javatype;
                if (type == null)
                {
                    javatype = TypeFactory.unknownType();
                } else
                {
                    javatype = typebindings.resolveType(type);
                }
                typebindings.addBinding(typevariable.getName(), javatype);
                j++;
            }
        }
        return typebindings.resolveType(getGenericType());
    }

    public final JavaType resolveParameterType(int i, TypeBindings typebindings)
    {
        return typebindings.resolveType(getParameterType(i));
    }
}
