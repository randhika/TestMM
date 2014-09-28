// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;

import android.util.Log;
import java.nio.ByteBuffer;

// Referenced classes of package jp.co.yahoo.android.yjvoice:
//            YJVORecognizeResult

class DCWrap
{

    public static final int ACCEPT_ACCEPT = 0;
    public static final int ACCEPT_NEGATIVE = 3;
    public static final int ACCEPT_NEITHER = 2;
    public static final int ACCEPT_POSITIVE = 1;
    public static final int ACCEPT_REJECT = 4;
    public static final int CALLBACK_DATACLIENT_RESULT = 1;
    public static final int CALLBACK_DATACLIENT_STATE = 0;
    public static final int CALLBACK_DATACLIENT_UPLOAD = 2;
    public static final int CODEC_FLAC = 2;
    public static final int CODEC_LPCM = 0;
    public static final int CODEC_MFCC = 4;
    public static final int CODEC_SPEEX = 3;
    public static final int CODEC_WAVE = 1;
    public static final int ENDIAN_BIG = 2;
    public static final int ENDIAN_LITTLE = 1;
    public static final int ENDIAN_NONE = 0;
    public static final int FILTER_NONE = 0;
    public static final int FILTER_NUMBER = 1;
    private static final int INVALID_DC = 0;
    public static final int LOCAL = 2;
    public static final int MODE_CONTINUOUS = 1;
    public static final int MODE_PHRASE = 0;
    public static final int PARAM_APPNAME = 6;
    public static final int PARAM_APPVER = 7;
    public static final int PARAM_BCOOKIE = 11;
    public static final int PARAM_COMPRESS_RESPONSE = 27;
    public static final int PARAM_DEVICENAME = 4;
    public static final int PARAM_ETC_SVTIME = 29;
    public static final int PARAM_ETC_VER = 28;
    public static final int PARAM_LINETYPE = 5;
    public static final int PARAM_MACADDRESS = 10;
    public static final int PARAM_OSINFO = 9;
    public static final int PARAM_RECOGNIZE_START = 30;
    public static final int PARAM_RECOG_CONTEXT = 18;
    public static final int PARAM_RECOG_DOMAIN = 19;
    public static final int PARAM_RECOG_ENV = 20;
    public static final int PARAM_RESULT_FILTER = 23;
    public static final int PARAM_RESULT_PARTIAL = 22;
    public static final int PARAM_RESULT_POST_DELIM = 25;
    public static final int PARAM_RESULT_POST_RANK = 26;
    public static final int PARAM_RESULT_POST_URL = 24;
    public static final int PARAM_RESULT_TYPE = 21;
    public static final int PARAM_SDKVER = 8;
    public static final int PARAM_SENSOR_GPS = 12;
    public static final int PARAM_SENSOR_MAGNETOSPHERE = 13;
    public static final int PARAM_SESSIONID = 0;
    public static final int PARAM_SPEAKER_AGE = 16;
    public static final int PARAM_SPEAKER_GENDER = 15;
    public static final int PARAM_SPEAKER_REGIONAL = 17;
    public static final int PARAM_TERMID = 2;
    public static final int PARAM_USER_ADAPT = 14;
    public static final int PARAM_UTTID = 1;
    public static final int PARAM_YID = 3;
    public static final String RECOG_DOMAIN_DICTATION = "Dictation";
    public static final String RECOG_DOMAIN_DICTATION_16K = "Dictation";
    public static final String RECOG_DOMAIN_DICTATION_8K = "Dictation8k";
    public static final String RECOG_DOMAIN_SEARCH = "VoiceSearch";
    public static final String RECOG_DOMAIN_SEARCH_16K = "VoiceSearch16k";
    public static final String RECOG_DOMAIN_SEARCH_8K = "VoiceSearch8k";
    public static final String RECOG_DOMAIN_TALK = "Dialogue";
    public static final String RECOG_DOMAIN_TALK_16K = "Dialogue16k";
    public static final String RECOG_DOMAIN_TALK_8K = "Dialogue8k";
    public static final String RESULT_FILTER_NORMAL = "normal";
    public static final String RESULT_FILTER_NUMBER = "number";
    public static final String RESULT_FILTER_SENTENCE = "sentence";
    public static final int RESULT_STATE_EOD = 1;
    public static final int RESULT_STATE_NORMAL = 0;
    public static final int RESULT_STATE_PARTIAL = 3;
    public static final int RESULT_STATE_TIMEOUT = 2;
    public static final int SAMPLE_16BIT = 2;
    public static final int SAMPLE_8BIT = 1;
    public static final int SAMPLE_RATE_11K = 11025;
    public static final int SAMPLE_RATE_16K = 16000;
    public static final int SAMPLE_RATE_22K = 22050;
    public static final int SAMPLE_RATE_32K = 32000;
    public static final int SAMPLE_RATE_44K = 44100;
    public static final int SAMPLE_RATE_48K = 48000;
    public static final int SAMPLE_RATE_88K = 0x15888;
    public static final int SAMPLE_RATE_8K = 8000;
    public static final int SAMPLE_RATE_96K = 0x17700;
    public static final int SERVER_RECOGNITION = 0;
    public static final int SERVER_UPLOAD = 1;
    public static final int STATE_BEGIN_CANCEL = 5;
    public static final int STATE_BEGIN_CANDIDATE = 4;
    public static final int STATE_CANCEL_ASYNC = -3;
    public static final int STATE_END_CANCEL = 8;
    public static final int STATE_END_CANDIDATE = 7;
    public static final int STATE_ERROR = -1;
    public static final int STATE_KNOCK_START = 0;
    public static final int STATE_KNOCK_START_ERROR = 1;
    public static final int STATE_MONITOR_FINISH = -2;
    public static final int STATE_PHRASE_CANCEL = 10;
    public static final int STATE_PHRASE_FINISH = 9;
    public static final int STATE_RECOGNIZE_CANCEL = 14;
    public static final int STATE_RECOGNIZE_ERROR = 17;
    public static final int STATE_RECOGNIZE_RESULT = 12;
    public static final int STATE_RECOGNIZE_START = 2;
    public static final int STATE_RECOGNIZE_START_ERROR = 3;
    public static final int STATE_RECOGNIZE_STOP = 13;
    public static final int STATE_SENDSTOP = 15;
    public static final int STATE_SENDSTOP_UNFINISH = 16;
    public static final int STATE_UPLOAD_ERROR = -1;
    public static final int STATE_UPLOAD_FINISH_ACCEPT = 5;
    public static final int STATE_UPLOAD_FINISH_DEBUG = 7;
    public static final int STATE_UPLOAD_FINISH_VOICE = 1;
    public static final int STATE_UPLOAD_FINISH_XML = 3;
    public static final int STATE_UPLOAD_START_ACCEPT = 4;
    public static final int STATE_UPLOAD_START_DEBUG = 6;
    public static final int STATE_UPLOAD_START_VOICE = 0;
    public static final int STATE_UPLOAD_START_XML = 2;
    public static final int STATE_VOICE_CONFIRM = 6;
    public static final int STATE_VOICE_TOO_LONG = 11;
    private static final String TAG = "YJVOICE:DCWrap";
    static boolean cLoadedLib = false;
    private long mDC;
    private long mDestroyDC;

