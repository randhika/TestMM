// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ext;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.SerializerBase;
import org.codehaus.jackson.map.ser.ToStringSerializer;
import org.codehaus.jackson.map.util.Provider;

public class CoreXMLSerializers
    implements Provider
{
    public static class XMLGregorianCalendarSerializer extends SerializerBase
    {

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
            throws JsonMappingException
        {
            return org.codehaus.jackson.map.ser.StdSerializers.CalendarSerializer.instance.getSchema(serializerprovider, type);
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((XMLGregorianCalendar)obj, jsongenerator, serializerprovider);
        }

        public void serialize(XMLGregorianCalendar xmlgregoriancalendar, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            org.codehaus.jackson.map.ser.StdSerializers.CalendarSerializer.instance.serialize(xmlgregoriancalendar.toGregorianCalendar(), jsongenerator, serializerprovider);
        }

        public XMLGregorianCalendarSerializer()
        {
            super(javax/xml/datatype/XMLGregorianCalendar);
        }
    }


    static final HashMap _serializers;

    public CoreXMLSerializers()
    {
    }

    public Collection provide()
    {
        return _serializers.entrySet();
    }

    static 
    {
        _serializers = new HashMap();
        ToStringSerializer tostringserializer = ToStringSerializer.instance;
        _serializers.put(javax/xml/datatype/Duration, tostringserializer);
        _serializers.put(javax/xml/datatype/XMLGregorianCalendar, new XMLGregorianCalendarSerializer());
        _serializers.put(javax/xml/namespace/QName, tostringserializer);
    }
}
