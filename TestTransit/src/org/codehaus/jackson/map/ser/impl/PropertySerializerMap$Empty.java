// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser.impl;

import org.codehaus.jackson.map.JsonSerializer;

// Referenced classes of package org.codehaus.jackson.map.ser.impl:
//            PropertySerializerMap

private static final class  extends PropertySerializerMap
{

    protected static final  instance = new <init>();

    protected PropertySerializerMap newWith(Class class1, JsonSerializer jsonserializer)
    {
        return new (class1, jsonserializer);
    }

    public JsonSerializer serializerFor(Class class1)
    {
        return null;
    }


    private ()
    {
    }
}
