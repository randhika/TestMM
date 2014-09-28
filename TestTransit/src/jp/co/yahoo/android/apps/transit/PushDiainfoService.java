// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import jp.co.yahoo.android.apps.transit.common.PushDiainfoManager;

public class PushDiainfoService extends Service
{

    public static final String ACTION_ON_APP_START = "jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_APP_START";
    public static final String ACTION_ON_GET_RAIL = "jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_GET_RAIL";
    private volatile Handler handler;
    private Queue intentQueue;
    private volatile Looper looper;
    private ArrayList processList;
    private PushDiainfoManager pushMgr;
    private final Runnable runnableOnStart = new Runnable() {

        final PushDiainfoService this$0;

        public void run()
        {
            Intent intent = (Intent)intentQueue.poll();
            if (intent != null)
            {
                String s = intent.getAction();
                if ("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_APP_START".equals(s))
                {
                    onAppStart();
                    return;
                }
                if ("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_GET_RAIL".equals(s))
                {
                    onGetRegisteredRail(intent);
                    return;
                }
            }
        }

            
            {
                this$0 = PushDiainfoService.this;
                super();
            }
    };

    public PushDiainfoService()
    {
        intentQueue = new ConcurrentLinkedQueue();
        processList = new ArrayList();
    }

    private void onAppStart()
    {
        processList.add("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_APP_START");
        if (pushMgr == null)
        {
            pushMgr = new PushDiainfoManager(this);
        }
        pushMgr.checkAppVersionup(new jp.co.yahoo.android.apps.transit.common.PushDiainfoManager.PushManagerListener() {

            final PushDiainfoService this$0;

            public void onCanceled()
            {
                processList.remove("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_APP_START");
                if (processList.isEmpty())
                {
                    stopSelf();
                }
            }

            public void onError(int i, String s, String s1, String s2)
            {
                processList.remove("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_APP_START");
                if (processList.isEmpty())
                {
                    stopSelf();
                }
            }

            public void onSuccess(String s, String s1)
            {
                processList.remove("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_APP_START");
                if (processList.isEmpty())
                {
                    stopSelf();
                }
            }

            
            {
                this$0 = PushDiainfoService.this;
                super();
            }
        });
    }

    private void onGetRegisteredRail(Intent intent)
    {
        processList.add("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_GET_RAIL");
        android.os.Bundle bundle = intent.getBundleExtra(getString(0x7f0d01d5));
        if (pushMgr == null)
        {
            pushMgr = new PushDiainfoManager(this);
        }
        pushMgr.checkRegisteredRail(bundle, new jp.co.yahoo.android.apps.transit.common.PushDiainfoManager.PushManagerListener() {

            final PushDiainfoService this$0;

            public void onCanceled()
            {
                processList.remove("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_GET_RAIL");
                if (processList.isEmpty())
                {
                    stopSelf();
                }
            }

            public void onError(int i, String s, String s1, String s2)
            {
                processList.remove("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_GET_RAIL");
                if (processList.isEmpty())
                {
                    stopSelf();
                }
            }

            public void onSuccess(String s, String s1)
            {
                processList.remove("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_GET_RAIL");
                if (processList.isEmpty())
                {
                    stopSelf();
                }
            }

            
            {
                this$0 = PushDiainfoService.this;
                super();
            }
        });
    }

    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public void onCreate()
    {
        super.onCreate();
        HandlerThread handlerthread = new HandlerThread(getClass().getSimpleName(), 10);
        handlerthread.start();
        looper = handlerthread.getLooper();
        handler = new Handler(looper);
    }

    public void onDestroy()
    {
        intentQueue.clear();
        if (looper != null)
        {
            looper.quit();
        }
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        if (intent == null)
        {
            intent = new Intent();
        }
        intentQueue.add(intent);
        handler.post(runnableOnStart);
        return 2;
    }




}
