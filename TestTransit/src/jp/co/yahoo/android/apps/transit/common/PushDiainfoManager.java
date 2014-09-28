// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import jp.co.yahoo.android.apps.transit.api.PushSubscribe;
import jp.co.yahoo.android.apps.transit.api.PushUpdateToken;
import jp.co.yahoo.android.apps.transit.api.PushUsersTopicid;
import jp.co.yahoo.android.apps.transit.api.RegistSearchSSO;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.common.util.CipherObject;
import jp.co.yahoo.android.apps.transit.common.util.CipherUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.db.PushSubscriptionDB;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            GcmRegistrar

public class PushDiainfoManager
{
    public static interface PushManagerListener
    {

        public abstract void onCanceled();

        public abstract void onError(int i, String s, String s1, String s2);

        public abstract void onSuccess(String s, String s1);
    }


    public static final String DIAINFO_RAIL = "diainfo_";
    public static final String DIAINFO_RAIL_ORIGIN = "diainfo_origin_";
    public static final String DIAINFO_STOPALL = "diainfo_stopAll";
    public static final String ERROR_CODE = "-1";
    public static final int TYPE_GET_PUSH = 3;
    public static final int TYPE_GET_REGID = 5;
    public static final int TYPE_GET_REGISTERED_RAIL = 1;
    public static final int TYPE_REGISTER_PUSH = 4;
    public static final int TYPE_REGISTER_RAIL = 2;
    public static final int TYPE_UPDATE_REGID = 6;
    private final String KEY_IV_YID = "i";
    private final String KEY_PUSH_TIME = "push_time";
    private final String KEY_SECRET_KEY = "s";
    private final String KEY_YID = "y";
    private final int RESULTS_DEFAULT = 2000;
    private Context context;

    public PushDiainfoManager(Context context1)
    {
        context = context1;
    }

