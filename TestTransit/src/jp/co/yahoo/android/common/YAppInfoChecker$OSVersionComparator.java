// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.util.Comparator;

// Referenced classes of package jp.co.yahoo.android.common:
//            YAppInfoChecker

private class <init>
    implements Comparator
{

    final YAppInfoChecker this$0;

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((compare)obj, (compare)obj1);
    }

    public int compare(compare compare1, compare compare2)
    {
        if (compare1 != null && compare2 != null)
        {
            return YAppInfoChecker.access$600(YAppInfoChecker.this, compare1.sion, compare2.sion);
        } else
        {
            return 0;
        }
    }

    private ()
    {
        this$0 = YAppInfoChecker.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
