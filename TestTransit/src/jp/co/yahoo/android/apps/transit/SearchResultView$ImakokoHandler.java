// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.Calendar;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultView

private static class refView extends Handler
{

    private final WeakReference refView;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 0 0: default 24
    //                   0 25;
           goto _L1 _L2
_L1:
        SearchResultView searchresultview;
        return;
_L2:
        if ((searchresultview = (SearchResultView)refView.get()) != null)
        {
            Calendar calendar = Calendar.getInstance();
            int i = calendar.get(13);
            int j = calendar.get(14);
            int k = -1;
            if (i == 0)
            {
                k = 60000 - j;
            } else
            if (i > 58)
            {
                k = 61000 - j;
            } else
            if (i < 2)
            {
                k = 59000 - j;
            }
            if (k != -1)
            {
                if (searchresultview.setImakokoAnimation(false))
                {
                    SearchResultView.access$000(searchresultview).sendMessageDelayed(obtainMessage(), k);
                    return;
                } else
                {
                    SearchResultView.access$000(searchresultview).removeMessages(0);
                    return;
                }
            } else
            {
                SearchResultView.access$000(searchresultview).sendMessageDelayed(obtainMessage(), 60000 - i * 1000 - j);
                return;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public (SearchResultView searchresultview)
    {
        refView = new WeakReference(searchresultview);
    }
}
