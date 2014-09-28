// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import java.io.File;
import java.nio.ByteBuffer;

// Referenced classes of package jp.co.yahoo.android.yjvoice:
//            YJVORecorderListener, MonitorListener, DCWrap, YJVORecorder, 
//            Monitor, YJVORecognizeListener, YJVO_STATE, YJVO_SAMPLERATE, 
//            YJVO_TYPE, YJVORecognizeResult, YJVONbestResult, YJVO_DOMAIN, 
//            YJVO_FILTER, YJVO_MODE, YJVO_ACCEPT, YJVO_SENSOR

public class YJVORecognizer
    implements YJVORecorderListener, MonitorListener, LocationListener
{

    private static final int DATA_TIME_MAX = 20000;
    private static final String LINETYPE_3G = "3G";
    private static final String LINETYPE_UNKNOWN = "Unknown";
    private static final String LINETYPE_WIFI = "WiFi";
    private static final float LOCATION_MINDISTANCE = 100F;
    private static final long LOCATION_MINTIME = 0x493e0L;
    private static final int MAX_NUM_RESULT_CANDIDATE = 6;
    private static final String MY_VERSION = "1.1.2";
    private static final String OS_NAME = "Android";
    private static final String STR_RESULT_PARTIAL_OFF = "off";
    private static final String STR_RESULT_PARTIAL_ON = "on";
    private static final String STR_RESULT_TYPE_LATTICE = "lattice";
    private static final String STR_RESULT_TYPE_NBEST = (new StringBuilder()).append(Integer.toString(6)).append("-best").toString();
    private static final String STR_UNKNOWN = "unknown";
    private static final String TAG = "YJVOICE:YJVORecognizer";
    protected Context mContext;
    protected DCWrap mDCW;
    private boolean mIsFinished;
    private int mIsRecognizing;
    private String mLatitude;
    private LocationManager mLocationMan;
    private final Object mLockIsRecognizing = new Object();
    private String mLongitude;
    private int mMaxTime;
    protected Monitor mMonitor;
    private YJVORecognizeListener mRecognizeListener;
    protected YJVORecorder mRecorder;
    private boolean mSensorGps;
    protected short mVolume;

    public YJVORecognizer()
    {
        mVolume = -1;
        mSensorGps = false;
        mIsRecognizing = 0;
        mIsFinished = false;
        mMaxTime = 20000;
        mDCW = new DCWrap();
        mRecorder = new YJVORecorder();
        mMonitor = new Monitor(this);
    }

    private boolean canNotify()
    {
        return mRecognizeListener != null && !mIsFinished;
    }

    private String getLineType()
    {
        ConnectivityManager connectivitymanager;
        if (mContext == null)
        {
            break MISSING_BLOCK_LABEL_97;
        }
        connectivitymanager = (ConnectivityManager)mContext.getSystemService("connectivity");
        if (connectivitymanager == null)
        {
            break MISSING_BLOCK_LABEL_97;
        }
        NetworkInfo networkinfo = connectivitymanager.getActiveNetworkInfo();
        if (networkinfo == null)
        {
            return null;
        }
        String s;
        if (!networkinfo.isAvailable() || !networkinfo.isConnectedOrConnecting())
        {
            break MISSING_BLOCK_LABEL_97;
        }
        s = networkinfo.getTypeName();
        if (s.equalsIgnoreCase("WIFI"))
        {
            return "WiFi";
        }
        if (s.equalsIgnoreCase("MOBILE"))
        {
            return "3G";
        }
        break MISSING_BLOCK_LABEL_97;
        SecurityException securityexception;
        securityexception;
        Log.e("YJVOICE:YJVORecognizer", securityexception.toString());
        return null;
    }

    private String getLocationInfo()
    {
        String s = mLatitude;
        String s1 = null;
        if (s != null)
        {
            String s2 = mLongitude;
            s1 = null;
            if (s2 != null)
            {
                s1 = (new StringBuilder()).append(mLatitude).append(",").append(mLongitude).toString();
            }
        }
        return s1;
    }

    private String getMacAddr()
    {
        Context context;
        String s;
        WifiManager wifimanager;
        WifiInfo wifiinfo;
        String s1;
        try
        {
            context = mContext;
        }
        catch (RuntimeException runtimeexception)
        {
            Log.e("YJVOICE:YJVORecognizer", runtimeexception.toString());
            return null;
        }
        s = null;
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        wifimanager = (WifiManager)mContext.getSystemService("wifi");
        s = null;
        if (wifimanager == null)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        wifiinfo = wifimanager.getConnectionInfo();
        s = null;
        if (wifiinfo == null)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        s1 = wifiinfo.getMacAddress();
        s = s1;
        return s;
    }

    public static final String getVersion()
    {
        return "1.1.2";
    }

    private void needlessLocationInfo()
    {
        if (mLocationMan != null)
        {
            mLocationMan.removeUpdates(this);
            mLocationMan = null;
        }
    }

    private void notifyRecognizeResultToListener(int i, YJVORecognizeResult yjvorecognizeresult)
    {
        this;
        JVM INSTR monitorenter ;
        if (canNotify())
        {
            mRecognizeListener.onRecognizeResult(i, yjvorecognizeresult);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void notifyStateToListener(int i)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = canNotify();
        boolean flag1 = false;
        if (!flag) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch -3 17: default 112
    //                   -3 171
    //                   -2 182
    //                   -1 112
    //                   0 112
    //                   1 112
    //                   2 112
    //                   3 204
    //                   4 112
    //                   5 112
    //                   6 115
    //                   7 112
    //                   8 112
    //                   9 149
    //                   10 112
    //                   11 226
    //                   12 112
    //                   13 160
    //                   14 193
    //                   15 112
    //                   16 112
    //                   17 215;
           goto _L3 _L4 _L5 _L3 _L3 _L3 _L3 _L6 _L3 _L3 _L7 _L3 _L3 _L8 _L3 _L9 _L3 _L10 _L11 _L3 _L3 _L12
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L7:
        YJVO_STATE yjvo_state = YJVO_STATE.PHRASE_START;
_L14:
        mRecognizeListener.onRecognizeState(yjvo_state);
_L2:
        if (!flag1) goto _L3; else goto _L13
_L13:
        setIsFinished(true);
          goto _L3
        Exception exception;
        exception;
        throw exception;
_L8:
        yjvo_state = YJVO_STATE.PHRASE_FINISH;
        flag1 = false;
          goto _L14
_L10:
        yjvo_state = YJVO_STATE.RECOGNIZE_FINISH;
        flag1 = true;
          goto _L14
_L4:
        yjvo_state = YJVO_STATE.RECOGNIZE_CANCEL;
        flag1 = true;
          goto _L14
_L5:
        yjvo_state = YJVO_STATE.RECOGNIZE_CANCEL;
        flag1 = true;
          goto _L14
_L11:
        yjvo_state = YJVO_STATE.RECOGNIZE_CANCEL;
        flag1 = true;
          goto _L14
_L6:
        yjvo_state = YJVO_STATE.RECOGNIZE_ERROR;
        flag1 = true;
          goto _L14
_L12:
        yjvo_state = YJVO_STATE.RECOGNIZE_ERROR;
        flag1 = true;
          goto _L14
_L9:
        yjvo_state = YJVO_STATE.VOICE_TOO_LONG;
        flag1 = false;
          goto _L14
    }

    private void onStateChanged(int i)
    {
        switch (i)
        {
        case -1: 
        default:
            return;

        case -3: 
            notifyStateToListener(i);
            return;

        case -2: 
            procStateMonitorFinish();
            notifyStateToListener(i);
            turnOffIsRecognizing();
            return;

        case 3: // '\003'
            procStateRecognizeStarterror();
            notifyStateToListener(i);
            turnOffIsRecognizing();
            return;

        case 13: // '\r'
            procStateRecognizeStop();
            notifyStateToListener(i);
            turnOffIsRecognizing();
            return;

        case 14: // '\016'
            procStateRecognizeCancel();
            notifyStateToListener(i);
            turnOffIsRecognizing();
            return;

        case 17: // '\021'
            procStateRecognizeError();
            notifyStateToListener(i);
            turnOffIsRecognizing();
            return;

        case 6: // '\006'
            procStateVoiceConfirm();
            notifyStateToListener(i);
            return;

        case 9: // '\t'
            procStatePhraseFinish();
            notifyStateToListener(i);
            return;

        case 11: // '\013'
            procStateVoiceTooLong();
            notifyStateToListener(i);
            return;

        case 2: // '\002'
            procStateRecognizeStart();
            return;

        case 12: // '\f'
            procStateRecognizeResult();
            return;

        case 16: // '\020'
            procStateSendstopUnfinish();
            return;

        case 15: // '\017'
            procStateSendstop();
            return;

        case 4: // '\004'
            procStateBeginCandidate();
            return;

        case 5: // '\005'
            procStateBeginCancel();
            return;

        case 7: // '\007'
            procStateEndCandidate();
            return;

        case 8: // '\b'
            procStateEndCancel();
            return;

        case 10: // '\n'
            procStatePhraseCancel();
            return;

        case 0: // '\0'
            procStateKnockStart();
            return;

        case 1: // '\001'
            procStateKnockStartError();
            return;
        }
    }

    private void readyLocationInfo()
    {
        try
        {
            if (mLocationMan == null && mContext != null)
            {
                mLocationMan = (LocationManager)mContext.getSystemService("location");
            }
            if (mLocationMan != null)
            {
                mLocationMan.requestLocationUpdates("gps", 0x493e0L, 100F, this);
                mLocationMan.requestLocationUpdates("network", 0x493e0L, 100F, this);
            }
            return;
        }
        catch (SecurityException securityexception)
        {
            Log.e("YJVOICE:YJVORecognizer", securityexception.toString());
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            Log.e("YJVOICE:YJVORecognizer", illegalargumentexception.toString());
            return;
        }
        catch (RuntimeException runtimeexception)
        {
            Log.e("YJVOICE:YJVORecognizer", runtimeexception.toString());
        }
    }

    private void setIsFinished(boolean flag)
    {
        mIsFinished = flag;
    }

    private void turnOffIsRecognizing()
    {
        synchronized (mLockIsRecognizing)
        {
            mIsRecognizing = 0;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void destroy()
    {
        mContext = null;
        turnOffIsRecognizing();
        mRecognizeListener = null;
        mRecorder.destroy();
        mMonitor.destroy();
        mDCW.destroy();
    }

    protected final void finalize()
    {
        destroy();
    }

    protected int forceTermination()
    {
        mRecorder.stop();
        int i = mDCW.forceTermination();
        if (i != 0)
        {
            Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: forceTermination return ").append(i).toString());
        }
        mMonitor.stop();
        turnOffIsRecognizing();
        return i;
    }

    public final short getAvarage()
    {
        return mDCW.getAvarage();
    }

    public final DCWrap getDataClientWrapper()
    {
        return mDCW;
    }

    public final short getPeak()
    {
        return mDCW.getPeak();
    }

    public final YJVORecognizeResult getResult(int i)
    {
        return mDCW.getResult(i);
    }

    public final int getResultCount()
    {
        return mDCW.getResultCount();
    }

    public final double getSNRate()
    {
        return mDCW.getSNRate();
    }

    public final short getVolume()
    {
        return mVolume;
    }

    public final int init(YJVORecognizeListener yjvorecognizelistener, Context context)
    {
        return init(YJVO_SAMPLERATE.SAMPLERATE_16000, 20000, yjvorecognizelistener, context);
    }

    public int init(YJVO_SAMPLERATE yjvo_samplerate, int i, YJVORecognizeListener yjvorecognizelistener, Context context)
    {
        if (yjvorecognizelistener == null) goto _L2; else goto _L1
_L1:
        mRecognizeListener = yjvorecognizelistener;
        if (context == null) goto _L4; else goto _L3
_L3:
        mContext = context.getApplicationContext();
        static class _cls2
        {

            static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_ACCEPT[];
            static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[];
            static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_FILTER[];
            static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_MODE[];
            static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[];
            static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SENSOR[];
            static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_TYPE[];

            static 
            {
                $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_ACCEPT = new int[YJVO_ACCEPT.values().length];
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_ACCEPT[YJVO_ACCEPT.ACCEPT_ACCEPT.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_ACCEPT[YJVO_ACCEPT.ACCEPT_POSITIVE.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_ACCEPT[YJVO_ACCEPT.ACCEPT_NEITHER.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_ACCEPT[YJVO_ACCEPT.ACCEPT_NEGATIVE.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_ACCEPT[YJVO_ACCEPT.ACCEPT_REJECT.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror4) { }
                $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SENSOR = new int[YJVO_SENSOR.values().length];
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SENSOR[YJVO_SENSOR.SENSOR_NONE.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SENSOR[YJVO_SENSOR.SENSOR_GPS.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror6) { }
                $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_FILTER = new int[YJVO_FILTER.values().length];
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_FILTER[YJVO_FILTER.NORMAL.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_FILTER[YJVO_FILTER.NUMBER.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_FILTER[YJVO_FILTER.SENTENCE.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror9) { }
                $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_TYPE = new int[YJVO_TYPE.values().length];
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_TYPE[YJVO_TYPE.NBEST.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_TYPE[YJVO_TYPE.LATTICE.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror11) { }
                $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN = new int[YJVO_DOMAIN.values().length];
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.SEARCH.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror12) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.TALK.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror13) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.DICTATION.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror14) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.SEARCH_16K.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror15) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.TALK_16K.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror16) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.DICTATION_16K.ordinal()] = 6;
                }
                catch (NoSuchFieldError nosuchfielderror17) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.SEARCH_8K.ordinal()] = 7;
                }
                catch (NoSuchFieldError nosuchfielderror18) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.TALK_8K.ordinal()] = 8;
                }
                catch (NoSuchFieldError nosuchfielderror19) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.DICTATION_8K.ordinal()] = 9;
                }
                catch (NoSuchFieldError nosuchfielderror20) { }
                $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_MODE = new int[YJVO_MODE.values().length];
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_MODE[YJVO_MODE.PHRASE.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror21) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_MODE[YJVO_MODE.CONTINUOUS.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror22) { }
                $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE = new int[YJVO_SAMPLERATE.values().length];
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_8000.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror23) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_11025.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror24) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_16000.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror25) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_22050.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror26) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_32000.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror27) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_44100.ordinal()] = 6;
                }
                catch (NoSuchFieldError nosuchfielderror28) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_48000.ordinal()] = 7;
                }
                catch (NoSuchFieldError nosuchfielderror29) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_88200.ordinal()] = 8;
                }
                catch (NoSuchFieldError nosuchfielderror30) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_96000.ordinal()] = 9;
                }
                catch (NoSuchFieldError nosuchfielderror31)
                {
                    return;
                }
            }
        }

        _cls2..SwitchMap.jp.co.yahoo.android.yjvoice.YJVO_SAMPLERATE[yjvo_samplerate.ordinal()];
        JVM INSTR tableswitch 1 9: default 80
    //                   1 282
    //                   2 295
    //                   3 80
    //                   4 308
    //                   5 321
    //                   6 334
    //                   7 347
    //                   8 360
    //                   9 373;
           goto _L5 _L6 _L7 _L5 _L8 _L9 _L10 _L11 _L12 _L13
