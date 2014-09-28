// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.introspect;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanDescription;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.introspect:
//            AnnotatedClass, AnnotatedField, VisibilityChecker, AnnotatedMethod, 
//            AnnotatedWithParams, AnnotatedConstructor, Annotated

public class BasicBeanDescription extends BeanDescription
{

    protected final AnnotationIntrospector _annotationIntrospector;
    protected TypeBindings _bindings;
    protected final AnnotatedClass _classInfo;
    protected final MapperConfig _config;

    public BasicBeanDescription(MapperConfig mapperconfig, JavaType javatype, AnnotatedClass annotatedclass)
    {
        super(javatype);
        _config = mapperconfig;
        _annotationIntrospector = mapperconfig.getAnnotationIntrospector();
        _classInfo = annotatedclass;
    }

    public static String descFor(AnnotatedElement annotatedelement)
    {
        if (annotatedelement instanceof Class)
        {
            return (new StringBuilder()).append("class ").append(((Class)annotatedelement).getName()).toString();
        }
        if (annotatedelement instanceof Method)
        {
            Method method = (Method)annotatedelement;
            return (new StringBuilder()).append("method ").append(method.getName()).append(" (from class ").append(method.getDeclaringClass().getName()).append(")").toString();
        }
        if (annotatedelement instanceof Constructor)
        {
            Constructor constructor = (Constructor)annotatedelement;
            return (new StringBuilder()).append("constructor() (from class ").append(constructor.getDeclaringClass().getName()).append(")").toString();
        } else
        {
            return (new StringBuilder()).append("unknown type [").append(annotatedelement.getClass()).append("]").toString();
        }
    }

    public static String manglePropertyName(String s)
    {
        int i = s.length();
        if (i != 0) goto _L2; else goto _L1
_L1:
        s = null;
_L4:
        return s;
_L2:
        StringBuilder stringbuilder;
        int j;
        stringbuilder = null;
        j = 0;
_L5:
        char c1;
        if (j >= i)
        {
            continue; /* Loop/switch isn't completed */
        }
        char c = s.charAt(j);
        c1 = Character.toLowerCase(c);
        if (c != c1)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        if (stringbuilder == null) goto _L4; else goto _L3
_L3:
        return stringbuilder.toString();
        if (stringbuilder == null)
        {
            stringbuilder = new StringBuilder(s);
        }
        stringbuilder.setCharAt(j, c1);
        j++;
          goto _L5
    }

    public LinkedHashMap _findPropertyFields(VisibilityChecker visibilitychecker, Collection collection, boolean flag)
    {
        LinkedHashMap linkedhashmap;
        PropertyNamingStrategy propertynamingstrategy;
        Iterator iterator;
        linkedhashmap = new LinkedHashMap();
        propertynamingstrategy = _config.getPropertyNamingStrategy();
        iterator = _classInfo.fields().iterator();
_L2:
        AnnotatedField annotatedfield;
        String s;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_273;
        }
        annotatedfield = (AnnotatedField)iterator.next();
        AnnotatedField annotatedfield1;
        String s1;
        String s2;
        if (flag)
        {
            s = _annotationIntrospector.findSerializablePropertyName(annotatedfield);
        } else
        {
            s = _annotationIntrospector.findDeserializablePropertyName(annotatedfield);
        }
        if (s == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (s.length() == 0)
        {
            s = annotatedfield.getName();
            if (propertynamingstrategy != null)
            {
                s = propertynamingstrategy.nameForField(_config, annotatedfield, s);
            }
        }
_L5:
        if (collection != null && collection.contains(s)) goto _L2; else goto _L1
_L1:
        annotatedfield1 = (AnnotatedField)linkedhashmap.put(s, annotatedfield);
        if (annotatedfield1 == null || annotatedfield1.getDeclaringClass() != annotatedfield.getDeclaringClass()) goto _L2; else goto _L3
_L3:
        s1 = annotatedfield1.getFullName();
        s2 = annotatedfield.getFullName();
        throw new IllegalArgumentException((new StringBuilder()).append("Multiple fields representing property \"").append(s).append("\": ").append(s1).append(" vs ").append(s2).toString());
        if (!visibilitychecker.isFieldVisible(annotatedfield)) goto _L2; else goto _L4
_L4:
        s = annotatedfield.getName();
        if (propertynamingstrategy != null)
        {
            s = propertynamingstrategy.nameForField(_config, annotatedfield, s);
        }
          goto _L5
        return linkedhashmap;
    }

    public TypeBindings bindingsForBeanType()
    {
        if (_bindings == null)
        {
            _bindings = new TypeBindings(_config.getTypeFactory(), _type);
        }
        return _bindings;
    }

