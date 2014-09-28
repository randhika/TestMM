// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer.etc;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import java.util.Vector;
import jp.co.yahoo.android.maps.MapCtrlEvent;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.Projection;
import jp.co.yahoo.android.maps.viewlayer.BaseView;
import jp.co.yahoo.android.maps.viewlayer.BaseViewCtrl;
import jp.co.yahoo.android.maps.viewlayer.LayerInfo;
import jp.co.yahoo.android.maps.viewlayer.MapLayerManager;
import jp.co.yahoo.android.maps.viewlayer.MapYml;
import jp.co.yahoo.android.maps.viewlayer.ViewLayer;

public class ScalebarAndCopyrightLayer extends ViewLayer
    implements jp.co.yahoo.android.maps.viewlayer.MapYml.MapYmlListener
{

    private static int $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType[];
    private BaseView mBaseView;
    private String mCopyright;
    private boolean mCopyrightOn;
    private int mCopyrightValign;
    private String mLastCopyright;
    private Vector mLines;
    private MapYml mMapYml;
    private Paint mPaint;
    private float mScaleBar_R;
    private Paint mScaleTextPaint;
    private boolean mScalebarOn;
    private Paint mScalebarPaint;
    private Path mScalebarPath;
    private int mScaleline;
    private boolean mYmlReTry;

    static int[] $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType()
    {
        int ai[] = $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType;
        if (ai != null)
        {
            return ai;
        }
        int ai1[] = new int[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.values().length];
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.EVENT_MAX.ordinal()] = 23;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.KEY_DOWN.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.KEY_UP.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_DESTROY.ordinal()] = 22;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_DOUBLETAP.ordinal()] = 7;
        }
        catch (NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_PINCH.ordinal()] = 11;
        }
        catch (NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_PINCH_FINISH.ordinal()] = 12;
        }
        catch (NoSuchFieldError nosuchfielderror6) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_SINGLETAPCONFIRMED.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror7) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_SIZECHANGED.ordinal()] = 10;
        }
        catch (NoSuchFieldError nosuchfielderror8) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TAP.ordinal()] = 6;
        }
        catch (NoSuchFieldError nosuchfielderror9) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TIMER.ordinal()] = 9;
        }
        catch (NoSuchFieldError nosuchfielderror10) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TOUCH.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror11) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TRACKBALLEVENT.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror12) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.ON_TWOTAP.ordinal()] = 8;
        }
        catch (NoSuchFieldError nosuchfielderror13) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.RESET_MAP.ordinal()] = 21;
        }
        catch (NoSuchFieldError nosuchfielderror14) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_ANIMETETO.ordinal()] = 19;
        }
        catch (NoSuchFieldError nosuchfielderror15) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_ATTESTATION.ordinal()] = 18;
        }
        catch (NoSuchFieldError nosuchfielderror16) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_CENTER.ordinal()] = 13;
        }
        catch (NoSuchFieldError nosuchfielderror17) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_COPYRIGHT.ordinal()] = 17;
        }
        catch (NoSuchFieldError nosuchfielderror18) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_FLICK.ordinal()] = 16;
        }
        catch (NoSuchFieldError nosuchfielderror19) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_MOVE.ordinal()] = 15;
        }
        catch (NoSuchFieldError nosuchfielderror20) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.SET_ZOOM.ordinal()] = 14;
        }
        catch (NoSuchFieldError nosuchfielderror21) { }
        try
        {
            ai1[jp.co.yahoo.android.maps.MapCtrlEvent.EventType.UPDATE_YML.ordinal()] = 20;
        }
        catch (NoSuchFieldError nosuchfielderror22) { }
        $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType = ai1;
        return ai1;
    }

    public ScalebarAndCopyrightLayer(BaseView baseview)
    {
        mCopyright = null;
        mCopyrightValign = 0;
        mPaint = null;
        mScalebarPaint = null;
        mScaleTextPaint = null;
        mScalebarPath = null;
        mMapYml = null;
        mLines = new Vector();
        mLastCopyright = "";
        mCopyrightOn = true;
        mScalebarOn = false;
        mYmlReTry = false;
        mScaleBar_R = 0.0F;
        mScaleline = 0;
        mBaseView = baseview;
        mPaint = new Paint(1);
        mPaint.setStyle(android.graphics.Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(20F);
        mMapYml = new MapYml(baseview, mBaseView.getBaseViewCtrl().getAppid());
        mMapYml.setMapYmlListener(this);
        mScalebarPath = new Path();
        mScalebarPaint = new Paint(1);
        mScalebarPaint.setStyle(android.graphics.Paint.Style.STROKE);
        mScalebarPaint.setAntiAlias(true);
        mScalebarPaint.setStrokeWidth(3F);
        mScalebarPaint.setColor(0xff000000);
        mScaleTextPaint = new Paint(1);
        mScaleTextPaint.setStyle(android.graphics.Paint.Style.STROKE);
        mScaleTextPaint.setAntiAlias(true);
        mScaleTextPaint.setColor(0xff000000);
        mScaleTextPaint.setStrokeWidth(1.0F);
        mScaleTextPaint.setTextSize(12F);
    }

    private void drawCopyright(Canvas canvas, BaseViewCtrl baseviewctrl)
    {
        if (mCopyright == null || !mCopyrightOn) goto _L2; else goto _L1
_L1:
        int i = baseviewctrl.getHeight();
        if (MapView.MapTypeSatellite != baseviewctrl.getMaptype() && MapView.MapTypeHybrid != baseviewctrl.getMaptype()) goto _L4; else goto _L3
_L3:
        mPaint.setColor(-1);
_L8:
        int j = i - 12 * (-1 + mLines.size()) - mCopyrightValign;
        int k = 0;
_L6:
        try
        {
            if (k >= mLines.size())
            {
                return;
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return;
        }
          goto _L5
_L4:
        mPaint.setColor(0xff000000);
        continue; /* Loop/switch isn't completed */
_L5:
        canvas.drawText((String)mLines.get(k), 0.0F, j, mPaint);
        j += 20;
        k++;
        if (true) goto _L6; else goto _L2
_L2:
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private void drawScalebar(Canvas canvas, BaseViewCtrl baseviewctrl)
    {
        int i;
        float f;
label0:
        {
            if (mScalebarOn && mScaleBar_R != 0.0F)
            {
                i = baseviewctrl.getHeight();
                mScalebarPath.reset();
                mScalebarPath.moveTo(10F, i - 70 - mCopyrightValign);
                mScalebarPath.lineTo(10F, i - 50 - mCopyrightValign);
                mScalebarPath.moveTo(10F, i - 60 - mCopyrightValign);
                mScalebarPath.lineTo(mScaleBar_R, i - 60 - mCopyrightValign);
                mScalebarPath.moveTo(mScaleBar_R, i - 70 - mCopyrightValign);
                mScalebarPath.lineTo(mScaleBar_R, i - 50 - mCopyrightValign);
                mScalebarPath.close();
                if (canvas != null)
                {
                    canvas.drawPath(mScalebarPath, mScalebarPaint);
                    f = mScaleline / 1000;
                    if (f >= 1.0F)
                    {
                        break label0;
                    }
                    canvas.drawText((new StringBuilder(String.valueOf(mScaleline))).append("m").toString(), 10F + mScaleBar_R, i - 55 - mCopyrightValign, mScaleTextPaint);
                }
            }
            return;
        }
        canvas.drawText((new StringBuilder(String.valueOf(f))).append("km").toString(), 10F + mScaleBar_R, i - 55 - mCopyrightValign, mScaleTextPaint);
    }

    private void makeCopyrightLines()
    {
        mLines.clear();
        int i = mBaseView.getBaseViewCtrl().getWidth();
        String s = mCopyright;
        do
        {
            int j = mPaint.breakText(s, true, i, null);
            if (j == 0)
            {
                mLines.add(s);
                return;
            }
            mLines.add(s.substring(0, j));
            s = s.substring(j);
        } while (true);
    }

    public void doEvent(MapCtrlEvent mapctrlevent, BaseViewCtrl baseviewctrl)
    {
        $SWITCH_TABLE$jp$co$yahoo$android$maps$MapCtrlEvent$EventType()[mapctrlevent.getEventType().ordinal()];
        JVM INSTR tableswitch 17 20: default 40
    //                   17 41
    //                   18 174
    //                   19 40
    //                   20 118;
           goto _L1 _L2 _L3 _L1 _L4
_L1:
        return;
_L2:
        MapLayerManager maplayermanager = baseviewctrl.getMapLayerManager();
        LayerInfo layerinfo = baseviewctrl.getNowLayer();
        mCopyright = ((MapYml)mapctrlevent.arg1).getCopyright(maplayermanager.getNowMapType(), layerinfo.scale);
        if (mCopyright != null)
        {
            if (!mLastCopyright.equals(mCopyright))
            {
                mLastCopyright = mCopyright;
                makeCopyrightLines();
            }
            mBaseView.reDraw();
            return;
        }
          goto _L1
_L4:
        mYmlReTry = false;
        if (!mMapYml.ThredYmlGet())
        {
            mYmlReTry = true;
        }
        mScaleline = mBaseView.getNowLayer().scaleline;
        mScaleBar_R = mBaseView.getBaseViewCtrl().metersToEquatorPixels(mScaleline);
        return;
_L3:
        mYmlReTry = false;
        mMapYml.setAttestation(baseviewctrl.getAttestation());
        mMapYml.ThredYmlGet();
        return;
    }

    public void draw(Canvas canvas, MapView mapview, boolean flag)
    {
        drawCopyright(canvas, mBaseView.getBaseViewCtrl());
        drawScalebar(canvas, mBaseView.getBaseViewCtrl());
    }

    public boolean endYmlHttpLoader(MapYml mapyml)
    {
        if (mBaseView != null && mYmlReTry)
        {
            mBaseView.doEvent(jp.co.yahoo.android.maps.MapCtrlEvent.EventType.UPDATE_YML, null);
            mYmlReTry = false;
        }
        return false;
    }

    public MapYml getMapYml()
    {
        return mMapYml;
    }

    public boolean getUnderground()
    {
        return mMapYml.getUnderground();
    }

    public void setCopyrightValign(int i)
    {
        mCopyrightValign = i;
    }

    public void setCpOn(boolean flag)
    {
        mCopyrightOn = flag;
    }

    public void setScalebar(boolean flag)
    {
        mScalebarOn = flag;
    }
}
