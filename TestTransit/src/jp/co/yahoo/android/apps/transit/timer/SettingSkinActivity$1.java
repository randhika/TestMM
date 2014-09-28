// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import java.io.File;
import jp.co.yahoo.android.apps.transit.timer.api.FileDownloader;
import jp.co.yahoo.android.apps.transit.timer.api.data.SkinMetaData;
import jp.co.yahoo.android.apps.transit.timer.db.SkinDb;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingSkinActivity

class this._cls0 extends Handler
{

    final SettingSkinActivity this$0;

    public void handleMessage(Message message)
    {
        super.handleMessage(message);
        if (SettingSkinActivity.access$000(SettingSkinActivity.this).isErorr())
        {
            SettingSkinActivity.access$100(SettingSkinActivity.this).dismiss();
            if (SettingSkinActivity.access$200(SettingSkinActivity.this) != null)
            {
                String s2 = (new StringBuilder()).append(SettingSkinActivity.access$200(SettingSkinActivity.this).sPath).append("/").append(getString(0x7f0d04ea)).append("/data.zip").toString();
                try
                {
                    CountdownUtil.delete(new File(s2));
                }
                catch (Exception exception2)
                {
                    exception2.printStackTrace();
                }
            }
            showErrorMessageDialog(getString(0x7f0d04ec), getString(0x7f0d015e));
        } else
        {
            if (SettingSkinActivity.access$000(SettingSkinActivity.this).isCancelled())
            {
                SettingSkinActivity.access$100(SettingSkinActivity.this).dismiss();
                return;
            }
            if (SettingSkinActivity.access$000(SettingSkinActivity.this).getStatus() == android.os.HED)
            {
                SettingSkinActivity.access$100(SettingSkinActivity.this).dismiss();
                if (SettingSkinActivity.access$200(SettingSkinActivity.this) != null)
                {
                    String s = (new StringBuilder()).append(SettingSkinActivity.access$200(SettingSkinActivity.this).sPath).append("/").append(getString(0x7f0d04ea)).toString();
                    String s1 = CountdownUtil.extract((new StringBuilder()).append(s).append("/").append("data.zip").toString(), (new StringBuilder()).append(s).append("/").toString(), true);
                    try
                    {
                        CountdownUtil.delete(new File((new StringBuilder()).append(s).append("/").append("data.zip").toString()));
                    }
                    catch (Exception exception)
                    {
                        exception.printStackTrace();
                    }
                    if (s1 == null)
                    {
                        try
                        {
                            CountdownUtil.delete(new File(s));
                        }
                        catch (Exception exception1)
                        {
                            exception1.printStackTrace();
                        }
                        showErrorMessageDialog(getString(0x7f0d04ee), getString(0x7f0d015e));
                        return;
                    } else
                    {
                        SettingSkinActivity.access$200(SettingSkinActivity.this).isDownloaded = true;
                        SettingSkinActivity.access$300(SettingSkinActivity.this).updateSkin(SettingSkinActivity.access$200(SettingSkinActivity.this));
                        SettingSkinActivity.access$300(SettingSkinActivity.this).updateSetting(SettingSkinActivity.access$200(SettingSkinActivity.this));
                        SettingSkinActivity.access$202(SettingSkinActivity.this, null);
                        SettingSkinActivity.access$400(SettingSkinActivity.this);
                        return;
                    }
                }
            } else
            {
                int i = SettingSkinActivity.access$000(SettingSkinActivity.this).getLoadedBytePercent();
                SettingSkinActivity.access$100(SettingSkinActivity.this).setProgress(i);
                SettingSkinActivity.access$500(SettingSkinActivity.this).sendEmptyMessageDelayed(0, 100L);
                return;
            }
        }
    }

    ()
    {
        this$0 = SettingSkinActivity.this;
        super();
    }
}