    public AnnotatedMethod findAnyGetter()
        throws IllegalArgumentException
    {
        AnnotatedMethod annotatedmethod = null;
        Iterator iterator = _classInfo.memberMethods().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            AnnotatedMethod annotatedmethod1 = (AnnotatedMethod)iterator.next();
            if (_annotationIntrospector.hasAnyGetterAnnotation(annotatedmethod1))
            {
                if (annotatedmethod != null)
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Multiple methods with 'any-getter' annotation (").append(annotatedmethod.getName()).append("(), ").append(annotatedmethod1.getName()).append(")").toString());
                }
                if (!java/util/Map.isAssignableFrom(annotatedmethod1.getRawType()))
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Invalid 'any-getter' annotation on method ").append(annotatedmethod1.getName()).append("(): return type is not instance of java.util.Map").toString());
                }
                annotatedmethod = annotatedmethod1;
            }
        } while (true);
        return annotatedmethod;
    }

    public AnnotatedMethod findAnySetter()
        throws IllegalArgumentException
    {
        AnnotatedMethod annotatedmethod = null;
        Iterator iterator = _classInfo.memberMethods().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            AnnotatedMethod annotatedmethod1 = (AnnotatedMethod)iterator.next();
            if (_annotationIntrospector.hasAnySetterAnnotation(annotatedmethod1))
            {
                if (annotatedmethod != null)
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Multiple methods with 'any-setter' annotation (").append(annotatedmethod.getName()).append("(), ").append(annotatedmethod1.getName()).append(")").toString());
                }
                int i = annotatedmethod1.getParameterCount();
                if (i != 2)
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Invalid 'any-setter' annotation on method ").append(annotatedmethod1.getName()).append("(): takes ").append(i).append(" parameters, should take 2").toString());
                }
                Class class1 = annotatedmethod1.getParameterClass(0);
                if (class1 != java/lang/String && class1 != java/lang/Object)
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Invalid 'any-setter' annotation on method ").append(annotatedmethod1.getName()).append("(): first argument not of type String or Object, but ").append(class1.getName()).toString());
                }
                annotatedmethod = annotatedmethod1;
            }
        } while (true);
        return annotatedmethod;
    }

    public Map findBackReferenceProperties()
    {
        HashMap hashmap = null;
        for (Iterator iterator = _classInfo.memberMethods().iterator(); iterator.hasNext();)
        {
            AnnotatedMethod annotatedmethod = (AnnotatedMethod)iterator.next();
            if (annotatedmethod.getParameterCount() == 1)
            {
                org.codehaus.jackson.map.AnnotationIntrospector.ReferenceProperty referenceproperty1 = _annotationIntrospector.findReferenceType(annotatedmethod);
                if (referenceproperty1 != null && referenceproperty1.isBackReference())
                {
                    if (hashmap == null)
                    {
                        hashmap = new HashMap();
                    }
                    if (hashmap.put(referenceproperty1.getName(), annotatedmethod) != null)
                    {
                        throw new IllegalArgumentException((new StringBuilder()).append("Multiple back-reference properties with name '").append(referenceproperty1.getName()).append("'").toString());
                    }
                }
            }
        }

        for (Iterator iterator1 = _classInfo.fields().iterator(); iterator1.hasNext();)
        {
            AnnotatedField annotatedfield = (AnnotatedField)iterator1.next();
            org.codehaus.jackson.map.AnnotationIntrospector.ReferenceProperty referenceproperty = _annotationIntrospector.findReferenceType(annotatedfield);
            if (referenceproperty != null && referenceproperty.isBackReference())
            {
                if (hashmap == null)
                {
                    hashmap = new HashMap();
                }
                if (hashmap.put(referenceproperty.getName(), annotatedfield) != null)
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Multiple back-reference properties with name '").append(referenceproperty.getName()).append("'").toString());
                }
            }
        }

        return hashmap;
    }

    public List findCreatorPropertyNames()
    {
        Object obj = null;
label0:
        for (int i = 0; i < 2; i++)
        {
            List list;
            Iterator iterator;
            if (i == 0)
            {
                list = getConstructors();
            } else
            {
                list = getFactoryMethods();
            }
            iterator = list.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    continue label0;
                }
                AnnotatedWithParams annotatedwithparams = (AnnotatedWithParams)iterator.next();
                int j = annotatedwithparams.getParameterCount();
                if (j >= 1)
                {
                    String s = _annotationIntrospector.findPropertyNameForParam(annotatedwithparams.getParameter(0));
                    if (s != null)
                    {
                        if (obj == null)
                        {
                            obj = new ArrayList();
                        }
                        ((List) (obj)).add(s);
                        int k = 1;
                        while (k < j) 
                        {
                            ((List) (obj)).add(_annotationIntrospector.findPropertyNameForParam(annotatedwithparams.getParameter(k)));
                            k++;
                        }
                    }
                }
            } while (true);
        }

        if (obj == null)
        {
            obj = Collections.emptyList();
        }
        return ((List) (obj));
    }

    public Constructor findDefaultConstructor()
    {
        AnnotatedConstructor annotatedconstructor = _classInfo.getDefaultConstructor();
        if (annotatedconstructor == null)
        {
            return null;
        } else
        {
            return annotatedconstructor.getAnnotated();
        }
    }

    public LinkedHashMap findDeserializableFields(VisibilityChecker visibilitychecker, Collection collection)
    {
        return _findPropertyFields(visibilitychecker, collection, false);
    }

    public transient Method findFactoryMethod(Class aclass[])
    {
        Iterator iterator = _classInfo.getStaticMethods().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            AnnotatedMethod annotatedmethod = (AnnotatedMethod)iterator.next();
            if (isFactoryMethod(annotatedmethod))
            {
                Class class1 = annotatedmethod.getParameterClass(0);
                int i = aclass.length;
                int j = 0;
                while (j < i) 
                {
                    if (class1.isAssignableFrom(aclass[j]))
                    {
                        return annotatedmethod.getAnnotated();
                    }
                    j++;
                }
            }
        } while (true);
        return null;
    }

    public LinkedHashMap findGetters(VisibilityChecker visibilitychecker, Collection collection)
    {
        LinkedHashMap linkedhashmap;
        PropertyNamingStrategy propertynamingstrategy;
        Iterator iterator;
        linkedhashmap = new LinkedHashMap();
        propertynamingstrategy = _config.getPropertyNamingStrategy();
        iterator = _classInfo.memberMethods().iterator();
_L2:
        AnnotatedMethod annotatedmethod;
        String s;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_329;
            }
            annotatedmethod = (AnnotatedMethod)iterator.next();
        } while (annotatedmethod.getParameterCount() != 0);
        s = _annotationIntrospector.findGettablePropertyName(annotatedmethod);
        if (s == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (s.length() == 0)
        {
            s = okNameForAnyGetter(annotatedmethod, annotatedmethod.getName());
            if (s == null)
            {
                s = annotatedmethod.getName();
            }
            if (propertynamingstrategy != null)
            {
                s = propertynamingstrategy.nameForGetterMethod(_config, annotatedmethod, s);
            }
        }
_L5:
        if (collection == null || !collection.contains(s))
        {
            AnnotatedMethod annotatedmethod1 = (AnnotatedMethod)linkedhashmap.put(s, annotatedmethod);
            if (annotatedmethod1 != null)
            {
                String s2 = annotatedmethod1.getFullName();
                String s3 = annotatedmethod.getFullName();
                throw new IllegalArgumentException((new StringBuilder()).append("Conflicting getter definitions for property \"").append(s).append("\": ").append(s2).append(" vs ").append(s3).toString());
            }
        }
        if (true) goto _L2; else goto _L1
_L1:
        String s1;
        s1 = annotatedmethod.getName();
        if (!s1.startsWith("get"))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!visibilitychecker.isGetterVisible(annotatedmethod)) goto _L2; else goto _L3
