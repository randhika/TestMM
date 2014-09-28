// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice.screen;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import java.nio.ByteBuffer;
import jp.co.yahoo.android.yjvoice.YJVORecognizeListener;
import jp.co.yahoo.android.yjvoice.YJVORecognizer;
import jp.co.yahoo.android.yjvoice.YJVO_SAMPLERATE;

// Referenced classes of package jp.co.yahoo.android.yjvoice.screen:
//            RecognizerTopViewListener, RecognizerScreen, RecognizerTopView

public class YJVOVRecognizer extends YJVORecognizer
    implements android.view.View.OnKeyListener, RecognizerTopViewListener
{

    private static final String TAG = "YJVOICESCREEN";
    RecognizerScreen.STATE mButtonState;
    int mErrScreenID;
    Handler mHandler;
    String mPromptRecog;
    RecognizerScreen mScreen;
    int mScreenID;

    public YJVOVRecognizer()
    {
        mScreenID = 0;
        mErrScreenID = 0;
        mButtonState = RecognizerScreen.STATE.HIDE;
    }

    private void onTouchButton(View view, MotionEvent motionevent)
    {
        if (view.getId() != R.id.yjvov_button) goto _L2; else goto _L1
_L1:
        ImageButton imagebutton;
        RecognizerScreen.STATE state;
        imagebutton = (ImageButton)view;
        state = mScreen.getState();
        static class _cls3
        {

            static final int $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[];

            static 
            {
                $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE = new int[RecognizerScreen.STATE.values().length];
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[RecognizerScreen.STATE.HIDE.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[RecognizerScreen.STATE.INIT.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[RecognizerScreen.STATE.PHRASE.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[RecognizerScreen.STATE.RECOG.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[RecognizerScreen.STATE.ERROR.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror4)
                {
                    return;
                }
            }
        }

        _cls3..SwitchMap.jp.co.yahoo.android.yjvoice.screen.RecognizerScreen.STATE[state.ordinal()];
        JVM INSTR tableswitch 1 5: default 68
    //                   1 68
    //                   2 69
    //                   3 143
    //                   4 224
    //                   5 298;
           goto _L2 _L2 _L3 _L4 _L5 _L6
_L2:
        return;
_L3:
        if (motionevent.getAction() != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        mButtonState = state;
        imagebutton.setImageResource(R.drawable.btncancel_on);
        int k = recognizeCancelInternal();
        if (k != 0)
        {
            Log.e("YJVOICESCREEN", (new StringBuilder()).append("recognizeCancelInternal(): error:").append(k).toString());
            return;
        }
        continue; /* Loop/switch isn't completed */
        if (motionevent.getAction() != 1) goto _L2; else goto _L7
_L7:
        imagebutton.setImageResource(R.drawable.btncancel);
        return;
_L4:
        if (motionevent.getAction() != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        mButtonState = state;
        if (isRecognizing())
        {
            imagebutton.setImageResource(R.drawable.btnend_on);
            int j = recognizeStopInternal();
            if (j != 0)
            {
                Log.e("YJVOICESCREEN", (new StringBuilder()).append("recognizeStopInternal(): error:").append(j).toString());
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
        if (motionevent.getAction() != 1) goto _L2; else goto _L8
_L8:
        imagebutton.setImageResource(R.drawable.btnend);
        return;
_L5:
        if (motionevent.getAction() != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        mButtonState = state;
        imagebutton.setImageResource(R.drawable.btncancel_on);
        int i = recognizeCancel();
        if (i != 0)
        {
            Log.e("YJVOICESCREEN", (new StringBuilder()).append("recognizeCancel(): error:").append(i).toString());
            return;
        }
        continue; /* Loop/switch isn't completed */
        if (motionevent.getAction() != 1) goto _L2; else goto _L9
_L9:
        imagebutton.setImageResource(R.drawable.btncancel);
        return;
_L6:
        if (motionevent.getAction() == 0)
        {
            mButtonState = state;
            imagebutton.setImageResource(R.drawable.btncancel_on);
            mErrScreenID = 0;
            mScreen.changeScreen(RecognizerScreen.STATE.HIDE);
            return;
        }
        if (motionevent.getAction() == 1)
        {
            imagebutton.setImageResource(R.drawable.btncancel);
            return;
        }
        if (true) goto _L2; else goto _L10
_L10:
    }

    private int recognizeStopInternal()
    {
        int i = super.recognizeStop();
        if (i != 0)
        {
            mScreen.changeScreen(RecognizerScreen.STATE.HIDE);
        }
        return i;
    }

    private void showErrorScreen()
    {
        mScreen.changeScreen(RecognizerScreen.STATE.ERROR);
        mErrScreenID = mScreenID;
        (new Thread(new Runnable() {

            final YJVOVRecognizer this$0;

            public void run()
            {
                int i = mScreenID;
                try
                {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException interruptedexception)
                {
                    Log.e("YJVOICESCREEN", interruptedexception.toString());
                }
                if (i == mErrScreenID && i == mScreenID)
                {
                    mScreen.changeScreen(RecognizerScreen.STATE.HIDE);
                }
            }

            
            {
                this$0 = YJVOVRecognizer.this;
                super();
            }
        })).start();
    }

    public void destroy()
    {
        if (mScreen != null)
        {
            mScreen.changeScreen(RecognizerScreen.STATE.HIDE);
        }
        super.destroy();
    }

    protected int forceTermination()
    {
        mScreen.changeScreen(RecognizerScreen.STATE.HIDE);
        return super.forceTermination();
    }

    public int init(YJVO_SAMPLERATE yjvo_samplerate, int i, YJVORecognizeListener yjvorecognizelistener, Context context)
    {
        int j = super.init(yjvo_samplerate, i, yjvorecognizelistener, context);
        if (j != 0)
        {
            return j;
        }
        mHandler = new Handler(Looper.getMainLooper());
        mScreen = new RecognizerScreen();
        int k = mScreen.init(context);
        if (k != 0)
        {
            return k;
        }
        mScreen.getTopView().setListener(this);
        View view = mScreen.findView(R.id.yjvov_button);
        if (view == null)
        {
            return -32768;
        } else
        {
            view.setOnTouchListener(new android.view.View.OnTouchListener() {

                final YJVOVRecognizer this$0;

                public boolean onTouch(View view1, MotionEvent motionevent)
                {
                    onTouchButton(view1, motionevent);
                    return true;
                }

            
            {
                this$0 = YJVOVRecognizer.this;
                super();
            }
            });
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.setOnKeyListener(this);
            return 0;
        }
    }

    public void onAttachedToWindow()
    {
        mScreen.onAttachedToWindow();
    }

    public void onDetachedFromWindow()
    {
        boolean flag;
        if (mScreen.isShowing() && isRecognizing())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mScreen.onDetachedFromWindow();
        if (flag)
        {
            recorderStop();
            forceTermination();
        }
    }

    public boolean onKey(View view, int i, KeyEvent keyevent)
    {
        if (view.getId() == R.id.yjvov_button && i == 4)
        {
            if (keyevent.getAction() == 0)
            {
                forceTermination();
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void onVolumeChanged(short word0)
    {
        mScreen.setLevelMeterValue(word0);
    }

    protected int procStateMonitorFinish()
    {
        super.procStateMonitorFinish();
        mScreen.changeScreen(RecognizerScreen.STATE.HIDE);
        return 0;
    }

    protected int procStatePhraseFinish()
    {
        super.procStatePhraseFinish();
        if (isPhraseMode())
        {
            mScreen.changeScreen(RecognizerScreen.STATE.RECOG);
        }
        return 0;
    }

    protected int procStateRecognizeCancel()
    {
        super.procStateRecognizeCancel();
        mScreen.changeScreen(RecognizerScreen.STATE.HIDE);
        return 0;
    }

    protected int procStateRecognizeError()
    {
        super.procStateRecognizeError();
        showErrorScreen();
        return 0;
    }

    protected int procStateRecognizeStarterror()
    {
        super.procStateRecognizeError();
        showErrorScreen();
        return 0;
    }

    protected int procStateRecognizeStop()
    {
        super.procStateRecognizeStop();
        mScreen.changeScreen(RecognizerScreen.STATE.HIDE);
        return 0;
    }

    protected int procStateVoiceConfirm()
    {
        super.procStateVoiceConfirm();
        if (!isPhraseMode()) goto _L2; else goto _L1
_L1:
        mScreen.changeScreen(RecognizerScreen.STATE.PHRASE);
_L4:
        return 0;
_L2:
        if (mScreen.getState() != RecognizerScreen.STATE.PHRASE)
        {
            mScreen.changeScreen(RecognizerScreen.STATE.PHRASE);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public int recognizeCancel()
    {
        int i = super.recognizeCancel();
        mScreen.changeScreen(RecognizerScreen.STATE.HIDE);
        return i;
    }

    protected int recognizeCancelInternal()
    {
        int i = super.recognizeCancelInternal();
        if (i != 0)
        {
            mScreen.changeScreen(RecognizerScreen.STATE.HIDE);
        }
        return i;
    }

    public int recognizeStart()
    {
        mButtonState = RecognizerScreen.STATE.HIDE;
        int i = super.recognizeStart();
        if (i == 0)
        {
            mScreenID = 1 + mScreenID;
            mScreen.changeScreen(RecognizerScreen.STATE.INIT);
        }
        return i;
    }

    public int recognizeStop()
    {
        mScreen.changeScreen(RecognizerScreen.STATE.HIDE);
        return super.recognizeStop();
    }

    public void recordFinished(int i)
    {
        super.recordFinished(i);
        switch (i)
        {
        default:
            mScreen.changeScreen(RecognizerScreen.STATE.HIDE);
            return;

        case 1: // '\001'
            mScreen.changeScreen(RecognizerScreen.STATE.RECOG);
            return;

        case 0: // '\0'
            mScreen.setLevelMeterValue(mVolume);
            return;
        }
    }

    public void recordStart()
    {
        super.recordStart();
    }

    public void recordState(ByteBuffer bytebuffer, int i, boolean flag)
    {
        super.recordState(bytebuffer, i, flag);
    }

    public int setPrompt(String s)
    {
        return mScreen.setPrompt(s);
    }

}
