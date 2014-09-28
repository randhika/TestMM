// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.node;

import java.util.Iterator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;

// Referenced classes of package org.codehaus.jackson.node:
//            ContainerNode, ObjectNode

abstract class NodeCursor extends JsonStreamContext
{
    protected static final class Array extends NodeCursor
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
            return getParent();
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

        public Array(JsonNode jsonnode, NodeCursor nodecursor)
        {
            super(1, nodecursor);
            _contents = jsonnode.getElements();
        }
    }

    protected static final class Object extends NodeCursor
    {

        Iterator _contents;
        java.util.Map.Entry _current;
        boolean _needEntry;

        public boolean currentHasChildren()
        {
            return ((ContainerNode)currentNode()).size() > 0;
        }

        public JsonNode currentNode()
        {
            if (_current == null)
            {
                return null;
            } else
            {
                return (JsonNode)_current.getValue();
            }
        }

        public JsonToken endToken()
        {
            return JsonToken.END_OBJECT;
        }

        public String getCurrentName()
        {
            if (_current == null)
            {
                return null;
            } else
            {
                return (String)_current.getKey();
            }
        }

        public volatile JsonStreamContext getParent()
        {
            return getParent();
        }

        public JsonToken nextToken()
        {
            if (_needEntry)
            {
                if (!_contents.hasNext())
                {
                    _current = null;
                    return null;
                } else
                {
                    _needEntry = false;
                    _current = (java.util.Map.Entry)_contents.next();
                    return JsonToken.FIELD_NAME;
                }
            } else
            {
                _needEntry = true;
                return ((JsonNode)_current.getValue()).asToken();
            }
        }

        public JsonToken nextValue()
        {
            JsonToken jsontoken = nextToken();
            if (jsontoken == JsonToken.FIELD_NAME)
            {
                jsontoken = nextToken();
            }
            return jsontoken;
        }

        public Object(JsonNode jsonnode, NodeCursor nodecursor)
        {
            super(2, nodecursor);
            _contents = ((ObjectNode)jsonnode).getFields();
            _needEntry = true;
        }
    }

    protected static final class RootValue extends NodeCursor
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
            return getParent();
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

        public RootValue(JsonNode jsonnode, NodeCursor nodecursor)
        {
            super(0, nodecursor);
            _done = false;
            _node = jsonnode;
        }
    }


    final NodeCursor _parent;

    public NodeCursor(int i, NodeCursor nodecursor)
    {
        _type = i;
        _index = -1;
        _parent = nodecursor;
    }

    public abstract boolean currentHasChildren();

    public abstract JsonNode currentNode();

    public abstract JsonToken endToken();

    public abstract String getCurrentName();

    public volatile JsonStreamContext getParent()
    {
        return getParent();
    }

    public final NodeCursor getParent()
    {
        return _parent;
    }

    public final NodeCursor iterateChildren()
    {
        JsonNode jsonnode = currentNode();
        if (jsonnode == null)
        {
            throw new IllegalStateException("No current node");
        }
        if (jsonnode.isArray())
        {
            return new Array(jsonnode, this);
        }
        if (jsonnode.isObject())
        {
            return new Object(jsonnode, this);
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("Current node of type ").append(jsonnode.getClass().getName()).toString());
        }
    }

    public abstract JsonToken nextToken();

    public abstract JsonToken nextValue();
}
