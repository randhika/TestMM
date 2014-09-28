// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.jsontype.impl;

import java.util.Collection;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.jsontype.impl:
//            AsArrayTypeDeserializer, AsPropertyTypeDeserializer, AsWrapperTypeDeserializer, AsArrayTypeSerializer, 
//            AsPropertyTypeSerializer, AsWrapperTypeSerializer, ClassNameIdResolver, MinimalClassNameIdResolver, 
//            TypeNameIdResolver

public class StdTypeResolverBuilder
    implements TypeResolverBuilder
{

    protected TypeIdResolver _customIdResolver;
    protected org.codehaus.jackson.annotate.JsonTypeInfo.Id _idType;
    protected org.codehaus.jackson.annotate.JsonTypeInfo.As _includeAs;
    protected String _typeProperty;

    public StdTypeResolverBuilder()
    {
    }

    public TypeDeserializer buildTypeDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, Collection collection, BeanProperty beanproperty)
    {
        TypeIdResolver typeidresolver = idResolver(deserializationconfig, javatype, collection, false, true);
        static class _cls1
        {

            static final int $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$As[];
            static final int $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$Id[];

            static 
            {
                $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$Id = new int[org.codehaus.jackson.annotate.JsonTypeInfo.Id.values().length];
                try
                {
                    $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$Id[org.codehaus.jackson.annotate.JsonTypeInfo.Id.CLASS.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$Id[org.codehaus.jackson.annotate.JsonTypeInfo.Id.MINIMAL_CLASS.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$Id[org.codehaus.jackson.annotate.JsonTypeInfo.Id.NAME.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$Id[org.codehaus.jackson.annotate.JsonTypeInfo.Id.CUSTOM.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$Id[org.codehaus.jackson.annotate.JsonTypeInfo.Id.NONE.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror4) { }
                $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$As = new int[org.codehaus.jackson.annotate.JsonTypeInfo.As.values().length];
                try
                {
                    $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$As[org.codehaus.jackson.annotate.JsonTypeInfo.As.WRAPPER_ARRAY.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$As[org.codehaus.jackson.annotate.JsonTypeInfo.As.PROPERTY.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$As[org.codehaus.jackson.annotate.JsonTypeInfo.As.WRAPPER_OBJECT.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror7)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.org.codehaus.jackson.annotate.JsonTypeInfo.As[_includeAs.ordinal()])
        {
        default:
            throw new IllegalStateException((new StringBuilder()).append("Do not know how to construct standard type serializer for inclusion type: ").append(_includeAs).toString());

        case 1: // '\001'
            return new AsArrayTypeDeserializer(javatype, typeidresolver, beanproperty);

        case 2: // '\002'
            return new AsPropertyTypeDeserializer(javatype, typeidresolver, beanproperty, _typeProperty);

        case 3: // '\003'
            return new AsWrapperTypeDeserializer(javatype, typeidresolver, beanproperty);
        }
    }

    public TypeSerializer buildTypeSerializer(SerializationConfig serializationconfig, JavaType javatype, Collection collection, BeanProperty beanproperty)
    {
        TypeIdResolver typeidresolver = idResolver(serializationconfig, javatype, collection, true, false);
        switch (_cls1..SwitchMap.org.codehaus.jackson.annotate.JsonTypeInfo.As[_includeAs.ordinal()])
        {
        default:
            throw new IllegalStateException((new StringBuilder()).append("Do not know how to construct standard type serializer for inclusion type: ").append(_includeAs).toString());

        case 1: // '\001'
            return new AsArrayTypeSerializer(typeidresolver, beanproperty);

        case 2: // '\002'
            return new AsPropertyTypeSerializer(typeidresolver, beanproperty, _typeProperty);

        case 3: // '\003'
            return new AsWrapperTypeSerializer(typeidresolver, beanproperty);
        }
    }

    public String getTypeProperty()
    {
        return _typeProperty;
    }

    protected TypeIdResolver idResolver(MapperConfig mapperconfig, JavaType javatype, Collection collection, boolean flag, boolean flag1)
    {
        if (_customIdResolver != null)
        {
            return _customIdResolver;
        }
        if (_idType == null)
        {
            throw new IllegalStateException("Can not build, 'init()' not yet called");
        }
        switch (_cls1..SwitchMap.org.codehaus.jackson.annotate.JsonTypeInfo.Id[_idType.ordinal()])
        {
        default:
            throw new IllegalStateException((new StringBuilder()).append("Do not know how to construct standard type id resolver for idType: ").append(_idType).toString());

        case 1: // '\001'
            return new ClassNameIdResolver(javatype, mapperconfig.getTypeFactory());

        case 2: // '\002'
            return new MinimalClassNameIdResolver(javatype, mapperconfig.getTypeFactory());

        case 3: // '\003'
            return TypeNameIdResolver.construct(mapperconfig, javatype, collection, flag, flag1);
        }
    }

    public volatile TypeResolverBuilder inclusion(org.codehaus.jackson.annotate.JsonTypeInfo.As as)
    {
        return inclusion(as);
    }

    public StdTypeResolverBuilder inclusion(org.codehaus.jackson.annotate.JsonTypeInfo.As as)
    {
        if (as == null)
        {
            throw new IllegalArgumentException("includeAs can not be null");
        } else
        {
            _includeAs = as;
            return this;
        }
    }

    public volatile TypeResolverBuilder init(org.codehaus.jackson.annotate.JsonTypeInfo.Id id, TypeIdResolver typeidresolver)
    {
        return init(id, typeidresolver);
    }

    public StdTypeResolverBuilder init(org.codehaus.jackson.annotate.JsonTypeInfo.Id id, TypeIdResolver typeidresolver)
    {
        if (id == null)
        {
            throw new IllegalArgumentException("idType can not be null");
        } else
        {
            _idType = id;
            _customIdResolver = typeidresolver;
            _typeProperty = id.getDefaultPropertyName();
            return this;
        }
    }

    public volatile TypeResolverBuilder typeProperty(String s)
    {
        return typeProperty(s);
    }

    public StdTypeResolverBuilder typeProperty(String s)
    {
        if (s == null || s.length() == 0)
        {
            s = _idType.getDefaultPropertyName();
        }
        _typeProperty = s;
        return this;
    }
}
