// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TimeZone;

public class YExifInterface
{

    public static final int ORIENTATION_FLIP_HORIZONTAL = 2;
    public static final int ORIENTATION_FLIP_VERTICAL = 4;
    public static final int ORIENTATION_NORMAL = 1;
    public static final int ORIENTATION_ROTATE_180 = 3;
    public static final int ORIENTATION_ROTATE_270 = 8;
    public static final int ORIENTATION_ROTATE_90 = 6;
    public static final int ORIENTATION_TRANSPOSE = 5;
    public static final int ORIENTATION_TRANSVERSE = 7;
    public static final int ORIENTATION_UNDEFINED = 0;
    public static final String TAG_DATETIME = "DateTime";
    public static final String TAG_FLASH = "Flash";
    public static final String TAG_FOCAL_LENGTH = "FocalLength";
    public static final String TAG_GPS_DATESTAMP = "GPSDateStamp";
    public static final String TAG_GPS_LATITUDE = "GPSLatitude";
    public static final String TAG_GPS_LATITUDE_REF = "GPSLatitudeRef";
    public static final String TAG_GPS_LONGITUDE = "GPSLongitude";
    public static final String TAG_GPS_LONGITUDE_REF = "GPSLongitudeRef";
    public static final String TAG_GPS_PROCESSING_METHOD = "GPSProcessingMethod";
    public static final String TAG_GPS_TIMESTAMP = "GPSTimeStamp";
    public static final String TAG_IMAGE_LENGTH = "ImageLength";
    public static final String TAG_IMAGE_WIDTH = "ImageWidth";
    public static final String TAG_MAKE = "Make";
    public static final String TAG_MODEL = "Model";
    public static final String TAG_ORIENTATION = "Orientation";
    public static final String TAG_WHITE_BALANCE = "WhiteBalance";
    public static final int WHITEBALANCE_AUTO = 0;
    public static final int WHITEBALANCE_MANUAL = 1;
    private static SimpleDateFormat sFormatter;
    private static Object sLock = new Object();
    private HashMap mAttributes;
    private String mFilename;
    private boolean mHasThumbnail;

    public YExifInterface(String s)
        throws IOException
    {
        mFilename = s;
        loadAttributes();
    }

    private native boolean appendThumbnailNative(String s, String s1);

    private native void commitChangesNative(String s);

    private static float convertRationalLatLonToFloat(String s, String s1)
    {
label0:
        {
label1:
            {
                float f1;
                boolean flag;
                try
                {
                    String as[] = s.split(",");
                    String as1[] = as[0].split("/");
                    int i = (int)(Float.parseFloat(as1[0].trim()) / Float.parseFloat(as1[1].trim()));
                    String as2[] = as[1].split("/");
                    int j = (int)(Float.parseFloat(as2[0].trim()) / Float.parseFloat(as2[1].trim()));
                    String as3[] = as[2].split("/");
                    float f = Float.parseFloat(as3[0].trim()) / Float.parseFloat(as3[1].trim());
                    f1 = (float)i + (float)j / 60F + f / 3600F;
                    if (s1.equals("S"))
                    {
                        break label1;
                    }
                    flag = s1.equals("W");
                }
                catch (RuntimeException runtimeexception)
                {
                    return 0.0F;
                }
                if (!flag)
                {
                    break label0;
                }
            }
            f1 = -f1;
        }
        return f1;
    }

    private native String getAttributesNative(String s);

    private native byte[] getThumbnailNative(String s);

