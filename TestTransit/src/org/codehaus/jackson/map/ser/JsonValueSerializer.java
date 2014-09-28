// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            SerializerBase

public final class JsonValueSerializer extends SerializerBase
    implements ResolvableSerializer, SchemaAware
{

    protected final Method _accessorMethod;
    protected boolean _forceTypeInformation;
    protected final BeanProperty _property;
    protected JsonSerializer _valueSerializer;

    public JsonValueSerializer(Method method, JsonSerializer jsonserializer, BeanProperty beanproperty)
    {
        super(java/lang/Object);
        _accessorMethod = method;
        _valueSerializer = jsonserializer;
        _property = beanproperty;
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        throws JsonMappingException
    {
        if (_valueSerializer instanceof SchemaAware)
        {
            return ((SchemaAware)_valueSerializer).getSchema(serializerprovider, null);
        } else
        {
            return JsonSchema.getDefaultSchemaNode();
        }
    }

    protected boolean isNaturalTypeWithStdHandling(JavaType javatype, JsonSerializer jsonserializer)
    {
        for (Class class1 = javatype.getRawClass(); (javatype.isPrimitive() ? class1 != Integer.TYPE && class1 != Boolean.TYPE && class1 != Double.TYPE : class1 != java/lang/String && class1 != java/lang/Integer && class1 != java/lang/Boolean && class1 != java/lang/Double) || jsonserializer.getClass().getAnnotation(org/codehaus/jackson/map/annotate/JacksonStdImpl) == null;)
        {
            return false;
        }

        return true;
    }

    public void resolve(SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        if (_valueSerializer == null && (serializerprovider.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.USE_STATIC_TYPING) || Modifier.isFinal(_accessorMethod.getReturnType().getModifiers())))
        {
            JavaType javatype = serializerprovider.constructType(_accessorMethod.getGenericReturnType());
            _valueSerializer = serializerprovider.findTypedValueSerializer(javatype, false, _property);
            _forceTypeInformation = isNaturalTypeWithStdHandling(javatype, _valueSerializer);
        }
    }

    public void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        Object obj2 = _accessorMethod.invoke(obj, new Object[0]);
        Object obj1;
        if (obj2 == null)
        {
            JsonSerializer jsonserializer;
            try
            {
                serializerprovider.defaultSerializeNull(jsongenerator);
                return;
            }
            catch (IOException ioexception)
            {
                throw ioexception;
            }
            catch (Exception exception)
            {
                obj1 = exception;
            }
            break MISSING_BLOCK_LABEL_73;
        }
        jsonserializer = _valueSerializer;
        if (jsonserializer != null)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        jsonserializer = serializerprovider.findTypedValueSerializer(obj2.getClass(), true, _property);
        jsonserializer.serialize(obj2, jsongenerator, serializerprovider);
        return;
        for (; (obj1 instanceof InvocationTargetException) && ((Throwable) (obj1)).getCause() != null; obj1 = ((Throwable) (obj1)).getCause()) { }
        if (obj1 instanceof Error)
        {
            throw (Error)obj1;
        } else
        {
            throw JsonMappingException.wrapWithPath(((Throwable) (obj1)), obj, (new StringBuilder()).append(_accessorMethod.getName()).append("()").toString());
        }
    }

    public void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        Object obj2 = _accessorMethod.invoke(obj, new Object[0]);
        Object obj1;
        if (obj2 == null)
        {
            JsonSerializer jsonserializer;
            try
            {
                serializerprovider.defaultSerializeNull(jsongenerator);
                return;
            }
            catch (IOException ioexception)
            {
                throw ioexception;
            }
            catch (Exception exception)
            {
                obj1 = exception;
            }
            break MISSING_BLOCK_LABEL_109;
        }
        jsonserializer = _valueSerializer;
        if (jsonserializer == null)
        {
            break MISSING_BLOCK_LABEL_81;
        }
        if (_forceTypeInformation)
        {
            typeserializer.writeTypePrefixForScalar(obj, jsongenerator);
        }
        jsonserializer.serializeWithType(obj2, jsongenerator, serializerprovider, typeserializer);
        if (_forceTypeInformation)
        {
            typeserializer.writeTypeSuffixForScalar(obj, jsongenerator);
            return;
        }
        break MISSING_BLOCK_LABEL_181;
        serializerprovider.findTypedValueSerializer(obj2.getClass(), true, _property).serialize(obj2, jsongenerator, serializerprovider);
        return;
        for (; (obj1 instanceof InvocationTargetException) && ((Throwable) (obj1)).getCause() != null; obj1 = ((Throwable) (obj1)).getCause()) { }
        if (obj1 instanceof Error)
        {
            throw (Error)obj1;
        } else
        {
            throw JsonMappingException.wrapWithPath(((Throwable) (obj1)), obj, (new StringBuilder()).append(_accessorMethod.getName()).append("()").toString());
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("(@JsonValue serializer for method ").append(_accessorMethod.getDeclaringClass()).append("#").append(_accessorMethod.getName()).append(")").toString();
    }
}
