// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ResolvableDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.deser.impl.BeanPropertyMap;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.TokenBuffer;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdDeserializer, CreatorContainer, SettableBeanProperty, PropertyValueBuffer, 
//            SettableAnyProperty, ContainerDeserializer, AbstractDeserializer

public class BeanDeserializer extends StdDeserializer
    implements ResolvableDeserializer
{

    protected final SettableAnyProperty _anySetter;
    protected final Map _backRefs;
    protected final BeanPropertyMap _beanProperties;
    protected final JavaType _beanType;
    protected final Constructor _defaultConstructor;
    protected final Creator.Delegating _delegatingCreator;
    protected final AnnotatedClass _forClass;
    protected final HashSet _ignorableProps;
    protected final boolean _ignoreAllUnknown;
    protected final Creator.NumberBased _numberCreator;
    protected final BeanProperty _property;
    protected final Creator.PropertyBased _propertyBasedCreator;
    protected final Creator.StringBased _stringCreator;
    protected HashMap _subDeserializers;

    protected BeanDeserializer(BeanDeserializer beandeserializer)
    {
        super(beandeserializer._beanType);
        _forClass = beandeserializer._forClass;
        _beanType = beandeserializer._beanType;
        _property = beandeserializer._property;
        _beanProperties = beandeserializer._beanProperties;
        _backRefs = beandeserializer._backRefs;
        _ignorableProps = beandeserializer._ignorableProps;
        _ignoreAllUnknown = beandeserializer._ignoreAllUnknown;
        _anySetter = beandeserializer._anySetter;
        _defaultConstructor = beandeserializer._defaultConstructor;
        _stringCreator = beandeserializer._stringCreator;
        _numberCreator = beandeserializer._numberCreator;
        _delegatingCreator = beandeserializer._delegatingCreator;
        _propertyBasedCreator = beandeserializer._propertyBasedCreator;
    }

    public BeanDeserializer(AnnotatedClass annotatedclass, JavaType javatype, BeanProperty beanproperty, CreatorContainer creatorcontainer, BeanPropertyMap beanpropertymap, Map map, HashSet hashset, 
            boolean flag, SettableAnyProperty settableanyproperty)
    {
        super(javatype);
        _forClass = annotatedclass;
        _beanType = javatype;
        _property = beanproperty;
        _beanProperties = beanpropertymap;
        _backRefs = map;
        _ignorableProps = hashset;
        _ignoreAllUnknown = flag;
        _anySetter = settableanyproperty;
        _stringCreator = creatorcontainer.stringCreator();
        _numberCreator = creatorcontainer.numberCreator();
        _delegatingCreator = creatorcontainer.delegatingCreator();
        _propertyBasedCreator = creatorcontainer.propertyBasedCreator();
        if (_delegatingCreator != null || _propertyBasedCreator != null)
        {
            _defaultConstructor = null;
            return;
        } else
        {
            _defaultConstructor = creatorcontainer.getDefaultConstructor();
            return;
        }
    }

    protected final Object _deserializeUsingPropertyBased(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        Creator.PropertyBased propertybased;
        PropertyValueBuffer propertyvaluebuffer;
        TokenBuffer tokenbuffer;
        JsonToken jsontoken;
        propertybased = _propertyBasedCreator;
        propertyvaluebuffer = propertybased.startBuilding(jsonparser, deserializationcontext);
        tokenbuffer = null;
        jsontoken = jsonparser.getCurrentToken();
_L12:
        if (jsontoken != JsonToken.FIELD_NAME) goto _L2; else goto _L1
_L1:
        String s;
        SettableBeanProperty settablebeanproperty;
        s = jsonparser.getCurrentName();
        jsonparser.nextToken();
        settablebeanproperty = propertybased.findCreatorProperty(s);
        if (settablebeanproperty == null) goto _L4; else goto _L3
_L3:
        Object obj2 = settablebeanproperty.deserialize(jsonparser, deserializationcontext);
        if (!propertyvaluebuffer.assignParameter(settablebeanproperty.getCreatorIndex(), obj2)) goto _L6; else goto _L5
_L5:
        jsonparser.nextToken();
        Object obj3 = propertybased.build(propertyvaluebuffer);
        Object obj4 = obj3;
        if (obj4.getClass() == _beanType.getRawClass()) goto _L8; else goto _L7
_L7:
        Object obj1 = handlePolymorphic(jsonparser, deserializationcontext, obj4, tokenbuffer);
_L10:
        return obj1;
        Exception exception1;
        exception1;
        wrapAndThrow(exception1, _beanType.getRawClass(), s, deserializationcontext);
_L6:
        jsontoken = jsonparser.nextToken();
        continue; /* Loop/switch isn't completed */
_L8:
        if (tokenbuffer != null)
        {
            obj4 = handleUnknownProperties(deserializationcontext, obj4, tokenbuffer);
        }
        return deserialize(jsonparser, deserializationcontext, obj4);
_L4:
        SettableBeanProperty settablebeanproperty1 = _beanProperties.find(s);
        if (settablebeanproperty1 != null)
        {
            propertyvaluebuffer.bufferProperty(settablebeanproperty1, settablebeanproperty1.deserialize(jsonparser, deserializationcontext));
        } else
        if (_ignorableProps != null && _ignorableProps.contains(s))
        {
            jsonparser.skipChildren();
        } else
        if (_anySetter != null)
        {
            propertyvaluebuffer.bufferAnyProperty(_anySetter, s, _anySetter.deserialize(jsonparser, deserializationcontext));
        } else
        {
            if (tokenbuffer == null)
            {
                tokenbuffer = new TokenBuffer(jsonparser.getCodec());
            }
            tokenbuffer.writeFieldName(s);
            tokenbuffer.copyCurrentStructure(jsonparser);
        }
        if (true) goto _L6; else goto _L2
_L2:
        Object obj;
        try
        {
            obj = propertybased.build(propertyvaluebuffer);
        }
        catch (Exception exception)
        {
            wrapInstantiationProblem(exception, deserializationcontext);
            return null;
        }
        obj1 = obj;
        if (tokenbuffer == null) goto _L10; else goto _L9
_L9:
        if (obj1.getClass() != _beanType.getRawClass())
        {
            return handlePolymorphic(null, deserializationcontext, obj1, tokenbuffer);
        }
        return handleUnknownProperties(deserializationcontext, obj1, tokenbuffer);
        if (true) goto _L12; else goto _L11
_L11:
    }

    protected JsonDeserializer _findSubclassDeserializer(DeserializationContext deserializationcontext, Object obj, TokenBuffer tokenbuffer)
        throws IOException, JsonProcessingException
    {
        this;
        JVM INSTR monitorenter ;
        if (_subDeserializers != null)
        {
            break MISSING_BLOCK_LABEL_22;
        }
        JsonDeserializer jsondeserializer = null;
_L1:
        this;
        JVM INSTR monitorexit ;
        if (jsondeserializer != null)
        {
            return jsondeserializer;
        }
        break MISSING_BLOCK_LABEL_55;
        jsondeserializer = (JsonDeserializer)_subDeserializers.get(new ClassKey(obj.getClass()));
          goto _L1
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        DeserializerProvider deserializerprovider = deserializationcontext.getDeserializerProvider();
        if (deserializerprovider == null)
        {
            break MISSING_BLOCK_LABEL_151;
        }
        JavaType javatype = deserializationcontext.constructType(obj.getClass());
        jsondeserializer = deserializerprovider.findValueDeserializer(deserializationcontext.getConfig(), javatype, _property);
        if (jsondeserializer == null)
        {
            break MISSING_BLOCK_LABEL_151;
        }
        this;
        JVM INSTR monitorenter ;
        if (_subDeserializers == null)
        {
            _subDeserializers = new HashMap();
        }
        _subDeserializers.put(new ClassKey(obj.getClass()), jsondeserializer);
        this;
        JVM INSTR monitorexit ;
        break MISSING_BLOCK_LABEL_151;
        Exception exception1;
        exception1;
        this;
        JVM INSTR monitorexit ;
        throw exception1;
        return jsondeserializer;
    }

    protected Object constructDefaultInstance()
    {
        Object obj;
        try
        {
            obj = _defaultConstructor.newInstance(new Object[0]);
        }
        catch (Exception exception)
        {
            ClassUtil.unwrapAndThrowAsIAE(exception);
            return null;
        }
        return obj;
    }

    public final Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken == JsonToken.START_OBJECT)
        {
            jsonparser.nextToken();
            return deserializeFromObject(jsonparser, deserializationcontext);
        }
        static class _cls1
        {

            static final int $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[];
            static final int $SwitchMap$org$codehaus$jackson$JsonToken[];

            static 
            {
                $SwitchMap$org$codehaus$jackson$JsonParser$NumberType = new int[org.codehaus.jackson.JsonParser.NumberType.values().length];
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[org.codehaus.jackson.JsonParser.NumberType.INT.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[org.codehaus.jackson.JsonParser.NumberType.LONG.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 6;
                }
                catch (NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_ARRAY.ordinal()] = 7;
                }
                catch (NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 8;
                }
                catch (NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_OBJECT.ordinal()] = 9;
                }
                catch (NoSuchFieldError nosuchfielderror10)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.org.codehaus.jackson.JsonToken[jsontoken.ordinal()])
        {
        default:
            throw deserializationcontext.mappingException(getBeanClass());

        case 1: // '\001'
            return deserializeFromString(jsonparser, deserializationcontext);

        case 2: // '\002'
        case 3: // '\003'
            return deserializeFromNumber(jsonparser, deserializationcontext);

        case 4: // '\004'
            return jsonparser.getEmbeddedObject();

        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
            return deserializeUsingCreator(jsonparser, deserializationcontext);

        case 8: // '\b'
        case 9: // '\t'
            return deserializeFromObject(jsonparser, deserializationcontext);
        }
    }

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken == JsonToken.START_OBJECT)
        {
            jsontoken = jsonparser.nextToken();
        }
        while (jsontoken == JsonToken.FIELD_NAME) 
        {
            String s = jsonparser.getCurrentName();
            SettableBeanProperty settablebeanproperty = _beanProperties.find(s);
            jsonparser.nextToken();
            if (settablebeanproperty != null)
            {
                try
                {
                    settablebeanproperty.deserializeAndSet(jsonparser, deserializationcontext, obj);
                }
                catch (Exception exception)
                {
                    wrapAndThrow(exception, obj, s, deserializationcontext);
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
            jsontoken = jsonparser.nextToken();
        }
        return obj;
    }

    public Object deserializeFromNumber(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (_numberCreator == null) goto _L2; else goto _L1
_L1:
        _cls1..SwitchMap.org.codehaus.jackson.JsonParser.NumberType[jsonparser.getNumberType().ordinal()];
        JVM INSTR tableswitch 1 2: default 40
    //                   1 57
    //                   2 69;
           goto _L2 _L3 _L4
_L2:
        if (_delegatingCreator != null)
        {
            return _delegatingCreator.deserialize(jsonparser, deserializationcontext);
        } else
        {
            throw deserializationcontext.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON Number");
        }
_L3:
        return _numberCreator.construct(jsonparser.getIntValue());
_L4:
        return _numberCreator.construct(jsonparser.getLongValue());
    }

    public Object deserializeFromObject(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (_defaultConstructor != null) goto _L2; else goto _L1
_L1:
        if (_propertyBasedCreator == null) goto _L4; else goto _L3
_L3:
        Object obj = _deserializeUsingPropertyBased(jsonparser, deserializationcontext);
_L6:
        return obj;
_L4:
        if (_delegatingCreator != null)
        {
            return _delegatingCreator.deserialize(jsonparser, deserializationcontext);
        }
        if (_beanType.isAbstract())
        {
            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Can not instantiate abstract type ").append(_beanType).append(" (need to add/enable type information?)").toString());
        } else
        {
            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("No suitable constructor found for type ").append(_beanType).append(": can not instantiate from JSON object (need to add/enable type information?)").toString());
        }
_L2:
        obj = constructDefaultInstance();
        while (jsonparser.getCurrentToken() != JsonToken.END_OBJECT) 
        {
            String s = jsonparser.getCurrentName();
            jsonparser.nextToken();
            SettableBeanProperty settablebeanproperty = _beanProperties.find(s);
            if (settablebeanproperty != null)
            {
                try
                {
                    settablebeanproperty.deserializeAndSet(jsonparser, deserializationcontext, obj);
                }
                catch (Exception exception1)
                {
                    wrapAndThrow(exception1, obj, s, deserializationcontext);
                }
            } else
            if (_ignorableProps != null && _ignorableProps.contains(s))
            {
                jsonparser.skipChildren();
            } else
            if (_anySetter != null)
            {
                try
                {
                    _anySetter.deserializeAndSet(jsonparser, deserializationcontext, obj, s);
                }
                catch (Exception exception)
                {
                    wrapAndThrow(exception, obj, s, deserializationcontext);
                }
            } else
            {
                handleUnknownProperty(jsonparser, deserializationcontext, obj, s);
            }
            jsonparser.nextToken();
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public Object deserializeFromString(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (_stringCreator != null)
        {
            return _stringCreator.construct(jsonparser.getText());
        }
        if (_delegatingCreator != null)
        {
            return _delegatingCreator.deserialize(jsonparser, deserializationcontext);
        }
        if (deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonparser.getTextLength() == 0)
        {
            return null;
        } else
        {
            throw deserializationcontext.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON String");
        }
    }

    public Object deserializeUsingCreator(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if (_delegatingCreator == null)
        {
            break MISSING_BLOCK_LABEL_28;
        }
        Object obj = _delegatingCreator.deserialize(jsonparser, deserializationcontext);
        return obj;
        Exception exception;
        exception;
        wrapInstantiationProblem(exception, deserializationcontext);
        throw deserializationcontext.mappingException(getBeanClass());
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromObject(jsonparser, deserializationcontext);
    }

    public SettableBeanProperty findBackReference(String s)
    {
        if (_backRefs == null)
        {
            return null;
        } else
        {
            return (SettableBeanProperty)_backRefs.get(s);
        }
    }

    public final Class getBeanClass()
    {
        return _beanType.getRawClass();
    }

    public int getPropertyCount()
    {
        return _beanProperties.size();
    }

    public JavaType getValueType()
    {
        return _beanType;
    }

    protected Object handlePolymorphic(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj, TokenBuffer tokenbuffer)
        throws IOException, JsonProcessingException
    {
        JsonDeserializer jsondeserializer = _findSubclassDeserializer(deserializationcontext, obj, tokenbuffer);
        if (jsondeserializer != null)
        {
            if (tokenbuffer != null)
            {
                tokenbuffer.writeEndObject();
                JsonParser jsonparser1 = tokenbuffer.asParser();
                jsonparser1.nextToken();
                obj = jsondeserializer.deserialize(jsonparser1, deserializationcontext, obj);
            }
            if (jsonparser != null)
            {
                obj = jsondeserializer.deserialize(jsonparser, deserializationcontext, obj);
            }
            return obj;
        }
        if (tokenbuffer != null)
        {
            obj = handleUnknownProperties(deserializationcontext, obj, tokenbuffer);
        }
        if (jsonparser != null)
        {
            obj = deserialize(jsonparser, deserializationcontext, obj);
        }
        return obj;
    }

    protected Object handleUnknownProperties(DeserializationContext deserializationcontext, Object obj, TokenBuffer tokenbuffer)
        throws IOException, JsonProcessingException
    {
        tokenbuffer.writeEndObject();
        String s;
        for (JsonParser jsonparser = tokenbuffer.asParser(); jsonparser.nextToken() != JsonToken.END_OBJECT; handleUnknownProperty(jsonparser, deserializationcontext, obj, s))
        {
            s = jsonparser.getCurrentName();
            jsonparser.nextToken();
        }

        return obj;
    }

    protected void handleUnknownProperty(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj, String s)
        throws IOException, JsonProcessingException
    {
        if (_ignoreAllUnknown || _ignorableProps != null && _ignorableProps.contains(s))
        {
            jsonparser.skipChildren();
            return;
        } else
        {
            super.handleUnknownProperty(jsonparser, deserializationcontext, obj, s);
            return;
        }
    }

    public boolean hasProperty(String s)
    {
        return _beanProperties.find(s) != null;
    }

    public Iterator properties()
    {
        if (_beanProperties == null)
        {
            throw new IllegalStateException("Can only call before BeanDeserializer has been resolved");
        } else
        {
            return _beanProperties.allProperties();
        }
    }

    public void resolve(DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider)
        throws JsonMappingException
    {
        Iterator iterator = _beanProperties.allProperties();
_L6:
        SettableBeanProperty settablebeanproperty1;
        String s;
        JsonDeserializer jsondeserializer1;
        boolean flag;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        settablebeanproperty1 = (SettableBeanProperty)iterator.next();
        if (!settablebeanproperty1.hasValueDeserializer())
        {
            settablebeanproperty1.setValueDeserializer(findDeserializer(deserializationconfig, deserializerprovider, settablebeanproperty1.getType(), settablebeanproperty1));
        }
        s = settablebeanproperty1.getManagedReferenceName();
        if (s == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        jsondeserializer1 = settablebeanproperty1._valueDeserializer;
        flag = false;
        if (!(jsondeserializer1 instanceof BeanDeserializer)) goto _L2; else goto _L1
_L1:
        SettableBeanProperty settablebeanproperty2 = ((BeanDeserializer)jsondeserializer1).findBackReference(s);
_L4:
        if (settablebeanproperty2 == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can not handle managed/back reference '").append(s).append("': no back reference property found from type ").append(settablebeanproperty1.getType()).toString());
        }
        break MISSING_BLOCK_LABEL_357;
_L2:
        if (!(jsondeserializer1 instanceof ContainerDeserializer))
        {
            break; /* Loop/switch isn't completed */
        }
        JsonDeserializer jsondeserializer2 = ((ContainerDeserializer)jsondeserializer1).getContentDeserializer();
        if (!(jsondeserializer2 instanceof BeanDeserializer))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can not handle managed/back reference '").append(s).append("': value deserializer is of type ContainerDeserializer, but content type is not handled by a BeanDeserializer ").append(" (instead it's of type ").append(jsondeserializer2.getClass().getName()).append(")").toString());
        }
        settablebeanproperty2 = ((BeanDeserializer)jsondeserializer2).findBackReference(s);
        flag = true;
        if (true) goto _L4; else goto _L3
_L3:
        if (jsondeserializer1 instanceof AbstractDeserializer)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can not handle managed/back reference for abstract types (property ").append(_beanType.getRawClass().getName()).append(".").append(settablebeanproperty1.getName()).append(")").toString());
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can not handle managed/back reference '").append(s).append("': type for value deserializer is not BeanDeserializer or ContainerDeserializer, but ").append(jsondeserializer1.getClass().getName()).toString());
        }
        JavaType javatype = _beanType;
        JavaType javatype1 = settablebeanproperty2.getType();
        if (!javatype1.getRawClass().isAssignableFrom(javatype.getRawClass()))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can not handle managed/back reference '").append(s).append("': back reference type (").append(javatype1.getRawClass().getName()).append(") not compatible with managed type (").append(javatype.getRawClass().getName()).append(")").toString());
        }
        _beanProperties.replace(new SettableBeanProperty.ManagedReferenceProperty(s, settablebeanproperty1, settablebeanproperty2, _forClass.getAnnotations(), flag));
        if (true) goto _L6; else goto _L5
_L5:
        if (_anySetter != null && !_anySetter.hasValueDeserializer())
        {
            _anySetter.setValueDeserializer(findDeserializer(deserializationconfig, deserializerprovider, _anySetter.getType(), _anySetter.getProperty()));
        }
        if (_delegatingCreator != null)
        {
            org.codehaus.jackson.map.BeanProperty.Std std = new org.codehaus.jackson.map.BeanProperty.Std(null, _delegatingCreator.getValueType(), _forClass.getAnnotations(), _delegatingCreator.getCreator());
            JsonDeserializer jsondeserializer = findDeserializer(deserializationconfig, deserializerprovider, _delegatingCreator.getValueType(), std);
            _delegatingCreator.setDeserializer(jsondeserializer);
        }
        if (_propertyBasedCreator != null)
        {
            Iterator iterator1 = _propertyBasedCreator.properties().iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                SettableBeanProperty settablebeanproperty = (SettableBeanProperty)iterator1.next();
                if (!settablebeanproperty.hasValueDeserializer())
                {
                    settablebeanproperty.setValueDeserializer(findDeserializer(deserializationconfig, deserializerprovider, settablebeanproperty.getType(), settablebeanproperty));
                }
            } while (true);
        }
        return;
    }

    public void wrapAndThrow(Throwable throwable, Object obj, int i)
        throws IOException
    {
        wrapAndThrow(throwable, obj, i, null);
    }

    public void wrapAndThrow(Throwable throwable, Object obj, int i, DeserializationContext deserializationcontext)
        throws IOException
    {
        for (; (throwable instanceof InvocationTargetException) && throwable.getCause() != null; throwable = throwable.getCause()) { }
        if (throwable instanceof Error)
        {
            throw (Error)throwable;
        }
        boolean flag;
        if (deserializationcontext == null || deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.WRAP_EXCEPTIONS))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (throwable instanceof IOException)
        {
            if (!flag || !(throwable instanceof JsonMappingException))
            {
                throw (IOException)throwable;
            }
        } else
        if (!flag && (throwable instanceof RuntimeException))
        {
            throw (RuntimeException)throwable;
        }
        throw JsonMappingException.wrapWithPath(throwable, obj, i);
    }

    public void wrapAndThrow(Throwable throwable, Object obj, String s)
        throws IOException
    {
        wrapAndThrow(throwable, obj, s, null);
    }

    public void wrapAndThrow(Throwable throwable, Object obj, String s, DeserializationContext deserializationcontext)
        throws IOException
    {
        for (; (throwable instanceof InvocationTargetException) && throwable.getCause() != null; throwable = throwable.getCause()) { }
        if (throwable instanceof Error)
        {
            throw (Error)throwable;
        }
        boolean flag;
        if (deserializationcontext == null || deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.WRAP_EXCEPTIONS))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (throwable instanceof IOException)
        {
            if (!flag || !(throwable instanceof JsonMappingException))
            {
                throw (IOException)throwable;
            }
        } else
        if (!flag && (throwable instanceof RuntimeException))
        {
            throw (RuntimeException)throwable;
        }
        throw JsonMappingException.wrapWithPath(throwable, obj, s);
    }

    protected void wrapInstantiationProblem(Throwable throwable, DeserializationContext deserializationcontext)
        throws IOException
    {
        for (; (throwable instanceof InvocationTargetException) && throwable.getCause() != null; throwable = throwable.getCause()) { }
        if (throwable instanceof Error)
        {
            throw (Error)throwable;
        }
        boolean flag;
        if (deserializationcontext == null || deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.WRAP_EXCEPTIONS))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (throwable instanceof IOException)
        {
            throw (IOException)throwable;
        }
        if (!flag && (throwable instanceof RuntimeException))
        {
            throw (RuntimeException)throwable;
        } else
        {
            throw deserializationcontext.instantiationException(_beanType.getRawClass(), throwable);
        }
    }
}
