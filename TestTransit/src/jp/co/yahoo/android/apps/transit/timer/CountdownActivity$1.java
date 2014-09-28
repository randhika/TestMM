// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;


// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class this._cls0
    implements Runnable
{

    final CountdownActivity this$0;

    public void run()
    {
        changeActivityToggle();
    }

    ()
    {
        this$0 = CountdownActivity.this;
        super();
    }
}