    private void checkRegisteredAndPush(final BearerToken token, final String yid, final Bundle registeredRail, final PushManagerListener listener)
    {
        if (registeredRail != null) goto _L2; else goto _L1
_L1:
        if (listener != null)
        {
            listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
        }
_L8:
        return;
_L2:
        HashMap hashmap;
        Iterator iterator;
        hashmap = (new PushSubscriptionDB(context)).getDiainfoAll();
        iterator = hashmap.keySet().iterator();
_L6:
        boolean flag;
        boolean flag1;
        flag = iterator.hasNext();
        flag1 = false;
        if (!flag) goto _L4; else goto _L3
_L3:
        String s = (String)iterator.next();
        if (!s.startsWith("diainfo_origin_") || !((Boolean)hashmap.get(s)).booleanValue()) goto _L6; else goto _L5
_L5:
        int ai[];
        int i;
        ai = getDiainfoRailRangeId(s);
        i = 0;
_L9:
        int j = registeredRail.size();
        boolean flag2 = false;
        if (i >= j)
        {
            continue; /* Loop/switch isn't completed */
        }
        DiainfoData diainfodata = (DiainfoData)registeredRail.getSerializable(Integer.toString(i));
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
            break MISSING_BLOCK_LABEL_278;
        }
        flag2 = true;
        if (flag2) goto _L6; else goto _L7
_L7:
        flag1 = true;
_L4:
        if (!flag1)
        {
            if (listener != null)
            {
                listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                return;
            }
        } else
        {
            getPushUsersTopicid(token, yid, true, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                final PushDiainfoManager this$0;
                final PushManagerListener val$listener;
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
                            listener.onError(3, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                        } else
                        {
                            listener.onError(3, apierror.getCode(), context.getString(0x7f0d014f), apierror.getMessage());
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
                            listener.onError(3, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                        }
                        return false;
                    }
                    HashMap hashmap1 = (HashMap)bundle.getSerializable("result");
                    if (hashmap1 == null)
                    {
                        if (listener != null)
                        {
                            listener.onError(3, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                        }
                        return false;
                    }
                    ArrayList arraylist = new ArrayList();
                    Iterator iterator1 = hashmap1.keySet().iterator();
label0:
                    do
                    {
                        if (iterator1.hasNext())
                        {
                            String s1 = (String)iterator1.next();
                            if (!s1.startsWith("diainfo_origin_") || !((Boolean)hashmap1.get(s1)).booleanValue())
                            {
                                continue;
                            }
                            int ai1[] = getDiainfoRailRangeId(s1);
                            int i1 = 0;
                            do
                            {
label1:
                                {
                                    int j1 = registeredRail.size();
                                    boolean flag3 = false;
                                    if (i1 < j1)
                                    {
                                        DiainfoData diainfodata1 = (DiainfoData)registeredRail.getSerializable(Integer.toString(i1));
                                        StringBuilder stringbuilder;
                                        Object aobj[];
                                        String s2;
                                        int k1;
                                        int l1;
                                        if (TextUtils.isEmpty(diainfodata1.getRailCode()))
                                        {
                                            k1 = 0;
                                        } else
                                        {
                                            k1 = Integer.parseInt(diainfodata1.getRailCode());
                                        }
                                        if (TextUtils.isEmpty(diainfodata1.getRailRangeCode()))
                                        {
                                            l1 = 0;
                                        } else
                                        {
                                            l1 = Integer.parseInt(diainfodata1.getRailRangeCode());
                                        }
                                        if (ai1[0] != k1 || ai1[1] != l1)
                                        {
                                            break label1;
                                        }
                                        flag3 = true;
                                    }
                                    if (!flag3)
                                    {
                                        arraylist.add(s1);
                                        stringbuilder = (new StringBuilder()).append("diainfo_");
                                        aobj = new Object[2];
                                        aobj[0] = Integer.valueOf(ai1[0]);
                                        aobj[1] = Integer.valueOf(ai1[1]);
                                        s2 = stringbuilder.append(String.format("%04d%04d", aobj)).toString();
                                        if (hashmap1.containsKey(s2) && ((Boolean)hashmap1.get(s2)).booleanValue())
                                        {
                                            arraylist.add(s2);
                                        }
                                    }
                                    continue label0;
                                }
                                i1++;
                            } while (true);
                        }
                        if (arraylist.isEmpty())
                        {
                            if (listener != null)
                            {
                                listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                            }
                            return false;
                        }
                        registerPushSubscribe(token, yid, arraylist, false, true, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                            final _cls16 this$1;

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
                                        listener.onError(4, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                                    } else
                                    {
                                        listener.onError(4, apierror.getCode(), context.getString(0x7f0d014f), apierror.getMessage());
                                    }
                                }
                                return false;
                            }

                            public boolean onSuccess(Bundle bundle)
                            {
                                if (listener != null)
                                {
                                    listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                                }
                                return false;
                            }

                            public volatile boolean onSuccess(Object obj)
                            {
                                return onSuccess((Bundle)obj);
                            }

            
            {
                this$1 = _cls16.this;
                super();
            }
                        });
                        return false;
                    } while (true);
                }

                public volatile boolean onSuccess(Object obj)
                {
                    return onSuccess((Bundle)obj);
                }

            
            {
                this$0 = PushDiainfoManager.this;
                listener = pushmanagerlistener;
                registeredRail = bundle;
                token = bearertoken;
                yid = s;
                super();
            }
            });
            return;
        }
          goto _L8
        i++;
          goto _L9
    }

    private APIError createAPIError(String s, String s1)
    {
        APIError apierror = new APIError();
        apierror.setCode(s);
        apierror.setMessage(s1);
        return apierror;
    }

    private APIError createDefaultAPIError()
    {
        APIError apierror = new APIError();
        apierror.setCode("500");
        apierror.setMessage(context.getString(0x7f0d0102));
        return apierror;
    }

    private void getAndRegisterPush(final BearerToken token, final DiainfoData diainfo, final boolean isRegisterRail, final PushManagerListener listener)
    {
        getPushUsersTopicid(token, TransitUtil.getYID(context), true, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

            final PushDiainfoManager this$0;
            final DiainfoData val$diainfo;
            final boolean val$isRegisterRail;
            final PushManagerListener val$listener;
            final BearerToken val$token;

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
                        listener.onError(3, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                    } else
                    {
                        listener.onError(3, apierror.getCode(), context.getString(0x7f0d014f), apierror.getMessage());
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
                        listener.onError(3, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                    }
                } else
                {
                    registerPush(token, bundle, diainfo, isRegisterRail, listener);
                }
                return false;
            }

            public volatile boolean onSuccess(Object obj)
            {
                return onSuccess((Bundle)obj);
            }

            
            {
                this$0 = PushDiainfoManager.this;
                listener = pushmanagerlistener;
                token = bearertoken;
                diainfo = diainfodata;
                isRegisterRail = flag;
                super();
            }
        });
    }

    private String getDiainfoId(DiainfoData diainfodata)
    {
        int i;
        int j;
        Object aobj[];
        if (TextUtils.isEmpty(diainfodata.getRailCode()))
        {
            i = 0;
        } else
        {
            i = Integer.parseInt(diainfodata.getRailCode());
        }
        if (TextUtils.isEmpty(diainfodata.getRailRangeCode()))
        {
            j = 0;
        } else
        {
            j = Integer.parseInt(diainfodata.getRailRangeCode());
        }
        aobj = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(j);
        return String.format("%04d%04d", aobj);
    }

    private int[] getDiainfoRailRangeId(String s)
    {
        String s1 = s.substring(-8 + s.length(), -4 + s.length());
        String s2 = s.substring(-4 + s.length());
        int ai[] = new int[2];
        ai[0] = Integer.parseInt(s1);
        ai[1] = Integer.parseInt(s2);
        return ai;
    }

    private void getPushUsersTopicid(BearerToken bearertoken, String s, String s1, final boolean saveDb, final jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener listener)
    {
        PushUsersTopicid pushuserstopicid = new PushUsersTopicid(context, bearertoken, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

            final PushDiainfoManager this$0;
            final jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener val$listener;
            final boolean val$saveDb;

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
                    listener.onError(apierror);
                }
                return false;
            }

            public boolean onSuccess(Bundle bundle)
            {
                if (saveDb && bundle != null && bundle.containsKey("result"))
                {
                    HashMap hashmap = (HashMap)bundle.getSerializable("result");
                    (new PushSubscriptionDB(context)).updateDiainfo(hashmap);
                }
                if (listener != null)
                {
                    listener.onSuccess(bundle);
                }
                return false;
            }

            public volatile boolean onSuccess(Object obj)
            {
                return onSuccess((Bundle)obj);
            }

            
            {
                this$0 = PushDiainfoManager.this;
                saveDb = flag;
                listener = authapilistener;
                super();
            }
        });
        pushuserstopicid.m_Consumeruri = s;
        pushuserstopicid.m_UserId = s1;
        pushuserstopicid.m_Result = 2000;
        pushuserstopicid.requestAsync(false);
    }

    private ArrayList getTopicidList(Bundle bundle, String s, String s1, String s2, boolean flag)
    {
        ArrayList arraylist = new ArrayList();
        HashMap hashmap;
        if (bundle != null)
        {
            if ((hashmap = (HashMap)bundle.getSerializable("result")) != null)
            {
                Iterator iterator = hashmap.keySet().iterator();
                while (iterator.hasNext()) 
                {
                    String s3 = (String)iterator.next();
                    if (s3.startsWith(s) && flag == ((Boolean)hashmap.get(s3)).booleanValue() && (TextUtils.isEmpty(s1) || !s3.startsWith(s1)) && (TextUtils.isEmpty(s2) || !s3.startsWith(s2)))
                    {
                        arraylist.add(s3);
                    }
                }
            }
        }
        return arraylist;
    }

    private void registerPush(BearerToken bearertoken, Bundle bundle, DiainfoData diainfodata, final boolean isRegisterRail, final PushManagerListener listener)
    {
        final boolean stopAllFlag;
        boolean aflag[];
        stopAllFlag = getDiainfoStopAllFlag(bundle);
        aflag = getDiainfoFlag(bundle, diainfodata);
        if (!stopAllFlag) goto _L2; else goto _L1
_L1:
        ArrayList arraylist;
        boolean flag;
        if (aflag[1])
        {
            arraylist = null;
            flag = false;
            if (listener != null)
            {
                listener.onError(4, "-1", context.getString(0x7f0d0155), context.getString(0x7f0d0139));
            }
        } else
        {
            arraylist = new ArrayList();
            String s1 = getDiainfoId(diainfodata);
            arraylist.add((new StringBuilder()).append("diainfo_origin_").append(s1).toString());
            flag = true;
        }
_L4:
        if (arraylist != null)
        {
            registerPushSubscribe(bearertoken, TransitUtil.getYID(context), arraylist, flag, true, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                final PushDiainfoManager this$0;
                final boolean val$isRegisterRail;
                final PushManagerListener val$listener;
                final boolean val$stopAllFlag;

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
                            listener.onError(4, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                        } else
                        {
                            listener.onError(4, apierror.getCode(), context.getString(0x7f0d014f), apierror.getMessage());
                        }
                    }
                    return false;
                }

                public boolean onSuccess(Bundle bundle1)
                {
                    if (listener != null)
                    {
                        String s2;
                        if (isRegisterRail)
                        {
                            if (stopAllFlag)
                            {
                                s2 = context.getString(0x7f0d00a6);
                            } else
                            {
                                s2 = context.getString(0x7f0d00a7);
                            }
                        } else
                        if (stopAllFlag)
                        {
                            s2 = context.getString(0x7f0d00a4);
                        } else
                        {
                            s2 = context.getString(0x7f0d00a5);
                        }
                        listener.onSuccess(context.getString(0x7f0d00b1), s2);
                    }
                    return false;
                }

                public volatile boolean onSuccess(Object obj)
                {
                    return onSuccess((Bundle)obj);
                }

            
            {
                this$0 = PushDiainfoManager.this;
                listener = pushmanagerlistener;
                isRegisterRail = flag;
                stopAllFlag = flag1;
                super();
            }
            });
        }
        return;
