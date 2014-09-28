// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.os.Bundle;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            PushDiainfoManager

class hManagerListener
    implements hManagerListener
{

    final PushDiainfoManager this$0;
    final hManagerListener val$listener;
    final Bundle val$registeredRail;
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
            val$listener.onError(i, s, s1, s2);
        }
    }

    public void onSuccess(String s, String s1)
    {
        if (!setRailPushForYid(val$token, new PushDiainfoManager.PushManagerListener() {

        final PushDiainfoManager._cls15 this$1;

        public void onCanceled()
        {
        }

        public void onError(int i, String s2, String s3, String s4)
        {
            if (listener != null)
            {
                listener.onError(i, s2, s3, s4);
            }
        }

        public void onSuccess(String s2, String s3)
        {
            PushDiainfoManager.access$900(this$0, token, yid, registeredRail, listener);
        }

            
            {
                this$1 = PushDiainfoManager._cls15.this;
                super();
            }
    }))
        {
            PushDiainfoManager.access$900(PushDiainfoManager.this, val$token, val$yid, val$registeredRail, val$listener);
        }
    }

    hManagerListener()
    {
        this$0 = final_pushdiainfomanager;
        val$token = bearertoken;
        val$yid = s;
        val$registeredRail = bundle;
        val$listener = hManagerListener.this;
        super();
    }
}
