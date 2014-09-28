// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.jraf.android.util.activitylifecyclecallbackscompat;

import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;

// Referenced classes of package jp.co.yahoo.applicot.jraf.android.util.activitylifecyclecallbackscompat:
//            ActivityLifecycleCallbacksCompat

public class MainLifecycleDispatcher
    implements ActivityLifecycleCallbacksCompat
{

    private static final MainLifecycleDispatcher INSTANCE = new MainLifecycleDispatcher();
    private ArrayList mActivityLifecycleCallbacks;

    private MainLifecycleDispatcher()
    {
        mActivityLifecycleCallbacks = new ArrayList();
    }

    private Object[] collectActivityLifecycleCallbacks()
    {
        ArrayList arraylist = mActivityLifecycleCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mActivityLifecycleCallbacks.size();
        Object aobj[];
        aobj = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_31;
        }
        aobj = mActivityLifecycleCallbacks.toArray();
        arraylist;
        JVM INSTR monitorexit ;
        return aobj;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static MainLifecycleDispatcher get()
    {
        return INSTANCE;
    }

    public void onActivityCreated(Activity activity, Bundle bundle)
    {
        Object aobj[] = collectActivityLifecycleCallbacks();
        if (aobj != null)
        {
            int i = aobj.length;
            for (int j = 0; j < i; j++)
            {
                ((ActivityLifecycleCallbacksCompat)aobj[j]).onActivityCreated(activity, bundle);
            }

        }
    }

    public void onActivityDestroyed(Activity activity)
    {
        Object aobj[] = collectActivityLifecycleCallbacks();
        if (aobj != null)
        {
            int i = aobj.length;
            for (int j = 0; j < i; j++)
            {
                ((ActivityLifecycleCallbacksCompat)aobj[j]).onActivityDestroyed(activity);
            }

        }
    }

    public void onActivityPaused(Activity activity)
    {
        Object aobj[] = collectActivityLifecycleCallbacks();
        if (aobj != null)
        {
            int i = aobj.length;
            for (int j = 0; j < i; j++)
            {
                ((ActivityLifecycleCallbacksCompat)aobj[j]).onActivityPaused(activity);
            }

        }
    }

    public void onActivityResumed(Activity activity)
    {
        Object aobj[] = collectActivityLifecycleCallbacks();
        if (aobj != null)
        {
            int i = aobj.length;
            for (int j = 0; j < i; j++)
            {
                ((ActivityLifecycleCallbacksCompat)aobj[j]).onActivityResumed(activity);
            }

        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle)
    {
        Object aobj[] = collectActivityLifecycleCallbacks();
        if (aobj != null)
        {
            int i = aobj.length;
            for (int j = 0; j < i; j++)
            {
                ((ActivityLifecycleCallbacksCompat)aobj[j]).onActivitySaveInstanceState(activity, bundle);
            }

        }
    }

    public void onActivityStarted(Activity activity)
    {
        Object aobj[] = collectActivityLifecycleCallbacks();
        if (aobj != null)
        {
            int i = aobj.length;
            for (int j = 0; j < i; j++)
            {
                ((ActivityLifecycleCallbacksCompat)aobj[j]).onActivityStarted(activity);
            }

        }
    }

    public void onActivityStopped(Activity activity)
    {
        Object aobj[] = collectActivityLifecycleCallbacks();
        if (aobj != null)
        {
            int i = aobj.length;
            for (int j = 0; j < i; j++)
            {
                ((ActivityLifecycleCallbacksCompat)aobj[j]).onActivityStopped(activity);
            }

        }
    }

    void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksCompat activitylifecyclecallbackscompat)
    {
        synchronized (mActivityLifecycleCallbacks)
        {
            mActivityLifecycleCallbacks.add(activitylifecyclecallbackscompat);
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacksCompat activitylifecyclecallbackscompat)
    {
        synchronized (mActivityLifecycleCallbacks)
        {
            mActivityLifecycleCallbacks.remove(activitylifecyclecallbackscompat);
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

}
