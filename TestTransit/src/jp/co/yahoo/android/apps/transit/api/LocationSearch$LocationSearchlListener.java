// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.os.Bundle;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            LocationSearch

public static interface Q
{

    public abstract void onError(String s, String s1);

    public abstract void onSuccess(String s, Bundle bundle);

    public abstract void onTimeout(String s, String s1);
}
