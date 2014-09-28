// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;

import java.util.Collection;
import java.util.LinkedHashMap;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;

public abstract class BeanDescription
{

    protected final JavaType _type;

    protected BeanDescription(JavaType javatype)
    {
        _type = javatype;
    }

    public abstract TypeBindings bindingsForBeanType();

    public abstract LinkedHashMap findGetters(VisibilityChecker visibilitychecker, Collection collection);

    public abstract LinkedHashMap findSetters(VisibilityChecker visibilitychecker);

    public Class getBeanClass()
    {
        return _type.getRawClass();
    }

    public abstract Annotations getClassAnnotations();

    public JavaType getType()
    {
        return _type;
    }

    public abstract boolean hasKnownClassAnnotations();
}
