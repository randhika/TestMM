// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.reflect.Method;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.util.ClassUtil;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdScalarDeserializer, EnumDeserializer

protected static class _factory extends StdScalarDeserializer
{

    protected final Class _enumClass;
    protected final Method _factory;

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (jsonparser.getCurrentToken() != JsonToken.VALUE_STRING)
        {
            throw deserializationcontext.mappingException(_enumClass);
        }
        String s = jsonparser.getText();
        Object obj;
        try
        {
            obj = _factory.invoke(_enumClass, new Object[] {
                s
            });
        }
        catch (Exception exception)
        {
            ClassUtil.unwrapAndThrowAsIAE(exception);
            return null;
        }
        return obj;
    }

    public (Class class1, AnnotatedMethod annotatedmethod)
    {
        super(java/lang/Enum);
        _enumClass = class1;
        _factory = annotatedmethod.getAnnotated();
    }
}
