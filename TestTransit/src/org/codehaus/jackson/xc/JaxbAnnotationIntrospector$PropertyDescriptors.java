// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.xc;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlTransient;

// Referenced classes of package org.codehaus.jackson.xc:
//            JaxbAnnotationIntrospector

protected static final class _properties
{

    private Map _byMethodName;
    private Map _byPropertyName;
    private final Class _forClass;
    private final List _properties;

    private static Map _processReadMethod(Map map, Method method, String s, List list)
        throws IntrospectionException
    {
        if (map == null)
        {
            map = new HashMap();
        } else
        {
            PropertyDescriptor propertydescriptor = (PropertyDescriptor)map.get(s);
            if (propertydescriptor != null)
            {
                propertydescriptor.setReadMethod(method);
                if (propertydescriptor.getWriteMethod() != null)
                {
                    list.add(propertydescriptor);
                    map.remove(s);
                    return map;
                }
            }
        }
        map.put(s, new PropertyDescriptor(s, method, null));
        return map;
    }

    private static Map _processWriteMethod(Map map, Method method, String s, List list)
        throws IntrospectionException
    {
        if (map == null)
        {
            map = new HashMap();
        } else
        {
            PropertyDescriptor propertydescriptor = (PropertyDescriptor)map.get(s);
            if (propertydescriptor != null)
            {
                propertydescriptor.setWriteMethod(method);
                if (propertydescriptor.getReadMethod() != null)
                {
                    list.add(propertydescriptor);
                    map.remove(s);
                    return map;
                }
            }
        }
        map.put(s, new PropertyDescriptor(s, null, method));
        return map;
    }

    public static _properties find(Class class1)
        throws IntrospectionException
    {
        BeanInfo beaninfo = Introspector.getBeanInfo(class1);
        Object obj;
        if (beaninfo.getPropertyDescriptors().length == 0)
        {
            obj = Collections.emptyList();
        } else
        {
            obj = new ArrayList();
            Map map = null;
            PropertyDescriptor apropertydescriptor[] = beaninfo.getPropertyDescriptors();
            int i = apropertydescriptor.length;
            int j = 0;
            while (j < i) 
            {
                PropertyDescriptor propertydescriptor = apropertydescriptor[j];
                Method method = propertydescriptor.getReadMethod();
                if (method != null && method.getAnnotation(javax/xml/bind/annotation/XmlTransient) != null)
                {
                    method = null;
                }
                String s;
                Method method1;
                if (method == null)
                {
                    s = null;
                } else
                {
                    s = JaxbAnnotationIntrospector.findJaxbPropertyName(method, propertydescriptor.getPropertyType(), null);
                }
                method1 = propertydescriptor.getWriteMethod();
                if (method1 != null && method1.getAnnotation(javax/xml/bind/annotation/XmlTransient) != null)
                {
                    method1 = null;
                }
                if (method != null || method1 != null)
                {
                    String s1;
                    if (method1 == null)
                    {
                        s1 = null;
                    } else
                    {
                        s1 = JaxbAnnotationIntrospector.findJaxbPropertyName(method1, propertydescriptor.getPropertyType(), null);
                    }
                    if (method1 == null)
                    {
                        if (s == null)
                        {
                            s = propertydescriptor.getName();
                        }
                        map = _processReadMethod(map, method, s, ((List) (obj)));
                    } else
                    if (method == null)
                    {
                        if (s1 == null)
                        {
                            s1 = propertydescriptor.getName();
                        }
                        map = _processWriteMethod(map, method1, s1, ((List) (obj)));
                    } else
                    if (s != null && s1 != null && !s.equals(s1))
                    {
                        map = _processWriteMethod(_processReadMethod(map, method, s, ((List) (obj))), method1, s1, ((List) (obj)));
                    } else
                    {
                        String s2;
                        if (s != null)
                        {
                            s2 = s;
                        } else
                        if (s1 != null)
                        {
                            s2 = s1;
                        } else
                        {
                            s2 = propertydescriptor.getName();
                        }
                        ((List) (obj)).add(new PropertyDescriptor(s2, method, method1));
                    }
                }
                j++;
            }
        }
        return new <init>(class1, ((List) (obj)));
    }

    public PropertyDescriptor findByMethodName(String s)
    {
        if (_byMethodName == null)
        {
            _byMethodName = new HashMap(_properties.size());
            Iterator iterator = _properties.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                PropertyDescriptor propertydescriptor = (PropertyDescriptor)iterator.next();
                Method method = propertydescriptor.getReadMethod();
                if (method != null)
                {
                    _byMethodName.put(method.getName(), propertydescriptor);
                }
                Method method1 = propertydescriptor.getWriteMethod();
                if (method1 != null)
                {
                    _byMethodName.put(method1.getName(), propertydescriptor);
                }
            } while (true);
        }
        return (PropertyDescriptor)_byMethodName.get(s);
    }

    public PropertyDescriptor findByPropertyName(String s)
    {
        if (_byPropertyName == null)
        {
            _byPropertyName = new HashMap(_properties.size());
            PropertyDescriptor propertydescriptor;
            for (Iterator iterator = _properties.iterator(); iterator.hasNext(); _byPropertyName.put(propertydescriptor.getName(), propertydescriptor))
            {
                propertydescriptor = (PropertyDescriptor)iterator.next();
            }

        }
        return (PropertyDescriptor)_byPropertyName.get(s);
    }

    public Class getBeanClass()
    {
        return _forClass;
    }

    public (Class class1, List list)
    {
        _forClass = class1;
        _properties = list;
    }
}
