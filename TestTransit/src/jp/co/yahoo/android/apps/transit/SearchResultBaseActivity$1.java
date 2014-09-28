// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultBaseActivity

class this._cls0
    implements android.content.ener
{

    final SearchResultBaseActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            sendKakaoLink();
            return;

        case 1: // '\001'
            sendLine();
            return;

        case 2: // '\002'
            SearchResultBaseActivity.access$000(SearchResultBaseActivity.this);
            return;

        case 3: // '\003'
            copyText();
            return;

        case 4: // '\004'
            sendCalender();
            return;

        case 5: // '\005'
            startTimer(result_id);
            return;

        case 6: // '\006'
            shareAnotherApp();
            break;
        }
    }

    A()
    {
        this$0 = SearchResultBaseActivity.this;
        super();
    }
}
