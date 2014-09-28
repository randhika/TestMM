// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerFactory;
import org.codehaus.jackson.map.Serializers;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.jsontype.SubtypeResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            BasicSerializerFactory, PropertyBuilder, BeanPropertyWriter, BeanSerializerModifier, 
//            BeanSerializerBuilder, AnyGetterWriter, MapSerializer, FilteredBeanPropertyWriter

public class BeanSerializerFactory extends BasicSerializerFactory
{
    public static class ConfigImpl extends org.codehaus.jackson.map.SerializerFactory.Config
    {

        protected static final BeanSerializerModifier NO_MODIFIERS[] = new BeanSerializerModifier[0];
        protected static final Serializers NO_SERIALIZERS[] = new Serializers[0];
        protected final Serializers _additionalKeySerializers[];
        protected final Serializers _additionalSerializers[];
        protected final BeanSerializerModifier _modifiers[];

        public boolean hasKeySerializers()
        {
            return _additionalKeySerializers.length > 0;
        }

        public boolean hasSerializerModifiers()
        {
            return _modifiers.length > 0;
        }

        public boolean hasSerializers()
        {
            return _additionalSerializers.length > 0;
        }

        public Iterable keySerializers()
        {
            return ArrayBuilders.arrayAsIterable(_additionalKeySerializers);
        }

        public Iterable serializerModifiers()
        {
            return ArrayBuilders.arrayAsIterable(_modifiers);
        }

        public Iterable serializers()
        {
            return ArrayBuilders.arrayAsIterable(_additionalSerializers);
        }

        public org.codehaus.jackson.map.SerializerFactory.Config withAdditionalKeySerializers(Serializers serializers1)
        {
            if (serializers1 == null)
            {
                throw new IllegalArgumentException("Can not pass null Serializers");
            } else
            {
                Serializers aserializers[] = (Serializers[])ArrayBuilders.insertInListNoDup(_additionalKeySerializers, serializers1);
                return new ConfigImpl(_additionalSerializers, aserializers, _modifiers);
            }
        }

        public org.codehaus.jackson.map.SerializerFactory.Config withAdditionalSerializers(Serializers serializers1)
        {
            if (serializers1 == null)
            {
                throw new IllegalArgumentException("Can not pass null Serializers");
            } else
            {
                return new ConfigImpl((Serializers[])ArrayBuilders.insertInListNoDup(_additionalSerializers, serializers1), _additionalKeySerializers, _modifiers);
            }
        }

        public org.codehaus.jackson.map.SerializerFactory.Config withSerializerModifier(BeanSerializerModifier beanserializermodifier)
        {
            if (beanserializermodifier == null)
            {
                throw new IllegalArgumentException("Can not pass null modifier");
            } else
            {
                BeanSerializerModifier abeanserializermodifier[] = (BeanSerializerModifier[])ArrayBuilders.insertInListNoDup(_modifiers, beanserializermodifier);
                return new ConfigImpl(_additionalSerializers, _additionalKeySerializers, abeanserializermodifier);
            }
        }


        public ConfigImpl()
        {
            this(null, null, null);
        }

        protected ConfigImpl(Serializers aserializers[], Serializers aserializers1[], BeanSerializerModifier abeanserializermodifier[])
        {
            if (aserializers == null)
            {
                aserializers = NO_SERIALIZERS;
            }
            _additionalSerializers = aserializers;
            if (aserializers1 == null)
            {
                aserializers1 = NO_SERIALIZERS;
            }
            _additionalKeySerializers = aserializers1;
            if (abeanserializermodifier == null)
            {
                abeanserializermodifier = NO_MODIFIERS;
            }
            _modifiers = abeanserializermodifier;
        }
    }


    public static final BeanSerializerFactory instance = new BeanSerializerFactory(null);
    protected final org.codehaus.jackson.map.SerializerFactory.Config _factoryConfig;

    protected BeanSerializerFactory()
    {
        this(null);
    }

