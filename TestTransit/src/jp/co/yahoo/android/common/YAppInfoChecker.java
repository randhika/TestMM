// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.Time;
import android.util.TimeFormatException;
import android.util.Xml;
import android.view.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;

// Referenced classes of package jp.co.yahoo.android.common:
//            YHttpRequest, YVersionCheckListener, YAppInfoEventListener

public class YAppInfoChecker
{
    public static class AppInfoData
    {

        public List buttonData;
        public String forceUpdateDate;
        public String latestAppVersion;
        public String message;
        public String minAppVersion;
        public String minOsVersion;
        public String title;

        public AppInfoData()
        {
            title = "";
            message = "";
            minOsVersion = "";
            latestAppVersion = "";
            minAppVersion = "";
            forceUpdateDate = "";
            buttonData = new ArrayList();
        }
    }

    private class OSVersionComparator
        implements Comparator
    {

        final YAppInfoChecker this$0;

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((AppInfoData)obj, (AppInfoData)obj1);
        }

        public int compare(AppInfoData appinfodata, AppInfoData appinfodata1)
        {
            if (appinfodata != null && appinfodata1 != null)
            {
                return compareVersion(appinfodata.minOsVersion, appinfodata1.minOsVersion);
            } else
            {
                return 0;
            }
        }

