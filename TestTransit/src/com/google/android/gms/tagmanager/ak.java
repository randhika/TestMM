// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import java.io.File;

// Referenced classes of package com.google.android.gms.tagmanager:
//            bh

class ak
{

    static boolean N(String s)
    {
        if (version() < 9)
        {
            return false;
        } else
        {
            File file = new File(s);
            file.setReadable(false, false);
            file.setWritable(false, false);
            file.setReadable(true, true);
            file.setWritable(true, true);
            return true;
        }
    }

    public static int version()
    {
        int i;
        try
        {
            i = Integer.parseInt(android.os.Build.VERSION.SDK);
        }
        catch (NumberFormatException numberformatexception)
        {
            bh.A((new StringBuilder()).append("Invalid version number: ").append(android.os.Build.VERSION.SDK).toString());
            return 0;
        }
        return i;
    }
}
