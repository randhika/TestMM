// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.collector;


public class ThreadCollector
{

    public ThreadCollector()
    {
    }

    public static String collect(Thread thread)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (thread != null)
        {
            stringbuilder.append("id=").append(thread.getId()).append("\n");
            stringbuilder.append("name=").append(thread.getName()).append("\n");
            stringbuilder.append("priority=").append(thread.getPriority()).append("\n");
            if (thread.getThreadGroup() != null)
            {
                stringbuilder.append("groupName=").append(thread.getThreadGroup().getName()).append("\n");
            }
        } else
        {
            stringbuilder.append("No broken thread, this might be a silent exception.");
        }
        return stringbuilder.toString();
    }
}
