// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser.impl;

import org.codehaus.jackson.map.JsonSerializer;

// Referenced classes of package org.codehaus.jackson.map.ser.impl:
//            PropertySerializerMap

private static final class _entries extends PropertySerializerMap
{

    private static final int MAX_ENTRIES = 8;
    private final dSerializer _entries[];

    protected PropertySerializerMap newWith(Class class1, JsonSerializer jsonserializer)
    {
        int i = _entries.length;
        if (i == 8)
        {
            return this;
        } else
        {
            dSerializer adserializer[] = new dSerializer[i + 1];
            System.arraycopy(_entries, 0, adserializer, 0, i);
            adserializer[i] = new dSerializer(class1, jsonserializer);
            return new <init>(adserializer);
        }
    }

    public JsonSerializer serializerFor(Class class1)
    {
        int i = 0;
        for (int j = _entries.length; i < j; i++)
        {
            dSerializer dserializer = _entries[i];
            if (dserializer.type == class1)
            {
                return dserializer.serializer;
            }
        }

        return null;
    }

    public dSerializer(dSerializer adserializer[])
    {
        _entries = adserializer;
    }
}
