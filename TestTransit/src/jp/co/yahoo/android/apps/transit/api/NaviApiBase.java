// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            YolpApiBase

public class NaviApiBase extends YolpApiBase
{

    public NaviApiBase(Context context, ApiBase.ApiListener apilistener)
    {
        super(context, apilistener);
        setParameter("appid", getContext().getString(0x7f0d003b));
    }
}