    public DCWrap()
    {
        mDC = 0L;
        mDestroyDC = 0L;
        if (cLoadedLib)
        {
            mDC = create();
        } else
        {
            mDC = 0L;
        }
        if (!isValid())
        {
            Log.e("YJVOICE:DCWrap", "error: failed in creating of dataclient");
        }
    }

    private final long create()
    {
        return jniCreate();
    }

    private final boolean isValid()
    {
        return mDC != 0L;
    }

    private native int jniCheckConnection(long l);

    private native long jniCreate();

    private native int jniDestroy(long l);

    private native int jniForceTermination(long l);

    private native short jniGetAvarage(long l);

    private native int jniGetMode(long l);

    private native String jniGetParam(long l, int i);

    private native short jniGetPeak(long l);

    private native YJVORecognizeResult jniGetResult(long l);

    private native YJVORecognizeResult jniGetResult(long l, int i);

    private native int jniGetResultCount(long l);

    private native double jniGetSNRate(long l);

    private native int jniGetState(long l);

    private native int jniGetState(long l, int i);

    private native int jniGetStateCount(long l);

    private native int jniGetStateError(long l);

    private native String jniGetUserDic(long l);

    private native int jniInit(long l, int i, int j, int k, String s);

    private native boolean jniIsKnocking(long l);

    private native boolean jniIsRecognizeStarted(long l);

    private native boolean jniIsRecognizing(long l);

    private native int jniKnock(long l, int i, int j, int k);

    private native int jniKnockAsync(long l, int i, int j, int k);

    private native int jniKnockCancel(long l);

