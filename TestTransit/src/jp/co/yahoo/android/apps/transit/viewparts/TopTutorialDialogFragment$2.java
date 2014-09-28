// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.widget.ImageView;
import java.util.ArrayList;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            TopTutorialDialogFragment

class val.indicatorList extends android.support.v4.view.Listener
{

    final TopTutorialDialogFragment this$0;
    final int val$imageList[];
    final ArrayList val$indicatorList;
    final ImageView val$leftArrow;
    final ImageView val$rightArrow;

    public void onPageSelected(int i)
    {
        super.onPageSelected(i);
        if (val$imageList.length > 1)
        {
            int j;
            if (i == 0)
            {
                val$leftArrow.setEnabled(false);
            } else
            {
                val$leftArrow.setEnabled(true);
            }
            if (i == -1 + val$imageList.length)
            {
                val$rightArrow.setEnabled(false);
            } else
            {
                val$rightArrow.setEnabled(true);
            }
            j = 0;
            while (j < val$indicatorList.size()) 
            {
                ImageView imageview = (ImageView)val$indicatorList.get(j);
                if (j == i)
                {
                    imageview.setSelected(true);
                } else
                {
                    imageview.setSelected(false);
                }
                j++;
            }
        }
    }

    ()
    {
        this$0 = final_toptutorialdialogfragment;
        val$imageList = ai;
        val$leftArrow = imageview;
        val$rightArrow = imageview1;
        val$indicatorList = ArrayList.this;
        super();
    }
}
