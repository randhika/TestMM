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

static final class _factoryMethod
{

    protected final Constructor _ctor;
    protected final Method _factoryMethod;
    protected final Class _valueClass;

    public Object construct(String s)
    {
        Object obj;
        if (_ctor != null)
        {
            return _ctor.newInstance(new Object[] {
                s
            });
        }
        if (_factoryMethod == null)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        obj = _factoryMethod.invoke(_valueClass, new Object[] {
            s
        });
        return obj;
        Exception exception;
        exception;
        ClassUtil.unwrapAndThrowAsIAE(exception);
        return null;
    }

    public (Class class1, AnnotatedConstructor annotatedconstructor, AnnotatedMethod annotatedmethod)
    {
        _valueClass = class1;
        Constructor constructor;
        Method method;
        if (annotatedconstructor == null)
        {
            constructor = null;
        } else
        {
            constructor = annotatedconstructor.getAnnotated();
        }
        _ctor = constructor;
        method = null;
        if (annotatedmethod != null)
        {
            method = annotatedmethod.getAnnotated();
        }
        _factoryMethod = method;
    }
}
