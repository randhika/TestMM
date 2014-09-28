// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.node;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;

// Referenced classes of package org.codehaus.jackson.node:
//            ContainerNode, ObjectNode, MissingNode, BaseJsonNode, 
//            JsonNodeFactory

public final class ArrayNode extends ContainerNode
{

    protected ArrayList _children;

    public ArrayNode(JsonNodeFactory jsonnodefactory)
    {
        super(jsonnodefactory);
    }

    private void _add(JsonNode jsonnode)
    {
        if (_children == null)
        {
            _children = new ArrayList();
        }
        _children.add(jsonnode);
    }

    private void _insert(int i, JsonNode jsonnode)
    {
        if (_children == null)
        {
            _children = new ArrayList();
            _children.add(jsonnode);
            return;
        }
        if (i < 0)
        {
            _children.add(0, jsonnode);
            return;
        }
        if (i >= _children.size())
        {
            _children.add(jsonnode);
            return;
        } else
        {
            _children.add(i, jsonnode);
            return;
        }
    }

    private boolean _sameChildren(ArrayList arraylist)
    {
        int i = arraylist.size();
        if (size() != i)
        {
            return false;
        }
        for (int j = 0; j < i; j++)
        {
            if (!((JsonNode)_children.get(j)).equals(arraylist.get(j)))
            {
                return false;
            }
        }

        return true;
    }

    public JsonNode _set(int i, JsonNode jsonnode)
    {
        if (_children == null || i < 0 || i >= _children.size())
        {
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Illegal index ").append(i).append(", array size ").append(size()).toString());
        } else
        {
            return (JsonNode)_children.set(i, jsonnode);
        }
    }

    public void add(double d)
    {
        _add(numberNode(d));
    }

    public void add(float f)
    {
        _add(numberNode(f));
    }

    public void add(int i)
    {
        _add(numberNode(i));
    }

    public void add(long l)
    {
        _add(numberNode(l));
    }

    public void add(String s)
    {
        if (s == null)
        {
            addNull();
            return;
        } else
        {
            _add(textNode(s));
            return;
        }
    }

    public void add(BigDecimal bigdecimal)
    {
        if (bigdecimal == null)
        {
            addNull();
            return;
        } else
        {
            _add(numberNode(bigdecimal));
            return;
        }
    }

    public void add(JsonNode jsonnode)
    {
        if (jsonnode == null)
        {
            jsonnode = nullNode();
        }
        _add(jsonnode);
    }

    public void add(boolean flag)
    {
        _add(booleanNode(flag));
    }

    public void add(byte abyte0[])
    {
        if (abyte0 == null)
        {
            addNull();
            return;
        } else
        {
            _add(binaryNode(abyte0));
            return;
        }
    }

    public JsonNode addAll(Collection collection)
    {
label0:
        {
            if (collection.size() > 0)
            {
                if (_children != null)
                {
                    break label0;
                }
                _children = new ArrayList(collection);
            }
            return this;
        }
        _children.addAll(collection);
        return this;
    }

    public JsonNode addAll(ArrayNode arraynode)
    {
        int i = arraynode.size();
        if (i > 0)
        {
            if (_children == null)
            {
                _children = new ArrayList(i + 2);
            }
            arraynode.addContentsTo(_children);
        }
        return this;
    }

    public ArrayNode addArray()
    {
        ArrayNode arraynode = arrayNode();
        _add(arraynode);
        return arraynode;
    }

    protected void addContentsTo(List list)
    {
        if (_children != null)
        {
            for (Iterator iterator = _children.iterator(); iterator.hasNext(); list.add((JsonNode)iterator.next())) { }
        }
    }

    public void addNull()
    {
        _add(nullNode());
    }

    public ObjectNode addObject()
    {
        ObjectNode objectnode = objectNode();
        _add(objectnode);
        return objectnode;
    }

    public void addPOJO(Object obj)
    {
        if (obj == null)
        {
            addNull();
            return;
        } else
        {
            _add(POJONode(obj));
            return;
        }
    }

