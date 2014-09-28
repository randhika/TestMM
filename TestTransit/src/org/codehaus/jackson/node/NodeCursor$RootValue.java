// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.node;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;

// Referenced classes of package org.codehaus.jackson.node:
//            NodeCursor

protected static final class _node extends NodeCursor
{

    protected boolean _done;
    JsonNode _node;

    public boolean currentHasChildren()
    {
        return false;
    }

    public JsonNode currentNode()
    {
        return _node;
    }

    public JsonToken endToken()
    {
        return null;
    }

    public String getCurrentName()
    {
        return null;
    }

    public volatile JsonStreamContext getParent()
    {
        return super.getParent();
    }

    public JsonToken nextToken()
    {
        if (!_done)
        {
            _done = true;
            return _node.asToken();
        } else
        {
            _node = null;
            return null;
        }
    }

    public JsonToken nextValue()
    {
        return nextToken();
    }

    public (JsonNode jsonnode, NodeCursor nodecursor)
    {
        super(0, nodecursor);
        _done = false;
        _node = jsonnode;
    }
}
