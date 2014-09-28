// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;

import android.util.Log;

// Referenced classes of package jp.co.yahoo.applicot:
//            ErrorReporter, Applicot, SendWorker

class val.endApplication extends Thread
{

    final ErrorReporter this$0;
    final boolean val$endApplication;
    final String val$reportFileName;
    final boolean val$showDirectDialog;
    final SendWorker val$worker;

    public void run()
    {
        Log.d(Applicot.LOG_TAG, "Waiting for Toast + worker...");
        while (!ErrorReporter.access$100() || val$worker != null && val$worker.isAlive()) 
        {
            try
            {
                Thread.sleep(100L);
            }
            catch (InterruptedException interruptedexception)
            {
                Log.e(Applicot.LOG_TAG, "Error : ", interruptedexception);
            }
        }
        if (val$showDirectDialog)
        {
            Log.d(Applicot.LOG_TAG, "About to create DIALOG from #handleException");
            notifyDialog(val$reportFileName);
        }
        Log.d(Applicot.LOG_TAG, (new StringBuilder()).append("Wait for Toast + worker ended. Kill Application ? ").append(val$endApplication).toString());
        if (val$endApplication)
        {
            ErrorReporter.access$200(ErrorReporter.this);
        }
    }

    ()
    {
        this$0 = final_errorreporter;
        val$worker = sendworker;
        val$showDirectDialog = flag;
        val$reportFileName = s;
        val$endApplication = Z.this;
        super();
    }
}
