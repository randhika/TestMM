// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.io.SerializedString;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.util.EnumValues;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            ScalarSerializerBase

public class EnumSerializer extends ScalarSerializerBase
{

    protected final EnumValues _values;

    public EnumSerializer(EnumValues enumvalues)
    {
        super(java/lang/Enum, false);
        _values = enumvalues;
    }

    public static EnumSerializer construct(Class class1, SerializationConfig serializationconfig, BasicBeanDescription basicbeandescription)
    {
        org.codehaus.jackson.map.AnnotationIntrospector annotationintrospector = serializationconfig.getAnnotationIntrospector();
        EnumValues enumvalues;
        if (serializationconfig.isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING))
        {
            enumvalues = EnumValues.constructFromToString(class1, annotationintrospector);
        } else
        {
            enumvalues = EnumValues.constructFromName(class1, annotationintrospector);
        }
        return new EnumSerializer(enumvalues);
    }

    public EnumValues getEnumValues()
    {
        return _values;
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
    {
        ObjectNode objectnode = createSchemaNode("string", true);
        if (type != null && serializerprovider.constructType(type).isEnumType())
        {
            ArrayNode arraynode = objectnode.putArray("enum");
            for (Iterator iterator = _values.values().iterator(); iterator.hasNext(); arraynode.add(((SerializedString)iterator.next()).getValue())) { }
        }
        return objectnode;
    }

    public final void serialize(Enum enum, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeString(_values.serializedValueFor(enum));
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((Enum)obj, jsongenerator, serializerprovider);
    }
}
