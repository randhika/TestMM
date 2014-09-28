// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.type;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.map.type:
//            SimpleType, TypeFactory

public class TypeBindings
{

    private static final JavaType NO_TYPES[] = new JavaType[0];
    public static final JavaType UNBOUND = new SimpleType(java/lang/Object);
    protected Map _bindings;
    protected final Class _contextClass;
    protected final JavaType _contextType;
    private final TypeBindings _parentBindings;
    protected HashSet _placeholders;
    protected final TypeFactory _typeFactory;

    public TypeBindings(TypeFactory typefactory, Class class1)
    {
        this(typefactory, null, class1, null);
    }

    private TypeBindings(TypeFactory typefactory, TypeBindings typebindings, Class class1, JavaType javatype)
    {
        _typeFactory = typefactory;
        _parentBindings = typebindings;
        _contextClass = class1;
        _contextType = javatype;
    }

    public TypeBindings(TypeFactory typefactory, JavaType javatype)
    {
        this(typefactory, null, javatype.getRawClass(), javatype);
    }

    public void _addPlaceholder(String s)
    {
        if (_placeholders == null)
        {
            _placeholders = new HashSet();
        }
        _placeholders.add(s);
    }

    protected void _resolve()
    {
        _resolveBindings(_contextClass);
        if (_contextType != null)
        {
            int i = _contextType.containedTypeCount();
            if (i > 0)
            {
                if (_bindings == null)
                {
                    _bindings = new LinkedHashMap();
                }
                for (int j = 0; j < i; j++)
                {
                    String s = _contextType.containedTypeName(j);
                    JavaType javatype = _contextType.containedType(j);
                    _bindings.put(s, javatype);
                }

            }
        }
        if (_bindings == null)
        {
            _bindings = Collections.emptyMap();
        }
    }

    protected void _resolveBindings(Type type)
    {
        if (type != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        ParameterizedType parameterizedtype;
        Type atype1[];
        if (!(type instanceof ParameterizedType))
        {
            break; /* Loop/switch isn't completed */
        }
        parameterizedtype = (ParameterizedType)type;
        atype1 = parameterizedtype.getActualTypeArguments();
        if (atype1 == null || atype1.length <= 0) goto _L4; else goto _L3
_L3:
        TypeVariable atypevariable1[];
        int i1;
        int j1;
        Class class2 = (Class)parameterizedtype.getRawType();
        atypevariable1 = class2.getTypeParameters();
        if (atypevariable1.length != atype1.length)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Strange parametrized type (in class ").append(class2.getName()).append("): number of type arguments != number of type parameters (").append(atype1.length).append(" vs ").append(atypevariable1.length).append(")").toString());
        }
        i1 = 0;
        j1 = atype1.length;
_L8:
        if (i1 >= j1) goto _L4; else goto _L5
_L5:
        String s1 = atypevariable1[i1].getName();
        if (_bindings != null) goto _L7; else goto _L6
_L6:
        _bindings = new LinkedHashMap();
_L10:
        _addPlaceholder(s1);
        _bindings.put(s1, _typeFactory._constructType(atype1[i1], this));
_L9:
        i1++;
          goto _L8
_L7:
        if (!_bindings.containsKey(s1)) goto _L10; else goto _L9
_L4:
        Class class1 = (Class)parameterizedtype.getRawType();
_L14:
        _resolveBindings(class1.getGenericSuperclass());
        Type atype[] = class1.getGenericInterfaces();
        int k = atype.length;
        int l = 0;
        while (l < k) 
        {
            _resolveBindings(atype[l]);
            l++;
        }
        if (true) goto _L1; else goto _L11
_L11:
        if (!(type instanceof Class)) goto _L1; else goto _L12
_L12:
        TypeVariable atypevariable[];
        class1 = (Class)type;
        atypevariable = class1.getTypeParameters();
        if (atypevariable == null || atypevariable.length <= 0) goto _L14; else goto _L13
_L13:
        int i;
        int j;
        i = atypevariable.length;
        j = 0;
_L20:
        if (j >= i) goto _L14; else goto _L15
_L15:
        String s;
        Type type1;
        TypeVariable typevariable = atypevariable[j];
        s = typevariable.getName();
        type1 = typevariable.getBounds()[0];
        if (type1 == null) goto _L17; else goto _L16
_L16:
        if (_bindings != null) goto _L19; else goto _L18
_L18:
        _bindings = new LinkedHashMap();
_L21:
        _addPlaceholder(s);
        _bindings.put(s, _typeFactory._constructType(type1, this));
_L17:
        j++;
          goto _L20
_L19:
        if (!_bindings.containsKey(s)) goto _L21; else goto _L17
    }

    public void addBinding(String s, JavaType javatype)
    {
        if (_bindings == null || _bindings.size() == 0)
        {
            _bindings = new LinkedHashMap();
        }
        _bindings.put(s, javatype);
    }

    public TypeBindings childInstance()
    {
        return new TypeBindings(_typeFactory, this, _contextClass, _contextType);
    }

    public JavaType findType(String s)
    {
        if (_bindings == null)
        {
            _resolve();
        }
        JavaType javatype = (JavaType)_bindings.get(s);
        if (javatype != null)
        {
            return javatype;
        }
        if (_placeholders != null && _placeholders.contains(s))
        {
            return UNBOUND;
        }
        if (_parentBindings != null)
        {
            return _parentBindings.findType(s);
        }
        if (_contextClass != null && _contextClass.getEnclosingClass() != null && !Modifier.isStatic(_contextClass.getModifiers()))
        {
            return UNBOUND;
        }
        String s1;
        if (_contextClass != null)
        {
            s1 = _contextClass.getName();
        } else
        if (_contextType != null)
        {
            s1 = _contextType.toString();
        } else
        {
            s1 = "UNKNOWN";
        }
        throw new IllegalArgumentException((new StringBuilder()).append("Type variable '").append(s).append("' can not be resolved (with context of class ").append(s1).append(")").toString());
    }

    public int getBindingCount()
    {
        if (_bindings == null)
        {
            _resolve();
        }
        return _bindings.size();
    }

    public JavaType resolveType(Class class1)
    {
        return _typeFactory._constructType(class1, this);
    }

    public JavaType resolveType(Type type)
    {
        return _typeFactory._constructType(type, this);
    }

    public String toString()
    {
        if (_bindings == null)
        {
            _resolve();
        }
        StringBuilder stringbuilder = new StringBuilder("[TypeBindings for ");
        if (_contextType != null)
        {
            stringbuilder.append(_contextType.toString());
        } else
        {
            stringbuilder.append(_contextClass.getName());
        }
        stringbuilder.append(": ").append(_bindings).append("]");
        return stringbuilder.toString();
    }

    public JavaType[] typesAsArray()
    {
        if (_bindings == null)
        {
            _resolve();
        }
        if (_bindings.size() == 0)
        {
            return NO_TYPES;
        } else
        {
            return (JavaType[])_bindings.values().toArray(new JavaType[_bindings.size()]);
        }
    }

}
