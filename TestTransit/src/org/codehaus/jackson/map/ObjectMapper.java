// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.DateFormat;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import org.codehaus.jackson.FormatSchema;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.PrettyPrinter;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.Versioned;
import org.codehaus.jackson.io.SegmentedStringWriter;
import org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import org.codehaus.jackson.map.deser.StdDeserializationContext;
import org.codehaus.jackson.map.deser.StdDeserializerProvider;
import org.codehaus.jackson.map.introspect.BasicClassIntrospector;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.map.jsontype.SubtypeResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.jsontype.impl.StdSubtypeResolver;
import org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder;
import org.codehaus.jackson.map.ser.BeanSerializerFactory;
import org.codehaus.jackson.map.ser.BeanSerializerModifier;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.StdSerializerProvider;
import org.codehaus.jackson.map.type.SimpleType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.type.TypeModifier;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.NullNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.TreeTraversingParser;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.DefaultPrettyPrinter;
import org.codehaus.jackson.util.TokenBuffer;
import org.codehaus.jackson.util.VersionUtil;

// Referenced classes of package org.codehaus.jackson.map:
//            MappingJsonFactory, SerializationConfig, DeserializationConfig, JsonMappingException, 
//            SerializerProvider, JsonDeserializer, DeserializerProvider, ObjectWriter, 
//            MappingIterator, ObjectReader, Module, AnnotationIntrospector, 
//            ClassIntrospector, SerializerFactory, DeserializationContext, HandlerInstantiator, 
//            PropertyNamingStrategy, BeanProperty, TypeDeserializer, TypeSerializer, 
//            AbstractTypeResolver, Deserializers, KeyDeserializers, Serializers

