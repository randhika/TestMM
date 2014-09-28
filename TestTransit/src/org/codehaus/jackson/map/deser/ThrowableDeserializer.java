// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.util.HashSet;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.deser.impl.BeanPropertyMap;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            BeanDeserializer, SettableBeanProperty, SettableAnyProperty

public class ThrowableDeserializer extends BeanDeserializer
{

    protected static final String PROP_NAME_MESSAGE = "message";

    public ThrowableDeserializer(BeanDeserializer beandeserializer)
    {
        super(beandeserializer);
    }

    public Object deserializeFromObject(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (_propertyBasedCreator == null) goto _L2; else goto _L1
_L1:
        Object obj = _deserializeUsingPropertyBased(jsonparser, deserializationcontext);
_L8:
        return obj;
_L2:
        Object aobj[];
        int i;
        if (_delegatingCreator != null)
        {
            return _delegatingCreator.deserialize(jsonparser, deserializationcontext);
        }
        if (_beanType.isAbstract())
        {
            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Can not instantiate abstract type ").append(_beanType).append(" (need to add/enable type information?)").toString());
        }
        if (_stringCreator == null)
        {
            throw new JsonMappingException((new StringBuilder()).append("Can not deserialize Throwable of type ").append(_beanType).append(" without having either single-String-arg constructor; or explicit @JsonCreator").toString());
        }
        obj = null;
        aobj = null;
        i = 0;
_L4:
        String s;
        if (jsonparser.getCurrentToken() == JsonToken.END_OBJECT)
        {
            continue; /* Loop/switch isn't completed */
        }
        s = jsonparser.getCurrentName();
        SettableBeanProperty settablebeanproperty = _beanProperties.find(s);
        jsonparser.nextToken();
        if (settablebeanproperty == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (obj != null)
        {
            settablebeanproperty.deserializeAndSet(jsonparser, deserializationcontext, obj);
        } else
        {
            if (aobj == null)
            {
                int k1 = _beanProperties.size();
                aobj = new Object[k1 + k1];
            }
            int j1 = i + 1;
            aobj[i] = settablebeanproperty;
            i = j1 + 1;
            aobj[j1] = settablebeanproperty.deserialize(jsonparser, deserializationcontext);
        }
_L5:
        jsonparser.nextToken();
        if (true) goto _L4; else goto _L3
_L3:
        if ("message".equals(s))
        {
            obj = _stringCreator.construct(jsonparser.getText());
            if (aobj != null)
            {
                int l = 0;
                for (int i1 = i; l < i1; l += 2)
                {
                    ((SettableBeanProperty)aobj[l]).set(obj, aobj[l + 1]);
                }

                aobj = null;
            }
        } else
        if (_ignorableProps != null && _ignorableProps.contains(s))
        {
            jsonparser.skipChildren();
        } else
        if (_anySetter != null)
        {
            _anySetter.deserializeAndSet(jsonparser, deserializationcontext, obj, s);
        } else
        {
            handleUnknownProperty(jsonparser, deserializationcontext, obj, s);
        }
          goto _L5
        if (true) goto _L4; else goto _L6
_L6:
        if (obj != null) goto _L8; else goto _L7
_L7:
        obj = _stringCreator.construct(null);
        if (aobj != null)
        {
            int j = 0;
            int k = i;
            while (j < k) 
            {
                ((SettableBeanProperty)aobj[j]).set(obj, aobj[j + 1]);
                j += 2;
            }
        }
        if (true) goto _L8; else goto _L9
_L9:
    }
}
