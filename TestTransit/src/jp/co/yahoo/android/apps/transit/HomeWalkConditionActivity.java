// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;
import jp.co.yahoo.android.apps.transit.viewparts.DeepRadioGroup;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

public class HomeWalkConditionActivity extends TransitBaseActivity
{

    private CheckBox chkSave;
    private ConditionData conditions;
    private Intent returnIntent;
    private DeepRadioGroup rgWalk;
    final CharSequence wsItems[] = new CharSequence[0];

    public HomeWalkConditionActivity()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030021);
        setTitle(getString(0x7f0d0590));
        returnIntent = new Intent();
        setResult(0, returnIntent);
        conditions = (ConditionData)getIntent().getSerializableExtra(getString(0x7f0d022c));
        chkSave = (CheckBox)findViewById(0x7f0a00b7);
        rgWalk = (DeepRadioGroup)findViewById(0x7f0a00c7);
        conditions.walkSpeed;
        JVM INSTR tableswitch 1 4: default 124
    //                   1 143
    //                   2 155
    //                   3 167
    //                   4 179;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        rgWalk.check(0x7f0a0000);
_L7:
        dispAd(this, "2080302615", "Z");
        return;
_L2:
        rgWalk.check(0x7f0a00c9);
        continue; /* Loop/switch isn't completed */
_L3:
        rgWalk.check(0x7f0a0000);
        continue; /* Loop/switch isn't completed */
_L4:
        rgWalk.check(0x7f0a00cc);
        continue; /* Loop/switch isn't completed */
_L5:
        rgWalk.check(0x7f0a00ce);
        if (true) goto _L7; else goto _L6
_L6:
    }

    public void saveAndBack(View view)
    {
        rgWalk.getCheckedRadioButtonId();
        JVM INSTR lookupswitch 4: default 48
    //                   2131361792: 144
    //                   2131361993: 133
    //                   2131361996: 155
    //                   2131361998: 166;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        conditions.walkSpeed = 2;
_L7:
        if (chkSave.isChecked())
        {
            ConditionData conditiondata = new ConditionData();
            conditiondata.walkSpeed = conditions.walkSpeed;
            (new SaveCondition(this)).setCond(conditiondata, false, true, false, false);
        }
        returnIntent.putExtra(getString(0x7f0d022c), conditions);
        setResult(-1, returnIntent);
        finish();
        return;
_L3:
        conditions.walkSpeed = 1;
        continue; /* Loop/switch isn't completed */
_L2:
        conditions.walkSpeed = 2;
        continue; /* Loop/switch isn't completed */
_L4:
        conditions.walkSpeed = 3;
        continue; /* Loop/switch isn't completed */
_L5:
        conditions.walkSpeed = 4;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public void selectItem(View view)
    {
        switch (Integer.parseInt((String)view.getTag()))
        {
        default:
            return;

        case 1: // '\001'
            rgWalk.check(0x7f0a00c9);
            return;

        case 2: // '\002'
            rgWalk.check(0x7f0a0000);
            return;

        case 3: // '\003'
            rgWalk.check(0x7f0a00cc);
            return;

        case 4: // '\004'
            rgWalk.check(0x7f0a00ce);
            break;
        }
    }
}
