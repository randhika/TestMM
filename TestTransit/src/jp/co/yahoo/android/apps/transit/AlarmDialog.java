// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Window;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.common.Alarm;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultActivity

public class AlarmDialog extends Activity
{

    private int alarmDataId;
    private int alarmSettingId;
    private ConditionData conditionData;
    private AlertDialog dialog;
    private Intent imakokoIntent;
    private Intent intent;
    private NaviSearchData results;
    private Vibrator vibrator;
    private int window_option;

    public AlarmDialog()
    {
        window_option = 0x680000;
        alarmSettingId = -1;
        alarmDataId = -1;
        conditionData = null;
        results = null;
    }

    private Intent createImakokoIntent()
    {
        if (conditionData == null || results == null)
        {
            return null;
        } else
        {
            Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/SearchResultActivity);
            intent1.addFlags(0x14000000);
            intent1.putExtra(getString(0x7f0d0231), 0);
            intent1.putExtra(getString(0x7f0d0232), results);
            intent1.putExtra(getString(0x7f0d022c), conditionData);
            intent1.putExtra(getString(0x7f0d020e), true);
            intent1.putExtra(getString(0x7f0d01ca), 1);
            return intent1;
        }
    }

    private String makeTrack(String s)
    {
        return s.replaceAll(getString(0x7f0d0320), getString(0x7f0d0321)).replaceAll(getString(0x7f0d0322), getString(0x7f0d0323)).replaceAll(getString(0x7f0d031e), getString(0x7f0d031f)).replaceAll("/", ",");
    }

    private void showAlertDialog()
    {
        if (intent == null)
        {
            finish();
            return;
        }
        Bundle bundle = intent.getBundleExtra(getString(0x7f0d0234));
        if (bundle == null)
        {
            finish();
            return;
        } else
        {
            alarmSettingId = bundle.getInt(getString(0x7f0d01c5));
            alarmDataId = bundle.getInt(getString(0x7f0d017e));
            conditionData = (ConditionData)intent.getSerializableExtra(getString(0x7f0d022c));
            results = (NaviSearchData)intent.getSerializableExtra(getString(0x7f0d0232));
            showAlertDialog(bundle);
            return;
        }
    }

    private void showAlertDialog(Bundle bundle)
    {
        int i = bundle.getInt(getString(0x7f0d01c8));
        long al[] = new long[2];
        al[0] = 0L;
        al[1] = i * 1000;
        vibrator.vibrate(al, -1);
        String s = getString(0x7f0d0058);
        String s1 = bundle.getString(getString(0x7f0d0241));
        if (s1 == null)
        {
            s1 = "";
        }
        int j = bundle.getInt(getString(0x7f0d01cc));
        int k = bundle.getInt(getString(0x7f0d0247));
        StringBuilder stringbuilder = new StringBuilder(s1);
        Object aobj[];
        long l;
        int i1;
        int j1;
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge;
        PendingIntent pendingintent;
        android.support.v4.app.NotificationCompat.WearableExtender wearableextender;
        android.support.v4.app.NotificationCompat.Builder builder;
        android.support.v4.app.NotificationCompat.Builder builder1;
        TransitDialogBuilder transitdialogbuilder;
        TransitDialogBuilder transitdialogbuilder1;
        String s2;
        android.content.DialogInterface.OnClickListener onclicklistener;
        android.app.AlertDialog.Builder builder2;
        String s3;
        android.content.DialogInterface.OnClickListener onclicklistener1;
        android.app.AlertDialog.Builder builder3;
        android.content.DialogInterface.OnCancelListener oncancellistener;
        if (k == getResources().getInteger(0x7f0c000a))
        {
            stringbuilder.append(getString(0x7f0d0301));
        } else
        {
            stringbuilder.append(getString(0x7f0d0284));
        }
        aobj = new Object[1];
        aobj[0] = Integer.valueOf(j);
        stringbuilder.append(getString(0x7f0d002a, aobj));
        l = System.currentTimeMillis();
        i1 = (int)(l / 1000L);
        j1 = bundle.getInt(getString(0x7f0d01d4));
        edge = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)results.routes.get(0)).edges.get(j1);
        if (!TransitUtil.isEmpty(edge.railDispName))
        {
            stringbuilder.append("\n");
            stringbuilder.append(getString(0x7f0d029f));
            stringbuilder.append(edge.railDispName);
        }
        stringbuilder.append("\n");
        if (!TransitUtil.isEmpty(edge.departureTrackNumber))
        {
            stringbuilder.append((new StringBuilder()).append(edge.departureTrackNumber).append(getString(0x7f0d026c)).toString());
        }
        if (!TransitUtil.isEmpty(edge.departureTrackNumber) && !TransitUtil.isEmpty(edge.arrivalTrackNumber))
        {
            stringbuilder.append(" \u2192 ");
        }
        if (!TransitUtil.isEmpty(edge.arrivalTrackNumber))
        {
            stringbuilder.append((new StringBuilder()).append(edge.arrivalTrackNumber).append(getString(0x7f0d025c)).toString());
        }
        imakokoIntent = createImakokoIntent();
        if (imakokoIntent == null)
        {
            pendingintent = PendingIntent.getActivity(this, i1, new Intent(), 0x8000000);
        } else
        {
            pendingintent = PendingIntent.getActivity(this, i1, imakokoIntent, 0x8000000);
        }
        wearableextender = (new android.support.v4.app.NotificationCompat.WearableExtender()).setBackground(BitmapFactory.decodeResource(getResources(), 0x7f0200ab));
        builder = new android.support.v4.app.NotificationCompat.Builder(this);
        builder1 = builder.setSmallIcon(0x7f0201cc).setContentTitle(s).setContentText(stringbuilder).setTicker(stringbuilder).setContentIntent(pendingintent).setWhen(l).setAutoCancel(true).extend(wearableextender);
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            builder1.setLargeIcon(BitmapFactory.decodeResource(getResources(), 0x7f0201ee));
        }
        ((NotificationManager)getSystemService("notification")).notify(i1, builder1.build());
        transitdialogbuilder = new TransitDialogBuilder(this);
        transitdialogbuilder1 = transitdialogbuilder.setTitleIcon(s, 0x7f02019c).setMessage(stringbuilder);
        s2 = getString(0x7f0d006f);
        onclicklistener = new android.content.DialogInterface.OnClickListener() {

            final AlarmDialog this$0;

            public void onClick(DialogInterface dialoginterface, int k1)
            {
                dialoginterface.cancel();
                if (imakokoIntent != null)
                {
                    startActivity(imakokoIntent);
                }
                finish();
            }

            
            {
                this$0 = AlarmDialog.this;
                super();
            }
        };
        builder2 = transitdialogbuilder1.setPositiveButton(s2, onclicklistener);
        s3 = getString(0x7f0d0072);
        onclicklistener1 = new android.content.DialogInterface.OnClickListener() {

            final AlarmDialog this$0;

            public void onClick(DialogInterface dialoginterface, int k1)
            {
                dialoginterface.cancel();
                finish();
            }

            
            {
                this$0 = AlarmDialog.this;
                super();
            }
        };
        builder3 = builder2.setNegativeButton(s3, onclicklistener1);
        oncancellistener = new android.content.DialogInterface.OnCancelListener() {

            final AlarmDialog this$0;

            public void onCancel(DialogInterface dialoginterface)
            {
                finish();
            }

            
            {
                this$0 = AlarmDialog.this;
                super();
            }
        };
        dialog = builder3.setOnCancelListener(oncancellistener).show();
    }

    public void finish()
    {
        if (vibrator != null)
        {
            vibrator.cancel();
        }
        super.finish();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        intent = getIntent();
        vibrator = (Vibrator)getSystemService("vibrator");
        getWindow().addFlags(window_option);
        showAlertDialog();
    }

    protected void onDestroy()
    {
        super.onDestroy();
        getWindow().clearFlags(window_option);
    }

    protected void onNewIntent(Intent intent1)
    {
        super.onNewIntent(intent1);
        getWindow().addFlags(window_option);
        if (alarmSettingId != -1 && alarmDataId != -1)
        {
            (new Alarm(this)).deleteAlarmSettingById(alarmSettingId, alarmDataId);
        }
        if (dialog != null)
        {
            try
            {
                dialog.dismiss();
            }
            catch (IllegalArgumentException illegalargumentexception) { }
            dialog = null;
        }
        if (vibrator != null)
        {
            vibrator.cancel();
        }
        intent = intent1;
        showAlertDialog();
    }

    protected void onPause()
    {
        super.onPause();
        if (alarmSettingId != -1 && alarmDataId != -1)
        {
            (new Alarm(this)).deleteAlarmSettingById(alarmSettingId, alarmDataId);
        }
    }

}
