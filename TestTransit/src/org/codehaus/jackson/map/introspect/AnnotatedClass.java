// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.map.util.ClassUtil;

// Referenced classes of package org.codehaus.jackson.map.introspect:
//            Annotated, AnnotationMap, MemberKey, AnnotatedConstructor, 
//            AnnotatedMethod, AnnotatedField, AnnotatedMethodMap, MethodFilter

public final class AnnotatedClass extends Annotated
{

    private static final AnnotationMap NO_ANNOTATION_MAPS[] = new AnnotationMap[0];
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final Class _class;
    protected AnnotationMap _classAnnotations;
    protected List _constructors;
    protected List _creatorMethods;
    protected AnnotatedConstructor _defaultConstructor;
    protected List _fields;
    protected List _ignoredFields;
    protected List _ignoredMethods;
    protected AnnotatedMethodMap _memberMethods;
    protected final org.codehaus.jackson.map.ClassIntrospector.MixInResolver _mixInResolver;
    protected final Class _primaryMixIn;
    protected final Collection _superTypes;

    private AnnotatedClass(Class class1, List list, AnnotationIntrospector annotationintrospector, org.codehaus.jackson.map.ClassIntrospector.MixInResolver mixinresolver)
    {
        _class = class1;
        _superTypes = list;
        _annotationIntrospector = annotationintrospector;
        _mixInResolver = mixinresolver;
        Class class2;
        if (_mixInResolver == null)
        {
            class2 = null;
        } else
        {
            class2 = _mixInResolver.findMixInClassFor(_class);
        }
        _primaryMixIn = class2;
    }

    private AnnotationMap _emptyAnnotationMap()
    {
        return new AnnotationMap();
    }

    private AnnotationMap[] _emptyAnnotationMaps(int i)
    {
        AnnotationMap aannotationmap[];
        if (i == 0)
        {
            aannotationmap = NO_ANNOTATION_MAPS;
        } else
        {
            aannotationmap = new AnnotationMap[i];
            int j = 0;
            while (j < i) 
            {
                aannotationmap[j] = _emptyAnnotationMap();
                j++;
            }
        }
        return aannotationmap;
    }

    private boolean _isIncludableField(Field field)
    {
        int i;
        if (!field.isSynthetic())
        {
            if (!Modifier.isStatic(i = field.getModifiers()) && !Modifier.isTransient(i))
            {
                return true;
            }
        }
        return false;
    }

    public static AnnotatedClass construct(Class class1, AnnotationIntrospector annotationintrospector, org.codehaus.jackson.map.ClassIntrospector.MixInResolver mixinresolver)
    {
        AnnotatedClass annotatedclass = new AnnotatedClass(class1, ClassUtil.findSuperTypes(class1, null), annotationintrospector, mixinresolver);
        annotatedclass.resolveClassAnnotations();
        return annotatedclass;
    }

    public static AnnotatedClass constructWithoutSuperTypes(Class class1, AnnotationIntrospector annotationintrospector, org.codehaus.jackson.map.ClassIntrospector.MixInResolver mixinresolver)
    {
        AnnotatedClass annotatedclass = new AnnotatedClass(class1, Collections.emptyList(), annotationintrospector, mixinresolver);
        annotatedclass.resolveClassAnnotations();
        return annotatedclass;
    }

    protected void _addClassMixIns(AnnotationMap annotationmap, Class class1)
    {
        if (_mixInResolver != null)
        {
            _addClassMixIns(annotationmap, class1, _mixInResolver.findMixInClassFor(class1));
        }
    }

    protected void _addClassMixIns(AnnotationMap annotationmap, Class class1, Class class2)
    {
        if (class2 != null)
        {
            Annotation aannotation[] = class2.getDeclaredAnnotations();
            int i = aannotation.length;
            for (int j = 0; j < i; j++)
            {
                Annotation annotation1 = aannotation[j];
                if (_annotationIntrospector.isHandled(annotation1))
                {
                    annotationmap.addIfNotPresent(annotation1);
                }
            }

            Iterator iterator = ClassUtil.findSuperTypes(class2, class1).iterator();
            while (iterator.hasNext()) 
            {
                Annotation aannotation1[] = ((Class)iterator.next()).getDeclaredAnnotations();
                int k = aannotation1.length;
                int l = 0;
                while (l < k) 
                {
                    Annotation annotation = aannotation1[l];
                    if (_annotationIntrospector.isHandled(annotation))
                    {
                        annotationmap.addIfNotPresent(annotation);
                    }
                    l++;
                }
            }
        }
    }

