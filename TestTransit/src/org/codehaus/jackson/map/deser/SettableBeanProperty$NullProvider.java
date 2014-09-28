// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            SettableBeanProperty

protected static final class _rawType
{

    private final boolean _isPrimitive;
    private final Object _nullValue;
    private final Class _rawType;

    public Object nullValue(DeserializationContext deserializationcontext)
        throws JsonProcessingException
    {
        if (_isPrimitive && deserializationcontext.isEnabled(org.codehaus.jackson.map._ON_NULL_FOR_PRIMITIVES))
        {
            throw deserializationcontext.mappingException((new StringBuilder()).append("Can not map JSON null into type ").append(_rawType.getName()).append(" (set DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)").toString());
        } else
        {
            return _nullValue;
        }
    }

    protected (JavaType javatype, Object obj)
    {
        _nullValue = obj;
        _isPrimitive = javatype.isPrimitive();
        _rawType = javatype.getRawClass();
    }
}
