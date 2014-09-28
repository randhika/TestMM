// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.node;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;

// Referenced classes of package org.codehaus.jackson.node:
//            ValueNode

public final class NullNode extends ValueNode
{

    public static final NullNode instance = new NullNode();

    private NullNode()
    {
    }

    public static NullNode getInstance()
    {
        return instance;
    }

    public JsonToken asToken()
    {
        return JsonToken.VALUE_NULL;
    }

    public boolean equals(Object obj)
    {
        return obj == this;
    }

    public double getValueAsDouble(double d)
    {
        return 0.0D;
    }

    public int getValueAsInt(int i)
    {
        return 0;
    }

    public long getValueAsLong(long l)
    {
        return 0L;
    }

    public String getValueAsText()
    {
        return "null";
    }

    public boolean isNull()
    {
        return true;
    }

    public final void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeNull();
    }

}
