// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.StrictMode;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import jp.co.yahoo.android.apps.transit.timer.api.TimeTableSearch;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.db.SettingDb;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.yolp.common.YolpError;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingTimetableActivity

public class TimetableAutoupdateService extends Service
{

    private SettingDb db;
    private volatile Handler handler;
    private Queue intentQueue;
    private volatile Looper looper;
    private final Runnable runnableOnStart = new Runnable() {

        final TimetableAutoupdateService this$0;

        public void run()
        {
            if ((Intent)intentQueue.poll() == null)
            {
                return;
            } else
            {
                updateAllbyDay();
                return;
            }
        }

            
            {
                this$0 = TimetableAutoupdateService.this;
                super();
            }
    };
    private Intent updateIntent;

    public TimetableAutoupdateService()
    {
        db = null;
        updateIntent = null;
        intentQueue = new ConcurrentLinkedQueue();
    }

    private void setNotice(String s)
    {
        NotificationManager notificationmanager = (NotificationManager)getSystemService("notification");
        if (s == null)
        {
            s = "";
        }
        String s1 = s;
        PendingIntent pendingintent = PendingIntent.getActivity(this, 0, updateIntent, 0);
        android.support.v4.app.NotificationCompat.Builder builder = (new android.support.v4.app.NotificationCompat.Builder(this)).setSmallIcon(0x7f0201cc).setContentTitle(getString(0x7f0d00b8)).setContentText(s1).setTicker(s1).setContentIntent(pendingintent).setWhen(System.currentTimeMillis()).setAutoCancel(true);
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), 0x7f0201ee));
        }
        notificationmanager.notify(1, builder.build());
    }

    private void setStrictMode()
    {
        StrictMode.setThreadPolicy((new android.os.StrictMode.ThreadPolicy.Builder()).permitAll().build());
    }

    public boolean error(StationData stationdata, int i)
    {
        setNotice(getString(0x7f0d0103));
        stopSelf();
        return false;
    }

    public Bundle getTimetable(StationData stationdata, int i)
    {
        TimeTableSearch timetablesearch = new TimeTableSearch(this);
        timetablesearch.setStation(stationdata);
        timetablesearch.setCode(stationdata.getStationId());
        timetablesearch.setId(stationdata.getDirid());
        timetablesearch.setKind(i);
        timetablesearch.execute();
        Object obj = timetablesearch.getResult();
        if (obj == null || !(obj instanceof Bundle))
        {
            YolpError yolperror = timetablesearch.getError();
            if (yolperror != null && yolperror.getCode() != null && yolperror.getCode().equals(getString(0x7f0d0516)))
            {
                return new Bundle();
            } else
            {
                return null;
            }
        } else
        {
            return (Bundle)obj;
        }
    }

    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public void onCreate()
    {
        super.onCreate();
        if (android.os.Build.VERSION.SDK_INT > 8)
        {
            setStrictMode();
        }
        db = new SettingDb(this);
        updateIntent = new Intent(this, jp/co/yahoo/android/apps/transit/timer/SettingTimetableActivity);
        updateIntent.setAction("android.intent.action.VIEW");
        updateIntent.putExtra(getString(0x7f0d01ca), 9);
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
        return 1;
    }

    public void sleep(long l)
    {
        this;
        JVM INSTR monitorenter ;
        Exception exception;
        try
        {
            wait(l);
        }
        catch (InterruptedException interruptedexception) { }
        finally
        {
            this;
        }
        return;
        throw exception;
    }

    public boolean updateAllEnd()
    {
        setNotice(getString(0x7f0d00a2));
        stopSelf();
        return false;
    }

    public void updateAllbyDay()
    {
        ArrayList arraylist = db.getAllStation(0);
        if (arraylist == null || arraylist.size() < 1)
        {
            updateAllEnd();
            return;
        }
        int i = CountdownUtil.getNowWeek(0, false, getApplicationContext());
        for (int j = 0; j < arraylist.size(); j++)
        {
            StationData stationdata = (StationData)arraylist.get(j);
            Bundle bundle = getTimetable(stationdata, i);
            if (bundle == null)
            {
                error(stationdata, j);
                return;
            }
            Bundle bundle1 = db.getTimetableById(Integer.parseInt(stationdata.getId())).getTimetable();
            bundle1.putBundle(Integer.toString(i), bundle);
            db.updateTimetable(stationdata, bundle1);
            updateEnd(stationdata);
            sleep(2000L);
        }

        updateAllEnd();
    }

    public boolean updateEnd(StationData stationdata)
    {
        setNotice((new StringBuilder()).append(stationdata.getRailname()).append(" ").append(stationdata.getDirname()).append(getString(0x7f0d0512)).append(" ").append(stationdata.getName()).append(getString(0x7f0d0304)).append(getString(0x7f0d036a)).toString());
        return false;
    }

}
