// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import java.util.List;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            AutoCompleteSuggestTextView

public static interface 
{

    public abstract void onInputed(String s);

    public abstract void onNoMatch(int i);

    public abstract void onNoinput();

    public abstract void onSuggestSuccess(int i, List list);
}
