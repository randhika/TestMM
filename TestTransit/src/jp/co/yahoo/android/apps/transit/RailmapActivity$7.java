// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.widget.CompoundButton;
import jp.co.yahoo.android.maps.PinOverlay;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            RailmapActivity

class this._cls0
    implements android.widget.heckedChangeListener
{

    final RailmapActivity this$0;

    public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        if (flag)
        {
            search(1);
            return;
        }
        if (RailmapActivity.access$1100(RailmapActivity.this) != null)
        {
            RailmapActivity.access$1100(RailmapActivity.this).clearPoint();
        }
        closePopup(1);
        RailmapActivity.access$900(RailmapActivity.this, false);
    }

    er()
    {
        this$0 = RailmapActivity.this;
        super();
    }
}
