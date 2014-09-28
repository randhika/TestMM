// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.io.NumberInput;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.KeyDeserializer;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            EnumResolver

public abstract class StdKeyDeserializer extends KeyDeserializer
{
    static final class BoolKD extends StdKeyDeserializer
    {

        public Boolean _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            if ("true".equals(s))
            {
                return Boolean.TRUE;
            }
            if ("false".equals(s))
            {
                return Boolean.FALSE;
            } else
            {
                throw deserializationcontext.weirdKeyException(_keyClass, s, "value not 'true' or 'false'");
            }
        }

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        BoolKD()
        {
            super(java/lang/Boolean);
        }
    }

    static final class ByteKD extends StdKeyDeserializer
    {

        public Byte _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            int i = _parseInt(s);
            if (i < -128 || i > 127)
            {
                throw deserializationcontext.weirdKeyException(_keyClass, s, "overflow, value can not be represented as 8-bit value");
            } else
            {
                return Byte.valueOf((byte)i);
            }
        }

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        ByteKD()
        {
            super(java/lang/Byte);
        }
    }

    static final class CharKD extends StdKeyDeserializer
    {

        public Character _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            if (s.length() == 1)
            {
                return Character.valueOf(s.charAt(0));
            } else
            {
                throw deserializationcontext.weirdKeyException(_keyClass, s, "can only convert 1-character Strings");
            }
        }

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        CharKD()
        {
            super(java/lang/Character);
        }
    }

    static final class DoubleKD extends StdKeyDeserializer
    {

        public Double _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            return Double.valueOf(_parseDouble(s));
        }

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        DoubleKD()
        {
            super(java/lang/Double);
        }
    }

    static final class EnumKD extends StdKeyDeserializer
    {

        final EnumResolver _resolver;

        public Enum _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            Enum enum = _resolver.findEnum(s);
            if (enum == null)
            {
                throw deserializationcontext.weirdKeyException(_keyClass, s, "not one of values for Enum class");
            } else
            {
                return enum;
            }
        }

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        EnumKD(EnumResolver enumresolver)
        {
            super(enumresolver.getEnumClass());
            _resolver = enumresolver;
        }
    }

    static final class FloatKD extends StdKeyDeserializer
    {

        public Float _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            return Float.valueOf((float)_parseDouble(s));
        }

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        FloatKD()
        {
            super(java/lang/Float);
        }
    }

    static final class IntKD extends StdKeyDeserializer
    {

        public Integer _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            return Integer.valueOf(_parseInt(s));
        }

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        IntKD()
        {
            super(java/lang/Integer);
        }
    }

    static final class LongKD extends StdKeyDeserializer
    {

        public Long _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            return Long.valueOf(_parseLong(s));
        }

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        LongKD()
        {
            super(java/lang/Long);
        }
    }

    static final class ShortKD extends StdKeyDeserializer
    {

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        public Short _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            int i = _parseInt(s);
            if (i < -32768 || i > 32767)
            {
                throw deserializationcontext.weirdKeyException(_keyClass, s, "overflow, value can not be represented as 16-bit value");
            } else
            {
                return Short.valueOf((short)i);
            }
        }

        ShortKD()
        {
            super(java/lang/Integer);
        }
    }

    static final class StringCtorKeyDeserializer extends StdKeyDeserializer
    {

        final Constructor _ctor;

        public Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _ctor.newInstance(new Object[] {
                s
            });
        }

        public StringCtorKeyDeserializer(Constructor constructor)
        {
            super(constructor.getDeclaringClass());
            _ctor = constructor;
        }
    }

    static final class StringFactoryKeyDeserializer extends StdKeyDeserializer
    {

        final Method _factoryMethod;

        public Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _factoryMethod.invoke(null, new Object[] {
                s
            });
        }

        public StringFactoryKeyDeserializer(Method method)
        {
            super(method.getDeclaringClass());
            _factoryMethod = method;
        }
    }


    protected final Class _keyClass;

    protected StdKeyDeserializer(Class class1)
    {
        _keyClass = class1;
    }

    protected abstract Object _parse(String s, DeserializationContext deserializationcontext)
        throws Exception;

    protected double _parseDouble(String s)
        throws IllegalArgumentException
    {
        return NumberInput.parseDouble(s);
    }

    protected int _parseInt(String s)
        throws IllegalArgumentException
    {
        return Integer.parseInt(s);
    }

    protected long _parseLong(String s)
        throws IllegalArgumentException
    {
        return Long.parseLong(s);
    }

    public final Object deserializeKey(String s, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        Object obj1;
        if (s == null)
        {
            obj1 = null;
        } else
        {
            Object obj;
            try
            {
                obj = _parse(s, deserializationcontext);
            }
            catch (Exception exception)
            {
                throw deserializationcontext.weirdKeyException(_keyClass, s, (new StringBuilder()).append("not a valid representation: ").append(exception.getMessage()).toString());
            }
            obj1 = obj;
            if (obj1 == null)
            {
                throw deserializationcontext.weirdKeyException(_keyClass, s, "not a valid representation");
            }
        }
        return obj1;
    }

    public Class getKeyClass()
    {
        return _keyClass;
    }
}