_L2:
        if (aflag[0])
        {
            arraylist = null;
            flag = false;
            if (listener != null)
            {
                listener.onError(4, "-1", context.getString(0x7f0d0155), context.getString(0x7f0d013a));
                arraylist = null;
                flag = false;
            }
        } else
        {
            arraylist = new ArrayList();
            String s = getDiainfoId(diainfodata);
            arraylist.add((new StringBuilder()).append("diainfo_").append(s).toString());
            if (!aflag[1])
            {
                arraylist.add((new StringBuilder()).append("diainfo_origin_").append(s).toString());
            }
            flag = true;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void registerPushMulti(final BearerToken token, final String yid, boolean flag, ArrayList arraylist, ArrayList arraylist1, final PushManagerListener listener)
    {
        HashMap hashmap;
        boolean flag1;
        ArrayList arraylist2;
        final ArrayList offTopicids;
        hashmap = (new PushSubscriptionDB(context)).getDiainfoAll();
        Iterator iterator1;
        String s4;
        if (hashmap.containsKey("diainfo_stopAll"))
        {
            flag1 = ((Boolean)hashmap.get("diainfo_stopAll")).booleanValue();
        } else
        {
            flag1 = false;
        }
        arraylist2 = new ArrayList();
        offTopicids = new ArrayList();
        if (!flag || !flag1) goto _L2; else goto _L1
_L1:
        offTopicids.add("diainfo_stopAll");
_L13:
        if (!flag) goto _L4; else goto _L3
_L3:
        if (flag1)
        {
            offTopicids.add("diainfo_stopAll");
        }
        iterator1 = arraylist.iterator();
_L6:
        if (!iterator1.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        s4 = getDiainfoId((DiainfoData)iterator1.next());
        String s5 = (new StringBuilder()).append("diainfo_").append(s4).toString();
        String s6 = (new StringBuilder()).append("diainfo_origin_").append(s4).toString();
        if (hashmap.containsKey(s5))
        {
            if (!((Boolean)hashmap.get(s5)).booleanValue())
            {
                arraylist2.add(s5);
            }
        } else
        {
            arraylist2.add(s5);
        }
        if (hashmap.containsKey(s6))
        {
            if (!((Boolean)hashmap.get(s6)).booleanValue())
            {
                arraylist2.add(s6);
            }
        } else
        {
            arraylist2.add(s6);
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (!flag && !flag1)
        {
            arraylist2.add("diainfo_stopAll");
        }
        continue; /* Loop/switch isn't completed */
        if (true) goto _L6; else goto _L5
_L5:
        Iterator iterator2 = arraylist1.iterator();
        do
        {
            if (!iterator2.hasNext())
            {
                break;
            }
            String s1 = getDiainfoId((DiainfoData)iterator2.next());
            String s2 = (new StringBuilder()).append("diainfo_").append(s1).toString();
            String s3 = (new StringBuilder()).append("diainfo_origin_").append(s1).toString();
            if (hashmap.containsKey(s2) && ((Boolean)hashmap.get(s2)).booleanValue())
            {
                offTopicids.add(s2);
            }
            if (hashmap.containsKey(s3) && ((Boolean)hashmap.get(s3)).booleanValue())
            {
                offTopicids.add(s3);
            }
        } while (true);
          goto _L7
_L4:
        if (!flag1)
        {
            arraylist2.add("diainfo_stopAll");
        }
        Iterator iterator = hashmap.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s = (String)iterator.next();
            if (s.startsWith("diainfo_") && !s.startsWith("diainfo_origin_") && !s.startsWith("diainfo_stopAll") && ((Boolean)hashmap.get(s)).booleanValue())
            {
                offTopicids.add(s);
            }
        } while (true);
_L7:
        if (arraylist2.isEmpty()) goto _L9; else goto _L8
_L8:
        registerPushSubscribe(token, yid, arraylist2, true, true, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

            final PushDiainfoManager this$0;
            final PushManagerListener val$listener;
            final ArrayList val$offTopicids;
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
                    listener.onError(4, apierror.getCode(), context.getString(0x7f0d014f), apierror.getMessage());
                }
                return false;
            }

            public boolean onSuccess(Bundle bundle)
            {
                if (offTopicids.isEmpty()) goto _L2; else goto _L1
_L1:
                registerPushMultiOff(token, yid, offTopicids, listener);
_L4:
                return false;
_L2:
                if (listener != null)
                {
                    listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                }
                if (true) goto _L4; else goto _L3
_L3:
            }

            public volatile boolean onSuccess(Object obj)
            {
                return onSuccess((Bundle)obj);
            }

            
            {
                this$0 = PushDiainfoManager.this;
                offTopicids = arraylist;
                token = bearertoken;
                yid = s;
                listener = pushmanagerlistener;
                super();
            }
        });
_L11:
        return;
_L9:
        if (!offTopicids.isEmpty())
        {
            registerPushMultiOff(token, yid, offTopicids, listener);
            return;
        }
        if (listener == null) goto _L11; else goto _L10
_L10:
        listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
        return;
        if (true) goto _L13; else goto _L12
