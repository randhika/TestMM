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

    final YHBGSearchActivity this$0;
    private final CharSequence val$s;

    public void run()
    {
        final boolean isEmpty;
        if (val$s.length() == 0)
        {
            isEmpty = true;
        } else
        {
            isEmpty = false;
        }
        YHBGSearchActivity.access$8(YHBGSearchActivity.this).post(new Runnable() {

            final YHBGSearchActivity._cls4 this$1;
            private final boolean val$isEmpty;
            private final CharSequence val$s;

            public void run()
            {
                if (isEmpty != YHBGSearchActivity.access$9(this$0))
                {
                    YHBGSearchActivity.access$10(this$0).changeCursor(null);
                }
                YHBGSearchActivity.access$11(this$0, isEmpty);
                if (isEmpty)
                {
                    YHBGSearchActivity.access$12(this$0).setVisibility(4);
                } else
                {
                    YHBGSearchActivity.access$12(this$0).setVisibility(0);
                }
                YHBGSearchActivity.access$13(this$0).setAdapter(YHBGSearchActivity.access$10(this$0));
                YHBGSearchActivity.access$10(this$0).setFilter(s);
            }

            
            {
                this$1 = YHBGSearchActivity._cls4.this;
                isEmpty = flag;
                s = charsequence;
                super();
            }
        });
    }


    _cls1.val.s()
    {
        this$0 = final_yhbgsearchactivity;
        val$s = CharSequence.this;
        super();
    }
}
