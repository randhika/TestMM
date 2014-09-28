// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.TimeZone;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.ContextualSerializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializable;
import org.codehaus.jackson.map.JsonSerializableWithType;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerFactory;
import org.codehaus.jackson.map.Serializers;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.ext.OptionalHandlerFactory;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.jsontype.SubtypeResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.ser.impl.IndexedStringListSerializer;
import org.codehaus.jackson.map.ser.impl.InetAddressSerializer;
import org.codehaus.jackson.map.ser.impl.ObjectArraySerializer;
import org.codehaus.jackson.map.ser.impl.StringCollectionSerializer;
import org.codehaus.jackson.map.ser.impl.TimeZoneSerializer;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.EnumValues;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.TokenBuffer;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            ToStringSerializer, JdkSerializers, ContainerSerializers, EnumMapSerializer, 
//            MapSerializer, JsonValueSerializer, EnumSerializer, NullSerializer

public abstract class BasicSerializerFactory extends SerializerFactory
{

    protected static final HashMap _arraySerializers;
    protected static final HashMap _concrete;
    protected static final HashMap _concreteLazy;
    protected OptionalHandlerFactory optionalHandlers;

    protected BasicSerializerFactory()
    {
        optionalHandlers = OptionalHandlerFactory.instance;
    }

    protected static JsonSerializer findContentSerializer(SerializationConfig serializationconfig, Annotated annotated, BeanProperty beanproperty)
    {
        AnnotationIntrospector annotationintrospector = serializationconfig.getAnnotationIntrospector();
        Class class1 = annotationintrospector.findContentSerializer(annotated);
        if ((class1 == null || class1 == org/codehaus/jackson/map/JsonSerializer$None) && beanproperty != null)
        {
            class1 = annotationintrospector.findContentSerializer(beanproperty.getMember());
        }
        if (class1 != null && class1 != org/codehaus/jackson/map/JsonSerializer$None)
        {
            return serializationconfig.serializerInstance(annotated, class1);
        } else
        {
            return null;
        }
    }

    protected static JsonSerializer findKeySerializer(SerializationConfig serializationconfig, Annotated annotated, BeanProperty beanproperty)
    {
        AnnotationIntrospector annotationintrospector = serializationconfig.getAnnotationIntrospector();
        Class class1 = annotationintrospector.findKeySerializer(annotated);
        if ((class1 == null || class1 == org/codehaus/jackson/map/JsonSerializer$None) && beanproperty != null)
        {
            class1 = annotationintrospector.findKeySerializer(beanproperty.getMember());
        }
        if (class1 != null && class1 != org/codehaus/jackson/map/JsonSerializer$None)
        {
            return serializationconfig.serializerInstance(annotated, class1);
        } else
        {
            return null;
        }
    }

