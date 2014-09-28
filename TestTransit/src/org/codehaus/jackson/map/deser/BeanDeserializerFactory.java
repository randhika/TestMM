// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.map.AbstractTypeResolver;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializerFactory;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.Deserializers;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.KeyDeserializers;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            BasicDeserializerFactory, CreatorContainer, SettableBeanProperty, BeanDeserializerBuilder, 
//            BeanDeserializerModifier, BeanDeserializer, ThrowableDeserializer, SettableAnyProperty, 
//            AbstractDeserializer

public class BeanDeserializerFactory extends BasicDeserializerFactory
{
    public static class ConfigImpl extends org.codehaus.jackson.map.DeserializerFactory.Config
    {

        protected static final AbstractTypeResolver NO_ABSTRACT_TYPE_RESOLVERS[] = new AbstractTypeResolver[0];
        protected static final KeyDeserializers NO_KEY_DESERIALIZERS[] = new KeyDeserializers[0];
        protected static final BeanDeserializerModifier NO_MODIFIERS[] = new BeanDeserializerModifier[0];
        protected final AbstractTypeResolver _abstractTypeResolvers[];
        protected final Deserializers _additionalDeserializers[];
        protected final KeyDeserializers _additionalKeyDeserializers[];
        protected final BeanDeserializerModifier _modifiers[];

        public Iterable abstractTypeResolvers()
        {
            return ArrayBuilders.arrayAsIterable(_abstractTypeResolvers);
        }

        public Iterable deserializerModifiers()
        {
            return ArrayBuilders.arrayAsIterable(_modifiers);
        }

        public Iterable deserializers()
        {
            return ArrayBuilders.arrayAsIterable(_additionalDeserializers);
        }

        public boolean hasAbstractTypeResolvers()
        {
            return _abstractTypeResolvers.length > 0;
        }

        public boolean hasDeserializerModifiers()
        {
            return _modifiers.length > 0;
        }

        public boolean hasDeserializers()
        {
            return _additionalDeserializers.length > 0;
        }

        public boolean hasKeyDeserializers()
        {
            return _additionalKeyDeserializers.length > 0;
        }

        public Iterable keyDeserializers()
        {
            return ArrayBuilders.arrayAsIterable(_additionalKeyDeserializers);
        }

        public org.codehaus.jackson.map.DeserializerFactory.Config withAbstractTypeResolver(AbstractTypeResolver abstracttyperesolver)
        {
            if (abstracttyperesolver == null)
            {
                throw new IllegalArgumentException("Can not pass null resolver");
            } else
            {
                AbstractTypeResolver aabstracttyperesolver[] = (AbstractTypeResolver[])ArrayBuilders.insertInListNoDup(_abstractTypeResolvers, abstracttyperesolver);
                return new ConfigImpl(_additionalDeserializers, _additionalKeyDeserializers, _modifiers, aabstracttyperesolver);
            }
        }

        public org.codehaus.jackson.map.DeserializerFactory.Config withAdditionalDeserializers(Deserializers deserializers1)
        {
            if (deserializers1 == null)
            {
                throw new IllegalArgumentException("Can not pass null Deserializers");
            } else
            {
                return new ConfigImpl((Deserializers[])ArrayBuilders.insertInListNoDup(_additionalDeserializers, deserializers1), _additionalKeyDeserializers, _modifiers, _abstractTypeResolvers);
            }
        }

        public org.codehaus.jackson.map.DeserializerFactory.Config withAdditionalKeyDeserializers(KeyDeserializers keydeserializers)
        {
            if (keydeserializers == null)
            {
                throw new IllegalArgumentException("Can not pass null KeyDeserializers");
            } else
            {
                KeyDeserializers akeydeserializers[] = (KeyDeserializers[])ArrayBuilders.insertInListNoDup(_additionalKeyDeserializers, keydeserializers);
                return new ConfigImpl(_additionalDeserializers, akeydeserializers, _modifiers, _abstractTypeResolvers);
            }
        }

        public org.codehaus.jackson.map.DeserializerFactory.Config withDeserializerModifier(BeanDeserializerModifier beandeserializermodifier)
        {
            if (beandeserializermodifier == null)
            {
                throw new IllegalArgumentException("Can not pass null modifier");
            } else
            {
                BeanDeserializerModifier abeandeserializermodifier[] = (BeanDeserializerModifier[])ArrayBuilders.insertInListNoDup(_modifiers, beandeserializermodifier);
                return new ConfigImpl(_additionalDeserializers, _additionalKeyDeserializers, abeandeserializermodifier, _abstractTypeResolvers);
            }
        }


        public ConfigImpl()
        {
            this(null, null, null, null);
        }

