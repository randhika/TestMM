// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingStartDetailActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.timer.viewparts.
{

    final SettingStartDetailActivity this$0;

    public void onCancel()
    {
    }

    public void onOk(int i)
    {
        SettingStartDetailActivity.access$000(SettingStartDetailActivity.this).setAlermVolume(i);
        SettingStartDetailActivity.access$800(SettingStartDetailActivity.this).setText((new StringBuilder()).append(Integer.toString(i)).append(getString(0x7f0d02af)).toString());
    }

    istener()
    {
        this$0 = SettingStartDetailActivity.this;
        super();
    }
}
