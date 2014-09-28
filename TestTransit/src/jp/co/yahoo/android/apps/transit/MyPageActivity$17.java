// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            MyPageActivity, OthersAdressSearchActivity

class val.nReqId
    implements android.content.ClickListener
{

    final MyPageActivity this$0;
    final int val$nReqId;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        Intent intent = new Intent(MyPageActivity.this, jp/co/yahoo/android/apps/transit/OthersAdressSearchActivity);
        intent.putExtra(getString(0x7f0d01df), val$nReqId);
        startActivityForResult(intent, getResources().getInteger(0x7f0c004f));
    }

    Activity()
    {
        this$0 = final_mypageactivity;
        val$nReqId = I.this;
        super();
    }
}
