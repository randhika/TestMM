// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import jp.co.yahoo.android.apps.transit.timer.api.data.DivideData;
import jp.co.yahoo.android.apps.transit.timer.api.data.SkinMetaData;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.timer.common.Holiday;
import jp.co.yahoo.android.apps.transit.timer.common.SettingDivide;
import jp.co.yahoo.android.apps.transit.timer.db.SettingDb;

public class CountdownUtil
{

    public CountdownUtil()
    {
    }

    public static void createSkinDirectry(String s)
    {
        File file = new File(s);
        if (!file.exists())
        {
            file.mkdir();
        }
    }

    public static void debugLs(String s)
    {
        File afile[] = (new File(s)).listFiles();
        if (afile != null)
        {
            int i = 0;
            while (i < afile.length) 
            {
                Log.d("list", afile[i].getAbsolutePath());
                i++;
            }
        }
    }

    public static void delete(File file)
    {
        if (file.exists())
        {
            if (file.isFile())
            {
                file.delete();
            }
            if (file.isDirectory())
            {
                File afile[] = file.listFiles();
                for (int i = 0; i < afile.length; i++)
                {
                    delete(afile[i]);
                }

                file.delete();
                return;
            }
        }
    }

    public static String extract(String s, String s1, boolean flag)
    {
        ZipInputStream zipinputstream = new ZipInputStream(new FileInputStream(s));
_L6:
        ZipEntry zipentry = zipinputstream.getNextEntry();
        if (zipentry == null) goto _L2; else goto _L1
_L1:
        BufferedOutputStream bufferedoutputstream;
        File file = new File(zipentry.getName());
        bufferedoutputstream = new BufferedOutputStream(new FileOutputStream((new StringBuilder()).append(s1).append(file.getName()).toString()));
        byte abyte0[] = new byte[1024];
_L5:
        int i = zipinputstream.read(abyte0);
        if (i == -1) goto _L4; else goto _L3
_L3:
        bufferedoutputstream.write(abyte0, 0, i);
          goto _L5
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
_L9:
        filenotfoundexception.printStackTrace();
        return null;
_L4:
        zipinputstream.closeEntry();
        bufferedoutputstream.close();
          goto _L6
_L2:
        zipinputstream.close();
        return s1;
        IOException ioexception;
        ioexception;
_L8:
        ioexception.printStackTrace();
        return null;
        ioexception;
        continue; /* Loop/switch isn't completed */
        ioexception;
        if (true) goto _L8; else goto _L7
_L7:
        filenotfoundexception;
          goto _L9
        filenotfoundexception;
          goto _L9
    }

    public static Bundle filter(Bundle bundle, String s, String s1)
    {
        boolean flag1;
        Bundle bundle1;
        if (s == null)
        {
            s = "";
        }
        if (s1 == null)
        {
            s1 = "";
        }
        String as[] = s.split(",");
        String as1[] = s1.split(",");
        int i = as.length;
        boolean flag = false;
        if (i > 0)
        {
            int j;
            int k;
            int l;
            try
            {
                Integer.parseInt(as[0]);
            }
            catch (Exception exception1)
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            flag = true;
        }
_L3:
        j = as1.length;
        flag1 = false;
        if (j <= 0)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        Integer.parseInt(as1[0]);
        flag1 = true;
_L1:
        bundle1 = new Bundle();
        k = 0;
        l = 0;
        while (l < bundle.size()) 
        {
            TimeTableItemData timetableitemdata = (TimeTableItemData)bundle.getSerializable(Integer.toString(l));
            String s2;
            String s3;
            Exception exception;
            if (flag)
            {
                s2 = timetableitemdata.getDesttype();
            } else
            {
                s2 = timetableitemdata.getDestinfo();
            }
            if (flag1)
            {
                s3 = timetableitemdata.getTraintype();
            } else
            {
                s3 = timetableitemdata.getTraininfo();
            }
            if (!Arrays.asList(as).contains(s2) && !Arrays.asList(as1).contains(s3))
            {
                timetableitemdata.setIndex(k);
                bundle1.putSerializable(Integer.toString(k), timetableitemdata);
                k++;
            }
            l++;
        }
        break MISSING_BLOCK_LABEL_230;
        exception;
        flag1 = false;
          goto _L1
        return bundle1;
        if (true) goto _L3; else goto _L2
_L2:
    }

