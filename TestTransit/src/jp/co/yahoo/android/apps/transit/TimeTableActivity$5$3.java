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

class 
    implements android.content.kListener
{

    final is._cls0 this$1;
    final TimeTableData val$latestTimeTable;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface.dismiss();
        TimeTableActivity.access$2100(_fld0).updateTimeTable(val$latestTimeTable);
        TimeTableActivity.access$2200(_fld0, true);
    }

    ()
    {
        this$1 = final_;
        val$latestTimeTable = TimeTableData.this;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/apps/transit/TimeTableActivity$5

/* anonymous class */
    class TimeTableActivity._cls5
        implements jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener
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
            TimeTableData timetabledata;
            if (TimeTableActivity.access$1100(TimeTableActivity.this) == null)
            {
                return false;
            }
            timetabledata = (TimeTableData)TimeTableActivity.access$1100(TimeTableActivity.this).getResult();
            if (!TimeTableActivity.access$2100(TimeTableActivity.this).isEqual(timetabledata)) goto _L2; else goto _L1
_L1:
            TimeTableActivity.access$2200(TimeTableActivity.this, false);
_L4:
            TimeTableActivity.access$1102(TimeTableActivity.this, null);
            return false;
_L2:
            if (!isFinishing())
            {
                (new TransitDialogBuilder(TimeTableActivity.this)).setTitleWarnning(getString(0x7f0d053d)).setMessage(getString(0x7f0d053b)).setPositiveButton(getString(0x7f0d053e), timetabledata. new TimeTableActivity._cls5._cls3()).setNegativeButton(getString(0x7f0d053c), new TimeTableActivity._cls5._cls2()).setOnCancelListener(new TimeTableActivity._cls5._cls1()).show();
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

            
            {
                this$0 = TimeTableActivity.this;
                super();
            }

        // Unreferenced inner class jp/co/yahoo/android/apps/transit/TimeTableActivity$5$1

/* anonymous class */
        class TimeTableActivity._cls5._cls1
            implements android.content.DialogInterface.OnCancelListener
        {

            final TimeTableActivity._cls5 this$1;

            public void onCancel(DialogInterface dialoginterface)
            {
                TimeTableActivity.access$2200(this$0, false);
            }

                    
                    {
                        this$1 = TimeTableActivity._cls5.this;
                        super();
                    }
        }


        // Unreferenced inner class jp/co/yahoo/android/apps/transit/TimeTableActivity$5$2

/* anonymous class */
        class TimeTableActivity._cls5._cls2
            implements android.content.DialogInterface.OnClickListener
        {

            final TimeTableActivity._cls5 this$1;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.cancel();
            }

                    
                    {
                        this$1 = TimeTableActivity._cls5.this;
                        super();
                    }
        }

    }

}
