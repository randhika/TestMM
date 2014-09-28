// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.node;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;

// Referenced classes of package org.codehaus.jackson.node:
//            BaseJsonNode

public final class MissingNode extends BaseJsonNode
{

    private static final MissingNode instance = new MissingNode();

    private MissingNode()
    {
    }

    public static MissingNode getInstance()
    {
        return instance;
    }

    public JsonToken asToken()
    {
        return JsonToken.NOT_AVAILABLE;
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
        return null;
    }

    public boolean isMissingNode()
    {
        return true;
    }

    public JsonNode path(int i)
    {
        return this;
    }

    public JsonNode path(String s)
    {
        return this;
    }

    public final void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeNull();
    }

    public void serializeWithType(JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeNull();
    }

    public String toString()
    {
        return "";
    }

}