    protected static JavaType modifySecondaryTypesByAnnotation(SerializationConfig serializationconfig, Annotated annotated, JavaType javatype)
    {
        AnnotationIntrospector annotationintrospector = serializationconfig.getAnnotationIntrospector();
        if (javatype.isContainerType())
        {
            Class class1 = annotationintrospector.findSerializationKeyType(annotated, javatype.getKeyType());
            Class class2;
            if (class1 != null)
            {
                if (!(javatype instanceof MapType))
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Illegal key-type annotation: type ").append(javatype).append(" is not a Map type").toString());
                }
                JavaType javatype1;
                JavaType javatype2;
                try
                {
                    javatype2 = ((MapType)javatype).widenKey(class1);
                }
                catch (IllegalArgumentException illegalargumentexception1)
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Failed to narrow key type ").append(javatype).append(" with key-type annotation (").append(class1.getName()).append("): ").append(illegalargumentexception1.getMessage()).toString());
                }
                javatype = javatype2;
            }
            class2 = annotationintrospector.findSerializationContentType(annotated, javatype.getContentType());
            if (class2 != null)
            {
                try
                {
                    javatype1 = javatype.widenContentsBy(class2);
                }
                catch (IllegalArgumentException illegalargumentexception)
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Failed to narrow content type ").append(javatype).append(" with content-type annotation (").append(class2.getName()).append("): ").append(illegalargumentexception.getMessage()).toString());
                }
                javatype = javatype1;
            }
        }
        return javatype;
    }

    protected JsonSerializer buildArraySerializer(SerializationConfig serializationconfig, ArrayType arraytype, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, boolean flag, TypeSerializer typeserializer, JsonSerializer jsonserializer)
    {
        Class class1 = arraytype.getRawClass();
        Object obj;
        if ([Ljava/lang/String; == class1)
        {
            obj = new ArraySerializers.StringArraySerializer(beanproperty);
        } else
        {
            obj = (JsonSerializer)_arraySerializers.get(class1.getName());
            if (obj == null)
            {
                return new ObjectArraySerializer(arraytype.getContentType(), flag, typeserializer, beanproperty, jsonserializer);
            }
        }
        return ((JsonSerializer) (obj));
    }

    protected JsonSerializer buildCollectionLikeSerializer(SerializationConfig serializationconfig, CollectionLikeType collectionliketype, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, boolean flag, TypeSerializer typeserializer, JsonSerializer jsonserializer)
    {
        for (Iterator iterator = customSerializers().iterator(); iterator.hasNext();)
        {
            JsonSerializer jsonserializer1 = ((Serializers)iterator.next()).findCollectionLikeSerializer(serializationconfig, collectionliketype, basicbeandescription, beanproperty, typeserializer, jsonserializer);
            if (jsonserializer1 != null)
            {
                return jsonserializer1;
            }
        }

        return null;
    }

    protected JsonSerializer buildCollectionSerializer(SerializationConfig serializationconfig, CollectionType collectiontype, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, boolean flag, TypeSerializer typeserializer, JsonSerializer jsonserializer)
    {
        for (Iterator iterator = customSerializers().iterator(); iterator.hasNext();)
        {
            JsonSerializer jsonserializer1 = ((Serializers)iterator.next()).findCollectionSerializer(serializationconfig, collectiontype, basicbeandescription, beanproperty, typeserializer, jsonserializer);
            if (jsonserializer1 != null)
            {
                return jsonserializer1;
            }
        }

        Class class1 = collectiontype.getRawClass();
        if (java/util/EnumSet.isAssignableFrom(class1))
        {
            return buildEnumSetSerializer(serializationconfig, collectiontype, basicbeandescription, beanproperty, flag, typeserializer, jsonserializer);
        }
        Class class2 = collectiontype.getContentType().getRawClass();
        if (isIndexedList(class1))
        {
            if (class2 == java/lang/String)
            {
                return new IndexedStringListSerializer(beanproperty);
            } else
            {
                return ContainerSerializers.indexedListSerializer(collectiontype.getContentType(), flag, typeserializer, beanproperty, jsonserializer);
            }
        }
        if (class2 == java/lang/String)
        {
            return new StringCollectionSerializer(beanproperty);
        } else
        {
            return ContainerSerializers.collectionSerializer(collectiontype.getContentType(), flag, typeserializer, beanproperty, jsonserializer);
        }
    }

    public JsonSerializer buildContainerSerializer(SerializationConfig serializationconfig, JavaType javatype, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, boolean flag)
    {
        TypeSerializer typeserializer = createTypeSerializer(serializationconfig, javatype.getContentType(), beanproperty);
        JsonSerializer jsonserializer;
        if (typeserializer != null)
        {
            flag = false;
        } else
        if (!flag)
        {
            flag = usesStaticTyping(serializationconfig, basicbeandescription, typeserializer, beanproperty);
        }
        jsonserializer = findContentSerializer(serializationconfig, basicbeandescription.getClassInfo(), beanproperty);
        if (javatype.isMapLikeType())
        {
            MapLikeType mapliketype = (MapLikeType)javatype;
            JsonSerializer jsonserializer1 = findKeySerializer(serializationconfig, basicbeandescription.getClassInfo(), beanproperty);
            if (mapliketype.isTrueMapType())
            {
                return buildMapSerializer(serializationconfig, (MapType)mapliketype, basicbeandescription, beanproperty, flag, jsonserializer1, typeserializer, jsonserializer);
            } else
            {
                return buildMapLikeSerializer(serializationconfig, mapliketype, basicbeandescription, beanproperty, flag, jsonserializer1, typeserializer, jsonserializer);
            }
        }
        if (javatype.isCollectionLikeType())
        {
            CollectionLikeType collectionliketype = (CollectionLikeType)javatype;
            if (collectionliketype.isTrueCollectionType())
            {
                return buildCollectionSerializer(serializationconfig, (CollectionType)collectionliketype, basicbeandescription, beanproperty, flag, typeserializer, jsonserializer);
            } else
            {
                return buildCollectionLikeSerializer(serializationconfig, collectionliketype, basicbeandescription, beanproperty, flag, typeserializer, jsonserializer);
            }
        }
        if (javatype.isArrayType())
        {
            return buildArraySerializer(serializationconfig, (ArrayType)javatype, basicbeandescription, beanproperty, flag, typeserializer, jsonserializer);
        } else
        {
            return null;
        }
    }

    protected JsonSerializer buildEnumMapSerializer(SerializationConfig serializationconfig, JavaType javatype, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, boolean flag, TypeSerializer typeserializer, JsonSerializer jsonserializer)
    {
        JavaType javatype1 = javatype.getKeyType();
        boolean flag1 = javatype1.isEnumType();
        EnumValues enumvalues = null;
        if (flag1)
        {
            enumvalues = EnumValues.construct(javatype1.getRawClass(), serializationconfig.getAnnotationIntrospector());
        }
        return new EnumMapSerializer(javatype.getContentType(), flag, enumvalues, typeserializer, beanproperty, jsonserializer);
    }

    protected JsonSerializer buildEnumSetSerializer(SerializationConfig serializationconfig, JavaType javatype, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, boolean flag, TypeSerializer typeserializer, JsonSerializer jsonserializer)
    {
        JavaType javatype1 = javatype.getContentType();
        if (!javatype1.isEnumType())
        {
            javatype1 = null;
        }
        return ContainerSerializers.enumSetSerializer(javatype1, beanproperty);
    }

    protected JsonSerializer buildIterableSerializer(SerializationConfig serializationconfig, JavaType javatype, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, boolean flag)
    {
        JavaType javatype1 = javatype.containedType(0);
        if (javatype1 == null)
        {
            javatype1 = TypeFactory.unknownType();
        }
        TypeSerializer typeserializer = createTypeSerializer(serializationconfig, javatype1, beanproperty);
        return ContainerSerializers.iterableSerializer(javatype1, usesStaticTyping(serializationconfig, basicbeandescription, typeserializer, beanproperty), typeserializer, beanproperty);
    }

    protected JsonSerializer buildIteratorSerializer(SerializationConfig serializationconfig, JavaType javatype, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, boolean flag)
    {
        JavaType javatype1 = javatype.containedType(0);
        if (javatype1 == null)
        {
            javatype1 = TypeFactory.unknownType();
        }
        TypeSerializer typeserializer = createTypeSerializer(serializationconfig, javatype1, beanproperty);
        return ContainerSerializers.iteratorSerializer(javatype1, usesStaticTyping(serializationconfig, basicbeandescription, typeserializer, beanproperty), typeserializer, beanproperty);
    }

    protected JsonSerializer buildMapLikeSerializer(SerializationConfig serializationconfig, MapLikeType mapliketype, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, boolean flag, JsonSerializer jsonserializer, TypeSerializer typeserializer, 
            JsonSerializer jsonserializer1)
    {
        for (Iterator iterator = customSerializers().iterator(); iterator.hasNext();)
        {
            JsonSerializer jsonserializer2 = ((Serializers)iterator.next()).findMapLikeSerializer(serializationconfig, mapliketype, basicbeandescription, beanproperty, jsonserializer, typeserializer, jsonserializer1);
            if (jsonserializer2 != null)
            {
                return jsonserializer2;
            }
        }

        return null;
    }

    protected JsonSerializer buildMapSerializer(SerializationConfig serializationconfig, MapType maptype, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, boolean flag, JsonSerializer jsonserializer, TypeSerializer typeserializer, 
            JsonSerializer jsonserializer1)
    {
        for (Iterator iterator = customSerializers().iterator(); iterator.hasNext();)
        {
            JsonSerializer jsonserializer2 = ((Serializers)iterator.next()).findMapSerializer(serializationconfig, maptype, basicbeandescription, beanproperty, jsonserializer, typeserializer, jsonserializer1);
            if (jsonserializer2 != null)
            {
                return jsonserializer2;
            }
        }

        if (java/util/EnumMap.isAssignableFrom(maptype.getRawClass()))
        {
            return buildEnumMapSerializer(serializationconfig, maptype, basicbeandescription, beanproperty, flag, typeserializer, jsonserializer1);
        } else
        {
            return MapSerializer.construct(serializationconfig.getAnnotationIntrospector().findPropertiesToIgnore(basicbeandescription.getClassInfo()), maptype, flag, typeserializer, beanproperty, jsonserializer, jsonserializer1);
        }
    }

    public abstract JsonSerializer createSerializer(SerializationConfig serializationconfig, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException;

    public TypeSerializer createTypeSerializer(SerializationConfig serializationconfig, JavaType javatype, BeanProperty beanproperty)
    {
        org.codehaus.jackson.map.introspect.AnnotatedClass annotatedclass = ((BasicBeanDescription)serializationconfig.introspectClassAnnotations(javatype.getRawClass())).getClassInfo();
        AnnotationIntrospector annotationintrospector = serializationconfig.getAnnotationIntrospector();
        TypeResolverBuilder typeresolverbuilder = annotationintrospector.findTypeResolver(serializationconfig, annotatedclass, javatype);
        Collection collection = null;
        if (typeresolverbuilder == null)
        {
            typeresolverbuilder = serializationconfig.getDefaultTyper(javatype);
        } else
        {
            collection = serializationconfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedclass, serializationconfig, annotationintrospector);
        }
        if (typeresolverbuilder == null)
        {
            return null;
        } else
        {
            return typeresolverbuilder.buildTypeSerializer(serializationconfig, javatype, collection, beanproperty);
        }
    }

    protected abstract Iterable customSerializers();

    public final JsonSerializer findSerializerByAddonType(SerializationConfig serializationconfig, JavaType javatype, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, boolean flag)
        throws JsonMappingException
    {
        Class class1 = javatype.getRawClass();
        if (java/util/Iterator.isAssignableFrom(class1))
        {
            return buildIteratorSerializer(serializationconfig, javatype, basicbeandescription, beanproperty, flag);
        }
        if (java/lang/Iterable.isAssignableFrom(class1))
        {
            return buildIterableSerializer(serializationconfig, javatype, basicbeandescription, beanproperty, flag);
        }
        if (java/lang/CharSequence.isAssignableFrom(class1))
        {
            return ToStringSerializer.instance;
        } else
        {
            return null;
        }
    }

    public final JsonSerializer findSerializerByLookup(JavaType javatype, SerializationConfig serializationconfig, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, boolean flag)
    {
        String s = javatype.getRawClass().getName();
        JsonSerializer jsonserializer = (JsonSerializer)_concrete.get(s);
        if (jsonserializer != null)
        {
            return jsonserializer;
        }
        Class class1 = (Class)_concreteLazy.get(s);
        if (class1 != null)
        {
            JsonSerializer jsonserializer1;
            try
            {
                jsonserializer1 = (JsonSerializer)class1.newInstance();
            }
            catch (Exception exception)
            {
                throw new IllegalStateException((new StringBuilder()).append("Failed to instantiate standard serializer (of type ").append(class1.getName()).append("): ").append(exception.getMessage()).toString(), exception);
            }
            return jsonserializer1;
        } else
        {
            return null;
        }
    }

    public final JsonSerializer findSerializerByPrimaryType(JavaType javatype, SerializationConfig serializationconfig, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, boolean flag)
        throws JsonMappingException
    {
        Class class1 = javatype.getRawClass();
        if (!org/codehaus/jackson/map/JsonSerializable.isAssignableFrom(class1)) goto _L2; else goto _L1
_L1:
        if (!org/codehaus/jackson/map/JsonSerializableWithType.isAssignableFrom(class1)) goto _L4; else goto _L3
_L3:
        Object obj = StdSerializers.SerializableWithTypeSerializer.instance;
_L6:
        return ((JsonSerializer) (obj));
_L4:
        return StdSerializers.SerializableSerializer.instance;
_L2:
        AnnotatedMethod annotatedmethod = basicbeandescription.findJsonValueMethod();
        if (annotatedmethod != null)
        {
            java.lang.reflect.Method method = annotatedmethod.getAnnotated();
            if (serializationconfig.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
            {
                ClassUtil.checkAndFixAccess(method);
            }
            return new JsonValueSerializer(method, findSerializerFromAnnotation(serializationconfig, annotatedmethod, beanproperty), beanproperty);
        }
        if (java/net/InetAddress.isAssignableFrom(class1))
        {
            return InetAddressSerializer.instance;
        }
        if (java/util/TimeZone.isAssignableFrom(class1))
        {
            return TimeZoneSerializer.instance;
        }
        obj = optionalHandlers.findSerializer(serializationconfig, javatype);
        if (obj == null)
        {
            if (java/lang/Number.isAssignableFrom(class1))
            {
                return StdSerializers.NumberSerializer.instance;
            }
            if (java/lang/Enum.isAssignableFrom(class1))
            {
                return EnumSerializer.construct(class1, serializationconfig, basicbeandescription);
            }
            if (java/util/Calendar.isAssignableFrom(class1))
            {
                return StdSerializers.CalendarSerializer.instance;
            }
            if (java/util/Date.isAssignableFrom(class1))
            {
                return StdSerializers.UtilDateSerializer.instance;
            } else
            {
                return null;
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected JsonSerializer findSerializerFromAnnotation(SerializationConfig serializationconfig, Annotated annotated, BeanProperty beanproperty)
        throws JsonMappingException
    {
        Object obj = serializationconfig.getAnnotationIntrospector().findSerializer(annotated);
        if (obj != null) goto _L2; else goto _L1
_L1:
        JsonSerializer jsonserializer = null;
_L4:
        return jsonserializer;
_L2:
        if (!(obj instanceof JsonSerializer))
        {
            break; /* Loop/switch isn't completed */
        }
        jsonserializer = (JsonSerializer)obj;
        if (jsonserializer instanceof ContextualSerializer)
        {
            return ((ContextualSerializer)jsonserializer).createContextual(serializationconfig, beanproperty);
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (!(obj instanceof Class))
        {
            throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector returned value of type ").append(obj.getClass().getName()).append("; expected type JsonSerializer or Class<JsonSerializer> instead").toString());
        }
        Class class1 = (Class)obj;
        if (!org/codehaus/jackson/map/JsonSerializer.isAssignableFrom(class1))
        {
            throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector returned Class ").append(class1.getName()).append("; expected Class<JsonSerializer>").toString());
        }
        jsonserializer = serializationconfig.serializerInstance(annotated, class1);
        if (jsonserializer instanceof ContextualSerializer)
        {
            return ((ContextualSerializer)jsonserializer).createContextual(serializationconfig, beanproperty);
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public final JsonSerializer getNullSerializer()
    {
        return NullSerializer.instance;
    }

    protected boolean isIndexedList(Class class1)
    {
        return java/util/RandomAccess.isAssignableFrom(class1);
    }

    protected JavaType modifyTypeByAnnotation(SerializationConfig serializationconfig, Annotated annotated, JavaType javatype)
    {
        Class class1 = serializationconfig.getAnnotationIntrospector().findSerializationType(annotated);
        if (class1 != null)
        {
            JavaType javatype1;
            try
            {
                javatype1 = javatype.widenBy(class1);
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Failed to widen type ").append(javatype).append(" with concrete-type annotation (value ").append(class1.getName()).append("), method '").append(annotated.getName()).append("': ").append(illegalargumentexception.getMessage()).toString());
            }
            javatype = javatype1;
        }
        return modifySecondaryTypesByAnnotation(serializationconfig, annotated, javatype);
    }

    protected boolean usesStaticTyping(SerializationConfig serializationconfig, BasicBeanDescription basicbeandescription, TypeSerializer typeserializer, BeanProperty beanproperty)
    {
        if (typeserializer == null)
        {
            AnnotationIntrospector annotationintrospector = serializationconfig.getAnnotationIntrospector();
            org.codehaus.jackson.map.annotate.JsonSerialize.Typing typing = annotationintrospector.findSerializationTyping(basicbeandescription.getClassInfo());
            if (typing != null)
            {
                if (typing == org.codehaus.jackson.map.annotate.JsonSerialize.Typing.STATIC)
                {
                    return true;
                }
            } else
            if (serializationconfig.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.USE_STATIC_TYPING))
            {
                return true;
            }
            if (beanproperty != null)
            {
                JavaType javatype = beanproperty.getType();
                if (javatype.isContainerType())
                {
                    if (annotationintrospector.findSerializationContentType(beanproperty.getMember(), beanproperty.getType()) != null)
                    {
                        return true;
                    }
                    if ((javatype instanceof MapType) && annotationintrospector.findSerializationKeyType(beanproperty.getMember(), beanproperty.getType()) != null)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static 
    {
        _concrete = new HashMap();
        _concreteLazy = new HashMap();
        _concrete.put(java/lang/String.getName(), new StdSerializers.StringSerializer());
        ToStringSerializer tostringserializer = ToStringSerializer.instance;
        _concrete.put(java/lang/StringBuffer.getName(), tostringserializer);
        _concrete.put(java/lang/StringBuilder.getName(), tostringserializer);
        _concrete.put(java/lang/Character.getName(), tostringserializer);
        _concrete.put(Character.TYPE.getName(), tostringserializer);
        _concrete.put(Boolean.TYPE.getName(), new StdSerializers.BooleanSerializer(true));
        _concrete.put(java/lang/Boolean.getName(), new StdSerializers.BooleanSerializer(false));
        StdSerializers.IntegerSerializer integerserializer = new StdSerializers.IntegerSerializer();
        _concrete.put(java/lang/Integer.getName(), integerserializer);
        _concrete.put(Integer.TYPE.getName(), integerserializer);
        _concrete.put(java/lang/Long.getName(), StdSerializers.LongSerializer.instance);
        _concrete.put(Long.TYPE.getName(), StdSerializers.LongSerializer.instance);
        _concrete.put(java/lang/Byte.getName(), StdSerializers.IntLikeSerializer.instance);
        _concrete.put(Byte.TYPE.getName(), StdSerializers.IntLikeSerializer.instance);
        _concrete.put(java/lang/Short.getName(), StdSerializers.IntLikeSerializer.instance);
        _concrete.put(Short.TYPE.getName(), StdSerializers.IntLikeSerializer.instance);
        _concrete.put(java/lang/Float.getName(), StdSerializers.FloatSerializer.instance);
        _concrete.put(Float.TYPE.getName(), StdSerializers.FloatSerializer.instance);
        _concrete.put(java/lang/Double.getName(), StdSerializers.DoubleSerializer.instance);
        _concrete.put(Double.TYPE.getName(), StdSerializers.DoubleSerializer.instance);
        StdSerializers.NumberSerializer numberserializer = new StdSerializers.NumberSerializer();
        _concrete.put(java/math/BigInteger.getName(), numberserializer);
        _concrete.put(java/math/BigDecimal.getName(), numberserializer);
        _concrete.put(java/util/Calendar.getName(), StdSerializers.CalendarSerializer.instance);
        _concrete.put(java/util/Date.getName(), StdSerializers.UtilDateSerializer.instance);
        _concrete.put(java/sql/Date.getName(), new StdSerializers.SqlDateSerializer());
        _concrete.put(java/sql/Time.getName(), new StdSerializers.SqlTimeSerializer());
        _concrete.put(java/sql/Timestamp.getName(), StdSerializers.UtilDateSerializer.instance);
        for (Iterator iterator = (new JdkSerializers()).provide().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            Object obj = entry.getValue();
            if (obj instanceof JsonSerializer)
            {
                _concrete.put(((Class)entry.getKey()).getName(), (JsonSerializer)obj);
            } else
            if (obj instanceof Class)
            {
                Class class1 = (Class)obj;
                _concreteLazy.put(((Class)entry.getKey()).getName(), class1);
            } else
            {
                throw new IllegalStateException((new StringBuilder()).append("Internal error: unrecognized value of type ").append(entry.getClass().getName()).toString());
            }
        }

        _concreteLazy.put(org/codehaus/jackson/util/TokenBuffer.getName(), org/codehaus/jackson/map/ser/StdSerializers$TokenBufferSerializer);
        _arraySerializers = new HashMap();
        _arraySerializers.put([Z.getName(), new ArraySerializers.BooleanArraySerializer());
        _arraySerializers.put([B.getName(), new ArraySerializers.ByteArraySerializer());
        _arraySerializers.put([C.getName(), new ArraySerializers.CharArraySerializer());
        _arraySerializers.put([S.getName(), new ArraySerializers.ShortArraySerializer());
        _arraySerializers.put([I.getName(), new ArraySerializers.IntArraySerializer());
        _arraySerializers.put([J.getName(), new ArraySerializers.LongArraySerializer());
        _arraySerializers.put([F.getName(), new ArraySerializers.FloatArraySerializer());
        _arraySerializers.put([D.getName(), new ArraySerializers.DoubleArraySerializer());
    }
}
