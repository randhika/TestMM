// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.events;

import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.gms.games.internal.events:
//            EventIncrementCache

public abstract class EventIncrementManager
{

    private final AtomicReference Rs = new AtomicReference();

    public EventIncrementManager()
    {
    }

    public void flush()
    {
        EventIncrementCache eventincrementcache = (EventIncrementCache)Rs.get();
        if (eventincrementcache != null)
        {
            eventincrementcache.flush();
        }
    }

    protected abstract EventIncrementCache hx();

    public void l(String s, int i)
    {
        EventIncrementCache eventincrementcache = (EventIncrementCache)Rs.get();
        if (eventincrementcache == null)
        {
            eventincrementcache = hx();
            if (!Rs.compareAndSet(null, eventincrementcache))
            {
                eventincrementcache = (EventIncrementCache)Rs.get();
            }
        }
        eventincrementcache.u(s, i);
    }
}
