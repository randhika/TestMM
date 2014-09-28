// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.widget.Button;
import java.util.List;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.NaviRenderingSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapController;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.PolylineOverlay;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            RailmapActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.
{

    final RailmapActivity this$0;

    public boolean onCanceled()
    {
        return false;
    }

    public boolean onError(APIError apierror)
    {
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        if (RailmapActivity.access$200(RailmapActivity.this) == null)
        {
            return false;
        }
        if (RailmapActivity.access$2600(RailmapActivity.this) != null)
        {
            RailmapActivity.access$200(RailmapActivity.this).getOverlays().remove(RailmapActivity.access$2600(RailmapActivity.this));
        }
        GeoPoint ageopoint[] = ((NaviRenderingSearch)apibase).getRoute();
        RailmapActivity.access$2602(RailmapActivity.this, new PolylineOverlay(ageopoint));
        RailmapActivity.access$2600(RailmapActivity.this).setWidth(10F);
        RailmapActivity.access$200(RailmapActivity.this).getOverlays().add(RailmapActivity.access$2600(RailmapActivity.this));
        RailmapActivity.access$200(RailmapActivity.this).getOverlays().remove(RailmapActivity.access$2300(RailmapActivity.this));
        RailmapActivity.access$200(RailmapActivity.this).getOverlays().add(RailmapActivity.access$2300(RailmapActivity.this));
        RailmapActivity.access$1002(RailmapActivity.this, true);
        RailmapActivity.access$2700(RailmapActivity.this).setVisibility(8);
        RailmapActivity.access$2800(RailmapActivity.this).setVisibility(0);
        GeoPoint geopoint = ageopoint[0];
        GeoPoint geopoint1 = ageopoint[-1 + ageopoint.length];
        if (start == null)
        {
            start = new StationData();
            start.setName(RailmapActivity.access$2500(RailmapActivity.this).startName);
            start.setLat(TransitUtil.getLatLngString(geopoint.getLatitudeE6()));
            start.setLon(TransitUtil.getLatLngString(geopoint.getLongitudeE6()));
            start.setId(RailmapActivity.access$2500(RailmapActivity.this).startCode);
            RailmapActivity.access$2400(RailmapActivity.this, start, 1, true);
        }
        if (goal == null)
        {
            goal = new StationData();
            goal.setName(RailmapActivity.access$2500(RailmapActivity.this).goalName);
            goal.setLat(TransitUtil.getLatLngString(geopoint1.getLatitudeE6()));
            goal.setLon(TransitUtil.getLatLngString(geopoint1.getLongitudeE6()));
            goal.setId(RailmapActivity.access$2500(RailmapActivity.this).goalCode);
            RailmapActivity.access$2400(RailmapActivity.this, goal, 2, true);
        }
        GeoPoint geopoint2 = new GeoPoint((geopoint.getLatitudeE6() + geopoint1.getLatitudeE6()) / 2, (geopoint.getLongitudeE6() + geopoint1.getLongitudeE6()) / 2);
        int i = Math.abs(geopoint1.getLatitudeE6() - geopoint.getLatitudeE6());
        int j = Math.abs(geopoint1.getLongitudeE6() - geopoint.getLongitudeE6());
        RailmapActivity.access$1500(RailmapActivity.this).zoomToSpan(i, j);
        RailmapActivity.access$1500(RailmapActivity.this).setCenter(geopoint2);
        RailmapActivity.access$1500(RailmapActivity.this).zoomOut();
        return false;
    }

    Util()
    {
        this$0 = RailmapActivity.this;
        super();
    }
}
