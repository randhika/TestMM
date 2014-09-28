// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.os.Bundle;

public interface YAppInfoEventListener
{

    public static final int DISMISS_DIALOG = 4;
    public static final int DOWNLOAD_CANCELED = 1;
    public static final int DOWNLOAD_COMPLETED = 0;
    public static final int DOWNLOAD_FAILED = 3;
    public static final int DOWNLOAD_TIMEOUT = 2;
    public static final int NEGATIVE_BUTTON_CLICKED = 6;
    public static final int POSITIVE_BUTTON_CLICKED = 5;

    public abstract boolean onAppInfoEvent(int i, Bundle bundle);
}
