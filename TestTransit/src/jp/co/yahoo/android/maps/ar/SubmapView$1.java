// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.ar;

import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.PolylineOverlay;

// Referenced classes of package jp.co.yahoo.android.maps.ar:
//            SubmapView

class <init> extends PolylineOverlay
{

    final SubmapView this$0;

    protected boolean onTap()
    {
        return true;
    }

    (GeoPoint ageopoint[])
    {
        this$0 = SubmapView.this;
        super(ageopoint);
    }
}