    protected BeanSerializerFactory(org.codehaus.jackson.map.SerializerFactory.Config config)
    {
        if (config == null)
        {
            config = new ConfigImpl();
        }
        _factoryConfig = config;
    }

    protected BeanPropertyWriter _constructWriter(SerializationConfig serializationconfig, TypeBindings typebindings, PropertyBuilder propertybuilder, boolean flag, String s, AnnotatedMember annotatedmember)
        throws JsonMappingException
    {
        if (serializationconfig.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
        {
            annotatedmember.fixAccess();
        }
        JavaType javatype = annotatedmember.getType(typebindings);
        org.codehaus.jackson.map.BeanProperty.Std std = new org.codehaus.jackson.map.BeanProperty.Std(s, javatype, propertybuilder.getClassAnnotations(), annotatedmember);
        JsonSerializer jsonserializer = findSerializerFromAnnotation(serializationconfig, annotatedmember, std);
        boolean flag1 = ClassUtil.isCollectionMapOrArray(javatype.getRawClass());
        TypeSerializer typeserializer = null;
        if (flag1)
        {
            typeserializer = findPropertyContentTypeSerializer(javatype, serializationconfig, annotatedmember, std);
        }
        BeanPropertyWriter beanpropertywriter = propertybuilder.buildWriter(s, javatype, jsonserializer, findPropertyTypeSerializer(javatype, serializationconfig, annotatedmember, std), typeserializer, annotatedmember, flag);
        beanpropertywriter.setViews(serializationconfig.getAnnotationIntrospector().findSerializationViews(annotatedmember));
        return beanpropertywriter;
    }

    protected List _sortBeanProperties(List list, List list1, String as[], boolean flag)
    {
        int i = list.size();
        Object obj;
        Iterator iterator;
        BeanPropertyWriter beanpropertywriter2;
        if (flag)
        {
            obj = new TreeMap();
        } else
        {
            obj = new LinkedHashMap(i * 2);
        }
        for (iterator = list.iterator(); iterator.hasNext(); ((Map) (obj)).put(beanpropertywriter2.getName(), beanpropertywriter2))
        {
            beanpropertywriter2 = (BeanPropertyWriter)iterator.next();
        }

        LinkedHashMap linkedhashmap = new LinkedHashMap(i * 2);
        if (as != null)
        {
            int j = as.length;
            for (int k = 0; k < j; k++)
            {
                String s1 = as[k];
                BeanPropertyWriter beanpropertywriter1 = (BeanPropertyWriter)((Map) (obj)).get(s1);
                if (beanpropertywriter1 != null)
                {
                    linkedhashmap.put(s1, beanpropertywriter1);
                }
            }

        }
        Iterator iterator1 = list1.iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            String s = (String)iterator1.next();
            BeanPropertyWriter beanpropertywriter = (BeanPropertyWriter)((Map) (obj)).get(s);
            if (beanpropertywriter != null)
            {
                linkedhashmap.put(s, beanpropertywriter);
            }
        } while (true);
        linkedhashmap.putAll(((Map) (obj)));
        return new ArrayList(linkedhashmap.values());
    }

