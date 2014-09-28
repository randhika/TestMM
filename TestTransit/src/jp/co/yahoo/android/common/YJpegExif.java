// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.text.format.Time;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Referenced classes of package jp.co.yahoo.android.common:
//            YExifInterface

public class YJpegExif
{

    private static SimpleDateFormat DF = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
    private final YExifInterface _exif;

    public YJpegExif(String s)
        throws IOException
    {
        _exif = new YExifInterface(s);
    }

    public Time getDateTaken()
    {
        String s = _exif.getAttribute("DateTime");
        if (s == null || s.length() == 0)
        {
            return null;
        }
        Time time;
        try
        {
            Date date = DF.parse(s);
            time = new Time();
            time.set(date.getTime());
        }
        catch (ParseException parseexception)
        {
            parseexception.printStackTrace();
            return null;
        }
        return time;
    }

}