public class ObjectMapper extends ObjectCodec
    implements Versioned
{
    public static class DefaultTypeResolverBuilder extends StdTypeResolverBuilder
    {

        protected final DefaultTyping _appliesFor;

        public TypeDeserializer buildTypeDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, Collection collection, BeanProperty beanproperty)
        {
            if (useForType(javatype))
            {
                return super.buildTypeDeserializer(deserializationconfig, javatype, collection, beanproperty);
            } else
            {
                return null;
            }
        }

        public TypeSerializer buildTypeSerializer(SerializationConfig serializationconfig, JavaType javatype, Collection collection, BeanProperty beanproperty)
        {
            if (useForType(javatype))
            {
                return super.buildTypeSerializer(serializationconfig, javatype, collection, beanproperty);
            } else
            {
                return null;
            }
        }

        public boolean useForType(JavaType javatype)
        {
            static class _cls2
            {

                static final int $SwitchMap$org$codehaus$jackson$map$ObjectMapper$DefaultTyping[];

                static 
                {
                    $SwitchMap$org$codehaus$jackson$map$ObjectMapper$DefaultTyping = new int[DefaultTyping.values().length];
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$map$ObjectMapper$DefaultTyping[DefaultTyping.NON_CONCRETE_AND_ARRAYS.ordinal()] = 1;
                    }
                    catch (NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$map$ObjectMapper$DefaultTyping[DefaultTyping.OBJECT_AND_NON_CONCRETE.ordinal()] = 2;
                    }
                    catch (NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$org$codehaus$jackson$map$ObjectMapper$DefaultTyping[DefaultTyping.NON_FINAL.ordinal()] = 3;
                    }
                    catch (NoSuchFieldError nosuchfielderror2)
                    {
                        return;
                    }
                }
            }

            _cls2..SwitchMap.org.codehaus.jackson.map.ObjectMapper.DefaultTyping[_appliesFor.ordinal()];
            JVM INSTR tableswitch 1 3: default 36
        //                       1 47
        //                       2 59
        //                       3 83;
               goto _L1 _L2 _L3 _L4
_L1:
            if (javatype.getRawClass() != java/lang/Object) goto _L6; else goto _L5
_L5:
            return true;
_L2:
            if (javatype.isArrayType())
            {
                javatype = javatype.getContentType();
            }
_L3:
            if (javatype.getRawClass() == java/lang/Object) goto _L8; else goto _L7
_L7:
            boolean flag;
            boolean flag1;
            flag1 = javatype.isConcrete();
            flag = false;
            if (flag1) goto _L9; else goto _L8
_L8:
            flag = true;
_L9:
            return flag;
_L4:
            if (javatype.isArrayType())
            {
                javatype = javatype.getContentType();
            }
            if (javatype.isFinal())
            {
                return false;
            }
            if (true) goto _L5; else goto _L6
_L6:
            return false;
        }

        public DefaultTypeResolverBuilder(DefaultTyping defaulttyping)
        {
            _appliesFor = defaulttyping;
        }
    }

    public static final class DefaultTyping extends Enum
    {

        private static final DefaultTyping $VALUES[];
        public static final DefaultTyping JAVA_LANG_OBJECT;
        public static final DefaultTyping NON_CONCRETE_AND_ARRAYS;
        public static final DefaultTyping NON_FINAL;
        public static final DefaultTyping OBJECT_AND_NON_CONCRETE;

        public static DefaultTyping valueOf(String s)
        {
            return (DefaultTyping)Enum.valueOf(org/codehaus/jackson/map/ObjectMapper$DefaultTyping, s);
        }

        public static DefaultTyping[] values()
        {
            return (DefaultTyping[])$VALUES.clone();
        }

        static 
        {
            JAVA_LANG_OBJECT = new DefaultTyping("JAVA_LANG_OBJECT", 0);
            OBJECT_AND_NON_CONCRETE = new DefaultTyping("OBJECT_AND_NON_CONCRETE", 1);
            NON_CONCRETE_AND_ARRAYS = new DefaultTyping("NON_CONCRETE_AND_ARRAYS", 2);
            NON_FINAL = new DefaultTyping("NON_FINAL", 3);
            DefaultTyping adefaulttyping[] = new DefaultTyping[4];
            adefaulttyping[0] = JAVA_LANG_OBJECT;
            adefaulttyping[1] = OBJECT_AND_NON_CONCRETE;
            adefaulttyping[2] = NON_CONCRETE_AND_ARRAYS;
            adefaulttyping[3] = NON_FINAL;
            $VALUES = adefaulttyping;
        }

        private DefaultTyping(String s, int i)
        {
            super(s, i);
        }
    }


    protected static final AnnotationIntrospector DEFAULT_ANNOTATION_INTROSPECTOR = new JacksonAnnotationIntrospector();
    protected static final ClassIntrospector DEFAULT_INTROSPECTOR;
    private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(org/codehaus/jackson/JsonNode);
    protected static final VisibilityChecker STD_VISIBILITY_CHECKER = org.codehaus.jackson.map.introspect.VisibilityChecker.Std.defaultInstance();
    protected DeserializationConfig _deserializationConfig;
    protected DeserializerProvider _deserializerProvider;
    protected final JsonFactory _jsonFactory;
    protected final ConcurrentHashMap _rootDeserializers;
    protected SerializationConfig _serializationConfig;
    protected SerializerFactory _serializerFactory;
    protected SerializerProvider _serializerProvider;
    protected SubtypeResolver _subtypeResolver;
    protected TypeFactory _typeFactory;

    public ObjectMapper()
    {
        this(null, null, null);
    }

    public ObjectMapper(JsonFactory jsonfactory)
    {
        this(jsonfactory, null, null);
    }

    public ObjectMapper(JsonFactory jsonfactory, SerializerProvider serializerprovider, DeserializerProvider deserializerprovider)
    {
        this(jsonfactory, serializerprovider, deserializerprovider, null, null);
    }

    public ObjectMapper(JsonFactory jsonfactory, SerializerProvider serializerprovider, DeserializerProvider deserializerprovider, SerializationConfig serializationconfig, DeserializationConfig deserializationconfig)
    {
        _rootDeserializers = new ConcurrentHashMap(64, 0.6F, 2);
        if (jsonfactory == null)
        {
            jsonfactory = new MappingJsonFactory(this);
        }
        _jsonFactory = jsonfactory;
        _typeFactory = TypeFactory.defaultInstance();
        if (serializationconfig == null)
        {
            serializationconfig = new SerializationConfig(DEFAULT_INTROSPECTOR, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, null, null, _typeFactory, null);
        }
        _serializationConfig = serializationconfig;
        if (deserializationconfig == null)
        {
            deserializationconfig = new DeserializationConfig(DEFAULT_INTROSPECTOR, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, null, null, _typeFactory, null);
        }
        _deserializationConfig = deserializationconfig;
        if (serializerprovider == null)
        {
            serializerprovider = new StdSerializerProvider();
        }
        _serializerProvider = serializerprovider;
        if (deserializerprovider == null)
        {
            deserializerprovider = new StdDeserializerProvider();
        }
        _deserializerProvider = deserializerprovider;
        _serializerFactory = BeanSerializerFactory.instance;
    }

    public ObjectMapper(SerializerFactory serializerfactory)
    {
        this(null, null, null);
        setSerializerFactory(serializerfactory);
    }

    private final void _configAndWriteCloseable(JsonGenerator jsongenerator, Object obj, SerializationConfig serializationconfig)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        Closeable closeable = (Closeable)obj;
        _serializerProvider.serializeValue(serializationconfig, jsongenerator, obj, _serializerFactory);
        JsonGenerator jsongenerator1;
        jsongenerator1 = jsongenerator;
        jsongenerator = null;
        jsongenerator1.close();
        Closeable closeable1;
        closeable1 = closeable;
        closeable = null;
        closeable1.close();
        Exception exception;
        if (false)
        {
            try
            {
                null.close();
            }
            catch (IOException ioexception3) { }
        }
        if (true)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        null.close();
        return;
        exception;
        IOException ioexception2;
        if (jsongenerator != null)
        {
            try
            {
                jsongenerator.close();
            }
            catch (IOException ioexception1) { }
        }
        if (closeable != null)
        {
            try
            {
                closeable.close();
            }
            catch (IOException ioexception) { }
        }
        throw exception;
        ioexception2;
    }

    private final void _writeCloseableValue(JsonGenerator jsongenerator, Object obj, SerializationConfig serializationconfig)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        Closeable closeable = (Closeable)obj;
        _serializerProvider.serializeValue(serializationconfig, jsongenerator, obj, _serializerFactory);
        if (serializationconfig.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE))
        {
            jsongenerator.flush();
        }
        Closeable closeable1;
        closeable1 = closeable;
        closeable = null;
        closeable1.close();
        if (true)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        null.close();
        return;
        Exception exception;
        exception;
        IOException ioexception1;
        if (closeable != null)
        {
            try
            {
                closeable.close();
            }
            catch (IOException ioexception) { }
        }
        throw exception;
        ioexception1;
    }

    protected final void _configAndWriteValue(JsonGenerator jsongenerator, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        SerializationConfig serializationconfig;
        serializationconfig = copySerializationConfig();
        if (serializationconfig.isEnabled(SerializationConfig.Feature.INDENT_OUTPUT))
        {
            jsongenerator.useDefaultPrettyPrinter();
        }
        if (!serializationconfig.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) || !(obj instanceof Closeable)) goto _L2; else goto _L1
