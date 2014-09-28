// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.os.Bundle;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            RegistSearch

public static interface I
{

    public abstract void onLoginApiCanceled(int i, String s);

    public abstract void onLoginApiError(int i, String s);

    public abstract void onLoginApiSuccess(int i, Bundle bundle);

    public abstract void onLoginServiceConnected();
}
