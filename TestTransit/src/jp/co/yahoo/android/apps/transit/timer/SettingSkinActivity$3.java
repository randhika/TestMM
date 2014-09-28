// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.common.ThumbnailManager;
import jp.co.yahoo.android.apps.transit.common.util.FileUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.timer.api.data.SkinMetaData;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingSkinActivity

class val.albumDescSub
    implements jp.co.yahoo.android.apps.transit.common.rListener
{

    final SettingSkinActivity this$0;
    final Activity val$activity;
    final TextView val$albumDescSub;
    final SkinMetaData val$albumSkin;
    final ImageView val$albumThumbnail;

    public boolean doInBackground()
    {
        jp.co.yahoo.android.apps.transit.common.util.ener ener = FileUtil.getDirectoryImageData(val$activity, true, val$albumSkin.sDownloadUrl);
        SettingSkinActivity.access$602(SettingSkinActivity.this, ener.tPath);
        return true;
    }

    public void onPostExecute()
    {
        ArrayList arraylist = SettingSkinActivity.access$600(SettingSkinActivity.this);
        int i = 0;
        if (arraylist != null)
        {
            boolean flag = SettingSkinActivity.access$600(SettingSkinActivity.this).isEmpty();
            i = 0;
            if (!flag)
            {
                i = SettingSkinActivity.access$600(SettingSkinActivity.this).size();
                File file = new File((String)SettingSkinActivity.access$600(SettingSkinActivity.this).get(0));
                android.graphics.Bitmap bitmap = BitmapFactory.decodeFile(val$albumSkin.sThumbnailPath);
                ThumbnailManager thumbnailmanager = ThumbnailManager.getInstance();
                thumbnailmanager.setImageSize((int)TransitUtil.dpToPx(val$activity, 100F));
                thumbnailmanager.loadImage(file, val$albumThumbnail, bitmap, 0);
            }
        }
        val$albumDescSub.setText((new StringBuilder()).append(i).append(val$activity.getString(0x7f0d04e6)).toString());
    }

    ta()
    {
        this$0 = final_settingskinactivity;
        val$activity = activity1;
        val$albumSkin = skinmetadata;
        val$albumThumbnail = imageview;
        val$albumDescSub = TextView.this;
        super();
    }
}
