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
//            DiainfoBaseActivity, DiainfoRailList, DiainfoDetailActivity

public class DiainfoAllCategory extends DiainfoBaseActivity
{

    private DiainfoSearch objSearch;

    public DiainfoAllCategory()
    {
    }

    protected void launchDiainfoRailList(DiainfoData diainfodata)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/DiainfoRailList);
        intent.putExtra(getString(0x7f0d01d6), diainfodata.getRailAreaCode());
        intent.putExtra(getString(0x7f0d01d7), diainfodata.getRailAreaName());
        intent.putExtra(getString(0x7f0d01af), isRegist);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0042));
    }

    protected void lounchDiainfoDetail(DiainfoData diainfodata)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/DiainfoDetailActivity);
        Bundle bundle = new Bundle();
        bundle.putString(getString(0x7f0d01d9), diainfodata.getRailCode());
        bundle.putString(getString(0x7f0d01a8), diainfodata.getCpId());
        intent.putExtra(getString(0x7f0d022c), bundle);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0040));
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
        setContentView(0x7f030019);
        setTitle(getString(0x7f0d00dc));
        objSearch = new DiainfoSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final DiainfoAllCategory this$0;

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
                showAllList(objSearch.getResult());
                return false;
            }

            
            {
                this$0 = DiainfoAllCategory.this;
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

    protected void showAllList(Bundle bundle)
    {
        LayoutInflater layoutinflater = (LayoutInflater)getSystemService("layout_inflater");
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a0071);
        LinearLayout linearlayout1 = (LinearLayout)findViewById(0x7f0a0072);
        int i = 0;
        while (i < bundle.size()) 
        {
            DiainfoData diainfodata = (DiainfoData)bundle.getSerializable(String.valueOf(i));
            View view = layoutinflater.inflate(0x7f030070, null);
            TextView textview = (TextView)view.findViewById(0x7f0a021d);
            ImageView imageview = (ImageView)layoutinflater.inflate(0x7f030059, null);
            imageview.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, 2));
            if (TransitUtil.isEmpty(diainfodata.getRailAreaCode()))
            {
                linearlayout1.addView(view);
                textview.setText(diainfodata.getRailName());
                view.setOnClickListener(new android.view.View.OnClickListener() {

                    final DiainfoAllCategory this$0;

                    public void onClick(View view1)
                    {
                        if (isRegist)
                        {
                            postRegist((DiainfoData)view1.getTag());
                            return;
                        } else
                        {
                            lounchDiainfoDetail((DiainfoData)view1.getTag());
                            return;
                        }
                    }

            
            {
                this$0 = DiainfoAllCategory.this;
                super();
            }
                });
                linearlayout1.addView(imageview);
            } else
            {
                linearlayout.addView(view);
                textview.setText(diainfodata.getRailAreaName());
                view.setOnClickListener(new android.view.View.OnClickListener() {

                    final DiainfoAllCategory this$0;

                    public void onClick(View view1)
                    {
                        launchDiainfoRailList((DiainfoData)view1.getTag());
                    }

            
            {
                this$0 = DiainfoAllCategory.this;
                super();
            }
                });
                linearlayout.addView(imageview);
            }
            if (diainfodata.isCondition() && !isRegist)
            {
                ((ImageView)view.findViewById(0x7f0a021e)).setVisibility(0);
            }
            view.setClickable(true);
            view.setTag(diainfodata);
            i++;
        }
    }

}
