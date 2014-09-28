// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            PushDiainfoManager

class val.registeredRail
    implements rarListener
{

    final PushDiainfoManager this$0;
    final hManagerListener val$listener;
    final Bundle val$registeredRail;
    final BearerToken val$token;
    final String val$yid;

    public void onRegistered(String s)
    {
        if (TextUtils.isEmpty(s))
        {
            if (val$listener != null)
            {
                val$listener.onError(5, "-1", PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d014f), PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d0102));
            }
            return;
        } else
        {
            PushDiainfoManager.access$900(PushDiainfoManager.this, val$token, val$yid, val$registeredRail, val$listener);
            return;
        }
    }

    hManagerListener()
    {
        this$0 = final_pushdiainfomanager;
        val$listener = hmanagerlistener;
        val$token = bearertoken;
        val$yid = s;
        val$registeredRail = Bundle.this;
        super();
    }
}
