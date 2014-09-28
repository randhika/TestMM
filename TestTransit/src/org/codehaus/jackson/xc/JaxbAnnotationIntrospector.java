// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.xc;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.ref.SoftReference;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.Versioned;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.annotate.JsonCachable;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.AnnotatedParameter;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.VersionUtil;

// Referenced classes of package org.codehaus.jackson.xc:
//            XmlAdapterJsonDeserializer, XmlAdapterJsonSerializer

public class JaxbAnnotationIntrospector extends AnnotationIntrospector
    implements Versioned
{
    private static class AnnotatedProperty
        implements AnnotatedElement
    {

        private final PropertyDescriptor pd;

        public Annotation getAnnotation(Class class1)
        {
            Method method = pd.getReadMethod();
            if (method != null)
            {
                Annotation annotation = method.getAnnotation(class1);
                if (annotation != null)
                {
                    return annotation;
                }
            }
            Method method1 = pd.getWriteMethod();
            if (method1 != null)
            {
                return method1.getAnnotation(class1);
            } else
            {
                return null;
            }
        }

        public Annotation[] getAnnotations()
        {
            throw new UnsupportedOperationException();
        }

        public Annotation[] getDeclaredAnnotations()
        {
            throw new UnsupportedOperationException();
        }

        public boolean isAnnotationPresent(Class class1)
        {
            Method method = pd.getReadMethod();
            Method method1;
            if (method == null || !method.isAnnotationPresent(class1))
            {
                if ((method1 = pd.getWriteMethod()) == null || !method1.isAnnotationPresent(class1))
                {
                    return false;
                }
            }
            return true;
        }

        private AnnotatedProperty(PropertyDescriptor propertydescriptor)
        {
            pd = propertydescriptor;
        }

    }

    protected static final class PropertyDescriptors
    {

        private Map _byMethodName;
        private Map _byPropertyName;
        private final Class _forClass;
        private final List _properties;

        private static Map _processReadMethod(Map map, Method method, String s, List list)
            throws IntrospectionException
        {
            if (map == null)
            {
                map = new HashMap();
            } else
            {
                PropertyDescriptor propertydescriptor = (PropertyDescriptor)map.get(s);
                if (propertydescriptor != null)
                {
                    propertydescriptor.setReadMethod(method);
                    if (propertydescriptor.getWriteMethod() != null)
                    {
                        list.add(propertydescriptor);
                        map.remove(s);
                        return map;
                    }
                }
            }
            map.put(s, new PropertyDescriptor(s, method, null));
            return map;
        }

        private static Map _processWriteMethod(Map map, Method method, String s, List list)
            throws IntrospectionException
        {
            if (map == null)
            {
                map = new HashMap();
            } else
            {
                PropertyDescriptor propertydescriptor = (PropertyDescriptor)map.get(s);
                if (propertydescriptor != null)
                {
                    propertydescriptor.setWriteMethod(method);
                    if (propertydescriptor.getReadMethod() != null)
                    {
                        list.add(propertydescriptor);
                        map.remove(s);
                        return map;
                    }
                }
            }
            map.put(s, new PropertyDescriptor(s, null, method));
            return map;
        }

        public static PropertyDescriptors find(Class class1)
            throws IntrospectionException
        {
            BeanInfo beaninfo = Introspector.getBeanInfo(class1);
            Object obj;
            if (beaninfo.getPropertyDescriptors().length == 0)
            {
                obj = Collections.emptyList();
            } else
            {
                obj = new ArrayList();
                Map map = null;
                PropertyDescriptor apropertydescriptor[] = beaninfo.getPropertyDescriptors();
                int i = apropertydescriptor.length;
                int j = 0;
                while (j < i) 
                {
                    PropertyDescriptor propertydescriptor = apropertydescriptor[j];
                    Method method = propertydescriptor.getReadMethod();
                    if (method != null && method.getAnnotation(javax/xml/bind/annotation/XmlTransient) != null)
                    {
                        method = null;
                    }
                    String s;
                    Method method1;
                    if (method == null)
                    {
                        s = null;
                    } else
                    {
                        s = JaxbAnnotationIntrospector.findJaxbPropertyName(method, propertydescriptor.getPropertyType(), null);
                    }
                    method1 = propertydescriptor.getWriteMethod();
                    if (method1 != null && method1.getAnnotation(javax/xml/bind/annotation/XmlTransient) != null)
                    {
                        method1 = null;
                    }
                    if (method != null || method1 != null)
                    {
                        String s1;
                        if (method1 == null)
                        {
                            s1 = null;
                        } else
                        {
                            s1 = JaxbAnnotationIntrospector.findJaxbPropertyName(method1, propertydescriptor.getPropertyType(), null);
                        }
                        if (method1 == null)
                        {
                            if (s == null)
                            {
                                s = propertydescriptor.getName();
                            }
                            map = _processReadMethod(map, method, s, ((List) (obj)));
                        } else
                        if (method == null)
                        {
                            if (s1 == null)
                            {
                                s1 = propertydescriptor.getName();
                            }
                            map = _processWriteMethod(map, method1, s1, ((List) (obj)));
                        } else
                        if (s != null && s1 != null && !s.equals(s1))
                        {
                            map = _processWriteMethod(_processReadMethod(map, method, s, ((List) (obj))), method1, s1, ((List) (obj)));
                        } else
                        {
                            String s2;
                            if (s != null)
                            {
                                s2 = s;
                            } else
                            if (s1 != null)
                            {
                                s2 = s1;
                            } else
                            {
                                s2 = propertydescriptor.getName();
                            }
                            ((List) (obj)).add(new PropertyDescriptor(s2, method, method1));
                        }
                    }
                    j++;
                }
            }
            return new PropertyDescriptors(class1, ((List) (obj)));
        }

        public PropertyDescriptor findByMethodName(String s)
        {
            if (_byMethodName == null)
            {
                _byMethodName = new HashMap(_properties.size());
                Iterator iterator = _properties.iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    PropertyDescriptor propertydescriptor = (PropertyDescriptor)iterator.next();
                    Method method = propertydescriptor.getReadMethod();
                    if (method != null)
                    {
                        _byMethodName.put(method.getName(), propertydescriptor);
                    }
                    Method method1 = propertydescriptor.getWriteMethod();
                    if (method1 != null)
                    {
                        _byMethodName.put(method1.getName(), propertydescriptor);
                    }
                } while (true);
            }
            return (PropertyDescriptor)_byMethodName.get(s);
        }

        public PropertyDescriptor findByPropertyName(String s)
        {
            if (_byPropertyName == null)
            {
                _byPropertyName = new HashMap(_properties.size());
                PropertyDescriptor propertydescriptor;
                for (Iterator iterator = _properties.iterator(); iterator.hasNext(); _byPropertyName.put(propertydescriptor.getName(), propertydescriptor))
                {
                    propertydescriptor = (PropertyDescriptor)iterator.next();
                }

            }
            return (PropertyDescriptor)_byPropertyName.get(s);
        }

        public Class getBeanClass()
        {
            return _forClass;
        }

        public PropertyDescriptors(Class class1, List list)
        {
            _forClass = class1;
            _properties = list;
        }
    }


    protected static final String MARKER_FOR_DEFAULT = "##default";
    private static final ThreadLocal _propertyDescriptors = new ThreadLocal();
    protected final JsonDeserializer _dataHandlerDeserializer;
    protected final JsonSerializer _dataHandlerSerializer;
    protected final String _jaxbPackageName = javax/xml/bind/annotation/XmlElement.getPackage().getName();

    public JaxbAnnotationIntrospector()
    {
        JsonSerializer jsonserializer = null;
        JsonDeserializer jsondeserializer;
        try
        {
            jsonserializer = (JsonSerializer)Class.forName("org.codehaus.jackson.xc.DataHandlerJsonSerializer").newInstance();
            jsondeserializer = (JsonDeserializer)Class.forName("org.codehaus.jackson.xc.DataHandlerJsonDeserializer").newInstance();
        }
        catch (Throwable throwable)
        {
            jsondeserializer = null;
        }
        _dataHandlerSerializer = jsonserializer;
        _dataHandlerDeserializer = jsondeserializer;
    }

    private final XmlAdapter checkAdapter(XmlJavaTypeAdapter xmljavatypeadapter, Class class1)
    {
        Class class2 = xmljavatypeadapter.type();
        if (class2 == javax/xml/bind/annotation/adapters/XmlJavaTypeAdapter$DEFAULT || class2.isAssignableFrom(class1))
        {
            return (XmlAdapter)ClassUtil.createInstance(xmljavatypeadapter.value(), false);
        } else
        {
            return null;
        }
    }

    protected static String findJaxbPropertyName(AnnotatedElement annotatedelement, Class class1, String s)
    {
        XmlElementWrapper xmlelementwrapper = (XmlElementWrapper)annotatedelement.getAnnotation(javax/xml/bind/annotation/XmlElementWrapper);
        if (xmlelementwrapper == null) goto _L2; else goto _L1
_L1:
        String s1 = xmlelementwrapper.name();
        if ("##default".equals(s1)) goto _L4; else goto _L3
_L3:
        return s1;
_L4:
        return s;
_L2:
        XmlAttribute xmlattribute = (XmlAttribute)annotatedelement.getAnnotation(javax/xml/bind/annotation/XmlAttribute);
        if (xmlattribute == null)
        {
            break; /* Loop/switch isn't completed */
        }
        s1 = xmlattribute.name();
        if ("##default".equals(s1))
        {
            return s;
        }
        if (true) goto _L3; else goto _L5
_L5:
        XmlElement xmlelement = (XmlElement)annotatedelement.getAnnotation(javax/xml/bind/annotation/XmlElement);
        if (xmlelement == null)
        {
            break; /* Loop/switch isn't completed */
        }
        s1 = xmlelement.name();
        if ("##default".equals(s1))
        {
            return s;
        }
        if (true) goto _L3; else goto _L6
_L6:
        XmlElementRef xmlelementref = (XmlElementRef)annotatedelement.getAnnotation(javax/xml/bind/annotation/XmlElementRef);
        if (xmlelementref == null)
        {
            break; /* Loop/switch isn't completed */
        }
        s1 = xmlelementref.name();
        if (!"##default".equals(s1))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (class1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        XmlRootElement xmlrootelement = (XmlRootElement)class1.getAnnotation(javax/xml/bind/annotation/XmlRootElement);
        if (xmlrootelement == null)
        {
            break; /* Loop/switch isn't completed */
        }
        s1 = xmlrootelement.name();
        if ("##default".equals(s1))
        {
            return Introspector.decapitalize(class1.getSimpleName());
        }
        if (true) goto _L3; else goto _L7
_L7:
        if ((XmlValue)annotatedelement.getAnnotation(javax/xml/bind/annotation/XmlValue) != null)
        {
            return "value";
        } else
        {
            return null;
        }
    }

    private XmlRootElement findRootElementAnnotation(AnnotatedClass annotatedclass)
    {
        return (XmlRootElement)findAnnotation(javax/xml/bind/annotation/XmlRootElement, annotatedclass, true, false, true);
    }

    private boolean isDataHandler(Class class1)
    {
        return class1 != null && java/lang/Object != class1 && ("javax.activation.DataHandler".equals(class1.getName()) || isDataHandler(class1.getSuperclass()));
    }

    protected Class _doFindDeserializationType(Annotated annotated, JavaType javatype, String s)
    {
        if (!annotated.hasAnnotation(javax/xml/bind/annotation/adapters/XmlJavaTypeAdapter)) goto _L2; else goto _L1
_L1:
        Class class1 = null;
_L4:
        return class1;
_L2:
        XmlElement xmlelement = (XmlElement)findAnnotation(javax/xml/bind/annotation/XmlElement, annotated, false, false, false);
        if (xmlelement == null)
        {
            break; /* Loop/switch isn't completed */
        }
        class1 = xmlelement.type();
        if (class1 != javax/xml/bind/annotation/XmlElement$DEFAULT) goto _L4; else goto _L3
_L3:
        if ((annotated instanceof AnnotatedMethod) && s != null)
        {
            XmlElement xmlelement1 = (XmlElement)findFieldAnnotation(javax/xml/bind/annotation/XmlElement, ((AnnotatedMethod)annotated).getDeclaringClass(), s);
            if (xmlelement1 != null && xmlelement1.type() != javax/xml/bind/annotation/XmlElement$DEFAULT)
            {
                return xmlelement1.type();
            }
        }
        return null;
    }

    protected TypeResolverBuilder _typeResolverFromXmlElements(AnnotatedMember annotatedmember)
    {
        XmlElements xmlelements = (XmlElements)findAnnotation(javax/xml/bind/annotation/XmlElements, annotatedmember, false, false, false);
        XmlElementRefs xmlelementrefs = (XmlElementRefs)findAnnotation(javax/xml/bind/annotation/XmlElementRefs, annotatedmember, false, false, false);
        if (xmlelements == null && xmlelementrefs == null)
        {
            return null;
        } else
        {
            return (new StdTypeResolverBuilder()).init(org.codehaus.jackson.annotate.JsonTypeInfo.Id.NAME, null).inclusion(org.codehaus.jackson.annotate.JsonTypeInfo.As.WRAPPER_OBJECT);
        }
    }

    protected XmlAccessType findAccessType(Annotated annotated)
    {
        XmlAccessorType xmlaccessortype = (XmlAccessorType)findAnnotation(javax/xml/bind/annotation/XmlAccessorType, annotated, true, true, true);
        if (xmlaccessortype == null)
        {
            return null;
        } else
        {
            return xmlaccessortype.value();
        }
    }

    protected XmlAdapter findAdapter(Annotated annotated, boolean flag)
    {
        if (!(annotated instanceof AnnotatedClass)) goto _L2; else goto _L1
_L1:
        XmlAdapter xmladapter = findAdapterForClass((AnnotatedClass)annotated, flag);
_L4:
        return xmladapter;
_L2:
        Class class1;
        class1 = annotated.getRawType();
        if (class1 == Void.TYPE && (annotated instanceof AnnotatedMethod))
        {
            class1 = ((AnnotatedMethod)annotated).getParameterClass(0);
        }
        Member member = (Member)annotated.getAnnotated();
        if (member == null)
        {
            break; /* Loop/switch isn't completed */
        }
        Class class2 = member.getDeclaringClass();
        if (class2 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        XmlJavaTypeAdapter xmljavatypeadapter1 = (XmlJavaTypeAdapter)class2.getAnnotation(javax/xml/bind/annotation/adapters/XmlJavaTypeAdapter);
        if (xmljavatypeadapter1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        xmladapter = checkAdapter(xmljavatypeadapter1, class1);
        if (xmladapter != null) goto _L4; else goto _L3
_L3:
        XmlJavaTypeAdapter xmljavatypeadapter = (XmlJavaTypeAdapter)findAnnotation(javax/xml/bind/annotation/adapters/XmlJavaTypeAdapter, annotated, true, false, false);
        if (xmljavatypeadapter == null)
        {
            break; /* Loop/switch isn't completed */
        }
        xmladapter = checkAdapter(xmljavatypeadapter, class1);
        if (xmladapter != null) goto _L4; else goto _L5
_L5:
label0:
        {
            XmlJavaTypeAdapters xmljavatypeadapters = (XmlJavaTypeAdapters)findAnnotation(javax/xml/bind/annotation/adapters/XmlJavaTypeAdapters, annotated, true, false, false);
            if (xmljavatypeadapters == null)
            {
                break label0;
            }
            XmlJavaTypeAdapter axmljavatypeadapter[] = xmljavatypeadapters.value();
            int i = axmljavatypeadapter.length;
            int j = 0;
            do
            {
                if (j >= i)
                {
                    break label0;
                }
                xmladapter = checkAdapter(axmljavatypeadapter[j], class1);
                if (xmladapter != null)
                {
                    break;
                }
                j++;
            } while (true);
        }
        if (true) goto _L4; else goto _L6
_L6:
        return null;
    }

    protected XmlAdapter findAdapterForClass(AnnotatedClass annotatedclass, boolean flag)
    {
        XmlJavaTypeAdapter xmljavatypeadapter = (XmlJavaTypeAdapter)annotatedclass.getAnnotated().getAnnotation(javax/xml/bind/annotation/adapters/XmlJavaTypeAdapter);
        if (xmljavatypeadapter != null)
        {
            return (XmlAdapter)ClassUtil.createInstance(xmljavatypeadapter.value(), false);
        } else
        {
            return null;
        }
    }

    protected Annotation findAnnotation(Class class1, Annotated annotated, boolean flag, boolean flag1, boolean flag2)
    {
        if (!(annotated instanceof AnnotatedMethod)) goto _L2; else goto _L1
_L1:
        PropertyDescriptor propertydescriptor = findPropertyDescriptor((AnnotatedMethod)annotated);
        if (propertydescriptor == null) goto _L2; else goto _L3
_L3:
        Annotation annotation = (new AnnotatedProperty(propertydescriptor)).getAnnotation(class1);
        if (annotation == null) goto _L2; else goto _L4
_L4:
        return annotation;
_L2:
        AnnotatedElement annotatedelement = annotated.getAnnotated();
        if (!(annotated instanceof AnnotatedParameter)) goto _L6; else goto _L5
_L5:
        AnnotatedParameter annotatedparameter;
        annotatedparameter = (AnnotatedParameter)annotated;
        annotation = annotatedparameter.getAnnotation(class1);
        if (annotation != null) goto _L4; else goto _L7
_L7:
        Class class2 = annotatedparameter.getMember().getDeclaringClass();
_L11:
        Class class3;
        if (class2 == null)
        {
            break MISSING_BLOCK_LABEL_264;
        }
        if (!flag2)
        {
            break MISSING_BLOCK_LABEL_250;
        }
        class3 = class2.getSuperclass();
_L9:
        if (class3 == null || class3 == java/lang/Object)
        {
            break MISSING_BLOCK_LABEL_250;
        }
        annotation = class3.getAnnotation(class1);
        if (annotation != null) goto _L4; else goto _L8
_L8:
        class3 = class3.getSuperclass();
          goto _L9
_L6:
        annotation = annotatedelement.getAnnotation(class1);
        if (annotation != null) goto _L4; else goto _L10
_L10:
        if (annotatedelement instanceof Member)
        {
            class2 = ((Member)annotatedelement).getDeclaringClass();
            if (flag1)
            {
                Annotation annotation1 = class2.getAnnotation(class1);
                if (annotation1 != null)
                {
                    return annotation1;
                }
            }
        } else
        if (annotatedelement instanceof Class)
        {
            class2 = (Class)annotatedelement;
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("Unsupported annotated member: ").append(annotated.getClass().getName()).toString());
        }
          goto _L11
        if (flag)
        {
            return class2.getPackage().getAnnotation(class1);
        }
        return null;
    }

    public VisibilityChecker findAutoDetectVisibility(AnnotatedClass annotatedclass, VisibilityChecker visibilitychecker)
    {
        XmlAccessType xmlaccesstype = findAccessType(annotatedclass);
        if (xmlaccesstype == null)
        {
            return visibilitychecker;
        }
        static class _cls1
        {

            static final int $SwitchMap$javax$xml$bind$annotation$XmlAccessType[];

            static 
            {
                $SwitchMap$javax$xml$bind$annotation$XmlAccessType = new int[XmlAccessType.values().length];
                try
                {
                    $SwitchMap$javax$xml$bind$annotation$XmlAccessType[XmlAccessType.FIELD.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$javax$xml$bind$annotation$XmlAccessType[XmlAccessType.NONE.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$javax$xml$bind$annotation$XmlAccessType[XmlAccessType.PROPERTY.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$javax$xml$bind$annotation$XmlAccessType[XmlAccessType.PUBLIC_MEMBER.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror3)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.javax.xml.bind.annotation.XmlAccessType[xmlaccesstype.ordinal()])
        {
        default:
            return visibilitychecker;

        case 1: // '\001'
            return visibilitychecker.withFieldVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.ANY).withSetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE).withGetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE).withIsGetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE);

        case 2: // '\002'
            return visibilitychecker.withFieldVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE).withSetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE).withGetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE).withIsGetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE);

        case 3: // '\003'
            return visibilitychecker.withFieldVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE).withSetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.PUBLIC_ONLY).withGetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.PUBLIC_ONLY).withIsGetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.PUBLIC_ONLY);

        case 4: // '\004'
            return visibilitychecker.withFieldVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.PUBLIC_ONLY).withSetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.PUBLIC_ONLY).withGetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.PUBLIC_ONLY).withIsGetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.PUBLIC_ONLY);
        }
    }

    public Boolean findCachability(AnnotatedClass annotatedclass)
    {
        JsonCachable jsoncachable = (JsonCachable)annotatedclass.getAnnotation(org/codehaus/jackson/map/annotate/JsonCachable);
        if (jsoncachable != null)
        {
            if (jsoncachable.value())
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        } else
        {
            return null;
        }
    }

    public Class findContentDeserializer(Annotated annotated)
    {
        return null;
    }

    public String findDeserializablePropertyName(AnnotatedField annotatedfield)
    {
        String s;
        if (isInvisible(annotatedfield))
        {
            s = null;
        } else
        {
            Field field = annotatedfield.getAnnotated();
            s = findJaxbPropertyName(field, field.getType(), "");
            if (s == null)
            {
                return field.getName();
            }
        }
        return s;
    }

    public Class findDeserializationContentType(Annotated annotated, JavaType javatype, String s)
    {
        return _doFindDeserializationType(annotated, javatype, s);
    }

    public Class findDeserializationKeyType(Annotated annotated, JavaType javatype, String s)
    {
        return null;
    }

    public Class findDeserializationType(Annotated annotated, JavaType javatype, String s)
    {
        if (!javatype.isContainerType())
        {
            return _doFindDeserializationType(annotated, javatype, s);
        } else
        {
            return null;
        }
    }

    public volatile Object findDeserializer(Annotated annotated, BeanProperty beanproperty)
    {
        return findDeserializer(annotated, beanproperty);
    }

    public JsonDeserializer findDeserializer(Annotated annotated, BeanProperty beanproperty)
    {
        XmlAdapter xmladapter = findAdapter(annotated, false);
        if (xmladapter != null)
        {
            return new XmlAdapterJsonDeserializer(xmladapter, beanproperty);
        }
        Class class1 = annotated.getRawType();
        if (class1 != null && _dataHandlerDeserializer != null && isDataHandler(class1))
        {
            return _dataHandlerDeserializer;
        } else
        {
            return null;
        }
    }

    public String findEnumValue(Enum enum)
    {
        Class class1 = enum.getDeclaringClass();
        String s = enum.name();
        XmlEnumValue xmlenumvalue;
        String s1;
        try
        {
            xmlenumvalue = (XmlEnumValue)class1.getDeclaredField(s).getAnnotation(javax/xml/bind/annotation/XmlEnumValue);
        }
        catch (NoSuchFieldException nosuchfieldexception)
        {
            throw new IllegalStateException((new StringBuilder()).append("Could not locate Enum entry '").append(s).append("' (Enum class ").append(class1.getName()).append(")").toString(), nosuchfieldexception);
        }
        if (xmlenumvalue == null)
        {
            break MISSING_BLOCK_LABEL_43;
        }
        s1 = xmlenumvalue.value();
        s = s1;
        return s;
    }

    protected Annotation findFieldAnnotation(Class class1, Class class2, String s)
    {
_L3:
        Field afield[] = class2.getDeclaredFields();
        int i = afield.length;
        for (int j = 0; j < i; j++)
        {
            Field field = afield[j];
            if (s.equals(field.getName()))
            {
                return field.getAnnotation(class1);
            }
        }

        if (!class2.isInterface() && class2 != java/lang/Object) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        class2 = class2.getSuperclass();
        if (class2 != null) goto _L3; else goto _L1
    }

    public String findGettablePropertyName(AnnotatedMethod annotatedmethod)
    {
        PropertyDescriptor propertydescriptor = findPropertyDescriptor(annotatedmethod);
        if (propertydescriptor != null)
        {
            return findJaxbSpecifiedPropertyName(propertydescriptor);
        } else
        {
            return null;
        }
    }

    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedclass)
    {
        return null;
    }

    protected String findJaxbSpecifiedPropertyName(PropertyDescriptor propertydescriptor)
    {
        return findJaxbPropertyName(new AnnotatedProperty(propertydescriptor, null), propertydescriptor.getPropertyType(), propertydescriptor.getName());
    }

    public Class findKeyDeserializer(Annotated annotated)
    {
        return null;
    }

    public String[] findPropertiesToIgnore(AnnotatedClass annotatedclass)
    {
        return null;
    }

    public TypeResolverBuilder findPropertyContentTypeResolver(MapperConfig mapperconfig, AnnotatedMember annotatedmember, JavaType javatype)
    {
        if (!javatype.isContainerType())
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Must call method with a container type (got ").append(javatype).append(")").toString());
        } else
        {
            return _typeResolverFromXmlElements(annotatedmember);
        }
    }

    protected PropertyDescriptor findPropertyDescriptor(AnnotatedMethod annotatedmethod)
    {
        return getDescriptors(annotatedmethod.getDeclaringClass()).findByMethodName(annotatedmethod.getName());
    }

    public String findPropertyNameForParam(AnnotatedParameter annotatedparameter)
    {
        return null;
    }

    public TypeResolverBuilder findPropertyTypeResolver(MapperConfig mapperconfig, AnnotatedMember annotatedmember, JavaType javatype)
    {
        if (javatype.isContainerType())
        {
            return null;
        } else
        {
            return _typeResolverFromXmlElements(annotatedmember);
        }
    }

    public String findRootName(AnnotatedClass annotatedclass)
    {
        XmlRootElement xmlrootelement = findRootElementAnnotation(annotatedclass);
        if (xmlrootelement != null)
        {
            String s = xmlrootelement.name();
            if ("##default".equals(s))
            {
                s = "";
            }
            return s;
        } else
        {
            return null;
        }
    }

    public String findSerializablePropertyName(AnnotatedField annotatedfield)
    {
        String s;
        if (isInvisible(annotatedfield))
        {
            s = null;
        } else
        {
            Field field = annotatedfield.getAnnotated();
            s = findJaxbPropertyName(field, field.getType(), "");
            if (s == null)
            {
                return field.getName();
            }
        }
        return s;
    }

    public org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion findSerializationInclusion(Annotated annotated, org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion inclusion)
    {
        XmlElementWrapper xmlelementwrapper = (XmlElementWrapper)annotated.getAnnotation(javax/xml/bind/annotation/XmlElementWrapper);
        if (xmlelementwrapper != null)
        {
            if (xmlelementwrapper.nillable())
            {
                return org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.ALWAYS;
            } else
            {
                return org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL;
            }
        }
        XmlElement xmlelement = (XmlElement)annotated.getAnnotation(javax/xml/bind/annotation/XmlElement);
        if (xmlelement != null)
        {
            if (xmlelement.nillable())
            {
                return org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.ALWAYS;
            } else
            {
                return org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL;
            }
        } else
        {
            return inclusion;
        }
    }

    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedclass)
    {
        XmlType xmltype = (XmlType)findAnnotation(javax/xml/bind/annotation/XmlType, annotatedclass, true, true, true);
        String as[];
        if (xmltype == null)
        {
            as = null;
        } else
        {
            as = xmltype.propOrder();
            if (as == null || as.length == 0)
            {
                return null;
            }
            PropertyDescriptors propertydescriptors = getDescriptors(annotatedclass.getRawType());
            int i = 0;
            int j = as.length;
            while (i < j) 
            {
                String s = as[i];
                if (propertydescriptors.findByPropertyName(s) == null && s.length() != 0)
                {
                    StringBuilder stringbuilder = new StringBuilder();
                    stringbuilder.append("get");
                    stringbuilder.append(Character.toUpperCase(s.charAt(0)));
                    if (s.length() > 1)
                    {
                        stringbuilder.append(s.substring(1));
                    }
                    PropertyDescriptor propertydescriptor = propertydescriptors.findByMethodName(stringbuilder.toString());
                    if (propertydescriptor != null)
                    {
                        as[i] = propertydescriptor.getName();
                    }
                }
                i++;
            }
        }
        return as;
    }

    public Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedclass)
    {
        boolean flag = true;
        XmlAccessorOrder xmlaccessororder = (XmlAccessorOrder)findAnnotation(javax/xml/bind/annotation/XmlAccessorOrder, annotatedclass, flag, flag, flag);
        if (xmlaccessororder == null)
        {
            return null;
        }
        if (xmlaccessororder.value() != XmlAccessOrder.ALPHABETICAL)
        {
            flag = false;
        }
        return Boolean.valueOf(flag);
    }

    public Class findSerializationType(Annotated annotated)
    {
        XmlElement xmlelement = (XmlElement)findAnnotation(javax/xml/bind/annotation/XmlElement, annotated, false, false, false);
        if (xmlelement == null || xmlelement.type() == javax/xml/bind/annotation/XmlElement$DEFAULT)
        {
            return null;
        }
        if (isIndexedType(annotated.getRawType()))
        {
            return null;
        } else
        {
            return xmlelement.type();
        }
    }

    public org.codehaus.jackson.map.annotate.JsonSerialize.Typing findSerializationTyping(Annotated annotated)
    {
        return null;
    }

    public Class[] findSerializationViews(Annotated annotated)
    {
        return null;
    }

    public volatile Object findSerializer(Annotated annotated, BeanProperty beanproperty)
    {
        return findSerializer(annotated, beanproperty);
    }

    public JsonSerializer findSerializer(Annotated annotated, BeanProperty beanproperty)
    {
        XmlAdapter xmladapter = findAdapter(annotated, true);
        if (xmladapter != null)
        {
            return new XmlAdapterJsonSerializer(xmladapter, beanproperty);
        }
        Class class1 = annotated.getRawType();
        if (class1 != null && _dataHandlerSerializer != null && isDataHandler(class1))
        {
            return _dataHandlerSerializer;
        } else
        {
            return null;
        }
    }

    public String findSettablePropertyName(AnnotatedMethod annotatedmethod)
    {
        PropertyDescriptor propertydescriptor = findPropertyDescriptor(annotatedmethod);
        if (propertydescriptor != null)
        {
            return findJaxbSpecifiedPropertyName(propertydescriptor);
        } else
        {
            return null;
        }
    }

    public List findSubtypes(Annotated annotated)
    {
        XmlElements xmlelements = (XmlElements)findAnnotation(javax/xml/bind/annotation/XmlElements, annotated, false, false, false);
        ArrayList arraylist;
        if (xmlelements != null)
        {
            arraylist = new ArrayList();
            XmlElement axmlelement[] = xmlelements.value();
            int i = axmlelement.length;
            for (int j = 0; j < i; j++)
            {
                XmlElement xmlelement = axmlelement[j];
                String s = xmlelement.name();
                if ("##default".equals(s))
                {
                    s = null;
                }
                arraylist.add(new NamedType(xmlelement.type(), s));
            }

        } else
        {
            XmlElementRefs xmlelementrefs = (XmlElementRefs)findAnnotation(javax/xml/bind/annotation/XmlElementRefs, annotated, false, false, false);
            if (xmlelementrefs != null)
            {
                arraylist = new ArrayList();
                XmlElementRef axmlelementref[] = xmlelementrefs.value();
                int k = axmlelementref.length;
                for (int l = 0; l < k; l++)
                {
                    XmlElementRef xmlelementref = axmlelementref[l];
                    Class class1 = xmlelementref.type();
                    if (!javax/xml/bind/JAXBElement.isAssignableFrom(class1))
                    {
                        String s1 = xmlelementref.name();
                        if (s1 == null || "##default".equals(s1))
                        {
                            XmlRootElement xmlrootelement = (XmlRootElement)class1.getAnnotation(javax/xml/bind/annotation/XmlRootElement);
                            if (xmlrootelement != null)
                            {
                                s1 = xmlrootelement.name();
                            }
                        }
                        if (s1 == null || "##default".equals(s1))
                        {
                            s1 = Introspector.decapitalize(class1.getSimpleName());
                        }
                        arraylist.add(new NamedType(class1, s1));
                    }
                }

            } else
            {
                arraylist = null;
            }
        }
        return arraylist;
    }

    public String findTypeName(AnnotatedClass annotatedclass)
    {
        XmlType xmltype = (XmlType)findAnnotation(javax/xml/bind/annotation/XmlType, annotatedclass, false, false, false);
        if (xmltype != null)
        {
            String s = xmltype.name();
            if (!"##default".equals(s))
            {
                return s;
            }
        }
        return null;
    }

    public TypeResolverBuilder findTypeResolver(MapperConfig mapperconfig, AnnotatedClass annotatedclass, JavaType javatype)
    {
        return null;
    }

    protected PropertyDescriptors getDescriptors(Class class1)
    {
        SoftReference softreference = (SoftReference)_propertyDescriptors.get();
        PropertyDescriptors propertydescriptors;
        if (softreference == null)
        {
            propertydescriptors = null;
        } else
        {
            propertydescriptors = (PropertyDescriptors)softreference.get();
        }
        if (propertydescriptors == null || propertydescriptors.getBeanClass() != class1)
        {
            PropertyDescriptors propertydescriptors1;
            try
            {
                propertydescriptors1 = PropertyDescriptors.find(class1);
            }
            catch (IntrospectionException introspectionexception)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Problem introspecting bean properties: ").append(introspectionexception.getMessage()).toString(), introspectionexception);
            }
            propertydescriptors = propertydescriptors1;
            _propertyDescriptors.set(new SoftReference(propertydescriptors));
        }
        return propertydescriptors;
    }

    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedmethod)
    {
        return false;
    }

    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedmethod)
    {
        return false;
    }

    public boolean hasCreatorAnnotation(Annotated annotated)
    {
        return false;
    }

    public boolean isHandled(Annotation annotation)
    {
        Class class1 = annotation.annotationType();
        Package package1 = class1.getPackage();
        String s;
        if (package1 != null)
        {
            s = package1.getName();
        } else
        {
            s = class1.getName();
        }
        while (s.startsWith(_jaxbPackageName) || class1 == org/codehaus/jackson/map/annotate/JsonCachable) 
        {
            return true;
        }
        return false;
    }

    public boolean isIgnorableConstructor(AnnotatedConstructor annotatedconstructor)
    {
        return false;
    }

    public boolean isIgnorableField(AnnotatedField annotatedfield)
    {
        return annotatedfield.getAnnotation(javax/xml/bind/annotation/XmlTransient) != null;
    }

    public boolean isIgnorableMethod(AnnotatedMethod annotatedmethod)
    {
        return annotatedmethod.getAnnotation(javax/xml/bind/annotation/XmlTransient) != null;
    }

    public Boolean isIgnorableType(AnnotatedClass annotatedclass)
    {
        return null;
    }

    protected boolean isIndexedType(Class class1)
    {
        return class1.isArray() || java/util/Collection.isAssignableFrom(class1) || java/util/Map.isAssignableFrom(class1);
    }

    protected boolean isInvisible(AnnotatedField annotatedfield)
    {
label0:
        {
            boolean flag = true;
            Annotation aannotation[] = annotatedfield.getAnnotated().getDeclaredAnnotations();
            int i = aannotation.length;
            for (int j = 0; j < i; j++)
            {
                if (isHandled(aannotation[j]))
                {
                    flag = false;
                }
            }

            if (flag)
            {
                XmlAccessType xmlaccesstype = XmlAccessType.PUBLIC_MEMBER;
                XmlAccessorType xmlaccessortype = (XmlAccessorType)findAnnotation(javax/xml/bind/annotation/XmlAccessorType, annotatedfield, true, true, true);
                if (xmlaccessortype != null)
                {
                    xmlaccesstype = xmlaccessortype.value();
                }
                if (xmlaccesstype == XmlAccessType.FIELD || xmlaccesstype == XmlAccessType.PUBLIC_MEMBER && Modifier.isPublic(annotatedfield.getAnnotated().getModifiers()))
                {
                    break label0;
                }
                flag = true;
            }
            return flag;
        }
        return false;
    }

    public Version version()
    {
        return VersionUtil.versionFor(getClass());
    }

}
