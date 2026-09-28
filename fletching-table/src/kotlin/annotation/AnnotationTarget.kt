package kotlin.annotation

import kotlin.enums.EnumEntries

public enum class AnnotationTarget {
   CLASS,
   ANNOTATION_CLASS,
   TYPE_PARAMETER,
   PROPERTY,
   FIELD,
   LOCAL_VARIABLE,
   VALUE_PARAMETER,
   CONSTRUCTOR,
   FUNCTION,
   PROPERTY_GETTER,
   PROPERTY_SETTER,
   TYPE,
   EXPRESSION,
   FILE,
   @SinceKotlin(version = "1.1")
   TYPEALIAS
   @JvmStatic
   fun getEntries(): EnumEntries<AnnotationTarget> {
      return $ENTRIES;
   }
}
