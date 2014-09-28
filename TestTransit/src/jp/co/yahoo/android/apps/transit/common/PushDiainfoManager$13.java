// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            PushDiainfoManager, GcmRegistrar

class val.registrationIdOld
    implements jp.co.yahoo.android.apps.transit.api.tener
{

    final PushDiainfoManager this$0;
    final hManagerListener val$listener;
    final String val$registrationIdOld;

    public boolean onCanceled()
    {
        GcmRegistrar.storeRegistrationIdOld(PushDiainfoManager.access$200(PushDiainfoManager.this), val$registrationIdOld);
        if (val$listener != null)
        {
            val$listener.onCanceled();
        }
        return false;
    }

    public boolean onError(APIError apierror)
    {
        GcmRegistrar.storeRegistrationIdOld(PushDiainfoManager.access$200(PushDiainfoManager.this), val$registrationIdOld);
        if (val$listener != null)
        {
            val$listener.onError(6, apierror.getCode(), PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d014f), apierror.getMessage());
        }
        return false;
    }

    public boolean onSuccess(Bundle bundle)
    {
        GcmRegistrar.clearRegistrationIdOld(PushDiainfoManager.access$200(PushDiainfoManager.this));
        if (val$listener != null)
        {
            val$listener.onSuccess(PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d00b1), PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d00a8));
        }
        return false;
    }

    public volatile boolean onSuccess(Object obj)
    {
        return onSuccess((Bundle)obj);
    }

    hManagerListener()
    {
        this$0 = final_pushdiainfomanager;
        val$listener = hmanagerlistener;
        val$registrationIdOld = String.this;
        super();
    }
}
