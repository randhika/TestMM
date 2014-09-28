// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.text.TextUtils;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            PushDiainfoManager, GcmRegistrar

class val.registrationId
    implements rarListener
{

    final PushDiainfoManager this$0;
    final hManagerListener val$listener;
    final String val$registrationId;

    public void onRegistered(String s)
    {
        if (TextUtils.isEmpty(s))
        {
            if (val$listener != null)
            {
                val$listener.onError(5, "-1", PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d014f), PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d0102));
            }
        } else
        if (s.equals(val$registrationId))
        {
            GcmRegistrar.clearRegistrationIdOld(PushDiainfoManager.access$200(PushDiainfoManager.this));
            if (val$listener != null)
            {
                val$listener.onSuccess(PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d00b1), PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d00a8));
                return;
            }
        } else
        {
            PushDiainfoManager.access$800(PushDiainfoManager.this, val$registrationId, s, val$listener);
            return;
        }
    }

    hManagerListener()
    {
        this$0 = final_pushdiainfomanager;
        val$listener = hmanagerlistener;
        val$registrationId = String.this;
        super();
    }
}
