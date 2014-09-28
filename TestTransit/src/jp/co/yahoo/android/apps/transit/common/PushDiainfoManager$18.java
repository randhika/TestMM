// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.text.TextUtils;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            PushDiainfoManager

class val.saveDb
    implements rarListener
{

    final PushDiainfoManager this$0;
    final jp.co.yahoo.android.apps.transit.api.tener val$listener;
    final boolean val$saveDb;
    final BearerToken val$token;
    final String val$yid;

    public void onRegistered(String s)
    {
        if (TextUtils.isEmpty(s))
        {
            if (val$listener != null)
            {
                val$listener.onError(PushDiainfoManager.access$1200(PushDiainfoManager.this));
            }
            return;
        } else
        {
            PushDiainfoManager.access$1300(PushDiainfoManager.this, val$token, s, val$yid, val$saveDb, val$listener);
            return;
        }
    }

    er()
    {
        this$0 = final_pushdiainfomanager;
        val$listener = tener;
        val$token = bearertoken;
        val$yid = s;
        val$saveDb = Z.this;
        super();
    }
}
