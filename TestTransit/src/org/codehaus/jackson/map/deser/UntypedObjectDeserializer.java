// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.util.ObjectBuffer;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdDeserializer

public class UntypedObjectDeserializer extends StdDeserializer
{

    public UntypedObjectDeserializer()
    {
        super(java/lang/Object);
    }

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        static class _cls1
        {

            static final int $SwitchMap$org$codehaus$jackson$JsonToken[];

            static 
            {
                $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 6;
                }
                catch (NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 7;
                }
                catch (NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_ARRAY.ordinal()] = 8;
                }
                catch (NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_OBJECT.ordinal()] = 9;
                }
                catch (NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 10;
                }
                catch (NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_ARRAY.ordinal()] = 11;
                }
                catch (NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_OBJECT.ordinal()] = 12;
                }
                catch (NoSuchFieldError nosuchfielderror11)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.org.codehaus.jackson.JsonToken[jsonparser.getCurrentToken().ordinal()])
        {
        default:
            throw deserializationcontext.mappingException(java/lang/Object);

        case 1: // '\001'
            return jsonparser.getText();

        case 2: // '\002'
            if (deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS))
            {
                return jsonparser.getBigIntegerValue();
            } else
            {
                return jsonparser.getNumberValue();
            }

        case 3: // '\003'
            if (deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS))
            {
                return jsonparser.getDecimalValue();
            } else
            {
                return Double.valueOf(jsonparser.getDoubleValue());
            }

        case 4: // '\004'
            return Boolean.TRUE;

        case 5: // '\005'
            return Boolean.FALSE;

        case 6: // '\006'
            return jsonparser.getEmbeddedObject();

        case 7: // '\007'
            return null;

        case 8: // '\b'
            return mapArray(jsonparser, deserializationcontext);

        case 9: // '\t'
        case 10: // '\n'
            return mapObject(jsonparser, deserializationcontext);
        }
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        switch (_cls1..SwitchMap.org.codehaus.jackson.JsonToken[jsontoken.ordinal()])
        {
        default:
            throw deserializationcontext.mappingException(java/lang/Object);

        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
            return typedeserializer.deserializeTypedFromAny(jsonparser, deserializationcontext);

        case 1: // '\001'
            return jsonparser.getText();

        case 2: // '\002'
            if (deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS))
            {
                return jsonparser.getBigIntegerValue();
            } else
            {
                return Integer.valueOf(jsonparser.getIntValue());
            }

        case 3: // '\003'
            if (deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS))
            {
                return jsonparser.getDecimalValue();
            } else
            {
                return Double.valueOf(jsonparser.getDoubleValue());
            }

        case 4: // '\004'
            return Boolean.TRUE;

        case 5: // '\005'
            return Boolean.FALSE;

        case 6: // '\006'
            return jsonparser.getEmbeddedObject();

        case 7: // '\007'
            return null;
        }
    }

    protected List mapArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (jsonparser.nextToken() == JsonToken.END_ARRAY)
        {
            return new ArrayList(4);
        }
        ObjectBuffer objectbuffer = deserializationcontext.leaseObjectBuffer();
        Object aobj[] = objectbuffer.resetAndStart();
        int i = 0;
        int j = 0;
        do
        {
            Object obj = deserialize(jsonparser, deserializationcontext);
            j++;
            if (i >= aobj.length)
            {
                aobj = objectbuffer.appendCompletedChunk(aobj);
                i = 0;
            }
            int k = i + 1;
            aobj[i] = obj;
            if (jsonparser.nextToken() == JsonToken.END_ARRAY)
            {
                ArrayList arraylist = new ArrayList(1 + (j + (j >> 3)));
                objectbuffer.completeAndClearBuffer(aobj, k, arraylist);
                return arraylist;
            }
            i = k;
        } while (true);
    }

    protected Map mapObject(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken == JsonToken.START_OBJECT)
        {
            jsontoken = jsonparser.nextToken();
        }
        if (jsontoken != JsonToken.FIELD_NAME)
        {
            return new LinkedHashMap(4);
        }
        String s = jsonparser.getText();
        jsonparser.nextToken();
        Object obj = deserialize(jsonparser, deserializationcontext);
        if (jsonparser.nextToken() != JsonToken.FIELD_NAME)
        {
            LinkedHashMap linkedhashmap = new LinkedHashMap(4);
            linkedhashmap.put(s, obj);
            return linkedhashmap;
        }
        String s1 = jsonparser.getText();
        jsonparser.nextToken();
        Object obj1 = deserialize(jsonparser, deserializationcontext);
        if (jsonparser.nextToken() != JsonToken.FIELD_NAME)
        {
            LinkedHashMap linkedhashmap1 = new LinkedHashMap(4);
            linkedhashmap1.put(s, obj);
            linkedhashmap1.put(s1, obj1);
            return linkedhashmap1;
        }
        LinkedHashMap linkedhashmap2 = new LinkedHashMap();
        linkedhashmap2.put(s, obj);
        linkedhashmap2.put(s1, obj1);
        do
        {
            String s2 = jsonparser.getText();
            jsonparser.nextToken();
            linkedhashmap2.put(s2, deserialize(jsonparser, deserializationcontext));
        } while (jsonparser.nextToken() != JsonToken.END_OBJECT);
        return linkedhashmap2;
    }
}
