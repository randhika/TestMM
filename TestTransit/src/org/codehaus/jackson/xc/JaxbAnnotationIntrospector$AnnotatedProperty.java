// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.xc;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

// Referenced classes of package org.codehaus.jackson.xc:
//            JaxbAnnotationIntrospector

private static class <init>
    implements AnnotatedElement
{

    private final PropertyDescriptor pd;

    public Annotation getAnnotation(Class class1)
    {
        Method method = pd.getReadMethod();
        if (method != null)
        {
            Annotation annotation = method.getAnnotation(class1);
            if (annotation != null)
            {
                return annotation;
            }
        }
        Method method1 = pd.getWriteMethod();
        if (method1 != null)
        {
            return method1.getAnnotation(class1);
        } else
        {
            return null;
        }
    }

    public Annotation[] getAnnotations()
    {
        throw new UnsupportedOperationException();
    }

    public Annotation[] getDeclaredAnnotations()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isAnnotationPresent(Class class1)
    {
        Method method = pd.getReadMethod();
        Method method1;
        if (method == null || !method.isAnnotationPresent(class1))
        {
            if ((method1 = pd.getWriteMethod()) == null || !method1.isAnnotationPresent(class1))
            {
                return false;
            }
        }
        return true;
    }

    private (PropertyDescriptor propertydescriptor)
    {
        pd = propertydescriptor;
    }

    pd(PropertyDescriptor propertydescriptor, pd pd1)
    {
        this(propertydescriptor);
    }
}
