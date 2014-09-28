// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.app.Activity;

public interface AppLogin
{

    public abstract String generateSnonce();

    public abstract void login(Activity activity, int i);

    public abstract void setClientId(String s);

    public abstract void setCustomUriScheme(String s);

    public abstract void showLoginView(Activity activity, int i);
}
