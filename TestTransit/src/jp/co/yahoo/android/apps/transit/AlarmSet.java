// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.common.Alarm;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.AlarmCheckListView;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, AlarmConfirm

public class AlarmSet extends TransitBaseActivity
{

    private AlarmCheckListView alarmList;
    private ConditionData conditionData;
    private int length;
    private LinearLayout listLayout;
    private int minutes;
    private NaviSearchData results;
    private int soundInt;
    private SparseArray timeList;

    public AlarmSet()
    {
        minutes = Alarm.ALARM_MINUTES_LIST[2];
        length = Alarm.ALARM_LENGTH_LIST[0];
        soundInt = 0x7f0c0007;
    }

    private int checkAlarmTime(ArrayList arraylist)
    {
        int i = -1;
        Calendar calendar = Calendar.getInstance();
        Iterator iterator = arraylist.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Integer integer = (Integer)iterator.next();
            String s = (String)timeList.get(integer.intValue(), "");
            if (TextUtils.isEmpty(s))
            {
                continue;
            }
            Calendar calendar1 = TransitUtil.timeStringToCalendar(s);
            calendar1.set(12, calendar1.get(12) - minutes);
            if (!calendar1.before(calendar))
            {
                continue;
            }
            i = integer.intValue();
            break;
        } while (true);
        return i;
    }

    private void setAlarmData(Alarm alarm, ArrayList arraylist)
    {
        ArrayList arraylist1 = new ArrayList();
        int i = getResources().getInteger(soundInt);
        Iterator iterator = arraylist.iterator();
        while (iterator.hasNext()) 
        {
            Integer integer = (Integer)iterator.next();
            Bundle bundle = new Bundle();
            bundle.putInt(getString(0x7f0d01cc), minutes);
            bundle.putInt(getString(0x7f0d01c8), length);
            bundle.putInt(getString(0x7f0d0238), i);
            if (integer.intValue() == 0)
            {
                bundle.putInt(getString(0x7f0d0247), getResources().getInteger(0x7f0c000a));
            } else
            {
                bundle.putInt(getString(0x7f0d0247), getResources().getInteger(0x7f0c0009));
            }
            bundle.putInt(getString(0x7f0d01d4), integer.intValue());
            arraylist1.add(bundle);
        }
        alarm.addAlarm(conditionData, results, arraylist1);
        showSetCompleteMessageDialog();
    }

    private void showAlarmList()
    {
        Calendar calendar;
        ArrayList arraylist;
        HashMap hashmap;
        ArrayList arraylist1;
        ArrayList arraylist2;
        ArrayList arraylist3;
        ArrayList arraylist4;
        if (alarmList != null)
        {
            alarmList.removeAllViews();
        }
        AlarmCheckListView alarmchecklistview = new AlarmCheckListView(this);
        alarmList = alarmchecklistview;
        calendar = Calendar.getInstance();
        timeList = new SparseArray();
        arraylist = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)results.routes.get(0)).edges;
        hashmap = results.points;
        arraylist1 = new ArrayList();
        arraylist2 = new ArrayList();
        arraylist3 = new ArrayList();
        arraylist4 = new ArrayList();
        if (arraylist.size() <= 0) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        i = arraylist.size();
        j = 0;
_L11:
        if (j >= i) goto _L2; else goto _L3
_L3:
        arraylist1.add(Integer.valueOf(j));
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(j);
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge1;
        edge1 = null;
        if (j <= 0)
        {
            break MISSING_BLOCK_LABEL_172;
        }
        edge1 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(j - 1);
        String s;
        StringBuilder stringbuilder;
        s = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(edge.startPointTarget)).stationName;
        stringbuilder = new StringBuilder();
        if (j != 0) goto _L5; else goto _L4
_L4:
        if (edge.departureDatetime == null) goto _L5; else goto _L6
_L6:
        String s1;
        String s2;
        s1 = edge.departureDatetime;
        s2 = (new StringBuilder()).append(" ").append(getString(0x7f0d0301)).toString();
_L10:
        Calendar calendar1;
        if (!TextUtils.isEmpty(s1))
        {
            stringbuilder.append(s1.substring(8, 10));
            stringbuilder.append(":");
            stringbuilder.append(s1.substring(10, 12));
            stringbuilder.append(" ");
        }
        stringbuilder.append(s);
        arraylist2.add(stringbuilder.toString());
        arraylist3.add(s2);
        timeList.put(j, s1);
        calendar1 = TransitUtil.timeStringToCalendar(s1);
        calendar1.set(12, -1 + calendar1.get(12));
        if (!calendar1.before(calendar)) goto _L8; else goto _L7
_L7:
        arraylist4.add(Boolean.valueOf(false));
          goto _L9
_L5:
        if (edge1 == null)
        {
            break MISSING_BLOCK_LABEL_548;
        }
        if (edge1.arrivalDatetime == null)
        {
            break MISSING_BLOCK_LABEL_548;
        }
        s1 = edge1.arrivalDatetime;
        s2 = (new StringBuilder()).append(" ").append(getString(0x7f0d0284)).toString();
          goto _L10
_L8:
        try
        {
            arraylist4.add(Boolean.valueOf(true));
        }
        catch (Exception exception)
        {
            arraylist2.add("-");
            arraylist3.add("-");
            arraylist4.add(Boolean.valueOf(false));
            timeList.put(j, "");
        }
          goto _L9
