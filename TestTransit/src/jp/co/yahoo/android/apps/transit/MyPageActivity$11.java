// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Collections;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.db.ResultDB;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            MyPageActivity

class this._cls0
    implements android.view.er
{

    final MyPageActivity this$0;

    public void onClick(View view)
    {
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0442)).append("/").append(getString(0x7f0d03db)).toString());
        LinearLayout linearlayout = (LinearLayout)view.getParent();
        Bundle bundle = (Bundle)linearlayout.getTag();
        String s = bundle.getString("id");
        ConditionData conditiondata = (ConditionData)bundle.getSerializable(getString(0x7f0d022c));
        String s1 = conditiondata.startName;
        String s2 = conditiondata.startLon;
        String s3 = conditiondata.startLat;
        String s4 = conditiondata.startCode;
        conditiondata.startName = conditiondata.goalName;
        conditiondata.startLon = conditiondata.goalLon;
        conditiondata.startLat = conditiondata.goalLat;
        conditiondata.startCode = conditiondata.goalCode;
        conditiondata.goalName = s1;
        conditiondata.goalLon = s2;
        conditiondata.goalLat = s3;
        conditiondata.goalCode = s4;
        if (conditiondata.viaName != null && conditiondata.viaName.size() > 0)
        {
            Collections.reverse(conditiondata.viaName);
            Collections.reverse(conditiondata.viaCode);
        }
        MyPageActivity.access$1500(MyPageActivity.this).updateMyrouteQuery(s, conditiondata);
        MyPageActivity.access$2500(MyPageActivity.this, conditiondata, linearlayout);
    }

    Data()
    {
        this$0 = MyPageActivity.this;
        super();
    }
}
