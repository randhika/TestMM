// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.DiainfoSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.viewparts.DiainfoExpandableListAdapter;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoBaseActivity, DiainfoDetailActivity

public class DiainfoRailList extends DiainfoBaseActivity
{

    private DiainfoSearch objSearch;
    protected String sArea;
    protected String sType;

    public DiainfoRailList()
    {
    }

    protected void lounchDiainfoDetail(DiainfoData diainfodata)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/DiainfoDetailActivity);
        intent.putExtra(getString(0x7f0d01ad), diainfodata);
        Bundle bundle = new Bundle();
        bundle.putString(getString(0x7f0d01d9), diainfodata.getRailCode());
        bundle.putString(getString(0x7f0d01a8), diainfodata.getCpId());
        bundle.putString(getString(0x7f0d01dc), diainfodata.getRailRangeCode());
        intent.putExtra(getString(0x7f0d022c), bundle);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0040));
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03001c);
        Intent intent = getIntent();
        sType = getString(0x7f0d0573);
        sArea = intent.getStringExtra(getString(0x7f0d01d6));
        String s;
        if (sType.equals(getString(0x7f0d0572)))
        {
            s = (new StringBuilder()).append("").append(getString(0x7f0d00e3)).toString();
        } else
        {
            s = (new StringBuilder()).append("").append(intent.getStringExtra(getString(0x7f0d01d7))).toString();
        }
        setTitle(s);
        objSearch = new DiainfoSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final DiainfoRailList this$0;

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
                showList(objSearch.getResult());
                return false;
            }

            
            {
                this$0 = DiainfoRailList.this;
                super();
            }
        });
        objSearch.setDetail("full");
        objSearch.setArea(sArea);
        objSearch.setType(sType);
        objSearch.setDetailAnalyze(true);
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

    protected void showExpList(Bundle bundle)
    {
        Bundle bundle1 = bundle.getBundle(sType);
        if (bundle1 != null)
        {
            LayoutInflater layoutinflater = (LayoutInflater)getSystemService("layout_inflater");
            int i = 0;
            while (i < bundle1.size()) 
            {
                DiainfoData diainfodata = (DiainfoData)bundle1.getSerializable(String.valueOf(i));
                LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a0081);
                View view = layoutinflater.inflate(0x7f03006e, null);
                ((TextView)view.findViewById(0x7f0a021d)).setText(diainfodata.getRailName());
                if (diainfodata.isDetail() && !isRegist)
                {
                    view.findViewById(0x7f0a021e).setVisibility(0);
                }
                view.setTag(diainfodata);
                linearlayout.addView(view);
                view.setOnClickListener(new android.view.View.OnClickListener() {

                    final DiainfoRailList this$0;

                    public void onClick(View view1)
                    {
                        DiainfoData diainfodata1 = (DiainfoData)view1.getTag();
                        if (isRegist)
                        {
                            postRegist(diainfodata1);
                            return;
                        } else
                        {
                            lounchDiainfoDetail(diainfodata1);
                            return;
                        }
                    }

            
            {
                this$0 = DiainfoRailList.this;
                super();
            }
                });
                linearlayout.addView((ImageView)layoutinflater.inflate(0x7f030059, null));
                i++;
            }
        }
    }

    protected void showList(Bundle bundle)
    {
        if (bundle == null)
        {
            ((TextView)findViewById(0x7f0a0080)).setVisibility(0);
        } else
        if (bundle.getBundle(sType) != null)
        {
            if (sType.equals(getString(0x7f0d0572)))
            {
                showExpList(bundle);
                return;
            } else
            {
                showLocalList(bundle);
                return;
            }
        }
    }

    protected void showLocalList(Bundle bundle)
    {
        Bundle bundle1 = bundle.getBundle(sType).getBundle(sArea);
        if (bundle1 == null)
        {
            return;
        }
        Set set = bundle1.keySet();
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        Bundle bundle2;
        for (Iterator iterator = set.iterator(); iterator.hasNext(); arraylist1.add(bundle2))
        {
            bundle2 = bundle1.getBundle(((String)iterator.next()).toString());
            arraylist.add(((DiainfoData)bundle2.getSerializable("0")).getRailCompanyName());
        }

        ExpandableListView expandablelistview = new ExpandableListView(this);
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a0081);
        DiainfoExpandableListAdapter diainfoexpandablelistadapter = new DiainfoExpandableListAdapter(this, arraylist, arraylist1);
        boolean flag;
        if (!isRegist)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        diainfoexpandablelistadapter.setDiainfoIcon(flag);
        expandablelistview.setAdapter(diainfoexpandablelistadapter);
        linearlayout.addView(expandablelistview);
        expandablelistview.setOnChildClickListener(new android.widget.ExpandableListView.OnChildClickListener() {

            final DiainfoRailList this$0;

            public boolean onChildClick(ExpandableListView expandablelistview1, View view, int i, int j, long l)
            {
                DiainfoData diainfodata = (DiainfoData)((DiainfoExpandableListAdapter)expandablelistview1.getExpandableListAdapter()).getChild(i, j);
                if (isRegist)
                {
                    postRegist(diainfodata);
                } else
                {
                    lounchDiainfoDetail(diainfodata);
                }
                return false;
            }

            
            {
                this$0 = DiainfoRailList.this;
                super();
            }
        });
    }

}
