// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.DiainfoSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoBaseActivity, DiainfoRailList

public class DiainfoAreaListActivity extends DiainfoBaseActivity
{

    private DiainfoSearch objSearch;
    private String sType;

    public DiainfoAreaListActivity()
    {
    }

    protected void launchDiainfoRailList(DiainfoData diainfodata)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/DiainfoRailList);
        intent.putExtra(getString(0x7f0d01d6), diainfodata.getRailAreaCode());
        intent.putExtra(getString(0x7f0d01d7), diainfodata.getRailAreaName());
        intent.putExtra(getString(0x7f0d01da), sType);
        intent.putExtra(getString(0x7f0d01af), isRegist);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0042));
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (j == -1 && i == getResources().getInteger(0x7f0c0042) && isRegist)
        {
            setResult(-1);
            finish();
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03001a);
        setTitle(getString(0x7f0d00dc));
        sType = getIntent().getStringExtra(getString(0x7f0d01da));
        objSearch = new DiainfoSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final DiainfoAreaListActivity this$0;

            public boolean onCanceled()
            {
                return false;
            }

            public boolean onError(APIError apierror)
            {
                showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                showAreaList(objSearch.getResult());
                return false;
            }

            
            {
                this$0 = DiainfoAreaListActivity.this;
                super();
            }
        });
        objSearch.setMethod("check");
        objSearch.request();
        dispAd(this, "2080124767", "pv");
    }

    public void onStop()
    {
        super.onStop();
        if (objSearch != null)
        {
            objSearch.stopRequest();
        }
    }

    protected void showAreaList(Bundle bundle)
    {
        LayoutInflater layoutinflater = (LayoutInflater)getSystemService("layout_inflater");
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a0071);
        int i = 0;
        while (i < bundle.size()) 
        {
            DiainfoData diainfodata = (DiainfoData)bundle.getSerializable(String.valueOf(i));
            if (!TransitUtil.isEmpty(diainfodata.getRailAreaCode()))
            {
                View view = layoutinflater.inflate(0x7f030070, null);
                ImageView imageview = (ImageView)layoutinflater.inflate(0x7f030059, null);
                imageview.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, 2));
                linearlayout.addView(view);
                linearlayout.addView(imageview);
                ((TextView)view.findViewById(0x7f0a021d)).setText(diainfodata.getRailAreaName());
                if (diainfodata.isCondition() && !isRegist)
                {
                    ((ImageView)view.findViewById(0x7f0a021e)).setVisibility(0);
                }
                view.setClickable(true);
                view.setTag(diainfodata);
                view.setOnClickListener(new android.view.View.OnClickListener() {

                    final DiainfoAreaListActivity this$0;

                    public void onClick(View view1)
                    {
                        launchDiainfoRailList((DiainfoData)view1.getTag());
                    }

            
            {
                this$0 = DiainfoAreaListActivity.this;
                super();
            }
                });
            }
            i++;
        }
    }

}
