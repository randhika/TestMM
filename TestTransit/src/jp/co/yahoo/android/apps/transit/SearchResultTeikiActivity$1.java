// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import android.widget.Button;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultTeikiActivity

class val.btnHighSchool
    implements android.view.sultTeikiActivity._cls1
{

    final SearchResultTeikiActivity this$0;
    final Button val$btnBussiness;
    final Button val$btnHighSchool;
    final Button val$btnUniversity;

    public void onClick(View view)
    {
        String s = (String)view.getTag();
        if (!TransitUtil.isEmpty(s)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (s.equals(getString(0x7f0d057a)))
        {
            touchTapRD(getString(0x7f0d0408));
            val$btnBussiness.setSelected(true);
            val$btnUniversity.setSelected(false);
            val$btnHighSchool.setSelected(false);
        } else
        {
            if (!s.equals(getString(0x7f0d057c)))
            {
                continue; /* Loop/switch isn't completed */
            }
            touchTapRD(getString(0x7f0d0407));
            val$btnBussiness.setSelected(false);
            val$btnUniversity.setSelected(true);
            val$btnHighSchool.setSelected(false);
        }
_L4:
        research((String)view.getTag());
        return;
        if (!s.equals(getString(0x7f0d057b))) goto _L1; else goto _L3
_L3:
        touchTapRD(getString(0x7f0d0406));
        val$btnBussiness.setSelected(false);
        val$btnUniversity.setSelected(false);
        val$btnHighSchool.setSelected(true);
          goto _L4
        if (true) goto _L1; else goto _L5
_L5:
    }

    ()
    {
        this$0 = final_searchresultteikiactivity;
        val$btnBussiness = button;
        val$btnUniversity = button1;
        val$btnHighSchool = Button.this;
        super();
    }
}
