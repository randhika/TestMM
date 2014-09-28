// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.api:
//            RevGeocoder, LocationSearch

public class location extends Thread
{

    private Location location;
    final LocationSearch this$0;

    private String getAddressFromLocation(Location location1)
    {
        JSONArray jsonarray;
        RevGeocoder revgeocoder = new RevGeocoder(LocationSearch.access$400(LocationSearch.this));
        revgeocoder.setLat(Double.toString(location1.getLatitude()));
        revgeocoder.setLon(Double.toString(location1.getLongitude()));
        revgeocoder.setDatum("wgs");
        revgeocoder.execute();
        jsonarray = revgeocoder.getFeature();
        if (jsonarray == null)
        {
            break MISSING_BLOCK_LABEL_79;
        }
        if (jsonarray.length() > 0)
        {
            return jsonarray.getJSONObject(0).getJSONObject("Property").getString("Address");
        }
        returnError();
        return "";
        JSONException jsonexception;
        jsonexception;
        returnError();
        return "";
    }

    private void returnError()
    {
        Bundle bundle = new Bundle();
        bundle.putInt(LocationSearch.access$100(LocationSearch.this, 0x7f0d01ce), 0x7f0c0077);
        bundle.putString(LocationSearch.access$100(LocationSearch.this, 0x7f0d01cf), LocationSearch.access$100(LocationSearch.this, 0x7f0d01d0));
        bundle.putString(LocationSearch.access$100(LocationSearch.this, 0x7f0d01b9), LocationSearch.access$100(LocationSearch.this, 0x7f0d00b2));
        bundle.putString(LocationSearch.access$100(LocationSearch.this, 0x7f0d01ba), LocationSearch.access$100(LocationSearch.this, 0x7f0d015e));
        Message message = LocationSearch.access$500(LocationSearch.this).obtainMessage();
        message.obj = bundle;
        LocationSearch.access$500(LocationSearch.this).sendMessage(message);
    }

    public void run()
    {
        String s = getAddressFromLocation(location);
        Bundle bundle = new Bundle();
        bundle.putInt(LocationSearch.access$100(LocationSearch.this, 0x7f0d01ce), 0x7f0c0078);
        bundle.putString(LocationSearch.access$100(LocationSearch.this, 0x7f0d01cf), LocationSearch.access$100(LocationSearch.this, 0x7f0d01d0));
        bundle.putString(LocationSearch.access$100(LocationSearch.this, 0x7f0d01a2), s);
        bundle.putString(LocationSearch.access$100(LocationSearch.this, 0x7f0d01a4), Double.toString(location.getLongitude()));
        bundle.putString(LocationSearch.access$100(LocationSearch.this, 0x7f0d01a3), Double.toString(location.getLatitude()));
        Message message = LocationSearch.access$500(LocationSearch.this).obtainMessage();
        message.obj = bundle;
        LocationSearch.access$500(LocationSearch.this).sendMessage(message);
    }

    public (Location location1)
    {
        this$0 = LocationSearch.this;
        super();
        location = location1;
    }
}
