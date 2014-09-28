// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.app.Activity;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.common.util.FileUtil;
import jp.co.yahoo.android.apps.transit.timer.api.data.SkinMetaData;
import jp.co.yahoo.android.apps.transit.timer.common.SkinDrawableManager;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class val.activity
    implements jp.co.yahoo.android.apps.transit.common.erListener
{

    final CountdownActivity this$0;
    final Activity val$activity;

    public boolean doInBackground()
    {
        jp.co.yahoo.android.apps.transit.common.util.tener tener = FileUtil.getDirectoryImageData(val$activity, true, CountdownActivity.access$1600(CountdownActivity.this).sDownloadUrl);
        if (tener.stPath != null && !tener.stPath.isEmpty())
        {
            CountdownActivity.access$1000(CountdownActivity.this).setSkinAlbumData(tener.stPath);
        }
        return true;
    }

    public void onPostExecute()
    {
        if (CountdownActivity.access$1600(CountdownActivity.this).nCount > 0)
        {
            CountdownActivity.access$1000(CountdownActivity.this).startTimer();
        }
    }

    nager()
    {
        this$0 = final_countdownactivity;
        val$activity = Activity.this;
        super();
    }
}
