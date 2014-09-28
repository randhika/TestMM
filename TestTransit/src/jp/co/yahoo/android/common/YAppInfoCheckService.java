// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

// Referenced classes of package jp.co.yahoo.android.common:
//            YHttpRequest, YLogger, YAppInfoCheckManager, YAppInfoData

public class YAppInfoCheckService extends Service
{
    public class YAppInfoCheckServiceBinder extends Binder
    {

        final YAppInfoCheckService this$0;

        public YAppInfoCheckService getService()
        {
            return YAppInfoCheckService.this;
        }

        public YAppInfoCheckServiceBinder()
        {
            this$0 = YAppInfoCheckService.this;
            super();
        }
    }


    public static final String ACTION_FOUND_UPDATE = "jp.co.yahoo.android.common.yappinfocheckservice.foundupdate";
    private static final long DEFAULT_INTERVAL = 0x5265c00L;
    private static final String FILE_NAME = "update.xml";
    private long mInterval;
    private Timer mTimer;
    private String mUrl;

    public YAppInfoCheckService()
    {
    }

    private void download()
    {
        YHttpRequest yhttprequest = new YHttpRequest(mUrl) {

            final YAppInfoCheckService this$0;

            public void onCanceled()
            {
                super.onCanceled();
            }

            public void onComplete()
            {
                onDownloadComplete();
            }

            public boolean onCompleteInThread()
            {
                if (getStatusCode() == 200)
                {
                    String s = getResponseString();
                    if (s != null)
                    {
                        writeUpdateXml(s);
                        return true;
                    }
                }
                return false;
            }

            public void onTimeout()
            {
                super.onTimeout();
            }

            
            {
                this$0 = YAppInfoCheckService.this;
                super(s);
            }
        };
        yhttprequest.setTimeout(10000);
        yhttprequest.asyncGet();
    }

    public static Intent getServiceIntent(Context context, String s, long l)
    {
        Intent intent = new Intent(context, jp/co/yahoo/android/common/YAppInfoCheckService);
        intent.putExtra("url", s);
        intent.putExtra("interval", l);
        return intent;
    }

    private long lastCheckedTime()
    {
        File file = getFileStreamPath("update.xml");
        if (file.isFile())
        {
            return file.lastModified();
        } else
        {
            return 0L;
        }
    }

    private void onDownloadComplete()
    {
        YLogger.log("onDownloadComplete");
        YAppInfoData yappinfodata = getAppInfo();
        if (yappinfodata != null)
        {
            Intent intent = new Intent("jp.co.yahoo.android.common.yappinfocheckservice.foundupdate");
            intent.putExtra("xml_data", yappinfodata);
            sendBroadcast(intent);
        }
    }

