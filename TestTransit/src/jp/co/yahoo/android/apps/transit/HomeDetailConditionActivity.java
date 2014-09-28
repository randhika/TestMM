// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

public class HomeDetailConditionActivity extends TransitBaseActivity
{

    private CheckBox chkSave;
    private ConditionData conditions;
    private Intent returnIntent;
    private Spinner seatSpnr;
    private Spinner ticketSpnr;

    public HomeDetailConditionActivity()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03001f);
        setTitle(getString(0x7f0d00d1));
        returnIntent = new Intent();
        setResult(0, returnIntent);
        conditions = (ConditionData)getIntent().getSerializableExtra(getString(0x7f0d022c));
        chkSave = (CheckBox)findViewById(0x7f0a00b7);
        if (conditions.ticket == null)
        {
            conditions.ticket = getString(0x7f0d0581);
        }
        int i;
        int j;
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        String as[];
        ArrayAdapter arrayadapter;
        String as1[];
        ArrayAdapter arrayadapter1;
        if (conditions.ticket.equals(getString(0x7f0d0582)))
        {
            i = 0;
        } else
        {
            i = 1;
        }
        j = conditions.seatKind;
        flag = conditions.superExpress;
        flag1 = conditions.express;
        flag2 = conditions.airplane;
        flag3 = conditions.highwayBus;
        flag4 = conditions.localBus;
        flag5 = conditions.ferry;
        ((CompoundButton)findViewById(0x7f0a00ae)).setChecked(flag);
        ((CompoundButton)findViewById(0x7f0a00ac)).setChecked(flag1);
        ((CompoundButton)findViewById(0x7f0a00b0)).setChecked(flag2);
        ((CompoundButton)findViewById(0x7f0a00b2)).setChecked(flag3);
        ((CompoundButton)findViewById(0x7f0a00b4)).setChecked(flag4);
        ((CompoundButton)findViewById(0x7f0a00b6)).setChecked(flag5);
        as = new String[2];
        as[0] = getString(0x7f0d02be);
        as[1] = getString(0x7f0d02c0);
        ticketSpnr = (Spinner)findViewById(0x7f0a00a6);
        arrayadapter = new ArrayAdapter(this, 0x1090008, as);
        arrayadapter.setDropDownViewResource(0x7f030077);
        ticketSpnr.setAdapter(arrayadapter);
        ticketSpnr.setSelection(i);
        ticketSpnr.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            final HomeDetailConditionActivity this$0;

            public void onItemSelected(AdapterView adapterview, View view, int k, long l)
            {
                switch (k)
                {
                default:
                    conditions.ticket = getString(0x7f0d0582);
                    return;

                case 1: // '\001'
                    conditions.ticket = getString(0x7f0d0583);
                    break;
                }
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            
            {
                this$0 = HomeDetailConditionActivity.this;
                super();
            }
        });
        as1 = new String[3];
        as1[0] = getString(0x7f0d02f4);
        as1[1] = getString(0x7f0d02f6);
        as1[2] = getString(0x7f0d02f5);
        seatSpnr = (Spinner)findViewById(0x7f0a00a8);
        arrayadapter1 = new ArrayAdapter(this, 0x1090008, as1);
        arrayadapter1.setDropDownViewResource(0x7f030077);
        seatSpnr.setAdapter(arrayadapter1);
        seatSpnr.setSelection(j - 1);
        seatSpnr.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            final HomeDetailConditionActivity this$0;

            public void onItemSelected(AdapterView adapterview, View view, int k, long l)
            {
                conditions.seatKind = k + 1;
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            
            {
                this$0 = HomeDetailConditionActivity.this;
                super();
            }
        });
        dispAd(this, "2080302616", "Z");
    }

    public void saveAndBack(View view)
    {
        conditions.superExpress = ((CompoundButton)findViewById(0x7f0a00ae)).isChecked();
        conditions.express = ((CompoundButton)findViewById(0x7f0a00ac)).isChecked();
        conditions.airplane = ((CompoundButton)findViewById(0x7f0a00b0)).isChecked();
        conditions.highwayBus = ((CompoundButton)findViewById(0x7f0a00b2)).isChecked();
        conditions.localBus = ((CompoundButton)findViewById(0x7f0a00b4)).isChecked();
        conditions.ferry = ((CompoundButton)findViewById(0x7f0a00b6)).isChecked();
        if (chkSave.isChecked())
        {
            ConditionData conditiondata = new ConditionData();
            conditiondata.superExpress = conditions.superExpress;
            conditiondata.express = conditions.express;
            conditiondata.airplane = conditions.airplane;
            conditiondata.highwayBus = conditions.highwayBus;
            conditiondata.localBus = conditions.localBus;
            conditiondata.ferry = conditions.ferry;
            conditiondata.seatKind = conditions.seatKind;
            conditiondata.ticket = conditions.ticket;
            (new SaveCondition(this)).setCond(conditiondata, true, false, true, true);
        }
        returnIntent.putExtra(getString(0x7f0d022c), conditions);
        setResult(-1, returnIntent);
        finish();
    }

}
