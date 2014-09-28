// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.data.storage;


public class SecretStorageException extends Exception
{

    private static final long serialVersionUID = 1L;
    private String error;
    private String errorDescription;

    public SecretStorageException()
    {
        error = "";
        errorDescription = "";
    }

    public SecretStorageException(String s, String s1)
    {
        super(s1);
        error = "";
        errorDescription = "";
        error = s;
        errorDescription = s1;
    }

    public SecretStorageException(String s, String s1, Throwable throwable)
    {
        super(s1, throwable);
        error = "";
        errorDescription = "";
        error = s;
        errorDescription = s1;
    }

    public SecretStorageException(String s, Throwable throwable)
    {
        super(throwable);
        error = "";
        errorDescription = "";
        error = s;
    }

    public String getError()
    {
        return error;
    }

    public String getErrorDescription()
    {
        return errorDescription;
    }

    public String toString()
    {
        return (new StringBuilder()).append("error: ").append(error).append(" error_description: ").append(errorDescription).append(" (").append(jp/co/yahoo/yconnect/data/storage/SecretStorageException.getSimpleName()).append(")").toString();
    }
}
