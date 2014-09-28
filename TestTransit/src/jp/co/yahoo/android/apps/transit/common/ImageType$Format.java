// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;


// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            ImageType

public static final class name extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES BMP;
    public static final .VALUES GIF;
    public static final .VALUES JPEG;
    public static final .VALUES PICT;
    public static final .VALUES PNG;
    public static final .VALUES TIFF;
    public static final .VALUES UNKNOWN;
    public final String name;

    public static name valueOf(String s)
    {
        return (name)Enum.valueOf(jp/co/yahoo/android/apps/transit/common/ImageType$Format, s);
    }

    public static name[] values()
    {
        return (name[])$VALUES.clone();
    }

    public String toString()
    {
        return name;
    }

    static 
    {
        JPEG = new <init>("JPEG", 0, "jpg");
        BMP = new <init>("BMP", 1, "bmp");
        GIF = new <init>("GIF", 2, "gif");
        PNG = new <init>("PNG", 3, "png");
        TIFF = new <init>("TIFF", 4, "tif");
        PICT = new <init>("PICT", 5, "pic");
        UNKNOWN = new <init>("UNKNOWN", 6, "UNKNOWN");
        name aname[] = new <init>[7];
        aname[0] = JPEG;
        aname[1] = BMP;
        aname[2] = GIF;
        aname[3] = PNG;
        aname[4] = TIFF;
        aname[5] = PICT;
        aname[6] = UNKNOWN;
        $VALUES = aname;
    }

    private (String s, int i, String s1)
    {
        super(s, i);
        name = s1;
    }
}
