// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ext;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.FromStringDeserializer;
import org.codehaus.jackson.map.deser.StdDeserializer;
import org.codehaus.jackson.map.deser.StdScalarDeserializer;
import org.codehaus.jackson.map.util.Provider;

public class CoreXMLDeserializers
    implements Provider
{
    public static class DurationDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected Duration _deserialize(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException
        {
            return CoreXMLDeserializers._dataTypeFactory.newDuration(s);
        }

        public DurationDeserializer()
        {
            super(javax/xml/datatype/Duration);
        }
    }

    public static class GregorianCalendarDeserializer extends StdScalarDeserializer
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

        public GregorianCalendarDeserializer()
        {
            super(javax/xml/datatype/XMLGregorianCalendar);
        }
    }

    public static class QNameDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected QName _deserialize(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException
        {
            return QName.valueOf(s);
        }

        public QNameDeserializer()
        {
            super(javax/xml/namespace/QName);
        }
    }


    static final DatatypeFactory _dataTypeFactory;

    public CoreXMLDeserializers()
    {
    }

    public Collection provide()
    {
        StdDeserializer astddeserializer[] = new StdDeserializer[3];
        astddeserializer[0] = new DurationDeserializer();
        astddeserializer[1] = new GregorianCalendarDeserializer();
        astddeserializer[2] = new QNameDeserializer();
        return Arrays.asList(astddeserializer);
    }

    static 
    {
        try
        {
            _dataTypeFactory = DatatypeFactory.newInstance();
        }
        catch (DatatypeConfigurationException datatypeconfigurationexception)
        {
            throw new RuntimeException(datatypeconfigurationexception);
        }
    }
}
