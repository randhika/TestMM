// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

public class hp
{

    public static String a(String s, String s1, Context context, AttributeSet attributeset, boolean flag, boolean flag1, String s2)
    {
        String s3;
        if (attributeset == null)
        {
            s3 = null;
        } else
        {
            s3 = attributeset.getAttributeValue(s, s1);
        }
        if (s3 != null && s3.startsWith("@string/") && flag)
        {
            String s4 = s3.substring("@string/".length());
            String s5 = context.getPackageName();
            TypedValue typedvalue = new TypedValue();
            try
            {
                context.getResources().getValue((new StringBuilder()).append(s5).append(":string/").append(s4).toString(), typedvalue, true);
            }
            catch (android.content.res.Resources.NotFoundException notfoundexception)
            {
                Log.w(s2, (new StringBuilder()).append("Could not find resource for ").append(s1).append(": ").append(s3).toString());
            }
            if (typedvalue.string != null)
            {
                s3 = typedvalue.string.toString();
            } else
            {
                Log.w(s2, (new StringBuilder()).append("Resource ").append(s1).append(" was not a string: ").append(typedvalue).toString());
            }
        }
        if (flag1 && s3 == null)
        {
            Log.w(s2, (new StringBuilder()).append("Required XML attribute \"").append(s1).append("\" missing").toString());
        }
        return s3;
    }
}