    public static int getCountdownType(Context context)
    {
        SettingDb settingdb = new SettingDb(context);
        int i = context.getResources().getInteger(0x7f0c0073);
        int j = context.getResources().getInteger(0x7f0c0074);
        int k = settingdb.count(context.getResources().getInteger(0x7f0c0074));
        int l = settingdb.count(context.getResources().getInteger(0x7f0c0073));
        Calendar calendar = Calendar.getInstance();
        int i1 = calendar.get(11);
        if (i1 <= 3)
        {
            i1 += 24;
        }
        int j1 = calendar.get(12);
        if (k == 0 && l == 0)
        {
            return -1;
        }
        DivideData dividedata = (new SettingDivide(context)).getDivide();
        int k1 = dividedata.getDivideHour();
        int l1 = dividedata.getDivideMin();
        int i2;
        if (k == 0)
        {
            i2 = i;
        } else
        if (l == 0)
        {
            i2 = j;
        } else
        if (i1 > k1 || i1 == k1 && j1 >= l1)
        {
            if (dividedata.isReverse())
            {
                i2 = j;
            } else
            {
                i2 = i;
            }
        } else
        if (dividedata.isReverse())
        {
            i2 = i;
        } else
        {
            i2 = j;
        }
        return i2;
    }

    public static int getLatLngInt(String s)
    {
        return (int)(1000000D * Double.parseDouble(s));
    }

    public static String getLatLngString(int i)
    {
        return Double.toString((double)i / 1000000D);
    }

    public static String[] getMargeDest(Bundle bundle, String s, String s1)
    {
        String as[] = new String[3];
        as[0] = Integer.toString(1);
        as[1] = Integer.toString(2);
        as[2] = Integer.toString(4);
        ArrayList arraylist = new ArrayList();
        for (int i = 0; i < as.length; i++)
        {
            Bundle bundle3 = bundle.getBundle(as[i]);
            if (bundle3 == null)
            {
                continue;
            }
            Bundle bundle4 = bundle3.getBundle("dest");
            if (bundle4 == null)
            {
                continue;
            }
            Bundle bundle5 = bundle4.getBundle(s);
            if (bundle5 != null)
            {
                arraylist.add(bundle5);
            }
        }

        ArrayList arraylist1 = new ArrayList();
        for (int j = 0; j < arraylist.size(); j++)
        {
            Bundle bundle1 = (Bundle)arraylist.get(j);
            for (int k = 0; k < bundle1.size(); k++)
            {
                Bundle bundle2 = bundle1.getBundle(Integer.toString(k));
                if (!arraylist1.contains(bundle2.getString(s1)))
                {
                    arraylist1.add(bundle2.getString(s1));
                }
            }

        }

        return (String[])(String[])arraylist1.toArray(new String[0]);
    }

    public static int getNowDay()
    {
        Calendar calendar = Calendar.getInstance();
        return Integer.parseInt((new SimpleDateFormat("yyyyMMdd")).format(calendar.getTime()));
    }

    public static int getNowTimeSec()
    {
        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        int i = time.hour;
        if (i <= 3)
        {
            i += 24;
        }
        return 60 * (i * 60) + 60 * time.minute + time.second;
    }

    public static int getNowWeek(int i, boolean flag, Context context)
    {
        Calendar calendar = Calendar.getInstance();
        int j = calendar.get(11);
        byte byte0 = 0;
        if (flag)
        {
            byte0 = 0;
            if (j <= 3)
            {
                byte0 = -1;
            }
        }
        calendar.add(5, byte0 + i);
        int k = calendar.get(7);
        if (k == 1 || k == 8)
        {
            return 4;
        }
        if ((new Holiday(context)).isHoliday((new SimpleDateFormat("yyyyMMdd")).format(calendar.getTime())))
        {
            return 4;
        }
        return k != 7 && k != 0 ? 1 : 2;
    }

    public static int getNowWeek(Context context)
    {
        return getNowWeek(0, true, context);
    }

    public static String getRepeatWeek(String s, Context context)
    {
        String s1 = "";
        if (s.equals("8"))
        {
            s1 = context.getString(0x7f0d0592);
        } else
        {
            if (s.equals("0"))
            {
                return context.getString(0x7f0d04cd);
            }
            String as[] = context.getResources().getStringArray(0x7f07000c);
            String as1[] = s.split(",");
            int i = 0;
            while (i < as1.length) 
            {
                if (i > 0)
                {
                    s1 = (new StringBuilder()).append(s1).append("\u3001").toString();
                }
                s1 = (new StringBuilder()).append(s1).append(as[-1 + Integer.parseInt(as1[i])]).toString();
                i++;
            }
        }
        return s1;
    }

    public static void getSkinPath(SkinMetaData skinmetadata)
    {
        String s = "";
        if (!isEmpty(skinmetadata.sThumbnailUrl))
        {
            String as[];
            try
            {
                String as1[] = (new URL(skinmetadata.sThumbnailUrl)).getPath().split("/");
                s = as1[-1 + as1.length];
            }
            catch (MalformedURLException malformedurlexception1)
            {
                malformedurlexception1.printStackTrace();
            }
            skinmetadata.sThumbnailPath = (new StringBuilder()).append(skinmetadata.sPath).append("/").append(s).toString();
        }
        if (!isEmpty(skinmetadata.sIconUrl))
        {
            try
            {
                as = (new URL(skinmetadata.sIconUrl)).getPath().split("/");
                s = as[-1 + as.length];
            }
            catch (MalformedURLException malformedurlexception)
            {
                malformedurlexception.printStackTrace();
            }
            skinmetadata.sIconPath = (new StringBuilder()).append(skinmetadata.sPath).append("/").append(s).toString();
        }
    }

