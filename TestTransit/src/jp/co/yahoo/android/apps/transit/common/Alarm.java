// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import jp.co.yahoo.android.apps.transit.AlarmReceiver;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.db.AlarmDB;

public class Alarm
{

    public static final int ALARM_DATA_MAX = 5;
    public static final int ALARM_LENGTH_DEFAULT_INDEX = 0;
    public static final int ALARM_LENGTH_LIST[] = {
        5, 10, 15, 20, 30, 45, 60
    };
    public static final int ALARM_MINUTES_DEFAULT_INDEX = 2;
    public static final int ALARM_MINUTES_LIST[] = {
        1, 2, 3, 5, 10, 30
    };
    public static final int ALARM_SOUND_DEFAULT = 0x7f0c0007;
    private Context context;

    public Alarm(Context context1)
    {
        context = context1;
    }

    private PendingIntent getPendingAlarmIntent(ConditionData conditiondata, NaviSearchData navisearchdata, Bundle bundle, int i)
    {
        Intent intent = new Intent(context, jp/co/yahoo/android/apps/transit/AlarmReceiver);
        intent.putExtra(context.getString(0x7f0d022c), conditiondata);
        intent.putExtra(context.getString(0x7f0d0232), navisearchdata);
        intent.putExtra(context.getString(0x7f0d0234), bundle);
        intent.setType(String.valueOf(i));
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    public void addAlarm(ConditionData conditiondata, NaviSearchData navisearchdata, ArrayList arraylist)
    {
        AlarmDB alarmdb = new AlarmDB(context);
        setAlarm(alarmdb.getAlarmDataSetting(alarmdb.addAlarmData(conditiondata, navisearchdata, arraylist)));
    }

    public int countAlarmData()
    {
        AlarmDB alarmdb = new AlarmDB(context);
        if (alarmdb.countAlarmSetting() == 0)
        {
            alarmdb.deleteAlarmDataAll();
            return 0;
        } else
        {
            return alarmdb.countAlarmData();
        }
    }

    public void deleteAlarmSetting(ArrayList arraylist)
    {
        AlarmDB alarmdb = new AlarmDB(context);
        ArrayList arraylist1 = new ArrayList();
        Iterator iterator = arraylist.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Bundle bundle = (Bundle)iterator.next();
            int i = bundle.getInt(context.getString(0x7f0d01c5));
            PendingIntent pendingintent = getPendingAlarmIntent(null, null, null, i);
            ((AlarmManager)context.getSystemService("alarm")).cancel(pendingintent);
            alarmdb.deleteAlarmSetting(i);
            int j = bundle.getInt(context.getString(0x7f0d017e));
            if (!arraylist1.contains(Integer.valueOf(j)))
            {
                arraylist1.add(Integer.valueOf(j));
            }
        } while (true);
        Iterator iterator1 = arraylist1.iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            Integer integer = (Integer)iterator1.next();
            if (alarmdb.countAlarmSettingByAlarmId(integer.intValue()) == 0)
            {
                alarmdb.deleteAlarmData(integer.intValue());
            }
        } while (true);
    }

    public void deleteAlarmSettingById(int i, int j)
    {
        AlarmDB alarmdb = new AlarmDB(context);
        alarmdb.deleteAlarmSetting(i);
        if (alarmdb.countAlarmSettingByAlarmId(j) == 0)
        {
            alarmdb.deleteAlarmData(j);
        }
    }

    public void deleteOldestAlarm()
    {
        AlarmDB alarmdb = new AlarmDB(context);
        deleteAlarmSetting((ArrayList)alarmdb.getAlarmSetting(alarmdb.getOldestAlarmId()));
    }

    public Bundle getAlarmData(int i)
    {
        return (new AlarmDB(context)).getAlarmData(i);
    }

    public List getAlarmList()
    {
        AlarmDB alarmdb;
        ArrayList arraylist;
        boolean flag;
        Iterator iterator;
        alarmdb = new AlarmDB(context);
        arraylist = (ArrayList)alarmdb.getAlarmList();
        flag = false;
        iterator = arraylist.iterator();
_L8:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        Bundle bundle;
        ArrayList arraylist1;
        ArrayList arraylist2;
        bundle = (Bundle)iterator.next();
        try
        {
            arraylist1 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)((NaviSearchData)bundle.getSerializable(context.getString(0x7f0d0232))).routes.get(0)).edges;
            arraylist2 = bundle.getParcelableArrayList(context.getString(0x7f0d0234));
        }
        catch (Exception exception)
        {
            return null;
        }
        if (arraylist1 != null && arraylist2 != null) goto _L4; else goto _L3
_L3:
        arraylist = null;
_L6:
        return arraylist;
