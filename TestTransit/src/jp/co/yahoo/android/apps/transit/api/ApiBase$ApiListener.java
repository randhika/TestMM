// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            ApiBase

public static interface 
{

    public abstract boolean onCanceled();

    public abstract boolean onError(APIError apierror);

    public abstract boolean onSuccess(ApiBase apibase, Object obj);
}
