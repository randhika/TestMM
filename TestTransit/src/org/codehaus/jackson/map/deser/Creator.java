// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            SettableBeanProperty, PropertyValueBuffer, PropertyValue

abstract class Creator
{
    static final class Delegating
    {

        protected final AnnotatedMember _creator;
        protected final Constructor _ctor;
        protected JsonDeserializer _deserializer;
        protected final Method _factoryMethod;
        protected final JavaType _valueType;

        public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            Object obj = _deserializer.deserialize(jsonparser, deserializationcontext);
            Object obj1;
            try
            {
                if (_ctor != null)
                {
                    return _ctor.newInstance(new Object[] {
                        obj
                    });
                }
                obj1 = _factoryMethod.invoke(null, new Object[] {
                    obj
                });
            }
            catch (Exception exception)
            {
                ClassUtil.unwrapAndThrowAsIAE(exception);
                return null;
            }
            return obj1;
        }

        public AnnotatedMember getCreator()
        {
            return _creator;
        }

        public JavaType getValueType()
        {
            return _valueType;
        }

        public void setDeserializer(JsonDeserializer jsondeserializer)
        {
            _deserializer = jsondeserializer;
        }

        public Delegating(BasicBeanDescription basicbeandescription, AnnotatedConstructor annotatedconstructor, AnnotatedMethod annotatedmethod)
        {
            TypeBindings typebindings = basicbeandescription.bindingsForBeanType();
            if (annotatedconstructor != null)
            {
                _creator = annotatedconstructor;
                _ctor = annotatedconstructor.getAnnotated();
                _factoryMethod = null;
                _valueType = typebindings.resolveType(annotatedconstructor.getParameterType(0));
                return;
            }
            if (annotatedmethod != null)
            {
                _creator = annotatedmethod;
                _ctor = null;
                _factoryMethod = annotatedmethod.getAnnotated();
                _valueType = typebindings.resolveType(annotatedmethod.getParameterType(0));
                return;
            } else
            {
                throw new IllegalArgumentException("Internal error: neither delegating constructor nor factory method passed");
            }
        }
    }

    static final class NumberBased
    {

        protected final Constructor _intCtor;
        protected final Method _intFactoryMethod;
        protected final Constructor _longCtor;
        protected final Method _longFactoryMethod;
        protected final Class _valueClass;

        public Object construct(int i)
        {
            Object obj;
            if (_intCtor != null)
            {
                Constructor constructor = _intCtor;
                Object aobj1[] = new Object[1];
                aobj1[0] = Integer.valueOf(i);
                return constructor.newInstance(aobj1);
            }
            if (_intFactoryMethod == null)
            {
                break MISSING_BLOCK_LABEL_85;
            }
            Method method = _intFactoryMethod;
            Class class1 = _valueClass;
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            obj = method.invoke(class1, aobj);
            return obj;
            Exception exception;
            exception;
            ClassUtil.unwrapAndThrowAsIAE(exception);
            return construct(i);
        }

        public Object construct(long l)
        {
            Object obj;
            if (_longCtor != null)
            {
                Constructor constructor = _longCtor;
                Object aobj1[] = new Object[1];
                aobj1[0] = Long.valueOf(l);
                return constructor.newInstance(aobj1);
            }
            if (_longFactoryMethod == null)
            {
                break MISSING_BLOCK_LABEL_87;
            }
            Method method = _longFactoryMethod;
            Class class1 = _valueClass;
            Object aobj[] = new Object[1];
            aobj[0] = Long.valueOf(l);
            obj = method.invoke(class1, aobj);
            return obj;
            Exception exception;
            exception;
            ClassUtil.unwrapAndThrowAsIAE(exception);
            return null;
        }

        public NumberBased(Class class1, AnnotatedConstructor annotatedconstructor, AnnotatedMethod annotatedmethod, AnnotatedConstructor annotatedconstructor1, AnnotatedMethod annotatedmethod1)
        {
            _valueClass = class1;
            Constructor constructor;
            Constructor constructor1;
            Method method;
            Method method1;
            if (annotatedconstructor == null)
            {
                constructor = null;
            } else
            {
                constructor = annotatedconstructor.getAnnotated();
            }
            _intCtor = constructor;
            if (annotatedconstructor1 == null)
            {
                constructor1 = null;
            } else
            {
                constructor1 = annotatedconstructor1.getAnnotated();
            }
            _longCtor = constructor1;
            if (annotatedmethod == null)
            {
                method = null;
            } else
            {
                method = annotatedmethod.getAnnotated();
            }
            _intFactoryMethod = method;
            method1 = null;
            if (annotatedmethod1 != null)
            {
                method1 = annotatedmethod1.getAnnotated();
            }
            _longFactoryMethod = method1;
        }
    }

    static final class PropertyBased
    {

        protected final Constructor _ctor;
        protected final Object _defaultValues[];
        protected final Method _factoryMethod;
        protected final HashMap _properties = new HashMap();

        public Object build(PropertyValueBuffer propertyvaluebuffer)
            throws Exception
        {
            Object obj = null;
            if (_ctor == null) goto _L2; else goto _L1
_L1:
            Object obj2 = _ctor.newInstance(propertyvaluebuffer.getParameters(_defaultValues));
            obj = obj2;
_L4:
            for (PropertyValue propertyvalue = propertyvaluebuffer.buffered(); propertyvalue != null; propertyvalue = propertyvalue.next)
            {
                propertyvalue.assign(obj);
            }

            break; /* Loop/switch isn't completed */
_L2:
            Object obj1 = _factoryMethod.invoke(null, propertyvaluebuffer.getParameters(_defaultValues));
            obj = obj1;
            if (true) goto _L4; else goto _L3
            Exception exception;
            exception;
            ClassUtil.throwRootCause(exception);
_L3:
            return obj;
        }

        public SettableBeanProperty findCreatorProperty(String s)
        {
            return (SettableBeanProperty)_properties.get(s);
        }

        public Collection properties()
        {
            return _properties.values();
        }

        public PropertyValueBuffer startBuilding(JsonParser jsonparser, DeserializationContext deserializationcontext)
        {
            return new PropertyValueBuffer(jsonparser, deserializationcontext, _properties.size());
        }

        public PropertyBased(AnnotatedConstructor annotatedconstructor, SettableBeanProperty asettablebeanproperty[], AnnotatedMethod annotatedmethod, SettableBeanProperty asettablebeanproperty1[])
        {
            SettableBeanProperty asettablebeanproperty2[];
            Object aobj[];
            int i;
            if (annotatedconstructor != null)
            {
                _ctor = annotatedconstructor.getAnnotated();
                _factoryMethod = null;
                asettablebeanproperty2 = asettablebeanproperty;
            } else
            if (annotatedmethod != null)
            {
                _ctor = null;
                _factoryMethod = annotatedmethod.getAnnotated();
                asettablebeanproperty2 = asettablebeanproperty1;
            } else
            {
                throw new IllegalArgumentException("Internal error: neither delegating constructor nor factory method passed");
            }
            aobj = null;
            i = 0;
            for (int j = asettablebeanproperty2.length; i < j; i++)
            {
                SettableBeanProperty settablebeanproperty = asettablebeanproperty2[i];
                _properties.put(settablebeanproperty.getName(), settablebeanproperty);
                if (settablebeanproperty.getType().isPrimitive())
                {
                    if (aobj == null)
                    {
                        aobj = new Object[j];
                    }
                    aobj[i] = ClassUtil.defaultValue(settablebeanproperty.getType().getRawClass());
                }
            }

            _defaultValues = aobj;
        }
    }

    static final class StringBased
    {

        protected final Constructor _ctor;
        protected final Method _factoryMethod;
        protected final Class _valueClass;

        public Object construct(String s)
        {
            Object obj;
            if (_ctor != null)
            {
                return _ctor.newInstance(new Object[] {
                    s
                });
            }
            if (_factoryMethod == null)
            {
                break MISSING_BLOCK_LABEL_57;
            }
            obj = _factoryMethod.invoke(_valueClass, new Object[] {
                s
            });
            return obj;
            Exception exception;
            exception;
            ClassUtil.unwrapAndThrowAsIAE(exception);
            return null;
        }

        public StringBased(Class class1, AnnotatedConstructor annotatedconstructor, AnnotatedMethod annotatedmethod)
        {
            _valueClass = class1;
            Constructor constructor;
            Method method;
            if (annotatedconstructor == null)
            {
                constructor = null;
            } else
            {
                constructor = annotatedconstructor.getAnnotated();
            }
            _ctor = constructor;
            method = null;
            if (annotatedmethod != null)
            {
                method = annotatedmethod.getAnnotated();
            }
            _factoryMethod = method;
        }
    }


    private Creator()
    {
    }
}
