// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.view.View;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            CampainBanner

class this._cls1
    implements android.view.anner.ReadCampainSetting._cls2
{

    final en this$1;

    public void onClick(View view)
    {
        if (gtitle != null && !gtitle.equals("") && gdesc != null && !gdesc.equals(""))
        {
            owDialog();
            return;
        } else
        {
            bannerClose(en);
            return;
        }
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
