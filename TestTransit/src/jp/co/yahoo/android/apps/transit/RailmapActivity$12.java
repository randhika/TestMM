// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.widget.CheckBox;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapController;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            RailmapActivity

class this._cls0
    implements android.content.lickListener
{

    final RailmapActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        String as[] = getResources().getStringArray(0x7f070006);
        String as1[] = getResources().getStringArray(0x7f070007);
        int j = TransitUtil.getLatLngInt(as[i]);
        int k = TransitUtil.getLatLngInt(as1[i]);
        RailmapActivity.access$1500(RailmapActivity.this).setCenter(new GeoPoint(j, k));
        if (RailmapActivity.access$700(RailmapActivity.this).isChecked())
        {
            search(as[i], as1[i], 2);
        }
        if (RailmapActivity.access$800(RailmapActivity.this).isChecked())
        {
            search(as[i], as1[i], 1);
        }
    }

    Util()
    {
        this$0 = RailmapActivity.this;
        super();
    }
}