    protected void _addConstructorMixIns(Class class1)
    {
        MemberKey amemberkey[];
        int i;
        int k;
        Constructor constructor;
        amemberkey = null;
        Constructor aconstructor[];
        int j;
        int i1;
        if (_constructors == null)
        {
            i = 0;
        } else
        {
            i = _constructors.size();
        }
        aconstructor = class1.getDeclaredConstructors();
        j = aconstructor.length;
        k = 0;
_L6:
        if (k >= j) goto _L2; else goto _L1
_L1:
        constructor = aconstructor[k];
        constructor.getParameterTypes().length;
        JVM INSTR tableswitch 0 0: default 64
    //                   0 129;
           goto _L3 _L4
_L3:
        MemberKey memberkey;
        int l;
        if (amemberkey == null)
        {
            amemberkey = new MemberKey[i];
            for (i1 = 0; i1 < i; i1++)
            {
                amemberkey[i1] = new MemberKey(((AnnotatedConstructor)_constructors.get(i1)).getAnnotated());
            }

        }
        memberkey = new MemberKey(constructor);
        l = 0;
          goto _L5
_L4:
        if (_defaultConstructor != null)
        {
            _addMixOvers(constructor, _defaultConstructor, false);
        }
_L8:
        k++;
          goto _L6
_L5:
        if (l >= i) goto _L8; else goto _L7
_L7:
        if (memberkey.equals(amemberkey[l])) goto _L10; else goto _L9
_L9:
        l++;
          goto _L5
_L10:
        _addMixOvers(constructor, (AnnotatedConstructor)_constructors.get(l), true);
          goto _L8
_L2:
    }

