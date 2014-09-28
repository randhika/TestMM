// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ext;

import java.io.IOException;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.StdScalarDeserializer;

// Referenced classes of package org.codehaus.jackson.map.ext:
//            CoreXMLDeserializers

public static class  extends StdScalarDeserializer
{

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public XMLGregorianCalendar deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        java.util.Date date = _parseDate(jsonparser, deserializationcontext);
        if (date == null)
        {
            return null;
        } else
        {
            GregorianCalendar gregoriancalendar = new GregorianCalendar();
            gregoriancalendar.setTime(date);
            return CoreXMLDeserializers._dataTypeFactory.newXMLGregorianCalendar(gregoriancalendar);
        }
    }

    public ()
    {
        super(javax/xml/datatype/XMLGregorianCalendar);
    }
}
