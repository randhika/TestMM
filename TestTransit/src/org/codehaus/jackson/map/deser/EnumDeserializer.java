// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.reflect.Method;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.util.ClassUtil;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdScalarDeserializer, EnumResolver

public class EnumDeserializer extends StdScalarDeserializer
{
    protected static class FactoryBasedDeserializer extends StdScalarDeserializer
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

        public FactoryBasedDeserializer(Class class1, AnnotatedMethod annotatedmethod)
        {
            super(java/lang/Enum);
            _enumClass = class1;
            _factory = annotatedmethod.getAnnotated();
        }
    }


    final EnumResolver _resolver;

    public EnumDeserializer(EnumResolver enumresolver)
    {
        super(java/lang/Enum);
        _resolver = enumresolver;
    }

    public static JsonDeserializer deserializerForCreator(DeserializationConfig deserializationconfig, Class class1, AnnotatedMethod annotatedmethod)
    {
        if (annotatedmethod.getParameterType(0) != java/lang/String)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Parameter #0 type for factory method (").append(annotatedmethod).append(") not suitable, must be java.lang.String").toString());
        }
        if (deserializationconfig.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
        {
            ClassUtil.checkAndFixAccess(annotatedmethod.getMember());
        }
        return new FactoryBasedDeserializer(class1, annotatedmethod);
    }

    public Enum deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken == JsonToken.VALUE_STRING)
        {
            String s = jsonparser.getText();
            Enum enum1 = _resolver.findEnum(s);
            if (enum1 == null)
            {
                throw deserializationcontext.weirdStringException(_resolver.getEnumClass(), "value not one of declared Enum instance names");
            } else
            {
                return enum1;
            }
        }
        if (jsontoken == JsonToken.VALUE_NUMBER_INT)
        {
            if (deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS))
            {
                throw deserializationcontext.mappingException("Not allowed to deserialize Enum value out of JSON number (disable DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS to allow)");
            }
            int i = jsonparser.getIntValue();
            Enum enum = _resolver.getEnum(i);
            if (enum == null)
            {
                throw deserializationcontext.weirdNumberException(_resolver.getEnumClass(), (new StringBuilder()).append("index value outside legal index range [0..").append(_resolver.lastValidIndex()).append("]").toString());
            } else
            {
                return enum;
            }
        } else
        {
            throw deserializationcontext.mappingException(_resolver.getEnumClass());
        }
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }
}