_L2:
        alarmList.setListLabels(arraylist2, arraylist3, arraylist4);
        alarmList.setListItems(arraylist1);
        listLayout.removeAllViews();
        listLayout.addView(alarmList);
        listLayout.setVisibility(0);
        alarmList.showView();
        return;
_L9:
        j++;
          goto _L11
        s1 = "";
        s2 = " ";
          goto _L10
    }

    private void showSetAgainMessageDialog()
    {
        if (isFinishing())
        {
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setMessage(getString(0x7f0d016e)).setNegativeButton(getString(0x7f0d006e), new android.content.DialogInterface.OnClickListener() {

                final AlarmSet this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$0 = AlarmSet.this;
                super();
            }
            }).setPositiveButton(getString(0x7f0d0082), new android.content.DialogInterface.OnClickListener() {

                final AlarmSet this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    Alarm alarm = new Alarm(AlarmSet.this);
                    ArrayList arraylist = alarmList.getCheckItems();
                    alarm.deleteOldestAlarm();
                    setAlarmData(alarm, arraylist);
                    dialoginterface.cancel();
                }

            
            {
                this$0 = AlarmSet.this;
                super();
            }
            }).show();
            return;
        }
    }

    private void showSetCompleteMessageDialog()
    {
        if (isFinishing())
        {
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setMessage(getString(0x7f0d0160)).setTitleInfo(getString(0x7f0d0022)).setPositiveButton(getString(0x7f0d015c), new android.content.DialogInterface.OnClickListener() {

                final AlarmSet this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                    finish();
                }

            
            {
                this$0 = AlarmSet.this;
                super();
            }
            }).show();
            return;
        }
    }

    public void cancelAlarm(View view)
    {
        finish();
    }

    public void confirmAlarm(View view)
    {
        startActivityForResult(new Intent(this, jp/co/yahoo/android/apps/transit/AlarmConfirm), getResources().getInteger(0x7f0c0038));
    }

    protected void delClickListener()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030037);
        setTitle(getString(0x7f0d002f));
        Intent intent = getIntent();
        conditionData = (ConditionData)intent.getSerializableExtra(getString(0x7f0d022c));
        results = (NaviSearchData)intent.getSerializableExtra(getString(0x7f0d0232));
        String as[] = new String[Alarm.ALARM_MINUTES_LIST.length];
        for (int i = 0; i < Alarm.ALARM_MINUTES_LIST.length; i++)
        {
            Object aobj1[] = new Object[1];
            aobj1[0] = Integer.valueOf(Alarm.ALARM_MINUTES_LIST[i]);
            as[i] = getString(0x7f0d0029, aobj1);
        }

        Spinner spinner = (Spinner)findViewById(0x7f0a018c);
        ArrayAdapter arrayadapter = new ArrayAdapter(this, 0x1090008, as);
        arrayadapter.setDropDownViewResource(0x7f030077);
        spinner.setAdapter(arrayadapter);
        spinner.setSelection(2);
        spinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            final AlarmSet this$0;

            public void onItemSelected(AdapterView adapterview, View view, int k, long l)
            {
                minutes = Alarm.ALARM_MINUTES_LIST[k];
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            
            {
                this$0 = AlarmSet.this;
                super();
            }
        });
        String as1[] = new String[Alarm.ALARM_LENGTH_LIST.length];
        for (int j = 0; j < Alarm.ALARM_LENGTH_LIST.length; j++)
        {
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(Alarm.ALARM_LENGTH_LIST[j]);
            as1[j] = getString(0x7f0d0026, aobj);
        }

        Spinner spinner1 = (Spinner)findViewById(0x7f0a018d);
        ArrayAdapter arrayadapter1 = new ArrayAdapter(this, 0x1090008, as1);
        arrayadapter1.setDropDownViewResource(0x7f030077);
        spinner1.setAdapter(arrayadapter1);
        spinner1.setSelection(0);
        spinner1.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            final AlarmSet this$0;

            public void onItemSelected(AdapterView adapterview, View view, int k, long l)
            {
                length = Alarm.ALARM_LENGTH_LIST[k];
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            
            {
                this$0 = AlarmSet.this;
                super();
            }
        });
        listLayout = (LinearLayout)findViewById(0x7f0a018b);
        showAlarmList();
        dispAd(this, "2080294928", "pv");
    }

    public void setAlarm(View view)
    {
        Alarm alarm = new Alarm(this);
        if (alarm.countAlarmData() >= 5)
        {
            showSetAgainMessageDialog();
            return;
        }
        ArrayList arraylist = alarmList.getCheckItems();
        if (arraylist.isEmpty())
        {
            showSimpleErrorMessageDialog(getString(0x7f0d015b));
            return;
        }
        int i = checkAlarmTime(arraylist);
        if (i != -1)
        {
            int j;
            if (i == 0)
            {
                j = 0x7f0d015a;
            } else
            {
                j = 0x7f0d0159;
            }
            showSimpleErrorMessageDialog(getString(j));
            return;
        } else
        {
            setAlarmData(alarm, arraylist);
            return;
        }
    }


/*
    static int access$002(AlarmSet alarmset, int i)
    {
        alarmset.minutes = i;
        return i;
    }

*/


/*
    static int access$102(AlarmSet alarmset, int i)
    {
        alarmset.length = i;
        return i;
    }

*/


}
