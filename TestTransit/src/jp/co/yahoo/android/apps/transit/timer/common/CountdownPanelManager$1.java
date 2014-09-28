// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.common:
//            CountdownPanelManager

class this._cls0 extends android.support.v4.view.angeListener
{

    final CountdownPanelManager this$0;

    public void onPageSelected(int i)
    {
        nCurrentIndex = i;
        objCurrentTime = (TimeTableItemData)objNowTimetable.getSerializable(Integer.toString(nCurrentIndex));
        if (listener != null)
        {
            listener.updateTarget(objCurrentTime);
        }
    }

    wnListener()
    {
        this$0 = CountdownPanelManager.this;
        super();
    }
}
