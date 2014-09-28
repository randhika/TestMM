// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

// Referenced classes of package jp.co.yahoo.android.common:
//            YDialogBaseActivity, YTime, YTimePicker, YDisplayUtils

public class YDialogTimePickerActivity extends YDialogBaseActivity
{

    public static final String DATE_KEY_NAME = "date";
    public static final int REQ_CODE = 0;
    private static final String TAG = jp/co/yahoo/android/common/YDialogTimePickerActivity.getSimpleName();
    public static final String TITLE_KEY_NAME = "title";
    private final String TITLE2 = "\u958B\u59CB\u6642\u9593";
    private final YTime mTime = new YTime();
    private YTimePicker mTimePicker;

    public YDialogTimePickerActivity()
    {
    }

    public static YTime getResultTime(Intent intent)
    {
        YTime ytime = new YTime();
        ytime.setToNow();
        ytime.set(intent.getLongExtra("date", ytime.toMillis(false)));
        return ytime;
    }

    private void setTitle(int i, int j)
    {
        mTime.hour = i;
        mTime.minute = j;
        mTime.normalize(false);
        setTitle(mTime);
    }

    private void setTitle(YTime ytime)
    {
        setTitle(((CharSequence) (mTime.format("%H : %M"))));
    }

    public static void startDialog(Activity activity, String s, YTime ytime)
    {
        Intent intent = new Intent(activity.getApplicationContext(), jp/co/yahoo/android/common/YDialogTimePickerActivity);
        intent.putExtra("date", ytime.toMillis(false));
        intent.putExtra("title", s);
        activity.startActivityForResult(intent, REQ_CODE);
    }

    public static void startDialog(Activity activity, String s, YTime ytime, int i)
    {
        Intent intent = new Intent(activity.getApplicationContext(), jp/co/yahoo/android/common/YDialogTimePickerActivity);
        intent.putExtra("date", ytime.toMillis(false));
        intent.putExtra("title", s);
        activity.startActivityForResult(intent, i);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.common_dialog_time_picker);
        Intent intent = getIntent();
        mTime.setToNow();
        mTime.set(intent.getLongExtra("date", mTime.toMillis()));
        String s = intent.getStringExtra("title");
        if (s == null)
        {
            s = "\u958B\u59CB\u6642\u9593";
        }
        setTitleIcon(R.drawable.common_datepicker_ll_timeline_time);
        setTitle(s);
        showFooterBorderLine(false);
        mTimePicker = (YTimePicker)findViewById(R.id.time_picker);
        mTimePicker.setCurrentHour(mTime.hour);
        mTimePicker.setCurrentMinute(mTime.minute);
        setDialogWidth((int)YDisplayUtils.getInstance(this).dp2px(200F));
    }

    protected boolean onOkButtonClick()
    {
        int i = mTimePicker.getCurrentHour();
        int j = mTimePicker.getCurrentMinute();
        if (i >= 0 && j >= 0)
        {
            mTime.hour = i;
            mTime.minute = j;
            mTime.normalize();
            Intent intent = getIntent();
            intent.putExtra("date", mTime.toMillis());
            setResult(-1, intent);
            return true;
        } else
        {
            return false;
        }
    }

    static 
    {
        REQ_CODE = jp/co/yahoo/android/common/YDialogTimePickerActivity.hashCode();
    }
}
