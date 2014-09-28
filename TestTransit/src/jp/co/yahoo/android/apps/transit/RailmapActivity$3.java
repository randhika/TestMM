// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.MotionEvent;
import android.widget.CheckBox;
import java.util.List;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.PinOverlay;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            RailmapActivity

class old_y
    implements jp.co.yahoo.android.maps.stener
{

    private float old_x;
    private float old_y;
    final RailmapActivity this$0;

    public boolean onLongPress(MapView mapview, Object obj, PinOverlay pinoverlay, GeoPoint geopoint)
    {
        RailmapActivity.access$200(RailmapActivity.this).getOverlays().remove(pinoverlay);
        RailmapActivity.access$302(RailmapActivity.this, geopoint);
        RailmapActivity.access$400(RailmapActivity.this, TransitUtil.getLatLngString(RailmapActivity.access$300(RailmapActivity.this).getLatitudeE6()), TransitUtil.getLatLngString(RailmapActivity.access$300(RailmapActivity.this).getLongitudeE6()));
        return false;
    }

    public boolean onPinchIn(MapView mapview)
    {
        return false;
    }

    public boolean onPinchOut(MapView mapview)
    {
        return false;
    }

    public boolean onTouch(MapView mapview, MotionEvent motionevent)
    {
        if (motionevent.getAction() == 1)
        {
            if (!RailmapActivity.access$500(RailmapActivity.this) && (RailmapActivity.access$600(RailmapActivity.this) == 0 || RailmapActivity.access$600(RailmapActivity.this) == 1))
            {
                double d = Math.sqrt(Math.pow(motionevent.getX() - old_x, 2D) + Math.pow(motionevent.getY() - old_y, 2D));
                if ((double)(RailmapActivity.access$200(RailmapActivity.this).getWidth() / 2) < d && (RailmapActivity.access$700(RailmapActivity.this).isChecked() || RailmapActivity.access$800(RailmapActivity.this).isChecked()))
                {
                    RailmapActivity.access$900(RailmapActivity.this, true);
                    return false;
                }
            }
        } else
        if (motionevent.getAction() == 0)
        {
            old_x = motionevent.getX();
            old_y = motionevent.getY();
            return false;
        }
        return false;
    }

    tUtil()
    {
        this$0 = RailmapActivity.this;
        super();
        old_x = 0.0F;
        old_y = 0.0F;
    }
}
