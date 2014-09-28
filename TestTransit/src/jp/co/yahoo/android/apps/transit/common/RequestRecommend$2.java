// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            RequestRecommend

class this._cls0
    implements android.content.lickListener
{

    final RequestRecommend this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        android.content.ditor ditor = RequestRecommend.access$000(RequestRecommend.this).edit();
        ditor.putInt("recommend_key", 0);
        ditor.putInt("ver_key", TransitUtil.getVersionCode(RequestRecommend.access$100(RequestRecommend.this)));
        ditor.commit();
        TransitUtil.touchRD(RequestRecommend.access$100(RequestRecommend.this).getString(0x7f0d0558));
    }

    ()
    {
        this$0 = RequestRecommend.this;
        super();
    }
}