    protected void _addFactoryMixIns(Class class1)
    {
        MemberKey amemberkey[];
        int i;
        Method amethod[];
        int j;
        int k;
        Method method;
        amemberkey = null;
        i = _creatorMethods.size();
        amethod = class1.getDeclaredMethods();
        j = amethod.length;
        k = 0;
        break MISSING_BLOCK_LABEL_26;
_L3:
        k++;
        if (true) goto _L2; else goto _L1
_L2:
        int l;
        if (k >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        method = amethod[k];
        if (Modifier.isStatic(method.getModifiers()) && method.getParameterTypes().length != 0)
        {
            if (amemberkey == null)
            {
                amemberkey = new MemberKey[i];
                for (int i1 = 0; i1 < i; i1++)
                {
                    amemberkey[i1] = new MemberKey(((AnnotatedMethod)_creatorMethods.get(i1)).getAnnotated());
                }

            }
            MemberKey memberkey = new MemberKey(method);
            l = 0;
            while (l < i) 
            {
label0:
                {
                    if (memberkey.equals(amemberkey[l]))
                    {
                        break label0;
                    }
                    l++;
                }
            }
        }
          goto _L3
        _addMixOvers(method, (AnnotatedMethod)_creatorMethods.get(l), true);
          goto _L3
_L1:
    }

    protected void _addFieldMixIns(Class class1, Map map)
    {
        Field afield[] = class1.getDeclaredFields();
        int i = afield.length;
        int j = 0;
        while (j < i) 
        {
            Field field = afield[j];
            AnnotatedField annotatedfield;
            if (_isIncludableField(field))
            {
                if ((annotatedfield = (AnnotatedField)map.get(field.getName())) != null)
                {
                    Annotation aannotation[] = field.getDeclaredAnnotations();
                    int k = aannotation.length;
                    int l = 0;
                    while (l < k) 
                    {
                        Annotation annotation = aannotation[l];
                        if (_annotationIntrospector.isHandled(annotation))
                        {
                            annotatedfield.addOrOverride(annotation);
                        }
                        l++;
                    }
                }
            }
            j++;
        }
    }

    protected void _addFields(Map map, Class class1)
    {
        Class class2 = class1.getSuperclass();
        if (class2 != null)
        {
            _addFields(map, class2);
            Field afield[] = class1.getDeclaredFields();
            int i = afield.length;
            int j = 0;
            while (j < i) 
            {
                Field field = afield[j];
                if (_isIncludableField(field))
                {
                    map.put(field.getName(), _constructField(field));
                }
                j++;
            }
            if (_mixInResolver != null)
            {
                Class class3 = _mixInResolver.findMixInClassFor(class1);
                if (class3 != null)
                {
                    _addFieldMixIns(class3, map);
                }
            }
        }
    }

    protected void _addMemberMethods(Class class1, MethodFilter methodfilter, AnnotatedMethodMap annotatedmethodmap, Class class2, AnnotatedMethodMap annotatedmethodmap1)
    {
        if (class2 != null)
        {
            _addMethodMixIns(methodfilter, annotatedmethodmap, class2, annotatedmethodmap1);
        }
        if (class1 != null)
        {
            Method amethod[] = class1.getDeclaredMethods();
            int i = amethod.length;
            int j = 0;
            while (j < i) 
            {
                Method method = amethod[j];
                if (_isIncludableMethod(method, methodfilter))
                {
                    AnnotatedMethod annotatedmethod = annotatedmethodmap.find(method);
                    if (annotatedmethod == null)
                    {
                        AnnotatedMethod annotatedmethod1 = _constructMethod(method);
                        annotatedmethodmap.add(annotatedmethod1);
                        AnnotatedMethod annotatedmethod2 = annotatedmethodmap1.remove(method);
                        if (annotatedmethod2 != null)
                        {
                            _addMixOvers(annotatedmethod2.getAnnotated(), annotatedmethod1, false);
                        }
                    } else
                    {
                        _addMixUnders(method, annotatedmethod);
                        if (annotatedmethod.getDeclaringClass().isInterface() && !method.getDeclaringClass().isInterface())
                        {
                            annotatedmethodmap.add(annotatedmethod.withMethod(method));
                        }
                    }
                }
                j++;
            }
        }
    }

    protected void _addMethodMixIns(MethodFilter methodfilter, AnnotatedMethodMap annotatedmethodmap, Class class1, AnnotatedMethodMap annotatedmethodmap1)
    {
        Method amethod[] = class1.getDeclaredMethods();
        int i = amethod.length;
        int j = 0;
        while (j < i) 
        {
            Method method = amethod[j];
            if (_isIncludableMethod(method, methodfilter))
            {
                AnnotatedMethod annotatedmethod = annotatedmethodmap.find(method);
                if (annotatedmethod != null)
                {
                    _addMixUnders(method, annotatedmethod);
                } else
                {
                    annotatedmethodmap1.add(_constructMethod(method));
                }
            }
            j++;
        }
    }

    protected void _addMixOvers(Constructor constructor, AnnotatedConstructor annotatedconstructor, boolean flag)
    {
        Annotation aannotation[] = constructor.getDeclaredAnnotations();
        int i = aannotation.length;
        for (int j = 0; j < i; j++)
        {
            Annotation annotation = aannotation[j];
            if (_annotationIntrospector.isHandled(annotation))
            {
                annotatedconstructor.addOrOverride(annotation);
            }
        }

        if (flag)
        {
            Annotation aannotation1[][] = constructor.getParameterAnnotations();
            int k = 0;
            for (int l = aannotation1.length; k < l; k++)
            {
                Annotation aannotation2[] = aannotation1[k];
                int i1 = aannotation2.length;
                for (int j1 = 0; j1 < i1; j1++)
                {
                    annotatedconstructor.addOrOverrideParam(k, aannotation2[j1]);
                }

            }

        }
    }

    protected void _addMixOvers(Method method, AnnotatedMethod annotatedmethod, boolean flag)
    {
        Annotation aannotation[] = method.getDeclaredAnnotations();
        int i = aannotation.length;
        for (int j = 0; j < i; j++)
        {
            Annotation annotation = aannotation[j];
            if (_annotationIntrospector.isHandled(annotation))
            {
                annotatedmethod.addOrOverride(annotation);
            }
        }

        if (flag)
        {
            Annotation aannotation1[][] = method.getParameterAnnotations();
            int k = 0;
            for (int l = aannotation1.length; k < l; k++)
            {
                Annotation aannotation2[] = aannotation1[k];
                int i1 = aannotation2.length;
                for (int j1 = 0; j1 < i1; j1++)
                {
                    annotatedmethod.addOrOverrideParam(k, aannotation2[j1]);
                }

            }

        }
    }

    protected void _addMixUnders(Method method, AnnotatedMethod annotatedmethod)
    {
        Annotation aannotation[] = method.getDeclaredAnnotations();
        int i = aannotation.length;
        for (int j = 0; j < i; j++)
        {
            Annotation annotation = aannotation[j];
            if (_annotationIntrospector.isHandled(annotation))
            {
                annotatedmethod.addIfNotPresent(annotation);
            }
        }

    }

    protected AnnotationMap _collectRelevantAnnotations(Annotation aannotation[])
    {
        AnnotationMap annotationmap = new AnnotationMap();
        if (aannotation != null)
        {
            int i = aannotation.length;
            for (int j = 0; j < i; j++)
            {
                Annotation annotation = aannotation[j];
                if (_annotationIntrospector.isHandled(annotation))
                {
                    annotationmap.add(annotation);
                }
            }

        }
        return annotationmap;
    }

    protected AnnotationMap[] _collectRelevantAnnotations(Annotation aannotation[][])
    {
        int i = aannotation.length;
        AnnotationMap aannotationmap[] = new AnnotationMap[i];
        for (int j = 0; j < i; j++)
        {
            aannotationmap[j] = _collectRelevantAnnotations(aannotation[j]);
        }

        return aannotationmap;
    }

    protected AnnotatedConstructor _constructConstructor(Constructor constructor, boolean flag)
    {
        if (_annotationIntrospector == null)
        {
            return new AnnotatedConstructor(constructor, _emptyAnnotationMap(), _emptyAnnotationMaps(constructor.getParameterTypes().length));
        }
        AnnotationMap annotationmap = _collectRelevantAnnotations(constructor.getDeclaredAnnotations());
        AnnotationMap aannotationmap[];
        if (flag)
        {
            aannotationmap = null;
        } else
        {
            aannotationmap = _collectRelevantAnnotations(constructor.getParameterAnnotations());
        }
        return new AnnotatedConstructor(constructor, annotationmap, aannotationmap);
    }

    protected AnnotatedMethod _constructCreatorMethod(Method method)
    {
        if (_annotationIntrospector == null)
        {
            return new AnnotatedMethod(method, _emptyAnnotationMap(), _emptyAnnotationMaps(method.getParameterTypes().length));
        } else
        {
            return new AnnotatedMethod(method, _collectRelevantAnnotations(method.getDeclaredAnnotations()), _collectRelevantAnnotations(method.getParameterAnnotations()));
        }
    }

    protected AnnotatedField _constructField(Field field)
    {
        if (_annotationIntrospector == null)
        {
            return new AnnotatedField(field, _emptyAnnotationMap());
        } else
        {
            return new AnnotatedField(field, _collectRelevantAnnotations(field.getDeclaredAnnotations()));
        }
    }

    protected AnnotatedMethod _constructMethod(Method method)
    {
        if (_annotationIntrospector == null)
        {
            return new AnnotatedMethod(method, _emptyAnnotationMap(), null);
        } else
        {
            return new AnnotatedMethod(method, _collectRelevantAnnotations(method.getDeclaredAnnotations()), null);
        }
    }

    protected boolean _isIncludableMethod(Method method, MethodFilter methodfilter)
    {
        while (methodfilter != null && !methodfilter.includeMethod(method) || method.isSynthetic() || method.isBridge()) 
        {
            return false;
        }
        return true;
    }

    public Iterable fields()
    {
        if (_fields == null)
        {
            return Collections.emptyList();
        } else
        {
            return _fields;
        }
    }

    public AnnotatedMethod findMethod(String s, Class aclass[])
    {
        return _memberMethods.find(s, aclass);
    }

    public Class getAnnotated()
    {
        return _class;
    }

    public volatile AnnotatedElement getAnnotated()
    {
        return getAnnotated();
    }

    public Annotation getAnnotation(Class class1)
    {
        if (_classAnnotations == null)
        {
            return null;
        } else
        {
            return _classAnnotations.get(class1);
        }
    }

    public Annotations getAnnotations()
    {
        return _classAnnotations;
    }

    public List getConstructors()
    {
        if (_constructors == null)
        {
            return Collections.emptyList();
        } else
        {
            return _constructors;
        }
    }

    public AnnotatedConstructor getDefaultConstructor()
    {
        return _defaultConstructor;
    }

    public int getFieldCount()
    {
        if (_fields == null)
        {
            return 0;
        } else
        {
            return _fields.size();
        }
    }

    public Type getGenericType()
    {
        return _class;
    }

    public int getMemberMethodCount()
    {
        return _memberMethods.size();
    }

    public int getModifiers()
    {
        return _class.getModifiers();
    }

    public String getName()
    {
        return _class.getName();
    }

    public Class getRawType()
    {
        return _class;
    }

    public List getStaticMethods()
    {
        if (_creatorMethods == null)
        {
            return Collections.emptyList();
        } else
        {
            return _creatorMethods;
        }
    }

    public boolean hasAnnotations()
    {
        return _classAnnotations.size() > 0;
    }

    public Iterable ignoredFields()
    {
        if (_ignoredFields == null)
        {
            return Collections.emptyList();
        } else
        {
            return _ignoredFields;
        }
    }

    public Iterable ignoredMemberMethods()
    {
        if (_ignoredMethods == null)
        {
            return Collections.emptyList();
        } else
        {
            return _ignoredMethods;
        }
    }

    public Iterable memberMethods()
    {
        return _memberMethods;
    }

    protected void resolveClassAnnotations()
    {
        _classAnnotations = new AnnotationMap();
        if (_primaryMixIn != null)
        {
            _addClassMixIns(_classAnnotations, _class, _primaryMixIn);
        }
        Annotation aannotation[] = _class.getDeclaredAnnotations();
        int i = aannotation.length;
        for (int j = 0; j < i; j++)
        {
            Annotation annotation1 = aannotation[j];
            if (_annotationIntrospector.isHandled(annotation1))
            {
                _classAnnotations.addIfNotPresent(annotation1);
            }
        }

        for (Iterator iterator = _superTypes.iterator(); iterator.hasNext();)
        {
            Class class1 = (Class)iterator.next();
            _addClassMixIns(_classAnnotations, class1);
            Annotation aannotation1[] = class1.getDeclaredAnnotations();
            int k = aannotation1.length;
            int l = 0;
            while (l < k) 
            {
                Annotation annotation = aannotation1[l];
                if (_annotationIntrospector.isHandled(annotation))
                {
                    _classAnnotations.addIfNotPresent(annotation);
                }
                l++;
            }
        }

        _addClassMixIns(_classAnnotations, java/lang/Object);
    }

    public void resolveCreators(boolean flag)
    {
        Constructor aconstructor[];
        int i;
        int j;
        _constructors = null;
        aconstructor = _class.getDeclaredConstructors();
        i = aconstructor.length;
        j = 0;
_L2:
        Constructor constructor;
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_115;
        }
        constructor = aconstructor[j];
        switch (constructor.getParameterTypes().length)
        {
        default:
            if (flag)
            {
                if (_constructors == null)
                {
                    _constructors = new ArrayList();
                }
                _constructors.add(_constructConstructor(constructor, false));
            }
            break;

        case 0: // '\0'
            break; /* Loop/switch isn't completed */
        }
_L3:
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        _defaultConstructor = _constructConstructor(constructor, true);
          goto _L3
        if (true) goto _L2; else goto _L4
_L4:
label0:
        {
            if (_primaryMixIn != null && (_defaultConstructor != null || _constructors != null))
            {
                _addConstructorMixIns(_primaryMixIn);
            }
            if (_annotationIntrospector != null)
            {
                if (_defaultConstructor != null && _annotationIntrospector.isIgnorableConstructor(_defaultConstructor))
                {
                    _defaultConstructor = null;
                }
                if (_constructors != null)
                {
                    int j1 = _constructors.size();
                    do
                    {
                        if (--j1 < 0)
                        {
                            break;
                        }
                        if (_annotationIntrospector.isIgnorableConstructor((AnnotatedConstructor)_constructors.get(j1)))
                        {
                            _constructors.remove(j1);
                        }
                    } while (true);
                }
            }
            _creatorMethods = null;
            if (!flag)
            {
                break label0;
            }
            Method amethod[] = _class.getDeclaredMethods();
            int k = amethod.length;
            int l = 0;
            do
            {
                if (l >= k)
                {
                    break;
                }
                Method method = amethod[l];
                if (Modifier.isStatic(method.getModifiers()) && method.getParameterTypes().length >= 1)
                {
                    if (_creatorMethods == null)
                    {
                        _creatorMethods = new ArrayList();
                    }
                    _creatorMethods.add(_constructCreatorMethod(method));
                }
                l++;
            } while (true);
            if (_primaryMixIn != null && _creatorMethods != null)
            {
                _addFactoryMixIns(_primaryMixIn);
            }
            if (_annotationIntrospector != null && _creatorMethods != null)
            {
                int i1 = _creatorMethods.size();
                do
                {
                    if (--i1 < 0)
                    {
                        break;
                    }
                    if (_annotationIntrospector.isIgnorableMethod((AnnotatedMethod)_creatorMethods.get(i1)))
                    {
                        _creatorMethods.remove(i1);
                    }
                } while (true);
            }
        }
        return;
    }

