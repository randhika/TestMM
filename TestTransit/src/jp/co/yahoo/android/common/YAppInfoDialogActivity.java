// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import java.util.List;
import java.util.Map;

// Referenced classes of package jp.co.yahoo.android.common:
//            YAppInfoData, YLogger

public class YAppInfoDialogActivity extends Activity
{

    private static final String APP_INFO_DATA = "app_info_data";
    private static final String BUTTON_ORDER_1 = "1";
    private static final String BUTTON_ORDER_2 = "2";
    private static final String BUTTON_ORDER_3 = "3";
    private static final String BUTTON_TYPE_CANCEL = "cancel";
    private static final String CALLING_ACTIVITY_NAME = "calling_activity_name";
    private static final String CANCELED_VERSION = "canceled_version";
    public static final String CLICKED_BUTTON_ACTION = "clicked_button_action";
    public static final String CLICKED_BUTTON_ORDER = "clicked_button_order";
    public static final String CLICKED_BUTTON_TYPE = "clicked_button_type";
    private static final String REQ_CODE = "req_code";
    private static final String SKIP_ACTION = "skip_action";
    private Intent mIntent;
    private boolean mIsForce;

    public YAppInfoDialogActivity()
    {
        mIsForce = false;
        mIntent = new Intent();
    }

    public static void showUpdateDialog(Activity activity, YAppInfoData yappinfodata)
    {
        showUpdateDialog(activity, yappinfodata, true, false);
    }

    public static void showUpdateDialog(Activity activity, YAppInfoData yappinfodata, boolean flag)
    {
        showUpdateDialog(activity, yappinfodata, true, flag);
    }

    private static void showUpdateDialog(Activity activity, YAppInfoData yappinfodata, boolean flag, boolean flag1)
    {
_L2:
        return;
        if (yappinfodata == null || "".equals(yappinfodata.minOsVersion) || "".equals(yappinfodata.latestAppVersion)) goto _L2; else goto _L1
_L1:
        boolean flag2;
        String s = activity.getSharedPreferences("PREFS_CHECK_VERSION", 0).getString("canceled_version", "0");
        YLogger.log((new StringBuilder()).append("canceledVersion = ").append(s).toString());
        if (!yappinfodata.hasUpdate)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        flag2 = true;
_L4:
        if (flag2)
        {
            Intent intent = new Intent(activity.getApplicationContext(), jp/co/yahoo/android/common/YAppInfoDialogActivity);
            intent.putExtra("app_info_data", yappinfodata);
            intent.putExtra("skip_action", flag1);
            intent.putExtra("calling_activity_name", activity.getClass().getName());
            intent.addFlags(0x20000);
            activity.startActivity(intent);
            return;
        }
        if (true) goto _L2; else goto _L3
_L3:
        if (yappinfodata.isUnderminVersion || yappinfodata.isForceUpdateDate)
        {
            flag2 = true;
        } else
        {
            boolean flag3 = yappinfodata.hasNewUpdate;
            flag2 = false;
            if (flag3)
            {
                flag2 = true;
            }
        }
          goto _L4
        if (true) goto _L2; else goto _L5
_L5:
    }

    public static void showUpdateDialogIfNotCanceled(Activity activity, YAppInfoData yappinfodata)
    {
        showUpdateDialog(activity, yappinfodata, false, false);
    }