_L13:
        break MISSING_BLOCK_LABEL_373;
_L5:
        YJVORecorder.SAMPLERATE samplerate;
        int j;
        samplerate = YJVORecorder.SAMPLERATE.K16;
        j = 16000;
_L14:
        mMaxTime = i;
        String s = "";
        if (mContext != null)
        {
            s = (new StringBuilder()).append(mContext.getFilesDir().getAbsolutePath()).append("/yjvoice/").toString();
            File file = new File(s);
            if (!file.exists() && !file.mkdir())
            {
                s = "";
                Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: failed in mkdir. path:").append(file).toString());
            }
        }
        int k = mDCW.init(j, 2, mMaxTime, s);
        if (k != 0)
        {
            switch (k)
            {
            default:
                destroy();
                Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error:mDCW.init:").append(k).toString());
                return k;

            case 101: // 'e'
                Log.e("YJVOICE:YJVORecognizer", "warning:WARNING_LOCAL_LOAD");
                break;
            }
        }
        break MISSING_BLOCK_LABEL_395;
_L2:
        return -32768;
_L4:
        return -32768;
_L6:
        samplerate = YJVORecorder.SAMPLERATE.K8;
        j = 8000;
          goto _L14
_L7:
        samplerate = YJVORecorder.SAMPLERATE.K11;
        j = 11025;
          goto _L14
