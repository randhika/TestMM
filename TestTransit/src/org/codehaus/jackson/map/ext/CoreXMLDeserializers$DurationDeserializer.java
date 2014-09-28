// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ext;

import java.io.IOException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.FromStringDeserializer;

// Referenced classes of package org.codehaus.jackson.map.ext:
//            CoreXMLDeserializers

public static class  extends FromStringDeserializer
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

    public ()
    {
        super(javax/xml/datatype/Duration);
    }
}
