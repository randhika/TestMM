// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.os.Bundle;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            PushDiainfoManager

class this._cls1
    implements anagerListener
{

    final l.listener this$1;

    public void onCanceled()
    {
    }

    public void onError(int i, String s, String s1, String s2)
    {
        if (listener != null)
        {
            listener.onError(i, s, s1, s2);
        }
    }

    public void onSuccess(String s, String s1)
    {
        PushDiainfoManager.access$900(_fld0, token, yid, registeredRail, listener);
    }

    anagerListener()
    {
        this$1 = this._cls1.this;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/apps/transit/common/PushDiainfoManager$15

/* anonymous class */
    class PushDiainfoManager._cls15
        implements PushDiainfoManager.PushManagerListener
    {

        final PushDiainfoManager this$0;
        final PushDiainfoManager.PushManagerListener val$listener;
        final Bundle val$registeredRail;
        final BearerToken val$token;
        final String val$yid;

        public void onCanceled()
        {
            if (listener != null)
            {
                listener.onCanceled();
            }
        }

        public void onError(int i, String s, String s1, String s2)
        {
            if (listener != null)
            {
                listener.onError(i, s, s1, s2);
            }
        }

        public void onSuccess(String s, String s1)
        {
            if (!setRailPushForYid(token, new PushDiainfoManager._cls15._cls1()))
            {
                PushDiainfoManager.access$900(PushDiainfoManager.this, token, yid, registeredRail, listener);
            }
        }

            
            {
                this$0 = final_pushdiainfomanager;
                token = bearertoken;
                yid = s;
                registeredRail = bundle;
                listener = PushDiainfoManager.PushManagerListener.this;
                super();
            }
    }

}