        protected ConfigImpl(Deserializers adeserializers[], KeyDeserializers akeydeserializers[], BeanDeserializerModifier abeandeserializermodifier[], AbstractTypeResolver aabstracttyperesolver[])
        {
            if (adeserializers == null)
            {
                adeserializers = 
// JavaClassFileOutputException: get_constant: invalid tag
    }


    private static final Class INIT_CAUSE_PARAMS[] = {
        java/lang/Throwable
    };
    public static final BeanDeserializerFactory instance = new BeanDeserializerFactory(null);
    protected final org.codehaus.jackson.map.DeserializerFactory.Config _factoryConfig;

    public BeanDeserializerFactory()
    {
        BeanDeserializerFactory(null);
    }

    public BeanDeserializerFactory(org.codehaus.jackson.map.DeserializerFactory.Config config)
    {
        if (config == null)
        {
            config = new ConfigImpl();
        }
        _factoryConfig = config;
    }

    protected void _addDeserializerConstructors(DeserializationConfig deserializationconfig, BasicBeanDescription basicbeandescription, VisibilityChecker visibilitychecker, AnnotationIntrospector annotationintrospector, CreatorContainer creatorcontainer)
        throws JsonMappingException
    {
        Iterator iterator = basicbeandescription.getConstructors().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            AnnotatedConstructor annotatedconstructor = (AnnotatedConstructor)iterator.next();
            int i = annotatedconstructor.getParameterCount();
            if (i >= 1)
            {
                boolean flag = annotationintrospector.hasCreatorAnnotation(annotatedconstructor);
                boolean flag1 = visibilitychecker.isCreatorVisible(annotatedconstructor);
                if (i == 1)
                {
                    org.codehaus.jackson.map.introspect.AnnotatedParameter annotatedparameter1 = annotatedconstructor.getParameter(0);
                    String s1 = annotationintrospector.findPropertyNameForParam(annotatedparameter1);
                    if (s1 == null || s1.length() == 0)
                    {
                        Class class1 = annotatedconstructor.getParameterClass(0);
                        if (class1 == java/lang/String)
                        {
                            if (flag || flag1)
                            {
                                creatorcontainer.addStringConstructor(annotatedconstructor);
                            }
                        } else
                        if (class1 == Integer.TYPE || class1 == java/lang/Integer)
                        {
                            if (flag || flag1)
                            {
                                creatorcontainer.addIntConstructor(annotatedconstructor);
                            }
                        } else
                        if (class1 == Long.TYPE || class1 == java/lang/Long)
                        {
                            if (flag || flag1)
                            {
                                creatorcontainer.addLongConstructor(annotatedconstructor);
                            }
                        } else
                        if (flag)
                        {
                            creatorcontainer.addDelegatingConstructor(annotatedconstructor);
                        }
                    } else
                    {
                        SettableBeanProperty asettablebeanproperty1[] = new SettableBeanProperty[1];
                        asettablebeanproperty1[0] = constructCreatorProperty(deserializationconfig, basicbeandescription, s1, 0, annotatedparameter1);
                        creatorcontainer.addPropertyConstructor(annotatedconstructor, asettablebeanproperty1);
                    }
                } else
                if (flag || flag1)
                {
                    boolean flag2 = false;
                    boolean flag3 = false;
                    SettableBeanProperty asettablebeanproperty[] = new SettableBeanProperty[i];
                    for (int j = 0; j < i; j++)
                    {
                        org.codehaus.jackson.map.introspect.AnnotatedParameter annotatedparameter = annotatedconstructor.getParameter(j);
                        String s;
                        boolean flag4;
                        boolean flag5;
                        if (annotatedparameter == null)
                        {
                            s = null;
                        } else
                        {
                            s = annotationintrospector.findPropertyNameForParam(annotatedparameter);
                        }
                        if (s == null || s.length() == 0)
                        {
                            flag4 = true;
                        } else
                        {
                            flag4 = false;
                        }
                        flag3 |= flag4;
                        if (!flag3)
                        {
                            flag5 = true;
                        } else
                        {
                            flag5 = false;
                        }
                        flag2 |= flag5;
                        if (flag3 && (flag2 || flag))
                        {
                            throw new IllegalArgumentException((new StringBuilder()).append("Argument #").append(j).append(" of constructor ").append(annotatedconstructor).append(" has no property name annotation; must have name when multiple-paramater constructor annotated as Creator").toString());
                        }
                        asettablebeanproperty[j] = constructCreatorProperty(deserializationconfig, basicbeandescription, s, j, annotatedparameter);
                    }

                    if (flag2)
                    {
                        creatorcontainer.addPropertyConstructor(annotatedconstructor, asettablebeanproperty);
                    }
                }
            }
        } while (true);
    }

