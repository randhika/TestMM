// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import jp.co.yahoo.android.apps.transit.timer.AlarmReceiver;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;
import jp.co.yahoo.android.apps.transit.timer.db.AlermDb;
import jp.co.yahoo.android.apps.transit.timer.db.SettingDb;

public class Alerm
{

    public Context context;

    public Alerm(Context context1)
    {
        context = null;
        context = context1;
    }

    private PendingIntent getPendingAlarmIntent(AlermData alermdata, int i)
    {
        Intent intent = new Intent(context, jp/co/yahoo/android/apps/transit/timer/AlarmReceiver);
        intent.putExtra("name", alermdata.toString());
        intent.putExtra("id", i);
        intent.putExtra("type", alermdata.getType());
        intent.setAction(context.getString(0x7f0d017c));
        intent.setType(Integer.toString(i));
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    public void delAlarm(AlermData alermdata)
    {
        if (alermdata == null)
        {
            return;
        } else
        {
            (new AlermDb(context)).deleteById(alermdata.getId());
            ((AlarmManager)context.getSystemService("alarm")).cancel(getPendingAlarmIntent(alermdata, alermdata.getId()));
            return;
        }
    }

    public void delAlarmByTime(int i)
    {
        AlermDb alermdb = new AlermDb(context);
        ArrayList arraylist = alermdb.getAlermAll(i);
        if (arraylist != null && arraylist.size() >= 1)
        {
            AlarmManager alarmmanager = (AlarmManager)context.getSystemService("alarm");
            Iterator iterator = arraylist.iterator();
            while (iterator.hasNext()) 
            {
                AlermData alermdata = (AlermData)iterator.next();
                alarmmanager.cancel(getPendingAlarmIntent(alermdata, alermdata.getId()));
                alermdb.deleteById(alermdata.getId());
            }
        }
    }

    public void delAlarmByTimetableId(int i)
    {
        AlermDb alermdb = new AlermDb(context);
        ArrayList arraylist = alermdb.getAlermByTimetableId(i);
        if (arraylist != null)
        {
            Iterator iterator = arraylist.iterator();
            while (iterator.hasNext()) 
            {
                AlermData alermdata = (AlermData)iterator.next();
                alermdb.deleteById(alermdata.getId());
                ((AlarmManager)context.getSystemService("alarm")).cancel(getPendingAlarmIntent(alermdata, alermdata.getId()));
            }
        }
    }

    public ArrayList getAlarmByTimetableId(int i)
    {
        return (new AlermDb(context)).getAlermByTimetableId(i);
    }

    public ArrayList getAlarmByTimetableId(int i, int j)
    {
        return (new AlermDb(context)).getAlermByTimetableId(i, j);
    }

    public AlermData getAlerm(int i)
    {
        return (new AlermDb(context)).getAlerm(i);
    }

    public AlermData getAlermItem(int i)
    {
        return (new AlermDb(context)).getAlerm(i);
    }

    public ArrayList getAllAlerm()
    {
        ArrayList arraylist = (new AlermDb(context)).getAlermAll();
        if (arraylist == null)
        {
            arraylist = null;
        }
        return arraylist;
    }

    public AlermData getCountdownAlerm()
    {
        return (new AlermDb(context)).getCountAlerm();
    }

    public AlermData getStartAlerm(int i)
    {
        AlermDb alermdb = new AlermDb(context);
        SettingDb settingdb = new SettingDb(context);
        AlermData alermdata = alermdb.getStartAlerm(i);
        alermdata.setStation(settingdb.getTimetableById(alermdata.getTimetableId()));
        return alermdata;
    }

    public ArrayList getStartAlermList()
    {
        ArrayList arraylist = (new AlermDb(context)).getStartAll();
        if (arraylist == null)
        {
            arraylist = null;
        } else
        {
            SettingDb settingdb = new SettingDb(context);
            int i = 0;
            while (i < arraylist.size()) 
            {
                AlermData alermdata = (AlermData)arraylist.get(i);
                jp.co.yahoo.android.apps.transit.timer.api.data.StationData stationdata = settingdb.getTimetableById(alermdata.getTimetableId());
                if (stationdata == null)
                {
                    delAlarm(alermdata);
                    arraylist.remove(i);
                } else
                {
                    alermdata.setStation(stationdata);
                }
                i++;
            }
        }
        return arraylist;
    }

    public AlermData getUpdateAlerm()
    {
        ArrayList arraylist = (new AlermDb(context)).getAlermAll(AlermData.TYPE_UPDATE);
        AlermData alermdata = null;
        if (arraylist != null)
        {
            int i = arraylist.size();
            alermdata = null;
            if (i > 0)
            {
                alermdata = (AlermData)arraylist.get(0);
            }
        }
        return alermdata;
    }

    public boolean isStartToday(int i)
    {
        String s = (new AlermDb(context)).getAlerm(i).getRepeat();
        if (s.equals("8") || s.equals("0"))
        {
            return true;
        }
        String as[] = s.split(",");
        int j = Calendar.getInstance().get(7);
        boolean flag;
        if (Arrays.asList(as).contains(Integer.toString(j)))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public void setAlerm(AlermData alermdata, boolean flag)
    {
        AlarmManager alarmmanager = (AlarmManager)context.getSystemService("alarm");
        AlermDb alermdb = new AlermDb(context);
        PendingIntent pendingintent;
        if (flag)
        {
            alermdb.updateAlerm(alermdata);
            pendingintent = getPendingAlarmIntent(alermdata, alermdata.getId());
            alarmmanager.cancel(pendingintent);
        } else
        {
            alermdata.setId(alermdb.addStart(alermdata));
            pendingintent = getPendingAlarmIntent(alermdata, alermdata.getId());
        }
        if (!alermdata.isSetting())
        {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        int i = alermdata.getStartTime() - 60 * alermdata.getCountdownTime();
        int j = i / 3600;
        if (j >= 24)
        {
            j -= 24;
        }
        int k = (i % 3600) / 60;
        int l = calendar.get(11);
        int i1 = calendar.get(12);
        if (j < l)
        {
            calendar.add(5, 1);
        } else
        if (j == l && k < i1)
        {
            calendar.add(5, 1);
        }
        calendar.set(11, j);
        calendar.set(12, k);
        calendar.set(13, 0);
        calendar.set(14, 0);
        if (alermdata.getRepeat().equals("0"))
        {
            alarmmanager.set(0, calendar.getTimeInMillis(), pendingintent);
            return;
        } else
        {
            alarmmanager.setRepeating(0, calendar.getTimeInMillis(), 0x5265c00L, pendingintent);
            return;
        }
    }
}
