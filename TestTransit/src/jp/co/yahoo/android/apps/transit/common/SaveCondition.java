// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.content.SharedPreferences;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;

public class SaveCondition
{

    private static final String PREF_CONDITION = "condition";
    private static final String PREF_CONDITION_KEY = "condition_key";
    private ConditionData condition;
    private Context context;
    private SharedPreferences pref;

    public SaveCondition(Context context1)
    {
        pref = null;
        condition = null;
        context = null;
        context = context1;
        pref = context1.getSharedPreferences("condition", 0);
        loadCond();
    }

    public SaveCondition(Context context1, boolean flag)
    {
        pref = null;
        condition = null;
        context = null;
        context = context1;
        pref = context1.getSharedPreferences("condition", 0);
        if (flag)
        {
            loadCond();
        }
    }

    public ConditionData getCond()
    {
        return condition;
    }

    public boolean isSavedConditionTicket()
    {
        String s = pref.getString("condition_key", null);
        return s != null && s.contains(context.getString(0x7f0d0193));
    }

    public void loadCond()
    {
        condition = toCondition(pref.getString("condition_key", null));
    }

    public ConditionData mergeCond(ConditionData conditiondata)
    {
        return mergeCond(conditiondata, true, true, true, true);
    }

    public ConditionData mergeCond(ConditionData conditiondata, boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        if (condition == null)
        {
            return conditiondata;
        }
        if (flag)
        {
            condition.superExpress = conditiondata.superExpress;
            condition.airplane = conditiondata.airplane;
            condition.highwayBus = conditiondata.highwayBus;
            condition.localBus = conditiondata.localBus;
            condition.ferry = conditiondata.ferry;
            condition.express = conditiondata.express;
        }
        if (flag1)
        {
            condition.walkSpeed = conditiondata.walkSpeed;
        }
        if (flag2)
        {
            condition.seatKind = conditiondata.seatKind;
        }
        if (flag3)
        {
            condition.ticket = conditiondata.ticket;
        }
        return condition;
    }

    public ConditionData reverseMergeCond(ConditionData conditiondata)
    {
        if (condition == null)
        {
            return conditiondata;
        } else
        {
            conditiondata.superExpress = condition.superExpress;
            conditiondata.airplane = condition.airplane;
            conditiondata.highwayBus = condition.highwayBus;
            conditiondata.localBus = condition.localBus;
            conditiondata.ferry = condition.ferry;
            conditiondata.express = condition.express;
            conditiondata.walkSpeed = condition.walkSpeed;
            conditiondata.seatKind = condition.seatKind;
            conditiondata.ticket = condition.ticket;
            return conditiondata;
        }
    }

    public void setCond(ConditionData conditiondata)
    {
        String s = toString(conditiondata);
        android.content.SharedPreferences.Editor editor = pref.edit();
        editor.putString("condition_key", s);
        editor.commit();
    }

    public void setCond(ConditionData conditiondata, boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        setCond(mergeCond(conditiondata, flag, flag1, flag2, flag3));
    }

    protected ConditionData toCondition(String s)
    {
        ConditionData conditiondata;
        if (s == null || s.equals(""))
        {
            conditiondata = null;
        } else
        {
            conditiondata = new ConditionData();
            String as[] = s.split("&");
            if (as == null || as.length < 1)
            {
                return null;
            }
            int i = 0;
            while (i < as.length) 
            {
                String as1[] = as[i].split("=");
                if (as1 != null && as1.length >= 1)
                {
                    if (as1[0].equals(context.getString(0x7f0d019b)))
                    {
                        conditiondata.superExpress = Boolean.parseBoolean(as1[1]);
                    } else
                    if (as1[0].equals(context.getString(0x7f0d0196)))
                    {
                        conditiondata.express = Boolean.parseBoolean(as1[1]);
                    } else
                    if (as1[0].equals(context.getString(0x7f0d0195)))
                    {
                        conditiondata.airplane = Boolean.parseBoolean(as1[1]);
                    } else
                    if (as1[0].equals(context.getString(0x7f0d0197)))
                    {
                        conditiondata.highwayBus = Boolean.parseBoolean(as1[1]);
                    } else
                    if (as1[0].equals(context.getString(0x7f0d019a)))
                    {
                        conditiondata.localBus = Boolean.parseBoolean(as1[1]);
                    } else
                    if (as1[0].equals(context.getString(0x7f0d0198)))
                    {
                        conditiondata.ferry = Boolean.parseBoolean(as1[1]);
                    } else
                    if (as1[0].equals(context.getString(0x7f0d019e)))
                    {
                        conditiondata.walkSpeed = Integer.parseInt(as1[1]);
                    } else
                    if (as1[0].equals(context.getString(0x7f0d018d)))
                    {
                        conditiondata.seatKind = Integer.parseInt(as1[1]);
                    } else
                    if (as1[0].equals(context.getString(0x7f0d0193)))
                    {
                        conditiondata.ticket = as1[1];
                    }
                }
                i++;
            }
        }
        return conditiondata;
    }

    protected String toString(ConditionData conditiondata)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(context.getString(0x7f0d019b)).append("=").append(Boolean.toString(conditiondata.superExpress)).append("&");
        stringbuffer.append(context.getString(0x7f0d0196)).append("=").append(Boolean.toString(conditiondata.express)).append("&");
        stringbuffer.append(context.getString(0x7f0d0195)).append("=").append(Boolean.toString(conditiondata.airplane)).append("&");
        stringbuffer.append(context.getString(0x7f0d0197)).append("=").append(Boolean.toString(conditiondata.highwayBus)).append("&");
        stringbuffer.append(context.getString(0x7f0d019a)).append("=").append(Boolean.toString(conditiondata.localBus)).append("&");
        stringbuffer.append(context.getString(0x7f0d0198)).append("=").append(Boolean.toString(conditiondata.ferry)).append("&");
        stringbuffer.append(context.getString(0x7f0d019e)).append("=").append(Integer.toString(conditiondata.walkSpeed)).append("&");
        stringbuffer.append(context.getString(0x7f0d018d)).append("=").append(Integer.toString(conditiondata.seatKind)).append("&");
        if (conditiondata.ticket == null)
        {
            stringbuffer.append(context.getString(0x7f0d0193)).append("=").append(context.getString(0x7f0d0581)).append("&");
        } else
        {
            stringbuffer.append(context.getString(0x7f0d0193)).append("=").append(conditiondata.ticket).append("&");
        }
        if (conditiondata.passtype == null)
        {
            stringbuffer.append(context.getString(0x7f0d0384)).append("=").append(context.getString(0x7f0d057a)).append("&");
        } else
        {
            stringbuffer.append(context.getString(0x7f0d0384)).append("=").append(conditiondata.passtype).append("&");
        }
        return stringbuffer.toString();
    }
}
