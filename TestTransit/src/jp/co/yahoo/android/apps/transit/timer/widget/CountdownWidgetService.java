// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.widget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import jp.co.yahoo.android.ads.AdViewForInstance;
import jp.co.yahoo.android.apps.transit.timer.CountdownActivity;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.timer.common.CountdownManager;
import jp.co.yahoo.android.apps.transit.timer.db.SettingDb;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.widget:
//            CountdownWidgetProvider, CountdownWidgetReceiver

public class CountdownWidgetService extends Service
    implements CountdownWidgetReceiver.CountdownWidgetReceiverListener
{

    public static final String ACTION_BUTTON_CHANGE = "jp.co.yahoo.android.apps.transit.timer.ACTION_BUTTON_CHANGE";
    public static final String ACTION_BUTTON_LAUNCH = "jp.co.yahoo.android.apps.transit.timer.ACTION_BUTTON_LAUNCH";
    public static final String ACTION_CHECK_START = "jp.co.yahoo.android.apps.transit.timer.ACTION_CHECK_START";
    public static final String ACTION_DATA_CHANGED = "jp.co.yahoo.android.apps.transit.timer.ACTION_DATA_CHANGED";
    public static final String ACTION_DELETE = "jp.co.yahoo.android.apps.transit.timer.ACTION_DELETE";
    public static final String ACTION_RESTART = "jp.co.yahoo.android.apps.transit.timer.ACTION_RESTART";
    public static final String ACTION_RESTART_PROCESS = "jp.co.yahoo.android.apps.transit.timer.ACTION_RESTART_PROCESS";
    public static final String ACTION_UPDATE = "jp.co.yahoo.android.apps.transit.timer.ACTION_UPDATE";
    private static final int INTENT_TYPE_CHANGE = 2;
    private static final int INTENT_TYPE_LAUNCH = 1;
    private CountdownManager goalCountManager;
    private int goalNowTime;
    private StationData goalStation;
    private TimeTableItemData goalTargetTime;
    private int goalWidgetIdList[];
    private ArrayList goalWidgetIds;
    private volatile Handler handler;
    private CountdownManager homeCountManager;
    private int homeNowTime;
    private StationData homeStation;
    private TimeTableItemData homeTargetTime;
    private int homeWidgetIdList[];
    private ArrayList homeWidgetIds;
    private Queue intentQueue;
    private volatile Looper looper;
    private CountdownWidgetReceiver receiver;
    private final Runnable runnableConfigChanged = new Runnable() {

        final CountdownWidgetService this$0;

        public void run()
        {
            int i = 0;
            cancelTimer();
            widgetLayoutId = -1;
            Context context = getApplicationContext();
            if (homeWidgetIdList != null && homeWidgetIdList.length > 0)
            {
                int i1 = getResources().getInteger(0x7f0c0074);
                int ai1[] = homeWidgetIdList;
                int j1 = ai1.length;
                for (int k1 = 0; k1 < j1; k1++)
                {
                    int l1 = ai1[k1];
                    initAppWidget(context, l1, i1);
                }

            }
            if (goalWidgetIdList != null && goalWidgetIdList.length > 0)
            {
                int j = getResources().getInteger(0x7f0c0073);
                int ai[] = goalWidgetIdList;
                for (int k = ai.length; i < k; i++)
                {
                    int l = ai[i];
                    initAppWidget(context, l, j);
                }

            }
            if (homeStation != null || goalStation != null)
            {
                startTimer();
            }
        }

            
            {
                this$0 = CountdownWidgetService.this;
                super();
            }
    };
    private final Runnable runnableOnStart = new Runnable() {

        final CountdownWidgetService this$0;

        public void run()
        {
            Intent intent = (Intent)intentQueue.poll();
            if (intent != null)
            {
                clearCheckStartAlarm();
                String s = intent.getAction();
                if ("jp.co.yahoo.android.apps.transit.timer.ACTION_UPDATE".equals(s))
                {
                    startUpdateAppWidgets(intent);
                    return;
                }
                if ("jp.co.yahoo.android.apps.transit.timer.ACTION_DELETE".equals(s))
                {
                    deleteAppWidgets(intent);
                    return;
                }
                if ("jp.co.yahoo.android.apps.transit.timer.ACTION_BUTTON_LAUNCH".equals(s))
                {
                    launchCountdownTimer(intent);
                    return;
                }
                if ("jp.co.yahoo.android.apps.transit.timer.ACTION_BUTTON_CHANGE".equals(s))
                {
                    changeStationType(intent);
                    return;
                }
                if ("jp.co.yahoo.android.apps.transit.timer.ACTION_DATA_CHANGED".equals(s))
                {
                    onDataChanged(intent);
                    return;
                }
                if ("jp.co.yahoo.android.apps.transit.timer.ACTION_RESTART".equals(s))
                {
                    restartUpdateTimer();
                    return;
                }
                if ("jp.co.yahoo.android.apps.transit.timer.ACTION_RESTART_PROCESS".equals(s))
                {
                    restartUpdateAppWidgets();
                    return;
                }
                if ("jp.co.yahoo.android.apps.transit.timer.ACTION_CHECK_START".equals(s))
                {
                    restartUpdateAppWidgets();
                    return;
                }
            }
        }

            
            {
                this$0 = CountdownWidgetService.this;
                super();
            }
    };
    private final Runnable runnableTimeChanged = new Runnable() {

        final CountdownWidgetService this$0;

        public void run()
        {
            cancelTimer();
            if (homeWidgetIdList != null && homeWidgetIdList.length > 0)
            {
                homeNowTime = -1;
                homeTargetTime = null;
                if (homeStation != null)
                {
                    homeCountManager.setWeek(-1);
                    homeCountManager.setTime(null);
                    homeCountManager.resetCountDown(homeStation.getTimetable());
                    homeNowTime = homeCountManager.updateTime();
                    homeTargetTime = homeCountManager.getTargetTime();
                }
                initAppWidgets(getApplicationContext(), homeWidgetIdList, getResources().getInteger(0x7f0c0074));
            }
            if (goalWidgetIdList != null && goalWidgetIdList.length > 0)
            {
                goalNowTime = -1;
                goalTargetTime = null;
                if (goalStation != null)
                {
                    goalCountManager.setWeek(-1);
                    goalCountManager.setTime(null);
                    goalCountManager.resetCountDown(goalStation.getTimetable());
                    goalNowTime = goalCountManager.updateTime();
                    goalTargetTime = goalCountManager.getTargetTime();
                }
                initAppWidgets(getApplicationContext(), goalWidgetIdList, getResources().getInteger(0x7f0c0073));
            }
            if (homeStation != null || goalStation != null)
            {
                startTimer();
            }
        }

            
            {
                this$0 = CountdownWidgetService.this;
                super();
            }
    };
    private final Runnable runnableUpdateTime = new Runnable() {

        final CountdownWidgetService this$0;

        public void run()
        {
            if (homeCountManager != null && homeWidgetIdList != null && homeWidgetIdList.length > 0)
            {
                Context context1 = getApplicationContext();
                homeNowTime = homeCountManager.updateTimeEx();
                TimeTableItemData timetableitemdata1 = homeCountManager.getTargetTime();
                if (android.os.Build.VERSION.SDK_INT < 11)
                {
                    if (timetableitemdata1 == null)
                    {
                        if (homeTargetTime != null)
                        {
                            homeTargetTime = null;
                            int i3 = getResources().getInteger(0x7f0c0074);
                            int ai3[] = homeWidgetIdList;
                            int j3 = ai3.length;
                            for (int k3 = 0; k3 < j3; k3++)
                            {
                                int l3 = ai3[k3];
                                initAppWidget(context1, l3, i3);
                            }

                        }
                    } else
                    {
                        homeTargetTime = timetableitemdata1;
                        int i2 = getResources().getInteger(0x7f0c0074);
                        int ai2[] = homeWidgetIdList;
                        int j2 = ai2.length;
                        for (int k2 = 0; k2 < j2; k2++)
                        {
                            int l2 = ai2[k2];
                            initAppWidget(context1, l2, i2);
                        }

                    }
                } else
                {
                    RemoteViews remoteviews1 = new RemoteViews(getPackageName(), getWidgetLayoutId());
                    if (timetableitemdata1 == null)
                    {
                        if (homeTargetTime != null)
                        {
                            homeTargetTime = null;
                            Bundle bundle1 = homeCountManager.getTodayTimetables();
                            int i1;
                            int ai1[];
                            int j1;
                            int k1;
                            int l1;
                            AppWidgetManager appwidgetmanager1;
                            if (bundle1 == null || bundle1.size() < 1)
                            {
                                setViewNoWeekData(remoteviews1, homeStation);
                            } else
                            {
                                setViewNoFilterData(remoteviews1, homeStation);
                            }
                        }
                    } else
                    {
                        if (homeTargetTime == null)
                        {
                            setViewTimetableVisible(remoteviews1);
                        }
                        if (!timetableitemdata1.equals(homeTargetTime))
                        {
                            homeTargetTime = timetableitemdata1;
                            setViewTimetableData(remoteviews1, timetableitemdata1);
                        }
                        setViewTimetableTime(remoteviews1, homeNowTime, timetableitemdata1);
                    }
                    appwidgetmanager1 = AppWidgetManager.getInstance(context1);
                    partiallyUpdateAppWidget(appwidgetmanager1, homeWidgetIdList, remoteviews1);
                }
            }
            if (goalCountManager != null && goalWidgetIdList != null && goalWidgetIdList.length > 0)
            {
                Context context = getApplicationContext();
                goalNowTime = goalCountManager.updateTimeEx();
                TimeTableItemData timetableitemdata = goalCountManager.getTargetTime();
                if (android.os.Build.VERSION.SDK_INT < 11)
                {
                    if (timetableitemdata == null)
                    {
                        if (goalTargetTime != null)
                        {
                            goalTargetTime = null;
                            i1 = getResources().getInteger(0x7f0c0073);
                            ai1 = goalWidgetIdList;
                            j1 = ai1.length;
                            for (k1 = 0; k1 < j1; k1++)
                            {
                                l1 = ai1[k1];
                                initAppWidget(context, l1, i1);
                            }

                        }
                    } else
                    {
                        goalTargetTime = timetableitemdata;
                        int i = getResources().getInteger(0x7f0c0073);
                        int ai[] = goalWidgetIdList;
                        int j = ai.length;
                        for (int k = 0; k < j; k++)
                        {
                            int l = ai[k];
                            initAppWidget(context, l, i);
                        }

                    }
                } else
                {
                    RemoteViews remoteviews = new RemoteViews(getPackageName(), getWidgetLayoutId());
                    if (timetableitemdata == null)
                    {
                        if (goalTargetTime != null)
                        {
                            goalTargetTime = null;
                            Bundle bundle = goalCountManager.getTodayTimetables();
                            AppWidgetManager appwidgetmanager;
                            if (bundle == null || bundle.size() < 1)
                            {
                                setViewNoWeekData(remoteviews, goalStation);
                            } else
                            {
                                setViewNoFilterData(remoteviews, goalStation);
                            }
                        }
                    } else
                    {
                        if (goalTargetTime == null)
                        {
                            setViewTimetableVisible(remoteviews);
                        }
                        if (!timetableitemdata.equals(goalTargetTime))
                        {
                            goalTargetTime = timetableitemdata;
                            setViewTimetableData(remoteviews, timetableitemdata);
                        }
                        setViewTimetableTime(remoteviews, goalNowTime, timetableitemdata);
                    }
                    appwidgetmanager = AppWidgetManager.getInstance(context);
                    partiallyUpdateAppWidget(appwidgetmanager, goalWidgetIdList, remoteviews);
                }
            }
        }

            
            {
                this$0 = CountdownWidgetService.this;
                super();
            }
    };
    private Timer updateTimer;
    private int widgetLayoutId;

    public CountdownWidgetService()
    {
        homeWidgetIds = new ArrayList();
        goalWidgetIds = new ArrayList();
        homeNowTime = -1;
        goalNowTime = -1;
        widgetLayoutId = -1;
        intentQueue = new ConcurrentLinkedQueue();
    }

    private void changeStationType(Intent intent)
    {
        int i;
        int j;
        i = intent.getIntExtra(getString(0x7f0d01c5), -1);
        j = intent.getIntExtra(getString(0x7f0d0247), -1);
        if (i != -1 && j != -1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int k;
        int i1;
        k = getResources().getInteger(0x7f0c0074);
        int l = getResources().getInteger(0x7f0c0073);
        if (j == k && goalWidgetIds.contains(Integer.valueOf(i)) || j == l && homeWidgetIds.contains(Integer.valueOf(i)))
        {
            continue; /* Loop/switch isn't completed */
        }
        cancelTimer();
        String s;
        SharedPreferences sharedpreferences;
        String s1;
        android.content.SharedPreferences.Editor editor;
        boolean flag;
        AdViewForInstance adviewforinstance;
        if (j == k)
        {
            i1 = l;
            s = "2080325047";
        } else
        {
            i1 = k;
            s = "2080325046";
        }
        sharedpreferences = getCountdownSharedPreferences();
        s1 = getStationTypeKey(i);
        editor = sharedpreferences.edit();
        editor.putInt(s1, i1);
        editor.commit();
        flag = getResources().getBoolean(0x7f080008);
        adviewforinstance = new AdViewForInstance(getApplicationContext(), getString(0x7f0d001e), "pv", flag);
        adviewforinstance.spaceid(s);
        adviewforinstance.requestFreshAd();
        if (homeWidgetIds.size() < 1 && goalWidgetIds.size() < 1)
        {
            restartUpdateAppWidgets();
            return;
        }
        if (j != k) goto _L4; else goto _L3
_L3:
        boolean flag2;
        boolean flag4;
        boolean flag5 = homeWidgetIds.contains(Integer.valueOf(i));
        flag4 = false;
        if (flag5)
        {
            homeWidgetIds.remove(Integer.valueOf(i));
            flag4 = true;
        }
        boolean flag6 = goalWidgetIds.contains(Integer.valueOf(i));
        flag2 = false;
        if (!flag6)
        {
            goalWidgetIds.add(Integer.valueOf(i));
            flag2 = true;
        }
_L6:
        if (flag4)
        {
            homeWidgetIdList = new int[homeWidgetIds.size()];
            for (int k1 = 0; k1 < homeWidgetIds.size(); k1++)
            {
                homeWidgetIdList[k1] = ((Integer)homeWidgetIds.get(k1)).intValue();
            }

        }
        break; /* Loop/switch isn't completed */
_L4:
        boolean flag1 = goalWidgetIds.contains(Integer.valueOf(i));
        flag2 = false;
        if (flag1)
        {
            goalWidgetIds.remove(Integer.valueOf(i));
            flag2 = true;
        }
        boolean flag3 = homeWidgetIds.contains(Integer.valueOf(i));
        flag4 = false;
        if (!flag3)
        {
            homeWidgetIds.add(Integer.valueOf(i));
            flag4 = true;
        }
        if (true) goto _L6; else goto _L5
_L5:
        if (flag2)
        {
            goalWidgetIdList = new int[goalWidgetIds.size()];
            for (int j1 = 0; j1 < goalWidgetIds.size(); j1++)
            {
                goalWidgetIdList[j1] = ((Integer)goalWidgetIds.get(j1)).intValue();
            }

        }
        if (homeWidgetIds.size() < 1)
        {
            clearHomeWidgetData();
        }
        if (goalWidgetIds.size() < 1)
        {
            clearGoalWidgetData();
        }
        if (i1 == k)
        {
            createHomeWidgetData();
        } else
        {
            createGoalWidgetData();
        }
        initAppWidget(getApplicationContext(), i, i1);
        if (homeStation != null || goalStation != null)
        {
            startTimer();
            return;
        }
        if (true) goto _L1; else goto _L7
_L7:
    }

    private void clearCheckStartAlarm()
    {
        if (android.os.Build.VERSION.SDK_INT < 11)
        {
            Context context = getApplicationContext();
            PendingIntent pendingintent = getCheckStartIntent(context);
            ((AlarmManager)context.getSystemService("alarm")).cancel(pendingintent);
        }
    }

    private void clearGoalWidgetData()
    {
        goalNowTime = -1;
        goalStation = null;
        goalTargetTime = null;
        goalCountManager = null;
    }

    private void clearHomeWidgetData()
    {
        homeNowTime = -1;
        homeStation = null;
        homeTargetTime = null;
        homeCountManager = null;
    }

    private PendingIntent createChangePendingIntent(Context context, int i, int j)
    {
        Intent intent = new Intent(context, getClass());
        intent.setAction("jp.co.yahoo.android.apps.transit.timer.ACTION_BUTTON_CHANGE");
        intent.putExtra(context.getString(0x7f0d0247), j);
        intent.putExtra(context.getString(0x7f0d01c5), i);
        return PendingIntent.getService(context, 0x20000000 + i, intent, 0x8000000);
    }

    private void createGoalWidgetData()
    {
        if (goalWidgetIdList != null && goalWidgetIdList.length > 0 && goalStation == null)
        {
            Context context = getApplicationContext();
            goalStation = (new SettingDb(context)).getTimetable(getResources().getInteger(0x7f0c0073));
            if (goalStation != null)
            {
                goalCountManager = new CountdownManager(context);
                goalCountManager.setWeek(-1);
                goalCountManager.setCountDown(goalStation.getTimetable());
                goalNowTime = goalCountManager.updateTime();
                goalTargetTime = goalCountManager.getTargetTime();
            }
        }
    }

    private void createHomeWidgetData()
    {
        if (homeWidgetIdList != null && homeWidgetIdList.length > 0 && homeStation == null)
        {
            Context context = getApplicationContext();
            homeStation = (new SettingDb(context)).getTimetable(getResources().getInteger(0x7f0c0074));
            if (homeStation != null)
            {
                homeCountManager = new CountdownManager(context);
                homeCountManager.setWeek(-1);
                homeCountManager.setCountDown(homeStation.getTimetable());
                homeNowTime = homeCountManager.updateTime();
                homeTargetTime = homeCountManager.getTargetTime();
            }
        }
    }

    private PendingIntent createLaunchPendingIntent(Context context, int i, int j)
    {
        Intent intent = new Intent(context, getClass());
        intent.setAction("jp.co.yahoo.android.apps.transit.timer.ACTION_BUTTON_LAUNCH");
        intent.putExtra(getString(0x7f0d0247), j);
        return PendingIntent.getService(context, 0x10000000 + i, intent, 0x8000000);
    }

    private void deleteAppWidgets(Intent intent)
    {
        cancelTimer();
        SharedPreferences sharedpreferences = getCountdownSharedPreferences();
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
        boolean flag = false;
        boolean flag1 = false;
        int ai[] = intent.getIntArrayExtra(getString(0x7f0d017f));
        int i = ai.length;
        int j = 0;
        while (j < i) 
        {
            int i1 = ai[j];
            String s;
            if (homeWidgetIds.contains(Integer.valueOf(i1)))
            {
                homeWidgetIds.remove(Integer.valueOf(i1));
                flag = true;
            } else
            if (goalWidgetIds.contains(Integer.valueOf(i1)))
            {
                goalWidgetIds.remove(Integer.valueOf(i1));
                flag1 = true;
            }
            s = getStationTypeKey(i1);
            if (sharedpreferences.contains(s))
            {
                editor.remove(s);
            }
            j++;
        }
        editor.commit();
        if (flag)
        {
            homeWidgetIdList = new int[homeWidgetIds.size()];
            for (int l = 0; l < homeWidgetIds.size(); l++)
            {
                homeWidgetIdList[l] = ((Integer)homeWidgetIds.get(l)).intValue();
            }

        }
        if (flag1)
        {
            goalWidgetIdList = new int[goalWidgetIds.size()];
            for (int k = 0; k < goalWidgetIds.size(); k++)
            {
                goalWidgetIdList[k] = ((Integer)goalWidgetIds.get(k)).intValue();
            }

        }
        if (homeWidgetIds.size() < 1)
        {
            clearHomeWidgetData();
        }
        if (goalWidgetIds.size() < 1)
        {
            clearGoalWidgetData();
        }
        if (homeStation != null || goalStation != null)
        {
            startTimer();
        }
    }

    private PendingIntent getCheckStartIntent(Context context)
    {
        Intent intent = new Intent(context.getApplicationContext(), jp/co/yahoo/android/apps/transit/timer/widget/CountdownWidgetService);
        intent.setAction("jp.co.yahoo.android.apps.transit.timer.ACTION_CHECK_START");
        return PendingIntent.getService(context, 0, intent, 0);
    }

    private SharedPreferences getCountdownSharedPreferences()
    {
        return getSharedPreferences(getString(0x7f0d01a0), 0);
    }

    private String getStationTypeKey(int i)
    {
        return (new StringBuilder()).append("widget_countdown_type-").append(i).toString();
    }

    private int getWidgetLayoutId()
    {
        if (widgetLayoutId == -1)
        {
            if (getResources().getConfiguration().orientation == 2)
            {
                widgetLayoutId = 0x7f0300bb;
            } else
            {
                widgetLayoutId = 0x7f0300ba;
            }
        }
        return widgetLayoutId;
    }

    private void initAppWidget(Context context, int i, int j)
    {
        RemoteViews remoteviews = new RemoteViews(getPackageName(), getWidgetLayoutId());
        setViewButton(remoteviews, context, i, j);
        setViewInit(remoteviews, j);
        AppWidgetManager.getInstance(context).updateAppWidget(i, remoteviews);
    }

    private void initAppWidgets(Context context, int ai[], int i)
    {
        if (android.os.Build.VERSION.SDK_INT < 11)
        {
            int j = ai.length;
            for (int k = 0; k < j; k++)
            {
                initAppWidget(context, ai[k], i);
            }

        } else
        {
            RemoteViews remoteviews = new RemoteViews(getPackageName(), getWidgetLayoutId());
            setViewInit(remoteviews, i);
            partiallyUpdateAppWidget(AppWidgetManager.getInstance(context), ai, remoteviews);
        }
    }

    private void launchCountdownTimer(Intent intent)
    {
        int i = intent.getIntExtra(getString(0x7f0d0247), -1);
        Intent intent1 = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/timer/CountdownActivity);
        intent1.putExtra(getString(0x7f0d0247), i);
        intent1.putExtra(getString(0x7f0d01ca), 7);
        intent1.setFlags(0x14000000);
        getApplicationContext().startActivity(intent1);
        restartUpdateTimer();
    }

    private void onDataChanged(Intent intent)
    {
        if (homeWidgetIds.size() >= 1 || goalWidgetIds.size() >= 1) goto _L2; else goto _L1
_L1:
        restartUpdateAppWidgets();
_L5:
        return;
_L2:
        int i;
        int j;
        int k;
        i = intent.getIntExtra(getString(0x7f0d0247), -1);
        j = getResources().getInteger(0x7f0c0074);
        k = getResources().getInteger(0x7f0c0073);
        if (i != j || homeWidgetIdList == null || homeWidgetIdList.length <= 0) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        if (flag)
        {
            cancelTimer();
            int ai[];
            int l;
            if (i == j)
            {
                clearHomeWidgetData();
                createHomeWidgetData();
                initAppWidgets(getApplicationContext(), homeWidgetIdList, j);
            } else
            {
                clearGoalWidgetData();
                createGoalWidgetData();
                initAppWidgets(getApplicationContext(), goalWidgetIdList, k);
            }
            if (homeStation != null || goalStation != null)
            {
                startTimer();
                return;
            }
        }
        if (true) goto _L5; else goto _L4
_L4:
        flag = false;
        if (i == k)
        {
            ai = goalWidgetIdList;
            flag = false;
            if (ai != null)
            {
                l = goalWidgetIdList.length;
                flag = false;
                if (l > 0)
                {
                    flag = true;
                }
            }
        }
          goto _L6
    }

    private void partiallyUpdateAppWidget(AppWidgetManager appwidgetmanager, int ai[], RemoteViews remoteviews)
    {
        appwidgetmanager.partiallyUpdateAppWidget(ai, remoteviews);
    }

    private void restartUpdateAppWidgets()
    {
        cancelTimer();
        Context context = getApplicationContext();
        ComponentName componentname = new ComponentName(context, jp/co/yahoo/android/apps/transit/timer/widget/CountdownWidgetProvider.getName());
        int ai[] = AppWidgetManager.getInstance(context).getAppWidgetIds(componentname);
        if (ai.length > 0)
        {
            startUpdateAppWidgets(ai);
        }
    }

    private void restartUpdateTimer()
    {
        if (updateTimer == null)
        {
            if (homeWidgetIds.size() < 1 && goalWidgetIds.size() < 1)
            {
                restartUpdateAppWidgets();
                return;
            }
            if (homeWidgetIdList != null && homeWidgetIdList.length > 0 || goalWidgetIdList != null && goalWidgetIdList.length > 0)
            {
                onTimeChanged();
                return;
            }
        }
    }

    private void setCheckStartAlarm()
    {
        if (android.os.Build.VERSION.SDK_INT < 11)
        {
            Context context = getApplicationContext();
            PendingIntent pendingintent = getCheckStartIntent(context);
            AlarmManager alarmmanager = (AlarmManager)context.getSystemService("alarm");
            alarmmanager.cancel(pendingintent);
            Calendar calendar = Calendar.getInstance();
            calendar.add(13, 5);
            alarmmanager.set(1, calendar.getTimeInMillis(), pendingintent);
        }
    }

    private void setStationType(int ai[])
    {
        SharedPreferences sharedpreferences;
        android.content.SharedPreferences.Editor editor;
        int i;
        int j;
        boolean flag;
        boolean flag1;
        int k;
        int l;
        sharedpreferences = getCountdownSharedPreferences();
        editor = sharedpreferences.edit();
        i = getResources().getInteger(0x7f0c0074);
        j = getResources().getInteger(0x7f0c0073);
        flag = false;
        flag1 = false;
        k = ai.length;
        l = 0;
_L8:
        if (l >= k) goto _L2; else goto _L1
_L1:
        int k1;
        int j2;
        k1 = ai[l];
        int l1;
        String s;
        if (homeWidgetIds.contains(Integer.valueOf(k1)))
        {
            l1 = i;
        } else
        if (goalWidgetIds.contains(Integer.valueOf(k1)))
        {
            l1 = j;
        } else
        {
            l1 = -1;
        }
        if (l1 != -1) goto _L4; else goto _L3
_L3:
        s = getStationTypeKey(k1);
        int i2 = sharedpreferences.getInt(s, -1);
        if (i2 == -1)
        {
            j2 = CountdownUtil.getCountdownType(this);
            if (j2 == -1)
            {
                j2 = i;
            }
            editor.putInt(s, j2);
        } else
        {
            j2 = i2;
        }
        if (j2 != i) goto _L6; else goto _L5
_L5:
        if (!homeWidgetIds.contains(Integer.valueOf(k1)))
        {
            homeWidgetIds.add(Integer.valueOf(k1));
            flag = true;
        }
_L4:
        l++;
        continue; /* Loop/switch isn't completed */
_L6:
        if (j2 == j && !goalWidgetIds.contains(Integer.valueOf(k1)))
        {
            goalWidgetIds.add(Integer.valueOf(k1));
            flag1 = true;
        }
        if (true) goto _L4; else goto _L2
_L2:
        editor.commit();
        if (flag)
        {
            homeWidgetIdList = new int[homeWidgetIds.size()];
            for (int j1 = 0; j1 < homeWidgetIds.size(); j1++)
            {
                homeWidgetIdList[j1] = ((Integer)homeWidgetIds.get(j1)).intValue();
            }

        }
        if (flag1)
        {
            goalWidgetIdList = new int[goalWidgetIds.size()];
            for (int i1 = 0; i1 < goalWidgetIds.size(); i1++)
            {
                goalWidgetIdList[i1] = ((Integer)goalWidgetIds.get(i1)).intValue();
            }

        }
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private void setViewButton(RemoteViews remoteviews, Context context, int i, int j)
    {
        remoteviews.setOnClickPendingIntent(0x7f0a01c0, createLaunchPendingIntent(context, i, j));
        remoteviews.setOnClickPendingIntent(0x7f0a00d3, createChangePendingIntent(context, i, j));
        if (j == getResources().getInteger(0x7f0c0074))
        {
            remoteviews.setImageViewResource(0x7f0a00d3, 0x7f0202aa);
            return;
        } else
        {
            remoteviews.setImageViewResource(0x7f0a00d3, 0x7f0202a8);
            return;
        }
    }

    private void setViewInit(RemoteViews remoteviews, int i)
    {
        if (i == getResources().getInteger(0x7f0c0074))
        {
            if (homeStation == null)
            {
                setViewNoStationData(remoteviews, 0x7f0d00bc);
                return;
            }
            if (homeTargetTime == null)
            {
                Bundle bundle1 = homeCountManager.getTodayTimetables();
                if (bundle1 == null || bundle1.size() < 1)
                {
                    setViewNoWeekData(remoteviews, homeStation);
                    return;
                } else
                {
                    setViewNoFilterData(remoteviews, homeStation);
                    return;
                }
            } else
            {
                setViewStationData(remoteviews, homeStation);
                setViewTimetableData(remoteviews, homeTargetTime);
                setViewTimetableTime(remoteviews, homeNowTime, homeTargetTime);
                return;
            }
        }
        if (goalStation == null)
        {
            setViewNoStationData(remoteviews, 0x7f0d00bb);
            return;
        }
        if (goalTargetTime == null)
        {
            Bundle bundle = goalCountManager.getTodayTimetables();
            if (bundle == null || bundle.size() < 1)
            {
                setViewNoWeekData(remoteviews, goalStation);
                return;
            } else
            {
                setViewNoFilterData(remoteviews, goalStation);
                return;
            }
        } else
        {
            setViewStationData(remoteviews, goalStation);
            setViewTimetableData(remoteviews, goalTargetTime);
            setViewTimetableTime(remoteviews, goalNowTime, goalTargetTime);
            return;
        }
    }

    private void setViewNoFilterData(RemoteViews remoteviews, StationData stationdata)
    {
        remoteviews.setTextViewText(0x7f0a01bb, stationdata.getRailname());
        remoteviews.setTextViewText(0x7f0a01bd, stationdata.getName());
        remoteviews.setTextViewText(0x7f0a0270, getString(0x7f0d0304));
        remoteviews.setTextViewText(0x7f0a01f4, getString(0x7f0d00ba));
        remoteviews.setViewVisibility(0x7f0a01f4, 0);
        remoteviews.setViewVisibility(0x7f0a034a, 8);
        remoteviews.setViewVisibility(0x7f0a034b, 8);
        remoteviews.setViewVisibility(0x7f0a0350, 8);
    }

    private void setViewNoStationData(RemoteViews remoteviews, int i)
    {
        remoteviews.setTextViewText(0x7f0a01bb, "");
        remoteviews.setTextViewText(0x7f0a01bd, "");
        remoteviews.setTextViewText(0x7f0a0270, "");
        remoteviews.setTextViewText(0x7f0a01f4, getString(i));
        remoteviews.setViewVisibility(0x7f0a01f4, 0);
        remoteviews.setViewVisibility(0x7f0a034a, 8);
        remoteviews.setViewVisibility(0x7f0a034b, 8);
        remoteviews.setViewVisibility(0x7f0a0350, 8);
    }

    private void setViewNoWeekData(RemoteViews remoteviews, StationData stationdata)
    {
        remoteviews.setTextViewText(0x7f0a01bb, stationdata.getRailname());
        remoteviews.setTextViewText(0x7f0a01bd, stationdata.getName());
        remoteviews.setTextViewText(0x7f0a0270, getString(0x7f0d0304));
        remoteviews.setTextViewText(0x7f0a01f4, getString(0x7f0d00bd));
        remoteviews.setViewVisibility(0x7f0a01f4, 0);
        remoteviews.setViewVisibility(0x7f0a034a, 8);
        remoteviews.setViewVisibility(0x7f0a034b, 8);
        remoteviews.setViewVisibility(0x7f0a0350, 8);
    }

    private void setViewStationData(RemoteViews remoteviews, StationData stationdata)
    {
        remoteviews.setTextViewText(0x7f0a01bb, stationdata.getRailname());
        remoteviews.setTextViewText(0x7f0a01bd, stationdata.getName());
        remoteviews.setTextViewText(0x7f0a0270, getString(0x7f0d0304));
        remoteviews.setViewVisibility(0x7f0a01f4, 8);
        remoteviews.setViewVisibility(0x7f0a034a, 0);
    }

    private void setViewTimetableData(RemoteViews remoteviews, TimeTableItemData timetableitemdata)
    {
        if (timetableitemdata != null)
        {
            String s = CountdownUtil.getZeroNum(timetableitemdata.getHour());
            String s1 = CountdownUtil.getZeroNum(timetableitemdata.getMinute());
            remoteviews.setTextViewText(0x7f0a01d4, (new StringBuilder()).append(s).append(":").append(s1).append(getString(0x7f0d0300)).toString());
            StringBuffer stringbuffer = new StringBuffer(timetableitemdata.getTraininfo());
            if (timetableitemdata.isStartStation())
            {
                stringbuffer.append(" ").append(getString(0x7f0d051c));
            }
            if (timetableitemdata.isExtraLine())
            {
                stringbuffer.append(" ").append(getString(0x7f0d0518));
            }
            remoteviews.setTextViewText(0x7f0a01d8, stringbuffer);
            int i;
            if (timetableitemdata.getTraintype().equals("1"))
            {
                i = 0x7f090029;
            } else
            if (timetableitemdata.getTraintype().equals("2"))
            {
                i = 0x7f09002a;
            } else
            if (timetableitemdata.getTraintype().equals("3"))
            {
                i = 0x7f09002b;
            } else
            if (timetableitemdata.getTraintype().equals("4"))
            {
                i = 0x7f09002c;
            } else
            {
                i = 0x7f09002d;
            }
            remoteviews.setInt(0x7f0a01d8, "setBackgroundColor", getResources().getColor(i));
            remoteviews.setTextViewText(0x7f0a01d9, timetableitemdata.getDestinfo());
        }
    }

    private void setViewTimetableTime(RemoteViews remoteviews, int i, TimeTableItemData timetableitemdata)
    {
        int k;
        int l;
        int i1;
label0:
        {
label1:
            {
                if (i != -1 && timetableitemdata != null)
                {
                    int j = (60 * (60 * timetableitemdata.getHour()) + 60 * timetableitemdata.getMinute()) - i;
                    k = j / 3600;
                    l = (j % 3600) / 60;
                    i1 = j % 60;
                    if (k != 0)
                    {
                        break label0;
                    }
                    remoteviews.setViewVisibility(0x7f0a034b, 8);
                    remoteviews.setViewVisibility(0x7f0a0350, 0);
                    remoteviews.setTextViewText(0x7f0a0352, CountdownUtil.getZeroNum(Math.abs(l)));
                    remoteviews.setTextViewText(0x7f0a0353, CountdownUtil.getZeroNum(Math.abs(i1)));
                    if (!timetableitemdata.isFirstStation())
                    {
                        break label1;
                    }
                    remoteviews.setTextViewText(0x7f0a0351, getString(0x7f0d0283));
                    remoteviews.setViewVisibility(0x7f0a0351, 0);
                }
                return;
            }
            if (timetableitemdata.isLastStation())
            {
                remoteviews.setTextViewText(0x7f0a0351, getString(0x7f0d0291));
                remoteviews.setViewVisibility(0x7f0a0351, 0);
                return;
            } else
            {
                remoteviews.setViewVisibility(0x7f0a0351, 8);
                return;
            }
        }
        remoteviews.setViewVisibility(0x7f0a0350, 8);
        remoteviews.setViewVisibility(0x7f0a034b, 0);
        remoteviews.setTextViewText(0x7f0a034d, CountdownUtil.getZeroNum(Math.abs(k)));
        remoteviews.setTextViewText(0x7f0a034e, CountdownUtil.getZeroNum(Math.abs(l)));
        remoteviews.setTextViewText(0x7f0a034f, CountdownUtil.getZeroNum(Math.abs(i1)));
        if (timetableitemdata.isFirstStation())
        {
            remoteviews.setTextViewText(0x7f0a034c, getString(0x7f0d0283));
            remoteviews.setViewVisibility(0x7f0a034c, 0);
            return;
        }
        if (timetableitemdata.isLastStation())
        {
            remoteviews.setTextViewText(0x7f0a034c, getString(0x7f0d0291));
            remoteviews.setViewVisibility(0x7f0a034c, 0);
            return;
        } else
        {
            remoteviews.setViewVisibility(0x7f0a034c, 8);
            return;
        }
    }

    private void setViewTimetableVisible(RemoteViews remoteviews)
    {
        remoteviews.setViewVisibility(0x7f0a01f4, 8);
        remoteviews.setViewVisibility(0x7f0a034a, 0);
    }

    private void startUpdateAppWidgets(Intent intent)
    {
        startUpdateAppWidgets(intent.getIntArrayExtra(getString(0x7f0d017f)));
    }

    private void startUpdateAppWidgets(int ai[])
    {
        cancelTimer();
        setStationType(ai);
        createHomeWidgetData();
        createGoalWidgetData();
        int i = getResources().getInteger(0x7f0c0074);
        int j = getResources().getInteger(0x7f0c0073);
        int k = ai.length;
        int l = 0;
        while (l < k) 
        {
            int i1 = ai[l];
            if (homeWidgetIds.contains(Integer.valueOf(i1)))
            {
                initAppWidget(getApplicationContext(), i1, i);
            } else
            if (goalWidgetIds.contains(Integer.valueOf(i1)))
            {
                initAppWidget(getApplicationContext(), i1, j);
            }
            l++;
        }
        if (homeStation != null || goalStation != null)
        {
            startTimer();
        }
    }

    protected void cancelTimer()
    {
        if (updateTimer == null)
        {
            return;
        } else
        {
            updateTimer.cancel();
            updateTimer.purge();
            updateTimer = null;
            return;
        }
    }

    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public void onConfigurationChanged()
    {
        handler.post(runnableConfigChanged);
    }

    public void onCreate()
    {
        super.onCreate();
        receiver = new CountdownWidgetReceiver();
        receiver.registerReceiver(this, this);
        HandlerThread handlerthread = new HandlerThread(getClass().getSimpleName(), 10);
        handlerthread.start();
        looper = handlerthread.getLooper();
        handler = new Handler(looper);
        setCheckStartAlarm();
    }

    public void onDestroy()
    {
        cancelTimer();
        receiver.unregisterReceiver(this);
        intentQueue.clear();
        if (looper != null)
        {
            looper.quit();
        }
        super.onDestroy();
    }

    public void onScreenOff()
    {
        cancelTimer();
    }

    public void onScreenOn()
    {
        restartUpdateTimer();
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        if (intent == null)
        {
            intent = new Intent();
            intent.setAction("jp.co.yahoo.android.apps.transit.timer.ACTION_RESTART_PROCESS");
        }
        intentQueue.add(intent);
        handler.post(runnableOnStart);
        return 1;
    }

    public void onTimeChanged()
    {
        handler.post(runnableTimeChanged);
    }

    protected void startTimer()
    {
        if (updateTimer != null)
        {
            return;
        } else
        {
            long l = 1000 - Calendar.getInstance().get(14);
            updateTimer = new Timer(true);
            updateTimer.schedule(new TimerTask() {

                final CountdownWidgetService this$0;

                public void run()
                {
                    handler.post(runnableUpdateTime);
                }

            
            {
                this$0 = CountdownWidgetService.this;
                super();
            }
            }, l, 1000L);
            return;
        }
    }






/*
    static int access$1102(CountdownWidgetService countdownwidgetservice, int i)
    {
        countdownwidgetservice.homeNowTime = i;
        return i;
    }

*/



/*
    static TimeTableItemData access$1202(CountdownWidgetService countdownwidgetservice, TimeTableItemData timetableitemdata)
    {
        countdownwidgetservice.homeTargetTime = timetableitemdata;
        return timetableitemdata;
    }

*/















/*
    static int access$2402(CountdownWidgetService countdownwidgetservice, int i)
    {
        countdownwidgetservice.goalNowTime = i;
        return i;
    }

*/



/*
    static TimeTableItemData access$2502(CountdownWidgetService countdownwidgetservice, TimeTableItemData timetableitemdata)
    {
        countdownwidgetservice.goalTargetTime = timetableitemdata;
        return timetableitemdata;
    }

*/







/*
    static int access$3002(CountdownWidgetService countdownwidgetservice, int i)
    {
        countdownwidgetservice.widgetLayoutId = i;
        return i;
    }

*/






}