    protected JsonSerializer constructBeanSerializer(SerializationConfig serializationconfig, BasicBeanDescription basicbeandescription, BeanProperty beanproperty)
        throws JsonMappingException
    {
        if (basicbeandescription.getBeanClass() == java/lang/Object)
        {
            throw new IllegalArgumentException("Can not create bean serializer for Object.class");
        }
        BeanSerializerBuilder beanserializerbuilder = constructBeanSerializerBuilder(basicbeandescription);
        Object obj = findBeanProperties(serializationconfig, basicbeandescription);
        AnnotatedMethod annotatedmethod = basicbeandescription.findAnyGetter();
        if (_factoryConfig.hasSerializerModifiers())
        {
            if (obj == null)
            {
                obj = new ArrayList();
            }
            for (Iterator iterator2 = _factoryConfig.serializerModifiers().iterator(); iterator2.hasNext();)
            {
                obj = ((BeanSerializerModifier)iterator2.next()).changeProperties(serializationconfig, basicbeandescription, ((List) (obj)));
            }

        }
        List list;
        if (obj == null || ((List) (obj)).size() == 0)
        {
            if (annotatedmethod == null)
            {
                if (basicbeandescription.hasKnownClassAnnotations())
                {
                    return beanserializerbuilder.createDummy();
                } else
                {
                    return null;
                }
            }
            list = Collections.emptyList();
        } else
        {
            list = sortBeanProperties(serializationconfig, basicbeandescription, filterBeanProperties(serializationconfig, basicbeandescription, ((List) (obj))));
        }
        if (_factoryConfig.hasSerializerModifiers())
        {
            for (Iterator iterator1 = _factoryConfig.serializerModifiers().iterator(); iterator1.hasNext();)
            {
                list = ((BeanSerializerModifier)iterator1.next()).orderProperties(serializationconfig, basicbeandescription, list);
            }

        }
        beanserializerbuilder.setProperties(list);
        beanserializerbuilder.setFilterId(findFilterId(serializationconfig, basicbeandescription));
        if (annotatedmethod != null)
        {
            JavaType javatype = annotatedmethod.getType(basicbeandescription.bindingsForBeanType());
            beanserializerbuilder.setAnyGetter(new AnyGetterWriter(annotatedmethod, MapSerializer.construct(null, javatype, serializationconfig.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.USE_STATIC_TYPING), createTypeSerializer(serializationconfig, javatype.getContentType(), beanproperty), beanproperty, null, null)));
        }
        processViews(serializationconfig, beanserializerbuilder);
        if (_factoryConfig.hasSerializerModifiers())
        {
            for (Iterator iterator = _factoryConfig.serializerModifiers().iterator(); iterator.hasNext();)
            {
                beanserializerbuilder = ((BeanSerializerModifier)iterator.next()).updateBuilder(serializationconfig, basicbeandescription, beanserializerbuilder);
            }

        }
        return beanserializerbuilder.build();
    }

    protected BeanSerializerBuilder constructBeanSerializerBuilder(BasicBeanDescription basicbeandescription)
    {
        return new BeanSerializerBuilder(basicbeandescription);
    }

    protected BeanPropertyWriter constructFilteredBeanWriter(BeanPropertyWriter beanpropertywriter, Class aclass[])
    {
        return FilteredBeanPropertyWriter.constructViewBased(beanpropertywriter, aclass);
    }

    protected PropertyBuilder constructPropertyBuilder(SerializationConfig serializationconfig, BasicBeanDescription basicbeandescription)
    {
        return new PropertyBuilder(serializationconfig, basicbeandescription);
    }

    public JsonSerializer createKeySerializer(SerializationConfig serializationconfig, JavaType javatype, BeanProperty beanproperty)
    {
        if (_factoryConfig.hasKeySerializers()) goto _L2; else goto _L1
_L1:
        JsonSerializer jsonserializer = null;
_L4:
        return jsonserializer;
_L2:
        BasicBeanDescription basicbeandescription = (BasicBeanDescription)serializationconfig.introspectClassAnnotations(javatype.getRawClass());
        jsonserializer = null;
        Iterator iterator = _factoryConfig.keySerializers().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                continue; /* Loop/switch isn't completed */
            }
            jsonserializer = ((Serializers)iterator.next()).findSerializer(serializationconfig, javatype, basicbeandescription, beanproperty);
        } while (jsonserializer == null);
        break; /* Loop/switch isn't completed */
        if (true) goto _L4; else goto _L3
