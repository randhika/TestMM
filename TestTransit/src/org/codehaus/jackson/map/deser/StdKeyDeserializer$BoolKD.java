// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdKeyDeserializer

static final class  extends StdKeyDeserializer
{

    public Boolean _parse(String s, DeserializationContext deserializationcontext)
        throws JsonMappingException
    {
        if ("true".equals(s))
        {
            return Boolean.TRUE;
        }
        if ("false".equals(s))
        {
            return Boolean.FALSE;
        } else
        {
            throw deserializationcontext.weirdKeyException(_keyClass, s, "value not 'true' or 'false'");
        }
    }

    public volatile Object _parse(String s, DeserializationContext deserializationcontext)
        throws Exception
    {
        return _parse(s, deserializationcontext);
    }

    ()
    {
        super(java/lang/Boolean);
    }
}
