// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.plus;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.plus.model.moments.MomentBuffer;

// Referenced classes of package com.google.android.gms.plus:
//            PlusClient

public static interface 
{

    public abstract void onMomentsLoaded(ConnectionResult connectionresult, MomentBuffer momentbuffer, String s, String s1);
}
