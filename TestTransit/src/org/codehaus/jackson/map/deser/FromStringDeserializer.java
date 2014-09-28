// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdScalarDeserializer

public abstract class FromStringDeserializer extends StdScalarDeserializer
{
    public static class CurrencyDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected Currency _deserialize(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException
        {
            return Currency.getInstance(s);
        }

        public CurrencyDeserializer()
        {
            super(java/util/Currency);
        }
    }

    protected static class InetAddressDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected InetAddress _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException
        {
            return InetAddress.getByName(s);
        }

        public InetAddressDeserializer()
        {
            super(java/net/InetAddress);
        }
    }

    protected static class LocaleDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected Locale _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException
        {
            int i = s.indexOf('_');
            if (i < 0)
            {
                return new Locale(s);
            }
            String s1 = s.substring(0, i);
            String s2 = s.substring(i + 1);
            int j = s2.indexOf('_');
            if (j < 0)
            {
                return new Locale(s1, s2);
            } else
            {
                return new Locale(s1, s2.substring(0, j), s2.substring(j + 1));
            }
        }

        public LocaleDeserializer()
        {
            super(java/util/Locale);
        }
    }

    public static class PatternDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected Pattern _deserialize(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException
        {
            return Pattern.compile(s);
        }

        public PatternDeserializer()
        {
            super(java/util/regex/Pattern);
        }
    }

    protected static class TimeZoneDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected TimeZone _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException
        {
            return TimeZone.getTimeZone(s);
        }

        public TimeZoneDeserializer()
        {
            super(java/util/TimeZone);
        }
    }

    public static class URIDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected URI _deserialize(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException
        {
            return URI.create(s);
        }

        public URIDeserializer()
        {
            super(java/net/URI);
        }
    }

    public static class URLDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected URL _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException
        {
            return new URL(s);
        }

        public URLDeserializer()
        {
            super(java/net/URL);
        }
    }

    public static class UUIDDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected UUID _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return UUID.fromString(s);
        }

        protected volatile Object _deserializeEmbedded(Object obj, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserializeEmbedded(obj, deserializationcontext);
        }

        protected UUID _deserializeEmbedded(Object obj, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (obj instanceof byte[])
            {
                byte abyte0[] = (byte[])(byte[])obj;
                if (abyte0.length != 16)
                {
                    deserializationcontext.mappingException((new StringBuilder()).append("Can only construct UUIDs from 16 byte arrays; got ").append(abyte0.length).append(" bytes").toString());
                }
                DataInputStream datainputstream = new DataInputStream(new ByteArrayInputStream(abyte0));
                return new UUID(datainputstream.readLong(), datainputstream.readLong());
            } else
            {
                _deserializeEmbedded(obj, deserializationcontext);
                return null;
            }
        }

        public UUIDDeserializer()
        {
            super(java/util/UUID);
        }
    }


    protected FromStringDeserializer(Class class1)
    {
        super(class1);
    }

    public static Iterable all()
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new UUIDDeserializer());
        arraylist.add(new URLDeserializer());
        arraylist.add(new URIDeserializer());
        arraylist.add(new CurrencyDeserializer());
        arraylist.add(new PatternDeserializer());
        arraylist.add(new LocaleDeserializer());
        arraylist.add(new InetAddressDeserializer());
        arraylist.add(new TimeZoneDeserializer());
        return arraylist;
    }

    protected abstract Object _deserialize(String s, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException;

    protected Object _deserializeEmbedded(Object obj, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        throw deserializationcontext.mappingException((new StringBuilder()).append("Don't know how to convert embedded Object of type ").append(obj.getClass().getName()).append(" into ").append(_valueClass.getName()).toString());
    }

    public final Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (jsonparser.getCurrentToken() != JsonToken.VALUE_STRING) goto _L2; else goto _L1
_L1:
        Object obj1;
        String s;
        int i;
        s = jsonparser.getText().trim();
        i = s.length();
        obj1 = null;
        if (i != 0) goto _L4; else goto _L3
_L3:
        Object obj2;
        return obj1;
_L4:
        if ((obj1 = obj2 = _deserialize(s, deserializationcontext)) != null) goto _L3; else goto _L5
_L5:
        throw deserializationcontext.weirdStringException(_valueClass, "not a valid textual representation");
_L2:
        if (jsonparser.getCurrentToken() == JsonToken.VALUE_EMBEDDED_OBJECT)
        {
            Object obj = jsonparser.getEmbeddedObject();
            obj1 = null;
            if (obj != null)
            {
                if (_valueClass.isAssignableFrom(obj.getClass()))
                {
                    return obj;
                } else
                {
                    return _deserializeEmbedded(obj, deserializationcontext);
                }
            }
        } else
        {
            throw deserializationcontext.mappingException(_valueClass);
        }
          goto _L3
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
          goto _L5
    }
}
