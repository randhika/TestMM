// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.api;


// Referenced classes of package com.google.android.gms.common.api:
//            Api

public static interface NotRequiredOptions
{
    public static interface HasOptions
        extends Api.ApiOptions
    {
    }

    public static final class NoOptions
        implements NotRequiredOptions
    {

        private NoOptions()
        {
        }
    }

    public static interface NotRequiredOptions
        extends Api.ApiOptions
    {
    }

    public static interface Optional
        extends HasOptions, NotRequiredOptions
    {
    }

}
