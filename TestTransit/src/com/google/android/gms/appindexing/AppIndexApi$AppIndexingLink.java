// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.appindexing;

import android.net.Uri;
import android.view.View;

// Referenced classes of package com.google.android.gms.appindexing:
//            AppIndexApi

public static final class viewId
{

    public final Uri appIndexingUrl;
    public final int viewId;
    public final Uri webUrl;

    public (Uri uri, Uri uri1, View view)
    {
        appIndexingUrl = uri;
        webUrl = uri1;
        viewId = view.getId();
    }
}