    public static void showUpdateDialogIfNotCanceled(Activity activity, YAppInfoData yappinfodata, boolean flag)
    {
        showUpdateDialog(activity, yappinfodata, false, flag);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        final YAppInfoData xmlData = (YAppInfoData)getIntent().getParcelableExtra("app_info_data");
        List list = xmlData.buttonData;
        AlertDialog alertdialog = (new android.app.AlertDialog.Builder(this)).setTitle(xmlData.title).setMessage(xmlData.message).setCancelable(false).create();
        int i = 0;
        while (i < list.size()) 
        {
            final Map data = (Map)list.get(i);
            final String order = (String)data.get("order");
            final String type = (String)data.get("type");
            if (order == null || type == null)
            {
                finish();
                return;
            }
            final boolean isCancel = "cancel".equals(type);
            byte byte0 = -1;
            if ("1".equals(order))
            {
                byte0 = -1;
            } else
            if ("2".equals(order))
            {
                byte0 = -2;
            } else
            if ("3".equals(order))
            {
                byte0 = -3;
            }
            alertdialog.setButton(byte0, (CharSequence)data.get("text"), new android.content.DialogInterface.OnClickListener() {

                final YAppInfoDialogActivity this$0;
                final Map val$data;
                final boolean val$isCancel;
                final String val$order;
                final String val$type;

                public void onClick(DialogInterface dialoginterface, int j)
                {
                    String s = (String)data.get("action");
                    mIntent.putExtra("clicked_button_action", s);
                    mIntent.putExtra("clicked_button_order", order);
                    mIntent.putExtra("clicked_button_type", type);
                    if (isCancel)
                    {
                        dialoginterface.cancel();
                    }
                }

            
            {
                this$0 = YAppInfoDialogActivity.this;
                data = map;
                order = s;
                type = s1;
                isCancel = flag;
                super();
            }
            });
            i++;
        }
        alertdialog.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            final YAppInfoDialogActivity this$0;
            final YAppInfoData val$xmlData;

            public void onCancel(DialogInterface dialoginterface)
            {
                android.content.SharedPreferences.Editor editor = getSharedPreferences("PREFS_CHECK_VERSION", 0).edit();
                editor.putString("canceled_version", xmlData.latestAppVersion);
                editor.commit();
            }

            
            {
                this$0 = YAppInfoDialogActivity.this;
                xmlData = yappinfodata;
                super();
            }
        });
        alertdialog.setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

            final YAppInfoDialogActivity this$0;

            public boolean onKey(DialogInterface dialoginterface, int j, KeyEvent keyevent)
            {
                return j == 84;
            }

            
            {
                this$0 = YAppInfoDialogActivity.this;
                super();
            }
        });
        alertdialog.setOnDismissListener(new android.content.DialogInterface.OnDismissListener() {

            final YAppInfoDialogActivity this$0;
            final YAppInfoData val$xmlData;

            public void onDismiss(DialogInterface dialoginterface)
            {
                if (xmlData.isUnderminVersion || xmlData.isForceUpdateDate)
                {
                    mIsForce = true;
                }
                (new Handler()).postDelayed(new Runnable() {

                    final _cls4 this$1;

                    public void run()
                    {
                        mIntent.setClassName(getApplicationContext(), getIntent().getStringExtra("calling_activity_name"));
                        mIntent.addFlags(0x20000);
                        mIntent.putExtra("req_code", jp/co/yahoo/android/common/YAppInfoDialogActivity.hashCode());
                        startActivity(mIntent);
                        finish();
                    }

            
            {
                this$1 = _cls4.this;
                super();
            }
                }, 200L);
            }

            
            {
                this$0 = YAppInfoDialogActivity.this;
                xmlData = yappinfodata;
                super();
            }
        });
        alertdialog.show();
    }

    protected void onDestroy()
    {
        super.onDestroy();
        String s = mIntent.getStringExtra("clicked_button_action");
        if (!getIntent().getBooleanExtra("skip_action", false) && (s.startsWith("market://") || s.startsWith("http://") || s.startsWith("https://")))
        {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(s));
            intent.addFlags(0x10000000);
            startActivity(intent);
        }
        if (mIsForce)
        {
            moveTaskToBack(true);
        }
    }



/*
    static boolean access$102(YAppInfoDialogActivity yappinfodialogactivity, boolean flag)
    {
        yappinfodialogactivity.mIsForce = flag;
        return flag;
    }

*/
}