    private void loadAttributes()
        throws IOException
    {
        mAttributes = new HashMap();
        String s;
        synchronized (sLock)
        {
            s = getAttributesNative(mFilename);
        }
        int i = s.indexOf(' ');
        int j = Integer.parseInt(s.substring(0, i));
        int k = i + 1;
        int l = 0;
        while (l < j) 
        {
            int i1 = s.indexOf('=', k);
            String s1 = s.substring(k, i1);
            int j1 = i1 + 1;
            int k1 = s.indexOf(' ', j1);
            int l1 = Integer.parseInt(s.substring(j1, k1));
            int i2 = k1 + 1;
            String s2 = s.substring(i2, i2 + l1);
            k = i2 + l1;
            if (s1.equals("hasThumbnail"))
            {
                mHasThumbnail = s2.equalsIgnoreCase("true");
            } else
            {
                mAttributes.put(s1, s2);
            }
            l++;
        }
        break MISSING_BLOCK_LABEL_186;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private native void saveAttributesNative(String s, String s1);

    public String getAttribute(String s)
    {
        return (String)mAttributes.get(s);
    }

    public double getAttributeDouble(String s, double d)
    {
        String s1 = (String)mAttributes.get(s);
        if (s1 != null) goto _L2; else goto _L1
_L1:
        return d;
_L2:
        int i;
        double d1;
        double d2;
        try
        {
            i = s1.indexOf("/");
        }
        catch (NumberFormatException numberformatexception)
        {
            return d;
        }
        if (i == -1) goto _L1; else goto _L3
_L3:
        d1 = Double.parseDouble(s1.substring(i + 1));
        if (d1 == 0.0D) goto _L1; else goto _L4
_L4:
        d2 = Double.parseDouble(s1.substring(0, i));
        return d2 / d1;
    }

    public int getAttributeInt(String s, int i)
    {
        String s1 = (String)mAttributes.get(s);
        if (s1 == null)
        {
            return i;
        }
        int j;
        try
        {
            j = Integer.valueOf(s1).intValue();
        }
        catch (NumberFormatException numberformatexception)
        {
            return i;
        }
        return j;
    }

    public long getDateTime()
    {
        String s = (String)mAttributes.get("DateTime");
        if (s != null) goto _L2; else goto _L1
_L1:
        return -1L;
_L2:
        ParsePosition parseposition = new ParsePosition(0);
        Date date;
        long l;
        try
        {
            date = sFormatter.parse(s, parseposition);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return -1L;
        }
        if (date == null) goto _L1; else goto _L3
_L3:
        l = date.getTime();
        return l;
    }

    public long getGpsDateTime()
    {
        String s;
        String s1;
        s = (String)mAttributes.get("GPSDateStamp");
        s1 = (String)mAttributes.get("GPSTimeStamp");
        if (s != null && s1 != null) goto _L2; else goto _L1
_L1:
        String s2;
        return -1L;
_L2:
        if ((s2 = (new StringBuilder()).append(s).append(' ').append(s1).toString()) == null) goto _L1; else goto _L3
_L3:
        ParsePosition parseposition = new ParsePosition(0);
        Date date;
        long l;
        try
        {
            date = sFormatter.parse(s2, parseposition);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return -1L;
        }
        if (date == null) goto _L1; else goto _L4
_L4:
        l = date.getTime();
        return l;
    }

    public boolean getLatLong(float af[])
    {
        String s = (String)mAttributes.get("GPSLatitude");
        String s1 = (String)mAttributes.get("GPSLatitudeRef");
        String s2 = (String)mAttributes.get("GPSLongitude");
        String s3 = (String)mAttributes.get("GPSLongitudeRef");
        if (s != null && s1 != null && s2 != null && s3 != null)
        {
            af[0] = convertRationalLatLonToFloat(s, s1);
            af[1] = convertRationalLatLonToFloat(s2, s3);
            return true;
        } else
        {
            return false;
        }
    }

    public byte[] getThumbnail()
    {
        byte abyte0[];
        synchronized (sLock)
        {
            abyte0 = getThumbnailNative(mFilename);
        }
        return abyte0;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean hasThumbnail()
    {
        return mHasThumbnail;
    }

    public void saveAttributes()
        throws IOException
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = mAttributes.size();
        if (mAttributes.containsKey("hasThumbnail"))
        {
            i--;
        }
        stringbuilder.append((new StringBuilder()).append(i).append(" ").toString());
        Iterator iterator = mAttributes.entrySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            String s1 = (String)entry.getKey();
            if (!s1.equals("hasThumbnail"))
            {
                String s2 = (String)entry.getValue();
                stringbuilder.append((new StringBuilder()).append(s1).append("=").toString());
                stringbuilder.append((new StringBuilder()).append(s2.length()).append(" ").toString());
                stringbuilder.append(s2);
            }
        } while (true);
        String s = stringbuilder.toString();
        synchronized (sLock)
        {
            saveAttributesNative(mFilename, s);
            commitChangesNative(mFilename);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setAttribute(String s, String s1)
    {
        mAttributes.put(s, s1);
    }

    static 
    {
        System.loadLibrary("yexif");
        sFormatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        sFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
}
