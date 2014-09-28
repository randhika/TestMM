// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import jp.co.yahoo.android.yolp.common.ApiBase;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.common:
//            SkinMeta

class this._cls0
    implements jp.co.yahoo.android.yolp.common.Listener
{

    final SkinMeta this$0;

    public boolean endApi(ApiBase apibase, Object obj)
    {
        SkinMeta.access$000(SkinMeta.this);
        if (SkinMeta.access$100(SkinMeta.this) != null)
        {
            SkinMeta.access$100(SkinMeta.this).endApi(apibase, obj);
        }
        return false;
    }

    ()
    {
        this$0 = SkinMeta.this;
        super();
    }
}
