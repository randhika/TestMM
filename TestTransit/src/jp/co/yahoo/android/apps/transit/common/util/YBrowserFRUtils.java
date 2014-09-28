// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

public class YBrowserFRUtils
{

    private static final String EXTRA_KEY_FR = "jp.co.yahoo.android.ybrowser.extra.FR";
    private static final String PKG_YBROWSER = "jp.co.yahoo.android.ybrowser";
    private static final String PREF_KEY_RECOMMENDED = "recommended";
    private static final String PREF_NAME = "ybrowser";
    private static final String RECOMMEND_CHECKBOX = "\u4ECA\u5F8C\u3053\u306E\u30C0\u30A4\u30A2\u30ED\u30B0\u3092\u8868\u793A\u3057\u306A\u3044";
    private static final String RECOMMEND_MESSAGE = "\u30A4\u30F3\u30BF\u30FC\u30CD\u30C3\u30C8\u3092\u5B89\u5168\u3067\u5FEB\u9069\u306B\u95B2\u89A7\u3067\u304D\u308B\u300CYahoo!\u30D6\u30E9\u30A6\u30B6\u30FC\u300D\u3092\u304A\u3059\u3059\u3081\u3057\u307E\u3059\u3002\u305C\u3072\u3001\u4F7F\u3063\u3066\u307F\u3066\u304F\u3060\u3055\u3044\u3002";
    private static final String RECOMMEND_NEGATIVE = "\u30A6\u30A7\u30D6\u3092\u8868\u793A\n(\u30D6\u30E9\u30A6\u30B6\u30FC\u8D77\u52D5)";
    private static final String RECOMMEND_POSITIVE = "\u30C0\u30A6\u30F3\u30ED\u30FC\u30C9\n(Google Play)";
    private static final String RECOMMEND_TITLE = "Yahoo!\u30D6\u30E9\u30A6\u30B6\u30FC";
    private static final String URL_GOOGLE_PLAY = "market://details?id=jp.co.yahoo.android.ybrowser&referrer=%s";

    public YBrowserFRUtils()
    {
    }

    private static Drawable getAppIcon(Context context, String s)
    {
        PackageManager packagemanager = context.getPackageManager();
        Drawable drawable;
        try
        {
            drawable = packagemanager.getApplicationIcon(s);
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
            return null;
        }
        return drawable;
    }

    public static boolean isInstalledYBrowser(Context context)
    {
        if (context == null)
        {
            return false;
        }
        PackageManager packagemanager = context.getPackageManager();
        try
        {
            packagemanager.getApplicationInfo("jp.co.yahoo.android.ybrowser", 0);
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return false;
        }
        catch (Exception exception)
        {
            return false;
        }
        return true;
    }

    public static void openURL(Activity activity, String s, String s1)
    {
        openURL(activity, s, s1, -1);
    }

