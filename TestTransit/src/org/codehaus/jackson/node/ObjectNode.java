// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.node;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;

// Referenced classes of package org.codehaus.jackson.node:
//            ContainerNode, MissingNode, BaseJsonNode, TextNode, 
//            JsonNodeFactory, ArrayNode

public class ObjectNode extends ContainerNode
{
    protected static class NoFieldsIterator
        implements Iterator
    {

        static final NoFieldsIterator instance = new NoFieldsIterator();

        public boolean hasNext()
        {
            return false;
        }

        public volatile Object next()
        {
            return next();
        }

        public java.util.Map.Entry next()
        {
            throw new NoSuchElementException();
        }

        public void remove()
        {
            throw new IllegalStateException();
        }


        private NoFieldsIterator()
        {
        }
    }


    protected LinkedHashMap _children;

    public ObjectNode(JsonNodeFactory jsonnodefactory)
    {
        super(jsonnodefactory);
        _children = null;
    }

    private final JsonNode _put(String s, JsonNode jsonnode)
    {
        if (_children == null)
        {
            _children = new LinkedHashMap();
        }
        return (JsonNode)_children.put(s, jsonnode);
    }

    public JsonToken asToken()
    {
        return JsonToken.START_OBJECT;
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (obj == null)
        {
            return false;
        }
        if (obj.getClass() != getClass())
        {
            return false;
        }
        ObjectNode objectnode = (ObjectNode)obj;
        if (objectnode.size() != size())
        {
            return false;
        }
        if (_children == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        Iterator iterator = _children.entrySet().iterator();
        JsonNode jsonnode;
        JsonNode jsonnode1;
        do
        {
            if (!iterator.hasNext())
            {
                continue; /* Loop/switch isn't completed */
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            String s = (String)entry.getKey();
            jsonnode = (JsonNode)entry.getValue();
            jsonnode1 = objectnode.get(s);
        } while (jsonnode1 != null && jsonnode1.equals(jsonnode));
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L3
_L3:
        return false;
    }

    public volatile JsonNode findParent(String s)
    {
        return findParent(s);
    }

    public ObjectNode findParent(String s)
    {
label0:
        {
            if (_children == null)
            {
                break label0;
            }
            Iterator iterator = _children.entrySet().iterator();
            JsonNode jsonnode;
            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                if (s.equals(entry.getKey()))
                {
                    return this;
                }
                jsonnode = ((JsonNode)entry.getValue()).findParent(s);
            } while (jsonnode == null);
            return (ObjectNode)jsonnode;
        }
        return null;
    }

    public List findParents(String s, List list)
    {
        if (_children != null)
        {
            for (Iterator iterator = _children.entrySet().iterator(); iterator.hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                if (s.equals(entry.getKey()))
                {
                    if (list == null)
                    {
                        list = new ArrayList();
                    }
                    list.add(this);
                } else
                {
                    list = ((JsonNode)entry.getValue()).findParents(s, list);
                }
            }

        }
        return list;
    }

    public JsonNode findValue(String s)
    {
label0:
        {
            if (_children == null)
            {
                break label0;
            }
            Iterator iterator = _children.entrySet().iterator();
            JsonNode jsonnode;
            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                if (s.equals(entry.getKey()))
                {
                    return (JsonNode)entry.getValue();
                }
                jsonnode = ((JsonNode)entry.getValue()).findValue(s);
            } while (jsonnode == null);
            return jsonnode;
        }
        return null;
    }

    public List findValues(String s, List list)
    {
        if (_children != null)
        {
            for (Iterator iterator = _children.entrySet().iterator(); iterator.hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                if (s.equals(entry.getKey()))
                {
                    if (list == null)
                    {
                        list = new ArrayList();
                    }
                    list.add(entry.getValue());
                } else
                {
                    list = ((JsonNode)entry.getValue()).findValues(s, list);
                }
            }

        }
        return list;
    }

