// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.Transit;
import jp.co.yahoo.android.apps.transit.TransitBaseActivity;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

public class CreateShortcut
{
    public static interface OnShortcutCretaeListener
    {

        public abstract void onCancel();

        public abstract void onCreate(Intent intent);
    }


    private boolean bBroad;
    private Context context;

    public CreateShortcut(Context context1)
    {
        context = null;
        bBroad = true;
        context = context1;
    }

    public Intent saveShortCut(String s, ConditionData conditiondata)
    {
        int i = conditiondata.searchType;
        if (i != 2) goto _L2; else goto _L1
_L1:
        conditiondata.startName = context.getString(0x7f0d0289);
_L4:
        conditiondata.paramProp = "transit";
        conditiondata.type = context.getResources().getInteger(0x7f0c006b);
        conditiondata.year = 0;
        conditiondata.month = 0;
        conditiondata.day = 0;
        conditiondata.hour = 0;
        conditiondata.minite = 0;
        Uri uri = TransitUtil.condToUri(conditiondata, context);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClassName(context, jp/co/yahoo/android/apps/transit/Transit.getName());
        intent.setFlags(0x14000000);
        intent.putExtra("uri", uri.toString());
        Intent intent1;
        try
        {
            intent1 = new Intent();
            intent1.putExtra("android.intent.extra.shortcut.INTENT", intent);
            intent1.putExtra("android.intent.extra.shortcut.NAME", s);
            intent1.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", android.content.Intent.ShortcutIconResource.fromContext(context, 0x7f02029b));
            intent1.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            if (bBroad)
            {
                context.sendBroadcast(intent1);
                Toast.makeText(context, context.getString(0x7f0d04e4), 1).show();
            }
        }
        catch (Exception exception)
        {
            Toast.makeText(context, context.getString(0x7f0d04e3), 1).show();
            return null;
        }
        return intent1;
_L2:
        if (i == 3)
        {
            conditiondata.goalName = context.getString(0x7f0d0289);
        } else
        if (i == 4)
        {
            conditiondata.startName = context.getString(0x7f0d0289);
            conditiondata.goalName = context.getString(0x7f0d028b);
            conditiondata.goalLon = null;
            conditiondata.goalLat = null;
        } else
        if (i == 5)
        {
            conditiondata.startName = context.getString(0x7f0d0289);
            conditiondata.goalName = context.getString(0x7f0d0338);
            conditiondata.goalLon = null;
            conditiondata.goalLat = null;
        } else
        if (i == 6)
        {
            conditiondata.startName = context.getString(0x7f0d0289);
            conditiondata.goalName = context.getString(0x7f0d02a4);
            conditiondata.goalLon = null;
            conditiondata.goalLat = null;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void setBroadcastMode(boolean flag)
    {
        bBroad = flag;
    }

    public void showShortcutDialog(ConditionData conditiondata)
    {
        showShortcutDialog(conditiondata, null);
    }

    public void showShortcutDialog(final ConditionData conditionData, final OnShortcutCretaeListener onOKLisntener)
    {
        LinearLayout linearlayout = (LinearLayout)((LayoutInflater)context.getSystemService("layout_inflater")).inflate(0x7f0300a7, null);
        TextView textview = (TextView)linearlayout.findViewById(0x7f0a0316);
        TextView textview1 = (TextView)linearlayout.findViewById(0x7f0a0317);
        TextView textview2 = (TextView)linearlayout.findViewById(0x7f0a0318);
        String s = context.getString(0x7f0d0302);
        String s1 = context.getString(0x7f0d0285);
        String s2 = context.getString(0x7f0d032b);
        int i = conditionData.searchType;
        String s3;
        String s4;
        String s5;
        String s6;
        String s7;
        final EditText shortcutName;
        android.app.AlertDialog.Builder builder;
        String s8;
        android.content.DialogInterface.OnClickListener onclicklistener;
        android.app.AlertDialog.Builder builder1;
        String s9;
        android.content.DialogInterface.OnClickListener onclicklistener1;
        if (i == 2)
        {
            s4 = context.getString(0x7f0d0289);
            s3 = conditionData.goalName;
        } else
        if (i == 3)
        {
            s3 = context.getString(0x7f0d0289);
            s4 = conditionData.startName;
        } else
        if (i == 4)
        {
            s3 = context.getString(0x7f0d028b);
            s4 = context.getString(0x7f0d0289);
        } else
        if (i == 5)
        {
            s3 = context.getString(0x7f0d0338);
            s4 = context.getString(0x7f0d0289);
        } else
        if (i == 6)
        {
            s3 = context.getString(0x7f0d02a4);
            s4 = context.getString(0x7f0d0289);
        } else
        {
            s3 = conditionData.goalName;
            s4 = conditionData.startName;
        }
        (new StringBuilder()).append(s4).append("\uFF5E").append(s3).toString();
        s5 = (new StringBuilder()).append(s).append(s4).toString();
        s6 = (new StringBuilder()).append(s1).append(s3).toString();
        textview.setText(s5);
        textview1.setText(s6);
        if (conditionData.viaName != null && conditionData.viaName.size() > 0)
        {
            String s10 = TransitUtil.join(conditionData.viaName, ",");
            textview2.setText((new StringBuilder()).append(s2).append(s10).toString());
        } else
        {
            textview2.setVisibility(8);
        }
        s7 = (new StringBuilder()).append(s4).append("\uFF5E").append(s3).toString();
        shortcutName = (EditText)linearlayout.findViewById(0x7f0a0319);
        shortcutName.setText(s7);
        builder = (new TransitDialogBuilder(context)).setTitle(context.getString(0x7f0d02ff)).setView(linearlayout);
        s8 = context.getString(0x7f0d0074);
        onclicklistener = new android.content.DialogInterface.OnClickListener() {

            final CreateShortcut this$0;
            final ConditionData val$conditionData;
            final OnShortcutCretaeListener val$onOKLisntener;
            final EditText val$shortcutName;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                ((TransitBaseActivity)context).touchTapRD((new StringBuilder()).append(context.getString(0x7f0d0419)).append("/").append(context.getString(0x7f0d0400)).toString());
                Intent intent = saveShortCut(shortcutName.getText().toString(), conditionData);
                dialoginterface.dismiss();
                if (onOKLisntener != null)
                {
                    onOKLisntener.onCreate(intent);
                }
            }

            
            {
                this$0 = CreateShortcut.this;
                shortcutName = edittext;
                conditionData = conditiondata;
                onOKLisntener = onshortcutcretaelistener;
                super();
            }
        };
        builder1 = builder.setPositiveButton(s8, onclicklistener);
        s9 = context.getString(0x7f0d0071);
        onclicklistener1 = new android.content.DialogInterface.OnClickListener() {

            final CreateShortcut this$0;
            final OnShortcutCretaeListener val$onOKLisntener;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                ((TransitBaseActivity)context).touchTapRD((new StringBuilder()).append(context.getString(0x7f0d0419)).append("/").append(context.getString(0x7f0d03cd)).toString());
                dialoginterface.dismiss();
                if (onOKLisntener != null)
                {
                    onOKLisntener.onCancel();
                }
            }

            
            {
                this$0 = CreateShortcut.this;
                onOKLisntener = onshortcutcretaelistener;
                super();
            }
        };
        builder1.setNegativeButton(s9, onclicklistener1).show();
    }

}
