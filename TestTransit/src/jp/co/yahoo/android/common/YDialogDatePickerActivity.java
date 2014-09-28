// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;

// Referenced classes of package jp.co.yahoo.android.common:
//            YDialogBaseActivity, YTime, YDatePicker, YDisplayUtils

public class YDialogDatePickerActivity extends YDialogBaseActivity
{

    public static final String KEY_DATE = "date";
    public static final String KEY_TITLE = "title";
    public static final int REQ_CODE = jp/co/yahoo/android/common/YDialogDatePickerActivity.hashCode();
    private final String TITLE2 = "\u958B\u59CB\u65E5\u6642";
    private YDatePicker mDatePicker;

    public YDialogDatePickerActivity()
    {
    }

    public static YTime getResultTime(Intent intent)
    {
        YTime ytime = new YTime();
        ytime.setToNow();
        ytime.set(intent.getLongExtra("date", ytime.toMillis()));
        return ytime;
    }

    private void setTitle(int i, int j, int k)
    {
        YTime ytime = new YTime();
        ytime.set(k, j, i);
        ytime.normalize(false);
        setTitle(ytime);
    }

    private void setTitle(YTime ytime)
    {
        int i = 1 + ytime.month;
        setTitle(((CharSequence) ((new StringBuilder()).append(ytime.year).append("\u5E74").append(i).append("\u6708").append(ytime.monthDay).append("\u65E5").append(getJapaneseNameOfWeekDay(ytime, "(", ")")).toString())));
    }

    public static void startDialog(Activity activity, int i, YTime ytime)
    {
        startDialog(activity, activity.getString(i), ytime);
    }

    public static void startDialog(Activity activity, String s, YTime ytime)
    {
        Intent intent = new Intent(activity.getApplicationContext(), jp/co/yahoo/android/common/YDialogDatePickerActivity);
        intent.putExtra("date", ytime.toMillis(false));
        intent.putExtra("title", s);
        activity.startActivityForResult(intent, REQ_CODE);
    }

    public static void startDialog(Activity activity, String s, YTime ytime, int i)
    {
        Intent intent = new Intent(activity.getApplicationContext(), jp/co/yahoo/android/common/YDialogDatePickerActivity);
        intent.putExtra("date", ytime.toMillis(false));
        intent.putExtra("title", s);
        activity.startActivityForResult(intent, i);
    }

    public String getJapaneseNameOfWeekDay(YTime ytime, String s, String s1)
    {
        switch (ytime.weekDay)
        {
        default:
            return "";

        case 0: // '\0'
            return (new StringBuilder()).append(s).append("\u65E5").append(s1).toString();

        case 1: // '\001'
            return (new StringBuilder()).append(s).append("\u6708").append(s1).toString();

        case 2: // '\002'
            return (new StringBuilder()).append(s).append("\u706B").append(s1).toString();

        case 3: // '\003'
            return (new StringBuilder()).append(s).append("\u6C34").append(s1).toString();

        case 4: // '\004'
            return (new StringBuilder()).append(s).append("\u6728").append(s1).toString();

        case 5: // '\005'
            return (new StringBuilder()).append(s).append("\u91D1").append(s1).toString();

        case 6: // '\006'
            return (new StringBuilder()).append(s).append("\u571F").append(s1).toString();
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.common_dialog_date_picker);
        YTime ytime = new YTime();
        Intent intent = getIntent();
        ytime.setToNow();
        ytime.set(intent.getLongExtra("date", ytime.toMillis()));
        String s = intent.getStringExtra("title");
        if (s == null)
        {
            s = "\u958B\u59CB\u65E5\u6642";
        }
        setTitleIcon(R.drawable.common_datepicker_ll_timeline_time);
        setTitle(s);
        showFooterBorderLine(false);
        mDatePicker = (YDatePicker)findViewById(R.id.date_picker);
        mDatePicker.init(ytime.year, ytime.month, ytime.monthDay, new android.widget.DatePicker.OnDateChangedListener() {

            final YDialogDatePickerActivity this$0;

            public void onDateChanged(DatePicker datepicker, int i, int j, int k)
            {
            }

            
            {
                this$0 = YDialogDatePickerActivity.this;
                super();
            }
        });
        setDialogWidth((int)YDisplayUtils.getInstance(this).dp2px(290F));
    }

    protected boolean onOkButtonClick()
    {
        int i = mDatePicker.getYear();
        int j = mDatePicker.getMonth();
        int k = mDatePicker.getDayOfMonth();
        boolean flag = false;
        if (i >= 0)
        {
            flag = false;
            if (j >= 0)
            {
                flag = false;
                if (k >= 0)
                {
                    YTime ytime = new YTime();
                    ytime.set(0, 0, 0, k, j, i);
                    ytime.normalize();
                    Intent intent = getIntent();
                    intent.putExtra("date", ytime.toMillis());
                    setResult(-1, intent);
                    flag = true;
                }
            }
        }
        return flag;
    }

}
