// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            BaseNodeDeserializer

public class JsonNodeDeserializer extends BaseNodeDeserializer
{
    static final class ArrayDeserializer extends BaseNodeDeserializer
    {

        protected static final ArrayDeserializer _instance = new ArrayDeserializer();

        public static ArrayDeserializer getInstance()
        {
            return _instance;
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public ArrayNode deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (jsonparser.isExpectedStartArrayToken())
            {
                return deserializeArray(jsonparser, deserializationcontext);
            } else
            {
                throw deserializationcontext.mappingException(org/codehaus/jackson/node/ArrayNode);
            }
        }


        protected ArrayDeserializer()
        {
            super(org/codehaus/jackson/node/ArrayNode);
        }
    }

    static final class ObjectDeserializer extends BaseNodeDeserializer
    {

        protected static final ObjectDeserializer _instance = new ObjectDeserializer();

        public static ObjectDeserializer getInstance()
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


        protected ObjectDeserializer()
        {
            super(org/codehaus/jackson/node/ObjectNode);
        }
    }


    public static final JsonNodeDeserializer instance = new JsonNodeDeserializer();

    protected JsonNodeDeserializer()
    {
        super(org/codehaus/jackson/JsonNode);
    }

    public static JsonDeserializer getDeserializer(Class class1)
    {
        if (class1 == org/codehaus/jackson/node/ObjectNode)
        {
            return ObjectDeserializer.getInstance();
        }
        if (class1 == org/codehaus/jackson/node/ArrayNode)
        {
            return ArrayDeserializer.getInstance();
        } else
        {
            return instance;
        }
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public JsonNode deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserializeAny(jsonparser, deserializationcontext);
    }

    public volatile Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return super.deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
    }

}
