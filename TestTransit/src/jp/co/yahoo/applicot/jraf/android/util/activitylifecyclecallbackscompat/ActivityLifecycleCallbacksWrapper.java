// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.jraf.android.util.activitylifecyclecallbackscompat;

import android.app.Activity;
import android.os.Bundle;

// Referenced classes of package jp.co.yahoo.applicot.jraf.android.util.activitylifecyclecallbackscompat:
//            ActivityLifecycleCallbacksCompat

class ActivityLifecycleCallbacksWrapper
    implements android.app.Application.ActivityLifecycleCallbacks
{

    private ActivityLifecycleCallbacksCompat mCallback;

    public ActivityLifecycleCallbacksWrapper(ActivityLifecycleCallbacksCompat activitylifecyclecallbackscompat)
    {
        mCallback = activitylifecyclecallbackscompat;
    }

    public void onActivityCreated(Activity activity, Bundle bundle)
    {
        mCallback.onActivityCreated(activity, bundle);
    }

    public void onActivityDestroyed(Activity activity)
    {
        mCallback.onActivityDestroyed(activity);
    }

    public void onActivityPaused(Activity activity)
    {
        mCallback.onActivityPaused(activity);
    }

    public void onActivityResumed(Activity activity)
    {
        mCallback.onActivityResumed(activity);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle)
    {
        mCallback.onActivitySaveInstanceState(activity, bundle);
    }

    public void onActivityStarted(Activity activity)
    {
        mCallback.onActivityStarted(activity);
    }

    public void onActivityStopped(Activity activity)
    {
        mCallback.onActivityStopped(activity);
    }
}
