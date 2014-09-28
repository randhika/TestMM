// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdScalarDeserializer, StdDeserializer

public static class _calendarClass extends StdScalarDeserializer
{

    Class _calendarClass;

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public Calendar deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        Date date = _parseDate(jsonparser, deserializationcontext);
        if (date == null)
        {
            return null;
        }
        if (_calendarClass == null)
        {
            return deserializationcontext.constructCalendar(date);
        }
        Calendar calendar;
        try
        {
            calendar = (Calendar)_calendarClass.newInstance();
            calendar.setTimeInMillis(date.getTime());
        }
        catch (Exception exception)
        {
            throw deserializationcontext.instantiationException(_calendarClass, exception);
        }
        return calendar;
    }

    public I()
    {
        this(null);
    }

    public <init>(Class class1)
    {
        super(java/util/Calendar);
        _calendarClass = class1;
    }
}
