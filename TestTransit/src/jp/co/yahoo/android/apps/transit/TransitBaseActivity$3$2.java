// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Iterator;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.common.hamburger.YHBGRd;
import jp.co.yahoo.android.common.hamburger.YHBGRecommendApps;
import jp.co.yahoo.android.common.hamburger.YHBGUtils;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class ambergerEntry
    implements android.view.sitBaseActivity._cls3._cls2
{

    final ambergerEntry.mPackage this$1;
    final jp.co.yahoo.android.common.hamburger.rgerEntry val$entry;

    public void onClick(View view)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(val$entry.mUrl));
        if (YHBGUtils.startActivity(_fld0, intent))
        {
            android.content.Context context = getApplicationContext();
            String as[] = new String[2];
            as[0] = "store";
            as[1] = val$entry.mPackage;
            YHBGRd.sendAsync(context, as);
        }
    }

    ambergerEntry()
    {
        this$1 = final_ambergerentry;
        val$entry = jp.co.yahoo.android.common.hamburger.rgerEntry.this;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/apps/transit/TransitBaseActivity$3

/* anonymous class */
    class TransitBaseActivity._cls3
        implements android.os.Handler.Callback
    {

        final TransitBaseActivity this$0;

        public boolean handleMessage(Message message)
        {
            if (message.what != 1) goto _L2; else goto _L1
_L1:
            Iterator iterator;
            boolean flag;
            iterator = YHBGRecommendApps.getHambergerEntries(getApplicationContext());
            flag = false;
            LinearLayout linearlayout;
            LayoutInflater layoutinflater;
            linearlayout = (LinearLayout)findViewById(0x7f0a0295);
            linearlayout.setVisibility(0);
            layoutinflater = LayoutInflater.from(TransitBaseActivity.this);
            int i = 0;
_L9:
            if (!iterator.hasNext()) goto _L4; else goto _L3
_L3:
            final jp.co.yahoo.android.common.hamburger.YHBGRecommendApps.YHambergerEntry entry;
            LinearLayout linearlayout1;
            entry = (jp.co.yahoo.android.common.hamburger.YHBGRecommendApps.YHambergerEntry)iterator.next();
            if (entry.mName.equals("\u3082\u3063\u3068\u898B\u308B"))
            {
                continue; /* Loop/switch isn't completed */
            }
            linearlayout1 = (LinearLayout)layoutinflater.inflate(0x7f030072, linearlayout, false);
            ImageView imageview = (ImageView)linearlayout1.findViewById(0x7f0a0221);
            int j = (int)TransitUtil.dpToPx(TransitBaseActivity.this, 32F);
            android.view.ViewGroup.LayoutParams layoutparams = imageview.getLayoutParams();
            layoutparams.width = j;
            layoutparams.height = j;
            imageview.setLayoutParams(layoutparams);
            imageview.setImageBitmap(entry.mBitmap);
            ((TextView)linearlayout1.findViewById(0x7f0a0222)).setText(entry.mName);
            if (YHBGUtils.getApplicationInfo(getApplicationContext(), entry.mPackage) == null) goto _L6; else goto _L5
_L5:
            android.view.View.OnClickListener onclicklistener = new TransitBaseActivity._cls3._cls1();
            linearlayout1.setOnClickListener(onclicklistener);
_L7:
            int k = i + 1;
            linearlayout.addView(linearlayout1, i);
            flag = true;
            ImageView imageview1 = (ImageView)layoutinflater.inflate(0x7f030059, null);
            i = k + 1;
            try
            {
                linearlayout.addView(imageview1, k);
                continue; /* Loop/switch isn't completed */
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
_L4:
            if (!flag)
            {
                findViewById(0x7f0a0295).setVisibility(8);
                return false;
            }
            break; /* Loop/switch isn't completed */
_L6:
            TransitBaseActivity._cls3._cls2 _lcls2 = entry. new TransitBaseActivity._cls3._cls2();
            linearlayout1.setOnClickListener(_lcls2);
            if (true) goto _L7; else goto _L2
_L2:
            return false;
            if (true) goto _L9; else goto _L8
_L8:
        }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }

        // Unreferenced inner class jp/co/yahoo/android/apps/transit/TransitBaseActivity$3$1

/* anonymous class */
        class TransitBaseActivity._cls3._cls1
            implements android.view.View.OnClickListener
        {

            final TransitBaseActivity._cls3 this$1;
            final jp.co.yahoo.android.common.hamburger.YHBGRecommendApps.YHambergerEntry val$entry;

            public void onClick(View view)
            {
                Intent intent = getPackageManager().getLaunchIntentForPackage(entry.mPackage);
                if (YHBGUtils.startActivity(this$0, intent))
                {
                    android.content.Context context = getApplicationContext();
                    String as[] = new String[2];
                    as[0] = "kick";
                    as[1] = entry.mPackage;
                    YHBGRd.sendAsync(context, as);
                }
            }

                    
                    {
                        this$1 = TransitBaseActivity._cls3.this;
                        entry = yhambergerentry;
                        super();
                    }
        }

    }

}
