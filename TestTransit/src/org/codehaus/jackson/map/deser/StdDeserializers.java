// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import org.codehaus.jackson.map.type.TypeFactory;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            UntypedObjectDeserializer, DateDeserializer, TimestampDeserializer, FromStringDeserializer, 
//            StdDeserializer

class StdDeserializers
{

    final HashMap _deserializers = new HashMap();

    private StdDeserializers()
    {
        add(new UntypedObjectDeserializer());
        StdDeserializer.StringDeserializer stringdeserializer = new StdDeserializer.StringDeserializer();
        add(stringdeserializer, java/lang/String);
        add(stringdeserializer, java/lang/CharSequence);
        add(new StdDeserializer.ClassDeserializer());
        add(new StdDeserializer.BooleanDeserializer(java/lang/Boolean, null));
        add(new StdDeserializer.ByteDeserializer(java/lang/Byte, null));
        add(new StdDeserializer.ShortDeserializer(java/lang/Short, null));
        add(new StdDeserializer.CharacterDeserializer(java/lang/Character, null));
        add(new StdDeserializer.IntegerDeserializer(java/lang/Integer, null));
        add(new StdDeserializer.LongDeserializer(java/lang/Long, null));
        add(new StdDeserializer.FloatDeserializer(java/lang/Float, null));
        add(new StdDeserializer.DoubleDeserializer(java/lang/Double, null));
        add(new StdDeserializer.BooleanDeserializer(Boolean.TYPE, Boolean.FALSE));
        add(new StdDeserializer.ByteDeserializer(Byte.TYPE, Byte.valueOf((byte)0)));
        add(new StdDeserializer.ShortDeserializer(Short.TYPE, Short.valueOf((short)0)));
        add(new StdDeserializer.CharacterDeserializer(Character.TYPE, Character.valueOf('\0')));
        add(new StdDeserializer.IntegerDeserializer(Integer.TYPE, Integer.valueOf(0)));
        add(new StdDeserializer.LongDeserializer(Long.TYPE, Long.valueOf(0L)));
        add(new StdDeserializer.FloatDeserializer(Float.TYPE, Float.valueOf(0.0F)));
        add(new StdDeserializer.DoubleDeserializer(Double.TYPE, Double.valueOf(0.0D)));
        add(new StdDeserializer.NumberDeserializer());
        add(new StdDeserializer.BigDecimalDeserializer());
        add(new StdDeserializer.BigIntegerDeserializer());
        add(new DateDeserializer());
        add(new StdDeserializer.SqlDateDeserializer());
        add(new TimestampDeserializer());
        add(new StdDeserializer.CalendarDeserializer());
        add(new StdDeserializer.CalendarDeserializer(java/util/GregorianCalendar), java/util/GregorianCalendar);
        for (Iterator iterator = FromStringDeserializer.all().iterator(); iterator.hasNext(); add((FromStringDeserializer)iterator.next())) { }
        add(new StdDeserializer.StackTraceElementDeserializer());
        add(new StdDeserializer.TokenBufferDeserializer());
        add(new StdDeserializer.AtomicBooleanDeserializer());
    }

    private void add(StdDeserializer stddeserializer)
    {
        add(stddeserializer, stddeserializer.getValueClass());
    }

    private void add(StdDeserializer stddeserializer, Class class1)
    {
        _deserializers.put(TypeFactory.defaultInstance().constructType(class1), stddeserializer);
    }

    public static HashMap constructAll()
    {
        return (new StdDeserializers())._deserializers;
    }
}
