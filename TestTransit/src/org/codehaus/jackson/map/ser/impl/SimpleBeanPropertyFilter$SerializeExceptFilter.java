// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser.impl;

import java.util.Set;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.BeanPropertyWriter;

// Referenced classes of package org.codehaus.jackson.map.ser.impl:
//            SimpleBeanPropertyFilter

public static class _propertiesToExclude extends SimpleBeanPropertyFilter
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

    public (Set set)
    {
        _propertiesToExclude = set;
    }
}
