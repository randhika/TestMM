// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.google.android.gms.common.data:
//            DataBuffer, Freezable

public final class DataBufferUtils
{

    private DataBufferUtils()
    {
    }

    public static ArrayList freezeAndClose(DataBuffer databuffer)
    {
        ArrayList arraylist = new ArrayList(databuffer.getCount());
        for (Iterator iterator = databuffer.iterator(); iterator.hasNext(); arraylist.add(((Freezable)iterator.next()).freeze())) { }
        break MISSING_BLOCK_LABEL_55;
        Exception exception;
        exception;
        databuffer.close();
        throw exception;
        databuffer.close();
        return arraylist;
    }

    public static boolean hasNextPage(DataBuffer databuffer)
    {
        Bundle bundle = databuffer.eU();
        return bundle != null && bundle.getString("next_page_token") != null;
    }

    public static boolean hasPrevPage(DataBuffer databuffer)
    {
        Bundle bundle = databuffer.eU();
        return bundle != null && bundle.getString("prev_page_token") != null;
    }
}
