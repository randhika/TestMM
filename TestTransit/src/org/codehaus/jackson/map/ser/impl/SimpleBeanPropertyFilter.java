// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.BeanPropertyFilter;
import org.codehaus.jackson.map.ser.BeanPropertyWriter;

public abstract class SimpleBeanPropertyFilter
    implements BeanPropertyFilter
{
    public static class FilterExceptFilter extends SimpleBeanPropertyFilter
    {

        protected final Set _propertiesToInclude;

        public void serializeAsField(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, BeanPropertyWriter beanpropertywriter)
            throws Exception
        {
            if (_propertiesToInclude.contains(beanpropertywriter.getName()))
            {
                beanpropertywriter.serializeAsField(obj, jsongenerator, serializerprovider);
            }
        }

        public FilterExceptFilter(Set set)
        {
            _propertiesToInclude = set;
        }
    }

    public static class SerializeExceptFilter extends SimpleBeanPropertyFilter
    {

        protected final Set _propertiesToExclude;

        public void serializeAsField(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, BeanPropertyWriter beanpropertywriter)
            throws Exception
        {
            if (!_propertiesToExclude.contains(beanpropertywriter.getName()))
            {
                beanpropertywriter.serializeAsField(obj, jsongenerator, serializerprovider);
            }
        }

        public SerializeExceptFilter(Set set)
        {
            _propertiesToExclude = set;
        }
    }


    protected SimpleBeanPropertyFilter()
    {
    }

    public static SimpleBeanPropertyFilter filterOutAllExcept(Set set)
    {
        return new FilterExceptFilter(set);
    }

    public static transient SimpleBeanPropertyFilter filterOutAllExcept(String as[])
    {
        HashSet hashset = new HashSet(as.length);
        Collections.addAll(hashset, as);
        return new FilterExceptFilter(hashset);
    }

    public static SimpleBeanPropertyFilter serializeAllExcept(Set set)
    {
        return new SerializeExceptFilter(set);
    }

    public static transient SimpleBeanPropertyFilter serializeAllExcept(String as[])
    {
        HashSet hashset = new HashSet(as.length);
        Collections.addAll(hashset, as);
        return new SerializeExceptFilter(hashset);
    }
}
