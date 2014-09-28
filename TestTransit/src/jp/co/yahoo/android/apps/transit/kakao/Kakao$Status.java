// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.kakao;


// Referenced classes of package jp.co.yahoo.android.apps.transit.kakao:
//            Kakao

public static class mException
{

    private Exception mException;
    private int mHttpStatus;
    private boolean mIsSuccess;
    private String mMessage;

    public Exception getException()
    {
        return mException;
    }

    public int getHttpStatus()
    {
        return mHttpStatus;
    }

    public String getMessage()
    {
        return mMessage;
    }

    public boolean isSuccess()
    {
        return mIsSuccess;
    }

    _cls9(int i, String s, Exception exception)
    {
        mIsSuccess = false;
        mHttpStatus = i;
        mMessage = s;
        mException = exception;
    }

    mException(boolean flag)
    {
        if (flag)
        {
            mHttpStatus = 200;
        }
        mIsSuccess = flag;
    }

    mIsSuccess(boolean flag, String s, Exception exception)
    {
        mIsSuccess = flag;
        mMessage = s;
        mException = exception;
    }
}
