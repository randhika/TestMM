// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.viewparts.RidingPositionResultView;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

public class RidingPositionActivity extends TransitBaseActivity
{

    private RidingPositionActivity _this;
    private int dispWidth;
    private boolean isSpinner;
    private ArrayList ridingPosition;
    private int ridingPositionCar;
    private String ridingPositionName;
    private RidingPositionResultView ridingPositionTabView;
    private int ridingPositiondir;
    private TabHost tabs;

    public RidingPositionActivity()
    {
        _this = this;
    }

    private void setTab(ArrayList arraylist)
    {
        ((ImageView)findViewById(0x7f0a02b0)).setVisibility(0);
        for (int i = 0; i < arraylist.size(); i++)
        {
            String s = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionCar)arraylist.get(i)).numOfCar;
            View view = LayoutInflater.from(this).inflate(0x7f030097, null);
            if (i == 0)
            {
                view.setBackgroundResource(0x7f020273);
            }
            ((TextView)view.findViewById(0x7f0a02c0)).setText((new StringBuilder()).append(s).append("\u4E21").toString());
            android.widget.TabHost.TabSpec tabspec = tabs.newTabSpec(Integer.toString(i)).setIndicator(view).setContent(new android.widget.TabHost.TabContentFactory() {

                final RidingPositionActivity this$0;

                public View createTabContent(String s1)
                {
                    ridingPositionTabView = new RidingPositionResultView(_this);
                    ridingPositionTabView.setData(ridingPosition, ridingPositionName, ridingPositiondir, dispWidth);
                    ridingPositionTabView.setTabView(Integer.valueOf(s1).intValue());
                    return ridingPositionTabView;
                }

            
            {
                this$0 = RidingPositionActivity.this;
                super();
            }
            });
            tabs.addTab(tabspec);
        }

    }

    public void onCreate(Bundle bundle)
    {
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPosition ridingposition;
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionCar ridingpositioncar;
        super.onCreate(bundle);
        setContentView(0x7f030093);
        setTitle(getString(0x7f0d02db));
        tabs = (TabHost)findViewById(0x7f0a02af);
        tabs.setup();
        TextView textview = (TextView)findViewById(0x7f0a02ab);
        dispWidth = ((WindowManager)getSystemService("window")).getDefaultDisplay().getWidth();
        Intent intent = getIntent();
        ridingPosition = (ArrayList)intent.getSerializableExtra(getString(0x7f0d01f7));
        ridingPositionName = intent.getStringExtra(getString(0x7f0d01fa));
        ridingPositiondir = intent.getIntExtra(getString(0x7f0d01f9), 0);
        ridingPositionCar = intent.getIntExtra(getString(0x7f0d01f8), 0);
        ridingposition = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPosition)ridingPosition.get(ridingPositiondir);
        ridingpositioncar = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionCar)ridingposition.Cars.get(ridingPositionCar);
        textview.setText((new StringBuilder()).append(ridingPositionName.toString()).append(getString(0x7f0d0304)).toString());
        if (ridingposition.Cars.size() != 1) goto _L2; else goto _L1
_L1:
        tabs.setVisibility(8);
        TextView textview1 = (TextView)findViewById(0x7f0a02ae);
        String s = ridingpositioncar.numOfCar;
        textview1.setText((new StringBuilder()).append(s).append(getString(0x7f0d02a2)).toString());
        textview1.setVisibility(0);
        ridingPositionTabView = new RidingPositionResultView(this);
        ridingPositionTabView.setData(ridingPosition, ridingPositionName, ridingPositiondir, dispWidth);
        ridingPositionTabView.setTabView(0);
        ((FrameLayout)findViewById(0x7f0a02b1)).addView(ridingPositionTabView);
_L4:
        dispAd(this, "2080316112", "pv");
        return;
_L2:
        setTab(ridingposition.Cars);
        if (!isSpinner)
        {
            ((LinearLayout)findViewById(0x7f0a02ac)).setVisibility(8);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void showPictDescDialog(View view)
    {
        LinearLayout linearlayout = (LinearLayout)LayoutInflater.from(this).inflate(0x7f030096, null);
        (new TransitDialogBuilder(this)).setTitle(getString(0x7f0d02b0)).setView(linearlayout).setNegativeButton("\u9589\u3058\u308B", new android.content.DialogInterface.OnClickListener() {

            final RidingPositionActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.cancel();
            }

            
            {
                this$0 = RidingPositionActivity.this;
                super();
            }
        }).show();
    }



/*
    static RidingPositionResultView access$002(RidingPositionActivity ridingpositionactivity, RidingPositionResultView ridingpositionresultview)
    {
        ridingpositionactivity.ridingPositionTabView = ridingpositionresultview;
        return ridingpositionresultview;
    }

*/





}
