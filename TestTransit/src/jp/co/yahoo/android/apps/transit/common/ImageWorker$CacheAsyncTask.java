// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import java.io.File;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            AsyncTask, ImageWorker

protected class mFile extends AsyncTask
{

    private Integer mAction;
    private File mFile;
    final ImageWorker this$0;

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground(aobj);
    }

    protected transient Void doInBackground(Object aobj[])
    {
        mAction = (Integer)aobj[0];
        ((Integer)aobj[0]).intValue();
        JVM INSTR tableswitch 0 4: default 52
    //                   0 54
    //                   1 64
    //                   2 97
    //                   3 107
    //                   4 117;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return null;
_L2:
        clearCacheInternal();
        continue; /* Loop/switch isn't completed */
_L3:
        if (aobj.length > 1)
        {
            File file = (File)aobj[1];
            if (file != null)
            {
                mFile = file;
                removeCacheInternal(file);
            }
        }
        continue; /* Loop/switch isn't completed */
_L4:
        flushCacheInternal();
        continue; /* Loop/switch isn't completed */
_L5:
        closeCacheInternal();
        continue; /* Loop/switch isn't completed */
_L6:
        clearInvalidFileCacheInternal();
        if (true) goto _L1; else goto _L7
_L7:
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Void)obj);
    }

    protected void onPostExecute(Void void1)
    {
        if (ImageWorker.access$400(ImageWorker.this) == null) goto _L2; else goto _L1
_L1:
        mAction.intValue();
        JVM INSTR tableswitch 0 1: default 40
    //                   0 41
    //                   1 54;
           goto _L2 _L3 _L4
_L2:
        return;
_L3:
        ImageWorker.access$400(ImageWorker.this).onClearCache();
        return;
_L4:
        if (mFile != null)
        {
            ImageWorker.access$400(ImageWorker.this).onRemoveCache(mFile);
            mFile = null;
            return;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    protected ()
    {
        this$0 = ImageWorker.this;
        super();
        mFile = null;
    }
}
