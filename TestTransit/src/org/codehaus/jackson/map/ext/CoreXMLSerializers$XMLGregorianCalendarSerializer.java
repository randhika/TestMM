// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ext;

import java.io.IOException;
import java.lang.reflect.Type;
import javax.xml.datatype.XMLGregorianCalendar;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.SerializerBase;

// Referenced classes of package org.codehaus.jackson.map.ext:
//            CoreXMLSerializers

public static class  extends SerializerBase
{

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        throws JsonMappingException
    {
        return org.codehaus.jackson.map.ser..(serializerprovider, type);
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((XMLGregorianCalendar)obj, jsongenerator, serializerprovider);
    }

    public void serialize(XMLGregorianCalendar xmlgregoriancalendar, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        org.codehaus.jackson.map.ser.serialize.serialize(xmlgregoriancalendar.toGregorianCalendar(), jsongenerator, serializerprovider);
    }

    public ()
    {
        super(javax/xml/datatype/XMLGregorianCalendar);
    }
}
