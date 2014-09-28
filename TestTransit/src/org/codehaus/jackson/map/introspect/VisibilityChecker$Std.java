// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.introspect;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;

// Referenced classes of package org.codehaus.jackson.map.introspect:
//            VisibilityChecker, AnnotatedMember, AnnotatedField, AnnotatedMethod

public static class _fieldMinLevel
    implements VisibilityChecker
{

    protected static final withSetterVisibility DEFAULT = new <init>((JsonAutoDetect)org/codehaus/jackson/map/introspect/VisibilityChecker$Std.getAnnotation(org/codehaus/jackson/annotate/JsonAutoDetect));
    protected final org.codehaus.jackson.annotate.ity _creatorMinLevel;
    protected final org.codehaus.jackson.annotate.ity _fieldMinLevel;
    protected final org.codehaus.jackson.annotate.ity _getterMinLevel;
    protected final org.codehaus.jackson.annotate.ity _isGetterMinLevel;
    protected final org.codehaus.jackson.annotate.ity _setterMinLevel;

    public static _fieldMinLevel defaultInstance()
    {
        return DEFAULT;
    }

    private static boolean hasMethod(JsonMethod ajsonmethod[], JsonMethod jsonmethod)
    {
        int i = ajsonmethod.length;
        for (int j = 0; j < i; j++)
        {
            JsonMethod jsonmethod1 = ajsonmethod[j];
            if (jsonmethod1 == jsonmethod || jsonmethod1 == JsonMethod.ALL)
            {
                return true;
            }
        }

        return false;
    }

    public boolean isCreatorVisible(Member member)
    {
        return _creatorMinLevel.isVisible(member);
    }

    public boolean isCreatorVisible(AnnotatedMember annotatedmember)
    {
        return isCreatorVisible(annotatedmember.getMember());
    }

    public boolean isFieldVisible(Field field)
    {
        return _fieldMinLevel.isVisible(field);
    }

    public boolean isFieldVisible(AnnotatedField annotatedfield)
    {
        return isFieldVisible(annotatedfield.getAnnotated());
    }

    public boolean isGetterVisible(Method method)
    {
        return _getterMinLevel.isVisible(method);
    }

    public boolean isGetterVisible(AnnotatedMethod annotatedmethod)
    {
        return isGetterVisible(annotatedmethod.getAnnotated());
    }

    public boolean isIsGetterVisible(Method method)
    {
        return _isGetterMinLevel.isVisible(method);
    }

    public boolean isIsGetterVisible(AnnotatedMethod annotatedmethod)
    {
        return isIsGetterVisible(annotatedmethod.getAnnotated());
    }

    public boolean isSetterVisible(Method method)
    {
        return _setterMinLevel.isVisible(method);
    }

    public boolean isSetterVisible(AnnotatedMethod annotatedmethod)
    {
        return isSetterVisible(annotatedmethod.getAnnotated());
    }

    public String toString()
    {
        return (new StringBuilder("[Visibility:")).append(" getter: ").append(_getterMinLevel).append(", isGetter: ").append(_isGetterMinLevel).append(", setter: ").append(_setterMinLevel).append(", creator: ").append(_creatorMinLevel).append(", field: ").append(_fieldMinLevel).append("]").toString();
    }

    public _fieldMinLevel with(JsonAutoDetect jsonautodetect)
    {
        if (jsonautodetect == null)
        {
            return this;
        }
        JsonMethod ajsonmethod[] = jsonautodetect.value();
        org.codehaus.jackson.annotate.ity ity;
        _fieldMinLevel _lfieldminlevel;
        org.codehaus.jackson.annotate.ity ity1;
        _fieldMinLevel _lfieldminlevel1;
        org.codehaus.jackson.annotate.ity ity2;
        _fieldMinLevel _lfieldminlevel2;
        org.codehaus.jackson.annotate.ity ity3;
        _fieldMinLevel _lfieldminlevel3;
        org.codehaus.jackson.annotate.ity ity4;
        if (hasMethod(ajsonmethod, JsonMethod.GETTER))
        {
            ity = jsonautodetect.getterVisibility();
        } else
        {
            ity = org.codehaus.jackson.annotate.ity.NONE;
        }
        _lfieldminlevel = withGetterVisibility(ity);
        if (hasMethod(ajsonmethod, JsonMethod.IS_GETTER))
        {
            ity1 = jsonautodetect.isGetterVisibility();
        } else
        {
            ity1 = org.codehaus.jackson.annotate.ity.NONE;
        }
        _lfieldminlevel1 = _lfieldminlevel.withIsGetterVisibility(ity1);
        if (hasMethod(ajsonmethod, JsonMethod.SETTER))
        {
            ity2 = jsonautodetect.setterVisibility();
        } else
        {
            ity2 = org.codehaus.jackson.annotate.ity.NONE;
        }
        _lfieldminlevel2 = _lfieldminlevel1.withSetterVisibility(ity2);
        if (hasMethod(ajsonmethod, JsonMethod.CREATOR))
        {
            ity3 = jsonautodetect.creatorVisibility();
        } else
        {
            ity3 = org.codehaus.jackson.annotate.ity.NONE;
        }
        _lfieldminlevel3 = _lfieldminlevel2.withCreatorVisibility(ity3);
        if (hasMethod(ajsonmethod, JsonMethod.FIELD))
        {
            ity4 = jsonautodetect.fieldVisibility();
        } else
        {
            ity4 = org.codehaus.jackson.annotate.ity.NONE;
        }
        return _lfieldminlevel3.withFieldVisibility(ity4);
    }

    public volatile VisibilityChecker with(JsonAutoDetect jsonautodetect)
    {
        return with(jsonautodetect);
    }

    public with withCreatorVisibility(org.codehaus.jackson.annotate.ity ity)
    {
        if (ity == org.codehaus.jackson.annotate.ity.DEFAULT)
        {
            ity = DEFAULT._creatorMinLevel;
        }
        if (_creatorMinLevel == ity)
        {
            return this;
        } else
        {
            org.codehaus.jackson.annotate.ity ity1 = _getterMinLevel;
            org.codehaus.jackson.annotate.ity ity2 = _isGetterMinLevel;
            org.codehaus.jackson.annotate.ity ity3 = _setterMinLevel;
            org.codehaus.jackson.annotate.ity ity4 = _fieldMinLevel;
            return new <init>(ity1, ity2, ity3, ity, ity4);
        }
    }

    public volatile VisibilityChecker withCreatorVisibility(org.codehaus.jackson.annotate.ity ity)
    {
        return withCreatorVisibility(ity);
    }

    public withCreatorVisibility withFieldVisibility(org.codehaus.jackson.annotate.ity ity)
    {
        if (ity == org.codehaus.jackson.annotate.ity.DEFAULT)
        {
            ity = DEFAULT._fieldMinLevel;
        }
        if (_fieldMinLevel == ity)
        {
            return this;
        } else
        {
            return new <init>(_getterMinLevel, _isGetterMinLevel, _setterMinLevel, _creatorMinLevel, ity);
        }
    }

    public volatile VisibilityChecker withFieldVisibility(org.codehaus.jackson.annotate.ity ity)
    {
        return withFieldVisibility(ity);
    }

    public withFieldVisibility withGetterVisibility(org.codehaus.jackson.annotate.ity ity)
    {
        if (ity == org.codehaus.jackson.annotate.ity.DEFAULT)
        {
            ity = DEFAULT._getterMinLevel;
        }
        if (_getterMinLevel == ity)
        {
            return this;
        } else
        {
            org.codehaus.jackson.annotate.ity ity1 = _isGetterMinLevel;
            org.codehaus.jackson.annotate.ity ity2 = _setterMinLevel;
            org.codehaus.jackson.annotate.ity ity3 = _creatorMinLevel;
            org.codehaus.jackson.annotate.ity ity4 = _fieldMinLevel;
            return new <init>(ity, ity1, ity2, ity3, ity4);
        }
    }

    public volatile VisibilityChecker withGetterVisibility(org.codehaus.jackson.annotate.ity ity)
    {
        return withGetterVisibility(ity);
    }

    public withGetterVisibility withIsGetterVisibility(org.codehaus.jackson.annotate.ity ity)
    {
        if (ity == org.codehaus.jackson.annotate.ity.DEFAULT)
        {
            ity = DEFAULT._isGetterMinLevel;
        }
        if (_isGetterMinLevel == ity)
        {
            return this;
        } else
        {
            org.codehaus.jackson.annotate.ity ity1 = _getterMinLevel;
            org.codehaus.jackson.annotate.ity ity2 = _setterMinLevel;
            org.codehaus.jackson.annotate.ity ity3 = _creatorMinLevel;
            org.codehaus.jackson.annotate.ity ity4 = _fieldMinLevel;
            return new <init>(ity1, ity, ity2, ity3, ity4);
        }
    }

    public volatile VisibilityChecker withIsGetterVisibility(org.codehaus.jackson.annotate.ity ity)
    {
        return withIsGetterVisibility(ity);
    }

    public withIsGetterVisibility withSetterVisibility(org.codehaus.jackson.annotate.ity ity)
    {
        if (ity == org.codehaus.jackson.annotate.ity.DEFAULT)
        {
            ity = DEFAULT._setterMinLevel;
        }
        if (_setterMinLevel == ity)
        {
            return this;
        } else
        {
            org.codehaus.jackson.annotate.ity ity1 = _getterMinLevel;
            org.codehaus.jackson.annotate.ity ity2 = _isGetterMinLevel;
            org.codehaus.jackson.annotate.ity ity3 = _creatorMinLevel;
            org.codehaus.jackson.annotate.ity ity4 = _fieldMinLevel;
            return new <init>(ity1, ity2, ity, ity3, ity4);
        }
    }

    public volatile VisibilityChecker withSetterVisibility(org.codehaus.jackson.annotate.ity ity)
    {
        return withSetterVisibility(ity);
    }


    public (org.codehaus.jackson.annotate.ity ity, org.codehaus.jackson.annotate.ity ity1, org.codehaus.jackson.annotate.ity ity2, org.codehaus.jackson.annotate.ity ity3, org.codehaus.jackson.annotate.ity ity4)
    {
        _getterMinLevel = ity;
        _isGetterMinLevel = ity1;
        _setterMinLevel = ity2;
        _creatorMinLevel = ity3;
        _fieldMinLevel = ity4;
    }

    public _fieldMinLevel(JsonAutoDetect jsonautodetect)
    {
        JsonMethod ajsonmethod[] = jsonautodetect.value();
        org.codehaus.jackson.annotate.ity ity;
        org.codehaus.jackson.annotate.ity ity1;
        org.codehaus.jackson.annotate.ity ity2;
        org.codehaus.jackson.annotate.ity ity3;
        org.codehaus.jackson.annotate.ity ity4;
        if (hasMethod(ajsonmethod, JsonMethod.GETTER))
        {
            ity = jsonautodetect.getterVisibility();
        } else
        {
            ity = org.codehaus.jackson.annotate.ity.NONE;
        }
        _getterMinLevel = ity;
        if (hasMethod(ajsonmethod, JsonMethod.IS_GETTER))
        {
            ity1 = jsonautodetect.isGetterVisibility();
        } else
        {
            ity1 = org.codehaus.jackson.annotate.ity.NONE;
        }
        _isGetterMinLevel = ity1;
        if (hasMethod(ajsonmethod, JsonMethod.SETTER))
        {
            ity2 = jsonautodetect.setterVisibility();
        } else
        {
            ity2 = org.codehaus.jackson.annotate.ity.NONE;
        }
        _setterMinLevel = ity2;
        if (hasMethod(ajsonmethod, JsonMethod.CREATOR))
        {
            ity3 = jsonautodetect.creatorVisibility();
        } else
        {
            ity3 = org.codehaus.jackson.annotate.ity.NONE;
        }
        _creatorMinLevel = ity3;
        if (hasMethod(ajsonmethod, JsonMethod.FIELD))
        {
            ity4 = jsonautodetect.fieldVisibility();
        } else
        {
            ity4 = org.codehaus.jackson.annotate.ity.NONE;
        }
        _fieldMinLevel = ity4;
    }
}
