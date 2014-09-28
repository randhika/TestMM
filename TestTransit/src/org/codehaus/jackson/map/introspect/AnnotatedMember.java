// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.introspect;

import java.lang.reflect.Member;
import org.codehaus.jackson.map.util.ClassUtil;

// Referenced classes of package org.codehaus.jackson.map.introspect:
//            Annotated

public abstract class AnnotatedMember extends Annotated
{

    protected AnnotatedMember()
    {
    }

    public final void fixAccess()
    {
        ClassUtil.checkAndFixAccess(getMember());
    }

    public abstract Class getDeclaringClass();

    public abstract Member getMember();
}
