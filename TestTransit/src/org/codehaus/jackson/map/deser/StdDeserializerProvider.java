// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.AbstractTypeResolver;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.ContextualDeserializer;
import org.codehaus.jackson.map.ContextualKeyDeserializer;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerFactory;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.Deserializers;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.KeyDeserializers;
import org.codehaus.jackson.map.ResolvableDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdKeyDeserializers, BeanDeserializerFactory, BeanDeserializer, BeanDeserializerModifier

public class StdDeserializerProvider extends DeserializerProvider
{
    protected static final class WrappedDeserializer extends JsonDeserializer
    {

        final JsonDeserializer _deserializer;
        final TypeDeserializer _typeDeserializer;

        public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserializer.deserializeWithType(jsonparser, deserializationcontext, _typeDeserializer);
        }

        public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            throw new IllegalStateException("Type-wrapped deserializer's deserializeWithType should never get called");
        }

        public WrappedDeserializer(TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        {
            _typeDeserializer = typedeserializer;
            _deserializer = jsondeserializer;
        }
    }


    static final HashMap _keyDeserializers = StdKeyDeserializers.constructAll();
    protected final ConcurrentHashMap _cachedDeserializers;
    protected DeserializerFactory _factory;
    protected final HashMap _incompleteDeserializers;

    public StdDeserializerProvider()
    {
        this(((DeserializerFactory) (BeanDeserializerFactory.instance)));
    }

    public StdDeserializerProvider(DeserializerFactory deserializerfactory)
    {
        _cachedDeserializers = new ConcurrentHashMap(64, 0.75F, 2);
        _incompleteDeserializers = new HashMap(8);
        _factory = deserializerfactory;
    }

    protected JsonDeserializer _createAndCache2(DeserializationConfig deserializationconfig, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer;
        JsonDeserializer jsondeserializer1;
        try
        {
            jsondeserializer = _createDeserializer(deserializationconfig, javatype, beanproperty);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw new JsonMappingException(illegalargumentexception.getMessage(), null, illegalargumentexception);
        }
        jsondeserializer1 = jsondeserializer;
        if (jsondeserializer1 == null)
        {
            jsondeserializer1 = null;
        } else
        {
            boolean flag = jsondeserializer1 instanceof ResolvableDeserializer;
            boolean flag1;
            if (jsondeserializer1.getClass() == org/codehaus/jackson/map/deser/BeanDeserializer)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1 && deserializationconfig.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.USE_ANNOTATIONS))
            {
                AnnotationIntrospector annotationintrospector = deserializationconfig.getAnnotationIntrospector();
                Boolean boolean1 = annotationintrospector.findCachability(AnnotatedClass.construct(jsondeserializer1.getClass(), annotationintrospector, null));
                if (boolean1 != null)
                {
                    flag1 = boolean1.booleanValue();
                }
            }
            if (flag)
            {
                _incompleteDeserializers.put(javatype, jsondeserializer1);
                _resolveDeserializer(deserializationconfig, (ResolvableDeserializer)jsondeserializer1);
                _incompleteDeserializers.remove(javatype);
            }
            if (flag1)
            {
                _cachedDeserializers.put(javatype, jsondeserializer1);
                return jsondeserializer1;
            }
        }
        return jsondeserializer1;
    }

    protected JsonDeserializer _createAndCacheValueDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        HashMap hashmap = _incompleteDeserializers;
        hashmap;
        JVM INSTR monitorenter ;
        JsonDeserializer jsondeserializer = _findCachedDeserializer(javatype);
        if (jsondeserializer == null)
        {
            break MISSING_BLOCK_LABEL_27;
        }
        hashmap;
        JVM INSTR monitorexit ;
        return jsondeserializer;
        int i = _incompleteDeserializers.size();
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_65;
        }
        JsonDeserializer jsondeserializer1 = (JsonDeserializer)_incompleteDeserializers.get(javatype);
        if (jsondeserializer1 == null)
        {
            break MISSING_BLOCK_LABEL_65;
        }
        hashmap;
        JVM INSTR monitorexit ;
        return jsondeserializer1;
        JsonDeserializer jsondeserializer2 = _createAndCache2(deserializationconfig, javatype, beanproperty);
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_96;
        }
        if (_incompleteDeserializers.size() > 0)
        {
            _incompleteDeserializers.clear();
        }
        hashmap;
        JVM INSTR monitorexit ;
        return jsondeserializer2;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        Exception exception1;
        exception1;
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_134;
        }
        if (_incompleteDeserializers.size() > 0)
        {
            _incompleteDeserializers.clear();
        }
        throw exception1;
    }

    protected JsonDeserializer _createDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        if (javatype.isEnumType())
        {
            return _factory.createEnumDeserializer(deserializationconfig, this, javatype, beanproperty);
        }
        if (javatype.isContainerType())
        {
            if (javatype.isArrayType())
            {
                return _factory.createArrayDeserializer(deserializationconfig, this, (ArrayType)javatype, beanproperty);
            }
            if (javatype.isMapLikeType())
            {
                MapLikeType mapliketype = (MapLikeType)javatype;
                if (mapliketype.isTrueMapType())
                {
                    return _factory.createMapDeserializer(deserializationconfig, this, (MapType)mapliketype, beanproperty);
                } else
                {
                    return _factory.createMapLikeDeserializer(deserializationconfig, this, mapliketype, beanproperty);
                }
            }
            if (javatype.isCollectionLikeType())
            {
                CollectionLikeType collectionliketype = (CollectionLikeType)javatype;
                if (collectionliketype.isTrueCollectionType())
                {
                    return _factory.createCollectionDeserializer(deserializationconfig, this, (CollectionType)collectionliketype, beanproperty);
                } else
                {
                    return _factory.createCollectionLikeDeserializer(deserializationconfig, this, collectionliketype, beanproperty);
                }
            }
        }
        if (org/codehaus/jackson/JsonNode.isAssignableFrom(javatype.getRawClass()))
        {
            return _factory.createTreeDeserializer(deserializationconfig, this, javatype, beanproperty);
        } else
        {
            return _factory.createBeanDeserializer(deserializationconfig, this, javatype, beanproperty);
        }
    }

    protected JsonDeserializer _findCachedDeserializer(JavaType javatype)
    {
        return (JsonDeserializer)_cachedDeserializers.get(javatype);
    }

    protected KeyDeserializer _handleUnknownKeyDeserializer(JavaType javatype)
        throws JsonMappingException
    {
        throw new JsonMappingException((new StringBuilder()).append("Can not find a (Map) Key deserializer for type ").append(javatype).toString());
    }

    protected JsonDeserializer _handleUnknownValueDeserializer(JavaType javatype)
        throws JsonMappingException
    {
        if (!ClassUtil.isConcrete(javatype.getRawClass()))
        {
            throw new JsonMappingException((new StringBuilder()).append("Can not find a Value deserializer for abstract type ").append(javatype).toString());
        } else
        {
            throw new JsonMappingException((new StringBuilder()).append("Can not find a Value deserializer for type ").append(javatype).toString());
        }
    }

    protected void _resolveDeserializer(DeserializationConfig deserializationconfig, ResolvableDeserializer resolvabledeserializer)
        throws JsonMappingException
    {
        resolvabledeserializer.resolve(deserializationconfig, this);
    }

    public int cachedDeserializersCount()
    {
        return _cachedDeserializers.size();
    }

    public KeyDeserializer findKeyDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        KeyDeserializer keydeserializer;
        Class class1;
        keydeserializer = _factory.createKeyDeserializer(deserializationconfig, javatype, beanproperty);
        if (keydeserializer != null)
        {
            break MISSING_BLOCK_LABEL_97;
        }
        class1 = javatype.getRawClass();
        if (class1 != java/lang/String && class1 != java/lang/Object) goto _L2; else goto _L1
