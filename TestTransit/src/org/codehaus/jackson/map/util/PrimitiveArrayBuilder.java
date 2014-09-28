// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.util;


public abstract class PrimitiveArrayBuilder
{
    static final class Node
    {

        final Object _data;
        final int _dataLength;
        Node _next;

        public int copyData(Object obj, int i)
        {
            System.arraycopy(_data, 0, obj, i, _dataLength);
            return i + _dataLength;
        }

        public Object getData()
        {
            return _data;
        }

        public void linkNext(Node node)
        {
            if (_next != null)
            {
                throw new IllegalStateException();
            } else
            {
                _next = node;
                return;
            }
        }

        public Node next()
        {
            return _next;
        }

        public Node(Object obj, int i)
        {
            _data = obj;
            _dataLength = i;
        }
    }


    static final int INITIAL_CHUNK_SIZE = 12;
    static final int MAX_CHUNK_SIZE = 0x40000;
    static final int SMALL_CHUNK_SIZE = 16384;
    Node _bufferHead;
    Node _bufferTail;
    int _bufferedEntryCount;
    Object _freeBuffer;

    protected PrimitiveArrayBuilder()
    {
    }

    protected abstract Object _constructArray(int i);

    protected void _reset()
    {
        if (_bufferTail != null)
        {
            _freeBuffer = _bufferTail.getData();
        }
        _bufferTail = null;
        _bufferHead = null;
        _bufferedEntryCount = 0;
    }

    public final Object appendCompletedChunk(Object obj, int i)
    {
        Node node = new Node(obj, i);
        int j;
        if (_bufferHead == null)
        {
            _bufferTail = node;
            _bufferHead = node;
        } else
        {
            _bufferTail.linkNext(node);
            _bufferTail = node;
        }
        _bufferedEntryCount = i + _bufferedEntryCount;
        if (i < 16384)
        {
            j = i + i;
        } else
        {
            j = i + (i >> 2);
        }
        return _constructArray(j);
    }

    public Object completeAndClearBuffer(Object obj, int i)
    {
        int j = i + _bufferedEntryCount;
        Object obj1 = _constructArray(j);
        int k = 0;
        for (Node node = _bufferHead; node != null; node = node.next())
        {
            k = node.copyData(obj1, k);
        }

        System.arraycopy(obj, 0, obj1, k, i);
        int l = k + i;
        if (l != j)
        {
            throw new IllegalStateException((new StringBuilder()).append("Should have gotten ").append(j).append(" entries, got ").append(l).toString());
        } else
        {
            return obj1;
        }
    }

    public Object resetAndStart()
    {
        _reset();
        if (_freeBuffer == null)
        {
            return _constructArray(12);
        } else
        {
            return _freeBuffer;
        }
    }
}
