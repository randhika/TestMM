// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.os.Handler;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            BackgroundWorker

static final class val.listener
    implements Runnable
{

    final rkerListener val$listener;

    public void run()
    {
        if (val$listener.doInBackground())
        {
            BackgroundWorker.access$000().post(new Runnable() {

                final BackgroundWorker._cls1 this$0;

                public void run()
                {
                    listener.onPostExecute();
                }

            
            {
                this$0 = BackgroundWorker._cls1.this;
                super();
            }
            });
        }
    }

    rkerListener(rkerListener rkerlistener)
    {
        val$listener = rkerlistener;
        super();
    }
}
