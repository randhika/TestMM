// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.xc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataSource;

// Referenced classes of package org.codehaus.jackson.xc:
//            DataHandlerJsonDeserializer

class val.value
    implements DataSource
{

    final DataHandlerJsonDeserializer this$0;
    final byte val$value[];

    public String getContentType()
    {
        return "application/octet-stream";
    }

    public InputStream getInputStream()
        throws IOException
    {
        return new ByteArrayInputStream(val$value);
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

    ()
    {
        this$0 = final_datahandlerjsondeserializer;
        val$value = _5B_B.this;
        super();
    }
}