_L8:
        samplerate = YJVORecorder.SAMPLERATE.K22;
        j = 22050;
          goto _L14
_L9:
        samplerate = YJVORecorder.SAMPLERATE.K32;
        j = 32000;
          goto _L14
_L10:
        samplerate = YJVORecorder.SAMPLERATE.K44;
        j = 44100;
          goto _L14
_L11:
        samplerate = YJVORecorder.SAMPLERATE.K48;
        j = 48000;
          goto _L14
_L12:
        samplerate = YJVORecorder.SAMPLERATE.K88;
        j = 0x15888;
          goto _L14
        samplerate = YJVORecorder.SAMPLERATE.K96;
        j = 0x17700;
          goto _L14
        if (mRecorder.init(this, samplerate, mMaxTime) == -32768)
        {
            Log.e("YJVOICE:YJVORecognizer", "error:mRecorder.init");
            destroy();
            return -32768;
        }
        if (Build.MODEL == null) goto _L16; else goto _L15
_L15:
        int i2 = mDCW.setParam(4, Build.MODEL);
        if (i2 != 0)
        {
            Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: setParam:PARAM_DEVICENAME,value:").append(Build.MODEL).append(" return:").append(i2).toString());
        }
_L18:
        int i1 = mDCW.setParam(8, "1.1.2");
        if (i1 != 0)
        {
            Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: setParam:PARAM_SDKVER,value:1.1.2 return:").append(i1).toString());
        }
        int j1 = mDCW.setParam(9, (new StringBuilder()).append("Android").append(android.os.Build.VERSION.RELEASE).toString());
        if (j1 != 0)
        {
            Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: setParam:PARAM_OSINFO,value:Android").append(android.os.Build.VERSION.RELEASE).append(" return:").append(j1).toString());
        }
        String s1 = getMacAddr();
        if (s1 != null)
        {
            int l1 = mDCW.setParam(10, s1);
            if (l1 != 0)
            {
                Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: setParam:PARAM_MACADDRESS,value:").append(s1).append(" return:").append(l1).toString());
            }
        }
        int k1 = setResultType(YJVO_TYPE.NBEST);
        if (k1 != 0)
        {
            Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: setResultType:").append(YJVO_TYPE.NBEST).append(" return:").append(k1).toString());
        }
        return 0;
