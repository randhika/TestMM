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
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import java.util.Calendar;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoDetailActivity

public class PushDiainfoDialog extends Activity
{

    private Intent diainfoIntent;
    private AlertDialog dialog;
    private Intent intent;
    private int window_option;

    public PushDiainfoDialog()
    {
        window_option = 0x680000;
    }

    private void showAlertDialog()
    {
        if (intent == null)
        {
            finish();
            return;
        }
        TransitUtil.setCheckDiainfo(this, true);
        Bundle bundle = intent.getBundleExtra(getString(0x7f0d01ad));
        String s = bundle.getString("rail-id");
        String s1 = bundle.getString("range-id");
        if (TextUtils.isEmpty(s))
        {
            s = "0";
        }
        if (TextUtils.isEmpty(s1))
        {
            s1 = "0";
        }
        int i = 10000 * Integer.parseInt(s) + Integer.parseInt(s1);
        String s2 = getString(0x7f0d0058);
        String s3;
        String s4;
        Calendar calendar;
        long l;
        Bundle bundle1;
        Intent intent1;
        PendingIntent pendingintent;
        android.support.v4.app.NotificationCompat.WearableExtender wearableextender;
        android.support.v4.app.NotificationCompat.Builder builder;
        android.support.v4.app.NotificationCompat.Builder builder1;
        TransitDialogBuilder transitdialogbuilder;
        android.app.AlertDialog.Builder builder2;
        android.content.DialogInterface.OnCancelListener oncancellistener;
        if (bundle.containsKey("message"))
        {
            s3 = bundle.getString("message");
        } else
        {
            s3 = getString(0x7f0d00f7);
        }
        if (bundle.containsKey("time"))
        {
            s4 = bundle.getString("time");
        } else
        {
            s4 = null;
        }
        if (s4 != null)
        {
            calendar = TransitUtil.toCalendar(s4);
        } else
        {
            calendar = null;
        }
        if (calendar != null)
        {
            l = calendar.getTimeInMillis();
        } else
        {
            l = System.currentTimeMillis();
        }
        bundle1 = new Bundle();
        bundle1.putString(getString(0x7f0d01d9), s);
        bundle1.putString(getString(0x7f0d01dc), s1);
        intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/DiainfoDetailActivity);
        diainfoIntent = intent1;
        diainfoIntent.addFlags(0x14000000);
        diainfoIntent.putExtra(getString(0x7f0d022c), bundle1);
        diainfoIntent.putExtra(getString(0x7f0d01ca), 5);
        diainfoIntent.putExtra(getString(0x7f0d01be), true);
        pendingintent = PendingIntent.getActivity(this, i, diainfoIntent, 0x8000000);
        wearableextender = (new android.support.v4.app.NotificationCompat.WearableExtender()).setBackground(BitmapFactory.decodeResource(getResources(), 0x7f0200ac));
        builder = new android.support.v4.app.NotificationCompat.Builder(this);
        builder1 = builder.setSmallIcon(0x7f0201cc).setContentTitle(s2).setContentText(s3).setTicker(s3).setContentIntent(pendingintent).setWhen(l).setDefaults(3).setAutoCancel(true).extend(wearableextender);
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            builder1.setLargeIcon(BitmapFactory.decodeResource(getResources(), 0x7f0201ee));
        }
        ((NotificationManager)getSystemService("notification")).notify(i, builder1.build());
        transitdialogbuilder = new TransitDialogBuilder(this);
        builder2 = transitdialogbuilder.setTitleIcon(s2, 0x7f02019c).setMessage(s3).setPositiveButton(getString(0x7f0d00f3), new android.content.DialogInterface.OnClickListener() {

            final PushDiainfoDialog this$0;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                dialoginterface.cancel();
                startActivity(diainfoIntent);
                finish();
            }

            
            {
                this$0 = PushDiainfoDialog.this;
                super();
            }
        }).setNegativeButton(getString(0x7f0d0072), new android.content.DialogInterface.OnClickListener() {

            final PushDiainfoDialog this$0;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                dialoginterface.cancel();
                finish();
            }

            
            {
                this$0 = PushDiainfoDialog.this;
                super();
            }
        });
        oncancellistener = new android.content.DialogInterface.OnCancelListener() {

            final PushDiainfoDialog this$0;

            public void onCancel(DialogInterface dialoginterface)
            {
                finish();
            }

            
            {
                this$0 = PushDiainfoDialog.this;
                super();
            }
        };
        dialog = builder2.setOnCancelListener(oncancellistener).show();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        intent = getIntent();
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
        if (dialog != null)
        {
            try
            {
                dialog.dismiss();
            }
            catch (IllegalArgumentException illegalargumentexception) { }
            dialog = null;
        }
        intent = intent1;
        showAlertDialog();
    }

}
