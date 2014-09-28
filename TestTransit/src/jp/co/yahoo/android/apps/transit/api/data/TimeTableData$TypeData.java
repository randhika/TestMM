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

    private static final long serialVersionUID = 0xe36829260285a831L;
    public String id;
    public String info;
    public String mark;

    public boolean isEqual( )
    {
        if (!id.equals(.id))
        {
            return false;
        }
        boolean flag;
        if (!mark.equals(.mark))
        {
            break MISSING_BLOCK_LABEL_49;
        }
        flag = info.equals(.info);
        if (flag)
        {
            return true;
        }
        break MISSING_BLOCK_LABEL_49;
        Exception exception;
        exception;
        return false;
    }

    public ()
    {
    }
}
