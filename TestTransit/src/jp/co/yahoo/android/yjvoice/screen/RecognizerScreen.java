// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice.screen;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

// Referenced classes of package jp.co.yahoo.android.yjvoice.screen:
//            LevelMeterView, RecognizerTopView

class RecognizerScreen
{
    public static final class STATE extends Enum
    {

        private static final STATE $VALUES[];
        public static final STATE ERROR;
        public static final STATE HIDE;
        public static final STATE INIT;
        public static final STATE PHRASE;
        public static final STATE RECOG;

        public static STATE valueOf(String s)
        {
            return (STATE)Enum.valueOf(jp/co/yahoo/android/yjvoice/screen/RecognizerScreen$STATE, s);
        }

        public static STATE[] values()
        {
            return (STATE[])$VALUES.clone();
        }

        static 
        {
            HIDE = new STATE("HIDE", 0);
            INIT = new STATE("INIT", 1);
            PHRASE = new STATE("PHRASE", 2);
            RECOG = new STATE("RECOG", 3);
            ERROR = new STATE("ERROR", 4);
            STATE astate[] = new STATE[5];
            astate[0] = HIDE;
            astate[1] = INIT;
            astate[2] = PHRASE;
            astate[3] = RECOG;
            astate[4] = ERROR;
            $VALUES = astate;
        }

        private STATE(String s, int i)
        {
            super(s, i);
        }
    }


    private static final String TAG = "YJVOICESCREEN";
    private static RecognizerScreen cCurrentScreen = null;
    private ImageButton mButton;
    private final Runnable mChangeAction = new Runnable() {

        final RecognizerScreen this$0;

        public void run()
        {
            change();
        }

            
            {
                this$0 = RecognizerScreen.this;
                super();
            }
    };
    Handler mHandler;
    private final Runnable mHideAction = new Runnable() {

        final RecognizerScreen this$0;

        public void run()
        {
            hide();
        }

            
            {
                this$0 = RecognizerScreen.this;
                super();
            }
    };
    private android.view.WindowManager.LayoutParams mLayoutParams;
    private LevelMeterView mLevelMeter;
    private final Object mLock = new Object();
    private ImageView mMikeImage;
    private ProgressBar mProgressBar;
    private TextView mPrompt;
    String mPromptError;
    String mPromptRecog;
    String mPromptString;
    private final Runnable mShowAction = new Runnable() {

        final RecognizerScreen this$0;

        public void run()
        {
            show();
        }

            
            {
                this$0 = RecognizerScreen.this;
                super();
            }
    };
    private boolean mShowing;
    private STATE mState;
    private final Thread mUiThread = Looper.getMainLooper().getThread();
    private RecognizerTopView mViewTop;
    private WindowManager mWindowManager;

    RecognizerScreen()
    {
        mHandler = new Handler(Looper.getMainLooper());
        mWindowManager = null;
        mState = STATE.HIDE;
        mShowing = false;
    }

