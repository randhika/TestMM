// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.RevGeocoder;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.maps.GeoPoint;

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
        showErrorMessageDialog(getString(0x7f0d0117), getString(0x7f0d014f));
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        Bundle bundle = RailmapActivity.access$3100(RailmapActivity.this).getResults();
        if (bundle == null)
        {
            showErrorMessageDialog(getString(0x7f0d0117), getString(0x7f0d014f));
        } else
        if (RailmapActivity.access$300(RailmapActivity.this) != null)
        {
            String as[] = bundle.getStringArray(getString(0x7f0d01a2));
            if (as.length > 0 && !TransitUtil.isEmpty(as[0]))
            {
                String s = as[0];
                StationData stationdata = new StationData();
                stationdata.setName(s);
                stationdata.setAddress(s);
                stationdata.setLat(TransitUtil.getLatLngString(RailmapActivity.access$300(RailmapActivity.this).getLatitudeE6()));
                stationdata.setLon(TransitUtil.getLatLngString(RailmapActivity.access$300(RailmapActivity.this).getLongitudeE6()));
                stationdata.setType(3);
                stationdata.setnNaviType(128);
                addAddressPin(stationdata);
                return false;
            }
        }
        return false;
    }

    a()
    {
        this$0 = RailmapActivity.this;
        super();
    }
}
