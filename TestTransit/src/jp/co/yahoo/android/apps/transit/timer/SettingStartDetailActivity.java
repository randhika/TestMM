// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.ArrayList;
import java.util.Calendar;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.common.Alerm;
import jp.co.yahoo.android.apps.transit.timer.db.SettingDb;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.apps.transit.timer.viewparts.StationListAdapter;
import jp.co.yahoo.android.apps.transit.timer.viewparts.VolumeDialog;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownBaseActivity

public class SettingStartDetailActivity extends CountdownBaseActivity
{

    private Alerm alerm;
    private boolean bUpdate;
    private Button btnSave;
    private AlertDialog dialog;
    private boolean mCheckedItems[] = {
        1, 1, 1, 1, 1, 1, 1
    };
    private AlermData start;
    private TextView txtCount;
    private TextView txtLength;
    private TextView txtLine;
    private TextView txtRepeate;
    private TextView txtSound;
    private TextView txtStart;
    private TextView txtVibration;
    private TextView txtVolume;

    public SettingStartDetailActivity()
    {
        alerm = null;
        start = null;
        dialog = null;
        bUpdate = false;
        txtLine = null;
        txtCount = null;
        txtStart = null;
        txtRepeate = null;
        txtSound = null;
        txtLength = null;
        txtVolume = null;
        txtVibration = null;
        btnSave = null;
    }

    private void saveSetting()
    {
        start.setType(AlermData.TYPE_START);
        start.setSetting(true);
        alerm.setAlerm(start, bUpdate);
    }

    public void cancel(View view)
    {
        setResult(0);
        finish();
    }

    public void delete(View view)
    {
        alerm.delAlarm(start);
        setResult(-1);
        finish();
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        Uri uri;
label0:
        {
            if (i == 1 && j == -1)
            {
                uri = (Uri)intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
                if (uri != null)
                {
                    break label0;
                }
                start.setSound(null);
                start.setSoundUri(null);
                txtSound.setText(getString(0x7f0d04d2));
            }
            return;
        }
        String s = RingtoneManager.getRingtone(getApplicationContext(), uri).getTitle(this);
        txtSound.setText(s);
        start.setSound(s);
        start.setSoundUri(uri.toString());
    }

