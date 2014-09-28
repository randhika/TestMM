// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser.impl;

import org.codehaus.jackson.map.JsonSerializer;

// Referenced classes of package org.codehaus.jackson.map.ser.impl:
//            JsonSerializerMap

private static final class value
{

    public final org.codehaus.jackson.map.ser.cket key;
    public final value next;
    public final JsonSerializer value;

    public ( , org.codehaus.jackson.map.ser.cket cket, JsonSerializer jsonserializer)
    {
        next = ;
        key = cket;
        value = jsonserializer;
    }
}
