// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;


// Referenced classes of package jp.co.yahoo.android.maps:
//            MapView

public interface MapViewListener
{

    public abstract boolean onChangeMap(MapView mapview);

    public abstract boolean onChangeScale(MapView mapview);

    public abstract void onSendAd();
}
