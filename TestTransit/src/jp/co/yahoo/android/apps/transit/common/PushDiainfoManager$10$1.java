// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            PushDiainfoManager

class this._cls1
    implements jp.co.yahoo.android.apps.transit.api.ner
{

    final onSuccess this$1;

    public boolean onCanceled()
    {
        if (listener != null)
        {
            listener.onCanceled();
        }
        return false;
    }

    public boolean onError(APIError apierror)
    {
        if (listener != null)
        {
            if (apierror == null)
            {
                listener.onError(4, "-1", PushDiainfoManager.access$200(_fld0).getString(0x7f0d014f), PushDiainfoManager.access$200(_fld0).getString(0x7f0d0102));
            } else
            {
                listener.onError(4, apierror.getCode(), PushDiainfoManager.access$200(_fld0).getString(0x7f0d014f), apierror.getMessage());
            }
        }
        return false;
    }

    public boolean onSuccess(Bundle bundle)
    {
        clearYid();
        if (listener != null)
        {
            listener.onSuccess(PushDiainfoManager.access$200(_fld0).getString(0x7f0d00b1), PushDiainfoManager.access$200(_fld0).getString(0x7f0d00a8));
        }
        return false;
    }

    public volatile boolean onSuccess(Object obj)
    {
        return onSuccess((Bundle)obj);
    }

    l.yid()
    {
        this$1 = this._cls1.this;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/apps/transit/common/PushDiainfoManager$10

/* anonymous class */
    class PushDiainfoManager._cls10
        implements jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener
    {

        final PushDiainfoManager this$0;
        final PushDiainfoManager.PushManagerListener val$listener;
        final BearerToken val$token;
        final String val$yid;

        public boolean onCanceled()
        {
            if (listener != null)
            {
                listener.onCanceled();
            }
            return false;
        }

        public boolean onError(APIError apierror)
        {
            if (listener != null)
            {
                if (apierror == null)
                {
                    listener.onError(3, "-1", PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d014f), PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d0102));
                } else
                {
                    listener.onError(3, apierror.getCode(), PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d014f), apierror.getMessage());
                }
            }
            return false;
        }

        public boolean onSuccess(Bundle bundle)
        {
            if (bundle == null)
            {
                clearYid();
                if (listener != null)
                {
                    listener.onSuccess(PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d00b1), PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d00a8));
                }
            } else
            {
                ArrayList arraylist = PushDiainfoManager.access$600(PushDiainfoManager.this, bundle, "diainfo_", "diainfo_origin_", "diainfo_stopAll", true);
                if (arraylist.isEmpty())
                {
                    clearYid();
                    if (listener != null)
                    {
                        listener.onSuccess(PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d00b1), PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d00a8));
                        return false;
                    }
                } else
                {
                    PushDiainfoManager.access$700(PushDiainfoManager.this, token, yid, arraylist, false, false, new PushDiainfoManager._cls10._cls1());
                    return false;
                }
            }
            return false;
        }

        public volatile boolean onSuccess(Object obj)
        {
            return onSuccess((Bundle)obj);
        }

            
            {
                this$0 = final_pushdiainfomanager;
                listener = pushmanagerlistener;
                token = bearertoken;
                yid = String.this;
                super();
            }
    }

}
