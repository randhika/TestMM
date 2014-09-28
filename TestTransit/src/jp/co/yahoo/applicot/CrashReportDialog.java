// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.IOException;
import jp.co.yahoo.applicot.collector.CrashReportData;
import jp.co.yahoo.applicot.log.ApplicotLog;
import jp.co.yahoo.applicot.util.ToastSender;

// Referenced classes of package jp.co.yahoo.applicot:
//            Applicot, ApplicotConfiguration, ErrorReporter, CrashReportPersister, 
//            ReportField

public class CrashReportDialog extends Activity
    implements android.content.DialogInterface.OnClickListener, android.content.DialogInterface.OnDismissListener
{

    private static final String STATE_COMMENT = "comment";
    private static final String STATE_EMAIL = "email";
    AlertDialog mDialog;
    String mReportFileName;
    private SharedPreferences prefs;
    private EditText userComment;
    private EditText userEmail;

    public CrashReportDialog()
    {
    }

    private View buildCustomView(Bundle bundle)
    {
        LinearLayout linearlayout = new LinearLayout(this);
        linearlayout.setOrientation(1);
        linearlayout.setPadding(10, 10, 10, 10);
        linearlayout.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -2));
        linearlayout.setFocusable(true);
        linearlayout.setFocusableInTouchMode(true);
        ScrollView scrollview = new ScrollView(this);
        linearlayout.addView(scrollview, new android.widget.LinearLayout.LayoutParams(-1, -1, 1.0F));
        LinearLayout linearlayout1 = new LinearLayout(this);
        linearlayout1.setOrientation(1);
        scrollview.addView(linearlayout1);
        TextView textview = new TextView(this);
        int i = Applicot.getConfig().resDialogText();
        if (i != 0)
        {
            textview.setText(getText(i));
        }
        linearlayout1.addView(textview);
        int j = Applicot.getConfig().resDialogCommentPrompt();
        if (j != 0)
        {
            TextView textview1 = new TextView(this);
            textview1.setText(getText(j));
            textview1.setPadding(textview1.getPaddingLeft(), 10, textview1.getPaddingRight(), textview1.getPaddingBottom());
            linearlayout1.addView(textview1, new android.widget.LinearLayout.LayoutParams(-1, -2));
            userComment = new EditText(this);
            userComment.setLines(2);
            if (bundle != null)
            {
                String s1 = bundle.getString("comment");
                if (s1 != null)
                {
                    userComment.setText(s1);
                }
            }
            linearlayout1.addView(userComment);
        }
        int k = Applicot.getConfig().resDialogEmailPrompt();
        if (k != 0)
        {
            TextView textview2 = new TextView(this);
            textview2.setText(getText(k));
            textview2.setPadding(textview2.getPaddingLeft(), 10, textview2.getPaddingRight(), textview2.getPaddingBottom());
            linearlayout1.addView(textview2);
            userEmail = new EditText(this);
            userEmail.setSingleLine();
            userEmail.setInputType(33);
            prefs = getSharedPreferences(Applicot.getConfig().sharedPreferencesName(), Applicot.getConfig().sharedPreferencesMode());
            String s = null;
            if (bundle != null)
            {
                s = bundle.getString("email");
            }
            if (s != null)
            {
                userEmail.setText(s);
            } else
            {
                userEmail.setText(prefs.getString("applicot.user.email", ""));
            }
            linearlayout1.addView(userEmail);
        }
        return linearlayout;
    }

    private void cancelReports()
    {
        Applicot.getErrorReporter().deletePendingNonApprovedReports(false);
    }

    private void sendCrash()
    {
        String s;
        String s1;
        CrashReportPersister crashreportpersister;
        int i;
        if (userComment != null)
        {
            s = userComment.getText().toString();
        } else
        {
            s = "";
        }
        if (prefs != null && userEmail != null)
        {
            s1 = userEmail.getText().toString();
            android.content.SharedPreferences.Editor editor = prefs.edit();
            editor.putString("applicot.user.email", s1);
            editor.commit();
        } else
        {
            s1 = "";
        }
        crashreportpersister = new CrashReportPersister(getApplicationContext());
        try
        {
            Log.d(Applicot.LOG_TAG, (new StringBuilder()).append("Add user comment to ").append(mReportFileName).toString());
            CrashReportData crashreportdata = crashreportpersister.load(mReportFileName);
            crashreportdata.put(ReportField.USER_COMMENT, s);
            crashreportdata.put(ReportField.USER_EMAIL, s1);
            crashreportpersister.store(crashreportdata, mReportFileName);
        }
        catch (IOException ioexception)
        {
            Log.w(Applicot.LOG_TAG, "User comment not added: ", ioexception);
        }
        Log.v(Applicot.LOG_TAG, "About to start SenderWorker from CrashReportDialog");
        Applicot.getErrorReporter().startSendingReports(false, true);
        i = Applicot.getConfig().resDialogOkToast();
        if (i != 0)
        {
            ToastSender.sendToast(getApplicationContext(), i, 1);
        }
    }

    protected void cancelNotification()
    {
        ((NotificationManager)getSystemService("notification")).cancel(666);
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (i == -1)
        {
            sendCrash();
        } else
        {
            cancelReports();
        }
        finish();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (getIntent().getBooleanExtra("FORCE_CANCEL", false))
        {
            Applicot.log.d(Applicot.LOG_TAG, "Forced reports deletion.");
            cancelReports();
            finish();
            return;
        }
        mReportFileName = getIntent().getStringExtra("REPORT_FILE_NAME");
        Log.d(Applicot.LOG_TAG, (new StringBuilder()).append("Opening CrashReportDialog for ").append(mReportFileName).toString());
        if (mReportFileName == null)
        {
            finish();
        }
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        int i = Applicot.getConfig().resDialogTitle();
        if (i != 0)
        {
            builder.setTitle(i);
        }
        int j = Applicot.getConfig().resDialogIcon();
        if (j != 0)
        {
            builder.setIcon(j);
        }
        builder.setView(buildCustomView(bundle));
        builder.setPositiveButton(0x104000a, this);
        builder.setNegativeButton(0x1040000, this);
        cancelNotification();
        mDialog = builder.create();
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setOnDismissListener(this);
        mDialog.show();
    }

    public void onDismiss(DialogInterface dialoginterface)
    {
        finish();
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        if (userComment != null && userComment.getText() != null)
        {
            bundle.putString("comment", userComment.getText().toString());
        }
        if (userEmail != null && userEmail.getText() != null)
        {
            bundle.putString("email", userEmail.getText().toString());
        }
    }
}
