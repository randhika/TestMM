// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ext;

import java.io.IOException;
import javax.xml.namespace.QName;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.FromStringDeserializer;

// Referenced classes of package org.codehaus.jackson.map.ext:
//            CoreXMLDeserializers

public static class I extends FromStringDeserializer
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

    public I()
    {
        super(javax/xml/namespace/QName);
    }
}
