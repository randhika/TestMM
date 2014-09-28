// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

// Referenced classes of package jp.co.yahoo.android.common:
//            Base64

static final class val.loader extends ObjectInputStream
{

    final ClassLoader val$loader;

    public Class resolveClass(ObjectStreamClass objectstreamclass)
        throws IOException, ClassNotFoundException
    {
        Class class1 = Class.forName(objectstreamclass.getName(), false, val$loader);
        if (class1 == null)
        {
            class1 = super.resolveClass(objectstreamclass);
        }
        return class1;
    }

    (InputStream inputstream, ClassLoader classloader)
    {
        val$loader = classloader;
        super(inputstream);
    }
}
