// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.node.ObjectNode;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            BaseNodeDeserializer, JsonNodeDeserializer

static final class  extends BaseNodeDeserializer
{

    protected static final deserializeObject _instance = new <init>();

    public static  getInstance()
    {
        return _instance;
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public ObjectNode deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (jsonparser.getCurrentToken() == JsonToken.START_OBJECT)
        {
            jsonparser.nextToken();
            return deserializeObject(jsonparser, deserializationcontext);
        }
        if (jsonparser.getCurrentToken() == JsonToken.FIELD_NAME)
        {
            return deserializeObject(jsonparser, deserializationcontext);
        } else
        {
            throw deserializationcontext.mappingException(org/codehaus/jackson/node/ObjectNode);
        }
    }


    protected ()
    {
        super(org/codehaus/jackson/node/ObjectNode);
    }
}