_L1:
        KeyDeserializer keydeserializer1 = null;
_L4:
        return keydeserializer1;
_L2:
        keydeserializer1 = (KeyDeserializer)_keyDeserializers.get(javatype);
        if (keydeserializer1 != null) goto _L4; else goto _L3
_L3:
        if (javatype.isEnumType())
        {
            return StdKeyDeserializers.constructEnumKeyDeserializer(deserializationconfig, javatype);
        }
        keydeserializer1 = StdKeyDeserializers.findStringBasedKeyDeserializer(deserializationconfig, javatype);
        if (keydeserializer1 != null) goto _L4; else goto _L5
_L5:
        if (keydeserializer == null)
        {
            return _handleUnknownKeyDeserializer(javatype);
        }
        if (keydeserializer instanceof ContextualKeyDeserializer)
        {
            keydeserializer = ((ContextualKeyDeserializer)keydeserializer).createContextual(deserializationconfig, beanproperty);
        }
        return keydeserializer;
    }

    public JsonDeserializer findTypedValueDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        Object obj = findValueDeserializer(deserializationconfig, javatype, beanproperty);
        TypeDeserializer typedeserializer = _factory.findTypeDeserializer(deserializationconfig, javatype, beanproperty);
        if (typedeserializer != null)
        {
            obj = new WrappedDeserializer(typedeserializer, ((JsonDeserializer) (obj)));
        }
        return ((JsonDeserializer) (obj));
    }

    public JsonDeserializer findValueDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = _findCachedDeserializer(javatype);
        if (jsondeserializer != null)
        {
            if (jsondeserializer instanceof ContextualDeserializer)
            {
                jsondeserializer = ((ContextualDeserializer)jsondeserializer).createContextual(deserializationconfig, beanproperty);
            }
            return jsondeserializer;
        }
        JsonDeserializer jsondeserializer1 = _createAndCacheValueDeserializer(deserializationconfig, javatype, beanproperty);
        if (jsondeserializer1 == null)
        {
            jsondeserializer1 = _handleUnknownValueDeserializer(javatype);
        }
        if (jsondeserializer1 instanceof ContextualDeserializer)
        {
            jsondeserializer1 = ((ContextualDeserializer)jsondeserializer1).createContextual(deserializationconfig, beanproperty);
        }
        return jsondeserializer1;
    }

    public void flushCachedDeserializers()
    {
        _cachedDeserializers.clear();
    }

    public boolean hasValueDeserializerFor(DeserializationConfig deserializationconfig, JavaType javatype)
    {
        JsonDeserializer jsondeserializer = _findCachedDeserializer(javatype);
        if (jsondeserializer == null)
        {
            boolean flag;
            JsonDeserializer jsondeserializer1;
            try
            {
                jsondeserializer1 = _createAndCacheValueDeserializer(deserializationconfig, javatype, null);
            }
            catch (Exception exception)
            {
                return false;
            }
            jsondeserializer = jsondeserializer1;
        }
        flag = false;
        if (jsondeserializer != null)
        {
            flag = true;
        }
        return flag;
    }

    public DeserializerProvider withAbstractTypeResolver(AbstractTypeResolver abstracttyperesolver)
    {
        _factory = _factory.withAbstractTypeResolver(abstracttyperesolver);
        return this;
    }

    public DeserializerProvider withAdditionalDeserializers(Deserializers deserializers)
    {
        _factory = _factory.withAdditionalDeserializers(deserializers);
        return this;
    }

    public DeserializerProvider withAdditionalKeyDeserializers(KeyDeserializers keydeserializers)
    {
        _factory = _factory.withAdditionalKeyDeserializers(keydeserializers);
        return this;
    }

    public DeserializerProvider withDeserializerModifier(BeanDeserializerModifier beandeserializermodifier)
    {
        _factory = _factory.withDeserializerModifier(beandeserializermodifier);
        return this;
    }

}
