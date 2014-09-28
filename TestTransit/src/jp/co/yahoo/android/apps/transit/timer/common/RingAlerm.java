// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.widget.Toast;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

public class RingAlerm
{
    public static interface AlermListener
    {

        public abstract void onError();

        public abstract void onStop();
    }


    Context context;
    AlertDialog dialog;
    AlermListener listener;
    AudioManager manager;
    MediaPlayer mediaPlayer;
    int nSetVolume;
    Vibrator vib;

    public RingAlerm(Context context1, AlermListener alermlistener)
    {
        manager = null;
        mediaPlayer = null;
        vib = null;
        dialog = null;
        context = null;
        nSetVolume = 0;
        listener = null;
        manager = (AudioManager)context1.getSystemService("audio");
        vib = (Vibrator)context1.getSystemService("vibrator");
        listener = alermlistener;
        context = context1;
    }

    public void setVolume(int i)
    {
        if (i > 0 && i <= 100)
        {
            int j = (i * manager.getStreamMaxVolume(3)) / 100;
            manager.setStreamVolume(3, j, 0);
        }
    }

    public void startAlerm(AlermData alermdata, boolean flag)
    {
        Uri uri;
        int i;
        nSetVolume = manager.getStreamVolume(2);
        uri = null;
        if (alermdata != null)
        {
            boolean flag1 = CountdownUtil.isEmpty(alermdata.getSoundUri());
            uri = null;
            if (!flag1)
            {
                uri = Uri.parse(alermdata.getSoundUri());
            }
        }
        i = 1000 * alermdata.getAlermLength();
        if (uri == null) goto _L2; else goto _L1
_L1:
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(context, uri);
        mediaPlayer.setAudioStreamType(3);
        int j = (manager.getStreamMaxVolume(3) * alermdata.getAlermVolume()) / 100;
        manager.setStreamVolume(3, j, 0);
        mediaPlayer.setLooping(true);
        mediaPlayer.prepare();
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
_L4:
        Runnable runnable = new Runnable() {

            final RingAlerm this$0;

            public void run()
            {
                stopAlerm();
                if (listener != null)
                {
                    listener.onStop();
                }
            }

            
            {
                this$0 = RingAlerm.this;
                super();
            }
        };
        (new Handler()).postDelayed(runnable, i);
_L2:
        if (flag && (uri != null || alermdata.isVibration()))
        {
            dialog = (new TransitDialogBuilder(context)).setTitle(context.getString(0x7f0d04be)).setPositiveButton(context.getString(0x7f0d04bd), new android.content.DialogInterface.OnClickListener() {

                final RingAlerm this$0;

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    stopAlerm();
                }

            
            {
                this$0 = RingAlerm.this;
                super();
            }
            }).setNegativeButton(context.getString(0x7f0d0072), new android.content.DialogInterface.OnClickListener() {

                final RingAlerm this$0;

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$0 = RingAlerm.this;
                super();
            }
            }).show();
        }
        if (alermdata.isVibration())
        {
            vib.vibrate(i);
        }
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
        stopAlerm();
        Toast.makeText(context, context.getString(0x7f0d0123), 1).show();
        if (listener != null)
        {
            listener.onError();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void stopAlerm()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.stop();
            manager.setStreamVolume(3, nSetVolume, 0);
        }
        if (vib != null)
        {
            vib.cancel();
        }
        if (listener != null)
        {
            listener.onStop();
        }
        try
        {
            if (dialog != null)
            {
                dialog.cancel();
            }
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return;
        }
    }
}
