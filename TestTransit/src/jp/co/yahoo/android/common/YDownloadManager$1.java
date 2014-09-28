// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package jp.co.yahoo.android.common:
//            YDownloadManager, YDownloadItem, YDownloadListener

class this._cls0 extends Handler
{

    final YDownloadManager this$0;

    public void handleMessage(Message message)
    {
        YDownloadItem ydownloaditem = (YDownloadItem)message.obj;
        ydownloaditem.getListener().onCancelled(ydownloaditem);
    }

    ()
    {
        this$0 = YDownloadManager.this;
        super();
    }
}
