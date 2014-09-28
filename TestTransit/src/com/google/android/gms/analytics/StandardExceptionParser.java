// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

// Referenced classes of package com.google.android.gms.analytics:
//            ExceptionParser, aa

public class StandardExceptionParser
    implements ExceptionParser
{

    private final TreeSet xd = new TreeSet();

    public StandardExceptionParser(Context context, Collection collection)
    {
        setIncludedPackages(context, collection);
    }

    protected StackTraceElement getBestStackTraceElement(Throwable throwable)
    {
        StackTraceElement astacktraceelement[] = throwable.getStackTrace();
        if (astacktraceelement == null || astacktraceelement.length == 0)
        {
            return null;
        }
        int i = astacktraceelement.length;
        int j = 0;
        do
        {
            if (j >= i)
            {
                break;
            }
            StackTraceElement stacktraceelement = astacktraceelement[j];
            String s = stacktraceelement.getClassName();
            for (Iterator iterator = xd.iterator(); iterator.hasNext();)
            {
                if (s.startsWith((String)iterator.next()))
                {
                    return stacktraceelement;
                }
            }

            j++;
        } while (true);
        return astacktraceelement[0];
    }

    protected Throwable getCause(Throwable throwable)
    {
        for (; throwable.getCause() != null; throwable = throwable.getCause()) { }
        return throwable;
    }

    public String getDescription(String s, Throwable throwable)
    {
        return getDescription(getCause(throwable), getBestStackTraceElement(getCause(throwable)), s);
    }

    protected String getDescription(Throwable throwable, StackTraceElement stacktraceelement, String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(throwable.getClass().getSimpleName());
        if (stacktraceelement != null)
        {
            String as[] = stacktraceelement.getClassName().split("\\.");
            String s1 = "unknown";
            if (as != null && as.length > 0)
            {
                s1 = as[-1 + as.length];
            }
            Object aobj[] = new Object[3];
            aobj[0] = s1;
            aobj[1] = stacktraceelement.getMethodName();
            aobj[2] = Integer.valueOf(stacktraceelement.getLineNumber());
            stringbuilder.append(String.format(" (@%s:%s:%s)", aobj));
        }
        if (s != null)
        {
            stringbuilder.append(String.format(" {%s}", new Object[] {
                s
            }));
        }
        return stringbuilder.toString();
    }

    public void setIncludedPackages(Context context, Collection collection)
    {
        HashSet hashset;
        xd.clear();
        hashset = new HashSet();
        if (collection != null)
        {
            hashset.addAll(collection);
        }
        if (context == null) goto _L2; else goto _L1
_L1:
        ActivityInfo aactivityinfo[];
        String s2 = context.getApplicationContext().getPackageName();
        xd.add(s2);
        aactivityinfo = context.getApplicationContext().getPackageManager().getPackageInfo(s2, 15).activities;
        if (aactivityinfo == null) goto _L2; else goto _L3
_L3:
        int i = aactivityinfo.length;
        int j = 0;
_L4:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        hashset.add(aactivityinfo[j].packageName);
        j++;
        if (true) goto _L4; else goto _L2
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        aa.B("No package found");
_L2:
        Iterator iterator = hashset.iterator();
label0:
        do
        {
            if (iterator.hasNext())
            {
                String s = (String)iterator.next();
                Iterator iterator1 = xd.iterator();
                boolean flag = true;
                do
                {
label1:
                    {
                        if (iterator1.hasNext())
                        {
                            String s1 = (String)iterator1.next();
                            if (s.startsWith(s1))
                            {
                                break label1;
                            }
                            if (s1.startsWith(s))
                            {
                                xd.remove(s1);
                            }
                        }
                        if (flag)
                        {
                            xd.add(s);
                        }
                        continue label0;
                    }
                    flag = false;
                } while (true);
            }
            return;
        } while (true);
    }
}
