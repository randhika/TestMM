// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.DirectionSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.api.data.RailDirectionData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, TimeTableActivity

public class RailDirection extends TransitBaseActivity
{

    private boolean bSeqRaild;
    private LayoutInflater inflater;
    private int nReqCode;
    private RailDirectionData objResDirection;
    private StationData objResStation;
    private String sCpId;
    private String sRailId;
    private String sTitle;
    private StationData station;

    public RailDirection()
    {
        station = null;
        sRailId = null;
        sCpId = null;
        bSeqRaild = false;
        objResStation = null;
        objResDirection = null;
        nReqCode = 0;
        sTitle = "";
    }

    private void dispatchAd(int i)
    {
        Resources resources = getResources();
        if (i == resources.getInteger(0x7f0c0066))
        {
            sTitle = getString(0x7f0d0539);
            dispAd(this, "2080126926", "pv,bottom");
        } else
        if (i == resources.getInteger(0x7f0c0065))
        {
            sTitle = getString(0x7f0d0539);
            dispAd(this, "2080126926", "pv,bottom");
        } else
        if (i == resources.getInteger(0x7f0c0062))
        {
            sTitle = getString(0x7f0d050d);
            dispAd(this, "2080126926", "pv,bottom");
        } else
        if (i == resources.getInteger(0x7f0c0063))
        {
            sTitle = getString(0x7f0d050d);
            dispAd(this, "2080126926", "pv,bottom");
        } else
        if (i == resources.getInteger(0x7f0c003f))
        {
            sTitle = getString(0x7f0d00f7);
            dispAd(this, "2080078822", "pv");
        } else
        {
            sTitle = getString(0x7f0d0539);
            dispAd(this, "2080126926", "pv,bottom");
        }
        setTitle(sTitle);
    }

    private void launch()
    {
        if (nReqCode == 0 || nReqCode == getResources().getInteger(0x7f0c003f))
        {
            launchReturn();
            return;
        } else
        {
            launchNext();
            return;
        }
    }

    private void launchNext()
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/TimeTableActivity);
        intent.putExtra(getString(0x7f0d023e), objResStation);
        intent.putExtra(getString(0x7f0d01b3), objResDirection);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0065));
    }

    private void launchReturn()
    {
        Intent intent = new Intent();
        intent.putExtra(getString(0x7f0d01b3), objResDirection);
        intent.putExtra(getString(0x7f0d023e), objResStation);
        setResult(-1, intent);
        finish();
    }

    private void showList(Bundle bundle)
    {
        if (bundle != null && bundle.size() >= 1)
        {
            LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a02a3);
            linearlayout.setOrientation(1);
            int i = getResources().getDimensionPixelSize(0x7f0b003e);
            StationData stationdata = (StationData)bundle.getSerializable(String.valueOf(0));
            ((TextView)findViewById(0x7f0a02a2)).setText((new StringBuilder()).append(stationdata.getName()).append(getString(0x7f0d0530)).toString());
            int j = 0;
            int k = 0;
            while (k < bundle.size()) 
            {
                StationData stationdata1 = (StationData)bundle.getSerializable(String.valueOf(k));
                ArrayList arraylist;
                if (bSeqRaild)
                {
                    arraylist = stationdata1.getTagertDirection();
                } else
                {
                    arraylist = stationdata1.getRailDirection();
                }
                if (arraylist != null && arraylist.size() >= 1)
                {
                    j++;
                    LinearLayout linearlayout1 = new LinearLayout(this);
                    linearlayout1.setOrientation(1);
                    TextView textview = new TextView(this);
                    textview.setBackgroundColor(getResources().getColor(0x7f090036));
                    textview.setText(stationdata1.getDiainfo().getRailName());
                    textview.setPadding(i, i, i, i);
                    textview.setTextAppearance(this, 0x7f0e0016);
                    linearlayout1.addView(textview);
                    linearlayout.addView(linearlayout1);
                    Iterator iterator = arraylist.iterator();
                    while (iterator.hasNext()) 
                    {
                        RailDirectionData raildirectiondata = (RailDirectionData)iterator.next();
                        TextView textview1 = (TextView)inflater.inflate(0x7f030077, null);
                        textview1.setText((new StringBuilder()).append(raildirectiondata.getDirection()).append(getString(0x7f0d0512)).toString());
                        textview1.setTag(0x7f0d01b3, raildirectiondata);
                        textview1.setTag(0x7f0d023e, stationdata1);
                        android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

                            final RailDirection this$0;

                            public void onClick(View view)
                            {
                                objResStation = (StationData)view.getTag(0x7f0d023e);
                                objResDirection = (RailDirectionData)view.getTag(0x7f0d01b3);
                                launch();
                            }

            
            {
                this$0 = RailDirection.this;
                super();
            }
                        };
                        textview1.setOnClickListener(onclicklistener);
                        ImageView imageview = (ImageView)inflater.inflate(0x7f030059, null);
                        linearlayout1.addView(textview1);
                        linearlayout1.addView(imageview);
                    }
                }
                k++;
            }
            if (j == 0)
            {
                showErrorMessageDialog(getString(0x7f0d0130), getString(0x7f0d014f));
                return;
            }
        }
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        while (-1 == j || getResources().getInteger(0x7f0c0065) != i || intent == null || !intent.hasExtra(getResources().getString(0x7f0d01b9))) 
        {
            return;
        }
        showErrorMessageDialog(intent.getStringExtra(getResources().getString(0x7f0d01b9)), getString(0x7f0d014f));
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03008e);
        inflater = (LayoutInflater)getSystemService("layout_inflater");
        Intent intent = getIntent();
        DirectionSearch directionsearch;
        if ("android.intent.action.VIEW".equals(intent.getAction()))
        {
            Uri uri = getIntent().getData();
            if (uri == null)
            {
                showErrorMessageDialog(getString(0x7f0d0106), getString(0x7f0d014f));
                return;
            }
            String s = uri.getQueryParameter(getString(0x7f0d023f));
            station = new StationData();
            station.setId(s);
            sRailId = uri.getQueryParameter(getString(0x7f0d01d8));
            sCpId = uri.getQueryParameter(getString(0x7f0d01a8));
            bSeqRaild = true;
        } else
        {
            station = (StationData)intent.getSerializableExtra(getString(0x7f0d023e));
            sRailId = intent.getStringExtra(getString(0x7f0d01d9));
            sCpId = intent.getStringExtra(getString(0x7f0d01a8));
            nReqCode = intent.getIntExtra(getString(0x7f0d01df), 0);
        }
        directionsearch = new DirectionSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final RailDirection this$0;

            public boolean onCanceled()
            {
                return false;
            }

            public boolean onError(APIError apierror)
            {
                showErrorMessageDialog(getString(0x7f0d0106), getString(0x7f0d014f));
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                Bundle bundle1 = ((DirectionSearch)apibase).getResults();
                if (bundle1 == null || bundle1.size() < 1)
                {
                    showErrorMessageDialog(getString(0x7f0d0130), getString(0x7f0d014f));
                } else
                {
                    showList(bundle1);
                }
                return false;
            }

            
            {
                this$0 = RailDirection.this;
                super();
            }
        });
        directionsearch.setCode(station.getId());
        directionsearch.request();
        dispatchAd(nReqCode);
    }



/*
    static StationData access$102(RailDirection raildirection, StationData stationdata)
    {
        raildirection.objResStation = stationdata;
        return stationdata;
    }

*/


/*
    static RailDirectionData access$202(RailDirection raildirection, RailDirectionData raildirectiondata)
    {
        raildirection.objResDirection = raildirectiondata;
        return raildirectiondata;
    }

*/

}
