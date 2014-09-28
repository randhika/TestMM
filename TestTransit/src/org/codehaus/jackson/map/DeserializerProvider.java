// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;

import org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map:
//            JsonMappingException, BeanProperty, DeserializationConfig, KeyDeserializer, 
//            JsonDeserializer, AbstractTypeResolver, Deserializers, KeyDeserializers

public abstract class DeserializerProvider
{

    protected DeserializerProvider()
    {
    }

    public abstract int cachedDeserializersCount();

    public final KeyDeserializer findKeyDeserializer(DeserializationConfig deserializationconfig, JavaType javatype)
        throws JsonMappingException
    {
        return findKeyDeserializer(deserializationconfig, javatype, null);
    }

    public abstract KeyDeserializer findKeyDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException;

    public final JsonDeserializer findTypedValueDeserializer(DeserializationConfig deserializationconfig, JavaType javatype)
        throws JsonMappingException
    {
        return findTypedValueDeserializer(deserializationconfig, javatype, null);
    }

    public abstract JsonDeserializer findTypedValueDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException;

    public abstract JsonDeserializer findValueDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException;

    public final JsonDeserializer findValueDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, JavaType javatype1, String s)
        throws JsonMappingException
    {
        return findValueDeserializer(deserializationconfig, javatype, (BeanProperty)null);
    }

    public abstract void flushCachedDeserializers();

    public abstract boolean hasValueDeserializerFor(DeserializationConfig deserializationconfig, JavaType javatype);

    public abstract DeserializerProvider withAbstractTypeResolver(AbstractTypeResolver abstracttyperesolver);

    public abstract DeserializerProvider withAdditionalDeserializers(Deserializers deserializers);

    public abstract DeserializerProvider withAdditionalKeyDeserializers(KeyDeserializers keydeserializers);

    public abstract DeserializerProvider withDeserializerModifier(BeanDeserializerModifier beandeserializermodifier);
}
