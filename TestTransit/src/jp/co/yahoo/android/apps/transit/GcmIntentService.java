// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.IntentService;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.util.Calendar;
import jp.co.yahoo.android.apps.transit.common.PushDiainfoManager;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            PushDiainfoDialog

public class GcmIntentService extends IntentService
{

    public GcmIntentService()
    {
        super("GcmIntentService");
    }

    private void showPushDiainfoDialog(Bundle bundle)
    {
        if ((new PushDiainfoManager(this)).loadPushTime() == getResources().getInteger(0x7f0c0036))
        {
            Calendar calendar = Calendar.getInstance();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(11, 6);
            calendar1.set(12, 0);
            calendar1.set(13, 0);
            calendar1.set(14, 0);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(11, 22);
            calendar2.set(12, 0);
            calendar2.set(13, 0);
            calendar2.set(14, 0);
            if (calendar.before(calendar1) || calendar.after(calendar2))
            {
                return;
            }
        }
        if (TransitUtil.getAccessToken(this) == null)
        {
            stopSelf();
            return;
        } else
        {
            Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/PushDiainfoDialog);
            intent.addFlags(0x10000000);
            intent.putExtra(getString(0x7f0d01ad), bundle);
            startActivity(intent);
            return;
        }
    }

    protected void onHandleIntent(Intent intent)
    {
        Bundle bundle = intent.getExtras();
        if (!bundle.isEmpty() && "gcm".equals(GoogleCloudMessaging.getInstance(this).getMessageType(intent)))
        {
            showPushDiainfoDialog(bundle);
        }
        WakefulBroadcastReceiver.completeWakefulIntent(intent);
    }
}
