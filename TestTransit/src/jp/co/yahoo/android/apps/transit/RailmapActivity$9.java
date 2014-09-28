// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import jp.co.yahoo.android.apps.transit.api.LocationSearch;
import jp.co.yahoo.android.maps.MapView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            RailmapActivity

class this._cls0
    implements android.view.er
{

    final RailmapActivity this$0;

    public void onClick(View view)
    {
        if (view.isSelected())
        {
            view.setSelected(false);
            RailmapActivity.access$1300(RailmapActivity.this).disableMyLocation();
            RailmapActivity.access$200(RailmapActivity.this).invalidate();
            return;
        }
        if (!RailmapActivity.access$1400(RailmapActivity.this).isLocationEnabled())
        {
            showSimpleErrorMessageDialog(getString(0x7f0d054a));
            return;
        } else
        {
            view.setSelected(true);
            RailmapActivity.access$1300(RailmapActivity.this).enableMyLocation();
            RailmapActivity.access$200(RailmapActivity.this).invalidate();
            return;
        }
    }

    ()
    {
        this$0 = RailmapActivity.this;
        super();
    }
}
