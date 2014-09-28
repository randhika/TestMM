// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.agreementlib;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Referenced classes of package jp.co.yahoo.android.common.agreementlib:
//            YCSVReader, YJAgreementDialog

public class YJAgreementHelper
{
    public static interface OnAggreementListener
    {

        public abstract void onAgree();

        public abstract void onDisagree();
    }

    private static class YJAgreementPermission
    {

        public String _description;
        public String _label;

        public boolean equals(YJAgreementPermission yjagreementpermission)
        {
            while (_label == null || _description == null || !_label.equals(yjagreementpermission._label) || !_description.equals(yjagreementpermission._description)) 
            {
                return false;
            }
            return true;
        }

        public YJAgreementPermission(String s, String s1)
        {
            _label = s;
            _description = s1;
        }
    }


    public YJAgreementHelper()
    {
    }

    private static void copyToClipboard(Context context, String s)
    {
        if (android.os.Build.VERSION.SDK_INT < 11)
        {
            ((ClipboardManager)context.getSystemService("clipboard")).setText(s);
            return;
        } else
        {
            ((android.content.ClipboardManager)context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", s));
            return;
        }
    }

    protected static void dumpPermission(Activity activity)
    {
        PackageManager packagemanager = activity.getPackageManager();
        String as[];
        int i;
        as = packagemanager.getPackageInfo(activity.getPackageName(), 4096).requestedPermissions;
        i = as.length;
        int j = 0;
_L2:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        PermissionInfo permissioninfo = packagemanager.getPermissionInfo(as[j], 0);
        Log.d("yahoo", "/---------------------------");
        Log.d("yahoo", as[j]);
        Log.d("yahoo", (new StringBuilder()).append("\u30E9\u30D9\u30EB: ").append(activity.getString(permissioninfo.labelRes)).toString());
        Log.d("yahoo", (new StringBuilder()).append("\u8A73\u7D30: ").append(permissioninfo.loadDescription(packagemanager)).toString());
        PermissionGroupInfo permissiongroupinfo = packagemanager.getPermissionGroupInfo(permissioninfo.group, 0);
        Log.d("yahoo", (new StringBuilder()).append("\u30B0\u30EB\u30FC\u30D7: ").append(permissiongroupinfo.loadDescription(packagemanager).toString()).toString());
        Log.d("yahoo", (new StringBuilder()).append("\u30B0\u30EB\u30FC\u30D7\u30E9\u30D9\u30EB: ").append(activity.getString(permissiongroupinfo.labelRes)).toString());
        Log.d("yahoo", "---------------------------/");
        j++;
        if (true) goto _L2; else goto _L1
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        namenotfoundexception.printStackTrace();
_L1:
    }

    private static boolean duplicateCheck(YJAgreementPermission yjagreementpermission, List list)
    {
        if (yjagreementpermission != null && list != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int i = list.size();
        int j = 0;
label0:
        do
        {
label1:
            {
                if (j >= i)
                {
                    break label1;
                }
                if (yjagreementpermission.equals((YJAgreementPermission)list.get(j)))
                {
                    break label0;
                }
                j++;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        return true;
    }

    private static Drawable getAppIcon(Activity activity, PackageManager packagemanager)
    {
        Drawable drawable;
        try
        {
            drawable = packagemanager.getApplicationIcon(activity.getPackageName());
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
            return null;
        }
        return drawable;
    }

    private static String getAppLabel(Activity activity, PackageManager packagemanager)
    {
        return (String)packagemanager.getApplicationLabel(activity.getApplicationInfo());
    }

    private static List getPermissionList(Activity activity)
    {
        HashMap hashmap;
        ArrayList arraylist;
        hashmap = readPermissionListFromCSV(activity.getApplicationContext());
        arraylist = new ArrayList();
        PackageInfo packageinfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 4096);
        String as[] = packageinfo.requestedPermissions;
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            YJAgreementPermission yjagreementpermission = (YJAgreementPermission)hashmap.get(as[j]);
            if (yjagreementpermission != null && validationCheck(yjagreementpermission, arraylist))
            {
                arraylist.add(yjagreementpermission);
            }
        }

        break MISSING_BLOCK_LABEL_104;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        namenotfoundexception.printStackTrace();
        arraylist = null;
        return arraylist;
    }

    private static HashMap readPermissionListFromCSV(Context context)
    {
        ArrayList arraylist = (new YCSVReader()).readCSV(context, R.raw.permissions);
        HashMap hashmap = new HashMap();
        int i = arraylist.size();
        int j = 0;
        while (j < i) 
        {
            String as[] = (String[])arraylist.get(j);
            String s = as[0];
            IOException ioexception;
            String s1;
            String s2;
            if (as.length >= 2)
            {
                s1 = as[1];
            } else
            {
                s1 = null;
            }
            if (as.length >= 3)
            {
                s2 = as[2];
            } else
            {
                s2 = null;
            }
            hashmap.put(s, new YJAgreementPermission(s1, s2));
            j++;
        }
        break MISSING_BLOCK_LABEL_117;
        ioexception;
        ioexception.printStackTrace();
        hashmap = null;
        return hashmap;
    }

    public static void show(Activity activity, OnAggreementListener onaggreementlistener)
    {
        PackageManager packagemanager = activity.getPackageManager();
        String s = getAppLabel(activity, packagemanager);
        android.graphics.Bitmap bitmap = ((BitmapDrawable)getAppIcon(activity, packagemanager)).getBitmap();
        YJAgreementDialog yjagreementdialog = new YJAgreementDialog(activity);
        yjagreementdialog.setTitle(s);
        yjagreementdialog.setIcon(bitmap);
        yjagreementdialog.setCancelable(false);
        List list = getPermissionList(activity);
        int i = list.size();
        for (int j = 0; j < i; j++)
        {
            YJAgreementPermission yjagreementpermission = (YJAgreementPermission)list.get(j);
            yjagreementdialog.addPermission((new StringBuilder()).append("\u30FB").append(yjagreementpermission._label).toString(), yjagreementpermission._description);
        }

        Context context = activity.getApplicationContext();
        String s1 = context.getString(R.string.yjcommon_agreement_privacy_policy_url);
        Object aobj[] = new Object[2];
        aobj[0] = context.getString(R.string.yjcommon_agreement_privacy_policy);
        aobj[1] = s1;
        yjagreementdialog.setPrivacyPolicyText(String.format("%s\n%s", aobj));
        yjagreementdialog.setPrivacyPolicyLongClickListener(new android.view.View.OnLongClickListener(context, s1) {

            final Context val$context;
            final String val$privacyPolicyUrl;

            public boolean onLongClick(View view)
            {
                YJAgreementHelper.copyToClipboard(context, privacyPolicyUrl);
                Toast.makeText(context, context.getString(R.string.yjcommon_agreement_privacy_policy_url_copied), 1).show();
                return true;
            }

            
            {
                context = context1;
                privacyPolicyUrl = s;
                super();
            }
        });
        yjagreementdialog.setPositiveButton("\u540C\u610F\u3059\u308B", new android.view.View.OnClickListener(onaggreementlistener) {

            final OnAggreementListener val$listener;

            public void onClick(View view)
            {
                if (listener != null)
                {
                    listener.onAgree();
                }
            }

            
            {
                listener = onaggreementlistener;
                super();
            }
        });
        yjagreementdialog.setNegativeButton("\u540C\u610F\u3057\u306A\u3044", new android.view.View.OnClickListener(onaggreementlistener) {

            final OnAggreementListener val$listener;

            public void onClick(View view)
            {
                if (listener != null)
                {
                    listener.onDisagree();
                }
            }

            
            {
                listener = onaggreementlistener;
                super();
            }
        });
        yjagreementdialog.show();
    }

    private static boolean validationCheck(YJAgreementPermission yjagreementpermission, List list)
    {
        while (yjagreementpermission == null || TextUtils.isEmpty(yjagreementpermission._label) || TextUtils.isEmpty(yjagreementpermission._description) || !duplicateCheck(yjagreementpermission, list)) 
        {
            return false;
        }
        return true;
    }

}
