// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.mrbean;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.Versioned;
import org.codehaus.jackson.map.AbstractTypeResolver;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.VersionUtil;

// Referenced classes of package org.codehaus.jackson.mrbean:
//            BeanBuilder

public class AbstractTypeMaterializer extends AbstractTypeResolver
    implements Versioned
{
    public static final class Feature extends Enum
    {

        private static final Feature $VALUES[];
        public static final Feature FAIL_ON_UNMATERIALIZED_METHOD;
        final boolean _defaultState;

        protected static int collectDefaults()
        {
            int i = 0;
            Feature afeature[] = values();
            int j = afeature.length;
            for (int k = 0; k < j; k++)
            {
                Feature feature = afeature[k];
                if (feature.enabledByDefault())
                {
                    i |= feature.getMask();
                }
            }

            return i;
        }

        public static Feature valueOf(String s)
        {
            return (Feature)Enum.valueOf(org/codehaus/jackson/mrbean/AbstractTypeMaterializer$Feature, s);
        }

        public static Feature[] values()
        {
            return (Feature[])$VALUES.clone();
        }

        public boolean enabledByDefault()
        {
            return _defaultState;
        }

        public int getMask()
        {
            return 1 << ordinal();
        }

        static 
        {
            FAIL_ON_UNMATERIALIZED_METHOD = new Feature("FAIL_ON_UNMATERIALIZED_METHOD", 0, false);
            Feature afeature[] = new Feature[1];
            afeature[0] = FAIL_ON_UNMATERIALIZED_METHOD;
            $VALUES = afeature;
        }

        private Feature(String s, int i, boolean flag)
        {
            super(s, i);
            _defaultState = flag;
        }
    }

    private static class MyClassLoader extends ClassLoader
    {

        public Class loadAndResolve(String s, byte abyte0[], Class class1)
            throws IllegalArgumentException
        {
            Class class2 = findLoadedClass(s);
            if (class2 != null && class1.isAssignableFrom(class2))
            {
                return class2;
            }
            Class class3;
            try
            {
                class3 = defineClass(s, abyte0, 0, abyte0.length);
            }
            catch (LinkageError linkageerror)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Failed to load class '").append(s).append("': ").append(linkageerror.getMessage()).toString(), linkageerror);
            }
            resolveClass(class3);
            return class3;
        }

        public MyClassLoader(ClassLoader classloader)
        {
            super(classloader);
        }
    }


    protected static final int DEFAULT_FEATURE_FLAGS = 0;
    public static final String DEFAULT_PACKAGE_FOR_GENERATED = "org.codehaus.jackson.generated.";
    protected final MyClassLoader _classLoader;
    protected String _defaultPackage;
    protected int _featureFlags;

    public AbstractTypeMaterializer()
    {
        this(null);
    }

    public AbstractTypeMaterializer(ClassLoader classloader)
    {
        _featureFlags = DEFAULT_FEATURE_FLAGS;
        _defaultPackage = "org.codehaus.jackson.generated.";
        if (classloader == null)
        {
            classloader = getClass().getClassLoader();
        }
        _classLoader = new MyClassLoader(classloader);
    }

    public void disable(Feature feature)
    {
        _featureFlags = _featureFlags & (-1 ^ feature.getMask());
    }

    public void enable(Feature feature)
    {
        _featureFlags = _featureFlags | feature.getMask();
    }

    public final boolean isEnabled(Feature feature)
    {
        return (_featureFlags & feature.getMask()) != 0;
    }

    protected Class materializeClass(DeserializationConfig deserializationconfig, Class class1)
    {
        String s = (new StringBuilder()).append(_defaultPackage).append(class1.getName()).toString();
        byte abyte0[] = (new BeanBuilder(deserializationconfig, class1)).implement(isEnabled(Feature.FAIL_ON_UNMATERIALIZED_METHOD)).build(s);
        return _classLoader.loadAndResolve(s, abyte0, class1);
    }

    public JavaType resolveAbstractType(DeserializationConfig deserializationconfig, JavaType javatype)
    {
        if (javatype.isContainerType() || javatype.isPrimitive() || javatype.isEnumType() || javatype.isThrowable())
        {
            return null;
        } else
        {
            return deserializationconfig.constructType(materializeClass(deserializationconfig, javatype.getRawClass()));
        }
    }

    public void set(Feature feature, boolean flag)
    {
        if (flag)
        {
            enable(feature);
            return;
        } else
        {
            disable(feature);
            return;
        }
    }

    public void setDefaultPackage(String s)
    {
        if (!s.endsWith("."))
        {
            s = (new StringBuilder()).append(s).append(".").toString();
        }
        _defaultPackage = s;
    }

    public Version version()
    {
        return VersionUtil.versionFor(getClass());
    }

    static 
    {
        DEFAULT_FEATURE_FLAGS = Feature.collectDefaults();
    }
}