_L12:
    }

    private void registerPushMultiOff(BearerToken bearertoken, String s, ArrayList arraylist, final PushManagerListener listener)
    {
        registerPushSubscribe(bearertoken, s, arraylist, false, true, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

            final PushDiainfoManager this$0;
            final PushManagerListener val$listener;

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
                    listener.onError(4, apierror.getCode(), context.getString(0x7f0d014f), apierror.getMessage());
                }
                return false;
            }

            public boolean onSuccess(Bundle bundle)
            {
                if (listener != null)
                {
                    listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                }
                return false;
            }

            public volatile boolean onSuccess(Object obj)
            {
                return onSuccess((Bundle)obj);
            }

            
            {
                this$0 = PushDiainfoManager.this;
                listener = pushmanagerlistener;
                super();
            }
        });
    }

    private void registerPushSubscribe(BearerToken bearertoken, String s, String s1, final ArrayList topicid, final boolean subflag, final boolean saveDb, final jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener listener)
    {
        PushSubscribe pushsubscribe = new PushSubscribe(context, bearertoken, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

            final PushDiainfoManager this$0;
            final jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener val$listener;
            final boolean val$saveDb;
            final boolean val$subflag;
            final ArrayList val$topicid;

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
                    listener.onError(apierror);
                }
                return false;
            }

            public boolean onSuccess(Bundle bundle)
            {
                if (saveDb)
                {
                    HashMap hashmap = new HashMap();
                    for (Iterator iterator = topicid.iterator(); iterator.hasNext(); hashmap.put((String)iterator.next(), Boolean.valueOf(subflag))) { }
                    (new PushSubscriptionDB(context)).updateDiainfo(hashmap);
                }
                if (listener != null)
                {
                    listener.onSuccess(bundle);
                }
                return false;
            }

            public volatile boolean onSuccess(Object obj)
            {
                return onSuccess((Bundle)obj);
            }

            
            {
                this$0 = PushDiainfoManager.this;
                saveDb = flag;
                topicid = arraylist;
                subflag = flag1;
                listener = authapilistener;
                super();
            }
        });
        pushsubscribe.m_Consumeruri = s;
        pushsubscribe.m_Userid = s1;
        pushsubscribe.m_Topicid = topicid;
        pushsubscribe.m_Subflag = subflag;
        pushsubscribe.requestAsync(false);
    }

    private void registerPushSubscribe(final BearerToken token, final String yid, final ArrayList topicid, final boolean subflag, final boolean saveDb, final jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener listener)
    {
        if (token == null)
        {
            if (listener != null)
            {
                listener.onError(createDefaultAPIError());
            }
            return;
        }
        final String registrationId = GcmRegistrar.getRegistrationId(context);
        if (TextUtils.isEmpty(registrationId))
        {
            GcmRegistrar.registerInBackground(context, new GcmRegistrar.GcmRegistrarListener() {

                final PushDiainfoManager this$0;
                final jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener val$listener;
                final boolean val$saveDb;
                final boolean val$subflag;
                final BearerToken val$token;
                final ArrayList val$topicid;
                final String val$yid;

                public void onRegistered(String s)
                {
                    if (TextUtils.isEmpty(s))
                    {
                        if (listener != null)
                        {
                            listener.onError(createDefaultAPIError());
                        }
                        return;
                    } else
                    {
                        registerPushSubscribe(token, s, yid, topicid, subflag, saveDb, listener);
                        return;
                    }
                }

            
            {
                this$0 = PushDiainfoManager.this;
                listener = authapilistener;
                token = bearertoken;
                yid = s;
                topicid = arraylist;
                subflag = flag;
                saveDb = flag1;
                super();
            }
            });
            return;
        } else
        {
            checkOldRegistrationId(registrationId, new PushManagerListener() {

                final PushDiainfoManager this$0;
                final jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener val$listener;
                final String val$registrationId;
                final boolean val$saveDb;
                final boolean val$subflag;
                final BearerToken val$token;
                final ArrayList val$topicid;
                final String val$yid;

                public void onCanceled()
                {
                    if (listener != null)
                    {
                        listener.onCanceled();
                    }
                }

                public void onError(int i, String s, String s1, String s2)
                {
                    if (listener != null)
                    {
                        listener.onError(createAPIError(s, s2));
                    }
                }

                public void onSuccess(String s, String s1)
                {
                    registerPushSubscribe(token, registrationId, yid, topicid, subflag, saveDb, listener);
                }

            
            {
                this$0 = PushDiainfoManager.this;
                token = bearertoken;
                registrationId = s;
                yid = s1;
                topicid = arraylist;
                subflag = flag;
                saveDb = flag1;
                listener = authapilistener;
                super();
            }
            });
            return;
        }
    }

    private void registerRail(final BearerToken token, final DiainfoData diainfo, final PushManagerListener listener)
    {
        ArrayList arraylist = new ArrayList(1);
        Bundle bundle = new Bundle();
        bundle.putString("Name", diainfo.getRailName());
        bundle.putString("RailCode", diainfo.getRailCode());
        bundle.putString("RailRangeCode", diainfo.getRailRangeCode());
        arraylist.add(bundle);
        RegistSearchSSO registsearchsso = new RegistSearchSSO(context, token, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

            final PushDiainfoManager this$0;
            final DiainfoData val$diainfo;
            final PushManagerListener val$listener;
            final BearerToken val$token;

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
                String s = apierror.getCode();
                if (s == null || !s.equals("40002")) goto _L2; else goto _L1
_L1:
                getAndRegisterPush(token, diainfo, true, listener);
_L4:
                return false;
_L2:
                if (listener != null)
                {
                    listener.onError(2, apierror.getCode(), context.getString(0x7f0d014f), apierror.getMessage());
                }
                if (true) goto _L4; else goto _L3
_L3:
            }

            public boolean onSuccess(Bundle bundle1)
            {
                getAndRegisterPush(token, diainfo, true, listener);
                return false;
            }

            public volatile boolean onSuccess(Object obj)
            {
                return onSuccess((Bundle)obj);
            }

            
            {
                this$0 = PushDiainfoManager.this;
                token = bearertoken;
                diainfo = diainfodata;
                listener = pushmanagerlistener;
                super();
            }
        });
        registsearchsso.setSearchType(context.getString(0x7f0d01d5));
        registsearchsso.setPostData(arraylist);
        registsearchsso.setMethod("POST");
        registsearchsso.requestAsync(false);
    }

    private void updatePushToken(BearerToken bearertoken, String s, String s1, final jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener listener)
    {
        if (bearertoken == null)
        {
            if (listener != null)
            {
                listener.onError(createDefaultAPIError());
            }
        } else
        if (TextUtils.isEmpty(s) || TextUtils.isEmpty(s1))
        {
            if (listener != null)
            {
                listener.onError(createDefaultAPIError());
                return;
            }
        } else
        {
            PushUpdateToken pushupdatetoken = new PushUpdateToken(context, bearertoken, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                final PushDiainfoManager this$0;
                final jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener val$listener;

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
                        listener.onError(apierror);
                    }
                    return false;
                }

                public boolean onSuccess(Bundle bundle)
                {
                    if (listener != null)
                    {
                        listener.onSuccess(bundle);
                    }
                    return false;
                }

                public volatile boolean onSuccess(Object obj)
                {
                    return onSuccess((Bundle)obj);
                }

            
            {
                this$0 = PushDiainfoManager.this;
                listener = authapilistener;
                super();
            }
            });
            pushupdatetoken.m_Consumeruri = s1;
            pushupdatetoken.m_Oldconsumeruri = s;
            pushupdatetoken.requestAsync(false);
            return;
        }
    }

    private void updateRegistrationId(final String registrationIdOld, String s, final PushManagerListener listener)
    {
        BearerToken bearertoken = TransitUtil.getAccessToken(context);
        if (bearertoken == null)
        {
            GcmRegistrar.storeRegistrationIdOld(context, registrationIdOld);
            if (listener != null)
            {
                listener.onError(0, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
            }
            return;
        } else
        {
            updatePushToken(bearertoken, registrationIdOld, s, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                final PushDiainfoManager this$0;
                final PushManagerListener val$listener;
                final String val$registrationIdOld;

                public boolean onCanceled()
                {
                    GcmRegistrar.storeRegistrationIdOld(context, registrationIdOld);
                    if (listener != null)
                    {
                        listener.onCanceled();
                    }
                    return false;
                }

                public boolean onError(APIError apierror)
                {
                    GcmRegistrar.storeRegistrationIdOld(context, registrationIdOld);
                    if (listener != null)
                    {
                        listener.onError(6, apierror.getCode(), context.getString(0x7f0d014f), apierror.getMessage());
                    }
                    return false;
                }

                public boolean onSuccess(Bundle bundle)
                {
                    GcmRegistrar.clearRegistrationIdOld(context);
                    if (listener != null)
                    {
                        listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                    }
                    return false;
                }

                public volatile boolean onSuccess(Object obj)
                {
                    return onSuccess((Bundle)obj);
                }

            
            {
                this$0 = PushDiainfoManager.this;
                listener = pushmanagerlistener;
                registrationIdOld = s;
                super();
            }
            });
            return;
        }
    }

    public void checkAppVersionup(final PushManagerListener listener)
    {
        final String registrationId = GcmRegistrar.getRegistrationId(context);
        if (TextUtils.isEmpty(registrationId))
        {
            if (listener != null)
            {
                listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
            }
            return;
        }
        if (!GcmRegistrar.checkUpdateAppVersion(context))
        {
            checkOldRegistrationId(registrationId, listener);
            return;
        } else
        {
            GcmRegistrar.registerInBackground(context, new GcmRegistrar.GcmRegistrarListener() {

                final PushDiainfoManager this$0;
                final PushManagerListener val$listener;
                final String val$registrationId;

                public void onRegistered(String s)
                {
                    if (TextUtils.isEmpty(s))
                    {
                        if (listener != null)
                        {
                            listener.onError(5, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                        }
                    } else
                    if (s.equals(registrationId))
                    {
                        GcmRegistrar.clearRegistrationIdOld(context);
                        if (listener != null)
                        {
                            listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                            return;
                        }
                    } else
                    {
                        updateRegistrationId(registrationId, s, listener);
                        return;
                    }
                }

            
            {
                this$0 = PushDiainfoManager.this;
                listener = pushmanagerlistener;
                registrationId = s;
                super();
            }
            });
            return;
        }
    }

    public void checkDeletedAndPush(final BearerToken token, final String yid, final ArrayList deleteDiainfo, final PushManagerListener listener)
    {
        if (deleteDiainfo != null) goto _L2; else goto _L1
_L1:
        if (listener != null)
        {
            listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
        }
_L7:
        return;
_L2:
        HashMap hashmap;
        Iterator iterator;
        hashmap = (new PushSubscriptionDB(context)).getDiainfoAll();
        iterator = deleteDiainfo.iterator();
_L9:
        boolean flag;
        boolean flag1;
        flag = iterator.hasNext();
        flag1 = false;
        if (!flag) goto _L4; else goto _L3
_L3:
        String s;
        String s1;
        s = getDiainfoId((DiainfoData)iterator.next());
        s1 = (new StringBuilder()).append("diainfo_").append(s).toString();
        if (!hashmap.containsKey(s1) || !((Boolean)hashmap.get(s1)).booleanValue()) goto _L6; else goto _L5
_L5:
        flag1 = true;
_L4:
        String s2;
        if (!flag1)
        {
            if (listener != null)
            {
                listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                return;
            }
        } else
        {
            getPushUsersTopicid(token, yid, true, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                final PushDiainfoManager this$0;
                final ArrayList val$deleteDiainfo;
                final PushManagerListener val$listener;
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
                            listener.onError(3, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                        } else
                        {
                            listener.onError(3, apierror.getCode(), context.getString(0x7f0d014f), apierror.getMessage());
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
                        listener.onError(3, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                    }
_L4:
                    return false;
_L2:
                    HashMap hashmap1;
                    hashmap1 = (HashMap)bundle.getSerializable("result");
                    if (hashmap1 != null)
                    {
                        break; /* Loop/switch isn't completed */
                    }
                    if (listener != null)
                    {
                        listener.onError(3, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                        return false;
                    }
                    if (true) goto _L4; else goto _L3
_L3:
                    ArrayList arraylist = new ArrayList();
                    Iterator iterator1 = deleteDiainfo.iterator();
                    do
                    {
                        if (!iterator1.hasNext())
                        {
                            break;
                        }
                        DiainfoData diainfodata = (DiainfoData)iterator1.next();
                        String s3 = getDiainfoId(diainfodata);
                        String s4 = (new StringBuilder()).append("diainfo_").append(s3).toString();
                        if (hashmap1.containsKey(s4) && ((Boolean)hashmap1.get(s4)).booleanValue())
                        {
                            arraylist.add(s4);
                        }
                        String s5 = (new StringBuilder()).append("diainfo_origin_").append(s3).toString();
                        if (hashmap1.containsKey(s5) && ((Boolean)hashmap1.get(s5)).booleanValue())
                        {
                            arraylist.add(s5);
                        }
                    } while (true);
                    if (arraylist.isEmpty())
                    {
                        if (listener != null)
                        {
                            listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                            return false;
                        }
                    } else
                    {
                        registerPushSubscribe(token, yid, arraylist, false, true, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                            final _cls17 this$1;

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
                                        listener.onError(4, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                                    } else
                                    {
                                        listener.onError(4, apierror.getCode(), context.getString(0x7f0d014f), apierror.getMessage());
                                    }
                                }
                                return false;
                            }

                            public boolean onSuccess(Bundle bundle)
                            {
                                if (listener != null)
                                {
                                    listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                                }
                                return false;
                            }

                            public volatile boolean onSuccess(Object obj)
                            {
                                return onSuccess((Bundle)obj);
                            }

            
            {
                this$1 = _cls17.this;
                super();
            }
                        });
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
                this$0 = PushDiainfoManager.this;
                listener = pushmanagerlistener;
                deleteDiainfo = arraylist;
                token = bearertoken;
                yid = s;
                super();
            }
            });
            return;
        }
          goto _L7
