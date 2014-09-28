// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdKeyDeserializer, EnumResolver

static final class _resolver extends StdKeyDeserializer
{

    final EnumResolver _resolver;

    public Enum _parse(String s, DeserializationContext deserializationcontext)
        throws JsonMappingException
    {
        Enum enum = _resolver.findEnum(s);
        if (enum == null)
        {
            throw deserializationcontext.weirdKeyException(_keyClass, s, "not one of values for Enum class");
        } else
        {
            return enum;
        }
    }

    public volatile Object _parse(String s, DeserializationContext deserializationcontext)
        throws Exception
    {
        return _parse(s, deserializationcontext);
    }

    (EnumResolver enumresolver)
    {
        super(enumresolver.getEnumClass());
        _resolver = enumresolver;
    }
}
