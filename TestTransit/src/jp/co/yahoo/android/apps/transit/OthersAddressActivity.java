// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.common.SetSharedPreferences;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, OthersAdressSearchActivity

public class OthersAddressActivity extends TransitBaseActivity
{

    public OthersAddressActivity()
    {
    }

    protected void launchSearchAddress(int i)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/OthersAdressSearchActivity);
        intent.putExtra(getString(0x7f0d01df), i);
        startActivityForResult(intent, i);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030026);
        setTitle(getString(0x7f0d02ac));
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a011f);
        LinearLayout linearlayout1 = (LinearLayout)findViewById(0x7f0a0122);
        LinearLayout linearlayout2 = (LinearLayout)findViewById(0x7f0a0125);
        if (!TransitUtil.isEmpty((new SetSharedPreferences(this, getString(0x7f0d00c3))).getStringSharedPreferenceData("address")))
        {
            ((TextView)linearlayout.findViewById(0x7f0a0121)).setText(getString(0x7f0d02a7));
        }
        if (!TransitUtil.isEmpty((new SetSharedPreferences(this, getString(0x7f0d00c5))).getStringSharedPreferenceData("address")))
        {
            ((TextView)linearlayout1.findViewById(0x7f0a0124)).setText(getString(0x7f0d02a9));
        }
        if (!TransitUtil.isEmpty((new SetSharedPreferences(this, getString(0x7f0d00c4))).getStringSharedPreferenceData("address")))
        {
            ((TextView)linearlayout2.findViewById(0x7f0a0127)).setText(getString(0x7f0d02a8));
        }
        linearlayout.setOnClickListener(new android.view.View.OnClickListener() {

            final OthersAddressActivity this$0;

            public void onClick(View view)
            {
                launchSearchAddress(getResources().getInteger(0x7f0c0050));
            }

            
            {
                this$0 = OthersAddressActivity.this;
                super();
            }
        });
        linearlayout1.setOnClickListener(new android.view.View.OnClickListener() {

            final OthersAddressActivity this$0;

            public void onClick(View view)
            {
                launchSearchAddress(getResources().getInteger(0x7f0c0052));
            }

            
            {
                this$0 = OthersAddressActivity.this;
                super();
            }
        });
        linearlayout2.setOnClickListener(new android.view.View.OnClickListener() {

            final OthersAddressActivity this$0;

            public void onClick(View view)
            {
                launchSearchAddress(getResources().getInteger(0x7f0c0051));
            }

            
            {
                this$0 = OthersAddressActivity.this;
                super();
            }
        });
        dispAd(this, "2080302611", "pv,bottom");
    }
}
