// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.AnnotatedParameter;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.InternCache;

public abstract class SettableBeanProperty
    implements BeanProperty
{
    public static final class CreatorProperty extends SettableBeanProperty
    {

        protected final AnnotatedParameter _annotated;
        protected final int _index;

        public void deserializeAndSet(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
            throws IOException, JsonProcessingException
        {
            set(obj, deserialize(jsonparser, deserializationcontext));
        }

        public Annotation getAnnotation(Class class1)
        {
            return _annotated.getAnnotation(class1);
        }

        public int getCreatorIndex()
        {
            return _index;
        }

        public AnnotatedMember getMember()
        {
            return _annotated;
        }

        public void set(Object obj, Object obj1)
            throws IOException
        {
        }

        public CreatorProperty(String s, JavaType javatype, TypeDeserializer typedeserializer, Annotations annotations, AnnotatedParameter annotatedparameter, int i)
        {
            super(s, javatype, typedeserializer, annotations);
            _annotated = annotatedparameter;
            _index = i;
        }
    }

    public static final class FieldProperty extends SettableBeanProperty
    {

        protected final AnnotatedField _annotated;
        protected final Field _field;

        public void deserializeAndSet(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
            throws IOException, JsonProcessingException
        {
            set(obj, deserialize(jsonparser, deserializationcontext));
        }

        public Annotation getAnnotation(Class class1)
        {
            return _annotated.getAnnotation(class1);
        }

        public AnnotatedMember getMember()
        {
            return _annotated;
        }

        public final void set(Object obj, Object obj1)
            throws IOException
        {
            try
            {
                _field.set(obj, obj1);
                return;
            }
            catch (Exception exception)
            {
                _throwAsIOE(exception, obj1);
            }
        }

        public FieldProperty(String s, JavaType javatype, TypeDeserializer typedeserializer, Annotations annotations, AnnotatedField annotatedfield)
        {
            super(s, javatype, typedeserializer, annotations);
            _annotated = annotatedfield;
            _field = annotatedfield.getAnnotated();
        }
    }

    public static final class ManagedReferenceProperty extends SettableBeanProperty
    {

        protected final SettableBeanProperty _backProperty;
        protected final boolean _isContainer;
        protected final SettableBeanProperty _managedProperty;
        protected final String _referenceName;

        public void deserializeAndSet(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
            throws IOException, JsonProcessingException
        {
            set(obj, _managedProperty.deserialize(jsonparser, deserializationcontext));
        }

        public Annotation getAnnotation(Class class1)
        {
            return _managedProperty.getAnnotation(class1);
        }

        public AnnotatedMember getMember()
        {
            return _managedProperty.getMember();
        }

        public final void set(Object obj, Object obj1)
            throws IOException
        {
            _managedProperty.set(obj, obj1);
            if (obj1 != null)
            {
                if (_isContainer)
                {
                    if (obj1 instanceof Object[])
                    {
                        Object aobj[] = (Object[])(Object[])obj1;
                        int i = aobj.length;
                        for (int j = 0; j < i; j++)
                        {
                            Object obj4 = aobj[j];
                            if (obj4 != null)
                            {
                                _backProperty.set(obj4, obj);
                            }
                        }

                    } else
                    if (obj1 instanceof Collection)
                    {
                        Iterator iterator1 = ((Collection)obj1).iterator();
                        do
                        {
                            if (!iterator1.hasNext())
                            {
                                break;
                            }
                            Object obj3 = iterator1.next();
                            if (obj3 != null)
                            {
                                _backProperty.set(obj3, obj);
                            }
                        } while (true);
                    } else
                    if (obj1 instanceof Map)
                    {
                        Iterator iterator = ((Map)obj1).values().iterator();
                        do
                        {
                            if (!iterator.hasNext())
                            {
                                break;
                            }
                            Object obj2 = iterator.next();
                            if (obj2 != null)
                            {
                                _backProperty.set(obj2, obj);
                            }
                        } while (true);
                    } else
                    {
                        throw new IllegalStateException((new StringBuilder()).append("Unsupported container type (").append(obj1.getClass().getName()).append(") when resolving reference '").append(_referenceName).append("'").toString());
                    }
                } else
                {
                    _backProperty.set(obj1, obj);
                }
            }
        }

        public ManagedReferenceProperty(String s, SettableBeanProperty settablebeanproperty, SettableBeanProperty settablebeanproperty1, Annotations annotations, boolean flag)
        {
            super(settablebeanproperty.getName(), settablebeanproperty.getType(), settablebeanproperty._valueTypeDeserializer, annotations);
            _referenceName = s;
            _managedProperty = settablebeanproperty;
            _backProperty = settablebeanproperty1;
            _isContainer = flag;
        }
    }

    public static final class MethodProperty extends SettableBeanProperty
    {

        protected final AnnotatedMethod _annotated;
        protected final Method _setter;

        public void deserializeAndSet(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
            throws IOException, JsonProcessingException
        {
            set(obj, deserialize(jsonparser, deserializationcontext));
        }

        public Annotation getAnnotation(Class class1)
        {
            return _annotated.getAnnotation(class1);
        }

        public AnnotatedMember getMember()
        {
            return _annotated;
        }

        public final void set(Object obj, Object obj1)
            throws IOException
        {
            try
            {
                _setter.invoke(obj, new Object[] {
                    obj1
                });
                return;
            }
            catch (Exception exception)
            {
                _throwAsIOE(exception, obj1);
            }
        }

        public MethodProperty(String s, JavaType javatype, TypeDeserializer typedeserializer, Annotations annotations, AnnotatedMethod annotatedmethod)
        {
            super(s, javatype, typedeserializer, annotations);
            _annotated = annotatedmethod;
            _setter = annotatedmethod.getAnnotated();
        }
    }

    protected static final class NullProvider
    {

        private final boolean _isPrimitive;
        private final Object _nullValue;
        private final Class _rawType;

        public Object nullValue(DeserializationContext deserializationcontext)
            throws JsonProcessingException
        {
            if (_isPrimitive && deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES))
            {
                throw deserializationcontext.mappingException((new StringBuilder()).append("Can not map JSON null into type ").append(_rawType.getName()).append(" (set DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)").toString());
            } else
            {
                return _nullValue;
            }
        }

        protected NullProvider(JavaType javatype, Object obj)
        {
            _nullValue = obj;
            _isPrimitive = javatype.isPrimitive();
            _rawType = javatype.getRawClass();
        }
    }

    public static final class SetterlessProperty extends SettableBeanProperty
    {

        protected final AnnotatedMethod _annotated;
        protected final Method _getter;

        public final void deserializeAndSet(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
            throws IOException, JsonProcessingException
        {
            if (jsonparser.getCurrentToken() == JsonToken.VALUE_NULL)
            {
                return;
            }
            Object obj1;
            try
            {
                obj1 = _getter.invoke(obj, new Object[0]);
            }
            catch (Exception exception)
            {
                _throwAsIOE(exception);
                return;
            }
            if (obj1 == null)
            {
                throw new JsonMappingException((new StringBuilder()).append("Problem deserializing 'setterless' property '").append(getName()).append("': get method returned null").toString());
            } else
            {
                _valueDeserializer.deserialize(jsonparser, deserializationcontext, obj1);
                return;
            }
        }

        public Annotation getAnnotation(Class class1)
        {
            return _annotated.getAnnotation(class1);
        }

        public AnnotatedMember getMember()
        {
            return _annotated;
        }

        public final void set(Object obj, Object obj1)
            throws IOException
        {
            throw new UnsupportedOperationException("Should never call 'set' on setterless property");
        }

        public SetterlessProperty(String s, JavaType javatype, TypeDeserializer typedeserializer, Annotations annotations, AnnotatedMethod annotatedmethod)
        {
            super(s, javatype, typedeserializer, annotations);
            _annotated = annotatedmethod;
            _getter = annotatedmethod.getAnnotated();
        }
    }


    protected final Annotations _contextAnnotations;
    protected String _managedReferenceName;
    protected NullProvider _nullProvider;
    protected final String _propName;
    protected int _propertyIndex;
    protected final JavaType _type;
    protected JsonDeserializer _valueDeserializer;
    protected TypeDeserializer _valueTypeDeserializer;

    protected SettableBeanProperty(String s, JavaType javatype, TypeDeserializer typedeserializer, Annotations annotations)
    {
        _propertyIndex = -1;
        if (s == null || s.length() == 0)
        {
            _propName = "";
        } else
        {
            _propName = InternCache.instance.intern(s);
        }
        _type = javatype;
        _contextAnnotations = annotations;
        _valueTypeDeserializer = typedeserializer;
    }

    protected SettableBeanProperty(SettableBeanProperty settablebeanproperty)
    {
        _propertyIndex = -1;
        _propName = settablebeanproperty._propName;
        _type = settablebeanproperty._type;
        _contextAnnotations = settablebeanproperty._contextAnnotations;
        _valueDeserializer = settablebeanproperty._valueDeserializer;
        _valueTypeDeserializer = settablebeanproperty._valueTypeDeserializer;
        _nullProvider = settablebeanproperty._nullProvider;
        _managedReferenceName = settablebeanproperty._managedReferenceName;
        _propertyIndex = settablebeanproperty._propertyIndex;
    }

    protected IOException _throwAsIOE(Exception exception)
        throws IOException
    {
        if (exception instanceof IOException)
        {
            throw (IOException)exception;
        }
        if (exception instanceof RuntimeException)
        {
            throw (RuntimeException)exception;
        }
        Object obj;
        for (obj = exception; ((Throwable) (obj)).getCause() != null; obj = ((Throwable) (obj)).getCause()) { }
        throw new JsonMappingException(((Throwable) (obj)).getMessage(), null, ((Throwable) (obj)));
    }

    protected void _throwAsIOE(Exception exception, Object obj)
        throws IOException
    {
        if (exception instanceof IllegalArgumentException)
        {
            String s;
            StringBuilder stringbuilder;
            String s1;
            if (obj == null)
            {
                s = "[NULL]";
            } else
            {
                s = obj.getClass().getName();
            }
            stringbuilder = (new StringBuilder("Problem deserializing property '")).append(getPropertyName());
            stringbuilder.append("' (expected type: ").append(getType());
            stringbuilder.append("; actual type: ").append(s).append(")");
            s1 = exception.getMessage();
            if (s1 != null)
            {
                stringbuilder.append(", problem: ").append(s1);
            } else
            {
                stringbuilder.append(" (no error message provided)");
            }
            throw new JsonMappingException(stringbuilder.toString(), null, exception);
        } else
        {
            _throwAsIOE(exception);
            return;
        }
    }

    public void assignIndex(int i)
    {
        if (_propertyIndex != -1)
        {
            throw new IllegalStateException((new StringBuilder()).append("Property '").append(getName()).append("' already had index (").append(_propertyIndex).append("), trying to assign ").append(i).toString());
        } else
        {
            _propertyIndex = i;
            return;
        }
    }

    public final Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (jsonparser.getCurrentToken() == JsonToken.VALUE_NULL)
        {
            if (_nullProvider == null)
            {
                return null;
            } else
            {
                return _nullProvider.nullValue(deserializationcontext);
            }
        }
        if (_valueTypeDeserializer != null)
        {
            return _valueDeserializer.deserializeWithType(jsonparser, deserializationcontext, _valueTypeDeserializer);
        } else
        {
            return _valueDeserializer.deserialize(jsonparser, deserializationcontext);
        }
    }

    public abstract void deserializeAndSet(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException;

    public abstract Annotation getAnnotation(Class class1);

    public Annotation getContextAnnotation(Class class1)
    {
        return _contextAnnotations.get(class1);
    }

    public int getCreatorIndex()
    {
        return -1;
    }

    protected final Class getDeclaringClass()
    {
        return getMember().getDeclaringClass();
    }

    public String getManagedReferenceName()
    {
        return _managedReferenceName;
    }

    public abstract AnnotatedMember getMember();

    public final String getName()
    {
        return _propName;
    }

    public String getPropertyName()
    {
        return _propName;
    }

    public int getProperytIndex()
    {
        return _propertyIndex;
    }

    public JavaType getType()
    {
        return _type;
    }

    public JsonDeserializer getValueDeserializer()
    {
        return _valueDeserializer;
    }

    public boolean hasValueDeserializer()
    {
        return _valueDeserializer != null;
    }

    public abstract void set(Object obj, Object obj1)
        throws IOException;

    public void setManagedReferenceName(String s)
    {
        _managedReferenceName = s;
    }

    public void setValueDeserializer(JsonDeserializer jsondeserializer)
    {
        if (_valueDeserializer != null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Already had assigned deserializer for property '").append(getName()).append("' (class ").append(getDeclaringClass().getName()).append(")").toString());
        }
        _valueDeserializer = jsondeserializer;
        Object obj = _valueDeserializer.getNullValue();
        NullProvider nullprovider;
        if (obj == null)
        {
            nullprovider = null;
        } else
        {
            nullprovider = new NullProvider(_type, obj);
        }
        _nullProvider = nullprovider;
    }

    public String toString()
    {
        return (new StringBuilder()).append("[property '").append(getName()).append("']").toString();
    }
}
