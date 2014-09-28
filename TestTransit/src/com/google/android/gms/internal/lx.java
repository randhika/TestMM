// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;


public class lx
{

    private final byte amK[] = new byte[256];
    private int amL;
    private int amM;

    public lx(byte abyte0[])
    {
        for (int i = 0; i < 256; i++)
        {
            amK[i] = (byte)i;
        }

        int j = 0;
        for (int k = 0; k < 256; k++)
        {
            j = 0xff & j + amK[k] + abyte0[k % abyte0.length];
            byte byte0 = amK[k];
            amK[k] = amK[j];
            amK[j] = byte0;
        }

        amL = 0;
        amM = 0;
    }

    public void o(byte abyte0[])
    {
        int i = amL;
        int j = amM;
        for (int k = 0; k < abyte0.length; k++)
        {
            i = 0xff & i + 1;
            j = 0xff & j + amK[i];
            byte byte0 = amK[i];
            amK[i] = amK[j];
            amK[j] = byte0;
            abyte0[k] = (byte)(abyte0[k] ^ amK[0xff & amK[i] + amK[j]]);
        }

        amL = i;
        amM = j;
    }
}
