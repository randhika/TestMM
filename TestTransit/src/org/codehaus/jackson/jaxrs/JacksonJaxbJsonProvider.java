// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.jaxrs;

import org.codehaus.jackson.map.ObjectMapper;

// Referenced classes of package org.codehaus.jackson.jaxrs:
//            JacksonJsonProvider, Annotations

public class JacksonJaxbJsonProvider extends JacksonJsonProvider
{

    public static final Annotations DEFAULT_ANNOTATIONS[];

    public JacksonJaxbJsonProvider()
    {
        this(null, DEFAULT_ANNOTATIONS);
    }

    public JacksonJaxbJsonProvider(ObjectMapper objectmapper, Annotations aannotations[])
    {
        super(objectmapper, aannotations);
    }

    public transient JacksonJaxbJsonProvider(Annotations aannotations[])
    {
        this(null, aannotations);
    }

    static 
    {
        Annotations aannotations[] = new Annotations[2];
        aannotations[0] = Annotations.JACKSON;
        aannotations[1] = Annotations.JAXB;
        DEFAULT_ANNOTATIONS = aannotations;
    }
}
