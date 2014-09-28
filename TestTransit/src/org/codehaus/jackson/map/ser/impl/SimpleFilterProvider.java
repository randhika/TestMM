// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser.impl;

import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.map.ser.BeanPropertyFilter;
import org.codehaus.jackson.map.ser.FilterProvider;

public class SimpleFilterProvider extends FilterProvider
{

    protected BeanPropertyFilter _defaultFilter;
    protected final Map _filtersById;

    public SimpleFilterProvider()
    {
        _filtersById = new HashMap();
    }

    public SimpleFilterProvider(Map map)
    {
        _filtersById = new HashMap();
    }

    public SimpleFilterProvider addFilter(String s, BeanPropertyFilter beanpropertyfilter)
    {
        _filtersById.put(s, beanpropertyfilter);
        return this;
    }

    public BeanPropertyFilter findFilter(Object obj)
    {
        BeanPropertyFilter beanpropertyfilter = (BeanPropertyFilter)_filtersById.get(obj);
        if (beanpropertyfilter == null)
        {
            beanpropertyfilter = _defaultFilter;
        }
        return beanpropertyfilter;
    }

    public BeanPropertyFilter removeFilter(String s)
    {
        return (BeanPropertyFilter)_filtersById.remove(s);
    }

    public SimpleFilterProvider setDefaultFilter(BeanPropertyFilter beanpropertyfilter)
    {
        _defaultFilter = beanpropertyfilter;
        return this;
    }
}
