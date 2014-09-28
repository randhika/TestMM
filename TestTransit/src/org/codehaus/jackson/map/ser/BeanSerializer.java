// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            SerializerBase, BeanPropertyWriter, FilterProvider, ContainerSerializerBase, 
//            AnyGetterWriter, BeanPropertyFilter

public class BeanSerializer extends SerializerBase
    implements ResolvableSerializer, SchemaAware
{

    protected static final BeanPropertyWriter NO_PROPS[] = new BeanPropertyWriter[0];
    protected final AnyGetterWriter _anyGetterWriter;
    protected final BeanPropertyWriter _filteredProps[];
    protected final Object _propertyFilterId;
    protected final BeanPropertyWriter _props[];

    public BeanSerializer(Class class1, BeanPropertyWriter abeanpropertywriter[], BeanPropertyWriter abeanpropertywriter1[], AnyGetterWriter anygetterwriter, Object obj)
    {
        super(class1);
        _props = abeanpropertywriter;
        _filteredProps = abeanpropertywriter1;
        _anyGetterWriter = anygetterwriter;
        _propertyFilterId = obj;
    }

    protected BeanSerializer(BeanSerializer beanserializer)
    {
        this(beanserializer._handledType, beanserializer._props, beanserializer._filteredProps, beanserializer._anyGetterWriter, beanserializer._propertyFilterId);
    }

    public BeanSerializer(JavaType javatype, BeanPropertyWriter abeanpropertywriter[], BeanPropertyWriter abeanpropertywriter1[], AnyGetterWriter anygetterwriter, Object obj)
    {
        super(javatype);
        _props = abeanpropertywriter;
        _filteredProps = abeanpropertywriter1;
        _anyGetterWriter = anygetterwriter;
        _propertyFilterId = obj;
    }

    public static BeanSerializer createDummy(Class class1)
    {
        return new BeanSerializer(class1, NO_PROPS, null, null, null);
    }

    protected BeanPropertyFilter findFilter(SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        Object obj = _propertyFilterId;
        FilterProvider filterprovider = serializerprovider.getFilterProvider();
        if (filterprovider == null)
        {
            throw new JsonMappingException((new StringBuilder()).append("Can not resolve BeanPropertyFilter with id '").append(obj).append("'; no FilterProvider configured").toString());
        }
        BeanPropertyFilter beanpropertyfilter = filterprovider.findFilter(obj);
        if (beanpropertyfilter == null)
        {
            throw new JsonMappingException((new StringBuilder()).append("No filter configured with id '").append(obj).append("' (type ").append(obj.getClass().getName()).append(")").toString());
        } else
        {
            return beanpropertyfilter;
        }
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        throws JsonMappingException
    {
        ObjectNode objectnode = createSchemaNode("object", true);
        ObjectNode objectnode1 = objectnode.objectNode();
        int i = 0;
        while (i < _props.length) 
        {
            BeanPropertyWriter beanpropertywriter = _props[i];
            JavaType javatype = beanpropertywriter.getSerializationType();
            Object obj;
            org.codehaus.jackson.map.JsonSerializer jsonserializer;
            JsonNode jsonnode;
            if (javatype == null)
            {
                obj = beanpropertywriter.getGenericPropertyType();
            } else
            {
                obj = javatype.getRawClass();
            }
            jsonserializer = beanpropertywriter.getSerializer();
            if (jsonserializer == null)
            {
                Class class1 = beanpropertywriter.getRawSerializationType();
                if (class1 == null)
                {
                    class1 = beanpropertywriter.getPropertyType();
                }
                jsonserializer = serializerprovider.findValueSerializer(class1, beanpropertywriter);
            }
            if (jsonserializer instanceof SchemaAware)
            {
                jsonnode = ((SchemaAware)jsonserializer).getSchema(serializerprovider, ((Type) (obj)));
            } else
            {
                jsonnode = JsonSchema.getDefaultSchemaNode();
            }
            objectnode1.put(beanpropertywriter.getName(), jsonnode);
            i++;
        }
        objectnode.put("properties", objectnode1);
        return objectnode;
    }

    public void resolve(SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        int i;
        int j;
        BeanPropertyWriter beanpropertywriter;
        int k;
        if (_filteredProps == null)
        {
            i = 0;
        } else
        {
            i = _filteredProps.length;
        }
        j = 0;
        k = _props.length;
        if (j >= k)
        {
            break MISSING_BLOCK_LABEL_219;
        }
        beanpropertywriter = _props[j];
        if (!beanpropertywriter.hasSerializer())
        {
            break; /* Loop/switch isn't completed */
        }
_L4:
        j++;
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_18;
_L1:
        JavaType javatype;
label0:
        {
            javatype = beanpropertywriter.getSerializationType();
            if (javatype != null)
            {
                break label0;
            }
            javatype = serializerprovider.constructType(beanpropertywriter.getGenericPropertyType());
            if (javatype.isFinal())
            {
                break label0;
            }
            if (javatype.isContainerType() || javatype.containedTypeCount() > 0)
            {
                beanpropertywriter.setNonTrivialBaseType(javatype);
            }
        }
        continue; /* Loop/switch isn't completed */
        Object obj = serializerprovider.findValueSerializer(javatype, beanpropertywriter);
        if (javatype.isContainerType())
        {
            TypeSerializer typeserializer = (TypeSerializer)javatype.getContentType().getTypeHandler();
            if (typeserializer != null && (obj instanceof ContainerSerializerBase))
            {
                obj = ((ContainerSerializerBase)obj).withValueTypeSerializer(typeserializer);
            }
        }
        BeanPropertyWriter beanpropertywriter1 = beanpropertywriter.withSerializer(((org.codehaus.jackson.map.JsonSerializer) (obj)));
        _props[j] = beanpropertywriter1;
        if (j < i)
        {
            BeanPropertyWriter beanpropertywriter2 = _filteredProps[j];
            if (beanpropertywriter2 != null)
            {
                _filteredProps[j] = beanpropertywriter2.withSerializer(((org.codehaus.jackson.map.JsonSerializer) (obj)));
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (_anyGetterWriter != null)
        {
            _anyGetterWriter.resolve(serializerprovider);
        }
        return;
    }

    public final void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeStartObject();
        if (_propertyFilterId != null)
        {
            serializeFieldsFiltered(obj, jsongenerator, serializerprovider);
        } else
        {
            serializeFields(obj, jsongenerator, serializerprovider);
        }
        jsongenerator.writeEndObject();
    }

    protected void serializeFields(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        BeanPropertyWriter abeanpropertywriter[];
        int i;
        int j;
        BeanPropertyWriter beanpropertywriter;
        if (_filteredProps != null && serializerprovider.getSerializationView() != null)
        {
            abeanpropertywriter = _filteredProps;
        } else
        {
            abeanpropertywriter = _props;
        }
        i = 0;
        try
        {
            j = abeanpropertywriter.length;
        }
        catch (Exception exception)
        {
            String s1;
            if (i == abeanpropertywriter.length)
            {
                s1 = "[anySetter]";
            } else
            {
                s1 = abeanpropertywriter[i].getName();
            }
            wrapAndThrow(serializerprovider, exception, obj, s1);
            return;
        }
        catch (StackOverflowError stackoverflowerror)
        {
            JsonMappingException jsonmappingexception = new JsonMappingException("Infinite recursion (StackOverflowError)");
            String s;
            if (i == abeanpropertywriter.length)
            {
                s = "[anySetter]";
            } else
            {
                s = abeanpropertywriter[i].getName();
            }
            jsonmappingexception.prependPath(new org.codehaus.jackson.map.JsonMappingException.Reference(obj, s));
            throw jsonmappingexception;
        }
        if (i >= j) goto _L2; else goto _L1
_L1:
        beanpropertywriter = abeanpropertywriter[i];
        if (beanpropertywriter == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        beanpropertywriter.serializeAsField(obj, jsongenerator, serializerprovider);
        i++;
        break MISSING_BLOCK_LABEL_28;
_L2:
        if (_anyGetterWriter != null)
        {
            _anyGetterWriter.getAndSerialize(obj, jsongenerator, serializerprovider);
        }
        return;
    }

    protected void serializeFieldsFiltered(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        BeanPropertyWriter abeanpropertywriter[];
        BeanPropertyFilter beanpropertyfilter;
        int i;
        int j;
        BeanPropertyWriter beanpropertywriter;
        if (_filteredProps != null && serializerprovider.getSerializationView() != null)
        {
            abeanpropertywriter = _filteredProps;
        } else
        {
            abeanpropertywriter = _props;
        }
        beanpropertyfilter = findFilter(serializerprovider);
        i = 0;
        try
        {
            j = abeanpropertywriter.length;
        }
        catch (Exception exception)
        {
            String s1;
            if (i == abeanpropertywriter.length)
            {
                s1 = "[anySetter]";
            } else
            {
                s1 = abeanpropertywriter[i].getName();
            }
            wrapAndThrow(serializerprovider, exception, obj, s1);
            return;
        }
        catch (StackOverflowError stackoverflowerror)
        {
            JsonMappingException jsonmappingexception = new JsonMappingException("Infinite recursion (StackOverflowError)");
            String s;
            if (i == abeanpropertywriter.length)
            {
                s = "[anySetter]";
            } else
            {
                s = abeanpropertywriter[i].getName();
            }
            jsonmappingexception.prependPath(new org.codehaus.jackson.map.JsonMappingException.Reference(obj, s));
            throw jsonmappingexception;
        }
        if (i >= j) goto _L2; else goto _L1
_L1:
        beanpropertywriter = abeanpropertywriter[i];
        if (beanpropertywriter == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        beanpropertyfilter.serializeAsField(obj, jsongenerator, serializerprovider, beanpropertywriter);
        i++;
        break MISSING_BLOCK_LABEL_35;
_L2:
        if (_anyGetterWriter != null)
        {
            _anyGetterWriter.getAndSerialize(obj, jsongenerator, serializerprovider);
        }
        return;
    }

    public void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        typeserializer.writeTypePrefixForObject(obj, jsongenerator);
        if (_propertyFilterId != null)
        {
            serializeFieldsFiltered(obj, jsongenerator, serializerprovider);
        } else
        {
            serializeFields(obj, jsongenerator, serializerprovider);
        }
        typeserializer.writeTypeSuffixForObject(obj, jsongenerator);
    }

    public String toString()
    {
        return (new StringBuilder()).append("BeanSerializer for ").append(handledType().getName()).toString();
    }

}
