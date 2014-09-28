// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.util.Locale;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            FromStringDeserializer

protected static class  extends FromStringDeserializer
{

    protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return _deserialize(s, deserializationcontext);
    }

    protected Locale _deserialize(String s, DeserializationContext deserializationcontext)
        throws IOException
    {
        int i = s.indexOf('_');
        if (i < 0)
        {
            return new Locale(s);
        }
        String s1 = s.substring(0, i);
        String s2 = s.substring(i + 1);
        int j = s2.indexOf('_');
        if (j < 0)
        {
            return new Locale(s1, s2);
        } else
        {
            return new Locale(s1, s2.substring(0, j), s2.substring(j + 1));
        }
    }

    public ()
    {
        super(java/util/Locale);
    }
}
