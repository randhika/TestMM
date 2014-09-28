// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.util.ClassUtil;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            SettableBeanProperty

public class CreatorContainer
{

    final BasicBeanDescription _beanDesc;
    final boolean _canFixAccess;
    protected Constructor _defaultConstructor;
    AnnotatedConstructor _delegatingConstructor;
    AnnotatedMethod _delegatingFactory;
    AnnotatedConstructor _intConstructor;
    AnnotatedMethod _intFactory;
    AnnotatedConstructor _longConstructor;
    AnnotatedMethod _longFactory;
    AnnotatedConstructor _propertyBasedConstructor;
    SettableBeanProperty _propertyBasedConstructorProperties[];
    AnnotatedMethod _propertyBasedFactory;
    SettableBeanProperty _propertyBasedFactoryProperties[];
    AnnotatedConstructor _strConstructor;
    AnnotatedMethod _strFactory;

    public CreatorContainer(BasicBeanDescription basicbeandescription, boolean flag)
    {
        _propertyBasedFactoryProperties = null;
        _propertyBasedConstructorProperties = null;
        _beanDesc = basicbeandescription;
        _canFixAccess = flag;
    }

    public void addDelegatingConstructor(AnnotatedConstructor annotatedconstructor)
    {
        _delegatingConstructor = verifyNonDup(annotatedconstructor, _delegatingConstructor, "long");
    }

    public void addDelegatingFactory(AnnotatedMethod annotatedmethod)
    {
        _delegatingFactory = verifyNonDup(annotatedmethod, _delegatingFactory, "long");
    }

    public void addIntConstructor(AnnotatedConstructor annotatedconstructor)
    {
        _intConstructor = verifyNonDup(annotatedconstructor, _intConstructor, "int");
    }

    public void addIntFactory(AnnotatedMethod annotatedmethod)
    {
        _intFactory = verifyNonDup(annotatedmethod, _intFactory, "int");
    }

    public void addLongConstructor(AnnotatedConstructor annotatedconstructor)
    {
        _longConstructor = verifyNonDup(annotatedconstructor, _longConstructor, "long");
    }

    public void addLongFactory(AnnotatedMethod annotatedmethod)
    {
        _longFactory = verifyNonDup(annotatedmethod, _longFactory, "long");
    }

    public void addPropertyConstructor(AnnotatedConstructor annotatedconstructor, SettableBeanProperty asettablebeanproperty[])
    {
        _propertyBasedConstructor = verifyNonDup(annotatedconstructor, _propertyBasedConstructor, "property-based");
        if (asettablebeanproperty.length > 1)
        {
            HashMap hashmap = new HashMap();
            int i = 0;
            for (int j = asettablebeanproperty.length; i < j; i++)
            {
                String s = asettablebeanproperty[i].getName();
                Integer integer = (Integer)hashmap.put(s, Integer.valueOf(i));
                if (integer != null)
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Duplicate creator property \"").append(s).append("\" (index ").append(integer).append(" vs ").append(i).append(")").toString());
                }
            }

        }
        _propertyBasedConstructorProperties = asettablebeanproperty;
    }

    public void addPropertyFactory(AnnotatedMethod annotatedmethod, SettableBeanProperty asettablebeanproperty[])
    {
        _propertyBasedFactory = verifyNonDup(annotatedmethod, _propertyBasedFactory, "property-based");
        _propertyBasedFactoryProperties = asettablebeanproperty;
    }

    public void addStringConstructor(AnnotatedConstructor annotatedconstructor)
    {
        _strConstructor = verifyNonDup(annotatedconstructor, _strConstructor, "String");
    }

    public void addStringFactory(AnnotatedMethod annotatedmethod)
    {
        _strFactory = verifyNonDup(annotatedmethod, _strFactory, "String");
    }

    public Creator.Delegating delegatingCreator()
    {
        if (_delegatingConstructor == null && _delegatingFactory == null)
        {
            return null;
        } else
        {
            return new Creator.Delegating(_beanDesc, _delegatingConstructor, _delegatingFactory);
        }
    }

    public Constructor getDefaultConstructor()
    {
        return _defaultConstructor;
    }

    public Creator.NumberBased numberCreator()
    {
        if (_intConstructor == null && _intFactory == null && _longConstructor == null && _longFactory == null)
        {
            return null;
        } else
        {
            return new Creator.NumberBased(_beanDesc.getBeanClass(), _intConstructor, _intFactory, _longConstructor, _longFactory);
        }
    }

    public Creator.PropertyBased propertyBasedCreator()
    {
        if (_propertyBasedConstructor == null && _propertyBasedFactory == null)
        {
            return null;
        } else
        {
            return new Creator.PropertyBased(_propertyBasedConstructor, _propertyBasedConstructorProperties, _propertyBasedFactory, _propertyBasedFactoryProperties);
        }
    }

    public void setDefaultConstructor(Constructor constructor)
    {
        _defaultConstructor = constructor;
    }

    public Creator.StringBased stringCreator()
    {
        if (_strConstructor == null && _strFactory == null)
        {
            return null;
        } else
        {
            return new Creator.StringBased(_beanDesc.getBeanClass(), _strConstructor, _strFactory);
        }
    }

    protected AnnotatedConstructor verifyNonDup(AnnotatedConstructor annotatedconstructor, AnnotatedConstructor annotatedconstructor1, String s)
    {
        if (annotatedconstructor1 != null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Conflicting ").append(s).append(" constructors: already had ").append(annotatedconstructor1).append(", encountered ").append(annotatedconstructor).toString());
        }
        if (_canFixAccess)
        {
            ClassUtil.checkAndFixAccess(annotatedconstructor.getAnnotated());
        }
        return annotatedconstructor;
    }

    protected AnnotatedMethod verifyNonDup(AnnotatedMethod annotatedmethod, AnnotatedMethod annotatedmethod1, String s)
    {
        if (annotatedmethod1 != null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Conflicting ").append(s).append(" factory methods: already had ").append(annotatedmethod1).append(", encountered ").append(annotatedmethod).toString());
        }
        if (_canFixAccess)
        {
            ClassUtil.checkAndFixAccess(annotatedmethod.getAnnotated());
        }
        return annotatedmethod;
    }
}
