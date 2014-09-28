// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import org.codehaus.jackson.map.AbstractTypeResolver;
import org.codehaus.jackson.map.Deserializers;
import org.codehaus.jackson.map.KeyDeserializers;
import org.codehaus.jackson.map.util.ArrayBuilders;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            BeanDeserializerModifier, BeanDeserializerFactory

public static class _abstractTypeResolvers extends org.codehaus.jackson.map.gImpl
{

    protected static final AbstractTypeResolver NO_ABSTRACT_TYPE_RESOLVERS[] = new AbstractTypeResolver[0];
    protected static final KeyDeserializers NO_KEY_DESERIALIZERS[] = new KeyDeserializers[0];
    protected static final BeanDeserializerModifier NO_MODIFIERS[] = new BeanDeserializerModifier[0];
    protected final AbstractTypeResolver _abstractTypeResolvers[];
    protected final Deserializers _additionalDeserializers[];
    protected final KeyDeserializers _additionalKeyDeserializers[];
    protected final BeanDeserializerModifier _modifiers[];

    public Iterable abstractTypeResolvers()
    {
        return ArrayBuilders.arrayAsIterable(_abstractTypeResolvers);
    }

    public Iterable deserializerModifiers()
    {
        return ArrayBuilders.arrayAsIterable(_modifiers);
    }

    public Iterable deserializers()
    {
        return ArrayBuilders.arrayAsIterable(_additionalDeserializers);
    }

    public boolean hasAbstractTypeResolvers()
    {
        return _abstractTypeResolvers.length > 0;
    }

    public boolean hasDeserializerModifiers()
    {
        return _modifiers.length > 0;
    }

    public boolean hasDeserializers()
    {
        return _additionalDeserializers.length > 0;
    }

    public boolean hasKeyDeserializers()
    {
        return _additionalKeyDeserializers.length > 0;
    }

    public Iterable keyDeserializers()
    {
        return ArrayBuilders.arrayAsIterable(_additionalKeyDeserializers);
    }

    public org.codehaus.jackson.map.gImpl withAbstractTypeResolver(AbstractTypeResolver abstracttyperesolver)
    {
        if (abstracttyperesolver == null)
        {
            throw new IllegalArgumentException("Can not pass null resolver");
        } else
        {
            AbstractTypeResolver aabstracttyperesolver[] = (AbstractTypeResolver[])ArrayBuilders.insertInListNoDup(_abstractTypeResolvers, abstracttyperesolver);
            return new <init>(_additionalDeserializers, _additionalKeyDeserializers, _modifiers, aabstracttyperesolver);
        }
    }

    public org.codehaus.jackson.map.gImpl withAdditionalDeserializers(Deserializers deserializers1)
    {
        if (deserializers1 == null)
        {
            throw new IllegalArgumentException("Can not pass null Deserializers");
        } else
        {
            return new <init>((Deserializers[])ArrayBuilders.insertInListNoDup(_additionalDeserializers, deserializers1), _additionalKeyDeserializers, _modifiers, _abstractTypeResolvers);
        }
    }

    public org.codehaus.jackson.map.gImpl withAdditionalKeyDeserializers(KeyDeserializers keydeserializers)
    {
        if (keydeserializers == null)
        {
            throw new IllegalArgumentException("Can not pass null KeyDeserializers");
        } else
        {
            KeyDeserializers akeydeserializers[] = (KeyDeserializers[])ArrayBuilders.insertInListNoDup(_additionalKeyDeserializers, keydeserializers);
            return new <init>(_additionalDeserializers, akeydeserializers, _modifiers, _abstractTypeResolvers);
        }
    }

    public org.codehaus.jackson.map.gImpl withDeserializerModifier(BeanDeserializerModifier beandeserializermodifier)
    {
        if (beandeserializermodifier == null)
        {
            throw new IllegalArgumentException("Can not pass null modifier");
        } else
        {
            BeanDeserializerModifier abeandeserializermodifier[] = (BeanDeserializerModifier[])ArrayBuilders.insertInListNoDup(_modifiers, beandeserializermodifier);
            return new <init>(_additionalDeserializers, _additionalKeyDeserializers, abeandeserializermodifier, _abstractTypeResolvers);
        }
    }


    public ()
    {
        this(null, null, null, null);
    }

    protected <init>(Deserializers adeserializers[], KeyDeserializers akeydeserializers[], BeanDeserializerModifier abeandeserializermodifier[], AbstractTypeResolver aabstracttyperesolver[])
    {
        if (adeserializers == null)
        {
            adeserializers = BeanDeserializerFactory.access$000();
        }
        _additionalDeserializers = adeserializers;
        if (akeydeserializers == null)
        {
            akeydeserializers = NO_KEY_DESERIALIZERS;
        }
        _additionalKeyDeserializers = akeydeserializers;
        if (abeandeserializermodifier == null)
        {
            abeandeserializermodifier = NO_MODIFIERS;
        }
        _modifiers = abeandeserializermodifier;
        if (aabstracttyperesolver == null)
        {
            aabstracttyperesolver = NO_ABSTRACT_TYPE_RESOLVERS;
        }
        _abstractTypeResolvers = aabstracttyperesolver;
    }
}
