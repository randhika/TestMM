// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import org.codehaus.jackson.JsonProcessingException;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            PropertyValue, SettableAnyProperty

static final class _propertyName extends PropertyValue
{

    final SettableAnyProperty _property;
    final String _propertyName;

    public void assign(Object obj)
        throws IOException, JsonProcessingException
    {
        _property.set(obj, _propertyName, value);
    }

    public y(PropertyValue propertyvalue, Object obj, SettableAnyProperty settableanyproperty, String s)
    {
        super(propertyvalue, obj);
        _property = settableanyproperty;
        _propertyName = s;
    }
}
