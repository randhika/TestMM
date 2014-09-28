// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import android.content.Context;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.Collections;
import jp.co.yahoo.android.apps.transit.timer.api.data.SkinMetaData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.apps.transit.timer.viewparts.CustomImageView;

public class SkinDrawableManager
{
    private class MyOutAnimationListener
        implements android.view.animation.Animation.AnimationListener
    {

        final SkinDrawableManager this$0;

        public void onAnimationEnd(Animation animation)
        {
            setDrawable(nStartCount);
        }

        public void onAnimationRepeat(Animation animation)
        {
        }

        public void onAnimationStart(Animation animation)
        {
        }

        private MyOutAnimationListener()
        {
            this$0 = SkinDrawableManager.this;
            super();
        }
    }


    private Animation animIn;
    private Animation animOut;
    private android.view.View.OnClickListener clicklistener;
    private Context context;
    private boolean isAlbum;
    private boolean isStart;
    private ArrayList list;
    private ArrayList listAlbumPath;
    private SkinMetaData meta;
    private int nOverTime;
    private int nStartCount;
    private FrameLayout parent;
    private CustomImageView skin;

    public SkinDrawableManager(Context context1, SkinMetaData skinmetadata, FrameLayout framelayout)
    {
        nStartCount = 0;
        nOverTime = 0;
        isStart = false;
        isAlbum = false;
        animIn = null;
        animOut = null;
        context = context1;
        meta = skinmetadata;
        parent = framelayout;
    }

    private void generateRandamInt()
    {
        list = new ArrayList();
        for (int i = 1; i <= meta.nCount; i++)
        {
            list.add(Integer.valueOf(i));
        }

        Collections.shuffle(list);
    }

    private void resetCount()
    {
        generateRandamInt();
        nStartCount = 0;
        nOverTime = 0;
    }

    private void setDrawable(int i)
    {
        if (skin != null)
        {
            skin.cleanup();
            parent.removeViewAt(0);
        }
        skin = null;
        String s;
        CustomImageView customimageview;
        boolean flag;
        if (isAlbum)
        {
            s = (String)listAlbumPath.get(-1 + ((Integer)list.get(i)).intValue());
        } else
        {
            s = (new StringBuilder()).append(meta.sPath).append("/").append(context.getString(0x7f0d04ea)).append("/").append(CountdownUtil.getZeroNumH(((Integer)list.get(i)).intValue())).append(".jpg").toString();
        }
        skin = new CustomImageView(context);
        customimageview = skin;
        if (!meta.isScaleble)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        customimageview.setFit(flag);
        skin.setImage(s);
        skin.setMaxScale(1.0F);
        skin.setListener(clicklistener);
        skin.setLock(true);
        parent.addView(skin, 0);
    }

    public void clearImage()
    {
        if (skin != null)
        {
            skin.cleanup();
            parent.removeViewAt(0);
        }
        skin = null;
    }

    public void setLock(boolean flag)
    {
        if (skin != null)
        {
            skin.setLock(flag);
        }
    }

    public void setNextDrawable(int i)
    {
        if (isStart)
        {
            nOverTime = 1 + nOverTime;
            if (nOverTime % meta.nInterval == 0)
            {
                int j = nOverTime / meta.nInterval;
                if (j >= meta.nCount)
                {
                    resetCount();
                } else
                {
                    nStartCount = j;
                }
                if (skin == null);
                setDrawable(nStartCount);
                return;
            }
        }
    }

    public void setOnClickListener(android.view.View.OnClickListener onclicklistener)
    {
        clicklistener = onclicklistener;
    }

    public void setSkinAlbumData(ArrayList arraylist)
    {
        listAlbumPath = arraylist;
        meta.nCount = arraylist.size();
        isAlbum = true;
    }

    public void setStart(boolean flag)
    {
        isStart = flag;
    }

    public void startTimer()
    {
        isStart = true;
        nOverTime = 0;
        generateRandamInt();
        setDrawable(nStartCount);
    }


}
