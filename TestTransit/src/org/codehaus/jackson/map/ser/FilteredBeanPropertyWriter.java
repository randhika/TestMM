// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ser;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

// Referenced classes of package org.codehaus.jackson.map.ser:
//            BeanPropertyWriter

public abstract class FilteredBeanPropertyWriter
{
    private static final class MultiView extends BeanPropertyWriter
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
            return new MultiView(_delegate.withSerializer(jsonserializer), _views);
        }

        protected MultiView(BeanPropertyWriter beanpropertywriter, Class aclass[])
        {
            super(beanpropertywriter);
            _delegate = beanpropertywriter;
            _views = aclass;
        }
    }

    private static final class SingleView extends BeanPropertyWriter
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
            return new SingleView(_delegate.withSerializer(jsonserializer), _view);
        }

        protected SingleView(BeanPropertyWriter beanpropertywriter, Class class1)
        {
            super(beanpropertywriter);
            _delegate = beanpropertywriter;
            _view = class1;
        }
    }


    public FilteredBeanPropertyWriter()
    {
    }

    public static BeanPropertyWriter constructViewBased(BeanPropertyWriter beanpropertywriter, Class aclass[])
    {
        if (aclass.length == 1)
        {
            return new SingleView(beanpropertywriter, aclass[0]);
        } else
        {
            return new MultiView(beanpropertywriter, aclass);
        }
    }
}
