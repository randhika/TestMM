// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.ListView;
import android.widget.TextView;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.common.util.FileUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingAlbumListActivity

class val.activity
    implements jp.co.yahoo.android.apps.transit.common.ener
{

    final SettingAlbumListActivity this$0;
    final Activity val$activity;

    public boolean doInBackground()
    {
        long l = System.currentTimeMillis();
        jp.co.yahoo.android.apps.transit.common.util.tivity tivity = FileUtil.getAllImageData(val$activity, true);
        SettingAlbumListActivity.access$002(SettingAlbumListActivity.this, tivity.);
        SettingAlbumListActivity.access$102(SettingAlbumListActivity.this, tivity.);
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
            catch (InterruptedException interruptedexception)
            {
                return true;
            }
        }
        return true;
    }

    public void onPostExecute()
    {
        if (SettingAlbumListActivity.access$100(SettingAlbumListActivity.this) == null || SettingAlbumListActivity.access$100(SettingAlbumListActivity.this).isEmpty())
        {
            SettingAlbumListActivity.access$200(SettingAlbumListActivity.this).setVisibility(0);
        } else
        {
            SettingAlbumListActivity.access$302(SettingAlbumListActivity.this, new bumListAdapter(SettingAlbumListActivity.this, val$activity.getApplicationContext(), SettingAlbumListActivity.access$000(SettingAlbumListActivity.this), SettingAlbumListActivity.access$100(SettingAlbumListActivity.this)));
            SettingAlbumListActivity.access$400(SettingAlbumListActivity.this).setAdapter(SettingAlbumListActivity.access$300(SettingAlbumListActivity.this));
            SettingAlbumListActivity.access$400(SettingAlbumListActivity.this).setOnItemClickListener(SettingAlbumListActivity.this);
        }
        if (SettingAlbumListActivity.access$500(SettingAlbumListActivity.this) == null || !SettingAlbumListActivity.access$500(SettingAlbumListActivity.this).isShowing())
        {
            break MISSING_BLOCK_LABEL_67;
        }
        SettingAlbumListActivity.access$500(SettingAlbumListActivity.this).dismiss();
        return;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
    }

    bumListAdapter()
    {
        this$0 = final_settingalbumlistactivity;
        val$activity = Activity.this;
        super();
    }
}
