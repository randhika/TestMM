// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.xc;

import java.io.IOException;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.deser.StdDeserializer;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

public class XmlAdapterJsonDeserializer extends StdDeserializer
{

    protected static final JavaType ADAPTER_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(javax/xml/bind/annotation/adapters/XmlAdapter);
    protected JsonDeserializer _deserializer;
    protected final BeanProperty _property;
    protected final JavaType _valueType;
    protected final XmlAdapter _xmlAdapter;

    public XmlAdapterJsonDeserializer(XmlAdapter xmladapter, BeanProperty beanproperty)
    {
        super(java/lang/Object);
        _property = beanproperty;
        _xmlAdapter = xmladapter;
        TypeFactory typefactory = TypeFactory.defaultInstance();
        JavaType ajavatype[] = typefactory.findTypeParameters(typefactory.constructType(xmladapter.getClass()), javax/xml/bind/annotation/adapters/XmlAdapter);
        JavaType javatype;
        if (ajavatype == null || ajavatype.length == 0)
        {
            javatype = TypeFactory.unknownType();
        } else
        {
            javatype = ajavatype[0];
        }
        _valueType = javatype;
    }

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonDeserializer jsondeserializer = _deserializer;
        if (jsondeserializer == null)
        {
            org.codehaus.jackson.map.DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
            jsondeserializer = deserializationcontext.getDeserializerProvider().findValueDeserializer(deserializationconfig, _valueType, _property);
            _deserializer = jsondeserializer;
        }
        Object obj = jsondeserializer.deserialize(jsonparser, deserializationcontext);
        Object obj1;
        try
        {
            obj1 = _xmlAdapter.unmarshal(obj);
        }
        catch (Exception exception)
        {
            throw new JsonMappingException((new StringBuilder()).append("Unable to unmarshal (to type ").append(_valueType).append("): ").append(exception.getMessage()).toString(), exception);
        }
        return obj1;
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromAny(jsonparser, deserializationcontext);
    }

}
