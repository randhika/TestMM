// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ext;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.SerializerBase;
import org.codehaus.jackson.map.util.Provider;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class JodaSerializers
    implements Provider
{
    public static final class DateMidnightSerializer extends JodaSerializer
    {

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            String s;
            if (serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS))
            {
                s = "array";
            } else
            {
                s = "string";
            }
            return createSchemaNode(s, true);
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((DateMidnight)obj, jsongenerator, serializerprovider);
        }

        public void serialize(DateMidnight datemidnight, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            if (serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS))
            {
                jsongenerator.writeStartArray();
                jsongenerator.writeNumber(datemidnight.year().get());
                jsongenerator.writeNumber(datemidnight.monthOfYear().get());
                jsongenerator.writeNumber(datemidnight.dayOfMonth().get());
                jsongenerator.writeEndArray();
                return;
            } else
            {
                jsongenerator.writeString(printLocalDate(datemidnight));
                return;
            }
        }

        public DateMidnightSerializer()
        {
            super(org/joda/time/DateMidnight);
        }
    }

    public static final class DateTimeSerializer extends JodaSerializer
    {

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            String s;
            if (serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS))
            {
                s = "number";
            } else
            {
                s = "string";
            }
            return createSchemaNode(s, true);
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((DateTime)obj, jsongenerator, serializerprovider);
        }

        public void serialize(DateTime datetime, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            if (serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS))
            {
                jsongenerator.writeNumber(datetime.getMillis());
                return;
            } else
            {
                jsongenerator.writeString(datetime.toString());
                return;
            }
        }

        public DateTimeSerializer()
        {
            super(org/joda/time/DateTime);
        }
    }

    protected static abstract class JodaSerializer extends SerializerBase
    {

        static final DateTimeFormatter _localDateFormat = ISODateTimeFormat.date();
        static final DateTimeFormatter _localDateTimeFormat = ISODateTimeFormat.dateTime();

        protected String printLocalDate(ReadableInstant readableinstant)
            throws IOException, JsonProcessingException
        {
            return _localDateFormat.print(readableinstant);
        }

        protected String printLocalDate(ReadablePartial readablepartial)
            throws IOException, JsonProcessingException
        {
            return _localDateFormat.print(readablepartial);
        }

        protected String printLocalDateTime(ReadablePartial readablepartial)
            throws IOException, JsonProcessingException
        {
            return _localDateTimeFormat.print(readablepartial);
        }


        protected JodaSerializer(Class class1)
        {
            super(class1);
        }
    }

    public static final class LocalDateSerializer extends JodaSerializer
    {

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            String s;
            if (serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS))
            {
                s = "array";
            } else
            {
                s = "string";
            }
            return createSchemaNode(s, true);
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((LocalDate)obj, jsongenerator, serializerprovider);
        }

        public void serialize(LocalDate localdate, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            if (serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS))
            {
                jsongenerator.writeStartArray();
                jsongenerator.writeNumber(localdate.year().get());
                jsongenerator.writeNumber(localdate.monthOfYear().get());
                jsongenerator.writeNumber(localdate.dayOfMonth().get());
                jsongenerator.writeEndArray();
                return;
            } else
            {
                jsongenerator.writeString(printLocalDate(localdate));
                return;
            }
        }

        public LocalDateSerializer()
        {
            super(org/joda/time/LocalDate);
        }
    }

    public static final class LocalDateTimeSerializer extends JodaSerializer
    {

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            String s;
            if (serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS))
            {
                s = "array";
            } else
            {
                s = "string";
            }
            return createSchemaNode(s, true);
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((LocalDateTime)obj, jsongenerator, serializerprovider);
        }

        public void serialize(LocalDateTime localdatetime, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            if (serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS))
            {
                jsongenerator.writeStartArray();
                jsongenerator.writeNumber(localdatetime.year().get());
                jsongenerator.writeNumber(localdatetime.monthOfYear().get());
                jsongenerator.writeNumber(localdatetime.dayOfMonth().get());
                jsongenerator.writeNumber(localdatetime.hourOfDay().get());
                jsongenerator.writeNumber(localdatetime.minuteOfHour().get());
                jsongenerator.writeNumber(localdatetime.secondOfMinute().get());
                jsongenerator.writeNumber(localdatetime.millisOfSecond().get());
                jsongenerator.writeEndArray();
                return;
            } else
            {
                jsongenerator.writeString(printLocalDateTime(localdatetime));
                return;
            }
        }

        public LocalDateTimeSerializer()
        {
            super(org/joda/time/LocalDateTime);
        }
    }


    static final HashMap _serializers;

    public JodaSerializers()
    {
    }

    public Collection provide()
    {
        return _serializers.entrySet();
    }

    static 
    {
        _serializers = new HashMap();
        _serializers.put(org/joda/time/DateTime, new DateTimeSerializer());
        _serializers.put(org/joda/time/LocalDateTime, new LocalDateTimeSerializer());
        _serializers.put(org/joda/time/LocalDate, new LocalDateSerializer());
        _serializers.put(org/joda/time/DateMidnight, new DateMidnightSerializer());
    }
}
