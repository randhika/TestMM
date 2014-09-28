// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import jp.co.yahoo.android.apps.transit.timer.db.SkinDb;
import jp.co.yahoo.android.yolp.common.ApiBase;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownBaseActivity

class this._cls0
    implements jp.co.yahoo.android.yolp.common.vity._cls1
{

    final CountdownBaseActivity this$0;

    public boolean endApi(ApiBase apibase, Object obj)
    {
        if (skindb.count() > 1)
        {
            supportInvalidateOptionsMenu();
        }
        return false;
    }

    ()
    {
        this$0 = CountdownBaseActivity.this;
        super();
    }
}
