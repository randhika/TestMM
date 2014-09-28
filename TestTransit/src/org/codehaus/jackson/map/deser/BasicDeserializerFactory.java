// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.ContextualDeserializer;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializerFactory;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.deser.impl.StringCollectionDeserializer;
import org.codehaus.jackson.map.ext.OptionalHandlerFactory;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.AnnotatedParameter;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.jsontype.SubtypeResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdDeserializers, ArrayDeserializers, SettableBeanProperty, EnumResolver, 
//            ArrayDeserializer, EnumSetDeserializer, CollectionDeserializer, EnumDeserializer, 
//            EnumMapDeserializer, MapDeserializer, JsonNodeDeserializer, CreatorContainer

public abstract class BasicDeserializerFactory extends DeserializerFactory
{

    protected static final HashMap _arrayDeserializers = ArrayDeserializers.getAll();
    static final HashMap _collectionFallbacks;
    static final HashMap _mapFallbacks;
    static final HashMap _simpleDeserializers = StdDeserializers.constructAll();
    protected OptionalHandlerFactory optionalHandlers;

    protected BasicDeserializerFactory()
    {
        optionalHandlers = OptionalHandlerFactory.instance;
    }

    JsonDeserializer _constructDeserializer(DeserializationConfig deserializationconfig, Annotated annotated, BeanProperty beanproperty, Object obj)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer;
        if (obj instanceof JsonDeserializer)
        {
            jsondeserializer = (JsonDeserializer)obj;
            if (jsondeserializer instanceof ContextualDeserializer)
            {
                jsondeserializer = ((ContextualDeserializer)jsondeserializer).createContextual(deserializationconfig, beanproperty);
            }
        } else
        {
            if (!(obj instanceof Class))
            {
                throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector returned deserializer definition of type ").append(obj.getClass().getName()).append("; expected type JsonDeserializer or Class<JsonDeserializer> instead").toString());
            }
            Class class1 = (Class)obj;
            if (!org/codehaus/jackson/map/JsonDeserializer.isAssignableFrom(class1))
            {
                throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector returned Class ").append(class1.getName()).append("; expected Class<JsonDeserializer>").toString());
            }
            jsondeserializer = deserializationconfig.deserializerInstance(annotated, class1);
            if (jsondeserializer instanceof ContextualDeserializer)
            {
                return ((ContextualDeserializer)jsondeserializer).createContextual(deserializationconfig, beanproperty);
            }
        }
        return jsondeserializer;
    }

    protected abstract JsonDeserializer _findCustomArrayDeserializer(ArrayType arraytype, DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, BeanProperty beanproperty, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        throws JsonMappingException;

    protected abstract JsonDeserializer _findCustomCollectionDeserializer(CollectionType collectiontype, DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        throws JsonMappingException;

    protected abstract JsonDeserializer _findCustomCollectionLikeDeserializer(CollectionLikeType collectionliketype, DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        throws JsonMappingException;

    protected abstract JsonDeserializer _findCustomEnumDeserializer(Class class1, DeserializationConfig deserializationconfig, BasicBeanDescription basicbeandescription, BeanProperty beanproperty)
        throws JsonMappingException;

    protected abstract JsonDeserializer _findCustomMapDeserializer(MapType maptype, DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, KeyDeserializer keydeserializer, TypeDeserializer typedeserializer, 
            JsonDeserializer jsondeserializer)
        throws JsonMappingException;

    protected abstract JsonDeserializer _findCustomMapLikeDeserializer(MapLikeType mapliketype, DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, KeyDeserializer keydeserializer, TypeDeserializer typedeserializer, 
            JsonDeserializer jsondeserializer)
        throws JsonMappingException;

    protected abstract JsonDeserializer _findCustomTreeNodeDeserializer(Class class1, DeserializationConfig deserializationconfig, BeanProperty beanproperty)
        throws JsonMappingException;

    protected SettableBeanProperty constructCreatorProperty(DeserializationConfig deserializationconfig, BasicBeanDescription basicbeandescription, String s, int i, AnnotatedParameter annotatedparameter)
        throws JsonMappingException
    {
        JavaType javatype = deserializationconfig.getTypeFactory().constructType(annotatedparameter.getParameterType(), basicbeandescription.bindingsForBeanType());
        org.codehaus.jackson.map.BeanProperty.Std std = new org.codehaus.jackson.map.BeanProperty.Std(s, javatype, basicbeandescription.getClassAnnotations(), annotatedparameter);
        JavaType javatype1 = resolveType(deserializationconfig, basicbeandescription, javatype, annotatedparameter, std);
        if (javatype1 != javatype)
        {
            std = std.withType(javatype1);
        }
        JsonDeserializer jsondeserializer = findDeserializerFromAnnotation(deserializationconfig, annotatedparameter, std);
        JavaType javatype2 = modifyTypeByAnnotation(deserializationconfig, annotatedparameter, javatype1, s);
        SettableBeanProperty.CreatorProperty creatorproperty = new SettableBeanProperty.CreatorProperty(s, javatype2, findTypeDeserializer(deserializationconfig, javatype2, std), basicbeandescription.getClassAnnotations(), annotatedparameter, i);
        if (jsondeserializer != null)
        {
            creatorproperty.setValueDeserializer(jsondeserializer);
        }
        return creatorproperty;
    }

    protected EnumResolver constructEnumResolver(Class class1, DeserializationConfig deserializationconfig)
    {
        if (deserializationconfig.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING))
        {
            return EnumResolver.constructUnsafeUsingToString(class1);
        } else
        {
            return EnumResolver.constructUnsafe(class1, deserializationconfig.getAnnotationIntrospector());
        }
    }

    public JsonDeserializer createArrayDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, ArrayType arraytype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JavaType javatype = arraytype.getContentType();
        JsonDeserializer jsondeserializer = (JsonDeserializer)javatype.getValueHandler();
        if (jsondeserializer == null)
        {
            JsonDeserializer jsondeserializer2 = (JsonDeserializer)_arrayDeserializers.get(javatype);
            if (jsondeserializer2 != null)
            {
                JsonDeserializer jsondeserializer3 = _findCustomArrayDeserializer(arraytype, deserializationconfig, deserializerprovider, beanproperty, null, null);
                if (jsondeserializer3 != null)
                {
                    jsondeserializer2 = jsondeserializer3;
                }
                return jsondeserializer2;
            }
            if (javatype.isPrimitive())
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Internal error: primitive type (").append(arraytype).append(") passed, no array deserializer found").toString());
            }
        }
        TypeDeserializer typedeserializer = (TypeDeserializer)javatype.getTypeHandler();
        if (typedeserializer == null)
        {
            typedeserializer = findTypeDeserializer(deserializationconfig, javatype, beanproperty);
        }
        JsonDeserializer jsondeserializer1 = _findCustomArrayDeserializer(arraytype, deserializationconfig, deserializerprovider, beanproperty, typedeserializer, jsondeserializer);
        if (jsondeserializer1 != null)
        {
            return jsondeserializer1;
        }
        if (jsondeserializer == null)
        {
            jsondeserializer = deserializerprovider.findValueDeserializer(deserializationconfig, javatype, beanproperty);
        }
        return new ArrayDeserializer(arraytype, jsondeserializer, typedeserializer);
    }

    public JsonDeserializer createCollectionDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, CollectionType collectiontype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        CollectionType collectiontype1 = (CollectionType)mapAbstractType(deserializationconfig, collectiontype);
        Class class1 = collectiontype1.getRawClass();
        BasicBeanDescription basicbeandescription = (BasicBeanDescription)deserializationconfig.introspectClassAnnotations(class1);
        JsonDeserializer jsondeserializer = findDeserializerFromAnnotation(deserializationconfig, basicbeandescription.getClassInfo(), beanproperty);
        if (jsondeserializer != null)
        {
            return jsondeserializer;
        }
        CollectionType collectiontype2 = (CollectionType)modifyTypeByAnnotation(deserializationconfig, basicbeandescription.getClassInfo(), collectiontype1, null);
        JavaType javatype = collectiontype2.getContentType();
        JsonDeserializer jsondeserializer1 = (JsonDeserializer)javatype.getValueHandler();
        TypeDeserializer typedeserializer = (TypeDeserializer)javatype.getTypeHandler();
        if (typedeserializer == null)
        {
            typedeserializer = findTypeDeserializer(deserializationconfig, javatype, beanproperty);
        }
        JsonDeserializer jsondeserializer2 = _findCustomCollectionDeserializer(collectiontype2, deserializationconfig, deserializerprovider, basicbeandescription, beanproperty, typedeserializer, jsondeserializer1);
        if (jsondeserializer2 != null)
        {
            return jsondeserializer2;
        }
        if (jsondeserializer1 == null)
        {
            if (java/util/EnumSet.isAssignableFrom(class1))
            {
                return new EnumSetDeserializer(constructEnumResolver(javatype.getRawClass(), deserializationconfig));
            }
            jsondeserializer1 = deserializerprovider.findValueDeserializer(deserializationconfig, javatype, beanproperty);
        }
        if (collectiontype2.isInterface() || collectiontype2.isAbstract())
        {
            Class class2 = (Class)_collectionFallbacks.get(class1.getName());
            if (class2 == null)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Can not find a deserializer for non-concrete Collection type ").append(collectiontype2).toString());
            }
            class1 = class2;
        }
        java.lang.reflect.Constructor constructor = ClassUtil.findConstructor(class1, deserializationconfig.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS));
        if (javatype.getRawClass() == java/lang/String)
        {
            return new StringCollectionDeserializer(collectiontype2, jsondeserializer1, constructor);
        } else
        {
            return new CollectionDeserializer(collectiontype2, jsondeserializer1, typedeserializer, constructor);
        }
    }

    public JsonDeserializer createCollectionLikeDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, CollectionLikeType collectionliketype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        CollectionLikeType collectionliketype1 = (CollectionLikeType)mapAbstractType(deserializationconfig, collectionliketype);
        BasicBeanDescription basicbeandescription = (BasicBeanDescription)deserializationconfig.introspectClassAnnotations(collectionliketype1.getRawClass());
        JsonDeserializer jsondeserializer = findDeserializerFromAnnotation(deserializationconfig, basicbeandescription.getClassInfo(), beanproperty);
        if (jsondeserializer != null)
        {
            return jsondeserializer;
        }
        CollectionLikeType collectionliketype2 = (CollectionLikeType)modifyTypeByAnnotation(deserializationconfig, basicbeandescription.getClassInfo(), collectionliketype1, null);
        JavaType javatype = collectionliketype2.getContentType();
        JsonDeserializer jsondeserializer1 = (JsonDeserializer)javatype.getValueHandler();
        TypeDeserializer typedeserializer = (TypeDeserializer)javatype.getTypeHandler();
        if (typedeserializer == null)
        {
            typedeserializer = findTypeDeserializer(deserializationconfig, javatype, beanproperty);
        }
        return _findCustomCollectionLikeDeserializer(collectionliketype2, deserializationconfig, deserializerprovider, basicbeandescription, beanproperty, typedeserializer, jsondeserializer1);
    }

    public JsonDeserializer createEnumDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        BasicBeanDescription basicbeandescription = (BasicBeanDescription)deserializationconfig.introspectForCreation(javatype);
        JsonDeserializer jsondeserializer = findDeserializerFromAnnotation(deserializationconfig, basicbeandescription.getClassInfo(), beanproperty);
        if (jsondeserializer != null)
        {
            return jsondeserializer;
        }
        Class class1 = javatype.getRawClass();
        JsonDeserializer jsondeserializer1 = _findCustomEnumDeserializer(class1, deserializationconfig, basicbeandescription, beanproperty);
        if (jsondeserializer1 != null)
        {
            return jsondeserializer1;
        }
        for (Iterator iterator = basicbeandescription.getFactoryMethods().iterator(); iterator.hasNext();)
        {
            AnnotatedMethod annotatedmethod = (AnnotatedMethod)iterator.next();
            if (deserializationconfig.getAnnotationIntrospector().hasCreatorAnnotation(annotatedmethod))
            {
                if (annotatedmethod.getParameterCount() == 1 && annotatedmethod.getRawType().isAssignableFrom(class1))
                {
                    return EnumDeserializer.deserializerForCreator(deserializationconfig, class1, annotatedmethod);
                } else
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Unsuitable method (").append(annotatedmethod).append(") decorated with @JsonCreator (for Enum type ").append(class1.getName()).append(")").toString());
                }
            }
        }

        return new EnumDeserializer(constructEnumResolver(class1, deserializationconfig));
    }

    public JsonDeserializer createMapDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, MapType maptype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        MapType maptype1 = (MapType)mapAbstractType(deserializationconfig, maptype);
        BasicBeanDescription basicbeandescription = (BasicBeanDescription)deserializationconfig.introspectForCreation(maptype1);
        JsonDeserializer jsondeserializer = findDeserializerFromAnnotation(deserializationconfig, basicbeandescription.getClassInfo(), beanproperty);
        if (jsondeserializer != null)
        {
            return jsondeserializer;
        }
        MapType maptype2 = (MapType)modifyTypeByAnnotation(deserializationconfig, basicbeandescription.getClassInfo(), maptype1, null);
        JavaType javatype = maptype2.getKeyType();
        JavaType javatype1 = maptype2.getContentType();
        JsonDeserializer jsondeserializer1 = (JsonDeserializer)javatype1.getValueHandler();
        KeyDeserializer keydeserializer = (KeyDeserializer)javatype.getValueHandler();
        if (keydeserializer == null)
        {
            keydeserializer = deserializerprovider.findKeyDeserializer(deserializationconfig, javatype, beanproperty);
        }
        TypeDeserializer typedeserializer = (TypeDeserializer)javatype1.getTypeHandler();
        if (typedeserializer == null)
        {
            typedeserializer = findTypeDeserializer(deserializationconfig, javatype1, beanproperty);
        }
        JsonDeserializer jsondeserializer2 = _findCustomMapDeserializer(maptype2, deserializationconfig, deserializerprovider, basicbeandescription, beanproperty, keydeserializer, typedeserializer, jsondeserializer1);
        if (jsondeserializer2 != null)
        {
            return jsondeserializer2;
        }
        if (jsondeserializer1 == null)
        {
            jsondeserializer1 = deserializerprovider.findValueDeserializer(deserializationconfig, javatype1, beanproperty);
        }
        Class class1 = maptype2.getRawClass();
        if (java/util/EnumMap.isAssignableFrom(class1))
        {
            Class class3 = javatype.getRawClass();
            if (class3 == null || !class3.isEnum())
            {
                throw new IllegalArgumentException("Can not construct EnumMap; generic (key) type not available");
            } else
            {
                EnumMapDeserializer enummapdeserializer = new EnumMapDeserializer(constructEnumResolver(class3, deserializationconfig), jsondeserializer1);
                return enummapdeserializer;
            }
        }
        if (maptype2.isInterface() || maptype2.isAbstract())
        {
            Class class2 = (Class)_mapFallbacks.get(class1.getName());
            if (class2 == null)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Can not find a deserializer for non-concrete Map type ").append(maptype2).toString());
            }
            maptype2 = (MapType)maptype2.forcedNarrowBy(class2);
            basicbeandescription = (BasicBeanDescription)deserializationconfig.introspectForCreation(maptype2);
        }
        boolean flag = deserializationconfig.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
        java.lang.reflect.Constructor constructor = basicbeandescription.findDefaultConstructor();
        if (constructor != null && flag)
        {
            ClassUtil.checkAndFixAccess(constructor);
        }
        MapDeserializer mapdeserializer = new MapDeserializer(maptype2, constructor, keydeserializer, jsondeserializer1, typedeserializer);
        mapdeserializer.setIgnorableProperties(deserializationconfig.getAnnotationIntrospector().findPropertiesToIgnore(basicbeandescription.getClassInfo()));
        mapdeserializer.setCreators(findMapCreators(deserializationconfig, basicbeandescription));
        return mapdeserializer;
    }

    public JsonDeserializer createMapLikeDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, MapLikeType mapliketype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        MapLikeType mapliketype1 = (MapLikeType)mapAbstractType(deserializationconfig, mapliketype);
        BasicBeanDescription basicbeandescription = (BasicBeanDescription)deserializationconfig.introspectForCreation(mapliketype1);
        JsonDeserializer jsondeserializer = findDeserializerFromAnnotation(deserializationconfig, basicbeandescription.getClassInfo(), beanproperty);
        if (jsondeserializer != null)
        {
            return jsondeserializer;
        }
        MapLikeType mapliketype2 = (MapLikeType)modifyTypeByAnnotation(deserializationconfig, basicbeandescription.getClassInfo(), mapliketype1, null);
        JavaType javatype = mapliketype2.getKeyType();
        JavaType javatype1 = mapliketype2.getContentType();
        JsonDeserializer jsondeserializer1 = (JsonDeserializer)javatype1.getValueHandler();
        KeyDeserializer keydeserializer = (KeyDeserializer)javatype.getValueHandler();
        if (keydeserializer == null)
        {
            keydeserializer = deserializerprovider.findKeyDeserializer(deserializationconfig, javatype, beanproperty);
        }
        TypeDeserializer typedeserializer = (TypeDeserializer)javatype1.getTypeHandler();
        if (typedeserializer == null)
        {
            typedeserializer = findTypeDeserializer(deserializationconfig, javatype1, beanproperty);
        }
        return _findCustomMapLikeDeserializer(mapliketype2, deserializationconfig, deserializerprovider, basicbeandescription, beanproperty, keydeserializer, typedeserializer, jsondeserializer1);
    }

    public JsonDeserializer createTreeDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        Class class1 = javatype.getRawClass();
        JsonDeserializer jsondeserializer = _findCustomTreeNodeDeserializer(class1, deserializationconfig, beanproperty);
        if (jsondeserializer != null)
        {
            return jsondeserializer;
        } else
        {
            return JsonNodeDeserializer.getDeserializer(class1);
        }
    }

    protected JsonDeserializer findDeserializerFromAnnotation(DeserializationConfig deserializationconfig, Annotated annotated, BeanProperty beanproperty)
        throws JsonMappingException
    {
        Object obj = deserializationconfig.getAnnotationIntrospector().findDeserializer(annotated);
        if (obj != null)
        {
            return _constructDeserializer(deserializationconfig, annotated, beanproperty, obj);
        } else
        {
            return null;
        }
    }

    protected CreatorContainer findMapCreators(DeserializationConfig deserializationconfig, BasicBeanDescription basicbeandescription)
        throws JsonMappingException
    {
        AnnotationIntrospector annotationintrospector = deserializationconfig.getAnnotationIntrospector();
        CreatorContainer creatorcontainer = new CreatorContainer(basicbeandescription, deserializationconfig.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS));
        Iterator iterator = basicbeandescription.getConstructors().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            AnnotatedConstructor annotatedconstructor = (AnnotatedConstructor)iterator.next();
            int l = annotatedconstructor.getParameterCount();
            if (l >= 1 && annotationintrospector.hasCreatorAnnotation(annotatedconstructor))
            {
                SettableBeanProperty asettablebeanproperty1[] = new SettableBeanProperty[l];
                int i1 = 0;
                for (int j1 = 0; j1 < l; j1++)
                {
                    AnnotatedParameter annotatedparameter1 = annotatedconstructor.getParameter(j1);
                    String s1;
                    if (annotatedparameter1 == null)
                    {
                        s1 = null;
                    } else
                    {
                        s1 = annotationintrospector.findPropertyNameForParam(annotatedparameter1);
                    }
                    if (s1 == null || s1.length() == 0)
                    {
                        throw new IllegalArgumentException((new StringBuilder()).append("Parameter #").append(j1).append(" of constructor ").append(annotatedconstructor).append(" has no property name annotation: must have for @JsonCreator for a Map type").toString());
                    }
                    i1++;
                    asettablebeanproperty1[j1] = constructCreatorProperty(deserializationconfig, basicbeandescription, s1, j1, annotatedparameter1);
                }

                creatorcontainer.addPropertyConstructor(annotatedconstructor, asettablebeanproperty1);
            }
        } while (true);
        Iterator iterator1 = basicbeandescription.getFactoryMethods().iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            AnnotatedMethod annotatedmethod = (AnnotatedMethod)iterator1.next();
            int i = annotatedmethod.getParameterCount();
            if (i >= 1 && annotationintrospector.hasCreatorAnnotation(annotatedmethod))
            {
                SettableBeanProperty asettablebeanproperty[] = new SettableBeanProperty[i];
                int j = 0;
                for (int k = 0; k < i; k++)
                {
                    AnnotatedParameter annotatedparameter = annotatedmethod.getParameter(k);
                    String s;
                    if (annotatedparameter == null)
                    {
                        s = null;
                    } else
                    {
                        s = annotationintrospector.findPropertyNameForParam(annotatedparameter);
                    }
                    if (s == null || s.length() == 0)
                    {
                        throw new IllegalArgumentException((new StringBuilder()).append("Parameter #").append(k).append(" of factory method ").append(annotatedmethod).append(" has no property name annotation: must have for @JsonCreator for a Map type").toString());
                    }
                    j++;
                    asettablebeanproperty[k] = constructCreatorProperty(deserializationconfig, basicbeandescription, s, k, annotatedparameter);
                }

                creatorcontainer.addPropertyFactory(annotatedmethod, asettablebeanproperty);
            }
        } while (true);
        return creatorcontainer;
    }

    public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, AnnotatedMember annotatedmember, BeanProperty beanproperty)
    {
        AnnotationIntrospector annotationintrospector = deserializationconfig.getAnnotationIntrospector();
        TypeResolverBuilder typeresolverbuilder = annotationintrospector.findPropertyContentTypeResolver(deserializationconfig, annotatedmember, javatype);
        JavaType javatype1 = javatype.getContentType();
        if (typeresolverbuilder == null)
        {
            return findTypeDeserializer(deserializationconfig, javatype1, beanproperty);
        } else
        {
            return typeresolverbuilder.buildTypeDeserializer(deserializationconfig, javatype1, deserializationconfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedmember, deserializationconfig, annotationintrospector), beanproperty);
        }
    }

    public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, AnnotatedMember annotatedmember, BeanProperty beanproperty)
    {
        AnnotationIntrospector annotationintrospector = deserializationconfig.getAnnotationIntrospector();
        TypeResolverBuilder typeresolverbuilder = annotationintrospector.findPropertyTypeResolver(deserializationconfig, annotatedmember, javatype);
        if (typeresolverbuilder == null)
        {
            return findTypeDeserializer(deserializationconfig, javatype, beanproperty);
        } else
        {
            return typeresolverbuilder.buildTypeDeserializer(deserializationconfig, javatype, deserializationconfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedmember, deserializationconfig, annotationintrospector), beanproperty);
        }
    }

    protected JsonDeserializer findStdBeanDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = (JsonDeserializer)_simpleDeserializers.get(javatype);
        if (jsondeserializer != null)
        {
            return jsondeserializer;
        }
        Class class1 = javatype.getRawClass();
        if (class1 == java/lang/Class)
        {
            return new StdDeserializer.ClassDeserializer();
        }
        if (java/util/concurrent/atomic/AtomicReference.isAssignableFrom(class1))
        {
            JavaType ajavatype[] = deserializationconfig.getTypeFactory().findTypeParameters(javatype, java/util/concurrent/atomic/AtomicReference);
            JavaType javatype1;
            if (ajavatype == null || ajavatype.length < 1)
            {
                javatype1 = TypeFactory.unknownType();
            } else
            {
                javatype1 = ajavatype[0];
            }
            return new StdDeserializer.AtomicReferenceDeserializer(javatype1, beanproperty);
        }
        JsonDeserializer jsondeserializer1 = optionalHandlers.findDeserializer(javatype, deserializationconfig, deserializerprovider);
        if (jsondeserializer1 != null)
        {
            return jsondeserializer1;
        } else
        {
            return null;
        }
    }

    public TypeDeserializer findTypeDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, BeanProperty beanproperty)
    {
        org.codehaus.jackson.map.introspect.AnnotatedClass annotatedclass = ((BasicBeanDescription)deserializationconfig.introspectClassAnnotations(javatype.getRawClass())).getClassInfo();
        AnnotationIntrospector annotationintrospector = deserializationconfig.getAnnotationIntrospector();
        TypeResolverBuilder typeresolverbuilder = annotationintrospector.findTypeResolver(deserializationconfig, annotatedclass, javatype);
        Collection collection;
        if (typeresolverbuilder == null)
        {
            typeresolverbuilder = deserializationconfig.getDefaultTyper(javatype);
            collection = null;
            if (typeresolverbuilder == null)
            {
                return null;
            }
        } else
        {
            collection = deserializationconfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedclass, deserializationconfig, annotationintrospector);
        }
        return typeresolverbuilder.buildTypeDeserializer(deserializationconfig, javatype, collection, beanproperty);
    }

    protected abstract JavaType mapAbstractType(DeserializationConfig deserializationconfig, JavaType javatype)
        throws JsonMappingException;

    protected JavaType modifyTypeByAnnotation(DeserializationConfig deserializationconfig, Annotated annotated, JavaType javatype, String s)
        throws JsonMappingException
    {
        AnnotationIntrospector annotationintrospector = deserializationconfig.getAnnotationIntrospector();
        Class class1 = annotationintrospector.findDeserializationType(annotated, javatype, s);
        if (class1 != null)
        {
            JavaType javatype4;
            try
            {
                javatype4 = javatype.narrowBy(class1);
            }
            catch (IllegalArgumentException illegalargumentexception2)
            {
                throw new JsonMappingException((new StringBuilder()).append("Failed to narrow type ").append(javatype).append(" with concrete-type annotation (value ").append(class1.getName()).append("), method '").append(annotated.getName()).append("': ").append(illegalargumentexception2.getMessage()).toString(), null, illegalargumentexception2);
            }
            javatype = javatype4;
        }
        if (javatype.isContainerType())
        {
            Class class2 = annotationintrospector.findDeserializationKeyType(annotated, javatype.getKeyType(), s);
            Class class3;
            if (class2 != null)
            {
                if (!(javatype instanceof MapType))
                {
                    throw new JsonMappingException((new StringBuilder()).append("Illegal key-type annotation: type ").append(javatype).append(" is not a Map type").toString());
                }
                JavaType javatype1;
                Class class4;
                JsonDeserializer jsondeserializer;
                JavaType javatype2;
                Class class5;
                JavaType javatype3;
                try
                {
                    javatype3 = ((MapType)javatype).narrowKey(class2);
                }
                catch (IllegalArgumentException illegalargumentexception1)
                {
                    throw new JsonMappingException((new StringBuilder()).append("Failed to narrow key type ").append(javatype).append(" with key-type annotation (").append(class2.getName()).append("): ").append(illegalargumentexception1.getMessage()).toString(), null, illegalargumentexception1);
                }
                javatype = javatype3;
            }
            javatype1 = javatype.getKeyType();
            if (javatype1 != null && javatype1.getValueHandler() == null)
            {
                class5 = annotationintrospector.findKeyDeserializer(annotated);
                if (class5 != null && class5 != org/codehaus/jackson/map/KeyDeserializer$None)
                {
                    javatype1.setValueHandler(deserializationconfig.keyDeserializerInstance(annotated, class5));
                }
            }
            class3 = annotationintrospector.findDeserializationContentType(annotated, javatype.getContentType(), s);
            if (class3 != null)
            {
                try
                {
                    javatype2 = javatype.narrowContentsBy(class3);
                }
                catch (IllegalArgumentException illegalargumentexception)
                {
                    throw new JsonMappingException((new StringBuilder()).append("Failed to narrow content type ").append(javatype).append(" with content-type annotation (").append(class3.getName()).append("): ").append(illegalargumentexception.getMessage()).toString(), null, illegalargumentexception);
                }
                javatype = javatype2;
            }
            if (javatype.getContentType().getValueHandler() == null)
            {
                class4 = annotationintrospector.findContentDeserializer(annotated);
                if (class4 != null && class4 != org/codehaus/jackson/map/JsonDeserializer$None)
                {
                    jsondeserializer = deserializationconfig.deserializerInstance(annotated, class4);
                    javatype.getContentType().setValueHandler(jsondeserializer);
                }
            }
        }
        return javatype;
    }

    protected JavaType resolveType(DeserializationConfig deserializationconfig, BasicBeanDescription basicbeandescription, JavaType javatype, AnnotatedMember annotatedmember, BeanProperty beanproperty)
    {
        if (javatype.isContainerType())
        {
            AnnotationIntrospector annotationintrospector = deserializationconfig.getAnnotationIntrospector();
            JavaType javatype1 = javatype.getKeyType();
            if (javatype1 != null)
            {
                Class class2 = annotationintrospector.findKeyDeserializer(annotatedmember);
                if (class2 != null && class2 != org/codehaus/jackson/map/KeyDeserializer$None)
                {
                    javatype1.setValueHandler(deserializationconfig.keyDeserializerInstance(annotatedmember, class2));
                }
            }
            Class class1 = annotationintrospector.findContentDeserializer(annotatedmember);
            if (class1 != null && class1 != org/codehaus/jackson/map/JsonDeserializer$None)
            {
                JsonDeserializer jsondeserializer = deserializationconfig.deserializerInstance(annotatedmember, class1);
                javatype.getContentType().setValueHandler(jsondeserializer);
            }
            if (annotatedmember instanceof AnnotatedMember)
            {
                TypeDeserializer typedeserializer1 = findPropertyContentTypeDeserializer(deserializationconfig, javatype, annotatedmember, beanproperty);
                if (typedeserializer1 != null)
                {
                    javatype = javatype.withContentTypeHandler(typedeserializer1);
                }
            }
        }
        TypeDeserializer typedeserializer;
        if (annotatedmember instanceof AnnotatedMember)
        {
            typedeserializer = findPropertyTypeDeserializer(deserializationconfig, javatype, annotatedmember, beanproperty);
        } else
        {
            typedeserializer = findTypeDeserializer(deserializationconfig, javatype, null);
        }
        if (typedeserializer != null)
        {
            javatype = javatype.withTypeHandler(typedeserializer);
        }
        return javatype;
    }

    public abstract DeserializerFactory withConfig(org.codehaus.jackson.map.DeserializerFactory.Config config);

    static 
    {
        _mapFallbacks = new HashMap();
        _mapFallbacks.put(java/util/Map.getName(), java/util/LinkedHashMap);
        _mapFallbacks.put(java/util/concurrent/ConcurrentMap.getName(), java/util/concurrent/ConcurrentHashMap);
        _mapFallbacks.put(java/util/SortedMap.getName(), java/util/TreeMap);
        _mapFallbacks.put("java.util.NavigableMap", java/util/TreeMap);
        try
        {
            Class class1 = Class.forName("java.util.ConcurrentNavigableMap");
            Class class2 = Class.forName("java.util.ConcurrentSkipListMap");
            _mapFallbacks.put(class1.getName(), class2);
        }
        catch (ClassNotFoundException classnotfoundexception) { }
        _collectionFallbacks = new HashMap();
        _collectionFallbacks.put(java/util/Collection.getName(), java/util/ArrayList);
        _collectionFallbacks.put(java/util/List.getName(), java/util/ArrayList);
        _collectionFallbacks.put(java/util/Set.getName(), java/util/HashSet);
        _collectionFallbacks.put(java/util/SortedSet.getName(), java/util/TreeSet);
        _collectionFallbacks.put(java/util/Queue.getName(), java/util/LinkedList);
        _collectionFallbacks.put("java.util.Deque", java/util/LinkedList);
        _collectionFallbacks.put("java.util.NavigableSet", java/util/TreeSet);
    }
}
