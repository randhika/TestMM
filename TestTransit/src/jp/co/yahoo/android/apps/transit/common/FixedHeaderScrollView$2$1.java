// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;


// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            FixedHeaderScrollView

class this._cls1
    implements Runnable
{

    final ScrollView.invalidate this$1;

    public void run()
    {
        FixedHeaderScrollView.access$100(_fld0).invalidate();
    }

    is._cls0()
    {
        this$1 = this._cls1.this;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/apps/transit/common/FixedHeaderScrollView$2

/* anonymous class */
    class FixedHeaderScrollView._cls2
        implements FixedHeaderScrollView.HeaderLinearLayout.OnHeaderDrawListener
    {

        final FixedHeaderScrollView this$0;

        public void onHeaderDraw()
        {
            post(new FixedHeaderScrollView._cls2._cls1());
        }

            
            {
                this$0 = FixedHeaderScrollView.this;
                super();
            }
    }

}
