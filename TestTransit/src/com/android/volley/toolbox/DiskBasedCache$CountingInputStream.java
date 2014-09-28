// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package com.android.volley.toolbox:
//            DiskBasedCache

private static class <init> extends FilterInputStream
{

    private int bytesRead;

    public int read()
        throws IOException
    {
        int i = super.read();
        if (i != -1)
        {
            bytesRead = 1 + bytesRead;
        }
        return i;
    }

    public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        int k = super.read(abyte0, i, j);
        if (k != -1)
        {
            bytesRead = k + bytesRead;
        }
        return k;
    }


    private (InputStream inputstream)
    {
        super(inputstream);
        bytesRead = 0;
    }

    bytesRead(InputStream inputstream, bytesRead bytesread)
    {
        this(inputstream);
    }
}
