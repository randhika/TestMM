// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.appstate;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

// Referenced classes of package com.google.android.gms.appstate:
//            AppStateManager

public static interface I
    extends Releasable, Result
{

    public abstract byte[] getLocalData();

    public abstract String getResolvedVersion();

    public abstract byte[] getServerData();

    public abstract int getStateKey();
}
