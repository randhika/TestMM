// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.node;

import java.util.Iterator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;

// Referenced classes of package org.codehaus.jackson.node:
//            NodeCursor, ContainerNode

protected static final class _contents extends NodeCursor
{

    Iterator _contents;
    JsonNode _currentNode;

    public boolean currentHasChildren()
    {
        return ((ContainerNode)currentNode()).size() > 0;
    }

    public JsonNode currentNode()
    {
        return _currentNode;
    }

    public JsonToken endToken()
    {
        return JsonToken.END_ARRAY;
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
        if (!_contents.hasNext())
        {
            _currentNode = null;
            return null;
        } else
        {
            _currentNode = (JsonNode)_contents.next();
            return _currentNode.asToken();
        }
    }

    public JsonToken nextValue()
    {
        return nextToken();
    }

    public (JsonNode jsonnode, NodeCursor nodecursor)
    {
        super(1, nodecursor);
        _contents = jsonnode.getElements();
    }
}
