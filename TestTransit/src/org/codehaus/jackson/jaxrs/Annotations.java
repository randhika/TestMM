// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.jaxrs;


public final class Annotations extends Enum
{

    private static final Annotations $VALUES[];
    public static final Annotations JACKSON;
    public static final Annotations JAXB;

    private Annotations(String s, int i)
    {
        super(s, i);
    }

    public static Annotations valueOf(String s)
    {
        return (Annotations)Enum.valueOf(org/codehaus/jackson/jaxrs/Annotations, s);
    }

    public static Annotations[] values()
    {
        return (Annotations[])$VALUES.clone();
    }

    static 
    {
        JACKSON = new Annotations("JACKSON", 0);
        JAXB = new Annotations("JAXB", 1);
        Annotations aannotations[] = new Annotations[2];
        aannotations[0] = JACKSON;
        aannotations[1] = JAXB;
        $VALUES = aannotations;
    }
}