    public static int getVersionCode(Context context)
    {
        int i;
        try
        {
            i = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).versionCode;
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return -1;
        }
        return i;
    }

    public static String getWeekLabel(int i, Context context)
    {
        if (i == 4)
        {
            return context.getString(0x7f0d0594);
        }
        if (i == 2)
        {
            return context.getString(0x7f0d0596);
        } else
        {
            return context.getString(0x7f0d0595);
        }
    }

    public static String getZeroNum(int i)
    {
        if (i < 10)
        {
            return (new StringBuilder()).append("0").append(Integer.toString(i)).toString();
        } else
        {
            return Integer.toString(i);
        }
    }

    public static String getZeroNumH(int i)
    {
        if (i < 10)
        {
            return (new StringBuilder()).append("00").append(Integer.toString(i)).toString();
        }
        if (i < 100)
        {
            return (new StringBuilder()).append("0").append(Integer.toString(i)).toString();
        } else
        {
            return Integer.toString(i);
        }
    }

    public static boolean isBlank(Object obj)
    {
        return obj == null || obj.toString().equals("");
    }

    public static boolean isBlankMap(Map map)
    {
        for (Iterator iterator = map.values().iterator(); iterator.hasNext();)
        {
            if (!isBlank(iterator.next()))
            {
                return false;
            }
        }

        return true;
    }

    public static boolean isEmpty(String s)
    {
        return s == null || s.equals("") || s.length() == 0;
    }

    public static boolean isInteger(String s)
    {
        try
        {
            Integer.parseInt(s);
        }
        catch (NumberFormatException numberformatexception)
        {
            return false;
        }
        return true;
    }

    public static boolean isNowTerm(String s, String s1)
    {
        int i = getNowDay();
        if (s != null && Integer.parseInt(s) != 0 && i < Integer.parseInt(s))
        {
            return false;
        }
        boolean flag;
        if (s1 != null && Integer.parseInt(s1) != 0 && i > Integer.parseInt(s1))
        {
            flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public static String join(List list, String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s1;
        for (Iterator iterator = list.iterator(); iterator.hasNext(); stringbuffer.append(s1))
        {
            s1 = (String)iterator.next();
            if (stringbuffer.length() > 0)
            {
                stringbuffer.append(s);
            }
        }

        return stringbuffer.toString();
    }

    public static Calendar toCalendar(String s)
    {
        if (s != null && s.trim().length() >= 8) goto _L2; else goto _L1
_L1:
        Calendar calendar = null;
_L4:
        return calendar;
_L2:
        int l;
        calendar = Calendar.getInstance();
        calendar.setLenient(false);
        int i = Integer.parseInt(s.substring(0, 4));
        int j = Integer.parseInt(s.substring(5, 7));
        int k = Integer.parseInt(s.substring(8, 10));
        calendar.get(11);
        calendar.get(12);
        calendar.get(13);
        calendar.get(14);
        calendar.clear();
        calendar.set(i, j - 1, k);
        switch (s.length())
        {
        default:
            return null;

        case 10: // '\n'
            break;

        case 16: // '\020'
            int k2 = Integer.parseInt(s.substring(11, 13));
            int l2 = Integer.parseInt(s.substring(14, 16));
            calendar.set(11, k2);
            calendar.set(12, l2);
            return calendar;

        case 19: // '\023'
            int l1 = Integer.parseInt(s.substring(11, 13));
            int i2 = Integer.parseInt(s.substring(14, 16));
            int j2 = Integer.parseInt(s.substring(17, 19));
            calendar.set(11, l1);
            calendar.set(12, i2);
            calendar.set(13, j2);
            return calendar;

        case 23: // '\027'
            l = Integer.parseInt(s.substring(11, 13));
            break; /* Loop/switch isn't completed */
        }
        if (true) goto _L4; else goto _L3
_L3:
        int i1 = Integer.parseInt(s.substring(14, 16));
        int j1 = Integer.parseInt(s.substring(17, 19));
        int k1 = Integer.parseInt(s.substring(20, 23));
        calendar.set(11, l);
        calendar.set(12, i1);
        calendar.set(13, j1);
        calendar.set(14, k1);
        return calendar;
    }

    public static String trim(String s)
    {
        int i = s.length();
        int j = 0;
        char ac[];
        for (ac = s.toCharArray(); j < i && (ac[j] <= ' ' || ac[j] == '\u3000'); j++) { }
        for (; j < i && (ac[i - 1] <= ' ' || ac[i - 1] == '\u3000'); i--) { }
        if (j > 0 || i < s.length())
        {
            s = s.substring(j, i);
        }
        return s;
    }
}