_L3:
        s = okNameForGetter(annotatedmethod, s1);
_L7:
        if (s == null || _annotationIntrospector.hasAnyGetterAnnotation(annotatedmethod)) goto _L2; else goto _L4
_L4:
        if (propertynamingstrategy != null)
        {
            s = propertynamingstrategy.nameForGetterMethod(_config, annotatedmethod, s);
        }
          goto _L5
        if (!visibilitychecker.isIsGetterVisible(annotatedmethod)) goto _L2; else goto _L6
_L6:
        s = okNameForIsGetter(annotatedmethod, s1);
          goto _L7
        return linkedhashmap;
          goto _L5
    }

    public AnnotatedMethod findJsonValueMethod()
    {
        AnnotatedMethod annotatedmethod = null;
        Iterator iterator = _classInfo.memberMethods().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            AnnotatedMethod annotatedmethod1 = (AnnotatedMethod)iterator.next();
            if (_annotationIntrospector.hasAsValueAnnotation(annotatedmethod1))
            {
                if (annotatedmethod != null)
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Multiple methods with active 'as-value' annotation (").append(annotatedmethod.getName()).append("(), ").append(annotatedmethod1.getName()).append(")").toString());
                }
                if (!ClassUtil.hasGetterSignature(annotatedmethod1.getAnnotated()))
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Method ").append(annotatedmethod1.getName()).append("() marked with an 'as-value' annotation, but does not have valid getter signature (non-static, takes no args, returns a value)").toString());
                }
                annotatedmethod = annotatedmethod1;
            }
        } while (true);
        return annotatedmethod;
    }

    public AnnotatedMethod findMethod(String s, Class aclass[])
    {
        return _classInfo.findMethod(s, aclass);
    }

    public LinkedHashMap findSerializableFields(VisibilityChecker visibilitychecker, Collection collection)
    {
        return _findPropertyFields(visibilitychecker, collection, true);
    }

    public org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion findSerializationInclusion(org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion inclusion)
    {
        return _annotationIntrospector.findSerializationInclusion(_classInfo, inclusion);
    }

    public LinkedHashMap findSetters(VisibilityChecker visibilitychecker)
    {
        LinkedHashMap linkedhashmap;
        PropertyNamingStrategy propertynamingstrategy;
        Iterator iterator;
        linkedhashmap = new LinkedHashMap();
        propertynamingstrategy = _config.getPropertyNamingStrategy();
        iterator = _classInfo.memberMethods().iterator();
_L6:
        AnnotatedMethod annotatedmethod;
        String s;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        annotatedmethod = (AnnotatedMethod)iterator.next();
        if (annotatedmethod.getParameterCount() != 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        s = _annotationIntrospector.findSettablePropertyName(annotatedmethod);
        if (s == null) goto _L2; else goto _L1
_L1:
        if (s.length() == 0)
        {
            s = okNameForSetter(annotatedmethod);
            if (s == null)
            {
                s = annotatedmethod.getName();
            }
            if (propertynamingstrategy != null)
            {
                s = propertynamingstrategy.nameForSetterMethod(_config, annotatedmethod, s);
            }
        }
_L4:
        AnnotatedMethod annotatedmethod1;
        annotatedmethod1 = (AnnotatedMethod)linkedhashmap.put(s, annotatedmethod);
        if (annotatedmethod1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (annotatedmethod1.getDeclaringClass() == annotatedmethod.getDeclaringClass())
        {
            String s1 = annotatedmethod1.getFullName();
            String s2 = annotatedmethod.getFullName();
            throw new IllegalArgumentException((new StringBuilder()).append("Conflicting setter definitions for property \"").append(s).append("\": ").append(s1).append(" vs ").append(s2).toString());
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (!visibilitychecker.isSetterVisible(annotatedmethod))
        {
            continue; /* Loop/switch isn't completed */
        }
        s = okNameForSetter(annotatedmethod);
        if (s == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (propertynamingstrategy != null)
        {
            s = propertynamingstrategy.nameForSetterMethod(_config, annotatedmethod, s);
        }
        if (true) goto _L4; else goto _L3
_L3:
        linkedhashmap.put(s, annotatedmethod1);
        if (true) goto _L6; else goto _L5
_L5:
        return linkedhashmap;
    }

    public transient Constructor findSingleArgConstructor(Class aclass[])
    {
        Iterator iterator = _classInfo.getConstructors().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            AnnotatedConstructor annotatedconstructor = (AnnotatedConstructor)iterator.next();
            if (annotatedconstructor.getParameterCount() == 1)
            {
                Class class1 = annotatedconstructor.getParameterClass(0);
                int i = aclass.length;
                int j = 0;
                while (j < i) 
                {
                    if (aclass[j] == class1)
                    {
                        return annotatedconstructor.getAnnotated();
                    }
                    j++;
                }
            }
        } while (true);
        return null;
    }

    public Annotations getClassAnnotations()
    {
        return _classInfo.getAnnotations();
    }

    public AnnotatedClass getClassInfo()
    {
        return _classInfo;
    }

    public List getConstructors()
    {
        return _classInfo.getConstructors();
    }

    public List getFactoryMethods()
    {
        List list = _classInfo.getStaticMethods();
        if (list.isEmpty())
        {
            return list;
        }
        ArrayList arraylist = new ArrayList();
        Iterator iterator = list.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            AnnotatedMethod annotatedmethod = (AnnotatedMethod)iterator.next();
            if (isFactoryMethod(annotatedmethod))
            {
                arraylist.add(annotatedmethod);
            }
        } while (true);
        return arraylist;
    }

    public boolean hasKnownClassAnnotations()
    {
        return _classInfo.hasAnnotations();
    }

    public Object instantiateBean(boolean flag)
    {
        AnnotatedConstructor annotatedconstructor = _classInfo.getDefaultConstructor();
        if (annotatedconstructor == null)
        {
            return null;
        }
        if (flag)
        {
            annotatedconstructor.fixAccess();
        }
        Object obj1;
        try
        {
            obj1 = annotatedconstructor.getAnnotated().newInstance(new Object[0]);
        }
        catch (Exception exception)
        {
            Object obj;
            for (obj = exception; ((Throwable) (obj)).getCause() != null; obj = ((Throwable) (obj)).getCause()) { }
            if (obj instanceof Error)
            {
                throw (Error)obj;
            }
            if (obj instanceof RuntimeException)
            {
                throw (RuntimeException)obj;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Failed to instantiate bean of type ").append(_classInfo.getAnnotated().getName()).append(": (").append(obj.getClass().getName()).append(") ").append(((Throwable) (obj)).getMessage()).toString(), ((Throwable) (obj)));
            }
        }
        return obj1;
    }

    protected boolean isCglibGetCallbacks(AnnotatedMethod annotatedmethod)
    {
        Class class1 = annotatedmethod.getRawType();
        Package package1;
        String s;
        if (class1 != null && class1.isArray())
        {
            if ((package1 = class1.getComponentType().getPackage()) != null && ((s = package1.getName()).startsWith("net.sf.cglib") || s.startsWith("org.hibernate.repackage.cglib")))
            {
                return true;
            }
        }
        return false;
    }

    protected boolean isFactoryMethod(AnnotatedMethod annotatedmethod)
    {
        Class class1 = annotatedmethod.getRawType();
        if (getBeanClass().isAssignableFrom(class1))
        {
            if (_annotationIntrospector.hasCreatorAnnotation(annotatedmethod))
            {
                return true;
            }
            if ("valueOf".equals(annotatedmethod.getName()))
            {
                return true;
            }
        }
        return false;
    }

    protected boolean isGroovyMetaClassGetter(AnnotatedMethod annotatedmethod)
    {
        Class class1 = annotatedmethod.getRawType();
        Package package1;
        if (class1 != null && !class1.isArray())
        {
            if ((package1 = class1.getPackage()) != null && package1.getName().startsWith("groovy.lang"))
            {
                return true;
            }
        }
        return false;
    }

    protected boolean isGroovyMetaClassSetter(AnnotatedMethod annotatedmethod)
    {
        Package package1 = annotatedmethod.getParameterClass(0).getPackage();
        boolean flag = false;
        if (package1 != null)
        {
            boolean flag1 = package1.getName().startsWith("groovy.lang");
            flag = false;
            if (flag1)
            {
                flag = true;
            }
        }
        return flag;
    }

    protected String mangleGetterName(Annotated annotated, String s)
    {
        return manglePropertyName(s);
    }

    protected String mangleSetterName(Annotated annotated, String s)
    {
        return manglePropertyName(s);
    }

    public String okNameForAnyGetter(AnnotatedMethod annotatedmethod, String s)
    {
        String s1 = okNameForIsGetter(annotatedmethod, s);
        if (s1 == null)
        {
            s1 = okNameForGetter(annotatedmethod, s);
        }
        return s1;
    }

    public String okNameForGetter(AnnotatedMethod annotatedmethod, String s)
    {
        if (!s.startsWith("get") || ("getCallbacks".equals(s) ? isCglibGetCallbacks(annotatedmethod) : "getMetaClass".equals(s) && isGroovyMetaClassGetter(annotatedmethod)))
        {
            return null;
        } else
        {
            return mangleGetterName(annotatedmethod, s.substring(3));
        }
    }

    public String okNameForIsGetter(AnnotatedMethod annotatedmethod, String s)
    {
label0:
        {
            if (s.startsWith("is"))
            {
                Class class1 = annotatedmethod.getRawType();
                if (class1 == java/lang/Boolean || class1 == Boolean.TYPE)
                {
                    break label0;
                }
            }
            return null;
        }
        return mangleGetterName(annotatedmethod, s.substring(2));
    }

    public String okNameForSetter(AnnotatedMethod annotatedmethod)
    {
        String s = annotatedmethod.getName();
        String s1;
        if (s.startsWith("set"))
        {
            s1 = mangleSetterName(annotatedmethod, s.substring(3));
            break MISSING_BLOCK_LABEL_26;
        }
        do
        {
            return null;
        } while (s1 == null || "metaClass".equals(s1) && isGroovyMetaClassSetter(annotatedmethod));
        return s1;
    }
}
