// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
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

    // Unreferenced inner class jp/co/yahoo/android/apps/transit/common/PushDiainfoManager$11

/* anonymous class */
    class PushDiainfoManager._cls11
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
            if (bundle != null) goto _L2; else goto _L1
_L1:
            if (listener != null)
            {
                listener.onSuccess(PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d00b1), PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d00a8));
            }
_L4:
            return false;
_L2:
            if (!getDiainfoStopAllFlag(bundle))
            {
                break; /* Loop/switch isn't completed */
            }
            if (listener != null)
            {
                listener.onSuccess(PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d00b1), PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d00a8));
                return false;
            }
            if (true) goto _L4; else goto _L3
_L3:
            ArrayList arraylist = PushDiainfoManager.access$600(PushDiainfoManager.this, bundle, "diainfo_origin_", null, null, true);
            if (arraylist.isEmpty())
            {
                if (listener != null)
                {
                    listener.onSuccess(PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d00b1), PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d00a8));
                    return false;
                }
            } else
            {
                ArrayList arraylist1 = new ArrayList();
                String s;
                for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); arraylist1.add((new StringBuilder()).append("diainfo_").append(s.substring("diainfo_origin_".length())).toString()))
                {
                    s = (String)iterator.next();
                }

                PushDiainfoManager.access$700(PushDiainfoManager.this, token, yid, arraylist1, true, true, new PushDiainfoManager._cls11._cls1());
                return false;
            }
            if (true) goto _L4; else goto _L5
_L5:
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