    public void onCheck()
    {
        if (start.getLine() == null || start.getLine().equals(""))
        {
            btnSave.setEnabled(false);
            return;
        }
        if (start.getRepeat() == null || start.getRepeat().equals(""))
        {
            btnSave.setEnabled(false);
            return;
        }
        if (start.getCountdownTime() < 0)
        {
            btnSave.setEnabled(false);
            return;
        }
        if (start.getStartTime() < 0)
        {
            btnSave.setEnabled(false);
            return;
        } else
        {
            btnSave.setEnabled(true);
            return;
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0300a3);
        setTitle(getString(0x7f0d04d8));
        int i = getIntent().getIntExtra(getString(0x7f0d023d), 0);
        alerm = new Alerm(this);
        if (i != 0)
        {
            alerm = new Alerm(this);
            start = alerm.getStartAlerm(i);
        }
        txtLine = (TextView)findViewById(0x7f0a026f);
        txtStart = (TextView)findViewById(0x7f0a02fa);
        txtCount = (TextView)findViewById(0x7f0a02fc);
        txtRepeate = (TextView)findViewById(0x7f0a02fe);
        txtSound = (TextView)findViewById(0x7f0a0300);
        txtLength = (TextView)findViewById(0x7f0a0302);
        txtVolume = (TextView)findViewById(0x7f0a0304);
        txtVibration = (TextView)findViewById(0x7f0a0306);
        btnSave = (Button)findViewById(0x7f0a0309);
        if (start != null) goto _L2; else goto _L1
_L1:
        ((Button)findViewById(0x7f0a0307)).setEnabled(false);
        start = new AlermData();
_L4:
        if (!start.getRepeat().equals("8"))
        {
            break; /* Loop/switch isn't completed */
        }
        for (int i1 = 0; i1 < mCheckedItems.length; i1++)
        {
            mCheckedItems[i1] = true;
        }

        break MISSING_BLOCK_LABEL_681;
_L2:
        bUpdate = true;
        txtLine.setText(start.getLine());
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(start.getStartTime() / 3600);
        aobj[1] = Integer.valueOf((start.getStartTime() % 3600) / 60);
        String s = String.format("%02d:%02d", aobj);
        txtStart.setText(s);
        txtCount.setText((new StringBuilder()).append(Integer.toString(start.getCountdownTime())).append(getString(0x7f0d025d)).toString());
        String s1 = CountdownUtil.getRepeatWeek(start.getRepeat(), this);
        txtRepeate.setText(s1);
        if (!CountdownUtil.isEmpty(start.getSound()))
        {
            txtSound.setText(start.getSound());
        }
        if (start.getAlermLength() > 0)
        {
            String s2 = (new StringBuilder()).append(Integer.toString(start.getAlermLength())).append(getString(0x7f0d02f8)).toString();
            txtLength.setText(s2);
        }
        if (start.getAlermVolume() > 0)
        {
            txtVolume.setText((new StringBuilder()).append(Integer.toString(start.getAlermVolume())).append(getString(0x7f0d02af)).toString());
        }
        if (!start.isVibration())
        {
            txtVibration.setText(getString(0x7f0d04ca));
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (start.getRepeat().equals("0"))
        {
            for (int l = 0; l < mCheckedItems.length; l++)
            {
                mCheckedItems[l] = false;
            }

        } else
        {
            String as[] = start.getRepeat().split(",");
            for (int j = 0; j < mCheckedItems.length; j++)
            {
                mCheckedItems[j] = false;
            }

            for (int k = 0; k < as.length; k++)
            {
                mCheckedItems[-1 + Integer.parseInt(as[k])] = true;
            }

        }
        onCheck();
        return;
    }

    public void onStart()
    {
        super.onStart();
        dispAd(this, "2080325037", "pv");
    }

    public void save(View view)
    {
        saveSetting();
        setResult(-1);
        finish();
    }

    public void showCountdownTimeDialog(View view)
    {
        String s;
        int i;
        final String items[];
        String as[];
        if (start.getCountdownTime() >= 0)
        {
            s = Integer.toString(start.getCountdownTime());
        } else
        {
            s = "5";
        }
        i = 0;
        items = getResources().getStringArray(0x7f070000);
        as = new String[items.length];
        for (int j = 0; j < as.length; j++)
        {
            as[j] = (new StringBuilder()).append(items[j]).append(getString(0x7f0d025d)).toString();
            if (s.equals(items[j]))
            {
                i = j;
            }
        }

        showSingleChoiceListDialog(getString(0x7f0d04c0), 0, null, as, i, 0x7f0d007e, new android.content.DialogInterface.OnClickListener() {

            final SettingStartDetailActivity this$0;
            final String val$items[];

            public void onClick(DialogInterface dialoginterface, int k)
            {
                String s1 = items[k];
                start.setCountdownTime(Integer.parseInt(s1));
                txtCount.setText((new StringBuilder()).append(s1).append(getString(0x7f0d025d)).toString());
                onCheck();
            }

            
            {
                this$0 = SettingStartDetailActivity.this;
                items = as;
                super();
            }
        });
    }

    public void showLengthDialog(View view)
    {
        String s;
        int i;
        final String items[];
        String as[];
        if (start.getAlermLength() >= 0)
        {
            s = Integer.toString(start.getAlermLength());
        } else
        {
            s = "5";
        }
        i = 0;
        items = getResources().getStringArray(0x7f070000);
        as = new String[items.length];
        for (int j = 0; j < as.length; j++)
        {
            as[j] = (new StringBuilder()).append(items[j]).append(getString(0x7f0d02f8)).toString();
            if (s.equals(items[j]))
            {
                i = j;
            }
        }

        showSingleChoiceListDialog(getString(0x7f0d04bb), 0, null, as, i, 0x7f0d007e, new android.content.DialogInterface.OnClickListener() {

            final SettingStartDetailActivity this$0;
            final String val$items[];

            public void onClick(DialogInterface dialoginterface, int k)
            {
                String s1 = items[k];
                start.setAlermLength(Integer.parseInt(s1));
                txtLength.setText((new StringBuilder()).append(s1).append(getString(0x7f0d02f8)).toString());
                onCheck();
            }

            
            {
                this$0 = SettingStartDetailActivity.this;
                items = as;
                super();
            }
        });
    }

    public void showLineDialog(View view)
    {
        ArrayList arraylist = (new SettingDb(this)).getAllStation(0);
        if (arraylist == null || arraylist.size() < 1)
        {
            showErrorMessageDialog(getString(0x7f0d04d6), getString(0x7f0d015e));
            return;
        } else
        {
            LinearLayout linearlayout = new LinearLayout(this);
            linearlayout.setOrientation(1);
            linearlayout.setBackgroundColor(getResources().getColor(0x7f09006e));
            String s = getString(0x7f0d04c8);
            ListView listview = new ListView(this);
            listview.setScrollingCacheEnabled(false);
            listview.setAdapter(new StationListAdapter(this, arraylist));
            listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                final SettingStartDetailActivity this$0;

                public void onItemClick(AdapterView adapterview, View view1, int i, long l)
                {
                    StationData stationdata = (StationData)view1.getTag();
                    String s1 = (new StringBuilder()).append(stationdata.getRailname()).append(" ").append(stationdata.getDirname()).append(getString(0x7f0d0275)).append("\u3000").append(stationdata.getName()).append(getString(0x7f0d0304)).toString();
                    start.setTimetableId(Integer.parseInt(stationdata.getId()));
                    start.setLine(s1);
                    txtLine.setText(s1);
                    onCheck();
                    dialog.dismiss();
                }

            
            {
                this$0 = SettingStartDetailActivity.this;
                super();
            }
            });
            dialog = (new TransitDialogBuilder(this)).setTitle(s).setView(listview).setPositiveButton(getString(0x7f0d0071), new android.content.DialogInterface.OnClickListener() {

                final SettingStartDetailActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$0 = SettingStartDetailActivity.this;
                super();
            }
            }).create();
            dialog.show();
            return;
        }
    }

    public void showRepeatDialog(View view)
    {
        final String items[] = getResources().getStringArray(0x7f07000c);
        TransitDialogBuilder transitdialogbuilder = new TransitDialogBuilder(this);
        transitdialogbuilder.setTitle(getString(0x7f0d04cc));
        transitdialogbuilder.setMultiChoiceItems(items, mCheckedItems, new android.content.DialogInterface.OnMultiChoiceClickListener() {

            final SettingStartDetailActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i, boolean flag)
            {
                mCheckedItems[i] = flag;
            }

            
            {
                this$0 = SettingStartDetailActivity.this;
                super();
            }
        });
        transitdialogbuilder.setNegativeButton(getString(0x7f0d0071), new android.content.DialogInterface.OnClickListener() {

            final SettingStartDetailActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            
            {
                this$0 = SettingStartDetailActivity.this;
                super();
            }
        });
        transitdialogbuilder.setPositiveButton(getString(0x7f0d007e), new android.content.DialogInterface.OnClickListener() {

            final SettingStartDetailActivity this$0;
            final String val$items[];

            public void onClick(DialogInterface dialoginterface, int i)
            {
                StringBuffer stringbuffer = new StringBuffer();
                StringBuffer stringbuffer1 = new StringBuffer();
                boolean flag = true;
                int j = 0;
                while (j < mCheckedItems.length) 
                {
                    if (mCheckedItems[j])
                    {
                        if (!flag)
                        {
                            stringbuffer.append(",");
                            stringbuffer1.append("\u3001");
                        } else
                        {
                            flag = false;
                        }
                        stringbuffer.append(Integer.toString(j + 1));
                        stringbuffer1.append(items[j]);
                    }
                    j++;
                }
                if (stringbuffer.length() < 1)
                {
                    start.setRepeat("0");
                    txtRepeate.setText(getString(0x7f0d04cd));
                } else
                {
                    start.setRepeat(stringbuffer.toString());
                    txtRepeate.setText(stringbuffer1.toString());
                }
                onCheck();
            }

            
            {
                this$0 = SettingStartDetailActivity.this;
                items = as;
                super();
            }
        });
        transitdialogbuilder.show();
    }

    public void showSoundDialog(View view)
    {
        if (start.getSoundUri() != null)
        {
            String as[] = new String[2];
            as[0] = getString(0x7f0d04d1);
            as[1] = getString(0x7f0d04d3);
            (new TransitDialogBuilder(this)).setTitle(getString(0x7f0d04d0)).setItems(as, new android.content.DialogInterface.OnClickListener() {

                final SettingStartDetailActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    if (i == 0)
                    {
                        Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
                        startActivityForResult(intent, 1);
                        return;
                    } else
                    {
                        start.setSound(null);
                        start.setSoundUri(null);
                        txtSound.setText(getString(0x7f0d04d2));
                        return;
                    }
                }

            
            {
                this$0 = SettingStartDetailActivity.this;
                super();
            }
            }).setPositiveButton(getString(0x7f0d0071), new android.content.DialogInterface.OnClickListener() {

                final SettingStartDetailActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$0 = SettingStartDetailActivity.this;
                super();
            }
            }).show();
            return;
        } else
        {
            startActivityForResult(new Intent("android.intent.action.RINGTONE_PICKER"), 1);
            return;
        }
    }

    public void showStartTimeDialog(View view)
    {
        int i;
        int j;
        if (start.getStartTime() >= 0)
        {
            i = start.getStartTime() / 3600;
            j = (start.getStartTime() % 3600) / 60;
        } else
        {
            Calendar calendar = Calendar.getInstance();
            i = calendar.get(11);
            j = calendar.get(12);
        }
        showTimePickerDialog(getString(0x7f0d04d9), i, j, new android.app.TimePickerDialog.OnTimeSetListener() {

            final SettingStartDetailActivity this$0;

            public void onTimeSet(TimePicker timepicker, int k, int l)
            {
                start.setStartTime(60 * (k * 60) + l * 60);
                Object aobj[] = new Object[2];
                aobj[0] = Integer.valueOf(k);
                aobj[1] = Integer.valueOf(l);
                String s = String.format("%02d:%02d", aobj);
                txtStart.setText(s);
                onCheck();
            }

            
            {
                this$0 = SettingStartDetailActivity.this;
                super();
            }
        });
    }

    public void showVibDialog(View view)
    {
        String as[] = new String[2];
        as[0] = getString(0x7f0d04ca);
        as[1] = getString(0x7f0d04cb);
        (new TransitDialogBuilder(this)).setTitle(getString(0x7f0d04dd)).setItems(as, new android.content.DialogInterface.OnClickListener() {

            final SettingStartDetailActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                if (i == 0)
                {
                    start.setVibration(false);
                    txtVibration.setText(getString(0x7f0d04ca));
                    return;
                } else
                {
                    start.setVibration(true);
                    txtVibration.setText(getString(0x7f0d04cb));
                    return;
                }
            }

            
            {
                this$0 = SettingStartDetailActivity.this;
                super();
            }
        }).setPositiveButton(getString(0x7f0d0071), new android.content.DialogInterface.OnClickListener() {

            final SettingStartDetailActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.cancel();
            }

            
            {
                this$0 = SettingStartDetailActivity.this;
                super();
            }
        }).show();
    }

    public void showVolumeDialog(View view)
    {
        VolumeDialog volumedialog = new VolumeDialog(this, start);
        volumedialog.setDefVolume(start.getAlermVolume());
        volumedialog.showDilaog(new jp.co.yahoo.android.apps.transit.timer.viewparts.VolumeDialog.DialogListener() {

            final SettingStartDetailActivity this$0;

            public void onCancel()
            {
            }

            public void onOk(int i)
            {
                start.setAlermVolume(i);
                txtVolume.setText((new StringBuilder()).append(Integer.toString(i)).append(getString(0x7f0d02af)).toString());
            }

            
            {
                this$0 = SettingStartDetailActivity.this;
                super();
            }
        });
    }











}
