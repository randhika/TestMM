// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.util.Comparator;

// Referenced classes of package jp.co.yahoo.android.common:
//            YAppInfoData, YAppInfoCheckManager

private class <init>
    implements Comparator
{

    final YAppInfoCheckManager this$0;

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((YAppInfoData)obj, (YAppInfoData)obj1);
    }

    public int compare(YAppInfoData yappinfodata, YAppInfoData yappinfodata1)
    {
        if (yappinfodata != null && yappinfodata1 != null)
        {
            return YAppInfoCheckManager.access$100(YAppInfoCheckManager.this, yappinfodata.minOsVersion, yappinfodata1.minOsVersion);
        } else
        {
            return 0;
        }
    }

    private I()
    {
        this$0 = YAppInfoCheckManager.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
