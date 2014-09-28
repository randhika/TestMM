// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ListView;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YHBGSearchActivity

class val.s
    implements Runnable
{

    final val.s this$1;
    private final boolean val$isEmpty;
    private final CharSequence val$s;

    public void run()
    {
        if (val$isEmpty != YHBGSearchActivity.access$9(_fld0))
        {
            YHBGSearchActivity.access$10(_fld0).changeCursor(null);
        }
        YHBGSearchActivity.access$11(_fld0, val$isEmpty);
        if (val$isEmpty)
        {
            YHBGSearchActivity.access$12(_fld0).setVisibility(4);
        } else
        {
            YHBGSearchActivity.access$12(_fld0).setVisibility(0);
        }
        YHBGSearchActivity.access$13(_fld0).setAdapter(YHBGSearchActivity.access$10(_fld0));
        YHBGSearchActivity.access$10(_fld0).setFilter(val$s);
    }

    l.s()
    {
        this$1 = final_s1;
        val$isEmpty = flag;
        val$s = CharSequence.this;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/common/hamburger/YHBGSearchActivity$4

/* anonymous class */
    class YHBGSearchActivity._cls4
        implements Runnable
    {

        final YHBGSearchActivity this$0;
        private final CharSequence val$s;

        public void run()
        {
            final boolean isEmpty;
            if (s.length() == 0)
            {
                isEmpty = true;
            } else
            {
                isEmpty = false;
            }
            YHBGSearchActivity.access$8(YHBGSearchActivity.this).post(s. new YHBGSearchActivity._cls4._cls1());
        }


            
            {
                this$0 = final_yhbgsearchactivity;
                s = CharSequence.this;
                super();
            }
    }

}
