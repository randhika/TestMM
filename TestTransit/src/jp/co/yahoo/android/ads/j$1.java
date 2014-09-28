// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

// Referenced classes of package jp.co.yahoo.android.ads:
//            j

static final class nit> extends ObjectInputStream
{

    final ClassLoader a;

    public Class resolveClass(ObjectStreamClass objectstreamclass)
        throws IOException, ClassNotFoundException
    {
        Class class1 = Class.forName(objectstreamclass.getName(), false, a);
        if (class1 == null)
        {
            class1 = super.resolveClass(objectstreamclass);
        }
        return class1;
    }

    tion(InputStream inputstream, ClassLoader classloader)
    {
        a = classloader;
        super(inputstream);
    }
}