_L3:
        return jsonserializer;
    }

    public JsonSerializer createSerializer(SerializationConfig serializationconfig, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        BasicBeanDescription basicbeandescription = (BasicBeanDescription)serializationconfig.introspect(javatype);
        JsonSerializer jsonserializer = findSerializerFromAnnotation(serializationconfig, basicbeandescription.getClassInfo(), beanproperty);
        if (jsonserializer != null)
        {
            return jsonserializer;
        }
        JavaType javatype1 = modifyTypeByAnnotation(serializationconfig, basicbeandescription.getClassInfo(), javatype);
        boolean flag;
        if (javatype1 != javatype)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (javatype.isContainerType())
        {
            return buildContainerSerializer(serializationconfig, javatype1, basicbeandescription, beanproperty, flag);
        }
        for (Iterator iterator = _factoryConfig.serializers().iterator(); iterator.hasNext();)
        {
            JsonSerializer jsonserializer4 = ((Serializers)iterator.next()).findSerializer(serializationconfig, javatype1, basicbeandescription, beanproperty);
            if (jsonserializer4 != null)
            {
                return jsonserializer4;
            }
        }

        JsonSerializer jsonserializer1 = findSerializerByLookup(javatype1, serializationconfig, basicbeandescription, beanproperty, flag);
        if (jsonserializer1 != null)
        {
            return jsonserializer1;
        }
        JsonSerializer jsonserializer2 = findSerializerByPrimaryType(javatype1, serializationconfig, basicbeandescription, beanproperty, flag);
        if (jsonserializer2 != null)
        {
            return jsonserializer2;
        }
        JsonSerializer jsonserializer3 = findBeanSerializer(serializationconfig, javatype1, basicbeandescription, beanproperty);
        if (jsonserializer3 == null)
        {
            jsonserializer3 = super.findSerializerByAddonType(serializationconfig, javatype1, basicbeandescription, beanproperty, flag);
        }
        return jsonserializer3;
    }

    protected Iterable customSerializers()
    {
        return _factoryConfig.serializers();
    }

    protected List filterBeanProperties(SerializationConfig serializationconfig, BasicBeanDescription basicbeandescription, List list)
    {
        String as[] = serializationconfig.getAnnotationIntrospector().findPropertiesToIgnore(basicbeandescription.getClassInfo());
        if (as != null && as.length > 0)
        {
            HashSet hashset = ArrayBuilders.arrayToSet(as);
            Iterator iterator = list.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                if (hashset.contains(((BeanPropertyWriter)iterator.next()).getName()))
                {
                    iterator.remove();
                }
            } while (true);
        }
        return list;
    }

    protected List findBeanProperties(SerializationConfig serializationconfig, BasicBeanDescription basicbeandescription)
        throws JsonMappingException
    {
        VisibilityChecker visibilitychecker = serializationconfig.getDefaultVisibilityChecker();
        if (!serializationconfig.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.AUTO_DETECT_GETTERS))
        {
            org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility2 = org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;
            visibilitychecker = visibilitychecker.withGetterVisibility(visibility2);
        }
        if (!serializationconfig.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS))
        {
            org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility1 = org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;
            visibilitychecker = visibilitychecker.withIsGetterVisibility(visibility1);
        }
        if (!serializationconfig.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.AUTO_DETECT_FIELDS))
        {
            org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility = org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;
            visibilitychecker = visibilitychecker.withFieldVisibility(visibility);
        }
        AnnotationIntrospector annotationintrospector = serializationconfig.getAnnotationIntrospector();
        VisibilityChecker visibilitychecker1 = annotationintrospector.findAutoDetectVisibility(basicbeandescription.getClassInfo(), visibilitychecker);
        LinkedHashMap linkedhashmap = basicbeandescription.findGetters(visibilitychecker1, null);
        LinkedHashMap linkedhashmap1 = basicbeandescription.findSerializableFields(visibilitychecker1, linkedhashmap.keySet());
        removeIgnorableTypes(serializationconfig, basicbeandescription, linkedhashmap);
        removeIgnorableTypes(serializationconfig, basicbeandescription, linkedhashmap1);
        Object obj;
        if (linkedhashmap.isEmpty() && linkedhashmap1.isEmpty())
        {
            obj = null;
        } else
        {
            boolean flag = usesStaticTyping(serializationconfig, basicbeandescription, null, null);
            PropertyBuilder propertybuilder = constructPropertyBuilder(serializationconfig, basicbeandescription);
            int i = linkedhashmap.size();
            obj = new ArrayList(i);
            TypeBindings typebindings = basicbeandescription.bindingsForBeanType();
            Iterator iterator = linkedhashmap1.entrySet().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator.next();
                org.codehaus.jackson.map.AnnotationIntrospector.ReferenceProperty referenceproperty1 = annotationintrospector.findReferenceType((AnnotatedMember)entry1.getValue());
                if (referenceproperty1 == null || !referenceproperty1.isBackReference())
                {
                    BeanPropertyWriter beanpropertywriter1 = _constructWriter(serializationconfig, typebindings, propertybuilder, flag, (String)entry1.getKey(), (AnnotatedMember)entry1.getValue());
                    ((ArrayList) (obj)).add(beanpropertywriter1);
                }
            } while (true);
            Iterator iterator1 = linkedhashmap.entrySet().iterator();
            while (iterator1.hasNext()) 
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
                org.codehaus.jackson.map.AnnotationIntrospector.ReferenceProperty referenceproperty = annotationintrospector.findReferenceType((AnnotatedMember)entry.getValue());
                if (referenceproperty == null || !referenceproperty.isBackReference())
                {
                    BeanPropertyWriter beanpropertywriter = _constructWriter(serializationconfig, typebindings, propertybuilder, flag, (String)entry.getKey(), (AnnotatedMember)entry.getValue());
                    ((ArrayList) (obj)).add(beanpropertywriter);
                }
            }
        }
        return ((List) (obj));
    }

    public JsonSerializer findBeanSerializer(SerializationConfig serializationconfig, JavaType javatype, BasicBeanDescription basicbeandescription, BeanProperty beanproperty)
        throws JsonMappingException
    {
        if (isPotentialBeanType(javatype.getRawClass())) goto _L2; else goto _L1
_L1:
        JsonSerializer jsonserializer = null;
_L4:
        return jsonserializer;
_L2:
        jsonserializer = constructBeanSerializer(serializationconfig, basicbeandescription, beanproperty);
        if (_factoryConfig.hasSerializerModifiers())
        {
            Iterator iterator = _factoryConfig.serializerModifiers().iterator();
            while (iterator.hasNext()) 
            {
                jsonserializer = ((BeanSerializerModifier)iterator.next()).modifySerializer(serializationconfig, basicbeandescription, jsonserializer);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected Object findFilterId(SerializationConfig serializationconfig, BasicBeanDescription basicbeandescription)
    {
        return serializationconfig.getAnnotationIntrospector().findFilterId(basicbeandescription.getClassInfo());
    }

    public TypeSerializer findPropertyContentTypeSerializer(JavaType javatype, SerializationConfig serializationconfig, AnnotatedMember annotatedmember, BeanProperty beanproperty)
        throws JsonMappingException
    {
        JavaType javatype1 = javatype.getContentType();
        AnnotationIntrospector annotationintrospector = serializationconfig.getAnnotationIntrospector();
        TypeResolverBuilder typeresolverbuilder = annotationintrospector.findPropertyContentTypeResolver(serializationconfig, annotatedmember, javatype);
        if (typeresolverbuilder == null)
        {
            return createTypeSerializer(serializationconfig, javatype1, beanproperty);
        } else
        {
            return typeresolverbuilder.buildTypeSerializer(serializationconfig, javatype1, serializationconfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedmember, serializationconfig, annotationintrospector), beanproperty);
        }
    }

    public TypeSerializer findPropertyTypeSerializer(JavaType javatype, SerializationConfig serializationconfig, AnnotatedMember annotatedmember, BeanProperty beanproperty)
        throws JsonMappingException
    {
        AnnotationIntrospector annotationintrospector = serializationconfig.getAnnotationIntrospector();
        TypeResolverBuilder typeresolverbuilder = annotationintrospector.findPropertyTypeResolver(serializationconfig, annotatedmember, javatype);
        if (typeresolverbuilder == null)
        {
            return createTypeSerializer(serializationconfig, javatype, beanproperty);
        } else
        {
            return typeresolverbuilder.buildTypeSerializer(serializationconfig, javatype, serializationconfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedmember, serializationconfig, annotationintrospector), beanproperty);
        }
    }

    public org.codehaus.jackson.map.SerializerFactory.Config getConfig()
    {
        return _factoryConfig;
    }

    protected boolean isPotentialBeanType(Class class1)
    {
        return ClassUtil.canBeABeanType(class1) == null && !ClassUtil.isProxyType(class1);
    }

    protected void processViews(SerializationConfig serializationconfig, BeanSerializerBuilder beanserializerbuilder)
    {
        List list = beanserializerbuilder.getProperties();
        boolean flag = serializationconfig.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION);
        int i = list.size();
        int j = 0;
        BeanPropertyWriter abeanpropertywriter[] = new BeanPropertyWriter[i];
        int k = 0;
        while (k < i) 
        {
            BeanPropertyWriter beanpropertywriter = (BeanPropertyWriter)list.get(k);
            Class aclass[] = beanpropertywriter.getViews();
            if (aclass == null)
            {
                if (flag)
                {
                    abeanpropertywriter[k] = beanpropertywriter;
                }
            } else
            {
                j++;
                abeanpropertywriter[k] = constructFilteredBeanWriter(beanpropertywriter, aclass);
            }
            k++;
        }
        if (flag && j == 0)
        {
            return;
        } else
        {
            beanserializerbuilder.setFilteredProperties(abeanpropertywriter);
            return;
        }
    }

    protected void removeIgnorableTypes(SerializationConfig serializationconfig, BasicBeanDescription basicbeandescription, Map map)
    {
        if (!map.isEmpty())
        {
            AnnotationIntrospector annotationintrospector = serializationconfig.getAnnotationIntrospector();
            Iterator iterator = map.entrySet().iterator();
            HashMap hashmap = new HashMap();
            while (iterator.hasNext()) 
            {
                Class class1 = ((AnnotatedMember)((java.util.Map.Entry)iterator.next()).getValue()).getRawType();
                Boolean boolean1 = (Boolean)hashmap.get(class1);
                if (boolean1 == null)
                {
                    boolean1 = annotationintrospector.isIgnorableType(((BasicBeanDescription)serializationconfig.introspectClassAnnotations(class1)).getClassInfo());
                    if (boolean1 == null)
                    {
                        boolean1 = Boolean.FALSE;
                    }
                    hashmap.put(class1, boolean1);
                }
                if (boolean1.booleanValue())
                {
                    iterator.remove();
                }
            }
        }
    }

    protected List sortBeanProperties(SerializationConfig serializationconfig, BasicBeanDescription basicbeandescription, List list)
    {
        List list1 = basicbeandescription.findCreatorPropertyNames();
        AnnotationIntrospector annotationintrospector = serializationconfig.getAnnotationIntrospector();
        org.codehaus.jackson.map.introspect.AnnotatedClass annotatedclass = basicbeandescription.getClassInfo();
        String as[] = annotationintrospector.findSerializationPropertyOrder(annotatedclass);
        Boolean boolean1 = annotationintrospector.findSerializationSortAlphabetically(annotatedclass);
        boolean flag;
        if (boolean1 == null)
        {
            flag = serializationconfig.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.SORT_PROPERTIES_ALPHABETICALLY);
        } else
        {
            flag = boolean1.booleanValue();
        }
        if (flag || !list1.isEmpty() || as != null)
        {
            list = _sortBeanProperties(list, list1, as, flag);
        }
        return list;
    }

    public SerializerFactory withConfig(org.codehaus.jackson.map.SerializerFactory.Config config)
    {
        if (_factoryConfig == config)
        {
            return this;
        }
        if (getClass() != org/codehaus/jackson/map/ser/BeanSerializerFactory)
        {
            throw new IllegalStateException((new StringBuilder()).append("Subtype of BeanSerializerFactory (").append(getClass().getName()).append(") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with ").append("additional serializer definitions").toString());
        } else
        {
            return new BeanSerializerFactory(config);
        }
    }

}