        private OSVersionComparator()
        {
            this$0 = YAppInfoChecker.this;
            super();
        }

    }


    private static long CHECK_INTERVAL = 0L;
    private static final String FILE_NAME = "update.xml";
    private static final String PREF_NAME = "PREFS_CHECK_VERSION";
    private static SimpleDateFormat mSimpleDateFormat;
    private static YAppInfoChecker sInstance;
    private AppInfoData mAppInfoData;
    private YAppInfoEventListener mAppInfoEventListener;
    private boolean mDialodShowing;
    private boolean mIsForceUpdate;
    private boolean mIsUnderMinVersion;
    private YHttpRequest mRequest;
    private String mResponse;
    private String mUrl;
    private YVersionCheckListener mVersionCheckListener;

    private YAppInfoChecker()
    {
        mUrl = "";
        mDialodShowing = false;
        mIsUnderMinVersion = false;
        mIsForceUpdate = false;
    }

    private int compareVersion(String s, String s1)
    {
        int i = s.split("\\.").length;
        int j = s1.split("\\.").length;
        if (i > j)
        {
            for (int i1 = 0; i1 < i - j; i1++)
            {
                s1 = (new StringBuilder()).append(s1).append(".0").toString();
            }

        } else
        if (i < j)
        {
            for (int l = 0; l < j - i; l++)
            {
                s = (new StringBuilder()).append(s).append(".0").toString();
            }

        }
        int k = normalisedVersion(s).compareTo(normalisedVersion(s1));
        if (k > 0)
        {
            return 1;
        }
        return k >= 0 ? 0 : -1;
    }

    public static YAppInfoChecker getInstanse()
    {
        if (sInstance == null)
        {
            sInstance = new YAppInfoChecker();
        }
        return sInstance;
    }

    private boolean hasNeedParams()
    {
        return mAppInfoData.latestAppVersion != null && !"".equals(mAppInfoData.latestAppVersion) && mAppInfoData.minOsVersion != null && !"".equals(mAppInfoData.minOsVersion);
    }

    private String normalisedVersion(String s)
    {
        return normalisedVersion(s, ".", 4);
    }

    private String normalisedVersion(String s, String s1, int i)
    {
        String as[] = Pattern.compile(s1, 16).split(s);
        StringBuilder stringbuilder = new StringBuilder();
        int j = as.length;
        for (int k = 0; k < j; k++)
        {
            String s2 = as[k];
            stringbuilder.append(String.format((new StringBuilder()).append("%").append(i).append('s').toString(), new Object[] {
                s2
            }));
        }

        return stringbuilder.toString();
    }

    private void restoreAppInfoData(Context context)
    {
        mAppInfoData = new AppInfoData();
        BufferedReader bufferedreader;
        StringBuffer stringbuffer;
        bufferedreader = new BufferedReader(new InputStreamReader(context.openFileInput("update.xml")));
        stringbuffer = new StringBuffer();
_L1:
        String s = bufferedreader.readLine();
label0:
        {
            if (s == null)
            {
                break label0;
            }
            try
            {
                stringbuffer.append(s);
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
                return;
            }
        }
          goto _L1
        mResponse = stringbuffer.toString();
        parse();
        return;
    }

    public static void terminate()
    {
        if (sInstance != null && sInstance.mRequest != null)
        {
            sInstance.mRequest.cancel();
        }
        sInstance = null;
    }

    private void writeAppInfoXml(Context context)
    {
        try
        {
            java.io.FileOutputStream fileoutputstream = context.openFileOutput("update.xml", 0);
            OutputStreamWriter outputstreamwriter = new OutputStreamWriter(fileoutputstream);
            outputstreamwriter.write(getResponse());
            outputstreamwriter.close();
            fileoutputstream.close();
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void download()
    {
        download(null);
    }

    public void download(Context context)
    {
        mRequest = new YHttpRequest(context) {

            final YAppInfoChecker this$0;
            final Context val$context;

            public void onCanceled()
            {
                super.onCanceled();
                onDownloadCanceled();
            }

            public void onComplete()
            {
                onDownloadComplete();
            }

            public boolean onCompleteInThread()
            {
                if (getStatusCode() == 200)
                {
                    mResponse = getResponseString();
                    parse();
                    if (context != null)
                    {
                        writeAppInfoXml(context);
                    }
                }
                return true;
            }

            public void onTimeout()
            {
                super.onTimeout();
                onDownloadTimeout();
            }

            
            {
                this$0 = YAppInfoChecker.this;
                context = context1;
                super(final_s);
            }
        };
        mRequest.setTimeout(10000);
        mRequest.asyncGet();
    }

    public AppInfoData getAppInfoData(Context context)
    {
        if (mAppInfoData == null)
        {
            restoreAppInfoData(context);
        }
        return mAppInfoData;
    }

    public String getCurrentVersion(Context context)
    {
        PackageManager packagemanager = context.getPackageManager();
        String s = context.getPackageName();
        String s1;
        try
        {
            s1 = packagemanager.getPackageInfo(s, 0).versionName;
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return "";
        }
        return s1;
    }

    public String getResponse()
    {
        return mResponse;
    }

    public boolean isActivityForeground(Activity activity)
    {
        return activity.hasWindowFocus();
    }

    public boolean isForceUpdateDate(Context context)
    {
        String s;
        if (mAppInfoData == null)
        {
            restoreAppInfoData(context);
        }
        s = mAppInfoData.forceUpdateDate;
        if (s == null || "".equals(s)) goto _L2; else goto _L1
_L1:
        Date date3;
        Time time = new Time();
        time.parse3339(s.trim());
        date3 = new Date(time.normalize(true));
        Date date = date3;
_L4:
        Date date1 = new Date();
        TimeFormatException timeformatexception;
        Date date2;
        if (date != null)
        {
            return date1.compareTo(date) > 0;
        }
          goto _L2
        timeformatexception;
        if (mSimpleDateFormat == null)
        {
            mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'h:m:ssZ");
        }
        date2 = mSimpleDateFormat.parse(s);
        date = date2;
        continue; /* Loop/switch isn't completed */
_L2:
        return false;
        Exception exception;
        exception;
        date = null;
        continue; /* Loop/switch isn't completed */
        ParseException parseexception;
        parseexception;
        date = null;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public boolean isLatestVersion(Context context)
    {
        if (mAppInfoData == null)
        {
            restoreAppInfoData(context);
        }
        return isLatestVersion(getCurrentVersion(context));
    }

    public boolean isLatestVersion(String s)
    {
        if (mAppInfoData != null)
        {
            return s != null && !"".equals(s) && compareVersion(s, mAppInfoData.latestAppVersion) >= 0;
        } else
        {
            return true;
        }
    }

    public boolean isNeedUpdateCheck(Context context)
    {
label0:
        {
            File file = context.getFileStreamPath("update.xml");
            if (file.isFile())
            {
                long l = file.lastModified();
                if ((new Date()).getTime() - l <= CHECK_INTERVAL)
                {
                    break label0;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isUnderMinVersion(Context context)
    {
        String s = getCurrentVersion(context);
        if (mAppInfoData == null)
        {
            restoreAppInfoData(context);
        }
        return mAppInfoData.minAppVersion != null && !mAppInfoData.minAppVersion.equals("") && compareVersion(s, mAppInfoData.minAppVersion) < 0;
    }

    public void onButtonClicked(int i, String s, Activity activity)
    {
        if (s != null && (s.startsWith("market://") || s.startsWith("http://") || s.startsWith("https://")))
        {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
        }
        if (mIsUnderMinVersion || mIsForceUpdate)
        {
            System.exit(0);
        }
    }

    protected void onDownloadCanceled()
    {
    }

    protected void onDownloadComplete()
    {
        if (mVersionCheckListener != null)
        {
            mVersionCheckListener.onAppInfoDownloadCompleted();
        }
        if (mAppInfoEventListener != null)
        {
            mAppInfoEventListener.onAppInfoEvent(0, null);
        }
    }

    protected void onDownloadTimeout()
    {
        if (mVersionCheckListener != null)
        {
            mVersionCheckListener.onAppInfoDownloadTimeout();
        }
        if (mAppInfoEventListener != null)
        {
            mAppInfoEventListener.onAppInfoEvent(2, null);
        }
    }

    protected void parse()
    {
        Map map;
        Object obj;
        int l;
        if (mResponse == null)
        {
            return;
        }
        StringReader stringreader = new StringReader(mResponse);
        boolean flag = false;
        ArrayList arraylist = new ArrayList();
        XmlPullParser xmlpullparser;
        AppInfoData appinfodata;
        int i;
        int j;
        String s;
        int k;
        String s1;
        try
        {
            xmlpullparser = Xml.newPullParser();
            xmlpullparser.setInput(stringreader);
            appinfodata = new AppInfoData();
            i = xmlpullparser.getEventType();
        }
        catch (Exception exception)
        {
            return;
        }
        j = i;
        map = null;
        if (j == 1) goto _L2; else goto _L1
_L1:
        j;
        JVM INSTR tableswitch 0 3: default 108
    //                   0 132
    //                   1 108
    //                   2 139
    //                   3 431;
           goto _L3 _L4 _L3 _L5 _L6
_L3:
        obj = map;
_L7:
        k = xmlpullparser.next();
        j = k;
        map = ((Map) (obj));
        break MISSING_BLOCK_LABEL_68;
_L4:
        obj = map;
          goto _L7
_L5:
        s1 = xmlpullparser.getName();
        if (!"VersionInfo".equals(s1)) goto _L9; else goto _L8
_L8:
        appinfodata = new AppInfoData();
        appinfodata.minOsVersion = xmlpullparser.getAttributeValue(null, "minOSVersion");
        obj = map;
          goto _L7
_L9:
        if (!"Button".equals(s1)) goto _L11; else goto _L10
_L10:
        flag = true;
        try
        {
            obj = new HashMap();
        }
        catch (Exception exception1)
        {
            map;
            return;
        }
          goto _L7
_L11:
        if (!"LatestAppVersion".equals(s1)) goto _L13; else goto _L12
_L12:
        appinfodata.latestAppVersion = xmlpullparser.nextText();
        obj = map;
          goto _L7
_L13:
        if (!"MinAppVersion".equals(s1)) goto _L15; else goto _L14
_L14:
        appinfodata.minAppVersion = xmlpullparser.nextText();
        obj = map;
          goto _L7
_L15:
        if (!"ForceUpdateDate".equals(s1)) goto _L17; else goto _L16
_L16:
        appinfodata.forceUpdateDate = xmlpullparser.nextText();
        obj = map;
          goto _L7
_L17:
        if (!"Title".equals(s1)) goto _L19; else goto _L18
_L18:
        if (!flag) goto _L21; else goto _L20
_L20:
        map.put("text", xmlpullparser.nextText());
        obj = map;
          goto _L7
_L21:
        appinfodata.title = xmlpullparser.nextText();
        obj = map;
          goto _L7
_L19:
        if (!"Message".equals(s1))
        {
            continue; /* Loop/switch isn't completed */
        }
        appinfodata.message = xmlpullparser.nextText();
        obj = map;
          goto _L7
        if (!"Action".equals(s1)) goto _L3; else goto _L22
_L22:
        map.put("action", xmlpullparser.nextText());
        obj = map;
          goto _L7
_L6:
        s = xmlpullparser.getName();
        if (!"VersionInfo".equals(s)) goto _L24; else goto _L23
_L23:
        if (appinfodata.latestAppVersion == null || "".equals(appinfodata.latestAppVersion) || appinfodata.minOsVersion == null || "".equals(appinfodata.minOsVersion)) goto _L3; else goto _L25
_L25:
        arraylist.add(appinfodata);
        obj = map;
          goto _L7
_L24:
        if (!"ApplicationInfo".equals(s)) goto _L27; else goto _L26
_L26:
        Collections.sort(arraylist, new OSVersionComparator());
        l = -1 + arraylist.size();
_L33:
        if (l < 0) goto _L29; else goto _L28
_L28:
        if (compareVersion(android.os.Build.VERSION.RELEASE, ((AppInfoData)arraylist.get(l)).minOsVersion) < 0) goto _L31; else goto _L30
_L30:
        mAppInfoData = (AppInfoData)arraylist.get(l);
          goto _L29
_L27:
        if (!"Button".equals(s)) goto _L3; else goto _L32
_L32:
        appinfodata.buttonData.add(map);
        obj = map;
        flag = false;
          goto _L7
_L2:
        map;
        return;
_L29:
        obj = map;
          goto _L7
_L31:
        l--;
          goto _L33
    }

    public void setAppInfoEventListener(YAppInfoEventListener yappinfoeventlistener)
    {
        mAppInfoEventListener = yappinfoeventlistener;
    }

    public void setUpdateCheckInterval(int i)
    {
        CHECK_INTERVAL = i;
    }

    public void setUrl(String s)
    {
        mUrl = s;
    }

    public void setVersionCheckListener(YVersionCheckListener yversionchecklistener)
    {
        mVersionCheckListener = yversionchecklistener;
    }

    public boolean showUpdateDialog(Activity activity)
    {
        return showUpdateDialog(activity, false);
    }

    public boolean showUpdateDialog(final Activity activity, boolean flag)
    {
        String s = activity.getSharedPreferences("PREFS_CHECK_VERSION", 0).getString("canceled", null);
        if (isLatestVersion(activity))
        {
            return false;
        }
        mIsUnderMinVersion = isUnderMinVersion(activity);
        mIsForceUpdate = isForceUpdateDate(activity);
        boolean flag1;
        if (hasNeedParams() && !isLatestVersion(s))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag || mIsUnderMinVersion || mIsForceUpdate || flag1)
        {
            if (mDialodShowing)
            {
                return false;
            }
            mDialodShowing = true;
            AlertDialog alertdialog = (new android.app.AlertDialog.Builder(activity)).setTitle(mAppInfoData.title).setMessage(mAppInfoData.message).setCancelable(false).create();
            if (mAppInfoData.buttonData.size() > 0)
            {
                final String btnText = (String)((Map)mAppInfoData.buttonData.get(0)).get("text");
                alertdialog.setButton(btnText, new android.content.DialogInterface.OnClickListener() {

                    final YAppInfoChecker this$0;
                    final String val$action;
                    final Activity val$activity;
                    final String val$btnText;

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        if (mAppInfoEventListener != null)
                        {
                            Bundle bundle = new Bundle();
                            bundle.putString("text", btnText);
                            bundle.putString("action", action);
                            if (mAppInfoEventListener.onAppInfoEvent(5, bundle))
                            {
                                onButtonClicked(0, action, activity);
                            }
                            return;
                        } else
                        {
                            onButtonClicked(0, action, activity);
                            return;
                        }
                    }

            
            {
                this$0 = YAppInfoChecker.this;
                btnText = s;
                action = s1;
                activity = activity1;
                super();
            }
                });
            }
            if (mAppInfoData.buttonData.size() > 1)
            {
                final String btnText = (String)((Map)mAppInfoData.buttonData.get(1)).get("text");
                alertdialog.setButton2(btnText, new android.content.DialogInterface.OnClickListener() {

                    final YAppInfoChecker this$0;
                    final String val$action;
                    final Activity val$activity;
                    final String val$btnText;

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        android.content.SharedPreferences.Editor editor = activity.getSharedPreferences("PREFS_CHECK_VERSION", 0).edit();
                        editor.putString("canceled", mAppInfoData.latestAppVersion);
                        editor.commit();
                        if (mAppInfoEventListener != null)
                        {
                            Bundle bundle = new Bundle();
                            bundle.putString("text", btnText);
                            bundle.putString("action", action);
                            if (mAppInfoEventListener.onAppInfoEvent(6, bundle))
                            {
                                onButtonClicked(1, action, activity);
                            }
                            return;
                        } else
                        {
                            onButtonClicked(1, action, activity);
                            return;
                        }
                    }

            
            {
                this$0 = YAppInfoChecker.this;
                activity = activity1;
                btnText = s;
                action = s1;
                super();
            }
                });
            }
            alertdialog.setOnDismissListener(new android.content.DialogInterface.OnDismissListener() {

                final YAppInfoChecker this$0;

                public void onDismiss(DialogInterface dialoginterface)
                {
                    mDialodShowing = false;
                    if (mVersionCheckListener != null)
                    {
                        mVersionCheckListener.onDismissUpdateDialog();
                    }
                    if (mAppInfoEventListener != null)
                    {
                        mAppInfoEventListener.onAppInfoEvent(4, null);
                    }
                }

            
            {
                this$0 = YAppInfoChecker.this;
                super();
            }
            });
            alertdialog.setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

                final YAppInfoChecker this$0;

                public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
                {
                    return keyevent.getAction() != 0 || i != 84;
                }

            
            {
                this$0 = YAppInfoChecker.this;
                super();
            }
            });
            alertdialog.show();
            return true;
        } else
        {
            return false;
        }
    }

    static 
    {
        CHECK_INTERVAL = -1L;
    }


/*
    static String access$002(YAppInfoChecker yappinfochecker, String s)
    {
        yappinfochecker.mResponse = s;
        return s;
    }

*/





/*
    static boolean access$402(YAppInfoChecker yappinfochecker, boolean flag)
    {
        yappinfochecker.mDialodShowing = flag;
        return flag;
    }

*/


}
