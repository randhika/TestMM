// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
import jp.co.yahoo.android.apps.transit.TransitBaseActivity;
import jp.co.yahoo.android.apps.transit.timer.CountdownActivity;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

public class SettingShortcut
{

    private static final String PREF_CONDITION = "setting";
    private static final String PREF_CONDITION_KEY = "setting_shortcut";
    private Context context;
    private SharedPreferences pref;

    public SettingShortcut(Context context1)
    {
        pref = null;
        context = context1;
        pref = context1.getSharedPreferences("setting", 0);
    }

    public static Intent getShortcut(Context context1)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClassName(context1, jp/co/yahoo/android/apps/transit/timer/CountdownActivity.getName());
        intent.setFlags(0x14000000);
        intent.putExtra(context1.getString(0x7f0d01ca), 7);
        intent.putExtra(context1.getString(0x7f0d01c7), true);
        Intent intent1 = new Intent();
        intent1.putExtra("android.intent.extra.shortcut.INTENT", intent);
        intent1.putExtra("android.intent.extra.shortcut.NAME", context1.getString(0x7f0d0349));
        intent1.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", android.content.Intent.ShortcutIconResource.fromContext(context1, 0x7f0201d9));
        intent1.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        return intent1;
    }

    public void create()
    {
        ((TransitBaseActivity)context).touchRD(context.getString(0x7f0d055a));
        Intent intent = getShortcut(context);
        try
        {
            context.sendBroadcast(intent);
            Toast.makeText(context, context.getString(0x7f0d04e4), 1).show();
            return;
        }
        catch (Exception exception)
        {
            Toast.makeText(context, context.getString(0x7f0d04e3), 1).show();
        }
    }

    public boolean isShowed()
    {
        return pref.getBoolean("setting_shortcut", false);
    }

    public void showCreate(int i, int j)
    {
        android.content.SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("setting_shortcut", true);
        editor.commit();
        (new TransitDialogBuilder(context)).setTitle(context.getString(i)).setMessage(context.getString(j)).setPositiveButton(context.getString(0x7f0d0068), new android.content.DialogInterface.OnClickListener() {

            final SettingShortcut this$0;

            public void onClick(DialogInterface dialoginterface, int k)
            {
                create();
            }

            
            {
                this$0 = SettingShortcut.this;
                super();
            }
        }).setNegativeButton(context.getString(0x7f0d0071), new android.content.DialogInterface.OnClickListener() {

            final SettingShortcut this$0;

            public void onClick(DialogInterface dialoginterface, int k)
            {
                dialoginterface.cancel();
            }

            
            {
                this$0 = SettingShortcut.this;
                super();
            }
        }).show();
    }
}
