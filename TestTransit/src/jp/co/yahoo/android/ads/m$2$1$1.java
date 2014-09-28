// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.app.Activity;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

// Referenced classes of package jp.co.yahoo.android.ads:
//            h, m, AdContainer

class a
    implements Runnable
{

    final w a;

    public void run()
    {
        try
        {
            ViewGroup viewgroup = (ViewGroup)a.a.a.findViewById(30);
            viewgroup.removeAllViews();
            ((ViewGroup)viewgroup.getParent()).removeView(viewgroup);
            return;
        }
        catch (Exception exception)
        {
            h.a(6, "Unhandled exception Ad\u3000end into AdView.", exception);
        }
    }

    ner(ner ner)
    {
        a = ner;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/ads/m$2

/* anonymous class */
    class m._cls2
        implements Runnable
    {

        final Activity a;
        final AdContainer b;
        final m c;

        public void run()
        {
            AlphaAnimation alphaanimation = new AlphaAnimation(1.0F, 0.0F);
            alphaanimation.setDuration(500L);
            alphaanimation.setAnimationListener(new m._cls2._cls1(this));
            b.startAnimation(alphaanimation);
        }

            
            {
                c = m1;
                a = activity;
                b = adcontainer;
                super();
            }
    }


    // Unreferenced inner class jp/co/yahoo/android/ads/m$2$1

/* anonymous class */
    class m._cls2._cls1
        implements android.view.animation.Animation.AnimationListener
    {

        final m._cls2 a;

        public void onAnimationEnd(Animation animation)
        {
            h.a(new m._cls2._cls1._cls1(this));
        }

        public void onAnimationRepeat(Animation animation)
        {
        }

        public void onAnimationStart(Animation animation)
        {
        }

            
            {
                a = _pcls2;
                super();
            }
    }

}
