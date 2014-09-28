// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.viewparts.MemoListView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            MyPageActivity

class this._cls0
    implements android.view.er
{

    final MyPageActivity this$0;

    public void onClick(View view)
    {
        if (MyPageActivity.access$3000(MyPageActivity.this) != 257) goto _L2; else goto _L1
_L1:
        ArrayList arraylist;
        MemoListView memolistview2 = MyPageActivity.access$3100(MyPageActivity.this);
        arraylist = null;
        if (memolistview2 != null)
        {
            arraylist = MyPageActivity.access$3100(MyPageActivity.this).getCheckItems();
        }
_L4:
        if (arraylist == null || arraylist.size() < 1)
        {
            MyPageActivity mypageactivity = MyPageActivity.this;
            MyPageActivity mypageactivity1 = MyPageActivity.this;
            Object aobj[] = new Object[1];
            aobj[0] = MyPageActivity.access$4000(MyPageActivity.this);
            mypageactivity.showErrorMessageDialog(mypageactivity1.getString(0x7f0d012c, aobj), getString(0x7f0d0150));
            return;
        }
        break; /* Loop/switch isn't completed */
_L2:
        int i = MyPageActivity.access$800(MyPageActivity.this).getVisibility();
        arraylist = null;
        if (i != 0)
        {
            switch (MyPageActivity.access$1900(MyPageActivity.this))
            {
            default:
                return;

            case 0: // '\0'
                arraylist = new ArrayList();
                if (MyPageActivity.access$3200(MyPageActivity.this).getVisibility() == 0 && MyPageActivity.access$3300(MyPageActivity.this) != null)
                {
                    arraylist.addAll(MyPageActivity.access$3300(MyPageActivity.this).getCheckItems());
                }
                if (MyPageActivity.access$3400(MyPageActivity.this).getVisibility() == 0 && MyPageActivity.access$3500(MyPageActivity.this) != null)
                {
                    arraylist.addAll(MyPageActivity.access$3500(MyPageActivity.this).getCheckItems());
                }
                if (MyPageActivity.access$3600(MyPageActivity.this).getVisibility() == 0 && MyPageActivity.access$3700(MyPageActivity.this) != null)
                {
                    arraylist.addAll(MyPageActivity.access$3700(MyPageActivity.this).getCheckItems());
                }
                break;

            case 1: // '\001'
                MemoListView memolistview1 = MyPageActivity.access$3800(MyPageActivity.this);
                arraylist = null;
                if (memolistview1 != null)
                {
                    arraylist = MyPageActivity.access$3800(MyPageActivity.this).getCheckItems();
                }
                break;

            case 2: // '\002'
                MemoListView memolistview = MyPageActivity.access$3900(MyPageActivity.this);
                arraylist = null;
                if (memolistview != null)
                {
                    arraylist = MyPageActivity.access$3900(MyPageActivity.this).getCheckItems();
                }
                break;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (MyPageActivity.access$4100(MyPageActivity.this) != 0)
        {
            showdelMessageDialog(getString(0x7f0d00cc), false);
            return;
        } else
        {
            showdelMessageDialog(getString(0x7f0d00cc), true);
            return;
        }
    }

    View()
    {
        this$0 = MyPageActivity.this;
        super();
    }
}