_L6:
        s2 = (new StringBuilder()).append("diainfo_origin_").append(s).toString();
        if (!hashmap.containsKey(s2) || !((Boolean)hashmap.get(s2)).booleanValue()) goto _L9; else goto _L8
_L8:
        flag1 = true;
          goto _L4
    }

    public void checkOldRegistrationId(String s, PushManagerListener pushmanagerlistener)
    {
        String s1 = GcmRegistrar.getRegistrationIdOld(context);
        if (!TextUtils.isEmpty(s1) && !s1.equals(s))
        {
            updateRegistrationId(s1, s, pushmanagerlistener);
        } else
        {
            if (!TextUtils.isEmpty(s1))
            {
                GcmRegistrar.clearRegistrationIdOld(context);
            }
            if (pushmanagerlistener != null)
            {
                pushmanagerlistener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                return;
            }
        }
    }

    public boolean checkPushEnable(Activity activity)
    {
        return GcmRegistrar.checkPlayServices(activity);
    }

    public void checkRegisteredRail(final Bundle registeredRail, final PushManagerListener listener)
    {
        final BearerToken token = TransitUtil.getAccessToken(context);
        if (token == null)
        {
            if (listener != null)
            {
                listener.onError(0, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
            }
        } else
        {
            final String yid = TransitUtil.getYID(context);
            if (TextUtils.isEmpty(yid))
            {
                if (listener != null)
                {
                    listener.onError(0, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                    return;
                }
            } else
            {
                String s = GcmRegistrar.getRegistrationId(context);
                if (TextUtils.isEmpty(s))
                {
                    GcmRegistrar.registerInBackground(context, new GcmRegistrar.GcmRegistrarListener() {

                        final PushDiainfoManager this$0;
                        final PushManagerListener val$listener;
                        final Bundle val$registeredRail;
                        final BearerToken val$token;
                        final String val$yid;

                        public void onRegistered(String s1)
                        {
                            if (TextUtils.isEmpty(s1))
                            {
                                if (listener != null)
                                {
                                    listener.onError(5, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                                }
                                return;
                            } else
                            {
                                checkRegisteredAndPush(token, yid, registeredRail, listener);
                                return;
                            }
                        }

            
            {
                this$0 = PushDiainfoManager.this;
                listener = pushmanagerlistener;
                token = bearertoken;
                yid = s;
                registeredRail = bundle;
                super();
            }
                    });
                    return;
                } else
                {
                    checkOldRegistrationId(s, new PushManagerListener() {

                        final PushDiainfoManager this$0;
                        final PushManagerListener val$listener;
                        final Bundle val$registeredRail;
                        final BearerToken val$token;
                        final String val$yid;

                        public void onCanceled()
                        {
                            if (listener != null)
                            {
                                listener.onCanceled();
                            }
                        }

                        public void onError(int i, String s1, String s2, String s3)
                        {
                            if (listener != null)
                            {
                                listener.onError(i, s1, s2, s3);
                            }
                        }

                        public void onSuccess(String s1, String s2)
                        {
                            if (!setRailPushForYid(token, new PushManagerListener() {

                        final _cls15 this$1;

                        public void onCanceled()
                        {
                        }

                        public void onError(int i, String s, String s1, String s2)
                        {
                            if (listener != null)
                            {
                                listener.onError(i, s, s1, s2);
                            }
                        }

                        public void onSuccess(String s, String s1)
                        {
                            checkRegisteredAndPush(token, yid, registeredRail, listener);
                        }

            
            {
                this$1 = _cls15.this;
                super();
            }
                    }))
                            {
                                checkRegisteredAndPush(token, yid, registeredRail, listener);
                            }
                        }

            
            {
                this$0 = PushDiainfoManager.this;
                token = bearertoken;
                yid = s;
                registeredRail = bundle;
                listener = pushmanagerlistener;
                super();
            }
                    });
                    return;
                }
            }
        }
    }

    public void clearYid()
    {
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences(context.getString(0x7f0d04df), 0).edit();
        editor.remove("i");
        editor.remove("y");
        editor.commit();
    }

    public boolean equalsDiainfoFlag(Bundle bundle, Bundle bundle1)
    {
_L2:
        return false;
        if (bundle == null || bundle1 == null || getDiainfoStopAllFlag(bundle) != getDiainfoStopAllFlag(bundle1)) goto _L2; else goto _L1
_L1:
        HashMap hashmap = (HashMap)bundle.getSerializable("result");
        HashMap hashmap1 = (HashMap)bundle1.getSerializable("result");
        if (hashmap != null && hashmap1 != null)
        {
            Set set;
            Iterator iterator;
            if (hashmap.size() > hashmap1.size())
            {
                set = hashmap.keySet();
            } else
            {
                set = hashmap1.keySet();
            }
            iterator = set.iterator();
label0:
            do
            {
label1:
                {
                    String s;
                    do
                    {
                        if (!iterator.hasNext())
                        {
                            break label1;
                        }
                        s = (String)iterator.next();
                    } while (!s.startsWith("diainfo_origin_"));
                    int ai[] = getDiainfoRailRangeId(s);
                    DiainfoData diainfodata = new DiainfoData();
                    diainfodata.setRailcode(String.valueOf(ai[0]));
                    diainfodata.setRailRangeCode(String.valueOf(ai[1]));
                    boolean aflag[] = getDiainfoFlag(bundle, diainfodata);
                    boolean aflag1[] = getDiainfoFlag(bundle1, diainfodata);
                    if (aflag[0] != aflag1[0])
                    {
                        break; /* Loop/switch isn't completed */
                    }
                    if (aflag[1] != aflag1[1])
                    {
                        return false;
                    }
                }
            } while (true);
        }
        if (true) goto _L2; else goto _L3
_L3:
        return true;
    }

    public boolean[] getDiainfoFlag(Bundle bundle, DiainfoData diainfodata)
    {
        boolean aflag[] = {
            0, 0
        };
        String s = getDiainfoId(diainfodata);
        String s1 = (new StringBuilder()).append("diainfo_").append(s).toString();
        String s2 = (new StringBuilder()).append("diainfo_origin_").append(s).toString();
        HashMap hashmap;
        if (bundle != null)
        {
            if ((hashmap = (HashMap)bundle.getSerializable("result")) != null)
            {
                if (hashmap.containsKey(s1))
                {
                    aflag[0] = ((Boolean)hashmap.get(s1)).booleanValue();
                }
                if (hashmap.containsKey(s2))
                {
                    aflag[1] = ((Boolean)hashmap.get(s2)).booleanValue();
                    return aflag;
                }
            }
        }
        return aflag;
    }

    public boolean getDiainfoStopAllFlag(Bundle bundle)
    {
        HashMap hashmap;
        if (bundle != null)
        {
            if ((hashmap = (HashMap)bundle.getSerializable("result")) != null && hashmap.containsKey("diainfo_stopAll"))
            {
                return ((Boolean)hashmap.get("diainfo_stopAll")).booleanValue();
            }
        }
        return false;
    }

    public void getPushUsersTopicid(final BearerToken token, final String yid, final boolean saveDb, final jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener listener)
    {
        if (token == null)
        {
            if (listener != null)
            {
                listener.onError(createDefaultAPIError());
            }
            return;
        }
        final String registrationId = GcmRegistrar.getRegistrationId(context);
        if (TextUtils.isEmpty(registrationId))
        {
            GcmRegistrar.registerInBackground(context, new GcmRegistrar.GcmRegistrarListener() {

                final PushDiainfoManager this$0;
                final jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener val$listener;
                final boolean val$saveDb;
                final BearerToken val$token;
                final String val$yid;

                public void onRegistered(String s)
                {
                    if (TextUtils.isEmpty(s))
                    {
                        if (listener != null)
                        {
                            listener.onError(createDefaultAPIError());
                        }
                        return;
                    } else
                    {
                        getPushUsersTopicid(token, s, yid, saveDb, listener);
                        return;
                    }
                }

            
            {
                this$0 = PushDiainfoManager.this;
                listener = authapilistener;
                token = bearertoken;
                yid = s;
                saveDb = flag;
                super();
            }
            });
            return;
        } else
        {
            checkOldRegistrationId(registrationId, new PushManagerListener() {

                final PushDiainfoManager this$0;
                final jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener val$listener;
                final String val$registrationId;
                final boolean val$saveDb;
                final BearerToken val$token;
                final String val$yid;

                public void onCanceled()
                {
                    if (listener != null)
                    {
                        listener.onCanceled();
                    }
                }

                public void onError(int i, String s, String s1, String s2)
                {
                    if (listener != null)
                    {
                        listener.onError(createAPIError(s, s2));
                    }
                }

                public void onSuccess(String s, String s1)
                {
                    getPushUsersTopicid(token, registrationId, yid, saveDb, listener);
                }

            
            {
                this$0 = PushDiainfoManager.this;
                token = bearertoken;
                registrationId = s;
                yid = s1;
                saveDb = flag;
                listener = authapilistener;
                super();
            }
            });
            return;
        }
    }

    public int loadPushTime()
    {
        return context.getSharedPreferences(context.getString(0x7f0d04df), 0).getInt("push_time", context.getResources().getInteger(0x7f0c0035));
    }

    public byte[] loadSecretKey()
    {
        SharedPreferences sharedpreferences = context.getSharedPreferences(context.getString(0x7f0d04df), 0);
        String s = sharedpreferences.getString("s", null);
        if (s == null)
        {
            byte abyte0[];
            try
            {
                abyte0 = CipherUtil.generateSecretKey();
                android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("s", Base64.encodeToString(abyte0, 0));
                editor.commit();
            }
            catch (Exception exception)
            {
                return null;
            }
            return abyte0;
        } else
        {
            return Base64.decode(s, 0);
        }
    }

    public String loadYid()
    {
        SharedPreferences sharedpreferences = context.getSharedPreferences(context.getString(0x7f0d04df), 0);
        byte abyte0[] = loadSecretKey();
        if (abyte0 == null)
        {
            return null;
        }
        String s2;
        String s = sharedpreferences.getString("i", null);
        String s1 = sharedpreferences.getString("y", null);
        if (TextUtils.isEmpty(s) || TextUtils.isEmpty(s1))
        {
            break MISSING_BLOCK_LABEL_104;
        }
        s2 = new String(CipherUtil.decrypt(abyte0, new CipherObject(Base64.decode(s, 0), Base64.decode(s1, 0))));
        return s2;
        Exception exception;
        exception;
        return null;
    }

    public void registerPushMulti(final BearerToken token, final boolean allFlag, final ArrayList onDiainfo, final ArrayList offDiainfo, final PushManagerListener listener)
    {
        if (token == null)
        {
            if (listener != null)
            {
                listener.onError(0, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
            }
        } else
        {
            final String yid = TransitUtil.getYID(context);
            if (TextUtils.isEmpty(yid))
            {
                if (listener != null)
                {
                    listener.onError(0, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                    return;
                }
            } else
            if (TextUtils.isEmpty(GcmRegistrar.getRegistrationId(context)))
            {
                GcmRegistrar.registerInBackground(context, new GcmRegistrar.GcmRegistrarListener() {

                    final PushDiainfoManager this$0;
                    final boolean val$allFlag;
                    final PushManagerListener val$listener;
                    final ArrayList val$offDiainfo;
                    final ArrayList val$onDiainfo;
                    final BearerToken val$token;
                    final String val$yid;

                    public void onRegistered(String s)
                    {
                        if (TextUtils.isEmpty(s))
                        {
                            if (listener != null)
                            {
                                listener.onError(5, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                            }
                            return;
                        } else
                        {
                            registerPushMulti(token, yid, allFlag, onDiainfo, offDiainfo, listener);
                            return;
                        }
                    }

            
            {
                this$0 = PushDiainfoManager.this;
                listener = pushmanagerlistener;
                token = bearertoken;
                yid = s;
                allFlag = flag;
                onDiainfo = arraylist;
                offDiainfo = arraylist1;
                super();
            }
                });
                return;
            } else
            {
                registerPushMulti(token, yid, allFlag, onDiainfo, offDiainfo, listener);
                return;
            }
        }
    }

    public void registerPushSingle(final BearerToken token, final DiainfoData diainfo, final PushManagerListener listener)
    {
        RegistSearchSSO registsearchsso = new RegistSearchSSO(context, token, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

            final PushDiainfoManager this$0;
            final DiainfoData val$diainfo;
            final PushManagerListener val$listener;
            final BearerToken val$token;

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
                    listener.onError(1, apierror.getCode(), context.getString(0x7f0d014f), apierror.getMessage());
                }
                return false;
            }

            public boolean onSuccess(Bundle bundle)
            {
                boolean flag = false;
                if (bundle == null) goto _L2; else goto _L1
_L1:
                int k;
                int i;
                int j;
                int l;
                if (TextUtils.isEmpty(diainfo.getRailCode()))
                {
                    i = 0;
                } else
                {
                    i = Integer.parseInt(diainfo.getRailCode());
                }
                if (TextUtils.isEmpty(diainfo.getRailRangeCode()))
                {
                    j = 0;
                } else
                {
                    j = Integer.parseInt(diainfo.getRailRangeCode());
                }
                k = 0;
_L7:
                l = bundle.size();
                flag = false;
                if (k >= l) goto _L2; else goto _L3
_L3:
                DiainfoData diainfodata = (DiainfoData)bundle.getSerializable(Integer.toString(k));
                int i1;
                int j1;
                if (TextUtils.isEmpty(diainfodata.getRailCode()))
                {
                    i1 = 0;
                } else
                {
                    i1 = Integer.parseInt(diainfodata.getRailCode());
                }
                if (TextUtils.isEmpty(diainfodata.getRailRangeCode()))
                {
                    j1 = 0;
                } else
                {
                    j1 = Integer.parseInt(diainfodata.getRailRangeCode());
                }
                if (i1 != i || j1 != j) goto _L5; else goto _L4
_L4:
                flag = true;
_L2:
                if (flag)
                {
                    getAndRegisterPush(token, diainfo, false, listener);
                    return false;
                } else
                {
                    registerRail(token, diainfo, listener);
                    return false;
                }
_L5:
                k++;
                if (true) goto _L7; else goto _L6
_L6:
            }

            public volatile boolean onSuccess(Object obj)
            {
                return onSuccess((Bundle)obj);
            }

            
            {
                this$0 = PushDiainfoManager.this;
                diainfo = diainfodata;
                token = bearertoken;
                listener = pushmanagerlistener;
                super();
            }
        });
        registsearchsso.setSearchType(context.getString(0x7f0d01d5));
        registsearchsso.requestAsync(false);
    }

    public void savePushTime(int i)
    {
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences(context.getString(0x7f0d04df), 0).edit();
        editor.putInt("push_time", i);
        editor.commit();
    }

    public void saveYid(String s)
    {
        byte abyte0[];
        CipherObject cipherobject;
        String s1;
        String s2;
        android.content.SharedPreferences.Editor editor;
        try
        {
            abyte0 = loadSecretKey();
        }
        catch (Exception exception)
        {
            return;
        }
        if (abyte0 == null)
        {
            return;
        }
        cipherobject = CipherUtil.encrypt(abyte0, s.getBytes());
        s1 = Base64.encodeToString(cipherobject.getIv(), 0);
        s2 = Base64.encodeToString(cipherobject.getEncryptedString(), 0);
        editor = context.getSharedPreferences(context.getString(0x7f0d04df), 0).edit();
        editor.putString("i", s1);
        editor.putString("y", s2);
        editor.commit();
        return;
    }

    public boolean setRailPushAllOff(final BearerToken token, final String yid, final PushManagerListener listener)
    {
        (new PushSubscriptionDB(context)).deleteDiainfoAll();
        if (TextUtils.isEmpty(yid))
        {
            return false;
        } else
        {
            getPushUsersTopicid(token, yid, false, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                final PushDiainfoManager this$0;
                final PushManagerListener val$listener;
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
                            listener.onError(3, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                        } else
                        {
                            listener.onError(3, apierror.getCode(), context.getString(0x7f0d014f), apierror.getMessage());
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
                            listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                        }
                    } else
                    {
                        ArrayList arraylist = getTopicidList(bundle, "diainfo_", "diainfo_origin_", "diainfo_stopAll", true);
                        if (arraylist.isEmpty())
                        {
                            clearYid();
                            if (listener != null)
                            {
                                listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                                return false;
                            }
                        } else
                        {
                            registerPushSubscribe(token, yid, arraylist, false, false, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                                final _cls10 this$1;

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
                                            listener.onError(4, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                                        } else
                                        {
                                            listener.onError(4, apierror.getCode(), context.getString(0x7f0d014f), apierror.getMessage());
                                        }
                                    }
                                    return false;
                                }

                                public boolean onSuccess(Bundle bundle)
                                {
                                    clearYid();
                                    if (listener != null)
                                    {
                                        listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                                    }
                                    return false;
                                }

                                public volatile boolean onSuccess(Object obj)
                                {
                                    return onSuccess((Bundle)obj);
                                }

            
            {
                this$1 = _cls10.this;
                super();
            }
                            });
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
                this$0 = PushDiainfoManager.this;
                listener = pushmanagerlistener;
                token = bearertoken;
                yid = s;
                super();
            }
            });
            return true;
        }
    }

    public boolean setRailPushAllOn(final BearerToken token, final String yid, final PushManagerListener listener)
    {
        saveYid(yid);
        if (TextUtils.isEmpty(yid))
        {
            return false;
        } else
        {
            getPushUsersTopicid(token, yid, true, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                final PushDiainfoManager this$0;
                final PushManagerListener val$listener;
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
                            listener.onError(3, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                        } else
                        {
                            listener.onError(3, apierror.getCode(), context.getString(0x7f0d014f), apierror.getMessage());
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
                        listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
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
                        listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                        return false;
                    }
                    if (true) goto _L4; else goto _L3
_L3:
                    ArrayList arraylist = getTopicidList(bundle, "diainfo_origin_", null, null, true);
                    if (arraylist.isEmpty())
                    {
                        if (listener != null)
                        {
                            listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
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

                        registerPushSubscribe(token, yid, arraylist1, true, true, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                            final _cls11 this$1;

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
                                        listener.onError(4, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                                    } else
                                    {
                                        listener.onError(4, apierror.getCode(), context.getString(0x7f0d014f), apierror.getMessage());
                                    }
                                }
                                return false;
                            }

                            public boolean onSuccess(Bundle bundle)
                            {
                                if (listener != null)
                                {
                                    listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                                }
                                return false;
                            }

                            public volatile boolean onSuccess(Object obj)
                            {
                                return onSuccess((Bundle)obj);
                            }

            
            {
                this$1 = _cls11.this;
                super();
            }
                        });
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
                this$0 = PushDiainfoManager.this;
                listener = pushmanagerlistener;
                token = bearertoken;
                yid = s;
                super();
            }
            });
            return true;
        }
    }

    public boolean setRailPushForYid(final BearerToken token, final PushManagerListener listener)
    {
        final String newYid;
        if (token != null)
        {
            if (!TextUtils.isEmpty(newYid = TransitUtil.getYID(context)))
            {
                String s = loadYid();
                if (TextUtils.isEmpty(s))
                {
                    return setRailPushAllOn(token, newYid, listener);
                }
                if (!s.equals(newYid))
                {
                    if (!setRailPushAllOff(token, s, new PushManagerListener() {

        final PushDiainfoManager this$0;
        final PushManagerListener val$listener;
        final String val$newYid;
        final BearerToken val$token;

        public void onCanceled()
        {
            if (listener != null)
            {
                listener.onCanceled();
            }
        }

        public void onError(int i, String s1, String s2, String s3)
        {
            if (listener != null)
            {
                listener.onError(4, s1, s2, s3);
            }
        }

        public void onSuccess(String s1, String s2)
        {
            if (!setRailPushAllOn(token, newYid, listener) && listener != null)
            {
                listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
            }
        }

            
            {
                this$0 = PushDiainfoManager.this;
                token = bearertoken;
                newYid = s;
                listener = pushmanagerlistener;
                super();
            }
    }))
                    {
                        return setRailPushAllOn(token, newYid, listener);
                    } else
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void unregisterPushSingle(BearerToken bearertoken, DiainfoData diainfodata, final PushManagerListener listener)
    {
        String s = getDiainfoId(diainfodata);
        if (!(new PushSubscriptionDB(context)).getDiainfoFlag((new StringBuilder()).append("diainfo_").append(s).toString()))
        {
            if (listener != null)
            {
                listener.onError(4, "0", context.getString(0x7f0d0155), context.getString(0x7f0d013c));
            }
            return;
        } else
        {
            ArrayList arraylist = new ArrayList();
            arraylist.add((new StringBuilder()).append("diainfo_").append(s).toString());
            arraylist.add((new StringBuilder()).append("diainfo_origin_").append(s).toString());
            registerPushSubscribe(bearertoken, TransitUtil.getYID(context), arraylist, false, true, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                final PushDiainfoManager this$0;
                final PushManagerListener val$listener;

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
                            listener.onError(4, "-1", context.getString(0x7f0d014f), context.getString(0x7f0d0102));
                        } else
                        {
                            listener.onError(4, apierror.getCode(), context.getString(0x7f0d014f), apierror.getMessage());
                        }
                    }
                    return false;
                }

                public boolean onSuccess(Bundle bundle)
                {
                    if (listener != null)
                    {
                        listener.onSuccess(context.getString(0x7f0d00b1), context.getString(0x7f0d00a8));
                    }
                    return false;
                }

                public volatile boolean onSuccess(Object obj)
                {
                    return onSuccess((Bundle)obj);
                }

            
            {
                this$0 = PushDiainfoManager.this;
                listener = pushmanagerlistener;
                super();
            }
            });
            return;
        }
    }
















}
