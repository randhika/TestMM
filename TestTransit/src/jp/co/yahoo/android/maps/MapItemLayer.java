// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.Canvas;
import jp.co.yahoo.android.maps.viewlayer.BaseViewCtrl;
import jp.co.yahoo.android.maps.viewlayer.ViewLayer;

// Referenced classes of package jp.co.yahoo.android.maps:
//            MapCtrlEvent, MapView

public class MapItemLayer extends ViewLayer
{

    private static int $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType[];

    static int[] $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType()
    {
        int ai[] = $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType;
        if (ai != null)
        {
            return ai;
        }
        int ai1[] = new int[MapCtrlEvent.EventType.values().length];
        try
        {
            ai1[MapCtrlEvent.EventType.EVENT_MAX.ordinal()] = 23;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            ai1[MapCtrlEvent.EventType.KEY_DOWN.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai1[MapCtrlEvent.EventType.KEY_UP.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai1[MapCtrlEvent.EventType.ON_DESTROY.ordinal()] = 22;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai1[MapCtrlEvent.EventType.ON_DOUBLETAP.ordinal()] = 7;
        }
        catch (NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai1[MapCtrlEvent.EventType.ON_PINCH.ordinal()] = 11;
        }
        catch (NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai1[MapCtrlEvent.EventType.ON_PINCH_FINISH.ordinal()] = 12;
        }
        catch (NoSuchFieldError nosuchfielderror6) { }
        try
        {
            ai1[MapCtrlEvent.EventType.ON_SINGLETAPCONFIRMED.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror7) { }
        try
        {
            ai1[MapCtrlEvent.EventType.ON_SIZECHANGED.ordinal()] = 10;
        }
        catch (NoSuchFieldError nosuchfielderror8) { }
        try
        {
            ai1[MapCtrlEvent.EventType.ON_TAP.ordinal()] = 6;
        }
        catch (NoSuchFieldError nosuchfielderror9) { }
        try
        {
            ai1[MapCtrlEvent.EventType.ON_TIMER.ordinal()] = 9;
        }
        catch (NoSuchFieldError nosuchfielderror10) { }
        try
        {
            ai1[MapCtrlEvent.EventType.ON_TOUCH.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror11) { }
        try
        {
            ai1[MapCtrlEvent.EventType.ON_TRACKBALLEVENT.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror12) { }
        try
        {
            ai1[MapCtrlEvent.EventType.ON_TWOTAP.ordinal()] = 8;
        }
        catch (NoSuchFieldError nosuchfielderror13) { }
        try
        {
            ai1[MapCtrlEvent.EventType.RESET_MAP.ordinal()] = 21;
        }
        catch (NoSuchFieldError nosuchfielderror14) { }
        try
        {
            ai1[MapCtrlEvent.EventType.SET_ANIMETETO.ordinal()] = 19;
        }
        catch (NoSuchFieldError nosuchfielderror15) { }
        try
        {
            ai1[MapCtrlEvent.EventType.SET_ATTESTATION.ordinal()] = 18;
        }
        catch (NoSuchFieldError nosuchfielderror16) { }
        try
        {
            ai1[MapCtrlEvent.EventType.SET_CENTER.ordinal()] = 13;
        }
        catch (NoSuchFieldError nosuchfielderror17) { }
        try
        {
            ai1[MapCtrlEvent.EventType.SET_COPYRIGHT.ordinal()] = 17;
        }
        catch (NoSuchFieldError nosuchfielderror18) { }
        try
        {
            ai1[MapCtrlEvent.EventType.SET_FLICK.ordinal()] = 16;
        }
        catch (NoSuchFieldError nosuchfielderror19) { }
        try
        {
            ai1[MapCtrlEvent.EventType.SET_MOVE.ordinal()] = 15;
        }
        catch (NoSuchFieldError nosuchfielderror20) { }
        try
        {
            ai1[MapCtrlEvent.EventType.SET_ZOOM.ordinal()] = 14;
        }
        catch (NoSuchFieldError nosuchfielderror21) { }
        try
        {
            ai1[MapCtrlEvent.EventType.UPDATE_YML.ordinal()] = 20;
        }
        catch (NoSuchFieldError nosuchfielderror22) { }
        $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType = ai1;
        return ai1;
    }

    public MapItemLayer()
    {
    }

    public void doEvent(MapCtrlEvent mapctrlevent, BaseViewCtrl baseviewctrl)
    {
        switch ($SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType()[mapctrlevent.getEventType().ordinal()])
        {
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        default:
            return;
        }
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
    }
}