_L1:
        _configAndWriteCloseable(jsongenerator, obj, serializationconfig);
_L4:
        return;
_L2:
        boolean flag = false;
        _serializerProvider.serializeValue(serializationconfig, jsongenerator, obj, _serializerFactory);
        flag = true;
        jsongenerator.close();
        if (flag) goto _L4; else goto _L3
_L3:
        try
        {
            jsongenerator.close();
            return;
        }
        catch (IOException ioexception1)
        {
            return;
        }
        Exception exception;
        exception;
        if (!flag)
        {
            try
            {
                jsongenerator.close();
            }
            catch (IOException ioexception) { }
        }
        throw exception;
    }

    protected final void _configAndWriteValue(JsonGenerator jsongenerator, Object obj, Class class1)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        SerializationConfig serializationconfig;
        serializationconfig = copySerializationConfig().withView(class1);
        if (serializationconfig.isEnabled(SerializationConfig.Feature.INDENT_OUTPUT))
        {
            jsongenerator.useDefaultPrettyPrinter();
        }
        if (!serializationconfig.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) || !(obj instanceof Closeable)) goto _L2; else goto _L1
_L1:
        _configAndWriteCloseable(jsongenerator, obj, serializationconfig);
_L4:
        return;
_L2:
        boolean flag = false;
        _serializerProvider.serializeValue(serializationconfig, jsongenerator, obj, _serializerFactory);
        flag = true;
        jsongenerator.close();
        if (flag) goto _L4; else goto _L3
