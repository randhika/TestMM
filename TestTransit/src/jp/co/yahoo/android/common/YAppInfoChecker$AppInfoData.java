// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package jp.co.yahoo.android.common:
//            YAppInfoChecker

public static class buttonData
{

    public List buttonData;
    public String forceUpdateDate;
    public String latestAppVersion;
    public String message;
    public String minAppVersion;
    public String minOsVersion;
    public String title;

    public ()
    {
        title = "";
        message = "";
        minOsVersion = "";
        latestAppVersion = "";
        minAppVersion = "";
        forceUpdateDate = "";
        buttonData = new ArrayList();
    }
}