    protected void _addDeserializerFactoryMethods(DeserializationConfig deserializationconfig, BasicBeanDescription basicbeandescription, VisibilityChecker visibilitychecker, AnnotationIntrospector annotationintrospector, CreatorContainer creatorcontainer)
        throws JsonMappingException
    {
        Iterator iterator = basicbeandescription.getFactoryMethods().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            AnnotatedMethod annotatedmethod = (AnnotatedMethod)iterator.next();
            int i = annotatedmethod.getParameterCount();
            if (i < 1)
            {
                continue;
            }
            boolean flag = annotationintrospector.hasCreatorAnnotation(annotatedmethod);
            if (i == 1)
            {
                String s1 = annotationintrospector.findPropertyNameForParam(annotatedmethod.getParameter(0));
                if (s1 == null || s1.length() == 0)
                {
                    Class class1 = annotatedmethod.getParameterClass(0);
                    if (class1 == java/lang/String)
                    {
                        if (flag || visibilitychecker.isCreatorVisible(annotatedmethod))
                        {
                            creatorcontainer.addStringFactory(annotatedmethod);
                        }
                    } else
                    if (class1 == Integer.TYPE || class1 == java/lang/Integer)
                    {
                        if (flag || visibilitychecker.isCreatorVisible(annotatedmethod))
                        {
                            creatorcontainer.addIntFactory(annotatedmethod);
                        }
                    } else
                    if (class1 == Long.TYPE || class1 == java/lang/Long)
                    {
                        if (flag || visibilitychecker.isCreatorVisible(annotatedmethod))
                        {
                            creatorcontainer.addLongFactory(annotatedmethod);
                        }
                    } else
                    if (annotationintrospector.hasCreatorAnnotation(annotatedmethod))
                    {
                        creatorcontainer.addDelegatingFactory(annotatedmethod);
                    }
                    continue;
                }
            } else
            if (!annotationintrospector.hasCreatorAnnotation(annotatedmethod))
            {
                continue;
            }
            SettableBeanProperty asettablebeanproperty[] = new SettableBeanProperty[i];
            for (int j = 0; j < i; j++)
            {
                org.codehaus.jackson.map.introspect.AnnotatedParameter annotatedparameter = annotatedmethod.getParameter(j);
                String s = annotationintrospector.findPropertyNameForParam(annotatedparameter);
                if (s == null || s.length() == 0)
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Argument #").append(j).append(" of factory method ").append(annotatedmethod).append(" has no property name annotation; must have when multiple-paramater static method annotated as Creator").toString());
                }
                asettablebeanproperty[j] = constructCreatorProperty(deserializationconfig, basicbeandescription, s, j, annotatedparameter);
            }