_L4:
        Calendar calendar = Calendar.getInstance();
        Iterator iterator1 = arraylist2.iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            Bundle bundle1 = (Bundle)iterator1.next();
            int j = bundle1.getInt(context.getString(0x7f0d01c5));
            int k = bundle1.getInt(context.getString(0x7f0d01cc));
            int l = bundle1.getInt(context.getString(0x7f0d01d4));
            if (arraylist1.get(l) != null)
            {
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist1.get(l);
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge1 = null;
                if (l > 1)
                {
                    edge1 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist1.get(l - 1);
                }
                String s;
                if (l == 0 && edge.departureDatetime != null)
                {
                    s = edge.departureDatetime;
                } else
                if (edge1 != null && edge1.arrivalDatetime != null)
                {
                    s = edge1.arrivalDatetime;
                } else
                {
                    s = "";
                }
                if (!TextUtils.isEmpty(s))
                {
                    Calendar calendar1 = TransitUtil.timeStringToCalendar(s);
                    calendar1.set(12, calendar1.get(12) - k);
                    if (calendar1.before(calendar))
                    {
                        flag = true;
                        alarmdb.deleteAlarmSetting(j);
                    }
                }
            }
        } while (true);
        int i = bundle.getInt(context.getString(0x7f0d01c5));
        if (alarmdb.countAlarmSettingByAlarmId(i) == 0)
        {
            alarmdb.deleteAlarmData(i);
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (!flag) goto _L6; else goto _L5
_L5:
        return alarmdb.getAlarmList();
        if (true) goto _L8; else goto _L7
_L7:
    }

    public void setAlarm(Bundle bundle)
    {
        ConditionData conditiondata;
        NaviSearchData navisearchdata;
        ArrayList arraylist;
        ArrayList arraylist1;
        HashMap hashmap;
        try
        {
            conditiondata = (ConditionData)bundle.getSerializable(context.getString(0x7f0d022c));
            navisearchdata = (NaviSearchData)bundle.getSerializable(context.getString(0x7f0d0232));
            arraylist = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)navisearchdata.routes.get(0)).edges;
            arraylist1 = bundle.getParcelableArrayList(context.getString(0x7f0d0234));
            hashmap = navisearchdata.points;
        }
        catch (Exception exception)
        {
            return;
        }
        if (arraylist != null && hashmap != null && arraylist1 != null)
        {
            Calendar calendar = Calendar.getInstance();
            Iterator iterator = arraylist1.iterator();
            while (iterator.hasNext()) 
            {
                Bundle bundle1 = (Bundle)iterator.next();
                int i = bundle1.getInt(context.getString(0x7f0d01c5));
                int j = bundle1.getInt(context.getString(0x7f0d01cc));
                int k = bundle1.getInt(context.getString(0x7f0d01d4));
                if (arraylist.get(k) != null)
                {
                    jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(k);
                    jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge1 = null;
                    if (k > 0)
                    {
                        edge1 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(k - 1);
                    }
                    String s = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(edge.startPointTarget)).stationName;
                    String s1;
                    if (k == 0 && edge.departureDatetime != null)
                    {
                        s1 = edge.departureDatetime;
                    } else
                    if (edge1 != null && edge1.arrivalDatetime != null)
                    {
                        s1 = edge1.arrivalDatetime;
                    } else
                    {
                        s1 = "";
                    }
                    if (!TextUtils.isEmpty(s1))
                    {
                        bundle1.putString(context.getString(0x7f0d0241), s);
                        PendingIntent pendingintent = getPendingAlarmIntent(conditiondata, navisearchdata, bundle1, i);
                        AlarmManager alarmmanager = (AlarmManager)context.getSystemService("alarm");
                        alarmmanager.cancel(pendingintent);
                        Calendar calendar1 = TransitUtil.timeStringToCalendar(s1);
                        calendar1.set(12, calendar1.get(12) - j);
                        if (calendar1.before(calendar))
                        {
                            (new AlarmDB(context)).deleteAlarmSetting(i);
                        } else
                        {
                            alarmmanager.set(0, calendar1.getTimeInMillis(), pendingintent);
                        }
                    }
                }
            }
        }
    }

    public void setAlarmAgain()
    {
        AlarmDB alarmdb = new AlarmDB(context);
        Iterator iterator = ((ArrayList)alarmdb.getAlarmList()).iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Bundle bundle = (Bundle)iterator.next();
            setAlarm(bundle);
            int i = bundle.getInt(context.getString(0x7f0d01c5));
            if (alarmdb.countAlarmSettingByAlarmId(i) == 0)
            {
                alarmdb.deleteAlarmData(i);
            }
        } while (true);
    }

}
