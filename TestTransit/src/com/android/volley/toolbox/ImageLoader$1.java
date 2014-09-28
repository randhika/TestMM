// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.widget.ImageView;
import com.android.volley.VolleyError;

// Referenced classes of package com.android.volley.toolbox:
//            ImageLoader

class val.defaultImageResId
    implements ageListener
{

    private final int val$defaultImageResId;
    private final int val$errorImageResId;
    private final ImageView val$view;

    public void onErrorResponse(VolleyError volleyerror)
    {
        if (val$errorImageResId != 0)
        {
            val$view.setImageResource(val$errorImageResId);
        }
    }

    public void onResponse(ageContainer agecontainer, boolean flag)
    {
        if (agecontainer.getBitmap() != null)
        {
            val$view.setImageBitmap(agecontainer.getBitmap());
        } else
        if (val$defaultImageResId != 0)
        {
            val$view.setImageResource(val$defaultImageResId);
            return;
        }
    }

    ageContainer()
    {
        val$errorImageResId = i;
        val$view = imageview;
        val$defaultImageResId = j;
        super();
    }
}
