// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.GridView;
import android.widget.TextView;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.common.util.FileUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.timer.api.data.SkinMetaData;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingAlbumDetailActivity

class val.activity
    implements jp.co.yahoo.android.apps.transit.common.er
{

    final SettingAlbumDetailActivity this$0;
    final Activity val$activity;

    public boolean doInBackground()
    {
        long l = System.currentTimeMillis();
        jp.co.yahoo.android.apps.transit.common.util.tivity tivity = FileUtil.getDirectoryImageData(val$activity, true, SettingAlbumDetailActivity.access$000(SettingAlbumDetailActivity.this).sDownloadUrl);
        SettingAlbumDetailActivity.access$102(SettingAlbumDetailActivity.this, tivity.);
        SettingAlbumDetailActivity.access$202(SettingAlbumDetailActivity.this, tivity.);
        float f = TransitUtil.dpToPx(val$activity, 10F);
        float f1 = TransitUtil.dpToPx(val$activity, 100F);
        float f2 = (float)TransitUtil.getWindowDisplayWidth(val$activity) - f;
        int i = (int)(f2 / (f + f1));
        if (i < 3)
        {
            i = 3;
        }
        SettingAlbumDetailActivity.access$302(SettingAlbumDetailActivity.this, (int)(0.5F + (f2 / (float)i - f)));
        long l1 = System.currentTimeMillis();
        long l2 = 0L;
        if (l1 - l < 500L)
        {
            l2 = 500L - (l1 - l);
        }
        if (l2 > 0L)
        {
            try
            {
                Thread.sleep(l2);
            }
            catch (InterruptedException interruptedexception) { }
        }
        return true;
    }

    public void onPostExecute()
    {
        if (SettingAlbumDetailActivity.access$200(SettingAlbumDetailActivity.this) == null || SettingAlbumDetailActivity.access$200(SettingAlbumDetailActivity.this).isEmpty())
        {
            SettingAlbumDetailActivity.access$400(SettingAlbumDetailActivity.this).setVisibility(0);
        } else
        {
            SettingAlbumDetailActivity.access$502(SettingAlbumDetailActivity.this, new bumListAdapter(SettingAlbumDetailActivity.this, val$activity.getApplicationContext(), SettingAlbumDetailActivity.access$100(SettingAlbumDetailActivity.this)));
            SettingAlbumDetailActivity.access$600(SettingAlbumDetailActivity.this).setColumnWidth(SettingAlbumDetailActivity.access$300(SettingAlbumDetailActivity.this));
            SettingAlbumDetailActivity.access$600(SettingAlbumDetailActivity.this).setAdapter(SettingAlbumDetailActivity.access$500(SettingAlbumDetailActivity.this));
            SettingAlbumDetailActivity.access$600(SettingAlbumDetailActivity.this).setOnItemClickListener(SettingAlbumDetailActivity.this);
        }
        if (SettingAlbumDetailActivity.access$700(SettingAlbumDetailActivity.this) == null || !SettingAlbumDetailActivity.access$700(SettingAlbumDetailActivity.this).isShowing())
        {
            break MISSING_BLOCK_LABEL_67;
        }
        SettingAlbumDetailActivity.access$700(SettingAlbumDetailActivity.this).dismiss();
        return;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
    }

    bumListAdapter()
    {
        this$0 = final_settingalbumdetailactivity;
        val$activity = Activity.this;
        super();
    }
}
