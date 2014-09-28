// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import jp.co.yahoo.android.apps.transit.common.DragAndDropLayout;
import jp.co.yahoo.android.apps.transit.timer.common.CountdownPanelManager;
import jp.co.yahoo.android.apps.transit.timer.common.SkinDrawableManager;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class this._cls1
    implements Runnable
{

    final is._cls0 this$1;

    public void run()
    {
        if (CountdownActivity.access$1400(_fld0).getVisibility() == 4)
        {
            SharedPreferences sharedpreferences = getSharedPreferences(getString(0x7f0d01a0), 0);
            int i = sharedpreferences.getInt(getString(0x7f0d01b7), 0xff676981);
            int j = sharedpreferences.getInt(getString(0x7f0d01b8), 0xff676981);
            if (i != 0xff676981 && j != 0xff676981)
            {
                CountdownActivity.access$1500(_fld0, i, j);
            }
            CountdownActivity.access$1400(_fld0).setVisibility(0);
        }
    }

    is._cls0()
    {
        this$1 = this._cls1.this;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/apps/transit/timer/CountdownActivity$11

/* anonymous class */
    class CountdownActivity._cls11
        implements android.view.View.OnClickListener
    {

        final CountdownActivity this$0;

        public void onClick(View view)
        {
            if (CountdownActivity.access$800(CountdownActivity.this).getVisibility() == 8)
            {
                getSupportActionBar().show();
                CountdownActivity.access$1000(CountdownActivity.this).setLock(true);
                CountdownActivity.access$1000(CountdownActivity.this).setStart(true);
                CountdownActivity.access$800(CountdownActivity.this).startAnimation(CountdownActivity.access$1100(CountdownActivity.this));
                CountdownActivity.access$800(CountdownActivity.this).setVisibility(0);
                if (CountdownActivity.access$000(CountdownActivity.this) != null)
                {
                    CountdownActivity.access$000(CountdownActivity.this).startAnimation(CountdownActivity.access$1100(CountdownActivity.this));
                    CountdownActivity.access$000(CountdownActivity.this).setVisibility(0);
                }
                CountdownActivity.access$900(CountdownActivity.this).startAnimation(CountdownActivity.access$1200(CountdownActivity.this));
            } else
            if (CountdownActivity.access$800(CountdownActivity.this).getVisibility() == 0)
            {
                getSupportActionBar().hide();
                CountdownActivity.access$1000(CountdownActivity.this).setLock(false);
                CountdownActivity.access$1000(CountdownActivity.this).setStart(false);
                CountdownActivity.access$800(CountdownActivity.this).startAnimation(CountdownActivity.access$1300(CountdownActivity.this));
                if (CountdownActivity.access$000(CountdownActivity.this) != null)
                {
                    CountdownActivity.access$000(CountdownActivity.this).startAnimation(CountdownActivity.access$1300(CountdownActivity.this));
                }
                CountdownActivity.access$900(CountdownActivity.this).startAnimation(CountdownActivity.access$1100(CountdownActivity.this));
                CountdownActivity.access$900(CountdownActivity.this).setVisibility(0);
                CountdownActivity.access$900(CountdownActivity.this).post(new CountdownActivity._cls11._cls1());
                CountdownActivity.access$300(CountdownActivity.this).updateSkinDragViewItem();
                return;
            }
        }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
    }

}
