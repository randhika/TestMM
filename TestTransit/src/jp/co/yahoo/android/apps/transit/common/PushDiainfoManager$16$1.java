// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
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

    // Unreferenced inner class jp/co/yahoo/android/apps/transit/common/PushDiainfoManager$16

/* anonymous class */
    class PushDiainfoManager._cls16
        implements jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener
    {

        final PushDiainfoManager this$0;
        final PushDiainfoManager.PushManagerListener val$listener;
        final Bundle val$registeredRail;
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
                if (listener != null)
                {
                    listener.onError(3, "-1", PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d014f), PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d0102));
                }
                return false;
            }
            HashMap hashmap = (HashMap)bundle.getSerializable("result");
            if (hashmap == null)
            {
                if (listener != null)
                {
                    listener.onError(3, "-1", PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d014f), PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d0102));
                }
                return false;
            }
            ArrayList arraylist = new ArrayList();
            Iterator iterator = hashmap.keySet().iterator();
label0:
            do
            {
                if (iterator.hasNext())
                {
                    String s = (String)iterator.next();
                    if (!s.startsWith("diainfo_origin_") || !((Boolean)hashmap.get(s)).booleanValue())
                    {
                        continue;
                    }
                    int ai[] = PushDiainfoManager.access$1000(PushDiainfoManager.this, s);
                    int i = 0;
                    do
                    {
label1:
                        {
                            int j = registeredRail.size();
                            boolean flag = false;
                            if (i < j)
                            {
                                DiainfoData diainfodata = (DiainfoData)registeredRail.getSerializable(Integer.toString(i));
                                StringBuilder stringbuilder;
                                Object aobj[];
                                String s1;
                                int k;
                                int l;
                                if (TextUtils.isEmpty(diainfodata.getRailCode()))
                                {
                                    k = 0;
                                } else
                                {
                                    k = Integer.parseInt(diainfodata.getRailCode());
                                }
                                if (TextUtils.isEmpty(diainfodata.getRailRangeCode()))
                                {
                                    l = 0;
                                } else
                                {
                                    l = Integer.parseInt(diainfodata.getRailRangeCode());
                                }
                                if (ai[0] != k || ai[1] != l)
                                {
                                    break label1;
                                }
                                flag = true;
                            }
                            if (!flag)
                            {
                                arraylist.add(s);
                                stringbuilder = (new StringBuilder()).append("diainfo_");
                                aobj = new Object[2];
                                aobj[0] = Integer.valueOf(ai[0]);
                                aobj[1] = Integer.valueOf(ai[1]);
                                s1 = stringbuilder.append(String.format("%04d%04d", aobj)).toString();
                                if (hashmap.containsKey(s1) && ((Boolean)hashmap.get(s1)).booleanValue())
                                {
                                    arraylist.add(s1);
                                }
                            }
                            continue label0;
                        }
                        i++;
                    } while (true);
                }
                if (arraylist.isEmpty())
                {
                    if (listener != null)
                    {
                        listener.onSuccess(PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d00b1), PushDiainfoManager.access$200(PushDiainfoManager.this).getString(0x7f0d00a8));
                    }
                    return false;
                }
                PushDiainfoManager.access$700(PushDiainfoManager.this, token, yid, arraylist, false, true, new PushDiainfoManager._cls16._cls1());
                return false;
            } while (true);
        }

        public volatile boolean onSuccess(Object obj)
        {
            return onSuccess((Bundle)obj);
        }

            
            {
                this$0 = final_pushdiainfomanager;
                listener = pushmanagerlistener;
                registeredRail = bundle;
                token = bearertoken;
                yid = String.this;
                super();
            }
    }

}
