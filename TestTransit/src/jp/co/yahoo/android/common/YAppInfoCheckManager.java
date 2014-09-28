// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.format.Time;
import android.util.TimeFormatException;
import android.util.Xml;
import java.io.IOException;
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
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package jp.co.yahoo.android.common:
//            YLogger, YAppInfoData

public class YAppInfoCheckManager
{
    private class OSVersionComparator
        implements Comparator
    {

        final YAppInfoCheckManager this$0;

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((YAppInfoData)obj, (YAppInfoData)obj1);
        }

        public int compare(YAppInfoData yappinfodata, YAppInfoData yappinfodata1)
        {
            if (yappinfodata != null && yappinfodata1 != null)
            {
                return compareVersion(yappinfodata.minOsVersion, yappinfodata1.minOsVersion);
            } else
            {
                return 0;
            }
        }

        private OSVersionComparator()
        {
            this$0 = YAppInfoCheckManager.this;
            super();
        }

    }


    public static final String PREF_NAME = "PREFS_CHECK_VERSION";
    private YAppInfoData mAppInfo;
    private Context mContext;
    private String mCurrentVersion;
    private SimpleDateFormat mSimpleDateFormat;

    public YAppInfoCheckManager(Context context)
    {
        mContext = context;
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

    private String getCurrentVersion()
    {
        PackageManager packagemanager = mContext.getPackageManager();
        String s;
        try
        {
            s = packagemanager.getPackageInfo(mContext.getPackageName(), 0).versionName;
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
            return "";
        }
        return s;
    }

    private boolean hasNewUpdate()
    {
        String s = mContext.getSharedPreferences("PREFS_CHECK_VERSION", 0).getString("canceled_version", "0");
        YLogger.log((new StringBuilder()).append("canceledVersion = ").append(s).toString());
        int i = compareVersion(s, mAppInfo.latestAppVersion);
        boolean flag = false;
        if (i < 0)
        {
            flag = true;
        }
        return flag;
    }

    private boolean hasUpdate()
    {
        return compareVersion(mCurrentVersion, mAppInfo.latestAppVersion) < 0;
    }

    private boolean isForceUpdateDate()
    {
        String s;
        s = mAppInfo.forceUpdateDate;
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_126;
        }
        Date date3;
        Time time = new Time();
        time.parse3339(s.trim());
        date3 = new Date(time.normalize(true));
        Date date = date3;
_L1:
        Date date1 = new Date();
        TimeFormatException timeformatexception;
        ParseException parseexception;
        Date date2;
        if (date != null)
        {
            return date1.compareTo(date) > 0;
        }
        break MISSING_BLOCK_LABEL_126;
        timeformatexception;
        if (mSimpleDateFormat == null)
        {
            mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'h:m:ssZ");
        }
        date2 = mSimpleDateFormat.parse(s);
        date = date2;
          goto _L1
        parseexception;
        parseexception.printStackTrace();
        date = null;
          goto _L1
        return false;
    }

    private boolean isUnderMinVersion()
    {
        return compareVersion(mCurrentVersion, mAppInfo.minAppVersion) < 0;
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

    private YAppInfoData parse(String s)
    {
        StringReader stringreader;
        boolean flag;
        ArrayList arraylist;
        if (s == null)
        {
            return null;
        }
        stringreader = new StringReader(s);
        flag = false;
        arraylist = new ArrayList();
        XmlPullParser xmlpullparser;
        YAppInfoData yappinfodata;
        int i;
        xmlpullparser = Xml.newPullParser();
        xmlpullparser.setInput(stringreader);
        yappinfodata = new YAppInfoData();
        i = xmlpullparser.getEventType();
        int j;
        Map map;
        j = i;
        map = null;
_L7:
        if (j == 1) goto _L2; else goto _L1
_L1:
        j;
        JVM INSTR tableswitch 0 3: default 104
    //                   0 128
    //                   1 104
    //                   2 135
    //                   3 475;
           goto _L3 _L4 _L3 _L5 _L6
_L3:
        Object obj = map;
_L8:
        int k = xmlpullparser.next();
        j = k;
        map = ((Map) (obj));
          goto _L7
_L4:
        obj = map;
          goto _L8
_L5:
        String s2 = xmlpullparser.getName();
        if (!"VersionInfo".equals(s2)) goto _L10; else goto _L9
_L9:
        yappinfodata = new YAppInfoData();
        yappinfodata.minOsVersion = xmlpullparser.getAttributeValue(null, "minOSVersion");
        obj = map;
          goto _L8
_L10:
        if (!"Button".equals(s2)) goto _L12; else goto _L11
_L11:
        flag = true;
        obj = new HashMap();
        ((Map) (obj)).put("order", xmlpullparser.getAttributeValue(null, "order"));
        ((Map) (obj)).put("type", xmlpullparser.getAttributeValue(null, "type"));
          goto _L8
        IOException ioexception;
        ioexception;
_L34:
        ioexception.printStackTrace();
_L32:
        return null;
_L12:
        if (!"LatestAppVersion".equals(s2)) goto _L14; else goto _L13
_L13:
        yappinfodata.latestAppVersion = xmlpullparser.nextText();
        obj = map;
          goto _L8
_L14:
        if (!"MinAppVersion".equals(s2)) goto _L16; else goto _L15
_L15:
        yappinfodata.minAppVersion = xmlpullparser.nextText();
        obj = map;
          goto _L8
_L16:
        if (!"ForceUpdateDate".equals(s2)) goto _L18; else goto _L17
_L17:
        yappinfodata.forceUpdateDate = xmlpullparser.nextText();
        obj = map;
          goto _L8
_L18:
        if (!"Title".equals(s2)) goto _L20; else goto _L19
_L19:
        if (!flag) goto _L22; else goto _L21
_L21:
        map.put("text", xmlpullparser.nextText());
        obj = map;
          goto _L8
_L22:
        yappinfodata.title = xmlpullparser.nextText();
        obj = map;
          goto _L8
_L20:
        if (!"Message".equals(s2))
        {
            continue; /* Loop/switch isn't completed */
        }
        yappinfodata.message = xmlpullparser.nextText();
        obj = map;
          goto _L8
        if (!"Action".equals(s2)) goto _L3; else goto _L23
_L23:
        map.put("action", xmlpullparser.nextText());
        obj = map;
          goto _L8
_L6:
        String s1 = xmlpullparser.getName();
        if (!"VersionInfo".equals(s1)) goto _L25; else goto _L24
_L24:
        if (yappinfodata.latestAppVersion == null || "".equals(yappinfodata.latestAppVersion) || yappinfodata.minOsVersion == null || "".equals(yappinfodata.minOsVersion)) goto _L3; else goto _L26
_L26:
        arraylist.add(yappinfodata);
        obj = map;
          goto _L8
_L25:
        int l;
        if (!"ApplicationInfo".equals(s1))
        {
            continue; /* Loop/switch isn't completed */
        }
        Collections.sort(arraylist, new OSVersionComparator());
        l = -1 + arraylist.size();
_L35:
        if (l < 0) goto _L28; else goto _L27
_L27:
        if (compareVersion(android.os.Build.VERSION.RELEASE, ((YAppInfoData)arraylist.get(l)).minOsVersion) < 0) goto _L30; else goto _L29
_L29:
        YAppInfoData yappinfodata1;
        yappinfodata1 = (YAppInfoData)arraylist.get(l);
        yappinfodata1.xmlString = s;
        return yappinfodata1;
        if (!"Button".equals(s1)) goto _L3; else goto _L31
_L31:
        yappinfodata.buttonData.add(map);
        obj = map;
        flag = false;
          goto _L8
_L2:
        map;
          goto _L32
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
_L33:
        xmlpullparserexception.printStackTrace();
          goto _L32
        xmlpullparserexception;
        map;
          goto _L33
        ioexception;
        map;
          goto _L34
_L30:
        l--;
          goto _L35
_L28:
        obj = map;
          goto _L8
    }

    public YAppInfoData getAppInfo(String s)
    {
        mAppInfo = parse(s);
        if (mAppInfo != null)
        {
            mCurrentVersion = getCurrentVersion();
            if (!"".equals(mCurrentVersion))
            {
                mAppInfo.hasUpdate = hasUpdate();
                mAppInfo.hasNewUpdate = hasNewUpdate();
                mAppInfo.isUnderminVersion = isUnderMinVersion();
                mAppInfo.isForceUpdateDate = isForceUpdateDate();
                YLogger.log((new StringBuilder()).append("isLatestVersion = ").append(mAppInfo.hasUpdate).toString());
                YLogger.log((new StringBuilder()).append("hasNewUpdate = ").append(mAppInfo.hasNewUpdate).toString());
                YLogger.log((new StringBuilder()).append("isUnderminVersion = ").append(mAppInfo.isUnderminVersion).toString());
                YLogger.log((new StringBuilder()).append("isForceUpdateDate = ").append(mAppInfo.isForceUpdateDate).toString());
                return mAppInfo;
            }
        }
        return null;
    }

}
