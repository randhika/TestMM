// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser.impl;

import org.codehaus.jackson.map.JsonSerializer;

// Referenced classes of package org.codehaus.jackson.map.ser.impl:
//            PropertySerializerMap

private static final class _serializer2 extends PropertySerializerMap
{

    private final JsonSerializer _serializer1;
    private final JsonSerializer _serializer2;
    private final Class _type1;
    private final Class _type2;

    protected PropertySerializerMap newWith(Class class1, JsonSerializer jsonserializer)
    {
        Serializer aserializer[] = new Serializer[2];
        aserializer[0] = new Serializer(_type1, _serializer1);
        aserializer[1] = new Serializer(_type2, _serializer2);
        return new init>(aserializer);
    }

    public JsonSerializer serializerFor(Class class1)
    {
        if (class1 == _type1)
        {
            return _serializer1;
        }
        if (class1 == _type2)
        {
            return _serializer2;
        } else
        {
            return null;
        }
    }

    public Serializer(Class class1, JsonSerializer jsonserializer, Class class2, JsonSerializer jsonserializer1)
    {
        _type1 = class1;
        _serializer1 = jsonserializer;
        _type2 = class2;
        _serializer2 = jsonserializer1;
    }
}
