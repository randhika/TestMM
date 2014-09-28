// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;


// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            SectionListView

class val.offset
    implements Runnable
{

    final SectionListView this$0;
    final int val$offset;
    final int val$position;

    public void run()
    {
        if (android.os.INT >= 11)
        {
            smoothScrollToPositionFromTop(val$position, val$offset);
            return;
        } else
        {
            setSelectionFromTop(val$position, val$offset);
            return;
        }
    }

    ()
    {
        this$0 = final_sectionlistview;
        val$position = i;
        val$offset = I.this;
        super();
    }
}
