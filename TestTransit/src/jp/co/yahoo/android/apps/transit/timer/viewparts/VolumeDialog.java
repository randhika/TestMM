// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.viewparts;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;
import jp.co.yahoo.android.apps.transit.timer.common.RingAlerm;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

public class VolumeDialog
{
    public static interface DialogListener
    {

        public abstract void onCancel();

        public abstract void onOk(int i);
    }


    private RingAlerm alerm;
    private Button btnPlay;
    protected Context context;
    private boolean isPlaying;
    private DialogListener listener;
    private LinearLayout lyVolume;
    private int nVolume;
    private SeekBar seekVolume;
    private AlermData start;
    private TextView txtVolume;

    public VolumeDialog(Context context1, AlermData alermdata)
    {
        context = null;
        txtVolume = null;
        lyVolume = null;
        seekVolume = null;
        btnPlay = null;
        nVolume = 50;
        start = null;
        alerm = null;
        isPlaying = false;
        listener = null;
        context = context1;
        start = new AlermData();
        start.setAlermLength(5);
        start.setSoundUri(alermdata.getSoundUri());
        start.setVibration(alermdata.isVibration());
        alerm = new RingAlerm(context, new jp.co.yahoo.android.apps.transit.timer.common.RingAlerm.AlermListener() {

            final VolumeDialog this$0;

            public void onError()
            {
                isPlaying = false;
                btnPlay.setText(context.getString(0x7f0d04bc));
            }

            public void onStop()
            {
                isPlaying = false;
                btnPlay.setText(context.getString(0x7f0d04bc));
            }

            
            {
                this$0 = VolumeDialog.this;
                super();
            }
        });
        lyVolume = (LinearLayout)((LayoutInflater)context1.getSystemService("layout_inflater")).inflate(0x7f030058, null);
        seekVolume = (SeekBar)lyVolume.findViewById(0x7f0a01f7);
        btnPlay = (Button)lyVolume.findViewById(0x7f0a01f8);
        txtVolume = (TextView)lyVolume.findViewById(0x7f0a01f6);
        seekVolume.setMax(20);
        seekVolume.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {

            final VolumeDialog this$0;

            public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
            {
                nVolume = i * 5;
                txtVolume.setText((new StringBuilder()).append(Integer.toString(nVolume)).append(context.getString(0x7f0d02af)).toString());
                start.setAlermVolume(nVolume);
                if (isPlaying)
                {
                    alerm.setVolume(nVolume);
                }
            }

            public void onStartTrackingTouch(SeekBar seekbar)
            {
            }

            public void onStopTrackingTouch(SeekBar seekbar)
            {
            }

            
            {
                this$0 = VolumeDialog.this;
                super();
            }
        });
        btnPlay.setOnClickListener(new android.view.View.OnClickListener() {

            final VolumeDialog this$0;

            public void onClick(View view)
            {
                togglePlay();
            }

            
            {
                this$0 = VolumeDialog.this;
                super();
            }
        });
    }

    private void togglePlay()
    {
        if (isPlaying)
        {
            isPlaying = false;
            btnPlay.setText(context.getString(0x7f0d04bc));
            alerm.stopAlerm();
            return;
        } else
        {
            isPlaying = true;
            btnPlay.setText(context.getString(0x7f0d04bd));
            alerm.startAlerm(start, false);
            return;
        }
    }

    public void setDefVolume(int i)
    {
        if (i > 0 && i <= 100)
        {
            nVolume = i;
            txtVolume.setText((new StringBuilder()).append(Integer.toString(nVolume)).append(context.getString(0x7f0d02af)).toString());
            seekVolume.setProgress(nVolume / 5);
        }
    }

    public void showDilaog(DialogListener dialoglistener)
    {
        listener = dialoglistener;
        txtVolume.setText((new StringBuilder()).append(Integer.toString(nVolume)).append(context.getString(0x7f0d02af)).toString());
        seekVolume.setProgress(nVolume / 5);
        (new TransitDialogBuilder(context)).setTitle(context.getString(0x7f0d04bf)).setView(lyVolume).setPositiveButton(context.getString(0x7f0d0074), new android.content.DialogInterface.OnClickListener() {

            final VolumeDialog this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                if (listener != null)
                {
                    alerm.stopAlerm();
                    listener.onOk(nVolume);
                }
            }

            
            {
                this$0 = VolumeDialog.this;
                super();
            }
        }).setNegativeButton(context.getString(0x7f0d0071), new android.content.DialogInterface.OnClickListener() {

            final VolumeDialog this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                if (listener != null)
                {
                    alerm.stopAlerm();
                    listener.onCancel();
                }
            }

            
            {
                this$0 = VolumeDialog.this;
                super();
            }
        }).setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            final VolumeDialog this$0;

            public void onCancel(DialogInterface dialoginterface)
            {
                alerm.stopAlerm();
            }

            
            {
                this$0 = VolumeDialog.this;
                super();
            }
        }).show();
    }



/*
    static boolean access$002(VolumeDialog volumedialog, boolean flag)
    {
        volumedialog.isPlaying = flag;
        return flag;
    }

*/




/*
    static int access$202(VolumeDialog volumedialog, int i)
    {
        volumedialog.nVolume = i;
        return i;
    }

*/





}