    public void resolveFields(boolean flag)
    {
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        _addFields(linkedhashmap, _class);
        if (_annotationIntrospector != null)
        {
            Iterator iterator = linkedhashmap.entrySet().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                AnnotatedField annotatedfield = (AnnotatedField)((java.util.Map.Entry)iterator.next()).getValue();
                if (_annotationIntrospector.isIgnorableField(annotatedfield))
                {
                    iterator.remove();
                    if (flag)
                    {
                        _ignoredFields = ArrayBuilders.addToList(_ignoredFields, annotatedfield);
                    }
                }
            } while (true);
        }
        if (linkedhashmap.isEmpty())
        {
            _fields = Collections.emptyList();
            return;
        } else
        {
            _fields = new ArrayList(linkedhashmap.size());
            _fields.addAll(linkedhashmap.values());
            return;
        }
    }

    public void resolveMemberMethods(MethodFilter methodfilter, boolean flag)
    {
        AnnotatedMethodMap annotatedmethodmap;
        _memberMethods = new AnnotatedMethodMap();
        annotatedmethodmap = new AnnotatedMethodMap();
        _addMemberMethods(_class, methodfilter, _memberMethods, _primaryMixIn, annotatedmethodmap);
        Iterator iterator = _superTypes.iterator();
        while (iterator.hasNext()) 
        {
            Class class2 = (Class)iterator.next();
            Class class3;
            if (_mixInResolver == null)
            {
                class3 = null;
            } else
            {
                class3 = _mixInResolver.findMixInClassFor(class2);
            }
            _addMemberMethods(class2, methodfilter, _memberMethods, class3, annotatedmethodmap);
        }
        if (_mixInResolver != null)
        {
            Class class1 = _mixInResolver.findMixInClassFor(java/lang/Object);
            if (class1 != null)
            {
                _addMethodMixIns(methodfilter, _memberMethods, class1, annotatedmethodmap);
            }
        }
        if (_annotationIntrospector == null)
        {
            break MISSING_BLOCK_LABEL_323;
        }
        if (annotatedmethodmap.isEmpty()) goto _L2; else goto _L1
_L1:
        Iterator iterator2 = annotatedmethodmap.iterator();
_L3:
        AnnotatedMethod annotatedmethod1;
        if (!iterator2.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        annotatedmethod1 = (AnnotatedMethod)iterator2.next();
        Method method = java/lang/Object.getDeclaredMethod(annotatedmethod1.getName(), annotatedmethod1.getParameterClasses());
        if (method != null)
        {
            try
            {
                AnnotatedMethod annotatedmethod2 = _constructMethod(method);
                _addMixOvers(annotatedmethod1.getAnnotated(), annotatedmethod2, false);
                _memberMethods.add(annotatedmethod2);
            }
            catch (Exception exception) { }
        }
        if (true) goto _L3; else goto _L2
_L2:
        Iterator iterator1 = _memberMethods.iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            AnnotatedMethod annotatedmethod = (AnnotatedMethod)iterator1.next();
            if (_annotationIntrospector.isIgnorableMethod(annotatedmethod))
            {
                iterator1.remove();
                if (flag)
                {
                    _ignoredMethods = ArrayBuilders.addToList(_ignoredMethods, annotatedmethod);
                }
            }
        } while (true);
    }

    public String toString()
    {
        return (new StringBuilder()).append("[AnnotedClass ").append(_class.getName()).append("]").toString();
    }

}