    private String readUpdateXml()
    {
        java.io.FileInputStream fileinputstream;
        BufferedReader bufferedreader;
        StringBuffer stringbuffer;
        fileinputstream = null;
        bufferedreader = null;
        stringbuffer = new StringBuffer("");
        BufferedReader bufferedreader1;
        fileinputstream = openFileInput("update.xml");
        bufferedreader1 = new BufferedReader(new InputStreamReader(fileinputstream));
_L3:
        String s = bufferedreader1.readLine();
        if (s == null) goto _L2; else goto _L1
_L1:
        stringbuffer.append(s);
          goto _L3
        IOException ioexception;
        ioexception;
        bufferedreader = bufferedreader1;
_L7:
        ioexception.printStackTrace();
        if (bufferedreader == null)
        {
            break MISSING_BLOCK_LABEL_78;
        }
        bufferedreader.close();
        if (fileinputstream != null)
        {
            try
            {
                fileinputstream.close();
            }
            catch (IOException ioexception2)
            {
                ioexception2.printStackTrace();
            }
        }
        return stringbuffer.toString();
_L2:
        if (bufferedreader1 == null)
        {
            break MISSING_BLOCK_LABEL_101;
        }
        bufferedreader1.close();
        if (fileinputstream != null)
        {
            try
            {
                fileinputstream.close();
            }
            catch (IOException ioexception3)
            {
                ioexception3.printStackTrace();
            }
        }
        if (true)
        {
            break MISSING_BLOCK_LABEL_86;
        }
        Exception exception;
        exception;
_L5:
        if (bufferedreader == null)
        {
            break MISSING_BLOCK_LABEL_142;
        }
        bufferedreader.close();
        if (fileinputstream != null)
        {
            try
            {
                fileinputstream.close();
            }
            catch (IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
        }
        throw exception;
        exception;
        bufferedreader = bufferedreader1;
        if (true) goto _L5; else goto _L4
_L4:
        ioexception;
        bufferedreader = null;
        if (true) goto _L7; else goto _L6
_L6:
    }

    private void schedule()
    {
        if (mUrl == null)
        {
            return;
        }
        if (mTimer != null)
        {
            mTimer.cancel();
        }
        mTimer = new Timer();
        TimerTask timertask = new TimerTask() {

            final YAppInfoCheckService this$0;

            public void run()
            {
                download();
            }

            
            {
                this$0 = YAppInfoCheckService.this;
                super();
            }
        };
        long l = lastCheckedTime();
        if ((new Date()).getTime() - l > mInterval)
        {
            mTimer.schedule(timertask, 0L, mInterval);
            return;
        } else
        {
            mTimer.schedule(timertask, new Date(l + mInterval), mInterval);
            return;
        }
    }

    private void writeUpdateXml(String s)
    {
        java.io.FileOutputStream fileoutputstream;
        OutputStreamWriter outputstreamwriter;
        fileoutputstream = null;
        outputstreamwriter = null;
        OutputStreamWriter outputstreamwriter1;
        fileoutputstream = openFileOutput("update.xml", 0);
        outputstreamwriter1 = new OutputStreamWriter(fileoutputstream);
        outputstreamwriter1.write(s);
        outputstreamwriter1.close();
        fileoutputstream.close();
        if (fileoutputstream != null)
        {
            try
            {
                fileoutputstream.close();
            }
            catch (IOException ioexception3)
            {
                ioexception3.printStackTrace();
                return;
            }
        }
        if (outputstreamwriter1 == null)
        {
            break MISSING_BLOCK_LABEL_55;
        }
        outputstreamwriter1.close();
_L2:
        return;
        IOException ioexception1;
        ioexception1;
_L5:
        ioexception1.printStackTrace();
        if (fileoutputstream == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        fileoutputstream.close();
        if (outputstreamwriter == null) goto _L2; else goto _L1
_L1:
        try
        {
            outputstreamwriter.close();
            return;
        }
        catch (IOException ioexception2)
        {
            ioexception2.printStackTrace();
        }
        return;
        Exception exception;
        exception;
_L4:
        if (fileoutputstream == null)
        {
            break MISSING_BLOCK_LABEL_106;
        }
        fileoutputstream.close();
        if (outputstreamwriter != null)
        {
            try
            {
                outputstreamwriter.close();
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        }
        throw exception;
        exception;
        outputstreamwriter = outputstreamwriter1;
        if (true) goto _L4; else goto _L3
_L3:
        ioexception1;
        outputstreamwriter = outputstreamwriter1;
          goto _L5
    }

    public YAppInfoData getAppInfo()
    {
        return (new YAppInfoCheckManager(getApplicationContext())).getAppInfo(readUpdateXml());
    }

    public IBinder onBind(Intent intent)
    {
        mUrl = intent.getStringExtra("url");
        mInterval = intent.getLongExtra("interval", 0x5265c00L);
        schedule();
        return new YAppInfoCheckServiceBinder();
    }

    public boolean onUnbind(Intent intent)
    {
        if (mTimer != null)
        {
            mTimer.cancel();
        }
        return super.onUnbind(intent);
    }



}
