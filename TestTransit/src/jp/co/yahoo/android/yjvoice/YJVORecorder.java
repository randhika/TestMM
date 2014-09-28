// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;

import android.media.AudioRecord;
import android.util.Log;
import java.nio.ByteBuffer;

// Referenced classes of package jp.co.yahoo.android.yjvoice:
//            YJVORecorderListener

class YJVORecorder
    implements Runnable
{
    public static final class SAMPLERATE extends Enum
    {

        private static final SAMPLERATE $VALUES[];
        public static final SAMPLERATE K11;
        public static final SAMPLERATE K16;
        public static final SAMPLERATE K22;
        public static final SAMPLERATE K32;
        public static final SAMPLERATE K44;
        public static final SAMPLERATE K48;
        public static final SAMPLERATE K8;
        public static final SAMPLERATE K88;
        public static final SAMPLERATE K96;

        public static SAMPLERATE valueOf(String s)
        {
            return (SAMPLERATE)Enum.valueOf(jp/co/yahoo/android/yjvoice/YJVORecorder$SAMPLERATE, s);
        }

        public static SAMPLERATE[] values()
        {
            return (SAMPLERATE[])$VALUES.clone();
        }

        static 
        {
            K8 = new SAMPLERATE("K8", 0);
            K11 = new SAMPLERATE("K11", 1);
            K16 = new SAMPLERATE("K16", 2);
            K22 = new SAMPLERATE("K22", 3);
            K32 = new SAMPLERATE("K32", 4);
            K44 = new SAMPLERATE("K44", 5);
            K48 = new SAMPLERATE("K48", 6);
            K88 = new SAMPLERATE("K88", 7);
            K96 = new SAMPLERATE("K96", 8);
            SAMPLERATE asamplerate[] = new SAMPLERATE[9];
            asamplerate[0] = K8;
            asamplerate[1] = K11;
            asamplerate[2] = K16;
            asamplerate[3] = K22;
            asamplerate[4] = K32;
            asamplerate[5] = K44;
            asamplerate[6] = K48;
            asamplerate[7] = K88;
            asamplerate[8] = K96;
            $VALUES = asamplerate;
        }

        private SAMPLERATE(String s, int i)
        {
            super(s, i);
        }
    }


    public static final int DEF_TIME = 20000;
    public static final int MAX_TIME = 60000;
    public static final int MIN_TIME = 100;
    public static final int PITCH_TIME = 100;
    public static final int REC_ERROR = -32768;
    public static final int REC_OK = 0;
    private static final int SAMPLE_BIT = 2;
    public static final int SAMPLE_RATE_11K = 11025;
    public static final int SAMPLE_RATE_16K = 16000;
    public static final int SAMPLE_RATE_22K = 22050;
    public static final int SAMPLE_RATE_32K = 32000;
    public static final int SAMPLE_RATE_44K = 44100;
    public static final int SAMPLE_RATE_48K = 48000;
    public static final int SAMPLE_RATE_88K = 0x15888;
    public static final int SAMPLE_RATE_8K = 8000;
    public static final int SAMPLE_RATE_96K = 0x17700;
    private static final String TAG = "YJVOICE:YJVORecorder";
    private boolean isAudioRecording;
    private boolean isRecording;
    private int mChannelConfig;
    private YJVORecorderListener mListener;
    private int mMaxDataSize;
    private int mMaxTime;
    private int mPcmBuffSize;
    private int mSampleRateInHz;
    private int mSendDataSize;
    private Thread mThread;

    public YJVORecorder()
    {
        mListener = null;
        isRecording = false;
        isAudioRecording = false;
    }

    private static final int calcBuffSize(int i, int j)
    {
        int k = 0;
        if (j >= 100)
        {
            k = 0;
            if (j <= 60000)
            {
                k = (2 * (i * j)) / 1000;
            }
        }
        return k;
    }

    public final int destroy()
    {
        stop();
        if (isAudioRecording)
        {
            isAudioRecording = false;
        }
        return 0;
    }

    public final int init(YJVORecorderListener yjvorecorderlistener)
    {
        return init(yjvorecorderlistener, SAMPLERATE.K16, 20000);
    }

    public final int init(YJVORecorderListener yjvorecorderlistener, SAMPLERATE samplerate, int i)
    {
        if (yjvorecorderlistener != null) goto _L2; else goto _L1
_L1:
        Log.e("YJVOICE:YJVORecorder", "error: bad parameter: listener");
_L16:
        return -32768;
_L2:
        mListener = yjvorecorderlistener;
        static class _cls1
        {

            static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVORecorder$SAMPLERATE[];

            static 
            {
                $SwitchMap$jp$co$yahoo$android$yjvoice$YJVORecorder$SAMPLERATE = new int[SAMPLERATE.values().length];
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVORecorder$SAMPLERATE[SAMPLERATE.K8.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVORecorder$SAMPLERATE[SAMPLERATE.K11.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVORecorder$SAMPLERATE[SAMPLERATE.K16.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVORecorder$SAMPLERATE[SAMPLERATE.K22.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVORecorder$SAMPLERATE[SAMPLERATE.K32.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVORecorder$SAMPLERATE[SAMPLERATE.K44.ordinal()] = 6;
                }
                catch (NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVORecorder$SAMPLERATE[SAMPLERATE.K48.ordinal()] = 7;
                }
                catch (NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVORecorder$SAMPLERATE[SAMPLERATE.K88.ordinal()] = 8;
                }
                catch (NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVORecorder$SAMPLERATE[SAMPLERATE.K96.ordinal()] = 9;
                }
                catch (NoSuchFieldError nosuchfielderror8)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.jp.co.yahoo.android.yjvoice.YJVORecorder.SAMPLERATE[samplerate.ordinal()];
        JVM INSTR tableswitch 1 9: default 80
    //                   1 92
    //                   2 123
    //                   3 133
    //                   4 143
    //                   5 153
    //                   6 163
    //                   7 172
    //                   8 181
    //                   9 190;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L3:
        Log.e("YJVOICE:YJVORecorder", "error: bad parameter: sampleRate");
        return -32768;
_L4:
        mSampleRateInHz = 8000;
_L14:
        if (i < 100 || i > 60000)
        {
            Log.e("YJVOICE:YJVORecorder", "error: bad parameter: maxTime");
            return -32768;
        }
        break; /* Loop/switch isn't completed */
_L5:
        mSampleRateInHz = 11025;
        continue; /* Loop/switch isn't completed */
_L6:
        mSampleRateInHz = 16000;
        continue; /* Loop/switch isn't completed */
_L7:
        mSampleRateInHz = 22050;
        continue; /* Loop/switch isn't completed */
_L8:
        mSampleRateInHz = 32000;
        continue; /* Loop/switch isn't completed */
_L9:
        mSampleRateInHz = 44100;
        continue; /* Loop/switch isn't completed */
_L10:
        mSampleRateInHz = 48000;
        continue; /* Loop/switch isn't completed */
_L11:
        mSampleRateInHz = 0x15888;
        continue; /* Loop/switch isn't completed */
_L12:
        mSampleRateInHz = 0x17700;
        if (true) goto _L14; else goto _L13
_L13:
        mMaxTime = i;
        mMaxDataSize = calcBuffSize(mSampleRateInHz, mMaxTime);
        if (mMaxDataSize == 0)
        {
            Log.e("YJVOICE:YJVORecorder", "error: data size is large");
            return -32768;
        }
        mSendDataSize = calcBuffSize(mSampleRateInHz, 100);
        mChannelConfig = 16;
        int j = AudioRecord.getMinBufferSize(mSampleRateInHz, mChannelConfig, 2);
        switch (j)
        {
        default:
            mPcmBuffSize = j;
            break; /* Loop/switch isn't completed */

        case -2: 
        case -1: 
            break;
        }
        if (true) goto _L16; else goto _L15
_L15:
        if (mPcmBuffSize < mSendDataSize)
        {
            mPcmBuffSize = mSendDataSize;
        }
        mPcmBuffSize = 2 * mPcmBuffSize;
        return 0;
    }

    public final boolean isRecording()
    {
        return isRecording;
    }

    public final void run()
    {
        ByteBuffer bytebuffer;
        AudioRecord audiorecord;
        int i;
        String s;
        try
        {
            bytebuffer = ByteBuffer.allocateDirect(mPcmBuffSize);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            Log.e("YJVOICE:YJVORecorder", illegalargumentexception.toString());
            isRecording = false;
            mThread = null;
            mListener.recordFinished(-2);
            return;
        }
        audiorecord = null;
        i = 5000 / 200;
        s = "";
        do
        {
            byte byte0;
            if (true)
            {
                try
                {
                    audiorecord = new AudioRecord(1, mSampleRateInHz, mChannelConfig, 2, mPcmBuffSize);
                }
                catch (IllegalArgumentException illegalargumentexception1)
                {
                    s = illegalargumentexception1.toString();
                    audiorecord = null;
                }
                if (audiorecord == null)
                {
                    long l2 = 200;
                    try
                    {
                        Thread.sleep(l2);
                    }
                    catch (InterruptedException interruptedexception2)
                    {
                        Log.e("YJVOICE:YJVORecorder", interruptedexception2.toString());
                    }
                    if (--i < 0)
                    {
                        Log.e("YJVOICE:YJVORecorder", s);
                        isRecording = false;
                        mThread = null;
                        mListener.recordFinished(-2);
                        return;
                    }
                    continue;
                }
                int j;
                int i1;
                int j1;
                boolean flag;
                try
                {
                    audiorecord.startRecording();
                }
                catch (IllegalStateException illegalstateexception2)
                {
                    s = illegalstateexception2.toString();
                    audiorecord.release();
                    long l1 = 200;
                    try
                    {
                        Thread.sleep(l1);
                    }
                    catch (InterruptedException interruptedexception1)
                    {
                        Log.e("YJVOICE:YJVORecorder", interruptedexception1.toString());
                    }
                    i--;
                    audiorecord = null;
                    if (i < 0)
                    {
                        Log.e("YJVOICE:YJVORecorder", s);
                        isRecording = false;
                        mThread = null;
                        mListener.recordFinished(-2);
                        return;
                    }
                    continue;
                }
            }
            isAudioRecording = true;
            mListener.recordStart();
            byte0 = 0;
            j = 0;
            while (isRecording) 
            {
                int k = audiorecord.read(bytebuffer, mSendDataSize);
                if (k > 0)
                {
                    j += k;
                    i1 = mMaxDataSize;
                    j1 = j;
                    flag = false;
                    if (j1 >= i1)
                    {
                        flag = true;
                    }
                    mListener.recordState(bytebuffer, k, flag);
                    if (flag)
                    {
                        byte0 = 1;
                        stop();
                    }
                } else
                if (k == -3)
                {
                    byte0 = -1;
                    stop();
                    Log.e("YJVOICE:YJVORecorder", "AudioRecord.read error: ERROR_INVALID_OPERATION");
                } else
                if (k == -2)
                {
                    byte0 = -1;
                    stop();
                    Log.e("YJVOICE:YJVORecorder", "AudioRecord.read error: ERROR_BAD_VALUE");
                } else
                if (k == 0)
                {
                    long l = 200;
                    try
                    {
                        Thread.sleep(l);
                    }
                    catch (InterruptedException interruptedexception)
                    {
                        Log.e("YJVOICE:YJVORecorder", interruptedexception.toString());
                    }
                    if (--i < 0)
                    {
                        byte0 = -1;
                        stop();
                        Log.e("YJVOICE:YJVORecorder", (new StringBuilder()).append("AudioRecord.read length:").append(k).toString());
                    } else
                    {
                        try
                        {
                            audiorecord.stop();
                            audiorecord.startRecording();
                        }
                        catch (IllegalStateException illegalstateexception1)
                        {
                            Log.e("YJVOICE:YJVORecorder", illegalstateexception1.toString());
                            isRecording = false;
                            audiorecord.release();
                            mThread = null;
                            mListener.recordFinished(-2);
                            return;
                        }
                    }
                }
            }
            try
            {
                audiorecord.stop();
            }
            catch (IllegalStateException illegalstateexception)
            {
                Log.e("YJVOICE:YJVORecorder", illegalstateexception.toString());
                byte0 = -2;
            }
            audiorecord.release();
            mThread = null;
            isAudioRecording = false;
            mListener.recordFinished(byte0);
            return;
        } while (true);
    }

    public final int start()
    {
        if (mThread == null)
        {
            isRecording = true;
            isAudioRecording = false;
            mThread = new Thread(this);
            mThread.start();
            return 0;
        } else
        {
            Log.e("YJVOICE:YJVORecorder", "cannot call Start during running!");
            return -32768;
        }
    }

    public final int stop()
    {
        isRecording = false;
        return 0;
    }
}
