// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.node;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.codehaus.jackson.JsonNode;

// Referenced classes of package org.codehaus.jackson.node:
//            ContainerNode

protected static class 
    implements Iterator
{

    static final next instance = new <init>();

    public static  instance()
    {
        return instance;
    }

    public boolean hasNext()
    {
        return false;
    }

    public volatile Object next()
    {
        return next();
    }

    public JsonNode next()
    {
        throw new NoSuchElementException();
    }

    public void remove()
    {
        throw new IllegalStateException();
    }


    private ()
    {
    }
}
