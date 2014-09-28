// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import jp.co.yahoo.android.apps.transit.timer.api.data.DivideData;
import jp.co.yahoo.android.apps.transit.timer.common.SettingDivide;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownBaseActivity

public class SettingDivideActivity extends CountdownBaseActivity
{

    private DivideData divide;
    private SettingDivide setting;
    private TextView txtAfterTime;
    private TextView txtBeforeTime;
    private TextView txtDivideTime;

    public SettingDivideActivity()
    {
        setting = null;
        divide = null;
    }

    private void setReverse(boolean flag)
    {
        if (flag)
        {
            txtBeforeTime.setCompoundDrawablesWithIntrinsicBounds(0, 0x7f020191, 0, 0);
            txtBeforeTime.setContentDescription((new StringBuilder()).append(getString(0x7f0d0265)).append(",").append(txtBeforeTime.getText()).toString());
            txtAfterTime.setCompoundDrawablesWithIntrinsicBounds(0, 0x7f0201b3, 0, 0);
            txtAfterTime.setContentDescription((new StringBuilder()).append(getString(0x7f0d0287)).append(",").append(txtAfterTime.getText()).toString());
            return;
        } else
        {
            txtBeforeTime.setCompoundDrawablesWithIntrinsicBounds(0, 0x7f0201b3, 0, 0);
            txtBeforeTime.setContentDescription((new StringBuilder()).append(getString(0x7f0d0287)).append(",").append(txtBeforeTime.getText()).toString());
            txtAfterTime.setCompoundDrawablesWithIntrinsicBounds(0, 0x7f020191, 0, 0);
            txtAfterTime.setContentDescription((new StringBuilder()).append(getString(0x7f0d0265)).append(",").append(txtAfterTime.getText()).toString());
            return;
        }
    }

    private void updateDivide()
    {
        String s = CountdownUtil.getZeroNum(divide.getDivideHour());
        String s1 = CountdownUtil.getZeroNum(divide.getDivideMin());
        String s2 = (new StringBuilder()).append(s).append(":").append(s1).toString();
        txtDivideTime.setText(s2);
        txtBeforeTime.setText((new StringBuilder()).append(getString(0x7f0d0283)).append("\uFF5E").append(s2).toString());
        txtAfterTime.setText((new StringBuilder()).append(s2).append("\uFF5E").append(getString(0x7f0d0291)).toString());
        setReverse(divide.isReverse());
    }

    public void cancel(View view)
    {
        setResult(0);
        finish();
    }

    public void changeDivide(View view)
    {
        if (divide.isReverse())
        {
            divide.setReverse(false);
        } else
        {
            divide.setReverse(true);
        }
        setReverse(divide.isReverse());
    }

    public void changeTime(View view)
    {
        int i = divide.getDivideHour();
        int j = divide.getDivideMin();
        showTimePickerDialog(getString(0x7f0d0510), i, j, new android.app.TimePickerDialog.OnTimeSetListener() {

            final SettingDivideActivity this$0;

            public void onTimeSet(TimePicker timepicker, int k, int l)
            {
                divide.setDivideHour(k);
                divide.setDivideMin(l);
                updateDivide();
            }

            
            {
                this$0 = SettingDivideActivity.this;
                super();
            }
        });
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0300a0);
        setTitle(getString(0x7f0d04c5));
        txtBeforeTime = (TextView)findViewById(0x7f0a02ee);
        txtAfterTime = (TextView)findViewById(0x7f0a02ef);
        txtDivideTime = (TextView)findViewById(0x7f0a02ec);
        setting = new SettingDivide(this);
        divide = setting.getDivide();
        updateDivide();
    }

    public void onStart()
    {
        super.onStart();
        dispAd(this, "2080325039", "pv");
    }

    public void save(View view)
    {
        setting.setDivide(divide);
        setResult(-1);
        finish();
    }


}
