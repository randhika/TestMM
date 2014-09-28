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
//            AnnotatedMember, AnnotatedField, AnnotatedMethod

public interface VisibilityChecker
{
    public static class Std
        implements VisibilityChecker
    {

        protected static final Std DEFAULT = new Std((JsonAutoDetect)org/codehaus/jackson/map/introspect/VisibilityChecker$Std.getAnnotation(org/codehaus/jackson/annotate/JsonAutoDetect));
        protected final org.codehaus.jackson.annotate.JsonAutoDetect.Visibility _creatorMinLevel;
        protected final org.codehaus.jackson.annotate.JsonAutoDetect.Visibility _fieldMinLevel;
        protected final org.codehaus.jackson.annotate.JsonAutoDetect.Visibility _getterMinLevel;
        protected final org.codehaus.jackson.annotate.JsonAutoDetect.Visibility _isGetterMinLevel;
        protected final org.codehaus.jackson.annotate.JsonAutoDetect.Visibility _setterMinLevel;

        public static Std defaultInstance()
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

        public Std with(JsonAutoDetect jsonautodetect)
        {
            if (jsonautodetect == null)
            {
                return this;
            }
            JsonMethod ajsonmethod[] = jsonautodetect.value();
            org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility;
            Std std;
            org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility1;
            Std std1;
            org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility2;
            Std std2;
            org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility3;
            Std std3;
            org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility4;
            if (hasMethod(ajsonmethod, JsonMethod.GETTER))
            {
                visibility = jsonautodetect.getterVisibility();
            } else
            {
                visibility = org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;
            }
            std = withGetterVisibility(visibility);
            if (hasMethod(ajsonmethod, JsonMethod.IS_GETTER))
            {
                visibility1 = jsonautodetect.isGetterVisibility();
            } else
            {
                visibility1 = org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;
            }
            std1 = std.withIsGetterVisibility(visibility1);
            if (hasMethod(ajsonmethod, JsonMethod.SETTER))
            {
                visibility2 = jsonautodetect.setterVisibility();
            } else
            {
                visibility2 = org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;
            }
            std2 = std1.withSetterVisibility(visibility2);
            if (hasMethod(ajsonmethod, JsonMethod.CREATOR))
            {
                visibility3 = jsonautodetect.creatorVisibility();
            } else
            {
                visibility3 = org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;
            }
            std3 = std2.withCreatorVisibility(visibility3);
            if (hasMethod(ajsonmethod, JsonMethod.FIELD))
            {
                visibility4 = jsonautodetect.fieldVisibility();
            } else
            {
                visibility4 = org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;
            }
            return std3.withFieldVisibility(visibility4);
        }

        public volatile VisibilityChecker with(JsonAutoDetect jsonautodetect)
        {
            return with(jsonautodetect);
        }

        public Std withCreatorVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility)
        {
            if (visibility == org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.DEFAULT)
            {
                visibility = DEFAULT._creatorMinLevel;
            }
            if (_creatorMinLevel == visibility)
            {
                return this;
            } else
            {
                org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility1 = _getterMinLevel;
                org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility2 = _isGetterMinLevel;
                org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility3 = _setterMinLevel;
                org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility4 = _fieldMinLevel;
                return new Std(visibility1, visibility2, visibility3, visibility, visibility4);
            }
        }

