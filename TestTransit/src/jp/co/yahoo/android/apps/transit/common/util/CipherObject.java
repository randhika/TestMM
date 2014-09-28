// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common.util;


public class CipherObject
{

    private byte encryptedData[];
    private byte iv[];

    public CipherObject(byte abyte0[], byte abyte1[])
    {
        setIv(abyte0);
        setEncryptedData(abyte1);
    }

    public byte[] getEncryptedString()
    {
        return encryptedData;
    }

    public byte[] getIv()
    {
        return iv;
    }

    public void setEncryptedData(byte abyte0[])
    {
        encryptedData = abyte0;
    }

    public void setIv(byte abyte0[])
    {
        iv = abyte0;
    }
}
