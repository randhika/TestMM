// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ext;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.StdDeserializer;
import org.codehaus.jackson.map.deser.StdScalarDeserializer;
import org.codehaus.jackson.map.util.Provider;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class JodaDeserializers
    implements Provider
{
    public static class DateMidnightDeserializer extends JodaDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public DateMidnight deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (jsonparser.isExpectedStartArrayToken())
            {
                jsonparser.nextToken();
                int i = jsonparser.getIntValue();
                jsonparser.nextToken();
                int j = jsonparser.getIntValue();
                jsonparser.nextToken();
                int k = jsonparser.getIntValue();
                if (jsonparser.nextToken() != JsonToken.END_ARRAY)
                {
                    throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.END_ARRAY, "after DateMidnight ints");
                } else
                {
                    return new DateMidnight(i, j, k);
                }
            }
            static class _cls1
            {

                static final int $SwitchMap$org$codehaus$jackson$JsonToken[];

                static 
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 1;
                    }
                    catch (NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 2;
                    }
                    catch (NoSuchFieldError nosuchfielderror1)
                    {
                        return;
                    }
                }
            }

            DateTime datetime;
            switch (_cls1..SwitchMap.org.codehaus.jackson.JsonToken[jsonparser.getCurrentToken().ordinal()])
            {
            default:
                throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.START_ARRAY, "expected JSON Array, Number or String");

            case 1: // '\001'
                return new DateMidnight(jsonparser.getLongValue());

            case 2: // '\002'
                datetime = parseLocal(jsonparser);
                break;
            }
            if (datetime == null)
            {
                return null;
            } else
            {
                return datetime.toDateMidnight();
            }
        }

        public DateMidnightDeserializer()
        {
            super(org/joda/time/DateMidnight);
        }
    }

    public static class DateTimeDeserializer extends JodaDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public ReadableInstant deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            JsonToken jsontoken = jsonparser.getCurrentToken();
            if (jsontoken == JsonToken.VALUE_NUMBER_INT)
            {
                return new DateTime(jsonparser.getLongValue(), DateTimeZone.UTC);
            }
            if (jsontoken == JsonToken.VALUE_STRING)
            {
                String s = jsonparser.getText().trim();
                if (s.length() == 0)
                {
                    return null;
                } else
                {
                    return new DateTime(s, DateTimeZone.UTC);
                }
            } else
            {
                throw deserializationcontext.mappingException(getValueClass());
            }
        }

        public DateTimeDeserializer(Class class1)
        {
            super(class1);
        }
    }

    static abstract class JodaDeserializer extends StdScalarDeserializer
    {

        static final DateTimeFormatter _localDateTimeFormat = ISODateTimeFormat.localDateOptionalTimeParser();

        protected DateTime parseLocal(JsonParser jsonparser)
            throws IOException, JsonProcessingException
        {
            String s = jsonparser.getText().trim();
            if (s.length() == 0)
            {
                return null;
            } else
            {
                return _localDateTimeFormat.parseDateTime(s);
            }
        }


        protected JodaDeserializer(Class class1)
        {
            super(class1);
        }
    }

    public static class LocalDateDeserializer extends JodaDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public LocalDate deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (jsonparser.isExpectedStartArrayToken())
            {
                jsonparser.nextToken();
                int i = jsonparser.getIntValue();
                jsonparser.nextToken();
                int j = jsonparser.getIntValue();
                jsonparser.nextToken();
                int k = jsonparser.getIntValue();
                if (jsonparser.nextToken() != JsonToken.END_ARRAY)
                {
                    throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.END_ARRAY, "after LocalDate ints");
                } else
                {
                    return new LocalDate(i, j, k);
                }
            }
            DateTime datetime;
            switch (_cls1..SwitchMap.org.codehaus.jackson.JsonToken[jsonparser.getCurrentToken().ordinal()])
            {
            default:
                throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.START_ARRAY, "expected JSON Array, String or Number");

            case 1: // '\001'
                return new LocalDate(jsonparser.getLongValue());

            case 2: // '\002'
                datetime = parseLocal(jsonparser);
                break;
            }
            if (datetime == null)
            {
                return null;
            } else
            {
                return datetime.toLocalDate();
            }
        }

        public LocalDateDeserializer()
        {
            super(org/joda/time/LocalDate);
        }
    }

    public static class LocalDateTimeDeserializer extends JodaDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public LocalDateTime deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (jsonparser.isExpectedStartArrayToken())
            {
                jsonparser.nextToken();
                int i = jsonparser.getIntValue();
                jsonparser.nextToken();
                int j = jsonparser.getIntValue();
                jsonparser.nextToken();
                int k = jsonparser.getIntValue();
                jsonparser.nextToken();
                int l = jsonparser.getIntValue();
                jsonparser.nextToken();
                int i1 = jsonparser.getIntValue();
                jsonparser.nextToken();
                int j1 = jsonparser.getIntValue();
                JsonToken jsontoken = jsonparser.nextToken();
                JsonToken jsontoken1 = JsonToken.END_ARRAY;
                int k1 = 0;
                if (jsontoken != jsontoken1)
                {
                    k1 = jsonparser.getIntValue();
                    jsonparser.nextToken();
                }
                if (jsonparser.getCurrentToken() != JsonToken.END_ARRAY)
                {
                    throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.END_ARRAY, "after LocalDateTime ints");
                } else
                {
                    return new LocalDateTime(i, j, k, l, i1, j1, k1);
                }
            }
            DateTime datetime;
            switch (_cls1..SwitchMap.org.codehaus.jackson.JsonToken[jsonparser.getCurrentToken().ordinal()])
            {
            default:
                throw deserializationcontext.wrongTokenException(jsonparser, JsonToken.START_ARRAY, "expected JSON Array or Number");

            case 1: // '\001'
                return new LocalDateTime(jsonparser.getLongValue());

            case 2: // '\002'
                datetime = parseLocal(jsonparser);
                break;
            }
            if (datetime == null)
            {
                return null;
            } else
            {
                return datetime.toLocalDateTime();
            }
        }

        public LocalDateTimeDeserializer()
        {
            super(org/joda/time/LocalDateTime);
        }
    }


    public JodaDeserializers()
    {
    }

    public Collection provide()
    {
        StdDeserializer astddeserializer[] = new StdDeserializer[6];
        astddeserializer[0] = new DateTimeDeserializer(org/joda/time/DateTime);
        astddeserializer[1] = new DateTimeDeserializer(org/joda/time/ReadableDateTime);
        astddeserializer[2] = new DateTimeDeserializer(org/joda/time/ReadableInstant);
        astddeserializer[3] = new LocalDateDeserializer();
        astddeserializer[4] = new LocalDateTimeDeserializer();
        astddeserializer[5] = new DateMidnightDeserializer();
        return Arrays.asList(astddeserializer);
    }
}
