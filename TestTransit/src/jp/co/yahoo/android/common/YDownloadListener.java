// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;


// Referenced classes of package jp.co.yahoo.android.common:
//            YDownloadItem

public interface YDownloadListener
{

    public abstract void onCancelled(YDownloadItem ydownloaditem);

    public abstract void onConnected(YDownloadItem ydownloaditem);

    public abstract void onFinished(YDownloadItem ydownloaditem);

    public abstract boolean onFinishedInBackground(YDownloadItem ydownloaditem);

    public abstract void onStarted(YDownloadItem ydownloaditem);
}
