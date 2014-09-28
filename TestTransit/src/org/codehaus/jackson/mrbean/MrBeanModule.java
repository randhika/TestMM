// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.mrbean;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.Module;

// Referenced classes of package org.codehaus.jackson.mrbean:
//            AbstractTypeMaterializer

public class MrBeanModule extends Module
{

    private static final Version VERSION = new Version(1, 8, 0, null);
    private final String NAME;
    protected AbstractTypeMaterializer _materializer;

    public MrBeanModule()
    {
        this(new AbstractTypeMaterializer());
    }

    public MrBeanModule(AbstractTypeMaterializer abstracttypematerializer)
    {
        NAME = "MrBeanModule";
        _materializer = abstracttypematerializer;
    }

    public String getModuleName()
    {
        return "MrBeanModule";
    }

    public void setupModule(org.codehaus.jackson.map.Module.SetupContext setupcontext)
    {
        setupcontext.addAbstractTypeResolver(_materializer);
    }

    public Version version()
    {
        return VERSION;
    }

}
