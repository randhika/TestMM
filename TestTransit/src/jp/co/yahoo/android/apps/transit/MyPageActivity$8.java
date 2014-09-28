// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import jp.co.yahoo.android.apps.transit.db.ResultDB;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            MyPageActivity

class val.item
    implements android.content.nClickListener
{

    final MyPageActivity this$0;
    final Bundle val$item;
    final int val$positionOld;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (val$positionOld == i) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 1 2: default 32
    //                   1 101
    //                   2 114;
           goto _L3 _L4 _L5
_L3:
        String s = getString(0x7f0d048f);
_L7:
        String s1 = val$item.getString("id");
        MyPageActivity.access$1500(MyPageActivity.this).updateSearchMemoCategory(s1, s);
        MyPageActivity.access$400(MyPageActivity.this, MyPageActivity.access$1900(MyPageActivity.this));
        Toast.makeText(MyPageActivity.this, getString(0x7f0d048c), 1).show();
_L2:
        return;
_L4:
        s = getString(0x7f0d048d);
        continue; /* Loop/switch isn't completed */
_L5:
        s = getString(0x7f0d048e);
        if (true) goto _L7; else goto _L6
_L6:
    }

    ()
    {
        this$0 = final_mypageactivity;
        val$positionOld = i;
        val$item = Bundle.this;
        super();
    }
}
