// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            BeanPropertyWriter, FilteredBeanPropertyWriter

private static final class _views extends BeanPropertyWriter
{

    protected final BeanPropertyWriter _delegate;
    protected final Class _views[];

    public void serializeAsField(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws Exception
    {
        Class class1 = serializerprovider.getSerializationView();
        if (class1 != null)
        {
            int i = 0;
            int j = _views.length;
            do
            {
                if (i >= j || _views[i].isAssignableFrom(class1))
                {
                    if (i == j)
                    {
                        return;
                    }
                    break;
                }
                i++;
            } while (true);
        }
        _delegate.serializeAsField(obj, jsongenerator, serializerprovider);
    }

    public BeanPropertyWriter withSerializer(JsonSerializer jsonserializer)
    {
        return new <init>(_delegate.withSerializer(jsonserializer), _views);
    }

    protected (BeanPropertyWriter beanpropertywriter, Class aclass[])
    {
        super(beanpropertywriter);
        _delegate = beanpropertywriter;
        _views = aclass;
    }
}
