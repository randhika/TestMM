// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import java.net.URLEncoder;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoDetailActivity

class this._cls0
    implements android.view.nfoDetailActivity._cls1
{

    final DiainfoDetailActivity this$0;

    public void onClick(View view)
    {
        touchTapRD(getString(0x7f0d0435));
        try
        {
            String s = getString(0x7f0d0570);
            Object aobj[] = new Object[1];
            aobj[0] = URLEncoder.encode(diainfo.getRailName(), "UTF-8");
            String s1 = String.format(s, aobj);
            TransitUtil.openURL(DiainfoDetailActivity.this, s1);
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    ()
    {
        this$0 = DiainfoDetailActivity.this;
        super();
    }
}
