// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;


// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class val.nSize
    implements jp.co.yahoo.android.apps.transit.timer.viewparts.gListener
{

    final CountdownActivity this$0;
    final int val$nSize;

    public void onCancel()
    {
    }

    public void onOk(String s)
    {
        settingFilter(s, val$nSize, false);
    }

    alog.DialogListener()
    {
        this$0 = final_countdownactivity;
        val$nSize = I.this;
        super();
    }
}