    public static void openURL(Activity activity, String s, String s1, int i)
    {
        if (isInstalledYBrowser(activity))
        {
            openURL(activity.getApplicationContext(), s, s1, true);
            return;
        }
        if (readPref(activity.getApplicationContext(), "recommended", false))
        {
            openURL(activity.getApplicationContext(), s, s1, false);
            return;
        } else
        {
            float f = activity.getResources().getDisplayMetrics().density;
            LinearLayout linearlayout = new LinearLayout(activity);
            CheckBox checkbox = new CheckBox(activity);
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
            ScrollView scrollview = (ScrollView)((LayoutInflater)activity.getSystemService("layout_inflater")).inflate(0x7f030056, null);
            ((TextView)scrollview.findViewById(0x7f0a01f4)).setText("\u30A4\u30F3\u30BF\u30FC\u30CD\u30C3\u30C8\u3092\u5B89\u5168\u3067\u5FEB\u9069\u306B\u95B2\u89A7\u3067\u304D\u308B\u300CYahoo!\u30D6\u30E9\u30A6\u30B6\u30FC\u300D\u3092\u304A\u3059\u3059\u3081\u3057\u307E\u3059\u3002\u305C\u3072\u3001\u4F7F\u3063\u3066\u307F\u3066\u304F\u3060\u3055\u3044\u3002");
            layoutparams.setMargins((int)(10F * f), 0, (int)(5F * f), (int)(5F * f));
            linearlayout.setOrientation(1);
            linearlayout.addView(scrollview, layoutparams);
            linearlayout.addView(checkbox, layoutparams);
            checkbox.setText("\u4ECA\u5F8C\u3053\u306E\u30C0\u30A4\u30A2\u30ED\u30B0\u3092\u8868\u793A\u3057\u306A\u3044");
            checkbox.setTextColor(i);
            checkbox.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener(activity) {

                final Activity val$activity;

                public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
                {
                    YBrowserFRUtils.writePref(activity.getApplicationContext(), "recommended", flag);
                }

            
            {
                activity = activity1;
                super();
            }
            });
            String s2 = activity.getPackageName();
            TransitDialogBuilder transitdialogbuilder = new TransitDialogBuilder(activity);
            transitdialogbuilder.setCancelable(true);
            transitdialogbuilder.setTitleIcon("Yahoo!\u30D6\u30E9\u30A6\u30B6\u30FC", 0x7f0201ee);
            transitdialogbuilder.setView(linearlayout);
            transitdialogbuilder.setPositiveButton("\u30C0\u30A6\u30F3\u30ED\u30FC\u30C9\n(Google Play)", new android.content.DialogInterface.OnClickListener(s2, activity) {

                final Activity val$activity;
                final String val$pkg;

                public void onClick(DialogInterface dialoginterface, int j)
                {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(String.format("market://details?id=jp.co.yahoo.android.ybrowser&referrer=%s", new Object[] {
                        pkg.substring(1 + pkg.lastIndexOf("."))
                    })));
                    intent.setFlags(0x10000000);
                    YBrowserFRUtils.startActivitySafely(activity.getApplicationContext(), intent);
                }

            
            {
                pkg = s;
                activity = activity1;
                super();
            }
            });
            transitdialogbuilder.setNegativeButton("\u30A6\u30A7\u30D6\u3092\u8868\u793A\n(\u30D6\u30E9\u30A6\u30B6\u30FC\u8D77\u52D5)", new android.content.DialogInterface.OnClickListener(activity, s, s1) {

                final Activity val$activity;
                final String val$fr;
                final String val$url;

                public void onClick(DialogInterface dialoginterface, int j)
                {
                    YBrowserFRUtils.openURL(activity.getApplicationContext(), url, fr, false);
                }

            
            {
                activity = activity1;
                url = s;
                fr = s1;
                super();
            }
            });
            transitdialogbuilder.show();
            return;
        }
    }

    public static void openURL(Context context, String s, String s1, boolean flag)
    {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(s));
        intent.setFlags(0x10000000);
        if (flag)
        {
            intent.putExtra("jp.co.yahoo.android.ybrowser.extra.FR", s1);
            intent.setPackage("jp.co.yahoo.android.ybrowser");
        }
        startActivitySafely(context, intent);
    }

    private static boolean readPref(Context context, String s, boolean flag)
    {
        return context.getSharedPreferences("ybrowser", 0).getBoolean(s, flag);
    }

    private static void startActivitySafely(Context context, Intent intent)
    {
        try
        {
            context.startActivity(intent);
            return;
        }
        catch (ActivityNotFoundException activitynotfoundexception)
        {
            activitynotfoundexception.printStackTrace();
            return;
        }
        catch (SecurityException securityexception)
        {
            securityexception.printStackTrace();
        }
    }

    private static void writePref(Context context, String s, boolean flag)
    {
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences("ybrowser", 0).edit();
        editor.putBoolean(s, flag);
        editor.commit();
    }


}
