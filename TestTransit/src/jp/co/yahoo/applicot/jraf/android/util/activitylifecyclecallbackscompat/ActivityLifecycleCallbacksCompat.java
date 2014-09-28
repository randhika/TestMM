// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.jraf.android.util.activitylifecyclecallbackscompat;

import android.app.Activity;
import android.os.Bundle;

public interface ActivityLifecycleCallbacksCompat
{

    public abstract void onActivityCreated(Activity activity, Bundle bundle);

    public abstract void onActivityDestroyed(Activity activity);

    public abstract void onActivityPaused(Activity activity);

    public abstract void onActivityResumed(Activity activity);

    public abstract void onActivitySaveInstanceState(Activity activity, Bundle bundle);

    public abstract void onActivityStarted(Activity activity);

    public abstract void onActivityStopped(Activity activity);
}
