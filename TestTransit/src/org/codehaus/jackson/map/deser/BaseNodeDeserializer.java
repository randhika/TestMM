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
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdDeserializer

abstract class BaseNodeDeserializer extends StdDeserializer
{

    public BaseNodeDeserializer(Class class1)
    {
        super(class1);
    }

    protected void _handleDuplicateField(String s, ObjectNode objectnode, JsonNode jsonnode, JsonNode jsonnode1)
        throws JsonProcessingException
    {
    }

    protected void _reportProblem(JsonParser jsonparser, String s)
        throws JsonMappingException
    {
        throw new JsonMappingException(s, jsonparser.getTokenLocation());
    }

    protected final JsonNode deserializeAny(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonNodeFactory jsonnodefactory = deserializationcontext.getNodeFactory();
        static class _cls1
        {

            static final int $SwitchMap$org$codehaus$jackson$JsonToken[];

            static 
            {
                $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_OBJECT.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_ARRAY.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 6;
                }
                catch (NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 7;
                }
                catch (NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 8;
                }
                catch (NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 9;
                }
                catch (NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_OBJECT.ordinal()] = 10;
                }
                catch (NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_ARRAY.ordinal()] = 11;
                }
                catch (NoSuchFieldError nosuchfielderror10)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.org.codehaus.jackson.JsonToken[jsonparser.getCurrentToken().ordinal()])
        {
        default:
            throw deserializationcontext.mappingException(getValueClass());

        case 1: // '\001'
        case 2: // '\002'
            return deserializeObject(jsonparser, deserializationcontext);

        case 3: // '\003'
            return deserializeArray(jsonparser, deserializationcontext);

        case 4: // '\004'
            return jsonnodefactory.textNode(jsonparser.getText());

        case 5: // '\005'
            org.codehaus.jackson.JsonParser.NumberType numbertype = jsonparser.getNumberType();
            if (numbertype == org.codehaus.jackson.JsonParser.NumberType.BIG_INTEGER || deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS))
            {
                return jsonnodefactory.numberNode(jsonparser.getBigIntegerValue());
            }
            if (numbertype == org.codehaus.jackson.JsonParser.NumberType.INT)
            {
                return jsonnodefactory.numberNode(jsonparser.getIntValue());
            } else
            {
                return jsonnodefactory.numberNode(jsonparser.getLongValue());
            }

        case 6: // '\006'
            if (jsonparser.getNumberType() == org.codehaus.jackson.JsonParser.NumberType.BIG_DECIMAL || deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS))
            {
                return jsonnodefactory.numberNode(jsonparser.getDecimalValue());
            } else
            {
                return jsonnodefactory.numberNode(jsonparser.getDoubleValue());
            }

        case 7: // '\007'
            return jsonnodefactory.booleanNode(true);

        case 8: // '\b'
            return jsonnodefactory.booleanNode(false);

        case 9: // '\t'
            return jsonnodefactory.nullNode();
        }
    }

    protected final ArrayNode deserializeArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        ArrayNode arraynode = deserializationcontext.getNodeFactory().arrayNode();
        for (; jsonparser.nextToken() != JsonToken.END_ARRAY; arraynode.add(deserializeAny(jsonparser, deserializationcontext))) { }
        return arraynode;
    }

    protected final ObjectNode deserializeObject(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        ObjectNode objectnode = deserializationcontext.getNodeFactory().objectNode();
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken == JsonToken.START_OBJECT)
        {
            jsontoken = jsonparser.nextToken();
        }
        for (; jsontoken == JsonToken.FIELD_NAME; jsontoken = jsonparser.nextToken())
        {
            String s = jsonparser.getCurrentName();
            jsonparser.nextToken();
            JsonNode jsonnode = deserializeAny(jsonparser, deserializationcontext);
            JsonNode jsonnode1 = objectnode.put(s, jsonnode);
            if (jsonnode1 != null)
            {
                _handleDuplicateField(s, objectnode, jsonnode1, jsonnode);
            }
        }

        return objectnode;
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromAny(jsonparser, deserializationcontext);
    }
}
