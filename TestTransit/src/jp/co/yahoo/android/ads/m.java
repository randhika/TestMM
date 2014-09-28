// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.ads:
//            k, a, h, AdContainer, 
//            AdResponse, f

public class m
    implements k
{

    private final Context a;
    private a b;
    private JSONObject c;
    private AdContainer d;

    public m(Context context, a a1, JSONObject jsonobject)
    {
        a = context;
        b = a1;
        c = jsonobject;
    }

    private Runnable a(AdContainer adcontainer)
    {
        return new Runnable((Activity)a, adcontainer) {

            final Activity a;
            final AdContainer b;
            final m c;

            public void run()
            {
                AlphaAnimation alphaanimation = new AlphaAnimation(1.0F, 0.0F);
                alphaanimation.setDuration(500L);
                alphaanimation.setAnimationListener(new android.view.animation.Animation.AnimationListener(this) {

                    final _cls2 a;

                    public void onAnimationEnd(Animation animation)
                    {
                        h.a(new Runnable(this) {

                            final _cls1 a;

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

            
            {
                a = _pcls1;
                super();
            }
                        });
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
                });
                b.startAnimation(alphaanimation);
            }

            
            {
                c = m.this;
                a = activity;
                b = adcontainer;
                super();
            }
        };
    }

    static Runnable a(m m1, AdContainer adcontainer)
    {
        return m1.a(adcontainer);
    }

    static a a(m m1)
    {
        return m1.b;
    }

    static AdContainer b(m m1)
    {
        return m1.d;
    }

    public void a()
    {
        try
        {
            if (c.has("csc"))
            {
                b.j(c.getString("csc"));
            }
            if (c.has("waitsec"))
            {
                b.k(c.getString("waitsec"));
            }
            if (c.has("setIntervalmin"))
            {
                b.j(c.getString("intervalmin"));
            }
            if (c.has("image"))
            {
                JSONObject jsonobject = c.getJSONObject("image");
                if (jsonobject.has("banner"))
                {
                    JSONObject jsonobject1 = jsonobject.getJSONObject("banner");
                    if (jsonobject1.has("portrait"))
                    {
                        b.d(jsonobject1.getString("portrait"));
                    }
                }
            }
            return;
        }
        catch (JSONException jsonexception)
        {
            h.a(6, (new StringBuilder()).append("JSONException Failed to parse im ad response:  ").append(c).toString(), jsonexception);
        }
    }

    public AdResponse b()
    {
        if (b.r() == null)
        {
            return null;
        } else
        {
            d = new AdContainer(a) {

                final m b;

                protected void onAttachedToWindow()
                {
                    (new Thread(new Runnable(this) {

                        final _cls1 a;

                        public void run()
                        {
                            f.a(a.getContext(), m.a(a.b).l());
                        }

            
            {
                a = _pcls1;
                super();
            }
                    })).start();
                    h.a(m.a(b, m.b(b)), (long)(1000F * Float.parseFloat(m.a(b).m())));
                }

            
            {
                b = m.this;
                super(context);
            }
            };
            android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, -1);
            d.setLayoutParams(layoutparams);
            d.setBackgroundColor(0xff333333);
            d.setId(30);
            android.widget.RelativeLayout.LayoutParams layoutparams1 = new android.widget.RelativeLayout.LayoutParams(-1, -1);
            layoutparams1.addRule(15);
            ImageView imageview = new ImageView(a);
            imageview.setImageBitmap(b.r());
            imageview.setAdjustViewBounds(true);
            imageview.setId(31);
            imageview.setLayoutParams(layoutparams1);
            d.addView(imageview, layoutparams1);
            AdResponse adresponse = new AdResponse();
            adresponse.setAdlayout(d);
            adresponse.setRslog(h.c(b.l()));
            adresponse.setCode("200");
            adresponse.setMessage("AdView Success");
            return adresponse;
        }
    }
}
