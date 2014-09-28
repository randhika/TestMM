// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.TimeTableSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.TimeTableData;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TimeTableActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.._cls0
{

    final TimeTableActivity this$0;

    public boolean onCanceled()
    {
        TimeTableActivity.access$2200(TimeTableActivity.this, false);
        TimeTableActivity.access$1102(TimeTableActivity.this, null);
        return false;
    }

    public boolean onError(APIError apierror)
    {
        TimeTableActivity.access$2200(TimeTableActivity.this, false);
        TimeTableActivity.access$1102(TimeTableActivity.this, null);
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        final TimeTableData latestTimeTable;
        if (TimeTableActivity.access$1100(TimeTableActivity.this) == null)
        {
            return false;
        }
        latestTimeTable = (TimeTableData)TimeTableActivity.access$1100(TimeTableActivity.this).getResult();
        if (!TimeTableActivity.access$2100(TimeTableActivity.this).isEqual(latestTimeTable)) goto _L2; else goto _L1
_L1:
        TimeTableActivity.access$2200(TimeTableActivity.this, false);
_L4:
        TimeTableActivity.access$1102(TimeTableActivity.this, null);
        return false;
_L2:
        if (!isFinishing())
        {
            (new TransitDialogBuilder(TimeTableActivity.this)).setTitleWarnning(getString(0x7f0d053d)).setMessage(getString(0x7f0d053b)).setPositiveButton(getString(0x7f0d053e), new android.content.DialogInterface.OnClickListener() {

                final TimeTableActivity._cls5 this$1;
                final TimeTableData val$latestTimeTable;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                    TimeTableActivity.access$2100(this$0).updateTimeTable(latestTimeTable);
                    TimeTableActivity.access$2200(this$0, true);
                }

            
            {
                this$1 = TimeTableActivity._cls5.this;
                latestTimeTable = timetabledata;
                super();
            }
            }).setNegativeButton(getString(0x7f0d053c), new android.content.DialogInterface.OnClickListener() {

                final TimeTableActivity._cls5 this$1;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$1 = TimeTableActivity._cls5.this;
                super();
            }
            }).setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

                final TimeTableActivity._cls5 this$1;

                public void onCancel(DialogInterface dialoginterface)
                {
                    TimeTableActivity.access$2200(this$0, false);
                }

            
            {
                this$1 = TimeTableActivity._cls5.this;
                super();
            }
            }).show();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    _cls3.val.latestTimeTable()
    {
        this$0 = TimeTableActivity.this;
        super();
    }
}
