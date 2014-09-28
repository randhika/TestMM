// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.io.Serializable;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api.data:
//            TimeTableData

public static class I
    implements Serializable
{

    private static final long serialVersionUID = 0x7ccfa0dcf2d06ad4L;
    public String cartype;
    public String id;

    public boolean isEqual(I i)
    {
        if (!id.equals(i.id))
        {
            return false;
        }
        boolean flag;
        try
        {
            flag = cartype.equals(i.cartype);
        }
        catch (Exception exception)
        {
            return false;
        }
label0:
        {
            if (flag)
            {
                return true;
            }
            break label0;
        }
    }

    public I()
    {
    }
}
