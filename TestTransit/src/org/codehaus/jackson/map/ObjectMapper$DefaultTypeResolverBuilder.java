// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;

import java.util.Collection;
import org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map:
//            ObjectMapper, DeserializationConfig, BeanProperty, TypeDeserializer, 
//            SerializationConfig, TypeSerializer

public static class _appliesFor extends StdTypeResolverBuilder
{

    protected final _appliesFor _appliesFor;

    public TypeDeserializer buildTypeDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, Collection collection, BeanProperty beanproperty)
    {
        if (useForType(javatype))
        {
            return super.buildTypeDeserializer(deserializationconfig, javatype, collection, beanproperty);
        } else
        {
            return null;
        }
    }

    public TypeSerializer buildTypeSerializer(SerializationConfig serializationconfig, JavaType javatype, Collection collection, BeanProperty beanproperty)
    {
        if (useForType(javatype))
        {
            return super.buildTypeSerializer(serializationconfig, javatype, collection, beanproperty);
        } else
        {
            return null;
        }
    }

    public boolean useForType(JavaType javatype)
    {
        ackson.map.ObjectMapper.DefaultTyping[_appliesFor._appliesFor()];
        JVM INSTR tableswitch 1 3: default 36
    //                   1 47
    //                   2 59
    //                   3 83;
           goto _L1 _L2 _L3 _L4
_L1:
        if (javatype.getRawClass() != java/lang/Object) goto _L6; else goto _L5
_L5:
        return true;
_L2:
        if (javatype.isArrayType())
        {
            javatype = javatype.getContentType();
        }
_L3:
        if (javatype.getRawClass() == java/lang/Object) goto _L8; else goto _L7
_L7:
        boolean flag;
        boolean flag1;
        flag1 = javatype.isConcrete();
        flag = false;
        if (flag1) goto _L9; else goto _L8
_L8:
        flag = true;
_L9:
        return flag;
_L4:
        if (javatype.isArrayType())
        {
            javatype = javatype.getContentType();
        }
        if (javatype.isFinal())
        {
            return false;
        }
        if (true) goto _L5; else goto _L6
_L6:
        return false;
    }

    public ( )
    {
        _appliesFor = ;
    }
}