        public volatile VisibilityChecker withCreatorVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility)
        {
            return withCreatorVisibility(visibility);
        }

        public Std withFieldVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility)
        {
            if (visibility == org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.DEFAULT)
            {
                visibility = DEFAULT._fieldMinLevel;
            }
            if (_fieldMinLevel == visibility)
            {
                return this;
            } else
            {
                return new Std(_getterMinLevel, _isGetterMinLevel, _setterMinLevel, _creatorMinLevel, visibility);
            }
        }

        public volatile VisibilityChecker withFieldVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility)
        {
            return withFieldVisibility(visibility);
        }

        public Std withGetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility)
        {
            if (visibility == org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.DEFAULT)
            {
                visibility = DEFAULT._getterMinLevel;
            }
            if (_getterMinLevel == visibility)
            {
                return this;
            } else
            {
                org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility1 = _isGetterMinLevel;
                org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility2 = _setterMinLevel;
                org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility3 = _creatorMinLevel;
                org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility4 = _fieldMinLevel;
                return new Std(visibility, visibility1, visibility2, visibility3, visibility4);
            }
        }

        public volatile VisibilityChecker withGetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility)
        {
            return withGetterVisibility(visibility);
        }

        public Std withIsGetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility)
        {
            if (visibility == org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.DEFAULT)
            {
                visibility = DEFAULT._isGetterMinLevel;
            }
            if (_isGetterMinLevel == visibility)
            {
                return this;
            } else
            {
                org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility1 = _getterMinLevel;
                org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility2 = _setterMinLevel;
                org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility3 = _creatorMinLevel;
                org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility4 = _fieldMinLevel;
                return new Std(visibility1, visibility, visibility2, visibility3, visibility4);
            }
        }

        public volatile VisibilityChecker withIsGetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility)
        {
            return withIsGetterVisibility(visibility);
        }

        public Std withSetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility)
        {
            if (visibility == org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.DEFAULT)
            {
                visibility = DEFAULT._setterMinLevel;
            }
            if (_setterMinLevel == visibility)
            {
                return this;
            } else
            {
                org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility1 = _getterMinLevel;
                org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility2 = _isGetterMinLevel;
                org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility3 = _creatorMinLevel;
                org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility4 = _fieldMinLevel;
                return new Std(visibility1, visibility2, visibility, visibility3, visibility4);
            }
        }

        public volatile VisibilityChecker withSetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility)
        {
            return withSetterVisibility(visibility);
        }


        public Std(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility, org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility1, org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility2, org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility3, org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility4)
        {
            _getterMinLevel = visibility;
            _isGetterMinLevel = visibility1;
            _setterMinLevel = visibility2;
            _creatorMinLevel = visibility3;
            _fieldMinLevel = visibility4;
        }

        public Std(JsonAutoDetect jsonautodetect)
        {
            JsonMethod ajsonmethod[] = jsonautodetect.value();
            org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility;
            org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility1;
            org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility2;
            org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility3;
            org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility4;
            if (hasMethod(ajsonmethod, JsonMethod.GETTER))
            {
                visibility = jsonautodetect.getterVisibility();
            } else
            {
                visibility = org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;
            }
            _getterMinLevel = visibility;
            if (hasMethod(ajsonmethod, JsonMethod.IS_GETTER))
            {
                visibility1 = jsonautodetect.isGetterVisibility();
            } else
            {
                visibility1 = org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;
            }
            _isGetterMinLevel = visibility1;
            if (hasMethod(ajsonmethod, JsonMethod.SETTER))
            {
                visibility2 = jsonautodetect.setterVisibility();
            } else
            {
                visibility2 = org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;
            }
            _setterMinLevel = visibility2;
            if (hasMethod(ajsonmethod, JsonMethod.CREATOR))
            {
                visibility3 = jsonautodetect.creatorVisibility();
            } else
            {
                visibility3 = org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;
            }
            _creatorMinLevel = visibility3;
            if (hasMethod(ajsonmethod, JsonMethod.FIELD))
            {
                visibility4 = jsonautodetect.fieldVisibility();
            } else
            {
                visibility4 = org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;
            }
            _fieldMinLevel = visibility4;
        }
    }


    public abstract boolean isCreatorVisible(Member member);

    public abstract boolean isCreatorVisible(AnnotatedMember annotatedmember);

    public abstract boolean isFieldVisible(Field field);

    public abstract boolean isFieldVisible(AnnotatedField annotatedfield);

    public abstract boolean isGetterVisible(Method method);

    public abstract boolean isGetterVisible(AnnotatedMethod annotatedmethod);

    public abstract boolean isIsGetterVisible(Method method);

    public abstract boolean isIsGetterVisible(AnnotatedMethod annotatedmethod);

    public abstract boolean isSetterVisible(Method method);

    public abstract boolean isSetterVisible(AnnotatedMethod annotatedmethod);

    public abstract VisibilityChecker with(JsonAutoDetect jsonautodetect);

    public abstract VisibilityChecker withCreatorVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility);

    public abstract VisibilityChecker withFieldVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility);

    public abstract VisibilityChecker withGetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility);

    public abstract VisibilityChecker withIsGetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility);

    public abstract VisibilityChecker withSetterVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility visibility);
}
