// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.UUID;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            FromStringDeserializer

public static class  extends FromStringDeserializer
{

    protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return _deserialize(s, deserializationcontext);
    }

    protected UUID _deserialize(String s, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return UUID.fromString(s);
    }

    protected volatile Object _deserializeEmbedded(Object obj, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return _deserializeEmbedded(obj, deserializationcontext);
    }

    protected UUID _deserializeEmbedded(Object obj, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (obj instanceof byte[])
        {
            byte abyte0[] = (byte[])(byte[])obj;
            if (abyte0.length != 16)
            {
                deserializationcontext.mappingException((new StringBuilder()).append("Can only construct UUIDs from 16 byte arrays; got ").append(abyte0.length).append(" bytes").toString());
            }
            DataInputStream datainputstream = new DataInputStream(new ByteArrayInputStream(abyte0));
            return new UUID(datainputstream.readLong(), datainputstream.readLong());
        } else
        {
            super._deserializeEmbedded(obj, deserializationcontext);
            return null;
        }
    }

    public ()
    {
        super(java/util/UUID);
    }
}
