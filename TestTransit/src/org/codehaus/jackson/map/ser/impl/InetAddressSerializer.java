// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser.impl;

import java.io.IOException;
import java.net.InetAddress;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.ser.ScalarSerializerBase;

public class InetAddressSerializer extends ScalarSerializerBase
{

    public static final InetAddressSerializer instance = new InetAddressSerializer();

    public InetAddressSerializer()
    {
        super(java/net/InetAddress);
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((InetAddress)obj, jsongenerator, serializerprovider);
    }

    public void serialize(InetAddress inetaddress, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        String s = inetaddress.toString().trim();
        int i = s.indexOf('/');
        if (i >= 0)
        {
            if (i == 0)
            {
                s = s.substring(1);
            } else
            {
                s = s.substring(0, i);
            }
        }
        jsongenerator.writeString(s);
    }

    public volatile void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        serializeWithType((InetAddress)obj, jsongenerator, serializerprovider, typeserializer);
    }

    public void serializeWithType(InetAddress inetaddress, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        typeserializer.writeTypePrefixForScalar(inetaddress, jsongenerator, java/net/InetAddress);
        serialize(inetaddress, jsongenerator, serializerprovider);
        typeserializer.writeTypeSuffixForScalar(inetaddress, jsongenerator);
    }

}