_L16:
        Log.e("YJVOICE:YJVORecognizer", "error: Build.MODEL is null");
        int l = mDCW.setParam(4, "unknown");
        if (l != 0)
        {
            Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: setParam:PARAM_DEVICENAME,value:unknown return:").append(l).toString());
        }
        if (true) goto _L18; else goto _L17
_L17:
    }

    protected boolean isPhraseMode()
    {
        return mDCW.getMode() == 0;
    }

    public final boolean isRecognizing()
    {
        return mIsRecognizing == 1 || mDCW.isRecognizing();
    }

    public void onLocationChanged(Location location)
    {
        mLatitude = String.valueOf(location.getLatitude());
        mLongitude = String.valueOf(location.getLongitude());
    }

    public void onProviderDisabled(String s)
    {
    }

    public void onProviderEnabled(String s)
    {
    }

    public void onStatusChanged(String s, int i, Bundle bundle)
    {
    }

    protected void onVolumeChanged(short word0)
    {
    }

    protected int procStateBeginCancel()
    {
        return 0;
    }

    protected int procStateBeginCandidate()
    {
        return 0;
    }

    protected int procStateEndCancel()
    {
        return 0;
    }

    protected int procStateEndCandidate()
    {
        return 0;
    }

    protected int procStateKnockStart()
    {
        return 0;
    }

    protected int procStateKnockStartError()
    {
        return 0;
    }

    protected int procStateMonitorFinish()
    {
        recognizeCancelInternal();
        return 0;
    }

    protected int procStatePhraseCancel()
    {
        return 0;
    }

    protected int procStatePhraseFinish()
    {
        return 0;
    }

    protected int procStateRecognizeCancel()
    {
        mRecorder.stop();
        mMonitor.stop();
        return 0;
    }

    protected int procStateRecognizeError()
    {
        mRecorder.stop();
        mMonitor.stop();
        return 0;
    }

    protected int procStateRecognizeResult()
    {
        YJVORecognizeResult yjvorecognizeresult = mDCW.getResult();
        if (yjvorecognizeresult != null)
        {
            int i;
            if (yjvorecognizeresult.type == YJVO_TYPE.NBEST)
            {
                i = ((YJVONbestResult)yjvorecognizeresult).index;
            } else
            {
                i = 0;
            }
            notifyRecognizeResultToListener(i, yjvorecognizeresult);
        } else
        {
            Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("stateChanged STATE_RECOGNIZE_RESULT:YJVORecognizeResult:").append(yjvorecognizeresult).toString());
        }
        return 0;
    }

    protected int procStateRecognizeStart()
    {
        return 0;
    }

    protected int procStateRecognizeStarterror()
    {
        mRecorder.stop();
        return 0;
    }

    protected int procStateRecognizeStop()
    {
        mRecorder.stop();
        mDCW.uploadData();
        return 0;
    }

    protected int procStateSendstop()
    {
        return 0;
    }

    protected int procStateSendstopUnfinish()
    {
        return 0;
    }

    protected int procStateVoiceConfirm()
    {
        return 0;
    }

    protected int procStateVoiceTooLong()
    {
        return 0;
    }

    public int recognizeCancel()
    {
        if (!isRecognizing())
        {
            return -202;
        } else
        {
            onStateChanged(-3);
            (new Thread(new Runnable() {

                final YJVORecognizer this$0;

                public void run()
                {
                    recognizeCancelInternal();
                }

            
            {
                this$0 = YJVORecognizer.this;
                super();
            }
            })).start();
            return 0;
        }
    }

    protected int recognizeCancelInternal()
    {
        mRecorder.stop();
        int i = mDCW.recognizeCancel();
        if (i != 0)
        {
            mMonitor.stop();
            turnOffIsRecognizing();
            Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: recognizeCancel return ").append(i).toString());
        }
        return i;
    }

    public int recognizeStart()
    {
label0:
        {
            synchronized (mLockIsRecognizing)
            {
                if (mIsRecognizing == 0)
                {
                    break MISSING_BLOCK_LABEL_49;
                }
                if (!mRecorder.isRecording() && !mMonitor.isMonitoring())
                {
                    break label0;
                }
            }
            return -201;
        }
        Log.e("YJVOICE:YJVORecognizer", "error:recognizeStart:Recognizer is recognizing, but recorder and monitor is not in processing.");
        if (!mDCW.isRecognizing())
        {
            break MISSING_BLOCK_LABEL_70;
        }
        obj;
        JVM INSTR monitorexit ;
        return -201;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        int i;
        mIsRecognizing = 1;
        setIsFinished(false);
        i = mDCW.resetData();
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_126;
        }
        Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: resetData:: return:").append(i).toString());
        turnOffIsRecognizing();
        obj;
        JVM INSTR monitorexit ;
        return i;
        obj;
        JVM INSTR monitorexit ;
        String s = getLineType();
        if (s == null)
        {
            s = "Unknown";
        }
        int j = mDCW.setParam(5, s);
        if (j != 0)
        {
            Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: setParam:PARAM_LINETYPE,value:").append(s).append(" return:").append(j).toString());
        }
        int l;
        if (mSensorGps)
        {
            String s1 = getLocationInfo();
            if (s1 != null)
            {
                int i1 = mDCW.setParam(12, s1);
                if (i1 != 0)
                {
                    Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: setParam:PARAM_SENSOR_GPS,value:").append(s1).append(" return:").append(i1).toString());
                }
            }
        } else
        {
            int k = mDCW.setParam(12, "");
            if (k != 0)
            {
                Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: setParam:PARAM_SENSOR_GPS, (unset) return:").append(k).toString());
            }
        }
        mRecorder.start();
        mMonitor.clearState();
        l = mDCW.recognizeStartAsync();
        if (l == 0)
        {
            mMonitor.start();
            return l;
        } else
        {
            mRecorder.stop();
            turnOffIsRecognizing();
            Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: recognizeStartAsync return ").append(l).toString());
            return l;
        }
    }

    public int recognizeStop()
    {
        mRecorder.stop();
        int i = mDCW.recognizeStop();
        if (i != 0)
        {
            mMonitor.stop();
            turnOffIsRecognizing();
            Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: recognizeStop return ").append(i).toString());
        }
        return i;
    }

    public void recordFinished(int i)
    {
        mVolume = -1;
        if (i < 0)
        {
            recognizeCancelInternal();
        }
    }

    public void recordStart()
    {
        if (canNotify())
        {
            mRecognizeListener.onRecordingStart();
        }
    }

    public void recordState(ByteBuffer bytebuffer, int i, boolean flag)
    {
        if (i <= 0) goto _L2; else goto _L1
_L1:
        int j = mDCW.setData(bytebuffer, i, 1);
        if (j < 0) goto _L4; else goto _L3
_L3:
        mVolume = (short)j;
        onVolumeChanged(mVolume);
        if (canNotify())
        {
            mRecognizeListener.onVolumeChanged(mVolume);
        }
        if (flag)
        {
            mDCW.recognizeStop();
        }
_L2:
        return;
_L4:
        switch (j)
        {
        default:
            Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: call mDCW.setData()ir:").append(j).toString());
            return;

        case -10101: 
            break;

        case -10102: 
            mRecorder.stop();
            break; /* Loop/switch isn't completed */
        }
        if (true) goto _L2; else goto _L5
_L5:
        Log.e("YJVOICE:YJVORecognizer", (new StringBuilder()).append("error: BUFFEROVERFLOW: call mDCW.setData() ir:").append(j).toString());
        return;
    }

    protected void recorderStop()
    {
        mRecorder.stop();
    }

    public final int setApplicationData(String s, String s1)
    {
        int i;
        if (s == null || s1 == null)
        {
            i = -32768;
        } else
        {
            i = mDCW.setParam(6, s);
            if (i == 0)
            {
                return mDCW.setParam(7, s1);
            }
        }
        return i;
    }

    public final int setDomain(YJVO_DOMAIN yjvo_domain)
    {
        _cls2..SwitchMap.jp.co.yahoo.android.yjvoice.YJVO_DOMAIN[yjvo_domain.ordinal()];
        JVM INSTR tableswitch 2 9: default 56
    //                   2 71
    //                   3 78
    //                   4 85
    //                   5 92
    //                   6 99
    //                   7 106
    //                   8 113
    //                   9 120;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
        String s = "VoiceSearch";
_L11:
        return mDCW.setParam(19, s);
_L2:
        s = "Dialogue";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "Dictation";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "VoiceSearch16k";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "Dialogue16k";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "Dictation";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "VoiceSearch8k";
        continue; /* Loop/switch isn't completed */
_L8:
        s = "Dialogue8k";
        continue; /* Loop/switch isn't completed */
_L9:
        s = "Dictation8k";
        if (true) goto _L11; else goto _L10
_L10:
    }

    public final int setFilter(YJVO_FILTER yjvo_filter)
    {
        _cls2..SwitchMap.jp.co.yahoo.android.yjvoice.YJVO_FILTER[yjvo_filter.ordinal()];
        JVM INSTR tableswitch 2 3: default 32
    //                   2 47
    //                   3 54;
           goto _L1 _L2 _L3
_L1:
        String s = "normal";
_L5:
        return mDCW.setParam(23, s);
_L2:
        s = "number";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "sentence";
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final int setMode(YJVO_MODE yjvo_mode)
    {
        _cls2..SwitchMap.jp.co.yahoo.android.yjvoice.YJVO_MODE[yjvo_mode.ordinal()];
        JVM INSTR tableswitch 2 2: default 28
    //                   2 39;
           goto _L1 _L2
_L1:
        int i = 0;
_L4:
        return mDCW.setMode(i);
_L2:
        i = 1;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final int setRecogOffset(int i)
    {
        return mDCW.setRecogOffset(i);
    }

    public final int setResultAccept(String s, int i, YJVO_ACCEPT yjvo_accept)
    {
        _cls2..SwitchMap.jp.co.yahoo.android.yjvoice.YJVO_ACCEPT[yjvo_accept.ordinal()];
        JVM INSTR tableswitch 1 5: default 44
    //                   1 59
    //                   2 65
    //                   3 71
    //                   4 77
    //                   5 83;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        byte byte0 = 2;
_L8:
        return mDCW.setResultAccept(s, i, byte0);
_L2:
        byte0 = 0;
        continue; /* Loop/switch isn't completed */
_L3:
        byte0 = 1;
        continue; /* Loop/switch isn't completed */
_L4:
        byte0 = 2;
        continue; /* Loop/switch isn't completed */
_L5:
        byte0 = 3;
        continue; /* Loop/switch isn't completed */
_L6:
        byte0 = 4;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final int setResultPartial(boolean flag)
    {
        String s;
        if (!flag)
        {
            s = "off";
        } else
        {
            s = "on";
        }
        return mDCW.setParam(22, s);
    }

    public final int setResultType(YJVO_TYPE yjvo_type)
    {
        _cls2..SwitchMap.jp.co.yahoo.android.yjvoice.YJVO_TYPE[yjvo_type.ordinal()];
        JVM INSTR tableswitch 2 2: default 28
    //                   2 43;
           goto _L1 _L2
_L1:
        String s = STR_RESULT_TYPE_NBEST;
_L4:
        return mDCW.setParam(21, s);
_L2:
        s = "lattice";
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void setSensorInfo(YJVO_SENSOR yjvo_sensor)
    {
        switch (_cls2..SwitchMap.jp.co.yahoo.android.yjvoice.YJVO_SENSOR[yjvo_sensor.ordinal()])
        {
        default:
            return;

        case 1: // '\001'
            mSensorGps = false;
            needlessLocationInfo();
            return;

        case 2: // '\002'
            mSensorGps = true;
            break;
        }
        readyLocationInfo();
    }

    public final int setServer(int i, String s, String s1, short word0, boolean flag)
    {
        int j = mDCW.setHost(i, s);
        if (j == 0)
        {
            j = mDCW.setPath(i, s1);
            if (j == 0)
            {
                j = mDCW.setPort(i, word0);
                if (j == 0)
                {
                    j = mDCW.setSSL(i, flag);
                }
            }
        }
        return j;
    }

    public final int setServer(String s, String s1, short word0, boolean flag)
    {
        int i = mDCW.setHost(0, s);
        if (i == 0)
        {
            i = mDCW.setPath(0, s1);
            if (i == 0)
            {
                i = mDCW.setPort(0, word0);
                if (i == 0)
                {
                    i = mDCW.setSSL(0, flag);
                }
            }
        }
        return i;
    }

    public final int setUserDic(String s)
    {
        return mDCW.setUserDic(s);
    }

    public final void stateChanged(int i)
    {
        onStateChanged(i);
    }

}