    public JsonToken asToken()
    {
        return JsonToken.START_ARRAY;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj == null)
            {
                return false;
            }
            if (obj.getClass() != getClass())
            {
                return false;
            }
            ArrayNode arraynode = (ArrayNode)obj;
            if (_children == null || _children.size() == 0)
            {
                if (arraynode.size() != 0)
                {
                    return false;
                }
            } else
            {
                return arraynode._sameChildren(_children);
            }
        }
        return true;
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
            Iterator iterator = _children.iterator();
            JsonNode jsonnode;
            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }
                jsonnode = ((JsonNode)iterator.next()).findParent(s);
            } while (jsonnode == null);
            return (ObjectNode)jsonnode;
        }
        return null;
    }

    public List findParents(String s, List list)
    {
        if (_children != null)
        {
            for (Iterator iterator = _children.iterator(); iterator.hasNext();)
            {
                list = ((JsonNode)iterator.next()).findParents(s, list);
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
            Iterator iterator = _children.iterator();
            JsonNode jsonnode;
            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }
                jsonnode = ((JsonNode)iterator.next()).findValue(s);
            } while (jsonnode == null);
            return jsonnode;
        }
        return null;
    }

    public List findValues(String s, List list)
    {
        if (_children != null)
        {
            for (Iterator iterator = _children.iterator(); iterator.hasNext();)
            {
                list = ((JsonNode)iterator.next()).findValues(s, list);
            }

        }
        return list;
    }

    public List findValuesAsText(String s, List list)
    {
        if (_children != null)
        {
            for (Iterator iterator = _children.iterator(); iterator.hasNext();)
            {
                list = ((JsonNode)iterator.next()).findValuesAsText(s, list);
            }

        }
        return list;
    }

    public JsonNode get(int i)
    {
        if (i >= 0 && _children != null && i < _children.size())
        {
            return (JsonNode)_children.get(i);
        } else
        {
            return null;
        }
    }

    public JsonNode get(String s)
    {
        return null;
    }

    public Iterator getElements()
    {
        if (_children == null)
        {
            return ContainerNode.NoNodesIterator.instance();
        } else
        {
            return _children.iterator();
        }
    }

    public int hashCode()
    {
        int i;
        if (_children == null)
        {
            i = 1;
        } else
        {
            i = _children.size();
            Iterator iterator = _children.iterator();
            while (iterator.hasNext()) 
            {
                JsonNode jsonnode = (JsonNode)iterator.next();
                if (jsonnode != null)
                {
                    i ^= jsonnode.hashCode();
                }
            }
        }
        return i;
    }

    public void insert(int i, double d)
    {
        _insert(i, numberNode(d));
    }

    public void insert(int i, float f)
    {
        _insert(i, numberNode(f));
    }

    public void insert(int i, int j)
    {
        _insert(i, numberNode(j));
    }

    public void insert(int i, long l)
    {
        _insert(i, numberNode(l));
    }

    public void insert(int i, String s)
    {
        if (s == null)
        {
            insertNull(i);
            return;
        } else
        {
            _insert(i, textNode(s));
            return;
        }
    }

    public void insert(int i, BigDecimal bigdecimal)
    {
        if (bigdecimal == null)
        {
            insertNull(i);
            return;
        } else
        {
            _insert(i, numberNode(bigdecimal));
            return;
        }
    }

    public void insert(int i, JsonNode jsonnode)
    {
        if (jsonnode == null)
        {
            jsonnode = nullNode();
        }
        _insert(i, jsonnode);
    }

    public void insert(int i, boolean flag)
    {
        _insert(i, booleanNode(flag));
    }

    public void insert(int i, byte abyte0[])
    {
        if (abyte0 == null)
        {
            insertNull(i);
            return;
        } else
        {
            _insert(i, binaryNode(abyte0));
            return;
        }
    }

    public ArrayNode insertArray(int i)
    {
        ArrayNode arraynode = arrayNode();
        _insert(i, arraynode);
        return arraynode;
    }

    public void insertNull(int i)
    {
        _insert(i, nullNode());
    }

    public ObjectNode insertObject(int i)
    {
        ObjectNode objectnode = objectNode();
        _insert(i, objectnode);
        return objectnode;
    }

    public void insertPOJO(int i, Object obj)
    {
        if (obj == null)
        {
            insertNull(i);
            return;
        } else
        {
            _insert(i, POJONode(obj));
            return;
        }
    }

    public boolean isArray()
    {
        return true;
    }

    public JsonNode path(int i)
    {
        if (i >= 0 && _children != null && i < _children.size())
        {
            return (JsonNode)_children.get(i);
        } else
        {
            return MissingNode.getInstance();
        }
    }

    public JsonNode path(String s)
    {
        return MissingNode.getInstance();
    }

    public JsonNode remove(int i)
    {
        if (i >= 0 && _children != null && i < _children.size())
        {
            return (JsonNode)_children.remove(i);
        } else
        {
            return null;
        }
    }

    public ArrayNode removeAll()
    {
        _children = null;
        return this;
    }

    public volatile ContainerNode removeAll()
    {
        return removeAll();
    }

    public final void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeStartArray();
        if (_children != null)
        {
            for (Iterator iterator = _children.iterator(); iterator.hasNext(); ((BaseJsonNode)(JsonNode)iterator.next()).writeTo(jsongenerator)) { }
        }
        jsongenerator.writeEndArray();
    }

    public void serializeWithType(JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        typeserializer.writeTypePrefixForArray(this, jsongenerator);
        if (_children != null)
        {
            for (Iterator iterator = _children.iterator(); iterator.hasNext(); ((BaseJsonNode)(JsonNode)iterator.next()).writeTo(jsongenerator)) { }
        }
        typeserializer.writeTypeSuffixForArray(this, jsongenerator);
    }

    public JsonNode set(int i, JsonNode jsonnode)
    {
        if (jsonnode == null)
        {
            jsonnode = nullNode();
        }
        return _set(i, jsonnode);
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
        StringBuilder stringbuilder = new StringBuilder(16 + (size() << 4));
        stringbuilder.append('[');
        if (_children != null)
        {
            int i = 0;
            for (int j = _children.size(); i < j; i++)
            {
                if (i > 0)
                {
                    stringbuilder.append(',');
                }
                stringbuilder.append(((JsonNode)_children.get(i)).toString());
            }

        }
        stringbuilder.append(']');
        return stringbuilder.toString();
    }
}
