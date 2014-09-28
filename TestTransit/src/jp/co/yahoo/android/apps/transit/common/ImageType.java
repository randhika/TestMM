// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageType
{
    public static final class Format extends Enum
    {

        private static final Format $VALUES[];
        public static final Format BMP;
        public static final Format GIF;
        public static final Format JPEG;
        public static final Format PICT;
        public static final Format PNG;
        public static final Format TIFF;
        public static final Format UNKNOWN;
        public final String name;

        public static Format valueOf(String s)
        {
            return (Format)Enum.valueOf(jp/co/yahoo/android/apps/transit/common/ImageType$Format, s);
        }

        public static Format[] values()
        {
            return (Format[])$VALUES.clone();
        }

        public String toString()
        {
            return name;
        }

        static 
        {
            JPEG = new Format("JPEG", 0, "jpg");
            BMP = new Format("BMP", 1, "bmp");
            GIF = new Format("GIF", 2, "gif");
            PNG = new Format("PNG", 3, "png");
            TIFF = new Format("TIFF", 4, "tif");
            PICT = new Format("PICT", 5, "pic");
            UNKNOWN = new Format("UNKNOWN", 6, "UNKNOWN");
            Format aformat[] = new Format[7];
            aformat[0] = JPEG;
            aformat[1] = BMP;
            aformat[2] = GIF;
            aformat[3] = PNG;
            aformat[4] = TIFF;
            aformat[5] = PICT;
            aformat[6] = UNKNOWN;
            $VALUES = aformat;
        }

        private Format(String s, int i, String s1)
        {
            super(s, i);
            name = s1;
        }
    }


    public static final String BMP = "bmp";
    public static final String GIF = "gif";
    public static final String JPEG = "jpg";
    public static final String PICT = "pic";
    public static final String PNG = "png";
    public static final String TIFF = "tif";
    public static final String UNKNOWN = "UNKNOWN";
    private static final long bmp = 0x424d000000000000L;
    private static final long gif = 0x4749460000000000L;
    private static final long jpeg = 0xffd8000000000000L;
    private static final long pic = 0x5049430000000000L;
    private static final long png = 0x89504e470d0a1a0aL;
    private static final long tiff1 = 0x4949000000000000L;
    private static final long tiff2 = 0x4d4d000000000000L;

    public ImageType()
    {
    }

    public static Format getFormat(File file)
        throws IOException
    {
        FileInputStream fileinputstream = null;
        FileInputStream fileinputstream1 = new FileInputStream(file);
        Format format = getFormat(((InputStream) (fileinputstream1)));
        if (fileinputstream1 != null)
        {
            fileinputstream1.close();
        }
        return format;
        Exception exception;
        exception;
_L2:
        if (fileinputstream != null)
        {
            fileinputstream.close();
        }
        throw exception;
        exception;
        fileinputstream = fileinputstream1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static Format getFormat(InputStream inputstream)
        throws IOException
    {
        byte abyte0[] = new byte[8];
        if (inputstream.read(abyte0, 0, 8) != 8)
        {
            return Format.UNKNOWN;
        } else
        {
            return getFormat(abyte0);
        }
    }

    public static Format getFormat(byte abyte0[])
    {
        if (abyte0.length < 8)
        {
            return Format.UNKNOWN;
        }
        long l = (long)abyte0[0] << 56 | (255L & (long)abyte0[1]) << 48 | (255L & (long)abyte0[2]) << 40 | (255L & (long)abyte0[3]) << 32 | (255L & (long)abyte0[4]) << 24 | (255L & (long)abyte0[5]) << 16 | (255L & (long)abyte0[6]) << 8 | 255L & (long)abyte0[7];
        if ((0xffd8000000000000L & l) == 0xffd8000000000000L)
        {
            return Format.JPEG;
        }
        if ((0x89504e470d0a1a0aL & l) == 0x89504e470d0a1a0aL)
        {
            return Format.PNG;
        }
        if ((0x4749460000000000L & l) == 0x4749460000000000L)
        {
            return Format.GIF;
        }
        if ((0x4949000000000000L & l) == 0x4949000000000000L || (0x4d4d000000000000L & l) == 0x4d4d000000000000L)
        {
            return Format.TIFF;
        }
        if ((0x424d000000000000L & l) == 0x424d000000000000L)
        {
            return Format.BMP;
        }
        if ((0x5049430000000000L & l) == 0x5049430000000000L)
        {
            return Format.PICT;
        } else
        {
            return Format.UNKNOWN;
        }
    }
}
