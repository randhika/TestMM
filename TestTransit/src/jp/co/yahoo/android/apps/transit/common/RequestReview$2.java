// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            RequestReview

class this._cls0
    implements android.content.OnClickListener
{

    final RequestReview this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        android.content.s.Editor editor = RequestReview.access$000(RequestReview.this).edit();
        editor.putInt("review_key", 2);
        editor.putInt("ver_key", TransitUtil.getVersionCode(RequestReview.access$100(RequestReview.this)));
        editor.commit();
        TransitUtil.touchRD(RequestReview.access$100(RequestReview.this).getString(0x7f0d0557));
    }

    ()
    {
        this$0 = RequestReview.this;
        super();
    }
}
