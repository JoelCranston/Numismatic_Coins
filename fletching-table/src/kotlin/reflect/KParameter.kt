package kotlin.reflect

import kotlin.enums.EnumEntries

public interface KParameter : KAnnotatedElement {
   public val index: Int
   public val name: String?
   public val type: KType
   public val kind: kotlin.reflect.KParameter.Kind
   public val isOptional: Boolean
   public val isVararg: Boolean

   // $VF: Class flags could not be determined
   internal class DefaultImpls

   public enum class Kind {
      INSTANCE,
      @ExperimentalContextParameters
      CONTEXT,
      EXTENSION_RECEIVER,
      VALUE
      @JvmStatic
      fun getEntries(): EnumEntries<KParameter.Kind> {
         return $ENTRIES;
      }
   }
}
