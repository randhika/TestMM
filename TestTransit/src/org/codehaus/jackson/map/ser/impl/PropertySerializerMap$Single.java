// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser.impl;

import org.codehaus.jackson.map.JsonSerializer;

// Referenced classes of package org.codehaus.jackson.map.ser.impl:
//            PropertySerializerMap

private static final class _serializer extends PropertySerializerMap
{

    private final JsonSerializer _serializer;
    private final Class _type;

    protected PropertySerializerMap newWith(Class class1, JsonSerializer jsonserializer)
    {
        return new <init>(_type, _serializer, class1, jsonserializer);
    }

    public JsonSerializer serializerFor(Class class1)
    {
        if (class1 == _type)
        {
            return _serializer;
        } else
        {
            return null;
        }
    }

    public (Class class1, JsonSerializer jsonserializer)
    {
        _type = class1;
        _serializer = jsonserializer;
    }
}
