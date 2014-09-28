// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.agreementlib;

import android.view.View;

// Referenced classes of package jp.co.yahoo.android.common.agreementlib:
//            YJAgreementHelper

static final class val.listener
    implements android.view.
{

    final AggreementListener val$listener;

    public void onClick(View view)
    {
        if (val$listener != null)
        {
            val$listener.onAgree();
        }
    }

    AggreementListener(AggreementListener aggreementlistener)
    {
        val$listener = aggreementlistener;
        super();
    }
}
