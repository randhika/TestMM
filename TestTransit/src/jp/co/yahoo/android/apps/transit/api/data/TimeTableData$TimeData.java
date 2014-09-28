// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.io.Serializable;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api.data:
//            TimeTableData

public static class 
    implements Serializable
{

    private static final long serialVersionUID = 0x5a5d353c18857374L;
    public int carId;
    public int destId;
    public boolean extraLine;
    public boolean firstStation;
    public int kindId;
    public int lineId;
    public int minute;

    public boolean isEqual( )
    {
        if (minute != .minute)
        {
            return false;
        }
        boolean flag;
        boolean flag1;
        if (lineId != .lineId || kindId != .kindId || destId != .destId || carId != .carId || extraLine != .extraLine)
        {
            break MISSING_BLOCK_LABEL_88;
        }
        flag = firstStation;
        flag1 = .firstStation;
        if (flag == flag1)
        {
            return true;
        }
        break MISSING_BLOCK_LABEL_88;
        Exception exception;
        exception;
        return false;
    }

    public ()
    {
    }
}
