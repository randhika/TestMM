// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.util.ClassUtil;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            Creator

static final class _longFactoryMethod
{

    protected final Constructor _intCtor;
    protected final Method _intFactoryMethod;
    protected final Constructor _longCtor;
    protected final Method _longFactoryMethod;
    protected final Class _valueClass;

    public Object construct(int i)
    {
        Object obj;
        if (_intCtor != null)
        {
            Constructor constructor = _intCtor;
            Object aobj1[] = new Object[1];
            aobj1[0] = Integer.valueOf(i);
            return constructor.newInstance(aobj1);
        }
        if (_intFactoryMethod == null)
        {
            break MISSING_BLOCK_LABEL_85;
        }
        Method method = _intFactoryMethod;
        Class class1 = _valueClass;
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        obj = method.invoke(class1, aobj);
        return obj;
        Exception exception;
        exception;
        ClassUtil.unwrapAndThrowAsIAE(exception);
        return construct(i);
    }

    public Object construct(long l)
    {
        Object obj;
        if (_longCtor != null)
        {
            Constructor constructor = _longCtor;
            Object aobj1[] = new Object[1];
            aobj1[0] = Long.valueOf(l);
            return constructor.newInstance(aobj1);
        }
        if (_longFactoryMethod == null)
        {
            break MISSING_BLOCK_LABEL_87;
        }
        Method method = _longFactoryMethod;
        Class class1 = _valueClass;
        Object aobj[] = new Object[1];
        aobj[0] = Long.valueOf(l);
        obj = method.invoke(class1, aobj);
        return obj;
        Exception exception;
        exception;
        ClassUtil.unwrapAndThrowAsIAE(exception);
        return null;
    }

    public (Class class1, AnnotatedConstructor annotatedconstructor, AnnotatedMethod annotatedmethod, AnnotatedConstructor annotatedconstructor1, AnnotatedMethod annotatedmethod1)
    {
        _valueClass = class1;
        Constructor constructor;
        Constructor constructor1;
        Method method;
        Method method1;
        if (annotatedconstructor == null)
        {
            constructor = null;
        } else
        {
            constructor = annotatedconstructor.getAnnotated();
        }
        _intCtor = constructor;
        if (annotatedconstructor1 == null)
        {
            constructor1 = null;
        } else
        {
            constructor1 = annotatedconstructor1.getAnnotated();
        }
        _longCtor = constructor1;
        if (annotatedmethod == null)
        {
            method = null;
        } else
        {
            method = annotatedmethod.getAnnotated();
        }
        _intFactoryMethod = method;
        method1 = null;
        if (annotatedmethod1 != null)
        {
            method1 = annotatedmethod1.getAnnotated();
        }
        _longFactoryMethod = method1;
    }
}
