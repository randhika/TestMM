// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import java.util.List;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.StationSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.PinOverlay;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            RailmapActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.
{

    final RailmapActivity this$0;

    public boolean onCanceled()
    {
        checkBoxEnable(true);
        return false;
    }

    public boolean onError(APIError apierror)
    {
        checkBoxEnable(true);
        showErrorMessageDialog(getString(0x7f0d010c), getString(0x7f0d014f));
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        checkBoxEnable(true);
        StationSearch stationsearch = (StationSearch)apibase;
        Bundle bundle = stationsearch.getResults();
        if (bundle == null)
        {
            return true;
        }
        closePopup(stationsearch.getMode());
        if (stationsearch.getMode() == 2)
        {
            int j;
            if (RailmapActivity.access$1200(RailmapActivity.this) == null)
            {
                RailmapActivity.access$1202(RailmapActivity.this, new PinOverlay(RailmapActivity.access$2900(RailmapActivity.this)));
                RailmapActivity.access$1200(RailmapActivity.this).setOnFocusChangeListener(RailmapActivity.access$2300(RailmapActivity.this));
                RailmapActivity.access$200(RailmapActivity.this).getOverlays().add(RailmapActivity.access$1200(RailmapActivity.this));
            } else
            {
                RailmapActivity.access$1200(RailmapActivity.this).clearPoint();
            }
            for (j = 0; j < bundle.size(); j++)
            {
                StationData stationdata1 = (StationData)bundle.getSerializable(String.valueOf(j));
                RailmapActivity.access$1200(RailmapActivity.this).addPoint(stationdata1.getGeopoint(), stationdata1.getName(), null, null, 0, stationdata1);
            }

        } else
        {
            int i;
            if (RailmapActivity.access$1100(RailmapActivity.this) == null)
            {
                RailmapActivity.access$1102(RailmapActivity.this, new PinOverlay(RailmapActivity.access$3000(RailmapActivity.this)));
                RailmapActivity.access$1100(RailmapActivity.this).setOnFocusChangeListener(RailmapActivity.access$2300(RailmapActivity.this));
                RailmapActivity.access$200(RailmapActivity.this).getOverlays().add(RailmapActivity.access$1100(RailmapActivity.this));
            } else
            {
                RailmapActivity.access$1100(RailmapActivity.this).clearPoint();
            }
            for (i = 0; i < bundle.size(); i++)
            {
                StationData stationdata = (StationData)bundle.getSerializable(String.valueOf(i));
                RailmapActivity.access$1100(RailmapActivity.this).addPoint(stationdata.getGeopoint(), stationdata.getName(), null, null, 0, stationdata);
            }

        }
        RailmapActivity.access$200(RailmapActivity.this).getOverlays().add(RailmapActivity.access$2300(RailmapActivity.this));
        return false;
    }

    a()
    {
        this$0 = RailmapActivity.this;
        super();
    }
}
