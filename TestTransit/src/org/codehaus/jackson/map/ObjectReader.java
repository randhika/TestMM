// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import org.codehaus.jackson.FormatSchema;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.Versioned;
import org.codehaus.jackson.map.deser.StdDeserializationContext;
import org.codehaus.jackson.map.type.SimpleType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.NullNode;
import org.codehaus.jackson.node.TreeTraversingParser;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jackson.util.VersionUtil;

// Referenced classes of package org.codehaus.jackson.map:
//            ObjectMapper, JsonMappingException, JsonDeserializer, DeserializerProvider, 
//            DeserializationConfig, MappingIterator, DeserializationContext

public class ObjectReader extends ObjectCodec
    implements Versioned
{

    private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(org/codehaus/jackson/JsonNode);
    protected final DeserializationConfig _config;
    protected final JsonFactory _jsonFactory;
    protected final DeserializerProvider _provider;
    protected final ConcurrentHashMap _rootDeserializers;
    protected final FormatSchema _schema;
    protected final Object _valueToUpdate;
    protected final JavaType _valueType;

    protected ObjectReader(ObjectMapper objectmapper, DeserializationConfig deserializationconfig)
    {
        this(objectmapper, deserializationconfig, null, null, null);
    }

    protected ObjectReader(ObjectMapper objectmapper, DeserializationConfig deserializationconfig, JavaType javatype, Object obj, FormatSchema formatschema)
    {
        _config = deserializationconfig;
        _rootDeserializers = objectmapper._rootDeserializers;
        _provider = objectmapper._deserializerProvider;
        _jsonFactory = objectmapper._jsonFactory;
        _valueType = javatype;
        _valueToUpdate = obj;
        if (obj != null && javatype.isArrayType())
        {
            throw new IllegalArgumentException("Can not update an array value");
        } else
        {
            _schema = formatschema;
            return;
        }
    }

    protected ObjectReader(ObjectReader objectreader, DeserializationConfig deserializationconfig, JavaType javatype, Object obj, FormatSchema formatschema)
    {
        _config = deserializationconfig;
        _rootDeserializers = objectreader._rootDeserializers;
        _provider = objectreader._provider;
        _jsonFactory = objectreader._jsonFactory;
        _valueType = javatype;
        _valueToUpdate = obj;
        if (obj != null && javatype.isArrayType())
        {
            throw new IllegalArgumentException("Can not update an array value");
        } else
        {
            _schema = formatschema;
            return;
        }
    }

    protected static JsonToken _initForReading(JsonParser jsonparser)
        throws IOException, JsonParseException, JsonMappingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if (jsontoken == null)
        {
            jsontoken = jsonparser.nextToken();
            if (jsontoken == null)
            {
                throw new EOFException("No content to map to Object due to end of input");
            }
        }
        return jsontoken;
    }

    protected Object _bind(JsonParser jsonparser)
        throws IOException, JsonParseException, JsonMappingException
    {
        JsonToken jsontoken = _initForReading(jsonparser);
        Object obj;
        if (jsontoken == JsonToken.VALUE_NULL || jsontoken == JsonToken.END_ARRAY || jsontoken == JsonToken.END_OBJECT)
        {
            obj = _valueToUpdate;
        } else
        {
            DeserializationContext deserializationcontext = _createDeserializationContext(jsonparser, _config);
            if (_valueToUpdate == null)
            {
                obj = _findRootDeserializer(_config, _valueType).deserialize(jsonparser, deserializationcontext);
            } else
            {
                _findRootDeserializer(_config, _valueType).deserialize(jsonparser, deserializationcontext, _valueToUpdate);
                obj = _valueToUpdate;
            }
        }
        jsonparser.clearCurrentToken();
        return obj;
    }

    protected Object _bindAndClose(JsonParser jsonparser)
        throws IOException, JsonParseException, JsonMappingException
    {
        if (_schema != null)
        {
            jsonparser.setSchema(_schema);
        }
        JsonToken jsontoken = _initForReading(jsonparser);
        if (jsontoken != JsonToken.VALUE_NULL && jsontoken != JsonToken.END_ARRAY && jsontoken != JsonToken.END_OBJECT) goto _L2; else goto _L1
_L1:
        Object obj = _valueToUpdate;
_L3:
        Exception exception;
        DeserializationContext deserializationcontext;
        try
        {
            jsonparser.close();
        }
        catch (IOException ioexception1)
        {
            return obj;
        }
        return obj;
_L2:
label0:
        {
            deserializationcontext = _createDeserializationContext(jsonparser, _config);
            if (_valueToUpdate != null)
            {
                break label0;
            }
            obj = _findRootDeserializer(_config, _valueType).deserialize(jsonparser, deserializationcontext);
        }
          goto _L3
        _findRootDeserializer(_config, _valueType).deserialize(jsonparser, deserializationcontext, _valueToUpdate);
        obj = _valueToUpdate;
          goto _L3
        exception;
        try
        {
            jsonparser.close();
        }
        catch (IOException ioexception) { }
        throw exception;
    }

    protected JsonNode _bindAndCloseAsTree(JsonParser jsonparser)
        throws IOException, JsonParseException, JsonMappingException
    {
        if (_schema != null)
        {
            jsonparser.setSchema(_schema);
        }
        JsonNode jsonnode = _bindAsTree(jsonparser);
        Exception exception;
        try
        {
            jsonparser.close();
        }
        catch (IOException ioexception1)
        {
            return jsonnode;
        }
        return jsonnode;
        exception;
        try
        {
            jsonparser.close();
        }
        catch (IOException ioexception) { }
        throw exception;
    }

    protected JsonNode _bindAsTree(JsonParser jsonparser)
        throws IOException, JsonParseException, JsonMappingException
    {
        JsonToken jsontoken = _initForReading(jsonparser);
        Object obj;
        if (jsontoken == JsonToken.VALUE_NULL || jsontoken == JsonToken.END_ARRAY || jsontoken == JsonToken.END_OBJECT)
        {
            obj = NullNode.instance;
        } else
        {
            DeserializationContext deserializationcontext = _createDeserializationContext(jsonparser, _config);
            obj = (JsonNode)_findRootDeserializer(_config, JSON_NODE_TYPE).deserialize(jsonparser, deserializationcontext);
        }
        jsonparser.clearCurrentToken();
        return ((JsonNode) (obj));
    }

    protected DeserializationContext _createDeserializationContext(JsonParser jsonparser, DeserializationConfig deserializationconfig)
    {
        return new StdDeserializationContext(deserializationconfig, jsonparser, _provider);
    }

    protected JsonDeserializer _findRootDeserializer(DeserializationConfig deserializationconfig, JavaType javatype)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = (JsonDeserializer)_rootDeserializers.get(javatype);
        if (jsondeserializer != null)
        {
            return jsondeserializer;
        }
        JsonDeserializer jsondeserializer1 = _provider.findTypedValueDeserializer(deserializationconfig, javatype, null);
        if (jsondeserializer1 == null)
        {
            throw new JsonMappingException((new StringBuilder()).append("Can not find a deserializer for type ").append(javatype).toString());
        } else
        {
            _rootDeserializers.put(javatype, jsondeserializer1);
            return jsondeserializer1;
        }
    }

    public JsonNode createArrayNode()
    {
        return _config.getNodeFactory().arrayNode();
    }

    public JsonNode createObjectNode()
    {
        return _config.getNodeFactory().objectNode();
    }

    public JsonNode readTree(InputStream inputstream)
        throws IOException, JsonProcessingException
    {
        return _bindAndCloseAsTree(_jsonFactory.createJsonParser(inputstream));
    }

    public JsonNode readTree(Reader reader)
        throws IOException, JsonProcessingException
    {
        return _bindAndCloseAsTree(_jsonFactory.createJsonParser(reader));
    }

    public JsonNode readTree(String s)
        throws IOException, JsonProcessingException
    {
        return _bindAndCloseAsTree(_jsonFactory.createJsonParser(s));
    }

    public JsonNode readTree(JsonParser jsonparser)
        throws IOException, JsonProcessingException
    {
        return _bindAsTree(jsonparser);
    }

    public Object readValue(File file)
        throws IOException, JsonProcessingException
    {
        return _bindAndClose(_jsonFactory.createJsonParser(file));
    }

    public Object readValue(InputStream inputstream)
        throws IOException, JsonProcessingException
    {
        return _bindAndClose(_jsonFactory.createJsonParser(inputstream));
    }

    public Object readValue(Reader reader)
        throws IOException, JsonProcessingException
    {
        return _bindAndClose(_jsonFactory.createJsonParser(reader));
    }

    public Object readValue(String s)
        throws IOException, JsonProcessingException
    {
        return _bindAndClose(_jsonFactory.createJsonParser(s));
    }

    public Object readValue(URL url)
        throws IOException, JsonProcessingException
    {
        return _bindAndClose(_jsonFactory.createJsonParser(url));
    }

    public Object readValue(JsonNode jsonnode)
        throws IOException, JsonProcessingException
    {
        return _bindAndClose(treeAsTokens(jsonnode));
    }

    public Object readValue(JsonParser jsonparser)
        throws IOException, JsonProcessingException
    {
        return _bind(jsonparser);
    }

    public Object readValue(JsonParser jsonparser, Class class1)
        throws IOException, JsonProcessingException
    {
        return withType(class1).readValue(jsonparser);
    }

    public Object readValue(JsonParser jsonparser, JavaType javatype)
        throws IOException, JsonProcessingException
    {
        return withType(javatype).readValue(jsonparser);
    }

    public Object readValue(JsonParser jsonparser, TypeReference typereference)
        throws IOException, JsonProcessingException
    {
        return withType(typereference).readValue(jsonparser);
    }

    public Object readValue(byte abyte0[])
        throws IOException, JsonProcessingException
    {
        return _bindAndClose(_jsonFactory.createJsonParser(abyte0));
    }

    public Object readValue(byte abyte0[], int i, int j)
        throws IOException, JsonProcessingException
    {
        return _bindAndClose(_jsonFactory.createJsonParser(abyte0, i, j));
    }

    public MappingIterator readValues(File file)
        throws IOException, JsonProcessingException
    {
        JsonParser jsonparser = _jsonFactory.createJsonParser(file);
        if (_schema != null)
        {
            jsonparser.setSchema(_schema);
        }
        DeserializationContext deserializationcontext = _createDeserializationContext(jsonparser, _config);
        return new MappingIterator(_valueType, jsonparser, deserializationcontext, _findRootDeserializer(_config, _valueType));
    }

    public MappingIterator readValues(InputStream inputstream)
        throws IOException, JsonProcessingException
    {
        JsonParser jsonparser = _jsonFactory.createJsonParser(inputstream);
        if (_schema != null)
        {
            jsonparser.setSchema(_schema);
        }
        DeserializationContext deserializationcontext = _createDeserializationContext(jsonparser, _config);
        return new MappingIterator(_valueType, jsonparser, deserializationcontext, _findRootDeserializer(_config, _valueType));
    }

    public MappingIterator readValues(Reader reader)
        throws IOException, JsonProcessingException
    {
        JsonParser jsonparser = _jsonFactory.createJsonParser(reader);
        if (_schema != null)
        {
            jsonparser.setSchema(_schema);
        }
        DeserializationContext deserializationcontext = _createDeserializationContext(jsonparser, _config);
        return new MappingIterator(_valueType, jsonparser, deserializationcontext, _findRootDeserializer(_config, _valueType));
    }

    public MappingIterator readValues(String s)
        throws IOException, JsonProcessingException
    {
        JsonParser jsonparser = _jsonFactory.createJsonParser(s);
        if (_schema != null)
        {
            jsonparser.setSchema(_schema);
        }
        DeserializationContext deserializationcontext = _createDeserializationContext(jsonparser, _config);
        return new MappingIterator(_valueType, jsonparser, deserializationcontext, _findRootDeserializer(_config, _valueType));
    }

    public MappingIterator readValues(URL url)
        throws IOException, JsonProcessingException
    {
        JsonParser jsonparser = _jsonFactory.createJsonParser(url);
        if (_schema != null)
        {
            jsonparser.setSchema(_schema);
        }
        DeserializationContext deserializationcontext = _createDeserializationContext(jsonparser, _config);
        return new MappingIterator(_valueType, jsonparser, deserializationcontext, _findRootDeserializer(_config, _valueType));
    }

    public MappingIterator readValues(JsonParser jsonparser)
        throws IOException, JsonProcessingException
    {
        DeserializationContext deserializationcontext = _createDeserializationContext(jsonparser, _config);
        return new MappingIterator(_valueType, jsonparser, deserializationcontext, _findRootDeserializer(_config, _valueType));
    }

    public MappingIterator readValues(byte abyte0[], int i, int j)
        throws IOException, JsonProcessingException
    {
        JsonParser jsonparser = _jsonFactory.createJsonParser(abyte0, i, j);
        if (_schema != null)
        {
            jsonparser.setSchema(_schema);
        }
        DeserializationContext deserializationcontext = _createDeserializationContext(jsonparser, _config);
        return new MappingIterator(_valueType, jsonparser, deserializationcontext, _findRootDeserializer(_config, _valueType));
    }

    public JsonParser treeAsTokens(JsonNode jsonnode)
    {
        return new TreeTraversingParser(jsonnode, this);
    }

    public Object treeToValue(JsonNode jsonnode, Class class1)
        throws IOException, JsonProcessingException
    {
        return readValue(treeAsTokens(jsonnode), class1);
    }

    public Version version()
    {
        return VersionUtil.versionFor(getClass());
    }

    public ObjectReader withNodeFactory(JsonNodeFactory jsonnodefactory)
    {
        if (jsonnodefactory == _config.getNodeFactory())
        {
            return this;
        } else
        {
            return new ObjectReader(this, _config.withNodeFactory(jsonnodefactory), _valueType, _valueToUpdate, _schema);
        }
    }

    public ObjectReader withSchema(FormatSchema formatschema)
    {
        if (_schema == formatschema)
        {
            return this;
        } else
        {
            return new ObjectReader(this, _config, _valueType, _valueToUpdate, formatschema);
        }
    }

    public ObjectReader withType(Class class1)
    {
        return withType(_config.constructType(class1));
    }

    public ObjectReader withType(Type type)
    {
        return withType(_config.getTypeFactory().constructType(type));
    }

    public ObjectReader withType(JavaType javatype)
    {
        if (javatype == _valueType)
        {
            return this;
        } else
        {
            return new ObjectReader(this, _config, javatype, _valueToUpdate, _schema);
        }
    }

    public ObjectReader withType(TypeReference typereference)
    {
        return withType(_config.getTypeFactory().constructType(typereference.getType()));
    }

    public ObjectReader withValueToUpdate(Object obj)
    {
        if (obj == _valueToUpdate)
        {
            return this;
        }
        if (obj == null)
        {
            throw new IllegalArgumentException("cat not update null value");
        } else
        {
            JavaType javatype = _config.constructType(obj.getClass());
            return new ObjectReader(this, _config, javatype, obj, _schema);
        }
    }

    public void writeTree(JsonGenerator jsongenerator, JsonNode jsonnode)
        throws IOException, JsonProcessingException
    {
        throw new UnsupportedOperationException("Not implemented for ObjectReader");
    }

    public void writeValue(JsonGenerator jsongenerator, Object obj)
        throws IOException, JsonProcessingException
    {
        throw new UnsupportedOperationException("Not implemented for ObjectReader");
    }

}
