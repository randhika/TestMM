// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;


// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            PushDiainfoManager

public static interface 
{

    public abstract void onCanceled();

    public abstract void onError(int i, String s, String s1, String s2);

    public abstract void onSuccess(String s, String s1);
}
