// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.mrbean;


// Referenced classes of package org.codehaus.jackson.mrbean:
//            AbstractTypeMaterializer

private static class  extends ClassLoader
{

    public Class loadAndResolve(String s, byte abyte0[], Class class1)
        throws IllegalArgumentException
    {
        Class class2 = findLoadedClass(s);
        if (class2 != null && class1.isAssignableFrom(class2))
        {
            return class2;
        }
        Class class3;
        try
        {
            class3 = defineClass(s, abyte0, 0, abyte0.length);
        }
        catch (LinkageError linkageerror)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to load class '").append(s).append("': ").append(linkageerror.getMessage()).toString(), linkageerror);
        }
        resolveClass(class3);
        return class3;
    }

    public (ClassLoader classloader)
    {
        super(classloader);
    }
}