    private void change()
    {
        static class _cls5
        {

            static final int $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[];

            static 
            {
                $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE = new int[STATE.values().length];
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[STATE.HIDE.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[STATE.INIT.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[STATE.PHRASE.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[STATE.RECOG.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$screen$RecognizerScreen$STATE[STATE.ERROR.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror4)
                {
                    return;
                }
            }
        }

        _cls5..SwitchMap.jp.co.yahoo.android.yjvoice.screen.RecognizerScreen.STATE[mState.ordinal()];
        JVM INSTR tableswitch 1 5: default 44
    //                   1 45
    //                   2 50
    //                   3 120
    //                   4 179
    //                   5 254;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return;
_L2:
        hide();
        return;
_L3:
        mPrompt.setText(mPromptString);
        if (mMikeImage.getVisibility() == 4)
        {
            mMikeImage.setVisibility(0);
        }
        mButton.setImageResource(R.drawable.btncancel);
        if (mProgressBar.getVisibility() == 0)
        {
            mProgressBar.setVisibility(4);
        }
        show();
        mLevelMeter.doAnimation();
        return;
_L4:
        mPrompt.setText(mPromptString);
        if (mMikeImage.getVisibility() == 4)
        {
            mMikeImage.setVisibility(0);
        }
        mButton.setImageResource(R.drawable.btnend);
        if (mProgressBar.getVisibility() == 0)
        {
            mProgressBar.setVisibility(4);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        mPrompt.setText(mPromptRecog);
        if (mMikeImage.getVisibility() == 0)
        {
            mMikeImage.setVisibility(4);
        }
        mLevelMeter.setValue(-1);
        mLevelMeter.invalidate();
        mButton.setImageResource(R.drawable.btncancel);
        if (mProgressBar.getVisibility() == 4)
        {
            mProgressBar.setVisibility(0);
            return;
        }
        if (true) goto _L1; else goto _L6
_L6:
        mPrompt.setText(mPromptError);
        if (mMikeImage.getVisibility() == 0)
        {
            mMikeImage.setVisibility(4);
        }
        mLevelMeter.setValue(-1);
        mLevelMeter.invalidate();
        mButton.setImageResource(R.drawable.btncancel);
        if (mProgressBar.getVisibility() == 0)
        {
            mProgressBar.setVisibility(4);
            return;
        }
        if (true) goto _L1; else goto _L7
_L7:
    }

    private void hide()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if (mShowing)
        {
            mShowing = false;
            mWindowManager.removeView(mViewTop);
        }
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        Log.e("YJVOICESCREEN", illegalargumentexception.toString());
          goto _L1
        Exception exception;
        exception;
        throw exception;
        Exception exception1;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    private void runIfUiThread(Runnable runnable)
    {
        if (!Thread.currentThread().equals(mUiThread))
        {
            mHandler.post(runnable);
            return;
        } else
        {
            runnable.run();
            return;
        }
    }

    private void show()
    {
        if (cCurrentScreen != this && cCurrentScreen != null && cCurrentScreen.isShowing())
        {
            cCurrentScreen.hideScreen();
        }
        synchronized (mLock)
        {
            if (!mShowing)
            {
                mShowing = true;
                mWindowManager.addView(mViewTop, mLayoutParams);
                mViewTop.invalidate();
                cCurrentScreen = this;
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void changeScreen(STATE state)
    {
        mState = state;
        runIfUiThread(mChangeAction);
    }

    public View findView(int i)
    {
        RecognizerTopView recognizertopview = mViewTop;
        View view = null;
        if (recognizertopview != null)
        {
            view = mViewTop.findViewById(i);
        }
        return view;
    }

    protected void findViews()
    {
        mPrompt = (TextView)mViewTop.findViewById(R.id.yjvov_prompt);
        mMikeImage = (ImageView)mViewTop.findViewById(R.id.yjvov_mikeimage);
        mLevelMeter = (LevelMeterView)mViewTop.findViewById(R.id.yjvov_levelmeter);
        mButton = (ImageButton)mViewTop.findViewById(R.id.yjvov_button);
        mProgressBar = (ProgressBar)mViewTop.findViewById(R.id.yjvov_progressbar);
    }

    public STATE getState()
    {
        return mState;
    }

    public RecognizerTopView getTopView()
    {
        return mViewTop;
    }

    public void hideScreen()
    {
        runIfUiThread(mHideAction);
    }

    public int init(Context context)
    {
        Context context1;
        context1 = context.getApplicationContext();
        mWindowManager = (WindowManager)context.getSystemService("window");
        mLayoutParams = new android.view.WindowManager.LayoutParams();
        mLayoutParams.format = -3;
        View view = ((LayoutInflater)context1.getSystemService("layout_inflater")).inflate(R.layout.recognizer_screen, null).findViewById(R.id.yjvov_screen);
        if (!(view instanceof RecognizerTopView)) goto _L2; else goto _L1
_L1:
        mViewTop = (RecognizerTopView)view;
_L3:
        ClassCastException classcastexception;
        Exception exception;
        InflateException inflateexception;
        if (mViewTop == null)
        {
            Log.e("YJVOICESCREEN", "RecognizerScreen: error: failed in the initialization.");
            return -32768;
        } else
        {
            Resources resources = context1.getResources();
            mPromptString = resources.getString(R.string.yjvov_prompt);
            mPromptRecog = resources.getString(R.string.yjvov_promptrecog);
            mPromptError = resources.getString(R.string.yjvov_prompterror);
            findViews();
            return 0;
        }
_L2:
        mViewTop = null;
          goto _L3
        inflateexception;
        mViewTop = null;
        Log.e("YJVOICESCREEN", inflateexception.toString());
          goto _L3
        exception;
        throw exception;
        classcastexception;
        mViewTop = null;
        Log.e("YJVOICESCREEN", classcastexception.toString());
          goto _L3
    }

    public boolean isShowing()
    {
        return mShowing;
    }

    public void onAttachedToWindow()
    {
    }

    public void onDetachedFromWindow()
    {
        synchronized (mLock)
        {
            if (mShowing)
            {
                mShowing = false;
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int setLevelMeterValue(int i)
    {
        if (mLevelMeter != null)
        {
            mLevelMeter.setValue(i);
            mHandler.post(new Runnable() {

                final RecognizerScreen this$0;

                public void run()
                {
                    mLevelMeter.invalidate();
                }

            
            {
                this$0 = RecognizerScreen.this;
                super();
            }
            });
        }
        return 0;
    }

    public int setPrompt(String s)
    {
        if (s != null)
        {
            mPromptString = s;
            return 0;
        } else
        {
            return -32768;
        }
    }





}
