// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import jp.co.yahoo.yconnect.core.oauth2.BearerToken;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            PushDiainfoManager

class er
    implements hManagerListener
{

    final PushDiainfoManager this$0;
    final jp.co.yahoo.android.apps.transit.api.tener val$listener;
    final String val$registrationId;
    final boolean val$saveDb;
    final BearerToken val$token;
    final String val$yid;

    public void onCanceled()
    {
        if (val$listener != null)
        {
            val$listener.onCanceled();
        }
    }

    public void onError(int i, String s, String s1, String s2)
    {
        if (val$listener != null)
        {
            val$listener.onError(PushDiainfoManager.access$1400(PushDiainfoManager.this, s, s2));
        }
    }

    public void onSuccess(String s, String s1)
    {
        PushDiainfoManager.access$1300(PushDiainfoManager.this, val$token, val$registrationId, val$yid, val$saveDb, val$listener);
    }

    er()
    {
        this$0 = final_pushdiainfomanager;
        val$token = bearertoken;
        val$registrationId = s;
        val$yid = s1;
        val$saveDb = flag;
        val$listener = jp.co.yahoo.android.apps.transit.api.tener.this;
        super();
    }
}
