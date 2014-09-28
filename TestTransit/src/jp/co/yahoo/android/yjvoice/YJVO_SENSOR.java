// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;


public final class YJVO_SENSOR extends Enum
{

    private static final YJVO_SENSOR $VALUES[];
    public static final YJVO_SENSOR SENSOR_GPS;
    public static final YJVO_SENSOR SENSOR_NONE;

    private YJVO_SENSOR(String s, int i)
    {
        super(s, i);
    }

    public static YJVO_SENSOR valueOf(String s)
    {
        return (YJVO_SENSOR)Enum.valueOf(jp/co/yahoo/android/yjvoice/YJVO_SENSOR, s);
    }

    public static YJVO_SENSOR[] values()
    {
        return (YJVO_SENSOR[])$VALUES.clone();
    }

    static 
    {
        SENSOR_NONE = new YJVO_SENSOR("SENSOR_NONE", 0);
        SENSOR_GPS = new YJVO_SENSOR("SENSOR_GPS", 1);
        YJVO_SENSOR ayjvo_sensor[] = new YJVO_SENSOR[2];
        ayjvo_sensor[0] = SENSOR_NONE;
        ayjvo_sensor[1] = SENSOR_GPS;
        $VALUES = ayjvo_sensor;
    }
}
