// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            BeanPropertyWriter, FilteredBeanPropertyWriter

private static final class _view extends BeanPropertyWriter
{

    protected final BeanPropertyWriter _delegate;
    protected final Class _view;

    public void serializeAsField(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws Exception
    {
        Class class1 = serializerprovider.getSerializationView();
        if (class1 == null || _view.isAssignableFrom(class1))
        {
            _delegate.serializeAsField(obj, jsongenerator, serializerprovider);
        }
    }

    public BeanPropertyWriter withSerializer(JsonSerializer jsonserializer)
    {
        return new <init>(_delegate.withSerializer(jsonserializer), _view);
    }

    protected (BeanPropertyWriter beanpropertywriter, Class class1)
    {
        super(beanpropertywriter);
        _delegate = beanpropertywriter;
        _view = class1;
    }
}
