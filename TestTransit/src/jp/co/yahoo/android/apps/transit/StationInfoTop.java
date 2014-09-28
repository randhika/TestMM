// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import jp.co.yahoo.android.apps.transit.api.data.StationData;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitStationSearchActivity, StationInfo

public class StationInfoTop extends TransitStationSearchActivity
{

    public StationInfoTop()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0300ab);
        setTitle(getString(0x7f0d050d));
        setReqCode(getResources().getInteger(0x7f0c0064));
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a0322);
        LinearLayout linearlayout1 = (LinearLayout)findViewById(0x7f0a0323);
        LinearLayout linearlayout2 = (LinearLayout)findViewById(0x7f0a0325);
        linearlayout.setOnClickListener(new android.view.View.OnClickListener() {

            final StationInfoTop this$0;

            public void onClick(View view)
            {
                getAroundStation();
            }

            
            {
                this$0 = StationInfoTop.this;
                super();
            }
        });
        linearlayout1.setOnClickListener(new android.view.View.OnClickListener() {

            final StationInfoTop this$0;

            public void onClick(View view)
            {
                getKeySearchStation();
            }

            
            {
                this$0 = StationInfoTop.this;
                super();
            }
        });
        linearlayout2.setOnClickListener(new android.view.View.OnClickListener() {

            final StationInfoTop this$0;

            public void onClick(View view)
            {
                getResistStation();
            }

            
            {
                this$0 = StationInfoTop.this;
                super();
            }
        });
        dispAd(this, "2080124760", "pv,bottom");
    }

    protected void onSuccessStation(StationData stationdata)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/StationInfo);
        intent.putExtra(getString(0x7f0d023e), stationdata);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0062));
    }
}
