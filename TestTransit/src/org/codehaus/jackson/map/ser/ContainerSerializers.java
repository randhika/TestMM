// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            ContainerSerializerBase

public final class ContainerSerializers
{
    public static abstract class AsArraySerializer extends ContainerSerializerBase
        implements ResolvableSerializer
    {

        protected PropertySerializerMap _dynamicSerializers;
        protected JsonSerializer _elementSerializer;
        protected final JavaType _elementType;
        protected final BeanProperty _property;
        protected final boolean _staticTyping;
        protected final TypeSerializer _valueTypeSerializer;

        protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap propertyserializermap, Class class1, SerializerProvider serializerprovider)
            throws JsonMappingException
        {
            org.codehaus.jackson.map.ser.impl.PropertySerializerMap.SerializerAndMapResult serializerandmapresult = propertyserializermap.findAndAddSerializer(class1, serializerprovider, _property);
            if (propertyserializermap != serializerandmapresult.map)
            {
                _dynamicSerializers = serializerandmapresult.map;
            }
            return serializerandmapresult.serializer;
        }

        protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap propertyserializermap, JavaType javatype, SerializerProvider serializerprovider)
            throws JsonMappingException
        {
            org.codehaus.jackson.map.ser.impl.PropertySerializerMap.SerializerAndMapResult serializerandmapresult = propertyserializermap.findAndAddSerializer(javatype, serializerprovider, _property);
            if (propertyserializermap != serializerandmapresult.map)
            {
                _dynamicSerializers = serializerandmapresult.map;
            }
            return serializerandmapresult.serializer;
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
            throws JsonMappingException
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            JavaType javatype = null;
            if (type != null)
            {
                javatype = serializerprovider.constructType(type).getContentType();
                if (javatype == null && (type instanceof ParameterizedType))
                {
                    Type atype[] = ((ParameterizedType)type).getActualTypeArguments();
                    if (atype.length == 1)
                    {
                        javatype = serializerprovider.constructType(atype[0]);
                    }
                }
            }
            if (javatype == null && _elementType != null)
            {
                javatype = _elementType;
            }
            if (javatype != null)
            {
                Class class1 = javatype.getRawClass();
                JsonNode jsonnode = null;
                if (class1 != java/lang/Object)
                {
                    JsonSerializer jsonserializer = serializerprovider.findValueSerializer(javatype, _property);
                    boolean flag = jsonserializer instanceof SchemaAware;
                    jsonnode = null;
                    if (flag)
                    {
                        jsonnode = ((SchemaAware)jsonserializer).getSchema(serializerprovider, null);
                    }
                }
                if (jsonnode == null)
                {
                    jsonnode = JsonSchema.getDefaultSchemaNode();
                }
                objectnode.put("items", jsonnode);
            }
            return objectnode;
        }

        public void resolve(SerializerProvider serializerprovider)
            throws JsonMappingException
        {
            if (_staticTyping && _elementType != null && _elementSerializer == null)
            {
                _elementSerializer = serializerprovider.findValueSerializer(_elementType, _property);
            }
        }

        public final void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeStartArray();
            serializeContents(obj, jsongenerator, serializerprovider);
            jsongenerator.writeEndArray();
        }

        protected abstract void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException;

        public final void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonGenerationException
        {
            typeserializer.writeTypePrefixForArray(obj, jsongenerator);
            serializeContents(obj, jsongenerator, serializerprovider);
            typeserializer.writeTypeSuffixForArray(obj, jsongenerator);
        }

        protected AsArraySerializer(Class class1, JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty)
        {
            this(class1, javatype, flag, typeserializer, beanproperty, null);
        }

        protected AsArraySerializer(Class class1, JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty, JsonSerializer jsonserializer)
        {
            boolean flag1;
label0:
            {
                super(class1, false);
                _elementType = javatype;
                if (!flag)
                {
                    flag1 = false;
                    if (javatype == null)
                    {
                        break label0;
                    }
                    boolean flag2 = javatype.isFinal();
                    flag1 = false;
                    if (!flag2)
                    {
                        break label0;
                    }
                }
                flag1 = true;
            }
            _staticTyping = flag1;
            _valueTypeSerializer = typeserializer;
            _property = beanproperty;
            _elementSerializer = jsonserializer;
            _dynamicSerializers = PropertySerializerMap.emptyMap();
        }
    }

    public static class CollectionSerializer extends AsArraySerializer
    {

        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return new CollectionSerializer(_elementType, _staticTyping, typeserializer, _property);
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((Collection)obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(Collection collection, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            if (_elementSerializer == null) goto _L2; else goto _L1
_L1:
            serializeContentsUsing(collection, jsongenerator, serializerprovider, _elementSerializer);
_L4:
            return;
_L2:
            Iterator iterator = collection.iterator();
            if (!iterator.hasNext()) goto _L4; else goto _L3
_L3:
            PropertySerializerMap propertyserializermap;
            TypeSerializer typeserializer;
            int i;
            propertyserializermap = _dynamicSerializers;
            typeserializer = _valueTypeSerializer;
            i = 0;
_L8:
            Object obj;
            Class class1;
            JsonSerializer jsonserializer;
            try
            {
                obj = iterator.next();
            }
            catch (Exception exception)
            {
                wrapAndThrow(serializerprovider, exception, collection, i);
                return;
            }
            if (obj != null) goto _L6; else goto _L5
_L5:
            serializerprovider.defaultSerializeNull(jsongenerator);
_L11:
            i++;
            if (iterator.hasNext()) goto _L8; else goto _L7
_L7:
            return;
_L6:
            class1 = obj.getClass();
            jsonserializer = propertyserializermap.serializerFor(class1);
            if (jsonserializer != null) goto _L10; else goto _L9
_L9:
            if (!_elementType.hasGenericTypes())
            {
                break MISSING_BLOCK_LABEL_170;
            }
            jsonserializer = _findAndAddDynamic(propertyserializermap, _elementType.forcedNarrowBy(class1), serializerprovider);
_L12:
            propertyserializermap = _dynamicSerializers;
_L10:
            if (typeserializer != null)
            {
                break MISSING_BLOCK_LABEL_184;
            }
            jsonserializer.serialize(obj, jsongenerator, serializerprovider);
              goto _L11
            jsonserializer = _findAndAddDynamic(propertyserializermap, class1, serializerprovider);
              goto _L12
            jsonserializer.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
              goto _L11
        }

        public void serializeContentsUsing(Collection collection, JsonGenerator jsongenerator, SerializerProvider serializerprovider, JsonSerializer jsonserializer)
            throws IOException, JsonGenerationException
        {
            Iterator iterator = collection.iterator();
            if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
            TypeSerializer typeserializer;
            int i;
            typeserializer = _valueTypeSerializer;
            i = 0;
_L5:
            Object obj = iterator.next();
            if (obj != null) goto _L4; else goto _L3
_L3:
            serializerprovider.defaultSerializeNull(jsongenerator);
_L6:
            i++;
_L7:
            if (iterator.hasNext()) goto _L5; else goto _L2
_L2:
            return;
_L4:
            if (typeserializer != null)
            {
                break MISSING_BLOCK_LABEL_92;
            }
            jsonserializer.serialize(obj, jsongenerator, serializerprovider);
              goto _L6
            Exception exception;
            exception;
            wrapAndThrow(serializerprovider, exception, collection, i);
              goto _L7
            jsonserializer.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
              goto _L6
        }

        public CollectionSerializer(JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty)
        {
            this(javatype, flag, typeserializer, beanproperty, null);
        }

        public CollectionSerializer(JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty, JsonSerializer jsonserializer)
        {
            super(java/util/Collection, javatype, flag, typeserializer, beanproperty, jsonserializer);
        }
    }

    public static class EnumSetSerializer extends AsArraySerializer
    {

        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return this;
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((EnumSet)obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(EnumSet enumset, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            JsonSerializer jsonserializer = _elementSerializer;
            Enum enum;
            for (Iterator iterator = enumset.iterator(); iterator.hasNext(); jsonserializer.serialize(enum, jsongenerator, serializerprovider))
            {
                enum = (Enum)iterator.next();
                if (jsonserializer == null)
                {
                    jsonserializer = serializerprovider.findValueSerializer(enum.getDeclaringClass(), _property);
                }
            }

        }

        public EnumSetSerializer(JavaType javatype, BeanProperty beanproperty)
        {
            super(java/util/EnumSet, javatype, true, null, beanproperty);
        }
    }

    public static class IndexedListSerializer extends AsArraySerializer
    {

        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return new IndexedListSerializer(_elementType, _staticTyping, typeserializer, _property, _elementSerializer);
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((List)obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(List list, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            if (_elementSerializer == null) goto _L2; else goto _L1
_L1:
            serializeContentsUsing(list, jsongenerator, serializerprovider, _elementSerializer);
_L9:
            return;
_L2:
            if (_valueTypeSerializer != null)
            {
                serializeTypedContents(list, jsongenerator, serializerprovider);
                return;
            }
            int i = list.size();
            if (i == 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            int j = 0;
            PropertySerializerMap propertyserializermap;
            Object obj;
            Class class1;
            JsonSerializer jsonserializer;
            try
            {
                propertyserializermap = _dynamicSerializers;
            }
            catch (Exception exception)
            {
                wrapAndThrow(serializerprovider, exception, list, j);
                return;
            }
            JsonSerializer jsonserializer1;
            for (; j >= i; j++)
            {
                continue; /* Loop/switch isn't completed */
            }

            obj = list.get(j);
            if (obj != null)
            {
                break MISSING_BLOCK_LABEL_86;
            }
            serializerprovider.defaultSerializeNull(jsongenerator);
            break MISSING_BLOCK_LABEL_184;
            class1 = obj.getClass();
            jsonserializer = propertyserializermap.serializerFor(class1);
            if (jsonserializer != null) goto _L4; else goto _L3
_L3:
            if (!_elementType.hasGenericTypes()) goto _L6; else goto _L5
_L5:
            jsonserializer = _findAndAddDynamic(propertyserializermap, _elementType.forcedNarrowBy(class1), serializerprovider);
_L7:
            propertyserializermap = _dynamicSerializers;
_L4:
            jsonserializer.serialize(obj, jsongenerator, serializerprovider);
            break MISSING_BLOCK_LABEL_184;
_L6:
            jsonserializer1 = _findAndAddDynamic(propertyserializermap, class1, serializerprovider);
            jsonserializer = jsonserializer1;
              goto _L7
            if (true) goto _L9; else goto _L8
_L8:
        }

        public void serializeContentsUsing(List list, JsonGenerator jsongenerator, SerializerProvider serializerprovider, JsonSerializer jsonserializer)
            throws IOException, JsonGenerationException
        {
            int i = list.size();
            if (i != 0) goto _L2; else goto _L1
_L1:
            return;
_L2:
            TypeSerializer typeserializer;
            int j;
            typeserializer = _valueTypeSerializer;
            j = 0;
_L4:
            Object obj;
            if (j >= i)
            {
                continue; /* Loop/switch isn't completed */
            }
            obj = list.get(j);
            if (obj == null)
            {
                try
                {
                    serializerprovider.defaultSerializeNull(jsongenerator);
                }
                catch (Exception exception)
                {
                    wrapAndThrow(serializerprovider, exception, list, j);
                }
                break MISSING_BLOCK_LABEL_96;
            }
            if (typeserializer != null)
            {
                break MISSING_BLOCK_LABEL_85;
            }
            jsonserializer.serialize(obj, jsongenerator, serializerprovider);
            break MISSING_BLOCK_LABEL_96;
            jsonserializer.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
            j++;
            if (true) goto _L4; else goto _L3
_L3:
            if (true) goto _L1; else goto _L5
_L5:
        }

        public void serializeTypedContents(List list, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            int i = list.size();
            if (i != 0) goto _L2; else goto _L1
_L1:
            return;
_L2:
            int j = 0;
            TypeSerializer typeserializer;
            PropertySerializerMap propertyserializermap;
            Object obj;
            Class class1;
            JsonSerializer jsonserializer;
            try
            {
                typeserializer = _valueTypeSerializer;
                propertyserializermap = _dynamicSerializers;
            }
            catch (Exception exception)
            {
                wrapAndThrow(serializerprovider, exception, list, j);
                return;
            }
            JsonSerializer jsonserializer1;
            for (; j >= i; j++)
            {
                continue; /* Loop/switch isn't completed */
            }

            obj = list.get(j);
            if (obj != null)
            {
                break MISSING_BLOCK_LABEL_59;
            }
            serializerprovider.defaultSerializeNull(jsongenerator);
            break MISSING_BLOCK_LABEL_159;
            class1 = obj.getClass();
            jsonserializer = propertyserializermap.serializerFor(class1);
            if (jsonserializer != null) goto _L4; else goto _L3
_L3:
            if (!_elementType.hasGenericTypes()) goto _L6; else goto _L5
_L5:
            jsonserializer = _findAndAddDynamic(propertyserializermap, _elementType.forcedNarrowBy(class1), serializerprovider);
_L7:
            propertyserializermap = _dynamicSerializers;
_L4:
            jsonserializer.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
            break MISSING_BLOCK_LABEL_159;
_L6:
            jsonserializer1 = _findAndAddDynamic(propertyserializermap, class1, serializerprovider);
            jsonserializer = jsonserializer1;
              goto _L7
            if (true) goto _L1; else goto _L8
_L8:
        }

        public IndexedListSerializer(JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty, JsonSerializer jsonserializer)
        {
            super(java/util/List, javatype, flag, typeserializer, beanproperty, jsonserializer);
        }
    }

    public static class IterableSerializer extends AsArraySerializer
    {

        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return new IterableSerializer(_elementType, _staticTyping, typeserializer, _property);
        }

        public void serializeContents(Iterable iterable, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            Iterator iterator = iterable.iterator();
            if (iterator.hasNext())
            {
                TypeSerializer typeserializer = _valueTypeSerializer;
                JsonSerializer jsonserializer = null;
                Class class1 = null;
                do
                {
                    Object obj = iterator.next();
                    if (obj == null)
                    {
                        serializerprovider.defaultSerializeNull(jsongenerator);
                    } else
                    {
                        Class class2 = obj.getClass();
                        JsonSerializer jsonserializer1;
                        if (class2 == class1)
                        {
                            jsonserializer1 = jsonserializer;
                        } else
                        {
                            jsonserializer1 = serializerprovider.findValueSerializer(class2, _property);
                            jsonserializer = jsonserializer1;
                            class1 = class2;
                        }
                        if (typeserializer == null)
                        {
                            jsonserializer1.serialize(obj, jsongenerator, serializerprovider);
                        } else
                        {
                            jsonserializer1.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
                        }
                    }
                } while (iterator.hasNext());
            }
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((Iterable)obj, jsongenerator, serializerprovider);
        }

        public IterableSerializer(JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty)
        {
            super(java/lang/Iterable, javatype, flag, typeserializer, beanproperty);
        }
    }

    public static class IteratorSerializer extends AsArraySerializer
    {

        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return new IteratorSerializer(_elementType, _staticTyping, typeserializer, _property);
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((Iterator)obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(Iterator iterator, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            if (iterator.hasNext())
            {
                TypeSerializer typeserializer = _valueTypeSerializer;
                JsonSerializer jsonserializer = null;
                Class class1 = null;
                do
                {
                    Object obj = iterator.next();
                    if (obj == null)
                    {
                        serializerprovider.defaultSerializeNull(jsongenerator);
                    } else
                    {
                        Class class2 = obj.getClass();
                        JsonSerializer jsonserializer1;
                        if (class2 == class1)
                        {
                            jsonserializer1 = jsonserializer;
                        } else
                        {
                            jsonserializer1 = serializerprovider.findValueSerializer(class2, _property);
                            jsonserializer = jsonserializer1;
                            class1 = class2;
                        }
                        if (typeserializer == null)
                        {
                            jsonserializer1.serialize(obj, jsongenerator, serializerprovider);
                        } else
                        {
                            jsonserializer1.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
                        }
                    }
                } while (iterator.hasNext());
            }
        }

        public IteratorSerializer(JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty)
        {
            super(java/util/Iterator, javatype, flag, typeserializer, beanproperty);
        }
    }


    private ContainerSerializers()
    {
    }

    public static ContainerSerializerBase collectionSerializer(JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty, JsonSerializer jsonserializer)
    {
        return new CollectionSerializer(javatype, flag, typeserializer, beanproperty, jsonserializer);
    }

    public static JsonSerializer enumSetSerializer(JavaType javatype, BeanProperty beanproperty)
    {
        return new EnumSetSerializer(javatype, beanproperty);
    }

    public static ContainerSerializerBase indexedListSerializer(JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty, JsonSerializer jsonserializer)
    {
        return new IndexedListSerializer(javatype, flag, typeserializer, beanproperty, jsonserializer);
    }

    public static ContainerSerializerBase iterableSerializer(JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty)
    {
        return new IterableSerializer(javatype, flag, typeserializer, beanproperty);
    }

    public static ContainerSerializerBase iteratorSerializer(JavaType javatype, boolean flag, TypeSerializer typeserializer, BeanProperty beanproperty)
    {
        return new IteratorSerializer(javatype, flag, typeserializer, beanproperty);
    }
}
