// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ByteArrayPool
{

    protected static final Comparator BUF_COMPARATOR = new Comparator() {

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((byte[])obj, (byte[])obj1);
        }

        public int compare(byte abyte0[], byte abyte1[])
        {
            return abyte0.length - abyte1.length;
        }

    };
    private List mBuffersByLastUse;
    private List mBuffersBySize;
    private int mCurrentSize;
    private final int mSizeLimit;

    public ByteArrayPool(int i)
    {
        mBuffersByLastUse = new LinkedList();
        mBuffersBySize = new ArrayList(64);
        mCurrentSize = 0;
        mSizeLimit = i;
    }

    private void trim()
    {
        this;
        JVM INSTR monitorenter ;
_L2:
        int i;
        int j;
        i = mCurrentSize;
        j = mSizeLimit;
        if (i > j)
        {
            break MISSING_BLOCK_LABEL_20;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        byte abyte0[] = (byte[])mBuffersByLastUse.remove(0);
        mBuffersBySize.remove(abyte0);
        mCurrentSize = mCurrentSize - abyte0.length;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public byte[] getBuf(int i)
    {
        this;
        JVM INSTR monitorenter ;
        int j = 0;
_L6:
        if (j < mBuffersBySize.size()) goto _L2; else goto _L1
_L1:
        byte abyte0[] = new byte[i];
_L4:
        this;
        JVM INSTR monitorexit ;
        return abyte0;
_L2:
        abyte0 = (byte[])mBuffersBySize.get(j);
        if (abyte0.length < i)
        {
            break; /* Loop/switch isn't completed */
        }
        mCurrentSize = mCurrentSize - abyte0.length;
        mBuffersBySize.remove(j);
        mBuffersByLastUse.remove(abyte0);
        if (true) goto _L4; else goto _L3
        Exception exception;
        exception;
        throw exception;
_L3:
        j++;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void returnBuf(byte abyte0[])
    {
        this;
        JVM INSTR monitorenter ;
        if (abyte0 == null) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        i = abyte0.length;
        j = mSizeLimit;
        if (i <= j) goto _L3; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L3:
        int k;
        mBuffersByLastUse.add(abyte0);
        k = Collections.binarySearch(mBuffersBySize, abyte0, BUF_COMPARATOR);
        if (k < 0)
        {
            k = -1 + -k;
        }
        mBuffersBySize.add(k, abyte0);
        mCurrentSize = mCurrentSize + abyte0.length;
        trim();
        if (true) goto _L2; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

}
