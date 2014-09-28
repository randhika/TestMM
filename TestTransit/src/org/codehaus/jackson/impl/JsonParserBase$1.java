// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.impl;

import org.codehaus.jackson.JsonToken;

// Referenced classes of package org.codehaus.jackson.impl:
//            JsonParserBase

static class 
{

    static final int $SwitchMap$org$codehaus$jackson$JsonToken[];

    static 
    {
        $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
        try
        {
            $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1)
        {
            return;
        }
    }
}
