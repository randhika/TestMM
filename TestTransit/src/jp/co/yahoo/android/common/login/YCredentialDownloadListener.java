// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;


public interface YCredentialDownloadListener
{

    public abstract void onCredentialCanceled();

    public abstract void onCredentialDownloaded(String s, String s1, long l);

    public abstract void onCredentialFailed(String s, int i, boolean flag);
}