    private native int jniRecognizeCancel(long l);

    private native int jniRecognizeStart(long l);

    private native int jniRecognizeStartAsync(long l);

    private native int jniRecognizeStop(long l);

    private native int jniResetData(long l);

    private native int jniSetBandWidth(long l, int i, int j);

    private native int jniSetBufSize(long l, int i, int j);

    private native int jniSetCodec(long l, int i, int j);

    private native int jniSetData(long l, ByteBuffer bytebuffer, int i, int j);

    private native int jniSetData(long l, ByteBuffer bytebuffer, int i, int j, int k, int i1, 
            int j1, short word0, short word1);

    private native int jniSetHost(long l, int i, String s);

    private native int jniSetMode(long l, int i);

    private native int jniSetParam(long l, int i, String s);

    private native int jniSetPath(long l, int i, String s);

    private native int jniSetPort(long l, int i, short word0);

    private native int jniSetRecogOffset(long l, int i);

    private native int jniSetResultAccept(long l, String s, int i, int j);

    private native int jniSetSSL(long l, int i, boolean flag);

    private native int jniSetTimeOut(long l, int i, int j);

    private native int jniSetUserAgent(long l, int i, String s);

    private native int jniSetUserDic(long l, String s);

    private native int jniUploadData(long l);

    public static final String stateToString(int i)
    {
        switch (i)
        {
        default:
            return "bad state";

        case 0: // '\0'
            return "STATE_KNOCK_START";

        case 1: // '\001'
            return "STATE_KNOCK_START_ERROR";

        case 2: // '\002'
            return "STATE_RECOGNIZE_START";

        case 3: // '\003'
            return "STATE_RECOGNIZE_START_ERROR";

        case 4: // '\004'
            return "BEGIN_CANDIDATE";

        case 5: // '\005'
            return "BEGIN_CANCEL";

        case 6: // '\006'
            return "VOICE_CONFIRM";

        case 7: // '\007'
            return "END_CANDIDATE";

        case 8: // '\b'
            return "END_CANCEL";

        case 9: // '\t'
            return "PHRASE_FINISH";

        case 10: // '\n'
            return "PHRASE_CANCEL";

        case 11: // '\013'
            return "VOICE_TOO_LONG";

        case 12: // '\f'
            return "RECOGNIZE_RESULT";

        case 13: // '\r'
            return "RECOGNIZE_STOP";

        case 14: // '\016'
            return "RECOGNIZE_CANCEL";

        case 15: // '\017'
            return "SENDSTOP";

        case 16: // '\020'
            return "SENDSTOP_UNFINISH";

        case 17: // '\021'
            return "RECOGNIZE_ERROR";

        case -1: 
            return "STATE_ERROR";

        case -2: 
            return "STATE_MONITOR_FINISH";

        case -3: 
            return "STATE_CANCEL_ASYNC";
        }
    }

