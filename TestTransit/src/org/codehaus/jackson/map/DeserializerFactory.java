// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;

import org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map:
//            Deserializers, JsonMappingException, DeserializationConfig, DeserializerProvider, 
//            BeanProperty, JsonDeserializer, KeyDeserializer, TypeDeserializer, 
//            AbstractTypeResolver, KeyDeserializers

public abstract class DeserializerFactory
{
    public static abstract class Config
    {

        public abstract Iterable abstractTypeResolvers();

        public abstract Iterable deserializerModifiers();

        public abstract Iterable deserializers();

        public abstract boolean hasAbstractTypeResolvers();

        public abstract boolean hasDeserializerModifiers();

        public abstract boolean hasDeserializers();

        public abstract boolean hasKeyDeserializers();

        public abstract Iterable keyDeserializers();

        public abstract Config withAbstractTypeResolver(AbstractTypeResolver abstracttyperesolver);

        public abstract Config withAdditionalDeserializers(Deserializers deserializers1);

        public abstract Config withAdditionalKeyDeserializers(KeyDeserializers keydeserializers);

        public abstract Config withDeserializerModifier(BeanDeserializerModifier beandeserializermodifier);

        public Config()
        {
        }
    }


    protected static final Deserializers NO_DESERIALIZERS[] = new Deserializers[0];

    public DeserializerFactory()
    {
    }

    public abstract JsonDeserializer createArrayDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, ArrayType arraytype, BeanProperty beanproperty)
        throws JsonMappingException;

    public final JsonDeserializer createArrayDeserializer(DeserializationConfig deserializationconfig, ArrayType arraytype, DeserializerProvider deserializerprovider)
        throws JsonMappingException
    {
        return createArrayDeserializer(deserializationconfig, deserializerprovider, arraytype, null);
    }

    public abstract JsonDeserializer createBeanDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException;

    public final JsonDeserializer createBeanDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, DeserializerProvider deserializerprovider)
        throws JsonMappingException
    {
        return createBeanDeserializer(deserializationconfig, deserializerprovider, javatype, null);
    }

    public abstract JsonDeserializer createCollectionDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, CollectionType collectiontype, BeanProperty beanproperty)
        throws JsonMappingException;

    public final JsonDeserializer createCollectionDeserializer(DeserializationConfig deserializationconfig, CollectionType collectiontype, DeserializerProvider deserializerprovider)
        throws JsonMappingException
    {
        return createCollectionDeserializer(deserializationconfig, deserializerprovider, collectiontype, null);
    }

    public abstract JsonDeserializer createCollectionLikeDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, CollectionLikeType collectionliketype, BeanProperty beanproperty)
        throws JsonMappingException;

    public final JsonDeserializer createEnumDeserializer(DeserializationConfig deserializationconfig, Class class1, DeserializerProvider deserializerprovider)
        throws JsonMappingException
    {
        return createEnumDeserializer(deserializationconfig, deserializerprovider, TypeFactory.type(class1), null);
    }

    public abstract JsonDeserializer createEnumDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException;

    public KeyDeserializer createKeyDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        return null;
    }

    public abstract JsonDeserializer createMapDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, MapType maptype, BeanProperty beanproperty)
        throws JsonMappingException;

    public final JsonDeserializer createMapDeserializer(DeserializationConfig deserializationconfig, MapType maptype, DeserializerProvider deserializerprovider)
        throws JsonMappingException
    {
        return createMapDeserializer(deserializationconfig, deserializerprovider, maptype, null);
    }

    public abstract JsonDeserializer createMapLikeDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, MapLikeType mapliketype, BeanProperty beanproperty)
        throws JsonMappingException;

    public final JsonDeserializer createTreeDeserializer(DeserializationConfig deserializationconfig, Class class1, DeserializerProvider deserializerprovider)
        throws JsonMappingException
    {
        return createTreeDeserializer(deserializationconfig, deserializerprovider, TypeFactory.type(class1), null);
    }

    public abstract JsonDeserializer createTreeDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException;

    public final TypeDeserializer findTypeDeserializer(DeserializationConfig deserializationconfig, JavaType javatype)
    {
        return findTypeDeserializer(deserializationconfig, javatype, null);
    }

    public TypeDeserializer findTypeDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, BeanProperty beanproperty)
    {
        return null;
    }

    public abstract Config getConfig();

    public final DeserializerFactory withAbstractTypeResolver(AbstractTypeResolver abstracttyperesolver)
    {
        return withConfig(getConfig().withAbstractTypeResolver(abstracttyperesolver));
    }

    public final DeserializerFactory withAdditionalDeserializers(Deserializers deserializers)
    {
        return withConfig(getConfig().withAdditionalDeserializers(deserializers));
    }

    public final DeserializerFactory withAdditionalKeyDeserializers(KeyDeserializers keydeserializers)
    {
        return withConfig(getConfig().withAdditionalKeyDeserializers(keydeserializers));
    }

    public abstract DeserializerFactory withConfig(Config config);

    public final DeserializerFactory withDeserializerModifier(BeanDeserializerModifier beandeserializermodifier)
    {
        return withConfig(getConfig().withDeserializerModifier(beandeserializermodifier));
    }

}
