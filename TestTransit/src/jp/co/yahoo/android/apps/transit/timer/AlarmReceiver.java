// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;
import jp.co.yahoo.android.apps.transit.timer.common.Alerm;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity, TimetableAutoupdateService

public class AlarmReceiver extends BroadcastReceiver
{

    private Alerm alerm;
    private Context context;
    private Intent intent;

    public AlarmReceiver()
    {
    }

    private void setAlerm()
    {
        ArrayList arraylist = alerm.getAllAlerm();
        if (arraylist != null)
        {
            Iterator iterator = arraylist.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                AlermData alermdata = (AlermData)iterator.next();
                if (alermdata.isSetting())
                {
                    alerm.setAlerm(alermdata, true);
                }
            } while (true);
        }
    }

    private void setNotice(String s, Intent intent1)
    {
        NotificationManager notificationmanager = (NotificationManager)context.getSystemService("notification");
        if (s == null)
        {
            s = "";
        }
        String s1 = s;
        if (intent1 == null)
        {
            intent1 = new Intent();
        }
        PendingIntent pendingintent = PendingIntent.getActivity(context, 0, intent1, 0);
        android.support.v4.app.NotificationCompat.Builder builder = (new android.support.v4.app.NotificationCompat.Builder(context)).setSmallIcon(0x7f0201cc).setContentTitle(context.getString(0x7f0d00b8)).setContentText(s1).setTicker(s1).setContentIntent(pendingintent).setWhen(System.currentTimeMillis()).setAutoCancel(true);
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), 0x7f0201ee));
        }
        notificationmanager.notify(1, builder.build());
    }

    private void startAlermStartup()
    {
        int i = intent.getIntExtra("id", -1);
        AlermData alermdata;
        if (i >= 0)
        {
            if ((alermdata = alerm.getAlerm(i)) != null && alerm.isStartToday(i))
            {
                if (alermdata.getRepeat().equals("0"))
                {
                    if (alermdata.getType() == AlermData.TYPE_START)
                    {
                        alermdata.setSetting(false);
                        alerm.setAlerm(alermdata, true);
                    } else
                    if (alermdata.getType() == AlermData.TYPE_ALERT)
                    {
                        alerm.delAlarm(alermdata);
                    }
                }
                if (alermdata.getType() == AlermData.TYPE_UPDATE)
                {
                    updateData();
                    return;
                } else
                {
                    Intent intent1 = new Intent(context, jp/co/yahoo/android/apps/transit/timer/CountdownActivity);
                    intent1.putExtra(context.getString(0x7f0d023d), alermdata);
                    intent1.setFlags(0x10000000);
                    context.startActivity(intent1);
                    setNotice(alermdata.getLine(), null);
                    return;
                }
            }
        }
    }

    private void updateData()
    {
        int i = alerm.getUpdateAlerm().getStartTime();
        Calendar calendar = Calendar.getInstance();
        if (i != 60 * (60 * calendar.get(11)) + 60 * calendar.get(12))
        {
            return;
        } else
        {
            Intent intent1 = new Intent(context, jp/co/yahoo/android/apps/transit/timer/TimetableAutoupdateService);
            context.startService(intent1);
            return;
        }
    }

    public void onReceive(Context context1, Intent intent1)
    {
        context = context1;
        intent = intent1;
        alerm = new Alerm(context1);
        String s = intent1.getAction();
        if (s == null || s.equals(""))
        {
            startAlermStartup();
            return;
        }
        if (s.equals(context1.getString(0x7f0d017c)))
        {
            startAlermStartup();
            return;
        }
        if (s.equals("android.intent.action.BOOT_COMPLETED"))
        {
            setAlerm();
            return;
        } else
        {
            startAlermStartup();
            return;
        }
    }
}
