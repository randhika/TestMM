// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            PushDiainfoManager

class val.offDiainfo
    implements trarListener
{

    final PushDiainfoManager this$0;
    final boolean val$allFlag;
    final shManagerListener val$listener;
    final ArrayList val$offDiainfo;
    final ArrayList val$onDiainfo;
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
            PushDiainfoManager.access$400(PushDiainfoManager.this, val$token, val$yid, val$allFlag, val$onDiainfo, val$offDiainfo, val$listener);
            return;
        }
    }

    shManagerListener()
    {
        this$0 = final_pushdiainfomanager;
        val$listener = shmanagerlistener;
        val$token = bearertoken;
        val$yid = s;
        val$allFlag = flag;
        val$onDiainfo = arraylist;
        val$offDiainfo = ArrayList.this;
        super();
    }
}
