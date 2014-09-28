// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;
import jp.co.yahoo.android.apps.transit.common.SetSharedPreferences;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, Transit, OthersActivity

public class OhtersAddressListActivity extends TransitBaseActivity
{

    private String addressText[];
    private Context context;
    private String lat[];
    private String lon[];
    private int nReqCode;
    private int nSearchType;
    private Resources res;
    private String sGoal;

    public OhtersAddressListActivity()
    {
        nReqCode = 0;
        nSearchType = 0;
        sGoal = "";
    }

    private void addressDialog()
    {
        (new TransitDialogBuilder(this)).setTitleInfo(getString(0x7f0d0022)).setCancelable(true).setPositiveButton(getString(0x7f0d0020), new android.content.DialogInterface.OnClickListener() {

            final OhtersAddressListActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                launchSearchResult();
            }

            
            {
                this$0 = OhtersAddressListActivity.this;
                super();
            }
        }).setNegativeButton(getString(0x7f0d0072), new android.content.DialogInterface.OnClickListener() {

            final OhtersAddressListActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                Intent intent = new Intent(OhtersAddressListActivity.this, jp/co/yahoo/android/apps/transit/OthersActivity);
                startActivityForResult(intent, res.getInteger(0x7f0c004a));
                finish();
            }

            
            {
                this$0 = OhtersAddressListActivity.this;
                super();
            }
        }).setMessage(getString(0x7f0d0021)).show();
    }

    private void launchSearchResult()
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/Transit);
        ConditionData conditiondata = (new SaveCondition(this)).getCond();
        if (conditiondata == null)
        {
            conditiondata = new ConditionData();
        }
        conditiondata.searchType = nSearchType;
        conditiondata.goalName = sGoal;
        conditiondata.startName = getString(0x7f0d0289);
        intent.putExtra(getString(0x7f0d022c), conditiondata);
        startActivityForResult(intent, res.getInteger(0x7f0c004a));
        finish();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030027);
        context = this;
        res = getResources();
        setTitle(getString(0x7f0d0540));
        Intent intent = getIntent();
        nReqCode = getIntent().getIntExtra(getString(0x7f0d01df), 0);
        Bundle bundle1 = intent.getBundleExtra("address");
        ListView listview = (ListView)findViewById(0x7f0a0128);
        LayoutInflater layoutinflater = (LayoutInflater)getSystemService("layout_inflater");
        int i = bundle1.size();
        int j = 0;
        addressText = new String[i];
        lat = new String[i];
        lon = new String[i];
        for (; bundle1.containsKey(Integer.toString(j)); j++)
        {
            ((TextView)layoutinflater.inflate(0x7f030077, null)).setTextAppearance(this, 0x1030044);
            addressText[j] = bundle1.getBundle(Integer.toString(j)).getString("address");
            lat[j] = bundle1.getBundle(Integer.toString(j)).getString("lat");
            lon[j] = bundle1.getBundle(Integer.toString(j)).getString("lon");
            listview.setAdapter(new ArrayAdapter(this, 0x7f030077, addressText));
            listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                final OhtersAddressListActivity this$0;

                public void onItemClick(AdapterView adapterview, View view, int k, long l)
                {
                    setAddress(addressText[k], lat[k], lon[k]);
                    addressDialog();
                }

            
            {
                this$0 = OhtersAddressListActivity.this;
                super();
            }
            });
        }

        Toast.makeText(context, (new StringBuilder()).append(i).append("\u4EF6\u8A72\u5F53\u3057\u307E\u3057\u305F\u3002").toString(), 0).show();
        dispAd(this, "2080124753", "pv");
    }

    public void setAddress(String s, String s1, String s2)
    {
        HashMap hashmap = new HashMap();
        hashmap.put("address", s);
        hashmap.put("lon", s2);
        hashmap.put("lat", s1);
        SetSharedPreferences setsharedpreferences;
        if (nReqCode == getResources().getInteger(0x7f0c0052))
        {
            setsharedpreferences = new SetSharedPreferences(this, getString(0x7f0d00c5));
            nSearchType = 5;
            sGoal = getString(0x7f0d0338);
        } else
        if (nReqCode == getResources().getInteger(0x7f0c0051))
        {
            setsharedpreferences = new SetSharedPreferences(this, getString(0x7f0d00c4));
            nSearchType = 6;
            sGoal = getString(0x7f0d02a4);
        } else
        {
            setsharedpreferences = new SetSharedPreferences(this, getString(0x7f0d00c3));
            nSearchType = 4;
            sGoal = getString(0x7f0d028b);
        }
        setsharedpreferences.setSharedPreferenceData(hashmap);
    }






}