    public final int checkConnection()
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniCheckConnection(mDC);
        }
    }

    public final int destroy()
    {
        if (!isValid())
        {
            return 0;
        } else
        {
            mDestroyDC = mDC;
            mDC = 0L;
            return jniDestroy(mDestroyDC);
        }
    }

    protected final void finalize()
    {
        destroy();
    }

    public final int forceTermination()
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniForceTermination(mDC);
        }
    }

    public final short getAvarage()
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniGetAvarage(mDC);
        }
    }

    public final int getMode()
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniGetMode(mDC);
        }
    }

    public final String getParam(int i)
    {
        if (!isValid())
        {
            return "";
        } else
        {
            return jniGetParam(mDC, i);
        }
    }

    public final short getPeak()
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniGetPeak(mDC);
        }
    }

    public final YJVORecognizeResult getResult()
    {
        if (!isValid())
        {
            return null;
        } else
        {
            return jniGetResult(mDC);
        }
    }

    public final YJVORecognizeResult getResult(int i)
    {
        if (!isValid())
        {
            return null;
        } else
        {
            return jniGetResult(mDC, i);
        }
    }

    public final int getResultCount()
    {
        if (!isValid())
        {
            return 0;
        } else
        {
            return jniGetResultCount(mDC);
        }
    }

    public final double getSNRate()
    {
        if (!isValid())
        {
            return -32768D;
        } else
        {
            return jniGetSNRate(mDC);
        }
    }

    public final int getState()
    {
        if (!isValid())
        {
            return -1;
        } else
        {
            return jniGetState(mDC);
        }
    }

    public final int getState(int i)
    {
        if (!isValid())
        {
            return -1;
        } else
        {
            return jniGetState(mDC, i);
        }
    }

    public final int getStateCount()
    {
        if (!isValid())
        {
            return 0;
        } else
        {
            return jniGetStateCount(mDC);
        }
    }

    public final int getStateError()
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniGetStateError(mDC);
        }
    }

    public final String getUserDic()
    {
        if (!isValid())
        {
            return "";
        } else
        {
            return jniGetUserDic(mDC);
        }
    }

    public final int init(int i, int j, int k, String s)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniInit(mDC, i, j, k, s);
        }
    }

    public final boolean isKnocking()
    {
        if (!isValid())
        {
            return false;
        } else
        {
            return jniIsKnocking(mDC);
        }
    }

    public final boolean isRecognizeStarted()
    {
        if (!isValid())
        {
            return false;
        } else
        {
            return jniIsRecognizeStarted(mDC);
        }
    }

    public final boolean isRecognizing()
    {
        if (!isValid())
        {
            return false;
        } else
        {
            return jniIsRecognizing(mDC);
        }
    }

    public final int knock(int i, int j, int k)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniKnock(mDC, i, j, k);
        }
    }

    public final int knockAsync(int i, int j, int k)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniKnockAsync(mDC, i, j, k);
        }
    }

    public final int knockCancel()
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniKnockCancel(mDC);
        }
    }

    public final int recognizeCancel()
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniRecognizeCancel(mDC);
        }
    }

    public final int recognizeStart()
    {
        if (!isValid())
        {
            Log.e("YJVOICE:DCWrap", "error: failed in recognizeStart: invalid dataclient");
            return -32768;
        } else
        {
            return jniRecognizeStart(mDC);
        }
    }

    public final int recognizeStartAsync()
    {
        if (!isValid())
        {
            Log.e("YJVOICE:DCWrap", "error: failed in recognizeStartAsync: invalid dataclient");
            return -32768;
        } else
        {
            return jniRecognizeStartAsync(mDC);
        }
    }

    public final int recognizeStop()
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniRecognizeStop(mDC);
        }
    }

    public final int resetData()
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniResetData(mDC);
        }
    }

    public final int setBandWidth(int i, int j)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniSetBandWidth(mDC, i, j);
        }
    }

    public final int setBufSize(int i, int j)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniSetBufSize(mDC, i, j);
        }
    }

    public final int setCodec(int i, int j)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniSetCodec(mDC, i, j);
        }
    }

    public final int setData(ByteBuffer bytebuffer, int i, int j)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniSetData(mDC, bytebuffer, i, j);
        }
    }

    public final int setData(ByteBuffer bytebuffer, int i, int j, int k, int l, int i1, short word0, 
            short word1)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniSetData(mDC, bytebuffer, i, j, k, l, i1, word0, word1);
        }
    }

    public final int setHost(int i, String s)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniSetHost(mDC, i, s);
        }
    }

    public final int setMode(int i)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniSetMode(mDC, i);
        }
    }

    public final int setParam(int i, String s)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniSetParam(mDC, i, s);
        }
    }

    public final int setPath(int i, String s)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniSetPath(mDC, i, s);
        }
    }

    public final int setPort(int i, short word0)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniSetPort(mDC, i, word0);
        }
    }

    public final int setRecogOffset(int i)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniSetRecogOffset(mDC, i);
        }
    }

    public final int setResultAccept(String s, int i, int j)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniSetResultAccept(mDC, s, i, j);
        }
    }

    public final int setSSL(int i, boolean flag)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniSetSSL(mDC, i, flag);
        }
    }

    public final int setTimeOut(int i, int j)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniSetTimeOut(mDC, i, j);
        }
    }

    public final int setUserAgent(int i, String s)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniSetUserAgent(mDC, i, s);
        }
    }

    public final int setUserDic(String s)
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniSetUserDic(mDC, s);
        }
    }

    public final int uploadData()
    {
        if (!isValid())
        {
            return -32768;
        } else
        {
            return jniUploadData(mDC);
        }
    }

    static 
    {
        System.loadLibrary("yjvoice_transit");
        cLoadedLib = true;
        return;
        UnsatisfiedLinkError unsatisfiedlinkerror;
        unsatisfiedlinkerror;
        Log.e("YJVOICE:DCWrap", unsatisfiedlinkerror.toString());
        return;
        Exception exception;
        exception;
        throw exception;
    }
}