            creatorcontainer.addPropertyFactory(annotatedmethod, asettablebeanproperty);
        } while (true);
    }

    protected JsonDeserializer _findCustomArrayDeserializer(ArrayType arraytype, DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, BeanProperty beanproperty, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        for (Iterator iterator = _factoryConfig.deserializers().iterator(); iterator.hasNext();)
        {
            JsonDeserializer jsondeserializer1 = ((Deserializers)iterator.next()).findArrayDeserializer(arraytype, deserializationconfig, deserializerprovider, beanproperty, typedeserializer, jsondeserializer);
            if (jsondeserializer1 != null)
            {
                return jsondeserializer1;
            }
        }

        return null;
    }

    protected JsonDeserializer _findCustomBeanDeserializer(JavaType javatype, DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, BasicBeanDescription basicbeandescription, BeanProperty beanproperty)
        throws JsonMappingException
    {
        for (Iterator iterator = _factoryConfig.deserializers().iterator(); iterator.hasNext();)
        {
            JsonDeserializer jsondeserializer = ((Deserializers)iterator.next()).findBeanDeserializer(javatype, deserializationconfig, deserializerprovider, basicbeandescription, beanproperty);
            if (jsondeserializer != null)
            {
                return jsondeserializer;
            }
        }

        return null;
    }

    protected JsonDeserializer _findCustomCollectionDeserializer(CollectionType collectiontype, DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        for (Iterator iterator = _factoryConfig.deserializers().iterator(); iterator.hasNext();)
        {
            JsonDeserializer jsondeserializer1 = ((Deserializers)iterator.next()).findCollectionDeserializer(collectiontype, deserializationconfig, deserializerprovider, basicbeandescription, beanproperty, typedeserializer, jsondeserializer);
            if (jsondeserializer1 != null)
            {
                return jsondeserializer1;
            }
        }

        return null;
    }

    protected JsonDeserializer _findCustomCollectionLikeDeserializer(CollectionLikeType collectionliketype, DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        for (Iterator iterator = _factoryConfig.deserializers().iterator(); iterator.hasNext();)
        {
            JsonDeserializer jsondeserializer1 = ((Deserializers)iterator.next()).findCollectionLikeDeserializer(collectionliketype, deserializationconfig, deserializerprovider, basicbeandescription, beanproperty, typedeserializer, jsondeserializer);
            if (jsondeserializer1 != null)
            {
                return jsondeserializer1;
            }
        }

        return null;
    }

    protected JsonDeserializer _findCustomEnumDeserializer(Class class1, DeserializationConfig deserializationconfig, BasicBeanDescription basicbeandescription, BeanProperty beanproperty)
        throws JsonMappingException
    {
        for (Iterator iterator = _factoryConfig.deserializers().iterator(); iterator.hasNext();)
        {
            JsonDeserializer jsondeserializer = ((Deserializers)iterator.next()).findEnumDeserializer(class1, deserializationconfig, basicbeandescription, beanproperty);
            if (jsondeserializer != null)
            {
                return jsondeserializer;
            }
        }

        return null;
    }

    protected JsonDeserializer _findCustomMapDeserializer(MapType maptype, DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, KeyDeserializer keydeserializer, TypeDeserializer typedeserializer, 
            JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        for (Iterator iterator = _factoryConfig.deserializers().iterator(); iterator.hasNext();)
        {
            JsonDeserializer jsondeserializer1 = ((Deserializers)iterator.next()).findMapDeserializer(maptype, deserializationconfig, deserializerprovider, basicbeandescription, beanproperty, keydeserializer, typedeserializer, jsondeserializer);
            if (jsondeserializer1 != null)
            {
                return jsondeserializer1;
            }
        }

        return null;
    }

    protected JsonDeserializer _findCustomMapLikeDeserializer(MapLikeType mapliketype, DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, BasicBeanDescription basicbeandescription, BeanProperty beanproperty, KeyDeserializer keydeserializer, TypeDeserializer typedeserializer, 
            JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        for (Iterator iterator = _factoryConfig.deserializers().iterator(); iterator.hasNext();)
        {
            JsonDeserializer jsondeserializer1 = ((Deserializers)iterator.next()).findMapLikeDeserializer(mapliketype, deserializationconfig, deserializerprovider, basicbeandescription, beanproperty, keydeserializer, typedeserializer, jsondeserializer);
            if (jsondeserializer1 != null)
            {
                return jsondeserializer1;
            }
        }

        return null;
    }

    protected JsonDeserializer _findCustomTreeNodeDeserializer(Class class1, DeserializationConfig deserializationconfig, BeanProperty beanproperty)
        throws JsonMappingException
    {
        for (Iterator iterator = _factoryConfig.deserializers().iterator(); iterator.hasNext();)
        {
            JsonDeserializer jsondeserializer = ((Deserializers)iterator.next()).findTreeNodeDeserializer(class1, deserializationconfig, beanproperty);
            if (jsondeserializer != null)
            {
                return jsondeserializer;
            }
        }

        return null;
    }

    protected JavaType _mapAbstractType2(DeserializationConfig deserializationconfig, JavaType javatype)
        throws JsonMappingException
    {
        Class class1 = javatype.getRawClass();
        if (!_factoryConfig.hasAbstractTypeResolvers()) goto _L2; else goto _L1
_L1:
        Iterator iterator = _factoryConfig.abstractTypeResolvers().iterator();
_L5:
        if (!iterator.hasNext()) goto _L2; else goto _L3
_L3:
        JavaType javatype1 = ((AbstractTypeResolver)iterator.next()).findTypeMapping(deserializationconfig, javatype);
        if (javatype1 == null || javatype1.getRawClass() == class1) goto _L5; else goto _L4
_L4:
        return javatype1;
_L2:
        AbstractTypeResolver abstracttyperesolver = deserializationconfig.getAbstractTypeResolver();
        if (abstracttyperesolver == null)
        {
            break; /* Loop/switch isn't completed */
        }
        javatype1 = abstracttyperesolver.findTypeMapping(deserializationconfig, javatype);
        if (javatype1 != null && javatype1.getRawClass() != class1) goto _L4; else goto _L6
_L6:
        return null;
    }

    protected void addBeanProps(DeserializationConfig deserializationconfig, BasicBeanDescription basicbeandescription, BeanDeserializerBuilder beandeserializerbuilder)
        throws JsonMappingException
    {
        VisibilityChecker visibilitychecker = deserializationconfig.getDefaultVisibilityChecker();
        if (!deserializationconfig.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.AUTO_DETECT_SETTERS))
        {
            visibilitychecker = visibilitychecker.withSetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE);
        }
        if (!deserializationconfig.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.AUTO_DETECT_FIELDS))
        {
            visibilitychecker = visibilitychecker.withFieldVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE);
        }
        VisibilityChecker visibilitychecker1 = deserializationconfig.getAnnotationIntrospector().findAutoDetectVisibility(basicbeandescription.getClassInfo(), visibilitychecker);
        LinkedHashMap linkedhashmap = basicbeandescription.findSetters(visibilitychecker1);
        AnnotatedMethod annotatedmethod = basicbeandescription.findAnySetter();
        AnnotationIntrospector annotationintrospector = deserializationconfig.getAnnotationIntrospector();
        Boolean boolean1 = annotationintrospector.findIgnoreUnknownProperties(basicbeandescription.getClassInfo());
        if (boolean1 != null)
        {
            beandeserializerbuilder.setIgnoreUnknownProperties(boolean1.booleanValue());
        }
        HashSet hashset = ArrayBuilders.arrayToSet(annotationintrospector.findPropertiesToIgnore(basicbeandescription.getClassInfo()));
        for (Iterator iterator = hashset.iterator(); iterator.hasNext(); beandeserializerbuilder.addIgnorable((String)iterator.next())) { }
        AnnotatedClass annotatedclass = basicbeandescription.getClassInfo();
        Iterator iterator1 = annotatedclass.ignoredMemberMethods().iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            String s3 = basicbeandescription.okNameForSetter((AnnotatedMethod)iterator1.next());
            if (s3 != null)
            {
                beandeserializerbuilder.addIgnorable(s3);
            }
        } while (true);
        for (Iterator iterator2 = annotatedclass.ignoredFields().iterator(); iterator2.hasNext(); beandeserializerbuilder.addIgnorable(((AnnotatedField)iterator2.next()).getName())) { }
        HashMap hashmap = new HashMap();
        Iterator iterator3 = linkedhashmap.entrySet().iterator();
        do
        {
            if (!iterator3.hasNext())
            {
                break;
            }
            java.util.Map.Entry entry2 = (java.util.Map.Entry)iterator3.next();
            String s2 = (String)entry2.getKey();
            if (!hashset.contains(s2))
            {
                AnnotatedMethod annotatedmethod2 = (AnnotatedMethod)entry2.getValue();
                if (isIgnorableType(deserializationconfig, basicbeandescription, annotatedmethod2.getParameterClass(0), hashmap))
                {
                    beandeserializerbuilder.addIgnorable(s2);
                } else
                {
                    SettableBeanProperty settablebeanproperty1 = constructSettableProperty(deserializationconfig, basicbeandescription, s2, annotatedmethod2);
                    if (settablebeanproperty1 != null)
                    {
                        beandeserializerbuilder.addProperty(settablebeanproperty1);
                    }
                }
            }
        } while (true);
        if (annotatedmethod != null)
        {
            beandeserializerbuilder.setAnySetter(constructAnySetter(deserializationconfig, basicbeandescription, annotatedmethod));
        }
        HashSet hashset1 = new HashSet(linkedhashmap.keySet());
        Iterator iterator4 = basicbeandescription.findDeserializableFields(visibilitychecker1, hashset1).entrySet().iterator();
        do
        {
            if (!iterator4.hasNext())
            {
                break;
            }
            java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator4.next();
            String s1 = (String)entry1.getKey();
            if (!hashset.contains(s1) && !beandeserializerbuilder.hasProperty(s1))
            {
                AnnotatedField annotatedfield = (AnnotatedField)entry1.getValue();
                if (isIgnorableType(deserializationconfig, basicbeandescription, annotatedfield.getRawType(), hashmap))
                {
                    beandeserializerbuilder.addIgnorable(s1);
                } else
                {
                    SettableBeanProperty settablebeanproperty = constructSettableProperty(deserializationconfig, basicbeandescription, s1, annotatedfield);
                    if (settablebeanproperty != null)
                    {
                        beandeserializerbuilder.addProperty(settablebeanproperty);
                        hashset1.add(s1);
                    }
                }
            }
        } while (true);
        if (deserializationconfig.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.USE_GETTERS_AS_SETTERS))
        {
            Iterator iterator5 = basicbeandescription.findGetters(visibilitychecker1, hashset1).entrySet().iterator();
            do
            {
                if (!iterator5.hasNext())
                {
                    break;
                }
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator5.next();
                AnnotatedMethod annotatedmethod1 = (AnnotatedMethod)entry.getValue();
                Class class1 = annotatedmethod1.getRawType();
                if (java/util/Collection.isAssignableFrom(class1) || java/util/Map.isAssignableFrom(class1))
                {
                    String s = (String)entry.getKey();
                    if (!hashset.contains(s) && !beandeserializerbuilder.hasProperty(s))
                    {
                        beandeserializerbuilder.addProperty(constructSetterlessProperty(deserializationconfig, basicbeandescription, s, annotatedmethod1));
                        hashset1.add(s);
                    }
                }
            } while (true);
        }
    }

    protected void addReferenceProperties(DeserializationConfig deserializationconfig, BasicBeanDescription basicbeandescription, BeanDeserializerBuilder beandeserializerbuilder)
        throws JsonMappingException
    {
        Map map = basicbeandescription.findBackReferenceProperties();
        if (map != null)
        {
            for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                String s = (String)entry.getKey();
                AnnotatedMember annotatedmember = (AnnotatedMember)entry.getValue();
                if (annotatedmember instanceof AnnotatedMethod)
                {
                    beandeserializerbuilder.addBackReferenceProperty(s, constructSettableProperty(deserializationconfig, basicbeandescription, annotatedmember.getName(), (AnnotatedMethod)annotatedmember));
                } else
                {
                    beandeserializerbuilder.addBackReferenceProperty(s, constructSettableProperty(deserializationconfig, basicbeandescription, annotatedmember.getName(), (AnnotatedField)annotatedmember));
                }
            }

        }
    }

    public JsonDeserializer buildBeanDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, BasicBeanDescription basicbeandescription, BeanProperty beanproperty)
        throws JsonMappingException
    {
        BeanDeserializerBuilder beandeserializerbuilder = constructBeanDeserializerBuilder(basicbeandescription);
        beandeserializerbuilder.setCreators(findDeserializerCreators(deserializationconfig, basicbeandescription));
        addBeanProps(deserializationconfig, basicbeandescription, beandeserializerbuilder);
        addReferenceProperties(deserializationconfig, basicbeandescription, beandeserializerbuilder);
        if (_factoryConfig.hasDeserializerModifiers())
        {
            for (Iterator iterator1 = _factoryConfig.deserializerModifiers().iterator(); iterator1.hasNext();)
            {
                beandeserializerbuilder = ((BeanDeserializerModifier)iterator1.next()).updateBuilder(deserializationconfig, basicbeandescription, beandeserializerbuilder);
            }

        }
        JsonDeserializer jsondeserializer = beandeserializerbuilder.build(beanproperty);
        if (_factoryConfig.hasDeserializerModifiers())
        {
            for (Iterator iterator = _factoryConfig.deserializerModifiers().iterator(); iterator.hasNext();)
            {
                jsondeserializer = ((BeanDeserializerModifier)iterator.next()).modifyDeserializer(deserializationconfig, basicbeandescription, jsondeserializer);
            }

        }
        return jsondeserializer;
    }

    public JsonDeserializer buildThrowableDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, BasicBeanDescription basicbeandescription, BeanProperty beanproperty)
        throws JsonMappingException
    {
        BeanDeserializerBuilder beandeserializerbuilder = constructBeanDeserializerBuilder(basicbeandescription);
        beandeserializerbuilder.setCreators(findDeserializerCreators(deserializationconfig, basicbeandescription));
        addBeanProps(deserializationconfig, basicbeandescription, beandeserializerbuilder);
        AnnotatedMethod annotatedmethod = basicbeandescription.findMethod("initCause", INIT_CAUSE_PARAMS);
        if (annotatedmethod != null)
        {
            SettableBeanProperty settablebeanproperty = constructSettableProperty(deserializationconfig, basicbeandescription, "cause", annotatedmethod);
            if (settablebeanproperty != null)
            {
                beandeserializerbuilder.addProperty(settablebeanproperty);
            }
        }
        beandeserializerbuilder.addIgnorable("localizedMessage");
        beandeserializerbuilder.addIgnorable("message");
        if (_factoryConfig.hasDeserializerModifiers())
        {
            for (Iterator iterator1 = _factoryConfig.deserializerModifiers().iterator(); iterator1.hasNext();)
            {
                beandeserializerbuilder = ((BeanDeserializerModifier)iterator1.next()).updateBuilder(deserializationconfig, basicbeandescription, beandeserializerbuilder);
            }

        }
        Object obj = beandeserializerbuilder.build(beanproperty);
        if (obj instanceof BeanDeserializer)
        {
            obj = new ThrowableDeserializer((BeanDeserializer)obj);
        }
        if (_factoryConfig.hasDeserializerModifiers())
        {
            for (Iterator iterator = _factoryConfig.deserializerModifiers().iterator(); iterator.hasNext();)
            {
                obj = ((BeanDeserializerModifier)iterator.next()).modifyDeserializer(deserializationconfig, basicbeandescription, ((JsonDeserializer) (obj)));
            }

        }
        return ((JsonDeserializer) (obj));
    }

    protected SettableAnyProperty constructAnySetter(DeserializationConfig deserializationconfig, BasicBeanDescription basicbeandescription, AnnotatedMethod annotatedmethod)
        throws JsonMappingException
    {
        if (deserializationconfig.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
        {
            annotatedmethod.fixAccess();
        }
        JavaType javatype = basicbeandescription.bindingsForBeanType().resolveType(annotatedmethod.getParameterType(1));
        org.codehaus.jackson.map.BeanProperty.Std std = new Std(annotatedmethod.getName(), javatype, basicbeandescription.getClassAnnotations(), annotatedmethod);
        JavaType javatype1 = resolveType(deserializationconfig, basicbeandescription, javatype, annotatedmethod, std);
        JsonDeserializer jsondeserializer = findDeserializerFromAnnotation(deserializationconfig, annotatedmethod, std);
        if (jsondeserializer != null)
        {
            SettableAnyProperty settableanyproperty = new SettableAnyProperty(std, annotatedmethod, javatype1);
            settableanyproperty.setValueDeserializer(jsondeserializer);
            return settableanyproperty;
        } else
        {
            return new SettableAnyProperty(std, annotatedmethod, modifyTypeByAnnotation(deserializationconfig, annotatedmethod, javatype1, std.getName()));
        }
    }

    protected BeanDeserializerBuilder constructBeanDeserializerBuilder(BasicBeanDescription basicbeandescription)
    {
        return new BeanDeserializerBuilder(basicbeandescription);
    }

    protected SettableBeanProperty constructSettableProperty(DeserializationConfig deserializationconfig, BasicBeanDescription basicbeandescription, String s, AnnotatedField annotatedfield)
        throws JsonMappingException
    {
        if (deserializationconfig.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
        {
            annotatedfield.fixAccess();
        }
        JavaType javatype = basicbeandescription.bindingsForBeanType().resolveType(annotatedfield.getGenericType());
        org.codehaus.jackson.map.BeanProperty.Std std = new Std(s, javatype, basicbeandescription.getClassAnnotations(), annotatedfield);
        JavaType javatype1 = resolveType(deserializationconfig, basicbeandescription, javatype, annotatedfield, std);
        if (javatype1 != javatype)
        {
            std = std.withType(javatype1);
        }
        JsonDeserializer jsondeserializer = findDeserializerFromAnnotation(deserializationconfig, annotatedfield, std);
        JavaType javatype2 = modifyTypeByAnnotation(deserializationconfig, annotatedfield, javatype1, s);
        SettableBeanProperty.FieldProperty fieldproperty = new FieldProperty(s, javatype2, (TypeDeserializer)javatype2.getTypeHandler(), basicbeandescription.getClassAnnotations(), annotatedfield);
        if (jsondeserializer != null)
        {
            fieldproperty.setValueDeserializer(jsondeserializer);
        }
        org.codehaus.jackson.map.AnnotationIntrospector.ReferenceProperty referenceproperty = deserializationconfig.getAnnotationIntrospector().findReferenceType(annotatedfield);
        if (referenceproperty != null && referenceproperty.isManagedReference())
        {
            fieldproperty.setManagedReferenceName(referenceproperty.getName());
        }
        return fieldproperty;
    }

    protected SettableBeanProperty constructSettableProperty(DeserializationConfig deserializationconfig, BasicBeanDescription basicbeandescription, String s, AnnotatedMethod annotatedmethod)
        throws JsonMappingException
    {
        if (deserializationconfig.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
        {
            annotatedmethod.fixAccess();
        }
        JavaType javatype = basicbeandescription.bindingsForBeanType().resolveType(annotatedmethod.getParameterType(0));
        org.codehaus.jackson.map.BeanProperty.Std std = new Std(s, javatype, basicbeandescription.getClassAnnotations(), annotatedmethod);
        JavaType javatype1 = resolveType(deserializationconfig, basicbeandescription, javatype, annotatedmethod, std);
        if (javatype1 != javatype)
        {
            std = std.withType(javatype1);
        }
        JsonDeserializer jsondeserializer = findDeserializerFromAnnotation(deserializationconfig, annotatedmethod, std);
        JavaType javatype2 = modifyTypeByAnnotation(deserializationconfig, annotatedmethod, javatype1, s);
        SettableBeanProperty.MethodProperty methodproperty = new MethodProperty(s, javatype2, (TypeDeserializer)javatype2.getTypeHandler(), basicbeandescription.getClassAnnotations(), annotatedmethod);
        if (jsondeserializer != null)
        {
            methodproperty.setValueDeserializer(jsondeserializer);
        }
        org.codehaus.jackson.map.AnnotationIntrospector.ReferenceProperty referenceproperty = deserializationconfig.getAnnotationIntrospector().findReferenceType(annotatedmethod);
        if (referenceproperty != null && referenceproperty.isManagedReference())
        {
            methodproperty.setManagedReferenceName(referenceproperty.getName());
        }
        return methodproperty;
    }

    protected SettableBeanProperty constructSetterlessProperty(DeserializationConfig deserializationconfig, BasicBeanDescription basicbeandescription, String s, AnnotatedMethod annotatedmethod)
        throws JsonMappingException
    {
        if (deserializationconfig.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
        {
            annotatedmethod.fixAccess();
        }
        JavaType javatype = annotatedmethod.getType(basicbeandescription.bindingsForBeanType());
        JsonDeserializer jsondeserializer = findDeserializerFromAnnotation(deserializationconfig, annotatedmethod, new Std(s, javatype, basicbeandescription.getClassAnnotations(), annotatedmethod));
        JavaType javatype1 = modifyTypeByAnnotation(deserializationconfig, annotatedmethod, javatype, s);
        SettableBeanProperty.SetterlessProperty setterlessproperty = new SetterlessProperty(s, javatype1, (TypeDeserializer)javatype1.getTypeHandler(), basicbeandescription.getClassAnnotations(), annotatedmethod);
        if (jsondeserializer != null)
        {
            setterlessproperty.setValueDeserializer(jsondeserializer);
        }
        return setterlessproperty;
    }

    public JsonDeserializer createBeanDeserializer(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
        if (javatype.isAbstract())
        {
            javatype = mapAbstractType(deserializationconfig, javatype);
        }
        BasicBeanDescription basicbeandescription = (BasicBeanDescription)deserializationconfig.introspect(javatype);
        JsonDeserializer jsondeserializer = findDeserializerFromAnnotation(deserializationconfig, basicbeandescription.getClassInfo(), beanproperty);
        if (jsondeserializer != null)
        {
            return jsondeserializer;
        }
        JavaType javatype1 = modifyTypeByAnnotation(deserializationconfig, basicbeandescription.getClassInfo(), javatype, null);
        if (javatype1.getRawClass() != javatype.getRawClass())
        {
            javatype = javatype1;
            basicbeandescription = (BasicBeanDescription)deserializationconfig.introspect(javatype);
        }
        JsonDeserializer jsondeserializer1 = _findCustomBeanDeserializer(javatype, deserializationconfig, deserializerprovider, basicbeandescription, beanproperty);
        if (jsondeserializer1 != null)
        {
            return jsondeserializer1;
        }
        if (javatype.isThrowable())
        {
            return buildThrowableDeserializer(deserializationconfig, javatype, basicbeandescription, beanproperty);
        }
        if (javatype.isAbstract())
        {
            JavaType javatype2 = materializeAbstractType(deserializationconfig, basicbeandescription);
            if (javatype2 != null)
            {
                return buildBeanDeserializer(deserializationconfig, javatype2, (BasicBeanDescription)deserializationconfig.introspect(javatype2), beanproperty);
            }
        }
        JsonDeserializer jsondeserializer2 = findStdBeanDeserializer(deserializationconfig, deserializerprovider, javatype, beanproperty);
        if (jsondeserializer2 != null)
        {
            return jsondeserializer2;
        }
        if (!isPotentialBeanType(javatype.getRawClass()))
        {
            return null;
        }
        if (javatype.isAbstract())
        {
            return new AbstractDeserializer(javatype);
        } else
        {
            return buildBeanDeserializer(deserializationconfig, javatype, basicbeandescription, beanproperty);
        }
    }

    public KeyDeserializer createKeyDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, BeanProperty beanproperty)
        throws JsonMappingException
    {
label0:
        {
            if (!_factoryConfig.hasKeyDeserializers())
            {
                break label0;
            }
            BasicBeanDescription basicbeandescription = (BasicBeanDescription)deserializationconfig.introspectClassAnnotations(javatype.getRawClass());
            Iterator iterator = _factoryConfig.keyDeserializers().iterator();
            KeyDeserializer keydeserializer;
            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }
                keydeserializer = ((KeyDeserializers)iterator.next()).findKeyDeserializer(javatype, deserializationconfig, basicbeandescription, beanproperty);
            } while (keydeserializer == null);
            return keydeserializer;
        }
        return null;
    }

    protected CreatorContainer findDeserializerCreators(DeserializationConfig deserializationconfig, BasicBeanDescription basicbeandescription)
        throws JsonMappingException
    {
        boolean flag = deserializationconfig.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
        CreatorContainer creatorcontainer = new CreatorContainer(basicbeandescription, flag);
        AnnotationIntrospector annotationintrospector = deserializationconfig.getAnnotationIntrospector();
        if (basicbeandescription.getType().isConcrete())
        {
            java.lang.reflect.Constructor constructor = basicbeandescription.findDefaultConstructor();
            if (constructor != null)
            {
                if (flag)
                {
                    ClassUtil.checkAndFixAccess(constructor);
                }
                creatorcontainer.setDefaultConstructor(constructor);
            }
        }
        VisibilityChecker visibilitychecker = deserializationconfig.getDefaultVisibilityChecker();
        if (!deserializationconfig.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.AUTO_DETECT_CREATORS))
        {
            visibilitychecker = visibilitychecker.withCreatorVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE);
        }
        VisibilityChecker visibilitychecker1 = deserializationconfig.getAnnotationIntrospector().findAutoDetectVisibility(basicbeandescription.getClassInfo(), visibilitychecker);
        _addDeserializerConstructors(deserializationconfig, basicbeandescription, visibilitychecker1, annotationintrospector, creatorcontainer);
        _addDeserializerFactoryMethods(deserializationconfig, basicbeandescription, visibilitychecker1, annotationintrospector, creatorcontainer);
        return creatorcontainer;
    }

    public final org.codehaus.jackson.map.DeserializerFactory.Config getConfig()
    {
        return _factoryConfig;
    }

    protected boolean isIgnorableType(DeserializationConfig deserializationconfig, BasicBeanDescription basicbeandescription, Class class1, Map map)
    {
        Boolean boolean1 = (Boolean)map.get(class1);
        if (boolean1 == null)
        {
            BasicBeanDescription basicbeandescription1 = (BasicBeanDescription)deserializationconfig.introspectClassAnnotations(class1);
            boolean1 = deserializationconfig.getAnnotationIntrospector().isIgnorableType(basicbeandescription1.getClassInfo());
            if (boolean1 == null)
            {
                boolean1 = Boolean.FALSE;
            }
        }
        return boolean1.booleanValue();
    }

    protected boolean isPotentialBeanType(Class class1)
    {
        String s = ClassUtil.canBeABeanType(class1);
        if (s != null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can not deserialize Class ").append(class1.getName()).append(" (of type ").append(s).append(") as a Bean").toString());
        }
        if (ClassUtil.isProxyType(class1))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can not deserialize Proxy class ").append(class1.getName()).append(" as a Bean").toString());
        }
        String s1 = ClassUtil.isLocalType(class1);
        if (s1 != null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can not deserialize Class ").append(class1.getName()).append(" (of type ").append(s1).append(") as a Bean").toString());
        } else
        {
            return true;
        }
    }

    protected JavaType mapAbstractType(DeserializationConfig deserializationconfig, JavaType javatype)
        throws JsonMappingException
    {
        do
        {
            JavaType javatype1 = _mapAbstractType2(deserializationconfig, javatype);
            if (javatype1 == null)
            {
                return javatype;
            }
            Class class1 = javatype.getRawClass();
            Class class2 = javatype1.getRawClass();
            if (class1 == class2 || !class1.isAssignableFrom(class2))
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid abstract type resolution from ").append(javatype).append(" to ").append(javatype1).append(": latter is not a subtype of former").toString());
            }
            javatype = javatype1;
        } while (true);
    }

    protected JavaType materializeAbstractType(DeserializationConfig deserializationconfig, BasicBeanDescription basicbeandescription)
        throws JsonMappingException
    {
        AbstractTypeResolver abstracttyperesolver = deserializationconfig.getAbstractTypeResolver();
        if (abstracttyperesolver != null || _factoryConfig.hasAbstractTypeResolvers()) goto _L2; else goto _L1
_L1:
        JavaType javatype2 = null;
_L4:
        return javatype2;
_L2:
        JavaType javatype;
        javatype = basicbeandescription.getType();
        if (deserializationconfig.getAnnotationIntrospector().findTypeResolver(deserializationconfig, basicbeandescription.getClassInfo(), javatype) != null)
        {
            return null;
        }
        if (abstracttyperesolver == null)
        {
            break; /* Loop/switch isn't completed */
        }
        javatype2 = abstracttyperesolver.resolveAbstractType(deserializationconfig, javatype);
        if (javatype2 != null) goto _L4; else goto _L3
_L3:
        for (Iterator iterator = _factoryConfig.abstractTypeResolvers().iterator(); iterator.hasNext();)
        {
            JavaType javatype1 = ((AbstractTypeResolver)iterator.next()).resolveAbstractType(deserializationconfig, javatype);
            if (javatype1 != null)
            {
                return javatype1;
            }
        }

        return null;
    }

    public DeserializerFactory withConfig(org.codehaus.jackson.map.DeserializerFactory.Config config)
    {
        if (_factoryConfig == config)
        {
            return this;
        }
        if (getClass() != org/codehaus/jackson/map/deser/BeanDeserializerFactory)
        {
            throw new IllegalStateException((new StringBuilder()).append("Subtype of BeanDeserializerFactory (").append(getClass().getName()).append(") has not properly overridden method 'withAdditionalDeserializers': can not instantiate subtype with ").append("additional deserializer definitions").toString());
        } else
        {
            return new BeanDeserializerFactory(config);
        }
    }


}
