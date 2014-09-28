// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.xc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.StdScalarDeserializer;

public class DataHandlerJsonDeserializer extends StdScalarDeserializer
{

    public DataHandlerJsonDeserializer()
    {
        super(javax/activation/DataHandler);
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public DataHandler deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return new DataHandler(new DataSource() {

            final DataHandlerJsonDeserializer this$0;
            final byte val$value[];

            public String getContentType()
            {
                return "application/octet-stream";
            }

            public InputStream getInputStream()
                throws IOException
            {
                return new ByteArrayInputStream(value);
            }

            public String getName()
            {
                return "json-binary-data";
            }

            public OutputStream getOutputStream()
                throws IOException
            {
                throw new IOException();
            }

            
            {
                this$0 = DataHandlerJsonDeserializer.this;
                value = abyte0;
                super();
            }
        });
    }
}
