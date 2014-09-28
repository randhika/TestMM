// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.ZoomControls;
import java.util.Timer;
import java.util.TimerTask;

public class AutoInvisibleZoomControls extends ZoomControls
{

    private long delayTime;
    private Animation mAnimFadein;
    private Animation mAnimFadeout;
    private Handler mHandler;
    private TimerTask mTask;
    private Timer mTimer;
    private ZoomControls mZoomControls;

    public AutoInvisibleZoomControls(Context context)
    {
        super(context);
        delayTime = 2500L;
        init();
    }

    public AutoInvisibleZoomControls(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        delayTime = 2500L;
        init();
    }

    private void init()
    {
        mHandler = null;
        mTimer = null;
        mTask = null;
        mAnimFadein = null;
        mAnimFadeout = null;
        mZoomControls = this;
    }

    public void autoInvisible()
    {
        stopAutoInvisible();
        mTask = new TimerTask() {

            final AutoInvisibleZoomControls this$0;

            public void run()
            {
                mHandler.post(new Runnable() {

                    final _cls1 this$1;

                    public void run()
                    {
                        mZoomControls.setVisibility(4);
                    }

            
            {
                this$1 = _cls1.this;
                super();
            }
                });
            }

            
            {
                this$0 = AutoInvisibleZoomControls.this;
                super();
            }
        };
        mTimer.schedule(mTask, delayTime);
    }

    public void setDelayTime(long l)
    {
        delayTime = l;
    }

    public void setFadeinAnimation(Animation animation)
    {
        mAnimFadein = animation;
    }

    public void setFadeoutAnimation(Animation animation)
    {
        mAnimFadeout = animation;
    }

    public void setHandlerAndTimer(Handler handler, Timer timer)
    {
        mHandler = handler;
        mTimer = timer;
    }

    public void setVisibility(int i)
    {
        if (i != 0) goto _L2; else goto _L1
_L1:
        if (mAnimFadein != null && (getVisibility() == 8 || getVisibility() == 4))
        {
            mZoomControls.startAnimation(mAnimFadein);
        }
_L4:
        super.setVisibility(i);
        if (i == 0)
        {
            autoInvisible();
        }
        return;
_L2:
        if (mAnimFadeout != null && getVisibility() == 0)
        {
            mZoomControls.startAnimation(mAnimFadeout);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void stopAutoInvisible()
    {
        if (mTask != null)
        {
            mTask.cancel();
            mTimer.purge();
            mTask = null;
        }
    }


}