_L3:
        try
        {
            jsongenerator.close();
            return;
        }
        catch (IOException ioexception1)
        {
            return;
        }
        Exception exception;
        exception;
        if (!flag)
        {
            try
            {
                jsongenerator.close();
            }
            catch (IOException ioexception) { }
        }
        throw exception;
    }

    protected Object _convert(Object obj, JavaType javatype)
        throws IllegalArgumentException
    {
        if (obj == null)
        {
            return null;
        }
        TokenBuffer tokenbuffer = new TokenBuffer(this);
        Object obj1;
        try
        {
            writeValue(tokenbuffer, obj);
            JsonParser jsonparser = tokenbuffer.asParser();
            obj1 = readValue(jsonparser, javatype);
            jsonparser.close();
        }
        catch (IOException ioexception)
        {
            throw new IllegalArgumentException(ioexception.getMessage(), ioexception);
        }
        return obj1;
    }

    protected DeserializationContext _createDeserializationContext(JsonParser jsonparser, DeserializationConfig deserializationconfig)
    {
        return new StdDeserializationContext(deserializationconfig, jsonparser, _deserializerProvider);
    }

    protected PrettyPrinter _defaultPrettyPrinter()
    {
        return new DefaultPrettyPrinter();
    }

    protected JsonDeserializer _findRootDeserializer(DeserializationConfig deserializationconfig, JavaType javatype)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = (JsonDeserializer)_rootDeserializers.get(javatype);
        if (jsondeserializer != null)
        {
            return jsondeserializer;
        }
        JsonDeserializer jsondeserializer1 = _deserializerProvider.findTypedValueDeserializer(deserializationconfig, javatype, null);
        if (jsondeserializer1 == null)
        {
            throw new JsonMappingException((new StringBuilder()).append("Can not find a deserializer for type ").append(javatype).toString());
        } else
        {
            _rootDeserializers.put(javatype, jsondeserializer1);
            return jsondeserializer1;
        }
    }

    protected JsonToken _initForReading(JsonParser jsonparser)
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

    protected Object _readMapAndClose(JsonParser jsonparser, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        JsonToken jsontoken = _initForReading(jsonparser);
        if (jsontoken != JsonToken.VALUE_NULL && jsontoken != JsonToken.END_ARRAY && jsontoken != JsonToken.END_OBJECT) goto _L2; else goto _L1
_L4:
        jsonparser.clearCurrentToken();
        Object obj;
        Exception exception;
        DeserializationConfig deserializationconfig;
        DeserializationContext deserializationcontext;
        Object obj1;
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
        deserializationconfig = copyDeserializationConfig();
        deserializationcontext = _createDeserializationContext(jsonparser, deserializationconfig);
        obj1 = _findRootDeserializer(deserializationconfig, javatype).deserialize(jsonparser, deserializationcontext);
        obj = obj1;
        continue; /* Loop/switch isn't completed */
        exception;
        try
        {
            jsonparser.close();
        }
        catch (IOException ioexception) { }
        throw exception;
_L1:
        obj = null;
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected Object _readValue(DeserializationConfig deserializationconfig, JsonParser jsonparser, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        JsonToken jsontoken = _initForReading(jsonparser);
        Object obj;
        if (jsontoken == JsonToken.VALUE_NULL || jsontoken == JsonToken.END_ARRAY || jsontoken == JsonToken.END_OBJECT)
        {
            obj = null;
        } else
        {
            DeserializationContext deserializationcontext = _createDeserializationContext(jsonparser, deserializationconfig);
            obj = _findRootDeserializer(deserializationconfig, javatype).deserialize(jsonparser, deserializationcontext);
        }
        jsonparser.clearCurrentToken();
        return obj;
    }

    public boolean canDeserialize(JavaType javatype)
    {
        return _deserializerProvider.hasValueDeserializerFor(copyDeserializationConfig(), javatype);
    }

    public boolean canSerialize(Class class1)
    {
        return _serializerProvider.hasSerializerFor(copySerializationConfig(), class1, _serializerFactory);
    }

    public ObjectMapper configure(org.codehaus.jackson.JsonGenerator.Feature feature, boolean flag)
    {
        _jsonFactory.configure(feature, flag);
        return this;
    }

    public ObjectMapper configure(org.codehaus.jackson.JsonParser.Feature feature, boolean flag)
    {
        _jsonFactory.configure(feature, flag);
        return this;
    }

    public ObjectMapper configure(DeserializationConfig.Feature feature, boolean flag)
    {
        _deserializationConfig.set(feature, flag);
        return this;
    }

    public ObjectMapper configure(SerializationConfig.Feature feature, boolean flag)
    {
        _serializationConfig.set(feature, flag);
        return this;
    }

    public JavaType constructType(Type type)
    {
        return _typeFactory.constructType(type);
    }

    public Object convertValue(Object obj, Class class1)
        throws IllegalArgumentException
    {
        return _convert(obj, _typeFactory.constructType(class1));
    }

    public Object convertValue(Object obj, JavaType javatype)
        throws IllegalArgumentException
    {
        return _convert(obj, javatype);
    }

    public Object convertValue(Object obj, TypeReference typereference)
        throws IllegalArgumentException
    {
        return _convert(obj, _typeFactory.constructType(typereference));
    }

    public DeserializationConfig copyDeserializationConfig()
    {
        return _deserializationConfig.createUnshared(_subtypeResolver);
    }

    public SerializationConfig copySerializationConfig()
    {
        return _serializationConfig.createUnshared(_subtypeResolver);
    }

    public volatile JsonNode createArrayNode()
    {
        return createArrayNode();
    }

    public ArrayNode createArrayNode()
    {
        return _deserializationConfig.getNodeFactory().arrayNode();
    }

    public volatile JsonNode createObjectNode()
    {
        return createObjectNode();
    }

    public ObjectNode createObjectNode()
    {
        return _deserializationConfig.getNodeFactory().objectNode();
    }

    public ObjectWriter defaultPrettyPrintingWriter()
    {
        return new ObjectWriter(this, copySerializationConfig(), null, _defaultPrettyPrinter());
    }

    public ObjectMapper disableDefaultTyping()
    {
        return setDefaultTyping(null);
    }

    public ObjectMapper enableDefaultTyping()
    {
        return enableDefaultTyping(DefaultTyping.OBJECT_AND_NON_CONCRETE);
    }

    public ObjectMapper enableDefaultTyping(DefaultTyping defaulttyping)
    {
        return enableDefaultTyping(defaulttyping, org.codehaus.jackson.annotate.JsonTypeInfo.As.WRAPPER_ARRAY);
    }

    public ObjectMapper enableDefaultTyping(DefaultTyping defaulttyping, org.codehaus.jackson.annotate.JsonTypeInfo.As as)
    {
        return setDefaultTyping((new DefaultTypeResolverBuilder(defaulttyping)).init(org.codehaus.jackson.annotate.JsonTypeInfo.Id.CLASS, null).inclusion(as));
    }

    public ObjectMapper enableDefaultTypingAsProperty(DefaultTyping defaulttyping, String s)
    {
        return setDefaultTyping((new DefaultTypeResolverBuilder(defaulttyping)).init(org.codehaus.jackson.annotate.JsonTypeInfo.Id.CLASS, null).inclusion(org.codehaus.jackson.annotate.JsonTypeInfo.As.PROPERTY).typeProperty(s));
    }

    public ObjectWriter filteredWriter(FilterProvider filterprovider)
    {
        return new ObjectWriter(this, copySerializationConfig().withFilters(filterprovider));
    }

    public JsonSchema generateJsonSchema(Class class1)
        throws JsonMappingException
    {
        return generateJsonSchema(class1, copySerializationConfig());
    }

    public JsonSchema generateJsonSchema(Class class1, SerializationConfig serializationconfig)
        throws JsonMappingException
    {
        return _serializerProvider.generateJsonSchema(class1, serializationconfig, _serializerFactory);
    }

    public DeserializationConfig getDeserializationConfig()
    {
        return _deserializationConfig;
    }

    public DeserializerProvider getDeserializerProvider()
    {
        return _deserializerProvider;
    }

    public JsonFactory getJsonFactory()
    {
        return _jsonFactory;
    }

    public JsonNodeFactory getNodeFactory()
    {
        return _deserializationConfig.getNodeFactory();
    }

    public SerializationConfig getSerializationConfig()
    {
        return _serializationConfig;
    }

    public SerializerProvider getSerializerProvider()
    {
        return _serializerProvider;
    }

    public SubtypeResolver getSubtypeResolver()
    {
        if (_subtypeResolver == null)
        {
            _subtypeResolver = new StdSubtypeResolver();
        }
        return _subtypeResolver;
    }

    public TypeFactory getTypeFactory()
    {
        return _typeFactory;
    }

    public VisibilityChecker getVisibilityChecker()
    {
        return _serializationConfig.getDefaultVisibilityChecker();
    }

    public ObjectWriter prettyPrintingWriter(PrettyPrinter prettyprinter)
    {
        if (prettyprinter == null)
        {
            prettyprinter = ObjectWriter.NULL_PRETTY_PRINTER;
        }
        return new ObjectWriter(this, copySerializationConfig(), null, prettyprinter);
    }

    public JsonNode readTree(InputStream inputstream)
        throws IOException, JsonProcessingException
    {
        Object obj = (JsonNode)readValue(inputstream, JSON_NODE_TYPE);
        if (obj == null)
        {
            obj = NullNode.instance;
        }
        return ((JsonNode) (obj));
    }

    public JsonNode readTree(Reader reader1)
        throws IOException, JsonProcessingException
    {
        Object obj = (JsonNode)readValue(reader1, JSON_NODE_TYPE);
        if (obj == null)
        {
            obj = NullNode.instance;
        }
        return ((JsonNode) (obj));
    }

    public JsonNode readTree(String s)
        throws IOException, JsonProcessingException
    {
        Object obj = (JsonNode)readValue(s, JSON_NODE_TYPE);
        if (obj == null)
        {
            obj = NullNode.instance;
        }
        return ((JsonNode) (obj));
    }

    public JsonNode readTree(JsonParser jsonparser)
        throws IOException, JsonProcessingException
    {
        return readTree(jsonparser, copyDeserializationConfig());
    }

    public JsonNode readTree(JsonParser jsonparser, DeserializationConfig deserializationconfig)
        throws IOException, JsonProcessingException
    {
        Object obj = (JsonNode)_readValue(deserializationconfig, jsonparser, JSON_NODE_TYPE);
        if (obj == null)
        {
            obj = NullNode.instance;
        }
        return ((JsonNode) (obj));
    }

    public Object readValue(File file, Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(file), _typeFactory.constructType(class1));
    }

    public Object readValue(File file, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(file), javatype);
    }

    public Object readValue(File file, TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(file), _typeFactory.constructType(typereference));
    }

    public Object readValue(InputStream inputstream, Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(inputstream), _typeFactory.constructType(class1));
    }

    public Object readValue(InputStream inputstream, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(inputstream), javatype);
    }

    public Object readValue(InputStream inputstream, TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(inputstream), _typeFactory.constructType(typereference));
    }

    public Object readValue(Reader reader1, Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(reader1), _typeFactory.constructType(class1));
    }

    public Object readValue(Reader reader1, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(reader1), javatype);
    }

    public Object readValue(Reader reader1, TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(reader1), _typeFactory.constructType(typereference));
    }

    public Object readValue(String s, Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(s), _typeFactory.constructType(class1));
    }

    public Object readValue(String s, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(s), javatype);
    }

    public Object readValue(String s, TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(s), _typeFactory.constructType(typereference));
    }

    public Object readValue(URL url, Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(url), _typeFactory.constructType(class1));
    }

    public Object readValue(URL url, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(url), javatype);
    }

    public Object readValue(URL url, TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(url), _typeFactory.constructType(typereference));
    }

    public Object readValue(JsonNode jsonnode, Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readValue(copyDeserializationConfig(), treeAsTokens(jsonnode), _typeFactory.constructType(class1));
    }

    public Object readValue(JsonNode jsonnode, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readValue(copyDeserializationConfig(), treeAsTokens(jsonnode), javatype);
    }

    public Object readValue(JsonNode jsonnode, TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readValue(copyDeserializationConfig(), treeAsTokens(jsonnode), _typeFactory.constructType(typereference));
    }

    public Object readValue(JsonParser jsonparser, Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readValue(copyDeserializationConfig(), jsonparser, _typeFactory.constructType(class1));
    }

    public Object readValue(JsonParser jsonparser, Class class1, DeserializationConfig deserializationconfig)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readValue(deserializationconfig, jsonparser, _typeFactory.constructType(class1));
    }

    public Object readValue(JsonParser jsonparser, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readValue(copyDeserializationConfig(), jsonparser, javatype);
    }

    public Object readValue(JsonParser jsonparser, JavaType javatype, DeserializationConfig deserializationconfig)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readValue(deserializationconfig, jsonparser, javatype);
    }

    public Object readValue(JsonParser jsonparser, TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readValue(copyDeserializationConfig(), jsonparser, _typeFactory.constructType(typereference));
    }

    public Object readValue(JsonParser jsonparser, TypeReference typereference, DeserializationConfig deserializationconfig)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readValue(deserializationconfig, jsonparser, _typeFactory.constructType(typereference));
    }

    public Object readValue(byte abyte0[], int i, int j, Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(abyte0, i, j), _typeFactory.constructType(class1));
    }

    public Object readValue(byte abyte0[], int i, int j, JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(abyte0, i, j), javatype);
    }

    public Object readValue(byte abyte0[], int i, int j, TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(abyte0, i, j), _typeFactory.constructType(typereference));
    }

    public Object readValue(byte abyte0[], Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(abyte0), _typeFactory.constructType(class1));
    }

    public Object readValue(byte abyte0[], JavaType javatype)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(abyte0), javatype);
    }

    public Object readValue(byte abyte0[], TypeReference typereference)
        throws IOException, JsonParseException, JsonMappingException
    {
        return _readMapAndClose(_jsonFactory.createJsonParser(abyte0), _typeFactory.constructType(typereference));
    }

    public MappingIterator readValues(JsonParser jsonparser, Class class1)
        throws IOException, JsonProcessingException
    {
        return readValues(jsonparser, _typeFactory.constructType(class1));
    }

    public MappingIterator readValues(JsonParser jsonparser, JavaType javatype)
        throws IOException, JsonProcessingException
    {
        DeserializationConfig deserializationconfig = copyDeserializationConfig();
        return new MappingIterator(javatype, jsonparser, _createDeserializationContext(jsonparser, deserializationconfig), _findRootDeserializer(deserializationconfig, javatype));
    }

    public MappingIterator readValues(JsonParser jsonparser, TypeReference typereference)
        throws IOException, JsonProcessingException
    {
        return readValues(jsonparser, _typeFactory.constructType(typereference));
    }

    public ObjectReader reader()
    {
        return new ObjectReader(this, copyDeserializationConfig());
    }

    public ObjectReader reader(Class class1)
    {
        return reader(_typeFactory.constructType(class1));
    }

    public ObjectReader reader(JsonNodeFactory jsonnodefactory)
    {
        return (new ObjectReader(this, copyDeserializationConfig())).withNodeFactory(jsonnodefactory);
    }

    public ObjectReader reader(JavaType javatype)
    {
        return new ObjectReader(this, copyDeserializationConfig(), javatype, null, null);
    }

    public ObjectReader reader(TypeReference typereference)
    {
        return reader(_typeFactory.constructType(typereference));
    }

    public void registerModule(Module module)
    {
        if (module.getModuleName() == null)
        {
            throw new IllegalArgumentException("Module without defined name");
        }
        if (module.version() == null)
        {
            throw new IllegalArgumentException("Module without defined version");
        } else
        {
            module.setupModule(new Module.SetupContext() {

                final ObjectMapper this$0;
                final ObjectMapper val$mapper;

                public void addAbstractTypeResolver(AbstractTypeResolver abstracttyperesolver)
                {
                    mapper._deserializerProvider = mapper._deserializerProvider.withAbstractTypeResolver(abstracttyperesolver);
                }

                public void addBeanDeserializerModifier(BeanDeserializerModifier beandeserializermodifier)
                {
                    mapper._deserializerProvider = mapper._deserializerProvider.withDeserializerModifier(beandeserializermodifier);
                }

                public void addBeanSerializerModifier(BeanSerializerModifier beanserializermodifier)
                {
                    mapper._serializerFactory = mapper._serializerFactory.withSerializerModifier(beanserializermodifier);
                }

                public void addDeserializers(Deserializers deserializers)
                {
                    mapper._deserializerProvider = mapper._deserializerProvider.withAdditionalDeserializers(deserializers);
                }

                public void addKeyDeserializers(KeyDeserializers keydeserializers)
                {
                    mapper._deserializerProvider = mapper._deserializerProvider.withAdditionalKeyDeserializers(keydeserializers);
                }

                public void addKeySerializers(Serializers serializers)
                {
                    mapper._serializerFactory = mapper._serializerFactory.withAdditionalKeySerializers(serializers);
                }

                public void addSerializers(Serializers serializers)
                {
                    mapper._serializerFactory = mapper._serializerFactory.withAdditionalSerializers(serializers);
                }

                public void addTypeModifier(TypeModifier typemodifier)
                {
                    TypeFactory typefactory = mapper._typeFactory.withModifier(typemodifier);
                    mapper.setTypeFactory(typefactory);
                }

                public void appendAnnotationIntrospector(AnnotationIntrospector annotationintrospector)
                {
                    mapper._deserializationConfig.appendAnnotationIntrospector(annotationintrospector);
                    mapper._serializationConfig.appendAnnotationIntrospector(annotationintrospector);
                }

                public DeserializationConfig getDeserializationConfig()
                {
                    return mapper.getDeserializationConfig();
                }

                public Version getMapperVersion()
                {
                    return version();
                }

                public SerializationConfig getSerializationConfig()
                {
                    return mapper.getSerializationConfig();
                }

                public void insertAnnotationIntrospector(AnnotationIntrospector annotationintrospector)
                {
                    mapper._deserializationConfig.insertAnnotationIntrospector(annotationintrospector);
                    mapper._serializationConfig.insertAnnotationIntrospector(annotationintrospector);
                }

                public void setMixInAnnotations(Class class1, Class class2)
                {
                    mapper._deserializationConfig.addMixInAnnotations(class1, class2);
                    mapper._serializationConfig.addMixInAnnotations(class1, class2);
                }

            
            {
                this$0 = ObjectMapper.this;
                mapper = objectmapper1;
                super();
            }
            });
            return;
        }
    }

    public transient void registerSubtypes(Class aclass[])
    {
        getSubtypeResolver().registerSubtypes(aclass);
    }

    public transient void registerSubtypes(NamedType anamedtype[])
    {
        getSubtypeResolver().registerSubtypes(anamedtype);
    }

    public ObjectReader schemaBasedReader(FormatSchema formatschema)
    {
        return new ObjectReader(this, copyDeserializationConfig(), null, null, formatschema);
    }

    public ObjectWriter schemaBasedWriter(FormatSchema formatschema)
    {
        return new ObjectWriter(this, copySerializationConfig(), formatschema);
    }

    public ObjectMapper setAnnotationIntrospector(AnnotationIntrospector annotationintrospector)
    {
        _serializationConfig = _serializationConfig.withAnnotationIntrospector(annotationintrospector);
        _deserializationConfig = _deserializationConfig.withAnnotationIntrospector(annotationintrospector);
        return this;
    }

    public void setDateFormat(DateFormat dateformat)
    {
        _deserializationConfig = _deserializationConfig.withDateFormat(dateformat);
        _serializationConfig = _serializationConfig.withDateFormat(dateformat);
    }

    public ObjectMapper setDefaultTyping(TypeResolverBuilder typeresolverbuilder)
    {
        _deserializationConfig = _deserializationConfig.withTypeResolverBuilder(typeresolverbuilder);
        _serializationConfig = _serializationConfig.withTypeResolverBuilder(typeresolverbuilder);
        return this;
    }

    public ObjectMapper setDeserializationConfig(DeserializationConfig deserializationconfig)
    {
        _deserializationConfig = deserializationconfig;
        return this;
    }

    public ObjectMapper setDeserializerProvider(DeserializerProvider deserializerprovider)
    {
        _deserializerProvider = deserializerprovider;
        return this;
    }

    public void setFilters(FilterProvider filterprovider)
    {
        _serializationConfig = _serializationConfig.withFilters(filterprovider);
    }

    public void setHandlerInstantiator(HandlerInstantiator handlerinstantiator)
    {
        _deserializationConfig = _deserializationConfig.withHandlerInstantiator(handlerinstantiator);
        _serializationConfig = _serializationConfig.withHandlerInstantiator(handlerinstantiator);
    }

    public ObjectMapper setNodeFactory(JsonNodeFactory jsonnodefactory)
    {
        _deserializationConfig = _deserializationConfig.withNodeFactory(jsonnodefactory);
        return this;
    }

    public ObjectMapper setPropertyNamingStrategy(PropertyNamingStrategy propertynamingstrategy)
    {
        _serializationConfig = _serializationConfig.withPropertyNamingStrategy(propertynamingstrategy);
        _deserializationConfig = _deserializationConfig.withPropertyNamingStrategy(propertynamingstrategy);
        return this;
    }

    public ObjectMapper setSerializationConfig(SerializationConfig serializationconfig)
    {
        _serializationConfig = serializationconfig;
        return this;
    }

    public ObjectMapper setSerializerFactory(SerializerFactory serializerfactory)
    {
        _serializerFactory = serializerfactory;
        return this;
    }

    public ObjectMapper setSerializerProvider(SerializerProvider serializerprovider)
    {
        _serializerProvider = serializerprovider;
        return this;
    }

    public void setSubtypeResolver(SubtypeResolver subtyperesolver)
    {
        _subtypeResolver = subtyperesolver;
    }

    public ObjectMapper setTypeFactory(TypeFactory typefactory)
    {
        _typeFactory = typefactory;
        _deserializationConfig = _deserializationConfig.withTypeFactory(typefactory);
        _serializationConfig = _serializationConfig.withTypeFactory(typefactory);
        return this;
    }

    public void setVisibilityChecker(VisibilityChecker visibilitychecker)
    {
        _deserializationConfig = _deserializationConfig.withVisibilityChecker(visibilitychecker);
        _serializationConfig = _serializationConfig.withVisibilityChecker(visibilitychecker);
    }

    public JsonParser treeAsTokens(JsonNode jsonnode)
    {
        return new TreeTraversingParser(jsonnode, this);
    }

    public Object treeToValue(JsonNode jsonnode, Class class1)
        throws IOException, JsonParseException, JsonMappingException
    {
        return readValue(treeAsTokens(jsonnode), class1);
    }

    public ObjectWriter typedWriter(Class class1)
    {
        JavaType javatype;
        if (class1 == null)
        {
            javatype = null;
        } else
        {
            javatype = _typeFactory.constructType(class1);
        }
        return new ObjectWriter(this, copySerializationConfig(), javatype, null);
    }

    public ObjectWriter typedWriter(JavaType javatype)
    {
        return new ObjectWriter(this, copySerializationConfig(), javatype, null);
    }

    public ObjectWriter typedWriter(TypeReference typereference)
    {
        JavaType javatype;
        if (typereference == null)
        {
            javatype = null;
        } else
        {
            javatype = _typeFactory.constructType(typereference);
        }
        return new ObjectWriter(this, copySerializationConfig(), javatype, null);
    }

    public ObjectReader updatingReader(Object obj)
    {
        JavaType javatype = _typeFactory.constructType(obj.getClass());
        return new ObjectReader(this, copyDeserializationConfig(), javatype, obj, null);
    }

    public JsonNode valueToTree(Object obj)
        throws IllegalArgumentException
    {
        if (obj == null)
        {
            return null;
        }
        TokenBuffer tokenbuffer = new TokenBuffer(this);
        JsonNode jsonnode;
        try
        {
            writeValue(tokenbuffer, obj);
            JsonParser jsonparser = tokenbuffer.asParser();
            jsonnode = readTree(jsonparser);
            jsonparser.close();
        }
        catch (IOException ioexception)
        {
            throw new IllegalArgumentException(ioexception.getMessage(), ioexception);
        }
        return jsonnode;
    }

    public Version version()
    {
        return VersionUtil.versionFor(getClass());
    }

    public ObjectWriter viewWriter(Class class1)
    {
        return new ObjectWriter(this, copySerializationConfig().withView(class1));
    }

    public ObjectMapper withModule(Module module)
    {
        registerModule(module);
        return this;
    }

    public void writeTree(JsonGenerator jsongenerator, JsonNode jsonnode)
        throws IOException, JsonProcessingException
    {
        SerializationConfig serializationconfig = copySerializationConfig();
        _serializerProvider.serializeValue(serializationconfig, jsongenerator, jsonnode, _serializerFactory);
        if (serializationconfig.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE))
        {
            jsongenerator.flush();
        }
    }

    public void writeTree(JsonGenerator jsongenerator, JsonNode jsonnode, SerializationConfig serializationconfig)
        throws IOException, JsonProcessingException
    {
        _serializerProvider.serializeValue(serializationconfig, jsongenerator, jsonnode, _serializerFactory);
        if (serializationconfig.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE))
        {
            jsongenerator.flush();
        }
    }

    public void writeValue(File file, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        _configAndWriteValue(_jsonFactory.createJsonGenerator(file, JsonEncoding.UTF8), obj);
    }

    public void writeValue(OutputStream outputstream, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        _configAndWriteValue(_jsonFactory.createJsonGenerator(outputstream, JsonEncoding.UTF8), obj);
    }

    public void writeValue(Writer writer1, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        _configAndWriteValue(_jsonFactory.createJsonGenerator(writer1), obj);
    }

    public void writeValue(JsonGenerator jsongenerator, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        SerializationConfig serializationconfig = copySerializationConfig();
        if (serializationconfig.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable))
        {
            _writeCloseableValue(jsongenerator, obj, serializationconfig);
        } else
        {
            _serializerProvider.serializeValue(serializationconfig, jsongenerator, obj, _serializerFactory);
            if (serializationconfig.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE))
            {
                jsongenerator.flush();
                return;
            }
        }
    }

    public void writeValue(JsonGenerator jsongenerator, Object obj, SerializationConfig serializationconfig)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        if (serializationconfig.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable))
        {
            _writeCloseableValue(jsongenerator, obj, serializationconfig);
        } else
        {
            _serializerProvider.serializeValue(serializationconfig, jsongenerator, obj, _serializerFactory);
            if (serializationconfig.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE))
            {
                jsongenerator.flush();
                return;
            }
        }
    }

    public byte[] writeValueAsBytes(Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        ByteArrayBuilder bytearraybuilder = new ByteArrayBuilder(_jsonFactory._getBufferRecycler());
        _configAndWriteValue(_jsonFactory.createJsonGenerator(bytearraybuilder, JsonEncoding.UTF8), obj);
        byte abyte0[] = bytearraybuilder.toByteArray();
        bytearraybuilder.release();
        return abyte0;
    }

    public String writeValueAsString(Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        SegmentedStringWriter segmentedstringwriter = new SegmentedStringWriter(_jsonFactory._getBufferRecycler());
        _configAndWriteValue(_jsonFactory.createJsonGenerator(segmentedstringwriter), obj);
        return segmentedstringwriter.getAndClear();
    }

    public void writeValueUsingView(OutputStream outputstream, Object obj, Class class1)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        _configAndWriteValue(_jsonFactory.createJsonGenerator(outputstream, JsonEncoding.UTF8), obj, class1);
    }

    public void writeValueUsingView(Writer writer1, Object obj, Class class1)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        _configAndWriteValue(_jsonFactory.createJsonGenerator(writer1), obj, class1);
    }

    public void writeValueUsingView(JsonGenerator jsongenerator, Object obj, Class class1)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        _configAndWriteValue(jsongenerator, obj, class1);
    }

    public ObjectWriter writer()
    {
        return new ObjectWriter(this, copySerializationConfig());
    }

    static 
    {
        DEFAULT_INTROSPECTOR = BasicClassIntrospector.instance;
    }
}