    public List findValuesAsText(String s, List list)
    {
        if (_children != null)
        {
            for (Iterator iterator = _children.entrySet().iterator(); iterator.hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                if (s.equals(entry.getKey()))
                {
                    if (list == null)
                    {
                        list = new ArrayList();
                    }
                    list.add(((JsonNode)entry.getValue()).getValueAsText());
                } else
                {
                    list = ((JsonNode)entry.getValue()).findValuesAsText(s, list);
                }
            }

        }
        return list;
    }

    public JsonNode get(int i)
    {
        return null;
    }

    public JsonNode get(String s)
    {
        if (_children != null)
        {
            return (JsonNode)_children.get(s);
        } else
        {
            return null;
        }
    }

    public Iterator getElements()
    {
        if (_children == null)
        {
            return ContainerNode.NoNodesIterator.instance();
        } else
        {
            return _children.values().iterator();
        }
    }

    public Iterator getFieldNames()
    {
        if (_children == null)
        {
            return ContainerNode.NoStringsIterator.instance();
        } else
        {
            return _children.keySet().iterator();
        }
    }

    public Iterator getFields()
    {
        if (_children == null)
        {
            return NoFieldsIterator.instance;
        } else
        {
            return _children.entrySet().iterator();
        }
    }

    public int hashCode()
    {
        if (_children == null)
        {
            return -1;
        } else
        {
            return _children.hashCode();
        }
    }

    public boolean isObject()
    {
        return true;
    }

    public JsonNode path(int i)
    {
        return MissingNode.getInstance();
    }

    public JsonNode path(String s)
    {
        if (_children != null)
        {
            JsonNode jsonnode = (JsonNode)_children.get(s);
            if (jsonnode != null)
            {
                return jsonnode;
            }
        }
        return MissingNode.getInstance();
    }

    public JsonNode put(String s, JsonNode jsonnode)
    {
        if (jsonnode == null)
        {
            jsonnode = nullNode();
        }
        return _put(s, jsonnode);
    }

    public void put(String s, double d)
    {
        _put(s, numberNode(d));
    }

    public void put(String s, float f)
    {
        _put(s, numberNode(f));
    }

    public void put(String s, int i)
    {
        _put(s, numberNode(i));
    }

    public void put(String s, long l)
    {
        _put(s, numberNode(l));
    }

    public void put(String s, String s1)
    {
        if (s1 == null)
        {
            putNull(s);
            return;
        } else
        {
            _put(s, textNode(s1));
            return;
        }
    }

    public void put(String s, BigDecimal bigdecimal)
    {
        if (bigdecimal == null)
        {
            putNull(s);
            return;
        } else
        {
            _put(s, numberNode(bigdecimal));
            return;
        }
    }

    public void put(String s, boolean flag)
    {
        _put(s, booleanNode(flag));
    }

    public void put(String s, byte abyte0[])
    {
        if (abyte0 == null)
        {
            putNull(s);
            return;
        } else
        {
            _put(s, binaryNode(abyte0));
            return;
        }
    }

