// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import org.codehaus.jackson.JsonToken;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdDeserializer

static class 
{

    static final int $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[];
    static final int $SwitchMap$org$codehaus$jackson$JsonToken[];

    static 
    {
        $SwitchMap$org$codehaus$jackson$JsonParser$NumberType = new int[org.codehaus.jackson.ype.values().length];
        try
        {
            $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[org.codehaus.jackson.ype.INT.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[org.codehaus.jackson.ype.LONG.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
        try
        {
            $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror4)
        {
            return;
        }
    }
}
