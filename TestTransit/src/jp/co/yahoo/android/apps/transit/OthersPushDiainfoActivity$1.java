// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import android.widget.AdapterView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            OthersPushDiainfoActivity

class this._cls0
    implements android.widget.stener
{

    final OthersPushDiainfoActivity this$0;

    public void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        OthersPushDiainfoActivity.access$002(OthersPushDiainfoActivity.this, i);
        if (OthersPushDiainfoActivity.access$100(OthersPushDiainfoActivity.this))
        {
            OthersPushDiainfoActivity.access$102(OthersPushDiainfoActivity.this, false);
            return;
        } else
        {
            OthersPushDiainfoActivity.access$200(OthersPushDiainfoActivity.this, true);
            return;
        }
    }

    public void onNothingSelected(AdapterView adapterview)
    {
    }

    ()
    {
        this$0 = OthersPushDiainfoActivity.this;
        super();
    }
}