    public JsonNode putAll(Map map)
    {
        if (_children == null)
        {
            _children = new LinkedHashMap(map);
        } else
        {
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) 
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                Object obj = (JsonNode)entry.getValue();
                if (obj == null)
                {
                    obj = nullNode();
                }
                _children.put(entry.getKey(), obj);
            }
        }
        return this;
    }

    public JsonNode putAll(ObjectNode objectnode)
    {
        int i = objectnode.size();
        if (i > 0)
        {
            if (_children == null)
            {
                _children = new LinkedHashMap(i);
            }
            objectnode.putContentsTo(_children);
        }
        return this;
    }

    public ArrayNode putArray(String s)
    {
        ArrayNode arraynode = arrayNode();
        _put(s, arraynode);
        return arraynode;
    }

    protected void putContentsTo(Map map)
    {
        if (_children != null)
        {
            java.util.Map.Entry entry;
            for (Iterator iterator = _children.entrySet().iterator(); iterator.hasNext(); map.put(entry.getKey(), entry.getValue()))
            {
                entry = (java.util.Map.Entry)iterator.next();
            }

        }
    }

    public void putNull(String s)
    {
        _put(s, nullNode());
    }

    public ObjectNode putObject(String s)
    {
        ObjectNode objectnode = objectNode();
        _put(s, objectnode);
        return objectnode;
    }

    public void putPOJO(String s, Object obj)
    {
        _put(s, POJONode(obj));
    }

    public JsonNode remove(String s)
    {
        if (_children != null)
        {
            return (JsonNode)_children.remove(s);
        } else
        {
            return null;
        }
    }

    public ObjectNode remove(Collection collection)
    {
        if (_children != null)
        {
            String s;
            for (Iterator iterator = collection.iterator(); iterator.hasNext(); _children.remove(s))
            {
                s = (String)iterator.next();
            }

        }
        return this;
    }

    public volatile ContainerNode removeAll()
    {
        return removeAll();
    }

    public ObjectNode removeAll()
    {
        _children = null;
        return this;
    }

    public ObjectNode retain(Collection collection)
    {
        if (_children != null)
        {
            Iterator iterator = _children.entrySet().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                if (!collection.contains(((java.util.Map.Entry)iterator.next()).getKey()))
                {
                    iterator.remove();
                }
            } while (true);
        }
        return this;
    }

    public transient ObjectNode retain(String as[])
    {
        return retain(((Collection) (Arrays.asList(as))));
    }

    public final void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeStartObject();
        if (_children != null)
        {
            java.util.Map.Entry entry;
            for (Iterator iterator = _children.entrySet().iterator(); iterator.hasNext(); ((BaseJsonNode)entry.getValue()).serialize(jsongenerator, serializerprovider))
            {
                entry = (java.util.Map.Entry)iterator.next();
                jsongenerator.writeFieldName((String)entry.getKey());
            }

        }
        jsongenerator.writeEndObject();
    }

    public void serializeWithType(JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        typeserializer.writeTypePrefixForObject(this, jsongenerator);
        if (_children != null)
        {
            java.util.Map.Entry entry;
            for (Iterator iterator = _children.entrySet().iterator(); iterator.hasNext(); ((BaseJsonNode)entry.getValue()).serialize(jsongenerator, serializerprovider))
            {
                entry = (java.util.Map.Entry)iterator.next();
                jsongenerator.writeFieldName((String)entry.getKey());
            }

        }
        typeserializer.writeTypeSuffixForObject(this, jsongenerator);
    }

    public int size()
    {
        if (_children == null)
        {
            return 0;
        } else
        {
            return _children.size();
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(32 + (size() << 4));
        stringbuilder.append("{");
        if (_children != null)
        {
            int i = 0;
            java.util.Map.Entry entry;
            for (Iterator iterator = _children.entrySet().iterator(); iterator.hasNext(); stringbuilder.append(((JsonNode)entry.getValue()).toString()))
            {
                entry = (java.util.Map.Entry)iterator.next();
                if (i > 0)
                {
                    stringbuilder.append(",");
                }
                i++;
                TextNode.appendQuoted(stringbuilder, (String)entry.getKey());
                stringbuilder.append(':');
            }

        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public volatile JsonNode with(String s)
    {
        return with(s);
    }

    public ObjectNode with(String s)
    {
        ObjectNode objectnode;
        if (_children == null)
        {
            _children = new LinkedHashMap();
        } else
        {
            JsonNode jsonnode = (JsonNode)_children.get(s);
            if (jsonnode != null)
            {
                if (jsonnode instanceof ObjectNode)
                {
                    return (ObjectNode)jsonnode;
                } else
                {
                    throw new UnsupportedOperationException((new StringBuilder()).append("Property '").append(s).append("' has value that is not of type ObjectNode (but ").append(jsonnode.getClass().getName()).append(")").toString());
                }
            }
        }
        objectnode = objectNode();
        _children.put(s, objectnode);
        return objectnode;
    }
}
