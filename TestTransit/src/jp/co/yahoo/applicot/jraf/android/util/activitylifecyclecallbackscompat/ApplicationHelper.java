// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.jraf.android.util.activitylifecyclecallbackscompat;

import android.app.Application;

// Referenced classes of package jp.co.yahoo.applicot.jraf.android.util.activitylifecyclecallbackscompat:
//            ActivityLifecycleCallbacksWrapper, MainLifecycleDispatcher, ActivityLifecycleCallbacksCompat

public class ApplicationHelper
{

    public static final boolean PRE_ICS;

    public ApplicationHelper()
    {
    }

    private static void postIcsRegisterActivityLifecycleCallbacks(Application application, ActivityLifecycleCallbacksCompat activitylifecyclecallbackscompat)
    {
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksWrapper(activitylifecyclecallbackscompat));
    }

    private static void postIcsUnregisterActivityLifecycleCallbacks(Application application, ActivityLifecycleCallbacksCompat activitylifecyclecallbackscompat)
    {
        application.unregisterActivityLifecycleCallbacks(new ActivityLifecycleCallbacksWrapper(activitylifecyclecallbackscompat));
    }

    private static void preIcsRegisterActivityLifecycleCallbacks(ActivityLifecycleCallbacksCompat activitylifecyclecallbackscompat)
    {
        MainLifecycleDispatcher.get().registerActivityLifecycleCallbacks(activitylifecyclecallbackscompat);
    }

    private static void preIcsUnregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacksCompat activitylifecyclecallbackscompat)
    {
        MainLifecycleDispatcher.get().unregisterActivityLifecycleCallbacks(activitylifecyclecallbackscompat);
    }

    public static void registerActivityLifecycleCallbacks(Application application, ActivityLifecycleCallbacksCompat activitylifecyclecallbackscompat)
    {
        if (PRE_ICS)
        {
            preIcsRegisterActivityLifecycleCallbacks(activitylifecyclecallbackscompat);
            return;
        } else
        {
            postIcsRegisterActivityLifecycleCallbacks(application, activitylifecyclecallbackscompat);
            return;
        }
    }

    public void unregisterActivityLifecycleCallbacks(Application application, ActivityLifecycleCallbacksCompat activitylifecyclecallbackscompat)
    {
        if (PRE_ICS)
        {
            preIcsUnregisterActivityLifecycleCallbacks(activitylifecyclecallbackscompat);
            return;
        } else
        {
            postIcsUnregisterActivityLifecycleCallbacks(application, activitylifecyclecallbackscompat);
            return;
        }
    }

    static 
    {
        boolean flag;
        if (android.os.Build.VERSION.SDK_INT < 14)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        PRE_ICS = flag;
    }
}
